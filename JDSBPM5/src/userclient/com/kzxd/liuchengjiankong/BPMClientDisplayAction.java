package com.kzxd.liuchengjiankong;

import java.util.ArrayList;
import java.util.List;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.RouteDef;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.database.DbRouteDef;
import net.itjds.bpm.engine.database.right.DbActivityDefRight;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.userclient.common.BPMActionBase;

import com.opensymphony.xwork2.Action;




/**
 * <p>
 * Title: BPM工作流管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 *
 * </p>
 */
public class BPMClientDisplayAction extends BPMActionBase {
	public BPMClientDisplayAction()  {
		super();
	}
	
	/**
	 * 根据定义判断是否需要出现选人窗口
	 * @param toActivityDefId
	 * @return
	 * @throws BPMException
	 */
	public boolean isNoNeedSelect(String toActivityDefId) throws BPMException{
		String performType=(String) this.getClient().getActivityDefRightAttribute(toActivityDefId, OARightConstants.ACTIVITYDEF_RIGHT_ATT_PERFORMTYPE, null);
		if (performType.equals(OARightConstants.PERFORMTYPE_NEEDNOTSELECT)||performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)){
			return true	;
		}	
		
	return false;
    }
	

	/**
	 * 根据定义判断如果下下一活动中代办人中有当前办理人则直接打开进行办理
	 * @param toActivityDefId
	 * @return
	 * @throws BPMException
	 * @throws PersonNotFoundException
	 */
	public boolean isContinuous(String toActivityDefId) throws BPMException, PersonNotFoundException{
		List<Person> personList=(List<Person>) this.getClient().getActivityDefRightAttribute(toActivityDefId, DbActivityDefRight.ATT_PERFORMERSELECTEDID, null);
		 Person person=OrgManagerFactory.getOrgManager().getPersonByID(this.getClient().getConnectInfo().getUserID());
		 if (personList.contains(person)){
			 return true;
		 }
		 return false;
	}
	
	
	public String execute() throws Exception {
	  
	  
	   if (this.getActivityInst().isCanSignReceive()){
		   this.getClient().signReceive(this.getActivityInst().getActivityInstId(), null);	
	   }	  
	   
		int nResult = bpmUserClientUtil.performDisplay(this.getActivityInst().getActivityInstId());	
		if (nResult == -1) {
			return Action.ERROR;
		}
		if (nResult == -2) {
			return Action.INPUT;
		}	
		
		
		
		
		return Action.SUCCESS;
	}
	
	private List<DbRouteDef> routeDefList;

	public List<DbRouteDef> getRouteDefList() {
		return routeDefList;
	}

	public void setRouteDefList(List<DbRouteDef> routeDefList) {
		this.routeDefList = routeDefList;
	}
	
	
}