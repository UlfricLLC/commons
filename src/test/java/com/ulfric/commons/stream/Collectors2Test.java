package com.ulfric.commons.stream;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.Veracity;
import com.ulfric.veracity.suite.HelperTestSuite;

import java.util.List;
import java.util.stream.Stream;

class Collectors2Test extends HelperTestSuite {

	Collectors2Test() {
		super(Collectors2.class);
	}

	@Test
	void testToUnmodifiableList() {
		List<String> list = Stream.of("hello").collect(Collectors2.toUnmodifiableList());
		Truth.assertThat(list).containsExactly("hello");
	}

	@Test
	void testToUnmodifiableListIsImmutable() {
		List<String> list = Stream.of("hello").collect(Collectors2.toUnmodifiableList());
		Veracity.assertThat(() -> list.add("")).doesThrow(UnsupportedOperationException.class);
	}

}