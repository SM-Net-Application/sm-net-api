package com.sm.net.easy.h2.util;

public class EasyH2Util {

	/**
	 * Set Apostrophe
	 * 
	 * @param apostrophe
	 * @param value
	 * @return
	 */
	public static String setApostrophe(boolean apostrophe, String value) {
		if (apostrophe)
			return "'" + value + "'";
		return value;
	}
}
