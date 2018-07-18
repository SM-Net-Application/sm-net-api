package com.sm.net.easy.h2.db;

import java.util.ArrayList;
import java.util.List;

import com.sm.net.easy.h2.util.H2DataTypes;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Simple H2 Table
 *
 */
public class EasyH2Table {

	private String tableName;
	private String schema;
	private boolean ifNotExists;
	private List<EasyH2Column> columns;

	/**
	 * Simple H2 Table Constructor
	 */
	public EasyH2Table(String tableName, String schema) {
		super();

		this.tableName = tableName;
		this.schema = schema;
		this.ifNotExists = false;
		columns = new ArrayList<>();
	}

	/**
	 * Simple H2 Table IF NOT EXISTS Constructor
	 */
	public EasyH2Table(String tableName, String schema, boolean ifNotExists) {
		super();
		this.tableName = tableName;
		this.schema = schema;
		this.ifNotExists = ifNotExists;
		columns = new ArrayList<>();
	}

	public void addColumn(EasyH2Column... columns) {
		for (EasyH2Column column : columns)
			this.columns.add(column);
	}

	public String buildQuery() {

		String query = "CREATE TABLE";

		if (this.ifNotExists)
			query += " IF NOT EXISTS";

		query += " " + this.schema + "." + this.tableName;

		query += "(";

		boolean first = true;
		for (EasyH2Column column : columns) {

			// Separator
			if (first)
				first = false;
			else
				query += ", ";

			// Column name
			query += column.getColumnName() + " ";

			// Column type
			H2DataTypes h2DataType = column.getH2DataType();
			query += h2DataType.getName();

			if (h2DataType == H2DataTypes.VARCHAR)
				query += "(" + column.getCharLenght().intValue() + ")";

			// Primary Key
			if (column.isPrimaryKey()) {
				query += " PRIMARY KEY";
				if (column.isAutoIncrement())
					query += " AUTO_INCREMENT";
			}

			// Not Null
			if (column.isNotNull())
				query += " NOT NULL";
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

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public List<EasyH2Column> getColumns() {
		return columns;
	}

	public void setColumns(List<EasyH2Column> columns) {
		this.columns = columns;
	}

	public boolean isIfNotExists() {
		return ifNotExists;
	}

	public void setIfNotExists(boolean ifNotExists) {
		this.ifNotExists = ifNotExists;
	}
}
