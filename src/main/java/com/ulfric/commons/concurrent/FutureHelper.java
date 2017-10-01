package com.ulfric.commons.concurrent;

import java.util.concurrent.CompletableFuture;

public class FutureHelper {

	private static final CompletableFuture<?> EMPTY = CompletableFuture.completedFuture(null);

	@SuppressWarnings("unchecked")
	public static <T> CompletableFuture<T> emptyCompletableFuture() {
		return (CompletableFuture<T>) EMPTY;
	}

	private FutureHelper() {
	}

}
