package net.itjds.userclient.list;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.worklist.list.support.annotation.ColumnDefine;
import net.itjds.worklist.list.support.annotation.FrameDefine;
import net.itjds.worklist.list.support.annotation.enums.ElementAlign;
import net.itjds.worklist.list.support.annotation.enums.ElementButton;

@EsbBeanAnnotation(id = "DraftDefaultList", name = "草稿列表", expressionArr = "DraftDefaultList()", desc = "草稿列表", dataType = "action")
@FrameDefine(
		title = "",
		height = 250,
		width = 500,
		buttons = {ElementButton.delete},
		hasRowNumber = true,
		hasCheckBox = true,
		buttonAlign = ElementAlign.center,
		url = "bpmDataBind.action",
		root = "datas",
		pageSize = 20
		)
public class DraftDefaultList {
	private ActivityInst activityInst;

	@ColumnDefine(header = "processInstId", hidden=true, mapping = "processInstId")
	private String processInstId;

	@ColumnDefine(header = "标题", width = 220, mapping = "fileTitle")
	private String fileTitle;
	


	@ColumnDefine(header = "拟稿时间", width = 120, mapping = "startTime")
	private String startTime;
	
	@ColumnDefine(header = "拟稿人", width = 60, mapping = "startPerson")
	private String startPerson;
	
	public String getStartPerson() {
		return startPerson;
	}

	public void setStartPerson(String startPerson) {
		this.startPerson = startPerson;
	}
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

	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	
	/**
	 * @return the view
	 */
	//public String getView() {
	//	return view;
	//}

	/**
	 * @param view
	 *            the view to set
	 */
	//public void setView(String view) {
	//	this.view = view;
	//}

	public String getProcessInstId() {
		return activityInst.getProcessInstId();
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

}