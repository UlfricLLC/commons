package com.ulfric.commons.nio;

import com.ulfric.tryto.TryTo;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class InputStreamHelper {

	public static String getAsString(InputStream stream) {
		Objects.requireNonNull(stream, "stream");

		return TryTo.applyAutoclose(stream, input ->
			TryTo.applyAutoclose(new Scanner(input).useDelimiter("\\A"), Scanner::next)
		);
	}

	private InputStreamHelper() {
	}

}