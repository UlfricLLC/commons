package com.ulfric.commons.text;

import com.github.slugify.Slugify;

import com.ulfric.commons.collection.Collectors2;

import java.util.Collection;

public class StringHelper {

	private static final Slugify SLUGIFY = new Slugify();

	public static String joinWithOxfordComma(Collection<String> collection) {
		return collection.stream().collect(Collectors2.joiningOxfordComma());
	}

	public static String joinWithOxfordComma(Collection<String> collection, String finalWord) {
		return collection.stream().collect(Collectors2.joiningOxfordComma(finalWord));
	}

	public static String toSlug(String string) {
		return SLUGIFY.slugify(string);
	}

	private StringHelper() {
	}

}