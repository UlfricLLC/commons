package com.ulfric.commons.value;

public interface Result {

	static Result SUCCESS = () -> true;

	boolean isSuccess();

}