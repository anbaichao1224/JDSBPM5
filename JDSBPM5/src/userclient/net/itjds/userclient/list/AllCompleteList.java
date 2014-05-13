package net.itjds.userclient.list;


import java.util.List;
import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.org.base.Person;
import net.itjds.worklist.list.support.annotation.ColumnDefine;
import net.itjds.worklist.list.support.annotation.FrameDefine;
import net.itjds.worklist.list.support.annotation.enums.ElementAlign;
import net.itjds.worklist.list.support.annotation.enums.ElementTbar;

@EsbBeanAnnotation(id="AllCompleteList", name="所有已办列表", expressionArr="AllCompleteList()", desc="所有已办列表", dataType="action")
@FrameDefine(title="", height=400, width=600, tbar=ElementTbar.commonQuery, buttons={net.itjds.worklist.list.support.annotation.enums.ElementButton.none}, hasRowNumber=true, hasCheckBox=false, buttonAlign=ElementAlign.center, url="bpmDataProcessBind.action", root="datas", pageSize=30)
public class AllCompleteList
{
  private ActivityInst activityInst;
  private String activityInstId;
  @ColumnDefine(header="标题", width=300, sortable=true, hidden=false, tooltip="", align=ElementAlign.left, mapping="fileTitle")
  private String fileTitle;

  @ColumnDefine(header="等级", width=60, sortable=true, hidden=false, tooltip="", align=ElementAlign.left, mapping="impending")
  private String impending;

  @ColumnDefine(header="拟稿人", width=80, sortable=true, hidden=false, tooltip="", align=ElementAlign.left, mapping="startPerson")
  private String startPerson;

  @ColumnDefine(header="拟稿时间", width=150, sortable=true, hidden=false, tooltip="", align=ElementAlign.left, mapping="startTime")
  private String startTime;

  @ColumnDefine(header="当前办理人", width=80, sortable=true, hidden=false, tooltip="", align=ElementAlign.left, mapping="currPerson")
  private String currPerson;

  @ColumnDefine(header="时限", width=60, sortable=true, hidden=false, tooltip="", align=ElementAlign.left, mapping="timeLimit")
  private String timeLimit;

  @ColumnDefine(header="流程名称", width=120, sortable=true, hidden=false, tooltip="", align=ElementAlign.left, mapping="processName")
  private String processName;
  
  @ColumnDefine(header="查看流程", width=80, sortable=true, hidden=false, tooltip="", align=ElementAlign.left, mapping="actHisId")
  private String actHisId;

  @ColumnDefine(header="操作", width=80, sortable=true, hidden=false, tooltip="", align=ElementAlign.left, mapping="chakan")
  private String chakan;
  private ActivityInstHistory activityInstHistory;

  public void setStartTime(String startTime)
  {
    this.startTime = startTime;
  }

  public String getStartTime() {
    return this.startTime;
  }

  public void setTimeLimit(String timeLimit) {
    this.timeLimit = timeLimit;
  }

  public String getTimeLimit() {
    return this.timeLimit;
  }

  public void setFileTitle(String fileTitle)
  {
    this.fileTitle = fileTitle;
  }

  public String getFileTitle() {
    return this.fileTitle;
  }

  public void setStartPerson(String startPerson)
  {
    this.startPerson = startPerson;
  }

  public String getStartPerson() {
    return this.startPerson;
  }

  public void setProcessName(String processName) {
    this.processName = processName;
  }

  public String getProcessName() {
    return this.processName;
  }

  public void setImpending(String impending) {
    this.impending = impending;
  }

  public String getImpending() {
    return this.impending;
  }

  public void setCurrPerson(String currPerson) {
    this.currPerson = currPerson;
  }

  public String getCurrPerson() {
   
  
    String pname = "";
    try
    {
      List list = this.activityInstHistory.getProcessInst().getActivityInstList();

      String activityInstId = "";
      for (int i = 0; i < list.size(); i++) {
        ActivityInst inst = (ActivityInst)list.get(i);
        if ((!"SPLITED".equals(inst.getDealMethod())) && (!"suspended".equals(inst.getState()))) {
          activityInstId = inst.getActivityInstId();
        }
      }
 
      

    }
    catch (BPMException e)
    {
      e.printStackTrace();
    }
    return pname;
  }

  public ActivityInst getActivityInst()
  {
    return this.activityInst;
  }

  public void setActivityInst(ActivityInst activityInst) {
    this.activityInst = activityInst;
  }

  public String getActivityInstId() {
	  String ActivityInstId ="";
    try {
		 ActivityInstId = this.activityInstHistory.getActivityInstId();
	} catch (BPMException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return ActivityInstId;
  }

  public void setActivityInstId(String activityInstId) {
    this.activityInstId = this.activityInst.getActivityInstId();
  }

  public ActivityInstHistory getActivityInstHistory()
  {
    return this.activityInstHistory;
  }

  public void setActivityInstHistory(ActivityInstHistory activityInstHistory) {
    this.activityInstHistory = activityInstHistory;
  }




  public String getActHisId() {
    return "<a href='#' onclick=lcrzshow('" + getActivityInstId() + "')>查看流程</a>";
  }

  public void setActHisId(String actHisId) {
    this.actHisId = ("<a href='#' onclick=lcrzshow('" + getActivityInstId() + "')>查看流程</a>");
  }

  public String getChakan() {
    return "<a href='#' onclick=openselfActivityInstWin('" + getActivityInstId() + "') >查看</a>";
  }

  public void setChakan(String chakan) {
    this.chakan = ("<a href='#' onclick=openselfActivityInstWin('" + getActivityInstId() + "') >查看</a>");
  }
  
}