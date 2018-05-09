package com.sm.net.simple.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public static boolean runQuery(String query, JdbcConnectionPool jdbcConnectionPool) {

		boolean status = true;
		Connection connection = openConnection(jdbcConnectionPool);

		if (connection != null) {
			Statement statement = null;
			try {
				statement = connection.createStatement();
				statement.execute(query);
			} catch (SQLException e) {
				status = false;
				System.out.println(e.getMessage());
			} finally {
				closeStatement(statement);
				closeConnection(connection);
			}
		} else
			status = false;

		return status;
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

	// public static ResultSetH2 runQueryGetResultSet(String query,
	// JdbcConnectionPool jcp) {
	//
	// Connection connection = null;
	// Statement statement = null;
	// ResultSet resultSet = null;
	// ResultSetH2 resultSetH2 = null;
	// try {
	// connection = jcp.getConnection();
	// if (connection != null) {
	// statement = connection.createStatement();
	// resultSet = statement.executeQuery(query);
	// resultSetH2 = new ResultSetH2(connection, statement, resultSet);
	// }
	// } catch (SQLException e) {
	// System.out.println(e.getMessage());
	// }
	//
	// return resultSetH2;
	// }

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
