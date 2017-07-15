package com.ulfric.commons.test;

import org.junit.jupiter.api.BeforeEach;

import com.google.common.jimfs.Jimfs;

import java.nio.file.FileSystem;
import java.nio.file.Path;

public abstract class FileSystemTestSuite {

	protected FileSystem fileSystem;
	protected Path file;

	@BeforeEach
	final void setupFileSystem() {
		fileSystem = Jimfs.newFileSystem();
		this.file = fileSystem.getPath("file");
	}

}