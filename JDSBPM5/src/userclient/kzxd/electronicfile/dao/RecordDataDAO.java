package kzxd.electronicfile.dao;

import java.sql.Clob;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class RecordDataDAO extends DAO {
	String uuid;
	String title;
	String content;
	String dkeyword;
	Date createdate;
	String personid;
	String personname;
	int status;
	String rollid;
	String senddept;
	String senddeptid;
	String wenhao;
	String fenshu;
	String recordindex;
	String jjcd;
	String miji;
	String nbyj;
	String cljg;
	String yzh;
	String lwdw;
	Date cwdate;
	String ldps;
	String ybqm;
	String activityInstId;
	String processDefId;
	String wjzluuid;
	int guidangid;
	
	
	public int getGuidangid() {
		return guidangid;
	}
	public void setGuidangid(int _guidangid) {
		firePropertyChange("guidangid", guidangid, _guidangid);
		this.guidangid = _guidangid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String _title) {
		firePropertyChange("title", title, _title);
		this.title = _title;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		this.createdate = _createdate;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		this.personid = _personid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRollid() {
		return rollid;
	}
	public void setRollid(String _rollid) {
		firePropertyChange("rollid", rollid, _rollid);
		this.rollid = _rollid;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String _content) {
		firePropertyChange("content", content, _content);
		this.content = _content;
	}
	
	public String getSenddept() {
		return senddept;
	}
	public void setSenddept(String _senddept) {
		firePropertyChange("senddept", senddept, _senddept);
		this.senddept = _senddept;
	}
	public String getSenddeptid() {
		return senddeptid;
	}
	public void setSenddeptid(String _senddeptid) {
		firePropertyChange("senddeptid", senddeptid, _senddeptid);
		this.senddeptid = _senddeptid;
	}
	
	public String getDkeyword() {
		return dkeyword;
	}
	public void setDkeyword(String _dkeyword) {
		firePropertyChange("dkeyword", dkeyword, _dkeyword);
		this.dkeyword = _dkeyword;
	}
	
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String _personname) {
		firePropertyChange("personname", personname, _personname);
		this.personname = _personname;
	}
	
	public String getWenhao() {
		return wenhao;
	}
	public void setWenhao(String _wenhao) {
		firePropertyChange("wenhao", wenhao, _wenhao);
		this.wenhao = _wenhao;
	}
	public String getFenshu() {
		return fenshu;
	}
	public void setFenshu(String _fenshu) {
		firePropertyChange("fenshu", fenshu, _fenshu);
		this.fenshu = _fenshu;
	}
	public String getRecordindex() {
		return recordindex;
	}
	public void setRecordindex(String _recordindex) {
		firePropertyChange("recordindex", recordindex, _recordindex);
		this.recordindex = _recordindex;
	}
	public String getJjcd() {
		return jjcd;
	}
	public void setJjcd(String _jjcd) {
		firePropertyChange("jjcd", jjcd, _jjcd);
		this.jjcd = _jjcd;
	}
	public String getMiji() {
		return miji;
	}
	public void setMiji(String _miji) {
		firePropertyChange("miji", miji, _miji);
		this.miji = _miji;
	}
	public String getNbyj() {
		return nbyj;
	}
	public void setNbyj(String _nbyj) {
		firePropertyChange("nbyj", nbyj, _nbyj);
		this.nbyj = _nbyj;
	}
	public String getCljg() {
		return cljg;
	}
	public void setCljg(String _cljg) {
		firePropertyChange("cljg", cljg, _cljg);
		this.cljg = _cljg;
	}
	public String getYzh() {
		return yzh;
	}
	public void setYzh(String _yzh) {
		firePropertyChange("yzh", yzh, _yzh);
		this.yzh = _yzh;
	}
	public String getActivityInstId() {
		return activityInstId;
	}
	
	public String getLwdw() {
		return lwdw;
	}
	public void setLwdw(String _lwdw) {
		firePropertyChange("lwdw", lwdw, _lwdw);
		this.lwdw = _lwdw;
	}
	public Date getCwdate() {
		return cwdate;
	}
	public void setCwdate(Date _cwdate) {
		firePropertyChange("cwdate", cwdate, _cwdate);
		this.cwdate = _cwdate;
	}
	
	public String getLdps() {
		return ldps;
	}
	public void setLdps(String _ldps) {
		firePropertyChange("ldps", ldps, _ldps);
		this.ldps = _ldps;
	}
	public String getYbqm() {
		return ybqm;
	}
	public void setYbqm(String _ybqm) {
		firePropertyChange("ybqm", ybqm, _ybqm);
		this.ybqm = _ybqm;
	}
	public void setActivityInstId(String _activityInstId) {
		firePropertyChange("activityInstId", activityInstId, _activityInstId);
		this.activityInstId = _activityInstId;
	}
	
	public String getProcessDefId() {
		return processDefId;
	}
	public void setProcessDefId(String _processDefId) {
		firePropertyChange("processDefId", processDefId, _processDefId);
		this.processDefId = _processDefId;
	}
	
	public String getWjzluuid() {
		return wjzluuid;
	}
	public void setWjzluuid(String _wjzluuid) {
		firePropertyChange("wjzluuid", wjzluuid, _wjzluuid);
		this.wjzluuid = _wjzluuid;
	}
	@Override
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("createdate", "CREATEDATE");
		addField("personid", "PERSONID");
		addField("title", "TITLE");
		addField("rollid", "ROLLID");
		addField("content", "CONTENT");
		addField("senddept", "SENDDEPT");
		addField("senddeptid", "SENDDEPTID");
		addField("dkeyword", "DKEYWORD");
		addField("personname", "PERSONNAME");
		addField("wenhao", "WENHAO");
		addField("fenshu", "FENSHU");
		addField("recordindex", "RECORDINDEX");
		addField("jjcd", "JJCD");
		addField("miji", "MIJI");
		addField("nbyj", "NBYJ");
		addField("cljg", "CLJG");
		addField("yzh", "YZH");
		addField("lwdw", "LWDW");
		addField("cwdate", "CWDATE");
		addField("ldps", "LDPS");
		addField("ybqm", "YBQM");
		addField("activityInstId", "ACTIVITYINST_ID");
		addField("processDefId", "PROCESSDEF_ID");
		addField("wjzluuid", "WJZLUUID");
		addField("guidangid","GUIDANGID");
		setTableName("FDT_OA_RECORDDATA");
		addKey("UUID");
	}
	public RecordDataDAO() {
		super();
	}
	
	
}
