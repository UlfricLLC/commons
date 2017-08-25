package com.ulfric.commons.properties;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.suite.HelperTestSuite;

import java.nio.file.Paths;
import java.util.Properties;

class PropertiesHelperTest extends HelperTestSuite {

	PropertiesHelperTest() {
		super(PropertiesHelper.class);
	}

	@Test
	void testLoadProperties() {
		Properties properties = PropertiesHelper.loadProperties(Paths.get("src/test/resources/test.properties"));
		Truth.assertThat(properties).containsEntry("hello", "world!");
	}

}