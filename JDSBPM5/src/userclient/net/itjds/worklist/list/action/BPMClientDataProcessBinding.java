package net.itjds.worklist.list.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.query.Condition;
import net.itjds.bpm.engine.query.ConditionKey;
import net.itjds.bpm.engine.query.Order;
import net.itjds.userclient.common.BPMUserClientUtil;

@SuppressWarnings("unchecked")
public class BPMClientDataProcessBinding extends BPMClientBaseBinding {
	private String activityDefId;
	public String processDefName;
	public String processDeId;
	private String processDefVersionIds;
	
	public String getProcessDefVersionIds() {
		return processDefVersionIds;
	}

	public void setProcessDefVersionIds(String processDefVersionIds) {
		this.processDefVersionIds = processDefVersionIds;
	}
	Condition subCondition = null; // ---------------子条件状态
	public String execute() throws Exception {
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		WorkflowClientService client = bpmUserClientUtil.getClient();

		/**
		 * ---------------------------------------------------权限设置开始---------------------------------------
		 */
		Condition condition = new Condition(); // 过虑主条件,子条件
		Condition mainCondition = new Condition(); // ---------------主条件状态
		
	
		Condition cState = null;
		Map ctx = new HashMap(); // ---------------权限状态
		if (ctype != null) {
			mainCondition = BPMUserClientUtil.getClassificationCondition(
					BPMUserClientUtil.CONDITION_TYPE_PROCESSINST,
					ctype);
		}
		int state = Integer.parseInt(this.state);
		switch (state) {
		case 0: // 草稿
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_MYWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_NOTSTARTED);
			break;
		case 1: // 待办工作，所有待签收的公文
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_WAITEDWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING); 
			break;
		case 2: // 在办工作，所有已签收未办完的公文
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_CURRENTWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING);
			break;
		case 3: // 已归档
			
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ALLWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_COMPLETED);
			break;
		case 4: // 所有工作
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ALLWORK);
			break;
		case 5: // 我的工作，包括所有的待办工作和在办工作
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_MYWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING);
			break;
		case 6: // 等待阅办
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_READ);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING);
			break;
		case 7: // 已阅必
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ENDREAD);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING);
			break;
		case 8: //已办
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION, OARightConstants.CONDITION_COMPLETEDWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING);
			break;
		default: // 非法：所有工作
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ALLWORK);
			break;
		}
		if (cState != null) {
			mainCondition.addCondition(cState, Condition.JOIN_AND);
		}
		
		if(processDeId != null && processDeId.length()>0){
			cState = new Condition(ConditionKey.PROCESSDEF_ID,
					Condition.EQUALS, processDeId);
			mainCondition.addCondition(cState, Condition.JOIN_AND);
		}
		
		 // 加上指定活动定义
		if (this.getActivityDefId() != null && !this.getActivityDefId().equals("") ) {
			StringBuffer activityDefId = new StringBuffer() ;
			String[] activityDefIds = getActivityDefId().split(",");
			for(int i=0;i<activityDefIds.length;i++)
			{
				activityDefId.append("'");
				activityDefId.append(activityDefIds[i]);
				activityDefId.append("'");
				if(i+1 < activityDefIds.length)
					activityDefId.append(",");
			}
			String whereSql = " where activitydef_id in ("+activityDefId.toString()+" ))";
			String inSql = " ( select processinst_id from bpm_activityinstance";
			Condition actvityDefCon = new Condition(ConditionKey.PROCESSINST_ID,
					Condition.IN, inSql + whereSql);
			mainCondition.addCondition(actvityDefCon, Condition.JOIN_OR);
		}
		
		//表单条件
		Condition formCon = bpmUserClientUtil.getFormCondition(this
				.getMapdaoMap(), 1);
		if (formCon != null) {
			
			getSubCondition().addCondition(formCon, Condition.JOIN_AND);
		}
		
		//指定流程ID过滤
		if (this.getProcessDefVersionIds() != null && !this.getProcessDefVersionIds().equals("") ) {
			StringBuffer processDefIdsStr = new StringBuffer() ;
			String[] processDefIds = getProcessDefVersionIds().split(",");
			 if (getProcessDefVersionIds().startsWith("!")){
				 String fristProcessDefId=processDefIds[0].substring(1, processDefIds[0].length());
				 processDefIds[0]= fristProcessDefId;
			 }
		Condition processDefCon = null;
			for(int i=0;i<processDefIds.length;i++)
			{
				processDefIdsStr.append("'");
				processDefIdsStr.append(processDefIds[i]);
				processDefIdsStr.append("'");
				if(i+1 < processDefIds.length)
					processDefIdsStr.append(",");
			}
			
			if (getProcessDefVersionIds().startsWith("!")){
			 processDefCon = new Condition(ConditionKey.PROCESSINST_PROCESSDEF_VERSION_ID,
					Condition.NOT_IN, "("+processDefIdsStr.toString()+" )");
			 }else{
				  processDefCon = new Condition(ConditionKey.PROCESSINST_PROCESSDEF_VERSION_ID,
							Condition.IN, "("+processDefIdsStr.toString()+" )");
			 }
			mainCondition.addCondition(processDefCon, Condition.JOIN_AND);
		}
		
		//标题处理
		if(status.equals("文件标题")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FILETITLE' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			//编号查询
		}else if(status.equals("文件编号")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='BH' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			
			//文件类型查询
		}else if(status.equals("文件类型")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME=WJLX' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			
			//等级查询
		}else if(status.equals("文件等级")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='IMPENDING' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			//来文单位查询
		}else if(status.equals("原文编号")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='YWBH' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			//来文单位查询
		}else if(status.equals("收文编号")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='SWBH' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			//来文单位查询
		}else if(status.equals("发方总号")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FFZH' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			//来文单位查询
		}else if(status.equals("来文单位")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='LWDW' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
		}
		
//		根据流程名称查询
		if (this.getProcessDefName()!= null && this.getProcessDefName().trim().length()>0) {
			String whereSql = " WHERE DEFNAME='"+this.getProcessDefName()+"' ) ";
			String inSql = " ( SELECT PROCESSDEF_ID FROM BPM_PROCESSDEF ";
			Condition processC = new Condition(ConditionKey.PROCESSINST_PROCESSDEF_ID,
					Condition.IN, inSql + whereSql);
			getSubCondition().addCondition(processC, Condition.JOIN_AND);
		}
		
		
//		 时间段处理
		if (this.startTime != null) {
			if (endTime == null) {
				endTime = new Date();
			}
			Collection timeList = new ArrayList();
			timeList.add(startTime);
			timeList.add(this.endTime);
			Condition timec = new Condition(ConditionKey.PROCESSINST_STARTTIME,
					Condition.BETWEEN, timeList);
			getSubCondition().addCondition(timec, Condition.JOIN_AND);
		}
		
		if(this.subCondition != null)
		{
			condition.addCondition(subCondition,Condition.JOIN_AND);
		}
			condition.addCondition(mainCondition,Condition.JOIN_AND);
		
		condition.addOrderBy(new Order(
				ConditionKey.PROCESSINST_STARTTIME, false));
		List<ProcessInst> list = client.getProcessInstList(condition,
				null, ctx);
		/**
		 *-------------------------------------------- 权限设置结束---------------------------------------
		 */
		StringBuffer json = InjectProcessInstData(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=gbk");
		PrintWriter pw = response.getWriter();
		pw.append(json.toString());
		pw.flush();
		return null;
	}
	public Condition getSubCondition()
	{
		if(subCondition == null)
		{
			subCondition =  new Condition();
		}
		return this.subCondition;
	}
	
	public String getActivityDefId() {
		return activityDefId;
	}
	public void setActivityDefId(String activityDefId) {
		this.activityDefId = activityDefId;
	}
	public String getProcessDefName() {
		return processDefName;
	}
	public String getProcessDeId() {
		return processDeId;
	}
	public void setProcessDeId(String processDeId) {
		this.processDeId = processDeId;
	}

}
