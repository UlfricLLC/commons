package com.ulfric.commons.json;

import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.jimfs.Jimfs;
import com.google.common.truth.Truth;
import com.google.gson.JsonElement;

import com.ulfric.commons.nio.FileHelper;
import com.ulfric.commons.value.Bean;
import com.ulfric.veracity.suite.HelperTestSuite;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

@RunWith(JUnitPlatform.class)
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