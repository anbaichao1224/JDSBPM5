package net.itjds.userclient.comment;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class CommentsDAO extends DAO {

	String id;

	String userid;

	String text="Òâ¼û-ok";

	public String getId() {
		return id;
	}

	public void setId(String _id) {
		firePropertyChange("id", id, _id);
		id = _id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String _userid) {
		firePropertyChange("userid", userid, _userid);
		userid = _userid;
	}

	public String getText() {
		return text;
	}

	public void setText(String _text) {
		firePropertyChange("text", text, _text);
		text = _text;
	}

	protected void setupFields() throws DAOException {
		addField("id", "ID");
		addField("userid", "USERID");
		addField("text", "TEXT");
		setTableName("COMMENTS");
		addKey("ID");
	}

	public CommentsDAO(Connection conn) {
		super(conn);
	}

	public CommentsDAO() {
		super();
	}

}
