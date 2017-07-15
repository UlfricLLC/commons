package com.ulfric.commons.nio;

import com.ulfric.commons.concurrent.ThreadHelper;

import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public final class PathWatcher implements AutoCloseable {

	private static final Thread THREAD = newTask();
	private static final Duration ROUTINE_UPDATE_DELAY = Duration.ofSeconds(30);
	private static final ConcurrentMap<Path, PathWatcher> WATCHERS = new ConcurrentHashMap<>();

	private static Thread newTask() {
		return ThreadHelper.start(() -> {
			while (ThreadHelper.sleep(ROUTINE_UPDATE_DELAY).isSuccess()) {
				WATCHERS.values().forEach(PathWatcher::routineUpdate);
			}

			PathWatcher.closeAll();
		}, "pathwatcher");
	}

	public static Thread getUpdateTask() {
		return THREAD;
	}

	public static PathWatcher watch(Path path) {
		Objects.requireNonNull(path, "path");

		return WATCHERS.computeIfAbsent(path, PathWatcher::new);
	}

	public static void closeAll() {
		WATCHERS.values().forEach(PathWatcher::close);
	}

	private final List<Runnable> callbacks = new ArrayList<>();
	private final Path path;
	private Instant lastCallback;
	private volatile boolean closed;
	private volatile boolean paused;

	private PathWatcher(Path path) {
		this.path = path;
		this.lastCallback = Instant.now();
	}

	public synchronized void closeIfInactive() {
		if (callbacks.isEmpty()) {
			close();
		}
	}

	@Override
	public synchronized void close() {
		if (closed) {
			throw new IllegalStateException("Already closed");
		}

		closed = true;
		WATCHERS.remove(path, this);
	}

	public synchronized boolean isClosed() {
		return closed;
	}

	private synchronized void routineUpdate() {
		if (paused) {
			return;
		}

		Instant lastCallback = this.lastCallback;
		Instant lastModified = FileHelper.getLastModified(path);
		if (lastCallback.isBefore(lastModified)) {
			this.lastCallback = lastModified;
			update();
		}
	}

	public synchronized void update() {
		callbacks.forEach(Runnable::run);
	}

	public synchronized void pause() {
		paused = true;
	}

	public synchronized void resume() {
		paused = false;
	}

	public synchronized void callback(Runnable callback) {
		Objects.requireNonNull(callback, "callback");

		callbacks.add(callback);
	}

	public synchronized void removeCallback(Runnable callback) {
		callbacks.remove(callback);
	}

}