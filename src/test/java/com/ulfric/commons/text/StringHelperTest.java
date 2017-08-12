package com.ulfric.commons.text;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.suite.HelperTestSuite;

import java.util.Arrays;
import java.util.Collections;

class StringHelperTest extends HelperTestSuite {

	StringHelperTest() {
		super(StringHelper.class);
	}

	@Test
	void testJoinWithOxfordCommaEmpty() {
		Truth.assertThat(StringHelper.joinWithOxfordComma(Collections.emptyList())).isEmpty();
	}

	@Test
	void testJoinWithOxfordCommaSingleton() {
		Truth.assertThat(StringHelper.joinWithOxfordComma(Collections.singleton("hello"))).isEqualTo("hello");
	}

	@Test
	void testJoinWithOxfordCommaTwo() {
		Truth.assertThat(StringHelper.joinWithOxfordComma(Arrays.asList("hello", "world")))
			.isEqualTo("hello and world");
	}

	@Test
	void testJoinWithOxfordCommaThree() {
		Truth.assertThat(StringHelper.joinWithOxfordComma(Arrays.asList("hello", "world", "apples")))
			.isEqualTo("hello, world, and apples");
	}

	@Test
	void testJoinWithOxfordCommaTwoSpecialLast() {
		Truth.assertThat(StringHelper.joinWithOxfordComma(Arrays.asList("hello", "world"), "or"))
			.isEqualTo("hello or world");
	}

	@Test
	void testJoinWithOxfordCommaThreeSpecialLast() {
		Truth.assertThat(StringHelper.joinWithOxfordComma(Arrays.asList("hello", "world", "apples"), "or"))
			.isEqualTo("hello, world, or apples");
	}

}