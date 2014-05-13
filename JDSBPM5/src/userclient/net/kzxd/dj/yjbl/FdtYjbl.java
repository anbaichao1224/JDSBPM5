package net.kzxd.dj.yjbl;

import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class FdtYjbl extends DAO {
	private String uuid;
	private String yjjjcd;
	private String yjmj;
	private String yjlwdw;
	private Date yjswrq;
	private String yjbh;
	private String yjbt;
	private String activityinstid;
	private String processinstid;

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("yjjjcd", "YJJJCD");
		addField("yjmj", "YJMJ");
		addField("yjlwdw", "YJLWDW");
		addField("yjswrq", "YJSWRQ");
		addField("yjbh", "YJBH");
		addField("yjbt", "YJBT");
		addField("activityinstid","ACTIVITYINST_ID");
		addField("processinstid","PROCESSINST_ID");
		setTableName("FDT_OA_YJBL");
		addKey("UUID");
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		firePropertyChange("uuid", this.uuid, uuid);
		this.uuid = uuid;
	}

	public String getYjjjcd() {
		return yjjjcd;
	}

	public void setYjjjcd(String yjjjcd) {
		firePropertyChange("yjjjcd", this.yjjjcd, yjjjcd);
		this.yjjjcd = yjjjcd;
	}

	public String getYjmj() {
		return yjmj;
	}

	public void setYjmj(String yjmj) {
		firePropertyChange("yjmj", this.yjmj, yjmj);
		this.yjmj = yjmj;
	}

	public String getYjlwdw() {
		return yjlwdw;
	}

	public void setYjlwdw(String yjlwdw) {
		firePropertyChange("yjlwdw", this.yjlwdw, yjlwdw);
		this.yjlwdw = yjlwdw;
	}

	public Date getYjswrq() {
		return yjswrq;
	}

	public void setYjswrq(Date yjswrq) {
		firePropertyChange("yjswrq", this.yjswrq, yjswrq);
		this.yjswrq = yjswrq;
	}

	public String getYjbh() {
		return yjbh;
	}

	public void setYjbh(String yjbh) {
		firePropertyChange("yjbh", this.yjbh, yjbh);
		this.yjbh = yjbh;
	}

	public String getYjbt() {
		return yjbt;
	}

	public void setYjbt(String yjbt) {
		firePropertyChange("yjbt", this.yjbt, yjbt);
		this.yjbt = yjbt;
	}

	public String getActivityinstid() {
		return activityinstid;
	}

	public void setActivityinstid(String activityinstid) {
		firePropertyChange("activityinstid", this.activityinstid, activityinstid);
		this.activityinstid = activityinstid;
	}

	public String getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(String processinstid) {
		firePropertyChange("processinstid", this.processinstid, processinstid);
		this.processinstid = processinstid;
	}

}
