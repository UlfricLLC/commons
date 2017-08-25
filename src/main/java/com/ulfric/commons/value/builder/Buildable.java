package com.ulfric.commons.value.builder;

public interface Buildable<T extends Buildable<T>> {

	Builder<T> copyToBuilder();

}