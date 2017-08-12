package com.ulfric.commons.nio;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.suite.HelperTestSuite;

import java.util.UUID;

class ResourceHelperTest extends HelperTestSuite {

	ResourceHelperTest() {
		super(ResourceHelper.class);
	}

	@Test
	void testGetMissingResource() {
		Truth.assertThat(ResourceHelper.getResourceText(UUID.randomUUID().toString())).isNull();
	}

	@Test
	void testGetTestResource() {
		Truth.assertThat(ResourceHelper.getResourceText("test.txt")).isEqualTo("test123\n2ndLine");
	}

	@Test
	void testGetMissingResourceLines() {
		Truth.assertThat(ResourceHelper.getResourceLines(UUID.randomUUID().toString())).isEmpty();
	}

	@Test
	void testGetTestResourceLines() {
		Truth.assertThat(ResourceHelper.getResourceLines("test.txt")).containsExactly("test123", "2ndLine");
	}

}