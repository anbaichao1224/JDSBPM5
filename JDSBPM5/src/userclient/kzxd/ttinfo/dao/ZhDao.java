package kzxd.ttinfo.dao;

import java.util.Date;

import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;

public class ZhDao extends DAO{
	     private String actid;
	     private int zihao;
	     private String wenzhong;
	     private Integer nh;
	     private String wenzhongid;
	     private String uuid;
	     private String bt;
	     private Date bhsj;
	     private String flag;
	     private String year;
	     private Integer bh;
		
		
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
	
		public Integer getNh() {
			return nh;
		}
		public void setNh(Integer nh) {
			firePropertyChange("nh",nh,nh);
			this.nh = nh;
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
		
	
		public Integer getBh() {
			return bh;
		}
		public void setBh(Integer bh) {
			firePropertyChange("bh",bh,bh);
			this.bh = bh;
		}
		protected void setupFields() throws DAOException {
			addField("uuid", "UUID");
			addField("actid", "ACTID");
			addField("zihao", "ZIHAO");
			addField("wenzhong", "WENZHONG");
			addField("year", "YEAR");
			addField("nh", "NH");
			addField("wenzhongid", "WENZHONGID");
			addField("bhsj", "BHSJ");
			addField("bt", "BT");
			addField("bh", "BH");
			addField("flag","Flag");
			setTableName("KZXD_ZIHAO");
			addKey("UUID");
		}
		
		public Date getBhsj() {
			return bhsj;
		}
		public void setBhsj(Date _bhsj) {
			firePropertyChange("bhsj",bhsj,_bhsj);
			this.bhsj = _bhsj;
		}
		public String getFlag() {
			return flag;
		}
		public void setFlag(String _flag) {
			firePropertyChange("flag",flag,_flag);
			this.flag = _flag;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			firePropertyChange("year",year,year);
			this.year = year;
		}
		public String getBt() {
			return bt;
		}
		public void setBt(String bt) {
			this.bt = bt;
		}
	     
	     
	}