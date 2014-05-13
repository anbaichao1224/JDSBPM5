package com.kzxd.xzsp.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.xml.rpc.ServiceException;

import net.itjds.common.database.DBBeanBase;

import org.apache.struts2.ServletActionContext;
import org.appfuse.webapp.action.BaseAction;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import com.kzxd.xzsp.util.Acceptnode;
import com.kzxd.xzsp.util.Application1;
import com.kzxd.xzsp.util.Applynode;
import com.kzxd.xzsp.util.Handlenode;
import com.kzxd.xzsp.util.Material;
import com.kzxd.xzsp.util.Node;
import com.kzxd.xzsp.util.Notifynode;
import com.kzxd.xzsp.util.Permission;
import com.kzxd.xzsp.util.XzspjkTempBFBean;
import com.opensymphony.xwork2.Action;

@SuppressWarnings("serial")
public class XZSPAction extends BaseAction implements Action{
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private DBBeanBase dbbase = null;
	
	private List<Permission> lbList = null;
	private Permission per = null;
	private Application1 ap = null;
	
	private String applicationid;
	private String permissionid;
	private String applicationidList;
	
	private Application1 application = null;
	private Permission permission = null;
	private Material material = null;
	private Node node = null;
	private Notifynode notifynode = null;
	private Acceptnode acceptnode = null;
	private Applynode applynode = null;
	private Handlenode handlenode = null;

	private List<Material> materials = null;
	private List<Node> nodes = null;
	private List<Notifynode> notifynodes = null;
	private List<Acceptnode> acceptnodes = null;
	private List<Applynode> applynodes = null;
	private List<Handlenode> handlenodes = null;
	
	private HashMap<String, Object> allStl = null;
    
	private String bsnum = null;
	private String permissionId = null;
    private StringBuffer sql = null;

    //��ҳ
    private String start;
    private String limit;
    private int totalCount;
    
    //δ��������
    private String deluuid;
    
    //����޸�����״̬Ϊ���״̬status��ֵ��0��Ϊ1
    public void xzspXgzt(String bsnum){
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		String sqlStr = "update xzspjk_permission t set t.status='1' where t.bsnum='"+bsnum+"'";
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.createStatement();
			st.executeUpdate(sqlStr);
			conn.commit();
						
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
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
    
  //������������xml�ļ�
    public List<String> tbcxSlscXml(String bsnum,Handlenode hn){
    	
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		Statement st = null;
		ResultSet rs = null;
		Permission p = null;
		Application1 a = null;
		String appid = "";
		try{
			st = conn.createStatement();
			
			String sqlp = "select * from xzspjk_permission t where t.bsnum='"+bsnum+"'";	
			rs = st.executeQuery(sqlp);
			if(rs != null && rs.next()){
				p = new Permission();
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setBsnum(rs.getString("bsnum"));
			  	p.setXmmc(rs.getString("xmmc"));
			  	p.setDepartment(rs.getString("department"));
			  	p.setStatus("1");
			  	appid = rs.getString("applicationid");
			}
			
			String sqla = "select * from xzspjk_application t where t.uuid = '"+appid+"'";
			rs = st.executeQuery(sqla);
			if(rs != null && rs.next()){
				a = new Application1();
				a.setAppname(rs.getString("appname"));
				a.setApporg(rs.getString("apporg"));
				a.setCardid(rs.getString("cardid"));
				a.setAppdate(rs.getString("appdate"));
				a.setMobilephone(rs.getString("mobilephone"));
				a.setPhone(rs.getString("phone"));
				a.setEmail(rs.getString("email"));
				a.setAddress(rs.getString("address"));
				a.setAuthenticateid(rs.getString("authenticateid"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {			
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element rr  = document.addElement("authenticate");
		Element authenticateidEle = rr.addElement("authenticateid");
		authenticateidEle.addCDATA(a.getAuthenticateid());
		
		//������Document�ĵ�
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		//��Ӹ�Ԫ��
		Element root  = doc.addElement("cooperation");
		//����application��Ԫ�ؽڵ�
		Element cooperationEle = root.addElement("application");
		
		Element appnameEle = cooperationEle.addElement("appname");
		appnameEle.addCDATA(a.getAppname());
		Element apporgEle = cooperationEle.addElement("apporg");
		apporgEle.addCDATA(a.getApporg());
		Element cardidEle = cooperationEle.addElement("cardid");
		cardidEle.addCDATA(a.getCardid());
		Element appdateEle = cooperationEle.addElement("appdate");
		appdateEle.addCDATA(a.getAppdate());
		Element phoneEle = cooperationEle.addElement("phone");
		phoneEle.addCDATA(a.getPhone());
		Element mobilephoneEle = cooperationEle.addElement("mobilephone");
		mobilephoneEle.addCDATA(a.getMobilephone());
		Element emailEle = cooperationEle.addElement("email");
		emailEle.addCDATA(a.getEmail());
		Element addressEle = cooperationEle.addElement("address");
		addressEle.addCDATA(a.getAddress());
		
		Element permissionsEle = root.addElement("permissions");
		
		Element permissionEle = permissionsEle.addElement("permission");
		Element pidEle = permissionEle.addElement("id");
		pidEle.addCDATA(p.getId());
		Element nameEle = permissionEle.addElement("name");
		nameEle.addCDATA(p.getName());
		Element bsnumEle = permissionEle.addElement("bsnum");
		bsnumEle.addCDATA(p.getBsnum());
		Element xmmcEle = permissionEle.addElement("xmmc");
		xmmcEle.addCDATA(p.getXmmc());
		Element pdepartmentEle = permissionEle.addElement("department");
		pdepartmentEle.addCDATA(p.getDepartment());
		Element pstatusEle = permissionEle.addElement("status");
		pstatusEle.addCDATA(p.getStatus());
		
		permissionEle.addElement("materials");
		Element nodesEle = permissionEle.addElement("nodes");
		
		nodesEle.addElement("specialnode");
		Element complementnodeEle = nodesEle.addElement("complementnode");

		Element specialresultEle = complementnodeEle.addElement("specialresult");
		specialresultEle.addCDATA(hn.getSpecialresult());
		Element specialresultdateEle = complementnodeEle.addElement("specialresultdate");
		specialresultdateEle.addCDATA(hn.getSpecialresultdate());
		Element specialenddateEle = complementnodeEle.addElement("specialenddate");
		specialenddateEle.addCDATA(hn.getSpecialenddate());
		Element specialpayEle = complementnodeEle.addElement("specialpay");
		specialpayEle.addCDATA(hn.getSpecialpay());
		
		List<String> xmlStrs = null;
		try {
			//businessxml
			java.io.ByteArrayOutputStream fos = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(fos,new OutputFormat (" ", true, "GB2312"));
			writer.write(doc);
			
			xmlStrs = new ArrayList<String>();
			xmlStrs.add(fos.toString());
			
			//authenticatexml
			java.io.ByteArrayOutputStream os = new ByteArrayOutputStream();
			XMLWriter w = new XMLWriter(os,new OutputFormat (" ", true, "GB2312"));
			w.write(document);
			xmlStrs.add(os.toString());
			
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlStrs;
		
    }
    
    //������������xml�ļ�
    public List<String> bjSlscXml(String bsnum,Acceptnode an){
    	
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		Statement st = null;
		ResultSet rs = null;
		Permission p = null;
		Application1 a = null;
		String appid = "";
		try{
			st = conn.createStatement();
			
			String sqlp = "select * from xzspjk_permission t where t.bsnum='"+bsnum+"'";	
			rs = st.executeQuery(sqlp);
			if(rs != null && rs.next()){
				p = new Permission();
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setBsnum(rs.getString("bsnum"));
			  	p.setXmmc(rs.getString("xmmc"));
			  	p.setDepartment(rs.getString("department"));
			  	p.setStatus("1");
			  	appid = rs.getString("applicationid");
			}
			
			String sqla = "select * from xzspjk_application t where t.uuid = '"+appid+"'";
			rs = st.executeQuery(sqla);
			if(rs != null && rs.next()){
				a = new Application1();
				a.setAppname(rs.getString("appname"));
				a.setApporg(rs.getString("apporg"));
				a.setCardid(rs.getString("cardid"));
				a.setAppdate(rs.getString("appdate"));
				a.setMobilephone(rs.getString("mobilephone"));
				a.setPhone(rs.getString("phone"));
				a.setEmail(rs.getString("email"));
				a.setAddress(rs.getString("address"));
				a.setAuthenticateid(rs.getString("authenticateid"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element rr  = document.addElement("authenticate");
		Element authenticateidEle = rr.addElement("authenticateid");
		authenticateidEle.addCDATA(a.getAuthenticateid());
		
		//������Document�ĵ�
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		//��Ӹ�Ԫ��
		Element root  = doc.addElement("cooperation");
		//����application��Ԫ�ؽڵ�
		Element cooperationEle = root.addElement("application");
		
		Element appnameEle = cooperationEle.addElement("appname");
		appnameEle.addCDATA(a.getAppname());
		Element apporgEle = cooperationEle.addElement("apporg");
		apporgEle.addCDATA(a.getApporg());
		Element cardidEle = cooperationEle.addElement("cardid");
		cardidEle.addCDATA(a.getCardid());
		Element appdateEle = cooperationEle.addElement("appdate");
		appdateEle.addCDATA(a.getAppdate());
		Element phoneEle = cooperationEle.addElement("phone");
		phoneEle.addCDATA(a.getPhone());
		Element mobilephoneEle = cooperationEle.addElement("mobilephone");
		mobilephoneEle.addCDATA(a.getMobilephone());
		Element emailEle = cooperationEle.addElement("email");
		emailEle.addCDATA(a.getEmail());
		Element addressEle = cooperationEle.addElement("address");
		addressEle.addCDATA(a.getAddress());
		
		Element permissionsEle = root.addElement("permissions");
		
		Element permissionEle = permissionsEle.addElement("permission");
		Element pidEle = permissionEle.addElement("id");
		pidEle.addCDATA(p.getId());
		Element nameEle = permissionEle.addElement("name");
		nameEle.addCDATA(p.getName());
		Element bsnumEle = permissionEle.addElement("bsnum");
		bsnumEle.addCDATA(p.getBsnum());
		Element xmmcEle = permissionEle.addElement("xmmc");
		xmmcEle.addCDATA(p.getXmmc());
		Element pdepartmentEle = permissionEle.addElement("department");
		pdepartmentEle.addCDATA(p.getDepartment());
		Element pstatusEle = permissionEle.addElement("status");
		pstatusEle.addCDATA(p.getStatus());
		
		permissionEle.addElement("materials");
		Element nodesEle = permissionEle.addElement("nodes");
		
		Element acceptnodeEle = nodesEle.addElement("specialnode");
		nodesEle.addElement("complementnode");
		
		Element handlerEle = acceptnodeEle.addElement("handler");
		handlerEle.addCDATA(an.getHandler());
		Element handlerdateEle = acceptnodeEle.addElement("handlerdate");
		handlerdateEle.addCDATA(an.getHandlerdate());
		Element handleraddrEle = acceptnodeEle.addElement("handleraddr");
		handleraddrEle.addCDATA(an.getHandleraddr());
		Element handlerlistEle = acceptnodeEle.addElement("handlerlist");
		handlerlistEle.addCDATA(an.getHandlerlist());
		
		List<String> xmlStrs = null;
		try {
			//businessxml
			java.io.ByteArrayOutputStream fos = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(fos,new OutputFormat (" ", true, "GB2312"));
			writer.write(doc);
			
			xmlStrs = new ArrayList<String>();
			xmlStrs.add(fos.toString());
			
			//authenticatexml
			java.io.ByteArrayOutputStream os = new ByteArrayOutputStream();
			XMLWriter w = new XMLWriter(os,new OutputFormat (" ", true, "GB2312"));
			w.write(document);
			xmlStrs.add(os.toString());
			
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlStrs;
		
    }
    
    //�������xml�ļ�
    public List<String> xzspBjscXml(String bsnum){
    	
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		Statement st = null;
		ResultSet rs = null;
		Permission p = null;
		Application1 a = null;
		String appid = "";
		try{
			st = conn.createStatement();
			
			String sqlp = "select * from xzspjk_permission t where t.bsnum='"+bsnum+"'";	
			rs = st.executeQuery(sqlp);
			if(rs != null && rs.next()){
				p = new Permission();
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setBsnum(rs.getString("bsnum"));
			  	p.setXmmc(rs.getString("xmmc"));
			  	p.setDepartment(rs.getString("department"));
			  	p.setStatus("1");
			  	appid = rs.getString("applicationid");
			}
			
			String sqla = "select * from xzspjk_application t where t.uuid = '"+appid+"'";
			rs = st.executeQuery(sqla);
			if(rs != null && rs.next()){
				a = new Application1();
				a.setAppname(rs.getString("appname"));
				a.setApporg(rs.getString("apporg"));
				a.setCardid(rs.getString("cardid"));
				a.setAppdate(rs.getString("appdate"));
				a.setMobilephone(rs.getString("mobilephone"));
				a.setPhone(rs.getString("phone"));
				a.setEmail(rs.getString("email"));
				a.setAddress(rs.getString("address"));
				a.setAuthenticateid(rs.getString("authenticateid"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {			
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element rr  = document.addElement("authenticate");
		Element authenticateidEle = rr.addElement("authenticateid");
		authenticateidEle.addCDATA(a.getAuthenticateid());
		
		//������Document�ĵ�
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		//��Ӹ�Ԫ��
		Element root  = doc.addElement("cooperation");
		//����application��Ԫ�ؽڵ�
		Element cooperationEle = root.addElement("application");
		
		Element appnameEle = cooperationEle.addElement("appname");
		appnameEle.addCDATA(a.getAppname());
		Element apporgEle = cooperationEle.addElement("apporg");
		apporgEle.addCDATA(a.getApporg());
		Element cardidEle = cooperationEle.addElement("cardid");
		cardidEle.addCDATA(a.getCardid());
		Element appdateEle = cooperationEle.addElement("appdate");
		appdateEle.addCDATA(a.getAppdate());
		Element phoneEle = cooperationEle.addElement("phone");
		phoneEle.addCDATA(a.getPhone());
		Element mobilephoneEle = cooperationEle.addElement("mobilephone");
		mobilephoneEle.addCDATA(a.getMobilephone());
		Element emailEle = cooperationEle.addElement("email");
		emailEle.addCDATA(a.getEmail());
		Element addressEle = cooperationEle.addElement("address");
		addressEle.addCDATA(a.getAddress());
		
		Element permissionsEle = root.addElement("permissions");
		
		Element permissionEle = permissionsEle.addElement("permission");
		Element pidEle = permissionEle.addElement("id");
		pidEle.addCDATA(p.getId());
		Element nameEle = permissionEle.addElement("name");
		nameEle.addCDATA(p.getName());
		Element bsnumEle = permissionEle.addElement("bsnum");
		bsnumEle.addCDATA(p.getBsnum());
		Element xmmcEle = permissionEle.addElement("xmmc");
		xmmcEle.addCDATA(p.getXmmc());
		Element pdepartmentEle = permissionEle.addElement("department");
		pdepartmentEle.addCDATA(p.getDepartment());
		Element pstatusEle = permissionEle.addElement("status");
		pstatusEle.addCDATA(p.getStatus());
		
		permissionEle.addElement("materials");
		Element nodesEle = permissionEle.addElement("nodes");
		
		nodesEle.addElement("specialnode");
		nodesEle.addElement("complementnode");
		
		
		List<String> xmlStrs = null;
		try {
			//businessxml
			java.io.ByteArrayOutputStream fos = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(fos,new OutputFormat (" ", true, "GB2312"));
			writer.write(doc);
			
			xmlStrs = new ArrayList<String>();
			xmlStrs.add(fos.toString());
			
			//authenticatexml
			java.io.ByteArrayOutputStream os = new ByteArrayOutputStream();
			XMLWriter w = new XMLWriter(os,new OutputFormat (" ", true, "GB2312"));
			w.write(document);
			xmlStrs.add(os.toString());
			
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlStrs;
    }
    
    //�����ر����Ҫ�õ�xml�ļ�
    public List<String> tbcxXml(String bsnum, Applynode applynode){
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		Statement st = null;
		ResultSet rs = null;
		Permission p = null;
		Application1 a = null;
		String appid = "";
		try{
			st = conn.createStatement();
			
			String sqlp = "select * from xzspjk_permission t where t.bsnum='"+bsnum+"'";	
			rs = st.executeQuery(sqlp);
			if(rs != null && rs.next()){
				p = new Permission();
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setBsnum(rs.getString("bsnum"));
				//p.setBsnum("0000000290");
			  	p.setXmmc(rs.getString("xmmc"));
			  	p.setDepartment(rs.getString("department"));
			  	p.setStatus("4");
			  	appid = rs.getString("applicationid");
			}
			
			String sqla = "select * from xzspjk_application t where t.uuid = '"+appid+"'";
			rs = st.executeQuery(sqla);
			if(rs != null && rs.next()){
				a = new Application1();
				a.setAppname(rs.getString("appname"));
				a.setApporg(rs.getString("apporg"));
				a.setCardid(rs.getString("cardid"));
				a.setAppdate(rs.getString("appdate"));
				a.setMobilephone(rs.getString("mobilephone"));
				a.setPhone(rs.getString("phone"));
				a.setEmail(rs.getString("email"));
				a.setAddress(rs.getString("address"));
				a.setAuthenticateid(rs.getString("authenticateid"));
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {			
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element rr  = document.addElement("authenticate");
		Element authenticateidEle = rr.addElement("authenticateid");
		authenticateidEle.addCDATA(a.getAuthenticateid());
		
		//������Document�ĵ�
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		//��Ӹ�Ԫ��
		Element root  = doc.addElement("cooperation");
		//����application��Ԫ�ؽڵ�
		Element cooperationEle = root.addElement("application");
		
		Element appnameEle = cooperationEle.addElement("appname");
		appnameEle.addCDATA(a.getAppname());
		Element apporgEle = cooperationEle.addElement("apporg");
		apporgEle.addCDATA(a.getApporg());
		Element cardidEle = cooperationEle.addElement("cardid");
		cardidEle.addCDATA(a.getCardid());
		Element appdateEle = cooperationEle.addElement("appdate");
		appdateEle.addCDATA(a.getAppdate());
		Element phoneEle = cooperationEle.addElement("phone");
		phoneEle.addCDATA(a.getPhone());
		Element mobilephoneEle = cooperationEle.addElement("mobilephone");
		mobilephoneEle.addCDATA(a.getMobilephone());
		Element emailEle = cooperationEle.addElement("email");
		emailEle.addCDATA(a.getEmail());
		Element addressEle = cooperationEle.addElement("address");
		addressEle.addCDATA(a.getAddress());
		
		Element permissionsEle = root.addElement("permissions");
		
		Element permissionEle = permissionsEle.addElement("permission");
		Element pidEle = permissionEle.addElement("id");
		pidEle.addCDATA(p.getId());
		Element nameEle = permissionEle.addElement("name");
		nameEle.addCDATA(p.getName());
		Element bsnumEle = permissionEle.addElement("bsnum");
		bsnumEle.addCDATA(p.getBsnum());
		Element xmmcEle = permissionEle.addElement("xmmc");
		xmmcEle.addCDATA(p.getXmmc());
		Element pdepartmentEle = permissionEle.addElement("department");
		pdepartmentEle.addCDATA(p.getDepartment());
		Element pstatusEle = permissionEle.addElement("status");
		pstatusEle.addCDATA(p.getStatus());
		
		permissionEle.addElement("materials");
		Element nodesEle = permissionEle.addElement("nodes");
		
		Element specialnodeEle = nodesEle.addElement("specialnode");
		nodesEle.addElement("complementnode");
		Element applynodeEle = specialnodeEle.addElement("applynode");
		
		Element specialtypeEle = applynodeEle.addElement("specialtype");
		specialtypeEle.addCDATA(applynode.getSpecialtype());
		Element specialnameEle = applynodeEle.addElement("specialname");
		specialnameEle.addCDATA(applynode.getSpecialname());
		Element specialstartdateEle = applynodeEle.addElement("specialstartdate");
		specialstartdateEle.addCDATA(applynode.getSpecialstartdate());
		Element specialuserEle = applynodeEle.addElement("specialuser");
		specialuserEle.addCDATA(applynode.getSpecialuser());
		Element specialusertelEle = applynodeEle.addElement("specialusertel");
		specialusertelEle.addCDATA(applynode.getSpecialusertel());
		Element specialuserphoneEle = applynodeEle.addElement("specialuserphone");
		specialuserphoneEle.addCDATA(applynode.getSpecialuserphone());
		Element specialideaEle = applynodeEle.addElement("specialidea");
		specialideaEle.addCDATA(applynode.getSpecialidea());
		Element specialcontentEle = applynodeEle.addElement("specialcontent");
		specialcontentEle.addCDATA(applynode.getSpecialcontent());
		Element speciallimitEle = applynodeEle.addElement("speciallimit");
		speciallimitEle.addCDATA(applynode.getSpeciallimit());
		Element specialunitEle = applynodeEle.addElement("specialunit");
		specialunitEle.addCDATA(applynode.getSpecialunit());
		Element adepartmentEle = applynodeEle.addElement("department");
		adepartmentEle.addCDATA("7336105");
		
		List<String> xmlStrs = null;
		try {
			//businessxml
			java.io.ByteArrayOutputStream fos = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(fos,new OutputFormat (" ", true, "GB2312"));
			writer.write(doc);
			
			xmlStrs = new ArrayList<String>();
			xmlStrs.add(fos.toString());
			
			//authenticatexml
			java.io.ByteArrayOutputStream os = new ByteArrayOutputStream();
			XMLWriter w = new XMLWriter(os,new OutputFormat (" ", true, "GB2312"));
			w.write(document);
			xmlStrs.add(os.toString());
			
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlStrs;
    	
    }
    
    //����������������Ҫ�õ�xml�ļ�
    public List<String> bjclXml(String bsnum, Notifynode nn){
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		Statement st = null;
		ResultSet rs = null;
		Permission p = null;
		Application1 a = null;
		String appid = "";
		try{
			st = conn.createStatement();
			
			String sqlp = "select * from xzspjk_permission t where t.bsnum='"+bsnum+"'";		
			rs = st.executeQuery(sqlp);
			if(rs != null && rs.next()){
				p = new Permission();
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setBsnum(rs.getString("bsnum"));
				//p.setBsnum("0000000290");
			  	p.setXmmc(rs.getString("xmmc"));
			  	p.setDepartment(rs.getString("department"));
			  	p.setStatus("3");
			  	appid = rs.getString("applicationid");
			}
			
			String sqla = "select * from xzspjk_application t where t.uuid = '"+appid+"'";
			rs = st.executeQuery(sqla);
			if(rs != null && rs.next()){
				a = new Application1();
				a.setAppname(rs.getString("appname"));
				a.setApporg(rs.getString("apporg"));
				a.setCardid(rs.getString("cardid"));
				a.setAppdate(rs.getString("appdate"));
				a.setMobilephone(rs.getString("mobilephone"));
				a.setPhone(rs.getString("phone"));
				a.setEmail(rs.getString("email"));
				a.setAddress(rs.getString("address"));
				a.setAuthenticateid(rs.getString("authenticateid"));
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {			
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element rr  = document.addElement("authenticate");
		Element authenticateidEle = rr.addElement("authenticateid");
		authenticateidEle.addCDATA(a.getAuthenticateid());
		
		//������Document�ĵ�
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		//��Ӹ�Ԫ��
		Element root  = doc.addElement("cooperation");
		//����application��Ԫ�ؽڵ�
		Element cooperationEle = root.addElement("application");
		
		Element appnameEle = cooperationEle.addElement("appname");
		appnameEle.addCDATA(a.getAppname());
		Element apporgEle = cooperationEle.addElement("apporg");
		apporgEle.addCDATA(a.getApporg());
		Element cardidEle = cooperationEle.addElement("cardid");
		cardidEle.addCDATA(a.getCardid());
		Element appdateEle = cooperationEle.addElement("appdate");
		appdateEle.addCDATA(a.getAppdate());
		Element phoneEle = cooperationEle.addElement("phone");
		phoneEle.addCDATA(a.getPhone());
		Element mobilephoneEle = cooperationEle.addElement("mobilephone");
		mobilephoneEle.addCDATA(a.getMobilephone());
		Element emailEle = cooperationEle.addElement("email");
		emailEle.addCDATA(a.getEmail());
		Element addressEle = cooperationEle.addElement("address");
		addressEle.addCDATA(a.getAddress());
		
		Element permissionsEle = root.addElement("permissions");
		
		Element permissionEle = permissionsEle.addElement("permission");
		Element pidEle = permissionEle.addElement("id");
		pidEle.addCDATA(p.getId());
		Element nameEle = permissionEle.addElement("name");
		nameEle.addCDATA(p.getName());
		Element bsnumEle = permissionEle.addElement("bsnum");
		bsnumEle.addCDATA(p.getBsnum());
		Element xmmcEle = permissionEle.addElement("xmmc");
		xmmcEle.addCDATA(p.getXmmc());
		Element pdepartmentEle = permissionEle.addElement("department");
		pdepartmentEle.addCDATA(p.getDepartment());
		Element pstatusEle = permissionEle.addElement("status");
		pstatusEle.addCDATA(p.getStatus());
		
		permissionEle.addElement("materials");
		Element nodesEle = permissionEle.addElement("nodes");
		
		nodesEle.addElement("specialnode");
		Element complementnodeEle = nodesEle.addElement("complementnode");
		Element notifynodeEle = complementnodeEle.addElement("notifynode");
		
		Element nodeactorEle = notifynodeEle.addElement("nodeactor");
		nodeactorEle.addCDATA(nn.getNodeactor());
		Element complementdateEle = notifynodeEle.addElement("complementdate");
		complementdateEle.addCDATA(nn.getComplementdate());
		Element complementideaEle = notifynodeEle.addElement("complementidea");
		complementideaEle.addCDATA(nn.getComplementidea());
		Element complementlistEle = notifynodeEle.addElement("complementlist");
		complementlistEle.addCDATA(nn.getComplementlist());
		Element departmentEle = notifynodeEle.addElement("department");
		departmentEle.addCDATA(nn.getDepartment());
		
		List<String> xmlStrs = null;
		try {
			//businessxml
			java.io.ByteArrayOutputStream fos = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(fos,new OutputFormat (" ", true, "GB2312"));
			writer.write(doc);
			
			xmlStrs = new ArrayList<String>();
			xmlStrs.add(fos.toString());
			
			//authenticatexml
			java.io.ByteArrayOutputStream os = new ByteArrayOutputStream();
			XMLWriter w = new XMLWriter(os,new OutputFormat (" ", true, "GB2312"));
			w.write(document);
			xmlStrs.add(os.toString());
			
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlStrs;
    	
    }
  
    
    //���ɼ�������Ҫ�õ�xml�ļ�
	public List<String> jtqScXml(String bsnum,Node node,String status){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		Statement st = null;
		ResultSet rs = null;
		Permission p = null;
		Application1 a = null;
		String appid = "";
		try{
			st = conn.createStatement();
			
			String sqlp = "select * from xzspjk_permission t where t.bsnum='"+bsnum+"'";		
			rs = st.executeQuery(sqlp);
			if(rs != null && rs.next()){
				p = new Permission();
				p.setId(rs.getString("id"));
				p.setName(rs.getString("name"));
				p.setBsnum(rs.getString("bsnum"));
				//p.setBsnum("0000000290");
			  	p.setXmmc(rs.getString("xmmc"));
			  	p.setDepartment(rs.getString("department"));
			  	p.setStatus(status);
			  	appid = rs.getString("applicationid");
			}
			
			String sqla = "select * from xzspjk_application t where t.uuid = '"+appid+"'";
			rs = st.executeQuery(sqla);
			if(rs != null && rs.next()){
				a = new Application1();
				a.setAppname(rs.getString("appname"));
				a.setApporg(rs.getString("apporg"));
				a.setCardid(rs.getString("cardid"));
				a.setAppdate(rs.getString("appdate"));
				a.setMobilephone(rs.getString("mobilephone"));
				a.setPhone(rs.getString("phone"));
				a.setEmail(rs.getString("email"));
				a.setAddress(rs.getString("address"));
				a.setAuthenticateid(rs.getString("authenticateid"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element rr  = document.addElement("authenticate");
		Element authenticateidEle = rr.addElement("authenticateid");
		authenticateidEle.addCDATA(a.getAuthenticateid());
		
		//������Document�ĵ�
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		//��Ӹ�Ԫ��
		Element root  = doc.addElement("cooperation");
		//����application��Ԫ�ؽڵ�
		Element cooperationEle = root.addElement("application");
		
		Element appnameEle = cooperationEle.addElement("appname");
		appnameEle.addCDATA(a.getAppname());
		Element apporgEle = cooperationEle.addElement("apporg");
		apporgEle.addCDATA(a.getApporg());
		Element cardidEle = cooperationEle.addElement("cardid");
		cardidEle.addCDATA(a.getCardid());
		Element appdateEle = cooperationEle.addElement("appdate");
		appdateEle.addCDATA(a.getAppdate());
		Element phoneEle = cooperationEle.addElement("phone");
		phoneEle.addCDATA(a.getPhone());
		Element mobilephoneEle = cooperationEle.addElement("mobilephone");
		mobilephoneEle.addCDATA(a.getMobilephone());
		Element emailEle = cooperationEle.addElement("email");
		emailEle.addCDATA(a.getEmail());
		Element addressEle = cooperationEle.addElement("address");
		addressEle.addCDATA(a.getAddress());
		
		Element permissionsEle = root.addElement("permissions");
		
		Element permissionEle = permissionsEle.addElement("permission");
		Element pidEle = permissionEle.addElement("id");
		pidEle.addCDATA(p.getId());
		Element nameEle = permissionEle.addElement("name");
		nameEle.addCDATA(p.getName());
		Element bsnumEle = permissionEle.addElement("bsnum");
		bsnumEle.addCDATA(p.getBsnum());
		Element xmmcEle = permissionEle.addElement("xmmc");
		xmmcEle.addCDATA(p.getXmmc());
		Element pdepartmentEle = permissionEle.addElement("department");
		pdepartmentEle.addCDATA(p.getDepartment());
		Element pstatusEle = permissionEle.addElement("status");
		pstatusEle.addCDATA(p.getStatus());
		
		permissionEle.addElement("materials");
		Element nodesEle = permissionEle.addElement("nodes");
		
		Element nodeEle = nodesEle.addElement("node");
		nodesEle.addElement("specialnode");
		nodesEle.addElement("complementnode");
		
		Element nodeidEle = nodeEle.addElement("nodeid");
		nodeidEle.addCDATA(node.getNodeid());
		Element nodenameEle = nodeEle.addElement("nodename");
		nodenameEle.addCDATA(node.getNodename());
		Element nodeactorEle = nodeEle.addElement("nodeactor");
		nodeactorEle.addCDATA(node.getNodeactor());
		Element nodeactorghEle = nodeEle.addElement("nodeactorgh");
		nodeactorghEle.addCDATA(node.getNodeactorgh());
		Element nodeactorzwmcEle = nodeEle.addElement("nodeactorzwmc");
		nodeactorzwmcEle.addCDATA(node.getNodeactorzwmc());
		Element nodeactorzwdmEle = nodeEle.addElement("nodeactorzwdm");
		nodeactorzwdmEle.addCDATA(node.getNodeactorzwdm());
		Element nndepartmentEle = nodeEle.addElement("department");
		nndepartmentEle.addCDATA(node.getDepartment());
		Element nnhandlerdateEle = nodeEle.addElement("handlerdate");
		nnhandlerdateEle.addCDATA(node.getHandlerdate());
		Element handlerideaEle = nodeEle.addElement("handleridea");
		handlerideaEle.addCDATA(node.getHandleridea());
		
		List<String> xmlStrs = null;
		try {
			//�����ʽ��
//			OutputFormat format = OutputFormat.createPrettyPrint();
//			format.setEncoding("utf-8");
			
			//businessxml
			java.io.ByteArrayOutputStream fos = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(fos,new OutputFormat (" ", true, "GB2312"));
			writer.write(doc);
			
			xmlStrs = new ArrayList<String>();
			xmlStrs.add(fos.toString());
			
			//authenticatexml
			java.io.ByteArrayOutputStream os = new ByteArrayOutputStream();
			XMLWriter w = new XMLWriter(os,new OutputFormat (" ", true, "GB2312"));
			w.write(document);
			xmlStrs.add(os.toString());
			
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlStrs;
	}
    //����permissionid�������е�ʵ�������ϸ��Ϣ
    public void findAllStl(){
    	String applicationId = null;

		Statement st = null;
		ResultSet rs = null;
		
		allStl = new HashMap<String, Object>();
		
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		try {
			st = conn.createStatement();
		
			sql = new StringBuffer();
			sql.append("select * from xzspjk_permission t where t.permissionId = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			if(rs != null && rs.next()){
				permission =  new Permission();
				permission.setUuid(permissionId);
				permission.setName(rs.getString("name"));
				permission.setBsnum(bsnum);
				permission.setXmmc(rs.getString("xmmc"));
				permission.setDepartment(rs.getString("department"));
				permission.setStatus(rs.getString("status"));
				permission.setId(rs.getString("id"));
				application = new Application1();
				applicationId = rs.getString("applicationid");
				application.setUuid(applicationId);
				permission.setApplication1(application);
			}
			allStl.put("permission", permission);
			
			sql = new StringBuffer();
			sql.append("select * from xzspjk_application t where t.uuid = '");
			sql.append(applicationId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			if(rs != null && rs.next()){
				application = new Application1();
				application.setUuid(applicationId);
				application.setAppname(rs.getString("appname"));
				application.setApporg(rs.getString("apporg"));
				application.setCardid(rs.getString("cardid"));
				application.setAppdate(rs.getString("appdate"));
				application.setMobilephone(rs.getString("mobilephone"));
				application.setPhone(rs.getString("phone"));
				application.setEmail(rs.getString("email"));
				application.setAddress(rs.getString("address"));
				application.setAuthenticateid(rs.getString("authenticateid"));
			}
			allStl.put("application", application);
			
			materials = new ArrayList<Material>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_material t where t.permissionid = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				material = new Material();
				material.setUuid(rs.getString("uuid"));
				material.setId(rs.getString("id"));
				material.setMlid(rs.getString("mlid"));
				material.setMlname(rs.getString("mlname"));
				material.setSelecttype(rs.getString("selecttype"));
				material.setFid(rs.getString("fid"));
				material.setFpath(rs.getString("fpath"));
				material.setFname(rs.getString("fname"));
				material.setStatus(rs.getString("status"));
				material.setOrinum(rs.getString("orinum"));
				material.setCopynum(rs.getString("copynum"));
				material.setIsneed(rs.getString("isneed"));
				material.setBaseinfo(rs.getString("baseinfo"));
				material.setAdjustment(rs.getString("adjustment"));
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				material.setPermission(permission);
				materials.add(material);
			}
			allStl.put("materials", materials);

			nodes = new ArrayList<Node>();  
			sql = new StringBuffer();
			sql.append("select * from xzspjk_node t where t.permissionid = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				node = new Node();
				node.setUuid(rs.getString("uuid"));
				node.setNodeid(rs.getString("nodeid"));
				node.setNodename(rs.getString("nodename"));
				node.setNodeactor(rs.getString("nodeactor"));
				node.setNodeactorgh(rs.getString("nodeactorgh"));
				node.setNodeactorzwmc(rs.getString("nodeactorzwmc"));
				node.setNodeactorzwdm(rs.getString("nodeactorzwdm"));
				node.setDepartment(rs.getString("department"));
				node.setHandlerdate(rs.getString("handlerdate"));
				node.setHandleridea(rs.getString("handleridea"));				
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				node.setPermission(permission);
				nodes.add(node);
			}
			allStl.put("nodes", nodes);

			notifynodes = new ArrayList<Notifynode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_notifynode t where t.permissionid = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				notifynode = new Notifynode();
				notifynode.setUuid(rs.getString("uuid"));
				notifynode.setNodeactor(rs.getString("nodeactor"));
				notifynode.setComplementdate(rs.getString("complementdate"));
				notifynode.setComplementidea(rs.getString("complementidea"));
				notifynode.setComplementlist(rs.getString("complementlist"));
				notifynode.setDepartment(rs.getString("department"));				
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				notifynode.setPermission(permission);
				notifynodes.add(notifynode);
			}
			allStl.put("notifynodes", notifynodes);

			applynodes = new ArrayList<Applynode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_applynode t where t.permissionid='");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				applynode = new Applynode();
				applynode.setUuid(rs.getString("uuid"));
				applynode.setSpecialtype(rs.getString("specialtype"));
				applynode.setSpecialname(rs.getString("specialname"));
				applynode.setSpecialstartdate(rs.getString("specialstartdate"));
				applynode.setSpecialuser(rs.getString("specialuser"));
				applynode.setSpecialusertel(rs.getString("specialusertel"));
				applynode.setSpecialuserphone(rs.getString("specialuserphone"));
				applynode.setSpecialidea(rs.getString("specialidea"));
				applynode.setSpecialcontent(rs.getString("specialcontent"));
				applynode.setSpeciallimit(rs.getString("speciallimit"));
				applynode.setSpecialunit(rs.getString("specialunit"));
				applynode.setDepartment(rs.getString("department"));
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				applynode.setPermission(permission);
				applynodes.add(applynode);				
			}
			allStl.put("applynodes", applynodes);

			acceptnodes = new ArrayList<Acceptnode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_acceptnode t where t.permissionid = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				acceptnode = new Acceptnode();
				acceptnode.setUuid(rs.getString("uuid"));
				acceptnode.setHandler(rs.getString("handler"));
				acceptnode.setHandlerdate(rs.getString("handlerdate"));
				acceptnode.setHandleraddr(rs.getString("handleraddr"));
				acceptnode.setHandlerlist(rs.getString("handlerlist"));
				acceptnode.setDepartment(rs.getString("department"));
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				acceptnode.setPermission(permission);
				acceptnodes.add(acceptnode);
			}
			allStl.put("acceptnodes", acceptnodes);

			handlenodes = new ArrayList<Handlenode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_handlenode t where t.permissionid = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				handlenode = new Handlenode();
				handlenode.setUuid(rs.getString("uuid"));
				handlenode.setSpecialresult(rs.getString("specialresult"));
				handlenode.setSpecialresultdate(rs.getString("specialresultdate"));
				handlenode.setSpecialenddate(rs.getString("specialenddate"));
				handlenode.setSpecialpay(rs.getString("specialpay"));
				handlenode.setDepartment(rs.getString("department"));
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				handlenode.setPermission(permission);
				handlenodes.add(handlenode);				
			}			
			allStl.put("handlenodes", handlenodes);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
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
    
    //����xml
    public List<String> scXml(String permissionid){
    	String permissionId = null;
    	String applicationId = null;

		Statement st = null;
		ResultSet rs = null;
		
    	DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		try {
			st = conn.createStatement();
			
			sql = new StringBuffer();
			sql.append("select * from xzspjk_permission t where t.uuid = '");
			sql.append(permissionid);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			if(rs != null && rs.next()){
				permission =  new Permission();
				permissionId = rs.getString("uuid");
				permission.setUuid(permissionId);
				permission.setName(rs.getString("name"));
				permission.setBsnum(rs.getString("bsnum"));
				permission.setXmmc(rs.getString("xmmc"));
				permission.setDepartment(rs.getString("department"));
				permission.setStatus(rs.getString("status"));
				permission.setId(rs.getString("id"));
				applicationId = rs.getString("applicationid");
				permission.setApplication1(application);
			}
			
			sql = new StringBuffer();
			sql.append("select * from xzspjk_application t where t.uuid = '");
			sql.append(applicationId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			if(rs != null && rs.next()){
				application = new Application1();
				application.setUuid(applicationId);
				application.setAppname(rs.getString("appname"));
				application.setApporg(rs.getString("apporg"));
				application.setCardid(rs.getString("cardid"));
				application.setAppdate(rs.getString("appdate"));
				application.setMobilephone(rs.getString("mobilephone"));
				application.setPhone(rs.getString("phone"));
				application.setEmail(rs.getString("email"));
				application.setAddress(rs.getString("address"));
				application.setAuthenticateid(rs.getString("authenticateid"));
			}
			
			materials = new ArrayList<Material>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_material t where t.permissionid = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				material = new Material();
				material.setUuid(rs.getString("uuid"));
				material.setId(rs.getString("id"));
				material.setMlid(rs.getString("mlid"));
				material.setMlname(rs.getString("mlname"));
				material.setSelecttype(rs.getString("selecttype"));
				material.setFid(rs.getString("fid"));
				material.setFpath(rs.getString("fpath"));
				material.setFname(rs.getString("fname"));
				material.setStatus(rs.getString("status"));
				material.setOrinum(rs.getString("orinum"));
				material.setCopynum(rs.getString("copynum"));
				material.setIsneed(rs.getString("isneed"));
				material.setBaseinfo(rs.getString("baseinfo"));
				material.setAdjustment(rs.getString("adjustment"));
//				permission = new Permission();
//				permission.setUuid(rs.getString("permissionid"));
				material.setPermission(permission);
				materials.add(material);
			}

			nodes = new ArrayList<Node>();  
			sql = new StringBuffer();
			sql.append("select * from xzspjk_node t where t.permissionid = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				node = new Node();
				node.setUuid(rs.getString("uuid"));
				node.setNodeid(rs.getString("nodeid"));
				node.setNodename(rs.getString("nodename"));
				node.setNodeactor(rs.getString("nodeactor"));
				node.setNodeactorgh(rs.getString("nodeactorgh"));
				node.setNodeactorzwmc(rs.getString("nodeactorzwmc"));
				node.setNodeactorzwdm(rs.getString("nodeactorzwdm"));
				node.setDepartment(rs.getString("department"));
				node.setHandlerdate(rs.getString("handlerdate"));
				node.setHandleridea(rs.getString("handleridea"));				
//				permission = new Permission();
//				permission.setUuid(rs.getString("permissionid"));
				node.setPermission(permission);
				nodes.add(node);
			}

			notifynodes = new ArrayList<Notifynode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_notifynode t where t.permissionid = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				notifynode = new Notifynode();
				notifynode.setUuid(rs.getString("uuid"));
				notifynode.setNodeactor(rs.getString("nodeactor"));
				notifynode.setComplementdate(rs.getString("complementdate"));
				notifynode.setComplementidea(rs.getString("complementidea"));
				notifynode.setComplementlist(rs.getString("complementlist"));
				notifynode.setDepartment(rs.getString("department"));				
//				permission = new Permission();
//				permission.setUuid(rs.getString("permissionid"));
				notifynode.setPermission(permission);
				notifynodes.add(notifynode);
			}

			applynodes = new ArrayList<Applynode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_applynode t where t.permissionid='");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				applynode = new Applynode();
				applynode.setUuid(rs.getString("uuid"));
				applynode.setSpecialtype(rs.getString("specialtype"));
				applynode.setSpecialname(rs.getString("specialname"));
				applynode.setSpecialstartdate(rs.getString("specialstartdate"));
				applynode.setSpecialuser(rs.getString("specialuser"));
				applynode.setSpecialusertel(rs.getString("specialusertel"));
				applynode.setSpecialuserphone(rs.getString("specialuserphone"));
				applynode.setSpecialidea(rs.getString("specialidea"));
				applynode.setSpecialcontent(rs.getString("specialcontent"));
				applynode.setSpeciallimit(rs.getString("speciallimit"));
				applynode.setSpecialunit(rs.getString("specialunit"));
				applynode.setDepartment(rs.getString("department"));
//				permission = new Permission();
//				permission.setUuid(rs.getString("permissionid"));
				applynode.setPermission(permission);
				applynodes.add(applynode);				
			}

			acceptnodes = new ArrayList<Acceptnode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_acceptnode t where t.permissionid = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				acceptnode = new Acceptnode();
				acceptnode.setUuid(rs.getString("uuid"));
				acceptnode.setHandler(rs.getString("handler"));
				acceptnode.setHandlerdate(rs.getString("handlerdate"));
				acceptnode.setHandleraddr(rs.getString("handleraddr"));
				acceptnode.setHandlerlist(rs.getString("handlerlist"));
				acceptnode.setDepartment(rs.getString("department"));
//				permission = new Permission();
//				permission.setUuid(rs.getString("permissionid"));
				acceptnode.setPermission(permission);
				acceptnodes.add(acceptnode);
			}

			handlenodes = new ArrayList<Handlenode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_handlenode t where t.permissionid = '");
			sql.append(permissionId);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				handlenode = new Handlenode();
				handlenode.setUuid(rs.getString("uuid"));
				handlenode.setSpecialresult(rs.getString("specialresult"));
				handlenode.setSpecialresultdate(rs.getString("specialresultdate"));
				handlenode.setSpecialenddate(rs.getString("specialenddate"));
				handlenode.setSpecialpay(rs.getString("specialpay"));
				handlenode.setDepartment(rs.getString("department"));
//				permission = new Permission();
//				permission.setUuid(rs.getString("permissionid"));
				handlenode.setPermission(permission);
				handlenodes.add(handlenode);				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		Document document = DocumentHelper.createDocument();
		document.setXMLEncoding("UTF-8");
		Element rr  = document.addElement("authenticate");
		Element authenticateidEle = rr.addElement("authenticateid");
		authenticateidEle.addCDATA(application.getAuthenticateid());
		
		//������Document�ĵ�
		Document doc = DocumentHelper.createDocument();
		doc.setXMLEncoding("UTF-8");
		//��Ӹ�Ԫ��
		Element root  = doc.addElement("cooperation");
		//����application��Ԫ�ؽڵ�
		Element cooperationEle = root.addElement("application");
		
		Element appnameEle = cooperationEle.addElement("appname");
		appnameEle.addCDATA(application.getAppname());
		Element apporgEle = cooperationEle.addElement("apporg");
		apporgEle.addCDATA(application.getApporg());
		Element cardidEle = cooperationEle.addElement("cardid");
		cardidEle.addCDATA(application.getCardid());
		Element appdateEle = cooperationEle.addElement("appdate");
		appdateEle.addCDATA(application.getAppdate());
		Element phoneEle = cooperationEle.addElement("phone");
		phoneEle.addCDATA(application.getPhone());
		Element mobilephoneEle = cooperationEle.addElement("mobilephone");
		mobilephoneEle.addCDATA(application.getMobilephone());
		Element emailEle = cooperationEle.addElement("email");
		emailEle.addCDATA(application.getEmail());
		Element addressEle = cooperationEle.addElement("address");
		addressEle.addCDATA(application.getAddress());
		
		Element permissionsEle = root.addElement("permissions");
		
		Element permissionEle = permissionsEle.addElement("permission");
		Element pidEle = permissionEle.addElement("id");
		pidEle.addCDATA(permission.getId());
		Element nameEle = permissionEle.addElement("name");
		nameEle.addCDATA(permission.getName());
		Element bsnumEle = permissionEle.addElement("bsnum");
		bsnumEle.addCDATA(permission.getBsnum());
		Element xmmcEle = permissionEle.addElement("xmmc");
		xmmcEle.addCDATA(permission.getXmmc());
		Element pdepartmentEle = permissionEle.addElement("department");
		pdepartmentEle.addCDATA(permission.getDepartment());
		Element pstatusEle = permissionEle.addElement("status");
		pstatusEle.addCDATA(permission.getStatus());

		Element materialsEle = permissionEle.addElement("materials");
		
		for(Material m : materials){
			Element materialEle = materialsEle.addElement("material");
			Element idEle = materialEle.addElement("id");
			idEle.addCDATA(m.getId());
			Element mlidEle = materialEle.addElement("mlid");
			mlidEle.addCDATA(m.getMlid());
			Element mlnameEle = materialEle.addElement("mlname");
			mlnameEle.addCDATA(m.getMlname());
			Element selecttypeEle = materialEle.addElement("selecttype");
			selecttypeEle.addCDATA(m.getSelecttype());
			Element fidEle = materialEle.addElement("fid");
			fidEle.addCDATA(m.getFid());
			Element fpathEle = materialEle.addElement("fpath");
			fpathEle.addCDATA(m.getFpath());
			Element fnameEle = materialEle.addElement("fname");
			fnameEle.addCDATA(m.getFname());
			Element statusEle = materialEle.addElement("status");
			statusEle.addCDATA(m.getStatus());
			Element orinumEle = materialEle.addElement("orinum");
			orinumEle.addCDATA(m.getOrinum());
			Element copynumEle = materialEle.addElement("copynum");
			copynumEle.addCDATA(m.getCopynum());
			Element isneedEle = materialEle.addElement("isneed");
			isneedEle.addCDATA(m.getIsneed());
			Element baseinfoEle = materialEle.addElement("baseinfo");
			baseinfoEle.addCDATA(m.getBaseinfo());
			Element adjustmentEle = materialEle.addElement("adjustment");
			adjustmentEle.addCDATA(m.getAdjustment());
		}
		
		Element nodesEle = permissionEle.addElement("nodes");
		for(Node node : nodes){
			Element nodeEle = nodesEle.addElement("node");
			
			Element nodeidEle = nodeEle.addElement("nodeid");
			nodeidEle.addCDATA(node.getNodeid());
			Element nodenameEle = nodeEle.addElement("nodename");
			nodenameEle.addCDATA(node.getNodename());
			Element nodeactorEle = nodeEle.addElement("nodeactor");
			nodeactorEle.addCDATA(node.getNodeactor());
			Element nodeactorghEle = nodeEle.addElement("nodeactorgh");
			nodeactorghEle.addCDATA(node.getNodeactorgh());
			Element nodeactorzwmcEle = nodeEle.addElement("nodeactorzwmc");
			nodeactorzwmcEle.addCDATA(node.getNodeactorzwmc());
			Element nodeactorzwdmEle = nodeEle.addElement("nodeactorzwdm");
			nodeactorzwdmEle.addCDATA(node.getNodeactorzwdm());
			Element nndepartmentEle = nodeEle.addElement("department");
			nndepartmentEle.addCDATA(node.getDepartment());
			Element nnhandlerdateEle = nodeEle.addElement("handlerdate");
			nnhandlerdateEle.addCDATA(node.getHandlerdate());
			Element handlerideaEle = nodeEle.addElement("handleridea");
			handlerideaEle.addCDATA(node.getHandleridea());
			
			Element complementnodeEle = nodesEle.addElement("complementnode");

			for(Notifynode notifynode : notifynodes){
				Element notifynodeEle = complementnodeEle.addElement("notifynode");
				
				Element nnodeactorEle = notifynodeEle.addElement("nodeactor");
				nnodeactorEle.addCDATA(notifynode.getNodeactor());
				Element complementdateEle = notifynodeEle.addElement("complementdate");
				complementdateEle.addCDATA(notifynode.getComplementdate());
				Element complementideaEle = notifynodeEle.addElement("complementidea");
				complementideaEle.addCDATA(notifynode.getComplementidea());
				Element complementlistEle = notifynodeEle.addElement("complementlist");
				complementlistEle.addCDATA(notifynode.getComplementlist());
				Element cdepartmentEle = notifynodeEle.addElement("department");
				cdepartmentEle.addCDATA(notifynode.getDepartment());
			}

			for(Acceptnode acceptnode : acceptnodes){
				Element acceptnodeEle = complementnodeEle.addElement("acceptnode");
				
				Element handlerEle = acceptnodeEle.addElement("handler");
				handlerEle.addCDATA(acceptnode.getHandler());
				Element hhandlerdateEle = acceptnodeEle.addElement("handlerdate");
				hhandlerdateEle.addCDATA(acceptnode.getHandlerdate());
				Element handleraddrEle = acceptnodeEle.addElement("handleraddr");
				handleraddrEle.addCDATA(acceptnode.getHandleraddr());
				Element handlerlistEle = acceptnodeEle.addElement("handlerlist");
				handlerlistEle.addCDATA(acceptnode.getHandlerlist());
				Element departmentEle = acceptnodeEle.addElement("department");
				departmentEle.addCDATA(acceptnode.getDepartment());
			}

			Element specialnodeEle = nodesEle.addElement("specialnode");
			
			for(Applynode applynode : applynodes){
				Element applynodeEle = specialnodeEle.addElement("applynode");
				
				Element specialtypeEle = applynodeEle.addElement("specialtype");
				specialtypeEle.addCDATA(applynode.getSpecialtype());
				Element specialnameEle = applynodeEle.addElement("specialname");
				specialnameEle.addCDATA(applynode.getSpecialname());
				Element specialstartdateEle = applynodeEle.addElement("specialstartdate");
				specialstartdateEle.addCDATA(applynode.getSpecialstartdate());
				Element specialuserEle = applynodeEle.addElement("specialuser");
				specialuserEle.addCDATA(applynode.getSpecialuser());
				Element specialusertelEle = applynodeEle.addElement("specialusertel");
				specialusertelEle.addCDATA(applynode.getSpecialusertel());
				Element specialuserphoneEle = applynodeEle.addElement("specialuserphone");
				specialuserphoneEle.addCDATA(applynode.getSpecialuserphone());
				Element specialideaEle = applynodeEle.addElement("specialidea");
				specialideaEle.addCDATA(applynode.getSpecialidea());
				Element specialcontentEle = applynodeEle.addElement("specialcontent");
				specialcontentEle.addCDATA(applynode.getSpecialcontent());
				Element speciallimitEle = applynodeEle.addElement("speciallimit");
				speciallimitEle.addCDATA(applynode.getSpeciallimit());
				Element specialunitEle = applynodeEle.addElement("specialunit");
				specialunitEle.addCDATA(applynode.getSpecialunit());
				Element adepartmentEle = applynodeEle.addElement("department");
				adepartmentEle.addCDATA(applynode.getDepartment());
			}
			
			for(Handlenode handlenode : handlenodes){
				Element handlenodeEle = specialnodeEle.addElement("handlenode");
				
				Element specialresultEle = handlenodeEle.addElement("specialresult");
				specialresultEle.addCDATA(handlenode.getSpecialresult());
				Element specialresultdateEle = handlenodeEle.addElement("specialresultdate");
				specialresultdateEle.addCDATA(handlenode.getSpecialresultdate());
				Element specialenddateEle = handlenodeEle.addElement("specialenddate");
				specialenddateEle.addCDATA(handlenode.getSpecialenddate());
				Element specialpayEle = handlenodeEle.addElement("specialpay");
				specialpayEle.addCDATA(handlenode.getSpecialpay());
				Element departmentEle = handlenodeEle.addElement("department");
				departmentEle.addCDATA(handlenode.getDepartment());				
			}
		}
		
		List<String> xmlStrs = null;
		try {
			//�����ʽ��
//			OutputFormat format = OutputFormat.createPrettyPrint();
//			format.setEncoding("utf-8");
			
			//businessxml
			java.io.ByteArrayOutputStream fos = new ByteArrayOutputStream();
			XMLWriter writer = new XMLWriter(fos,new OutputFormat (" ", true, "GB2312"));
			writer.write(doc);
			
			xmlStrs = new ArrayList<String>();
			xmlStrs.add(fos.toString());
			
			//authenticatexml
			java.io.ByteArrayOutputStream os = new ByteArrayOutputStream();
			XMLWriter w = new XMLWriter(os,new OutputFormat (" ", true, "GB2312"));
			w.write(document);
			xmlStrs.add(os.toString());
			
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xmlStrs;
    }
    
    public String getXzspFwqUrl(){
		
		String xzspFwqUrl = null;
			
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xzspFwqUrl.properties");
		Properties p = new Properties();
		try {
			p.load(inputStream);
			xzspFwqUrl = p.getProperty("xzspFwqUrl");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xzspFwqUrl;
	}

    
    //��axisдһ���ͻ���
    public Integer fsXml(String xmlString, String xmlStr){
    	int flag = 0;
    	
    	String xzspFwqUrl = this.getXzspFwqUrl();
    	try {             
    		
			String[] p = new String[]{xmlString,xmlStr};
			Object returnObject = WsClientManager.call(xzspFwqUrl,"saveOrUpdateBusinessCollect",p);
			if(returnObject != null){
				flag = Integer.parseInt(returnObject.toString());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();         
		}  catch (RemoteException e) { 
			flag=2;
			e.printStackTrace();         
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return flag;
		
    }
    
    //����id��ѯ��material�Ƿ����
    public boolean findMaterialById(String id){
    	boolean flag = false;

     	dbbase = new DBBeanBase("bpm", true);
     	conn = dbbase.getConn();
		try {
			st = conn.createStatement();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_material t where t.id = '");
			sql.append(id);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			if(rs != null && rs.next()){
				flag = true;
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	return flag;
    }
    
    
    //����permissionid����material�������ϸ��Ϣ
    public void findMaterialByPerid(){
     	dbbase = new DBBeanBase("bpm", true);
     	conn = dbbase.getConn();
		try {
			st = conn.createStatement();
			
			materials = new ArrayList<Material>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_material t where t.permissionid = '");
			sql.append(permissionid);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				material = new Material();
				material.setUuid(rs.getString("uuid"));
				material.setId(rs.getString("id"));
				material.setMlid(rs.getString("mlid"));
				material.setMlname(rs.getString("mlname"));
				if(rs.getString("selecttype") != null && rs.getString("selecttype").length() > 0){
					int s = Integer.parseInt(rs.getString("selecttype"));
					String[] ss = {"�����","ϵͳ����","�ϴ���ɨ���","֤��","���ڵݽ�","�����ٵ�","���ϲ���","��Ƶץȡ"};
					material.setSelecttype(ss[s]);
				}else{
					material.setSelecttype("");
				}
				material.setFid(rs.getString("fid"));
				material.setFpath(rs.getString("fpath"));
				material.setFname(rs.getString("fname"));
				if(rs.getString("status") != null && rs.getString("status").length() > 0){
					int i = Integer.parseInt(rs.getString("status"));
					String[] ii = {"δ�ύ","�ύ","����"};
					material.setStatus(ii[i]);
				}else{
					material.setStatus("");
				}
				material.setOrinum(rs.getString("orinum"));
				material.setCopynum(rs.getString("copynum"));
				if(rs.getString("isneed") != null && rs.getString("isneed").length() >0){
					int is = Integer.parseInt(rs.getString("isneed"));
					String[] iis = {"�Ǳ�Ҫ","��Ҫ"};
					material.setIsneed(iis[is]);
				}else{
					material.setIsneed("");
				}
				if(rs.getString("baseinfo") != null && rs.getString("baseinfo").length() > 0){
					int b = Integer.parseInt(rs.getString("baseinfo"));
					String[] bb = {"�Ǳ�","����","��ҵ","ҵ��"};
					material.setBaseinfo(bb[b]);
				}else{
					material.setBaseinfo("");
				}
				material.setAdjustment(rs.getString("adjustment"));
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				material.setPermission(permission);
				materials.add(material);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//ƴ��jason��
		StringBuffer jasonStr = new StringBuffer();
		if(materials.size() > 0 ){
			int i = materials.size();
			jasonStr.append("{totalProperty:" + materials.size() + ",root:["); 
			for(Material material: materials){	
				jasonStr.append("{uuid:'"); 
				jasonStr.append(material.getUuid());
				jasonStr.append("',id:'"); 
				jasonStr.append(material.getId());
				jasonStr.append("',mlid:'"); 
				jasonStr.append(material.getMlid());
				jasonStr.append("',mlname:'"); 
				jasonStr.append(material.getMlname());
				jasonStr.append("',selecttype:'"); 
				jasonStr.append(material.getSelecttype());
				jasonStr.append("',fid:'"); 
				jasonStr.append(material.getFid());
				jasonStr.append("',fpath:'"); 
				jasonStr.append(material.getFpath());
				jasonStr.append("',fname:'"); 
				jasonStr.append(material.getFname());
				jasonStr.append("',status:'"); 
				jasonStr.append(material.getStatus());
				jasonStr.append("',orinum:'"); 
				jasonStr.append(material.getOrinum());
				jasonStr.append("',copynum:'"); 
				jasonStr.append(material.getCopynum());
				jasonStr.append("',isneed:'"); 
				jasonStr.append(material.getIsneed());
				jasonStr.append("',baseinfo:'"); 
				jasonStr.append(material.getBaseinfo());
				jasonStr.append("',adjustment:'"); 
				jasonStr.append(material.getAdjustment());
				jasonStr.append("',permissionid:'");
				jasonStr.append(material.getPermission().getUuid());
				
				jasonStr.append("'}");
				
				i = i-1;
				if(i != 0){
					jasonStr.append(",");
				}
			}
			jasonStr.append("]}");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			String str =jasonStr.toString().replace("null", "");
			response.getWriter().write(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    //����permissionid����node�������ϸ��Ϣ
    public void findNodeByPerid(){
     	dbbase = new DBBeanBase("bpm", true);
     	conn = dbbase.getConn();
		try {
			st = conn.createStatement();
			
			nodes = new ArrayList<Node>();  
			sql = new StringBuffer();
			sql.append("select * from xzspjk_node t where t.permissionid = '");
			sql.append(permissionid);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				node = new Node();
				node.setUuid(rs.getString("uuid"));
				node.setNodeid(rs.getString("nodeid"));
				node.setNodename(rs.getString("nodename"));
				node.setNodeactor(rs.getString("nodeactor"));
				node.setNodeactorgh(rs.getString("nodeactorgh"));
				node.setNodeactorzwmc(rs.getString("nodeactorzwmc"));
				node.setNodeactorzwdm(rs.getString("nodeactorzwdm"));
				node.setDepartment(rs.getString("department"));
				node.setHandlerdate(rs.getString("handlerdate"));
				node.setHandleridea(rs.getString("handleridea"));				
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				node.setPermission(permission);
				nodes.add(node);
			}
    
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//ƴ��jason��
		StringBuffer jasonStr = new StringBuffer();
		if(nodes.size() > 0 ){
			int i = nodes.size();
			jasonStr.append("{totalProperty:" + nodes.size() + ",root:["); 
			for(Node node: nodes){	
				jasonStr.append("{uuid:'"); 
				jasonStr.append(node.getUuid());
				jasonStr.append("',nodeid:'"); 
				jasonStr.append(node.getNodeid());
				jasonStr.append("',nodename:'"); 
				jasonStr.append(node.getNodename());
				jasonStr.append("',nodeactor:'"); 
				jasonStr.append(node.getNodeactor());
				jasonStr.append("',nodeactorgh:'"); 
				jasonStr.append(node.getNodeactorgh());
				jasonStr.append("',nodeactorzwmc:'"); 
				jasonStr.append(node.getNodeactorzwmc());
				jasonStr.append("',nodeactorzwdm:'"); 
				jasonStr.append(node.getNodeactorzwdm());
				jasonStr.append("',handlerdate:'"); 
				jasonStr.append(node.getHandlerdate());
				jasonStr.append("',handleridea:'"); 
				jasonStr.append(node.getHandleridea());
				jasonStr.append("',department:'"); 
				jasonStr.append(node.getDepartment());
				jasonStr.append("',permissionid:'");
				jasonStr.append(node.getPermission().getUuid());
				
				jasonStr.append("'}");
				
				i = i-1;
				if(i != 0){
					jasonStr.append(",");
				}
			}
			jasonStr.append("]}");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {

			String str =jasonStr.toString().replace("null", "");
			response.getWriter().write(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    //����permissionid����notifynode�������ϸ��Ϣ
    public void findNotifynodeByPerid(){
     	dbbase = new DBBeanBase("bpm", true);
     	conn = dbbase.getConn();
		try {
			st = conn.createStatement();
			
			notifynodes = new ArrayList<Notifynode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_notifynode t where t.permissionid = '");
			sql.append(permissionid);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				notifynode = new Notifynode();
				notifynode.setUuid(rs.getString("uuid"));
				notifynode.setNodeactor(rs.getString("nodeactor"));
				notifynode.setComplementdate(rs.getString("complementdate"));
				notifynode.setComplementidea(rs.getString("complementidea"));
				notifynode.setComplementlist(rs.getString("complementlist"));
				notifynode.setDepartment(rs.getString("department"));				
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				notifynode.setPermission(permission);
				notifynodes.add(notifynode);
			}
    
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {		
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//ƴ��jason��
		StringBuffer jasonStr = new StringBuffer();
		if(notifynodes.size() > 0 ){
			int i = notifynodes.size();
			jasonStr.append("{totalProperty:" + notifynodes.size() + ",root:["); 
			for(Notifynode notifynode: notifynodes){	
				jasonStr.append("{uuid:'"); 
				jasonStr.append(notifynode.getUuid());
				jasonStr.append("',nodeactor:'"); 
				jasonStr.append(notifynode.getNodeactor());
				jasonStr.append("',complementdate:'"); 
				jasonStr.append(notifynode.getComplementdate());
				jasonStr.append("',complementidea:'"); 
				jasonStr.append(notifynode.getComplementidea());
				jasonStr.append("',complementlist:'"); 
				jasonStr.append(notifynode.getComplementlist());
				jasonStr.append("',department:'"); 
				jasonStr.append(notifynode.getDepartment());
				jasonStr.append("',permissionid:'");
				jasonStr.append(notifynode.getPermission().getUuid());
				
				jasonStr.append("'}");
				
				i = i-1;
				if(i != 0){
					jasonStr.append(",");
				}
			}
			jasonStr.append("]}");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			String str =jasonStr.toString().replace("null", "");
			response.getWriter().write(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    //����permissionid����acceptnode�������ϸ��Ϣ
    public void findAcceptnodeByPerid(){
     	dbbase = new DBBeanBase("bpm", true);
     	conn = dbbase.getConn();
		try {
			st = conn.createStatement();
			
			acceptnodes = new ArrayList<Acceptnode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_acceptnode t where t.permissionid = '");
			sql.append(permissionid);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				acceptnode = new Acceptnode();
				acceptnode.setUuid(rs.getString("uuid"));
				acceptnode.setHandler(rs.getString("handler"));
				acceptnode.setHandlerdate(rs.getString("handlerdate"));
				acceptnode.setHandleraddr(rs.getString("handleraddr"));
				acceptnode.setHandlerlist(rs.getString("handlerlist"));
				acceptnode.setDepartment(rs.getString("department"));
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				acceptnode.setPermission(permission);
				acceptnodes.add(acceptnode);
			}
    
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {			
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//ƴ��jason��
		StringBuffer jasonStr = new StringBuffer();
		if(acceptnodes.size() > 0 ){
			int i = acceptnodes.size();
			jasonStr.append("{totalProperty:" + acceptnodes.size() + ",root:["); 
			for(Acceptnode acceptnode : acceptnodes){	
				jasonStr.append("{uuid:'"); 
				jasonStr.append(acceptnode.getUuid());
				jasonStr.append("',handler:'"); 
				jasonStr.append(acceptnode.getHandler());
				jasonStr.append("',handlerdate:'"); 
				jasonStr.append(acceptnode.getHandlerdate());
				jasonStr.append("',handleraddr:'"); 
				jasonStr.append(acceptnode.getHandleraddr());
				jasonStr.append("',handlerlist:'"); 
				jasonStr.append(acceptnode.getHandlerlist());
				jasonStr.append("',department:'"); 
				jasonStr.append(acceptnode.getDepartment());
				jasonStr.append("',permissionid:'");
				jasonStr.append(acceptnode.getPermission().getUuid());
				
				jasonStr.append("'}");
				
				i = i-1;
				if(i != 0){
					jasonStr.append(",");
				}
			}
			jasonStr.append("]}");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			String str =jasonStr.toString().replace("null", "");
			response.getWriter().write(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    //����permissionid����applynode�������ϸ��Ϣ
    public void findApplynodeByPerid(){
     	dbbase = new DBBeanBase("bpm", true);
     	conn = dbbase.getConn();
		try {
			st = conn.createStatement();
			
			applynodes = new ArrayList<Applynode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_applynode t where t.permissionid='");
			sql.append(permissionid);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				applynode = new Applynode();
				applynode.setUuid(rs.getString("uuid"));
				applynode.setSpecialtype(rs.getString("specialtype"));
				applynode.setSpecialname(rs.getString("specialname"));
				applynode.setSpecialstartdate(rs.getString("specialstartdate"));
				applynode.setSpecialuser(rs.getString("specialuser"));
				applynode.setSpecialusertel(rs.getString("specialusertel"));
				applynode.setSpecialuserphone(rs.getString("specialuserphone"));
				applynode.setSpecialidea(rs.getString("specialidea"));
				applynode.setSpecialcontent(rs.getString("specialcontent"));
				applynode.setSpeciallimit(rs.getString("speciallimit"));
				applynode.setSpecialunit(rs.getString("specialunit"));
				applynode.setDepartment(rs.getString("department"));
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				applynode.setPermission(permission);
				applynodes.add(applynode);				
			}
    
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//ƴ��jason��
		StringBuffer jasonStr = new StringBuffer();
		if(applynodes.size() > 0 ){
			int i = applynodes.size();
			jasonStr.append("{totalProperty:" + applynodes.size() + ",root:["); 
			for(Applynode applynode : applynodes){	
				jasonStr.append("{uuid:'"); 
				jasonStr.append(applynode.getUuid());
				jasonStr.append("',specialtype:'"); 
				jasonStr.append(applynode.getSpecialtype());
				jasonStr.append("',specialname:'"); 
				jasonStr.append(applynode.getSpecialname());
				jasonStr.append("',specialstartdate:'"); 
				jasonStr.append(applynode.getSpecialstartdate());
				jasonStr.append("',specialuser:'"); 
				jasonStr.append(applynode.getSpecialuser());
				jasonStr.append("',specialusertel:'"); 
				jasonStr.append(applynode.getSpecialusertel());
				jasonStr.append("',specialuserphone:'"); 
				jasonStr.append(applynode.getSpecialuserphone());
				jasonStr.append("',specialidea:'"); 
				jasonStr.append(applynode.getSpecialidea());
				jasonStr.append("',specialcontent:'"); 
				jasonStr.append(applynode.getSpecialcontent());
				jasonStr.append("',speciallimit:'"); 
				jasonStr.append(applynode.getSpeciallimit());
				jasonStr.append("',specialunit:'"); 
				jasonStr.append(applynode.getSpecialunit());
				jasonStr.append("',department:'"); 
				jasonStr.append(applynode.getDepartment());
				jasonStr.append("',permissionid:'");
				jasonStr.append(applynode.getPermission().getUuid());
				
				jasonStr.append("'}");
				
				i = i-1;
				if(i != 0){
					jasonStr.append(",");
				}
			}
			jasonStr.append("]}");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			String str =jasonStr.toString().replace("null", "");
			response.getWriter().write(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    
    //����permissionid����handlenode�������ϸ��Ϣ
    public void findHandlenodeByPerid(){
     	dbbase = new DBBeanBase("bpm", true);
     	conn = dbbase.getConn();
		try {
			st = conn.createStatement();
			
			handlenodes = new ArrayList<Handlenode>();
			sql = new StringBuffer();
			sql.append("select * from xzspjk_handlenode t where t.permissionid = '");
			sql.append(permissionid);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				handlenode = new Handlenode();
				handlenode.setUuid(rs.getString("uuid"));
				handlenode.setSpecialresult(rs.getString("specialresult"));
				handlenode.setSpecialresultdate(rs.getString("specialresultdate"));
				handlenode.setSpecialenddate(rs.getString("specialenddate"));
				handlenode.setSpecialpay(rs.getString("specialpay"));
				handlenode.setDepartment(rs.getString("department"));
				permission = new Permission();
				permission.setUuid(rs.getString("permissionid"));
				handlenode.setPermission(permission);
				handlenodes.add(handlenode);				
			}
    
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {			
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//ƴ��jason��
		StringBuffer jasonStr = new StringBuffer();
		if(handlenodes.size() > 0 ){
			int i = handlenodes.size();
			jasonStr.append("{totalProperty:" + handlenodes.size() + ",root:["); 
			for(Handlenode handlenode : handlenodes){	
				jasonStr.append("{uuid:'"); 
				jasonStr.append(handlenode.getUuid());
				jasonStr.append("',specialresult:'");
				jasonStr.append(handlenode.getSpecialresult());
				jasonStr.append("',specialresultdate:'");
				jasonStr.append(handlenode.getSpecialresultdate());
				jasonStr.append("',specialenddate:'");
				jasonStr.append(handlenode.getSpecialenddate());
				jasonStr.append("',specialpay:'");
				jasonStr.append(handlenode.getSpecialpay());
				jasonStr.append("',department:'");
				jasonStr.append(handlenode.getDepartment());
				jasonStr.append("',permissionid:'");
				jasonStr.append(handlenode.getPermission().getUuid());
				
				jasonStr.append("'}");
				
				i = i-1;
				if(i != 0){
					jasonStr.append(",");
				}
			}
			jasonStr.append("]}");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			String str =jasonStr.toString().replace("null", "");
			response.getWriter().write(str);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    //����permissionid����permission�������ϸ��Ϣ
	public void findPermissionByPerUuid(){
     	dbbase = new DBBeanBase("bpm", true);
     	conn = dbbase.getConn();
		try {
			st = conn.createStatement();
			
			sql = new StringBuffer();
			sql.append("select * from xzspjk_permission t where t.uuid = '");
			sql.append(permissionid);
			sql.append("'");
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				permission =  new Permission();
				permission.setUuid(permissionid);
				permission.setName(rs.getString("name"));
				permission.setBsnum(rs.getString("bsnum"));
				permission.setXmmc(rs.getString("xmmc"));
				permission.setDepartment(rs.getString("department"));
				if(rs.getString("status") != null&& rs.getString("status").length() > 0){
					int s = Integer.parseInt(rs.getString("status"));
					String[] ss = {"�ڰ�","���","����","��������","�ر����","�ݴ�","�ѹ鵵"};
					permission.setStatus(ss[s]);
				}else{
					permission.setStatus("");
				}
				permission.setId(rs.getString("id"));
				application = new Application1();
				application.setUuid(rs.getString("applicationid"));
				permission.setApplication1(application);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {			
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//ƴ���ַ���
		String totalCount = "1";//���Permission����һ���ж���������
		
		StringBuffer jasonStr = new StringBuffer();
		if(permission != null ){
			jasonStr.append("{totalProperty:" + totalCount + ",root:["); 
			jasonStr.append("{uuid:'"); 
			jasonStr.append(permission.getUuid());
			jasonStr.append("',sxmc:'"); 
			jasonStr.append(permission.getName());
			jasonStr.append("',ywbh:'"); 
			jasonStr.append(permission.getBsnum());
			jasonStr.append("',xmmc:'");
			jasonStr.append(permission.getXmmc());
			jasonStr.append("',department:'"); 
			jasonStr.append(permission.getDepartment());
			jasonStr.append("',status:'"); 
			jasonStr.append(permission.getStatus());
			jasonStr.append("',applicationid:'"); 
			jasonStr.append(permission.getApplication1().getUuid());
			jasonStr.append("'}]}");
		}
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			String str =jasonStr.toString().replace("null", "");
			response.getWriter().write(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
    
    //����applicationid��ѯpermission����
    public List<Permission> findPersByAid(String applicationid){
    	List<Permission> pers = new ArrayList<Permission>();

    	sql = new StringBuffer();
    	sql.append("select * from xzspjk_permission t where t.applicationid = '");
    	sql.append(applicationid);
    	sql.append("'");
    	
     	dbbase = new DBBeanBase("bpm", true);
     	conn = dbbase.getConn();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			while(rs != null && rs.next()){
				per = new Permission();
				per.setUuid(rs.getString("uuid"));
				pers.add(per);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	
    	return pers;
    }
	
    //����uuidɾ��--------------------------------------------------------------------------------------
    public void deleteByUuid() throws UnsupportedEncodingException{
    	
    	if(applicationidList != null){
    		String[] appList = applicationidList.split(",");
    		for(int i=0; i<appList.length; i++){
			    List<Permission> pers = this.findPersByAid(appList[i]);
		     	dbbase = new DBBeanBase("bpm", true);
		     	try {
					conn = dbbase.getConn();
					st = conn.createStatement();
					//ɾ��application  
					sql = new StringBuffer();
				    sql.append("delete from xzspjk_application t where t.uuid = '");
				    sql.append(appList[i]);
				    sql.append("'");
				    st.executeUpdate(sql.toString());
					
				    for(Permission p : pers){
				    	//
						sql = new StringBuffer();
					    sql.append("delete from xzspjk_permission t where t.uuid = '");
					    sql.append(p.getUuid());
					    sql.append("'");
					    st.executeUpdate(sql.toString());
				    	
				    	//ɾ��acceptnode
						sql = new StringBuffer();
					    sql.append("delete from xzspjk_acceptnode t where t.permissionid = '");
					    sql.append(p.getUuid());
					    sql.append("'");
					    st.executeUpdate(sql.toString());
					    //ɾ��material 
						sql = new StringBuffer();
					    sql.append("delete from xzspjk_material t where t.permissionid = '");
					    sql.append(p.getUuid());
					    sql.append("'");
					    st.executeUpdate(sql.toString());
					    //ɾ��node 
						sql = new StringBuffer();
					    sql.append("delete from xzspjk_node t where t.permissionid = '");
					    sql.append(p.getUuid());
					    sql.append("'");
					    st.executeUpdate(sql.toString());
					    //ɾ��notifynode  
						sql = new StringBuffer();
					    sql.append("delete from xzspjk_notifynode t where t.permissionid = '");
					    sql.append(p.getUuid());
					    sql.append("'");
					    st.executeUpdate(sql.toString());
					    //ɾ��applynode  
						sql = new StringBuffer();
					    sql.append("delete from xzspjk_applynode t where t.permissionid = '");
					    sql.append(p.getUuid());
					    sql.append("'");
					    st.executeUpdate(sql.toString());
					    
					    //ɾ��handlenode  
						sql = new StringBuffer();
					    sql.append("delete from xzspjk_handlenode t where t.permissionid = '");
					    sql.append(p.getUuid());
					    sql.append("'");
					    st.executeUpdate(sql.toString());
					    conn.commit();
				    }
				    
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					try {
						if(rs != null){
							rs.close();					
						}
					} catch (Exception e) {
						e.printStackTrace(); 
					}
					
					try {
						if(st != null){
							st.close();					
						}
					} catch (Exception e) {
						e.printStackTrace(); 
					}
					
					try {
						if(conn != null){
							conn.close();
						}				
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
    	}
    	HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().write("ɾ���ɹ�");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    //��ѯ�� xzspjk_permission�ܹ��ж���������---------------------------------------------------------
    public String findCountByPer(){
    	String size = "";
    	sql = new StringBuffer();
    	sql.append("select count(uuid) as zs from xzspjk_permission");
    	dbbase = new DBBeanBase("bpm", true);
	    try{
			conn = dbbase.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			if(rs != null && rs.next()){
				size = rs.getString("zs");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {			
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    	
    	return size;
    }
    
    //����û�з��ͳ�ȥ������
    public void BsnumFindAllAction(){
    	HttpServletResponse response=ServletActionContext.getResponse();
    	try{
			int index=Integer.parseInt(start);
			int pageSize=Integer.parseInt(limit);
			XzspjkTempBFAction xtbfAction=new XzspjkTempBFAction();
			List<XzspjkTempBFBean> bsnumlist= xtbfAction.findAll(index, pageSize);
			int totalCount = xtbfAction.Count();
			String json="{totalProperty:"+totalCount+",root:[";
			for(int i=0;i<bsnumlist.size();i++){
					   json+="{bsnum:'"+bsnumlist.get(i).getBsnum()+"'}";
						if(i!=(bsnumlist.size()-1)){
							json+=",";
						}
			}
			json+="]}";
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);

		}catch(Exception e){
			e.printStackTrace();
		}
    }
    
  //ɾ��û�з��ͳ�ȥ������
    public void delBsnum(){
		HttpServletResponse response=ServletActionContext.getResponse();
		String uid[]=deluuid.split(",");
		XzspjkTempBFAction xtbfAction=new XzspjkTempBFAction();
		try{
			for(int i=0;i<uid.length;i++){
				String id=uid[i];
				int a=xtbfAction.deleteByBSNUM(id);
			}
			 response.getWriter().print("{success:true}");
		}catch(Exception e){
			e.printStackTrace();
		}
    }

	//��������------------------------------------------------------------------------------------------
	public void findAllYtzXZSP() throws UnsupportedEncodingException{
		int index = Integer.parseInt(start);
		int pageSize = Integer.parseInt(limit);
		PreparedStatement pst = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
	     String countQuery="";
	     countQuery="select count(*) from xzspjk_permission a left join xzspjk_application b on a.applicationid = b.uuid where a.flag = '1' ";
	     try {
	    	dbbase = new DBBeanBase("bpm", true);  
		    conn = dbbase.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(countQuery);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
		
	    sql = new StringBuffer();
	    sql.append("select * From (" +
	    		"select A.*,RowNum RN  from " +
	    		"(select a.*,b.appname,b.apporg from xzspjk_permission a left join xzspjk_application b on a.applicationid = b.uuid where a.flag = '1'  order by b.appdate desc)" +
	    		"A ) " +
	    		"where  RN>"+index+" AND RN<="+(index+pageSize));
	         pst = conn.prepareStatement(sql.toString());
		     rs = pst.executeQuery();
	    
	    lbList = new ArrayList<Permission>();	    
			while(rs != null && rs.next()){
				per = new Permission();
				per.setUuid(rs.getString("uuid"));
				per.setId(rs.getString("id"));
				per.setName(rs.getString("name"));
				per.setBsnum(rs.getString("bsnum"));
				per.setXmmc(rs.getString("xmmc"));
				per.setDepartment(rs.getString("department"));
				per.setStatus(rs.getString("status"));
				ap = new Application1();
				ap.setUuid(rs.getString("applicationid"));
				ap.setAppname(rs.getString("appname"));
				ap.setApporg(rs.getString("apporg"));
				per.setApplication1(ap);
				lbList.add(per);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//ƴ��jason��
		//String totalCount = this.findCountByPer();//���Permission����һ���ж���������
		
		StringBuffer jasonStr = new StringBuffer();
		if(lbList.size() > 0 ){
			int i = lbList.size();
			jasonStr.append("{totalProperty:" + totalCount + ",root:["); 
			for(Permission pp : lbList){	
				jasonStr.append("{uuid:'"); //uuid
				jasonStr.append(pp.getUuid());
				jasonStr.append("',sxmc:'"); //��������
				jasonStr.append(pp.getApplication1().getApporg()+"("+pp.getApplication1().getAppname()+"����"+pp.getName()+"����������)");
				jasonStr.append("',ywbh:'"); //ҵ����
				jasonStr.append(pp.getBsnum());
				jasonStr.append("',applicationid:'"); //applicationid
				jasonStr.append(pp.getApplication1().getUuid());
				jasonStr.append("',sqr:'");//������
				jasonStr.append(pp.getApplication1().getAppname());
				jasonStr.append("',sqdw:'"); //���뵥λ
				jasonStr.append(pp.getApplication1().getApporg());
				jasonStr.append("'}");
				
				i = i-1;
				if(i != 0){
					jasonStr.append(",");
				}
			}
			jasonStr.append("]}");
		}
		try {
			response.getWriter().write(jasonStr.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
   
	//��������------δ��ת������------------------------------------------------------------------------------------
	public void findAllXZSP() throws UnsupportedEncodingException{
		int index = Integer.parseInt(start);
		int pageSize = Integer.parseInt(limit);
		PreparedStatement pst = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
	     String countQuery="";
	     countQuery="select count(*) from xzspjk_permission a left join xzspjk_application b on a.applicationid = b.uuid where a.flag = '0' ";
	     try {
	    	dbbase = new DBBeanBase("bpm", true);  
		    conn = dbbase.getConn();
			st = conn.createStatement();
			rs = st.executeQuery(countQuery);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
		
	    sql = new StringBuffer();
	    sql.append("select * From (" +
	    		"select A.*,RowNum RN  from " +
	    		"(select a.*,b.appname,b.apporg from xzspjk_permission a left join xzspjk_application b on a.applicationid = b.uuid where a.flag = '0'  order by b.appdate desc  ) " +
	    		"A ) " +
	    		"where  RN>"+index+" AND RN<="+(index+pageSize));
	         pst = conn.prepareStatement(sql.toString());
		     rs = pst.executeQuery();
	    
	    lbList = new ArrayList<Permission>();	    
			while(rs != null && rs.next()){
				per = new Permission();
				per.setUuid(rs.getString("uuid"));
				per.setId(rs.getString("id"));
				per.setName(rs.getString("name"));
				per.setBsnum(rs.getString("bsnum"));
				per.setXmmc(rs.getString("xmmc"));
				per.setDepartment(rs.getString("department"));
				per.setStatus(rs.getString("status"));
				ap = new Application1();
				ap.setUuid(rs.getString("applicationid"));
				ap.setAppname(rs.getString("appname"));
				ap.setApporg(rs.getString("apporg"));
				per.setApplication1(ap);
				lbList.add(per);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {			
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//ƴ��jason��
		//String totalCount = this.findCountByPer();//���Permission����һ���ж���������
		
		StringBuffer jasonStr = new StringBuffer();
		if(lbList.size() > 0 ){
			int i = lbList.size();
			jasonStr.append("{totalProperty:" + totalCount + ",root:["); 
			for(Permission pp : lbList){	
				jasonStr.append("{uuid:'"); //uuid
				jasonStr.append(pp.getUuid());
				jasonStr.append("',sxmc:'"); //��������
				jasonStr.append(pp.getApplication1().getApporg()+"("+pp.getApplication1().getAppname()+"����"+pp.getName()+"����������)");
				jasonStr.append("',ywbh:'"); //ҵ����
				jasonStr.append(pp.getBsnum());
				jasonStr.append("',applicationid:'"); //applicationid
				jasonStr.append(pp.getApplication1().getUuid());
				jasonStr.append("',sqr:'");//������
				jasonStr.append(pp.getApplication1().getAppname());
				jasonStr.append("',sqdw:'"); //���뵥λ
				jasonStr.append(pp.getApplication1().getApporg());
				jasonStr.append("',cz:'");
				jasonStr.append(pp.getUuid());
				jasonStr.append("'}");
				
				i = i-1;
				if(i != 0){
					jasonStr.append(",");
				}
			}
			jasonStr.append("]}");
		}
		try {
			response.getWriter().write(jasonStr.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Permission> getLbList() {
		return lbList;
	}

	public void setLbList(List<Permission> lbList) {
		this.lbList = lbList;
	}

	public String getApplicationid() {
		return applicationid;
	}

	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}

	public String getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}

	public String getApplicationidList() {
		return applicationidList;
	}

	public void setApplicationidList(String applicationidList) {
		this.applicationidList = applicationidList;
	}


	public Permission getPermission() {
		return permission;
	}


	public void setPermission(Permission permission) {
		this.permission = permission;
	}


	public List<Material> getMaterials() {
		return materials;
	}


	public void setMaterials(List<Material> materials) {
		this.materials = materials;
	}


	public List<Node> getNodes() {
		return nodes;
	}


	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}


	public List<Notifynode> getNotifynodes() {
		return notifynodes;
	}


	public void setNotifynodes(List<Notifynode> notifynodes) {
		this.notifynodes = notifynodes;
	}


	public List<Acceptnode> getAcceptnodes() {
		return acceptnodes;
	}


	public void setAcceptnodes(List<Acceptnode> acceptnodes) {
		this.acceptnodes = acceptnodes;
	}


	public List<Applynode> getApplynodes() {
		return applynodes;
	}


	public void setApplynodes(List<Applynode> applynodes) {
		this.applynodes = applynodes;
	}


	public List<Handlenode> getHandlenodes() {
		return handlenodes;
	}


	public void setHandlenodes(List<Handlenode> handlenodes) {
		this.handlenodes = handlenodes;
	}

	public String getBsnum() {
		return bsnum;
	}

	public void setBsnum(String bsnum) {
		this.bsnum = bsnum;
	}

	public String getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
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

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public String getDeluuid() {
		return deluuid;
	}

	public void setDeluuid(String deluuid) {
		this.deluuid = deluuid;
	}

}
	
