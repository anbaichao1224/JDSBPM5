package net.itjds.userclient.usm.action;


import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.engine.AdminService;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.WorkflowServer;
import net.itjds.bpm.engine.query.Condition;
import net.itjds.bpm.engine.query.ConditionKey;
import net.itjds.bpm.engine.query.Order;
import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.action.BPMClientBaseBinding;

@SuppressWarnings("unchecked")
public class BPMAdminDataProcessBinding extends BPMClientBaseBinding {
	private String activityDefId;
	public String processDefName;
	public String processDeId;
	Condition subCondition = null; // ---------------子条件状态
	public String execute() throws Exception {
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		//WorkflowClientService client = bpmUserClientUtil.getClient();
		AdminService client = WorkflowServer.getInstance().getAdminService(bpmUserClientUtil.getClient());

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

		
		cState = new Condition(ConditionKey.PROCESSINST_STATE,
				Condition.NOT_EQUAL, ProcessInst.STATE_NOTSTARTED);
		if (cState != null) {
			mainCondition.addCondition(cState, Condition.JOIN_AND);
		}
		
		cState = new Condition(ConditionKey.PROCESSDEF_ID,
				Condition.EQUALS, processDeId);
		mainCondition.addCondition(cState, Condition.JOIN_AND);
		
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
		
		//标题处理
		if (this.fileTitle != null && fileTitle.trim().length()>0) {
			String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FILETILE' AND PROPVALUE like '%" + fileTitle + "%' ) ";
			String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
			Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
					Condition.IN, inSql + whereSql);
			getSubCondition().addCondition(processC, Condition.JOIN_AND);
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
		//查询由本部门发出去的文件
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession session = request.getSession(true);
			String personId = session.getAttribute("personId").toString();
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String[] orgids = person.getOrgIds();
			Org org = OrgManagerFactory.getOrgManager().getOrgByID(orgids[0]);
			List userIds = org.getPersonIdList();
			//本部门所有人
			ctx.put("USERIDS", userIds);
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ALLWORK);
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
