package com.ulfric.commons.value;

import java.util.Optional;
import java.util.stream.Stream;

public class OptionalHelper {

	public static <T> Stream<T> stream(Optional<T> optional) {
		return optional.isPresent() ? Stream.of(optional.get()) : Stream.empty();
	}

	private OptionalHelper() {
	}

}