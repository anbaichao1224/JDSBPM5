package com.kzxd.rcap.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;

import com.kzxd.rcap.dao.gzrdydao;
import com.kzxd.rcap.entity.gzrdy;



public class gzrdydaoimpl implements gzrdydao{

	public void deletebyid(String uuid) throws DAOException{
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<gzrdy> gzrdy = new ArrayList<gzrdy>();
		gzrdy dy = new gzrdy();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, dy);
			dy.setUuid(uuid);
			dy.setConnection(conn);
			dy.delete();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save(List<gzrdy> beans) {
		
		String uuid;
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		uuid = (new UUID()).toString();
		gzrdy gdao = new gzrdy();
		try{
			conn = dbbase.getConn();
			for(int i = 0; i < beans.size(); i++){
				gzrdy zdao = beans.get(i);
				gdao.setUuid(zdao.getUuid());
				factory = new DAOFactory(conn, gdao);
				List list = factory.find();
				zdao.setConnection(conn);
				if(list.size()>0){
					zdao.update();
				}else{
					zdao.setUuid(uuid);
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

	public List<gzrdy> findbyid() {
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<gzrdy> gzrdy = new ArrayList<gzrdy>();
		gzrdy dy = new gzrdy();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, dy);
			gzrdy = factory.find();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  return gzrdy;
	}

}
