/**
 * $RCSfile: RouteLogAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/06/09 14:41:58 $
 *
 * Copyright (C) 2003 itjds, Inc. All rights reserved.
 *
 * This software is the proprietary information of itjds, Inc.
 * Use is subject to license terms.
 */
package net.itjds.userclient.bpm;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import net.itjds.bpm.client.ActivityDef;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.ProcessDefVersion;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.client.RouteDef;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.inter.EIActivityDef;
import net.itjds.bpm.engine.inter.EIActivityDefManager;
import net.itjds.common.util.DateUtility;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.userclient.graph.Edge;
import net.itjds.userclient.graph.Graph;
import net.itjds.userclient.graph.Vertex;

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
public class RouteAction extends BPMActionBase {


	
	private BPMUserClientUtil bpmUserClientUtil;
	
	private List historyList;

	private WorkflowClientService client;
	private ArrayList webHistoryList;
	private ArrayList webCurrentWrpList;
	private String duorenbanli;
	private Integer start = 0, limit = 20;
	private List<ActivityDef> pdvList;
	private int totalCount;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	


	public RouteAction() {
		this.bpmUserClientUtil = new BPMUserClientUtil();
	}

	public String execute() throws Exception {


		this.client = bpmUserClientUtil.getClient();
	

			historyList = client.getActivityInstHistoryListByProcessInst(
					this.getProcessInst().getProcessInstId(), null);
		
		if (!this.getProcessInst().getState().equals(ProcessInst.STATE_COMPLETED)) {
			totalCount = historyList.size() + this.getCurrentWrpList().size();
		} else {
			totalCount = historyList.size();
		}

		return this.SUCCESS;
	}
	public String execute1(String activityInstId,List historyList) throws Exception {


		this.client = bpmUserClientUtil.getClient();
	

			historyList = client.getActivityInstHistoryListByProcessInst(
					this.getProcessInst().getProcessInstId(), null);
		
		if (!this.getProcessInst().getState().equals(ProcessInst.STATE_COMPLETED)) {
			totalCount = historyList.size() + this.getCurrentWrpList().size();
		} else {
			totalCount = historyList.size();
		}
		return null;

	}
	public String getProcessDiagram() throws Exception{
	
		this.client = bpmUserClientUtil.getClient();
		this.client = bpmUserClientUtil.getClient();

		
			historyList = client.getActivityInstHistoryListByProcessInst(
					this.getProcessInst().getProcessInstId(), null);


		if (!this.getProcessInst().getState().equals(ProcessInst.STATE_COMPLETED)) {
			totalCount = historyList.size() + this.getCurrentWrpList().size();
		} else {
			totalCount = historyList.size();
		}
		
		pdvList = this.getProcessInst().getProcessDefVersion().getAllActivityDefs();
		return this.SUCCESS;
	}

	private class HistoryWrp {
		private ActivityInstHistory activityInstHistory;
		private String activityHistoryId;
		private List performers;
		private ActivityDef activityDef;

		public HistoryWrp(ActivityInstHistory history,
				WorkflowClientService vlient) throws BPMException {
			this.activityInstHistory = history;
			this.activityHistoryId = history.getActivityHistoryId();
			this.performers = (List) client
					.getActivityInstHistoryRightAttribute(
							activityHistoryId,
							OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER,
							null);
			this.activityDef = client
					.getActivityDef(history.getActivityDefId());
		}

		public ActivityInstHistory getActivityInstHistory() {
			return activityInstHistory;
		}

		public String getActivityHistoryId() {
			return activityHistoryId;
		}

		public ActivityDef getActivityDef() {
			return activityDef;
		}

		public List getPerformers() {
			return this.performers;
		}

		public String getArrivedTime() {
			return DateUtility.formatDate(activityInstHistory.getArrivedTime(),
					"yyyy-MM-dd HH:mm:ss");
		}

		public String getEndTime() {
			return DateUtility.formatDate(activityInstHistory.getEndTime(),
					"yyyy-MM-dd HH:mm:ss");
		}

	}

	private class CurrentWrp {
		private ActivityInst activityInst;
		private String activityInstId;
		private List performers;
		private ActivityDef activityDef;
		private String activityDefName;
		public CurrentWrp(ActivityInst activityInst,
				WorkflowClientService vlient) throws BPMException {
			this.activityInst = activityInst;
			this.activityInstId = activityInst.getActivityInstId();
			this.performers = (List) client.getActivityInstRightAttribute(
					activityInstId,
					OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER,
					null);

			this.activityDef = client.getActivityDef(activityInst
					.getActivityDefId());
//			if(activityDef.getName()=="多人批办"){
//				activityDefName=activityDef.getName();
//			}
		}

		public ActivityInst getActivityInst() {
			return activityInst;
		}

		public String getActivityHistoryId() {
			return activityInstId;
		}

		public ActivityDef getActivityDef() {
			return activityDef;
		}

		public List getPerformers() {
			return this.performers;
		}

		public String getStatus()
		{
		String status="";
			if (activityInst.getState().equals(ActivityInst.STATUS_READ)){
				status="传阅";
			}else if(activityInst.getState().equals(ActivityInst.STATUS_ENDREAD)){
				status="阅毕";
			}else if(activityInst.getState().equals(ActivityInst.STATE_NOTSTARTED)){
				status="<span style='color:red'>未阅</span>";
			}else if(activityInst.getState().equals(ActivityInst.STATE_RUNNING)){
				status="正在办理";
			}
			return status;
		}
		
		public String getArrivedTime() {
			return DateUtility.formatDate(activityInst.getArrivedTime(),
					"yyyy-MM-dd HH:mm:ss");
		}

		public int getHistoryWrpSize() {

			return historyList.size();
		}

		public String getActivityDefName() {
			return activityDefName;
		}

		public void setActivityDefName(String activityDefName) {
			this.activityDefName = activityDefName;
		}
	}

	public List getCurrentWrpList() throws BPMException {
		if (webCurrentWrpList == null) {
			this.webCurrentWrpList = new ArrayList();
			List activityList = activityInst.getProcessInst()
					.getActivityInstList();
			for (int i = 0; activityList.size() > i; i++) {
				ActivityInst activityInst = (ActivityInst) activityList.get(i);
				

				CurrentWrp currentWrp = new CurrentWrp(activityInst,
						this.client);
				if(activityInst.getActivityDef().getName().equals("多人批办")){
					webCurrentWrpList.add(currentWrp);
				}
			
			}
		}
		return webCurrentWrpList;
	}

	public List getHistoryWrpList() throws BPMException {
		if (webHistoryList == null) {
			this.webHistoryList = new ArrayList();
			
			for (int i = 0; historyList.size() > i; i++) {
				ActivityInstHistory activityHistory = (ActivityInstHistory) historyList
						.get(i);
				HistoryWrp historyWrp = new HistoryWrp(activityHistory,
						this.client);
				if(activityHistory.getActivityDef().getName().equals("多人批办")){
					webHistoryList.add(historyWrp);
					}
					
				
			}
		}
		return webHistoryList;
	}


	
	public void seBPMUserClientUtil(BPMUserClientUtil bpmUserClientUtil) {
		this.bpmUserClientUtil = bpmUserClientUtil;
	}

	public BPMUserClientUtil getWebworkOAUtil() {
		return this.bpmUserClientUtil;
	}

	

	public List<ActivityDef> getPdvList() {
		return pdvList;
	}

	public void setPdvList(List<ActivityDef> pdvList) {
		this.pdvList = pdvList;
	}

	public String getDuorenbanli() {
		return duorenbanli;
	}

	public void setDuorenbanli(String duorenbanli) {
		this.duorenbanli = duorenbanli;
	}

	
}
