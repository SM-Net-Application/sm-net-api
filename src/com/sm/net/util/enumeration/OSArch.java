package com.sm.net.util.enumeration;

public enum OSArch {

	BIT32(0, "32 bit"), BIT64(1, "64 bit");

	private Integer id;
	private String text;

	private OSArch(Integer id, String text) {
		this.id = id;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
