package net.itjds.usm.persistence.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import net.itjds.usm.annotation.PropertyChinaName;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "RO_Personsecure")
public class Personsecure implements Serializable {

	@PropertyChinaName(cname = "����ԱID")
	private String adminid;

	@Column(length = 3000)
	public String getAdminid() {
		return adminid;
	}

	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}

	@PropertyChinaName(cname = "����ʱ��")
	private Timestamp createtime;

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@PropertyChinaName(cname = "��������ԱID")
	private String webmasterid;

	@Column(length = 3000)
	public String getWebmasterid() {
		return webmasterid;
	}

	public void setWebmasterid(String webmasterid) {
		this.webmasterid = webmasterid;
	}

	@PropertyChinaName(cname = "��ϵͳID")
	private String sysid;

	@Column(length = 3000)
	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	@PropertyChinaName(cname = "UUID")
	private String uuid;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
