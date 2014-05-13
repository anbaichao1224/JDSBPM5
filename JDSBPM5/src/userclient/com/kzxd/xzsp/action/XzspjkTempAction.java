package com.kzxd.xzsp.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;

import com.kzxd.xzsp.util.XzspjkTemp;
import com.kzxd.xzsp.util.XzspjkTempBean;

public class XzspjkTempAction {
	
	private int totalCount;
	
public void save(XzspjkTempBean bean) {
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XzspjkTemp dao = new XzspjkTemp();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, dao);
			dao.setUuid(bean.getUuid());
			dao.setXmlstr(bean.getXmlstr());
			dao.setXmlstr2(bean.getXmlstr2());
			dao.setBsnum(bean.getBsnum());
			dao.setConnection(conn);
			dao.create();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}


public XzspjkTemp findByUuid(String uuid){
	DBBeanBase dbbase;
	Connection conn;
	dbbase = new DBBeanBase("bpm", true);
	conn = null;
	DAOFactory factory = null;
	XzspjkTemp dao = new XzspjkTemp();
	XzspjkTemp xzspdao = null;
	try{
		conn = dbbase.getConn();
		factory = new DAOFactory(conn,dao);
		dao.setUuid(uuid);
		List<XzspjkTemp> list = (List<XzspjkTemp>)factory.find();
		if(list.size()>0){
			xzspdao = list.get(0);
			return xzspdao;
		}
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
	
	try{
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	return null;
}


public List<XzspjkTempBean> findAll(){
	List<XzspjkTempBean> list=new ArrayList<XzspjkTempBean>();
			
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		try{
			conn = dbbase.getConn();
		    String countsql = "select count(*) from XZSPJK_LSB ";
			st = conn.createStatement();
			rs = st.executeQuery(countsql);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
			
			String sql="SELECT * FROM "+
			"("+
			"SELECT A.*, ROWNUM RN "+
			"FROM (select * from XZSPJK_LSB ) A "
			+")"+
			"WHERE RN>="+1+" AND RN<="+10;
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				XzspjkTempBean bean = new XzspjkTempBean();	
				bean.setUuid(rs.getString("uuid"));
				bean.setXmlstr(rs.getString("xmlstr"));
				bean.setXmlstr2(rs.getString("xmlstr2"));
				bean.setBsnum(rs.getString("bsnum"));
				list.add(bean);
			}
			
		}catch(Exception e){
			
		}finally{
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				st.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		return list;
}

public void delete(XzspjkTempBean bean) {
	// TODO Auto-generated method stub
	DBBeanBase dbbase;
	Connection conn;
	dbbase = new DBBeanBase("bpm", true);
	conn = null;
	DAOFactory factory = null;
	XzspjkTemp dao = new XzspjkTemp();
	XzspjkTemp xzspdao= null;
	try{
		conn = dbbase.getConn();
		factory = new DAOFactory(conn, dao);
		xzspdao=findByUuid(bean.getUuid());
		xzspdao.setConnection(conn);
		xzspdao.delete();
		conn.commit();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
	
		try{
			conn.close();
			dbbase.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}


public int getTotalCount() {
	return totalCount;
}


public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
}






}
