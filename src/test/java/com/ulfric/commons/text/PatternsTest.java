package com.ulfric.commons.text;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.suite.HelperTestSuite;

class PatternsTest extends HelperTestSuite {

	PatternsTest() {
		super(Patterns.class);
	}

	@Test
	void testSplitsOnNewLine() {
		Truth.assertThat(Patterns.NEW_LINE.split("hello\nworld")).asList().containsExactly("hello", "world");
	}

}
