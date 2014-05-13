package kzxd.instantprompt.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import kzxd.instantprompt.dao.PromptBean;
import kzxd.instantprompt.dao.PromptPersonBean;
import kzxd.instantprompt.dao.PromptPersonDAO;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.sf.json.JSONArray;

import com.opensymphony.xwork2.Action;

public class PromptPersonAction implements Action {

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void saves(){
		if(personIds!=null&&!personIds.equals("")){
			String[] personIdArr = personIds.split(",");
			for(String id:personIdArr){
				save(promIds,id);
			}
		}
		
	}
	
	public void save(String promId,String personId){
		//HttpServletRequest request = ServletActionContext.getRequest();
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		PromptPersonDAO ppdao = new PromptPersonDAO();
		String uuid = new UUID().toString();
		try{
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			conn = dbbase.getConn();
			ppdao.setConnection(conn);
			ppdao.setUuid(uuid);
			ppdao.setPersonId(personId);
			ppdao.setPersonname(person.getName());
			ppdao.setPromptId(promId);
			ppdao.setIscancel(0);
			ppdao.create();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void deletes(){
		if(promIds!=null&&!promIds.equals("")){
			String[] promIdArr = promIds.split(",");
			for(String id:promIdArr){
				deleteById(id);
			}
		}
	}
	
	
	public void delete(String promId){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		PromptPersonDAO ppdao = new PromptPersonDAO();
		String uuid = new UUID().toString();
		try{
			conn = dbbase.getConn();
			ppdao.setConnection(conn);
			ppdao.setPromptId(promId);
			//ppdao.delete();
			ppdao.batchDelete();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void deleteById(String id){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		PromptPersonDAO ppdao = new PromptPersonDAO();
		String uuid = new UUID().toString();
		try{
			conn = dbbase.getConn();
			ppdao.setConnection(conn);
			ppdao.setUuid(id);
			//ppdao.delete();
			ppdao.delete();
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void cancel(){
		
		String[] promIdArr = promIds.split(",");
		for(String id:promIdArr){
			PromptPersonDAO pdao = getByPromId(id);
			DBBeanBase dbbase = new DBBeanBase("bpm", true);
			Connection conn = null;
			DAOFactory factory = null;
			try{
				conn = dbbase.getConn();
				pdao.setConnection(conn);
				pdao.setIscancel(1);//将状态改为已取消
				pdao.update();
				conn.commit();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getPersonByPid(){
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try{
			conn = dbbase.getConn();
			
			
			String sql = "select m.* from (select rownum r,mr.begindate,pp.personname,pp.iscancel,pp.uuid from kzxd_prompt mr,kzxd_prompt_person pp where pp.promptid='"+promIds+"' and pp.promptid=mr.uuid) m order by m.iscancel ";
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			pplist = new ArrayList<PromptPersonBean>();
			
			while(rs.next()){
				PromptPersonBean pbean = new PromptPersonBean();
				pbean.setUuid(rs.getString("uuid"));
				pbean.setPersonname(rs.getString("personname"));
				pbean.setBegindate(formatDate(rs.getDate("begindate"),"yyyy-MM-dd"));
				pbean.setIscancel(rs.getInt("iscancel"));
				pplist.add(pbean);
			}
			totalCount = pplist.size();
		}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return "pplist";
	}
	
	public PromptPersonDAO getByPromId(String promId){
		HttpServletRequest request = ServletActionContext.getRequest();
		String personId = request.getSession().getAttribute("personId").toString();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		PromptPersonDAO ppdao = new PromptPersonDAO();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn,ppdao);
			ppdao.setPromptId(promId);
			ppdao.setPersonId(personId);
			List list = factory.find();
			if(list!=null&&list.size()>0){
				return (PromptPersonDAO)list.get(0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public String formatDate(Date date,String formatstr){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(formatstr);
			return sdf.format(date);
		}
		return null;
	}
	
	private List<PromptPersonBean> pplist;
	private String promIds;
	private String personIds;
	private int totalCount;
	
	public String getPromIds() {
		return promIds;
	}

	public void setPromIds(String promIds) {
		this.promIds = promIds;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<PromptPersonBean> getPplist() {
		return pplist;
	}

	public void setPplist(List<PromptPersonBean> pplist) {
		this.pplist = pplist;
	}

	public String getPersonIds() {
		return personIds;
	}

	public void setPersonIds(String personIds) {
		this.personIds = personIds;
	}
	
	

}
