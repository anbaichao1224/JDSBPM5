package com.kzxd.newkaoqin.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;


public class QingJiaTiaoDAO extends DAO {

	private String uuid;
	private String activityinstId;
	private String processinstId;
	private String ckmc;
	private Date sqrq;
	private String xm;
	private String qjlx;
	private Date qjsjks;
	private Date qjsjjs;
	private String gjt;
	private String sy;
	private String ckfzryj;
	private String xtdbkfzryj;
	private String zxldyj;
	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
	firePropertyChange("_uuid", uuid,_uuid);
		this.uuid = _uuid;
	}
	
	public String getActivityinstId() {
		return activityinstId;
	}
	public void setActivityinstId(String _activityinstId) {
		firePropertyChange("activityinstId", activityinstId,_activityinstId);
		this.activityinstId = _activityinstId;
	}
	
	public String getProcessinstId() {
		return processinstId;
	}
	public void setProcessinstId(String _processinstId) {
		firePropertyChange("processinstId", processinstId,_processinstId);
		this.processinstId = _processinstId;
	}
	
	public String getCkmc() {
		return ckmc;
	}
	public void setCkmc(String _ckmc) {
	firePropertyChange("ckmc", ckmc,_ckmc);
		this.ckmc = _ckmc;
	}
	
	public Date getSqrq() {
		return sqrq;
	}
	public void setSqrq(Date _sqrq) {
	firePropertyChange("sqrq", sqrq,_sqrq);
		this.sqrq = _sqrq;
	}
	
	public String getXm() {
		return xm;
	}
	public void setXm(String _xm) {
	  firePropertyChange("xm", xm,_xm);
		this.xm = _xm;
	}
	
	public String getQjlx() {
		return qjlx;
	}
	public void setQjlx(String _qjlx) {
		 firePropertyChange("qjlx", qjlx,_qjlx);
		this.qjlx = _qjlx;
	}
	
	public Date getQjsjks() {
		return qjsjks;
	}
	public void setQjsjks(Date _qjsjks) {
		firePropertyChange("qjsjks", _qjsjks,_qjsjks);
		this.qjsjks = _qjsjks;
	}
	
	public Date getQjsjjs() {
		return qjsjjs;
	}
	public void setQjsjjs(Date _qjsjjs) {
		firePropertyChange("qjsjjs", qjsjjs,_qjsjjs);
		this.qjsjjs = _qjsjjs;
	}
	
	public String getGjt() {
		return gjt;
	}
	public void setGjt(String _gjt) {
		firePropertyChange("gjt", gjt,_gjt);
		this.gjt = _gjt;
	}
	
	public String getSy() {
		return sy;
	}
	public void setSy(String _sy) {
		firePropertyChange("sy", sy,_sy);
		this.sy = _sy;
	}
	
	public String getCkfzryj() {
		return ckfzryj;
	}
	public void setCkfzryj(String _ckfzryj) {
		firePropertyChange("ckfzryj", ckfzryj,_ckfzryj);
		this.ckfzryj = _ckfzryj;
	}

	public String getXtdbkfzryj() {
		return xtdbkfzryj;
	}
	public void setXtdbkfzryj(String _xtdbkfzryj) {
		firePropertyChange("xtdbkfzryj", xtdbkfzryj,_xtdbkfzryj);
		this.xtdbkfzryj = _xtdbkfzryj;
	}

	public String getZxldyj() {
		return zxldyj;
	}
	public void setZxldyj(String _zxldyj) {
		firePropertyChange("zxldyj", zxldyj,_zxldyj);
		this.zxldyj = _zxldyj;
	}
	
	protected void setupFields() throws DAOException {
		
		addField("uuid", "UUID");
		addField("activityinstId","ACTIVITYINST_ID");
		addField("processinstId","PROCESSINST_ID");
		addField("ckmc","CKMC");
		addField("sqrq","SQRQ");
		addField("xm","XM");
		addField("qjlx","QJLX");
		addField("qjsjks","QJSJKS");
		addField("qjsjjs","QJSJJS");
		addField("gjt","GJT");
		addField("sy","SY");
		addField("ckfzryj","CKFZRYJ");
		addField("xtdbkfzryj","XTDBKFZRYJ");
		addField("zxldyj","ZXLDYJ");
		addKey("UUID");
		setTableName("FDT_OA_QJT");
	}
	public QingJiaTiaoDAO() {
		super();
	}

	public QingJiaTiaoDAO(Connection connection) {
		super(connection);
	}
	
}
