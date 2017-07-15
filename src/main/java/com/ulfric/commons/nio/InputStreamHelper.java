package com.ulfric.commons.nio;

import com.ulfric.tryto.Try;

import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

public class InputStreamHelper {

	public static String getAsString(InputStream stream) {
		Objects.requireNonNull(stream, "stream");

		return Try.toApplyAutoclose(stream, input ->
			Try.toApplyAutoclose(new Scanner(input).useDelimiter("\\A"), Scanner::next)
		);
	}

	private InputStreamHelper() {
	}

}