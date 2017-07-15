package com.ulfric.commons.nio;
import com.ulfric.tryto.Try;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
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

	public static void createDefaultFile(Path file) {
		if (Files.exists(file)) {
			return;
		}
		createFile(file);

		String resource = ResourceHelper.getResourceText(file.toString());

		if (resource == null) {
			return;
		}

		write(file, resource);
	}

	public static void createFile(Path file) {
		Objects.requireNonNull(file, "file");

		if (Files.isRegularFile(file)) {
			return;
		}

		createParentDirectories(file);
		Try.toRunIo(() -> Files.createFile(file));
	}

	public static void createParentDirectories(Path file) {
		Objects.requireNonNull(file, "file");

		if (Files.exists(file)) {
			return;
		}

		Path parent = file.getParent();
		if (parent == null) {
			return;
		}

		createDirectories(parent);
	}

	public static void createDirectories(Path file) {
		Objects.requireNonNull(file, "file");

		Try.toRunIo(() -> Files.createDirectories(file));
	}

	public static void write(Path file, String value) {
		Objects.requireNonNull(value, "value");

		write(file, value.getBytes());
	}

	public static void write(Path file, byte[] value) {
		Objects.requireNonNull(file, "file");
		Objects.requireNonNull(value, "value");

		Try.toRunIo(() -> Files.write(file, value));
	}

	public static String read(Path file) {
		Objects.requireNonNull(file, "file");

		return new String(Try.toGetIo(() -> Files.readAllBytes(file)));
	}

	public static Instant getLastModified(Path file) {
		return Try.toGetIo(() -> Files.getLastModifiedTime(file)).toInstant();
	}

	private FileHelper() {
	}

}