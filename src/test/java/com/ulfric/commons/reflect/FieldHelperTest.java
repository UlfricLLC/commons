package com.ulfric.commons.reflect;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.truth.Truth8;

import com.ulfric.commons.test.HelperTestSuite;

import java.lang.reflect.Field;

@RunWith(JUnitPlatform.class)
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