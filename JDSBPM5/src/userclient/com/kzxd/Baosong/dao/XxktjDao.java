package com.kzxd.Baosong.dao;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class XxktjDao extends DAO {

	private static final long serialVersionUID = 3281022572316305489L;
	
	String uuid;
	String uuid_bd;		//对应表单uuid
	String uuid_gz;     //对应规则uuid
	String yorn;	    //规则是否生效
//	String uuid_bm;     //对应部门uuid
//	int fenshu;	        //对应每条记录分数
//	Date kaishishijian; //对应每条表单的生成时间
	int yingyong;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	public String getUuid_bd() {
		return uuid_bd;
	}
	public void setUuid_bd(String _uuid_bd) {
		
		firePropertyChange("uuid_bd", uuid_bd, _uuid_bd);
		uuid_bd =_uuid_bd;
	}
	public String getUuid_gz() {
		return uuid_gz;
	}
	public void setUuid_gz(String _uuid_gz) {
		firePropertyChange("uuid_gz", uuid_gz, _uuid_gz);
		uuid_gz = _uuid_gz;
	}
	
	
	
//	public String getUuid_bm() {
//		return uuid_bm;
//	}
//	public void setUuid_bm(String _uuid_bm) {
//		this.uuid_bm = _uuid_bm;
//		firePropertyChange("uuid_bm",uuid_bm,_uuid_bm);
//	}
//	public int getFenshu() {
//		return fenshu;
//	}
//	public void setFenshu(int _fenshu) {
//		firePropertyChange("fenshu",fenshu,_fenshu);
//		this.fenshu = _fenshu;
//	}
//	public Date getKaishishijian() {
//		return kaishishijian;
//	}
//	public void setKaishishijian(Date _kaishishijian) {
//		firePropertyChange("kaishishijian",kaishishijian,_kaishishijian);
//		this.kaishishijian = _kaishishijian;
//	}
	
	
	
	public int getYingyong() {
		return yingyong;
	}
	public void setYingyong(int _yingyong) {
		firePropertyChange("yingyong",yingyong, _yingyong);
		this.yingyong = _yingyong;
	}
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("uuid_gz","UUID_GZ");
		addField("uuid_bd","UUID_BD");
		addField("yingyong","YINGYONG");
		setTableName("FDT_OA_XXKBSTJ");
		addKey("UUID");
	}
	
	public String getYorn() {
		return yorn;
	}
	public void setYorn(String _yorn) {
		firePropertyChange("yorn",yorn,_yorn);
		this.yorn = _yorn;
	}
	public XxktjDao(){
		super();
	}
	
	public XxktjDao(Connection conn){
		super(conn);
	}
	
}
