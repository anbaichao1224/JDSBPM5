package com.kzxd.xiugaimima.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.usm.util.CacheUtil;

import org.apache.struts2.ServletActionContext;
import org.appfuse.webapp.action.BaseAction;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opensymphony.xwork2.ActionContext;


@SuppressWarnings("serial")
public class ChangePass extends BaseAction{
	
	private String newPassword;
	private String oldPassword;
	private String uuid,userId,personId;

	
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	//获得登录人员的相关信息
	public void getUser(){
		
		personId = (String) ServletActionContext.getRequest().getSession().getAttribute("personId");
		
		StringBuffer sql = new 	StringBuffer("");
		sql.append("select t.uuid,t.userId, t.password from ro_personaccount t where t.personId = '");
		sql.append(personId);
		sql.append("'");
		DBBeanBase dbbase = new DBBeanBase("org"); 
	    Connection conn = dbbase.getConn();
	    Statement stmt = null;
	    ResultSet rs = null;
	   	try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql.toString());
			if(rs.next()){
				uuid = rs.getString("UUID");
				userId = rs.getString("USERID");
			}
	   	}catch(SQLException e){
	    	e.printStackTrace();
	  	}finally{
	  		try{
	  			rs.close();
	  		}catch(Exception e){
	  			e.printStackTrace();
	  		}
	  		
	  		try{
	  			stmt.close();
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
	}
	
	//修改密码
	public String passwordModify(){
		HttpServletResponse response=ServletActionContext.getResponse();

		Session session = null;
		//获得登录人员的相关信息
		this.getUser();
		StringBuffer sql=new StringBuffer("");
		sql.append("select password  from ro_personaccount pa where pa.personid='");
		sql.append(personId);
		sql.append("' and pa.uuid ='");
		sql.append(uuid);
		sql.append("'");
		CacheUtil.clearCache(personId);
		CacheUtil.clearCache(userId);
		int flag=0;
		ResultSet rs=null;
		DBBeanBase dbbase = new DBBeanBase("org"); 
	    Connection conn = dbbase.getConn();
	    Statement stmt = null;
	   	try {
			stmt=conn.createStatement();
			rs=stmt.executeQuery(sql.toString());
			String pwd=null;
			if(rs.next()){
				 pwd=rs.getString("password");
				 }
			if(pwd.equals(oldPassword)){
				
				this.password();
			}else{
				try {
					ServletActionContext.getResponse().getWriter().print("{failure:true}");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	   	}catch(SQLException e){
	    	e.printStackTrace();
	  	}finally{
	  		try{
	  			stmt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			try{
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			try{
				dbbase.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
	 	}
/*
	   	DbPerson p = (DbPerson)ActionContext.getContext().getValueStack().findValue("$currPerson"); //加入 修改密码后可即时登录
	    p.setPassword(newPassword);
	    
	    try {
			OrgManagerFactory.getOrgManager("").getCacheManager().reloadAll();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().getWriter().print("{success:true}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		return null;
	}
	public String password(){
		//获得登录人员的相关信息
		this.getUser();
		StringBuffer sql=new StringBuffer("");
		sql.append("update ro_personaccount pa set pa.password='");
		sql.append(newPassword);
		sql.append("' where pa.personid='");
		sql.append(personId);
		sql.append("' and pa.uuid ='");
		sql.append(uuid);
		sql.append("'");
		CacheUtil.clearCache(personId);
		CacheUtil.clearCache(userId);
		int flag=0;
		DBBeanBase dbbase = new DBBeanBase("org"); 
	    Connection conn = dbbase.getConn();
	    Statement stmt = null;
	   	try {
			stmt=conn.createStatement();
			flag=stmt.executeUpdate(sql.toString());
			if(flag==0){
				conn.rollback();
			}
	   	}catch(SQLException e){
	    	e.printStackTrace();
	  	}finally{
	  		try{
	  			stmt.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			try{
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			try{
				dbbase.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
	 	}

	   	DbPerson p = (DbPerson)ActionContext.getContext().getValueStack().findValue("$currPerson"); //加入 修改密码后可即时登录
	    p.setPassword(newPassword);
	    
	    try {
			OrgManagerFactory.getOrgManager("").getCacheManager().reloadAll();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			ServletActionContext.getResponse().getWriter().print("{success:true}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


}
