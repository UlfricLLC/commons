package com.ulfric.commons.runtime;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.suite.HelperTestSuite;

public class RuntimeHelperTest extends HelperTestSuite {

	public RuntimeHelperTest() {
		super(RuntimeHelper.class);
	}

	@Test
	void testGetAvailableProcessors() {
		Truth.assertThat(RuntimeHelper.getAvailableProcessors()).isSameAs(Runtime.getRuntime().availableProcessors());
	}

}