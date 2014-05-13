package net.itjds.userclient.bpm.listener;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kzxd.cpbl.module.BaseBean;
import com.kzxd.cpbl.module.CbBean;
import com.kzxd.cpbl.module.PbBean;
import com.kzxd.cpbl.service.CbManager;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.org.base.Person;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.support.GridUtils;
import net.itjds.worklist.list.support.rules.StartPersonRule;
import net.itjds.worklist.list.support.rules.StartTimeRule;
import net.itjds.worklist.list.support.rules.TimeLimitRule;

public class DrblActivityListener2 implements ActivityListener{

	CbManager cbManager;
	
	public void activityActived(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityActiving(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

	public void activityCompleted(ActivityEvent event) throws BPMException {
		 int activityInstIdSize=event.getActivityInsts().length;
		 ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		 cbManager = (CbManager) ac2.getBean("cbManager");
		 for(int i=0;i<activityInstIdSize;i++){
			 String processInstId = event.getActivityInsts()[i].getProcessInstId();
			 String activityInstId = event.getActivityInsts()[i].getActivityInstId();
			 
			 CbBean cbb=cbManager.select(processInstId, activityInstId);
			 if(cbb!=null){
				 cbb.setStatus("completed");
				 cbManager.update(cbb);
			 }
		 }
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

	public void activityRouted(ActivityEvent event) throws BPMException {

		ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		
		String processInstId=event.getActivityInsts()[0].getProcessInstId();
		String activityInstId = event.getActivityInsts()[0].getActivityInstId();
		cbManager = (CbManager) ac2.getBean("cbManager");
		CbBean cbb=cbManager.select(processInstId, activityInstId);
		
		if(cbb==null){
			CbBean cb = (CbBean)getCbBean(event.getActivityInsts()[0],"CB","suspended");
			cbManager.save(cb);			
		}
	}


	private BaseBean getCbBean(ActivityInst ai,String filetype,String zt) {
		String activityInstId = ai.getActivityInstId();
		String processInstId = ai.getProcessInstId();
		String processname = "";
		String fileTitle = "";
		String ngr = "";
		String ngsj = "";
		String status = "";
		String timeLimit = "";
		
		StartPersonRule startpersonRule = new StartPersonRule();
		ngr = startpersonRule.invoke(ai).toString();

		StartTimeRule starttimeRule = new StartTimeRule();
		ngsj = starttimeRule.invoke(ai).toString();
		
		
		status = zt;
		TimeLimitRule timeRule = new TimeLimitRule();
		timeLimit = timeRule.invoke(ai).toString();
		BaseBean cb = null;
		if(filetype.equals("CB")){
			cb = new CbBean();
		}else{
			cb = new PbBean();
		}
		
		
		try {
			String title = ai.getProcessInst().getAttribute("fileTitle");
			if (title == null || title.equals("")) {
				fileTitle = ai.getProcessInst().getProcessDef().getName();
			} else {
				fileTitle = GridUtils.filterJsEnter(title);
			}
			fileTitle = fileTitle.replace("\n", "").replace("\r", "").replace("\"", "\\\"");
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			cb.setTitle(fileTitle);
			cb.setNgr(ngr);
			cb.setNgsj(sdf.parse(ngsj));
			cb.setSx(timeLimit);
			cb.setStatus(status);
			cb.setActivityinstid(activityInstId);
			cb.setProcessinstid(processInstId);
			cb.setArriveTime(ai.getArrivedTime());
			String impending = ai.getProcessInst().getAttribute("huanJi");
			if(impending != null && impending.equals("0")){
				impending = "∆’Õ®";
			}else if(impending != null && impending.equals("1")){
				impending = "<span style='color:red'>º±!!</span>";
			}else{
				impending = "∆’Õ®";
			}
			cb.setImpending(impending);
			cb.setLastNodePerson(getLastNodePerson(ai));
			cb.setProcessname(processname);
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
			List<ActivityInstHistory> list = client.getActivityInstHistoryListByActvityInst(ai.getActivityInstId(), null);//ai.getActivityInstHistoryListByActvityInst();
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
		
	}

	public void activityTakebacking(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
		
	}

}
