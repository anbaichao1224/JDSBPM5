package com.kzxd.xzsp.util;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class Applynode extends DAO {
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String specialtype;			
	private String specialname;			
	private String specialstartdate;	
	private String specialuser;			
	private String specialusertel;		
	private String specialuserphone;	
	private String specialidea;			
	private String specialcontent;		
	private String speciallimit;		
	private String specialunit;			
	private String department;		
	private Permission permission;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		this.uuid = _uuid;
	}
	public String getSpecialtype() {
		return specialtype;
	}
	public void setSpecialtype(String _specialtype) {
		firePropertyChange("specialtype",specialtype,_specialtype);
		this.specialtype = _specialtype;
	}
	public String getSpecialname() {
		return specialname;
	}
	public void setSpecialname(String _specialname) {
		firePropertyChange("specialname",specialname,_specialname);
		this.specialname = _specialname;
	}
	public String getSpecialstartdate() {
		return specialstartdate;
	}
	public void setSpecialstartdate(String _specialstartdate) {
		firePropertyChange("specialstartdate",specialstartdate,_specialstartdate);
		this.specialstartdate = _specialstartdate;
	}
	public String getSpecialuser() {
		return specialuser;
	}
	public void setSpecialuser(String _specialuser) {
		firePropertyChange("specialuser",specialuser,_specialuser);
		this.specialuser = _specialuser;
	}
	public String getSpecialusertel() {
		return specialusertel;
	}
	public void setSpecialusertel(String _specialusertel) {
		firePropertyChange("specialusertel",specialusertel,_specialusertel);
		this.specialusertel = _specialusertel;
	}
	public String getSpecialuserphone() {
		return specialuserphone;
	}
	public void setSpecialuserphone(String _specialuserphone) {
		firePropertyChange("specialuserphone",specialuserphone,_specialuserphone);
		this.specialuserphone = _specialuserphone;
	}
	public String getSpecialidea() {
		return specialidea;
	}
	public void setSpecialidea(String _specialidea) {
		firePropertyChange("specialidea",specialidea,_specialidea);
		this.specialidea = _specialidea;
	}
	public String getSpecialcontent() {
		return specialcontent;
	}
	public void setSpecialcontent(String _specialcontent) {
		firePropertyChange("specialcontent",specialcontent,_specialcontent);
		this.specialcontent = _specialcontent;
	}
	public String getSpeciallimit() {
		return speciallimit;
	}
	public void setSpeciallimit(String _speciallimit) {
		firePropertyChange("speciallimit",speciallimit,_speciallimit);
		this.speciallimit = _speciallimit;
	}
	public String getSpecialunit() {
		return specialunit;
	}
	public void setSpecialunit(String _specialunit) {
		firePropertyChange("specialunit",specialunit,_specialunit);
		this.specialunit = _specialunit;
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
		addField("specialtype","SPECIALTYPE");
		addField("specialname","SPECIALNAME");
		addField("specialstartdate","SPECIALSTARTDATE");
		addField("specialusertel","SPECIALUSERTEL");
		addField("specialuserphone","SPECIALUSERPHONE");
		addField("specialidea","SPECIALISEA");
		addField("specialcontent","SPECIALCONTENT");
		addField("speciallimit","SPECIALLIMIT");
		addField("specialunit","SPECIALUNIT");
		addField("department","SPECIALMENT");
		addField("permission","PERMISSION");
		setTableName("XZSPJK_APPLYNODE");
		addKey("UUID");
	}
	
	public Applynode(){
		super();
	}
	
	public Applynode(Connection conn){
		super(conn);
	}
	
}
