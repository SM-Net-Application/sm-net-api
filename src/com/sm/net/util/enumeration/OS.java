package com.sm.net.util.enumeration;

public enum OS {

	WINDOWS(0, "Windows"), LINUX(1, "Linux"), MAC(2, "Mac");

	private Integer id;
	private String text;

	private OS(Integer id, String text) {
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
