package com.ulfric.commons.reflect;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth8;

import com.ulfric.veracity.suite.HelperTestSuite;

import java.lang.reflect.Field;

class FieldHelperTest extends HelperTestSuite {

	FieldHelperTest() {
		super(FieldHelper.class);
	}

	@Test
	void testGetMissingField() {
		Truth8.assertThat(FieldHelper.getDeclaredField(this.getClass(), "notRealField")).isEmpty();
	}

	@Test
	void testGetValidField() {
		Truth8.assertThat(FieldHelper.getDeclaredField(this.getClass(), "testField")).isPresent();
	}

	Field testField;

}