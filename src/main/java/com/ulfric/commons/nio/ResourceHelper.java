package com.ulfric.commons.nio;

import java.io.InputStream;

public class ResourceHelper {

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