package com.ulfric.commons.concurrent;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.suite.EnumTestSuite;

class ThreadSleepResultTest extends EnumTestSuite {

	ThreadSleepResultTest() {
		super(ThreadSleepResult.class);
	}

	@Test
	void testIsSuccessOnSuccess() {
		Truth.assertThat(ThreadSleepResult.SUCCESS.isSuccess()).isTrue();
	}

	@Test
	void testIsSuccessOnInterrupted() {
		Truth.assertThat(ThreadSleepResult.INTERRUPTED.isSuccess()).isFalse();
	}

}