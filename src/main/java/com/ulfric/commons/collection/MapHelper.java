package com.ulfric.commons.collection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapHelper {

	public static <K, V> ConcurrentMap<K, V> newConcurrentMap(int concurrencyLevel) {
		return new ConcurrentHashMap<>(10, 0.8F, concurrencyLevel);
	}

	private MapHelper() {
	}

}