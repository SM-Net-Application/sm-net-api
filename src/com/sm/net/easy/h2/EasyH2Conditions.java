package com.sm.net.easy.h2;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EasyH2Conditions {

	/**
	 * Return Condition EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String equals(String column, String value) {
		if (value != null)
			return column + "='" + value + "'";
		return "";
	}

	/**
	 * Return Condition EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String equals(String column, Integer value) {
		if (value != null)
			return column + "=" + value.toString();
		return "";
	}

	/**
	 * Return Condition EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String equals(String column, BigDecimal value) {
		if (value != null)
			return column + "=" + value.toString();
		return "";
	}

	/**
	 * Return Condition EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String equals(String column, Date value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "='" + dtf.format(value.toInstant()) + "'";
		}
		return "";
	}

	/**
	 * Return Condition EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String equals(String column, LocalDate value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "='" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String equals(String column, Instant value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "='" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition NOT EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String notEquals(String column, String value) {
		if (value != null)
			return column + "<>'" + value + "'";
		return "";
	}

	/**
	 * Return Condition NOT EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String notEquals(String column, Integer value) {
		if (value != null)
			return column + "<>" + value.toString();
		return "";
	}

	/**
	 * Return Condition NOT EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String notEquals(String column, BigDecimal value) {
		if (value != null)
			return column + "<>" + value.toString();
		return "";
	}

	/**
	 * Return Condition NOT EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String notEquals(String column, Date value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "<>'" + dtf.format(value.toInstant()) + "'";
		}
		return "";
	}

	/**
	 * Return Condition NOT EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String notEquals(String column, LocalDate value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "<>'" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition NOT EQUALS
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String notEquals(String column, Instant value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "<>'" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition GREATER THAN
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String greaterThan(String column, Integer value) {
		if (value != null)
			return column + ">" + value.toString();
		return "";
	}

	/**
	 * Return Condition GREATER THAN
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String greaterThan(String column, BigDecimal value) {
		if (value != null)
			return column + ">" + value.toString();
		return "";
	}

	/**
	 * Return Condition GREATER THAN
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String greaterThan(String column, Date value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + ">'" + dtf.format(value.toInstant()) + "'";
		}
		return "";
	}

	/**
	 * Return Condition GREATER THAN
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String greaterThan(String column, LocalDate value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + ">'" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition GREATER THAN
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String greaterThan(String column, Instant value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + ">'" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition LESS THAN
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String lessThan(String column, Integer value) {
		if (value != null)
			return column + "<" + value.toString();
		return "";
	}

	/**
	 * Return Condition LESS THAN
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String lessThan(String column, BigDecimal value) {
		if (value != null)
			return column + "<" + value.toString();
		return "";
	}

	/**
	 * Return Condition LESS THAN
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String lessThan(String column, Date value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "<'" + dtf.format(value.toInstant()) + "'";
		}
		return "";
	}

	/**
	 * Return Condition LESS THAN
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String lessThan(String column, LocalDate value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "<'" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition LESS THAN
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String lessThan(String column, Instant value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "<'" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition GREATER THAN OR EQUAL
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String greaterThanOrEqual(String column, Integer value) {
		if (value != null)
			return column + ">=" + value.toString();
		return "";
	}

	/**
	 * Return Condition GREATER THAN OR EQUAL
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String greaterThanOrEqual(String column, BigDecimal value) {
		if (value != null)
			return column + ">=" + value.toString();
		return "";
	}

	/**
	 * Return Condition GREATER THAN OR EQUAL
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String greaterThanOrEqual(String column, Date value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + ">='" + dtf.format(value.toInstant()) + "'";
		}
		return "";
	}

	/**
	 * Return Condition GREATER THAN OR EQUAL
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String greaterThanOrEqual(String column, LocalDate value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + ">='" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition GREATER THAN OR EQUAL
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String greaterThanOrEqual(String column, Instant value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + ">='" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition LESS THAN OR EQUAL
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String lessThanOrEqual(String column, Integer value) {
		if (value != null)
			return column + "<=" + value.toString();
		return "";
	}

	/**
	 * Return Condition LESS THAN OR EQUAL
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String lessThanOrEqual(String column, BigDecimal value) {
		if (value != null)
			return column + "<=" + value.toString();
		return "";
	}

	/**
	 * Return Condition LESS THAN OR EQUAL
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String lessThanOrEqual(String column, Date value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "<='" + dtf.format(value.toInstant()) + "'";
		}
		return "";
	}

	/**
	 * Return Condition LESS THAN OR EQUAL
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String lessThanOrEqual(String column, LocalDate value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "<='" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition LESS THAN OR EQUAL
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            Value
	 * @return
	 */
	public static String lessThanOrEqual(String column, Instant value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + "<='" + dtf.format(value) + "'";
		}
		return "";
	}

	/**
	 * Return Condition BETWEEN
	 * 
	 * @param column
	 *            Column name
	 * @param value1
	 *            Value 1
	 * @param value2
	 *            Value 2
	 * @return
	 */
	public static String between(String column, Integer value1, Integer value2) {
		if (value1 != null && value2 != null)
			return column + " BETWEEN " + value1.toString() + " AND " + value2.toString();
		return "";
	}

	/**
	 * Return Condition BETWEEN
	 * 
	 * @param column
	 *            Column name
	 * @param value1
	 *            Value 1
	 * @param value2
	 *            Value 2
	 * @return
	 */
	public static String between(String column, BigDecimal value1, BigDecimal value2) {
		if (value1 != null && value2 != null)
			return column + " BETWEEN " + value1.toString() + " AND " + value2.toString();
		return "";
	}

	/**
	 * Return Condition BETWEEN
	 * 
	 * @param column
	 *            Column name
	 * @param value1
	 *            Value 1
	 * @param value2
	 *            Value 2
	 * @return
	 */
	public static String between(String column, Date value1, Date value2) {
		if (value1 != null && value2 != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + " BETWEEN '" + dtf.format(value1.toInstant()) + "' AND '" + dtf.format(value2.toInstant())
					+ "'";
		}
		return "";
	}

	/**
	 * Return Condition BETWEEN
	 * 
	 * @param column
	 *            Column name
	 * @param value1
	 *            Value 1
	 * @param value2
	 *            Value 2
	 * @return
	 */
	public static String between(String column, LocalDate value1, LocalDate value2) {
		if (value1 != null && value2 != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + " BETWEEN '" + dtf.format(value1) + "' AND '" + dtf.format(value2) + "'";
		}
		return "";
	}

	/**
	 * Return Condition BETWEEN
	 * 
	 * @param column
	 *            Column name
	 * @param value1
	 *            Value 1
	 * @param value2
	 *            Value 2
	 * @return
	 */
	public static String between(String column, Instant value1, Instant value2) {
		if (value1 != null && value2 != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			return column + " BETWEEN '" + dtf.format(value1) + "' AND '" + dtf.format(value2) + "'";
		}
		return "";
	}

	/**
	 * Return Condition LIKE
	 * 
	 * @param column
	 *            Column name
	 * @param pattern
	 *            Value
	 * @return
	 */
	public static String like(String column, String pattern) {
		return column + " LIKE '" + pattern + "'";
	}
}
