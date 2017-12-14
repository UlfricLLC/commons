package com.ulfric.commons.time;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
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

	public static Instant instantNowMinus(TemporalAmount amount) {
		return instantNow().minus(amount);
	}

	public static Duration betweenNowAnd(Temporal end) {
		return Duration.between(instantNow(), end);
	}

	public static Duration betweenStartAndNow(Temporal start) {
		return Duration.between(start, instantNow());
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

	public static ZoneId newYorkOrDefault() {
		try {
			return ZoneId.of("America/New_York");
		} catch (Exception thatsOk) {
			return ZoneId.systemDefault();
		}
	}

	private TemporalHelper() {
	}

}