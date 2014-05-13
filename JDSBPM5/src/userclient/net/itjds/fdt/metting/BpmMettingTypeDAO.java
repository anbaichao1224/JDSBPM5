package net.itjds.fdt.metting;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.PageScrollParas;
import net.itjds.j2ee.util.SystemUtil;


public class BpmMettingTypeDAO extends DAO {

	String uuid;

	String createdate;
	
	String creatorid;
	
	String creator;
	
	String lxmc;
	
	String description;
	

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

	public String getCreator() {
		return creator;
	}

	public void setCreator(String _creator) {
		firePropertyChange("creator", creator, _creator);
		this.creator = _creator;
	}

	public String getLxmc() {
		return lxmc;
	}

	public void setLxmc(String _lxmc) {
		firePropertyChange("lxmc", lxmc, _lxmc);
		this.lxmc = _lxmc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String _description) {
		firePropertyChange("description", description, _description);
		this.description = _description;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("createdate", "CREATEDATE");
		addField("creatorid", "CREATORID");
		addField("creator", "CREATOR");
		addField("lxmc", "LXMC");
		addField("description","DESCRIPTION");
		setTableName("FDT_OA_METTINGTYPE");
		addKey("UUID");
	}
	public BpmMettingTypeDAO(Connection conn) {
		super(conn);
	}

	public BpmMettingTypeDAO() {
		super();
	}

}
