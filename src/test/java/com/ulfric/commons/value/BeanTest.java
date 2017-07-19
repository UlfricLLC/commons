package com.ulfric.commons.value;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.truth.Truth;
import com.google.gson.Gson;

import com.ulfric.commons.value.Bean;

@RunWith(JUnitPlatform.class)
class BeanTest {

	private final Gson gson = new Gson();
	private SimpleBean bean;

	@BeforeEach
	void setup() {
		bean = new SimpleBean();
	}

	@Test
	void testToEmptyJson() {
		String emptyJson = bean.toJson();
		Truth.assertThat(emptyJson).isEqualTo(gson.toJson(bean));
	}

	@Test
	void testToPopulatedJson() {
		populateDefaults(bean);

		String populatedJson = bean.toJson();
		Truth.assertThat(populatedJson).isEqualTo(gson.toJson(bean));
	}

	@Test
	void testToPopulatedJsonIsDifferentThanEmptyJson() {
		String emptyJson = bean.toJson();

		populateDefaults(bean);

		String populatedJson = bean.toJson();
		Truth.assertThat(populatedJson).isNotEqualTo(emptyJson);
	}

	@Test
	void testToJsonRoundtrip() {
		populateDefaults(bean);
		String json = bean.toJson();

		SimpleBean roundtrip = gson.fromJson(json, SimpleBean.class);
		Truth.assertThat(roundtrip).isNotSameAs(bean);
		Truth.assertThat(roundtrip).isEqualTo(bean);
	}

	@Test
	void testToStringEqualsToJson() {
		populateDefaults(bean);

		Truth.assertThat(bean.toString()).isEqualTo(bean.toJson());
	}

	@Test
	void testHashCodeOnEqualBeansIsSame() {
		populateDefaults(bean);
		SimpleBean second = new SimpleBean();
		populateDefaults(second);

		Truth.assertThat(second.hashCode()).isEqualTo(bean.hashCode());
	}

	private void populateDefaults(SimpleBean bean) {
		bean.setBool(true);
		bean.setInteger(5);
		bean.setString("Hello");
	}

	static class SimpleBean extends Bean {
		private Integer integer;
		private String string;
		private Boolean bool;

		public Integer getInteger() {
			return integer;
		}

		public void setInteger(Integer integer) {
			this.integer = integer;
		}

		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}

		public Boolean getBool() {
			return bool;
		}

		public void setBool(Boolean bool) {
			this.bool = bool;
		}
	}

}