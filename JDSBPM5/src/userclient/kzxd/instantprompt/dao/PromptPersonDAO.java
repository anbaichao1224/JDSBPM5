package kzxd.instantprompt.dao;

import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class PromptPersonDAO extends DAO {

	private String uuid;
	private String promptId;
	private String personId;
	private String personname;
	private Date enddate;
	private Integer iscancel;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	public String getPromptId() {
		return promptId;
	}
	public void setPromptId(String _promptId) {
		firePropertyChange("promptId", promptId, _promptId);
		this.promptId = _promptId;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String _personId) {
		firePropertyChange("personId", personId, _personId);
		this.personId = _personId;
	}
	public String getPersonname() {
		return personname;
	}
	public void setPersonname(String _personname) {
		firePropertyChange("personname", personname, _personname);
		this.personname = _personname;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date _enddate) {
		firePropertyChange("enddate", enddate, _enddate);
		this.enddate = _enddate;
	}
	
	public Integer getIscancel() {
		return iscancel;
	}
	public void setIscancel(Integer _iscancel) {
		firePropertyChange("iscancel", iscancel, _iscancel);
		this.iscancel = _iscancel;
	}
	@Override
	protected void setupFields() throws DAOException {
		addField("uuid","UUID");
		addField("promptId","PROMPTID");
		addField("personId","PERSONID");
		addField("personname","PERSONNAME");
		addField("enddate","ENDDATE");
		addField("iscancel","ISCANCEL");
		setTableName("KZXD_PROMPT_PERSON");
		addKey("UUID");
	}
	
	public PromptPersonDAO() {
		super();
	}
	
	
	
}
