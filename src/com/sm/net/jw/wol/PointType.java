package com.sm.net.jw.wol;

public enum PointType {

	POINT(0, "Point"), VIDEO(1, "Video"), TEXT(2, "Text");

	private Integer id;
	private String type;

	private PointType(Integer id, String type) {
		this.id = id;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
