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


@EsbBeanAnnotation(id="GetSignflagCountHg",
		name="获取id",
		expressionArr="GetSignflagCountHg()",
		desc="获取id",
		dataType="action")
public class GetSignflagCountHg {
	private  String  activiyInstid;

	public GetSignflagCountHg() {

		 activiyInstid=	(String) ActionContext.getContext().getValueStack().findValue("$currActivityInst.activityInstId");

		
	}

	
	 public boolean getFindsHg(){
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
        WorkflowClientService client = bpmUserClientUtil.getClient();
        String processinstid = null;
		try {
		     processinstid = client.getActivityInst(activiyInstid).getProcessInstId();
		    
		} catch (BPMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    DBBeanBase dbbase = new DBBeanBase("bpm", true);
	    Connection conn = dbbase.getConn();
	    String sqlStr ="select * from fdt_oa_gwg t where t.processinst_id='"+processinstid+"'";


	    Statement st = null;
	    ResultSet rs = null; 
	    boolean result=false;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sqlStr);
			while(rs != null && rs.next()){
				String  hgr = rs.getString("hgr");
				String  hgdw = rs.getString("hgdw");
			 if(hgr == null &&hgr.equals("")||hgdw ==null&&hgdw.equals("")){
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
