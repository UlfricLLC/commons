package com.ulfric.commons.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Optional;

public class MethodHelper {

	public static Optional<Method> getDeclaredMethod(Class<?> type, String name, Class<?>... parameterTypes) {
		try {
			return Optional.of(type.getDeclaredMethod(name, parameterTypes));
		} catch (NoSuchMethodException | SecurityException ignore) {
			return Optional.empty();
		}
	}

	public static boolean returnsVoid(Method method) {
		return TypeHelper.isVoid(method.getReturnType());
	}

	public static boolean isStatic(Method method) {
		return Modifier.isStatic(method.getModifiers());
	}

	private MethodHelper() {
	}

}