package com.sm.net.simple.h2;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OperationBuilder {

	private String schemaName;
	private String tableName;
	private List<String> editColumnsName;
	private List<String> editColumnsValue;
	private List<String> conditions;
	private List<String> selections;

	public OperationBuilder(String schemaName, String tableName) {
		super();
		this.schemaName = schemaName;
		this.tableName = tableName;

		this.editColumnsName = new ArrayList<>();
		this.editColumnsValue = new ArrayList<>();
		this.conditions = new ArrayList<>();
		this.selections = new ArrayList<>();
	}

	/**
	 * Add INT Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            Integer value
	 */
	public void setColumnValue(String columnName, Integer value) {
		addColumnValue(columnName, value.toString(), false);
	}

	/**
	 * Add INT Conditions - Equals
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            Integer value
	 */
	public void setConditionEquals(String columnName, Integer value) {
		addConditionValue(H2Conditions.EQUALS, columnName, value.toString(), null, false);
	}

	/**
	 * Add BOOLEAN Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            Boolean value
	 */
	public void setColumnValue(String columnName, Boolean value) {
		addColumnValue(columnName, value.toString(), false);
	}

	/**
	 * Add DECIMAL Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            BigDecimal value
	 */
	public void setColumnValue(String columnName, BigDecimal value) {
		addColumnValue(columnName, value.setScale(2).toString(), false);
	}

	/**
	 * Add DATE Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            Date value
	 */
	public void setColumnValue(String columnName, Date value) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
		addColumnValue(columnName, dtf.format(value.toInstant()), true);
	}

	/**
	 * Add DATE Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            LocalDate value
	 */
	public void setColumnValue(String columnName, LocalDate value) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
		addColumnValue(columnName, dtf.format(value), true);
	}

	/**
	 * Add DATE Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            Instant value
	 */
	public void setColumnValue(String columnName, Instant value) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
		addColumnValue(columnName, dtf.format(value), true);
	}

	/**
	 * Add VARCHAR Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            String value
	 */
	public void setColumnValue(String columnName, String value) {
		addColumnValue(columnName, value, true);
	}

	/**
	 * Add Column in Arrays editColumns
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            String value
	 * @param apostrophe
	 *            If true: add apostrophe
	 */
	private void addColumnValue(String columnName, String value, boolean apostrophe) {
		editColumnsName.add(columnName);
		editColumnsValue.add(setApostrophe(apostrophe, value));
	}

	/**
	 * Add Column in Arrays conditionColumns
	 * 
	 * @param condition
	 *            Condition
	 * @param columnName
	 *            Column name
	 * @param value
	 *            String value
	 * @param apostrophe
	 *            If true: add apostrophe
	 */
	private void addConditionValue(H2Conditions condition, String columnName, String value1, String value2,
			boolean apostrophe) {

		switch (condition) {
		case EQUALS:
			this.conditions.add(columnName + "=" + setApostrophe(apostrophe, value1));
			break;
		default:
			break;
		}
	}

	/**
	 * Set Apostrophe
	 * 
	 * @param apostrophe
	 * @param value
	 * @return
	 */
	private String setApostrophe(boolean apostrophe, String value) {
		if (apostrophe)
			return "'" + value + "'";
		return value;
	}

	/**
	 * Add Selection
	 * 
	 * @param tableName
	 *            Table name
	 * @param columnName
	 *            Column name
	 */
	public void setSelection(String tableName, String columnName) {
		selections.add(tableName + "." + columnName);
	}

	/**
	 * Build Insert Query
	 * 
	 * @return Query as String
	 */
	public String buildInsert() {

		String query = "INSERT INTO";
		query += " " + this.schemaName + "." + this.tableName;

		query += " (";

		boolean first = true;
		for (String editColumn : editColumnsName) {

			if (first)
				first = false;
			else
				query += ", ";

			query += editColumn;
		}

		query += ") VALUES (";

		first = true;
		for (String editColumn : editColumnsValue) {

			if (first)
				first = false;
			else
				query += ", ";

			query += editColumn;
		}

		query += ")";

		return query;

	}

	/**
	 * Build Update Query
	 * 
	 * @return Query as String
	 */
	public String buildUpdate() {

		String query = "UPDATE";
		query += " " + this.schemaName + "." + this.tableName;

		query += " SET";

		boolean first = true;

		for (int i = 0; i < editColumnsName.size(); i++) {
			if (first)
				first = false;
			else
				query += ", ";

			query += " " + editColumnsName.get(i) + "=" + editColumnsValue.get(i);
		}

		if (conditions.size() > 0) {

			query += " WHERE";

			first = true;
			for (int i = 0; i < conditions.size(); i++) {
				if (first)
					first = false;
				else
					query += " AND ";

				query += " " + conditions.get(i);
			}
		}

		return query;
	}

	/**
	 * Build Selection Query
	 * 
	 * @return Query as String
	 */
	public String buildSelection() {

		String query = "SELECT";

		boolean first = true;

		for (String select : selections) {
			if (first)
				first = false;
			else
				query += ", ";

			query += " " + select;
		}

		query += " FROM";
		query += " " + this.schemaName + "." + this.tableName;

		if (conditions.size() > 0) {

			query += " WHERE";

			first = true;
			for (int i = 0; i < conditions.size(); i++) {
				if (first)
					first = false;
				else
					query += " AND ";

				query += " " + conditions.get(i);
			}
		}

		return query;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<String> getEditColumnsName() {
		return editColumnsName;
	}

	public void setEditColumnsName(List<String> editColumnsName) {
		this.editColumnsName = editColumnsName;
	}

	public List<String> getEditColumnsValue() {
		return editColumnsValue;
	}

	public void setEditColumnsValue(List<String> editColumnsValue) {
		this.editColumnsValue = editColumnsValue;
	}

	public List<String> getConditions() {
		return conditions;
	}

	public void setConditions(List<String> conditions) {
		this.conditions = conditions;
	}

	public List<String> getSelections() {
		return selections;
	}

	public void setSelections(List<String> selections) {
		this.selections = selections;
	}

}
