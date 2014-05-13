package kzxd.electronicfile.file;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class BpmMAttachmentDAO extends DAO {

	String uuid;

	String createdate;
	
	String creatorid;

	String creatorname;
	
	String filepath;

	String filename;
	
	String recordid;

	String filetype;
	
	String categorytype;
	Integer isToPdf;
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
		this.createdate = _createdate;
	}

	public String getCreatorid() {
		return creatorid;
	}

	public void setCreatorid(String _creatorid) {
		firePropertyChange("creatorid", creatorid, _creatorid);
		this.creatorid = _creatorid;
	}

	public String getCreatorname() {
		return creatorname;
	}

	public void setCreatorname(String _creatorname) {
		firePropertyChange("creatorname", creatorname, _creatorname);
		this.creatorname = _creatorname;
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
	
	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String _recordid) {
		firePropertyChange("recordid", recordid, _recordid);
		this.recordid = _recordid;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String _filetype) {
		firePropertyChange("filetype", filetype, _filetype);
		this.filetype = _filetype;
	}
	
	public String getCategorytype() {
		return categorytype;
	}

	public void setCategorytype(String _categorytype) {
		firePropertyChange("categorytype", categorytype, _categorytype);
		this.categorytype = _categorytype;
	}
	
	
	public Integer getIsToPdf() {
		return isToPdf;
	}

	public void setIsToPdf(Integer _isToPdf) {
		firePropertyChange("isToPdf", isToPdf, _isToPdf);
		this.isToPdf = _isToPdf;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("createdate", "CREATEDATE");
		addField("creatorid", "CREATORID");
		addField("creatorname", "CREATORNAME");
		addField("filename", "FILENAME");
		addField("filepath", "FILEPATH");
		addField("recordid", "RECORDUUID");
		addField("filetype", "FILETYPE");
		addField("categorytype", "CATEGORYTYPE");
		addField("isToPdf", "ISTOPDF");
		setTableName("FDT_OA_RECORDACHMENT");
		addKey("UUID");
	}

	public BpmMAttachmentDAO(Connection conn) {
		super(conn);
	}

	public BpmMAttachmentDAO() {
		super();
	}

}
