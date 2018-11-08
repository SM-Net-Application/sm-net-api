package com.sm.net.util.enumeration;

public enum JSONStatus {

	NO_STATUS(Integer.valueOf(-1), "No status"), OK(Integer.valueOf(0), "Query execute"), POSTED_JSON_EMPTY(
			Integer.valueOf(1), "The JSON parameter is empty"), DECODED_JSON_EMPTY(Integer.valueOf(2),
					"The decoded JSON parameter is empty"), JSON_TYPE_ERROR(Integer.valueOf(3),
							"Unable to determine the type of JSON parameter"), DB_CONN_ERROR(Integer.valueOf(4),
									"MySQL connection error"), DB_RESULT_EMPTY(Integer.valueOf(5), "Query no result");

	private Integer id;
	private String text;

	private JSONStatus(Integer id, String text) {
		this.id = id;
		this.text = text;
	}

	public static JSONStatus getFromId(Integer id) {
		for (JSONStatus enu : JSONStatus.values())
			if (enu.getId().compareTo(id) == 0)
				return enu;
		return null;
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
