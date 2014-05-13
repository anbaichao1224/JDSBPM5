package com.kzxd.db.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.util.UUID;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class Chaxun extends ActionSupport {

	/*
	 * 监控单位---监控单位列表显示
	 */
	public String index() {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		Map<String, Object> childrenmap1 = new HashMap<String, Object>();
		String mysql = "select count(*) from tbl_amqp_temp";
		int ZxAmqp = this.MySql(MySqlUrl, mysql, "root", "root");

		String zxqmqp = "";
		if (ZxAmqp == 0) {
			zxqmqp = "中心交换[" + ZxAmqp + "]";
		} else {
			zxqmqp = "<a href=\"admin/zxamqp.jsp?username=" + "root"
					+ "&password=" + "root" + "&deptid=" + deptid + "\")>"
					+ "<font  color='red'>" + "中心交换[" + ZxAmqp + "]"
					+ "</font>" + "</a>";
		}
		childrenmap1.put("dw", "中心服务器");
		childrenmap1.put("dworder", 0);
		childrenmap1.put("deptid", 0);
		childrenmap1.put("username", "root");
		childrenmap1.put("password", "root");
		childrenmap1.put("uuid", 0);
		childrenmap1.put("ckgwjh", "");
		childrenmap1.put("gwjhamqp", zxqmqp);
		childrenmap1.put("ckry", "");
		childrenmap1.put("msm", "");
		childrenmap1.put("hqamqp", "");
		childrenmap1.put("hq", "");
		childrens.add(childrenmap1);
		int totalProperty = this.OADwcount();
		List<OaBean> sendlist = this.OADw();
		map.put("totalProperty", totalProperty);
		for (int i = 0; i < sendlist.size(); i++) {
			String dw = sendlist.get(i).getDw();
			String username = sendlist.get(i).getUsername();
			String password = sendlist.get(i).getPassword();
			String deptid = sendlist.get(i).getDeptid();
			String uuid = sendlist.get(i).getUuid();
			int flag = sendlist.get(i).getFlag();
			int GwAmqp = 0;
			int HqAmqp = 0;
			int GwTj = 0;
			int GwjhYf = 0;
			int GwYS = 0;
			try {
				GwTj = this.GwTj(username, password, flag);
				GwjhYf = this.GwjhYfCount(username, password, deptid, flag);
				HqAmqp = this.HqAmqp(username, password, flag);
				GwAmqp = this.GwAmqp(username, password, flag);
				GwYS = this.GwYsCount(username, password, deptid, flag);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String ckry = "<a href=\"admin/ckry.jsp?username=" + username
					+ "&password=" + password + "&flag=" + String.valueOf(flag)
					+ "\")>" + "查看人员" + "</a>";
			String ckgwjh = "<a href=\"admin/gwjh.jsp?username=" + username
					+ "&password=" + password + "&flag=" + String.valueOf(flag)
					+ "&deptid=" + deptid + "\")>" + "公文交换" + "</a>";
			String gwjhqmqp = "";
			if (GwAmqp == 0) {
				gwjhqmqp = "公文交换[" + GwAmqp + "]";
			} else {
				gwjhqmqp = "<a href=\"admin/gwjhamqp.jsp?username=" + username
						+ "&flag=" + String.valueOf(flag) + "&password="
						+ password + "&deptid=" + deptid + "\")>"
						+ "<font  color='red'>" + "公文交换[" + GwAmqp + "]"
						+ "</font>" + "</a>";
			}

			String msm = "<a href=\"admin/msm.jsp?username=" + username
					+ "&flag=" + String.valueOf(flag) + "&password=" + password
					+ "\")>" + "发送记录" + "</a>";
			String hq = "<a href=\"admin/hq.jsp?username=" + username
					+ "&password=" + password + "&deptid=" + deptid + "&flag="
					+ String.valueOf(flag) + "\")>" + "会签" + "</a>";
			String hqamqp = "";
			if (HqAmqp == 0) {
				hqamqp = "会签[" + HqAmqp + "]";
			} else {
				hqamqp = "<a href=\"admin/hqamqp.jsp?username=" + username
						+ "&flag=" + String.valueOf(flag) + "&password="
						+ password + "&deptid=" + deptid + "\")>"
						+ "<font  color='red'>" + "会签[" + HqAmqp + "]"
						+ "</font>" + "</a>";
			}

			Map<String, Object> childrenmap = new HashMap<String, Object>();
			childrenmap.put("dw", dw);
			childrenmap.put("dworder", i + 1);
			childrenmap.put("deptid", deptid);
			childrenmap.put("GwYS", GwYS);
			childrenmap.put("username", username);
			childrenmap.put("password", password);
			childrenmap.put("uuid", uuid);
			childrenmap.put("ckgwjh", ckgwjh);
			childrenmap.put("gwjhamqp", gwjhqmqp);
			childrenmap.put("ckry", ckry);
			childrenmap.put("msm", msm);
			childrenmap.put("hqamqp", hqamqp);
			childrenmap.put("hq", hq);
			childrenmap.put("GwTj", GwTj);
			childrenmap.put("GwjhYf", GwjhYf);
			childrenmap.put("FLAG", flag);
			childrens.add(childrenmap);
		}
		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		try {

			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public int OADwcount() {
		int a = 0;
		DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
		conn = dbbase.getConn();
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select count(*) from OA ";
		try {
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paramlist.size(); i++) {
				Object o = paramlist.get(i);
				if (o instanceof Date) {
					pst.setDate(i + 1, (java.sql.Date) o);
				} else {
					pst.setObject(i + 1, o);
				}
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				a = rs.getInt(1);
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
		return a;

	}

	public List<OaBean> OADwByDeptId(String deptid) {
		List<OaBean> sendlist = new ArrayList<OaBean>();
		DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
		conn = dbbase.getConn();
		String sql = "select * from OA  where deptid=" + deptid;
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				OaBean bean = new OaBean();
				bean.setDw(rs.getString("dw"));
				bean.setDworder(String.valueOf(rs.getInt("dworder")));
				bean.setPassword(rs.getString("password"));
				bean.setUsername(rs.getString("username"));
				bean.setUuid(rs.getString("uuid"));
				bean.setDeptid(rs.getString("deptid"));
				bean.setFlag(rs.getInt("flag"));
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

	public List<OaBean> OADw() {
		List<OaBean> sendlist = new ArrayList<OaBean>();
		DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
		conn = dbbase.getConn();
		String sql = "select * from OA  order by dworder";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				OaBean bean = new OaBean();
				bean.setDw(rs.getString("dw"));
				bean.setDworder(String.valueOf(rs.getInt("dworder")));
				bean.setPassword(rs.getString("password"));
				bean.setUsername(rs.getString("username"));
				bean.setUuid(rs.getString("uuid"));
				bean.setDeptid(rs.getString("deptid"));
				bean.setFlag(rs.getInt("flag"));
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

	/* 中心---- 调度表详细 */
	@SuppressWarnings("unchecked")
	public String ZxAmqp() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<OaBean> inceptlist = new ArrayList();
		String mysql = "select * from tbl_amqp_temp";
		inceptlist = this.MySqlDu(MySqlUrl, mysql, "root", "root");
		map.put("totalProperty", inceptlist.size());
		for (int i = 0; i < inceptlist.size(); i++) {
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			int flag = inceptlist.get(i).getOperate_type();
			String zt = "";
			String title = "";
			String senddept = "";
			String send_id = "";
			String qxdw = "";
			List<OaBean> List = new ArrayList();
			// 操作类型，0-发文，1-补发，2-取消，3-签收，4-退回，5-回执，6-中止报名, 7-继续报名, 8-报名,9-删除已发公文
			if (flag == 0) {
				String fasong = "select * from tbl_send  where id ='"
						+ inceptlist.get(i).getOperate_uuid() + "'";
				List = this.MySqlList(MySqlUrl, fasong, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					title = List.get(j).getTitle();
					senddept = List.get(j).getSenddept();
				}
				zt = senddept + "[0-发送]";
			} else if (flag == 1) {
				String fasong = "select * from tbl_send  where id ='"
						+ inceptlist.get(i).getOperate_uuid() + "'";
				List = this.MySqlList(MySqlUrl, fasong, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					title = List.get(j).getTitle();
					senddept = List.get(j).getSenddept();
				}
				zt = senddept + "[1-补发]";
			} else if (flag == 2) {
				String fasong = "select * from tbl_incept  where id ='"
						+ inceptlist.get(i).getOperate_uuid() + "'";
				List = this.MySqlList1(MySqlUrl, fasong, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					send_id = List.get(j).getTitle();
					qxdw = List.get(j).getSenddept();
				}
				String fasong2 = "select * from tbl_send  where id ='"
						+ send_id + "'";
				List = this.MySqlList(MySqlUrl, fasong2, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					title = List.get(j).getTitle();
					senddept = List.get(j).getSenddept();
				}
				zt = senddept + "[2-取消]" + qxdw;
			} else if (flag == 3) {
				String fasong = "select * from tbl_incept  where id ='"
						+ inceptlist.get(i).getOperate_uuid() + "'";
				List = this.MySqlList1(MySqlUrl, fasong, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					send_id = List.get(j).getTitle();
					qxdw = List.get(j).getSenddept();
				}
				String fasong2 = "select * from tbl_send  where id ='"
						+ send_id + "'";
				List = this.MySqlList(MySqlUrl, fasong2, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					title = List.get(j).getTitle();
					senddept = List.get(j).getSenddept();
				}
				zt = qxdw + "[3-签收]" + senddept;
			} else if (flag == 4) {
				String fasong = "select * from tbl_incept  where id ='"
						+ inceptlist.get(i).getOperate_uuid() + "'";
				List = this.MySqlList1(MySqlUrl, fasong, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					send_id = List.get(j).getTitle();
					qxdw = List.get(j).getSenddept();
				}
				String fasong2 = "select * from tbl_send  where id ='"
						+ send_id + "'";
				List = this.MySqlList(MySqlUrl, fasong2, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					title = List.get(j).getTitle();
					senddept = List.get(j).getSenddept();
				}
				zt = qxdw + "[4-拒签]" + senddept;
			} else if (flag == 5) {
				String fasong = "select * from tbl_incept  where id ='"
						+ inceptlist.get(i).getOperate_uuid() + "'";
				List = this.MySqlList1(MySqlUrl, fasong, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					send_id = List.get(j).getTitle();
					qxdw = List.get(j).getSenddept();
				}
				String fasong2 = "select * from tbl_send  where id ='"
						+ send_id + "'";
				List = this.MySqlList(MySqlUrl, fasong2, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					title = List.get(j).getTitle();
					senddept = List.get(j).getSenddept();
				}
				zt = qxdw + "[5-回执]" + senddept;
			} else if (flag == 6) {
				String fasong = "select * from tbl_send  where id ='"
						+ inceptlist.get(i).getOperate_uuid() + "'";
				List = this.MySqlList(MySqlUrl, fasong, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					title = List.get(j).getTitle();
					senddept = List.get(j).getSenddept();
				}
				zt = senddept + "[6-终止报名]";
			} else if (flag == 7) {
				String fasong = "select * from tbl_send  where id ='"
						+ inceptlist.get(i).getOperate_uuid() + "'";
				List = this.MySqlList(MySqlUrl, fasong, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					title = List.get(j).getTitle();
					senddept = List.get(j).getSenddept();
				}
				zt = senddept + "[7-继续报名]";
			} else if (flag == 8) {
				String fasong = "select * from tbl_incept  where id ='"
						+ inceptlist.get(i).getOperate_uuid() + "'";
				List = this.MySqlList1(MySqlUrl, fasong, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					send_id = List.get(j).getTitle();
					qxdw = List.get(j).getSenddept();
				}
				String fasong2 = "select * from tbl_send  where id ='"
						+ send_id + "'";
				List = this.MySqlList(MySqlUrl, fasong2, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					title = List.get(j).getTitle();
					senddept = List.get(j).getSenddept();
				}
				zt = qxdw + "[8-报名]" + senddept;
			} else if (flag == 9) {
				String fasong = "select * from tbl_send  where id ='"
						+ inceptlist.get(i).getOperate_uuid() + "'";
				List = this.MySqlList(MySqlUrl, fasong, "root", "root");
				for (int j = 0; j < List.size(); j++) {
					title = List.get(j).getTitle();
					senddept = List.get(j).getSenddept();
				}
				zt = senddept + "[9-删除公文]";
			}
			String del = "<a href=\"Chaxun_ZxAmqpDel.action?username=" + "root"
					+ "&password=" + "root" + "&delid="
					+ inceptlist.get(i).getZxuuid() + "\")>" + "删除数据" + "</a>";
			String createtime = formatDate(inceptlist.get(i).getCreatetime(),
					"yyyy-MM-dd HH:mm:ss");
			childrenmap.put("uuid", inceptlist.get(i).getZxuuid());
			childrenmap.put("zt", zt);
			childrenmap.put("createtime", createtime);
			childrenmap.put("title", title);
			childrenmap.put("del", del);
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

	/* 中心服务器-----删除调度数据 */

	public String ZxAmqpDel() throws SQLException {
		String mysql = "delete from tbl_amqp_temp where uuid='" + delid + "'";
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(MySqlUrl, username, password);
		stmt = conn.createStatement();
		stmt.executeUpdate(mysql);
		conn.close();
		stmt.close();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();

		Map<String, Object> childrenmap = new HashMap<String, Object>();
		childrenmap.put("username", "root");
		childrenmap.put("password", "root");
		childrens.add(childrenmap);
		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		return "ZxDel";
	}

	/*
	 * 会签-----实现列表 发文：已发，已收，未收 收文：已发，已收，未收
	 */

	public String Hq() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		Map<String, Object> childrenmap = new HashMap<String, Object>();
		String hqfwyf = "<a href=\"admin/hqfwyf.jsp?username=" + username
				+ "&password=" + password + "&deptid=" + deptid + "&flag="
				+ flag + "\")>" + "<font  color='red'>" + "已发公文" + "</font>"
				+ "</a>";
		String hqfwws = "<a href=\"admin/hqfwws.jsp?username=" + this.username
				+ "&password=" + this.password + "&deptid=" + deptid + "&flag="
				+ flag + "\")>" + "<font  color='red'>" + "未收公文" + "</font>"
				+ "</a>";
		String hqfwys = "<a href=\"admin/hqfwys.jsp?username=" + this.username
				+ "&password=" + this.password + "&deptid=" + deptid + "&flag="
				+ flag + "\")>" + "<font  color='red'>" + "已收公文" + "</font>"
				+ "</a>";

		String hqswyf = "<a href=\"admin/hqswyf.jsp?username=" + username
				+ "&password=" + password + "&deptid=" + deptid + "&flag="
				+ flag + "\")>" + "<font  color='blue'>" + "已发公文" + "</font>"
				+ "</a>";
		String hqswws = "<a href=\"admin/hqswws.jsp?username=" + this.username
				+ "&password=" + this.password + "&deptid=" + deptid + "&flag="
				+ flag + "\")>" + "<font  color='blue'>" + "未收公文" + "</font>"
				+ "</a>";
		String hqswys = "<a href=\"admin/hqswys.jsp?username=" + this.username
				+ "&password=" + this.password + "&deptid=" + deptid + "&flag="
				+ flag + "\")>" + "<font  color='blue'>" + "已收公文" + "</font>"
				+ "</a>";

		childrenmap.put("hqfwyf", hqfwyf);
		childrenmap.put("hqfwws", hqfwws);
		childrenmap.put("hqfwys", hqfwys);
		childrenmap.put("hqswyf", hqswyf);
		childrenmap.put("hqswws", hqswws);
		childrenmap.put("hqswys", hqswys);
		childrens.add(childrenmap);
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

	/* 会签---- 调度表详细 */
	@SuppressWarnings("unchecked")
	public String HqAmqp() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<OaBean> inceptlist = new ArrayList();
		String sql = "select * from hq_amqp_temp order by createtime desc";
		inceptlist = this.PersonList(sql);
		map.put("totalProperty", inceptlist.size());
		for (int i = 0; i < inceptlist.size(); i++) {
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			int flag = inceptlist.get(i).getOperatetype();
			String zt = "";
			String title = "";
			String qxdw = "";
			// 操作类型，30-会签，31-取消请求，32-退回，33-开始办理，34-办理状态，35-办理完毕，
			// 36-补发, 37-取消确认,38-签收，46交办理取消用 47收文交办理取消用
			if (flag == 30) {
				zt = "[30-会签发文-发送]";
			} else if (flag == 31) {
				zt = "[31-会签发文-取消会签]";
			} else if (flag == 32) {
				zt = "[32-会签发文-退回]";
			} else if (flag == 33) {
				zt = "[33-会签发文-启动流程]";
			} else if (flag == 34) {
				zt = "[34-会签发文-办理状态]";
			} else if (flag == 35) {
				zt = "[35-会签发文-会签结束]";
			} else if (flag == 36) {
				zt = "[36-会签发文-补发]";
			} else if (flag == 37) {
				zt = "[37-会签发文-取消确认]";
			} else if (flag == 38) {
				zt = "[38-会签发文-签收]";
			} else if (flag == 39) {
				zt = "[39-会签收文-发送]";
			} else if (flag == 40) {
				zt = "[40-会签收文-签收]";
			} else if (flag == 41) {
				zt = "[41-会签收文-退回]";
			} else if (flag == 42) {
				zt = "[42-会签收文-开始办理]";
			} else if (flag == 43) {
				zt = "[43-会签收文-取消]";
			} else if (flag == 44) {
				zt = "[44-会签收文-取消确认]";
			} else if (flag == 45) {
				zt = "[45-会签收文-办理完毕]";
			} else if (flag == 46) {
				zt = "[46-会签发文-交办理取消]";
			} else if (flag == 47) {
				zt = "[47-会签收文-交办理取消]";
			}
			String del = "<a href=\"Chaxun_HqAmqpDel.action?username="
					+ this.username + "&password=" + this.password + "&delid="
					+ inceptlist.get(i).getUuid() + "\")>" + "删除数据" + "</a>";
			String createtime = formatDate(inceptlist.get(i).getCreatetime(),
					"yyyy-MM-dd HH:mm:ss");
			childrenmap.put("uuid", inceptlist.get(i).getUuid());
			childrenmap.put("qxdw", qxdw);
			childrenmap.put("zt", zt);
			childrenmap.put("createtime", createtime);
			childrenmap.put("title", title);
			childrenmap.put("del", del);
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

	/* 公文交换-----删除调度数据 */

	public String HqAmqpDel() throws SQLException {
		String sql = "delete from hq_amqp_temp where uuid='" + delid + "'";
		Connection conn = null;
		Statement stmt = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.close();
			stmt.close();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
				stmt.close();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		conn.close();
		stmt.close();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();

		Map<String, Object> childrenmap = new HashMap<String, Object>();
		childrenmap.put("username", username);
		childrenmap.put("password", password);
		childrens.add(childrenmap);
		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		return "HqDel";
	}

	// 会签收文---未收列表
	public void HqSwWs() throws SQLException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<Object> paramList = new ArrayList<Object>();
		int index = Integer.parseInt(start);
		int pageSize = Integer.parseInt(limit);
		int totalCount = 0;
		totalCount = this.HqWsCount();
		String sql = "select * from (select A.* ,rownum RN from(select t.*, d.uuid as danweiuuid from HQ_DANWEI d,HQ_SWYEWU t where t.uuid = d.huiqian_uuid and d.deptid = "
				+ deptid + " and d.sign_flag=0";
		sql += " order by t.swrq desc";
		sql += " ) A)where RN>? and RN <=?";
		paramList.add(index);
		paramList.add(pageSize);
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++) {
				Object o = paramList.get(i);
				if (o instanceof Date) {

					pst.setDate(i + 1, (java.sql.Date) o);
				} else {
					pst.setObject(i + 1, o);
				}
			}
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress != null && ress.next()) {
			Date date = ress.getDate("SWRQ");
			SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
			String extdate = "";
			if (date != null) {
				extdate = sdate.format(date);
			}
			Date date1 = ress.getDate("FSSJ");
			SimpleDateFormat sdate1 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String extdate1 = "";
			if (date1 != null) {
				extdate1 = sdate1.format(date1);
			}
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			childrenmap.put("BT", ress.getString("WJBT"));
			childrenmap.put("TH", "");
			childrenmap.put("BH", ress.getString("SWBH"));
			childrenmap.put("DJ", ress.getString("JJCD"));
			childrenmap.put("NGDW", ress.getString("YWDW"));
			childrenmap.put("NGSJ", extdate);
			childrenmap.put("FSSJ", extdate1);
			childrens.add(childrenmap);

		}
		ress.close();
		conn.close();
		pst.close();
		map.put("totalProperty", totalCount);
		map.put("root", childrens);
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		try {
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* 会签-----未收列表 */
	public void HqFwWs() throws SQLException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		@SuppressWarnings("unused")
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<Object> paramList = new ArrayList<Object>();
		int index = Integer.parseInt(start);
		int pageSize = Integer.parseInt(limit);
		@SuppressWarnings("unused")
		int totalCount = 0;
		totalCount = this.HqWsCount();
		String sql = "select * from (select A.* ,rownum RN from(select t.*, d.uuid as danweiuuid from HQ_DANWEI d,HQ_YEWU t where t.uuid = d.huiqian_uuid and d.deptid = "
				+ deptid + " and d.sign_flag=0";
		sql += " order by t.ngsj desc";
		sql += " ) A)where RN>? and RN <=?";
		paramList.add(index);
		paramList.add(pageSize);
		ResultSet ress = null;
		Connection conn = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++) {
				Object o = paramList.get(i);
				if (o instanceof Date) {

					pst.setDate(i + 1, (java.sql.Date) o);
				} else {
					pst.setObject(i + 1, o);
				}
			}
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress != null && ress.next()) {
			Date date = ress.getDate("NGSJ");
			SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
			String extdate = "";
			if (date != null) {
				extdate = sdate.format(date);
			}
			Date date1 = ress.getDate("FSSJ");
			SimpleDateFormat sdate1 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String extdate1 = "";
			if (date1 != null) {
				extdate1 = sdate1.format(date1);
			}
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			String wjbh = ress.getString("WJBH");
			if (wjbh != null) {

				childrenmap.put("BH", ress.getString("WJBH"));
			} else {
				childrenmap.put("BH", "");
			}
			childrenmap.put("BT", ress.getString("BT"));
			childrenmap.put("TH", "");
			childrenmap.put("DJ", ress.getString("JJCD"));
			childrenmap.put("NGDW", ress.getString("NGDW"));
			childrenmap.put("NGSJ", extdate);
			childrenmap.put("FSSJ", extdate1);
			childrens.add(childrenmap);

		}
		conn.close();
		ress.close();
		pst.close();
		map.put("totalProperty", totalCount);
		map.put("root", childrens);
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		try {
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* 会签---未收列表未收条数 */
	public int HqWsCount() throws SQLException {
		int totalCount = 0;
		String sql = "select count(*) from HQ_YEWU a where a.FLAG=0";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while (ress.next()) {
			totalCount = ress.getInt(1);
		}
		ress.close();
		pst.close();
		conn.close();
		return totalCount;
	}

	// 会签收文--收文已收会签列表

	public void HqSwYs() throws SQLException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<Object> paramList = new ArrayList<Object>();
		int index = Integer.parseInt(start);
		int pageSize = Integer.parseInt(limit);
		int totalCount = this.HqYsCount();

		String sql = "select * from (select A.* ,rownum RN from(select t.*, d.uuid as danweiuuid from HQ_DANWEI d,HQ_SWYEWU t where t.uuid = d.huiqian_uuid and d.deptid = "
				+ deptid + " and d.sign_flag=1";
		sql += " order by t.swrq desc";
		sql += " ) A)where RN>? and RN <=?";
		paramList.add(index);
		paramList.add(pageSize);
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++) {
				Object o = paramList.get(i);
				if (o instanceof Date) {

					pst.setDate(i + 1, (java.sql.Date) o);
				} else {
					pst.setObject(i + 1, o);
				}
			}
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress != null && ress.next()) {
			Date date = ress.getDate("SWRQ");
			SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
			String extdate = "";
			if (date != null) {
				extdate = sdate.format(date);
			}
			Date date1 = ress.getDate("FSSJ");
			SimpleDateFormat sdate1 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String extdate1 = "";
			if (date1 != null) {
				extdate1 = sdate1.format(date1);
			}

			// 获得发送人的名字
			String jblUuid = ress.getString("JBLUUID");
			String fsr = "";
			if (jblUuid != null) {
				fsr = this.HqSwJbl(jblUuid);
			}
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			if (ress.getString("SWBH") != null) {
				childrenmap.put("BH", ress.getString("SWBH"));
			} else {
				childrenmap.put("BH", "");
			}
			childrenmap.put("BT", ress.getString("WJBT"));

			childrenmap.put("DJ", ress.getString("JJCD"));
			childrenmap.put("NGDW", ress.getString("YWDW"));
			childrenmap.put("NGSJ", extdate);
			childrenmap.put("FSSJ", extdate1);
			if (ress.getString("flag").equals("0")) {
				childrenmap.put("QDLC", "启动流程");
				childrenmap.put("JBL", "交办理");

			} else if (ress.getString("flag").equals("1")) {

				childrenmap.put("JBL", "取回(" + fsr + ")");

			} else if (ress.getString("flag").equals("2")) {
				childrenmap.put("JBL", "");
			}
			childrens.add(childrenmap);

		}
		ress.close();
		pst.close();
		conn.close();
		map.put("totalProperty", totalCount);
		map.put("root", childrens);
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		try {
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// 会签发文-- 已收会签列表

	public void HqFwYs() throws SQLException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<Object> paramList = new ArrayList<Object>();
		int index = Integer.parseInt(start);
		int pageSize = Integer.parseInt(limit);
		int totalCount = 0;
		totalCount = this.HqYsCount();
		String sql = "select * from (select A.* ,rownum RN from(select t.*, d.uuid as danweiuuid from HQ_DANWEI d,HQ_YEWU t where t.uuid = d.huiqian_uuid and d.deptid = "
				+ deptid + " and d.sign_flag=1";
		sql += " order by t.ngsj desc";
		sql += " ) A)where RN>? and RN <=?";
		paramList.add(index);
		paramList.add(pageSize);
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++) {
				Object o = paramList.get(i);
				if (o instanceof Date) {

					pst.setDate(i + 1, (java.sql.Date) o);
				} else {
					pst.setObject(i + 1, o);
				}
			}
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while (ress != null && ress.next()) {
			Date date = ress.getDate("NGSJ");
			SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
			String extdate = "";
			if (date != null) {
				extdate = sdate.format(date);
			}
			Date date1 = ress.getDate("FSSJ");
			SimpleDateFormat sdate1 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String extdate1 = "";
			if (date1 != null) {
				extdate1 = sdate1.format(date1);
			}

			// 获得发送人的名字
			String jblUuid = ress.getString("JBLUUID");
			String fsr = "";
			if (jblUuid != null) {
				fsr = this.HqFwJbl(jblUuid);
			}
			String BT = "<a href=\"javascript:void(0);\" onclick=\"window.top.ck('"
					+ ress.getString("uuid")
					+ "')\">"
					+ ress.getString("BT")
					+ "</a>";
			String jbl = "<a href=\"javascript:void(0);\" onclick=\"jbl('"
					+ ress.getString("uuid") + "');\">交办理</a>";
			String qh = "<a href=\"javascript:void(0);\" onclick=\"quhui('"
					+ ress.getString("uuid") + "');\">取回(" + fsr + ")</a>";

			String qdlc = "<a href=\"javascript:void(0);\" onclick=\"window.top.hqstartProcess('"
					+ ress.getString("uuid") + "');\">启动流程</a>";
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			if (ress.getString("WJBH") != null) {

				childrenmap.put("BH", ress.getString("WJBH"));
			} else {
				childrenmap.put("BH", "");
			}
			childrenmap.put("BT", BT);

			childrenmap.put("DJ", ress.getString("JJCD"));
			childrenmap.put("NGDW", ress.getString("NGDW"));
			childrenmap.put("NGSJ", extdate);
			childrenmap.put("FSSJ", extdate1);
			if (ress.getString("flag").equals("0")) {
				childrenmap.put("QDLC", qdlc);
				childrenmap.put("JBL", jbl);

			} else if (ress.getString("flag").equals("1")) {

				childrenmap.put("JBL", qh);

			} else if (ress.getString("flag").equals("2")) {
				childrenmap.put("JBL", "");
			}
			childrens.add(childrenmap);

		}
		ress.close();
		conn.close();
		pst.close();
		map.put("totalProperty", totalCount);
		map.put("root", childrens);
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		try {
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/* 会签----已收条数 */
	public int HqYsCount() {
		int totalCount = 0;
		String sql = "select count(*) from HQ_YEWU a where a.FLAG=1";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			while (ress.next()) {
				totalCount = ress.getInt(1);
			}
			ress.close();
			conn.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalCount;
	}

	/* 会签---交办理人 */
	public String HqFwJbl(String jblUuid) throws SQLException {

		// 根据交办理的uuid查找发送人的personid
		String sql = "select t.personid from HQ_JBL t where t.uuid = '"
				+ jblUuid + "'";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql);
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress != null && ress.next()) {
			personid = ress.getString("personid");
		}
		ress.close();
		conn.close();
		pst.close();
		return this.HqFind(personid);

	}

	/* 会签---交办理人 */
	public String HqSwJbl(String jblUuid) throws SQLException {

		// 根据交办理的uuid查找发送人的personid
		String sql = "select t.personid from HQ_SWJBL t where t.uuid = '"
				+ jblUuid + "'";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql);
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress != null && ress.next()) {
			personid = ress.getString("personid");
		}
		ress.close();
		conn.close();
		pst.close();
		return this.HqFind(personid);
	}

	// 会签----根据personid查询人名
	public String HqFind(String id) throws SQLException {
		StringBuffer sql = null;
		String deptname = "";
		Connection conn = null;
		ResultSet ress = null;
		sql = new StringBuffer();
		sql.append("select cnname from ro_person t where t.personid = '");
		sql.append(id);
		sql.append("'");
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql.toString());
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql.toString());
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ress != null && ress.next()) {

			deptname = ress.getString("cnname");
		}
		ress.close();
		conn.close();
		pst.close();
		return deptname;

	}

	// 会签收文--收文已发会签列表

	public void HqSwYf() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();

		int totalCount = 0;
		totalCount = this.HqYfCount();
		map.put("totalProperty", totalCount);

		List<OaBean> sendlist = this.HqSwYfList();

		for (int i = 0; i < sendlist.size(); i++) {
			int uncount = sendlist.get(i).getUnreceivecount();
			int count = sendlist.get(i).getReceivecount();
			int backcount = sendlist.get(i).getBackcount();
			int total = sendlist.get(i).getTotalcount();
			int blwb = sendlist.get(i).getBlwb();
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			String extdate = formatDate(sendlist.get(i).getSwrq(), "yyyy-MM-dd");
			String extdate1 = formatDate(sendlist.get(i).getFssj(),
					"yyyy-MM-dd hh:mm:ss");
			childrenmap.put("BT", sendlist.get(i).getWjbt());
			childrenmap.put("BH", sendlist.get(i).getYwbh());
			childrenmap.put("DJ", sendlist.get(i).getJjcd());
			childrenmap.put("NGDW", sendlist.get(i).getYwdw());
			childrenmap.put("NGSJ", extdate);
			childrenmap.put("WSDW", uncount);
			childrenmap.put("YSDW", count);
			childrenmap.put("TH", backcount);
			childrenmap.put("FSDWS", total);
			childrenmap.put("YBJ", blwb);
			childrenmap.put("FSSJ", extdate1);
			childrens.add(childrenmap);
		}

		map.put("totalProperty", totalCount);
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

	}

	// 会签发文--发文已发会签列表

	public void HqFwYf() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();

		int totalCount = 0;
		totalCount = this.HqYfCount();
		map.put("totalProperty", totalCount);

		List<OaBean> sendlist = this.HqFwYfList(Integer.valueOf(deptid));

		for (int i = 0; i < sendlist.size(); i++) {
			int count = sendlist.get(i).getReceivecount();
			int backcount = sendlist.get(i).getBackcount();
			int total = sendlist.get(i).getTotalcount();
			int blwb = sendlist.get(i).getBlwb();
			String DJ = sendlist.get(i).getJjcd();
			String dxcs = "";
			String unreceive = "";

			Map<String, Object> childrenmap = new HashMap<String, Object>();
			String extdate = formatDate(sendlist.get(i).getNgsj(), "yyyy-MM-dd");
			String extdate1 = formatDate(sendlist.get(i).getFssj(),
					"yyyy-MM-dd hh:mm:ss");
			childrenmap.put("BT", sendlist.get(i).getBt());
			if (sendlist.get(i).getWjbh() != null) {
				childrenmap.put("BH", sendlist.get(i).getWjbh());

			} else {
				childrenmap.put("BH", "");
			}

			childrenmap.put("uuid", sendlist.get(i).getUuid());
			childrenmap.put("NGDW", sendlist.get(i).getNgdw());
			childrenmap.put("NGSJ", extdate);
			childrenmap.put("WSDW", unreceive);
			childrenmap.put("YSDW", count);
			childrenmap.put("TH", backcount);
			childrenmap.put("FSDWS", total);
			childrenmap.put("YBJ", blwb);
			childrenmap.put("DJ", DJ);
			childrenmap.put("dxcs", dxcs);
			childrenmap.put("FSSJ", extdate1);
			childrens.add(childrenmap);
		}

		map.put("totalProperty", totalCount);
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

	}

	/* 会签发文--已发条数 */
	public int HqYfCount() throws SQLException {
		int totalCount = 0;
		String sql = "select count(*) from HQ_YEWU a where a.fsdwid='" + deptid
				+ "'";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			totalCount = ress.getInt(1);
		}
		ress.close();
		conn.close();
		pst.close();
		return totalCount;
	}

	// 会签收文--收文会签已发查询

	public List<OaBean> HqSwYfList() throws SQLException {
		List<OaBean> sendlist = new ArrayList<OaBean>();
		int index = Integer.parseInt(start);
		int pageSize = Integer.parseInt(limit);
		@SuppressWarnings("unused")
		int totalCount = 0;
		totalCount = this.HqYfCount();
		List<Object> paramList = new ArrayList<Object>();
		String sql = "select * from (select A.*,ROWNUM RN from (select t.*,nvl((select count(d1.uuid) from HQ_DANWEI d1 where  d1.huiqian_uuid=t.uuid  and d1.sign_flag=0),0) as unreceivecount,nvl( (select count(d2.uuid) from HQ_DANWEI d2 where  d2.huiqian_uuid=t.uuid  and d2.sign_flag=4), 0) as receivecount,nvl( (select count(d5.uuid) from HQ_DANWEI d5 where  d5.huiqian_uuid=t.uuid  and d5.sign_flag=5), 0) as blwb,"
				+ "nvl( (select count(d3.uuid) from HQ_DANWEI d3 where  d3.huiqian_uuid=t.uuid  and d3.sign_flag=3), 0) as backcount,nvl( (select count(d4.uuid) from HQ_DANWEI d4 where  d4.huiqian_uuid=t.uuid), 0) as totalcount  from HQ_SWYEWU t where t.fsdwid ='"
				+ deptid + "' order by t.swrq desc) A)where RN>? and RN <=?";
		paramList.add(index);
		paramList.add(pageSize);
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++) {
				Object o = paramList.get(i);
				if (o instanceof Date) {

					pst.setDate(i + 1, (java.sql.Date) o);
				} else {
					pst.setObject(i + 1, o);
				}
			}
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			OaBean hydao = new OaBean();
			hydao.setUuid(ress.getString("uuid"));
			hydao.setYwdw(ress.getString("ywdw"));

			hydao.setJjcd(ress.getString("jjcd"));
			hydao.setSwbh(ress.getString("swbh"));
			hydao.setWjbt(ress.getString("wjbt"));
			hydao.setNbyj(ress.getString("nbyj"));
			hydao.setYf(ress.getString("yf"));
			hydao.setSwrq(ress.getDate("swrq"));
			hydao.setYwbh(ress.getString("ywbh"));
			hydao.setFfzh(ress.getString("ffzh"));
			hydao.setJbr(ress.getString("jbr"));
			hydao.setHdr(ress.getString("hdr"));
			hydao.setUnreceivecount(ress.getInt("unreceivecount"));
			hydao.setReceivecount(ress.getInt("receivecount"));
			hydao.setBackcount(ress.getInt("backcount"));
			hydao.setTotalcount(ress.getInt("totalcount"));
			hydao.setBlwb(ress.getInt("blwb"));
			hydao.setFssj(ress.getDate("fssj"));
			sendlist.add(hydao);
		}
		ress.close();
		return sendlist;

	}

	// 会签--已发查询
	public List<OaBean> HqFwYfList(int deptid) throws SQLException {
		List<OaBean> sendlist = new ArrayList<OaBean>();
		int index = Integer.parseInt(start);
		int pageSize = Integer.parseInt(limit);
		@SuppressWarnings("unused")
		int totalCount = 0;
		List<Object> paramList = new ArrayList<Object>();
		totalCount = this.HqYfCount();
		String sql = "select * from (select A.*,ROWNUM RN from (select t.*,nvl((select count(d1.uuid) from HQ_DANWEI d1 where  d1.huiqian_uuid=t.uuid  and d1.sign_flag=0),0) as unreceivecount,nvl( (select count(d2.uuid) from HQ_DANWEI d2 where  d2.huiqian_uuid=t.uuid  and d2.sign_flag=4), 0) as receivecount,nvl( (select count(d5.uuid) from HQ_DANWEI d5 where  d5.huiqian_uuid=t.uuid  and d5.sign_flag=5), 0) as blwb,"
				+ "nvl( (select count(d3.uuid) from HQ_DANWEI d3 where  d3.huiqian_uuid=t.uuid  and d3.sign_flag=3), 0) as backcount,nvl( (select count(d4.uuid) from HQ_DANWEI d4 where  d4.huiqian_uuid=t.uuid), 0) as totalcount  from HQ_YEWU t where t.fsdwid ='"
				+ deptid + "' order by t.ngsj desc) A)where RN>? and RN <=?";
		paramList.add(index);
		paramList.add(pageSize);
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++) {
				Object o = paramList.get(i);
				if (o instanceof Date) {

					pst.setDate(i + 1, (java.sql.Date) o);
				} else {
					pst.setObject(i + 1, o);
				}
			}
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			OaBean hydao = new OaBean();
			hydao.setUuid(ress.getString("uuid"));
			if (ress.getString("wjbh") != null) {
				hydao.setWjbh(ress.getString("wjbh"));
			} else {
				hydao.setWjbh("");
			}
			hydao.setNgdw(ress.getString("ngdw"));
			hydao.setNgr(ress.getString("ngr"));
			hydao.setNgsj(ress.getDate("ngsj"));
			hydao.setWjlx(ress.getString("wjlx"));
			hydao.setJjcd(ress.getString("jjcd"));

			hydao.setBt(ress.getString("bt"));
			hydao.setNbyj(ress.getString("nbyj"));
			hydao.setHgr(ress.getString("hgr"));
			hydao.setHgdw(ress.getString("hgdw"));
			hydao.setHgsj(ress.getDate("hgsj"));
			hydao.setGkfs(ress.getString("gkfs"));
			hydao.setCs(ress.getString("cs"));
			hydao.setZs(ress.getString("zs"));
			hydao.setYffs(ress.getString("yffs"));

			hydao.setYfsj(ress.getDate("yfsj"));
			hydao.setXd(ress.getString("xd"));
			hydao.setDz(ress.getString("dz"));
			hydao.setBwxh(ress.getString("bwxh"));
			hydao.setFsdwid(ress.getInt("fsdwid"));
			hydao.setFlag1(ress.getString("flag"));
			hydao.setUnreceivecount(ress.getInt("unreceivecount"));
			hydao.setReceivecount(ress.getInt("receivecount"));
			hydao.setBackcount(ress.getInt("backcount"));
			hydao.setTotalcount(ress.getInt("totalcount"));
			hydao.setBlwb(ress.getInt("blwb"));
			hydao.setFssj(ress.getDate("fssj"));
			sendlist.add(hydao);
		}
		ress.close();
		return sendlist;

	}

	// 会签---调度表条数
	public int HqAmqp(String username, String password, int flag)
			throws SQLException {
		int HqAmqp = 0;
		String sql = "select count(*) from hq_amqp_temp";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag == 0) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag == 2) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			HqAmqp = ress.getInt(1);
		}
		ress.close();
		conn.close();
		pst.close();
		return HqAmqp;
	}

	/* 公文交换-----现实列表 已发，已收，未收 */

	public String Gwjh() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();

		Map<String, Object> childrenmap = new HashMap<String, Object>();
		String gwjhyf = "<a href=\"admin/gwjhyf.jsp?username=" + username
				+ "&password=" + password + "&deptid=" + deptid + "&flag="
				+ flag + "\")>" + "已发公文" + "</a>";
		String gwjhws = "<a href=\"admin/gwjhws.jsp?username=" + this.username
				+ "&password=" + this.password + "&deptid=" + deptid + "&flag="
				+ flag + "\")>" + "未收公文" + "</a>";
		String gwjhys = "<a href=\"admin/gwjhys.jsp?username=" + this.username
				+ "&password=" + this.password + "&deptid=" + deptid + "&flag="
				+ flag + "\")>" + "已收公文" + "</a>";
		childrenmap.put("gwjhyf", gwjhyf);
		childrenmap.put("gwjhws", gwjhws);
		childrenmap.put("gwjhys", gwjhys);
		childrens.add(childrenmap);
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

	/* 公文交换-----删除调度数据 */

	public String GwAmqpDel() throws SQLException {
		String sql = "delete from tbl_amqp_temp where uuid='" + delid + "'";
		Connection conn = null;
		Statement stmt = null;
		conn = DriverManager.getConnection(url, username, password);
		stmt = conn.createStatement();
		stmt.executeUpdate(sql);
		conn.close();
		stmt.close();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();

		Map<String, Object> childrenmap = new HashMap<String, Object>();
		childrenmap.put("username", username);
		childrenmap.put("password", password);
		childrens.add(childrenmap);
		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		return "GwDel";
	}

	// 查询人员部门
	public List<OaBean> FindPersonId(String username, String password)
			throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet ress = null;
		List<OaBean> PersonId = new ArrayList<OaBean>();
		String sql = "select * from ro_person";
		try {

			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			ress = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		while (ress.next()) {
			OaBean oaBean = new OaBean();
			oaBean.setPersonid(ress.getString("personid"));
			PersonId.add(oaBean);
		}
		ress.close();
		stmt.close();
		conn.close();
		return PersonId;
	}

	// 公文交换---调度表条数
	public int GwAmqp(String username, String password, int flag)
			throws SQLException {
		int totalCount = 0;
		String sql = "select count(*) from tbl_amqp_temp";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag == 0) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag == 2) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			totalCount = ress.getInt(1);
		}
		ress.close();
		conn.close();
		pst.close();
		return totalCount;
	}

	/* 发文办理---- 办结列表条数 */
	public int Fwbl(String username, String password) throws SQLException {
		int fwbl = 0;
		Connection conn = null;
		Statement stmt = null;
		ResultSet ress = null;
		String sql = "select count(*) from fdt_oa_gwg_process";
		try {
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			ress = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		while (ress.next()) {
			fwbl = ress.getInt(1);
		}
		ress.close();
		stmt.close();
		conn.close();
		return fwbl;
	}

	/* 会签发文办理---- 办结列表条数 */
	public int GwTj(String username, String password, int flag)
			throws SQLException {
		int completed = 0;

		String sql = "select count(*) from bpm_processinstance where processinst_state != 'aborted'";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag == 0) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag == 2) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		while (ress.next()) {
			completed = ress.getInt(1);
		}
		ress.close();
		conn.close();
		pst.close();
		return completed;
	}

	/* 公文交换---- 调度表详细 */
	@SuppressWarnings("unchecked")
	public String GwAmqp() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<OaBean> inceptlist = new ArrayList();
		String sql = "select * from tbl_amqp_temp order by createtime desc";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			OaBean bean = new OaBean();
			bean.setUuid(ress.getString("uuid"));
			bean.setOperatetype(ress.getInt("operate_type"));
			bean.setOperateuuid(ress.getString("operate_uuid"));
			bean.setCreatetime(ress.getTimestamp("createtime"));
			bean.setFlag(ress.getInt("flag"));
			inceptlist.add(bean);
		}
		ress.close();
		conn.close();
		pst.close();
		map.put("totalProperty", inceptlist.size());
		for (int i = 0; i < inceptlist.size(); i++) {
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			int flag1 = inceptlist.get(i).getOperatetype();
			String zt = "";
			String title = "";
			String sendid = "";
			String qxdw = "";
			String qsdw = "";
			String inceptid = "";
			// 操作类型，0-发文，1-补发，2-取消，3-签收，4-退回，5-回执，6-中止报名, 7-继续报名, 8-报名,9-删除已发公文
			if (flag1 == 0) {

				String fasong = "select t2.send_title from tbl_send t2 where t2.uuid ='"
						+ inceptlist.get(i).getOperateuuid() + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				while (ress.next()) {
					title = ress.getString("send_title");
				}
				ress.close();
				conn.close();
				pst.close();
				zt = "[0-发送]";
			} else if (flag1 == 1) {
				String fasong = "select t2.send_title from tbl_send t2 where t2.uuid ='"
						+ inceptlist.get(i).getOperateuuid() + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					title = ress.getString("send_title");
				}
				ress.close();
				zt = "[1-补发]";
			} else if (flag1 == 2) {
				String fasong = "select * from tbl_incept where uuid ='"
						+ inceptlist.get(i).getOperateuuid() + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					qxdw = ress.getString("incept_dept");
					sendid = ress.getString("send_id");
				}
				ress.close();
				conn.close();
				pst.close();
				String fasong1 = "select t2.send_title from tbl_send t2 where t2.uuid ='"
						+ sendid + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong1);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong1);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					title = ress.getString("send_title");
				}
				ress.close();
				conn.close();
				pst.close();
				zt = "[2-取消]" + qxdw;
			} else if (flag1 == 3) {
				String fasong = "select * from tbl_incept where uuid ='"
						+ inceptlist.get(i).getOperateuuid() + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					qsdw = ress.getString("incept_dept");
					sendid = ress.getString("send_id");
				}
				ress.close();
				conn.close();
				pst.close();
				String fasong1 = "select t2.send_title from tbl_send t2 where t2.uuid ='"
						+ sendid + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong1);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong1);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					title = ress.getString("send_title");
				}
				ress.close();
				conn.close();
				pst.close();
				zt = "[3-签收]" + qsdw;
			} else if (flag1 == 4) {
				String fasong = "select * from tbl_incept where uuid ='"
						+ inceptlist.get(i).getOperateuuid() + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					qsdw = ress.getString("incept_dept");
					sendid = ress.getString("send_id");
				}
				ress.close();
				conn.close();
				pst.close();
				String fasong1 = "select t2.send_title from tbl_send t2 where t2.uuid ='"
						+ sendid + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong1);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong1);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					title = ress.getString("send_title");
				}
				ress.close();
				conn.close();
				pst.close();
				zt = "[4-拒签]" + qsdw;
			} else if (flag1 == 5) {
				String fasong = "select * from tbl_reply where uuid ='"
						+ inceptlist.get(i).getOperateuuid() + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					inceptid = ress.getString("incept_id");
					sendid = ress.getString("send_id");
				}
				ress.close();
				conn.close();
				pst.close();
				String fasong1 = "select t2.send_title from tbl_send t2 where t2.uuid ='"
						+ sendid + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong1);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong1);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					title = ress.getString("send_title");
				}
				ress.close();
				conn.close();
				pst.close();
				String fasong2 = "select * from tbl_incept where uuid ='"
						+ inceptid + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong2);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong2);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					qsdw = ress.getString("incept_dept");
				}
				ress.close();
				conn.close();
				pst.close();
				zt = "[5-回执]" + qsdw;
			} else if (flag1 == 6) {
				String fasong = "select t2.send_title from tbl_send t2 where t2.uuid ='"
						+ inceptlist.get(i).getOperateuuid() + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					title = ress.getString("send_title");
				}
				ress.close();
				conn.close();
				pst.close();
				zt = "[6-终止报名]";
			} else if (flag1 == 7) {
				String fasong = "select t2.send_title from tbl_send t2 where t2.uuid ='"
						+ inceptlist.get(i).getOperateuuid() + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					title = ress.getString("send_title");
				}
				ress.close();
				conn.close();
				pst.close();
				zt = "[7-继续报名]";
			} else if (flag1 == 8) {
				String fasong = "select * from tbl_incept where uuid ='"
						+ inceptlist.get(i).getOperateuuid() + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					qsdw = ress.getString("incept_dept");
					sendid = ress.getString("send_id");
				}
				ress.close();
				conn.close();
				pst.close();
				String fasong1 = "select t2.send_title from tbl_send t2 where t2.uuid ='"
						+ sendid + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong1);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong1);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					title = ress.getString("send_title");
				}
				ress.close();
				conn.close();
				pst.close();
				zt = "[8-报名]" + qsdw;
			} else if (flag1 == 9) {
				String fasong = "select t2.send_title from tbl_send t2 where t2.uuid ='"
						+ inceptlist.get(i).getOperateuuid() + "'";
				if (flag.equals("0")) {
					try {
						conn = DriverManager.getConnection(url, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					}
				} else if (flag.equals("2")) {
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver")
								.newInstance();
						conn = DriverManager.getConnection(oracleurl, username,
								password);
						pst = conn.prepareStatement(fasong);
						ress = pst.executeQuery();
					} catch (SQLException ex) {
						System.err.println(ex.getMessage());
					} catch (InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				while (ress.next()) {
					title = ress.getString("send_title");
				}
				ress.close();
				conn.close();
				pst.close();
				zt = "[9-删除已发公文]";
			}
			String del = "<a href=\"Chaxun_GwAmqpDel.action?username="
					+ this.username + "&password=" + this.password + "&delid="
					+ inceptlist.get(i).getUuid() + "\")>" + "删除数据" + "</a>";
			String createtime = formatDate(inceptlist.get(i).getCreatetime(),
					"yyyy-MM-dd HH:mm:ss");
			childrenmap.put("uuid", inceptlist.get(i).getUuid());
			childrenmap.put("qxdw", qxdw);
			childrenmap.put("zt", zt);
			childrenmap.put("createtime", createtime);
			childrenmap.put("title", title);
			childrenmap.put("del", del);
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

	// 公文交换---- 已发列表
	public String GwYf() throws SQLException {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		@SuppressWarnings("unused")
		java.sql.Date startDate = null;
		@SuppressWarnings("unused")
		java.sql.Date endDate = null;

		if (from != null) {
			startDate = new java.sql.Date(from.getTime());
		}
		if (to != null) {
			endDate = new java.sql.Date(to.getTime());
		}
		int totalCount = this.GwYfCount();
		List<OaBean> sendlist = this.GwYfList();
		map.put("totalProperty", totalCount);
		for (int i = 0; i < sendlist.size(); i++) {
			String etitle = sendlist.get(i).getSendTitle();
			int uncount = sendlist.get(i).getUnreceivecount();
			int count = sendlist.get(i).getReceivecount();
			int total = sendlist.get(i).getTotalcount();
			// 未收单位
			String unreceive;
			if (uncount != 0) {
				unreceive = "<a href=\"admin/gwjhwsdw.jsp?username="
						+ this.username + "&password=" + this.password
						+ "&uuid=" + sendlist.get(i).getUuid() + "&deptid="
						+ this.deptid + "&flag=" + flag + "\")>"
						+ "<font  color='red'>" + uncount + "</font>" + "</a>";
			} else {

				unreceive = "<a href=\"admin/gwjhwsdw.jsp?username="
						+ this.username + "&password=" + this.password
						+ "&uuid=" + sendlist.get(i).getUuid() + "&deptid="
						+ this.deptid + "&flag=" + flag + "\")>" + uncount
						+ "</a>";
			}

			// 已收单位
			String receive;
			String replyflag = this.GwYfFlag(sendlist.get(i).getUuid());
			if (replyflag != null && replyflag.equals("1")) {
				receive = "<a  href=\"admin/gwjhysdw.jsp?username="
						+ this.username + "&password=" + this.password
						+ "&uuid=" + sendlist.get(i).getUuid() + "&deptid="
						+ this.deptid + "&flag=" + flag + "\")>"
						+ "<font  color='red'>" + count + "</font>" + "</a>";
			} else {
				receive = "<a  href=\"admin/gwjhysdw.jsp?username="
						+ this.username + "&password=" + this.password
						+ "&uuid=" + sendlist.get(i).getUuid() + "&deptid="
						+ this.deptid + "&flag=" + this.flag + "\")>" + count
						+ "</a>";
			}

			// 拒签单位
			@SuppressWarnings("unused")
			String back;
			int backcount = sendlist.get(i).getBackcount();
			if (backcount != 0) {
				back = "<a href=\"desktop/widgets/gwjh/sendbacklist.jsp?uuid="
						+ sendlist.get(i).getUuid() + "&flag=" + this.flag
						+ "&status=1" + "\")>" + "<font  color='red'>"
						+ backcount + "</font>" + "</a>";
			} else {
				back = "<a href=\"desktop/widgets/gwjh/sendbacklist.jsp?uuid="
						+ sendlist.get(i).getUuid() + "&flag=" + this.flag
						+ "&status=1" + "\")>" + backcount + "</a>";
			}

			Map<String, Object> childrenmap = new HashMap<String, Object>();
			String sendtime = formatDate(sendlist.get(i).getSendtime(),
					"yyyy-MM-dd  HH:mm:ss");
			childrenmap.put("uuid", sendlist.get(i).getUuid());
			childrenmap.put("title", etitle);
			childrenmap.put("fdbh", sendlist.get(i).getSendType());
			childrenmap.put("sendor", sendlist.get(i).getSendor());
			childrenmap.put("senddept", sendlist.get(i).getSenddept());
			childrenmap.put("sendtime", sendtime);
			childrenmap.put("copy", total);
			childrenmap.put("wh", sendlist.get(i).getSendWh());
			childrenmap.put("unreceive", unreceive);
			childrenmap.put("receive", receive);
			childrenmap.put("back", backcount);
			childrens.add(childrenmap);
		}

		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		try {

			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String GwYfFlag(String sendid) {
		String flag1 = null;
		StringBuffer sql = null;
		sql = new StringBuffer();
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		sql.append("select reply_flag from tbl_incept t where t.send_id = '");
		sql.append(sendid);
		sql.append("' and t.reply_flag='1'");
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql.toString());
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql.toString());

				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			if (ress != null && ress.next()) {
				flag1 = "1";
			} else {
				flag1 = "0";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ress.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag1;

	}

	/* 公文交换---- 查看已收单位 */
	public String GwYfYsDw() throws SQLException {
		List<OaBean> inceptlist = new ArrayList<OaBean>();
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		String sql = "select * from TBL_INCEPT where send_id='" + uuid
				+ "' and cancel_flag = 0 and back_flag=0 and sign_receive=" + 1;
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql.toString());
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql.toString());
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			OaBean personbean = new OaBean();
			personbean.setUuid(ress.getString("uuid"));
			personbean.setInceptor(ress.getString("inceptor"));
			personbean.setInceptDept(ress.getString("incept_dept"));
			personbean.setInceptTime(ress.getTimestamp("incept_time"));
			inceptlist.add(personbean);
		}

		ress.close();
		conn.close();
		pst.close();
		String ckreply = "没有回执";
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		map.put("totalProperty", inceptlist.size());
		for (int i = 0; i < inceptlist.size(); i++) {
			String flag = this.GwYfYsDwFlag(inceptlist.get(i).getUuid());
			if (flag != null && flag.equals("1")) {
				ckreply = "<font  color='red'>有回执信息</font>";
			}

			String reply = ckreply;
			String inceptor = "";
			String incepttime = "";
			if (inceptlist.get(i).getInceptor() != null) {
				inceptor = inceptlist.get(i).getInceptor();
			}
			if (inceptlist.get(i).getInceptTime() != null) {
				incepttime = formatDate(inceptlist.get(i).getInceptTime(),
						"yyyy-MM-dd HH:mm:ss");
			}
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			childrenmap.put("uuid", inceptlist.get(i).getUuid());
			childrenmap.put("Inceptor", inceptor);
			childrenmap.put("Inceptdept", inceptlist.get(i).getInceptDept());
			childrenmap.put("Incepttime", incepttime);
			childrenmap.put("reply", reply);
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

	public String GwYfYsDwFlag(String uuid) throws SQLException {
		String flag1 = null;
		StringBuffer sql = null;
		sql = new StringBuffer();
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		sql.append("select reply_flag from tbl_incept t where t.uuid = '");
		sql.append(uuid);
		sql.append("'");
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql.toString());
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql.toString());
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ress != null && ress.next()) {

			flag1 = ress.getString("reply_flag");
		}
		ress.close();
		conn.close();
		pst.close();
		return flag1;

	}

	/* 公文交换---- 未收公文单位 */
	public String GwYfWsDw() throws SQLException {
		List<OaBean> inceptlist = new ArrayList<OaBean>();
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		String sql = "select * from TBL_INCEPT where send_id='" + uuid
				+ "' and cancel_flag = 0 and back_flag=0 and sign_receive=" + 0;
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			OaBean personbean = new OaBean();
			personbean.setUuid(ress.getString("uuid"));
			personbean.setInceptor(ress.getString("inceptor"));
			personbean.setInceptDept(ress.getString("incept_dept"));
			inceptlist.add(personbean);
		}
		ress.close();
		conn.close();
		pst.close();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		map.put("totalProperty", inceptlist.size());
		for (int i = 0; i < inceptlist.size(); i++) {
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			String inceptor = "";
			String incept = "";
			if (inceptlist.get(i).getInceptor() != null) {
				inceptor = inceptlist.get(i).getInceptor();
			}
			incept = inceptlist.get(i).getInceptDept();
			childrenmap.put("uuid", inceptlist.get(i).getUuid());
			childrenmap.put("unInceptor", inceptor);
			childrenmap.put("unInceptdept", incept);
			childrenmap.put("cancel", inceptlist.get(i).getUuid());
			childrenmap.put("flag", flag);
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

	// 公文交换---- 查询公文交换总条数
	public int GwjhYfCount(String username, String password, String deptid,
			int flag) {
		int totalCount = 0;
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		String sql = "select count(*) from TBL_SEND t where t.SEND_DEPT_ID="
				+ Integer.valueOf(deptid);
		if (flag == 0) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag == 2) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			while (ress.next()) {
				totalCount = ress.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ress.close();
			conn.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return totalCount;
	}

	// 公文交换----已收公文总条数
	public int GwYsCount(String username, String password, String deptId,
			int flag) throws SQLException {
		int totalCount = 0;

		String sql = "select count(t1.uuid) from TBL_INCEPT t ,TBL_SEND t1 where t.incept_deptid="
				+ Integer.valueOf(deptId)
				+ "and t1.uuid=t.send_id and  t.cancel_Flag=0 and t.back_flag=0 and sign_receive=1";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag == 0) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag == 2) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			totalCount = ress.getInt(1);
		}
		try {
			ress.close();
			conn.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return totalCount;

	}

	// 公文交换---- 查询公文交换总条数
	public int GwYfCount() throws SQLException {
		int totalCount = 0;
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select count(*) from TBL_SEND t where t.SEND_DEPT_ID=?";
		paramlist.add(Integer.valueOf(deptid));
		if (title != null && title.length() > 0) {
			sql += "and t.send_title like ?";
			paramlist.add("%" + title + "%");
		}
		if (from != null) {
			sql += "and t.send_time >= ?";
			paramlist.add(from);
		}
		if (to != null) {
			sql += "and t.send_time <=?";
			paramlist.add(to);
		}
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramlist.size(); i++) {
					Object o = paramlist.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramlist.size(); i++) {
					Object o = paramlist.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			totalCount = ress.getInt(1);
		}
		ress.close();
		conn.close();
		pst.close();
		return totalCount;
	}

	// 公文交换---- 已收List
	public List<OaBean> GwYfList() throws SQLException {
		List<OaBean> sendlist = new ArrayList<OaBean>();
		List<Object> paramlist = new ArrayList<Object>();
		String sql = "select * from (select A.*,ROWNUM RN from (select t.*,nvl((select count(incept1.uuid) from TBL_INCEPT incept1 where  incept1.send_id=t.uuid  and incept1.sign_receive=0 and incept1.cancel_flag=0),0) as unreceivecount , nvl( (select count(incept1.uuid) from TBL_INCEPT incept1 where  incept1.send_id=t.uuid  and incept1.sign_receive=1 and incept1.cancel_flag=0), 0) as receivecount,"
				+ "nvl( (select count(incept1.uuid) from TBL_INCEPT incept1 where  incept1.send_id=t.uuid and incept1.cancel_flag=0), 0) as totalcount,"
				+ "nvl( (select count(incept1.uuid) from TBL_INCEPT incept1 where  incept1.send_id=t.uuid  and incept1.sign_receive=1 and incept1.back_flag=1 and incept1.cancel_flag=0), 0) as backcount  from TBL_SEND t where t.SEND_DEPT_ID=?";
		paramlist.add(Integer.valueOf(deptid));
		if (title != null && title.length() > 0) {
			sql += "and t.send_title like ?";
			paramlist.add("%" + title + "%");
		}
		if (from != null) {
			sql += "and t.send_time >= ?";
			paramlist.add(from);
		}
		if (to != null) {
			sql += "and t.send_time <=?";
			paramlist.add(to);
		}
		sql += "order by t.send_time desc";
		sql += ") A) " + "where RN> ? and RN<=?";
		paramlist.add(Integer.valueOf(start));
		paramlist.add(Integer.valueOf(start) + Integer.valueOf(limit));
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paramlist.size(); i++) {
				Object o = paramlist.get(i);
				if (o instanceof Date) {

					pst.setDate(i + 1, (java.sql.Date) o);
				} else {
					pst.setObject(i + 1, o);
				}
			}
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramlist.size(); i++) {
					Object o = paramlist.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			OaBean bean = new OaBean();
			bean.setUuid(ress.getString("uuid"));
			bean.setSendTitle(ress.getString("send_title"));
			bean.setSendType(ress.getString("send_type"));
			bean.setSenddept(ress.getString("send_dept"));
			bean.setSendor(ress.getString("sendor"));
			bean.setSendtime(ress.getTimestamp("send_time"));
			bean.setSendWh(ress.getString("send_wh"));
			bean.setUnreceivecount(ress.getInt("unreceivecount"));
			bean.setBackcount(ress.getInt("backcount"));
			bean.setReceivecount(ress.getInt("receivecount"));
			bean.setTotalcount(ress.getInt("totalcount"));

			sendlist.add(bean);
		}
		ress.close();
		conn.close();
		pst.close();
		return sendlist;
	}

	// 公文交换----已收列表
	public String GwYs() throws NumberFormatException, SQLException {
		java.sql.Date startDate = null;
		java.sql.Date endDate = null;
		if (from != null) {
			startDate = new java.sql.Date(from.getTime());
		}
		if (to != null) {
			endDate = new java.sql.Date(to.getTime());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		int totalCount = this.GwYsCount(Integer.valueOf(deptid), 1, title,
				startDate, endDate);
		List<Map<String, String>> listmap = this.GwYsList(Integer
				.valueOf(deptid), 1, title, Integer.parseInt(start), Integer
				.parseInt(limit), startDate, endDate);
		map.put("totalProperty", totalCount);
		try {
			for (int i = 0; i < listmap.size(); i++) {
				Map<String, Object> childrenmap = new HashMap<String, Object>();
				String etitle = listmap.get(i).get("sendTitle");
				childrenmap.put("uuid", listmap.get(i).get("uuid"));
				childrenmap.put("title", etitle);
				childrenmap.put("fdbh", listmap.get(i).get("fdbh"));
				childrenmap.put("sendor", listmap.get(i).get("sendor"));
				childrenmap.put("senddept", listmap.get(i).get("senddept"));
				childrenmap.put("sendtime", listmap.get(i).get("sendtime"));
				childrenmap.put("jjcd", listmap.get(i).get("jjcd"));
				childrenmap.put("wh", listmap.get(i).get("wh"));
				childrens.add(childrenmap);
			}
			map.put("root", childrens);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

	// 公文交换---已收公文详细
	public List<Map<String, String>> GwYsList(int deptId, Integer signFlag,
			String title, int start, int limit, Date from, Date to)
			throws SQLException {
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		List<Object> paramList = new ArrayList<Object>();
		List<Map<String, String>> beanslist = new ArrayList<Map<String, String>>();
		String sql = "select * from (select A.*,RowNum RN from "
				+ "(select t1.send_title,t1.sendor,t1.send_dept,t1.send_time,t1.send_mj,t1.send_jjcd,t1.send_type,t1.send_copy,t1.send_wh ,t.uuid ,t.send_id "
				+ "from TBL_INCEPT t ,TBL_SEND t1 where t.incept_deptid= ? and t1.uuid=t.send_id  and  t.cancel_Flag=0 and t.back_flag=0 and t.sign_receive=?";
		paramList.add(deptId);
		paramList.add(signFlag);
		if (title != null && title.length() > 0) {
			sql += "and t1.send_title like ?";
			paramList.add("%" + title + "%");
		}
		if (from != null) {
			sql += "and t1.send_time >=?";
			paramList.add(from);
		}
		if (to != null) {
			sql += "and t1.send_time <=?";
			paramList.add(to);
		}
		sql += "order by t1.send_time desc";
		sql += ")A ) where RN>? and RN<=?";

		paramList.add(start);
		paramList.add(start + limit);
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paramList.size(); i++) {
				Object o = paramList.get(i);
				if (o instanceof Date) {

					pst.setDate(i + 1, (java.sql.Date) o);
				} else {
					pst.setObject(i + 1, o);
				}
			}
			ress = pst.executeQuery();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			Map<String, String> beans = new HashMap<String, String>();
			String sendtime = formatDate(ress.getDate("send_time"),
					"yyyy-MM-dd  HH:mm:ss");
			beans.put("sendTitle", ress.getString("send_title"));
			beans.put("sendor", ress.getString("sendor"));
			beans.put("uuid", ress.getString("uuid"));
			beans.put("sendid", ress.getString("send_id"));
			beans.put("senddept", ress.getString("send_dept"));
			beans.put("sendtime", sendtime);
			beans.put("mj", ress.getString("send_mj"));
			beans.put("wh", ress.getString("send_wh"));
			beans.put("jjcd", ress.getString("send_jjcd"));
			beans.put("copy", ress.getString("send_copy"));
			beans.put("fdbh", ress.getString("send_type"));
			beans.put("wh", ress.getString("send_wh"));
			beanslist.add(beans);
		}
		ress.close();
		conn.close();
		pst.close();
		return beanslist;
	}

	// 公文交换----已收公文总条数
	public int GwYsCount(Integer deptId, Integer signFlag, String title,
			Date from, Date to) throws SQLException {
		int totalCount = 0;
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		List<Object> paramList = new ArrayList<Object>();
		String sql = "select count(t1.uuid) from TBL_INCEPT t ,TBL_SEND t1 where t.incept_deptid=? and t1.uuid=t.send_id and  t.cancel_Flag=0 and t.back_flag=0 and sign_receive=?";
		paramList.add(deptId);
		paramList.add(signFlag);
		if (title != null && title.length() > 0) {
			sql += "and t1.send_title like ?";
			paramList.add("%" + title + "%");
		}
		if (from != null) {
			sql += "and t1.send_time >=?";
			paramList.add(from);
		}
		if (to != null) {
			sql += "and t1.send_time <=?";
			paramList.add(to);
		}
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			totalCount = ress.getInt(1);
		}
		ress.close();
		conn.close();
		pst.close();
		return totalCount;

	}

	// 公文交换----未收列表
	public String GwWs() throws NumberFormatException, SQLException {
		@SuppressWarnings("unused")
		java.sql.Date startDate = null;
		@SuppressWarnings("unused")
		java.sql.Date endDate = null;
		if (from != null) {
			startDate = new java.sql.Date(from.getTime());
		}
		if (to != null) {
			endDate = new java.sql.Date(to.getTime());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		int totalCount = this.GwWsCount(Integer.valueOf(deptid), 0, title,
				startDate, endDate);
		List<Map<String, String>> listmap = this.GwWsList(Integer
				.valueOf(deptid), 0, title, Integer.parseInt(start), Integer
				.parseInt(limit), startDate, endDate);
		map.put("totalProperty", totalCount);
		try {
			for (int i = 0; i < listmap.size(); i++) {
				Map<String, Object> childrenmap = new HashMap<String, Object>();
				String etitle = listmap.get(i).get("sendTitle");
				childrenmap.put("uuid", listmap.get(i).get("uuid"));
				childrenmap.put("title", etitle);
				childrenmap.put("fdbh", listmap.get(i).get("fdbh"));
				childrenmap.put("sendor", listmap.get(i).get("sendor"));
				childrenmap.put("senddept", listmap.get(i).get("senddept"));
				childrenmap.put("sendtime", listmap.get(i).get("sendtime"));
				childrenmap.put("wh", listmap.get(i).get("wh"));
				childrenmap.put("jjcd", listmap.get(i).get("jjcd"));
				childrens.add(childrenmap);
			}
			map.put("root", childrens);

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

	/* 公文交换---未收条数 */
	public int GwWsCount(Integer deptId, Integer signFlag, String title,
			Date from, Date to) throws SQLException {
		int totalCount = 0;
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		List<Object> paramList = new ArrayList<Object>();
		String sql = "select count(t1.uuid) from TBL_INCEPT t ,TBL_SEND t1 where t.incept_deptid=? and t1.uuid=t.send_id and  t.cancel_Flag=0 and t.back_flag=0 and sign_receive=?";
		paramList.add(deptId);
		paramList.add(signFlag);
		if (title != null && title.length() > 0) {
			sql += "and t1.send_title like ?";
			paramList.add("%" + title + "%");
		}
		if (from != null) {
			sql += "and t1.send_time >=?";
			paramList.add(from);
		}
		if (to != null) {
			sql += "and t1.send_time <=?";
			paramList.add(to);
		}
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			totalCount = ress.getInt(1);
		}
		ress.close();
		conn.close();
		pst.close();
		return totalCount;

	}

	/* 公文交换---未收公文List */
	public List<Map<String, String>> GwWsList(int deptId, Integer signFlag,
			String title, int start, int limit, Date from, Date to)
			throws SQLException {
		List<Object> paramList = new ArrayList<Object>();
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		List<Map<String, String>> beanslist = new ArrayList<Map<String, String>>();
		String sql = "select * from (select A.*,RowNum RN from "
				+ "(select t1.send_title,t1.sendor,t1.send_dept,t1.send_time,t1.send_mj,t1.send_jjcd,t1.send_type,t1.send_copy,t1.send_wh ,t.uuid ,t.send_id "
				+ "from TBL_INCEPT t ,TBL_SEND t1 where t.incept_deptid= ? and t1.uuid=t.send_id  and  t.cancel_Flag=0 and t.back_flag=0 and t.sign_receive=?";
		paramList.add(deptId);
		paramList.add(signFlag);
		if (title != null && title.length() > 0) {
			sql += "and t1.send_title like ?";
			paramList.add("%" + title + "%");
		}
		if (from != null) {
			sql += "and t1.send_time >=?";
			paramList.add(from);
		}
		if (to != null) {
			sql += "and t1.send_time <=?";
			paramList.add(to);
		}
		sql += "order by t1.send_time desc";
		sql += ")A ) where RN>? and RN<=?";

		paramList.add(start);
		paramList.add(start + limit);
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				for (int i = 0; i < paramList.size(); i++) {
					Object o = paramList.get(i);
					if (o instanceof Date) {

						pst.setDate(i + 1, (java.sql.Date) o);
					} else {
						pst.setObject(i + 1, o);
					}
				}
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			Map<String, String> beans = new HashMap<String, String>();
			String sendtime = formatDate(ress.getDate("send_time"),
					"yyyy-MM-dd  HH:mm:ss");
			beans.put("sendTitle", ress.getString("send_title"));
			beans.put("sendor", ress.getString("sendor"));
			beans.put("uuid", ress.getString("uuid"));
			beans.put("sendid", ress.getString("send_id"));
			beans.put("senddept", ress.getString("send_dept"));
			beans.put("sendtime", sendtime);
			beans.put("mj", ress.getString("send_mj"));
			beans.put("wh", ress.getString("send_wh"));
			beans.put("jjcd", ress.getString("send_jjcd"));
			beans.put("copy", ress.getString("send_copy"));
			beans.put("fdbh", ress.getString("send_type"));
			beans.put("wh", ress.getString("send_wh"));
			beanslist.add(beans);
		}
		ress.close();
		conn.close();
		pst.close();
		return beanslist;
	}

	/* 短信提醒-----查询项 */

	public String SMS() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();

		Map<String, Object> childrenmap = new HashMap<String, Object>();
		String all = "<a href=\"admin/msmall.jsp?username=" + this.username
				+ "&password=" + this.password + "&flag=" + flag + "\")>"
				+ "所有查询" + "</a>";
		String qxtdfs = "<a href=\"admin/msmqxtdfs.jsp?username="
				+ this.username + "&password=" + this.password + "&flag="
				+ flag + "\")>" + "企信通待发送查询" + "</a>";
		String qxtyfs = "<a href=\"admin/msmqxtyfs.jsp?username="
				+ this.username + "&password=" + this.password + "&flag="
				+ flag + "\")>" + "企信通已发送查询" + "</a>";
		childrenmap.put("all", all);
		childrenmap.put("qxtdfs", qxtdfs);
		childrenmap.put("qxtyfs", qxtyfs);
		childrens.add(childrenmap);
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

	/* 短信提醒-----查询所有短信发送记录 */

	@SuppressWarnings("unchecked")
	public String SMSAll() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<OaBean> inceptlist = new ArrayList();
		String sql = "select * from tbl_msgnewsreel order by createtime desc";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			OaBean bean = new OaBean();
			bean.setUuid(ress.getString("uuid"));
			bean.setCreatetime(ress.getTimestamp("createtime"));
			bean.setUsername(ress.getString("username"));
			bean.setTel1(ress.getString("tel1"));
			bean.setTel2(ress.getString("tel2"));
			bean.setFlag1(ress.getString("flag"));
			bean.setType(ress.getString("type"));
			bean.setMsg(ress.getString("msg"));
			inceptlist.add(bean);
		}
		ress.close();
		conn.close();
		pst.close();
		map.put("totalProperty", inceptlist.size());
		for (int i = 0; i < inceptlist.size(); i++) {
			String uuid = inceptlist.get(i).getUuid();
			String msg = inceptlist.get(i).getMsg();
			String username = inceptlist.get(i).getUsername();
			String tel1 = inceptlist.get(i).getTel1();
			String tel2 = inceptlist.get(i).getTel2();
			String flag1 = inceptlist.get(i).getFlag1();
			String type = inceptlist.get(i).getType();
			String createtime = formatDate(inceptlist.get(i).getCreatetime(),
					"yyyy-MM-dd HH:mm:ss");
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			childrenmap.put("uuid", uuid);
			childrenmap.put("xh", i + 1);
			childrenmap.put("msg", msg);
			childrenmap.put("username", username);
			childrenmap.put("createtime", createtime);
			childrenmap.put("tel1", tel1);
			childrenmap.put("tel2", tel2);
			childrenmap.put("flag", flag1);
			childrenmap.put("type", type);
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

	/* 短信提醒-----查询企信通短信待发送记录 */
	@SuppressWarnings("unchecked")
	public String SMSQxtDfs() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<OaBean> inceptlist = new ArrayList();
		String sql = "select * from dfsdl order by deadtime desc";
		ResultSet ress = null;
		Connection conn = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			OaBean bean = new OaBean();
			bean.setId(ress.getInt("id"));
			bean.setMobile(ress.getString("mobile"));
			bean.setDeadtime(ress.getTimestamp("deadtime"));
			bean.setContent(ress.getString("content"));
			bean.setStatus(ress.getInt("status"));
			bean.setEid(ress.getString("eid"));
			bean.setUserid(ress.getString("userid"));
			bean.setPassword(ress.getString("password"));
			inceptlist.add(bean);
		}
		ress.close();
		conn.close();
		pst.close();
		map.put("totalProperty", inceptlist.size());
		for (int i = 0; i < inceptlist.size(); i++) {
			int xh = i + 1;
			String Deadtime = formatDate(inceptlist.get(i).getDeadtime(),
					"yyyy-MM-dd HH:mm:ss");

			Map<String, Object> childrenmap = new HashMap<String, Object>();
			childrenmap.put("Mobile", inceptlist.get(i).getMobile());
			childrenmap.put("xh", xh);
			childrenmap.put("Content", inceptlist.get(i).getContent());
			int a = inceptlist.get(i).getStatus();
			if (a == 0) {
				childrenmap.put("Status", a);
			} else {
				String b = "<font color='red'>" + a + "</font>";
				childrenmap.put("Status", b);
			}
			String update = "<a href=\"Chaxun_SMSQxtDfsUpdateStatus.action?username="
					+ this.username
					+ "&password="
					+ this.password
					+ "&id="
					+ inceptlist.get(i).getId() + "\")>" + "修改" + "</a>";
			childrenmap.put("Eid", inceptlist.get(i).getEid());
			childrenmap.put("Userid", inceptlist.get(i).getUserid());
			childrenmap.put("Password", inceptlist.get(i).getPassword());
			childrenmap.put("Deadtime", Deadtime);
			childrenmap.put("Update", update);
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

	/* 企信通-----修改发送状态 */

	public String SMSQxtDfsUpdateStatus() throws SQLException {
		String sql = "update  dfsdl set status= 0 where id='" + id + "'";
		Connection conn = null;
		Statement stmt = null;
		ResultSet ress = null;
		conn = DriverManager.getConnection(url, username, password);
		stmt = conn.createStatement();
		ress = stmt.executeQuery(sql);
		ress.close();
		stmt.close();
		conn.close();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();

		Map<String, Object> childrenmap = new HashMap<String, Object>();
		childrenmap.put("username", username);
		childrenmap.put("password", password);
		childrens.add(childrenmap);
		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		return "updateStatus";
	}

	/* 短信提醒-----查询企信通短信已发送记录 */
	@SuppressWarnings("unchecked")
	public String SMSQxtYfs() throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<OaBean> inceptlist = new ArrayList();
		String sql = "select * from yfsdl order by deadtime desc";
		ResultSet ress = null;
		Connection conn = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while (ress.next()) {
			OaBean bean = new OaBean();
			bean.setId(ress.getInt("id"));
			bean.setMobile(ress.getString("mobile"));
			bean.setDeadtime(ress.getTimestamp("deadtime"));
			bean.setContent(ress.getString("content"));
			bean.setStatus(ress.getInt("status"));
			bean.setEid(ress.getString("eid"));
			bean.setUserid(ress.getString("userid"));
			bean.setPassword(ress.getString("password"));
			inceptlist.add(bean);
		}
		ress.close();
		conn.close();
		pst.close();
		map.put("totalProperty", inceptlist.size());
		for (int i = 0; i < inceptlist.size(); i++) {
			int xh = i + 1;
			String Deadtime = formatDate(inceptlist.get(i).getDeadtime(),
					"yyyy-MM-dd HH:mm:ss");
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			childrenmap.put("Mobile", inceptlist.get(i).getMobile());
			childrenmap.put("xh", xh);
			childrenmap.put("Content", inceptlist.get(i).getContent());
			int a = inceptlist.get(i).getStatus();
			if (a == 1) {
				childrenmap.put("Status", a);
			} else {
				String b = "<font color='red'>" + a + "</font>";
				childrenmap.put("Status", b);
			}
			childrenmap.put("Eid", inceptlist.get(i).getEid());
			childrenmap.put("Userid", inceptlist.get(i).getUserid());
			childrenmap.put("Password", inceptlist.get(i).getPassword());
			childrenmap.put("Deadtime", Deadtime);
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

	/* 管理员-----查看管理员 */
	public String GPersonList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<OaBean> inceptlist = new ArrayList<OaBean>();
		DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
		conn = dbbase.getConn();
		String sql = "select * from OA_person WHERE FLAG='1' order by CREATEDATE";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				OaBean bean = new OaBean();
				bean.setPassword(rs.getString("password"));
				bean.setUsername(rs.getString("username"));
				bean.setFlag1(rs.getString("flag"));
				bean.setUserid(rs.getString("uuid"));
				inceptlist.add(bean);
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
		map.put("totalProperty", inceptlist.size());
		for (int i = 0; i < inceptlist.size(); i++) {
			int id = i + 1;
			Map<String, Object> childrenmap = new HashMap<String, Object>();
			childrenmap.put("password", inceptlist.get(i).getPassword());
			childrenmap.put("id", id);
			childrenmap.put("username", inceptlist.get(i).getUsername());
			childrenmap.put("cz", "删除管理员");
			childrenmap.put("user", inceptlist.get(i).getUserid());
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

	/* OA管理员-----查看管理员 */
	public String DmOAGL() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet ress = null;
		String personid = "";
		String sql = "select * from ro_personextaccount";
		try {
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			ress = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		try {
			while (ress.next()) {

				personid = ress.getString("adminid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ress.close();
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personid;
	}

	/* OA管理员-----查看管理员 */
	public String OracleOAGL() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet ress = null;
		String personid = "";
		String sql = "select * from ro_personextaccount";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
			conn = DriverManager.getConnection(oracleurl, username, password);
			stmt = conn.createStatement();
			ress = stmt.executeQuery(sql);
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (ress.next()) {
				personid = ress.getString("adminid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			ress.close();
			conn.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return personid;
	}

	/* 查看人员-----查找指定单位人员列表 */
	public String PersonList() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<OaBean> sendlist = new ArrayList<OaBean>();
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		String sql = "select t1.*,t2.* from ro_personaccount t1,ro_person t2 where t1.personid=t2.personid and t1.accountstat=3 order by userid ";
		if (flag.equals("0")) {

			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
				while (ress.next()) {
					OaBean personbean = new OaBean();
					personbean.setCnname(ress.getString("cnname"));
					personbean.setUserid(ress.getString("userid"));
					personbean.setPersonid(ress.getString("personid"));
					personbean.setPassword(ress.getString("password"));
					sendlist.add(personbean);
				}
				ress.close();
				pst.close();
				for (int i = 0; i < sendlist.size(); i++) {

					String personid = sendlist.get(i).getPersonid();
					String userid = sendlist.get(i).getUserid();
					String password = sendlist.get(i).getPassword();
					String cnname = sendlist.get(i).getCnname();
					String gl = this.DmOAGL();
					String oagl = "";
					if (gl != null && !gl.equals("")) {
						if (gl.equals(personid)) {
							oagl = "<a href=\"Chaxun_OAGldel.action?username="
									+ this.username + "&password="
									+ this.password + "&personid=" + personid
									+ "&flag=" + flag + "\")>" + "管理员[点击删除管理员]"
									+ "</a>";
						}
					} else {
						oagl = "<a href=\"Chaxun_OAGladd.action?username="
								+ this.username + "&password=" + this.password
								+ "&personid=" + personid + "&flag=" + flag
								+ "\")>" + "设置管理员" + "</a>";
					}
					int flag1 = 0;
					String sql1 = "select count(*) from kzxd_tixing where personid='"
							+ personid + "'";
					pst = conn.prepareStatement(sql1);
					ress = pst.executeQuery();
					while (ress.next()) {
						flag1 = ress.getInt(1);
					}
					ress.close();
					String cktx = "";
					if (flag1 != 0) {
						cktx = "<a href=\"admin/cktx.jsp?personid=" + personid
								+ "&flag=" + flag + "&password="
								+ this.password + "&username=" + this.username
								+ "\")>" + "查看提醒[" + flag1 + "]" + "</a>";
					}
					Map<String, Object> childrenmap = new HashMap<String, Object>();
					childrenmap.put("id", i + 1);
					childrenmap.put("cnname", cnname);
					childrenmap.put("personid", personid);
					childrenmap.put("userid", userid);
					childrenmap.put("password", password);
					childrenmap.put("cktx", cktx);
					childrenmap.put("oagl", oagl);
					childrens.add(childrenmap);
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
				while (ress.next()) {
					OaBean personbean = new OaBean();
					personbean.setCnname(ress.getString("cnname"));
					personbean.setUserid(ress.getString("userid"));
					personbean.setPersonid(ress.getString("personid"));
					personbean.setPassword(ress.getString("password"));
					sendlist.add(personbean);
				}
				ress.close();
				pst.close();
				for (int i = 0; i < sendlist.size(); i++) {

					String personid = sendlist.get(i).getPersonid();
					String userid = sendlist.get(i).getUserid();
					String password = sendlist.get(i).getPassword();
					String cnname = sendlist.get(i).getCnname();
					String gl = this.OracleOAGL();
					String oagl = "";
					if (gl != null && !gl.equals("")) {
						if (gl.equals(personid)) {
							oagl = "<a href=\"Chaxun_OAGldel.action?username="
									+ this.username + "&password="
									+ this.password + "&personid=" + personid
									+ "&flag=" + flag + "\")>" + "管理员[点击删除管理员]"
									+ "</a>";
						}
					} else {
						oagl = "<a href=\"Chaxun_OAGladd.action?username="
								+ this.username + "&password=" + this.password
								+ "&personid=" + personid + "&flag=" + flag
								+ "\")>" + "设置管理员" + "</a>";
					}
					int flag1 = 0;
					String sql1 = "select count(*) from kzxd_tixing where personid='"
							+ personid + "'";
					pst = conn.prepareStatement(sql1);
					ress = pst.executeQuery();
					while (ress.next()) {
						flag1 = ress.getInt(1);
					}
					ress.close();
					String cktx = "";
					if (flag1 != 0) {
						cktx = "<a href=\"admin/cktx.jsp?personid=" + personid
								+ "&flag=" + flag + "&password="
								+ this.password + "&username=" + this.username
								+ "\")>" + "查看提醒[" + flag1 + "]" + "</a>";
					}
					Map<String, Object> childrenmap = new HashMap<String, Object>();
					childrenmap.put("id", i + 1);
					childrenmap.put("cnname", cnname);
					childrenmap.put("personid", personid);
					childrenmap.put("userid", userid);
					childrenmap.put("password", password);
					childrenmap.put("cktx", cktx);
					childrenmap.put("oagl", oagl);
					childrens.add(childrenmap);
				}
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		try {

			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/* 单位人员-----查找单个人提醒 */
	public String PersonTx() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		List<OaBean> sendlist = new ArrayList<OaBean>();
		String sql = "select * from kzxd_tixing where personid='" + personid
				+ "'";
		Connection conn = null;
		ResultSet ress = null;
		PreparedStatement pst = null;
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
				while (ress.next()) {
					OaBean bean = new OaBean();
					bean.setMkname(ress.getString("mkname"));
					bean.setDelid(ress.getString("delid"));
					bean.setPersonid(ress.getString("personid"));
					bean.setTime(ress.getTimestamp("time"));
					bean.setTitle(ress.getString("title"));
					bean.setUuid(ress.getString("uuid"));
					sendlist.add(bean);
				}
				for (int i = 0; i < sendlist.size(); i++) {
					String delid = sendlist.get(i).getDelid();
					String mkname = sendlist.get(i).getMkname();
					String personid = sendlist.get(i).getPersonid();
					String title = sendlist.get(i).getTitle();
					String time = formatDate(sendlist.get(i).getTime(),
							"yyyy-MM-dd HH:mm:ss");
					Map<String, Object> childrenmap = new HashMap<String, Object>();
					String del = "<a href=\"Chaxun_PerTxdel.action?username="
							+ this.username + "&password=" + this.password
							+ "&personid=" + personid + "&delid=" + delid
							+ "&flag=" + flag + "\")>" + "删除提醒" + "</a>";
					childrenmap.put("delid", delid);
					childrenmap.put("id", i + 1);
					childrenmap.put("mkname", mkname);
					childrenmap.put("personid", personid);
					childrenmap.put("title", title);
					childrenmap.put("time", time);
					childrenmap.put("del", del);
					childrens.add(childrenmap);
				}
				ress.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
				while (ress.next()) {
					OaBean bean = new OaBean();
					bean.setMkname(ress.getString("mkname"));
					bean.setDelid(ress.getString("delid"));
					bean.setPersonid(ress.getString("personid"));
					bean.setTime(ress.getTimestamp("time"));
					bean.setTitle(ress.getString("title"));
					bean.setUuid(ress.getString("uuid"));
					sendlist.add(bean);
				}
				for (int i = 0; i < sendlist.size(); i++) {
					String delid = sendlist.get(i).getDelid();
					String mkname = sendlist.get(i).getMkname();
					String personid = sendlist.get(i).getPersonid();
					String title = sendlist.get(i).getTitle();
					String time = formatDate(sendlist.get(i).getTime(),
							"yyyy-MM-dd HH:mm:ss");
					Map<String, Object> childrenmap = new HashMap<String, Object>();
					String del = "<a href=\"Chaxun_PerTxdel.action?username="
							+ this.username + "&password=" + this.password
							+ "&personid=" + personid + "&delid=" + delid
							+ "&flag=" + flag + "\")>" + "删除提醒" + "</a>";
					childrenmap.put("delid", delid);
					childrenmap.put("id", i + 1);
					childrenmap.put("mkname", mkname);
					childrenmap.put("personid", personid);
					childrenmap.put("title", title);
					childrenmap.put("time", time);
					childrenmap.put("del", del);
					childrens.add(childrenmap);
				}
				ress.close();
				pst.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		try {

			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	/* 查看人员-----删除单人提醒 */

	public String PerTxdel() throws SQLException {
		String sql = "delete from KZXD_TIXING where delid='" + delid + "'";
		Connection conn = null;
		Statement stmt = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.close();
			stmt.close();
		} else if (flag.equals("2")) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
				stmt.close();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		Map<String, Object> childrenmap = new HashMap<String, Object>();
		childrenmap.put("personid", personid);
		childrenmap.put("username", username);
		childrenmap.put("password", password);
		childrenmap.put("flag", flag);
		childrens.add(childrenmap);
		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		return "deletx";
	}

	/* OA单位-----删除管理员 */

	public String OAGldel() throws SQLException {
		String sql = "delete from ro_personextaccount where adminid='"
				+ personid + "'";
		Connection conn = null;
		Statement stmt = null;
		if (flag.equals("0")) {
			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.close();
			stmt.close();
		} else if (flag.equals("2")) {

			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
				stmt.close();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				return "OAGldel";
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				return "OAGldel";
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				return "OAGldel";
			}

		}

		return "OAGldel";
	}

	/* OA单位-----设置管理员 */

	public String OAGladd() {
		Connection conn = null;
		Statement stmt = null;
		String time = formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		String sql = "insert into ro_personextaccount (uuid,sysid,webmasterid,adminid,createtime) values ('"
				+ personid
				+ "','16416','"
				+ personid
				+ "','"
				+ personid
				+ "','" + time + "')";
		if (flag.equals("0")) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				stmt = conn.createStatement();
				stmt.executeUpdate(sql);
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return "OAGldel";
			}

		} else if (flag.equals("2")) {
			try {
				String sql1 = "insert into ro_personextaccount (uuid,sysid,webmasterid,adminid) values ('"
						+ personid
						+ "','16416','"
						+ personid
						+ "','"
						+ personid + "')";
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				stmt = conn.createStatement();
				stmt.executeUpdate(sql1);
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				return "OAGldel";
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				return "OAGldel";
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				return "OAGldel";
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				return "OAGldel";
			}
		}
		return "OAGldel";
	}

	/**
	 * 单位监控--添加单位列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public String AddDw() {

		return "AddDw";
	}

	public String Gxts() {
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> childrens = new ArrayList<Map<String, Object>>();
		Map<String, Object> childrenmap = new HashMap<String, Object>();
		childrenmap.put("fileList", fileList);
		childrens.add(childrenmap);
		map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja = JSONObject.fromObject(map);
		return "Gxts";
	}

	public String AddPerson() {
		return "AddPerson";
	}

	/**
	 * 单位监控--保存单位列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public void addPer(OaPersonDAO oapersondao) {
		oapersondao.getPassword();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection connect = dbbase.getConn();
		try {
			oapersondao.setConnection(connect);
			oapersondao.setFlag("1");
			oapersondao.setUuid((new UUID()).toString());
			oapersondao.create();
			connect.commit();
		} catch (SQLException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (DAOException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			dbbase.close();

		}
	}

	public String Ckgly() {

		return "Ckgly";
	}

	/**
	 * 单位监控--添加单位列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addDw() {
		String uuid = (new UUID()).toString();
		dao.setUuid(uuid);
		this.AddDw(dao);

		return "addDw";
	}

	public String gxts() {
		System.out.println(id);
		System.out.println(deptnames);
		String[] ids = null;
		String[] names = null;
		if (id != null && id.length() > 0) {
			ids = id.split(",");
			names = deptnames.split(",");
			for (int q = 0; q < ids.length; q++) {
				List<OaBean> sendlist = this.OADwByDeptId(ids[q]);
				for (int i = 0; i < sendlist.size(); i++) {
					String username = sendlist.get(i).getUsername();
					String password = sendlist.get(i).getPassword();
					int flag = sendlist.get(i).getFlag();
					List<OaBean> PersonList = this.PersonList(username,
							password, flag);
					for (int h = 0; h < PersonList.size(); h++) {
						String personid = PersonList.get(h).getPersonid();

						try {
							this.InstGx(username, password, personid, msm,
									(new UUID()).toString(), flag);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}

		}

		return "addDw";
	}

	public List<OaBean> OADwByIndex(String uuid) {
		List<OaBean> sendlist = new ArrayList<OaBean>();
		DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
		conn = dbbase.getConn();
		String sql = "select * from OA where uuid='" + uuid + "'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				OaBean bean = new OaBean();
				bean.setDw(rs.getString("dw"));
				bean.setDworder(String.valueOf(rs.getInt("dworder")));
				bean.setPassword(rs.getString("password"));
				bean.setUsername(rs.getString("username"));
				bean.setUuid(rs.getString("uuid"));
				bean.setDeptid(rs.getString("deptid"));
				bean.setFlag(rs.getInt("flag"));
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

	/* OA单位-----删除管理员 */
	/* 单位人员-----查找指定单位人员列表 */
	public List<OaBean> PersonList(String username, String password, int flag) {
		List<OaBean> sendlist = new ArrayList<OaBean>();
		String sql = "select t1.*,t2.* from ro_personaccount t1,ro_person t2 where t1.personid=t2.personid and t1.accountstat=3 order by userid ";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet ress = null;

		if (flag == 0) {
			try {
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag == 2) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				ress = pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			while (ress.next()) {
				OaBean personbean = new OaBean();
				personbean.setPersonid(ress.getString("personid"));
				sendlist.add(personbean);
			}
			ress.close();
			conn.close();
			pst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sendlist;

	}

	public String InstGx(String username, String password, String personid,
			String msm, String uuid, int flag) throws SQLException {
		PreparedStatement pst = null;
		Connection conn = null;

		if (flag == 0) {
			try {
				String time = formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
				String sql = "insert into KZXD_TIXING (uuid,personid,time,mkname,flag,title,delid) values('"
						+ uuid
						+ "','"
						+ personid
						+ "','"
						+ time
						+ "','系统提醒','1','" + msm + "','" + personid + "')";
				String sql2 = "insert into oa_GXTX (uuid,MSM,createdate,PERSONID) values('"
						+ uuid
						+ "','"
						+ msm
						+ "','"
						+ time
						+ "','"
						+ personid
						+ "')";
				conn = DriverManager.getConnection(url, username, password);
				pst = conn.prepareStatement(sql);
				pst.executeQuery();
				pst = conn.prepareStatement(sql2);
				pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			}
		} else if (flag == 2) {
			try {
				String time = formatDate(new Date(), "HH:mm:ss");
				String sql = "insert into KZXD_TIXING (uuid,personid,time,mkname,flag,title,delid) values('"
						+ uuid
						+ "','"
						+ personid
						+ "',to_date('"
						+ time
						+ "','HH24:MI:SS'),'系统提醒','1','"
						+ msm
						+ "','"
						+ personid + "')";
				String sql2 = "insert into oa_GXTX (uuid,MSM,createdate,PERSONID) values('"
						+ uuid
						+ "','"
						+ msm
						+ "',to_date('"
						+ time
						+ "','HH24:MI:SS'),'" + personid + "')";
				Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
				conn = DriverManager.getConnection(oracleurl, username,
						password);
				pst = conn.prepareStatement(sql);
				pst.executeQuery();
				pst = conn.prepareStatement(sql2);
				pst.executeQuery();
			} catch (SQLException ex) {
				System.err.println(ex.getMessage());
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;
	}

	/**
	 * 单位监控--保存单位列表
	 * 
	 * @return
	 * @throws IOException
	 */
	public void AddDw(OaDAO dao) {
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection connect = dbbase.getConn();
		try {
			dao.setConnection(connect);
			dao.create();
			connect.commit();
		} catch (SQLException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (DAOException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			if (connect != null) {
				try {
					connect.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			dbbase.close();

		}
	}

	/*
	 * 监控单位---查找监控单位
	 */

	// 达梦
	public void Dm() {
		try {
			Class.forName("dm.jdbc.driver.DmDriver");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	// 达梦--数据库查询（无参数）
	public List<OaBean> PersonList(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet ress = null;
		List<OaBean> personlist = new ArrayList<OaBean>();
		try {

			conn = DriverManager.getConnection(url, username, password);
			stmt = conn.createStatement();
			ress = stmt.executeQuery(sql);
			while (ress.next()) {
				OaBean personbean = new OaBean();
				personbean.setCnname(ress.getString("cnname"));
				personbean.setUserid(ress.getString("userid"));
				personbean.setPersonid(ress.getString("personid"));
				personbean.setPassword(ress.getString("password"));
				personlist.add(personbean);
			}
			ress.close();
			conn.close();
			stmt.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return personlist;
	}

	// 达梦--数据库查询（传参数）
	@SuppressWarnings("unchecked")
	public ResultSet Dm(String sql, List paramlist) {
		Connection conn = null;
		ResultSet ress = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			PreparedStatement pst = null;
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paramlist.size(); i++) {
				Object o = paramlist.get(i);
				if (o instanceof Date) {

					pst.setDate(i + 1, (java.sql.Date) o);
				} else {
					pst.setObject(i + 1, o);
				}
			}
			ress = pst.executeQuery();
			conn.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}
		return ress;
	}

	// 连接MySql
	public List<OaBean> MySqlDu(String MySqlUrl, String mysql, String name,
			String pass) {
		List<OaBean> list = new ArrayList<OaBean>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet ress = null;
		try {
			conn = DriverManager.getConnection(MySqlUrl, name, pass);
			stmt = conn.createStatement();
			ress = stmt.executeQuery(mysql);
			try {
				while (ress.next()) {
					OaBean bean = new OaBean();
					bean.setZxuuid(ress.getInt("uuid"));
					bean.setOperate_type(ress.getInt("operate_type"));
					bean.setOperate_uuid(ress.getInt("operate_uuid"));
					bean.setCreatetime(ress.getTimestamp("create_time"));
					bean.setFlag(ress.getInt("flag"));
					list.add(bean);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			conn.close();
			stmt.close();
			ress.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return list;
	}

	// 连接MySql
	public List<OaBean> MySqlList(String MySqlUrl, String mysql, String name,
			String pass) {
		List<OaBean> list = new ArrayList<OaBean>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet ress = null;
		try {
			conn = DriverManager.getConnection(MySqlUrl, name, pass);
			stmt = conn.createStatement();
			ress = stmt.executeQuery(mysql);
			try {
				while (ress.next()) {
					OaBean bean = new OaBean();
					bean.setTitle(ress.getString("send_title"));
					bean.setSenddept(ress.getString("send_dept"));
					list.add(bean);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			conn.close();
			stmt.close();
			ress.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return list;
	}

	// 连接MySql
	public List<OaBean> MySqlList1(String MySqlUrl, String mysql, String name,
			String pass) {
		List<OaBean> list = new ArrayList<OaBean>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet ress = null;
		try {
			conn = DriverManager.getConnection(MySqlUrl, name, pass);
			stmt = conn.createStatement();
			ress = stmt.executeQuery(mysql);
			try {
				while (ress.next()) {
					OaBean bean = new OaBean();
					bean.setTitle(ress.getString("send_id"));
					bean.setSenddept(ress.getString("incept_dept"));
					list.add(bean);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			conn.close();
			stmt.close();
			ress.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return list;
	}

	// 连接MySql
	public int MySql(String MySqlUrl, String mysql, String name, String pass) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet ress = null;
		int ZxAmqp = 0;
		try {
			conn = DriverManager.getConnection(MySqlUrl, name, pass);
			stmt = conn.createStatement();
			ress = stmt.executeQuery(mysql);
			try {
				while (ress.next()) {
					ZxAmqp = ress.getInt(1);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			conn.close();
			stmt.close();
			ress.close();
		} catch (SQLException ex) {
			System.err.println(ex.getMessage());
		}

		return ZxAmqp;
	}

	// private String url = "jdbc:oracle:thin:@127.0.0.1:1521:ORCL";

	/*
	 * private String MySqlUrl =
	 * "jdbc:mysql://127.0.0.1:3306/docexchange?useUnicode=true&amp;characterEncoding=UTF-8";
	 * private String url = "jdbc:dm://127.0.0.1:5236"; private String oracleurl =
	 * "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	 */

	private String MySqlUrl = "jdbc:mysql://19.177.51.85:3306/docexchange?useUnicode=true&amp;characterEncoding=UTF-8";
	private String url = "jdbc:dm://19.177.51.88:5236";
	private String oracleurl = "jdbc:oracle:thin:@19.177.51.87:1521:orcl";

	private String username;
	private String password;
	private String personid;
	private JSONObject ja;
	private OaDAO dao;
	private OaPersonDAO oapersondao;
	private String deptid;
	private String delid;
	private Date from;
	private Date to;
	private String start;
	private String limit;
	private String title;
	private String uuid;
	private String id;
	private String msm;
	private String fileList;
	private String flag;
	private String deptnames;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getMsm() {
		return msm;
	}

	public void setMsm(String msm) {
		this.msm = msm;
	}

	public OaPersonDAO getOapersondao() {
		return oapersondao;
	}

	public void setOapersondao(OaPersonDAO oapersondao) {
		this.oapersondao = oapersondao;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public OaDAO getDao() {
		return dao;
	}

	public void setDao(OaDAO dao) {
		this.dao = dao;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDelid() {
		return delid;
	}

	public void setDelid(String delid) {
		this.delid = delid;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}

	private String formatDate(java.util.Date date, String fmstr) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sfd = new SimpleDateFormat(fmstr);
		return sfd.format(date);
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLimit() {
		return limit;
	}

	public void setLimit(String limit) {
		this.limit = limit;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public JSONObject getJa() {
		return ja;
	}

	public void setJa(JSONObject ja) {
		this.ja = ja;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileList() {
		return fileList;
	}

	public void setFileList(String fileList) {
		this.fileList = fileList;
	}

	public String getDeptnames() {
		return deptnames;
	}

	public void setDeptnames(String deptnames) {
		this.deptnames = deptnames;
	}
}
