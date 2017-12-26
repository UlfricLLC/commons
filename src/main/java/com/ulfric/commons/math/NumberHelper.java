package com.ulfric.commons.math;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.OptionalInt;

import org.apache.commons.lang3.math.NumberUtils;

public class NumberHelper {

	public static OptionalInt parseInt(String string) {
		try {
			string = string.replace(",", "");
			Integer value = NumberUtils.createInteger(string);
			if (value == null) {
				return OptionalInt.empty();
			}
			return OptionalInt.of(value);
		} catch (NumberFormatException notNumber) {
			return OptionalInt.empty();
		}
	}

	public static Optional<BigDecimal> parseBigDecimal(String string) {
		try {
			string = string.replace(",", "");
			BigDecimal value = NumberUtils.createBigDecimal(string);
			return Optional.ofNullable(value);
		} catch (NumberFormatException notNumber) {
			return Optional.empty();
		}
	}

	public static boolean isNegative(BigDecimal number) {
		return number.compareTo(BigDecimal.ZERO) < 0;
	}

	public static boolean isPositive(BigDecimal number) {
		return number.compareTo(BigDecimal.ZERO) > 0;
	}

	private NumberHelper() {
	}

}