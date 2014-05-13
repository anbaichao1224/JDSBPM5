package com.kzxd.xzsp.util;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;


public class Permission extends DAO {
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String id;					
	private String name;				
	private String bsnum;				
	private String xmmc;				
	private String department;			
	private String status;		
	private String processinstid;
	private String flag;
	private Application1 application1;	
	
	public String getProcessinstid() {
		return processinstid;
	}
	public void setProcessinst_id(String _processinstid) {
		firePropertyChange("processinstid",processinstid,_processinstid);
		this.processinstid = _processinstid;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String _flag) {
		firePropertyChange("flag",flag,_flag);
		this.flag = _flag;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		this.uuid = _uuid;
	}
	public String getId() {
		return id;
	}
	public void setId(String _id) {
		firePropertyChange("id",id,_id);
		this.id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String _name) {
		firePropertyChange("name",name,_name);
		this.name = _name;
	}
	public String getBsnum() {
		return bsnum;
	}
	public void setBsnum(String _bsnum) {
		firePropertyChange("bsnum",bsnum,_bsnum);
		this.bsnum = _bsnum;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String _xmmc) {
		firePropertyChange("xmmc",xmmc,_xmmc);
		this.xmmc = _xmmc;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String _department) {
		firePropertyChange("department",department,_department);
		this.department = _department;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String _status) {
		firePropertyChange("status",status,_status);
		this.status = _status;
	}
	
	public Application1 getApplication1() {
		return application1;
	}
	public void setApplication1(Application1 application1) {
		this.application1 = application1;
	}
	protected void setupFields() throws DAOException {

		addField("uuid", "UUID");
		addField("id","ID");
		addField("name","NAME");
		addField("bsnum","BSNUM");
		addField("department","DEPARTMENT");
		addField("status","STATUS");
		addField("application1","APPLICATION1");
		setTableName("XZSPJK_PERMISSION");
		addKey("UUID");
	}
	
	public Permission(){
		super();
	}
	
	public Permission(Connection conn){
		super(conn);
	}
	
}
