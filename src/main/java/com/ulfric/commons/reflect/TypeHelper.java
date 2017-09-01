package com.ulfric.commons.reflect;

public class TypeHelper {

	public static boolean isVoid(Class<?> type) {
		return type == Void.class || type == void.class;
	}

	private TypeHelper() {
	}

}
