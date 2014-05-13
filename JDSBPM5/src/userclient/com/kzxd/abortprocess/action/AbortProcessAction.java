package com.kzxd.abortprocess.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kzxd.tixing.action.TiXingSaveDao;
import kzxd.tixing.action.TiXingSaveDaoImpl;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.support.GridUtils;
import net.itjds.worklist.list.support.rules.StartPersonRule;
import net.itjds.worklist.list.support.rules.StartTimeRule;
import net.itjds.worklist.list.support.rules.TimeLimitRule;

import org.apache.struts2.ServletActionContext;
import org.appfuse.webapp.action.BaseAction;

import com.kzxd.abortprocess.module.AbortProcessBean;
import com.kzxd.abortprocess.service.AbortProcessManager;
import com.kzxd.cpbl.module.BaseBean;
import com.kzxd.cpbl.module.CbBean;
import com.kzxd.cpbl.module.PbBean;

public class AbortProcessAction extends BaseAction {
	
	public void abort() throws BPMException{
		HttpServletRequest request = ServletActionContext.getRequest();
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		ActivityInst ai = bpmUserClientUtil.getClient().getActivityInst(activityInstId);
		String processInstId = ai.getProcessInstId();
		bpmUserClientUtil.getClient().abortProcessInst(processInstId, null);
		
		TiXingSaveDao tisaveDaoImpl = new TiXingSaveDaoImpl();
			tisaveDaoImpl.delete(activityInstId);
		
	}
	
	
	private AbortProcessBean getCbBean(ActivityInst ai) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String personId = request.getSession().getAttribute("personId").toString();
		String activityInstId = ai.getActivityInstId();
		String processInstId = ai.getProcessInstId();
		String processname = "";
		String fileTitle = "";
		String ngr = "";
		String ngsj = "";
		String status = "";
		String timeLimit = "";
		String personName = "";
		
		
		StartPersonRule startpersonRule = new StartPersonRule();
		ngr = startpersonRule.invoke(ai).toString();

		StartTimeRule starttimeRule = new StartTimeRule();
		ngsj = starttimeRule.invoke(ai).toString();
		
		
		status = ai.getState();
		TimeLimitRule timeRule = new TimeLimitRule();
		timeLimit = timeRule.invoke(ai).toString();
		AbortProcessBean cb = new AbortProcessBean();
		
		try {
			String title = ai.getProcessInst().getAttribute("fileTitle");
			if (title == null || title.equals("")) {
				fileTitle = ai.getProcessInst().getProcessDef().getName();
			} else {
				fileTitle = GridUtils.filterJsEnter(title);
			}
			fileTitle = fileTitle.replace("\n", "").replace("\r", "").replace("\"", "\\\"");
			
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			personName = person.getName();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			cb.setTitle(fileTitle);
			cb.setNgr(ngr);
			cb.setNgsj(sdf.parse(ngsj));
			cb.setSx(timeLimit);
			cb.setStatus(status);
			cb.setActivityinstid(activityInstId);
			cb.setProcessinstid(processInstId);
			cb.setArriveTime(ai.getArrivedTime());
			cb.setLastNodePerson(getLastNodePerson(ai));
			cb.setProcessname(processname);
			cb.setAbortname(personName);
			cb.setAbortdate(new Date());
		} catch (Exception e) {
			e.printStackTrace();
			cb = null;
		}finally{
			
			return cb;
		}
	}
	
	
	public String getLastNodePerson(ActivityInst ai) {
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		WorkflowClientService client = bpmUserClientUtil.getClient();
		String pname = "";
		try {
			List<ActivityInstHistory> list = ai.getActivityInstHistoryListByActvityInst();
			ActivityInstHistory his = list.get(list.size() - 1);
			List<Person> performers = (List<Person>) client
					.getActivityInstHistoryRightAttribute(
							his.getActivityHistoryId(),
							OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER,
							null);
			Person person = performers.get(0);
			pname = person.getName();
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pname;
	}
	
	private AbortProcessManager abortManager;
	
	
	private String activityInstId;

	public String getActivityInstId() {
		return activityInstId;
	}

	public void setActivityInstId(String activityInstId) {
		this.activityInstId = activityInstId;
	}

	public AbortProcessManager getAbortManager() {
		return abortManager;
	}

	public void setAbortManager(AbortProcessManager abortManager) {
		this.abortManager = abortManager;
	}
	
	
}
