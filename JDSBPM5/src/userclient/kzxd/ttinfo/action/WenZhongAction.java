package kzxd.ttinfo.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import kzxd.ttinfo.dao.WenZhongDAO;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;

import com.kzxd.zihao.action.ZiHaoAct;
import com.opensymphony.xwork2.Action;

public class WenZhongAction implements Action {

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String list(){
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		WenZhongDAO wzdao = new WenZhongDAO();
		
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn,wzdao);
			wzdao.addOrderBy("createdate", true);
			wzlist = factory.find();
		}catch(Exception e){
			
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "wzlist";
	}
	
	public void save() throws PersonNotFoundException{
		HttpServletRequest request = ServletActionContext.getRequest();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		WenZhongDAO wzdao = new WenZhongDAO();
		String personId = request.getSession().getAttribute("personId").toString();
		Person person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
		try{
			
			conn = dbbase.getConn();
			wzdao.setConnection(conn);
			
			wzdao.setWzname(wzname);
			wzdao.setWzename(wzename);
			wzdao.setCreator(person.getName());
			wzdao.setCreatorId(personId);
			
			if(uuid==null||uuid.equals("")){
				
				uuid = new UUID().toString();
				wzdao.setUuid(uuid);
				wzdao.create();
				wzdao.setCreatedate(new Date());
			}else{
				wzdao.setUuid(uuid);
				wzdao.setCreatedate(getById(uuid).getCreatedate());
				wzdao.update();
				
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
	
	public void delete() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String msg = "删除成功";
		boolean flag = true;
		String[] idarray = ids.split(",");
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		WenZhongDAO wzdao = new WenZhongDAO();
		for(String id:idarray){
			if (getzihao(id)==0) {
				try {
					conn = dbbase.getConn();
					wzdao.setConnection(conn);
					wzdao.setUuid(id);
					wzdao.delete();
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
			}else{
				msg = "所选中有文种已经被使用，无法删除";
			}
		}
		
			response.getWriter().write(msg);
		
	}
	
	public WenZhongDAO getByEname(String ename){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		WenZhongDAO wzdao = new WenZhongDAO();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn,wzdao);
			wzdao.setWzename(ename);
			List list = factory.find();
			if(list!=null&&list.size()>0){
				return (WenZhongDAO)list.get(0);
			}
			return null;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public int getzihao(String wzid){
		
		//ZiHaoAct zhao = new ZiHaoAct();
		List list = zihaoAct.getByWzId(wzid);
		if(list==null){
			return 0;
		}
		return list.size();
	}
	
	public WenZhongDAO getById(String wzId){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		WenZhongDAO wzdao = new WenZhongDAO();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn,wzdao);
			wzdao.setUuid(wzId);
			WenZhongDAO wzdb = (WenZhongDAO)factory.findByPrimaryKey();
			
			return wzdb;
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Date formatDate(String date){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				return sdf.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	private ZiHaoAct zihaoAct;
	
	
	private List wzlist;
	
	private String uuid;
	private String wzname;
	private String wzename;
	private String createdate;
	private String ids;
	private int totalCount;
	public List getWzlist() {
		return wzlist;
	}

	public void setWzlist(List wzlist) {
		this.wzlist = wzlist;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWzname() {
		return wzname;
	}

	public void setWzname(String wzname) {
		this.wzname = wzname;
	}

	public String getWzename() {
		return wzename;
	}

	public void setWzename(String wzename) {
		this.wzename = wzename;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public ZiHaoAct getZihaoAct() {
		return zihaoAct;
	}

	public void setZihaoAct(ZiHaoAct zihaoAct) {
		this.zihaoAct = zihaoAct;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
	
}
