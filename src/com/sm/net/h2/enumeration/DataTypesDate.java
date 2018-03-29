package com.sm.net.h2.enumeration;

public enum DataTypesDate {

	DATE(0, "DATE"), TIMESTAMP(1, "TIMESTAMP"), TIMESTAMP_WITH_TIME_ZONE(2, "TIMESTAMP WITH TIME ZONE");

	private Integer id;
	private String name;

	private DataTypesDate(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return getName();
	}
}