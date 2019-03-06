package com.sm.net.jw.wol;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sm.net.project.Language;

public class ScheduleForMeeting {

	private String bibleChapters;
	private Song song1;
	private Part openingComments;
	private Part treasuresTalk;
	private Part treasuresDigging;

	public ScheduleForMeeting(ArrayList<String> relevantRows, Language language) {
		super();

		defineFirstInfo(relevantRows, language);
		defineTreasures(relevantRows, language);
	}

	/**
	 * Define Song 1, bible chapters and opening comments
	 * 
	 * @param relevantRows
	 * @param language
	 */
	private void defineFirstInfo(ArrayList<String> relevantRows, Language language) {

		// Start point: Song 1 index
		int index = find(relevantRows, language.getString(WatchtowerOnlineLibrary.LanguageKey.SONG1));
		if (isValid(relevantRows, index)) {
			this.song1 = new Song(relevantRows.get(index));

			// Bible chapters
			index -= 1;
			if (isValid(relevantRows, index)) {
				this.bibleChapters = relevantRows.get(index);

				// Opening comments
				index += 2;
				if (isValid(relevantRows, index))
					this.openingComments = new Part(relevantRows.get(index));
			}
		}
	}

	private void defineTreasures(ArrayList<String> relevantRows, Language language) {

		// Treasures from God's word index + 1
		int talkIndex = find(relevantRows, language.getString(WatchtowerOnlineLibrary.LanguageKey.TREASURES));
		// Digging for Spiritual Gems index
		int diggingIndex = find(relevantRows, language.getString(WatchtowerOnlineLibrary.LanguageKey.DIGGING));
		// Bible Reading
		int bibleReadingIndex = find(relevantRows,
				language.getString(WatchtowerOnlineLibrary.LanguageKey.BIBLEREADING));

		if (isValid(relevantRows, talkIndex) && isValid(relevantRows, diggingIndex)
				&& isValid(relevantRows, bibleReadingIndex)) {

			this.treasuresTalk = new Part(relevantRows.get(talkIndex + 1));
			this.treasuresDigging = new Part(relevantRows.get(diggingIndex));
		}
	}

	/**
	 * Is index valid?
	 * 
	 * @param relevantRows
	 * @param index
	 * @return
	 */
	private boolean isValid(ArrayList<String> relevantRows, int index) {
		return index > -1 && index < relevantRows.size();
	}

	/**
	 * Find pattern
	 * 
	 * @param relevantRows
	 * @param pattern
	 * @return
	 */
	private int find(ArrayList<String> relevantRows, String pattern) {

		for (int i = 0; i < relevantRows.size(); i++)
			if (relevantRows.get(i).toLowerCase().matches(pattern))
				return i;

		return -1;
	}

	/**
	 * First number in the text
	 * 
	 * @param text
	 * @return
	 */
	public static Integer firstNumberInText(String text) {

		Matcher m = Pattern.compile("\\d+").matcher(text);
		if (m.find())
			return Integer.valueOf(m.group());

		return null;
	}

	/**
	 * Remove last brackets
	 * 
	 * @param text
	 * @return
	 */
	public static String removeLastBrackets(String text) {
		int index = text.lastIndexOf("(");
		return (index > -1) ? text.substring(0, index).trim() : text;
	}

	/**
	 * Print all info
	 * 
	 * @return
	 */
	public String print() {

		String text = "- Bible chapters: ";
		if (this.bibleChapters != null)
			text += this.bibleChapters;
		text += "\n";

		text += "\n";
		text += "- Song 1: ";
		if (this.song1 != null)
			text += this.song1.print();
		text += "\n";

		text += "\n";
		text += "- Opening comments: ";
		if (this.openingComments != null)
			text += this.openingComments.print();
		text += "\n";

		return text;
	}

	public Song getSong1() {
		return song1;
	}

	public void setSong1(Song song1) {
		this.song1 = song1;
	}

	public String getBibleChapters() {
		return bibleChapters;
	}

	public void setBibleChapters(String bibleChapters) {
		this.bibleChapters = bibleChapters;
	}

	public Part getOpeningComments() {
		return openingComments;
	}

	public void setOpeningComments(Part openingComments) {
		this.openingComments = openingComments;
	}

	public Part getTreasuresTalk() {
		return treasuresTalk;
	}

	public void setTreasuresTalk(Part treasuresTalk) {
		this.treasuresTalk = treasuresTalk;
	}

	public Part getTreasuresDigging() {
		return treasuresDigging;
	}

	public void setTreasuresDigging(Part treasuresDigging) {
		this.treasuresDigging = treasuresDigging;
	}

	/**
	 * Represents a song
	 */
	public class Song {

		private String text;
		private Integer songNo;

		public Song(String text) {
			super();
			this.text = text;
			this.songNo = ScheduleForMeeting.firstNumberInText(this.text);
		}

		public String print() {

			String text = "Text: ";
			text += this.text;
			text += "\n";
			text += "SongNo: ";
			if (this.songNo != null)
				text += this.songNo.toString();

			return text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Integer getSongNo() {
			return songNo;
		}

		public void setSongNo(Integer songNo) {
			this.songNo = songNo;
		}

	}

	/**
	 * Represents: OpeningComments
	 */
	public class Part {

		private String text;
		private Integer min;
		private String title;

		public Part(String text) {
			super();
			this.text = text;
			this.min = ScheduleForMeeting.firstNumberInText(this.text);
			this.title = ScheduleForMeeting.removeLastBrackets(this.text);
		}

		public String print() {

			String text = "Text: ";
			text += this.text;
			text += "\n";
			text += "Min: ";
			if (this.min != null)
				text += this.min.toString();
			text += "\n";
			text += "Title: ";
			if (this.title != null)
				text += this.title;

			return text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public Integer getMin() {
			return min;
		}

		public void setMin(Integer min) {
			this.min = min;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

	}

}
