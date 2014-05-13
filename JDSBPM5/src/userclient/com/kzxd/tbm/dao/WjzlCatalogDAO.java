package com.kzxd.tbm.dao;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class WjzlCatalogDAO extends DAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String catalogName;
	private String catalogDesc;
	private Integer isRoot;
	private String parentId;
	private String catalogOrgId;
	private String catalogOrgName;
	private Integer catalogOrder;
	private Date createDate;
	private String personid;
	private String bz;
	private String duty;
	private String phone;
	

	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.firePropertyChange("bz", this.bz, bz);
		this.bz = bz;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.firePropertyChange("duty", this.duty, duty);
		this.duty = duty;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.firePropertyChange("phone", this.phone, phone);
		this.phone = phone;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public WjzlCatalogDAO(){
		super();
	}
	
	public WjzlCatalogDAO(Connection connect){
		super(connect);
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.firePropertyChange("uuid", this.uuid, uuid);
		this.uuid = uuid;
	}
	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.firePropertyChange("personid", this.personid, personid);
		this.personid = personid;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public void setCatalogName(String catalogName) {
		this.firePropertyChange("catalogName", this.catalogName, catalogName);
		this.catalogName = catalogName;
	}

	public String getCatalogDesc() {
		return catalogDesc;
	}

	public void setCatalogDesc(String catalogDesc) {
		this.firePropertyChange("catalogDesc", this.catalogDesc, catalogDesc);
		this.catalogDesc = catalogDesc;
	}

	public Integer getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Integer isRoot) {
		this.firePropertyChange("isRoot", this.isRoot, isRoot);
		this.isRoot = isRoot;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.firePropertyChange("parentId", this.parentId, parentId);
		this.parentId = parentId;
	}

	public String getCatalogOrgId() {
		return catalogOrgId;
	}

	public void setCatalogOrgId(String catalogOrgId) {
		this.firePropertyChange("catalogOrgId", this.catalogOrgId, catalogOrgId);
		this.catalogOrgId = catalogOrgId;
	}

	public String getCatalogOrgName() {
		return catalogOrgName;
	}

	public void setCatalogOrgName(String catalogOrgName) {
		this.firePropertyChange("catalogOrgName", this.catalogOrgName, catalogOrgName);
		this.catalogOrgName = catalogOrgName;
	}

	public Integer getCatalogOrder() {
		return catalogOrder;
	}

	public void setCatalogOrder(Integer catalogOrder) {
		this.firePropertyChange("catalogOrder", this.catalogOrder, catalogOrder);
		this.catalogOrder = catalogOrder;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.firePropertyChange("createDate", this.createDate, createDate);
		this.createDate = createDate;
	}

	/**
	 * 	private String uuid;
	private String catalogName;
	private String catalogDesc;
	private Integer isRoot;
	private String parentId;
	private String catalogOrgId;
	private String catalogOrgName;
	private Integer catalogOrder;
	private Date createDate;
	 */
	@Override
	protected void setupFields() throws DAOException {
		// TODO Auto-generated method stub
		this.addField("uuid", "UUID");
		this.addField("catalogName", "CATALOG_NAME");
		this.addField("catalogDesc", "CATALOG_DESC");
		this.addField("isRoot", "IS_ROOT");
		this.addField("parentId", "PARENT_ID");
		this.addField("catalogOrgId", "CATALOG_ORG_ID");
		this.addField("catalogOrgName", "CATALOG_ORG_NAME");
		this.addField("catalogOrder", "CATALOG_ORDER");
		this.addField("createDate", "CREATE_DATE");
		this.addField("personid", "PERSONID");
		this.addField("bz", "BZ");
		this.addField("duty", "DUTY");
		this.addField("phone", "PHONE");
		this.addKey("UUID");
		this.setTableName("TBL_BBDEPT");
	}
}
