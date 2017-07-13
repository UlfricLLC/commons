package com.ulfric.commons.nio;

import com.ulfric.tryto.Try;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class FileHelper {

	public static void delete(Path file) {
		Objects.requireNonNull(file, "file");

		if (Files.notExists(file)) {
			return;
		}

		Try.toRunIo(() -> {
			if (Files.isRegularFile(file)) {
				Files.delete(file);
				return;
			}

			Files.walkFileTree(file, new DeletionFileVisitor());
		});
	}

	private FileHelper() {
	}

}