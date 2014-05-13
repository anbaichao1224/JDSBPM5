package net.itjds.fdt.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.common.BPMUserClientUtil;
import com.opensymphony.xwork2.ActionContext;


@EsbBeanAnnotation(id="GetBhflagCount",
		name="获取id",
		expressionArr="GetBhflagCount()",
		desc="获取id",
		dataType="action")
public class GetBhflagCount {
	private  String  activiyInstid;

	public GetBhflagCount() {

		 activiyInstid=	(String) ActionContext.getContext().getValueStack().findValue("$currActivityInst.activityInstId");

		
	}

	
	 public boolean getFindsl(){
	    String ywuuid=null;
	    ywuuid=finddeptid1();
	    if(ywuuid==null){
	    	ywuuid=finddeptid2();
	    }
	    DBBeanBase dbbase = new DBBeanBase("bpm", true);
	    Connection conn = dbbase.getConn();
	    String sqlStr ="select count(d.uuid) as unreceivecount from HQ_DANWEI d where  d.huiqian_uuid='"+ywuuid+"'  and d.bhflag=1";


	    Statement st = null;
	    ResultSet rs = null; 
	    boolean result=false;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sqlStr);
			while(rs != null && rs.next()){
				Integer ws = rs.getInt("unreceivecount");
			 if(ws == 0){
				 result = true;
			 }
				 
			 }
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally{
		this.closeLJ(conn, st, rs, dbbase);
	}	
	
	
	return result;
}
		private String finddeptid1() {
			BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
	        WorkflowClientService client = bpmUserClientUtil.getClient();
	        String processinstid = null;
			try {
			     processinstid = client.getActivityInst(activiyInstid).getProcessInstId();
			    
			} catch (BPMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String ywuuid = null;
		    
			DBBeanBase dbbase = new DBBeanBase("bpm", true);
			Connection conn = dbbase.getConn();
			String sqlStr = "select t.uuid from hq_yewu t where t.processinstid='"+processinstid+"'";
			  
			Statement st = null;
			ResultSet rs = null; 
			DAOFactory factory = null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sqlStr);
				
				while(rs != null && rs.next()){
					ywuuid = rs.getString("uuid");
				}
				conn.commit();
							
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				this.closeLJ(conn, st, rs, dbbase);
			}	
			
			return ywuuid;
		}
		private String finddeptid2() {
			BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
	        WorkflowClientService client = bpmUserClientUtil.getClient();
	        String processinstid = null;
			try {
			     processinstid = client.getActivityInst(activiyInstid).getProcessInstId();
			    
			} catch (BPMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String ywuuid = null;
		    
			DBBeanBase dbbase = new DBBeanBase("bpm", true);
			Connection conn = dbbase.getConn();
			String sqlStr = "select t.uuid from hq_jbl t where t.processinstid='"+processinstid+"'";
			  
			Statement st = null;
			ResultSet rs = null; 
			DAOFactory factory = null;
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sqlStr);
				
				while(rs != null && rs.next()){
					ywuuid = rs.getString("uuid");
				}
				conn.commit();
							
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				this.closeLJ(conn, st, rs, dbbase);
			}	
			
			return ywuuid;
		}
	//关闭连接
	public void closeLJ(Connection conn, Statement st, ResultSet rs, DBBeanBase dbbase ){
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
		dbbase.close();
	}


	public String getActiviyInstid() {
		return activiyInstid;
	}


	public void setActiviyInstid(String activiyInstid) {
		this.activiyInstid = activiyInstid;
	}


	

}
