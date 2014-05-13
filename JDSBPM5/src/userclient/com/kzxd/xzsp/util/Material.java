package com.kzxd.xzsp.util;

import java.sql.Connection;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;


public class Material extends DAO {
	private static final long serialVersionUID = 1L;

	private String uuid;
	private String id;					
	private String mlid;				
	private String mlname;				
	private String selecttype;			
	private String fid;					
	private String fpath;				
	private String fname;				
	private String status;				
	private String orinum;				
	private String copynum;				
	private String isneed;				
	private String baseinfo;			
	private String adjustment;			
	private Permission permission;
	
	public String getId() {
		return id;
	}
	public void setId(String _id) {
		firePropertyChange("id",id,_id);
		this.id = _id;
	}
	public String getMlid() {
		return mlid;
	}
	public void setMlid(String _mlid) {
		firePropertyChange("mlid",mlid,_mlid);
		this.mlid = _mlid;
	}
	public String getMlname() {
		return mlname;
	}
	public void setMlname(String _mlname) {
		firePropertyChange("mlname",mlname,_mlname);
		this.mlname = _mlname;
	}
	public String getSelecttype() {
		return selecttype;
	}
	public void setSelecttype(String _selecttype) {
		firePropertyChange("selecttype",selecttype,_selecttype);
		this.selecttype = _selecttype;
	}
	public String getFid() {
		return fid;
	}
	public void setFid(String _fid) {
		firePropertyChange("fid",fid,_fid);
		this.fid = _fid;
	}
	public String getFpath() {
		return fpath;
	}
	public void setFpath(String _fpath) {
		firePropertyChange("fpath",fpath,_fpath);
		this.fpath = _fpath;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String _fname) {
		firePropertyChange("fname",fname,_fname);
		this.fname = _fname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String _status) {
		firePropertyChange("status",status,_status);
		this.status = _status;
	}
	public String getOrinum() {
		return orinum;
	}
	public void setOrinum(String _orinum) {
		firePropertyChange("orinum",orinum,_orinum);
		this.orinum = _orinum;
	}
	public String getCopynum() {
		return copynum;
	}
	public void setCopynum(String _copynum) {
		firePropertyChange("copynum",copynum,_copynum);
		this.copynum = _copynum;
	}
	public String getIsneed() {
		return isneed;
	}
	public void setIsneed(String _isneed) {
		firePropertyChange("isneed",isneed,_isneed);
		this.isneed = _isneed;
	}
	public String getBaseinfo() {
		return baseinfo;
	}
	public void setBaseinfo(String _baseinfo) {
		firePropertyChange("baseinfo",baseinfo,_baseinfo);
		this.baseinfo = _baseinfo;
	}
	public String getAdjustment() {
		return adjustment;
	}
	public void setAdjustment(String _adjustment) {
		firePropertyChange("adjustment",adjustment,_adjustment);
		this.adjustment = _adjustment;
	}
	public Permission getPermission() {
		return permission;
	}
	public void setPermission(Permission _permission) {
		firePropertyChange("permission",permission,_permission);
		this.permission = _permission;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		this.uuid = _uuid;
	} 
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("id","ID");
		addField("mlid","MLID");
		addField("mlname","MLNAME");
		addField("fid","FID");
		addField("fpath","FPATH");
		addField("fname","FNAME");
		addField("orinum","ORINUM");
		addField("copynum","COPYNUM");
		addField("isneed","ISNEED");
		addField("adjustment","ADJUSYMENT");
		addField("baseinfo","BASEINFO");
		addField("permission","PERMISSION");
		setTableName("XZSPJK_MATERIAL");
		addKey("UUID");
	}
	
	public Material(){
		super();
	}
	
	public Material(Connection conn){
		super(conn);
	}
	
}
