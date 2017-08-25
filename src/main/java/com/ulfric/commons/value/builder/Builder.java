package com.ulfric.commons.value.builder;

public interface Builder<T extends Buildable<T>> {

	T build();

}