package com.kzxd.Baosong.dao;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

/**
 * 信息科上报，上报之后，信息科应用的表单dao
 * @author admin
 *
 */
public class Xxkbsbdbh extends DAO{

	 
//	private static final long serialVersionUID = 4933383655453699739L;
	

	String uuid;
	String dw;
	Date sbsj;
	String djr;
	int zt;
	String dw_uuid;
	String bt;
	 
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		uuid = _uuid;
	}
	public String getDw() {
		return dw;
	}
	public void setDw(String _dw) {
		firePropertyChange("dw",dw,_dw);
		dw = _dw;
	}
	public Date getSbsj() {
		return sbsj;
	}
	public void setSbsj(Date _sbsj) {
		firePropertyChange("sbsj", sbsj, _sbsj);
		sbsj = _sbsj;
	}
	public String getDjr() {
		return djr;
	}
	public void setDjr(String _djr) {
		firePropertyChange("djr", djr, _djr);
		djr = _djr;
	}
	public int getZt() {
		return zt;
	}
	public void setZt(int _zt) {
		firePropertyChange("zt", zt, _zt);
		zt = _zt;
	}
	public String getDw_uuid() {
		return dw_uuid;
	}
	public void setDw_uuid(String _dw_uuid) {
		firePropertyChange("dw_uuid", dw_uuid, _dw_uuid);
		dw_uuid = _dw_uuid;
	}
	
	
	public String getBt() {
		return bt;
	}
	public void setBt(String _bt) {
		firePropertyChange("bt", bt, _bt);
		this.bt = _bt;
	}
	protected void setupFields() throws DAOException {
//		String uuid;
//		String dw;
//		Date sbsj;
//		String djr;
//		int zt;
//		String dw_uuid; fdt_oa_xxkbsdwbd
		addField("uuid", "UUID");
		addField("dw","DW");
		addField("sbsj","SBSJ");
		addField("djr","DJR");
		addField("zt","ZT");
		addField("bt","BT");
		addField("dw_uuid","DW_UUID");
		setTableName("FDT_OA_XXKBSBDBH");
		addKey("UUID");
	}//Xxkbsbdbh
	
	public Xxkbsbdbh(){
		super();
	}
	
	public Xxkbsbdbh(Connection conn){
		super(conn);
	}
	

}
