package net.itjds.worklist.list.action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.itjds.bpm.client.ActivityDef;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.ProcessInst;

import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.query.Condition;
import net.itjds.bpm.engine.query.ConditionKey;
import net.itjds.bpm.engine.query.Order;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.util.DateUtility;

import net.itjds.userclient.common.BPMUserClientUtil;

import org.apache.struts2.ServletActionContext;

import com.kzxd.db.action.OaBean;


@SuppressWarnings("unchecked")
public class BPMClientDataActivityBinding extends BPMClientBaseBinding {
	private String activityDefIds;
	private String processDefVersionIds;

	public String getActivityDefIds() {
		return activityDefIds;
	}

	public void setActivityDefIds(String activityDefIds) {
		this.activityDefIds = activityDefIds;
	}

	public String getProcessDefVersionIds() {
		return processDefVersionIds;
	}

	public void setProcessDefVersionIds(String processDefVersionIds) {
		this.processDefVersionIds = processDefVersionIds;
	}

	public String execute() throws Exception {
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		WorkflowClientService client = bpmUserClientUtil.getClient();
		/**
		 * ---------------------------------------------------权限设置开始---------------------------------------
		 */
		Condition mainCondition = new Condition(); // ---------------主条件状态
		Condition cState = null;
		Map ctx = new HashMap(); // ---------------权限状态
		if (ctype != null) {
			mainCondition = BPMUserClientUtil.getClassificationCondition(
					BPMUserClientUtil.CONDITION_TYPE_ACTIVITYINST, ctype);// 2009-11-13日修改为CONDITION_TYPE_ACTIVITYINST
		}

		int state = Integer.parseInt(this.state);
		switch (state) {
		case 0: // 草稿
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_CURRENTWORK);
			cState = new Condition(ConditionKey.ACTIVITYINST_PROCESSINST_ID,
					Condition.IN,
					"(SELECT PROCESSINST_ID FROM BPM_PROCESSINSTANCE WHERE PROCESSINST_STATE='"
							+ ProcessInst.STATE_NOTSTARTED + "' )");
			break;
		case 1: // 待办工作，所有待签收的公文
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_WAITEDWORK);
			cState = new Condition(ConditionKey.ACTIVITYINST_STATE,
					Condition.EQUALS, ActivityInst.STATE_NOTSTARTED);
			break;
		case 2: // 在办工作，所有已签收未办完的公文
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_CURRENTWORK);
			cState = new Condition(ConditionKey.ACTIVITYINST_STATE,
					Condition.EQUALS, ActivityInst.STATE_RUNNING);
			break;
		case 3: // 已办
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_COMPLETEDWORK);
			break;
		case 4: // 所有工作
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ALLWORK);
			break;
		case 5: // 我的工作，包括所有的待办工作和在办工作
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_MYWORK);
			cState = new Condition(ConditionKey.ACTIVITYINST_RUNSTATUS,
					Condition.NOT_EQUAL,
					ActivityInst.RUNSTATUS_PROCESSNOTSTARTED);
			break;
		case 6: // 等待阅办
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_READ);
			cState = new Condition(ConditionKey.ACTIVITYINST_STATE,
					Condition.EQUALS, ActivityInst.STATUS_READ);
			break;
		case 7: // 已阅必
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.ACTIVITYDEF_RIGHT_ATT_READERS);
			cState = new Condition(ConditionKey.ACTIVITYINST_STATE,
					Condition.EQUALS, ActivityInst.STATUS_ENDREAD);
			break;
		default: // 非法：所有工作
			ctx.put(OARightConstants.CTX_INSTANCE_CONDITION,
					OARightConstants.CONDITION_ALLWORK);
			break;
		}
		if (cState != null) {
			mainCondition.addCondition(cState, Condition.JOIN_AND);
		}

		String inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME ='USMTYPE' ) ";
		Condition c = new Condition(ConditionKey.ACTIVITYINST_PROCESSINST_ID,
				Condition.NOT_IN, inSql);
		mainCondition.addCondition(c, Condition.JOIN_AND);

		inSql = "( SELECT ACTIVITYINST_ID FROM BPM_ACTIVITYINST_PROPERTY  WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='ISREADED'  ) ";
		c = new Condition(ConditionKey.ACTIVITYINST_ID, Condition.NOT_IN, inSql);
		mainCondition.addCondition(c, Condition.JOIN_AND);

		Condition formCon = bpmUserClientUtil.getFormCondition(this
				.getMapdaoMap(), 0);
		if (formCon != null) {
			mainCondition.addCondition(formCon, Condition.JOIN_AND);
		}
		// 标题处理
		if (status.equals("文件标题")) {
			if (this.fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FILETITLE' AND PROPVALUE LIKE '%"
						+ fileTitle + "%' ) ";
				inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(
						ConditionKey.ACTIVITYINST_PROCESSINST_ID, Condition.IN,
						inSql + whereSql);
				mainCondition.addCondition(processC, Condition.JOIN_AND);
			}
			// 编号查询
		} else if (status.equals("文件编号")) {
			if (this.fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='BH' AND PROPVALUE LIKE '%"
						+ fileTitle + "%' ) ";
				inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(
						ConditionKey.ACTIVITYINST_PROCESSINST_ID, Condition.IN,
						inSql + whereSql);
				mainCondition.addCondition(processC, Condition.JOIN_AND);
			}

			// 文件类型查询
		} else if (status.equals("文件类型")) {
			if (this.fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='WJLX' AND PROPVALUE LIKE '%"
						+ fileTitle + "%' ) ";
				inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(
						ConditionKey.ACTIVITYINST_PROCESSINST_ID, Condition.IN,
						inSql + whereSql);
				mainCondition.addCondition(processC, Condition.JOIN_AND);
			}

			// 等级查询
		} else if (status.equals("文件等级")) {
			if (this.fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='IMPENDING' AND PROPVALUE like '%"
						+ fileTitle + "%' ) ";
				inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(
						ConditionKey.ACTIVITYINST_PROCESSINST_ID, Condition.IN,
						inSql + whereSql);
				mainCondition.addCondition(processC, Condition.JOIN_AND);
			}
			// 来文单位查询
		} else if (status.equals("原文编号")) {
			if (this.fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='YWBH' AND PROPVALUE LIKE '%"
						+ fileTitle + "%' ) ";
				inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(
						ConditionKey.ACTIVITYINST_PROCESSINST_ID, Condition.IN,
						inSql + whereSql);
				mainCondition.addCondition(processC, Condition.JOIN_AND);
			}
			// 来文单位查询
		} else if (status.equals("收文编号")) {
			if (this.fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='SWBH' AND PROPVALUE LIKE '%"
						+ fileTitle + "%' ) ";
				inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(
						ConditionKey.ACTIVITYINST_PROCESSINST_ID, Condition.IN,
						inSql + whereSql);
				mainCondition.addCondition(processC, Condition.JOIN_AND);
			}
			// 来文单位查询
		} else if (status.equals("发方总号")) {
			if (this.fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FFZH' AND PROPVALUE LIKE '%"
						+ fileTitle + "%' ) ";
				inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(
						ConditionKey.ACTIVITYINST_PROCESSINST_ID, Condition.IN,
						inSql + whereSql);
				mainCondition.addCondition(processC, Condition.JOIN_AND);
			}
			// 来文单位查询
		} else if (status.equals("来文单位")) {
			if (this.fileTitle != null && fileTitle.trim().length() > 0) {
				String whereSql = " WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='LWDW' AND PROPVALUE LIKE '%"
						+ fileTitle + "%' ) ";
				inSql = " ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY ";
				Condition processC = new Condition(
						ConditionKey.ACTIVITYINST_PROCESSINST_ID, Condition.IN,
						inSql + whereSql);
				mainCondition.addCondition(processC, Condition.JOIN_AND);
			}
		}
		// 指定流程ID过滤
		if (this.getProcessDefVersionIds() != null
				&& !this.getProcessDefVersionIds().equals("")) {
			StringBuffer processDefIdsStr = new StringBuffer();
			String[] processDefIds = getProcessDefVersionIds().split(",");
			if (getProcessDefVersionIds().startsWith("!")) {
				String fristProcessDefId = processDefIds[0].substring(1,
						processDefIds[0].length());
				processDefIds[0] = fristProcessDefId;
			}
			Condition processDefCon = null;
			for (int i = 0; i < processDefIds.length; i++) {
				processDefIdsStr.append("'");
				processDefIdsStr.append(processDefIds[i]);
				processDefIdsStr.append("'");
				if (i + 1 < processDefIds.length)
					processDefIdsStr.append(",");
			}

			if (getProcessDefVersionIds().startsWith("!")) {
				processDefCon = new Condition(
						ConditionKey.ACTIVITYINST_PROCESSDEF_ID,
						Condition.NOT_IN, "(" + processDefIdsStr.toString()
								+ " )");
			} else {
				processDefCon = new Condition(
						ConditionKey.ACTIVITYINST_PROCESSDEF_ID, Condition.IN,
						"(" + processDefIdsStr.toString() + " )");
			}
			mainCondition.addCondition(processDefCon, Condition.JOIN_AND);
		}

		// 加上指定活动定义
		if (this.getActivityDefIds() != null
				&& !this.getActivityDefIds().equals("")) {
			StringBuffer activityDefId = new StringBuffer();
			Condition actvityDefCon = null;
			String[] activityDefIds = getActivityDefIds().split(",");
			if (getActivityDefIds().startsWith("!")) {
				activityDefIds[0] = activityDefIds[0].substring(1,
						activityDefIds[0].length());
			}
			for (int i = 0; i < activityDefIds.length; i++) {
				activityDefId.append("'");
				activityDefId.append(activityDefIds[i]);
				activityDefId.append("'");
				if (i + 1 < activityDefIds.length)
					activityDefId.append(",");
			}
			if (getActivityDefIds().startsWith("!")) {
				actvityDefCon = new Condition(ConditionKey.ACTIVITYINST_ID,
						Condition.NOT_IN, "(" + activityDefId.toString() + " )");
			} else {
				actvityDefCon = new Condition(ConditionKey.ACTIVITYINST_ID,
						Condition.IN, "(" + activityDefId.toString() + " )");
			}
			mainCondition.addCondition(actvityDefCon, Condition.JOIN_AND);
		}

		// 时间段处理
		if (this.startTime != null) {
			if (endTime == null) {
				endTime = new Date();
			}
			Collection timeList = new ArrayList();
			timeList.add(startTime);
			timeList.add(this.endTime);
			Condition timec = new Condition(
					ConditionKey.ACTIVITYINST_ARRIVEDTIME, Condition.BETWEEN,
					timeList);
			mainCondition.addCondition(timec, Condition.JOIN_AND);
		}

		mainCondition.addOrderBy(new Order(
				ConditionKey.ACTIVITYINST_ARRIVEDTIME, false));
		List<ActivityInst> list = client.getActivityInstList(mainCondition,
				null, ctx);
		/**
		 * --------------------------------------------
		 * 权限设置结束---------------------------------------
		 */
		List<ActivityInst> list1 = new ArrayList<ActivityInst>();
		Map<String, ActivityInst> map = new HashMap<String, ActivityInst>();
		for (int i = (list.size() - 1); i >= 0; i--) {
			ActivityInst inst = list.get(i);
			String State = inst.getState();
			String activityInstId = inst.getActivityInstId();
			String processInstId = inst.getProcessInstId();
				if (State.equals("running") || State.equals("notStarted")) {
					ActivityInst activityInst = client
					.getActivityInst(activityInstId);
					String ActivityName = activityInst.getActivityDef().getName();
					String cantakeback = inst.getCanTakeBack();
					
					if(ActivityName.equals("拟稿人")){
						
						if(cantakeback!=null&&cantakeback.equals("YES")){
							map.put(inst.getProcessInstId(), inst);
						}else if(cantakeback!=null&&cantakeback.equals("NO")){
							String activitydef = inst.getActivityDefId();
							boolean flag =this.flag(processInstId, activityInstId,activitydef);
							if(flag){
								map.put(inst.getProcessInstId(), inst);
							}
						}else{
							map.put(inst.getProcessInstId(), inst);
						}
						
					}else{
						map.put(inst.getProcessInstId(), inst);
					}
						
					}	

		}
		list1.addAll(map.values());
		Collections.sort(list1, new Comparator<ActivityInst>() {
			public int compare(ActivityInst arg0, ActivityInst arg1) {
				//				 
				return arg1.getArrivedTime().compareTo(arg0.getArrivedTime());
			}
		});

		StringBuffer json = InjectActivityInstData(list1);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.append(json.toString());
		pw.flush();
		return null;
	}
	public boolean flag(String processInstId,String actityInstId,String activitydef) {
		DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean flag=false;
		dbbase = new DBBeanBase("bpm", true);
		conn = dbbase.getConn();
		String sql = "select * from bpm_activityinstance where processinst_id='"+processInstId+"' and  activityinst_id!='"+actityInstId+"'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			int i=0;
			while (rs.next()) {
				if(rs.getString("activityinst_state").equals("notStarted")){
					if(rs.getString("starttime")!=null&&!rs.getString("starttime").equals("")){
						if(rs.getString("activitydef_id").equals(activitydef)){
							i++;
						}
						
					}
				}else if(rs.getString("activityinst_state").equals("suspended")){
					if(rs.getString("starttime")!=null&&!rs.getString("starttime").equals("")){
						i++;
					}
				}
			}
		int k = this.copynumber(processInstId, actityInstId);
		int b= i+1;
		if(k==b){
			flag = true;
		}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return flag;

	}
	public int copynumber(String processInstId,String actityInstId) {
		DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
		conn = dbbase.getConn();
		int i=0;
		String sql = "select * from bpm_processinstance where processinst_id='"+processInstId+"'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				String a = String.valueOf(rs.getInt("copynumber"));
				if(a!=null&&!a.equals("")){
					i=rs.getInt("copynumber");
				}
				
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return i;

	}
}
