package net.itjds.fdt.metting;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class BpmMettingDAO extends DAO {

	String uuid;

	String hymc;
	
	String hykssj;
	
	String hyjssj;
	
	String hylx;
	
	String createdate;
	
	String creatorId;
	
	String creator;
	
	String hyzt;
	
	String isdelete;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		uuid = _uuid;
	}

	public String getHymc() {
		return hymc;
	}

	public void setHymc(String _hymc) {
		firePropertyChange("hymc", hymc, _hymc);
		this.hymc = _hymc;
	}

	public String getHykssj() {
		return hykssj;
	}

	public void setHykssj(String _hykssj) {
		firePropertyChange("hykssj", hykssj, _hykssj);
		this.hykssj = _hykssj;
	}

	public String getHyjssj() {
		return hyjssj;
	}

	public void setHyjssj(String _hyjssj) {
		firePropertyChange("hyjssj", hyjssj, _hyjssj);
		this.hyjssj = _hyjssj;
	}

	public String getHylx() {
		return hylx;
	}

	public void setHylx(String _hylx) {
		firePropertyChange("hylx", hylx, _hylx);
		this.hylx = _hylx;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		this.createdate = _createdate;
	}

	
	
	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String _creatorId) {
		firePropertyChange("creatorId", creatorId, _creatorId);
		this.creatorId = _creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String _creator) {
		firePropertyChange("creator", creator, _creator);
		this.creator = _creator;
	}

	public String getHyzt() {
		return hyzt;
	}

	public void setHyzt(String _hyzt) {
		firePropertyChange("hyzt", hyzt, _hyzt);
		this.hyzt = _hyzt;
	}
	

	public String getIsdelete() {
		return isdelete;
	}

	public void setIsdelete(String _isdelete) {
		firePropertyChange("isdelete", isdelete, _isdelete);
		this.isdelete = _isdelete;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("hymc", "HYMC");
		addField("hykssj", "HYKSSJ");
		addField("hyjssj", "HYJSSJ");
		addField("hylx", "HYLX");
		addField("createdate", "CREATEDATE");
		addField("creatorId","CREATORID");
		addField("creator", "CREATOR");
		addField("hyzt", "HYZT");
		addField("isdelete","ISDELETE");
		setTableName("FDT_OA_METTING");
		addKey("UUID");
	}

	public BpmMettingDAO(Connection conn) {
		super(conn);
	}

	public BpmMettingDAO() {
		super();
	}

}
