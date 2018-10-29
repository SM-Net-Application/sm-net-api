package com.sm.net.jw.wol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sm.net.jw.wol.Languages;
import com.sm.net.jw.wol.PointType;
import com.sm.net.jw.wol.WatchtowerOnlineLibrary;
import com.sm.net.util.DateUtil;
import com.sm.net.util.Html;

public class Programm {

	private LocalDate date;
	private Languages lang;
	private LocalDate weekOf;

	private String linkWOL;
	private ArrayList<String> WOLHtmlRelevantRows;

	private String weekBible;
	private Song song1;
	private Song song2;
	private Song song3;

	private Part openingComment;
	private Integer indexHtmlRow;
	private Treasures treasuresSpeaking;

	public Programm(LocalDate date, Languages lang) {

		this.date = date;
		this.lang = lang;
		this.weekOf = DateUtil.getFirstDayOfWeek(this.date);

		this.linkWOL = WatchtowerOnlineLibrary.createLinkDayProgram(lang, date);
		this.WOLHtmlRelevantRows = WatchtowerOnlineLibrary.getWOLHtmlRelevantRows(Html.getSourceCode(this.linkWOL));

		this.weekBible = Html.removeTag(WOLHtmlRelevantRows.get(4)).trim();
		this.defineSongs();
		this.openingComment = new Part("", "", "", Integer.valueOf(-1));
		this.defineOpeningComments();
		this.indexHtmlRow = Integer.valueOf(9);
		this.treasuresSpeaking = new Treasures("", "", "", Integer.valueOf(-1));
		this.defineTreasuresSpeaking();
	}

	public String getWeekOfStyle1() {
		return String.format(lang.getWeekOfPattern1(), weekOf.getDayOfMonth(), getMonthString(weekOf));
	}

	public String getWeekOfStyle2() {
		LocalDate weekEnd = weekOf.plusDays(6);
		return checkMonth(weekEnd) ? weekOfPattern2(weekEnd) : weekOfPattern3(weekEnd);
	}

	private boolean checkMonth(LocalDate weekEnd) {
		return weekOf.getMonthValue() == weekEnd.getMonthValue();
	}

	private String weekOfPattern2(LocalDate weekEnd) {
		return String.format(lang.getWeekOfPattern2(), weekOf.getDayOfMonth(), weekEnd.getDayOfMonth(),
				getMonthString(weekEnd));
	}

	private String weekOfPattern3(LocalDate weekEnd) {
		return String.format(lang.getWeekOfPattern3(), weekOf.getDayOfMonth(), getMonthString(weekOf),
				weekEnd.getDayOfMonth(), getMonthString(weekEnd));
	}

	private String getMonthString(LocalDate date) {
		return lang.getMonths()[date.getMonthValue() - 1];
	}

	public void sysOutRelevantRows() {
		for (String s : WOLHtmlRelevantRows)
			System.out.println(s);
	}

	private void defineSongs() {

		int start = 5;
		int end = this.WOLHtmlRelevantRows.size() - 2;

		boolean song1 = false;
		boolean song2 = false;
		boolean song3 = false;

		for (int i = start; i < end; i++) {

			String htmlRow = this.WOLHtmlRelevantRows.get(i);
			String row = Html.convertNoBreakSpace160(Html.removeTag(htmlRow)).trim();
			if (row.matches(lang.getSongPattern())) {
				Matcher m = Pattern.compile("\\d+").matcher(row);
				if (m.find()) {
					if (!song1) {
						this.song1 = new Song(row, Integer.valueOf(m.group()));
						song1 = true;
					} else if (!song2) {
						this.song2 = new Song(row, Integer.valueOf(m.group()));
						song2 = true;
					} else if (!song3) {
						this.song3 = new Song(row, Integer.valueOf(m.group()));
						song3 = true;
					}
				}
			}

		}
	}

	private void defineOpeningComments() {

		String htmlRow6 = this.WOLHtmlRelevantRows.get(6);
		String row6 = Html.removeTag(htmlRow6).trim();

		this.openingComment.setFullTitle(row6);
		this.openingComment.setTitle(row6.substring(0, row6.indexOf("(")).trim());
		this.openingComment.setFullMin(Html.getSubsourceCode(row6, "(", ")"));
		this.openingComment.setMin(getMin(this.openingComment.getFullMin()));
	}

	private Integer getMin(String fullMin) {

		Matcher m = Pattern.compile("\\d+").matcher(fullMin);
		return m.find() ? Integer.valueOf(m.group()) : Integer.valueOf(-1);
	}

	private void defineTreasuresSpeaking() {

		String htmlRow8 = this.WOLHtmlRelevantRows.get(8);
		String row8 = Html.removeTag(htmlRow8).trim();

		this.treasuresSpeaking.setFullTitle(row8);
		this.treasuresSpeaking.setTitle(removeTheLastTwoPoints(row8.substring(0, row8.indexOf("(")).trim()));
		this.treasuresSpeaking.setFullMin(Html.getSubsourceCode(row8, "(", ")"));
		this.treasuresSpeaking.setMin(getMin(this.treasuresSpeaking.getFullMin()));

		defineTreasuresSpeakingPoints();
	}

	private void defineTreasuresSpeakingPoints() {

		String row = this.WOLHtmlRelevantRows.get(this.indexHtmlRow.intValue());

		while (row.contains("class=\"sw\"")) {

			if (row.contains("class=\"b\"")) {
				addTreasuresSpeakingPoint(row);
			} else if (row.contains("class=\"fb\"")) {
				addTreasuresSpeakingVideo(row);
			}

			this.indexHtmlRow = Integer.valueOf(this.indexHtmlRow.intValue() + 1);
			row = this.WOLHtmlRelevantRows.get(this.indexHtmlRow.intValue());
		}
	}

	private void addTreasuresSpeakingVideo(String row) {
		String fullTitle = Html.removeTag(row);
		this.treasuresSpeaking.getPoints().add(new PointTreasures(PointType.VIDEO, fullTitle, fullTitle, ""));
	}

	private void addTreasuresSpeakingPoint(String row) {

		String bible = "";
		String fullTitle = Html.convertNoBreakSpace160(Html.removeTag(row)).trim();
		String title = "";
		String materials = "";

		String[] splitFullTitle = fullTitle.split("—");
		if (splitFullTitle.length == 2) {
			bible = splitFullTitle[0].trim();
			title = splitFullTitle[1].trim();
		}

		int lastIndex = title.lastIndexOf("(");
		if (lastIndex > -1) {
			materials = title.substring(lastIndex + 1, title.length() - 1).trim();
			title = title.substring(0, lastIndex).trim();
		}

		ArrayList<String> material = new ArrayList<>();
		String[] splitMaterials = materials.split(";");
		for (String s : splitMaterials)
			material.add(s.trim());

		this.treasuresSpeaking.getPoints().add(new PointTreasures(PointType.POINT, fullTitle, title, bible, material));
	}

	private String removeTheLastTwoPoints(String s) {
		return (s.charAt(s.length() - 1) == ':') ? s.substring(0, s.length() - 1) : s;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Languages getLang() {
		return lang;
	}

	public void setLang(Languages lang) {
		this.lang = lang;
	}

	public LocalDate getWeekOf() {
		return weekOf;
	}

	public void setWeekOf(LocalDate weekOf) {
		this.weekOf = weekOf;
	}

	public String getLinkWOL() {
		return linkWOL;
	}

	public void setLinkWOL(String linkWOL) {
		this.linkWOL = linkWOL;
	}

	public ArrayList<String> getWOLHtmlRelevantRows() {
		return WOLHtmlRelevantRows;
	}

	public void setWOLHtmlRelevantRows(ArrayList<String> wOLHtmlRelevantRows) {
		WOLHtmlRelevantRows = wOLHtmlRelevantRows;
	}

	public String getWeekBible() {
		return weekBible;
	}

	public void setWeekBible(String weekBible) {
		this.weekBible = weekBible;
	}

	public Song getSong1() {
		return song1;
	}

	public void setSong1(Song song1) {
		this.song1 = song1;
	}

	public Song getSong2() {
		return song2;
	}

	public void setSong2(Song song2) {
		this.song2 = song2;
	}

	public Song getSong3() {
		return song3;
	}

	public void setSong3(Song song3) {
		this.song3 = song3;
	}

	public Treasures getTreasuresSpeaking() {
		return treasuresSpeaking;
	}

	public void setTreasuresSpeaking(Treasures treasuresSpeaking) {
		this.treasuresSpeaking = treasuresSpeaking;
	}

	public Part getOpeningComment() {
		return openingComment;
	}

	public void setOpeningComment(Part openingComment) {
		this.openingComment = openingComment;
	}

	public Integer getIndexHtmlRow() {
		return indexHtmlRow;
	}

	public void setIndexHtmlRow(Integer indexHtmlRow) {
		this.indexHtmlRow = indexHtmlRow;
	}

	public class Song {

		private String fullTitle;
		private Integer song;

		public Song(String fullTitle, Integer song) {
			super();
			this.fullTitle = fullTitle;
			this.song = song;
		}

		public String getFullTitle() {
			return fullTitle;
		}

		public void setFullTitle(String fullTitle) {
			this.fullTitle = fullTitle;
		}

		public Integer getSong() {
			return song;
		}

		public void setSong(Integer song) {
			this.song = song;
		}
	}

	public class PartTitle {

		private String fullTitle;
		private String title;

		public PartTitle(String fullTitle, String title) {
			super();
			this.fullTitle = fullTitle;
			this.title = title;
		}

		public String getFullTitle() {
			return fullTitle;
		}

		public void setFullTitle(String fullTitle) {
			this.fullTitle = fullTitle;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}

	public class Part extends PartTitle {

		private String fullMin;
		private Integer min;

		public Part(String fullTitle, String title, String fullMin, Integer min) {
			super(fullTitle, title);
			this.fullMin = fullMin;
			this.min = min;
		}

		public String getFullMin() {
			return fullMin;
		}

		public void setFullMin(String fullMin) {
			this.fullMin = fullMin;
		}

		public Integer getMin() {
			return min;
		}

		public void setMin(Integer min) {
			this.min = min;
		}
	}

	public class PartWithMaterial extends Part {

		private String material;

		public PartWithMaterial(String fullTitle, String title, String fullMin, Integer min, String material) {
			super(fullTitle, title, fullMin, min);
			this.material = material;
		}

		public String getMaterial() {
			return material;
		}

		public void setMaterial(String material) {
			this.material = material;
		}
	}

	public class Treasures extends Part {

		ArrayList<PointTreasures> points;

		public Treasures(String fullTitle, String title, String fullMin, Integer min) {
			super(fullTitle, title, fullMin, min);
			this.points = new ArrayList<>();
		}

		public ArrayList<PointTreasures> getPoints() {
			return points;
		}

		public void setPoints(ArrayList<PointTreasures> points) {
			this.points = points;
		}

	}

	public class PointTreasures extends PartTitle {

		PointType pointType;
		String bible;
		ArrayList<String> materials;

		public PointTreasures(PointType pointType, String fullTitle, String title, String bible) {
			super(fullTitle, title);
			this.pointType = pointType;
			this.bible = bible;
			this.materials = new ArrayList<>();
		}

		public PointTreasures(PointType pointType, String fullTitle, String title, String bible,
				ArrayList<String> materials) {
			super(fullTitle, title);
			this.pointType = pointType;
			this.bible = bible;
			this.materials = materials;
		}

		public String getBible() {
			return bible;
		}

		public void setBible(String bible) {
			this.bible = bible;
		}

		public ArrayList<String> getMaterials() {
			return materials;
		}

		public void setMaterials(ArrayList<String> materials) {
			this.materials = materials;
		}

		public PointType getPointType() {
			return pointType;
		}

		public void setPointType(PointType pointType) {
			this.pointType = pointType;
		}
	}
}
