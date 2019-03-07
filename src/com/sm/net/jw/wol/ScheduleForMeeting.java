package com.sm.net.jw.wol;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sm.net.project.Language;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ScheduleForMeeting {

	private String bibleChapters;
	private Song song1;
	private Part openingComments;
	private Part treasuresTalk;
	private ObservableList<Point> talkPointsList;
	private Part treasuresDigging;
	private ObservableList<Point> diggingPointsList;
	private BibleReadingPart treasuresBibleReading;
	private ObservableList<MinistryPart> ministryPartsList;

	public ScheduleForMeeting(ArrayList<String> relevantRows, Language language) {
		super();

		defineFirstInfo(relevantRows, language);
		defineTreasures(relevantRows, language);
		defineMinistry(relevantRows, language);
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

	/**
	 * Define Talk, Digging and Bible Reading
	 * 
	 * @param relevantRows
	 * @param language
	 */
	private void defineTreasures(ArrayList<String> relevantRows, Language language) {

		// Treasures from God's word index + 1
		int talkIndex = find(relevantRows, language.getString(WatchtowerOnlineLibrary.LanguageKey.TREASURES));
		// Digging for Spiritual Gems index
		int diggingIndex = find(relevantRows, language.getString(WatchtowerOnlineLibrary.LanguageKey.DIGGING));
		// Bible Reading
		int bibleReadingIndex = find(relevantRows,
				language.getString(WatchtowerOnlineLibrary.LanguageKey.BIBLEREADING));

		if (isValid(relevantRows, talkIndex) && isValid(relevantRows, diggingIndex)) {
			// Talk
			talkIndex += 1;
			this.treasuresTalk = new Part(relevantRows.get(talkIndex));

			// Talk-Points
			this.talkPointsList = FXCollections.observableArrayList();
			for (int i = (talkIndex + 1); i < diggingIndex; i++)
				talkPointsList.add(new Point(relevantRows.get(i), language));
		}

		if (isValid(relevantRows, diggingIndex) && isValid(relevantRows, bibleReadingIndex)) {
			// Digging
			this.treasuresDigging = new Part(relevantRows.get(diggingIndex));

			// Digging-Points
			this.diggingPointsList = FXCollections.observableArrayList();
			for (int i = (diggingIndex + 1); i < bibleReadingIndex; i++)
				diggingPointsList.add(new Point(relevantRows.get(i), language));
		}

		if (isValid(relevantRows, bibleReadingIndex)) {
			// Bible Reading
			this.treasuresBibleReading = new BibleReadingPart(relevantRows.get(bibleReadingIndex));
		}
	}

	private void defineMinistry(ArrayList<String> relevantRows, Language language) {

		// Apply yourself to the field ministry index + 1
		int ministryIndex = find(relevantRows, language.getString(WatchtowerOnlineLibrary.LanguageKey.MINISTRY));
		// Living as christians index
		int christiansIndex = find(relevantRows, language.getString(WatchtowerOnlineLibrary.LanguageKey.CHRISTIANS));

		if (isValid(relevantRows, ministryIndex) && isValid(relevantRows, christiansIndex)) {
			// Ministry-Parts
			this.ministryPartsList = FXCollections.observableArrayList();
			for (int i = (ministryIndex + 1); i < christiansIndex; i++) {
				MinistryPart e = new MinistryPart(relevantRows.get(i));
				System.out.println(e.print());
				ministryPartsList.add(e);
			}
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
	 * Remove last brackets if presents
	 * 
	 * @param text
	 * @return
	 */
	public static String removeLastBrackets(String text) {
		if (text.charAt(text.length() - 1) == ')') {
			int index = text.lastIndexOf("(");
			return (index > -1) ? text.substring(0, index).trim() : text;
		}
		return text;
	}

	/**
	 * Get last brachets
	 * 
	 * @param text
	 * @return
	 */
	public static String getLastBrachets(String text) {

		if (text.charAt(text.length() - 1) == ')') {
			int index = text.lastIndexOf("(");
			return (index > -1) ? text.substring(index, text.length()).trim() : text;
		}

		return "";
	}

	/**
	 * Remove open and close square brackets
	 * 
	 * @param text
	 * @return
	 */
	public static String removeOpenAndCloseSquareBrackets(String text) {
		if (!text.isEmpty())
			return (text.charAt(0) == '[' && text.charAt(text.length() - 1) == ']')
					? text.substring(1, text.length() - 1).trim() : text;
		return text;
	}

	/**
	 * Remove open and close brackets
	 * 
	 * @param text
	 * @return
	 */
	public static String removeOpenAndCloseBrackets(String text) {
		if (!text.isEmpty())
			return (text.charAt(0) == '(' && text.charAt(text.length() - 1) == ')')
					? text.substring(1, text.length() - 1).trim() : text;
		return text;
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

		text += "\n";
		text += "- Treasures Talk: ";
		if (this.treasuresTalk != null)
			text += this.treasuresTalk.print();
		text += "\n";

		text += "\n";
		text += "- Treasures Talk Points: ";
		if (this.talkPointsList != null)
			for (Point p : this.talkPointsList)
				text += p.print();
		text += "\n";

		text += "\n";
		text += "- Treasures Digging: ";
		if (this.treasuresDigging != null)
			text += this.treasuresDigging.print();
		text += "\n";

		text += "\n";
		text += "- Treasures Digging Points: ";
		if (this.diggingPointsList != null)
			for (Point p : this.diggingPointsList)
				text += p.print();
		text += "\n";

		text += "\n";
		text += "- Treasures Bible Reading: ";
		if (this.treasuresBibleReading != null)
			text += this.treasuresBibleReading.print();
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

	public ObservableList<Point> getTalkPointsList() {
		return talkPointsList;
	}

	public void setTalkPointsList(ObservableList<Point> talkPointsList) {
		this.talkPointsList = talkPointsList;
	}

	public ObservableList<Point> getDiggingPointsList() {
		return diggingPointsList;
	}

	public void setDiggingPointsList(ObservableList<Point> diggingPointsList) {
		this.diggingPointsList = diggingPointsList;
	}

	public BibleReadingPart getTreasuresBibleReading() {
		return treasuresBibleReading;
	}

	public void setTreasuresBibleReading(BibleReadingPart treasuresBibleReading) {
		this.treasuresBibleReading = treasuresBibleReading;
	}

	public ObservableList<MinistryPart> getMinistryPartsList() {
		return ministryPartsList;
	}

	public void setMinistryPartsList(ObservableList<MinistryPart> ministryPartsList) {
		this.ministryPartsList = ministryPartsList;
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

			String text = "";

			text += "\n";
			text += "Text: ";
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
	 * Represents a Part
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

			String text = "";

			text += "\n";
			text += "Text: ";
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

	/**
	 * Represents a Main Point
	 */
	public class Point {

		private String text;
		private String bible;
		private String title;
		private String material;

		public Point(String text, Language language) {
			super();
			this.text = text;

			if (isPoint(this.text, language)) {
				this.bible = defineBible();
				this.title = defineTitle();
				this.material = defineMaterial();
			} else if (isPointText(this.text, language)) {
				this.bible = "";
				this.title = ScheduleForMeeting.removeOpenAndCloseSquareBrackets(this.text);
				this.material = "";
			} else {
				this.bible = "";
				this.title = text;
				this.material = "";
			}
		}

		private String defineMaterial() {
			return ScheduleForMeeting.removeOpenAndCloseBrackets(ScheduleForMeeting.getLastBrachets(this.text));
		}

		private String defineTitle() {
			int i = this.text.indexOf("—");
			if (i > -1) {
				String temp = this.text.substring(i + 1, this.text.length()).trim();
				return ScheduleForMeeting.removeLastBrackets(temp);
			}
			return "error";
		}

		private String defineBible() {
			int i = this.text.indexOf("—");
			return (i > -1) ? this.text.substring(0, i - 1).trim() : "error";
		}

		private boolean isPoint(String text, Language language) {

			if (text.matches(language.getString(WatchtowerOnlineLibrary.LanguageKey.POINT)))
				return true;

			return false;
		}

		private boolean isPointText(String text, Language language) {

			String pattern = language.getString(WatchtowerOnlineLibrary.LanguageKey.POINTTEXT);
			if (text.matches(pattern))
				return true;

			return false;
		}

		public String print() {

			String text = "";

			text += "\n";
			text += "Text: ";
			text += this.text;
			text += "\n";
			text += "Bible: ";
			if (this.bible != null)
				text += this.bible;
			text += "\n";
			text += "Title: ";
			if (this.title != null)
				text += this.title;
			text += "\n";
			text += "Material: ";
			if (this.material != null)
				text += this.material;
			text += "\n";

			return text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getBible() {
			return bible;
		}

		public void setBible(String bible) {
			this.bible = bible;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getMaterial() {
			return material;
		}

		public void setMaterial(String material) {
			this.material = material;
		}
	}

	/**
	 * Represents the Bible Reading Part
	 */
	public class BibleReadingPart {

		private String text;
		private String textPart;
		private Integer min;
		private String bible;
		private String material;

		public BibleReadingPart(String text) {
			super();
			this.text = text;
			this.textPart = defineTextPart();
			this.min = ScheduleForMeeting.firstNumberInText(this.text);
			this.bible = defineBible();
			this.material = defineMaterial();
		}

		private String defineTextPart() {
			int i = this.text.indexOf("(");
			return (i > -1) ? (this.text.substring(0, i - 1).trim()) : "";
		}

		private String defineBible() {
			int i = this.text.indexOf(")");
			return (i > -1)
					? ScheduleForMeeting.removeLastBrackets(this.text.substring(i + 1, this.text.length()).trim()) : "";
		}

		private String defineMaterial() {
			return ScheduleForMeeting.removeOpenAndCloseBrackets(ScheduleForMeeting.getLastBrachets(this.text));
		}

		public String print() {

			String text = "";

			text += "\n";
			text += "Text: ";
			text += this.text;
			text += "\n";
			text += "TextPart: ";
			if (this.textPart != null)
				text += this.textPart;
			text += "\n";
			text += "Min: ";
			if (this.min != null)
				text += this.min.toString();
			text += "\n";
			text += "Bible: ";
			if (this.bible != null)
				text += this.bible;
			text += "\n";
			text += "Material: ";
			if (this.material != null)
				text += this.material;

			return text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getTextPart() {
			return textPart;
		}

		public void setTextPart(String textPart) {
			this.textPart = textPart;
		}

		public Integer getMin() {
			return min;
		}

		public void setMin(Integer min) {
			this.min = min;
		}

		public String getBible() {
			return bible;
		}

		public void setBible(String bible) {
			this.bible = bible;
		}

		public String getMaterial() {
			return material;
		}

		public void setMaterial(String material) {
			this.material = material;
		}

	}

	/**
	 * Represents the Bible Reading Part
	 */
	public class MinistryPart {

		private String text;
		private String textPart;
		private Integer min;
		private String body;
		private String material;

		public MinistryPart(String text) {
			super();
			this.text = text;
			this.textPart = defineTextPart();
			this.min = ScheduleForMeeting.firstNumberInText(this.text);
			this.body = defineBody();
			this.material = defineMaterial();
		}

		private String defineTextPart() {
			int i = this.text.indexOf("(");
			return (i > -1) ? (this.text.substring(0, i - 1).trim()) : "";
		}

		private String defineBody() {
			int i = this.text.indexOf(")");
			return (i > -1)
					? ScheduleForMeeting.removeLastBrackets(this.text.substring(i + 1, this.text.length()).trim()) : "";
		}

		private String defineMaterial() {
			return ScheduleForMeeting.removeOpenAndCloseBrackets(ScheduleForMeeting.getLastBrachets(this.text));
		}

		public String print() {

			String text = "";

			text += "\n";
			text += "Text: ";
			text += this.text;
			text += "\n";
			text += "TextPart: ";
			if (this.textPart != null)
				text += this.textPart;
			text += "\n";
			text += "Min: ";
			if (this.min != null)
				text += this.min.toString();
			text += "\n";
			text += "Body: ";
			if (this.body != null)
				text += this.body;
			text += "\n";
			text += "Material: ";
			if (this.material != null)
				text += this.material;

			return text;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
		}

		public String getTextPart() {
			return textPart;
		}

		public void setTextPart(String textPart) {
			this.textPart = textPart;
		}

		public Integer getMin() {
			return min;
		}

		public void setMin(Integer min) {
			this.min = min;
		}

		public String getBody() {
			return body;
		}

		public void setBody(String body) {
			this.body = body;
		}

		public String getMaterial() {
			return material;
		}

		public void setMaterial(String material) {
			this.material = material;
		}

	}
}
