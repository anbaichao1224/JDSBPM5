package net.itjds.userclient.bpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.WorkflowEngine;
import net.itjds.common.org.base.Person;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;

import org.apache.tools.ant.util.DateUtils;

import com.opensymphony.xwork2.ActionContext;

public class TakebackRouteAction  extends BPMActionBase{

	
	private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
	private int totalCount;
	//历史环节
	private String activityInstHistoryId;
	@SuppressWarnings("unchecked")
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if(this.client == null)
			this.client = this.bpmUserClientUtil.getClient();
		
		String hisId = this.getSelfHistoryId();
		ActivityInstHistory his = client.getActivityInstHistory(hisId);
			
		List<ActivityInst> insts = client.getActivityInstListByOutActvityInstHistory(hisId, null);
	
		totalCount = insts.size();
		for(int i=0; i<insts.size(); i++){
			Map<String, Object> map = new HashMap<String, Object>();
			ActivityInst inst = insts.get(i);
			if(!inst.isCanTakeBack()){
				continue;
			}
			if("suspended".equals(inst.getState())){
				continue;
			}
			String name = inst.getActivityDef().getName();
			System.out.println(name);
			map.put("name", name);
			map.put("arriveTime", DateUtils.format(inst.getArrivedTime(),
					"yyyy-MM-dd HH:mm:ss"));
			if(inst.getStartTime() != null)
				map.put("startTime", DateUtils.format(inst.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
			else
				map.put("startTime", "");
			map.put("status", this.getStatus(inst));
			List performers = (List)client.getActivityInstRightAttribute(inst.getActivityInstId(), OARightConstants.ACTIVITYINST_RIGHT_ATT_PERFORMER, null);
			System.out.println(inst.getActivityInstId());
			Person p = (Person) performers.get(0);
			String person = p.getName() + '/' + p.getOrgs()[0].getName();
			map.put("person", person);
			map.put("instId", inst.getActivityInstId());
			map.put("historyId", his.getActivityHistoryId());
			list.add(map);
		}
		
		return SUCCESS;
	}
	
	@SuppressWarnings("unchecked")
	private String getSelfHistoryId(){
		ActivityInstHistory retHis = null;
		//return this.getActivityInstHistory().getActivityHistoryId();
		BPMUserClientUtil bpmUserClientUtil =  new BPMUserClientUtil();
		WorkflowClientService client =  bpmUserClientUtil.getClient();
		
		try {
			
			ActivityInstHistory activityInstHistory = client.getActivityInstHistory(activityInstHistoryId);
			
			List historyList = client.getLastActivityInstHistoryListByActvityInst(activityInstHistory.getActivityInst().getActivityInstId(), null);
			//List historyList = client.getActivityInstHistoryListByProcessInst(activityInstHistory.getProcessInstId(), null);
			for(int i=0; i<historyList.size(); i++){
				ActivityInstHistory his = (ActivityInstHistory) historyList.get(i);
				if(his.getDealMethod()!=null && "SPLITED".equals(his.getDealMethod())){
					List performers =  (List) client.getActivityInstHistoryRightAttribute(his.getActivityHistoryId(),  OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER, null);
					if (performers.size()>0){
						Person p = (Person) performers.get(0);
						System.out.println("historyId=" + his.getActivityHistoryId() + " and performer=" + p.getName());
						Person currentPerson = (Person) ActionContext.getContext().getValueStack().findValue("$currPerson");
						if(currentPerson.getID().equals(p.getID()) && his.getActivityInst()!=null){
							retHis = his;
							//break;
						}
					}
				}
				
			}
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return retHis.getActivityHistoryId();
	}
	
	public void setActivityInstHistoryId(String activityInstHistoryId) {
		this.activityInstHistoryId = activityInstHistoryId;
	}
	public String getActivityInstHistoryId() {
		return activityInstHistoryId;
	}

	public String getStatus(ActivityInst activityInst)
	{
		String status="";
		if (activityInst.getState().equals(ActivityInst.STATUS_READ)){
			status="传阅";
		}else if(activityInst.getState().equals(ActivityInst.STATUS_ENDREAD)){
			status="阅毕";
		}else if(activityInst.getState().equals(ActivityInst.STATE_NOTSTARTED)){
			status="未阅";
		}else if(activityInst.getState().equals(ActivityInst.STATE_RUNNING)){
			status="正在办理";
		}
		return status;
	}
	public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}
	public List<Map<String, Object>> getList() {
		return list;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
}
