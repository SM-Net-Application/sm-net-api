package com.sm.net.jw.wol;

public enum Languages {

	ITALIAN(0, "Italiano", 6, "it", "i", monthsItalian(), "Settimana del %d %s", "%d-%d %s", "%d %s - %d %s",
			"^Cantico\\s+\\d+.+"), GERMAN(0, "Deutsch", 10, "de", "x", monthsGerman(), "Woche vom %d. %s", "%d.-%d. %s",
					"%d. %s - %d. %s", "^Lied\\s+\\d+.+");

	private Integer id;
	private String lang;
	private Integer langID;
	private String langCode;
	private String langShortCode;
	private String[] months;
	private String weekOfPattern1;
	private String weekOfPattern2;
	private String weekOfPattern3;
	private String songPattern;

	private Languages(Integer id, String lang, Integer langID, String langCode, String langShortCode, String[] months,
			String weekOfPattern1, String weekOfPattern2, String weekOfPattern3, String songPattern) {
		this.id = id;
		this.lang = lang;
		this.langID = langID;
		this.langCode = langCode;
		this.langShortCode = langShortCode;
		this.months = months;
		this.weekOfPattern1 = weekOfPattern1;
		this.weekOfPattern2 = weekOfPattern2;
		this.weekOfPattern3 = weekOfPattern3;
		this.songPattern = songPattern;
	}

	private static String[] monthsItalian() {
		return new String[] { "Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto",
				"Settembre", "Ottobre", "Novembre", "Dicembre" };
	}

	private static String[] monthsGerman() {
		return new String[] { "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September",
				"Oktober", "November", "Dezember" };
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public Integer getLangID() {
		return langID;
	}

	public void setLangID(Integer langID) {
		this.langID = langID;
	}

	public String getLangCode() {
		return langCode;
	}

	public void setLangCode(String langCode) {
		this.langCode = langCode;
	}

	public String getLangShortCode() {
		return langShortCode;
	}

	public void setLangShortCode(String langShortCode) {
		this.langShortCode = langShortCode;
	}

	public String[] getMonths() {
		return months;
	}

	public void setMonths(String[] months) {
		this.months = months;
	}

	public String getWeekOfPattern1() {
		return weekOfPattern1;
	}

	public void setWeekOfPattern1(String weekOfPattern1) {
		this.weekOfPattern1 = weekOfPattern1;
	}

	public String getWeekOfPattern2() {
		return weekOfPattern2;
	}

	public void setWeekOfPattern2(String weekOfPattern2) {
		this.weekOfPattern2 = weekOfPattern2;
	}

	public String getWeekOfPattern3() {
		return weekOfPattern3;
	}

	public void setWeekOfPattern3(String weekOfPattern3) {
		this.weekOfPattern3 = weekOfPattern3;
	}

	public String getSongPattern() {
		return songPattern;
	}

	public void setSongPattern(String songPattern) {
		this.songPattern = songPattern;
	}

}
