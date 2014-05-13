package com.kzxd.rcap.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;

import com.kzxd.rcap.dao.sxtxdao;
import com.kzxd.rcap.entity.sxtx;



public class sxtxdaoimpl implements sxtxdao{

	public void deletebyid(String uuid) throws DAOException{
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<sxtx> sxtx = new ArrayList<sxtx>();
		sxtx tx = new sxtx();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, tx);
			tx.setUuid(uuid);
			tx.setConnection(conn);
			tx.delete();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void save(List<sxtx> beans) {
		
		String uuid;
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		uuid = (new UUID()).toString();
		try{
			conn = dbbase.getConn();
			for(int i = 0; i < beans.size(); i++){
				sxtx zdao = beans.get(i);
				factory = new DAOFactory(conn, zdao);
				zdao.setConnection(conn);
				zdao.create();
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

	public List<sxtx> findbyid(String personid) {
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<sxtx> sxtx = new ArrayList<sxtx>();
		sxtx tx = new sxtx();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, tx);
			tx.setPersonid(personid);
			sxtx = factory.find();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  return sxtx;
	}
}
