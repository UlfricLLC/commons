package com.ulfric.commons.nio;

import org.apache.commons.lang3.StringUtils;

import com.ulfric.commons.text.RegexHelper;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ResourceHelper {

	private static final Pattern NEW_LINE = RegexHelper.compile("\n");

	public static List<String> getResourceLines(String resource) {
		String text = getResourceText(resource);

		if (text == null) {
			return Collections.emptyList();
		}

		return Arrays.stream(NEW_LINE.split(text)).map(StringUtils::chomp).collect(Collectors.toList());
	}

	public static String getResourceText(String resource) {
		InputStream stream =
				Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);

		if (stream == null) {
			return null;
		}

		return InputStreamHelper.getAsString(stream);
	}

	private ResourceHelper() {
	}

}