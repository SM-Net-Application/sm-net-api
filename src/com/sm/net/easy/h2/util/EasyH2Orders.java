package com.sm.net.easy.h2.util;

public class EasyH2Orders {

	/**
	 * Ascending order
	 * 
	 * @param schema
	 *            Schema name
	 * @param column
	 *            Column name
	 * @return
	 */
	public static String asc(String schema, String column) {
		return schema + "." + column + " ASC";
	}

	/**
	 * Descending order
	 * 
	 * @param schema
	 *            Schema name
	 * @param column
	 *            Column name
	 * @return
	 */
	public static String desc(String schema, String column) {
		return schema + "." + column + " DESC";
	}
}
