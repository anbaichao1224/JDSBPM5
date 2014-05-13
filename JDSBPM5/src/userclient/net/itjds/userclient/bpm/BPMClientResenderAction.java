package net.itjds.userclient.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.RouteDef;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.ReturnType;
import net.itjds.common.org.base.Person;
import net.itjds.service.bpm.GetRouteToBean;
import net.itjds.service.bpm.RouteToBean;
import net.itjds.userclient.common.BPMUserClientUtil;

/**
 * @author olivery	BPMClientResenderAction.java 
 * @since Jul 23, 2009
 */
public class BPMClientResenderAction  {

	private String processInstId;
	private String activityDefId;
	private String activityInstId;
	private ActivityInst activityInst;
	private String performPersonIds;
	
	//zq add
	private String routeId;
	
	
	
	public String getPerformPersonIds() {
		return performPersonIds;
	}

	public void setPerformPersonIds(String performPersonIds) {
		this.performPersonIds = performPersonIds;
	}

	public ActivityInst getActivityInst() {
		return activityInst;
	}

	public void setActivityInst(ActivityInst activityInst) {
		this.activityInst = activityInst;
	}

	public String getActivityInstId() {
		return activityInstId;
	}

	public void setActivityInstId(String activityInstId) {
		this.activityInstId = activityInstId;
	}

	public String execute() throws Exception {
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
	
		/*RouteDef rdef = bpmUserClientUtil.getClient().getRouteDef(routeId);
		String[] adefid = {rdef.getToActivityDefId()};
		//bpmUserClientUtil.getClient().routeTo(activityInstId, adefid, arg2)
		List performPersonIdList = new ArrayList();
		String[] pid = performPersonIds.split(",");
		for(int i=0;i<pid.length;i++){
			performPersonIdList.add(pid[i]);
		}
		Map[] contextMapList = new Map[1];
		Map contextMap=new HashMap();
		contextMap.put(OARightConstants.CTX_PERFORMERS, performPersonIdList);
		contextMapList[0] = contextMap;
		ReturnType type = bpmUserClientUtil.getClient().routeTo(activityInstId, adefid, contextMapList);
		if (!type.isSucess()){
			return Action.ERROR;
		}*/
		this.activityInst = bpmUserClientUtil.getClient().getActivityInst(this.activityInstId);
		if (activityInst.isCanSignReceive()){
			bpmUserClientUtil.getClient().signReceive(activityInstId, null);	
		}
		ActivityInst activityInst1 = (ActivityInst)bpmUserClientUtil.getClient().newActivityInstByActivityDefId(this.activityInst.getProcessInstId(),this.activityInst.getActivityDefId(),null);
		this.activityInstId = activityInst1.getActivityInstId();
		this.activityInst = activityInst1;
		 ServletActionContext
		.getRequest().setAttribute("activityInstId", activityInstId);
		
//		 bpmUserClientUtil.getClient().updateActivityInstMapDAO(activityInstId, 
//					activityInst.getProcessInstId(), 
//					activityInst.getAllMapDAO(bpmUserClientUtil.getClient().getConnectInfo().getUserID()));
		 
		 	  
		
		 
		 
		return "success";
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getActivityDefId() {
		return activityDefId;
	}

	public void setActivityDefId(String activityDefId) {
		this.activityDefId = activityDefId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}
	

}

