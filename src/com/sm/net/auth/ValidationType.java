package com.sm.net.auth;

public enum ValidationType {
	
	VERY_STRONG(0, "^[A-Za-z](?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[,;.:/*\\-+!$%&@])(?!.*[´`^°])\\S{8,15}$");

	private int ordinal;
	private String pattern;

	ValidationType(int ordinal, String pattern) {
		this.ordinal = ordinal;
		this.pattern = pattern;
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
}
