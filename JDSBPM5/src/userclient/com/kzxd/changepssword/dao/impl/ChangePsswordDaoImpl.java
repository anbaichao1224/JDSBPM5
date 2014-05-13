package com.kzxd.changepssword.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOFactory;

import com.kzxd.changepssword.dao.ChangePsswordDao;
import com.kzxd.changepssword.entity.Changepssword;
import com.kzxd.changepssword.entity.personaccount;
import com.kzxd.tzgg.entity.notice;

public class ChangePsswordDaoImpl implements ChangePsswordDao {

	public List<personaccount> getPssword(String username, String oldpassword) {
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List< personaccount> person = new ArrayList<personaccount>();
		 personaccount mt=new personaccount();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, mt);
			//.setWhereClause("from RO_PERSONACCOUNT m where m.userid='"+username+"'and m.password='"+oldpassword+"' ");
			mt.setUserid(username);
			mt.setPassword(oldpassword);
			person = factory.find();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		
	finally{	
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		
			
		}
	}
	  return  person;
	}

	
	
	public void updataPssword(String uuid ,String newpssword,personaccount mt) {
		
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		try {
			DAOFactory factory = null;
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, mt);
			mt.setUuid(uuid);
		    mt.setPassword(newpssword);
			mt.update();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		finally{
			try {
			conn.close();  
		} catch (SQLException e) {
			e.printStackTrace();
		
	         }
	           }
	    }


	
	
	
	
	
	
	
	
	
	
	
	
	
     public List<personaccount> findbyid(String username,String personid,String oldpassword) {
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<personaccount> no = new ArrayList<personaccount>();
		personaccount nt = new personaccount();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, nt);
			//nt.setWhereClause("uuid in (select uuid from RO_PERSONACCOUNT m where m.personid='"+personid+"') and m.userid = '"+username+"' ");
			nt.setConnection(conn);
			nt.setUserid(username);
			nt.setPersonid(personid);
			nt.setPassword(oldpassword);
			no = factory.find();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  return no;
	}
     
     
      public void update(String uuid,String newpssword,personaccount nt) {
	
	   DBBeanBase dbbase;  
	   Connection conn;
	   dbbase = new DBBeanBase("bpm", true);
	  conn = null;
	  DAOFactory factory = null;
	 // personaccount mt = new personaccount();
	   try {
		  conn = dbbase.getConn();
		   factory = new DAOFactory(conn, nt);
			nt.setConnection(conn);
		   nt.setPassword(newpssword);
		       nt.update();
		      conn.commit();
		
	       } catch (Exception e) {
		  e.printStackTrace();
	      }
	       finally{  
	    	   
	    	   try {
		  conn.close();
	   } catch (SQLException e) {
		  e.printStackTrace();
	  } 
	}
}

      
      


		
	}
		
		
		
		



