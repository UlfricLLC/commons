package com.ulfric.commons.collection;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.function.Supplier;

public class ListHelper {

	public static void cutToSize(List<?> list, int newSize) {
		cutToSize(list, newSize, ignore -> {}); // TODO static "do nothing" consumer
	}

	public static <T> void cutToSize(List<T> list, int newSize, Consumer<T> consumer) {
		int currentSize = list.size();
		if (newSize >= currentSize) {
			return;
		}

		for (int x = currentSize - 1; x >= newSize; x--) {
			consumer.accept(list.remove(x));
		}
	}

	public static <E> void growToSize(List<E> list, int newSize, Supplier<E> supplier) {
		growToSize(list, newSize, ignore -> supplier.get());
	}

	public static <E> void growToSize(List<E> list, int newSize, IntFunction<E> function) {
		int currentSize = list.size();
		if (newSize <= currentSize) {
			return;
		}

		for (int x = currentSize; x < newSize; x++) {
			list.add(function.apply(x));
		}
	}

	public static <E> List<E> asList(Iterable<E> iterable) {
		if (iterable instanceof List) {
			return (List<E>) iterable;
		}

		List<E> list = new ArrayList<>();
		iterable.forEach(list::add);
		return list;
	}

	private ListHelper() {
	}

}
