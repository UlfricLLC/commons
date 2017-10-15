package com.ulfric.commons.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class FutureHelper {

	private static final CompletableFuture<?> EMPTY = CompletableFuture.completedFuture(null);

	@SuppressWarnings("unchecked")
	public static <T> CompletableFuture<T> emptyCompletableFuture() {
		return (CompletableFuture<T>) EMPTY;
	}

	@SafeVarargs
	public static <T> CompletableFuture<List<T>> allOf(CompletableFuture<T>... futures) {
		return CompletableFuture.allOf(futures)
				.thenApply(ignore -> Arrays.stream(futures)
						.map(CompletableFuture::join)
						.collect(Collectors.toList()));
	}

	private FutureHelper() {
	}

}
