package com.sm.net.h2;

import java.util.ArrayList;
import java.util.List;

import com.sm.net.h2.enumeration.DataTypesDate;


public class InsertBuilder {

	private String schemaName;
	private String tableName;
	private List<String> columns;
	private List<String> values;

	public InsertBuilder(String schemaName, String tableName) {
		super();
		this.schemaName = schemaName;
		this.tableName = tableName;
		columns = new ArrayList<>();
		values = new ArrayList<>();
	}

	public void addNumberColumn(String columnName, String value) {
		this.columns.add(columnName);
		this.values.add(value);
	}

	public void addTextColumn(String columnName, String value) {
		this.columns.add(columnName);
		this.values.add("'" + value + "'");
	}

	public void addDateColumn(String columnName, DataTypesDate dataTypesDate, String value, String stringFormat) {
		this.columns.add(columnName);
		switch (dataTypesDate) {
		case TIMESTAMP:

			String valueTemp = "parsedatetime";
			valueTemp += "(";
			valueTemp += "'";
			valueTemp += value;
			valueTemp += "'";
			valueTemp += ",";
			valueTemp += " ";
			valueTemp += "'";
			valueTemp += stringFormat;
			valueTemp += "'";
			valueTemp += ")";

			this.values.add(valueTemp);
			break;

		default:
			break;
		}
	}

	public String getQuery() {

		String query = "INSERT INTO";
		query += " ";
		query += this.schemaName;
		query += ".";
		query += this.tableName;
		query += "(";

		boolean first = true;
		for (String column : columns) {

			if (!first) {
				query += ",";
				query += " ";
			} else {
				first = false;
			}

			query += column;
		}

		query += ")";
		query += " ";
		query += "VALUES";
		query += " ";
		query += "(";

		first = true;
		for (String value : values) {

			if (!first) {
				query += ",";
				query += " ";
			} else {
				first = false;
			}

			query += value;
		}

		query += ")";

		return query;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public List<String> getColumns() {
		return columns;
	}

	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	public List<String> getValue() {
		return values;
	}

	public void setValue(List<String> value) {
		this.values = value;
	}
}