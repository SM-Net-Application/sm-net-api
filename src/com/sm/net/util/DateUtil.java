package com.sm.net.util;

import java.time.LocalDate;

public class DateUtil {

	public static LocalDate getFirstDayOfWeek(LocalDate today) {

		if (today != null) {
			int dayOfWeek = today.getDayOfWeek().getValue();
			return (dayOfWeek > 1) ? today.minusDays((dayOfWeek - 1)) : today;
		}

		return null;
	}

}
