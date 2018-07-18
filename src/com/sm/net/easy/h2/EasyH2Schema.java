package com.sm.net.easy.h2;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Simple H2 Schema
 *
 */
public class EasyH2Schema {

	private String schemaName;
	private boolean ifNotExists;

	/**
	 * Simple H2 Schema Constructor
	 * 
	 * @param schemaName
	 *            Schema name
	 * @param ifNotExists
	 *            If true: create schema only if not exists
	 */
	public EasyH2Schema(String schemaName, boolean ifNotExists) {
		super();

		this.schemaName = schemaName;
		this.ifNotExists = ifNotExists;
	}

	public String buildQuery() {

		String query = "CREATE SCHEMA ";

		if (ifNotExists)
			query += "IF NOT EXISTS ";

		query += this.schemaName;

		return query;
	}

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public boolean isIfNotExists() {
		return ifNotExists;
	}

	public void setIfNotExists(boolean ifNotExists) {
		this.ifNotExists = ifNotExists;
	}
}
