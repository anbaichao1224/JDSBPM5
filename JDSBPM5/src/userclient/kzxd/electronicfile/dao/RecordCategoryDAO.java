package kzxd.electronicfile.dao;

import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class RecordCategoryDAO extends DAO {
	String uuid;
	
	String categoryname;
	
	String parentid;
	
	String creator;
	
	String creatorid;
	
	Date createdate;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String _categoryname) {
		firePropertyChange("categoryname", categoryname, _categoryname);
		this.categoryname = _categoryname;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String _parentid) {
		firePropertyChange("parentid", parentid, _parentid);
		this.parentid = _parentid;
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
	
	@Override
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("categoryname", "CATEGORY_NAME");
		addField("parentid", "PARENT_ID");
		addField("creator", "CREATOR");
		addField("creatorid", "CREATORID");
		addField("createdate", "CREATEDATE");
		setTableName("FDT_OA_RECORDCATEGORY");
		addKey("UUID");
	}
	
	public RecordCategoryDAO() {
		super();
	}
	
	
	
}
