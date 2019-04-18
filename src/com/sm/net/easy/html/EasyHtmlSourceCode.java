package com.sm.net.easy.html;

import java.util.ArrayList;

public class EasyHtmlSourceCode {

	private String htmlSourceCode;
	private ArrayList<String> htmlSourceCodeRows;
	private ArrayList<String> htmlCleanSourceCodeRows;

	public EasyHtmlSourceCode(String url) {
		super();

		this.htmlSourceCode = "";
		this.htmlSourceCodeRows = new ArrayList<>();
		this.htmlCleanSourceCodeRows = new ArrayList<>();

		if (EasyHtml.isValidUrl(url))
			this.htmlSourceCode = EasyHtml.getSourceCode(url);

		htmlSourceCodeHandle();
		htmlCleanSourceCodeHandle();
	}

	private void htmlSourceCodeHandle() {

		for (String s : htmlSourceCode.split("\n"))
			htmlSourceCodeRows.add(s);
	}

	private void htmlCleanSourceCodeHandle() {

		for (String s : htmlSourceCodeRows) {
			String row = EasyHtml.replaceNoBreakSpace(s);
			row = EasyHtml.removeLineBreak(row);
			row = row.trim();
			if (!row.isEmpty())
				htmlCleanSourceCodeRows.add(row);
		}
	}

	public void printHtmlSourceCodeRows() {
		for (String s : htmlSourceCodeRows)
			System.out.println(s);
	}

	public void printHtmlCleanSourceCodeRows() {
		for (String s : htmlCleanSourceCodeRows)
			System.out.println(s);
	}

	public String getHtmlSourceCode() {
		return htmlSourceCode;
	}

	public void setHtmlSourceCode(String htmlSourceCode) {
		this.htmlSourceCode = htmlSourceCode;
	}

	public ArrayList<String> getHtmlSourceCodeRows() {
		return htmlSourceCodeRows;
	}

	public void setHtmlSourceCodeRows(ArrayList<String> htmlSourceCodeRows) {
		this.htmlSourceCodeRows = htmlSourceCodeRows;
	}

	public ArrayList<String> getHtmlCleanSourceCodeRows() {
		return htmlCleanSourceCodeRows;
	}

	public void setHtmlCleanSourceCodeRows(ArrayList<String> htmlCleanSourceCodeRows) {
		this.htmlCleanSourceCodeRows = htmlCleanSourceCodeRows;
	}
}
