package com.ulfric.commons.runtime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.common.truth.Truth;

import com.ulfric.veracity.suite.HelperTestSuite;

class ShutdownHookHelperTest extends HelperTestSuite {

	ShutdownHookHelperTest() {
		super(ShutdownHookHelper.class);
	}

	private Runnable callback;
	private ShutdownHook hook;

	@BeforeEach
	void setupHook() {
		this.callback = Mockito.mock(Runnable.class);
		this.hook = ShutdownHookHelper.registerShutdownHook(callback);
	}

	@AfterEach
	void teardownHook() {
		hook.unregister();
	}

	@Test
	void testRegisterRunnable() {
		ShutdownHook hook = ShutdownHookHelper.registerShutdownHook(() -> {});
		Truth.assertThat(hook.isRegistered()).isTrue();
	}

	@Test
	void testUnregisterRunnable() {
		ShutdownHook hook = ShutdownHookHelper.registerShutdownHook(() -> {});
		hook.unregister();
		Truth.assertThat(hook.isRegistered()).isFalse();
	}

	@Test
	void testReregisterRunnable() {
		ShutdownHook hook = ShutdownHookHelper.registerShutdownHook(() -> {});
		hook.unregister();
		hook.register();
		Truth.assertThat(hook.isRegistered()).isTrue();
	}

	@Test
	void testDoubleReregisterRunnable() {
		ShutdownHook hook = ShutdownHookHelper.registerShutdownHook(() -> {});
		hook.register();
		Truth.assertThat(hook.isRegistered()).isTrue();
	}

	@Test
	void testDoubleUnregisterRunnable() {
		ShutdownHook hook = ShutdownHookHelper.registerShutdownHook(() -> {});
		hook.unregister();
		hook.unregister();
		Truth.assertThat(hook.isRegistered()).isFalse();
	}

}