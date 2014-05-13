package net.kzxd.dj.bean;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class BpmProcessinstance extends DAO{
	private String processinstid;
	private String processdefid;
	private String pvid;
	private String pstate;
	private long stime;
	private String runstate;

	protected void setupFields() throws DAOException {
		addField("processinstid", "PROCESSINST_ID");
		addField("processdefid", "PROCESSDEF_ID");
		addField("pvid", "PROCESSDEF_VERSION_ID");
		addField("pstate", "PROCESSINST_STATE");
		addField("stime", "STARTTIME");
		addField("runstate", "RUNSTATUS");
		setTableName("BPM_PROCESSINSTANCE");
		addKey("processinstid");
	}
	
	public String getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(String processinstid) {
		firePropertyChange("processinstid", this.processinstid, processinstid);
		this.processinstid = processinstid;
	}

	public String getProcessdefid() {
		return processdefid;
	}

	public void setProcessdefid(String processdefid) {
		firePropertyChange("processdefid", this.processdefid, processdefid);
		this.processdefid = processdefid;
	}

	public String getPvid() {
		return pvid;
	}

	public void setPvid(String pvid) {
		firePropertyChange("pvid", this.pvid, pvid);
		this.pvid = pvid;
	}

	public String getPstate() {
		return pstate;
	}

	public void setPstate(String pstate) {
		firePropertyChange("pstate", this.pstate, pstate);
		this.pstate = pstate;
	}

	public long getStime() {
		return stime;
	}

	public void setStime(long stime) {
		firePropertyChange("stime", this.stime, stime);
		this.stime = stime;
	}

	public String getRunstate() {
		return runstate;
	}

	public void setRunstate(String runstate) {
		firePropertyChange("runstate", this.runstate, runstate);
		this.runstate = runstate;
	}

}
