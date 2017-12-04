package com.ulfric.commons.collection;

import java.util.Collections;
import java.util.List;
import java.util.Set;
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

	public static <R> Collector<R, ?, List<R>> toUnmodifiableList() {
		return Collectors.collectingAndThen(Collectors.toList(), Collections::unmodifiableList);
	}

	public static <R> Collector<R, ?, Set<R>> toUnmodifiableSet() {
		return Collectors.collectingAndThen(Collectors.toSet(), Collections::unmodifiableSet);
	}

	private Collectors2() {
	}

}
