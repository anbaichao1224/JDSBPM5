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
	Condition subCondition = null; // ---------------������״̬
	public String execute() throws Exception {
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		//WorkflowClientService client = bpmUserClientUtil.getClient();
		AdminService client = WorkflowServer.getInstance().getAdminService(bpmUserClientUtil.getClient());

		/**
		 * ---------------------------------------------------Ȩ�����ÿ�ʼ---------------------------------------
		 */
		Condition condition = new Condition(); // ����������,������
		Condition mainCondition = new Condition(); // ---------------������״̬
		
	
		Condition cState = null;
		Map ctx = new HashMap(); // ---------------Ȩ��״̬
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
		
		 // ����ָ�������
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
		
		//������
		Condition formCon = bpmUserClientUtil.getFormCondition(this
				.getMapdaoMap(), 1);
		if (formCon != null) {
			
			getSubCondition().addCondition(formCon, Condition.JOIN_AND);
		}
		
		//���⴦��
		if (this.fileTitle != null && fileTitle.trim().length()>0) {
			String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FILETILE' AND PROPVALUE like '%" + fileTitle + "%' ) ";
			String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
			Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
					Condition.IN, inSql + whereSql);
			getSubCondition().addCondition(processC, Condition.JOIN_AND);
		}
		
//		�����������Ʋ�ѯ
		if (this.getProcessDefName()!= null && this.getProcessDefName().trim().length()>0) {
			String whereSql = " WHERE DEFNAME='"+this.getProcessDefName()+"' ) ";
			String inSql = " ( SELECT PROCESSDEF_ID FROM BPM_PROCESSDEF ";
			Condition processC = new Condition(ConditionKey.PROCESSINST_PROCESSDEF_ID,
					Condition.IN, inSql + whereSql);
			getSubCondition().addCondition(processC, Condition.JOIN_AND);
		}
		
		
//		 ʱ��δ���
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
		//��ѯ�ɱ����ŷ���ȥ���ļ�
			HttpServletRequest request=ServletActionContext.getRequest();
			HttpSession session = request.getSession(true);
			String personId = session.getAttribute("personId").toString();
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String[] orgids = person.getOrgIds();
			Org org = OrgManagerFactory.getOrgManager().getOrgByID(orgids[0]);
			List userIds = org.getPersonIdList();
			//������������
			ctx.put("USERIDS", userIds);
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ALLWORK);
		condition.addOrderBy(new Order(
				ConditionKey.PROCESSINST_STARTTIME, false));
		List<ProcessInst> list = client.getProcessInstList(condition,
				null, ctx);
		
		/**
		 *-------------------------------------------- Ȩ�����ý���---------------------------------------
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
