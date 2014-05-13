package kzxd.instantprompt.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import kzxd.instantprompt.dao.PromptBean;
import kzxd.instantprompt.dao.PromptDAO;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.sf.json.JSONArray;

import com.opensymphony.xwork2.Action;

public class PromptAction implements Action {

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String list(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String personId = request.getSession().getAttribute("personId").toString();
		//Person person = (Person)OrgManagerFactory.getOrgManager().getPersonByID(personId);
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		PromptDAO promdao = new PromptDAO(); 
		try {
			
			conn = dbbase.getConn();
			String querystr = "";
			int count = 0;
			String countsql = "select count(*) from KZXD_PROMPT mr where mr.creatorid='"+personId+"'";
			st = conn.createStatement();
			rs = st.executeQuery(countsql);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
			String sql = "select m.* from (select rownum r,mr.* from (select * from kzxd_prompt order by createdate desc) mr where mr.creatorid=? and rownum<=?) m where m.r>?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, personId);
			pst.setInt(2,limit + start);
			pst.setInt(3,start);
			rs = pst.executeQuery();
			mypromptlist = new ArrayList();
			while (rs.next()) {
				
				PromptBean pbean = new PromptBean();
				pbean.setUuid(rs.getString("uuid"));
				pbean.setTitle(rs.getString("title"));
				pbean.setContent(rs.getString("content"));
				pbean.setCreatedate(formatDate(rs.getTimestamp("createdate"),"yyyy-MM-dd HH:mm:ss"));
				pbean.setCreator(rs.getString("creator"));
				pbean.setBegindate(formatDate(rs.getDate("begindate"),"yyyy-MM-dd"));
				mypromptlist.add(pbean);
			}
			/*factory = new DAOFactory(conn, promdao);
			promdao.setCreatorid(personId);
			promdao.addOrderBy("createdate", false);
			List<PromptDAO> list = factory.find();
			mypromptlist = new ArrayList();
			for(PromptDAO pdao:list){
				PromptBean pbean = daozhBean(pdao);
				mypromptlist.add(pbean);
			}
			totalCount = mypromptlist.size();*/
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
		return "list";
	}
	
	public String recelist(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String personId = request.getSession().getAttribute("personId").toString();
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try{
			conn = dbbase.getConn();
			String querystr = "";
			int count = 0;
			String countsql = "select count(*) from KZXD_PROMPT mr where UUID IN(select promptid from kzxd_prompt_person where personid='"+personId+"')";
			st = conn.createStatement();
			rs = st.executeQuery(countsql);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
			//String sql = "select m.* from (select rownum r,mr.* from KZXD_PROMPT mr where mr.uuid in (select promptid from kzxd_prompt_person where personid='"+personId+"')  and rownum <=? order by mr.createdate desc) m where  m.r >?";
			String sql = "select m.* from (select rownum r,mr.* from (select mrr.*,pp.iscancel from kzxd_prompt mrr,kzxd_prompt_person pp where pp.personid='"+personId+"' and pp.promptid=mrr.uuid order by pp.iscancel) mr where rownum<=?) m where m.r>?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1,limit + start);
			pst.setInt(2,start);
			rs = pst.executeQuery();
			mypromptlist = new ArrayList();
			while (rs.next()) {
				PromptBean pbean = new PromptBean();
				pbean.setUuid(rs.getString("uuid"));
				pbean.setTitle(rs.getString("title"));
				pbean.setContent(rs.getString("content"));
				pbean.setCreatedate(formatDate(rs.getTimestamp("createdate"),"yyyy-MM-dd HH:mm:ss"));
				pbean.setCreator(rs.getString("creator"));
				pbean.setBegindate(formatDate(rs.getDate("begindate"),"yyyy-MM-dd"));
				pbean.setIscancel(rs.getInt("iscancel"));
				mypromptlist.add(pbean);
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
		return "recelist";
	}
	
	
	public void getPromList(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String personId = request.getSession().getAttribute("personId").toString();
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try{
			conn = dbbase.getConn();
			
			//String sql = "select m.* from (select rownum r,mr.* from KZXD_PROMPT mr where mr.uuid in (select promptid from kzxd_prompt_person where personid='"+personId+"')  and rownum <=? order by mr.createdate desc) m where  m.r >?";
			String newdate = formatDate(new Date(),"yyyy-MM-dd");
			String sql = "select m.* from (select rownum r,mr.*,pp.iscancel from kzxd_prompt mr,kzxd_prompt_person pp where pp.personid='"+personId+"' and pp.iscancel=0 and pp.promptid=mr.uuid and mr.begindate<=to_date('"+newdate+"','yyyy/mm/dd')) m order by m.createdate desc";
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			List<PromptBean> list = new ArrayList<PromptBean>();
			int count = 0;
			String jsonstr = "";
			while(rs.next()){
				/*PromptBean pbean = new PromptBean();
				pbean.setUuid(rs.getString("uuid"));
				pbean.setTitle(rs.getString("title"));
				pbean.setContent(rs.getString("content"));
				pbean.setCreator(rs.getString("creator"));
				list.add(pbean);*/
				
				jsonstr +="{\"uuid\":\""+rs.getString("uuid")+"\",\"title\":\"<a href=javascript:void(0) onclick=window.top.openPrompt('"+rs.getString("uuid")+"','"+rs.getString("title")+"','"+rs.getString("content")+"','"+formatDate(rs.getDate("begindate"),"yyyy-MM-dd")+"')>"+rs.getString("title")+"</a>\"},";//,\"iscancel\":\"<a href=javascript:void(0) onclick=window.top.cancelPrompt('"+rs.getString("uuid")+"')>»°œ˚Ã·–—</a>\"},";
				count += 1;
			}
			if(!jsonstr.equals("")){
				jsonstr = jsonstr.substring(0,jsonstr.lastIndexOf(","));
			}
			jsonstr = "[" + jsonstr + "]";
			jsonstr = "{totalCount:"+count+",root:"+jsonstr+"}";
			
			//JSONArray  ja = JSONArray.fromObject(list);
			
			response.getWriter().write(jsonstr);
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
	
	public void getPromptCount(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		String personId = request.getSession().getAttribute("personId").toString();
		ResultSet rs = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try{
			conn = dbbase.getConn();
			String querystr = "";
			int count = 0;
			String newdate = formatDate(new Date(),"yyyy-MM-dd");
			String countsql = "select count(*) from (select rownum r from kzxd_prompt mr,kzxd_prompt_person pp where pp.personid='"+personId+"' and pp.iscancel=0 and pp.promptid=mr.uuid and mr.begindate<=to_date('"+newdate+"','yyyy/mm/dd')) m";
			st = conn.createStatement();
			rs = st.executeQuery(countsql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			response.getWriter().write(count+"");
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
	public void save(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String personId = request.getSession().getAttribute("personId").toString();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		PromptDAO promdao = new PromptDAO(); 
		try {
			Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			String uuid = new UUID().toString();
			conn = dbbase.getConn();
			promdao = beanzhDao(prombean);
			promdao.setConnection(conn);
			
			promdao.setCreator(person.getName());
			promdao.setCreatorid(personId);
			
			if(prombean.getBegindate()==null){
				promdao.setBegindate(new Date());
			}
			if(prombean.getUuid()!=null&&!prombean.getUuid().equals("")){
				promdao.update();
				
			}else{
				promdao.setCreatedate(new Date());
				promdao.setUuid(uuid);
				promdao.create();
			}
			
			conn.commit();
			if(personIds!=null&&!personIds.equals("")){
				String[] personIdArr = personIds.split(",");
				PromptPersonAction ppAct = new PromptPersonAction();
				for(String id:personIdArr){
					ppAct.save(uuid, id);
				}
			}
			
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
	
	public void delete(){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		PromptDAO promdao = null; 
		try{
			String[] promIdArr = promIds.split(",");
			PromptPersonAction ppAct = new PromptPersonAction();
			conn = dbbase.getConn();
			for(String id:promIdArr){
				promdao = new PromptDAO(); 
				ppAct.delete(id);
				promdao.setConnection(conn);
				promdao.setUuid(id);
				promdao.batchDelete();
				
			}
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
	public PromptBean daozhBean(PromptDAO promptdao){
		PromptBean prombean = new PromptBean();
		prombean.setUuid(promptdao.getUuid());
		prombean.setTitle(promptdao.getTitle());
		prombean.setContent(promptdao.getContent());
		prombean.setCreator(promptdao.getCreator());
		prombean.setCreatorid(promptdao.getCreatorid());
		prombean.setCreatedate(formatDate(promptdao.getCreatedate(),"yyyy-MM-dd HH:mm:ss"));
		prombean.setBegindate(formatDate(promptdao.getBegindate(),"yyyy-MM-dd"));
		return prombean;
	}
	
	public PromptDAO beanzhDao(PromptBean promptbean){
		PromptDAO promdao = new PromptDAO();
		promdao.setUuid(promptbean.getUuid());
		promdao.setTitle(promptbean.getTitle());
		promdao.setContent(promptbean.getContent());
		promdao.setCreator(promptbean.getCreator());
		promdao.setCreatorid(promptbean.getCreatorid());
		promdao.setCreatedate(formatDate(promptbean.getCreatedate(),"yyyy-MM-dd HH:mm:ss"));
		promdao.setBegindate(formatDate(promptbean.getBegindate(),"yyyy-MM-dd"));
		return promdao;
	}
	
	public String formatDate(Date date,String formatstr){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(formatstr);
			return sdf.format(date);
		}
		return null;
	}
	
	public Date formatDate(String date,String formatstr){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(formatstr);
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private List mypromptlist;
	private List recevicelist;
	private PromptBean prombean;
	private String personIds;
	private String promIds;
	private int totalCount;
	private int start;
	private int limit;
	public List getMypromptlist() {
		return mypromptlist;
	}

	public void setMypromptlist(List mypromptlist) {
		this.mypromptlist = mypromptlist;
	}

	public List getRecevicelist() {
		return recevicelist;
	}

	public void setRecevicelist(List recevicelist) {
		this.recevicelist = recevicelist;
	}

	public PromptBean getPrombean() {
		return prombean;
	}

	public void setPrombean(PromptBean prombean) {
		this.prombean = prombean;
	}

	public String getPersonIds() {
		return personIds;
	}

	public void setPersonIds(String personIds) {
		this.personIds = personIds;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getPromIds() {
		return promIds;
	}

	public void setPromIds(String promIds) {
		this.promIds = promIds;
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
	
	
	
}
