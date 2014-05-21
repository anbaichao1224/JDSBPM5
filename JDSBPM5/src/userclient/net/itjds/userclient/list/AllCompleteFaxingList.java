package net.itjds.userclient.list;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import net.itjds.oa.*;
import net.itjds.common.database.DBBeanBase;

import net.itjds.common.dm.DM;
import net.itjds.common.util.DateUtility;
import java.io.PrintWriter;

import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.support.rules.*;
import net.itjds.bpm.engine.*;
import net.itjds.common.org.base.*;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.database.DbRouteDef;
import net.itjds.common.org.base.Person;
import net.itjds.worklist.list.support.annotation.ColumnDefine;
import net.itjds.worklist.list.support.annotation.FrameDefine;
import net.itjds.worklist.list.support.annotation.enums.ElementAlign;
import net.itjds.worklist.list.support.annotation.enums.ElementButton;
import net.itjds.worklist.list.support.annotation.enums.ElementTbar;

import com.opensymphony.xwork2.ActionContext;


@EsbBeanAnnotation(id = "AllCompleteFaxingList", name = "所有已办列表", expressionArr = "AllCompleteList()", desc = "所有已办列表", dataType = "action")
@FrameDefine(title = "", height = 400, width = 600, tbar = ElementTbar.commonQuery, buttons = { ElementButton.none }, hasRowNumber = true, hasCheckBox = false, buttonAlign = ElementAlign.center, url = "bpmDataBind.action", root = "datas", pageSize = 20)
public class AllCompleteFaxingList {

	private ActivityInst activityInst;
	private String activityInstId;
	//全已办列表
	@ColumnDefine(header = "拟稿日期", width = 120, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "startTime")
	private String startTime;

	@ColumnDefine(header = "时限", width = 40, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "timeLimit")
	private String timeLimit;

	@ColumnDefine(header = "标题", width = 600, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "fileTitle")
	private String fileTitle;
	
	@ColumnDefine(header="来文单位", width=60, sortable=true, hidden=false, tooltip="", align=ElementAlign.left, mapping="lwdw")
	private String lwdw;

//	@ColumnDefine(header = "状态", width = 60, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "status")
//	private String status;

	@ColumnDefine(header = "拟稿人", width = 60, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "startPerson")
	private String startPerson;

	@ColumnDefine(header = "流程名称", width = 80, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "processName")
	private String processName;

	@ColumnDefine(header = "紧急程度", width = 60, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "impending")
	private String impending;
	/**
	@ColumnDefine(
		header = "操作",
		width = 60,
		sortable = true,
		hidden = false,
		tooltip = "",
		align = ElementAlign.left,
		mapping = "view"
	)
	private String view;
	 */
	@ColumnDefine(header = "当前办理人", width = 80, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "currPerson")
	private String currPerson;

	/**
	 * 
	 * @return
	 */
	@ColumnDefine(header = "查看流程图", width = 80, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "actHisId")
	private String actHisId;
	@ColumnDefine(header = "疏导", width = 80, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "shudao")
	private String shudao;
	@ColumnDefine(header = "操作", width = 80, sortable = true, hidden = false, tooltip = "", align = ElementAlign.left, mapping = "chakan")
	private String chakan;

	private ActivityInstHistory activityInstHistory;
	private WorkflowClientService client;
	private BPMUserClientUtil bpmUserClientUtil;
	private ArrayList webHistoryList;
	private ArrayList webCurrentWrpList;
	private List historyList;
	

	public ArrayList getWebHistoryList() {
		return webHistoryList;
	}

	public void setWebHistoryList(ArrayList webHistoryList) {
		this.webHistoryList = webHistoryList;
	}

	public ArrayList getWebCurrentWrpList() {
		return webCurrentWrpList;
	}

	public void setWebCurrentWrpList(ArrayList webCurrentWrpList) {
		this.webCurrentWrpList = webCurrentWrpList;
	}

	public List getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List historyList) {
		this.historyList = historyList;
	}

	public BPMUserClientUtil getBpmUserClientUtil() {
		return bpmUserClientUtil;
	}

	public void setBpmUserClientUtil(BPMUserClientUtil bpmUserClientUtil) {
		this.bpmUserClientUtil = bpmUserClientUtil;
	}

	public WorkflowClientService getClient() {
		return client;
	}

	public void setClient(WorkflowClientService client) {
		this.client = client;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartTime() throws SQLException {
		return this.startTime;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getTimeLimit() {
		return this.timeLimit;
	}

	public void setFileTitle(String fileTitle) {
		//this.fileTitle = fileTitle;
		fileTitle = fileTitle.substring(fileTitle.indexOf(">") + 1, fileTitle
				.indexOf("</"));
		this.fileTitle = "<a href='#' onclick=openselfActivityInstWin('"
				+ this.getActivityInstId() + "')>" + fileTitle + "</a>";
	}

	public String getFileTitle() {
		return this.fileTitle;

		//fileTitle = this.fileTitle.substring(fileTitle.indexOf(">")+1,fileTitle.indexOf("</"));
		//return "<a href='#' onclick=openselfActivityInstWin('"+this.getActivityInstId()+"')>"+fileTitle+"</a>";
	}

//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public String getStatus() {
//		return this.status;
//	}

	public void setStartPerson(String startPerson) {
		this.startPerson = startPerson;
	}

	public String getStartPerson() {
		return this.startPerson;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}
	//sun  2012-12-11 已办列表，流程名称显示文件进行到哪一步了；
	public String getProcessName() throws SQLException{
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection con=dbbase.getConn();
		Statement stmt = null;
		ResultSet rs = null;
		this.processName="";
		try {
			stmt=con.createStatement();
			String sql="select defname from bpm_activitydef where activitydef_id in(select activitydef_id from bpm_activityinstance where ActivityInst_id='"+this.getActivityInstId()+"')";
			rs=stmt.executeQuery(sql);
//			System.out.println(sql);
			while(rs.next()){
				this.processName +=rs.getString("defname")+"、";
			}
			if(this.processName!=""){
				this.processName=this.processName.substring(0,this.processName.length()-1);
			}	
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			rs.close();
			stmt.close();
			con.close();
		}
		
		return this.processName;
	}

	public void setImpending(String impending) {
		this.impending = impending;
	}

	public String getImpending() {
		return this.impending;
	}

	/*
	public void setView(String view){
		System.out.println("view" + view);
		this.view = view;
	}
	
	public String getView(){
		System.out.println("view" + view);
		return this.view;
	}*/

	public void setCurrPerson(String currPerson) {
		
		this.currPerson = currPerson;
	}
	//sun 查询当前办理人 2012-12-14
	public String getCurrPerson()  throws SQLException {
	
	DBBeanBase dbbase = new DBBeanBase("bpm", true);
	Connection con=dbbase.getConn();
	Statement stmt = null;
	ResultSet rs = null;
	this.currPerson="";
	try {
		stmt=con.createStatement();
		String sql="select cnname from ro_person where personid in (select person_id from rt_activity_person t where processinst_id in (select processinst_id from bpm_activityinstance where activityinst_id ='"+this.getActivityInstId()+"'))";
		rs=stmt.executeQuery(sql);
		while(rs.next()){
			this.currPerson +=rs.getString("cnname")+"、";
		}
		if(this.currPerson!=""){
			this.currPerson =this.currPerson.substring(0, this.currPerson.length()-1);
		}
	}catch (Exception e) {
		e.printStackTrace();
	}finally{
		rs.close();
		stmt.close();
		con.close();
	}
		
		return this.currPerson;
	}

	public ActivityInst getActivityInst() {
		return activityInst;
	}

	public void setActivityInst(ActivityInst activityInst) {
		this.activityInst = activityInst;
	}

	public String getActivityInstId() {
		return this.activityInst.getActivityInstId();
	}

	public void setActivityInstId(String activityInstId) {
		this.activityInstId = this.activityInst.getActivityInstId();
		//this.activityInst.get

	}

	public String getShudao() {
		boolean isme = false;
		try {

			WorkflowClientService client = (WorkflowClientService) ActionContext
					.getContext().getValueStack().findValue("$BPMC");
			String userId = client.getConnectInfo().getUserID();
			List<ActivityInstHistory> his = this.getActivityInst()
					.getActivityInstHistoryListByActvityInst();
			List routeIds = this.getActivityInst().getActivityDef()
					.getInRouteIds();

			for (int j = 0; j < routeIds.size(); j++) {
				DbRouteDef routeDef = (DbRouteDef) routeIds.get(j);
				String defId = routeDef.getFromActivityDefId();
				for (int i = 0; i < his.size(); i++) {
					ActivityInstHistory ah = his.get(i);
					if (ah == null)
						continue;
					if (ah.getActivityDefId().equals(defId)) {
						List<Person> personList = (List<Person>) client
								.getActivityInstHistoryRightAttribute(
										ah.getActivityHistoryId(),
										OARightConstants.ACTIVITYINSTHISTORY_RIGHT_ATT_PERFORMER,
										null); // ai.getRightAttribute(OARightConstants.ACTIVITYINST_RIGHT_ATT_PERFORMER);
						if (personList == null)
							continue;
						for (int index = 0; index < personList.size(); index++) {
							if (personList.get(index).getID().equals(userId)) {
								isme = true;
								break;
							}
						}
					}
					if (isme)
						break;
				}
				if (isme)
					break;
			}
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//	this.getActivityInst().getActivityInstHistoryListByActvityInst();
		if (isme)
			return "<a href='#' onclick=openshudao('"
					+ this.getActivityInstId() + "')>疏导</a>";
		else
			return "";
	}

	public void setShudao(String shudao) {
		this.shudao = "<a href='#' onclick=openshudao('"
				+ this.getActivityInstId() + "')>疏导</a>";
	}

	public ActivityInstHistory getActivityInstHistory() {
		return activityInstHistory;
	}

	public void setActivityInstHistory(ActivityInstHistory activityInstHistory) {
		this.activityInstHistory = activityInstHistory;
	}

	public String getActHisId() {
		return "<a href='#' onclick=lcrzshow('" + this.getActivityInstId()
				+ "')>查看流程图</a>";
	}

	public void setActHisId(String actHisId) {
		this.actHisId = "<a href='#' onclick=lcrzshow('"
				+ this.getActivityInstId() + "')>查看流程图</a>";
	}

	public String getChakan() {
		return "<a href='#' onclick=openselfActivityInstWin('"
				+ this.getActivityInstId() + "') >查看</a>";
	}

	public void setChakan(String chakan) {
		this.chakan = "<a href='#' onclick=openselfActivityInstWin('"
				+ this.getActivityInstId() + "') >查看</a>";
	}

	public String getLwdw() throws SQLException{
		    DBBeanBase dbbase = new DBBeanBase("bpm", true);
		    Connection con = dbbase.getConn();
		    Statement stmt = null;
		    ResultSet rs = null;
		    this.lwdw = "";
		    try {
			      stmt = con.createStatement();
			      String sql = "select lwdw from fdt_oa_nmswbl where ActivityInst_id='" + getActivityInstId() + "'";
			      rs = stmt.executeQuery(sql);
			      while (rs.next())
			      this.lwdw = rs.getString("lwdw");
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
		      rs.close();
		      stmt.close();
		      con.close();
		    }
		    return this.lwdw;
	  }

	  public void setLwdw(String lwdw) {
	    this.lwdw = lwdw;
	  }
}