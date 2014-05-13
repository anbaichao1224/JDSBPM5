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


public class BpmMettingTreeDAO extends DAO {

	String uuid;

	String createdate;
	
	String creatorid;
	
	String creator;
	
	String xxmc;
	
	String mtypeid;
	
	String parentid;
	
	String kssj;
	
	String jssj;
	
	String sxlx;
	
	String blrmc;
	
	String blrid;
	
	String blstatus;
	
	String sxnr;
	
	String isdelete;
	
	String activityinstid;
	
	String processinstid;
	
	String processdefid;
	
	String isopen;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		this.createdate = _createdate;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String _creatorid) {
		firePropertyChange("creatorid", creatorid, _creatorid);
		this.creatorid = _creatorid;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String _creator) {
		firePropertyChange("creator", creator, _creator);
		this.creator = _creator;
	}

	public String getXxmc() {
		return xxmc;
	}

	public void setXxmc(String _xxmc) {
		firePropertyChange("xxmc", xxmc, _xxmc);
		this.xxmc = _xxmc;
	}

	public String getMtypeid() {
		return mtypeid;
	}

	public void setMtypeid(String _mtypeid) {
		firePropertyChange("mtypeid", mtypeid, _mtypeid);
		this.mtypeid = _mtypeid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String _parentid) {
		firePropertyChange("parentid", parentid, _parentid);
		this.parentid = _parentid;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String _kssj) {
		firePropertyChange("kssj", kssj, _kssj);
		this.kssj = _kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String _jssj) {
		firePropertyChange("jssj", jssj, _jssj);
		this.jssj = _jssj;
	}

	public String getSxlx() {
		return sxlx;
	}

	public void setSxlx(String _sxlx) {
		firePropertyChange("sxlx", sxlx, _sxlx);
		this.sxlx = _sxlx;
	}

	public String getBlrmc() {
		return blrmc;
	}

	public void setBlrmc(String _blrmc) {
		firePropertyChange("blrmc", blrmc, _blrmc);
		this.blrmc = _blrmc;
	}

	public String getBlrid() {
		return blrid;
	}

	public void setBlrid(String _blrid) {
		firePropertyChange("blrid", blrid, _blrid);
		this.blrid = _blrid;
	}
	

	public String getBlstatus() {
		return blstatus;
	}

	public void setBlstatus(String _blstatus) {
		firePropertyChange("blstatus", blstatus, _blstatus);
		this.blstatus = _blstatus;
	}
	

	public String getSxnr() {
		return sxnr;
	}

	public void setSxnr(String _sxnr) {
		firePropertyChange("sxnr", sxnr, _sxnr);
		this.sxnr = _sxnr;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}

	public String getActivityinstid() {
		return activityinstid;
	}

	public void setActivityinstid(String _activityinstid) {
		firePropertyChange("activityinstid", activityinstid, _activityinstid);
		this.activityinstid = _activityinstid;
	}

	public String getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(String _processinstid) {
		firePropertyChange("processinstid", processinstid, _processinstid);
		this.processinstid = _processinstid;
	}

	public String getProcessdefid() {
		return processdefid;
	}

	public void setProcessdefid(String _processdefid) {
		firePropertyChange("processdefid", processdefid, _processdefid);
		this.processdefid = _processdefid;
	}
	
	public String getIsopen() {
		return isopen;
	}

	public void setIsopen(String _isopen) {
		firePropertyChange("isopen", isopen, _isopen);
		this.isopen = _isopen;
	}

	String mettingid;
	
	String mettingname;
	
	
	public String getMettingname() {
		return mettingname;
	}

	public void setMettingname(String mettingname) {
		this.mettingname = mettingname;
	}

	public String getMettingid() {
		return mettingid;
	}

	public void setMettingid(String _mettingid) {
		firePropertyChange("mettingid", mettingid, _mettingid);
		this.mettingid = _mettingid;
	}

	protected void setupFields() throws DAOException {
	}
	
	public BpmMettingTreeDAO(Connection conn) {
		super(conn);
	}

	public BpmMettingTreeDAO() {
		super();
	}

}
