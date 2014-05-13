package net.itjds.userclient.bpm.listener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.engine.BPMException;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;

public class SwdjListener implements ActivityListener{

	public void activityActived(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityActiving(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityCompleted(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityCompleting(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityDisplay(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityFormSaveed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityFormSaveing(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityInited(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityJoined(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityJoining(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityOutFlowReturned(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityOutFlowReturning(ActivityEvent arg0)
			throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityOutFlowed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityOutFlowing(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityResumed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityResuming(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * 点击启动流程，修改本人的数据列表  标识Flag 变成 1
	 * @return
	 */
	public void updateJblFlag1(String processinstid){
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		String sqlStr = "update FDT_OA_JBL t set t.flag='1' where t.processinstid='"+processinstid+"'";
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.createStatement();
			st.executeUpdate(sqlStr);
			
			conn.commit();
						
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			this.closeLJ(conn, st, rs, dbbase);
		}	
	}
	
	
	/**
	 * 点击启动流程，修改本人的数据列表  标识Flag 变成 2  使其不可以被取回
	 * @returnnet.kzxd.dj.action.JblAction.updateFlagTo0
	 */
	public void updateDjFlagTo2(String processinstid){
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		String sqlStr = "update FDT_OA_DJ t set t.flag='2' where t.processinstid='"+processinstid+"'";
		  
		Statement st = null;
		ResultSet rs = null; 
		DAOFactory factory = null;
		try {
			st = conn.createStatement();
			st.executeUpdate(sqlStr);
			    
			conn.commit();
						
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally{
			this.closeLJ(conn, st, rs, dbbase);
		}	
	}
	
	//根据流程实例id查找对应的交办理的uuid
	private String findJblUuidProcessinstId(String processinstid) {
		String tid = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		String sqlStr = "select t.uuid from FDT_OA_JBL t where t.processinstid='"+processinstid+"'";
		  
		Statement st = null;
		ResultSet rs = null; 
		DAOFactory factory = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sqlStr);
			
			while(rs != null && rs.next()){
				tid = rs.getString("uuid");
			}
			conn.commit();
						
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.closeLJ(conn, st, rs, dbbase);
		}	
		
		return tid;
	}

	//删除对应的交办理的提醒表里面的数据
	public void delTiXing(String tid) {
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		String sqlStr = "delete from KZXD_TIXING t where t.delid = '"+tid+"'";
		  
		Statement st = null;
		ResultSet rs = null; 
		DAOFactory factory = null;
		try {
			st = conn.createStatement();
			st.executeUpdate(sqlStr);
			conn.commit();
						
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally{
			this.closeLJ(conn, st, rs, dbbase);
		}	
	}
	
	/**
	 * 点击启动流程，修改本人的数据列表  标识Flag 变成 2  使其不可以被取回
	 * @returnnet.kzxd.dj.action.JblAction.updateFlagTo0
	 */
	public void updaDjFlagTo2ByJbluuid(String tid){
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		String sqlStr = "update FDT_OA_DJ t set t.flag='2' where t.jbluuid='"+tid+"'";
		  
		Statement st = null;
		ResultSet rs = null; 
		DAOFactory factory = null;
		try {
			st = conn.createStatement();
			st.executeUpdate(sqlStr);
			    
			conn.commit();
						
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally{
			this.closeLJ(conn, st, rs, dbbase);
		}	
	}

	public void activityRouted(ActivityEvent event) throws BPMException {
		
		String processinstid=event.getActivityInsts()[0].getProcessInstId();
		
		//根据流程实例id 查找对应的交办理的uuid
		String tid = this.findJblUuidProcessinstId(processinstid);
		if(tid != null){

			//交办理的标识改成1  表示已经跳转到流程  不能再跳转
			this.updateJblFlag1(processinstid);
			
			this.updaDjFlagTo2ByJbluuid(tid);

			//删除对应的交办理的提醒表里面的数据
			this.delTiXing(tid);
		}else{
			//收文登记的标识改成2   表示已经跳转到流程  不能再跳转
			this.updateDjFlagTo2(processinstid);

		}
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
	
	public void activityRouting(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activitySplited(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activitySpliting(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activitySuspended(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activitySuspending(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityTakebacked(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityTakebacking(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

}
