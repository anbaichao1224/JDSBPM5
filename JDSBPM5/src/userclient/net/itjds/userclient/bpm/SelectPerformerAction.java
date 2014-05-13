/**
 * $RCSfile: SelectPerformerAction.java,v $
 * $Revision: 1.3 $
 * $Date: 2013/07/31 13:26:14 $
 *
 * Copyright (C) 2003 itjds, Inc. All rights reserved.
 *
 * This software is the proprietary information of itjds, Inc.
 * Use is subject to license terms.
 */
package net.itjds.userclient.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.ActivityDef;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.RouteDef;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.OARightEngine;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.WorkflowEngine;
import net.itjds.bpm.engine.WorkflowServer;
import net.itjds.bpm.engine.inter.EIRouteDef;
import net.itjds.bpm.engine.inter.EIRouteInst;
import net.itjds.bpm.engine.proxy.RouteDefProxy;
import net.itjds.common.org.base.OrgNotFoundException;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.util.OnlyOrgExtTree;
import net.itjds.common.org.util.PersonExtTree;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.userclient.common.UserClientException;

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
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company: www.justdos.net
 * </p>
 * 
 * @author wenzhangli
 * @version 1.0
 */
public class SelectPerformerAction extends BPMActionBase {

	@SuppressWarnings("unused")
	private final static String READ = "READ", PERFORM = "PERFORM";
	private Map<String, String> performTypeMap;
	private Map<String, List<String>> performpersonMap;
	private PersonExtTree readPersonExtTree;
	private String activityInstHistoryId;
	private String routeDefId;
	private String routeto = "routeto";
	private String childOrgId;
	@SuppressWarnings("unchecked")
	private List readers;
	private String treeType = PERFORM;
	private String checked;
	private String disabled;
	@SuppressWarnings("unchecked")
	private Map performpersonExtTreeMap;
	private List<RouteDef> routeDefs;
	private String bf;

	public String getBf() {
		return bf;
	}

	public void setBf(String bf) {
		this.bf = bf;
	}

	public String getTreeType() {
		return treeType;
	}

	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}

	public SelectPerformerAction() {
		super();
	}

	public String execute() throws BPMException, UserClientException {
		this.init();
		return Action.SUCCESS;

	}

	@SuppressWarnings("unchecked")
	public List getRouteDefs() throws UserClientException, BPMException {

		if (routeDefs == null) {
			routeDefs = new ArrayList<RouteDef>();
			client = bpmUserClientUtil.getClient();

			ActivityInst activityInst = client.getActivityInst(activityInstId);
			if (routeto.equals("reSend") && activityInstHistoryId != null
					&& !activityInstHistoryId.equals("")) {

				ActivityInstHistory his = client
						.getActivityInstHistory(activityInstHistoryId);
				WorkflowEngine workflowEngine = WorkflowEngine.getEngine("oa");

				@SuppressWarnings("unused")
				String startActivityDefId = his.getActivityDefId();

				List<ActivityInstHistory> historyList = client
						.getLastActivityInstHistoryListByActvityInst(his
								.getActivityInstId(), null);

				for (int i = 0; i < historyList.size(); i++) {
					ActivityInstHistory ahis = historyList.get(i);
					System.out.println(ahis.getDealMethod());
					if (ahis.getDealMethod().equals("SPLITED")) {
						his = ahis;
						startActivityDefId = ahis.getActivityDefId();
					}
				}

				List routeInList = workflowEngine
						.getActivityInstHistoryOutRoute(his
								.getActivityHistoryId());
				// String startActivityDefId=his.getActivityDefId();
				String toActivityDefId = null;

				@SuppressWarnings("unused")
				List<String> routeDefIds = new ArrayList<String>();
				for (int k = routeInList.size() - 1; k >= 0; k--) {

					EIRouteInst routeInst = (EIRouteInst) routeInList.get(k);

					if (routeInst.getRouteType().equals("ACTIVITY")) {
						ActivityInst toActivityInst = client
								.getActivityInst(routeInst.getToActivityId());
						toActivityDefId = toActivityInst.getActivityDefId();
						continue;

					} else if (toActivityDefId == null) {
						ActivityInstHistory toActivityInstHis = client
								.getActivityInstHistory(routeInst
										.getToActivityId());
						toActivityDefId = toActivityInstHis.getActivityDefId();
					}

				}

				List list = his.getActivityDef().getOutRouteIds();
				for (int k = 0; k < list.size(); k++) {
					RouteDef routeDef = new RouteDefProxy((EIRouteDef) list
							.get(k), client.getSystemCode());

					if (routeDef.getToActivityDefId().equals(toActivityDefId)) {
						this.routeDefId = routeDef.getRouteDefId();
					}
				}
			}

			if (this.routeDefId != null && !routeDefId.equals("")) {
				if (!routeDefId.equals("read")) {
					RouteDef routeDef = client.getRouteDef(routeDefId);

					routeDefs.add(routeDef);
				}
			} else {
				routeDefs = activityInst.getNextRoutes();
			}
		}
		return routeDefs;
	}

	public boolean isNoNeedSelect(String toActivityDefId) throws BPMException {

		String performType = (String) client.getActivityDefRightAttribute(
				toActivityDefId,
				OARightConstants.ACTIVITYDEF_RIGHT_ATT_PERFORMTYPE, null);
		if (performType.equals(OARightConstants.PERFORMTYPE_NEEDNOTSELECT)
				|| performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public List<Person> getPerformPersons(String toActivityDefId)
			throws UserClientException, BPMException {
		Map<String, String> ctx = new HashMap<String, String>();
		ctx.put(OARightConstants.CTX_ACTIVITYINST_ID, activityInstId);
		ctx.put(OARightConstants.CTX_PROCESSINST_ID, activityInst
				.getProcessInstId());
		List<Person> PerformPersons = (List) client
				.getActivityDefRightAttribute(toActivityDefId,
						OARightConstants.ACTIVITYDEF_RIGHT_ATT_PERFORMERS, ctx);

		return PerformPersons;
	}

	public String getReadExtStr() {
		readers = this.getReaders();
		if (readPersonExtTree == null) {
			this.readPersonExtTree = new PersonExtTree(this.readers, null, true);
		}
		String treeStr = readPersonExtTree.getChildTree(childOrgId);
		return treeStr;

	}

	public String getPerformType(String routeDefId) {
		performTypeMap = this.getPerformTypeMap();
		if (!performTypeMap.containsKey(routeDefId)) {
			this.initMap();
		}
		return performTypeMap.get(routeDefId);
	}
	private String getSelfHistoryId(String activityInstHistoryId){
		ActivityInstHistory retHis = null;
		//return this.getActivityInstHistory().getActivityHistoryId();
		BPMUserClientUtil bpmUserClientUtil =  new BPMUserClientUtil();
		WorkflowClientService client =  bpmUserClientUtil.getClient();
		
		try {
			ActivityInstHistory activityInstHistory = client.getActivityInstHistory(activityInstHistoryId);
			
			List historyList = client.getLastActivityInstHistoryListByActvityInst(activityInstHistory.getActivityInst().getActivityInstId(), null);
			//List historyList = client.getActivityInstHistoryListByProcessInst(activityInstHistory.getProcessInstId(), null);
			for(int i=0; i<historyList.size(); i++){
				ActivityInstHistory his = (ActivityInstHistory) historyList.get(i);
				if(his.getDealMethod()!=null && "SPLITED".equals(his.getDealMethod())){
					List performers =  (List) client.getActivityInstHistoryRightAttribute(his.getActivityHistoryId(),  OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER, null);
					if (performers.size()>0){
						Person p = (Person) performers.get(0);
						System.out.println("historyId=" + his.getActivityHistoryId() + " and performer=" + p.getName());
						Person currentPerson = (Person) ActionContext.getContext().getValueStack().findValue("$currPerson");
						if(currentPerson.getID().equals(p.getID()) && his.getActivityInst()!=null){
							retHis = his;
							//break;
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
	@SuppressWarnings("unchecked")
	public String getPerformersExtStr() throws BPMException,
			OrgNotFoundException {
		if(this.bf!=null&&this.bf.equals("bf")){
			 BPMUserClientUtil bpmUserClientUtil =  new BPMUserClientUtil();
				WorkflowClientService client =  bpmUserClientUtil.getClient();
				String hisId = getSelfHistoryId(activityInstHistoryId);
				ActivityInstHistory his = client.getActivityInstHistory(hisId);
					
				List<ActivityInst> insts = client.getActivityInstListByOutActvityInstHistory(hisId, null);
				List<String> addrs = new ArrayList<String>(0);
				for(int i=0; i<insts.size(); i++){
					ActivityInst inst = insts.get(i);
					List performers = (List)client.getActivityInstRightAttribute(inst.getActivityInstId(), OARightConstants.ACTIVITYINST_RIGHT_ATT_PERFORMER, null);
					Person p = (Person) performers.get(0);
					addrs.add(p.getName());
				}
			
			performpersonMap = this.getPerformpersonMap();
			String treeStr = "";
			this.performpersonExtTreeMap = this.getPerformpersonExtTreeMap();
			if (!performpersonMap.containsKey(routeDefId)) {
				this.initMap();
			}
			List<String> performperson = performpersonMap.get(routeDefId);
			if (!performpersonExtTreeMap.containsKey(routeDefId)) {

				if (this.getPerformType(routeDefId).equals(
						OARightConstants.PERFORMTYPE_JOINTSIGN)) {
					performpersonExtTreeMap.put(routeDefId, new OnlyOrgExtTree(
							performperson, null));
				} else {
					performpersonExtTreeMap.put(routeDefId, new PersonExtTree(
							performperson, null, true));
				}
			}
			if (this.getPerformType(routeDefId).equals(
					OARightConstants.PERFORMTYPE_JOINTSIGN)) {
				OnlyOrgExtTree performPersonExtTree = (OnlyOrgExtTree) performpersonExtTreeMap
						.get(routeDefId);
				treeStr = performPersonExtTree.getChildTree(childOrgId);
			} else {
				PersonExtTree performPersonExtTree = (PersonExtTree) performpersonExtTreeMap
						.get(routeDefId);
				treeStr = performPersonExtTree.getChildTreeBf(childOrgId,addrs.toArray(new String[addrs.size()]));
			}

			return treeStr;
		}else{
			performpersonMap = this.getPerformpersonMap();
			String treeStr = "";
			this.performpersonExtTreeMap = this.getPerformpersonExtTreeMap();
			if (!performpersonMap.containsKey(routeDefId)) {
				this.initMap();
			}
			List<String> performperson = performpersonMap.get(routeDefId);
			if (!performpersonExtTreeMap.containsKey(routeDefId)) {

				if (this.getPerformType(routeDefId).equals(
						OARightConstants.PERFORMTYPE_JOINTSIGN)) {
					performpersonExtTreeMap.put(routeDefId, new OnlyOrgExtTree(
							performperson, null));
				} else {
					performpersonExtTreeMap.put(routeDefId, new PersonExtTree(
							performperson, null, true));
				}
			}
			if (this.getPerformType(routeDefId).equals(
					OARightConstants.PERFORMTYPE_JOINTSIGN)) {
				OnlyOrgExtTree performPersonExtTree = (OnlyOrgExtTree) performpersonExtTreeMap
						.get(routeDefId);
				treeStr = performPersonExtTree.getChildTree(childOrgId);
			} else {
				PersonExtTree performPersonExtTree = (PersonExtTree) performpersonExtTreeMap
						.get(routeDefId);
				treeStr = performPersonExtTree.getChildTree(childOrgId);
			}

			return treeStr;
		}
		

	}

	@SuppressWarnings("unchecked")
	public void initMap() {
		try {
			client = bpmUserClientUtil.getClient();
			if (activityInstId == null || activityInstId.equals("")) {
				if (this.activityInstHistoryId != null) {
					activityInstId = client.getActivityInstHistory(
							activityInstHistoryId).getActivityInstId();
					activityInst = client.getActivityInst(activityInstId);
				}

			}

			List<RouteDef> routeDefs = this.getRouteDefs();
			readers = new ArrayList();
			performpersonMap = new HashMap<String, List<String>>();
			performTypeMap = new HashMap<String, String>();
			Map<String, String> ctx = new HashMap<String, String>();
			ctx.put(OARightConstants.CTX_ACTIVITYINST_ID, activityInstId);
			ctx.put(OARightConstants.CTX_PROCESSINST_ID, activityInst
					.getProcessInstId());
			List readerList = (List) client.getActivityDefRightAttribute(
					activityInst.getActivityDefId(),
					OARightConstants.ACTIVITYDEF_RIGHT_ATT_READERS, ctx);
			for (int k = 0; routeDefs.size() > k; k++) {
				RouteDef routeDef = routeDefs.get(k);
				ActivityDef toActivityDef = routeDef.getToActivityDef();

				if (toActivityDef == null) {
					throw new Exception(
							"The activity definition not specified!");
				}

				String performType = (String) client
						.getActivityDefRightAttribute(
								toActivityDef.getActivityDefId(),
								OARightConstants.ACTIVITYDEF_RIGHT_ATT_PERFORMTYPE,
								null);
				List performers = null;

				if (performType.equals(OARightConstants.PERFORMTYPE_JOINTSIGN)) {
					OARightEngine rightEngine = (OARightEngine) WorkflowServer
							.getRigthEngine("oa");
					routeDef = client.getRouteDef(routeDefId);
					performers = rightEngine.getParameter("orgs", routeDef
							.getToActivityDefId());
				} else {
					performers = (List) client.getActivityDefRightAttribute(
							toActivityDef.getActivityDefId(),
							OARightConstants.ACTIVITYDEF_RIGHT_ATT_PERFORMERS,
							ctx);
				}
				performpersonMap.put(routeDef.getRouteDefId(), performers);
				performTypeMap.put(routeDef.getRouteDefId(), performType);
			}
			readers.addAll(readerList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getRouteDefId() {
		return routeDefId;
	}

	public void setRouteDefId(String routeDefId) {
		this.routeDefId = routeDefId;
	}

	public String getChildOrgId() {
		return childOrgId;
	}

	public void setChildOrgId(String childOrgId) {
		this.childOrgId = childOrgId;
	}

	@SuppressWarnings("unchecked")
	private Map<String, List<String>> getPerformpersonMap() {
		if (performpersonMap == null || performpersonMap.isEmpty()) {
			this.initMap();
		}
		return performpersonMap;
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> getPerformTypeMap() {

		if (performTypeMap == null) {
			this.initMap();
		}
		return performTypeMap;
	}

	@SuppressWarnings("unchecked")
	public List getReaders() {

		if (readers == null) {
			this.initMap();
		}
		return readers;
	}

	@SuppressWarnings("unchecked")
	private Map getPerformpersonExtTreeMap() {
		if (performpersonExtTreeMap == null) {
			performpersonExtTreeMap = new HashMap<String, PersonExtTree>();
		}
		return performpersonExtTreeMap;
	}

	public String getActivityInstHistoryId() {
		return activityInstHistoryId;
	}

	public void setActivityInstHistoryId(String activityInstHistoryId) {
		this.activityInstHistoryId = activityInstHistoryId;
	}

	public String getRouteto() {
		return routeto;
	}

	public void setRouteto(String routeto) {
		this.routeto = routeto;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

}
