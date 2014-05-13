package net.itjds.userclient.list;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.worklist.list.support.annotation.ColumnDefine;
import net.itjds.worklist.list.support.annotation.FrameDefine;
import net.itjds.worklist.list.support.annotation.SearchItemDefine;
import net.itjds.worklist.list.support.annotation.enums.ElementAlign;
import net.itjds.worklist.list.support.annotation.enums.ElementTbar;
import net.itjds.worklist.list.support.annotation.enums.FieldType;

@EsbBeanAnnotation(id = "KzListerViewList", name = "监控列表", expressionArr = "KzListerViewList()", desc = "监控列表", dataType = "action")
@FrameDefine(url = "bpmAdminDataProcessBindself.action",tbar=ElementTbar.commonQuery)
public class KzListerViewList {
	
	private ActivityInst activityInst;
	private String activityInstId;
	
	private ActivityInstHistory activityInstHistory;

	@ColumnDefine(header = "等级", width = 60, mapping = "impending")
	private String impending;

	@ColumnDefine(header = "时限", width = 40, mapping = "timeLimit")
	private String timeLimit;

	@ColumnDefine(header = "标题", search=@SearchItemDefine(name="公文标题",fieldType=FieldType.TextField,width=80), width = 200, mapping = "fileTitle")
	private String fileTitle;
	
	@ColumnDefine(header = "拟稿人", width = 60, mapping = "startPerson")
	private String startPerson;

	@ColumnDefine(header = "拟稿时间",search=@SearchItemDefine(name="开始时间",fieldType=FieldType.DateField,width=80), width = 120, mapping = "startTime")
	private String startTime;
	
	@ColumnDefine(header = "结束时间",mapping="endTime",search=@SearchItemDefine(name="结束时间",fieldType=FieldType.DateField,width=80))
	private String endTime;
	
	@ColumnDefine(header = "流程名称", width = 80, mapping = "processName")
	private String processName;
	
	@ColumnDefine(header = "操作", width = 60, mapping = "view")
	private String view;
	
	//zhongqun add shudao 2011-11-21
	@ColumnDefine(
			header = "查看流程图",
			width = 80,
			sortable = true,
			hidden = false,
			tooltip = "",
			align = ElementAlign.left,
			mapping = "actHisId"
		)
	private String actHisId;
	@ColumnDefine(
			header = "疏导",
			width = 80,
			sortable = true,
			hidden = false,
			tooltip = "",
			align = ElementAlign.left,
			mapping = "shudao"
		)
	private String shudao;

	public ActivityInstHistory getActivityInstHistory() {
		return activityInstHistory;
	}

	public void setActivityInstHistory(ActivityInstHistory activityInstHistory) {
		this.activityInstHistory = activityInstHistory;
	}

	public String getFileTitle() {
		return fileTitle;
	}

	public void setFileTitle(String fileTitle) {
		this.fileTitle = fileTitle;
	}

	public String getStartPerson() {
		return startPerson;
	}

	public void setStartPerson(String startPerson) {
		this.startPerson = startPerson;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the view 
	 */
	public String getView() {
		return view;
	}

	/**
	 * @param view the view to set
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * @return the endTime 
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	/**
	 * @return the impending 
	 */
	public String getImpending() {
		return impending;
	}

	/**
	 * @return the processName 
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * @return the timeLimit 
	 */
	public String getTimeLimit() {
		return timeLimit;
	}

	/**
	 * @param impending the impending to set
	 */
	public void setImpending(String impending) {
		this.impending = impending;
	}

	/**
	 * @param processName the processName to set
	 */
	public void setProcessName(String processName) {
		this.processName = processName;
	}

	/**
	 * @param timeLimit the timeLimit to set
	 */
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public ActivityInst getActivityInst() {
		return activityInst;
	}

	public void setActivityInst(ActivityInst activityInst) {
		this.activityInst = activityInst;
	}

	public String getActivityInstId() throws BPMException {
		//return this.activityInst.getActivityInstId();
		return this.getActivityInstHistory().getActivityInstId();
	}

	public void setActivityInstId(String activityInstId) throws BPMException {
		this.activityInstId = this.getActivityInstHistory().getActivityInstId();
		//this.activityInst.get
		
	}
	
	public String getActHisId() throws BPMException {
		return "<a href='#' onclick=lcrzshow('"+this.getActivityInstId()+"')>查看流程图</a>";
	}

	public void setActHisId(String actHisId) throws BPMException {
		///this.actHisId = actHisId;
		this.actHisId = "<a href='#' onclick=lcrzshow('"+this.getActivityInstId()+"')>查看流程图</a>";
	}

	public String getShudao() throws BPMException {
		return  "<a href='#' onclick=openshudao('"+this.getActivityInstId()+"')>疏导</a>";
	}

	public void setShudao(String shudao) throws BPMException {
		this.shudao = "<a href='#' onclick=openshudao('"+this.getActivityInstId()+"')>疏导</a>";
	}
	

}