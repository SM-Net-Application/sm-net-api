package com.sm.net.auth;

public enum ValidationType {

	VERY_STRONG(0, "^[A-Za-z](?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[,;.:/*\\-+!$%&@])(?!.*[´`^°])\\S{8,15}$",
			"- Lunghezza minima: 8\n- Lunghezza massima: 15\n- Lettere maiuscole\n- Lettere minuscole\n- Numeri\n- Simboli");

	private int ordinal;
	private String pattern;
	private String info;

	ValidationType(int ordinal, String pattern, String info) {
		this.ordinal = ordinal;
		this.pattern = pattern;
		this.info = info;
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
}
