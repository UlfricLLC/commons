package com.ulfric.commons.regex;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.truth.Truth;

import com.ulfric.commons.test.HelperTestSuite;

import java.util.regex.Pattern;

@RunWith(JUnitPlatform.class)
class RegexHelperTest extends HelperTestSuite {

	RegexHelperTest() {
		super(RegexHelper.class);
	}

	@Test
	void testMatches() {
		Truth.assertThat(RegexHelper.matches("abc123", Pattern.compile("[a-z0-9]+"))).isTrue();
	}

}