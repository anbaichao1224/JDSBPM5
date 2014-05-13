package com.kzxd.nbyj.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kzxd.nbyj.dao.NbyjDAO;
import com.kzxd.nbyj.entity.NbyjBean;


import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;


public class NbyjService {
	public void save(NbyjBean bean){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		NbyjDAO nbyjdao = new NbyjDAO();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, nbyjdao);
			nbyjdao.setUuid(bean.getUuid());
			nbyjdao.setNbyj(bean.getNbyj());
			nbyjdao.setOrg(bean.getOrg());
			nbyjdao.setPersonname(bean.getPersonname());
			nbyjdao.setCreatedate(bean.getCreatedate());
			nbyjdao.setActivityinst_id(bean.getActivityinst_id());
			nbyjdao.setProcessinst_id(bean.getProcessinst_id());
			nbyjdao.setHj(bean.getHj());
			nbyjdao.setConnection(conn);
			nbyjdao.create();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
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
		
	}
	
	
public List<NbyjDAO> findByProcessInstId(String processinstId){
		
		List<NbyjDAO> nbyjlist = new ArrayList<NbyjDAO>();
		DBBeanBase dbbase;
		Connection conn=null;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		NbyjDAO nbyjdao = new NbyjDAO();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, nbyjdao);
			nbyjdao.setProcessinst_id(processinstId);
			nbyjdao.addOrderBy("replyTime", false);
			nbyjlist = (List<NbyjDAO>)factory.find();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		  try{
			conn.close(); 
		  }catch(Exception e){
			e.printStackTrace();
		  }
		  
		  try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
	
		return nbyjlist;
		
		
		
		
		
		
	}
	
}
