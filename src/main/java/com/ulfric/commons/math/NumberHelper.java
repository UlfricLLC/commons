package com.ulfric.commons.math;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.OptionalInt;

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

	private NumberHelper() {
	}

}