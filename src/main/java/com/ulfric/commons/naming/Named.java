package com.ulfric.commons.naming;

import java.util.Objects;

public interface Named {

	static String getNameFromAnnotation(Named named) {
		Objects.requireNonNull(named, "named");

		Name name = named.getClass().getDeclaredAnnotation(Name.class);
		Objects.requireNonNull(name, "name");

		return name.value();
	}

	default String getName() {
		return Named.getNameFromAnnotation(this);
	}

}