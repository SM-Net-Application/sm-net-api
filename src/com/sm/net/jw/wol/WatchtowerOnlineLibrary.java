package com.sm.net.jw.wol;

import java.time.LocalDate;
import java.util.ArrayList;

public class WatchtowerOnlineLibrary {

	private static final String PATTERN_LINK = "https://wol.jw.org/%s/wol/dt/r%d/lp-%s/%d/%d/%d";
	private static final String PATTERN_RELEVANT_ROW = ".+id=\"p\\d+\".+";

	public static String createLinkDayProgram(Languages language, LocalDate date) {
		return String.format(PATTERN_LINK, language.getLangCode(), language.getLangID(), language.getLangShortCode(),
				date.getYear(), date.getMonthValue(), date.getDayOfMonth());
	}

	public static ArrayList<String> getWOLHtmlRelevantRows(String sourceCode) {

		ArrayList<String> list = null;

		if (!sourceCode.isEmpty()) {

			list = new ArrayList<>();

			String[] codeRows = sourceCode.split("\n");

			for (String codeRow : codeRows) {

				codeRow = codeRow.trim();

				if (!codeRow.isEmpty())
					if (codeRow.matches(PATTERN_RELEVANT_ROW))
						list.add(codeRow);
			}
		}

		return list;
	}
}
