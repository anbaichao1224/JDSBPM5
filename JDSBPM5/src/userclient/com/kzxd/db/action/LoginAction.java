package com.kzxd.db.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kzxd.filter.RecordSessionListener;
import com.kzxd.filter.User;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.bpm.engine.BPMSessionHandle;
import net.itjds.bpm.engine.ConnectInfo;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.webservice.WSException;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.usm.dao.RoLogDAO;
import net.itjds.userclient.usm.plugins.nds.operator.AutherUser;
import net.itjds.userclient.usm.ws.WSUtil;
import net.itjds.userclient.usm.ws.server.ServerBean;
import net.itjds.userclient.usm.ws.server.ServerBeanManager;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.OrgManager;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonMsgNotFoundException;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.conf.Constants;
import net.itjds.common.org.impl.database.DbPerson;
import net.itjds.common.util.DateUtility;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.util.UUID;
import net.itjds.oa.OAConstants;

/**
 * <p>
 * Title: BPM工作流管理系统
 * </p>
 * <p>
 * Description: 演示程序应用登陆LoginAction
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: 基督山BPM
 * </p>
 * 
 * @author wenzhang
 * @version 2.1
 */
public class LoginAction  {
	protected static Log log = LogFactory.getLog(Constants.CONFIG_KEY,
			LoginAction.class);
	private String username;

	private String password;

	private String message;

	private String subServerUrl;
	public static HashMap<String, Integer> map = new HashMap<String, Integer>();

	public LoginAction() {
	}

	@SuppressWarnings("static-access")
	public String execute() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String personId="";
		 try {
			personId = this.findPersonId(username, password);
			if(personId!=null&&!personId.equals("")){
				request.getSession().setAttribute("personId", username);
				return Action.SUCCESS;
			}else{
				this.message = "用户名或密码不正确";
				return Action.LOGIN;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;

		
	}

	public String  findPersonId(String userid,String password){
		String personid = null;
		Statement stmt = null;
		StringBuffer sql = null;
		DBBeanBase dbbase = new DBBeanBase("org");
		Connection conn = dbbase.getConn();
		String flag = "false";
	    try {
			
			stmt = conn.createStatement();
			sql = new StringBuffer();
			sql.append("select t.uuid from oa_person t where t.username = '");
			sql.append(userid);
			sql.append("'");
			sql.append("and t.password='");
			sql.append(password);
			sql.append("'");
			ResultSet rs = stmt.executeQuery(sql.toString());
			if(rs != null && rs.next()){
				personid = rs.getString("UUID");
				
				
			}
			rs.close();
			stmt.close();
			conn.close();
			dbbase.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			}
		}
		return personid;
		
		
	}


	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSubServerUrl() {
		return subServerUrl;
	}

	public void setSubServerUrl(String subServerUrl) {
		this.subServerUrl = subServerUrl;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
