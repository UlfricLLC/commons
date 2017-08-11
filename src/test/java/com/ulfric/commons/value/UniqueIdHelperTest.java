package com.ulfric.commons.value;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.suite.HelperTestSuite;

import java.util.UUID;

class UniqueIdHelperTest extends HelperTestSuite {

	UniqueIdHelperTest() {
		super(UniqueIdHelper.class);
	}

	@Test
	void testNameUniqueId() {
		Truth.assertThat(UniqueIdHelper.nameUniqueId("hello"))
			.isEqualTo(UUID.nameUUIDFromBytes("hello".getBytes()));
	}

}