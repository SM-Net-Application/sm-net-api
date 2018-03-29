package com.sm.net.util;

import java.io.UnsupportedEncodingException;
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
	public static String instantToString(Instant instant, String format) {

		if (instant != null) {
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault());
			return dateTimeFormatter.format(instant);
		}
		return null;
	}

	/**
	 * 
	 * Returns the string for the millisecond's output
	 * 
	 * @param millisecond
	 * @param format
	 *            For example: dd.MM.yyyy HH:mm:ss
	 * @return string or null
	 */
	public static String millisecondToString(long millisecond, String format) {

		Instant instant = Instant.ofEpochMilli(millisecond);

		if (instant != null)
			return instantToString(instant, format);

		return null;
	}

	/**
	 * 
	 * Returns the string recoded with charset
	 * 
	 * @param string
	 * @param charset
	 *            For example: UTF-8
	 * @return the same string or the recoded string
	 */
	public static String recoding(String string, String charset) {

		String recodedString = string;

		byte[] bytes = string.getBytes();

		try {
			recodedString = new String(bytes, charset);
		} catch (UnsupportedEncodingException e) {
		}

		return recodedString;
	}
}