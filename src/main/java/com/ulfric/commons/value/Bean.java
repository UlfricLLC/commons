package com.ulfric.commons.value;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.ulfric.commons.json.JsonHelper;

public class Bean {

	@Override
	public boolean equals(Object that) {
		return EqualsBuilder.reflectionEquals(this, that);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return toJson();
	}

	public final String toJson() {
		return JsonHelper.toJson(this);
	}

}