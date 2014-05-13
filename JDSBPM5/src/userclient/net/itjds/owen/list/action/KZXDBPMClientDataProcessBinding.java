/**
 * @author liyang
 * @version v1.0
 * ���Ӵ������ڽ���鵵��Ϣ�޷����հ汾�ſ���
 */

package net.itjds.owen.list.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.query.Condition;
import net.itjds.bpm.engine.query.ConditionKey;
import net.itjds.bpm.engine.query.Order;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.action.BPMClientBaseBinding;

import org.apache.struts2.ServletActionContext;

public class KZXDBPMClientDataProcessBinding extends BPMClientBaseBinding {

	public KZXDBPMClientDataProcessBinding() {
		subCondition = null;
	}

	public String execute() throws Exception {
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		WorkflowClientService client = bpmUserClientUtil.getClient();
		Condition condition = new Condition();
		Condition mainCondition = new Condition();
		Condition cState = null;
		Map ctx = new HashMap();
		if (ctype != null)
			mainCondition = BPMUserClientUtil.getClassificationCondition(4,
					ctype);
		int state = Integer.parseInt(this.state);
		switch (state) {
		case 0: // '\0'
			ctx.put("OARightConstants.INSTANCE_CONDITION", "CONDITION_MYWORK");
			cState = new Condition(ConditionKey.PROCESSINST_STATE, 1,
					"notStarted");
			break;

		case 1: // '\001'
			ctx.put("OARightConstants.INSTANCE_CONDITION",
					"CONDITION_WAITEDWORK");
			cState = new Condition(ConditionKey.PROCESSINST_STATE, 1, "running");
			break;

		case 2: // '\002'
			ctx.put("OARightConstants.INSTANCE_CONDITION",
					"CONDITION_CURRENTWORK");
			cState = new Condition(ConditionKey.PROCESSINST_STATE, 1, "running");
			break;

		case 3: // '\003'
			ctx.put("OARightConstants.INSTANCE_CONDITION", "CONDITION_ALLWORK");
			cState = new Condition(ConditionKey.PROCESSINST_STATE, 1,
					"completed");
			break;

		case 4: // '\004'
			ctx.put("OARightConstants.INSTANCE_CONDITION", "CONDITION_ALLWORK");
			break;

		case 5: // '\005'
			ctx.put("OARightConstants.INSTANCE_CONDITION", "CONDITION_MYWORK");
			cState = new Condition(ConditionKey.PROCESSINST_STATE, 1, "running");
			break;

		case 6: // '\006'
			ctx.put("OARightConstants.INSTANCE_CONDITION", "CONDITION_READ");
			cState = new Condition(ConditionKey.PROCESSINST_STATE, 1, "running");
			break;

		case 7: // '\007'
			ctx.put("OARightConstants.INSTANCE_CONDITION", "CONDITION_ENDREAD");
			cState = new Condition(ConditionKey.PROCESSINST_STATE, 1, "running");
			break;

		case 8: // '\b'
			ctx.put("OARightConstants.INSTANCE_CONDITION",
					"CONDITION_COMPLETEDWORK");
			cState = new Condition(ConditionKey.PROCESSINST_STATE, 1, "running");
			break;

		default:
			ctx.put("OARightConstants.INSTANCE_CONDITION", "CONDITION_ALLWORK");
			break;
		}
		if (cState != null)
			mainCondition.addCondition(cState, 100);
		cState = new Condition(ConditionKey.PROCESSDEF_ID, 1, processDeId);
		mainCondition.addCondition(cState, 100);

		/**
		 * ��ԭ�л���������106-123�������� ���������İ汾ID��Ϊ���� ���в�ѯ����
		 */
		if (getProcessDefVersionIds() != null
				&& !getProcessDefVersionIds().equals("")) {
			StringBuffer processDefIdsStr = new StringBuffer();
			String processDefIds[] = getProcessDefVersionIds().split(",");
			for (int i = 0; i < processDefIds.length; i++) {
				processDefIdsStr.append("'");
				processDefIdsStr.append(processDefIds[i]);
				processDefIdsStr.append("'");
				if (i + 1 < processDefIds.length)
					processDefIdsStr.append(",");
			}
			String whereSql = (new StringBuilder(
					" where PROCESSDEF_VERSION_ID in (")).append(
					processDefIdsStr.toString()).append(" ))").toString();
			String inSql = " ( select processinst_id from BPM_PROCESSINSTANCE";
			Condition processDefCon = new Condition(
					ConditionKey.PROCESSINST_ID, 10, (new StringBuilder(String
							.valueOf(inSql))).append(whereSql).toString());
			mainCondition.addCondition(processDefCon, 100);
		}

		if (getActivityDefId() != null && !getActivityDefId().equals("")) {
			StringBuffer activityDefId = new StringBuffer();
			String activityDefIds[] = getActivityDefId().split(",");
			for (int i = 0; i < activityDefIds.length; i++) {
				activityDefId.append("'");
				activityDefId.append(activityDefIds[i]);
				activityDefId.append("'");
				if (i + 1 < activityDefIds.length)
					activityDefId.append(",");
			}

			String whereSql = (new StringBuilder(" where activitydef_id in ("))
					.append(activityDefId.toString()).append(" ))").toString();
			String inSql = " ( select processinst_id from bpm_activityinstance";
			Condition actvityDefCon = new Condition(
					ConditionKey.PROCESSINST_ID, 10, (new StringBuilder(String
							.valueOf(inSql))).append(whereSql).toString());
			mainCondition.addCondition(actvityDefCon, 101);
		}
		Condition formCon = bpmUserClientUtil.getFormCondition(getMapdaoMap(),
				1);
		if (formCon != null)
			getSubCondition().addCondition(formCon, 100);
		if (status.equals("�ļ�����")) {
			if (fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = (new StringBuilder(
						" WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FILETITLE' AND PROPVALUE like '%"))
						.append(fileTitle).append("%' ) ").toString();
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						10, (new StringBuilder(String.valueOf(inSql))).append(
								whereSql).toString());
				getSubCondition().addCondition(processC, 100);
			}
		} else if (status.equals("�ļ����")) {
			if (fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = (new StringBuilder(
						" WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='BH' AND PROPVALUE like '%"))
						.append(fileTitle).append("%' ) ").toString();
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						10, (new StringBuilder(String.valueOf(inSql))).append(
								whereSql).toString());
				getSubCondition().addCondition(processC, 100);
			}
			// �ļ����Ͳ�ѯ
		} else if (status.equals("�ļ�����")) {
			if (fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = (new StringBuilder(
						" WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='WJLX' AND PROPVALUE like '%"))
						.append(fileTitle).append("%' ) ").toString();
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						10, (new StringBuilder(String.valueOf(inSql))).append(
								whereSql).toString());
				getSubCondition().addCondition(processC, 100);
			}

			// �ȼ���ѯ
		} else if (status.equals("�ļ��ȼ�")) {

			if (fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = (new StringBuilder(
						" WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='IMPENDING' AND PROPVALUE like '%"))
						.append(fileTitle).append("%' ) ").toString();
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						10, (new StringBuilder(String.valueOf(inSql))).append(
								whereSql).toString());
				getSubCondition().addCondition(processC, 100);
			}
			// ���ĵ�λ��ѯ
		}else if (status.equals("ԭ�ı��")) {

			if (fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = (new StringBuilder(
						" WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='YWBH' AND PROPVALUE like '%"))
						.append(fileTitle).append("%' ) ").toString();
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						10, (new StringBuilder(String.valueOf(inSql))).append(
								whereSql).toString());
				getSubCondition().addCondition(processC, 100);
			}
			// ���ĵ�λ��ѯ
		}else if (status.equals("���ı��")) {

			if (fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = (new StringBuilder(
						" WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='SWBH' AND PROPVALUE like '%"))
						.append(fileTitle).append("%' ) ").toString();
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						10, (new StringBuilder(String.valueOf(inSql))).append(
								whereSql).toString());
				getSubCondition().addCondition(processC, 100);
			}
			// ���ĵ�λ��ѯ
		} else if (status.equals("�����ܺ�")) {

			if (fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = (new StringBuilder(
						" WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FFZH' AND PROPVALUE like '%"))
						.append(fileTitle).append("%' ) ").toString();
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						10, (new StringBuilder(String.valueOf(inSql))).append(
								whereSql).toString());
				getSubCondition().addCondition(processC, 100);
			}
			// ���ĵ�λ��ѯ
		}else if (status.equals("���ĵ�λ")) {
			if (fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = (new StringBuilder(
						" WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='LWDW' AND PROPVALUE like '%"))
						.append(fileTitle).append("%' ) ").toString();
				String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(ConditionKey.PROCESSINST_ID,
						10, (new StringBuilder(String.valueOf(inSql))).append(
								whereSql).toString());
				getSubCondition().addCondition(processC, 100);
			}
		}
		if (getProcessDefName() != null
				&& getProcessDefName().trim().length() > 0) {
			String whereSql = (new StringBuilder(" WHERE DEFNAME='")).append(
					getProcessDefName()).append("' ) ").toString();
			String inSql = " ( SELECT PROCESSDEF_ID FROM BPM_PROCESSDEF ";
			Condition processC = new Condition(
					ConditionKey.PROCESSINST_PROCESSDEF_ID, 10,
					(new StringBuilder(String.valueOf(inSql))).append(whereSql)
							.toString());
			getSubCondition().addCondition(processC, 100);
		}
		if (startTime != null) {
			if (endTime == null)
				endTime = new Date();
			Collection timeList = new ArrayList();
			timeList.add(startTime);
			timeList.add(endTime);
			Condition timec = new Condition(ConditionKey.PROCESSINST_STARTTIME,
					12, timeList);
			getSubCondition().addCondition(timec, 100);
		}
		if (subCondition != null)
			condition.addCondition(subCondition, 100);
		condition.addCondition(mainCondition, 100);
		condition.addOrderBy(new Order(ConditionKey.PROCESSINST_STARTTIME,
				false));
		java.util.List list = client.getProcessInstList(condition, null, ctx);
		StringBuffer json = InjectProcessInstData(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=gbk");
		PrintWriter pw = response.getWriter();
		pw.append(json.toString());
		pw.flush();
		return null;
	}

	public Condition getSubCondition() {
		if (subCondition == null)
			subCondition = new Condition();
		return subCondition;
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

	public String getProcessDefVersionIds() {
		return processDefVersionIds;
	}

	public void setProcessDefVersionIds(String processDefVersionIds) {
		this.processDefVersionIds = processDefVersionIds;
	}

	private String activityDefId;
	public String processDefName;
	public String processDeId;

	/**
	 * ��������ʵ�а汾ID ��get��set����
	 */
	public String processDefVersionIds;
	Condition subCondition;

}
