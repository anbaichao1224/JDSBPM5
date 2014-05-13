package kzxd.ttinfo.dao;

import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class WenZhongDAO extends DAO {

	private String uuid;
	private String wzname;
	private String wzename;
	private String creatorId;
	private String creator;
	private Date createdate;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		this.uuid = _uuid;
	}
	public String getWzname() {
		return wzname;
	}
	public void setWzname(String _wzname) {
		firePropertyChange("wzname",wzname,_wzname);
		this.wzname = _wzname;
	}
	public String getCreatorId() {
		return creatorId;
	}
	public void setCreatorId(String _creatorId) {
		firePropertyChange("creatorId",creatorId,_creatorId);
		this.creatorId = _creatorId;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String _creator) {
		firePropertyChange("creator",creator,_creator);
		this.creator = _creator;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date _createdate) {
		firePropertyChange("createdate",createdate,_createdate);
		this.createdate = _createdate;
	}
	
	public String getWzename() {
		return wzename;
	}
	public void setWzename(String _wzename) {
		firePropertyChange("wzename",wzename,_wzename);
		this.wzename = _wzename;
	}
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("wzname", "WZNAME");
		addField("wzename", "WZENAME");
		addField("creatorId", "CREATORID");
		addField("creator", "CREATOR");
		addField("createdate", "CREATEDATE");
		setTableName("KZXD_WENZHONG");
		addKey("UUID");
	}
	
}
