package com.kzxd.rcap.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;

import com.kzxd.rcap.dao.rcapdao;
import com.kzxd.rcap.entity.rcap;



public class rcapdaoimpl implements rcapdao{

	public void deletebyid(String uuid) throws DAOException{
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<rcap> rcap = new ArrayList<rcap>();
		rcap rc = new rcap();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, rc);
			rc.setUuid(uuid);
			rc.setConnection(conn);
			rc.delete();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save(List<rcap> beans) {
		
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		rcap rdao = new rcap();
		try{
			conn = dbbase.getConn();
			for(int i = 0; i < beans.size(); i++){
				rcap zdao = beans.get(i);
				rdao.setUuid(zdao.getUuid());
				factory = new DAOFactory(conn, rdao);
				List list = factory.find();
				
				zdao.setConnection(conn);
				if(list.size()>0){
					zdao.update();
				}else{
					zdao.setUuid(new UUID().toString());
					zdao.create();
				
				}
			}
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

	public List<rcap> findbyid(String personid) {
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<rcap> rcap = new ArrayList<rcap>();
		rcap rc = new rcap();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, rc);
			rc.setPersonid(personid);
			rcap = factory.find();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  return rcap;
	}
	

}
