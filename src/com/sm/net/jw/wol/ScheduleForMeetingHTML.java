package com.sm.net.jw.wol;

import java.time.LocalDate;
import java.util.ArrayList;

import com.sm.net.easy.html.EasyHtml;
import com.sm.net.project.Language;

/**
 * 
 * @author SM-Net <https://sm-netzwerk.com/>
 *
 */
public class ScheduleForMeetingHTML {

	private Language language;
	private LocalDate date;
	private ArrayList<String> relevantHTMLRows;
	private ArrayList<String> relevantRows;

	public ScheduleForMeetingHTML(Language language, LocalDate date) {
		super();
		this.language = language;
		this.date = date;
	}

	/**
	 * Download the HTML code from WOL
	 */
	public void download() {

		String wolLink = WatchtowerOnlineLibrary.createLink(this.language, this.date);
		if (!wolLink.isEmpty()) {
			String sourceCode = EasyHtml.getSourceCode(wolLink);
			this.relevantHTMLRows = WatchtowerOnlineLibrary.relevantHTMLRows(sourceCode);
			defineRelevantRows();
		}
	}

	/**
	 * Define relevant rows from HTML rows
	 */
	private void defineRelevantRows() {

		this.relevantRows = new ArrayList<>();

		for (String row : this.relevantHTMLRows)
			this.relevantRows.add(EasyHtml.removeTag(EasyHtml.replaceNoBreakSpace(row)).trim());
	}

	/**
	 * Print relevant HTML rows
	 */
	public void printRelevantHTMLRows() {

		if (this.relevantHTMLRows != null)
			for (String row : relevantHTMLRows)
				System.out.println(row);
		else
			System.out.println("Relevant HTML rows: null");
	}

	/**
	 * Print relevant rows
	 */
	public void printRelevantRows() {

		if (this.relevantRows != null)
			for (String row : relevantRows)
				System.out.println(row);
		else
			System.out.println("Relevant rows: null");
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ArrayList<String> getRelevantHTMLRows() {
		return relevantHTMLRows;
	}

	public void setRelevantHTMLRows(ArrayList<String> relevantHTMLRows) {
		this.relevantHTMLRows = relevantHTMLRows;
	}

	public ArrayList<String> getRelevantRows() {
		return relevantRows;
	}

	public void setRelevantRows(ArrayList<String> relevantRows) {
		this.relevantRows = relevantRows;
	}

}
