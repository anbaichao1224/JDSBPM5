package kzxd.electronicfile.dao;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class RollDAO extends DAO {
	String uuid;
	
	String rollname;
	
	String rolldirectnum;
	
	String rollnum;
	
	String amanuensis;
	
	String miji;
	
	String timelimit;
	
	Date starttime;
	
	Date endtime;
	
	int pagenum;
	
	String savedirection;
	
	String yearnum;
	
	String creator;
	
	String creatorid;
	
	Date createdate;
	
	int status;
	
	String category_uuid;
	
	Date recordDate;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}

	public String getRollname() {
		return rollname;
	}

	public void setRollname(String _rollname) {
		firePropertyChange("rollname", rollname, _rollname);
		this.rollname = _rollname;
	}

	public String getRolldirectnum() {
		return rolldirectnum;
	}

	public void setRolldirectnum(String _rolldirectnum) {
		firePropertyChange("rolldirectnum", rolldirectnum, _rolldirectnum);
		this.rolldirectnum = _rolldirectnum;
	}

	public String getRollnum() {
		return rollnum;
	}

	public void setRollnum(String _rollnum) {
		firePropertyChange("rollnum", rollnum, _rollnum);
		this.rollnum = _rollnum;
	}

	public String getAmanuensis() {
		return amanuensis;
	}

	public void setAmanuensis(String _amanuensis) {
		firePropertyChange("amanuensis", amanuensis, _amanuensis);
		this.amanuensis = _amanuensis;
	}

	public String getMiji() {
		return miji;
	}

	public void setMiji(String _miji) {
		firePropertyChange("miji", miji, _miji);
		this.miji = _miji;
	}

	public String getTimelimit() {
		return timelimit;
	}

	public void setTimelimit(String _timelimit) {
		firePropertyChange("timelimit", timelimit, _timelimit);
		this.timelimit = _timelimit;
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

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int _pagenum) {
		firePropertyChange("pagenum", pagenum, _pagenum);
		this.pagenum = _pagenum;
	}

	public String getSavedirection() {
		return savedirection;
	}

	public void setSavedirection(String _savedirection) {
		firePropertyChange("savedirection", savedirection, _savedirection);
		this.savedirection = _savedirection;
	}

	public String getYearnum() {
		return yearnum;
	}

	public void setYearnum(String _yearnum) {
		firePropertyChange("yearnum", yearnum, _yearnum);
		this.yearnum = _yearnum;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String _creator) {
		firePropertyChange("creator", creator, _creator);
		this.creator = _creator;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String _creatorid) {
		firePropertyChange("creatorid", creatorid, _creatorid);
		this.creatorid = _creatorid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		this.createdate = _createdate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int _status) {
		firePropertyChange("status", status, _status);
		this.status = _status;
	}

	public String getCategory_uuid() {
		return category_uuid;
	}

	public void setCategory_uuid(String _creategoryUuid) {
		firePropertyChange("category_uuid", category_uuid, _creategoryUuid);
		this.category_uuid = _creategoryUuid;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date _recordDate) {
		firePropertyChange("recordDate", recordDate, _recordDate);
		this.recordDate = _recordDate;
	}
	
	
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("rollname", "ROLLNAME");
		addField("rolldirectnum", "ROLLDIRECTNUM");
		addField("rollnum", "ROLLNUM");
		addField("amanuensis", "AMANUENSIS");
		addField("miji", "MIJI");
		addField("timelimit", "TIMELIMIT");
		addField("starttime", "STARTTIME");
		addField("endtime", "ENDTIME");
		addField("pagenum", "PAGENUM");
		addField("savedirection", "SAVEDIRECTION");
		addField("yearnum", "YEARNUM");
		addField("creator", "CREATOR");
		addField("creatorid", "CREATORID");
		addField("createdate", "CREATEDATE");
		addField("status", "STATUS");
		addField("category_uuid", "CATEGORY_UUID");
		addField("recordDate", "RECORDDATE");
		setTableName("FDT_OA_ROLL");
		addKey("UUID");
	}
	public RollDAO() {
		super();
	}
	public RollDAO(Connection conn){
		super(conn);
	}
	
	
}
