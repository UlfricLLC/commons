package com.ulfric.commons.reflect;

import java.lang.reflect.AnnotatedElement;

public class AnnotationHelper {

	public static int countDirectAnnotations(AnnotatedElement element) {
		return element.getAnnotations().length;
	}

	private AnnotationHelper() {
	}

}