package com.ulfric.commons.time;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;

public class TemporalHelper {

	private static final Clock CLOCK = Clock.systemUTC();

	public static Instant instantNow() {
		return CLOCK.instant();
	}

	public static Instant instantNowPlus(TemporalAmount amount) {
		return instantNow().plus(amount);
	}

	public static Duration betweenNowAnd(Temporal end) {
		return Duration.between(instantNow(), end);
	}

	public static long millisFrom(TemporalAmount amount) {
		return durationFrom(amount).toMillis();
	}

	public static Duration durationFrom(TemporalAmount amount) {
		if (amount instanceof Duration) {
			return (Duration) amount;
		}

		return Duration.from(amount);
	}

	private TemporalHelper() {
	}

}