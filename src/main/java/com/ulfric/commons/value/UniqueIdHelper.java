package com.ulfric.commons.value;

import java.util.UUID;

public class UniqueIdHelper {

	public static UUID nameUniqueId(String name) {
		return UUID.nameUUIDFromBytes(name.getBytes());
	}

	public static UUID parseUniqueId(String uniqueId) {
		UUID exact = parseUniqueIdExact(uniqueId);

		if (exact != null) {
			return exact;
		}

		if (uniqueId.length() >= 32) {
			return parseInferredUniqueId(uniqueId);
		}

		return null;
	}

	public static UUID parseUniqueIdExact(String uniqueId) {
		try {
			return UUID.fromString(uniqueId);
		} catch (IllegalArgumentException thatsOk) {
			return null;
		}
	}

	private static UUID parseInferredUniqueId(String uniqueId) {
		uniqueId = uniqueId.trim().replace("-", "").replace("_", "");
		if (uniqueId.length() != 32) {
			return null;
		}

		return parseUniqueIdExact(uniqueIdWithDashes(uniqueId));
	}

	private static String uniqueIdWithDashes(String uniqueId) {
		StringBuilder insertable = new StringBuilder(uniqueId.replace("-", ""));

		insertable.insert(20, '-');
		insertable.insert(16, '-');
		insertable.insert(12, '-');
		insertable.insert(8, '-');

		return insertable.toString();
	}

	private UniqueIdHelper() {
	}

}