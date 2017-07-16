package com.ulfric.commons.nio;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathHelper {

	public static Path defaultPath() {
		return Paths.get("");
	}

	private PathHelper() {
	}

}