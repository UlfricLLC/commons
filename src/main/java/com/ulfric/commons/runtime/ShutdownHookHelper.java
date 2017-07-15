package com.ulfric.commons.runtime;

import java.util.Objects;

public class ShutdownHookHelper {

	public static ShutdownHook registerShutdownHook(Runnable callback) {
		return registerShutdownHook(callback, "shutdown-hook");
	}

	public static ShutdownHook registerShutdownHook(Runnable callback, String name) {
		Objects.requireNonNull(callback, "callback");
		Objects.requireNonNull(name, "name");

		ShutdownHook hook = new ShutdownHook(new Thread(callback, name));
		hook.register();
		return hook;
	}

	private ShutdownHookHelper() {
	}

}