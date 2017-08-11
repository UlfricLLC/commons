package com.ulfric.commons.text;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.suite.HelperTestSuite;

import java.util.regex.Pattern;

class RegexHelperTest extends HelperTestSuite {

	RegexHelperTest() {
		super(RegexHelper.class);
	}

	@Test
	void testMatches() {
		Truth.assertThat(RegexHelper.matches("abc123", Pattern.compile("[a-z0-9]+"))).isTrue();
	}

	@Test
	void testCompileMatches() {
		Truth.assertThat("a").matches(RegexHelper.compile("[a-z]"));
	}

}