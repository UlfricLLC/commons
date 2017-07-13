package com.ulfric.commons.nio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import com.google.common.jimfs.Jimfs;
import com.google.common.truth.Truth;

import java.lang.reflect.Constructor;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;

@RunWith(JUnitPlatform.class)
class FileHelperTest {

	private FileSystem fileSystem;
	private Path file;

	@BeforeEach
	void setup() {
		fileSystem = Jimfs.newFileSystem();
		file = fileSystem.getPath("test");
	}

	@Test
	void testDeleteRegularFile() throws Exception {
		Files.createFile(file);
		Truth.assertThat(exists()).isTrue();
		FileHelper.delete(file);
		Truth.assertThat(exists()).isFalse();
	}

	@Test
	void testDeleteNonExistentFile() throws Exception {
		Truth.assertThat(exists()).isFalse();
		FileHelper.delete(file);
		Truth.assertThat(exists()).isFalse();
	}

	@Test
	void testDeleteDirectory() throws Exception {
		Files.createDirectory(file);
		Truth.assertThat(exists()).isTrue();
		FileHelper.delete(file);
		Truth.assertThat(exists()).isFalse();
	}

	@Test
	void testDeleteDirectoryTree() throws Exception {
		Files.createDirectory(file);
		Files.createFile(file.resolve("regularfile"));
		Files.createDirectories(file.resolve("subdirectory1").resolve("subdirectory2"));

		Truth.assertThat(exists()).isTrue();
		FileHelper.delete(file);
		Truth.assertThat(exists()).isFalse();
	}

	@Test
	void testDeleteNull() throws Exception {
		Assertions.assertThrows(NullPointerException.class,
				() -> FileHelper.delete(null));
	}

	@Test
	void testInstantiation() throws Exception {
		Constructor<?> constructor = FileHelper.class.getDeclaredConstructors()[0];
		constructor.setAccessible(true);
		constructor.newInstance();
	}

	private boolean exists() {
		return Files.exists(file);
	}

}