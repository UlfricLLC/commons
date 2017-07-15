package com.ulfric.commons.nio;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.google.common.truth.Truth;

import com.ulfric.commons.concurrent.ThreadHelper;
import com.ulfric.commons.reflect.FieldHelper;
import com.ulfric.commons.test.FileSystemTestSuite;
import com.ulfric.veracity.Veracity;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.Duration;

@RunWith(JUnitPlatform.class)
class PathWatcherTest extends FileSystemTestSuite {

	private static Field threadField = FieldHelper.getDeclaredField(PathWatcher.class, "THREAD").orElse(null);
	private static Thread originalThread;

	private static Field tickField = FieldHelper.getDeclaredField(PathWatcher.class, "ROUTINE_UPDATE_DELAY").orElse(null);
	private static Duration originalTick;

	@BeforeAll
	static void tickSetup() throws Exception {
		makeMutable(tickField);

		originalTick = (Duration) tickField.get(null);
		tickField.set(null, Duration.ofMillis(1));
	}

	@AfterAll
	static void tickTeardown() throws Exception {
		tickField.set(null, originalTick);
	}

	@BeforeAll
	static void threadSetup() throws Exception {
		makeMutable(threadField);

		originalThread = (Thread) threadField.get(null);
		mockThread();
	}

	@AfterAll
	static void threadTeardown() throws Exception {
		threadField.set(null, originalThread);
	}

	private static void makeMutable(Field field) throws Exception {
		field.setAccessible(true);
		Field modifiers = Field.class.getDeclaredField("modifiers");
		modifiers.setAccessible(true);
		modifiers.setInt(field, field.getModifiers() & ~Modifier.FINAL);
	}

	private static void mockThread() throws Exception {
		threadField.set(null, ThreadHelper.start(originalThread::run, "mock-tick-task"));
	}

	private PathWatcher watcher;
	private Runnable callback;

	@BeforeEach
	void setupWatcher() {
		FileHelper.createFile(file);
		this.watcher = PathWatcher.watch(file);
		callback = Mockito.mock(Runnable.class);
		watcher.callback(callback);
	}

	@Test
	void testWatchNull() {
		Veracity.assertThat(() -> PathWatcher.watch(null)).doesThrow(NullPointerException.class);
	}

	@Test
	void testWatchFile() {
		notifyWatcher();
		Mockito.verify(callback, Mockito.times(1)).run();
	}

	@Test
	void testClosedWatcherIsNotRun() {
		watcher.close();
		notifyWatcher();
		Mockito.verify(callback, Mockito.never()).run();
	}

	@Test
	void testCloseWatcher() {
		watcher.close();
		Truth.assertThat(watcher.isClosed()).isTrue();
	}

	@Test
	void testCloseAllWatchers() {
		PathWatcher.closeAll();
		Truth.assertThat(watcher.isClosed()).isTrue();
	}

	@Test
	void testCloseAlreadyClosed() {
		watcher.close();
		Veracity.assertThat(watcher::close).doesThrow(IllegalStateException.class);
	}

	@Test
	void testPauseWatcher() {
		watcher.pause();
		notifyWatcher();
		Mockito.verify(callback, Mockito.never()).run();
	}

	@Test
	void testPauseResumeWatcher() {
		watcher.pause();
		watcher.resume();
		notifyWatcher();
		Mockito.verify(callback, Mockito.times(1)).run();
	}

	@Test
	void testInterrupt() throws Exception {
		PathWatcher.getUpdateTask().interrupt();
		pause();
		Truth.assertThat(watcher.isClosed()).isTrue();
		mockThread();
	}

	private void notifyWatcher() {
		pause();
		FileHelper.write(file, "anything");
		pause();
	}

	private void pause() {
		ThreadHelper.sleep(Duration.ofMillis(2));
	}

}