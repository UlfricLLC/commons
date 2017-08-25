package com.ulfric.commons.properties;

import com.ulfric.commons.nio.FileHelper;
import com.ulfric.tryto.TryTo;

import java.nio.file.Path;
import java.util.Properties;

public class PropertiesHelper {

	public static Properties loadProperties(Path file) {
		return TryTo.applyAutoclose(FileHelper.newBufferedReader(file), reader -> {
			Properties properties = new Properties();
			properties.load(reader);
			return properties;
		});
	}

	private PropertiesHelper() {
	}

}