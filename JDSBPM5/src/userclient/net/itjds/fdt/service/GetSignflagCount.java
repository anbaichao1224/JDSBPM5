package net.itjds.fdt.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletResponse;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.database.DBBeanBase;
import net.itjds.userclient.common.BPMUserClientUtil;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;


@EsbBeanAnnotation(id="GetSignflagCount",
		name="获取id",
		expressionArr="GetSignflagCount()",
		desc="获取id",
		dataType="action")
public class GetSignflagCount {
	private  String  activiyInstid;

	public GetSignflagCount() {

		 activiyInstid=	(String) ActionContext.getContext().getValueStack().findValue("$currActivityInst.activityInstId");

		
	}

	
	 public boolean getFindsl(){
//		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
//        WorkflowClientService client = bpmUserClientUtil.getClient();
//        String processinstid = null;
//		try {
//		     processinstid = client.getActivityInst(activiyInstid).getProcessInstId();
//		    
//		} catch (BPMException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	    String ywuuid=null;
	    ywuuid = this.finddeptid1();
		if(ywuuid==null){
		    ywuuid =this.swfinddeptid1();
		}
	    DBBeanBase dbbase = new DBBeanBase("bpm", true);
	    Connection conn = dbbase.getConn();
	    String sqlStr ="select count(d.uuid) as unreceivecount from HQ_DANWEI d  where d.huiqian_uuid='"+ywuuid+"'  and (d.sign_flag=0 or d.sign_flag=4 or d.sign_flag=1)";


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
		//会签在办未办树变灰根据传过来的实例id得uuid
		public String finddeptid1(){
		
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
		  String sqlStr ="select a.uuid from HQ_YEWU a where a.PROCCESSINST_UUID='"+processinstid+"'";
		   
		 Statement st = null;
		 ResultSet rs = null; 
	   
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
		
		//收文会签在办未办树变灰根据传过来的实例id得uuid
		public String swfinddeptid1(){
		
			BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
	        WorkflowClientService client = bpmUserClientUtil.getClient();
	        String processinstid = null;
			try {
			     processinstid = client.getActivityInst(activiyInstid).getProcessInstId();
			} catch (BPMException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		  String ywuuidjbl = null;
		  DBBeanBase dbbase = new DBBeanBase("bpm", true);
		  Connection conn = dbbase.getConn();
		  String sqlStr ="select a.uuid from HQ_SWYEWU a where a.PROCCESSINST_UUID='"+processinstid+"'";
		   
		 Statement st = null;
		 ResultSet rs = null; 
	   
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sqlStr);
			
			while(rs != null && rs.next()){
				ywuuidjbl = rs.getString("uuid");
				
			}
			
			conn.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeLJ(conn, st, rs, dbbase);
		}	
		
		return ywuuidjbl;
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
