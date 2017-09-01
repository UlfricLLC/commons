package com.ulfric.commons.text;

import com.ulfric.commons.collection.MapHelper;

import java.util.concurrent.ConcurrentMap;
import java.util.regex.Pattern;

public class RegexHelper {

	private static final ConcurrentMap<String, Pattern> PATTERNS = MapHelper.newConcurrentMap(1);

	public static Pattern compile(String text) {
		return PATTERNS.computeIfAbsent(text, Pattern::compile);
	}

	public static boolean matches(String stringInQuestion, String pattern) {
		return matches(stringInQuestion, compile(pattern));
	}

	public static boolean matches(String stringInQuestion, Pattern pattern) {
		return pattern.matcher(stringInQuestion).matches();
	}

	private RegexHelper() {
	}

}