package kzxd.tixing.dao;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class TiXingDao extends DAO {

	private String uuid;
	private String personid;
	private String mkname;
	private String title;
	private Date time;
	private String delid;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String _personid) {
		firePropertyChange("personid", personid, _personid);
		this.personid = _personid;
	}
	public String getMkname() {
		return mkname;
	}
	public void setMkname(String _mkname) {
		firePropertyChange("mkname", mkname, _mkname);
		this.mkname = _mkname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String _title) {
		firePropertyChange("title", title, _title);
		this.title = _title;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date _time) {
		firePropertyChange("time", time, _time);
		this.time = _time;
	}
	public String getDelid() {
		return delid;
	}
	public void setDelid(String _delid) {
		firePropertyChange("delid", delid, _delid);
		this.delid = _delid;
	}
	
	protected  void setupFields()
		throws DAOException {
			addField("uuid","UUID");
			addField("personid","PERSONID");
			addField("mkname","MKNAME");
			addField("title","TITLE");
			addField("time","TIME");
			addField("delid","DELID");
			addKey("UUID");
			setTableName("KZXD_TIXING");
			
	}
	
	public TiXingDao() {
		super();
	}
	
	public TiXingDao(Connection connection) {
		super(connection);
	}
}
