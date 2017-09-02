package com.ulfric.commons.collection;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class Computations {

	public static <T> List<T> newArrayListIgnoring(Object ignore) {
		return new ArrayList<>();
	}

	public static <K, V> Map<K, V> newIdentityHashMapIgnoring(Object ignore) {
		return new IdentityHashMap<>();
	}

	private Computations() {
	}

}