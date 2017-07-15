package com.ulfric.commons.nio;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.truth.Truth;

import com.ulfric.commons.test.HelperTestSuite;

import java.util.UUID;

@RunWith(JUnitPlatform.class)
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
		Truth.assertThat(ResourceHelper.getResourceText("test.txt")).isEqualTo("test123");
	}

}