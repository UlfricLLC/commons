package com.ulfric.commons.value;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.apache.commons.lang3.reflect.FieldUtils;

public class BeanHelper {

	public static void nullify(Object bean) {
		if (bean == null) {
			return;
		}

		for (Field field : FieldUtils.getAllFieldsList(bean.getClass())) {
			if (field.isEnumConstant() || Modifier.isStatic(field.getModifiers()) || field.getType().isPrimitive()) {
				continue;
			}

			field.setAccessible(true);
			try {
				field.set(bean, null);
			} catch (IllegalArgumentException | IllegalAccessException exception) {
				throw new RuntimeException(exception);
			}
		}
	}

	private BeanHelper() {
	}

}
