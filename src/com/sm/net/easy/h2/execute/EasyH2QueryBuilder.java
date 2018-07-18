package com.sm.net.easy.h2.execute;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.sm.net.easy.h2.util.EasyH2Util;

public class EasyH2QueryBuilder {

	private String fromSchema;
	private String fromTable;
	private List<String> updateName;
	private List<String> updateValue;
	private List<String> where;
	private List<String> select;
	private List<String> order;

	public EasyH2QueryBuilder(String schema, String table) {
		super();
		this.fromSchema = schema;
		this.fromTable = table;

		this.updateName = new ArrayList<>();
		this.updateValue = new ArrayList<>();
		this.where = new ArrayList<>();
		this.select = new ArrayList<>();
		this.order = new ArrayList<>();
	}

	/**
	 * Update/Insert INT Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            Integer value
	 */
	public void addUpdate(String columnName, Integer value) {
		if (value != null)
			addUpdateValue(columnName, value.toString(), false);
	}

	/**
	 * Update/Insert BOOLEAN Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            Boolean value
	 */
	public void addUpdate(String columnName, Boolean value) {
		if (value != null)
			addUpdateValue(columnName, value.toString(), false);
	}

	/**
	 * Update/Insert DECIMAL Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            BigDecimal value
	 */
	public void addUpdate(String columnName, BigDecimal value) {
		if (value != null)
			addUpdateValue(columnName, value.setScale(2).toString(), false);
	}

	/**
	 * Update/Insert DATE Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            Date value
	 */
	public void addUpdate(String columnName, Date value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			addUpdateValue(columnName, dtf.format(value.toInstant()), true);
		}
	}

	/**
	 * Update/Insert DATE Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            LocalDate value
	 */
	public void addUpdate(String columnName, LocalDate value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			addUpdateValue(columnName, dtf.format(value), true);
		}
	}

	/**
	 * Update/Insert DATE Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            Instant value
	 */
	public void addUpdate(String columnName, Instant value) {
		if (value != null) {
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());
			addUpdateValue(columnName, dtf.format(value), true);
		}
	}

	/**
	 * Update/Insert VARCHAR Column
	 * 
	 * @param columnName
	 *            Column name
	 * @param value
	 *            String value
	 */
	public void addUpdate(String columnName, String value) {
		if (value != null)
			addUpdateValue(columnName, value, true);
	}

	/**
	 * Add Column in Arrays "update"
	 * 
	 * @param column
	 *            Column name
	 * @param value
	 *            String value
	 * @param apostrophe
	 *            If true: add apostrophe
	 */
	private void addUpdateValue(String column, String value, boolean apostrophe) {
		updateName.add(column);
		updateValue.add(EasyH2Util.setApostrophe(apostrophe, value));
	}

	/**
	 * Add Condition
	 * 
	 * @param condition
	 *            Use the class: EasyH2Conditions
	 */
	public void addCondition(String condition) {
		if (!condition.trim().isEmpty())
			this.where.add(condition);
	}

	/**
	 * Add Order
	 * 
	 * @param order
	 *            Use the class: EasyH2Orders
	 */
	public void addOrder(String order) {
		if (!order.trim().isEmpty())
			this.order.add(order);
	}

	/**
	 * Add Selection
	 * 
	 * @param table
	 *            Table name
	 * @param column
	 *            Column name
	 */
	public void addSelection(String table, String column) {
		select.add(table + "." + column);
	}

	/**
	 * Build Insert Query
	 * 
	 * @return Query as String
	 */
	public String buildInsertQuery() {

		String query = "INSERT INTO ";
		query += getTable();
		query += " (";
		query += getColumns();
		query += ") VALUES (";
		query += getValues();
		query += ")";

		return query;
	}

	/**
	 * Build Update Query
	 * 
	 * @return Query as String
	 */
	public String buildUpdateQuery() {

		String query = "UPDATE ";
		query += getTable();
		query += " SET";
		query += getUpdates();
		query += getConditions();

		return query;
	}

	/**
	 * Build Selection Query
	 * 
	 * @return Query as String
	 */
	public String buildSelection() {

		String query = "SELECT ";
		query += getSelections();
		query += " FROM ";
		query += getTable();
		query += getConditions();
		query += getOrders();

		return query;
	}

	/**
	 * Return SchemaName.TableName
	 * 
	 * @return
	 */
	private String getTable() {
		return this.fromSchema + "." + this.fromTable;
	}

	/**
	 * Return String: column1, column2, column3 ...
	 * 
	 * @return
	 */
	private String getColumns() {

		String columns = "";
		boolean first = true;

		for (String columnName : updateName) {
			if (first)
				first = false;
			else
				columns += ", ";

			columns += columnName;
		}
		return columns;
	}

	/**
	 * Return String: value1, value2, value3 ...
	 * 
	 * @return
	 */
	private String getValues() {

		String values = "";
		boolean first = true;

		for (String columnValue : updateValue) {
			if (first)
				first = false;
			else
				values += ", ";

			values += columnValue;
		}
		return values;
	}

	/**
	 * Return String: column1=value1, column1=value2, column3=value3 ...
	 * 
	 * @return
	 */
	private String getUpdates() {

		String updates = "";
		boolean first = true;

		for (int i = 0; i < updateName.size(); i++) {
			if (first)
				first = false;
			else
				updates += ",";

			updates += " " + updateName.get(i) + "=" + updateValue.get(i);
		}
		return updates;
	}

	/**
	 * Return WHERE Statement
	 * 
	 * @return
	 */
	private String getConditions() {

		String conditions = "";
		boolean first = true;

		if (where.size() > 0) {

			conditions += " WHERE";
			for (int i = 0; i < where.size(); i++) {
				if (first)
					first = false;
				else
					conditions += " AND";

				conditions += " " + where.get(i);
			}
		}
		return conditions;
	}

	/**
	 * Return String: table.column1, table.column2, table.column3 ...
	 * 
	 * @return
	 */
	private String getSelections() {

		String selections = "";
		boolean first = true;

		for (String sel : select) {
			if (first)
				first = false;
			else
				selections += ",";

			selections += " " + sel;
		}
		return selections;
	}

	/**
	 * Return ORDER BY Statement
	 * 
	 * @return
	 */
	private String getOrders() {

		String orders = "";
		boolean first = true;

		if (order.size() > 0) {

			orders += " ORDER BY";
			for (int i = 0; i < order.size(); i++) {
				if (first)
					first = false;
				else
					orders += " AND";

				orders += " " + order.get(i);
			}
		}
		return orders;
	}
}
