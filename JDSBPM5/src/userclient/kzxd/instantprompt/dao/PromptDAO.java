package kzxd.instantprompt.dao;

import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class PromptDAO extends DAO {

	private String uuid;
	private String title;
	private String content;
	private String creator;
	private String creatorid;
	private Date createdate;
	private Date begindate;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String _title) {
		firePropertyChange("title", title, _title);
		this.title = _title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String _content) {
		firePropertyChange("content", content, _content);
		this.content = _content;
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
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date _begindate) {
		firePropertyChange("begindate", begindate, _begindate);
		this.begindate = _begindate;
	}
	
	@Override
	protected  void setupFields()
			throws DAOException {
			addField("uuid","UUID");
			addField("title","TITLE");
			addField("content","CONTENT");
			addField("creator","CREATOR");
			addField("creatorid","CREATORID");
			addField("createdate","CREATEDATE");
			addField("begindate","BEGINDATE");
			setTableName("KZXD_PROMPT");
			addKey("UUID");
	}
	
	public PromptDAO() {
		super();
	}
}
