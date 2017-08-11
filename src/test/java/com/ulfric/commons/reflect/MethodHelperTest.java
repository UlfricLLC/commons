package com.ulfric.commons.reflect;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth8;

import com.ulfric.veracity.suite.HelperTestSuite;

class MethodHelperTest extends HelperTestSuite {

	MethodHelperTest() {
		super(MethodHelper.class);
	}

	@Test
	void testGetMissingMethod() {
		Truth8.assertThat(MethodHelper.getDeclaredMethod(Object.class, "notRealMethod")).isEmpty();
	}

	@Test
	void testGetValidMethod() {
		Truth8.assertThat(MethodHelper.getDeclaredMethod(Object.class, "hashCode")).isPresent();
	}

}