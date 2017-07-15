package com.ulfric.commons.test;

import org.junit.jupiter.api.Test;

public abstract class EnumTestSuite {

	private final Class<? extends Enum<?>> type;

	public EnumTestSuite(Class<? extends Enum<?>> type) {
		this.type = type;
	}

	@Test
	void testEnumValues() throws Exception {
		type.getMethod("values").invoke(null);
	}

	@Test
	void testValueOf() throws Exception {
		type.getMethod("valueOf", String.class).invoke(null, type.getEnumConstants()[0].name());
	}

}