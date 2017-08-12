package com.ulfric.commons.text;

import com.ulfric.commons.stream.Collectors2;

import java.util.Collection;

public class StringHelper {

	public static String joinWithOxfordComma(Collection<String> collection) {
		return collection.stream().collect(Collectors2.joiningOxfordComma());
	}

	public static String joinWithOxfordComma(Collection<String> collection, String finalWord) {
		return collection.stream().collect(Collectors2.joiningOxfordComma(finalWord));
	}

	private StringHelper() {
	}

}