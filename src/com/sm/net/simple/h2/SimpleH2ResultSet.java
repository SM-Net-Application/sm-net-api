package com.sm.net.simple.h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class SimpleH2ResultSet {

	private ResultSet resultSet;
	private Connection connection;
	private Statement statement;

	public SimpleH2ResultSet(Connection connection, Statement statement, ResultSet resultSet) {
		super();
		this.connection = connection;
		this.statement = statement;
		this.resultSet = resultSet;
	}

	public void close() {
		SimpleH2SQLCommands.closeResultSet(this.resultSet);
		SimpleH2SQLCommands.closeStatement(this.statement);
		SimpleH2SQLCommands.closeConnection(this.connection);
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

}
