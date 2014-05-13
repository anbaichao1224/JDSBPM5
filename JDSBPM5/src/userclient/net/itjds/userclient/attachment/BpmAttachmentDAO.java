package net.itjds.userclient.attachment;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class BpmAttachmentDAO extends DAO {

	String uuid;

	String processinstid;

	String activityinstid;

	String filepath;

	String filename;

	String fileuploaduser;

	String fileuploaddate;

	Integer fileindex;

	String remark;

	String filetype;

	String formid;

	/**
	 * 新增加一列，判断是否
	 */
	Integer isToPdf;
	
	
	
	public Integer getIsToPdf() {
		return isToPdf;
	}

	public void setIsToPdf(Integer _isToPdf) {
		firePropertyChange("isToPdf", isToPdf, _isToPdf);
		isToPdf = _isToPdf;
	}

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

	public String getFileuploaduser() {
		return fileuploaduser;
	}

	public void setFileuploaduser(String _fileuploaduser) {
		firePropertyChange("fileuploaduser", fileuploaduser, _fileuploaduser);
		fileuploaduser = _fileuploaduser;
	}

	public String getFileuploaddate() {
		return fileuploaddate;
	}

	public void setFileuploaddate(String _fileuploaddate) {
		firePropertyChange("fileuploaddate", fileuploaddate, _fileuploaddate);
		fileuploaddate = _fileuploaddate;
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

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String _filetype) {
		firePropertyChange("filetype", filetype, _filetype);
		filetype = _filetype;
	}

	public String getFormid() {
		return formid;
	}

	public void setFormid(String _formid) {
		firePropertyChange("formid", formid, _formid);
		formid = _formid;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("processinstid", "PROCESSINSTID");
		addField("activityinstid", "ACTIVITYINSTID");
		addField("filepath", "FILEPATH");
		addField("filename", "FILENAME");
		addField("fileuploaduser", "FILEUPLOADUSER");
		addField("fileuploaddate", "FILEUPLOADDATE");
		addField("fileindex", "FILEINDEX");
		addField("remark", "REMARK");
		addField("filetype", "FILETYPE");
		addField("formid", "FORMID");
		addField("isToPdf","ISTOPDF");
		setTableName("BPM_ATTACHMENT");
		addKey("UUID");
	}

	public BpmAttachmentDAO(Connection conn) {
		super(conn);
	}

	public BpmAttachmentDAO() {
		super();
	}

}
