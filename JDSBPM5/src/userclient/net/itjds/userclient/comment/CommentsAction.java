 
package net.itjds.userclient.comment;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;



import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ProcessDef;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.common.util.StringUtility;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.oa.OAException;
import net.itjds.oa.OAUtil;

import net.itjds.userclient.comment.dao.BpmCommentsDAO;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;

public class CommentsAction extends BPMActionBase{
	private String formName;
	private String json;
	private String commenttext;
	private List<CommentBean> commentList;
	private BpmCommentsDAO bpmCommentsDAO;
	private String fieldName;
	private String uuid;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String cmsManager(){
		return SUCCESS;
	}
	public BpmCommentsDAO getBpmCommentsDAO(){
		if (bpmCommentsDAO==null){
			bpmCommentsDAO=new BpmCommentsDAO();
		}
		return bpmCommentsDAO;
	}
	
	
	public String delData() throws SQLException{
		if(this.uuid != null && this.uuid.length()>0){
			DBBeanBase dbbase=new DBBeanBase("bpm");
			Connection conn=dbbase.getConn();
			BpmCommentsDAO bpmCommentsDAO =this.getBpmCommentsDAO();
			bpmCommentsDAO.setConnection(conn);
			bpmCommentsDAO.setPrimaryKeyValue(this.uuid);
			try {
				bpmCommentsDAO.delete();
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			conn.close();
		}
		return SUCCESS;
	}
	
	public String editData() throws SQLException{
		if (this.uuid!=null){
			DBBeanBase dbbase=new DBBeanBase("bpm");
			Connection conn=dbbase.getConn();
			BpmCommentsDAO bpmCommentsDAO =this.getBpmCommentsDAO();
			bpmCommentsDAO.setConnection(conn);
			bpmCommentsDAO.setPrimaryKeyValue(this.uuid);
		
			try {
				this.bpmCommentsDAO=(BpmCommentsDAO) bpmCommentsDAO.findByPrimaryKey();
			} catch (DAOException e1) {
				conn.close();
				e1.printStackTrace();
			}
			conn.close();
		}
		
		return SUCCESS;
	}
	
	public List<CommentBean> getCommentList() throws DAOException, SQLException {
	
		HttpServletRequest request = ServletActionContext.getRequest();
		if (commentList==null){
			commentList=new ArrayList<CommentBean> ()	;
			DBBeanBase dbbase=new DBBeanBase("bpm");
			Connection conn=dbbase.getConn();
			BpmCommentsDAO bpmCommentsDAO=new BpmCommentsDAO();
			bpmCommentsDAO.setFormname(formName);
			bpmCommentsDAO.setFieldname(this.fieldName);
		//	String posititonname = bpmCommentsDAO.getPosition();
		//	System.out.println("----------------------------"+posititonname);
			ProcessDef processDef = null;
			BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
			WorkflowClientService client = bpmUserClientUtil.getClient();
			try{
			if (this.getActivityInst()!=null){
				bpmCommentsDAO.setProcessinstid(this.getActivityInst().getProcessInstId());
				//System.out.println(this.getActivityInst().getProcessInst().getProcessDefId());
				
				processDef = client.getProcessDef(this.getActivityInst().getProcessInst().getProcessDefId());
			}else if(this.getActivityInstHistory()!=null){
				bpmCommentsDAO.setProcessinstid(this.getActivityInstHistory().getProcessInstId());
				//processDef = client.getProcessDef(this.getActivityInstHistory().getProcessInst().getProcessDefId());
				processDef = client.getProcessDef(this.getActivityInstHistory().getProcessInst().getProcessDefId());
			}
			}catch(BPMException e){
				
			}
			bpmCommentsDAO.addOrderBy("createdate", true);
			DAOFactory  factory = new DAOFactory(conn,bpmCommentsDAO);
			List<BpmCommentsDAO> daolist=factory.find();
			conn.close();
			for (int k=0;k<daolist.size();k++ ){
				if(daolist.get(k).getCreateuser()!=null){
					commentList.add(new CommentBean(daolist.get(k)));
				}
				
			}
		}
		
		return commentList;
	}

	class CommentBean{
		public BpmCommentsDAO comment;
		public Person person;
		public boolean isEdit;
		public String commenttext;
		public String personid;
		public String position;
		
		public String findpersonid(String personid2) throws java.sql.SQLException, SQLException{
			 String personid1=null;
			Statement stmt = null;
			StringBuffer sql = null;
			DBBeanBase dbbase = new DBBeanBase("org");
			Connection conn = dbbase.getConn();
			try {
				stmt = conn.createStatement();
				sql = new StringBuffer();
				sql.append("select personid from ro_person where personid='");
				sql.append(personid2);
				sql.append("'");
				ResultSet rs = stmt.executeQuery(sql.toString());
				if(rs != null && rs.next()){
					personid1 = rs.getString("personid");
				}
				stmt.close();
				conn.close();
				dbbase.close();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
			 return personid1;
		}
		public CommentBean(BpmCommentsDAO bpmCommentsDAO) throws SQLException{
			this.comment=bpmCommentsDAO;
			try {
					String personid2 = bpmCommentsDAO.getCreateuser();
					String personid1=this.findpersonid(personid2);
					if(personid1!=null){
						this.person=OrgManagerFactory.getOrgManager().getPersonByID(bpmCommentsDAO.getCreateuser());
					}
				
					
					
				
			} catch (PersonNotFoundException e) {
				e.printStackTrace();
			}
		}


		public BpmCommentsDAO getComment() {
			return comment;
		}


		public void setComment(BpmCommentsDAO comment) {
			this.comment = comment;
		}


		public Person getPerson() {
			return person;
		}
		public void setPerson(Person person) {
			this.person = person;
		}

		public boolean isEdit() throws BPMException {
			isEdit=false;
			ActivityInst activityInst=CommentsAction.this.getActivityInst();
			if (activityInst!=null && CommentsAction.this.getCurrPerson().getID().equals(this.person.getID()) ){
				isEdit=true;
			}
			if(activityInst.getState().equals(ActivityInst.STATUS_READ) ||activityInst.getState().equals(ActivityInst.STATUS_ENDREAD	))
			{
				isEdit=false;
			}
			return isEdit;
		}


		public String getCommenttext() {
			String commenttext = this.comment.getComments();
			commenttext = StringUtility.escapeJSSpecial(commenttext);
			return commenttext;
		}


		public String getPosition() {
			String position = this.comment.getPosition();
			return position;
		}


		public void setPosition(String position) {
			this.position = position;
		}
	}
	
	public String saveComments()throws Exception{
		BpmCommentsDAO bpmCommentsDAO= this.getBpmCommentsDAO();
		DBBeanBase dbbase=new DBBeanBase("bpm",true);
		Connection conn=dbbase.getConn();
		 bpmCommentsDAO.setConnection(conn);
		 bpmCommentsDAO.setCreateuser(this.getCurrPerson().getID());
		 bpmCommentsDAO.setPersonindex(this.getCurrPerson().getIndex());
		 bpmCommentsDAO.setGroupindex(this.getCurrPerson().getOrgs()[0].getIndex());
		 System.out.println("person index is " + this.getCurrPerson().getIndex());
		 bpmCommentsDAO.setActivityinstid(this.getActivityInst().getActivityInstId());
		 bpmCommentsDAO.setProcessinstid(this.getActivityInst().getProcessInstId());
		 bpmCommentsDAO.setActivitydefid(this.getActivityInst().getActivityDefId())	; 
		 if (bpmCommentsDAO.getCreatedate()==null||bpmCommentsDAO.getCreatedate().equals("")){
			 bpmCommentsDAO.setCreatedate(new Date().toLocaleString());
		 }
		 if (bpmCommentsDAO.getUuid()==null ||bpmCommentsDAO.getUuid().equals("")){
			 bpmCommentsDAO.setUuid(new UUID().toString());
			 bpmCommentsDAO.create();
		 }else{
			 bpmCommentsDAO.update(true);
		 }
		 conn.commit();
		 conn.close();
		return SUCCESS;
	}
	public String execute() throws Exception {
	
		return SUCCESS;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getCommenttext() {
		String commenttext = this.bpmCommentsDAO.getComments();
		commenttext = StringUtility.escapeJSSpecial(commenttext);
		return commenttext;
	}


}
