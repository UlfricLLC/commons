package com.ulfric.commons.reflect;

import java.lang.reflect.Field;
import java.util.Optional;

public class FieldHelper {

	public static Optional<Field> getDeclaredField(Class<?> type, String name) {
		try {
			return Optional.of(type.getDeclaredField(name));
		} catch (NoSuchFieldException | SecurityException ignore) {
			return Optional.empty();
		}
	}

	private FieldHelper() {
	}

}