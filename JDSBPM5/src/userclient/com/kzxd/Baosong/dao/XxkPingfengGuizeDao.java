package com.kzxd.Baosong.dao;

import java.sql.Connection;
import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class XxkPingfengGuizeDao extends DAO{
	/**
	 * 注视提示的版本号。若有错误可以删除
	 */
	private static final long serialVersionUID = -7836075572806870806L;
	String uuid;
	String name;
	String biaohao;
	int banbenhao;
	int fenshu;
	Date kaishishijian;
	Date jieshushijian;
	String jiancheng;
	
	String yingyong;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid", uuid, _uuid);
		this.uuid = _uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String _name) {
		firePropertyChange("name", name, _name);
		this.name = _name;
	}
	public String getBiaohao() {
		return biaohao;
	}
	public void setBiaohao(String _biaohao) {
		firePropertyChange("biaohao", biaohao, _biaohao);
		this.biaohao = _biaohao;
	}
	public int getBanbenhao() {
		return banbenhao;
	}
	public void setBanbenhao(int _banbenhao) {
		firePropertyChange("banbenhao", banbenhao, _banbenhao);
		this.banbenhao = _banbenhao;
	}
	public int getFenshu() {
		return fenshu;
	}
	public void setFenshu(int _fenshu) {
		firePropertyChange("fenshu", fenshu, _fenshu);
		this.fenshu = _fenshu;
	}
	public Date getKaishishijian() {
		return kaishishijian;
	}
	public void setKaishishijian(Date _kaishishijian) {
		firePropertyChange("kaishishijian", kaishishijian, _kaishishijian);
		this.kaishishijian = _kaishishijian;
	}
	public Date getJieshushijian() {
		return jieshushijian;
	}
	public void setJieshushijian(Date _jieshushijian) {
		firePropertyChange("jieshushijian", jieshushijian, _jieshushijian);
		this.jieshushijian = _jieshushijian;
	}
	
	
	
	public String getJiancheng() {
		return jiancheng;
	}
	public void setJiancheng(String _jiancheng) {
		firePropertyChange("jiancheng", jiancheng, _jiancheng);
		this.jiancheng = _jiancheng;
	}
	public String getYingyong() {
		return yingyong;
	}
	public void setYingyong(String _yingyong) {
		firePropertyChange("yingyong", yingyong, _yingyong);
		this.yingyong = _yingyong;
	}
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("name", "NAME");
		addField("biaohao", "BIAOHAO");
		addField("banbenhao","BANBENHAO");
		addField("fenshu","FENSHU");
		addField("kaishishijian","KAISHISHIJIAN");
		addField("jieshushijian","JIESHUSHIJIAN");
		addField("yingyong","YINGYONG");
		addField("jiancheng","JIANCHENG");
		setTableName("FDT_OA_XXKPFGZ");
		addKey("UUID");
	}
	
	public XxkPingfengGuizeDao(Connection conn){
		super(conn);
	}
	
	public XxkPingfengGuizeDao(){
		super();
	}
	
}
