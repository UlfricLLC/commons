package com.ulfric.commons.test;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.Veracity;

import java.lang.reflect.Constructor;
import java.util.Objects;

public abstract class HelperTestSuite {

	private final Class<?> type;

	public HelperTestSuite(Class<?> type) {
		Objects.requireNonNull(type);
		this.type = type;
	}

	@Test
	void testHasOneConstructor() {
		Truth.assertThat(type.getDeclaredConstructors()).hasLength(1);
	}

	@Test
	void testConstructorIsPrivate() {
		Veracity.assertThat(type.getDeclaredConstructors()[0]).isPrivate();
	}

	@Test
	void testNewInstance() {
		Veracity.assertThat(() -> {
			Constructor<?> constructor = type.getDeclaredConstructors()[0];
			constructor.setAccessible(true);
			constructor.newInstance();
		}).runsWithoutExceptions();
	}

}