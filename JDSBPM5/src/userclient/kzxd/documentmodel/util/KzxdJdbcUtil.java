package kzxd.documentmodel.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author 李洋 通过JDBC链接数据库的工具类
 */
public class KzxdJdbcUtil {

	static String[] cparams = PraseXmlUtil.getContent();
	private static String driver = cparams[0];
	private static String url = cparams[1];
	private static String username = cparams[2];
	private static String password = cparams[3];
	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
		}
	}

	public static Connection getConnection() {
		// 建立连接
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
		}
		return con;
	}

	public static void close(Connection con, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				try {
					if (con != null) {
						con.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
	}

	public static void close(Connection con, PreparedStatement ps) {
		// TODO Auto-generated method stub
		try {
			if (ps != null) {
				ps.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
