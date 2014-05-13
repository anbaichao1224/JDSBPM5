package com.kzxd.tbm.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;


import com.kzxd.tbm.dao.WjzlCatalogDAO;

public class PersonService {
	 //增加根目录
	public void addPerson(WjzlCatalogDAO wjzldao){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection connect  = dbbase.getConn();
		try{
			wjzldao.setConnection(connect);
			wjzldao.create();
			connect.commit();
		}catch(SQLException e){
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (DAOException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dbbase.close();
			
		}
	}
	
	public List<WjzlCatalogDAO>findperson(String parentId){
		List<WjzlCatalogDAO> personlist = new ArrayList<WjzlCatalogDAO>();
		DBBeanBase dbbase;
		Connection conn=null;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		WjzlCatalogDAO persondao = new WjzlCatalogDAO();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, persondao);
			persondao.setParentId(parentId);
			personlist = (List<WjzlCatalogDAO>)factory.find();
		}catch(Exception e){
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
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
		
		return personlist;
	}
}
