package com.ulfric.commons.value;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.google.common.truth.Truth;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

class BeanTest {

	private final Gson gson = new Gson();
	private SimpleBean bean;

	@BeforeEach
	void setup() {
		bean = new SimpleBean();
	}

	@Test
	void testToEmptyJson() {
		JsonElement emptyJson = bean.toJson();
		Truth.assertThat(emptyJson).isEqualTo(gson.toJsonTree(bean));
	}

	@Test
	void testToPopulatedJson() {
		populateDefaults(bean);

		JsonElement populatedJson = bean.toJson();
		Truth.assertThat(populatedJson).isEqualTo(gson.toJsonTree(bean));
	}

	@Test
	void testToPopulatedJsonIsDifferentThanEmptyJson() {
		JsonElement emptyJson = bean.toJson();

		populateDefaults(bean);

		JsonElement populatedJson = bean.toJson();
		Truth.assertThat(populatedJson).isNotEqualTo(emptyJson);
	}

	@Test
	void testToJsonRoundtrip() {
		populateDefaults(bean);
		JsonElement json = bean.toJson();

		SimpleBean roundtrip = gson.fromJson(json, SimpleBean.class);
		Truth.assertThat(roundtrip).isNotSameAs(bean);
		Truth.assertThat(roundtrip).isEqualTo(bean);
	}

	@Test
	void testToStringEqualsToJson() {
		populateDefaults(bean);

		Truth.assertThat(bean.toString()).isEqualTo(bean.toJson().toString());
	}

	@Test
	void testHashCodeOnEqualBeansIsSame() {
		populateDefaults(bean);
		SimpleBean second = new SimpleBean();
		populateDefaults(second);

		Truth.assertThat(second.hashCode()).isEqualTo(bean.hashCode());
	}

	@Test
	void testIllegalToStringFallback() {
		Naughty naughty = new Naughty();
		naughty.data = Mockito.mock(DontSerializeMeBro.class);
		Truth.assertThat(naughty.toString()).isEqualTo("{\"data\":\"{0}\"}".replace("{0}", naughty.data.toString()));
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

	static class Naughty extends Bean {
		private DontSerializeMeBro data;

		public DontSerializeMeBro getData() {
			return data;
		}

		public void setData(DontSerializeMeBro data) {
			this.data = data;
		}
	}

	interface DontSerializeMeBro {
	}

}