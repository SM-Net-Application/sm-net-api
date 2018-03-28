package com.sm.net.h2.enumeration;

public enum DataTypesNumber {

	INT(0, "INT"), SMALLINT(1, "SMALLINT"), BIGINT(2, "BIGINT"), DECIMAL(3, "DECIMAL"), DOUBLE(4, "DOUBLE"), REAL(5,
			"REAL");

	private Integer id;
	private String name;

	private DataTypesNumber(Integer id, String name) {
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