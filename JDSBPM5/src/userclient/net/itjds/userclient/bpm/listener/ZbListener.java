package net.itjds.userclient.bpm.listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.data.DataMap;
import net.itjds.bpm.data.mapdao.MapDAODataMap;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.DataEngine;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.common.org.base.Person;

public class ZbListener implements ActivityListener{

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

	public void activityRouted(ActivityEvent event) throws BPMException {
		
		String activityInstId = event.getActivityInsts()[0].getActivityInstId();
		List<Person> performers=(List<Person>)event.getClientService().getActivityInstRightAttribute(activityInstId,OARightConstants.ACTIVITYINST_RIGHT_ATT_PERFORMER,null);
		for(int i=0; i<event.getActivityInsts().length; i++){
			System.out.println("zb activityInstId="+event.getActivityInsts()[i].getActivityInstId());
		}
		Person person = performers.get(0);
		String orgName = person.getOrgList().get(0).getName();
		System.out.println("orgName="+orgName);

		Map map = new HashMap();		
		map.put(orgName, orgName);
		
		String str = "";
		
		ActionContext context=ActionContext.getContext();
		Object result=context.getValueStack().findValue("$Fdtnmswbl.fdtOaNmswblDAO.ffzh");
		String ffzh=(String) result;
		Iterator it = map.entrySet().iterator(); 
		while (it.hasNext()) { 
			Map.Entry entry = (Map.Entry) it.next(); 
			String value = (String) entry.getValue();
			if(ffzh==null || ffzh.equals("")){
				str+=value;
				str+=",";
			}else{
				str = ffzh + "," + value + ",";
			}
						
		} 
		System.out.println("×Üstr="+str);
		context.getValueStack().setValue("$Fdtnmswbl.fdtOaNmswblDAO.ffzh", str.substring(0,str.length()-1));
		
		MapDAODataMap dataMap = (MapDAODataMap) event.getClientService().getMapDAODataEngine().getActivityInstMapDAOMap(activityInstId, person.getID());
		
		dataMap.put("$Fdtnmswbl.fdtOaNmswblDAO.ffzh", str.substring(0,str.length()-1));

		event.getClientService().getMapDAODataEngine().updateActivityInstMapDAO(activityInstId, (DataMap)dataMap, activityInstId);
	}

	public void activityRouting(ActivityEvent event) throws BPMException {

	}

	public void activitySplited(ActivityEvent event) throws BPMException {

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
