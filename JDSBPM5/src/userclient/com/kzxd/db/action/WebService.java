package com.kzxd.db.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import net.itjds.common.database.DBBeanBase;


public class WebService {
	public Integer Chaxun(){
		ResultSet rs = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		int count = -1;
		try{
			conn = dbbase.getConn();
			String countsql = "select count(*) from KZXD_TIXING";
			st = conn.createStatement();
			rs = st.executeQuery(countsql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				try {
					dbbase.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		return count;
		
	}
}
