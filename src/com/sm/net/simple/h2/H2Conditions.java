package com.sm.net.simple.h2;

public enum H2Conditions {

	EQUALS(0, "Equals"), EQUALS_NOT(1, "Unequals"), GREATER_OR_EQUAL(2, "Greater or equal"), GREATER_THAN(3,
			"Greater than"), LOWER_OR_EQUAL(4, "Lower or equal"), LOWER_THAN(5, "Lower than"), BETWEEN(6, "Between");

	private Integer id;
	private String name;

	private H2Conditions(Integer id, String name) {
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
