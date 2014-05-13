/**
 * $RCSfile: BPMClientHisDisplayAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/06/09 14:41:59 $
 *
 * Copyright (C) 2003 itjds, Inc. All rights reserved.
 *
 * This software is the proprietary information of itjds, Inc.
 * Use is subject to license terms.
 */
package net.itjds.userclient.bpm;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.tools.ant.util.DateUtils;

import net.itjds.bpm.client.ActivityDef;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.database.right.DbActivityDefRight;
import net.itjds.bpm.engine.inter.EIActivityInstHistory;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.userclient.common.UserClientException;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;




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
 * Company: www.justdos.net
 * </p>
 * 
 * @author wenzhangli
 * @version 2.0
 */
public class BPMClientHisDisplayAction  {
	private ActivityDef activityDef;
	private String activityInstHistoryId;
	private String activityInstId;
	private BPMUserClientUtil bpmUserClientUtil;
	private ActivityInstHistory activityInstHistory;

	public ActivityDef getActivityDef() throws BPMException, UserClientException {
		  this.init();	
		return activityDef;
	}

	public void setActivityDef(ActivityDef activityDef) {
		this.activityDef = activityDef;
	}

	public BPMClientHisDisplayAction()  {
		this.bpmUserClientUtil = new BPMUserClientUtil();
	}
	
	public String execute() throws Exception {
	    this.init();	
		
		return Action.SUCCESS;
	}
	
	public boolean isCanResend() throws BPMException{
		WorkflowClientService client = this.bpmUserClientUtil.getClient();
		
		boolean retValue = false;
		String hisId = this.getSelfHistoryId();
		ActivityInstHistory his = client.getActivityInstHistory(hisId);
		List<ActivityInst> insts = client.getActivityInstListByOutActvityInstHistory(hisId, null);
		if(insts==null || insts.size()==0)
			return false;
		for(int i=0; i<insts.size(); i++){
			ActivityInst inst = insts.get(i);
			if(inst.getDealMethod() != null && "SPLITED".equals(inst.getDealMethod())){
			continue;
			}
			if(("YES".equals(inst.getActivityDef().getRightAttribute("CANRESEND"))) 
					&& ("MULTIPLE".equals(inst.getActivityDef().getRightAttribute("PERFORMTYPE")))){
				retValue  = true;
				break;
			}
		}
		return retValue;
	}
	
	
	public boolean isCanTakeBack() throws BPMException{
		WorkflowClientService client = this.bpmUserClientUtil.getClient();
		
		boolean retValue = false;
		String hisId = this.getSelfHistoryId();
		ActivityInstHistory his = client.getActivityInstHistory(hisId);
		List<ActivityInst> insts = client.getActivityInstListByOutActvityInstHistory(hisId, null);
		if(insts==null || insts.size()==0)
			return false;
		for(int i=0; i<insts.size(); i++){
			ActivityInst inst = insts.get(i);
			if(inst.getDealMethod() != null && "SPLITED".equals(inst.getDealMethod())){
				continue;
			}
			if("SINGLE".equals(inst.getActivityDef().getRightAttribute("PERFORMTYPE"))){
				return false;
			}
			try {
				if(!inst.isCanTakeBack()){
					continue;
				}
			} catch (BPMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
			
			retValue = true;
		}
		return retValue;
	}
	
	
	private String getSelfHistoryId(){
		ActivityInstHistory retHis = null;
		//return this.getActivityInstHistory().getActivityHistoryId();
		BPMUserClientUtil bpmUserClientUtil =  new BPMUserClientUtil();
		WorkflowClientService client =  bpmUserClientUtil.getClient();
		
		try {
			
			ActivityInstHistory activityInstHistory = client.getActivityInstHistory(activityInstHistoryId);
			retHis = client.getActivityInstHistory(activityInstHistoryId);;
		
		
			List historyList = client.getLastActivityInstHistoryListByActvityInst(activityInstHistory.getActivityInst().getActivityInstId(), null);
			//List historyList = client.getActivityInstHistoryListByProcessInst(activityInstHistory.getProcessInstId(), null);
			for(int i=0; i<historyList.size(); i++){
				ActivityInstHistory his = (ActivityInstHistory) historyList.get(i);
				if(his.getDealMethod()!=null && "SPLITED".equals(his.getDealMethod())){
					List performers =  (List) client.getActivityInstHistoryRightAttribute(his.getActivityHistoryId(),  OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER, null);
					if (performers.size()>0){
						Person p = (Person) performers.get(0);
					// 	System.out.println("historyId=" + his.getActivityHistoryId() + " and performer=" + p.getName());
						Person currentPerson = (Person) ActionContext.getContext().getValueStack().findValue("$currPerson");
						if(currentPerson.getID().equals(p.getID())){
							retHis = his;
						}
					}
				}
				
			}
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return retHis.getActivityHistoryId();
	}
	

	/**
	 * 根据定义判断是否需要出现选人窗口
	 * @param toActivityDefId
	 * @return
	 * @throws BPMException
	 */
	public boolean isNoNeedSelect(String toActivityDefId) throws BPMException{
		
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
	
		 return false;
	}
	
	
	/**
	 * 
	 * @throws OAException
	 * @throws BPMException
	 * @throws UserClientException 
	 */
	public void init() throws  BPMException, UserClientException  {
		
		this.activityInstHistory=bpmUserClientUtil.getClient().getActivityInstHistory(activityInstHistoryId);
		this.activityInstId=activityInstHistory.getActivityInstId();
		ActivityInst activityInst = bpmUserClientUtil.getClient().getActivityInst(activityInstId);
		if(activityInst != null)
		{
			this.activityDef = activityInst.getActivityDef();
		}else{
			this.activityDef=activityInstHistory.getActivityDef();
		}
		
		if (this.activityInstHistoryId == null) {
			this.activityInstHistoryId = (String) ActionContext.getContext()
					.getValueStack().findValue("activityInstId");
			if (this.activityInstHistoryId == null || activityInstHistoryId.equals("")) {
				// 如果是第二次发送时数据可能在Parameters()中
				this.activityInstHistoryId = (String) ServletActionContext
						.getRequest().getParameter("activityInstId");
			}			
		}	
		
	
	}
	
	
	

	public ActivityInstHistory getActivityInstHistory() throws BPMException {
		if(this.activityInstHistory == null)
			return null;
			ActivityInstHistory retHis = activityInstHistory;
		BPMUserClientUtil bpmUserClientUtil =  new BPMUserClientUtil();
		WorkflowClientService client =  bpmUserClientUtil.getClient();
		
		if("completed".equals(retHis.getProcessInst().getState())){
			return retHis;
		}
		List<ActivityInstHistory> historyList=null;
		try {
			historyList = client.getLastActivityInstHistoryListByActvityInst(activityInstHistory.getActivityInst().getActivityInstId(), null);
		
		for(int i=0;i<historyList.size();i++){
			ActivityInstHistory his=historyList.get(i);
			if (his.getDealMethod().equals("SPLITED")){
				this.activityInstId=his.getActivityInstId();
				return his;
			}
		}
		this.activityInstId=retHis.getActivityInstId();
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return activityInstHistory;

		
//		try {
//			List ps = (List) client.getActivityInstHistoryRightAttribute(activityInstHistory.getActivityHistoryId(),  OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER, null);
//			if (ps.size()==0){
//				return retHis;
//			}
//			Person p1 = (Person) ps.get(0);
//			
//			System.out.println("historyId=" + activityInstHistory.getActivityHistoryId() + " and performer=" + p1.getName());
//			System.out.println("--------------------------------------------------------------------------------");
//			List historyList = client.getLastActivityInstHistoryListByActvityInst(activityInstHistory.getActivityInst().getActivityInstId(), null);
//			for(int i=0; i<historyList.size(); i++){
//				ActivityInstHistory his = (ActivityInstHistory) historyList.get(i);
//				List performers =  (List) client.getActivityInstHistoryRightAttribute(his.getActivityHistoryId(),  OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER, null);
//				if (performers.size()>0){
//					Person p = (Person) performers.get(0);
//					System.out.println("historyId=" + his.getActivityHistoryId() + " and performer=" + p.getName());
//					Person currentPerson = (Person) ActionContext.getContext().getValueStack().findValue("$currPerson");
//					if(currentPerson.getID().equals(p.getID())){
//						retHis = his;
//					}
//				}
//				
//			}
//		} catch (BPMException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return retHis;
//		return activityInstHistory;
	}

	public void setActivityInstHistory(ActivityInstHistory activityInstHistory) {
		this.activityInstHistory = activityInstHistory;
	}

	public String getActivityInstHistoryId() throws BPMException {
		//return activityInstHistoryId;
		return this.getActivityInstHistory().getActivityHistoryId();
	}

	public void setActivityInstHistoryId(String activityInstHistoryId) {
		this.activityInstHistoryId = activityInstHistoryId;
	}

	public String getActivityInstId() throws BPMException {
		return this.getActivityInstHistory().getActivityInst().getActivityInstId();
//		return activityInstId;
	}

	public void setActivityInstId(String activityInstId) {
		
		this.activityInstId = activityInstId;
	}

	
	
}
