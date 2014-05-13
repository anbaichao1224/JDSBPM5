package com.kzxd.xzsp.util;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;


public class Node extends DAO {
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String nodeid;				
	private String nodename;			
	private String nodeactor;			
	private String nodeactorgh;			
	private String nodeactorzwmc;		
	private String nodeactorzwdm;		
	private String department;			
	private String handlerdate;			
	private String handleridea;			
	private Permission permission;	

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		this.uuid = _uuid;
	}	
	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String _nodeid) {
		firePropertyChange("nodeid",nodeid,_nodeid);
		this.nodeid = _nodeid;
	}
	public String getNodename() {
		return nodename;
	}
	public void setNodename(String _nodename) {
		firePropertyChange("nodename",nodename,_nodename);
		this.nodename = _nodename;
	}
	public String getNodeactor() {
		return nodeactor;
	}
	public void setNodeactor(String _nodeactor) {
		firePropertyChange("nodeactor",nodeactor,_nodeactor);
		this.nodeactor = _nodeactor;
	}
	public String getNodeactorgh() {
		return nodeactorgh;
	}
	public void setNodeactorgh(String _nodeactorgh) {
		firePropertyChange("nodeactorgh",nodeactorgh,_nodeactorgh);
		this.nodeactorgh = _nodeactorgh;
	}
	public String getNodeactorzwmc() {
		return nodeactorzwmc;
	}
	public void setNodeactorzwmc(String _nodeactorzwmc) {
		firePropertyChange("nodeactorzwmc",nodeactorzwmc,_nodeactorzwmc);
		this.nodeactorzwmc = _nodeactorzwmc;
	}
	public String getNodeactorzwdm() {
		return nodeactorzwdm;
	}
	public void setNodeactorzwdm(String _nodeactorzwdm) {
		firePropertyChange("nodeactorzwdm",nodeactorzwdm,_nodeactorzwdm);
		this.nodeactorzwdm = _nodeactorzwdm;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String _department) {
		firePropertyChange("department",department,_department);
		this.department = _department;
	}
	public String getHandlerdate() {
		return handlerdate;
	}
	public void setHandlerdate(String _handlerdate) {
		firePropertyChange("handlerdate",handlerdate,_handlerdate);
		this.handlerdate = _handlerdate;
	}
	public String getHandleridea() {
		return handleridea;
	}
	public void setHandleridea(String _handleridea) {
		firePropertyChange("handleridea",handleridea,_handleridea);
		this.handleridea = _handleridea;
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
		addField("nodeid","NODEID");
		addField("nodename","NODENAME");
		addField("nodeactor","NODEACTOR");
		addField("nodeactorgh","NODEACTORGH");
		addField("nodeactorzwmc","NODEACTORZWMC");
		addField("nodeactorzwdm","NODEACTORZWDM");
		addField("department","DEPARTMENT");
		addField("handlerdate","HANDLERDATE");
		addField("handleridea","HANDLERIDEA");
		addField("permission","PERMISSION");
		setTableName("XZSPJK_NODE");
		addKey("UUID");
	}
	
	public Node(){
		super();
	}
	
	public Node(Connection conn){
		super(conn);
	}
	
}
