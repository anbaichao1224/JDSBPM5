package kzxd.electronicfile.action;

import java.io.IOException;
import java.lang.reflect.Field;
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
import javax.servlet.http.HttpSession;

import org.apache.lucene.index.IndexReader;
import org.apache.struts2.ServletActionContext;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.sf.json.JSONArray;
import kzxd.electronicfile.dao.RecordDataDAO;
import kzxd.electronicfile.dao.RollDAO;
import kzxd.electronicfile.entity.CategoryTreeBean;
import kzxd.electronicfile.entity.RollBean;
import kzxd.electronicfile.entity.RollTreeBean;
import kzxd.lucenetest.CreateLuceneIndex;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class RollManagerAction implements Action {
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void save(){
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		RollDAO rolldao = new RollDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    try{
	    	Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
	    	conn = dbbase.getConn();
	    	factory = new DAOFactory(conn,rolldao);
	    	rolldao.setConnection(conn);
	    	rolldao.setRollname(rollbean.getRollname());
	    	rolldao.setRolldirectnum(rollbean.getRolldirectnum());
	    	rolldao.setRollnum(rollbean.getRollnum());
	    	rolldao.setAmanuensis(rollbean.getAmanuensis());
	    	rolldao.setMiji(rollbean.getMiji());
	    	rolldao.setTimelimit(rollbean.getTimelimit());
	    	rolldao.setStarttime(formatDate(rollbean.getStarttime()));
	    	rolldao.setEndtime(formatDate(rollbean.getEndtime()));
	    	rolldao.setPagenum(rollbean.getPagenum());
	    	rolldao.setSavedirection(rollbean.getSavedirection());
	    	rolldao.setYearnum(rollbean.getYearnum());
	    	rolldao.setCreator(person.getName());
	    	rolldao.setCreatorid(personId);
	    	
	    	rolldao.setStatus(0);
	    	rolldao.setCategory_uuid(rollbean.getCategoryid());
	    	if(rollbean.getUuid().equals("")){
	    		rolldao.setCreatedate(new Date());
	    		rolldao.setUuid(new UUID().toString());
	    		rolldao.create();
	    	}else{
	    		rolldao.setCreatedate(formatDate(rollbean.getCreatedate()));
	    		rolldao.setUuid(rollbean.getUuid());
	    		rolldao.update();
	    		new CreateLuceneIndex().deleteTerm(rollbean.getUuid());
	    	}
	    	
	    	conn.commit();
	    	new CreateLuceneIndex().addDoc("", rolldao.getUuid(), rollbean.getRollname(), "", formatDate(rolldao.getCreatedate(),"yyyy-MM-dd"), rolldao.getCreator(),"1",rollbean.getRollnum(),rollbean.getYearnum(),"");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }finally{
	    	try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	}

	public String list(){
		List list = getlist("");
		totalCount = list.size();
		int count = start + limit;
		if(count>totalCount){
			//slist = list.subList(start, totalCount);
			rolllist = list.subList(start, totalCount);
		}else{
			rolllist = list.subList(start, count);
		}
		//rolllist = list;
		return "rolllist";
	}

	public List getlist(String lstatus){
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		try {
			conn = dbbase.getConn();
			RollDAO rolldao = new RollDAO();
			factory = new DAOFactory(conn,rolldao);
			String wheresql = "";
			if(lstatus.equals("")){
			if(yearnum!=null&&(!yearnum.equals(""))){
				rolldao.setYearnum(yearnum);
				wheresql += " yearnum='"+yearnum+"'";
				if(status!=null&&(!status.equals(""))||rollnum!=null&&(!rollnum.equals(""))){
					wheresql += " and ";
				}
			}
			if(status!=null&&(!status.equals(""))){
				rolldao.setStatus(Integer.parseInt(status));
				wheresql += " status="+Integer.parseInt(status);
				if(rollnum!=null&&(!rollnum.equals(""))){
					wheresql += " and ";
				}
			}
			if(rollnum!=null&&(!rollnum.equals(""))){
				wheresql += " rollnum='"+rollnum+"'";
				if(rollname!=null&&(!rollname.equals(""))){
					wheresql += " and ";
				}
			}
			
			if(rollname!=null&&(!rollname.equals(""))){
				wheresql += " rollname like '%"+rollname+"%'";
			}
			}
			else{
			//Date ndate = new Date();
			String edate = formatDate(new Date(),"yyyy-MM-dd");
			wheresql = " uuid not in(select rollid from fdt_oa_rollpepodom where endtime>=to_date('"+edate+"','yyyy/mm/dd'))";
			}
			rolldao.addOrderBy("createdate", false);
			if(!wheresql.equals("")){
				rolldao.setWhereClause(wheresql);
				rolllist = factory.find();
			}else{
				rolllist = factory.findAll();
			}
			
			if(rolllist!=null){
				totalCount = rolllist.size();
				
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
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rolllist;
		
	}
	//同坐状态查询 （是否组卷）
	public String listByStatus(){
		rolllist = listByParam(0);
		return "select";
	}
	//未组卷
	public String treelist(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest request=ServletActionContext.getRequest();
		List list = listByParam(0);
		List rolllist = getTree(list,null);
		JSONArray  ja = JSONArray.fromObject(rolllist);
		String jsonstrm = ja.toString();
		request.setAttribute("jsonstrm", jsonstrm);
		return "treelist";
	}
	//通过案卷id查询档案列表 （电子目录下查看某一案卷时调用此方法）
	public String rollDetailTree(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest request=ServletActionContext.getRequest();
		List list = new ArrayList();
		if (rollbean != null) {
			RollDAO rdao = findDAOById(rollbean.getUuid());
			list.add(rdao);
		}
		List rolllist = getTree(list,null);
		JSONArray  ja = JSONArray.fromObject(rolllist);
		String jsonstrm = ja.toString();
		request.setAttribute("jsonstrm", jsonstrm);
		return "treelist";
	}
	
	//通过目录id查询案卷列表
	public String rolllistByCid(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest request=ServletActionContext.getRequest();
		List list = new ArrayList();
		if (rollbean != null) {
			list = findByCid(rollbean.getCategoryid());
		}
		List rolllist = getTree(list,null);
		totalCount = rolllist.size();
		JSONArray  ja = JSONArray.fromObject(rolllist);
		String jsonstrm = ja.toString();
		request.setAttribute("jsonstrm", jsonstrm);
		return "treelist";
	}
	
	public List getTree(List list,String ispepodom){
		List rolllist = new ArrayList();
		for(int i=0;i<list.size();i++){
			RollDAO rdao = (RollDAO)list.get(i);
			RollTreeBean treebean = new RollTreeBean();
			treebean.setId(rdao.getUuid());
			//treebean.setText(rdao.getRollname());
			//treebean.setName(rdao.getRollname());
			String namestr ="<a href=javascript:void(0) onclick=\"updateMatter('/roll_findById.action?rollbean.uuid="+treebean.getId()+"&rollbean.ispepodom="+ispepodom+"','')\">"+rdao.getRollname()+"</a>";
			treebean.setName(namestr);
			treebean.setRollnum(rdao.getRollnum());
			treebean.setYearnum(rdao.getYearnum());
			treebean.setTimelimit(rdao.getTimelimit());
			treebean.setIcon("/desktop/widgets/electronicfile/images/tree_01.png");
			List dlist = new RecordDataAction().findByRid(rdao.getUuid());
			if(dlist!=null&&dlist.size()>0){
				treebean.setLeaf(false);
				//for(int j=0;j<dlist.size();j++){
					addRollItem(treebean);
				//}
			}else{
				treebean.setLeaf(true);
			}
			rolllist.add(treebean);
		}
		return rolllist;
	}
	public void addRollItem(RollTreeBean parentbean){
		List list = new RecordDataAction().findByRid(parentbean.getId());
		if(list!=null&&list.size()>0){
			for(int i=0;i<list.size();i++){
				RecordDataDAO ddao = (RecordDataDAO)list.get(i);
				RollTreeBean childbean = new RollTreeBean();
				childbean.setId(ddao.getUuid());
				childbean.setName(ddao.getTitle());
				String namestr ="<a href=javascript:void(0) onclick=\"window.top.openUrlWin('data_findById.action?dataId="+childbean.getId()+"','档案信息')\">"+ddao.getTitle()+"</a>";
				childbean.setName(namestr);
				childbean.setIcon("/desktop/widgets/electronicfile/images/tree_02.png");
				//childbean.setText(ddao.getTitle());
				childbean.setLeaf(true);
				childbean.setRollnum(ddao.getPersonname());
				childbean.setYearnum(formatDate(ddao.getCreatedate(),"yyyy-MM-dd"));
				//parentbean.addChild(childbean);
				parentbean.getChildren().add(childbean);
				//childbean.setParentbean(parentbean);
			}
		}
		
	}
	public List listByParam(int status){
		RollDAO rolldao = new RollDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		String wheresql = "";
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, rolldao);
			wheresql += " status="+status;
			if(yearnum!=null&&!yearnum.equals("")){
				wheresql += " and yearnum='"+yearnum+"'";
			}
			if(rollnum!=null&&!rollnum.equals("")){
				wheresql += " and rollnum='"+rollnum+"'";
			}
			if(rollname!=null&&!rollname.equals("")){
				wheresql += " and rollname like '%"+rollname+"%'";
			}
			rolldao.setWhereClause(wheresql);
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
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//删除
	public void delRoll(){
		String msg = "删除成功";
		if(rollids!=null){
			
			String[] rids = rollids.split(",");
			for(int i=0;i<rids.length;i++){
				if(!delById(rids[i])){
					msg = "删除失败";
					break;
				}
			}
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean delById(String rid){
		RollDAO rolldao = new RollDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		try {
			conn = dbbase.getConn();
			rolldao.setUuid(rid);
			rolldao.setConnection(conn);
			rolldao.delete();
			conn.commit();
			return true;
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public String findById(){
		RollDAO rolldao = null;
		String forwardstr = "rolldetail";
		if(rollbean!=null){
			rolldao = findDAOById(rollbean.getUuid());
			if(rollbean.getIspepodom()!=null){
				forwardstr = "rolldetailbyapp";
			}
		}
		
		if(rolldao!=null){
			rollbean = daozh(rolldao);
		}
		
		return forwardstr;
	}
	public RollDAO findDAOById(String id){
		RollDAO rolldao = new RollDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		try {
			conn = dbbase.getConn();
			//rolldao.setStatus(0);
			factory = new DAOFactory(conn, rolldao);
				rolldao.setUuid(id);
				rolldao = (RollDAO)factory.findByPrimaryKey();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rolldao;
		
	}
	
	//��������ѯ
	public List findByParam(String[][] cid){
		RollDAO rolldao = new RollDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, rolldao);
			Field[] fields= RollDAO.class.getDeclaredFields();
			for(int i=0;i<cid.length;i++){
				for(int j=0;j<fields.length;j++){
					if(fields[j].getName().equals(cid[i][0])){
						
					}
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
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//���Ŀ¼����
	public List findByCid(String cid){
		RollDAO rolldao = new RollDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		List list = new ArrayList();
		String wheresql = "";
		try {
			conn = dbbase.getConn();
			/*String countsql = "select count(*) from FDT_OA_ROLL";
			st = conn.createStatement();
			rs = st.executeQuery(countsql);
			while (rs.next()) {
				totalcount = rs.getInt(1);
			}
			String ws = "";
			if(cid!=null){
				ws += " category_uuid=? ";
			
			if(yearnum!=null&&!yearnum.equals("")){
				//rolldao.setWhereClause(" category_uuid='"+cid+"' and yearnum='"+yearnum+"'");
				ws += " and yearnum=?";
			}
			if(rollnum!=null&&!rollnum.equals("")){
				ws += " and rollnum=?";
			}
			}
			String sql = "select m.* from (select rownum r,mr.* from FDT_OA_ROLL mr where "+ws+" and rownum <=?) m where m.r >?";
			pst = conn.prepareStatement(sql);
			int count = 0;
			if(cid!=null){
				pst.setString(1, cid);
				count = 1;
			if(yearnum!=null&&!yearnum.equals("")){
				pst.setString(2, yearnum);
				count = 2;
			}
			if(rollnum!=null&&!rollnum.equals("")){
				pst.setString(count, rollnum);
				count +=1;
			}
			}
			pst.setInt(count+1,limit + start);
			pst.setInt(count+2,start);
			rs = pst.executeQuery();
			while (rs.next()) {
				
				rb.setUuid(rs.getString("UUID"));
				rb.setRolldirectnum(rs.getString("ROLLDIRECTNUM"));
				rb.setRollnum(rs.getString("ROLLNUM"));
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
		    	rb.setCategory_uuid(rs.getString("CATEGORY_UUID"));
				list.add(rb);
			}*/
			
			factory = new DAOFactory(conn, rolldao);
			if(cid!=null){
				/*if(cid.equals("18bbaac-13349102b61-2f87dce8d8643a73d4d0ede3f3cf44cb")){
				}*/
				rolldao.setWhereClause(" category_uuid='"+cid+"'");
				wheresql += "category_uuid='"+cid+"'";
				if(yearnum!=null&&!yearnum.equals("")){
					//rolldao.setWhereClause(" category_uuid='"+cid+"' and yearnum='"+yearnum+"'");
					wheresql += " and yearnum='"+yearnum+"'";
				}
				if(rollnum!=null&&!rollnum.equals("")){
					wheresql += " and rollnum='"+rollnum+"'";
				}
				rolldao.setWhereClause(wheresql);
				list = factory.find();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
		return list;
	}
	//��� 
	public void updateStatus(){
		RollDAO rolldao = new RollDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		try {
			conn = dbbase.getConn();
			//rolldao.setStatus(0);
			factory = new DAOFactory(conn, rolldao);
			if(rollbean!=null){
				
				rolldao.setUuid(rollbean.getUuid());
				rolldao = (RollDAO)factory.findByPrimaryKey();
				if(rolldao!=null){
					rolldao.setConnection(conn);
					rolldao.setCategory_uuid(rollbean.getCategoryid());
					rolldao.setStatus(rollbean.getStatus());
					//rolldao.setWhereClause(" category_uuid='"+rollbean.getCategoryid()+"' and status="+rollbean.getStatus());
					rolldao.update();
				}
				conn.commit();
				new CreateLuceneIndex().deleteTerm(rollbean.getUuid());
				new CreateLuceneIndex().addDoc("", rollbean.getUuid(), rolldao.getRollname(), "", formatDate(rolldao.getCreatedate(),"yyyy-MM-dd"), rolldao.getCreator(),"1", rolldao.getRollnum(), rolldao.getYearnum(),"");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	
	public RollBean daozh(RollDAO rdao){
		RollBean bean = new RollBean();
		bean.setUuid(rdao.getUuid());
    	bean.setRollname(rdao.getRollname());
    	String namestr ="<a href=javascript:void(0) onclick=\"updateMatter('roll_findById.action?rollbean.uuid="+bean.getUuid()+"')\"";
    	
    	bean.setRolldirectnum(rdao.getRolldirectnum());
    	bean.setRollnum(rdao.getRollnum());
    	bean.setAmanuensis(rdao.getAmanuensis());
    	bean.setMiji(rdao.getMiji());
    	bean.setTimelimit(rdao.getTimelimit());
    	bean.setStarttime(formatDate(rdao.getStarttime(),"yyyy-MM-dd"));
    	bean.setEndtime(formatDate(rdao.getEndtime(),"yyyy-MM-dd"));
    	bean.setPagenum(rdao.getPagenum());
    	bean.setSavedirection(rdao.getSavedirection());
    	bean.setYearnum(rdao.getYearnum());
    	bean.setCreator(rdao.getCreator());
    	bean.setCreatorid(rdao.getCreatorid());
    	bean.setCreatedate(formatDate(rdao.getCreatedate(),"yyyy-MM-dd HH:mm:ss"));
    	bean.setStatus(rdao.getStatus());
    	bean.setCategoryid(rdao.getCategory_uuid());
    	return bean;
	}
	
	private String formatDate(Date date,String fmstr){
		if(date==null){
			return null;
		}
		SimpleDateFormat sfd = new SimpleDateFormat(fmstr);
		return sfd.format(date);
	}
	private Date formatDate(String date){
		SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sfd.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private RollBean rollbean;
	private List rolllist;
	private int totalCount = 0;
	private int start;
	private int limit;
	
	private String rollids;
	
	private String yearnum;
	private String status;
	private String rollnum;
	private String rollname;
	
	private String dataIds;//��Ҫ���ĵ���id����
	public RollBean getRollbean() {
		return rollbean;
	}

	public void setRollbean(RollBean rollbean) {
		this.rollbean = rollbean;
	}

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

	public String getDataIds() {
		return dataIds;
	}

	public void setDataIds(String dataIds) {
		this.dataIds = dataIds;
	}

	public String getYearnum() {
		return yearnum;
	}

	public void setYearnum(String yearnum) {
		this.yearnum = yearnum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRollids() {
		return rollids;
	}

	public void setRollids(String rollids) {
		this.rollids = rollids;
	}

	public String getRollnum() {
		return rollnum;
	}

	public void setRollnum(String rollnum) {
		this.rollnum = rollnum;
	}

	public String getRollname() {
		return rollname;
	}

	public void setRollname(String rollname) {
		this.rollname = rollname;
	}
	
	
}
