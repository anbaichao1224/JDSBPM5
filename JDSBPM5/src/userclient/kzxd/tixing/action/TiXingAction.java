package kzxd.tixing.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kzxd.tixing.dao.TiXingBean;
import kzxd.tixing.dao.TiXingDao;
import kzxd.tixing.dao.TiXingListBean;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.opensymphony.xwork2.Action;

public class TiXingAction extends HibernateDaoSupport implements Action{
	
	public void save(TiXingBean txbean){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		TiXingDao txdao = new TiXingDao(); 
		try {
			String uuid = new UUID().toString();
			conn = dbbase.getConn();
			txdao.setUuid(uuid);
			txdao.setPersonid(txbean.getPersonid());
			txdao.setMkname(txbean.getMkname());
			txdao.setTitle(txbean.getTitle());
			txdao.setTime(txbean.getTime());
			txdao.setDelid(txbean.getDelid());
			txdao.setConnection(conn);
			txdao.create();
			conn.commit();
			
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
	
	public void delete(String delid){
		DBBeanBase dbbase = null;
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;
		try {
			dbbase = new DBBeanBase("bpm", true);
			conn = dbbase.getConn();
			String sql = "delete from KZXD_TIXING where delid='"+delid+"'";
			pst = conn.prepareStatement(sql);
			rs = pst.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

	public void count(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		String personId = request.getSession().getAttribute("personId").toString();
		ResultSet rs = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		try{
			conn = dbbase.getConn();
			int count = 0;
			String countsql = "select count(*) from KZXD_TIXING where personid='"+personId+"'";
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
	
	public void list() throws IOException{
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String personId = request.getSession().getAttribute("personId").toString();
		List<TiXingListBean> txlist = null;
		Session session = null;
		
		Properties pro = new Properties();
		pro.load(TiXingAction.class.getResourceAsStream("/tixingpath.properties"));// 加载配置文件
    	String fwmoudleid= pro.getProperty("fwmoudleid");
    	String swmoudleid = pro.getProperty("swmoudleid");
    	String tzggmoudleid= pro.getProperty("tzggmoudleid");
    	String gwjhmoudleid= pro.getProperty("gwjhmoudleid");
        String xzspmoudleid= pro.getProperty("xzspmoudleid");
        String jblmoudleid= pro.getProperty("jblmoudleid");
        String wjnbcsmoudleid= pro.getProperty("wjnbcsmoudleid");
        String hqdaiwanmoudleid= pro.getProperty("hqdaiwanmoudleid");
        String hqweishou= pro.getProperty("hqweishou");
        String hqjbl= pro.getProperty("hqjbl");
        String swhqdaiwanmoudleid= pro.getProperty("swhqdaiwanmoudleid");
        String swhqweishou= pro.getProperty("swhqweishou");
        String swhqjbl= pro.getProperty("swhqjbl");
        String hqyishou= pro.getProperty("hqyishou");
        String swhqyishou=pro.getProperty("swhqyishou");
        String gxtx=pro.getProperty("gxtx");
		try{
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery(
					"select mkname, count(*) sl from KZXD_TIXING where personid=? group by mkname"
					).setResultTransformer(Transformers.aliasToBean(TiXingListBean.class));
			query.addScalar("mkname");
			query.addScalar("sl", Hibernate.INTEGER);
			query.setParameter(0, personId);
			txlist=query.list();
			int totalCount = txlist.size();
			String json="{totalCount:"+totalCount+",root:[";
			for(int i=0;i<totalCount;i++){
				String url = "";
				if(txlist.get(i).getMkname().equals("通知公告")){
					url="<a href=javascript:window.top.openWinById2(\""+tzggmoudleid+"\")>";
				}else if(txlist.get(i).getMkname().equals("发文办理")){
					url="<a href=javascript:window.top.openWinById2(\""+fwmoudleid+"\")>";
				}else if(txlist.get(i).getMkname().equals("收文办理")){
					url="<a href=javascript:window.top.openWinById2(\""+swmoudleid+"\")>";
				}else if(txlist.get(i).getMkname().equals("网上协同审批")){
					url="<a href=javascript:window.top.openWinById2(\""+xzspmoudleid+"\")>";
				}else if(txlist.get(i).getMkname().equals("公文交换")){
					url="<a href=javascript:window.top.openWinById2(\""+gwjhmoudleid+"\")>";
				}else if(txlist.get(i).getMkname().equals("交办理收文")){
					url="<a href=javascript:window.top.openWinById2(\""+jblmoudleid+"\")>";
				}else if(txlist.get(i).getMkname().equals("会签发文办理")){
					url="<a href=javascript:window.top.openWinById2(\""+hqdaiwanmoudleid+"\")>";
				}else if(txlist.get(i).getMkname().equals("会签发文未收公文")){
					url="<a href=javascript:window.top.openWinById2(\""+hqweishou+"\")>";
				}else if(txlist.get(i).getMkname().equals("会签发文交办理")){
					url="<a href=javascript:window.top.openWinById2(\""+hqjbl+"\")>";
				}else if(txlist.get(i).getMkname().equals("会签收文办理")){
					url="<a href=javascript:window.top.openWinById2(\""+swhqdaiwanmoudleid+"\")>";
				}else if(txlist.get(i).getMkname().equals("会签收文未收公文")){
					url="<a href=javascript:window.top.openWinById2(\""+swhqweishou+"\")>";
				}else if(txlist.get(i).getMkname().equals("会签收文交办理")){
					url="<a href=javascript:window.top.openWinById2(\""+swhqjbl+"\")>";
				}else if(txlist.get(i).getMkname().equals("会签发文已收公文")){
					url="<a href=javascript:window.top.openWinById2(\""+hqyishou+"\")>";
				}else if(txlist.get(i).getMkname().equals("会签收文已收公文")){
					url="<a href=javascript:window.top.openWinById2(\""+swhqyishou+"\")>";
				}else if(txlist.get(i).getMkname().equals("文件内部传送")){
					url="<a href=javascript:window.top.openWinById2(\""+wjnbcsmoudleid+"\")>";
				}else if(txlist.get(i).getMkname().equals("系统提醒")){
					url="<a href=javascript:window.top.openWinById2(\""+gxtx+"\")>";
				}
				
				json+="{mkname:'"+url+""+txlist.get(i).getMkname()+"</a>',sl:'共"+txlist.get(i).getSl()+"条'}";

				if(i!=(totalCount-1)){
					json+=",";
				}
			}
			json+="]}";
			
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
	}

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
