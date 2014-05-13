package kzxd.ttinfo.dao;

import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class ZiHaoDAO extends DAO {
     private String actid;
     private int zihao;
     private String wenzhong;
     private String year;
     private String wenzhongid;
     private String uuid;
     private String name;
     private Date createdate;
     private String zh;
	
	
	public String getActid() {
		return actid;
	}
	public void setActid(String _actid) {
		firePropertyChange("actid",actid,_actid);
		this.actid = _actid;
	}
	public int getZihao() {
		return zihao;
	}
	public void setZihao(int _zihao) {
		firePropertyChange("zihao",zihao,_zihao);
		this.zihao = _zihao;
	}
	public String getWenzhong() {
		return wenzhong;
	}
	public void setWenzhong(String _wenzhong) {
		firePropertyChange("wenzhong",wenzhong,_wenzhong);
		this.wenzhong = _wenzhong;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String _year) {
		firePropertyChange("year",year,_year);
		this.year = _year;
	}
	public String getWenzhongid() {
		return wenzhongid;
	}
	public void setWenzhongid(String _wenzhongid) {
		firePropertyChange("wenzhongid",wenzhongid,_wenzhongid);
		this.wenzhongid = _wenzhongid;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String _uuid) {
		firePropertyChange("uuid",uuid,_uuid);
		this.uuid = _uuid;
	}
	
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date _createdate) {
		firePropertyChange("createdate",createdate,_createdate);
		this.createdate = _createdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String _name) {
		firePropertyChange("name",name,_name);
		this.name =_name;
	}
	public String getZh() {
		return zh;
	}
	public void setZh(String _zh) {
		firePropertyChange("zh",zh,_zh);
		this.zh =_zh;
	}
     
	protected void setupFields() throws DAOException {
		addField("uuid", "UUID");
		addField("actid", "ACTID");
		addField("zihao", "ZIHAO");
		addField("wenzhong", "WENZHONG");
		addField("year", "YEAR");
		addField("wenzhongid", "WENZHONGID");
		addField("createdate", "CREATEDATE");
		addField("name", "NAME");
		addField("zh", "ZH");
		setTableName("KZXD_ZIHAO");
		addKey("UUID");
	}
     
     
}
