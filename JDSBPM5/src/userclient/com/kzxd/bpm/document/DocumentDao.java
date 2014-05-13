package com.kzxd.bpm.document;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class DocumentDao extends DAO{
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
		this.uuid = _uuid;
	}
	public String getProcessinstid() {
		return processinstid;
	}
	public void setProcessinstid(String _processinstid) {
		firePropertyChange("processinstid", processinstid, _processinstid);
		this.processinstid = _processinstid;
	}
	public String getActivityinstid() {
		return activityinstid;
	}
	public void setActivityinstid(String _activityinstid) {
		firePropertyChange("activityinstid", activityinstid, _activityinstid);
		this.activityinstid = _activityinstid;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String _filepath) {
		firePropertyChange("filepath", filepath, _filepath);
		this.filepath = _filepath;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String _filename) {
		firePropertyChange("filename", filename, _filename);
		this.filename = _filename;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String _createuser) {
		firePropertyChange("createuser", createuser, _createuser);
		this.createuser = _createuser;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		this.createdate = _createdate;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String _filetype) {
		firePropertyChange("filetype", filetype, _filetype);
		this.filetype = _filetype;
	}
	public Integer getProcesstep() {
		return processtep;
	}
	public void setProcesstep(Integer _processtep) {
		firePropertyChange("processtep", processtep, _processtep);
		this.processtep = _processtep;
	}
	public Integer getFileindex() {
		return fileindex;
	}
	public void setFileindex(Integer _fileindex) {
		firePropertyChange("fileindex", fileindex, _fileindex);
		this.fileindex = _fileindex;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String _remark) {
		firePropertyChange("remark", remark, _remark);
		this.remark = _remark;
	}
	
	public String getDisplayname() {
		return displayname;
	}
	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}
	protected void setupFields() throws DAOException {
		
		addField("uuid", "UUID");
		addField("processinstid","processinstid");
		addField("activityinstid","activityinstid");
		addField("filepath", "filepath");
		addField("filename", "filename");
		addField("createuser","createuser");
		addField("createdate","createdate");		
		addField("filetype", "filetype");
		addField("processtep","processtep");
		addField("fileindex","fileindex");
		addField("remark", "remark");
		addField("displayname", "DISPLAYNAME");
		addKey("UUID");
		setTableName("BPM_DOCUMENT");
	}
	public DocumentDao() {
		super();
	}
	public DocumentDao(Connection connection) {
		super(connection);
	}

}
