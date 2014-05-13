package net.kzxd.dj.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.kzxd.dj.bean.FdtOaDj;
import net.kzxd.dj.bean.FdtOaDjBean;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class LwdjAction implements Action{

	private String xmlmodel;
	private String uuid;

	private FdtOaDj rfod;
	/**
	 * extjs分页参数
	 */
	private String start;
	private String limit;

	/**
	 * 查询参数
	 */
	private String docname;
	private String department;
	private String rdata;
	private String tdata;
	
	private String sxxxid;
	private String tid;

	/**
	 * 登记数据添加
	 */
	private String djbh;
	private String djlwbt;
	private String djlwdw;
	private String djmj;
	private String djjjcd;
	private Date djlwrq;
	
	/**
	 * 删除
	 */
	private String deluuid;
	
	/**
	 * 用于内部的集合;
	 */
	private List fodlists;
	private List<FdtOaDjBean> bfodlists = new ArrayList<FdtOaDjBean>(0);

	public String execute() throws Exception {
		return SUCCESS;
	}

	/**
	 * 查找所有的数据 用于前台页面像是的登记记录
	 * 
	 * @throws Exception
	 */
	public void findAllByModel() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		// 1、得到数据库链接
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		FdtOaDj fod = new FdtOaDj();
		// 2、通过model查找所有的数据
		try {
			conn = dbbase.getConn();
			StringBuffer sql = new StringBuffer();
			factory = new DAOFactory(conn, fod);
			if (xmlmodel != null) {
				sql.append("djdel = 'Y'");
				sql.append(" and xmlmodel = '" + xmlmodel + "'");
				if (docname != null && !"".equals(docname)) {
					sql.append(" and docbt like '%" + docname + "%'");
				}
				if (department != null && !"".equals(department)) {
					sql.append(" and department like '%" + department + "%'");
				}
				if (rdata != null && !"".equals(rdata)) {
					sql.append(" and RDATE >= to_date('" + rdata + "','yyyy-mm-dd hh24:mi:ss')");
				}
				if (tdata != null && !"".equals(tdata)) {
					sql.append(" and RDATE <= to_date('" + tdata + "','yyyy-mm-dd hh24:mi:ss')");
				}
				fod.setWhereClause(sql.toString());
				fod.addOrderBy("rdate", false);
				fodlists = factory.find();
				addFodBeanList();
			} else {
			}
			String json = "";
			if (bfodlists != null) {
				json = this.getDataJson(bfodlists);
			}
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	
	/**
	 * 添加页面的跳转方法
	 * @return
	 */
	public String addpage(){
		sxxxid = new UUID().toString();
		return "addpage";
	}
	
	/**
	 * 添加登记信息的方法
	 * @throws IOException 
	 */
	public void addDj() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		Connection conn = null;
		DAOFactory factory = null;
		DBBeanBase dbbase = null;
		FdtOaDj fod = new FdtOaDj();
		PrintWriter out = response.getWriter();
		dbbase = new DBBeanBase("bpm", true);
		if(djbh == null){
			djbh = "";
		}
		if(djlwbt == null){
			djlwbt = "";
		}
		if(djlwdw == null){
			djlwdw = "";
		}
		if(djjjcd == null){
			djjjcd = "";
		}
		if(djlwrq == null) {
			djlwrq = new Date();
		}
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, fod);
			factory.setDAO(fod);
			fod.setConnection(conn);
			fod.setUuid(tid);
			fod.setDocbt(djlwbt);
			fod.setSn(djbh);
			fod.setDepartment(djlwdw);
			fod.setDjdel("Y");
			fod.setRdate(djlwrq);
			fod.setEmergency(djjjcd);
			fod.setClassification(djmj);
			fod.setXmlmodel(xmlmodel);
			fod.create();
			conn.commit();
			out.print("success");
		}catch(Exception e) {
			out.print("failure");
		}finally{
			out.close();
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 显示修改登记信息页面的方法
	 * @throws IOException 
	 */
	public String updatePage() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		Connection conn = null;
		DAOFactory factory = null;
		DBBeanBase dbbase = null;
		FdtOaDj fod = new FdtOaDj();
		rfod = new FdtOaDj();
		dbbase = new DBBeanBase("bpm", true);
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, fod);
			fod.setUuid(uuid);
			rfod = (FdtOaDj) factory.findByPrimaryKey();
			rfod.setConnection(conn);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return "updatepage";		
	} 
	
	public void updateDj() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		Connection conn = null;
		DAOFactory factory = null;
		DBBeanBase dbbase = null;
		FdtOaDj fod = new FdtOaDj();
		FdtOaDj rfod = new FdtOaDj();
		dbbase = new DBBeanBase("bpm", true);
		PrintWriter out = response.getWriter();
		if(djbh == null){
			djbh = "";
		}
		if(djlwbt == null){
			djlwbt = "";
		}
		if(djlwdw == null){
			djlwdw = "";
		}
		if(djjjcd == null){
			djjjcd = "";
		}
		if(djlwrq == null) {
			djlwrq = new Date();
		}
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, fod);
			fod.setUuid(uuid);
			rfod = (FdtOaDj) factory.findByPrimaryKey();
			rfod.setConnection(conn);
			rfod.setSn(djbh);
			rfod.setDocbt(djlwbt);
			rfod.setDepartment(djlwdw);
			rfod.setRdate(djlwrq);
			rfod.setEmergency(djjjcd);
			rfod.setClassification(djmj);
			rfod.update();
			conn.commit();
			out.print("success");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("failure");
		} finally {
			out.close();
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	// 删除方法
	public void delDj() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String del[] = deluuid.split(",");
		Connection conn = null;
		DAOFactory factory = null;
		DBBeanBase dbbase = null;
		FdtOaDj fod = new FdtOaDj();
		FdtOaDj rfod = new FdtOaDj();
		dbbase = new DBBeanBase("bpm", true);
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, fod);
			for (int i = 0; i < del.length; i++) {
				fod.setUuid(del[i]);
				rfod = (FdtOaDj) factory.findByPrimaryKey();
				rfod.setConnection(conn);
				rfod.setDjdel("N");
				rfod.update();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	
	/**
	 * 转换类
	 * 
	 * @throws PersonNotFoundException
	 */
	public void addFodBeanList() throws PersonNotFoundException {
		bfodlists = new ArrayList();
		for (int i = 0; i < fodlists.size(); i++) {
			FdtOaDj fod = (FdtOaDj) fodlists.get(i);
			FdtOaDjBean fb = new FdtOaDjBean();
			fb.setUuid(fod.getUuid());
			fb.setDocbt(fod.getDocbt());
			fb.setRdate(fod.getRdate());
			fb.setDepartment(fod.getDepartment());
			fb.setClassification(fod.getClassification());
			fb.setEmergency(fod.getEmergency());
			fb.setSn(fod.getSn());
			fb.setXmlmodel(fod.getXmlmodel());
			fb.setModeltype(fod.getModeltype());
			bfodlists.add(fb);
		}
	}

	/**
	 * 返回前台页面显示数据的json
	 * 
	 * @param list
	 * @return
	 */
	public String getDataJson(List list) {
		int count = Integer.parseInt(start) + Integer.parseInt(limit);
		int totalpoperty = list.size();

		String str = "";
		String json = "{totalCount:" + totalpoperty + ",root:[";
		if (count > list.size()) {
			list = list.subList(Integer.parseInt(start), list.size());
		} else {
			list = list.subList(Integer.parseInt(start), Integer
					.parseInt(start)
					+ Integer.parseInt(limit));
		}
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			FdtOaDjBean fb = new FdtOaDjBean();
			fb = (FdtOaDjBean) iter.next();
			String modeltype = fb.getModeltype();
			String mt = "";
			if (modeltype != null && !"null".equals(modeltype)) {
				mt = modeltype;
			}
			String DOCBT = "";
			String DEPARTMENT = "";
			String CLASSIFICATION = "";
			String EMERGENCY = "";
			String SN = "";
			if(fb.getDocbt() != null){
				DOCBT = fb.getDocbt();
			}
			if(fb.getDepartment() != null){
				DEPARTMENT = fb.getDepartment();
			}
			if(fb.getEmergency() != null){
				EMERGENCY = fb.getEmergency();
			}
			if(fb.getClassification() != null){
				CLASSIFICATION = fb.getClassification();
			}
			if(fb.getSn() != null){
				SN = fb.getSn();
			}
			Date date = fb.getRdate();
			SimpleDateFormat sdate = new SimpleDateFormat("yyyy-MM-dd");
			String extdate = "";
			if (date != null) {
				extdate = sdate.format(date);
			}
			str += "{UUID:'"
					+ fb.getUuid()
					+ "',DOCBT:'"
					+ DOCBT
					+ "',RDATE:'"
					+ extdate
					+ "',DEPARTMENT:'"
					+ DEPARTMENT
					+ "',CLASSIFICATION:'"
					+ CLASSIFICATION
					+ "',EMERGENCY:'"
					+ EMERGENCY
					+ "',SN:'"
					+ SN
					+ "',ATTNAME:'"
					+ fb.getAttname()
					+ "',MODELTYPE:'"
					+ mt
					+ "',OPTION:'<a href=\"javascript:void(0);\" onclick=\"startlc();\">启动流程</a>'},";
		}
		if (!"".equals(str)) {
			str = str.substring(0, str.length() - 1);
		}
		json += str;
		json += "]}";
		return json;
	}

	public String getXmlmodel() {
		return xmlmodel;
	}

	public void setXmlmodel(String xmlmodel) {
		this.xmlmodel = xmlmodel;
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

	public String getDocname() {
		return docname;
	}

	public void setDocname(String docname) {
		this.docname = docname;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getRdata() {
		return rdata;
	}

	public void setRdata(String rdata) {
		this.rdata = rdata;
	}

	public String getTdata() {
		return tdata;
	}

	public void setTdata(String tdata) {
		this.tdata = tdata;
	}

	public String getSxxxid() {
		return sxxxid;
	}

	public void setSxxxid(String sxxxid) {
		this.sxxxid = sxxxid;
	}

	public String getDjbh() {
		return djbh;
	}

	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}

	public String getDjlwbt() {
		return djlwbt;
	}

	public void setDjlwbt(String djlwbt) {
		this.djlwbt = djlwbt;
	}

	public String getDjlwdw() {
		return djlwdw;
	}

	public void setDjlwdw(String djlwdw) {
		this.djlwdw = djlwdw;
	}

	public String getDjmj() {
		return djmj;
	}

	public void setDjmj(String djmj) {
		this.djmj = djmj;
	}

	public String getDjjjcd() {
		return djjjcd;
	}

	public void setDjjjcd(String djjjcd) {
		this.djjjcd = djjjcd;
	}

	public Date getDjlwrq() {
		return djlwrq;
	}

	public void setDjlwrq(Date djlwrq) {
		this.djlwrq = djlwrq;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public FdtOaDj getRfod() {
		return rfod;
	}

	public void setRfod(FdtOaDj rfod) {
		this.rfod = rfod;
	}

	public String getDeluuid() {
		return deluuid;
	}

	public void setDeluuid(String deluuid) {
		this.deluuid = deluuid;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
	
}
