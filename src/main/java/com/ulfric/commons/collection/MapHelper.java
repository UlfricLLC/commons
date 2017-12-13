package com.ulfric.commons.collection;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapHelper {

	public static <K, V> ConcurrentMap<K, V> newConcurrentMap(int concurrencyLevel) {
		return new ConcurrentHashMap<>(10, 0.8F, concurrencyLevel);
	}

	public static <K, V> Map<K, V> unmodifiableCopy(Map<K, V> map) {
		return Collections.unmodifiableMap(new HashMap<>(map));
	}

	public static int size(Map<?, ?> map) {
		return map == null ? 0 : map.size();
	}

	private MapHelper() {
	}

}