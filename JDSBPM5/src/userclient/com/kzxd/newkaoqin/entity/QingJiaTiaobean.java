package com.kzxd.newkaoqin.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;


public class QingJiaTiaobean  {

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

		this.uuid = _uuid;
	}
	
	public String getActivityinstId() {
		return activityinstId;
	}
	public void setActivityinstId(String _activityinstId) {
		
		this.activityinstId = _activityinstId;
	}
	
	public String getProcessinstId() {
		return processinstId;
	}
	public void setProcessinstId(String _processinstId) {
		
		this.processinstId = _processinstId;
	}
	
	public String getCkmc() {
		return ckmc;
	}
	public void setCkmc(String _ckmc) {

		this.ckmc = _ckmc;
	}
	
	public Date getSqrq() {
		return sqrq;
	}
	public void setSqrq(Date _sqrq) {

		this.sqrq = _sqrq;
	}
	
	public String getXm() {
		return xm;
	}
	public void setXm(String _xm) {
	
		this.xm = _xm;
	}
	
	public String getQjlx() {
		return qjlx;
	}
	public void setQjlx(String _qjlx) {
		
		this.qjlx = _qjlx;
	}
	
	public Date getQjsjks() {
		return qjsjks;
	}
	public void setQjsjks(Date _qjsjks) {
		
		this.qjsjks = _qjsjks;
	}
	
	public Date getQjsjjs() {
		return qjsjjs;
	}
	public void setQjsjjs(Date _qjsjjs) {
		
		this.qjsjjs = _qjsjjs;
	}
	
	public String getGjt() {
		return gjt;
	}
	public void setGjt(String _gjt) {
		
		this.gjt = _gjt;
	}
	
	public String getSy() {
		return sy;
	}
	public void setSy(String _sy) {
		
		this.sy = _sy;
	}
	
	public String getCkfzryj() {
		return ckfzryj;
	}
	public void setCkfzryj(String _ckfzryj) {
		
		this.ckfzryj = _ckfzryj;
	}

	public String getXtdbkfzryj() {
		return xtdbkfzryj;
	}
	public void setXtdbkfzryj(String _xtdbkfzryj) {
		
		this.xtdbkfzryj = _xtdbkfzryj;
	}

	public String getZxldyj() {
		return zxldyj;
	}
	public void setZxldyj(String _zxldyj) {
	
		this.zxldyj = _zxldyj;
	}
	
	
}
