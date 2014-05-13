package net.itjds.userclient.list;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.itjds.bpm.client.ActivityDef;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.org.base.Person;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.support.annotation.ColumnDefine;
import net.itjds.worklist.list.support.annotation.FrameDefine;
import net.itjds.worklist.list.support.annotation.enums.ElementAlign;
import net.itjds.worklist.list.support.annotation.enums.ElementButton;
import net.itjds.worklist.list.support.annotation.enums.ElementTbar;

@EsbBeanAnnotation(id = "AllWaitList", name = "所有待办列表", expressionArr = "AllWaitList()", desc = "所有待办列表", dataType = "action")
@FrameDefine(title = "", height = 400, width = 600, tbar = ElementTbar.commonQuery, buttons = { ElementButton.none }, hasRowNumber = true, hasCheckBox = false, buttonAlign = ElementAlign.center, url = "bpmDataBind.action", root = "datas", pageSize = 20)
public class AllWaitList {
	
	private ActivityInst activityInst;
	
	
	@ColumnDefine(header = "标题", width = 300, mapping = "fileTitle")
	private String fileTitle;
	
	@ColumnDefine(header = "等级", width = 120, mapping = "impending")
	private String impending;

	@ColumnDefine(header = "发送人", width = 100, mapping = "lastNodePerson")
	private String lastNodePerson;
	
	@ColumnDefine(header = "状态", width = 100, mapping = "status")
	private String status;

	@ColumnDefine(header = "时限", width = 100, mapping = "timeLimit")
	private String timeLimit;
	
	/*@ColumnDefine(header = "拟稿人", width = 65, mapping = "startPerson")
	private String startPerson;*/

	@ColumnDefine(header = "接收时间", width = 150, mapping = "arriveTime")
	private String arriveTime;

	@ColumnDefine(header = "流程名称", width = 100, mapping = "processName")
	private String processName;
//	@ColumnDefine(header = "步骤", width = 100, mapping = "step")
//	private String step;
	/*@ColumnDefine(
			header = "查看流程图",
			width = 80,
			sortable = true,
			hidden = false,
			tooltip = "",
			align = ElementAlign.left,
			mapping = "actHisId"
		)
	private String actHisId;*/

	
	/*@ColumnDefine(header = "操作", width = 100, mapping = "view")
	private String view;*/
	@ColumnDefine(
			header = "操作",
			width = 80,
			sortable = true,
			hidden = false,
			tooltip = "",
			align = ElementAlign.left,
			mapping = "actHisId"
		)
	private String actHisId;
	
	private String  activityInstId;
	
	
	public ActivityInst getActivityInst() {
		return activityInst;
	}

	public void setActivityInst(ActivityInst activityInst) {
		this.activityInst = activityInst;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	/*public String getStartPerson() {
		return startPerson;
	}

	public void setStartPerson(String startPerson) {
		this.startPerson = startPerson;
	}*/

	public String getArriveTime() {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = this.getActivityInst().getArrivedTime();
		return sf.format(this.getActivityInst().getArrivedTime());
	}

	public void setArriveTime(String startTime) {
		this.arriveTime = startTime;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the timeLimit
	 */
	public String getTimeLimit() {
		return timeLimit;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	/**
	 * @return the view
	 */
//	public String getView() {
//		return view;
//	}

	/**
	 * @param view
	 *            the view to set
	 */
//	public void setView(String view) {
//		this.view = view;
//	}

	/**
	 * @return the impending
	 */
	public String getImpending() {
		return impending;
	}

	/**
	 * @param impending
	 *            the impending to set
	 */
	public void setImpending(String impending) {
		this.impending = impending;
	}

	/**
	 * @return the processName
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * @param processName
	 *            the processName to set
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getLastNodePerson() {
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		WorkflowClientService client = bpmUserClientUtil.getClient();
		String pname = "";
		try {
			
			List<ActivityInstHistory> list = client.getLastActivityInstHistoryListByActvityInst(this.activityInst.getActivityInstId(), null);
		
		//	List<ActivityInstHistory> list = this.activityInst.getActivityInstHistoryListByActvityInst();
			if(list.size() > 0){
				ActivityInstHistory his = list.get(list.size() - 1);
				List<Person> performers = (List<Person>) client
						.getActivityInstHistoryRightAttribute(
								his.getActivityHistoryId(),
								OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER,
								null);
				if(performers.size()>0){
					Person person = performers.get(0);
					pname = person.getName();
				}
			}
			
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pname;
	}

	public void setLastNodePerson(String lastNodePerson) {
		this.lastNodePerson = lastNodePerson;
	}
	

	public String getActHisId(){
		return "<a href='#' onclick=lcrzshow('"+this.getActivityInst().getActivityInstId()+"')>查看流程</a>";
	}

	public void setActHisId(String actHisId){
		this.actHisId = "<a href='#' onclick=lcrzshow('"+this.getActivityInst().getActivityInstId()+"')>查看流程</a>";
	}
	




	public String getActivityInstId() {
		return this.getActivityInst().getActivityInstId();
	}

	public void setActivityInstId(String activityInstId) {
		this.activityInstId = activityInstId;
		
	}



//	public String getStep() {
//		return step;
//	}
//
//	public void setStep(String step) {
//		this.step = step;
//	}


	
	

	
/*	private ActivityDef activityDef;
	public ActivityDef getActivityDef() {
		return activityDef;
	}

	public void setActivityDef(ActivityDef activityDef) {
		this.activityDef = activityDef;
	}
	private String activityDefID;
	public String getActivityDefID() {
		return this.getActivityDef().getActivityDefId();
	}

	public void setActivityDefID(String activityDefID) {
		this.activityDefID = activityDefID;
	}
	*/
}