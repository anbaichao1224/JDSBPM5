package net.itjds.userclient.document;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class BpmDocumentDAO extends DAO {

	String uuid;

	String processinstid;

	String activityinstid;

	String filepath;

	String filename;

	String createuser;

	String createdate;

	String filetype;

	Integer processtep;

	Integer fileindex;

	String remark;
	
	String displayname;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}

	public String getProcessinstid() {
		return processinstid;
	}

	public void setProcessinstid(String _processinstid) {
		firePropertyChange("processinstid", processinstid, _processinstid);
		processinstid = _processinstid;
	}

	public String getActivityinstid() {
		return activityinstid;
	}

	public void setActivityinstid(String _activityinstid) {
		firePropertyChange("activityinstid", activityinstid, _activityinstid);
		activityinstid = _activityinstid;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String _filepath) {
		firePropertyChange("filepath", filepath, _filepath);
		filepath = _filepath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String _filename) {
		firePropertyChange("filename", filename, _filename);
		filename = _filename;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String _createuser) {
		firePropertyChange("createuser", createuser, _createuser);
		createuser = _createuser;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		createdate = _createdate;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String _filetype) {
		firePropertyChange("filetype", filetype, _filetype);
		filetype = _filetype;
	}

	public Integer getProcesstep() {
		return processtep;
	}

	public void setProcesstep(Integer _processtep) {
		firePropertyChange("processtep", processtep, _processtep);
		processtep = _processtep;
	}

	public Integer getFileindex() {
		return fileindex;
	}

	public void setFileindex(Integer _fileindex) {
		firePropertyChange("fileindex", fileindex, _fileindex);
		fileindex = _fileindex;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String _remark) {
		firePropertyChange("remark", remark, _remark);
		remark = _remark;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String _displayname) {
		firePropertyChange("displayname", displayname, _displayname);
		this.displayname = _displayname;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("processinstid", "PROCESSINSTID");
		addField("activityinstid", "ACTIVITYINSTID");
		addField("filepath", "FILEPATH");
		addField("filename", "FILENAME");
		addField("createuser", "CREATEUSER");
		addField("createdate", "CREATEDATE");
		addField("filetype", "FILETYPE");
		addField("processtep", "PROCESSTEP");
		addField("fileindex", "FILEINDEX");
		addField("remark", "REMARK");
		addField("displayname", "DISPLAYNAME");
		setTableName("BPM_DOCUMENT");
		addKey("UUID");
	}

	public BpmDocumentDAO(Connection conn) {
		super(conn);
	}

	public BpmDocumentDAO() {
		super();
	}

}
