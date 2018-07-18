package com.sm.net.easy.h2.db;

import java.io.File;
import java.util.List;

import org.h2.jdbcx.JdbcConnectionPool;

import com.sm.net.easy.h2.execute.EasyH2Commands;

/**
 * 
 * @author SM-Net <http://sm-netzwerk.com>
 * 
 *         Simple H2 Database
 *
 */
public class EasyH2Database {

	public final static String urlJdbcH2 = "jdbc:h2:";
	public final static String urlAutoServer = "AUTO_SERVER=TRUE;";

	private String message;

	private boolean creable;
	private File databaseFolder;
	private String databaseFileName;

	private boolean login;
	private String userName;
	private String userPassword;

	private boolean autoServer;

	private boolean connectionPool;
	private JdbcConnectionPool jdbcConnectionPool;

	/**
	 * Fields initialization
	 */
	private void initFields() {
		this.creable = false;
		this.setMessage("");
		this.databaseFolder = null;
		this.databaseFileName = "";
		this.login = false;
		this.userName = "";
		this.userPassword = "";
		this.autoServer = false;
		this.connectionPool = false;
		this.jdbcConnectionPool = null;
	}

	/**
	 * Simple H2 Database Construttor
	 * 
	 * @param databaseFolder
	 *            Where to create the file
	 * @param databaseFileName
	 *            Database file name
	 * @param name
	 *            Username
	 * @param password
	 *            Password
	 */
	public EasyH2Database(File databaseFolder, String databaseFileName, String userName, String userPassword,
			boolean autoServer) {
		super();

		initFields();

		if (databaseFolder != null)
			try {
				if (databaseFolder.isDirectory()) {
					if ((!databaseFileName.isEmpty()) && databaseFileName.matches("^\\w+$")) {
						this.setDatabaseFolder(databaseFolder);
						this.setDatabaseFileName(databaseFileName);
						this.creable = true;
						this.setAutoServer(autoServer);
						this.setLoginCredentials(userName, userPassword);
					} else
						this.setMessage("Database file name not valid: " + databaseFileName);
				} else
					this.setMessage(
							"The folder does not exist or is not a folder: " + databaseFolder.getAbsolutePath());
			} catch (SecurityException e) {
				this.setMessage("Access denied: " + databaseFolder.getAbsolutePath());
			}
		else
			this.setMessage("Invalid folder: null");
	}

	/**
	 * Login credentials
	 * 
	 * @param name
	 *            Username
	 * @param password
	 *            Password
	 */
	public void setLoginCredentials(String name, String password) {
		if (this.creable)
			if ((!name.isEmpty()) && name.matches("^\\w+$")) {
				this.login = true;
				this.userName = name;
				this.userPassword = password;
				this.createConnectionPool();
			} else
				this.setMessage("Invalid credentials: " + name + " | " + password);
	}

	/**
	 * Create JDBC Connection Pool to execute SQL Commands
	 */
	public void createConnectionPool() {
		if (this.login) {

			this.jdbcConnectionPool = EasyH2Commands.createJdbcConnectionPool(buildJdbcUrl(), this.userName,
					this.userPassword);

			if (this.jdbcConnectionPool != null)
				this.connectionPool = true;
			else
				this.setMessage("Creation JDBC Connection Pool failed");
		} else
			this.setMessage("Missing credentials");
	}

	/**
	 * Build JDBC URL String
	 * 
	 * @return JDBC URL String
	 */
	private String buildJdbcUrl() {

		String jdbcUrl = "";

		jdbcUrl += urlJdbcH2;
		jdbcUrl += this.databaseFolder.getAbsolutePath();
		jdbcUrl += File.separatorChar;
		jdbcUrl += this.databaseFileName;
		jdbcUrl += ";";

		if (this.autoServer)
			jdbcUrl += urlAutoServer;

		return jdbcUrl;
	}

	/**
	 * Discards all connections
	 */
	public void disposeConnectionPool() {
		if (this.jdbcConnectionPool != null)
			this.jdbcConnectionPool.dispose();
	}

	/**
	 * Create Schema in the database
	 * 
	 * @param schema
	 *            Simple H2 Schema Object
	 */
	public void createSchema(EasyH2Schema schema) {

		if (this.connectionPool) {
			if (!EasyH2Commands.executeQuery(schema.buildQuery(), this.jdbcConnectionPool))
				this.setMessage("Query CREATE SCHEMA " + schema.getSchemaName() + " failed");
		} else
			this.setMessage("JDBC Connection Pool is not available");
	}

	/**
	 * Create Table in the schema
	 * 
	 * @param table
	 */
	public void createTable(EasyH2Table table) {
		if (this.connectionPool) {
			if (!EasyH2Commands.executeQuery(table.buildQuery(), this.jdbcConnectionPool))
				this.setMessage("Query CREATE TABLE " + table.getTableName() + " failed");
		} else
			this.setMessage("JDBC Connection Pool is not available");
	}

	/**
	 * Execut Insert, Delete or Update Operation
	 * 
	 * @param query
	 */
	public void runOperation(String query) {
		if (this.connectionPool) {
			if (EasyH2Commands.executeUpdateQuery(query, this.jdbcConnectionPool) == -1)
				this.setMessage("Operation failed: " + query);
		} else
			this.setMessage("JDBC Connection Pool is not available");
	}

	/**
	 * Execute a Insert Query and return the auto-generated keys
	 * 
	 * @param query
	 */
	public List<Integer> runInsert(String query) {
		if (this.connectionPool) {
			return EasyH2Commands.executeInsertQuery(query, this.jdbcConnectionPool);
		} else
			this.setMessage("JDBC Connection Pool is not available");

		return null;
	}

	/**
	 * Execut Selection
	 * 
	 * @param query
	 */
	public EasyH2ResultSet runSelection(String query) {
		if (this.connectionPool) {
			EasyH2ResultSet simpleH2ResultSet = EasyH2Commands.executeSelectionQuery(query, this.jdbcConnectionPool);
			if (simpleH2ResultSet != null)
				return simpleH2ResultSet;
			else
				this.setMessage("Selection failed: " + query);
		} else
			this.setMessage("JDBC Connection Pool is not available");

		return null;
	}

	public boolean isCreable() {
		return creable;
	}

	public void setCreable(boolean creable) {
		this.creable = creable;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public File getDatabaseFolder() {
		return databaseFolder;
	}

	public void setDatabaseFolder(File databaseFolder) {
		this.databaseFolder = databaseFolder;
	}

	public String getDatabaseFileName() {
		return databaseFileName;
	}

	public void setDatabaseFileName(String databaseFileName) {
		this.databaseFileName = databaseFileName;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public boolean isAutoServer() {
		return autoServer;
	}

	public void setAutoServer(boolean autoServer) {
		this.autoServer = autoServer;
	}

	public JdbcConnectionPool getJdbcConnectionPool() {
		return jdbcConnectionPool;
	}

	public void setJdbcConnectionPool(JdbcConnectionPool jdbcConnectionPool) {
		this.jdbcConnectionPool = jdbcConnectionPool;
	}

	public boolean isConnectionPool() {
		return connectionPool;
	}

	public void setConnectionPool(boolean connectionPool) {
		this.connectionPool = connectionPool;
	}
}
