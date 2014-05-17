package net.itjds.userclient.bpm.listener;

import java.text.SimpleDateFormat;
import java.util.List;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.ReturnType;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.support.GridUtils;
import net.itjds.worklist.list.support.rules.FileTitleRule;
import net.itjds.worklist.list.support.rules.StartPersonRule;
import net.itjds.worklist.list.support.rules.StartTimeRule;
import net.itjds.worklist.list.support.rules.StatusRule;
import net.itjds.worklist.list.support.rules.TimeLimitRule;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.kzxd.cpbl.module.BaseBean;
import com.kzxd.cpbl.module.CbBean;
import com.kzxd.cpbl.module.PbBean;
import com.kzxd.cpbl.service.BaseBeanManager;
import com.kzxd.cpbl.service.CbManager;
import com.kzxd.cpbl.service.PbManager;

public class DrblActivityListener implements ActivityListener {

	CbManager cbManager;

	public void activityActived(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub
	}

	public void activityActiving(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityCompleted(ActivityEvent event) throws BPMException {
		 ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
		 BaseBeanManager bbManager = (BaseBeanManager)ac2.getBean("bbManager");
		
		String processInstId = event.getActivityInsts()[0].getProcessInstId();
		 BaseBean bb = bbManager.findByProcessInstId(processInstId);
		 if(bb!=null){
			 bbManager.delete(bb);
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

	public void activityJoined(ActivityEvent event) throws BPMException {
	}

	public void activityJoining(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityOutFlowed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityOutFlowing(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityOutFlowReturned(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityOutFlowReturning(ActivityEvent arg0)
			throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityResumed(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityResuming(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

	public void activityRouted(ActivityEvent event) throws BPMException {
		if (event.getActivityInsts().length > 0) {
			 String processInstId = event.getActivityInsts()[0].getProcessInstId();
			 ApplicationContext ac2 = WebApplicationContextUtils.getWebApplicationContext(ServletActionContext.getServletContext());
			 BaseBeanManager bbManager = (BaseBeanManager)ac2.getBean("bbManager");
			 BaseBean bb = bbManager.findByProcessInstId(processInstId);
			boolean flag = true;
			for (ActivityInstHistory ah : event.getActivityInsts()[0].getActivityInstHistoryListByActvityInst()) {
				if (ah.getActivityDef().getName().equals("单人承办")||ah.getActivityDef().getName().equals("多人承办")) {
					flag = false;
					cbManager = (CbManager) ac2.getBean("cbManager");
					CbBean cb = (CbBean)getCbBean(event.getActivityInsts()[0],"CB");
					if(bb!=null){
						if(bb instanceof CbBean){
							if(cb!=null){
								cb.setUuid(bb.getUuid());
								cbManager.save(cb);
							}
						}else if(bb instanceof PbBean){
							bbManager.delete(bb);
							cbManager.save(cb);
						}
						
						
					}else{
						if(cb!=null){
							cbManager.save(cb);
						}
						
					}
					
					
				}

			}
			if(flag){//没有承办、多人承办中只有一人办理过
				PbManager pbManager = (PbManager) ac2.getBean("pbManager");
				cbManager = (CbManager) ac2.getBean("cbManager");
				if(bb!=null){
					if(bb instanceof CbBean){
						CbBean cb = (CbBean)getCbBean(event.getActivityInsts()[0],"CB");
						cb.setUuid(bb.getUuid());
						cbManager.save(cb);
					}else{
						PbBean pb = (PbBean)getCbBean(event.getActivityInsts()[0],"PB");
						pb.setUuid(bb.getUuid());
						pbManager.save(pb);
					}
					
				}else{
					PbBean pb = (PbBean)getCbBean(event.getActivityInsts()[0],"PB");
					pbManager.save(pb);
				}
				
			}
			
		}

	}


	private BaseBean getCbBean(ActivityInst ai,String filetype) {
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
		
		
		status = ai.getState();
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
				impending = "普通";
			}else if(impending != null && impending.equals("1")){
				impending = "<span style='color:red'>急!!</span>";
			}else{
				impending = "普通";
			}
			cb.setImpending(impending);
			cb.setLastNodePerson(getLastNodePerson(activityInstId));
			cb.setProcessname(processname);
		} catch (Exception e) {
			e.printStackTrace();
			cb = null;
		}finally{
			
			return cb;
		}
	}
	
	//(2014-05-16)获取list  （hcl）
	public String getLastNodePerson(String activityInstId) {
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		WorkflowClientService client = bpmUserClientUtil.getClient();
		String pname = "";
		try {
			List<ActivityInstHistory> list = client.getLastActivityInstHistoryListByActvityInst(activityInstId, null);
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
		// TODO Auto-generated method stub

	}

	public void activityTakebacking(ActivityEvent arg0) throws BPMException {
		// TODO Auto-generated method stub

	}

}
