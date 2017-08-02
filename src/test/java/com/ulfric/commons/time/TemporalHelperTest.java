package com.ulfric.commons.time;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import com.google.common.truth.Truth;

import com.ulfric.veracity.Veracity;
import com.ulfric.veracity.suite.HelperTestSuite;

import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.temporal.TemporalAmount;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Collections;

@RunWith(JUnitPlatform.class)
class TemporalHelperTest extends HelperTestSuite {

	TemporalHelperTest() {
		super(TemporalHelper.class);
	}

	@Test
	void testNowIsNow() {
		long nowMillis = Instant.now().toEpochMilli();
		long thatMillis = TemporalHelper.instantNow().toEpochMilli();

		Truth.assertThat(thatMillis - nowMillis).isAtMost(1L);
	}

	@Test
	void testNowPlusDuration() {
		long nowMillis = Instant.now().toEpochMilli() + 10;
		long nowPlusTenMillis = TemporalHelper.instantNowPlus(Duration.ofMillis(10)).toEpochMilli();

		Truth.assertThat(nowPlusTenMillis - nowMillis).isAtMost(1L);
	}

	@Test
	void testMillisFromDuration() {
		long millis = TemporalHelper.millisFrom(Duration.ofMillis(5));
		Truth.assertThat(millis).isEqualTo(5);
	}

	@Test
	void testMillisFromPeriodThrowsException() {
		Veracity.assertThat(() -> TemporalHelper.millisFrom(Period.ofDays(1)))
			.doesThrow(UnsupportedTemporalTypeException.class);
	}

	@Test
	void testDurationFromPeriodThrowsException() {
		Veracity.assertThat(() -> TemporalHelper.durationFrom(Period.ofDays(1)))
			.doesThrow(UnsupportedTemporalTypeException.class);
	}

	@Test
	void testDurationFromNullThrowsException() {
		Veracity.assertThat(() -> TemporalHelper.durationFrom(null))
			.doesThrow(NullPointerException.class);
	}

	@Test
	void testDurationFromDuration() {
		Duration duration = Duration.ofMillis(5);
		Truth.assertThat(TemporalHelper.durationFrom(duration)).isSameAs(duration);
	}

	@Test
	void testDurationFromEmptyMock() {
		TemporalAmount mock = Mockito.mock(TemporalAmount.class);
		Mockito.when(mock.getUnits()).thenReturn(Collections.emptyList());
		Truth.assertThat(TemporalHelper.durationFrom(mock)).isEqualTo(Duration.ZERO);
	}

	@Test
	void testBetweenNowAnd5MinsFromNow() {
		Duration fiveMinutes = TemporalHelper.betweenNowAnd(Instant.now().plus(Duration.ofMinutes(5)));
		Veracity.assertThat(fiveMinutes).isEqualToWithinMarginOfError(Duration.ofMinutes(5));
	}

	@Test
	void testBetween5MinsAgoAndNow() {
		Duration fiveMinutes = TemporalHelper.betweenStartAndNow(Instant.now().minus(Duration.ofMinutes(5)));
		Veracity.assertThat(fiveMinutes).isEqualToWithinMarginOfError(Duration.ofMinutes(5));
	}

}