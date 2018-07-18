package com.sm.net.simple.h2;

public enum H2DataTypes {

	INT(0, "INT"), BOOLEAN(1, "BOOLEAN"), DECIMAL(2, "DECIMAL"), DATE(3, "DATE"), VARCHAR(4, "VARCHAR");

	private Integer id;
	private String name;

	private H2DataTypes(Integer id, String name) {
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
}
