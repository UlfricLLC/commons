package com.ulfric.commons.value;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.JsonElement;

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
		try {
			return toJson().toString();
		} catch (UnsupportedOperationException thatsOk) {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
		}
	}

	public final JsonElement toJson() {
		return JsonHelper.toJsonObject(this);
	}

}