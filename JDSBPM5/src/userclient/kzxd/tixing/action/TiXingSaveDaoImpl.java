package kzxd.tixing.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import kzxd.tixing.dao.TiXingDao;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.util.UUID;

public class TiXingSaveDaoImpl implements TiXingSaveDao{

	public void save(String personid, String mkname, String title, String delid) {
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		TiXingDao txdao = new TiXingDao(); 
		try {
			String uuid = new UUID().toString();
			conn = dbbase.getConn();
			txdao.setUuid(uuid);
			txdao.setPersonid(personid);
			txdao.setMkname(mkname);
			txdao.setTitle(title);
			txdao.setTime(new Date());
			txdao.setDelid(delid);
			txdao.setConnection(conn);
			txdao.create();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		
	}

	public void delete(String delid) {
		DBBeanBase dbbase = null;
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;
		try {
			dbbase = new DBBeanBase("bpm", true);
			conn = dbbase.getConn();
			String sql = "delete from KZXD_TIXING where delid='"+delid+"'";
			pst = conn.prepareStatement(sql);
			rs = pst.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
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
		
	}

}
