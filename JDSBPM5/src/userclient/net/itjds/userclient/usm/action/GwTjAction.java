package net.itjds.userclient.usm.action;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ProcessDef;
import net.itjds.bpm.client.ProcessDefVersion;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.engine.AdminService;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.WorkflowServer;
import net.itjds.bpm.engine.database.DbActivityInst;
import net.itjds.bpm.engine.database.DbActivityInstList;
import net.itjds.bpm.engine.database.DbActivityInstManager;
import net.itjds.bpm.engine.database.DbProcessInst;
import net.itjds.bpm.engine.database.DbProcessInstList;
import net.itjds.bpm.engine.inter.EIActivityInstManager;
import net.itjds.bpm.engine.proxy.WorkflowListProxy;
import net.itjds.bpm.engine.query.Condition;
import net.itjds.bpm.engine.query.ConditionKey;
import net.itjds.bpm.engine.query.Order;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.OrgNotFoundException;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.oa.OAException;
import net.itjds.oa.OAUtil;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.action.BPMClientBaseBinding;
import net.itjds.worklist.list.support.GridUtils;
import net.itjds.worklist.list.support.rules.CurrPersonRule;
import net.itjds.worklist.list.support.rules.FileTitleRule;
import net.itjds.worklist.list.support.rules.ImpendingRule;
import net.itjds.worklist.list.support.rules.ProcessNameRule;
import net.itjds.worklist.list.support.rules.StartPersonRule;
import net.itjds.worklist.list.support.rules.StartTimeRule;
import net.itjds.worklist.list.support.rules.StatusRule;
import net.itjds.worklist.list.support.rules.TimeLimitRule;
import net.itjds.worklist.list.support.rules.ViewRule;

@SuppressWarnings("unchecked")
public class GwTjAction extends BPMClientBaseBinding {

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	//获取子部门
	public void findChildDept() throws PersonNotFoundException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId  = session.getAttribute("personId").toString();
		Person person =  OrgManagerFactory.getOrgManager().getPersonByID(personId);
		List<Org> orglist = person.getOrgList();
		String jsonstr = "[{id:\"\",name:\"默认\"}";
		for(int i=0;i<orglist.size();i++){
			Org org = orglist.get(i);
			
				jsonstr += ",{id:'"+org.getID()+"',name:'"+org.getName()+"'}";
			
			
			List<Org> childlist = org.getChildrenList();
			for(int j=0;j<childlist.size();j++){
				Org childOrg = childlist.get(j);
				jsonstr +=",{id:\""+childOrg.getID()+"',name:\""+childOrg.getName()+"\"}";
			}
		}
		jsonstr += "]";
		
		try {
			response.getWriter().write(jsonstr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//查询部门下的人员
	public void findPersonByDept() throws OrgNotFoundException, IOException, PersonNotFoundException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId  = session.getAttribute("personId").toString();
		Person personCurr =  OrgManagerFactory.getOrgManager().getPersonByID(personId);
		if(deptId==null){
			deptId = personCurr.getOrgList().get(0).getID();
		}
		Org org = OrgManagerFactory.getOrgManager().getOrgByID(deptId);
		List<Person> personlist = org.getPersonList();
		String jsonstr = "[{personid:\"\",personname:\"默认\"}";
		for(int i=0;i<personlist.size();i++){
			Person person = personlist.get(i);
			jsonstr +=",{personid:\""+person.getID()+"\",personname:\""+person.getName()+"\"}";
		}
		jsonstr += "]";
		response.getWriter().write(jsonstr);
	}
	
	public void findGwType() throws SQLException, IOException{
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String sql = "select v.processdef_version_id,p.defname from bpm_processdef_version v,bpm_processdef p where v.processdef_id = p.processdef_id";
		String jsonstr = "[{defId:\"\",defName:\"默认\"},";
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		ResultSet rs = null;
		Statement st = null;
		Connection conn = null;
		try{
			conn = dbbase.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()){
				if(rs.getString(2).equals("内部流转")){
					continue;
				}
				jsonstr += "{defId:\""+rs.getString(1)+"\",defName:\""+rs.getString(2)+"\"},";
			}
			jsonstr = jsonstr.substring(0,jsonstr.lastIndexOf(","));
			jsonstr += "]";
			response.getWriter().write(jsonstr);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try{
				st.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try{
				dbbase.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	//list转为json串
	public String returnjson(List<ActivityInst> list) throws BPMException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId  = session.getAttribute("personId").toString();
		
		String jsonstr = "{totalCount:"+list.size()+",root:[";
		for(int i=0;i<list.size();i++){
			ActivityInst ai = list.get(i);
			/*String jj = ai.getProcessInst().getAttribute("fileTitle");*/
			String impend = new ImpendingRule().invoke(ai).toString();
			String status = new StatusRule().invoke(ai).toString();
			String timeLimit = new TimeLimitRule().invoke(ai).toString();
			String fileTitle = new FileTitleRule().invoke(ai).toString();
			String startPerson = new StartPersonRule().invoke(ai).toString();
			String startTime = new StartTimeRule().invoke(ai).toString();
			String processName = new ProcessNameRule().invoke(ai).toString();
			String currPerson = new CurrPersonRule().invoke(ai).toString();
			String currPersonId = getPersonId(ai).toString();
			String view = "<span style=\\\"color:#0000FF\\\" onmouseover=\\\"javascript:this.style.cursor = 'hand';\\\" onclick=javascript:";//openActivityInstWin('"+ai.getActivityInstId()+"') >查看</span>";
			if(personId.equals(currPersonId)){
				view += "window.top.openActivityInstWin('"+ai.getActivityInstId()+"')>";
			}else{
				view += "window.top.openselfActivityInstWin('"+ai.getActivityInstId()+"')>";
			}
			view += "查看</span>";
			
			if(i==list.size()-1){
				jsonstr += "{impend:\""+impend+"\",status:\""+status+"\",timeLimit:\""+timeLimit+"\",fileTitle:\""+fileTitle+"\",currPerson:\""+currPerson+"\",startPerson:\""+startPerson+"\",startTime:\""+startTime+"\",processName:\""+processName+"\",view:\""+view+"\"}";
				continue;
			}
			jsonstr += "{impend:\""+impend+"\",status:\""+status+"\",timeLimit:\""+timeLimit+"\",fileTitle:\""+fileTitle+"\",currPerson:\""+currPerson+"\",startPerson:\""+startPerson+"\",startTime:\""+startTime+"\",processName:\""+processName+"\",view:\""+view+"\"},";
		}
		jsonstr +="]}";
		return jsonstr;
	}
	
	
	public String tj() throws SQLException, PersonNotFoundException, OrgNotFoundException, BPMException, InstantiationException, IllegalAccessException, ClassNotFoundException, IOException{
		
		List<ActivityInst> list = querylist();
		
		String jsonstr = returnjson(list);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.append(jsonstr);
		pw.flush();
		return null;
		
	}
	
	public List<ActivityInst> querylist() throws BPMException, SQLException, PersonNotFoundException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId  = session.getAttribute("personId").toString();
		
		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
		WorkflowClientService client = bpmUserClientUtil.getClient();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		ResultSet rs = null;
		Statement st = null;
		Connection conn = null;
		conn = dbbase.getConn();
		String sql = "select BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID,BPM_ACTIVITYINSTANCE.PROCESSINST_ID,BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID,BPM_ACTIVITYINSTANCE.PROCESSDEF_ID,BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE,BPM_ACTIVITYINSTANCE.URGENCYTYPE,BPM_ACTIVITYINSTANCE.ARRIVEDTIME,BPM_ACTIVITYINSTANCE.LIMITTIME,BPM_ACTIVITYINSTANCE.ALERTTIME,BPM_ACTIVITYINSTANCE.STARTTIME,BPM_ACTIVITYINSTANCE.RECEIVEMETHOD,BPM_ACTIVITYINSTANCE.DEALMETHOD,BPM_ACTIVITYINSTANCE.RUNSTATUS,BPM_ACTIVITYINSTANCE.CANTAKEBACK from BPM_ACTIVITYINSTANCE  WHERE ACTIVITYINST_ID IN (";
		sql += "select BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID from BPM_ACTIVITYINSTANCE"+""
		+"  where (BPM_ACTIVITYINSTANCE.RUNSTATUS != 'processNotStarted' "+""
		+"AND BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE != 'notStarted' "+""
		+"AND BPM_ACTIVITYINSTANCE.PROCESSINST_ID NOT IN (( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME ='USMTYPE' )) "+""
		+"AND BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID NOT IN (( SELECT ACTIVITYINST_ID FROM BPM_ACTIVITYINST_PROPERTY  WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='ISREADED'  ) ) ";
		String personWhere = "";
		String titleWhere = "";
		String timeWhere = " BETWEEN ";
		String blstatusWhere = "";
		if(title!=null){
			if(!title.equals("")){
			titleWhere = " AND PROPNAME='FILETITLE' AND PROPVALUE LIKE '%"+title+"%'";
			sql += " AND BPM_ACTIVITYINSTANCE.PROCESSINST_ID IN ( ( SELECT PROCESSINST_ID FROM BPM_PROCESSINST_PROPERTY  WHERE PROPTYPE='CUSTOMIZE' AND PROPNAME='FILETITLE' AND PROPVALUE LIKE '%"+title+"%' ) ) ";
			}
		}
		if(defId!=null&&!defId.equals("")){
			
			sql += " AND BPM_ACTIVITYINSTANCE.PROCESSDEF_ID IN (('"+defId+"' ))";
		}
		if(starttime!=null){
			if(endtime==null){
				endtime = new Date();
			}
			Collection timeList = new ArrayList();
			timeList.add(starttime);
			timeList.add(endtime);
			Iterator ite = timeList.iterator();
            if (ite.hasNext()) {
            	timeWhere += String.valueOf(((java.util.Date)ite.next()).getTime());
            }
            timeWhere += " AND ";
            if (ite.hasNext()) {
            	timeWhere += String.valueOf(((java.util.Date)ite.next()).getTime()); 
            }
            sql += " AND BPM_ACTIVITYINSTANCE.ARRIVEDTIME "+timeWhere;
		}
		sql += " AND BPM_ACTIVITYINSTANCE.PROCESSDEF_ID IN (SELECT BPM_PROCESSDEF_VERSION.PROCESSDEF_VERSION_ID FROM BPM_PROCESSDEF, BPM_PROCESSDEF_VERSION WHERE BPM_PROCESSDEF.PROCESSDEF_ID=BPM_PROCESSDEF_VERSION.PROCESSDEF_ID AND BPM_PROCESSDEF.SYSTEMCODE='oa')";
		if(personid!=null&&!personid.equals("")){
			personWhere = "'"+personid+"'";
		}else{
			if(deptId==null||deptId.equals("")){
			
				Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
				if(person.getOrgIds()!=null&&person.getOrgIds().length>0){
					deptId = person.getOrgIds()[0];
				}
			}
			Person[] persons = OrgManagerFactory.getOrgManager().getPersonsByOrgID(deptId);
			for(int i=0;i<persons.length;i++){
				if(i==persons.length-1){
					personWhere += "'"+persons[i].getID()+"'";
					continue;
				}
				personWhere += "'"+persons[i].getID()+"'"+",";
			}
		}
		
		if(blstatus!=null){
			if(blstatus==0){
				blstatusWhere =" AND RIGHT_GRP_CODE IN ( 'PERFORMER','READER') AND PERSON_ACTIVITY_STATE IN ('WAITING', 'CURRENT', 'READ')";
			}else{
				blstatusWhere =" AND ((RIGHT_GRP_CODE IN ( 'PERFORMER','READER') AND PERSON_ACTIVITY_STATE = 'FINISH' )  OR ( RIGHT_GRP_CODE != 'PERFORMER' AND RIGHT_GRP_CODE !='READER')  )";
			}
			sql += "";
		}
		if(!personWhere.equals("")){
			sql += " AND BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID IN ( ( SELECT ACTIVITYINST_ID  FROM RT_ACTIVITY_PERSON WHERE PERSON_ID IN ("+personWhere+")  "+blstatusWhere+" ))";
		}
		sql += "))";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			
			ArrayList v = new ArrayList();
			DbActivityInstManager activityInstMgr = new DbActivityInstManager();
			while(rs.next()){
				v.add(activityInstMgr.decodeRow(rs));
			}
			DbActivityInst[] dbpinst = ((DbActivityInst[])v.toArray(new DbActivityInst[0]));
			List result = new DbActivityInstList(dbpinst);
			List resultsec = Collections.unmodifiableList(result);
			List<ActivityInst> list = new WorkflowListProxy(resultsec, null);
			List list1 = new ArrayList<ActivityInst>();
			for(int i=0;i<list.size();i++){
				String a =list.get(i).getState();
				if(!a.equals("suspended")){
					list1.add(list.get(i));
				}
			}
			/*List<ActivityInst> list = client.getActivityInstList(null,
					null, null);*/
			return list1;
			//tjlist =  ;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try{
				st.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try{
				dbbase.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	 
	
	public String getPersonIdsByOrg(String orgId){
		Person[] persons = OrgManagerFactory.getOrgManager().getPersonsByOrgID(orgId);
		String personIds = "";
		for(int k=0;k<persons.length;k++){
			if(k==persons.length-1){
				personIds += "'"+persons[k].getID()+"'";
				continue;
			}
			personIds += "'"+persons[k].getID()+"',";
		}
		return personIds;
	}
	
	/**
	 * 获取当前办理人Id
	 * @param ai
	 * @return
	 */
	public Object getPersonId(ActivityInst ai) {
		String personid = "";
		try {
			WorkflowClientService client= (WorkflowClientService) ActionContext.getContext().getValueStack().findValue("$BPMC");
			List<Person> personList=(List<Person>) client.getActivityInstRightAttribute(ai.getActivityInstId(), OARightConstants.ACTIVITYINST_RIGHT_ATT_PERFORMER, null);
			if (personList.size()==0){
				personList=(List<Person>) client.getActivityInstRightAttribute(ai.getActivityInstId(), OARightConstants.ACTIVITYINST_RIGHT_ATT_READER, null);
				
				if(personList.size()>0)
				{
					Person person=personList.get(0);
					personid=person.getID();
				}
			}else if (personList.size()>0){
				
				Person person = personList.get(0);
				personid = person.getID();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personid;
	}
	
	
	
	private List<ActivityInst> tjlist;
	private Integer blstatus;
	private String defId;
	private String deptId;
	private String personid;
	private String title;
	private Date starttime;
	private Date endtime;
	
	/*private int totalCount;
	private int start;
	private int limit;*/
	
	public List<ActivityInst> getTjlist() {
		return tjlist;
	}

	public void setTjlist(List<ActivityInst> tjlist) {
		this.tjlist = tjlist;
	}

	public int getBlstatus() {
		return blstatus;
	}

	public void setBlstatus(int blstatus) {
		this.blstatus = blstatus;
	}
	
	public String getDefId() {
		return defId;
	}

	public void setDefId(String defId) {
		this.defId = defId;
	}

	public void setBlstatus(Integer blstatus) {
		this.blstatus = blstatus;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	

}
