package com.ulfric.commons.reflect;

import java.lang.reflect.Field;
import java.util.Optional;

public class FieldHelper {

	public static Optional<Field> getDeclaredField(Class<?> type, String name) {
		try {
			Field field = type.getDeclaredField(name);
			field.setAccessible(true);
			return Optional.of(field);
		} catch (NoSuchFieldException | SecurityException ignore) {
			return Optional.empty();
		}
	}

	private FieldHelper() {
	}

}