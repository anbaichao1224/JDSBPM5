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
	Condition subCondition = null; // ---------------������״̬
	public String execute() throws Exception {
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		WorkflowClientService client = bpmUserClientUtil.getClient();

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
		int state = Integer.parseInt(this.state);
		switch (state) {
		case 0: // �ݸ�
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_MYWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_NOTSTARTED);
			break;
		case 1: // ���칤�������д�ǩ�յĹ���
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_WAITEDWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING); 
			break;
		case 2: // �ڰ칤����������ǩ��δ����Ĺ���
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_CURRENTWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING);
			break;
		case 3: // �ѹ鵵
			
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ALLWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_COMPLETED);
			break;
		case 4: // ���й���
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ALLWORK);
			break;
		case 5: // �ҵĹ������������еĴ��칤�����ڰ칤��
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_MYWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING);
			break;
		case 6: // �ȴ��İ�
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_READ);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING);
			break;
		case 7: // ���ı�
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ENDREAD);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING);
			break;
		case 8: //�Ѱ�
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION, OARightConstants.CONDITION_COMPLETEDWORK);
			cState = new Condition(ConditionKey.PROCESSINST_STATE,
					Condition.EQUALS, ProcessInst.STATE_RUNNING);
			break;
		default: // �Ƿ������й���
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
		
		//ָ������ID����
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
		
		//���⴦��
		if(status.equals("�ļ�����")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FILETITLE' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			//��Ų�ѯ
		}else if(status.equals("�ļ����")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='BH' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			
			//�ļ����Ͳ�ѯ
		}else if(status.equals("�ļ�����")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME=WJLX' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			
			//�ȼ���ѯ
		}else if(status.equals("�ļ��ȼ�")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='IMPENDING' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			//���ĵ�λ��ѯ
		}else if(status.equals("ԭ�ı��")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='YWBH' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			//���ĵ�λ��ѯ
		}else if(status.equals("���ı��")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='SWBH' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			//���ĵ�λ��ѯ
		}else if(status.equals("�����ܺ�")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FFZH' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
			//���ĵ�λ��ѯ
		}else if(status.equals("���ĵ�λ")){
			if (this.fileTitle != null && fileTitle.trim().length()>0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='LWDW' AND PROPVALUE like '%" + fileTitle + "%' ) ";
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						Condition.IN, inSql + whereSql);
				getSubCondition().addCondition(processC, Condition.JOIN_AND);
			}
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
