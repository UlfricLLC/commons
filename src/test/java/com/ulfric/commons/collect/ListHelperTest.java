package com.ulfric.commons.collect;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.truth.Truth;

import com.ulfric.commons.collection.ListHelper;
import com.ulfric.veracity.suite.HelperTestSuite;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RunWith(JUnitPlatform.class)
class ListHelperTest extends HelperTestSuite {

	ListHelperTest() {
		super(ListHelper.class);
	}

	@Test
	void testCutToSize() {
		List<Integer> values = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());
		ListHelper.cutToSize(values, 3);
		Truth.assertThat(values).containsExactly(1, 2, 3);
	}

	@Test
	void testCutToSizeLargerCut() {
		List<Integer> values = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());
		ListHelper.cutToSize(values, 10);
		Truth.assertThat(values).hasSize(5);
	}

	@Test
	void testGrowToSize() {
		List<Integer> values = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());
		ListHelper.growToSize(values, 10, value -> value + 1);
		Truth.assertThat(values).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
	}

	@Test
	void testGrowToSizeSmallerGrowth() {
		List<Integer> values = IntStream.rangeClosed(1, 5).boxed().collect(Collectors.toList());
		ListHelper.growToSize(values, 1, value -> value);
		Truth.assertThat(values).hasSize(5);
	}

}