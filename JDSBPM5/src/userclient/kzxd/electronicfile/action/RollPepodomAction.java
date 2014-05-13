package kzxd.electronicfile.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kzxd.electronicfile.dao.RecordDataDAO;
import kzxd.electronicfile.dao.RollDAO;
import kzxd.electronicfile.dao.RollPepodomDAO;
import kzxd.electronicfile.entity.RecordDataBean;
import kzxd.electronicfile.entity.RollBean;
import kzxd.electronicfile.entity.RollPepodomBean;
import kzxd.electronicfile.file.BpmMAttachmentDAO;
import kzxd.electronicfile.file.FileListAction;
import kzxd.lucenetest.PepodomCreateLuceneIndex;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.base.PersonRole;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;

import com.jds.usm.persistence.model.Role;
import com.opensymphony.xwork2.Action;

public class RollPepodomAction implements Action {

	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	//可申请档案列表
	public void list(){
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		RecordDataAction rdaction = new RecordDataAction();
		rdaction.setStart(start);
		rdaction.setLimit(limit);
		ResultSet rs = rdaction.findByPepodom(rollid,status,title,starttime,endtime);
		totalCount = rdaction.getTotalCount();
		String jsonstr = "{totalCount:"+totalCount+",root:[";
		try {
			if(rs.isAfterLast()!=rs.isBeforeFirst()){
			while (rs.next()) {
				String dtitle = rs.getString("title");
				String passstatus = "未申请";
				if(rs.getInt("ispass")==1){
					passstatus = "待审核";
				}else if(rs.getInt("ispass")==3){
					passstatus = "未通过";
				}
				if(rs.getInt("ispass")==2){
					passstatus = "通过";
					dtitle = "<a href=javascript:void(0) onclick=opentype('/data_findById.action?dataId="+rs.getString("uuid")+"')>"+rs.getString("title")+"</a>";
				}
				jsonstr += "{uuid:\""+rs.getString("uuid")+"\",namemc:\""+dtitle+"\",creator:\""+rs.getString("personname")+"\",createdate:\""+formatDate(rs.getDate("createdate"))+"\",status:\""+passstatus+"\"}";
				jsonstr += ",";
			}
			jsonstr = jsonstr.substring(0,jsonstr.lastIndexOf(","));
			}
			jsonstr += "]}";
			response.getWriter().write(jsonstr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public String save() throws PersonNotFoundException{
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		RollPepodomDAO rpdao = new RollPepodomDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    String msg = "";
	    Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
    	List<PersonRole> prlist = person.getRoleList();
    	for(PersonRole pr:prlist){
    		if(pr.getName().equals("档案管理员")){
    			msg = "您是管理员无需申请";
    			//if(filetype.equals("3")){
    				try {
	    				response.getWriter().print("{success:true,msg:\""+msg+"\"}");
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			return null;
    		}
    	}
	    try{
	    	
	    	conn = dbbase.getConn();
	    	factory = new DAOFactory(conn,rpdao);
	    	//RollDAO rdao = new RollManagerAction().findDAOById(rollid);
	    	String title = "";
	    	RollDAO rdao = null;
	    	RecordDataDAO ddao = null;
	    	boolean flag = true;
	    	
	    	if(this.findDaoBydid(rollid)!=null){
    			flag = false;
    			msg = "您已申请过不能重复申请";
    				try {
	    				response.getWriter().print("{success:true,msg:\""+msg+"\"}");
	    			} catch (IOException e) {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			}
	    			return null;
	    	}else{
	    	if(filetype.equals("1")){
	    		
	    		 rdao = new RollManagerAction().findDAOById(rollid);
	    		 title = rdao.getRollname();
		    
	    	}else if(filetype.equals("2")){
	    		ddao = new RecordDataAction().findDAOById(rollid);
	    		title = ddao.getTitle();
		    	
	    	}else{
	    		
	    			BpmMAttachmentDAO matt = new FileListAction().findById(rollid);
	    			RecordDataDAO rddao = new RecordDataAction().findDAOById(matt.getRecordid());
	    			if(rddao==null){
	    				msg = "无法申请！";
	        			//if(filetype.equals("3")){
	        				try {
	    	    				response.getWriter().print("{success:true,msg:\""+msg+"\"}");
	    	    			} catch (IOException e) {
	    	    				// TODO Auto-generated catch block
	    	    				e.printStackTrace();
	    	    			}
	    	    			return null;
	    			}
	    			title = matt.getFilename();
	    		
	    	}
	    	
			if (!title.equals("")&&flag) {
				rpdao.setConnection(conn);
				rpdao.setUuid(new UUID().toString());
				rpdao.setRollid(rollid);
				rpdao.setApplicant(person.getName());
				rpdao.setApplicantid(personId);
				rpdao.setApplicantdate(new Date());
				rpdao.setIspass(1);// 1����� 2ͨ�� 3 δͨ��
				rpdao.setStarttime(formatDate(starttime));
				rpdao.setEndtime(formatDate(endtime));
				rpdao.setRollname(title);
				rpdao.setFiletype(filetype);
				rpdao.create();
				conn.commit();
				msg = "申请成功";
				if(starttime==null){
					starttime = "";
				}
				if(endtime==null){
					endtime = "";
				}
				new PepodomCreateLuceneIndex().addDoc(rpdao.getUuid(),rollid,title, filetype, personId, starttime, endtime, "1");
				try {
    				response.getWriter().print("{success:true,msg:\""+msg+"\"}");
    			} catch (IOException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
			}
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    return null;
	    
		
	}
	//修改申请信息（即开始时间结束时间）
	public void update(){
		
		JSONArray jsonArray=JSONArray.fromObject(jsonstr);
		List<RollPepodomBean> list1 = (List<RollPepodomBean>)jsonArray.toCollection(jsonArray,RollPepodomBean.class);
		for(RollPepodomBean rb:list1){
			
			updatedb(rb.getUuid(),rb.getStarttime(),rb.getEndtime(),2);
		}
		 	 
	}
	//审核是否通过
	public void updateIspass(){
		String[] pid = pids.split(",");
		for(int i=0;i<pid.length;i++){
			updatedb(pid[i],null,null,ispass);
			
		}
	}
	//修改数据库方法
	public void updatedb(String uuid,String stime,String endtime,int ispass){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		
		RollPepodomDAO rpdao = new RollPepodomDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    try{
	    	Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
	    	conn = dbbase.getConn();
	    	factory = new DAOFactory(conn,rpdao);
	    	rpdao = findDAOById(uuid);
	    	rpdao.setConnection(conn);
	    	if(!(stime==null&&endtime==null)){
	    		
	    		rpdao.setStarttime(formatDate(stime));
	    		rpdao.setEndtime(formatDate(endtime));
	    	}
	    	rpdao.setVerifier(person.getName());
	    	rpdao.setVerifierid(personId);
	    	rpdao.setVerifierdate(new Date());
	    	rpdao.setIspass(ispass);
	    	rpdao.update();
	    	conn.commit();
	    	String starttime = formatDate(rpdao.getStarttime());
	    	String etime = formatDate(rpdao.getEndtime());
	    	if(starttime==null){
	    		starttime = "";
	    	}
	    	if(etime==null){
	    		etime = "";
	    	}
	    	new PepodomCreateLuceneIndex().addDoc(uuid,rpdao.getRollid(),rpdao.getRollname(), rpdao.getFiletype(), rpdao.getApplicantid(),starttime , etime,ispass+"");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}
	
	//申请通过档案列表
	public String applicantpass(){
		//rolldao.setWhereClause("applicantid='"+personId+"' and ispass=2 and endtime>to_date('"+formatDate(new Date())+"','yyyy-mm-dd')");
    	//rolldao.addOrderBy("applicantdate", false);
    	HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
    	ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		//List list = new ArrayList();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try{
			conn = dbbase.getConn();
			String querystr = "";
			int count = 0;
			if(!title.equals("")){
				querystr += " and rollname like '%"+title+"%' ";
				//count +=1;
			}
			if(!starttime.equals("")){
				querystr += " and applicantdate>=to_date('"+starttime+"','yyyy/mm/dd')";
				
			}
			if(!endtime.equals("")){
				querystr += " and applicantdate<=to_date('"+endtime+"','yyyy/mm/dd')";
				
			}
			String edate = formatDate(new Date());
			String ws = " applicantid='"+personId+"' and filetype='1' and ispass=2 and endtime>=to_date('"+formatDate(new Date())+"','yyyy/mm/dd')";
		String countsql = "select count(*) from FDT_OA_ROLLPEPODOM where "+ws+querystr;
		st = conn.createStatement();
		rs = st.executeQuery(countsql);
		while (rs.next()) {
			totalCount = rs.getInt(1);
		}
		
		
		rolllist = new ArrayList();
		String sql = "select m.* from (select rownum r,mr.* from FDT_OA_ROLLPEPODOM mr where "+ws+querystr+" and rownum <=?) m where m.r >?";
		pst = conn.prepareStatement(sql);
		//int count = 0;
	
		pst.setInt(1,limit + start);
		pst.setInt(2,start);
		rs = pst.executeQuery();
		while (rs.next()) {
			RollPepodomBean rb = new RollPepodomBean();
			rb.setUuid(rs.getString("UUID"));
			rb.setRollid(rs.getString("ROLLID"));
			rb.setRollname(rs.getString("rollname"));
			rb.setApplicantdate(formatDate(rs.getDate("APPLICANTDATE")));
			rb.setVerifier(rs.getString("VERIFIER"));
			rb.setVerifierdate(rs.getString("VERIFIERDATE"));
			rb.setStarttime(formatDate(rs.getDate("STARTTIME")));
			rb.setEndtime(formatDate(rs.getDate("endtime")));
			rb.setIspass(rs.getInt("ispass"));
			/*rb.SETsetRollnum(rs.getString("ROLLNUM"));
			rb.setAmanuensis(rs.getString("AMANUENSIS"));
			rb.setMiji(rs.getString("MIJI"));
			rb.setTimelimit(rs.getString("TIMELIMIT"));
			rb.setStarttime(rs.getDate("STARTTIME"));
			rb.setEndtime(rs.getDate("ENDTIME"));
			rb.setPagenum(rs.getInt("PAGENUM"));
			rb.setSavedirection(rs.getString("SAVEDIRECTION"));
			rb.setYearnum(rs.getString("YEARNUM"));
			rb.setCreator(rs.getString("CREATOR"));
			rb.setCreatorid(rs.getString("CREATORID"));
	    	rb.setCreatedate(rs.getDate("CREATEDATE"));
	    	rb.setStatus(rs.getInt("STATUS"));
	    	rb.setCategory_uuid(rs.getString("CATEGORY_UUID"));*/
			rolllist.add(rb);
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    
	    	/*List list = factory.find();
	    	totalCount = list.size();*/
	    	
	    
	    return "apppass";
	}
	//申请记录列表
	public String applicantrecord(){
		
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		String forwardstr = "";
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		
		RollPepodomDAO rpdao = new RollPepodomDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    rolllist = new ArrayList();
	    try{
	    	//Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
	    	conn = dbbase.getConn();
	    	factory = new DAOFactory(conn,rpdao);
	    	
	    	if(liststatus!=null){
	    		String swhere = "";
	    		
	    		/*if(!swhere.equals("")){
	    			swhere += " and ";
	    		}*/
	    		if(!title.equals("")){
	    			swhere += " and rollname like '%"+title+"%' ";
					//count +=1;
				}
				if(!starttime.equals("")){
					swhere += " and applicantdate>=to_date('"+starttime+"','yyyy/mm/dd')";
					
				}
				if(ispass<4&&ispass!=0){
	    			swhere = " and ispass="+ispass;
	    		}
				/*if(!endtime.equals("")){
					swhere += " and ";
					
				}*/
	    		if(liststatus==1){
	    			
	    			rpdao.setWhereClause(" applicantid='"+personId+"'"+swhere);
	    			forwardstr = "recordlist";
	    			//list = factory.find();
	    		}else if(liststatus==2){
	    			//rpdao.setWhereClause(" rollid='"+rollid+"'"+swhere);
	    			/*rpdao.setRollname(title);
	    			rpdao.addOrderBy("status", true);
	    			rpdao.addOrderBy("applicantdate", false);
	    			list = factory.find();*/
	    			forwardstr = "verilist";
	    		}
	    		
	    		String countsql = "select count(*) from FDT_OA_ROLLPEPODOM";
	    		String sql = "select m.* from (select rownum r,mr.* from FDT_OA_ROLLPEPODOM mr where ";
	    		if(!swhere.equals("")){
	    			countsql += " where 1=1 "+swhere;
	    			sql += " rownum <=?"+swhere+") m where m.r >?";
	    		}else{
	    			sql += " rownum <=?) m where m.r >?";
	    		}
	    		sql += " order by ispass,applicantdate desc";
	    		
	    		st = conn.createStatement();
	    		rs = st.executeQuery(countsql);
	    		while (rs.next()) {
	    			totalCount = rs.getInt(1);
	    		}
	    		
	    		
	    		
	    		
	    		//if(){}
	    		pst = conn.prepareStatement(sql);
	    		//int count = 0;
	    	
	    		pst.setInt(1,limit + start);
	    		pst.setInt(2,start);
	    		rs = pst.executeQuery();
	    		while (rs.next()) {
	    			RollPepodomBean rb = new RollPepodomBean();
	    			rb.setUuid(rs.getString("UUID"));
	    			rb.setRollid(rs.getString("ROLLID"));
	    			rb.setRollname(rs.getString("rollname"));
	    			rb.setApplicant(rs.getString("APPLICANT"));
	    			rb.setApplicantdate(formatDate(rs.getDate("applicantdate")));
	    			rb.setVerifier(rs.getString("VERIFIER"));
	    			rb.setVerifierdate(rs.getString("VERIFIERDATE"));
	    			rb.setStarttime(formatDate(rs.getDate("STARTTIME")));
	    			rb.setEndtime(formatDate(rs.getDate("endtime")));
	    			rb.setIspass(rs.getInt("ISPASS"));
	    			rb.setFiletype(rs.getString("filetype"));
	    			/*rb.SETsetRollnum(rs.getString("ROLLNUM"));
	    			rb.setAmanuensis(rs.getString("AMANUENSIS"));
	    			rb.setMiji(rs.getString("MIJI"));
	    			rb.setTimelimit(rs.getString("TIMELIMIT"));
	    			rb.setStarttime(rs.getDate("STARTTIME"));
	    			rb.setEndtime(rs.getDate("ENDTIME"));
	    			rb.setPagenum(rs.getInt("PAGENUM"));
	    			rb.setSavedirection(rs.getString("SAVEDIRECTION"));
	    			rb.setYearnum(rs.getString("YEARNUM"));
	    			rb.setCreator(rs.getString("CREATOR"));
	    			rb.setCreatorid(rs.getString("CREATORID"));
	    	    	rb.setCreatedate(rs.getDate("CREATEDATE"));
	    	    	rb.setStatus(rs.getInt("STATUS"));
	    	    	rb.setCategory_uuid(rs.getString("CATEGORY_UUID"));*/
	    			rolllist.add(rb);
	    		}
	    		
	    	}
	    	//rpdao.addOrderBy("applicantdate", false);
	    	//List list = factory.find();
	    	/*List listbean = new ArrayList();
	    	for(int i=0;i<list.size();i++){
	    		RollPepodomBean rpbean = daozh((RollPepodomDAO)list.get(i));
	    		listbean.add(rpbean);
	    	}
	    	totalCount = listbean.size();
			int count = start + limit;
			if(count>totalCount){
				rolllist = listbean.subList(start, totalCount);
			}else{
				rolllist = listbean.subList(start, count);
			}*/
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    return forwardstr;
	}
	
	//可申请案卷列表
	public void waitApplicantRoll(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		String forwardstr = "";
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		
		RollDAO rolldao = new RollDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    rolllist = new ArrayList();
	    try{
	    	conn = dbbase.getConn();
	    	String countsql = "select count(*) from FDT_OA_ROLL where ";
    		String sql = "select m.* from (select rownum r,mr.* from FDT_OA_ROLL mr where ";
    		String wheresql = " status=1";
    		if(rollname!=null&&!rollname.equals("")){
    			wheresql += " and rollname='"+rollname+"'";
    		}
    		if(rollnum!=null&&!rollnum.equals("")){
    			wheresql += " and rollnum='"+rollnum+"'";
    		}
    		if(yearnum!=null&&!yearnum.equals("")){
    			wheresql += " and yearnum='"+yearnum+"'";
    		}
    		
    			
    			String enddate = formatDate(new Date());
    			wheresql += " and uuid not in (select rollid from fdt_oa_rollpepodom where filetype=1 and endtime>=to_date('"+enddate+"','yyyy/mm/dd'))";
    			countsql += " "+wheresql;
    			sql += " rownum <=? and "+wheresql+") m where m.r >?";
    	
    		st = conn.createStatement();
    		rs = st.executeQuery(countsql);
    		while (rs.next()) {
    			totalCount = rs.getInt(1);
    		}
    		
    		pst = conn.prepareStatement(sql);
    		pst.setInt(1,limit + start);
    		pst.setInt(2,start);
    		rs = pst.executeQuery();
    		String jsonstr = "{totalCount:"+totalCount+",root:[";
    		while(rs.next()){
    			RollBean bean = new RollBean();
    			bean.setUuid(rs.getString("uuid"));
    			bean.setRollname(rs.getString("rollname"));
    			bean.setRollnum(rs.getString("rollnum"));
    			bean.setYearnum(rs.getString("yearnum"));
    			bean.setAmanuensis(rs.getString("amanuensis"));
    	    	bean.setMiji(rs.getString("miji"));
    	    	bean.setTimelimit(rs.getString("timelimit"));
    	    	bean.setStarttime(formatDate(rs.getDate("starttime")));
    	    	bean.setEndtime(formatDate(rs.getDate("endtime")));
    	    	bean.setPagenum(rs.getInt("pagenum"));
    	    	bean.setSavedirection(rs.getString("savedirection"));
    	    	bean.setCreator(rs.getString("creator"));
    	    	bean.setCreatorid(rs.getString("creatorid"));
    	    	bean.setCreatedate(formatDate(rs.getDate("createdate")));
    	    	bean.setStatus(0);
    	    	bean.setCategoryid(rs.getString("category_uuid"));
    	    	//rolllist.add(bean);
    	    	jsonstr += "{uuid:\""+bean.getUuid()+"\",rollname:\""+bean.getRollname()+"\",rollnum:\""+bean.getRollnum()+"\",yearnum:\""+bean.getYearnum()+"\",timelimit:\""+bean.getTimelimit()+"\"}";
    	    	jsonstr += ",";
    		}
    		if(totalCount!=0){
    			jsonstr = jsonstr.substring(0, jsonstr.lastIndexOf(","));
    		}
    		
    		jsonstr += "]}";
    		response.getWriter().write(jsonstr);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    
	    
	}
	
	public RollPepodomDAO findDaoBydid(String did){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		RollPepodomDAO rpdao = new RollPepodomDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    try{
	    	//Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
	    	conn = dbbase.getConn();
	    	factory = new DAOFactory(conn,rpdao);
	    	if(!this.filetype.equals("3")){
	    		rpdao.setWhereClause(" rollid='"+did+"' and applicantid='"+personId+"' and endtime>=to_date('"+formatDate(new Date())+"','yyyy/mm/dd')");
	    	}else{
	    		if(status!=null&&status.equals("2")){
	    			
	    			rpdao.setWhereClause(" rollid='"+did+"' and applicantid='"+personId+"' and ispass=2");
	    		}else{
	    			rpdao.setWhereClause(" rollid='"+did+"' and applicantid='"+personId+"'");
	    		}
	    	}
	    	//rpdao.setWhereClause(" rollid='"+did+"' and applicantid='"+personId+"' and endtime>=to_date('"+formatDate(new Date())+"','yyyy/mm/dd')");
	    	Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
	    	List<PersonRole> prlist = person.getRoleList();
	    	for(PersonRole pr:prlist){
	    		if(pr.getName().equals("档案管理员")){
	    			RollPepodomDAO ba = new RollPepodomDAO();
		    		ba.setIspass(2);
		    		return ba;
	    		}
	    	}
	    	/*if(personId.equals("1fbea09-1323400d8b5-604a336da963c28d833e2a810c46f356")){
	    		RollPepodomDAO ba = new RollPepodomDAO();
	    		ba.setIspass(2);
	    		return ba;
	    	}*/
	    	List list = factory.find();
	    	if(list.size()>0){
	    		RollPepodomDAO ba = (RollPepodomDAO)list.get(0);	
	    		return ba;
	    	}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    return null;
	}
	
	public RollPepodomDAO findDAOById(String uuid){
		RollPepodomDAO rpdao = new RollPepodomDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    try{
	    	//Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
	    	conn = dbbase.getConn();
	    	factory = new DAOFactory(conn,rpdao);
	    	//rpdao.setApplicantid(personId);
	    	rpdao.setUuid(uuid);
	    	RollPepodomDAO ba = (RollPepodomDAO)factory.findByPrimaryKey();
	    	if(ba!=null){
	    		return ba;
	    	}
	    	
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    return null;
	}
	
	public RollPepodomBean daozh(RollPepodomDAO rpdao){
		RollPepodomBean rpbean = new RollPepodomBean();
		rpbean.setUuid(rpdao.getUuid());
		rpbean.setRollid(rpdao.getRollid());
		rpbean.setRollname(rpdao.getRollname());
		rpbean.setApplicant(rpdao.getApplicant());
		rpbean.setApplicantdate(formatDate(rpdao.getApplicantdate()));
		rpbean.setIspass(rpdao.getIspass());
		rpbean.setVerifier(rpdao.getVerifier());
		rpbean.setVerifierid(rpdao.getVerifierid());
		rpbean.setVerifierdate(formatDate(rpdao.getVerifierdate()));
		rpbean.setStarttime(formatDate(rpdao.getStarttime()));
		rpbean.setEndtime(formatDate(rpdao.getEndtime()));
		return rpbean;
	}
	
	public Date formatDate(String datestr){
		if(datestr==null){
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(datestr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(date!=null){
			return sdf.format(date);
		}else{
			return null;
		}
		
	}
	
	private List rolllist;
	private int totalCount;
	private int start;
	private int limit;
	private Integer liststatus;
	
	private String rollid;
	private String starttime;
	private String endtime;
	
	private String rollname;
	private String rollnum;
	private String yearnum;
	
	private String jsonstr;
	
	private String pids;
	private int ispass;
	private String filetype;
	
	//查询字段
	private String title;
	private String status;//是否通过审核
	private String applicantdate;
	
	public List getRolllist() {
		return rolllist;
	}

	public void setRolllist(List rolllist) {
		this.rolllist = rolllist;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	public Integer getListstatus() {
		return liststatus;
	}

	public void setListstatus(Integer liststatus) {
		this.liststatus = liststatus;
	}

	public String getRollid() {
		return rollid;
	}

	public void setRollid(String rollid) {
		this.rollid = rollid;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getJsonstr() {
		return jsonstr;
	}

	public void setJsonstr(String jsonstr) {
		this.jsonstr = jsonstr;
	}

	public String getPids() {
		return pids;
	}

	public void setPids(String pids) {
		this.pids = pids;
	}

	public int getIspass() {
		return ispass;
	}

	public void setIspass(int ispass) {
		this.ispass = ispass;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getApplicantdate() {
		return applicantdate;
	}

	public void setApplicantdate(String applicantdate) {
		this.applicantdate = applicantdate;
	}

	public String getRollname() {
		return rollname;
	}

	public void setRollname(String rollname) {
		this.rollname = rollname;
	}

	public String getRollnum() {
		return rollnum;
	}

	public void setRollnum(String rollnum) {
		this.rollnum = rollnum;
	}

	public String getYearnum() {
		return yearnum;
	}

	public void setYearnum(String yearnum) {
		this.yearnum = yearnum;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	
	
}
