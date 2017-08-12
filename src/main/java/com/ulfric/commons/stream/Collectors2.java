package com.ulfric.commons.stream;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Collectors2 {

	public static Collector<String, Object, String> joiningOxfordComma() {
		return joiningOxfordComma("and");
	}

	public static Collector<String, Object, String> joiningOxfordComma(String finalWord) {
		return joiningWithDifferentLastDelimiter(", ", ", " + finalWord + ' ', ' ' + finalWord + ' ');
	}

	public static Collector<String, Object, String> joiningWithDifferentLastDelimiter(
			String delimiter,
			String lastDelimiter,
			String twoElementsDelimiter) {
		return Collectors.collectingAndThen(Collectors.toList(),
				joinDifferentLast(delimiter, lastDelimiter, twoElementsDelimiter));
	}

	private static Function<List<String>, String> joinDifferentLast(
			String delimiter,
			String lastDelimiter,
			String twoElementsDelimiter) {
		return list -> {
			int last = list.size() - 1;

			if (last < 1) {
				return String.join(delimiter, list);
			}

			if (last == 1) {
				return String.join(twoElementsDelimiter, list);
			}

			return String.join(lastDelimiter,
					String.join(delimiter, list.subList(0, last)),
					list.get(last));
		};
	}

	private Collectors2() {
	}

}