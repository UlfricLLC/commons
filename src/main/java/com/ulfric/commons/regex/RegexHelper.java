package com.ulfric.commons.regex;

import java.util.regex.Pattern;

public class RegexHelper {

	public static boolean matches(String stringInQuestion, Pattern pattern) {
		return pattern.matcher(stringInQuestion).matches();
	}

	private RegexHelper() {
	}

}