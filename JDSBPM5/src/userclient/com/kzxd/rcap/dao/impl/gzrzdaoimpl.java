package com.kzxd.rcap.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;

import com.kzxd.rcap.dao.gzrzdao;
import com.kzxd.rcap.entity.gzrz;



public class gzrzdaoimpl implements gzrzdao{

	public void deletebyid(String uuid) throws DAOException{
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<gzrz> gzrz = new ArrayList<gzrz>();
		gzrz rz = new gzrz();
		try {
			//conn = dbbase.getConn();
			//Statement db=conn.createStatement();
			//String sql = " delete from KZXD_GZRZ t where t.uuid = '"+uuid+"'";
			//db.execute(sql);
			//db.close();
			//conn.commit();
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, rz);
			rz.setUuid(uuid);
			rz.setConnection(conn);
			rz.delete();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
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
	}

	public void save(List<gzrz> beans) {
		
		String uuid;
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		uuid = (new UUID()).toString();
		gzrz gdao = new gzrz();
		try{
			conn = dbbase.getConn();
			for(int i = 0; i < beans.size(); i++){
				gzrz zdao = beans.get(i);
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
		}finally{
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
		
	}

	public List<gzrz> findbyid(String personid) {
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<gzrz> gzrz = new ArrayList<gzrz>();
		gzrz rz = new gzrz();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, rz);
			//rz.setUuid(uuid);
			//gzrz dao = (gzrz)tjdao.findByPrimaryKey();
			rz.setPersonid(personid);
			gzrz = factory.find();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
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
	  return gzrz;
	}

	

}
