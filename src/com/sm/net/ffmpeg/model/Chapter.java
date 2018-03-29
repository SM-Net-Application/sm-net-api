package com.sm.net.ffmpeg.model;

import java.math.BigDecimal;

public class Chapter {

	private String title;
	private BigDecimal start;
	private BigDecimal end;

	public Chapter() {
		super();
		this.title = "";
		this.start = BigDecimal.ZERO;
		this.end = BigDecimal.ZERO;
	}

	public Chapter(String title, BigDecimal start, BigDecimal end) {
		super();
		this.title = title;
		this.start = start;
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getStart() {
		return start;
	}

	public void setStart(BigDecimal start) {
		this.start = start;
	}

	public BigDecimal getEnd() {
		return end;
	}

	public void setEnd(BigDecimal end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return getTitle();
	}

}