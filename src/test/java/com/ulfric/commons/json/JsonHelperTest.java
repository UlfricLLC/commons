package com.ulfric.commons.json;

import org.junit.jupiter.api.Test;

import org.apache.commons.lang3.reflect.TypeUtils;

import com.google.common.jimfs.Jimfs;
import com.google.common.truth.Truth;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.ulfric.commons.nio.FileHelper;
import com.ulfric.commons.value.Bean;
import com.ulfric.veracity.suite.HelperTestSuite;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

class JsonHelperTest extends HelperTestSuite {

	JsonHelperTest() {
		super(JsonHelper.class);
	}

	@Test
	void testReadPath() {
		Path file = getTestFile();
		HelloBean hello = JsonHelper.read(file, HelloBean.class);
		Truth.assertThat(hello.getHello()).isEqualTo("hello!");
	}

	@Test
	void testReadReader() throws Exception {
		try (Reader reader = Files.newBufferedReader(getTestFile())) {
			HelloBean hello = JsonHelper.read(reader, HelloBean.class);
			Truth.assertThat(hello.getHello()).isEqualTo("hello!");
		}
	}

	@Test
	void testReadJsonElement() {
		JsonElement helloJson = JsonHelper.read("{\"hello\":\"hello!\"}", JsonElement.class);
		HelloBean hello = JsonHelper.read(helloJson, HelloBean.class);
		Truth.assertThat(hello.getHello()).isEqualTo("hello!");
	}

	@Test
	void testReadSpecialType() {
		JsonObject mapJson = JsonHelper.read("{\"key\":\"value\"}", JsonObject.class);
		Map<String, String> map = JsonHelper.read(mapJson,
				TypeUtils.parameterize(Map.class, String.class, String.class), Map.class);
		Truth.assertThat(map.get("key")).isEqualTo("value");
	}

	@Test
	void testReadString() {
		HelloBean hello = JsonHelper.read("{\"hello\":\"hello!\"}", HelloBean.class);
		Truth.assertThat(hello.getHello()).isEqualTo("hello!");
	}

	@Test
	void testReadEmptyString() {
		HelloBean hello = JsonHelper.read("", HelloBean.class);
		Truth.assertThat(hello.getHello()).isNull();
	}

	@Test
	void testToJsonObject() {
		HelloBean hello = JsonHelper.read("{\"hello\":\"hello!\"}", HelloBean.class);
		hello = JsonHelper.read(JsonHelper.toJsonObject(hello), HelloBean.class);
		Truth.assertThat(hello.getHello()).isEqualTo("hello!");
	}

	@Test
	void testOverride() {
		HelloBean hello = new HelloBean();
		hello.setHello("hola");
		JsonHelper.override(JsonHelper.read("{\"hello\":\"hello!\"}", JsonElement.class), hello);
		Truth.assertThat(hello.getHello()).isEqualTo("hello!");
	}

	private Path getTestFile() {
		Path file = Jimfs.newFileSystem().getPath("test");
		FileHelper.write(file, "{\"hello\":\"hello!\"}");
		return file;
	}

	static class HelloBean extends Bean {
		private String hello;

		public String getHello() {
			return hello;
		}

		public void setHello(String hello) {
			this.hello = hello;
		}
	}

}