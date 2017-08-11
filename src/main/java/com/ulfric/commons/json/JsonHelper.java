package com.ulfric.commons.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.ulfric.tryto.TryTo;

import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.function.Supplier;

public class JsonHelper {

	private static final Gson GSON = new Gson();

	public static <T> T read(Path json, Class<T> as) {
		return TryTo.applyAutoclose(TryTo.getIo(() -> Files.newBufferedReader(json)),
				reader -> read(reader, as));
	}

	public static <T> T read(Reader json, Class<T> as) {
		return getOrDefault(() -> GSON.fromJson(json, as), as);
	}

	public static <T> T read(String json, Class<T> as) {
		return getOrDefault(() -> GSON.fromJson(json, as), as);
	}

	public static <T> T read(JsonElement json, Type as, Class<T> defaultType) {
		return getOrDefault(() -> GSON.fromJson(json, as), defaultType);
	}

	public static <T> T read(JsonElement json, Class<T> as) {
		return getOrDefault(() -> GSON.fromJson(json, as), as);
	}

	private static <T> T getOrDefault(Supplier<T> supplier, Class<T> as) {
		T result = supplier.get();
		if (result == null) {
			return GSON.fromJson(new JsonObject(), as);
		}
		return result;
	}

	public static <T> T override(JsonElement json, T into) {
		Class<?> beanType = into.getClass();

		Gson gson = new GsonBuilder()
				.registerTypeAdapter(beanType, new InstanceCreator<Object>() {

					@Override
					public Object createInstance(Type type) {
						return into;
					}

				}).create();

		gson.fromJson(json, beanType);

		return into;
	}

	public static String toJson(Object source) {
		return GSON.toJson(source);
	}

	public static JsonElement toJsonObject(Object source) {
		return GSON.toJsonTree(source);
	}

	private JsonHelper() {
	}

}
