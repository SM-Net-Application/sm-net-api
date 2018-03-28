package com.sm.net.h2;

public class StandardQuery {

	public static String getCreateSchemaQuery(String schema) {

		String query = "";

		query += "CREATE SCHEMA IF NOT EXISTS";
		query += " ";
		query += schema;

		return query;
	}

	public static String getAllRowsFromTable(String schema, String table) {

		String query = "";

		query += "SELECT * FROM";
		query += " ";
		query += schema;
		query += ".";
		query += table;

		return query;
	}
}
