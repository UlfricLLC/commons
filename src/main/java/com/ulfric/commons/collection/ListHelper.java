package com.ulfric.commons.collection;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.IntFunction;

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

	public static <E> void growToSize(List<E> list, int newSize, IntFunction<E> supplier) {
		int currentSize = list.size();
		if (newSize <= currentSize) {
			return;
		}

		for (int x = currentSize; x < newSize; x++) {
			list.add(supplier.apply(x));
		}
	}

	private ListHelper() {
	}

}
