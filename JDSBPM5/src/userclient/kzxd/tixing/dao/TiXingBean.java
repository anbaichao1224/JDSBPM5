package kzxd.tixing.dao;

import java.util.Date;

public class TiXingBean {

	private String uuid;
	private String personid;
	private String mkname;
	private String title;
	private Date time;
	private String delid;
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getPersonid() {
		return personid;
	}
	public void setPersonid(String personid) {
		this.personid = personid;
	}
	public String getMkname() {
		return mkname;
	}
	public void setMkname(String mkname) {
		this.mkname = mkname;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getDelid() {
		return delid;
	}
	public void setDelid(String delid) {
		this.delid = delid;
	}
	
	
}
