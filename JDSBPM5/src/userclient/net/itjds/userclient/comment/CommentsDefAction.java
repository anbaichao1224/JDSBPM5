package net.itjds.userclient.comment;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;

import net.itjds.userclient.comment.CommentsDAO;
import net.itjds.userclient.common.BPMActionBase;






public class CommentsDefAction extends BPMActionBase{
	
	private String id;
	private String name;
	private String fid;
	private String json;
	private String comments;
	private Integer start = 0, limit = 15;
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	
	public String cmsManager(){
		return SUCCESS;
	}


	
	public String commentsDefList()throws Exception{
		List list=new ArrayList();
	    Person person=getCurrPerson();
		DBBeanBase dbbase=new DBBeanBase("bpm",true);
		Connection conn=dbbase.getConn();
		CommentsDAO commentsDAO=null;
		DAOFactory factory=null;
		 String str="";
		 commentsDAO= new CommentsDAO();
		 commentsDAO.setWhereClause("userid ='"+ person.getID() +"' or userid='public'");
		 factory = new DAOFactory(conn,commentsDAO);
		 list=factory.find();
		 conn.close();
			if (start==null){
				start=0;
			}
		int len = Math.min(list.size(), start + limit);
		 json="{totalCount:"+list.size()+",data:[";
		   for (int i = start; len > i; i++) {
				 CommentsDAO mo=new CommentsDAO();
				 mo=(CommentsDAO)list.get(i);
				 str+="{" 
					    +" id:'"+mo.getId()
					    +"',text:'"+mo.getText()
						+"',desc:'"+mo.getText()
					
				     +"'},";
			 }
		   if(!"".equals(str)){
				str=str.substring(0,str.length()-1);
				}
				json+=str;
				json+="]}";
		
		return SUCCESS;
	}
	public String removeCommentsDef()throws Exception{
		DBBeanBase dbbase=new DBBeanBase("bpm",true);
		Connection conn=dbbase.getConn();
		DAOFactory factory=null;
		CommentsDAO commentsDAO= new CommentsDAO();
		if(id.length()<10){
			return INPUT;
		}else{
		 factory = new DAOFactory(conn,commentsDAO);
		 factory.setDAO(commentsDAO);
		 commentsDAO.setConnection(conn);
		 commentsDAO.setId(id);
		 commentsDAO.delete();
		 conn.commit();
		 conn.close();
		}
		return SUCCESS;
	}
	public String saveCommentsDef()throws Exception{
		Person person=(Person) ActionContext.getContext()
		.getValueStack().findValue("$currPerson");
		CommentsDAO commentsDAO= new CommentsDAO();
		DBBeanBase dbbase=new DBBeanBase("bpm",true);
		Connection conn=dbbase.getConn();
		DAOFactory factory=null;
		 factory = new DAOFactory(conn,commentsDAO);
		 factory.setDAO(commentsDAO);
		 commentsDAO.setConnection(conn);
		 Date date=new Date();
		 commentsDAO.setId(date.toLocaleString());
		 commentsDAO.setUserid(person.getID());
		// String str=new String(comments.getBytes("iso-8859-1"));
		 commentsDAO.setText(comments);
		 commentsDAO.create();
		 conn.commit();
		 conn.close();
		return SUCCESS;
	}
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}


}
