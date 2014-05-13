package com.kzxd.bpm.document;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;

import com.opensymphony.xwork2.Action;



public class Document implements Action{
	
	private String uuid;
	private String diva;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getDiva() {
		return diva;
	}

	public void setDiva(String diva) {
		this.diva = diva;
	}

	public String getType(String processinstid){
		List<DocumentDao> inceptlist = new ArrayList<DocumentDao>();
		String type = "";
		DBBeanBase dbbase;
		Connection conn=null;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		DocumentDao docdao = new DocumentDao();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, docdao);
			docdao.setWhereClause("processinstid='"+processinstid+"'");
            inceptlist = (List<DocumentDao>)factory.find();
            if(inceptlist!=null){
				type=inceptlist.get(0).getFiletype();
			}
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
		
		return type;
	}
	
	public List<DocumentDao> findByActInstId(String aInstId){
		List<DocumentDao> doclist = new ArrayList<DocumentDao>();
		DBBeanBase dbbase;
		Connection conn=null;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		DocumentDao docdao = new DocumentDao();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, docdao);
			docdao.setWhereClause("activityinstid='"+aInstId+"'");
			doclist = (List<DocumentDao>)factory.find();
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
		
		return doclist;
	}
	
	public void del(){
		
		DBBeanBase dbbase = null;
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;		
		
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			dbbase = new DBBeanBase("bpm", true);
			conn = dbbase.getConn();
			String sql = "delete from BPM_DOCUMENT where uuid='"+uuid+"'";
			pst = conn.prepareStatement(sql);
			rs = pst.executeUpdate();
			conn.commit();
			
			String json="{result:'É¾³ý³É¹¦',diva:'" + diva +"'}";
			out.println(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
