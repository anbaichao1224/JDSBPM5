package com.kzxd.index.entity;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class RollPepodom extends DAO {
	String uuid;
	String rollid;
	String rollname;
	String applicant;
	String applicantid;
	Date   applicantdate;
	String verifier;
	String verifierid;
	Date   verifierdate;
	int    ispass;
	Date   starttime;
	Date   endtime;
	String verifierremark;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	
	public String getRollid() {
		return rollid;
	}
	public void setRollid(String _rollid) {
		firePropertyChange("rollid", rollid, _rollid);
		this.rollid = _rollid;
	}
	
	public String getRollname() {
		return rollname;
	}
	public void setRollname(String _rollname) {
		firePropertyChange("rollname", rollname, _rollname);
		this.rollname = _rollname;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String _applicant) {
		firePropertyChange("applicant", applicant, _applicant);
		this.applicant = _applicant;
	}
	public String getApplicantid() {
		return applicantid;
	}
	public void setApplicantid(String _applicantid) {
		firePropertyChange("applicantid", applicantid, _applicantid);
		this.applicantid = _applicantid;
	}
	public Date getApplicantdate() {
		return applicantdate;
	}
	public void setApplicantdate(Date _applicantdate) {
		firePropertyChange("applicantdate", applicantdate, _applicantdate);
		this.applicantdate = _applicantdate;
	}
	public String getVerifier() {
		return verifier;
	}
	public void setVerifier(String _verifier) {
		firePropertyChange("verifier", verifier, _verifier);
		this.verifier = _verifier;
	}
	public String getVerifierid() {
		return verifierid;
	}
	public void setVerifierid(String _verifierid) {
		firePropertyChange("verifierid", verifierid, _verifierid);
		this.verifierid = _verifierid;
	}
	public Date getVerifierdate() {
		return verifierdate;
	}
	public void setVerifierdate(Date _verifierdate) {
		firePropertyChange("verifierdate", verifierdate, _verifierdate);
		this.verifierdate = _verifierdate;
	}
	public int getIspass() {
		return ispass;
	}
	public void setIspass(int _ispass) {
		firePropertyChange("ispass", ispass, _ispass);
		this.ispass = _ispass;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date _starttime) {
		firePropertyChange("starttime", starttime, _starttime);
		this.starttime = _starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date _endtime) {
		firePropertyChange("endtime", endtime, _endtime);
		this.endtime = _endtime;
	}
	public String getVerifierremark() {
		return verifierremark;
	}
	public void setVerifierremark(String _verifierremark) {
		firePropertyChange("verifierremark", verifierremark, _verifierremark);
		this.verifierremark = _verifierremark;
	}
	@Override
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("rollid", "ROLLID");
		addField("rollname", "ROLLNAME");
		addField("applicant", "APPLICANT");
		addField("applicantid", "APPLICANTID");
		addField("applicantdate", "APPLICANTDATE");
		addField("verifier", "VERIFIER");
		addField("verifierid", "VERIFIERID");
		addField("verifierdate", "VERIFIERDATE");
		addField("ispass", "ISPASS");
		addField("starttime", "STARTTIME");
		addField("endtime", "ENDTIME");
		addField("verifierremark", "VERIFIERREMARK");
		setTableName("FDT_OA_ROLLPEPODOM");
		addKey("UUID");
	}
	public RollPepodom() {
		super();
	}
	public RollPepodom(Connection connection) {
		super(connection);
	}
	
}
