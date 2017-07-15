package com.ulfric.commons.concurrent;

import java.time.Duration;
import java.util.Objects;
import java.util.function.Supplier;

public class ThreadHelper {

	public static Thread start(Runnable runnable) {
		Objects.requireNonNull(runnable, "runnable");

		return start(() -> new Thread(runnable));
	}

	public static Thread start(Runnable runnable, String name) {
		Objects.requireNonNull(runnable, "runnable");
		Objects.requireNonNull(name, "name");

		return start(() -> new Thread(runnable, name));
	}

	public static <T extends Thread> T start(Supplier<T> supplier) {
		Objects.requireNonNull(supplier, "supplier");

		T thread = supplier.get();
		thread.start();
		return thread;
	}

	public static ThreadSleepResult sleep(Duration duration) {
		Objects.requireNonNull(duration, "duration");

		try {
			Thread.sleep(duration.toMillis());
			return ThreadSleepResult.SUCCESS;
		} catch (InterruptedException ignore) {
			return ThreadSleepResult.INTERRUPTED;
		}
	}

	private ThreadHelper() {
	}

}
