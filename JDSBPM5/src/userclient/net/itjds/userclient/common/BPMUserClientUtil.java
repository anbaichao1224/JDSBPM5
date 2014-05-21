/**
 * $RCSfile: BPMUserClientUtil.java,v $
 * $Revision: 1.3 $
 * $Date: 2013/06/04 09:56:15 $
 *
 * Copyright (C) 2003 itjds, Inc. All rights reserved.
 *
 * This software is the proprietary information of itjds, Inc.
 * Use is subject to license terms.
 */
package net.itjds.userclient.common;


import java.sql.SQLException;
import java.util.ArrayList;

import java.util.HashMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.opensymphony.xwork2.ActionContext;


import net.itjds.service.bpm.GetRouteToBean;
import net.itjds.service.bpm.RouteToBean;
import net.itjds.userclient.bpm.search.SearchMapDAODataMap;
import net.itjds.usm2.db.util.EsbUtil;



import net.itjds.bpm.client.ActivityDef;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.bpm.data.DataFactory;
import net.itjds.bpm.engine.BPMException;


import net.itjds.bpm.engine.BPMSessionHandle;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.ReturnType;
import net.itjds.bpm.engine.RightEngine;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.WorkflowEngine;


import net.itjds.bpm.engine.inter.EIActivityInstHistory;
import net.itjds.bpm.engine.query.Condition;
import net.itjds.bpm.engine.query.ConditionKey;
import net.itjds.common.mapdao.MapDAO;
import net.itjds.common.org.base.Person;
import net.itjds.common.util.UUIDGenerator;
import net.itjds.j2ee.dao.DAOException;





/**
 * <p>
 * Title: BPM工作流管理系统
 * </p>
 * <p>
 * Description: OA应用工具类
 * </p>
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 * <p>
 * Company: www.justdos.net
 * </p>
 * 
 * @author wenzhang
 * @version 1.0
 */
public final class BPMUserClientUtil {

	/** 流程定义版本内扩展属性 */
	public static final int CONDITION_TYPE_PROCESSDEFVERSION = 1;

	/** 活动定义内扩展属性 */
	public static final int CONDITION_TYPE_ACTIVITYDEF = 2;

	/** 路由定义内扩展属性 */
	public static final int CONDITION_TYPE_ROUTEDEF = 3;

	/** 流程实例内扩展属性 */
	public static final int CONDITION_TYPE_PROCESSINST = 4;
	
	/** 兼容使用 */
	public static final int CONDITION_TYPE_PROCESSINST_FLOWTYPE = 7;

	/** 活动实例内扩展属性 */
	public static final int CONDITION_TYPE_ACTIVITYINST = 5;

//	/** 活动实例历史内扩展属性 */
	public static final int CONDITION_TYPE_ACTIVITYINSTHISTORY = 6;
		
	
	private String saveType;
	
	private WorkflowClientService client;

	
	public BPMUserClientUtil(){
		
		
	}
	
	
	/**
	 * 获取工作流接口
	 * @return
	 * @throws UserClientException
	 */
	public WorkflowClientService getClient()  {
		if (this.client == null)
		      try {
		        this.client = ((WorkflowClientService)parExprocession("$BPMC"));
		      } catch (Exception localException) {
		    	  localException.printStackTrace();
		      }
		    if (this.client == null) {
		      try
		      {
		        this.client = new BPMSessionFactory().newClientService(new BPMSessionHandle(UUIDGenerator.genUUID()), "oa");
		        
		      }
		      catch (BPMException e) {
		        e.printStackTrace();
		      }
		    }
		    return this.client;
	}

	
	/**
	 * 
	 * @param dataMap
	 * @param type 0：活动条件，1：流程条件
	 * @return
	 * @throws DAOException
	 * @throws SQLException
	 */
	public Condition getFormCondition(SearchMapDAODataMap dataMap,int type) throws DAOException, SQLException{
		Condition c = null;
		if (dataMap.size()>0){
		
		Iterator<String> it=dataMap.keySet().iterator();
		Set<String> keyList=new HashSet<String>();
		String typekey="";
		if (type==0){ 
			typekey=DataFactory.ACTIVITYINSTID_KEY;
		}else if (type==1){
			typekey=DataFactory.PROCESSINSTID_KEY;
		}else if (type==2){
			typekey=DataFactory.ACTIVITYINSTHISTORYID_KEY;
		}
		for(;it.hasNext();){
			String key=it.next();
			if (dataMap.get(key) instanceof MapDAO){
				MapDAO mapdao=(MapDAO) dataMap.get(key);
				mapdao.findValueSet(keyList, null,typekey);
			}
		}
		
		List<String> strList=new ArrayList<String>();
		strList.addAll(keyList);
		// 根据表单中的值来匹配流程
		 StringBuffer subWhere=new StringBuffer();
		 	if (keyList.size()>0){
		 		
		 		if(keyList.size()>500)
		 		{
		 		//ORACLE有最大表达式大于一千的限制
		 		for(int f=0;f<keyList.size()/500;f++){
					List subList=strList.subList(f*500, (f+1)*500>keyList.size()?keyList.size():(f+1)*500);
					if (f+1<keyList.size()/500){
						for(int k=0;k<subList.size();k++){
							if (k<subList.size()-1){
							subWhere.append("'"+(String) subList.get(k).toString().trim()+"'"+",");
							}else{
							subWhere.append("'"+(String) subList.get(k).toString().trim()+"') or "+typekey+" in (");
							}
						}
					}else{
						for(int k=0;k<subList.size();k++){
							if (k<subList.size()-1){
							subWhere.append("'"+(String) subList.get(k).toString().trim()+"'"+",");
							}else{
							subWhere.append("'"+(String) subList.get(k).toString().trim()+"')");
							}
						}
					}
				}
		 		}else 
		 		{ 
		 			for(int k=0;k<strList.size();k++){
							if (k<strList.size()-1){
							subWhere.append("'"+(String) strList.get(k).toString().trim()+"'"+",");
							}else{
							subWhere.append("'"+(String) strList.get(k).toString().trim()+"' ) ") ; 
							}
						}
		 		}
		 	}else{
		 		
		 		subWhere.append("'' ) ");
		 	}
//			System.out.println("where sql ========="+ whereSql);
			
			String whereSql = "";
		//	System.out.println("where sql ========="+ whereSql); 
			if (type==0){ 
				whereSql=" "+subWhere.toString()+" ) ";
				c = new Condition(ConditionKey.ACTIVITYINST_ID,
						Condition.IN, whereSql);
			}else if (type==1){
				whereSql=" "+subWhere.toString()+" ) ";
				c = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, whereSql);
			}else if (type==2){
				whereSql=" "+subWhere.toString()+" ) ";
				c = new Condition(ConditionKey.ACTIVITYHISTORY_ID,
						Condition.IN, whereSql);
			}
		}	
			
		
		return c;
	}
	

	/**
	 * 取得扩展属性Condition，用来将扩展属性作为查询条件时使用 此条件只能用于查询'CUSTOMIZE'类型的扩展属性！
	 * 
	 * @param type
	 *            扩展属性所处的位置 <code>
	 *    <li>CONDITION_TYPE_PROCESSDEFVERSION : 流程定义版本内扩展属性 </li>
	 *    <li>CONDITION_TYPE_ACTIVITYDEF : 活动定义内扩展属性 </li>
	 *    <li>CONDITION_TYPE_ROUTEDEF : 路由定义内扩展属性 </li>
	 *    <li>CONDITION_TYPE_PROCESSINST : 流程实例内扩展属性 </li>
	 *    <li>CONDITION_TYPE_ACTIVITYINST : 活动实例内扩展属性 </li>
	 *    <li>CONDITION_TYPE_ACTIVITYINSTHISTORY : 活动实例历史内扩展属性 </li>
	 *  </code>
	 * @param attName
	 *            属性名称
	 * @param attValue
	 *            属性值
	 * @return
	 */
	public static Condition getAttributeCondition(int type, String attName,
			String attValue) {
		String inSql = "";
		String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='"
				+ attName + "' AND PROPVALUE='" + attValue + "' ) ";
		Condition c = null;
		switch (type) {
		case CONDITION_TYPE_PROCESSDEFVERSION:
			inSql += " ( SELECT PROCESSDEF_VERSION_ID FROM BPM_PROCESSDEF_PROPERTY ";
			c = new Condition(ConditionKey.PROCESSDEF_VERSION_VERSION_ID,
					Condition.IN, inSql + whereSql);
			break;
		case CONDITION_TYPE_ACTIVITYDEF:
			inSql += " ( SELECT ACTIVITYDEF_ID FROM BPM_ACTIVITYDEF_PROPERTY ";
			c = new Condition(ConditionKey.ACTIVITYDEF_ID, Condition.IN, inSql
					+ whereSql);
			break;

		case CONDITION_TYPE_ROUTEDEF:
			inSql += " ( SELECT ROUTEDEF_ID FROM BPM_ROUTEDEF_PROPERTY ";
			c = new Condition(ConditionKey.ROUTEDEF_ID, Condition.IN, inSql
					+ whereSql);
			break;

		case CONDITION_TYPE_PROCESSINST:
			inSql += " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
			c = new Condition(ConditionKey.PROCESSINST_ID, Condition.IN, inSql
					+ whereSql);
			break;

		case CONDITION_TYPE_ACTIVITYINST:
			inSql += " ( SELECT ACTIVITYINST_ID FROM BPM_ACTIVITYINST_PROPERTY ";
			c = new Condition(ConditionKey.ACTIVITYINST_ID, Condition.IN, inSql
					+ whereSql);
			break;

		case CONDITION_TYPE_ACTIVITYINSTHISTORY:
			inSql += " ( SELECT ACTIVITYHISTORY_ID FROM BPM_ACTIVITYHISTORY_PROPERTY ";
			c = new Condition(ConditionKey.ACTIVITYHISTORY_ID, Condition.IN,
					inSql + whereSql);
			break;
		default:
			return null;
		}

		return c;
	}

	/**
	 * 取得查询流程类别(Classification)的Condition
	 * 
	 * @param type
	 *            要查询的位置 <code>
	 *    <li>CONDITION_TYPE_PROCESSDEFVERSION : 查询流程定义版本 </li>
	 *    <li>CONDITION_TYPE_ACTIVITYDEF : 查询活动定义 </li>
	 *    <li>CONDITION_TYPE_ROUTEDEF : 查询路由定义 </li>
	 *    <li>CONDITION_TYPE_PROCESSINST : 查询流程实例 </li>
	 *    <li>CONDITION_TYPE_ACTIVITYINST : 查询活动实例 </li>
	 *    <li>CONDITION_TYPE_ACTIVITYINSTHISTORY : 查询活动实例历史 </li>
	 *  </code>
	 * @param classification
	 *            类别
	 * @return
	 */
	public static Condition getClassificationCondition(int type,
			String classification) {
		String inSql = "";
		Condition c = null;
		switch (type) {
		case CONDITION_TYPE_PROCESSDEFVERSION:
			inSql += " SELECT PROCESSDEF_ID FROM BPM_PROCESSDEF WHERE CLASSIFICATION = '"
					+ classification + "' ";
			c = new Condition(ConditionKey.PROCESSDEF_VERSION_PROCESSDEF_ID,
					Condition.IN, inSql);
			break;
		case CONDITION_TYPE_ACTIVITYDEF:
			inSql += " SELECT PROCESSDEF_ID FROM BPM_PROCESSDEF WHERE CLASSIFICATION = '"
					+ classification + "' ";
			c = new Condition(ConditionKey.ACTIVITYDEF_PROCESSDEF_ID,
					Condition.IN, inSql);
			break;

		case CONDITION_TYPE_ROUTEDEF:
			inSql += " SELECT PROCESSDEF_ID FROM BPM_PROCESSDEF WHERE CLASSIFICATION = '"
					+ classification + "' ";
			c = new Condition(ConditionKey.ROUTEDEF_ID, Condition.IN, inSql);
			break;
	
	
		//流程实例
		case CONDITION_TYPE_PROCESSINST:
			inSql += " SELECT T.PROCESSDEF_ID FROM BPM_PROCESSDEF T WHERE CLASSIFICATION = '"
				+ classification + "' ";

			c = new Condition(ConditionKey.PROCESSINST_PROCESSDEF_ID,
					Condition.IN, inSql);
			break;

		//活动实例
		case CONDITION_TYPE_ACTIVITYINST:
			inSql += "    SELECT g.processdef_version_id from bpm_processdef_version g  where g.processdef_id  in (select t.processdef_id FROM BPM_PROCESSDEF t WHERE CLASSIFICATION = '"
					+ classification + "') ";
			c = new Condition(ConditionKey.ACTIVITYINST_PROCESSDEF_ID,
					Condition.IN, inSql);
			break;

		//活动历史实例
		case CONDITION_TYPE_ACTIVITYINSTHISTORY:
			inSql += "    SELECT g.processdef_version_id from bpm_processdef_version g  where g.processdef_id  in (select t.processdef_id FROM BPM_PROCESSDEF t WHERE CLASSIFICATION = '"
				+ classification + "') ";
			c = new Condition(ConditionKey.ACTIVITYHISTORY_PROCESSDEF_ID,
					Condition.IN, inSql);
			break;
			//兼容不推荐使用
		case CONDITION_TYPE_PROCESSINST_FLOWTYPE:
			inSql += "    SELECT g.processdef_version_id from bpm_processdef_version g  where g.processdef_id  in (select t.processdef_id FROM BPM_PROCESSDEF t WHERE CLASSIFICATION = '"
				+ classification + "') ";

			c = new Condition(ConditionKey.PROCESSINST_PROCESSDEF_ID,
					Condition.IN, inSql);
			break;
		default:
			return null;
		}

		return c;
	}

	public int performDisplay(String activityInstId) throws BPMException {
		return this.getClient().display(activityInstId).mainCode();
	}

	
	//	阅毕
	private ReturnType endRead(String activityInstId) throws BPMException{
		return	client.getActivityInst(activityInstId).endRead();
	}
	
	
	
	@SuppressWarnings("unchecked")
	private int routeTo(String activityInstId,Map ctx) throws BPMException{
	
		List<String> activityDefList=new ArrayList<String> ();
			GetRouteToBean routeBeanList=(GetRouteToBean) ActionContext.getContext().getValueStack().findValue("$GetRouteToBean");
		Map<String, RouteToBean> routeBeanMap=routeBeanList.getBeanMap();
		Iterator<String> it=routeBeanMap.keySet().iterator();
		
		List<Map> contexMapList=new ArrayList<Map>();
		
		
		
		for(;it.hasNext();){
			String toActivityDefId=it.next();
			RouteToBean routeBean=routeBeanMap.get(toActivityDefId);
			activityDefList.add(toActivityDefId);
			
			Map contextMap=new HashMap();
			
			List performPersonIdList=routeBean.getPerformPersonIdList();
			if (performPersonIdList.size()==0){
				if (ctx==null){
					ctx = new HashMap<String, String>();
				}
			
				
				ctx.put(OARightConstants.CTX_ACTIVITYINST_ID, activityInstId);
				ctx.put(OARightConstants.CTX_PROCESSINST_ID, this.getClient().getActivityInst(activityInstId).getProcessInstId());	
				List<Person> performPersonList= (List<Person>) this.getClient().getActivityDefRightAttribute(toActivityDefId, OARightConstants.ACTIVITYDEF_RIGHT_ATT_PERFORMERS, ctx);	
				for(int p=0;p<performPersonList.size();p++){
					performPersonIdList.add(performPersonList.get(p).getID());
				}
			}
			if (ctx==null){
				ctx=new HashMap();
			}
			contextMap.putAll(ctx);
			contextMap.put(OARightConstants.CTX_PERFORMERS, performPersonIdList);
			contexMapList.add(contextMap);
			
		}
		
		
		ReturnType type=this.getClient().routeTo(activityInstId, (String[]) activityDefList
				.toArray(new String[0]), (Map[]) contexMapList
				.toArray(new Map[0]));	
		
		if (routeBeanList.getReadPersonIdList().size()>0){
			String activityHistoryId=this.getClient().getActivityInst(activityInstId).getActivityInstHistoryListByActvityInst().get(0).getActivityHistoryId();
			ReturnType copyType=	this.getClient().copyTo(activityHistoryId, routeBeanList.getReadPersonIdList());
			if (!type.isSucess()){
				return -1;
			}
		}
	
		if (!type.isSucess()){
			return -1;
		}
		return 1;
	}

	/***
	 * 
	 * @param activityInstId
	 * @return
	 * @throws BPMException 
	 */
	public int performUpdate(String activityInstId) throws BPMException {
		if (saveType.equalsIgnoreCase("saveonly")) {
			ActivityInst activityInst=this.getClient().getActivityInst(activityInstId);
			this.getClient().updateActivityInstMapDAO(activityInstId, 
					activityInst.getProcessInstId(), 
					activityInst.getAllMapDAO(this.getClient().getConnectInfo().getUserID()));
			
			return 0;
		}
		if (saveType.equalsIgnoreCase("copyto")) {
			endRead(activityInstId);
		}
		if (saveType.equalsIgnoreCase("resend")) {
			ActivityInst activityInst=this.getClient().getActivityInst(activityInstId);
			this.getClient().updateActivityInstMapDAO(activityInstId, 
					activityInst.getProcessInstId(), 
					activityInst.getAllMapDAO(this.getClient().getConnectInfo().getUserID()));
			reSend(activityInstId);
		}
		
		
		if (saveType.equalsIgnoreCase("routeback")) {
			      String activityInstHistoryId=(String) parExprocession("activityInstHistoryId");
					this.getClient().routeBack(activityInstId, activityInstHistoryId,null);				
				return 2;
		} 
		if (saveType.equalsIgnoreCase("routetoend")) {
					this.getClient().routeTo(activityInstId,new String[] { ActivityDef.VIRTUAL_LAST_DEF }, new Map[1]);		
		} 
		if (saveType.equalsIgnoreCase("routeto")
				|| saveType.equalsIgnoreCase("multirouteto")
				|| saveType.equalsIgnoreCase("changeperformer")) {
			return routeTo(activityInstId,null);	
			
		}
		return 0;
	}

//	private void reSend(String activityInstId) throws BPMException {
//		  String activityInstHistoryId=EsbUtil.getHttpParamsByName("activityInstHistoryId");
//		  if (activityInstHistoryId==null|| activityInstHistoryId.equals("")){			  
//				ActivityInst ai=client.getActivityInst(activityInstId);
//				//ActivityInstHistory activityInstHistory=ai.getActivityInstHistoryListByActvityInst().get(0);
//				List<ActivityInstHistory> llll= (List<ActivityInstHistory>)client.getLastActivityInstHistoryListByActvityInst(ai.getActivityInstId(), null);
//				
//				
//				ActivityInstHistory activityInstHistory = llll.get(llll.size()-1);
//				//ActivityInstHistory activityInstHistory=ai.getActivityInstHistoryListByActvityInst().get(ai.getActivityInstHistoryListByActvityInst().size()-1);
//				activityInstHistoryId=activityInstHistory.getActivityHistoryId();
//		  }
//		  
//		  Map ctx=new HashMap();
//		  ActivityInst activityInst=client.copyActivityInstByHistory(activityInstHistoryId,ctx);
//	
//		
//		 // ctx.put("reSend", "true");
//		  this.routeTo(activityInst.getActivityInstId(),ctx);
//		
//	}
	
	private String getSelfHistoryId(String activityInstId){
		ActivityInstHistory retHis = null;
		//return this.getActivityInstHistory().getActivityHistoryId();
		BPMUserClientUtil bpmUserClientUtil =  new BPMUserClientUtil();
		WorkflowClientService client =  bpmUserClientUtil.getClient();
		
		try {
			ActivityInst ai=client.getActivityInst(activityInstId);
			List actInstList = ai.getProcessInst().getActivityInstList();
			for(int j=0; j<actInstList.size();j++){
				ActivityInst inst  =(ActivityInst)actInstList.get(j);
				List historyList = client.getLastActivityInstHistoryListByActvityInst(inst.getActivityInstId(), null);
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
			}
			
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return retHis.getActivityHistoryId();
	}
	private void reSend(String activityInstId) throws BPMException {
		String activityInstHistoryId1=EsbUtil.getHttpParamsByName("activityInstHistoryId");
		  String activityInstHistoryId=getSelfHistoryId(activityInstId);//EsbUtil.getHttpParamsByName("activityInstHistoryId");
		  if (activityInstHistoryId==null|| activityInstHistoryId.equals("")){			  
				ActivityInst ai=client.getActivityInst(activityInstId);
				WorkflowEngine engine=WorkflowEngine.getEngine("oa");
				List<EIActivityInstHistory> list=engine.getLastSplitActivityInstHistoryByActvityInst(activityInstId);
				EIActivityInstHistory activityInstHistory=(EIActivityInstHistory) list.get(list.size()-1);
				activityInstHistoryId=activityInstHistory.getActivityHistoryId();
		  }
		  
		  Map ctx=new HashMap();
		  Person person=(Person) EsbUtil.parExpression("$currPerson");
			ctx.put(RightEngine.CTX_USER_ID,person.getID());
		  ActivityInst activityInst=client.copyActivityInstByHistory(activityInstHistoryId,ctx);
	
		
		 // ctx.put("reSend", "true");
		  this.routeTo(activityInst.getActivityInstId(),ctx);
		
	}

	public void setBPM_UpdateType(String saveType) {
		this.saveType = saveType;
	}
	
	private Object parExprocession(String exprocession){
		return ActionContext.getContext().getValueStack().findValue(exprocession);
	}


	
}
