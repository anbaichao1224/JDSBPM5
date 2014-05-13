package net.kzxd.dj.action;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.usm.persistence.model.Person;
import net.kzxd.dj.bean.FdtOaDj;
import net.kzxd.dj.bean.FdtOaDjBean;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class DjAction implements Action {

	private String versionid;

	private List fodlists;

	private List<FdtOaDjBean> bfodlists = new ArrayList<FdtOaDjBean>(0);

	private String columnjson = "";

	private String datajson = "";

	private String start;

	private String limit;

	private String docname;

	private String department;

	private String rdata;

	private String formbh; // ���

	private String formdocbt; // 4�ı���

	private String formdepartment; // 4�ĵ�λ

	private String formrdate; // 4������

	private String formmj; // �ܼ�

	private String formjjcd; // ��̶�

	public String execute() throws Exception {
		return SUCCESS;
	}

	public void findAllByVersionId() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		String start = request.getParameter("start");
		String limit = request.getParameter("limit");
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		FdtOaDj fod = new FdtOaDj();
		try {
			conn = dbbase.getConn();
			StringBuffer sql = new StringBuffer();
			factory = new DAOFactory(conn, fod);
			if (versionid != null && versionid.length() > 0) {
				String[] str = versionid.split(",");
				sql.append(" versionid in(");
				sql.append("'" + versionid + "'");
				if(str.length >= 0){
					sql.append(",");
				}
				for (int i = 0; i < str.length; i++) {
					sql.append("'" + str[i] + "'");
					if (str.length - 1 != i) {
						sql.append(",");
					}
				}
				sql.append(")");
				if (docname != null && !"".equals(docname)) {
					sql.append(" and docbt like '%" + docname + "%'");
				}
				if (department != null && !"".equals(department)) {
					sql.append(" and department like '%" + department + "%'");
				}
				if (rdata != null && !"".equals(rdata)) {
					sql.append(" and RDATE like '%" + rdata + "%'");
				}
				fod.setWhereClause(sql.toString());
				fod.addOrderBy("rdate", false);
				fodlists = factory.find();
				addFodBeanList();
			} else {
				return;
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

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
			fb.setVersionid(fod.getVersionid());
			bfodlists.add(fb);
		}
	}

	public void saveDj() throws UnsupportedEncodingException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		String uuid = new UUID().toString();
		Connection conn = null;
		DAOFactory factory = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		FdtOaDj fod = new FdtOaDj();
		dbbase = new DBBeanBase("bpm", true);
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, fod);
			factory.setDAO(fod);
			fod.setConnection(conn);
			fod.setUuid(uuid);
			fod.setDocbt(formdocbt);
			fod.setSn(formbh);
			fod.setVersionid(versionid);
			fod.setDepartment(formdepartment);
			fod.setRdate(formrdate);
			fod.setEmergency(formjjcd);
			fod.setClassification(formmj);
			fod.create();
			conn.commit();
			response.getWriter().print("{success:true,msg:'�ɹ�'}");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

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
			str += "{" + "DOCBT:'" + fb.getDocbt() + "',RDATE:'"
					+ fb.getRdate() + "',DEPARTMENT:'" + fb.getDepartment()
					+ "',CLASSIFICATION:'" + fb.getClassification()
					+ "',EMERGENCY:'" + fb.getEmergency() + "',SN:'"
					+ fb.getSn() + "',ATTNAME:'" + fb.getAttname() + "'},";
		}
		if (!"".equals(str)) {
			str = str.substring(0, str.length() - 1);
		}
		json += str;
		json += "]}";
		return json;
	}

	public String getVersionid() {
		return versionid;
	}

	public void setVersionid(String versionid) {
		this.versionid = versionid;
	}

	public List getFodlists() {
		return fodlists;
	}

	public void setFodlists(List fodlists) {
		this.fodlists = fodlists;
	}

	public List<FdtOaDjBean> getBfodlists() {
		return bfodlists;
	}

	public void setBfodlists(List<FdtOaDjBean> bfodlists) {
		this.bfodlists = bfodlists;
	}

	public String getColumnjson() {
		return columnjson;
	}

	public void setColumnjson(String columnjson) {
		this.columnjson = columnjson;
	}

	public String getDatajson() {
		return datajson;
	}

	public void setDatajson(String datajson) {
		this.datajson = datajson;
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

	public String getFormbh() {
		return formbh;
	}

	public void setFormbh(String formbh) {
		this.formbh = formbh;
	}

	public String getFormdocbt() {
		return formdocbt;
	}

	public void setFormdocbt(String formdocbt) {
		this.formdocbt = formdocbt;
	}

	public String getFormdepartment() {
		return formdepartment;
	}

	public void setFormdepartment(String formdepartment) {
		this.formdepartment = formdepartment;
	}

	public String getFormrdate() {
		return formrdate;
	}

	public void setFormrdate(String formrdate) {
		this.formrdate = formrdate;
	}

	public String getFormmj() {
		return formmj;
	}

	public void setFormmj(String formmj) {
		this.formmj = formmj;
	}

	public String getFormjjcd() {
		return formjjcd;
	}

	public void setFormjjcd(String formjjcd) {
		this.formjjcd = formjjcd;
	}

}
