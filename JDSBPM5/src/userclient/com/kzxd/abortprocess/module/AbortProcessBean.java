package com.kzxd.abortprocess.module;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "kzxd_abortprocess")
public class AbortProcessBean {
	
	private String uuid;
	private String title;
	private Date ngsj;
	private String sx;
	private String status;
	private String ngr;
	private String processname;
	private String processinstid;
	private String activityinstid;
	private Date arriveTime;
	private String lastNodePerson;
	private String abortname;
	private Date abortdate;
	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getNgsj() {
		return ngsj;
	}
	public void setNgsj(Date ngsj) {
		this.ngsj = ngsj;
	}
	public String getSx() {
		return sx;
	}
	public void setSx(String sx) {
		this.sx = sx;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNgr() {
		return ngr;
	}
	public void setNgr(String ngr) {
		this.ngr = ngr;
	}
	public String getProcessname() {
		return processname;
	}
	public void setProcessname(String processname) {
		this.processname = processname;
	}
	public String getProcessinstid() {
		return processinstid;
	}
	public void setProcessinstid(String processinstid) {
		this.processinstid = processinstid;
	}
	public String getActivityinstid() {
		return activityinstid;
	}
	public void setActivityinstid(String activityinstid) {
		this.activityinstid = activityinstid;
	}
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getLastNodePerson() {
		return lastNodePerson;
	}
	public void setLastNodePerson(String lastNodePerson) {
		this.lastNodePerson = lastNodePerson;
	}
	public String getAbortname() {
		return abortname;
	}
	public void setAbortname(String abortname) {
		this.abortname = abortname;
	}
	public Date getAbortdate() {
		return abortdate;
	}
	public void setAbortdate(Date abortdate) {
		this.abortdate = abortdate;
	}
	
	

}
