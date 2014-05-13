package net.itjds.userclient.comment.dao;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class BpmCommentsDAO extends DAO {

	private String uuid;

	private String processinstid;

	private String activityinstid;

	private String formname;

	private String createuser;

	private String createdate;

	private String comments;

	private Integer personindex;
	
	private Integer groupindex;

	private String proxyperson;

	private String activitydefid;

	private String fieldname;
	
	private String position;

	
	public String getPosition() {
		return position;
	}

	public void setPosition(String _position) {
		firePropertyChange("position", position, _position);
		position = _position;
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

	public String getFormname() {
		return formname;
	}

	public void setFormname(String _formname) {
		firePropertyChange("formname", formname, _formname);
		formname = _formname;
	}

	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String _createuser) {
		firePropertyChange("createuser", createuser, _createuser);
		createuser = _createuser;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String _createdate) {
		firePropertyChange("createdate", createdate, _createdate);
		createdate = _createdate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String _comments) {
		firePropertyChange("comments", comments, _comments);
		comments = _comments;
	}

	public Integer getPersonindex() {
		return personindex;
	}

	public void setPersonindex(Integer _personindex) {
		firePropertyChange("personindex", personindex, _personindex);
		personindex = _personindex;
	}

	public String getProxyperson() {
		return proxyperson;
	}

	public void setProxyperson(String _proxyperson) {
		firePropertyChange("proxyperson", proxyperson, _proxyperson);
		proxyperson = _proxyperson;
	}

	public String getActivitydefid() {
		return activitydefid;
	}

	public void setActivitydefid(String _activitydefid) {
		firePropertyChange("activitydefid", activitydefid, _activitydefid);
		activitydefid = _activitydefid;
	}

	public String getFieldname() {
		return fieldname;
	}

	public void setFieldname(String _fieldname) {
		firePropertyChange("fieldname", fieldname, _fieldname);
		fieldname = _fieldname;
	}

	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("processinstid", "PROCESSINSTID");
		addField("activityinstid", "ACTIVITYINSTID");
		addField("formname", "FORMNAME");
		addField("createuser", "CREATEUSER");
		addField("createdate", "CREATEDATE");
		addField("comments", "COMMENTS");
		addField("personindex", "PERSONINDEX");
		addField("proxyperson", "PROXYPERSON");
		addField("activitydefid", "ACTIVITYDEFID");
		addField("fieldname", "FIELDNAME");
		addField("position", "POSITION");
		addField("groupindex", "GROUPINDEX");
		setTableName("BPM_COMMENTS");
		addKey("UUID");
		addUUID("UUID");
	}

	public BpmCommentsDAO(Connection conn) {
		super(conn);
	}

	public BpmCommentsDAO() {
		super();
	}

	public void setGroupindex(Integer _groupindex) {
		firePropertyChange("groupindex", groupindex, _groupindex);
		this.groupindex = _groupindex;
	}

	public Integer getGroupindex() {
		return groupindex;
	}

}
