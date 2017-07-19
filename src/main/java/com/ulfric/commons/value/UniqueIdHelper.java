package com.ulfric.commons.value;

import java.util.UUID;

public class UniqueIdHelper {

	public static UUID nameUniqueId(String name) {
		return UUID.nameUUIDFromBytes(name.getBytes());
	}

	private UniqueIdHelper() {
	}

}