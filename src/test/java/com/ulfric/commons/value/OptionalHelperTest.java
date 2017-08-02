package com.ulfric.commons.value;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.truth.Truth8;

import com.ulfric.veracity.suite.HelperTestSuite;

import java.util.Optional;
import java.util.stream.Stream;

@RunWith(JUnitPlatform.class)
class OptionalHelperTest extends HelperTestSuite {

	OptionalHelperTest() {
		super(OptionalHelper.class);
	}

	@Test
	void testStreamOfPresent() {
		Optional<Object> optional = Optional.of(new Object());
		Stream<Object> stream = OptionalHelper.stream(optional);
		Truth8.assertThat(stream).hasSize(1);
	}

	@Test
	void testStreamOfEmpty() {
		Optional<Object> optional = Optional.empty();
		Stream<Object> stream = OptionalHelper.stream(optional);
		Truth8.assertThat(stream).isEmpty();
	}

}