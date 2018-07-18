package com.sm.net.easy.h2.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.sm.net.easy.h2.execute.EasyH2Commands;

public class EasyH2ResultSet {

	private ResultSet resultSet;
	private Connection connection;
	private Statement statement;

	public EasyH2ResultSet(Connection connection, Statement statement, ResultSet resultSet) {
		super();
		this.connection = connection;
		this.statement = statement;
		this.resultSet = resultSet;
	}

	public void close() {
		EasyH2Commands.closeResultSet(this.resultSet);
		EasyH2Commands.closeStatement(this.statement);
		EasyH2Commands.closeConnection(this.connection);
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

}
