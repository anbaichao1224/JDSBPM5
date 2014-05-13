package com.kzxd.xzsp.util;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;


public class Acceptnode extends DAO {
	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private String handler;				
	private String handlerdate;			
	private String handleraddr;			
	private String handlerlist;			
	private String department;			
	private Permission permission;
	

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		this.uuid = _uuid;
	}
	public String getHandler() {
		return handler;
	}
	public void setHandler(String _handler) {
		firePropertyChange("handler",handler,_handler);
		this.handler = _handler;
	}
	public String getHandlerdate() {
		return handlerdate;
	}
	public void setHandlerdate(String _handlerdate) {
		firePropertyChange("handlerdate",handlerdate,_handlerdate);
		this.handlerdate = _handlerdate;
	}
	public String getHandleraddr() {
		return handleraddr;
	}
	public void setHandleraddr(String _handleraddr) {
		firePropertyChange("handleraddr",handleraddr,_handleraddr);
		this.handleraddr = _handleraddr;
	}
	public String getHandlerlist() {
		return handlerlist;
	}
	public void setHandlerlist(String _handlerlist) {
		firePropertyChange("handlerlist",handlerlist,_handlerlist);
		this.handlerlist = _handlerlist;
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
		addField("handler","HANDLER");
		addField("handlerdate","HANDLERDATE");
		addField("handleraddr","HANDLERADDR");
		addField("handlerlist","HANDLERLIST");
		addField("department","DEPARTMENT");
		addField("permission","PERMISSION");
		setTableName("XZSPJK_ACCEPTNODE");
		addKey("UUID");
	}
	
	public Acceptnode(){
		super();
	}
	
	public Acceptnode(Connection conn){
		super(conn);
	}
	
}
