package com.ulfric.commons.reflect;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;
import com.google.common.truth.Truth8;

import com.ulfric.veracity.suite.HelperTestSuite;

import java.lang.reflect.Method;

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

	@Test
	void testIsStatic() {
		Method method = MethodHelper.getDeclaredMethod(MethodHelperTest.class, "staticMethod").get();
		Truth.assertThat(MethodHelper.isStatic(method)).isTrue();
	}

	@Test
	void testReturnsVoid() {
		Method method = MethodHelper.getDeclaredMethod(MethodHelperTest.class, "staticMethod").get();
		Truth.assertThat(MethodHelper.returnsVoid(method)).isTrue();
	}

	static void staticMethod() {
	}

}