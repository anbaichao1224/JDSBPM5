package kzxd.electronicfile.action;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kzxd.electronicfile.dao.RecordCategoryDAO;
import kzxd.electronicfile.entity.RecordCategoryBean;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class RecordCategoryAction implements Action {

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void save(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		RecordCategoryDAO rcategory = new RecordCategoryDAO();
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try {
			Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, rcategory);
			rcategory.setConnection(conn);
			
			rcategory.setCategoryname(rbean.getCategoryname());
			if(rbean.getParentid()!=null){
				rcategory.setParentid(rbean.getParentid());
			}
			rcategory.setCreator(person.getName());
			rcategory.setCreatorid(person.getID());
			
			if(!rbean.getUuid().equals("")){
				findById();
				rcategory.setCreatedate(findDAOById().getCreatedate());
				rcategory.setUuid(rbean.getUuid());
				rcategory.update();
			}else{
				rcategory.setCreatedate(new Date());
				rcategory.setUuid(new UUID().toString());
				rcategory.create();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String findById(){
		RecordCategoryDAO rcategory = findDAOById();
		if(rcategory!=null){
			rbean = daozh(rcategory);
			return "categoryDetail";
		}else{
			return null;
		}
	}
	
	public RecordCategoryDAO findDAOById(){
		RecordCategoryDAO rcategory = new RecordCategoryDAO();
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, rcategory);
			rcategory.setUuid(rbean.getUuid());
			rcategory = (RecordCategoryDAO)factory.findByPrimaryKey();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rcategory;
	}
	
	public String getTree(){
		return SUCCESS;
	}
	public String getTreeJson(){
		return new CategoryTreeDisplay().getChildTree(categoryId, bool, "true");
	}
	public List findByPid(String pid){
		RecordCategoryDAO rcategory = new RecordCategoryDAO();
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, rcategory);
			if(pid==null){
				rcategory.setWhereClause(" parent_id is null");
			}else{
				rcategory.setParentid(pid);
			}
			list = factory.find();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public RecordCategoryBean daozh(RecordCategoryDAO rdao){
		RecordCategoryBean bean = new RecordCategoryBean();
		bean.setUuid(rdao.getUuid());
		bean.setCategoryname(rdao.getCategoryname());
		bean.setParentid(rdao.getParentid());
		bean.setCreator(rdao.getCreator());
		bean.setCreatorid(rdao.getCreatorid());
		bean.setCreatedate(formatDate(rdao.getCreatedate()));
		return bean;
	}
	
	public String formatDate(Date date){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		if(date==null){
			return null;
		}
		return format.format(date);
	}
	/*private String uuid;
	private String categoryname;
	private String parentid;*/
	private RecordCategoryBean rbean;
	
	//电子目录树形结构属性
	private String categoryId;
	private String treename;
	private boolean bool = true;
	
	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getTreename() {
		return treename;
	}

	public void setTreename(String treename) {
		this.treename = treename;
	}

	public RecordCategoryBean getRbean() {
		return rbean;
	}

	public void setRbean(RecordCategoryBean rbean) {
		this.rbean = rbean;
	}

	public boolean isBool() {
		return bool;
	}

	public void setBool(boolean bool) {
		this.bool = bool;
	}
	
	
	
}
