package com.ulfric.commons.runtime;

public class RuntimeHelper {

	public static int getAvailableProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}

	public static void addShutdownHook(Thread hook) {
		Runtime.getRuntime().addShutdownHook(hook);
	}

	public static void removeShutdownHook(Thread hook) {
		Runtime.getRuntime().removeShutdownHook(hook);
	}

	private RuntimeHelper() {
	}

}
