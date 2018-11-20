package com.sm.net.auth;

public class Authenticator {

	public static boolean isValid(String s, ValidationType validationType) {
		return s.matches(validationType.getPattern());
	}
}
