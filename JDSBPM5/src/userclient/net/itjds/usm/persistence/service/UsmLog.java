package net.itjds.usm.persistence.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.usm.persistence.action.SmallBean;
import net.itjds.usm.persistence.dao.UsmLogDAO;
import net.sf.json.JSONObject;

public class UsmLog {
	private JSONObject ja;

	public void save(SmallBean bean) {
		Connection conn;
		DBBeanBase dbbase = new DBBeanBase("org");
		conn = null;
		DAOFactory factory = null;
		UsmLogDAO usmdao = new UsmLogDAO();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, usmdao);
			usmdao.setCreatedate(bean.getCreatedate());
			usmdao.setMsm(bean.getMsm());
			usmdao.setPerson(bean.getPerson());
			usmdao.setUuid(bean.getUuid());
			usmdao.setConnection(conn);
			usmdao.create();
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {

				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public List<SmallBean> findByDeptId() {
		List<SmallBean> sendlist = new ArrayList<SmallBean>();
		DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
		conn = dbbase.getConn();
		String sql = "select * from usmlog order by createdate desc";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				SmallBean bean = new SmallBean();
				bean.setMsm(rs.getString("msm"));
				bean.setCreatedate(rs.getDate("createdate"));
				sendlist.add(bean);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				pst.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		return sendlist;

	}
	private String formatDate(Date date, String fmstr) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sfd = new SimpleDateFormat(fmstr);
		return sfd.format(date);
	}
	// 公文交换已发列表
	public String Find() {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<SmallBean> sendlist = this.findByDeptId();
		for (int i = 0; i < sendlist.size(); i++) {
			String msm = sendlist.get(i).getMsm();
			Date a = sendlist.get(i).getCreatedate();
			String createdate = formatDate(a,"yyyy-MM-dd  HH:mm:ss");
			sendlist.get(i).getCreatedate();
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			childrenmap.put("msm", msm);
			childrenmap.put("createdate", createdate);
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
		return null;
	}
}
