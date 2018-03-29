package com.sm.net.h2;

import java.util.ArrayList;
import java.util.List;

import com.sm.net.h2.enumeration.DataTypesDate;
import com.sm.net.h2.enumeration.DataTypesNumber;

public class TableBuilder {

	private String schemaName;
	private String tableName;
	private List<String> columns;

	public TableBuilder(String schemaName, String tableName) {
		super();
		this.schemaName = schemaName;
		this.tableName = tableName;
		columns = new ArrayList<>();
	}

	public void addIDColumn() {
		this.columns.add("ID BIGINT PRIMARY KEY AUTO_INCREMENT");
	}

	public void addTextColumn(String columnName, Integer lenght) {
		this.columns.add(columnName + " VARCHAR(" + lenght.intValue() + ")");
	}

	public void addDateColumn(String columnName, DataTypesDate dataTypesDate) {

		String sql = columnName;
		sql += " ";
		sql += dataTypesDate.getName();

		this.columns.add(sql);
	}

	public void addNumberColumn(String columnName, DataTypesNumber dataTypesNumber, Boolean notNull) {

		String sql = columnName;
		sql += " ";
		sql += dataTypesNumber.getName();

		if (notNull.booleanValue()) {
			sql += " NOT NULL";
		} else {
			sql += " NULL";
		}

		this.columns.add(sql);
	}

	public String getQuery() {

		String query = "CREATE TABLE";
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
}