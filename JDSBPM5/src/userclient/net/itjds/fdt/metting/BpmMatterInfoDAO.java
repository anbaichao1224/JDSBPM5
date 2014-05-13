package net.itjds.fdt.metting;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class BpmMatterInfoDAO extends DAO {

	String uuid;

	String sxmc;
	
	String creatorId;
	
	String creator;
	
	String creatordate;
	
	String sxkssj;
	
	String sxjssj;
	
	String sxnr;
	
	String sxblqk;
	
	String sxshyj;
	
	String sxlx;
	
	String sxhyid;
	
	String sxssjd;
	
	String blstatus;
	
	String isdelete;
	
	String activityinstid;
	
	String processinstid;
	
	String processdefid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}


	

	public String getSxmc() {
		return sxmc;
	}

	public void setSxmc(String _sxmc) {
		firePropertyChange("sxmc", sxmc, _sxmc);
		this.sxmc = _sxmc;
	}

	
	
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String _creatorId) {
		firePropertyChange("creatorId", creatorId, _creatorId);
		this.creatorId = _creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String _creator) {
		firePropertyChange("creator", creator, _creator);
		this.creator = _creator;
	}

	public String getCreatordate() {
		return creatordate;
	}

	public void setCreatordate(String _creatordate) {
		firePropertyChange("creatordate", creatordate, _creatordate);
		this.creatordate = _creatordate;
	}

	

	public String getSxkssj() {
		return sxkssj;
	}

	public void setSxkssj(String _sxkssj) {
		firePropertyChange("sxmc", sxmc, _sxkssj);
		this.sxkssj = _sxkssj;
	}

	public String getSxjssj() {
		return sxjssj;
	}

	public void setSxjssj(String _sxjssj) {
		firePropertyChange("sxjssj", sxjssj, _sxjssj);
		this.sxjssj = _sxjssj;
	}

	public String getSxnr() {
		return sxnr;
	}

	public void setSxnr(String _sxnr) {
		firePropertyChange("sxnr", sxnr, _sxnr);
		this.sxnr = _sxnr;
	}

	public String getSxblqk() {
		return sxblqk;
	}

	public void setSxblqk(String _sxblqk) {
		firePropertyChange("sxblqk", sxblqk, _sxblqk);
		this.sxblqk = _sxblqk;
	}

	public String getSxshyj() {
		return sxshyj;
	}

	public void setSxshyj(String _sxshyj) {
		firePropertyChange("sxshyj", sxshyj, _sxshyj);
		this.sxshyj = _sxshyj;
	}

	public String getSxlx() {
		return sxlx;
	}

	public void setSxlx(String _sxlx) {
		firePropertyChange("sxlx", sxlx, _sxlx);
		this.sxlx = _sxlx;
	}

	public String getSxhyid() {
		return sxhyid;
	}

	public void setSxhyid(String _sxhyid) {
		firePropertyChange("sxhyid", sxhyid, _sxhyid);
		this.sxhyid = _sxhyid;
	}
	

	public String getBlstatus() {
		return blstatus;
	}

	public void setBlstatus(String _blstatus) {
		firePropertyChange("blstatus", blstatus, _blstatus);
		this.blstatus = _blstatus;
	}

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String _isdelete) {
		firePropertyChange("isdelete", isdelete, _isdelete);
		this.isdelete = _isdelete;
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

	public String getSxssjd() {
		return sxssjd;
	}

	public void setSxssjd(String _sxssjd) {
		firePropertyChange("sxssjd", sxssjd, _sxssjd);
		this.sxssjd = _sxssjd;
	}
	
	

	public String getProcessdefid() {
		return processdefid;
	}

	public void setProcessdefid(String _processdefid) {
		firePropertyChange("processdefid", processdefid, _processdefid);
		this.processdefid = _processdefid;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("sxmc", "SXMC");
		addField("creatorId","CREATORID");
		addField("creator", "CREATOR");
		addField("creatordate", "CREATEDATE");
		addField("sxkssj", "SXKSSJ");
		addField("sxjssj", "SXJSSJ");
		addField("sxnr", "SXNR");
		addField("sxblqk", "SXBLQK");
		addField("sxshyj", "SXSHYJ");
		addField("sxlx", "SXLX");
		addField("sxhyid", "SXHYID");
		addField("sxssjd", "SXHYJD");
		addField("blstatus","BLSTATUS");
		addField("isdelete","ISDELETE");
		addField("activityinstid", "ACTIVITYINST_ID");
		addField("processinstid", "PROCESSINST_ID");
		addField("processdefid","PROCESSDEF_ID");
		setTableName("FDT_OA_MATTERINFO");
		addKey("UUID");
	}

	public BpmMatterInfoDAO(Connection conn) {
		super(conn);
	}

	public BpmMatterInfoDAO() {
		
	}

}
