package com.ulfric.commons.naming;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

import com.ulfric.veracity.Veracity;

class NamedTest {

	@Test
	void testGetNameFromAnnotationNoAnnotation() {
		Veracity.assertThat(() -> Named.getNameFromAnnotation(new Named() { })).doesThrow(NullPointerException.class);
	}

	@Test
	void testGetNameFromAnnotation() {
		Truth.assertThat(new Hello().getName()).isEqualTo("Hello");
	}

	@Name("Hello")
	static class Hello implements Named {
	}

}
