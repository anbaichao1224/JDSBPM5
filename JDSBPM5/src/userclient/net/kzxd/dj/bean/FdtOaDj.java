package net.kzxd.dj.bean;


import java.lang.reflect.Field;
import java.util.Date;



import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class FdtOaDj extends DAO{
	private String uuid;
	private String docbt;
	private Date rdate;
	private String department;
	private String classification;
	private String emergency;
	private String sn;
	private String attname;
	private String atturl;
	private String djdel;
	private String modeltype;
	private String xmlmodel;
	private String lwbh;
	private String djr;
	private String flag;
	private String jbluuid;
	private String gwjhinceptid;
	private Date cdate;
	private String  processinstid;
	private String ldbh;
	private String bmid;
	private String ryid;
	
	
	public String getRyid() {
		return ryid;
	}
	public void setRyid(String ryid) {
		firePropertyChange("ryid", this.ryid, ryid);
		this.ryid = ryid;
	}
	public String getBmid() {
		return bmid;
	}
	public void setBmid(String bmid) {
		firePropertyChange("bmid", this.bmid, bmid);
		this.bmid = bmid;
	}
	public String getLdbh() {
		return ldbh;
	}
	public void setLdbh(String ldbh) {
		firePropertyChange("ldbh", this.ldbh, ldbh);
		this.ldbh = ldbh;
	}
	public String getProcessinstid() {
		return processinstid;

	}
	public void setProcessinstid(String processinstid) {
		firePropertyChange("processinstid", this.processinstid, processinstid);
		this.processinstid = processinstid;
	}


	public Date getCdate() {
		return cdate;
	}


	public void setCdate(Date cdate) {
		firePropertyChange("cdate", this.cdate, cdate);
		this.cdate = cdate;
	}


	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("docbt", "DOCBT");
		addField("rdate", "RDATE");
		addField("department", "DEPARTMENT");
		addField("classification", "CLASSIFICATION");
		addField("emergency", "EMERGENCY");
		addField("sn", "SN");
		addField("attname","ATTNAME");
		addField("atturl","ATTURL");
		addField("djdel","DJDEL");
		addField("modeltype","MODELTYPE");
		addField("xmlmodel","XMLMODEL");
		addField("lwbh", "LWBH");
		addField("djr", "DJR");
		addField("flag","FLAG");
		addField("jbluuid","JBLUUID");
		addField("gwjhinceptid","GWJHINCEPTID");
		addField("cdate", "CDATE");
	    addField("processinstid","PROCESSINSTID");
	    addField("ldbh","LDBH");
	    addField("bmid","BMID");
	    addField("ryid","PERSONID");
		setTableName("FDT_OA_DJ");
		addKey("UUID");
	}


	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		firePropertyChange("uuid", this.uuid, uuid);
		this.uuid = uuid;
	}

	public String getDocbt() {
		return docbt;
	}

	public void setDocbt(String docbt) {
		firePropertyChange("docbt", this.docbt, docbt);
		this.docbt = docbt;
	}

	public Date getRdate() {
		return rdate;
	}

	public void setRdate(Date rdate) {
		firePropertyChange("rdate", this.rdate, rdate);
		this.rdate = rdate;
	}


	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		firePropertyChange("department", this.department, department);
		this.department = department;
	}

	public String getClassification() {
		return classification;
	}

	public void setClassification(String classification) {
		firePropertyChange("classification", this.classification, classification);
		this.classification = classification;
	}

	public String getEmergency() {
		return emergency;
	}

	public void setEmergency(String emergency) {
		firePropertyChange("emergency", this.emergency, emergency);
		this.emergency = emergency;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		firePropertyChange("sn", this.sn, sn);
		this.sn = sn;
	}

	public String getAttname() {
		return attname;
	}

	public void setAttname(String attname) {
		firePropertyChange("attname", this.attname, attname);
		this.attname = attname;
	}
	
	public String getAtturl() {
		return atturl;
	}


	public void setAtturl(String atturl) {
		firePropertyChange("atturl", this.atturl, atturl);
		this.atturl = atturl;
	}


	public String getDjdel() {
		return djdel;
	}


	public void setDjdel(String djdel) {
		firePropertyChange("djdel", this.djdel, djdel);
		this.djdel = djdel;
	}

	public String getModeltype() {
		return modeltype;
	}

	public void setModeltype(String modeltype) {
		firePropertyChange("modeltype", this.modeltype, modeltype);
		this.modeltype = modeltype;
	}

	public String getXmlmodel() {
		return xmlmodel;
	}

	public void setXmlmodel(String xmlmodel) {
		firePropertyChange("xmlmodel", this.xmlmodel, xmlmodel);
		this.xmlmodel = xmlmodel;
	}	
	public String getJbluuid() {
		return jbluuid;
	}


	public void setJbluuid(String jbluuid) {
		firePropertyChange("jbluuid", this.jbluuid, jbluuid);
		this.jbluuid = jbluuid;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		firePropertyChange("flag", this.flag, flag);
		this.flag = flag;
	}


	public String getLwbh() {
		return lwbh;
	}


	public void setLwbh(String lwbh) {
		firePropertyChange("lwbh", this.lwbh, lwbh);
		this.lwbh = lwbh;
	}


	public String getDjr() {
		return djr;
	}


	public void setDjr(String djr) {
		firePropertyChange("djr", this.djr, djr);
		this.djr = djr;
	}


	public String getGwjhinceptid() {
		return gwjhinceptid;
	}


	public void setGwjhinceptid(String gwjhinceptid) {
		firePropertyChange("gwjhinceptid", this.gwjhinceptid, gwjhinceptid);
		this.gwjhinceptid = gwjhinceptid;
	}
	
	
}
