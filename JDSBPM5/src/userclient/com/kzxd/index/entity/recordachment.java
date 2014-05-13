package com.kzxd.index.entity;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

/**
 * 
 * @author tanrui
 * FDT_OA_RECORDACHMENT表对应的实体类
 * 
 */

public class recordachment extends DAO{
    	
	String uuid;       //UUID
	String createdate;    //上传时间
	String creatorid;     //上传人
	String creatorname;  //上传人姓名
	String filename;   //附件名称
	String filepath;   //附件路径
	String recorduuid;   //档案id
	String filetype;  //附件类型
	String categorytype;   //所属类别
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}
	
	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		createdate = _createdate;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String _creatorid) {
		firePropertyChange("creatorid", creatorid, _creatorid);
		creatorid = _creatorid;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String _creatorname) {
		firePropertyChange("creatorname", creatorname, _creatorname);
		creatorname = _creatorname;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String _filename) {
		firePropertyChange("filename", filename, _filename);
		filename = _filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String _filepath) {
		firePropertyChange("filepath", filepath, _filepath);
		filepath = _filepath;
	}

	public String getRecorduuid() {
		return recorduuid;
	}

	public void setRecorduuid(String _recorduuid) {
		firePropertyChange("recorduuid", recorduuid, _recorduuid);
		recorduuid = _recorduuid;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String _filetype) {
		firePropertyChange("filetype", filetype, _filetype);
		filetype = _filetype;
	}

	public String getCategorytype() {
		return categorytype;
	}

	public void setCategorytype(String _categorytype) {
		firePropertyChange("categorytype", categorytype, _categorytype);
		categorytype = _categorytype;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("createdate","createdate");
		addField("creatorid","creatorid");
		addField("creatorname","creatorname");
		addField("filename","filename");
		addField("filepath", "filepath");
		addField("recorduuid", "recorduuid");
		addField("filetype", "filetype");
		addField("categorytype", "categorytype");
		addKey("UUID");
		setTableName("FDT_OA_RECORDACHMENT");
	}

	public recordachment() {
		super();
	}

	public recordachment(Connection connection) {
		super(connection);
	}
	
}
