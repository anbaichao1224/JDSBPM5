package com.kzxd.xzsp.util;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;


public class Handlenode extends DAO {
	private static final long serialVersionUID = 1L;

	private String uuid; 
	private String specialresult;		
	private String specialresultdate;	
	private String specialenddate;		
	private String specialpay;			
	private String department;			
	private Permission permission;	
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		this.uuid = _uuid;
	}
	public String getSpecialresult() {
		return specialresult;
	}
	public void setSpecialresult(String _specialresult) {
		firePropertyChange("specialresult",specialresult,_specialresult);
		this.specialresult = _specialresult;
	}
	public String getSpecialresultdate() {
		return specialresultdate;
	}
	public void setSpecialresultdate(String _specialresultdate) {
		firePropertyChange("specialresultdate",specialresultdate,_specialresultdate);
		this.specialresultdate = _specialresultdate;
	}
	public String getSpecialenddate() {
		return specialenddate;
	}
	public void setSpecialenddate(String _specialenddate) {
		firePropertyChange("specialenddate",specialenddate,_specialenddate);
		this.specialenddate = _specialenddate;
	}
	public String getSpecialpay() {
		return specialpay;
	}
	public void setSpecialpay(String _specialpay) {
		firePropertyChange("specialpay",specialpay,_specialpay);
		this.specialpay = _specialpay;
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
		addField("specialresult","SPECIALRESULT");
		addField("specialresultdate","SPECIALRESULTDATE");
		addField("specialenddate","SPECIALENDDATE");
		addField("specialpay","SPECIALPAY");
		addField("department","SPECIALMENT");
		addField("permission","PERMISSION");
		setTableName("XZSPJK_HANDLENODE");
		addKey("UUID");
	}
	
	public Handlenode(){
		super();
	}
	
	public Handlenode(Connection conn){
		super(conn);
	}
	
}
