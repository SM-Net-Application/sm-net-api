package com.sm.net.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.jdbcx.JdbcDataSource;

public class SQLCommands {

	public static JdbcConnectionPool createJdbcConnectionPool(String url, String user, String password) {

		JdbcDataSource jdbcDataSource = new JdbcDataSource();

		jdbcDataSource.setUrl(url);
		jdbcDataSource.setUser(user);
		jdbcDataSource.setPassword(password);

		return JdbcConnectionPool.create(jdbcDataSource);

	}

	public static void disposeJdbcConnectionPool(JdbcConnectionPool jdbcConnectionPool) {
		jdbcConnectionPool.dispose();
	}

	public static Connection openConnection(JdbcConnectionPool cp) {

		Connection connection = null;
		try {
			connection = cp.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection(Connection connection) {

		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeStatement(Statement statement) {

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closePreparedStatement(PreparedStatement preparedStatement) {

		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeResultSet(ResultSet resultSet) {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void runQuery(String query, JdbcConnectionPool cp) {

		Connection connection = null;
		connection = openConnection(cp);

		if (connection != null) {
			Statement statement = null;
			try {
				statement = connection.createStatement();
				statement.execute(query);
			} catch (SQLException e) {
				if (!e.getMessage().contains("already exists")) {
					e.printStackTrace();
				}
			} finally {
				closeStatement(statement);
				closeConnection(connection);
			}
		}
	}

	public static int runQueryUpdate(String query, JdbcConnectionPool cp) {

		Connection connection = null;
		connection = openConnection(cp);

		int status = -1;

		if (connection != null) {
			Statement statement = null;
			try {
				statement = connection.createStatement();
				status = statement.executeUpdate(query);
				// status = statement.execute(query);
			} catch (SQLException e) {
				if (!e.getMessage().contains("already exists")) {
					e.printStackTrace();
				}
			} finally {
				closeStatement(statement);
				closeConnection(connection);
			}
		}

		return status;
	}

	public static Integer runQueryGetId(String query, JdbcConnectionPool cp) {

		Integer indice = null;

		Connection connection = null;
		connection = openConnection(cp);

		if (connection != null) {
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				preparedStatement = connection.prepareStatement(query, new String[] { "ID" });
				preparedStatement.executeUpdate();

				resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next()) {
					indice = new Integer(resultSet.getInt(1));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResultSet(resultSet);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
		}

		return indice;
	}
}
