package kzxd.tixing.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.itjds.common.database.DBBeanBase;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.appfuse.webapp.action.BaseAction;

import com.kzxd.db.action.OaBean;

@SuppressWarnings("serial")
public class GxTx extends BaseAction {
	public List<OaBean> index() {
		List<OaBean> sendlist = new ArrayList<OaBean>();
		DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
		conn = dbbase.getConn();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		String sql = "select * from OA_GXTX where personid ='"+personId+"' order by createdate";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				OaBean bean = new OaBean();
				bean.setUuid(rs.getString("UUID"));
				bean.setMsm(rs.getString("MSM"));
				bean.setPersonid(rs.getString("PERSONID"));
				bean.setCreatetime(rs.getDate("createdate"));
				sendlist.add(bean);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				rs.close();
				pst.close();
				conn.close();
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return sendlist;
	}
	public void delete(String delid){
		DBBeanBase dbbase = null;
		Connection conn = null;
		PreparedStatement pst = null;
		int rs = 0;
		try {
			dbbase = new DBBeanBase("bpm", true);
			conn = dbbase.getConn();
			String sql = "delete from KZXD_TIXING where delid='"+delid+"' and flag ='1'";
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

	public String gxtx() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<OaBean> list=this.index();
		for(int i=0;i<list.size();i++){
			String uuid = list.get(i).getUuid();
			String msm = list.get(i).getMsm();
			String personid = list.get(i).getPersonid();
			String createtime = formatDate(list.get(i).getCreatetime(),
			"yyyy-MM-dd HH:mm:ss");
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			childrenmap.put("uuid", uuid);
			childrenmap.put("msm", msm);
			childrenmap.put("personid", personid);
			childrenmap.put("createtime", createtime);
			childrens.add(childrenmap);
		}
		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		try {

			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String personId = session.getAttribute("personId").toString();
		this.delete(personId);
		
		return null;
		
	}
	private JSONObject ja;
	private String formatDate(java.util.Date date, String fmstr) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sfd = new SimpleDateFormat(fmstr);
		return sfd.format(date);
	}

}
