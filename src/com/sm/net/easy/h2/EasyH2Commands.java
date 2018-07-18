package com.sm.net.simple.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.jdbcx.JdbcDataSource;

public class SimpleH2SQLCommands {

	/**
	 * Create JDBC Connection Pool
	 * 
	 * @param jdbcUrl
	 *            JDBC URL
	 * @param userName
	 *            Database user name
	 * @param userPassword
	 *            Database user password
	 * @return JDBC Connection Pool object
	 */
	public static JdbcConnectionPool createJdbcConnectionPool(String jdbcUrl, String userName, String userPassword) {

		JdbcDataSource jdbcDataSource = new JdbcDataSource();

		jdbcDataSource.setUrl(jdbcUrl);
		jdbcDataSource.setUser(userName);
		jdbcDataSource.setPassword(userPassword);

		return JdbcConnectionPool.create(jdbcDataSource);
	}

	/**
	 * Open connection
	 * 
	 * @param jdbcConnectionPool
	 * @return Connection object oder null
	 */
	public static Connection openConnection(JdbcConnectionPool jdbcConnectionPool) {
		try {
			return jdbcConnectionPool.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}

	/**
	 * Execute Create Query
	 * 
	 * @param query
	 * @param jdbcConnectionPool
	 * @return
	 */
	public static boolean runQuery(String query, JdbcConnectionPool jdbcConnectionPool) {

		boolean execute = true;
		Connection connection = openConnection(jdbcConnectionPool);

		if (connection != null) {
			Statement statement = null;
			try {
				statement = connection.createStatement();
				statement.execute(query);
			} catch (SQLException e) {
				execute = false;
			} finally {
				closeStatement(statement);
				closeConnection(connection);
			}
		} else
			execute = false;

		return execute;
	}

	/**
	 * Execute Insert, Delete or Update Query
	 * 
	 * @param query
	 * @param jdbcConnectionPool
	 * @return
	 */
	public static int runUpdateQuery(String query, JdbcConnectionPool jdbcConnectionPool) {

		int rowCount = -1;
		Connection connection = openConnection(jdbcConnectionPool);

		if (connection != null) {
			Statement statement = null;
			try {
				statement = connection.createStatement();
				rowCount = statement.executeUpdate(query);
			} catch (SQLException e) {
			} finally {
				closeStatement(statement);
				closeConnection(connection);
			}
		}

		return rowCount;
	}

	/**
	 * Execute Select Query
	 * 
	 * @param query
	 * @param jdbcConnectionPool
	 * @return SimpleH2ResultSet Object, run the method "close" after processing
	 *         the result
	 */
	public static SimpleH2ResultSet runSelectQuery(String query, JdbcConnectionPool jdbcConnectionPool) {

		SimpleH2ResultSet simpleH2ResultSet = null;
		Connection connection = openConnection(jdbcConnectionPool);

		if (connection != null) {
			Statement statement = null;
			try {
				statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(query);
				if (resultSet != null)
					simpleH2ResultSet = new SimpleH2ResultSet(connection, statement, resultSet);

			} catch (SQLException e) {
			}
		}

		return simpleH2ResultSet;
	}

	/**
	 * Execute a Insert Query and return the auto-generated keys
	 * 
	 * @param query
	 * @param jdbcConnectionPool
	 * @return
	 */
	public static List<Integer> runInsertQuery(String query, JdbcConnectionPool jdbcConnectionPool) {

		List<Integer> indexes = new ArrayList<>();
		Connection connection = null;
		connection = openConnection(jdbcConnectionPool);

		if (connection != null) {
			PreparedStatement preparedStatement = null;
			ResultSet resultSet = null;
			try {
				preparedStatement = connection.prepareStatement(query, new String[] { "ID" });
				preparedStatement.executeUpdate();
				resultSet = preparedStatement.getGeneratedKeys();

				while (resultSet.next())
					indexes.add(new Integer(resultSet.getInt(1)));

			} catch (SQLException e) {
			} finally {
				closeResultSet(resultSet);
				closePreparedStatement(preparedStatement);
				closeConnection(connection);
			}
		}

		return indexes;
	}

	/**
	 * Close connection
	 * 
	 * @param connection
	 *            Connection object
	 */
	public static void closeConnection(Connection connection) {

		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
			}
	}

	/**
	 * Close statement
	 * 
	 * @param statement
	 *            Statement object
	 */
	public static void closeStatement(Statement statement) {

		if (statement != null)
			try {
				statement.close();
			} catch (SQLException e) {
			}
	}

	/**
	 * Close prepared statement
	 * 
	 * @param preparedStatement
	 *            Prepared statement object
	 */
	public static void closePreparedStatement(PreparedStatement preparedStatement) {

		if (preparedStatement != null)
			try {
				preparedStatement.close();
			} catch (SQLException e) {
			}
	}

	/**
	 * Close resultSet
	 * 
	 * @param resultSet
	 *            ResultSet object
	 */
	public static void closeResultSet(ResultSet resultSet) {

		if (resultSet != null)
			try {
				resultSet.close();
			} catch (SQLException e) {
			}
	}
}
