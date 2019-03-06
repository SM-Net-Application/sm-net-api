package com.sm.net.util;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public class DateUtil {

	public static LocalDate getFirstDayOfWeek(LocalDate day) {

		if (day != null) {
			int dayOfWeek = day.getDayOfWeek().getValue();
			return (dayOfWeek > 1) ? day.minusDays((dayOfWeek - 1)) : day;
		}

		return null;
	}

	public static LocalDate getLastDayOfWeek(LocalDate day) {

		if (day != null) {
			int dayOfWeek = day.getDayOfWeek().getValue();
			return (dayOfWeek < 7) ? day.plusDays((7 - dayOfWeek)) : day;
		}

		return null;
	}

	public static int getWeekOfYears(LocalDate day) {
		return day.get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear());
	}
}
