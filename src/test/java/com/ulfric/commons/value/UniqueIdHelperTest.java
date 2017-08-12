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

	@Test
	void testParseUniqueIdExactInvalid() {
		Truth.assertThat(UniqueIdHelper.parseUniqueIdExact("bad")).isNull();
	}

	@Test
	void testParseUniqueIdExact() {
		UUID uniqueId = UUID.randomUUID();
		Truth.assertThat(UniqueIdHelper.parseUniqueIdExact(uniqueId.toString())).isEqualTo(uniqueId);
	}

	@Test
	void testParseUniqueIdInvalid() {
		Truth.assertThat(UniqueIdHelper.parseUniqueId("bad")).isNull();
	}

	@Test
	void testParseUniqueIdInvalidAndBig() {
		String bad = UUID.randomUUID() + "" + UUID.randomUUID();
		Truth.assertThat(UniqueIdHelper.parseUniqueId(bad)).isNull();
	}

	@Test
	void testParseUniqueIdNo() {
		UUID uniqueId = UUID.randomUUID();
		Truth.assertThat(UniqueIdHelper.parseUniqueId(uniqueId.toString()))
			.isEqualTo(uniqueId);
	}

	@Test
	void testParseUniqueIdNoDashes() {
		UUID uniqueId = UUID.randomUUID();
		Truth.assertThat(UniqueIdHelper.parseUniqueId(uniqueId.toString().replace("-", "")))
			.isEqualTo(uniqueId);
	}

	@Test
	void testParseUniqueIdUnderscores() {
		UUID uniqueId = UUID.randomUUID();
		Truth.assertThat(UniqueIdHelper.parseUniqueId(uniqueId.toString().replace("-", "_")))
			.isEqualTo(uniqueId);
	}

	@Test
	void testParseUniqueIdFirstDashMissing() {
		UUID uniqueId = UUID.randomUUID();
		Truth.assertThat(UniqueIdHelper.parseUniqueId(uniqueId.toString().replaceFirst("-", "")))
			.isEqualTo(uniqueId);
	}

}