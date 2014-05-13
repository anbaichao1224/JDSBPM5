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
import com.kzxd.xzsp.util.XzspjkTempBF;
import com.kzxd.xzsp.util.XzspjkTempBFBean;
import com.kzxd.xzsp.util.XzspjkTempBean;

public class XzspjkTempBFAction {
	
	private int totalCount;
	
public void save(XzspjkTempBFBean bean) {
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XzspjkTempBF dao = new XzspjkTempBF();
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
			dbbase.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		

	}

public List<XzspjkTempBF> findByBSNUM(String bsnum){
	
	DBBeanBase dbbase;
	Connection conn;
	dbbase = new DBBeanBase("bpm", true);
	conn = null;
	DAOFactory factory = null;
	List<XzspjkTempBF> bsumlist = new ArrayList<XzspjkTempBF>();
	XzspjkTempBF xzspdao = new XzspjkTempBF();
	try{
		conn = dbbase.getConn();
		factory = new DAOFactory(conn,xzspdao);
		xzspdao.setBsnum(bsnum);
		 bsumlist = (List<XzspjkTempBF>)factory.find();
		
		
	}catch(Exception e){
		e.printStackTrace();
	}finally{
	
	try{
		conn.close();
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	return bsumlist;
}
public XzspjkTempBF findByUuid(String uuid){
	DBBeanBase dbbase;
	Connection conn;
	dbbase = new DBBeanBase("bpm", true);
	conn = null;
	DAOFactory factory = null;
	XzspjkTempBF dao = new XzspjkTempBF();
	XzspjkTempBF xzspdao = null;
	try{
		conn = dbbase.getConn();
		factory = new DAOFactory(conn,dao);
		dao.setUuid(uuid);
		List<XzspjkTempBF> list = (List<XzspjkTempBF>)factory.find();
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


public List<XzspjkTempBFBean> findAll(){
	List<XzspjkTempBFBean> list=new ArrayList<XzspjkTempBFBean>();
			
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		try{
			conn = dbbase.getConn();
			String sql="select * from XZSPJK_LSBBF";
			
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				XzspjkTempBFBean bean = new XzspjkTempBFBean();	
				bean.setUuid(rs.getString("uuid"));
				bean.setXmlstr(rs.getString("xmlstr"));
				bean.setXmlstr2(rs.getString("xmlstr2"));
				bean.setBsnum(rs.getString("bsnum"));
				list.add(bean);
			}
			
		}catch(Exception e){
			
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return list;
}

public List<XzspjkTempBFBean> findAll(int index,int pageSize){
	List<XzspjkTempBFBean> list=new ArrayList<XzspjkTempBFBean>();
			
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		try{
			conn = dbbase.getConn();
			
			String sql="SELECT * FROM "+
			"("+
			"SELECT A.*, ROWNUM RN "+
			"FROM (select bsnum from XZSPJK_LSBBF group by bsnum) A "
			+")"+
			"WHERE RN>"+index+" AND RN<="+(index+pageSize);
			
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				XzspjkTempBFBean bean = new XzspjkTempBFBean();	
				bean.setBsnum(rs.getString("bsnum"));
				list.add(bean);
			}
			
		}catch(Exception e){
			
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		return list;
}

public int Count(){
	ResultSet rs = null;
	PreparedStatement pst = null;
	Statement st = null;
	DBBeanBase dbbase = new DBBeanBase("bpm", true);
	Connection conn = null;
	int i=0;
	try{
		conn = dbbase.getConn();
		String countsql = "select count(*) from XZSPJK_LSBBF group by bsnum ";
		st = conn.createStatement();
		rs = st.executeQuery(countsql);
	
		while (rs.next()) {
			totalCount = rs.getInt(1);
			i++;
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

	return i;
}

public void delete(XzspjkTempBFBean bean) {
	// TODO Auto-generated method stub
	DBBeanBase dbbase;
	Connection conn;
	dbbase = new DBBeanBase("bpm", true);
	conn = null;
	DAOFactory factory = null;
	XzspjkTempBF dao = new XzspjkTempBF();
	XzspjkTempBF xzspdao= null;
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
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}


public int deleteByBSNUM(String bsnum){
	DBBeanBase dbbase;
	Connection conn;
	dbbase = new DBBeanBase("bpm", true);
	conn = null;
	conn = dbbase.getConn();
	int rs = 0;
	Statement st = null;
	String deletesql = "delete from XZSPJK_LSBBF where bsnum="+bsnum;
	try {
		st = conn.createStatement();
		rs = st.executeUpdate(deletesql);
		conn.commit();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
	
		try{
			st.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			dbbase.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	return rs;
}

public int getTotalCount() {
	return totalCount;
}


public void setTotalCount(int totalCount) {
	this.totalCount = totalCount;
}






}
