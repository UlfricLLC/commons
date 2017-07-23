package com.ulfric.commons.collect;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.commons.collection.MapHelper;
import com.ulfric.veracity.suite.HelperTestSuite;

public class MapHelperTest extends HelperTestSuite {

	public MapHelperTest() {
		super(MapHelper.class);
	}

	@Test
	void testNewConcurrentMap() {
		Truth.assertThat(MapHelper.newConcurrentMap(3)).isNotNull();
	}

}