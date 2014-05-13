package net.itjds.fdt.metting;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.PageScrollParas;
import net.itjds.j2ee.util.SystemUtil;


public class BpmMatterBLInfoDAO extends DAO {

	String uuid;

	String sxxxid;
	
	String personid;
	
	String personname;
	
	String bldate;
	
	String ckstatus;
	
	String hfcontent;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}

	

	public String getSxxxid() {
		return sxxxid;
	}

	public void setSxxxid(String _sxxxid) {
		firePropertyChange("sxxxid", sxxxid, _sxxxid);
		this.sxxxid = _sxxxid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		this.personid = _personid;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String _personname) {
		firePropertyChange("personname", personname, _personname);
		this.personname = _personname;
	}

	
	public String getBldate() {
		return bldate;
	}

	public void setBldate(String _bldate) {
		firePropertyChange("bldate", bldate, _bldate);
		this.bldate = _bldate;
	}


	public String getCkstatus() {
		return ckstatus;
	}

	public void setCkstatus(String _ckstatus) {
		firePropertyChange("ckstatus", ckstatus, _ckstatus);
		this.ckstatus = _ckstatus;
	}

	public String getHfcontent() {
		return hfcontent;
	}

	public void setHfcontent(String _hfcontent) {
		firePropertyChange("hfcontent", hfcontent, _hfcontent);
		this.hfcontent = _hfcontent;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("sxxxid", "SXXXID");
		addField("personid", "PERSONID");
		addField("personname", "PERSONNAME");
		addField("bldate", "BLDATE");
		addField("ckstatus","CKSTATUS");
		addField("hfcontent","HFCONTENT");
		setTableName("FDT_OA_MATTERBLINFO");
		addKey("UUID");
	}

	public BpmMatterBLInfoDAO(Connection conn) {
		super(conn);
	}

	public BpmMatterBLInfoDAO() {
		super();
	}

}
