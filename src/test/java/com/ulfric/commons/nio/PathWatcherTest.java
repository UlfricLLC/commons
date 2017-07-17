package com.ulfric.commons.nio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.google.common.truth.Truth;

import com.ulfric.commons.suite.PathWatcherTestSuite;
import com.ulfric.veracity.Veracity;

@RunWith(JUnitPlatform.class)
class PathWatcherTest extends PathWatcherTestSuite {

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
	void testCloseIfInactiveOnInactive() {
		watcher.removeCallback(callback);
		watcher.closeIfInactive();
		Truth.assertThat(watcher.isClosed()).isTrue();
	}

	@Test
	void testCloseIfInactiveOnActive() {
		watcher.closeIfInactive();
		Truth.assertThat(watcher.isClosed()).isFalse();
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
		notifyWatcher(file, "anything");
	}

}