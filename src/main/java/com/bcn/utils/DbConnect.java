package com.bcn.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DbConnect {
	@Value("${JDBC_URL_DEV}")
	private String JDBC_URL;

	@Value("${JDBC_URL_PROD}")
	private String JDBC_PROD;

	@Value("${JDBC_USER}")
	private String JDBC_USER;

	@Value("${JDBC_PASSWORD}")
	private String JDBC_PASSWORD;

	public java.sql.Connection getConnection() throws Exception, SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}

	public void close(ResultSet rs) throws Exception, SQLException {
		rs.close();
	}

	public void close(PreparedStatement ps) throws Exception, SQLException {
		ps.close();
	}

	public void close(Connection conn) throws Exception, SQLException {
		conn.close();
	}

	public void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) throws Exception, SQLException {
		if (rs != null) {
			rs.close();
		}

		if (ps != null) {
			ps.close();
		}

		if (conn != null) {
			conn.close();
		}
	}

	public void closeConnection(Connection conn, PreparedStatement ps) throws Exception, SQLException {
		if (ps != null) {
			ps.close();
		}

		if (conn != null) {
			conn.close();
		}
	}
}
