package com.sm.net.jw.wol;

import java.time.LocalDate;
import java.util.ArrayList;

import com.sm.net.project.Language;

public class WatchtowerOnlineLibrary {

	/**
	 * Determine the WOL link. Language and date parameters are required.
	 * 
	 * @param language
	 * @param date
	 * @return
	 */
	public static String createLink(Language language, LocalDate date) {

		final String linkPattern = "https://wol.jw.org/%s/wol/dt/r%s/lp-%s/%d/%d/%d";

		String languageCode = language.getString(LanguageKey.CODE);
		String languageNo = language.getString(LanguageKey.NO);
		String languageShortcode = language.getString(LanguageKey.SHORTCODE);

		int dateYear = date.getYear();
		int dateMonth = date.getMonthValue();
		int dateDay = date.getDayOfMonth();

		String link = String.format(linkPattern, languageCode, languageNo, languageShortcode, dateYear, dateMonth,
				dateDay);

		return link;

	}

	/**
	 * Determine the WOL relevant lines of the HTML code
	 * 
	 * @param sourceCode
	 * @return
	 */
	public static ArrayList<String> relevantHTMLRows(String sourceCode) {

		ArrayList<String> list = null;

		if (!sourceCode.isEmpty()) {

			list = new ArrayList<>();

			String[] codeRows = sourceCode.split("\n");

			for (String codeRow : codeRows) {

				codeRow = codeRow.trim();

				if (!codeRow.isEmpty())
					if (codeRow.matches(Pattern.RELEVANT_ROW))
						list.add(codeRow);
			}
		}

		return list;
	}

	/**
	 * Language Keys
	 */
	public static class LanguageKey {
		public static final String CODE = "wol.language.code";
		public static final String SHORTCODE = "wol.language.shortcode";
		public static final String NO = "wol.language.no";
		public static final String SONG1 = "wol.pattern.song1";
		public static final String TREASURES = "wol.pattern.treasures";
		public static final String DIGGING = "wol.pattern.digging";
		public static final String BIBLEREADING = "wol.pattern.biblereading";
		public static final String POINT = "wol.pattern.point";
		public static final String POINTTEXT = "wol.pattern.pointtext";
	}

	/**
	 * Patterns
	 */
	public static class Pattern {
		public static final String RELEVANT_ROW = ".+id=\"p\\d+\".+";
	}
}
