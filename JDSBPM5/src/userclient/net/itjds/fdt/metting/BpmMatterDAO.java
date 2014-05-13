package net.itjds.fdt.metting;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class BpmMatterDAO extends DAO {

	String uuid;

	String sxmc;
	
	String creator;
	
	String creatorId;
	
	String creatordate;
	
	String sxsslx;
	
	String sxssjd;
	

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}


	

	public String getSxmc() {
		return sxmc;
	}

	public void setSxmc(String _sxmc) {
		firePropertyChange("sxmc", sxmc, _sxmc);
		this.sxmc = _sxmc;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String _creator) {
		firePropertyChange("creator", creator, _creator);
		this.creator = _creator;
	}
	
	

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String _creatorId) {
		firePropertyChange("creatorId", creatorId, _creatorId);
		this.creatorId = _creatorId;
	}

	public String getCreatordate() {
		return creatordate;
	}

	public void setCreatordate(String _creatordate) {
		firePropertyChange("creatordate", creatordate, _creatordate);
		this.creatordate = _creatordate;
	}

	public String getSxsslx() {
		return sxsslx;
	}

	public void setSxsslx(String _sxsslx) {
		firePropertyChange("sxsslx", sxsslx, _sxsslx);
		this.sxsslx = _sxsslx;
	}

	public String getSxssjd() {
		return sxssjd;
	}

	public void setSxssjd(String _sxssjd) {
		firePropertyChange("sxssjd", sxssjd, _sxssjd);
		this.sxssjd = _sxssjd;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("sxmc", "SXMC");
		addField("creator", "CREATOR");
		addField("creatorId","CREATORID");
		addField("creatordate", "CREATEDATE");
		addField("sxsslx", "SXSSLX");
		addField("sxssjd", "SXSSJD");
		setTableName("FDT_OA_MATTER");
		addKey("UUID");
	}

	public BpmMatterDAO(Connection conn) {
		super(conn);
	}

	public BpmMatterDAO() {
		
	}

}
