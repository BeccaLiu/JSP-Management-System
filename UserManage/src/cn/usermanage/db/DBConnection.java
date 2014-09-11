package cn.usermanage.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class DBConnection {
	private static String JDBC_DRIVER;

	private static String JDBC_URL;

	private static String USERNAME;

	private static String PASSWORD;

	// 读取数据库配置信�?
	static {
		try {
			Properties properties = new Properties();
			InputStream is = DBConnection.class
					.getResourceAsStream("/database.properties");
			properties.load(is);
			is.close();
			JDBC_DRIVER = StringUtils.trim(properties.getProperty("driver"));
			JDBC_URL = StringUtils.trim(properties.getProperty("url"));
			USERNAME = StringUtils.trim(properties.getProperty("user"));
			PASSWORD = StringUtils.trim(properties.getProperty("password"));

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage()+"   [!] DataBase connecton error");
		}
	}

	/**
	 * 获得连接对象
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return conn;
	}

	/**
	 * 关闭当前连接
	 * 
	 * @param conn
	 */
	public static void closeConn(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 关闭PreparedStatement对象
	 * 
	 * @param pstmt
	 */
	public static void closePreparedStatement(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	/**
	 * 关闭ResultSet对象
	 * 
	 * @param rs
	 */
	public static void closeResultset(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}

	public static void main(String[] args) {
		Connection conn = DBConnection.getConnection();
		System.out.println(conn.toString());
	}
}
