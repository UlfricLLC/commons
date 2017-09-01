package com.ulfric.commons.value;

import org.junit.jupiter.api.Test;

import com.google.common.truth.Truth;

class ResultTest {

	@Test
	void testSuccessIsTrue() {
		Truth.assertThat(Result.SUCCESS.isSuccess()).isTrue();
	}

}
