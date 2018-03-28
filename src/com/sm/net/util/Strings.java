package com.sm.net.util;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Utility Class for Strings
 */
public class Strings {

	/**
	 * 
	 * Returns the string for the instant's output
	 * 
	 * @param instant
	 * @param format
	 *            For example: dd.MM.yyyy HH:mm:ss
	 * @return string or null
	 */
	public static String printInstant(Instant instant, String format) {

		if (instant != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault());
			return dateTimeFormatter.format(instant);
		}
		return null;
	}
}