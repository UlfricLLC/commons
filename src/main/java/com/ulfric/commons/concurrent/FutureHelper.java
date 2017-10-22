package com.ulfric.commons.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

public class FutureHelper {

	public static <T> CompletableFuture<T> empty() {
		return CompletableFuture.completedFuture(null);
	}

	@SafeVarargs
	public static <T> CompletableFuture<List<T>> allOf(CompletableFuture<T>... futures) {
		return CompletableFuture.allOf(futures)
				.thenApply(ignore -> Arrays.stream(futures)
						.map(CompletableFuture::join)
						.collect(Collectors.toList()));
	}

	public static <T> T getUnchecked(Future<T> future, long timeout, TimeUnit unit) {
		 try {
			return future.get(timeout, unit);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			throw new RuntimeException(e);
		}
	}

	private FutureHelper() {
	}

}
