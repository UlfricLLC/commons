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
import java.util.function.Consumer;

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

	private final List<Consumer<Path>> callbacks = new ArrayList<>();
	private final Path path;
	private Instant lastCallback;
	private volatile boolean closed;
	private volatile boolean paused;

	private PathWatcher(Path path) {
		this.path = path;
		this.lastCallback = Instant.now();
	}

	@Override
	public synchronized void close() {
		if (closed) {
			throw new IllegalStateException("Already closed");
		}

		this.closed = true;
		WATCHERS.remove(path, this);
	}

	public synchronized boolean isClosed() {
		return closed;
	}

	private synchronized void routineUpdate() {
		if (this.paused) {
			return;
		}

		Instant lastCallback = this.lastCallback;
		Instant lastModified = FileHelper.getLastModified(path);
		if (lastCallback.isBefore(lastModified)) {
			this.lastCallback = lastModified;
			this.update();
		}
	}

	public synchronized void update() {
		this.callbacks.forEach(callback -> callback.accept(path));
	}

	public synchronized void pause() {
		this.paused = true;
	}

	public synchronized void resume() {
		this.paused = false;
	}

	public synchronized void callback(Runnable callback) {
		Objects.requireNonNull(callback);

		callback(ignore -> callback.run());
	}

	public synchronized void callback(Consumer<Path> callback) {
		Objects.requireNonNull(callback, "callback");

		this.callbacks.add(callback);
	}

}