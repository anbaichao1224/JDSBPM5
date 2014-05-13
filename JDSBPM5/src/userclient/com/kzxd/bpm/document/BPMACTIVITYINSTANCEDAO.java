package com.kzxd.bpm.document;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class BPMACTIVITYINSTANCEDAO extends DAO{
   private String ActivityinseId;
   private String ProcessinstId;
   private String ActivitydefId;
   private String ProcessdefId;
   private String ActivityinstState;
   private String Urgencytype;
   private Integer Arrivedtime;
   private Integer Limittime;
   private Integer Starttime;
   private String Receivemethod;
   private String Dealmethod;
   private String Rustatus;
   private String Cantakeback;
   private Integer Alertime;

   public String getActivityinseId() {
	return ActivityinseId;
}
public void setActivityinseId(String _activityinseId) {
	firePropertyChange("ActivityinseId", ActivityinseId, _activityinseId);
	ActivityinseId = _activityinseId;
}
public String getProcessinstId() {
	return ProcessinstId;
}
public void setProcessinstId(String _processinstId) {
	firePropertyChange("ProcessinstId", ProcessinstId, _processinstId);
	ProcessinstId = _processinstId;
}
public String getActivitydefId() {
	return ActivitydefId;
}
public void setActivitydefId(String _activitydefId) {
	firePropertyChange("ActivitydefId", ActivitydefId, _activitydefId);
	ActivitydefId = _activitydefId;
}
public String getProcessdefId() {
	return ProcessdefId;
}
public void setProcessdefId(String _processdefId) {
	firePropertyChange("ProcessdefId", ProcessdefId, _processdefId);
	ProcessdefId = _processdefId;
}
public String getActivityinstState() {
	return ActivityinstState;
}
public void setActivityinstState(String _activityinstState) {
	firePropertyChange("ActivityinstState", ActivityinstState, _activityinstState);
	ActivityinstState = _activityinstState;
}
public String getUrgencytype() {
	return Urgencytype;
}
public void setUrgencytype(String _urgencytype) {
	firePropertyChange("Urgencytype", Urgencytype, _urgencytype);
	Urgencytype = _urgencytype;
}
public String getReceivemethod() {
	return Receivemethod;
}
public void setReceivemethod(String _receivemethod) {
	firePropertyChange("Receivemethod", Receivemethod, _receivemethod);
	Receivemethod = _receivemethod;
}
public String getDealmethod() {
	return Dealmethod;
}
public void setDealmethod(String _dealmethod) {
	firePropertyChange("Dealmethod", Dealmethod, _dealmethod);
	Dealmethod = _dealmethod;
}
public String getRustatus() {
	return Rustatus;
}
public void setRustatus(String _rustatus) {
	firePropertyChange("Rustatus", Rustatus, _rustatus);
	Rustatus = _rustatus;
}
public String getCantakeback() {
	return Cantakeback;
}
public void setCantakeback(String _cantakeback) {
	firePropertyChange("Cantakeback", Cantakeback, _cantakeback);
	Cantakeback = _cantakeback;
}
public Integer getArrivedtime() {
	return Arrivedtime;
}
public void setArrivedtime(Integer _arrivedtime) {
	firePropertyChange("Arrivedtime", Arrivedtime, _arrivedtime);
	Arrivedtime = _arrivedtime;
}
public Integer getLimittime() {
	return Limittime;
}
public void setLimittime(Integer _limittime) {
	firePropertyChange("Limittime", Limittime, _limittime);
	Limittime = _limittime;
}
public Integer getStarttime() {
	return Starttime;
}
public void setStarttime(Integer _starttime) {
	firePropertyChange("Starttime", Starttime, _starttime);
	Starttime = _starttime;
}
public Integer getAlertime() {
	return Alertime;
}
public void setAlertime(Integer _alertime) {
	firePropertyChange("Alertime", Alertime, _alertime);
	Alertime = _alertime;
}
protected void setupFields() throws DAOException {
		
		addField("ActivityinseId", "ACTIVITYINST_ID");
		addField("ProcessinstId","PROCESSINST_ID");
		addField("ActivitydefId","ACTIVITYDEF_ID");
		addField("ProcessdefId", "PROCESSDEF_ID");
		addField("ActivityinstState", "ACTIVITYINST_STATE");
		addField("Urgencytype","URGENCYTYPE");
		addField("Arrivedtime","ARRIVEDTIME");		
		addField("Limittime", "LIMITTIME");
		addField("Starttime","STARTTIME");
		addField("Receivemethod","RECEIVEMETHOD");
		addField("Dealmethod", "DEALMETHOD");
		addField("Rustatus", "RUNSTATUS");
		addField("Cantakeback", "CANTAKEBACK");
		addField("Alertime", "ALERTTIME");
		addKey("ACTIVITYINST_ID");
		setTableName("BPM_ACTIVITYINSTANCE");
	}
	public BPMACTIVITYINSTANCEDAO() {
		super();
	}
	public BPMACTIVITYINSTANCEDAO(Connection connection) {
		super(connection);
	}
	

   
}
