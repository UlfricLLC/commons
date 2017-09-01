package com.ulfric.commons.reflect;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.suite.HelperTestSuite;

class TypeHelperTest extends HelperTestSuite {

	TypeHelperTest() {
		super(TypeHelper.class);
	}

	@Test
	void testIsVoidPrimitive() {
		Truth.assertThat(TypeHelper.isVoid(void.class)).isTrue();
	}

	@Test
	void testIsVoidType() {
		Truth.assertThat(TypeHelper.isVoid(Void.class)).isTrue();
	}

	@Test
	void testIsVoidNotVoid() {
		Truth.assertThat(TypeHelper.isVoid(Object.class)).isFalse();
	}

}
