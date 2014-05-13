package net.itjds.userclient.bpm.listener;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.kzxd.bpm.document.BPMACTIVITYINSTANCEBEAN;
import com.kzxd.bpm.document.BPMACTIVITYINSTANCEDAO;


import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.inter.EIActivityInst;
import net.itjds.bpm.engine.inter.EIActivityInstManager;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;


public class HbListener implements ActivityListener{
	public void activityActived(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityActiving(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityCompleted(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityCompleting(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityDisplay(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityFormSaveed(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityFormSaveing(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityInited(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityJoined(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityJoining(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityOutFlowReturned(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityOutFlowReturning(ActivityEvent event)
			throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityOutFlowed(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityOutFlowing(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityResumed(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityResuming(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityRouted(ActivityEvent event) throws BPMException {
		String ProcessinstId = event.getActivityInsts()[0].getProcessInstId();
		List<BPMACTIVITYINSTANCEBEAN> bean =  this.find(ProcessinstId); 
		
	}

	public void activityRouting(ActivityEvent event) throws BPMException {
	}

	public void activitySplited(ActivityEvent event) throws BPMException {
	}

	public void activitySpliting(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activitySuspended(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activitySuspending(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityTakebacked(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityTakebacking(ActivityEvent event) throws BPMException {
		// TODO Auto-generated method stub
	}
	public List<BPMACTIVITYINSTANCEBEAN> find(String ProcessinstId){
		List<BPMACTIVITYINSTANCEBEAN> beanlist = new ArrayList<BPMACTIVITYINSTANCEBEAN>();
		DBBeanBase dbbase;
		Connection conn=null;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		BPMACTIVITYINSTANCEDAO bpmdao = new BPMACTIVITYINSTANCEDAO();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bpmdao);
			bpmdao.setProcessinstId(ProcessinstId);
			beanlist = (List<BPMACTIVITYINSTANCEBEAN>)factory.find();
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
		
		return beanlist;
		
	}

}
