package com.kzxd.xzsp.util;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;


public class Notifynode extends DAO {
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String nodeactor;			
	private String complementdate;		
	private String complementidea;		
	private String complementlist;		
	private String department;			
	private Permission permission;		
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		this.uuid = _uuid;
	}
	public String getNodeactor() {
		return nodeactor;
	}
	public void setNodeactor(String _nodeactor) {
		firePropertyChange("nodeactor",nodeactor,_nodeactor);
		this.nodeactor = _nodeactor;
	}
	public String getComplementdate() {
		return complementdate;
	}
	public void setComplementdate(String _complementdate) {
		firePropertyChange("complementdate",complementdate,_complementdate);
		this.complementdate = _complementdate;
	}
	public String getComplementidea() {
		return complementidea;
	}
	public void setComplementidea(String _complementidea) {
		firePropertyChange("complementidea",complementidea,_complementidea);
		this.complementidea = _complementidea;
	}
	public String getComplementlist() {
		return complementlist;
	}
	public void setComplementlist(String _complementlist) {
		firePropertyChange("complementlist",complementlist,_complementlist);
		this.complementlist = _complementlist;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String _department) {
		firePropertyChange("department",department,_department);
		this.department = _department;
	}

	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission _permission) {
		firePropertyChange("permission",permission,_permission);
		this.permission = _permission;
	}

	protected void setupFields() throws DAOException {
			
		addField("uuid", "UUID");
		addField("nodeactor","NADEACTOR");
		addField("complementdate","COMPLEMENTDATE");
		addField("complementidea","COMPLEMENTIDEA");
		addField("complementlist","COMPLEMENTLIST");
		addField("department","DEPARTMENT");
		addField("permission","PERMISSION");
		setTableName("XZSPJK_NOTIFYNODE");
		addKey("UUID");
	}
	
	public Notifynode(){
		super();
	}
	
	public Notifynode(Connection conn){
		super(conn);
	}
	
}
