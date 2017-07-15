package com.ulfric.commons.concurrent;

import com.ulfric.commons.value.Result;

public enum ThreadSleepResult implements Result {

	SUCCESS,
	INTERRUPTED;

	@Override
	public boolean isSuccess() {
		return this == SUCCESS;
	}

}