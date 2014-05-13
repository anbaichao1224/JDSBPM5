package net.itjds.userclient.bpm.listener;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kzxd.db.action.OaBean;

import com.opensymphony.xwork2.ActionContext;

import kzxd.tixing.action.TiXingAction;
import kzxd.tixing.dao.TiXingBean;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.bpm.engine.OARightConstants;

public class TiXingListener implements ActivityListener {

	private TiXingBean txbean;
	private TiXingAction txAction;

	public void activityActived(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityActiving(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityCompleted(ActivityEvent arg0) throws BPMException {
		arg0.getActivityInsts();
		// C0E04360-622D-11E3-8360-F2F0FE0F2FDB
		// C1129E00-622D-11E3-9E00-DB279336EDA1
		String activityInstId = arg0.getActivityInsts()[0].getActivityInstId();
		txAction = new TiXingAction();
		txAction.delete(activityInstId);
	}

	public void activityCompleting(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityDisplay(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityFormSaveed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityFormSaveing(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityInited(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityJoined(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityJoining(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityOutFlowReturned(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityOutFlowReturning(ActivityEvent arg0)
			throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityOutFlowed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityOutFlowing(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityResumed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityResuming(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	public void activityRouted(ActivityEvent arg0) throws BPMException {
		String mkname = arg0.getActivityInsts()[0].getProcessInst()
				.getProcessDef().getName();
		String activityInstId = arg0.getActivityInsts()[0].getActivityInstId();

		List<Person> performers = (List<Person>) arg0
				.getClientService()
				.getActivityInstRightAttribute(activityInstId,
						OARightConstants.ACTIVITYINST_RIGHT_ATT_PERFORMER, null);
		for (int i = 0; i < performers.size(); i++) {

			Person person = performers.get(i);
			String personid = person.getID();
			String state = arg0.getActivityInsts()[0].getState();
			String cantakeback = arg0.getActivityInsts()[0].getCanTakeBack();
			String provessinstID = arg0.getActivityInsts()[0]
					.getProcessInstId();
			String dealmethod = arg0.getActivityInsts()[0].getDealMethod();
			if (state.equals("suspended")) {

			} else {
				if (cantakeback != null) {
					if (cantakeback.equals("NO") && dealmethod.equals("NORMAL")) {
						boolean flag = this.OADw(provessinstID);
						if (flag) {
							txbean = new TiXingBean();
							txbean.setPersonid(personid);
							txbean.setMkname(mkname);
							txbean.setTime(new Date());
							txbean.setDelid(activityInstId);
							txAction = new TiXingAction();
							txAction.save(txbean);
							
						}
					} else {
						txbean = new TiXingBean();
						txbean.setPersonid(personid);
						txbean.setMkname(mkname);
						txbean.setTime(new Date());
						txbean.setDelid(activityInstId);
						txAction = new TiXingAction();
						txAction.save(txbean);
						
					}
				} else {
					txbean = new TiXingBean();
					txbean.setPersonid(personid);
					txbean.setMkname(mkname);
					txbean.setTime(new Date());
					txbean.setDelid(activityInstId);
					txAction = new TiXingAction();
					txAction.save(txbean);
					
				}

			}
		}

	}

	public void activityRouting(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activitySplited(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activitySpliting(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activitySuspended(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activitySuspending(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityTakebacked(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		String activityInstId = arg0.getActivityInsts()[0].getActivityInstId();
		System.out.println("activityInstId=" + activityInstId);
		txAction = new TiXingAction();
		txAction.delete(activityInstId);
		String activityInstHistoryId = (String) ServletActionContext
				.getRequest().getParameter("activityInstHistoryId");
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		WorkflowClientService client = bpmUserClientUtil.getClient();
		String hisId = this.getSelfHistoryId(activityInstHistoryId);
		if (hisId == null) {
			String mkname = arg0.getActivityInsts()[0].getProcessInst()
					.getProcessDef().getName();

			String state = arg0.getActivityInsts()[0].getState();
			if (state.equals("suspended")) {

			} else {
				ActivityInst aaa = arg0.getActivityInsts()[0].getProcessInst()
						.getActivityInstList().get(0);
				String bbb = aaa.getActivityInstId();
				HttpServletRequest request = ServletActionContext.getRequest();
				HttpSession session = request.getSession(true);
				String personid = session.getAttribute("personId").toString();
				txbean = new TiXingBean();
				txbean.setPersonid(personid);
				txbean.setMkname(mkname);
				txbean.setTime(new Date());
				txbean.setDelid(bbb);
				txAction = new TiXingAction();
				txAction.save(txbean);
				
			}
		}
	}

	public void activityTakebacking(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public TiXingBean getTxbean() {
		return txbean;
	}

	public void setTxbean(TiXingBean txbean) {
		this.txbean = txbean;
	}

	public TiXingAction getTxAction() {
		return txAction;
	}

	public void setTxAction(TiXingAction txAction) {
		this.txAction = txAction;
	}

	@SuppressWarnings("unchecked")
	private String getSelfHistoryId(String activityInstHistoryId) {
		ActivityInstHistory retHis = null;
		// return this.getActivityInstHistory().getActivityHistoryId();
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		WorkflowClientService client = bpmUserClientUtil.getClient();

		try {

			ActivityInstHistory activityInstHistory = client
					.getActivityInstHistory(activityInstHistoryId);

			List historyList;
			try {
				activityInstHistory.getActivityInst().getActivityInstId();
				historyList = client
						.getLastActivityInstHistoryListByActvityInst(
								activityInstHistory.getActivityInst()
										.getActivityInstId(), null);
			} catch (Exception e) {
				return null;
				// TODO: handle exception
			}
			// List historyList =
			// client.getActivityInstHistoryListByProcessInst(activityInstHistory.getProcessInstId(),
			// null);
			for (int i = 0; i < historyList.size(); i++) {
				ActivityInstHistory his = (ActivityInstHistory) historyList
						.get(i);
				if (his.getDealMethod() != null
						&& "SPLITED".equals(his.getDealMethod())) {
					List performers = (List) client
							.getActivityInstHistoryRightAttribute(
									his.getActivityHistoryId(),
									OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER,
									null);
					if (performers.size() > 0) {
						Person p = (Person) performers.get(0);
						System.out.println("historyId="
								+ his.getActivityHistoryId()
								+ " and performer=" + p.getName());
						Person currentPerson = (Person) ActionContext
								.getContext().getValueStack().findValue(
										"$currPerson");
						if (currentPerson.getID().equals(p.getID())
								&& his.getActivityInst() != null) {
							retHis = his;
							// break;
						}
					}
				}

			}
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		return retHis.getActivityHistoryId();
	}

	public boolean OADw(String provessinstID) {
		DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean flag = false;
		dbbase = new DBBeanBase("bpm", true);
		conn = dbbase.getConn();
		String sql = "select * from bpm_activityinstance where processinst_id= '"
				+ provessinstID + "'";
		try {
			pst = conn.prepareStatement(sql);

			rs = pst.executeQuery();
			int i = 0;
			int h = 0;
			while (rs.next()) {
				h++;
				System.out.println(rs.getString("activityinst_state"));
				if (rs.getString("activityinst_state").equals("notStarted")) {
					String starttime = rs.getString("starttime");
					if (starttime != null && !starttime.equals("")) {
						i++;
					}

				}

			}
			int g = h - i;
			if (g == 1) {
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
}
