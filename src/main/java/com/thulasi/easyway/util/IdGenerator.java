package com.thulasi.easyway.util;

import java.security.SecureRandom;

public class IdGenerator {

	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static final SecureRandom random = new SecureRandom();

	public static String generateId(String prefix) {
		StringBuilder sb = new StringBuilder(prefix);

		for (int i = 0; i < 10; i++) {
			sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
		}
		return sb.toString();
	}

}
