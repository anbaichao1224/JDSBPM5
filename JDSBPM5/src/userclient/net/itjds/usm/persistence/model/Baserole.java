package net.itjds.usm.persistence.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "roletype", discriminatorType = DiscriminatorType.STRING)
@Table(name = "RO_Roletemp")
public class Baserole implements Serializable {

	private String uuid;

	private String roleid;

	private String moduleid;

	private String sysid;

	private String roleclass;

	private String adminname;

	private String roleacct;

	private String roletypeid;

	private String createtime;

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

	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getModuleid() {
		return moduleid;
	}

	public void setModuleid(String moduleid) {
		this.moduleid = moduleid;
	}

	public String getSysid() {
		return sysid;
	}

	public void setSysid(String sysid) {
		this.sysid = sysid;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	public String getRoleclass() {
		return roleclass;
	}

	public void setRoleclass(String roleclass) {
		this.roleclass = roleclass;
	}

	public String getAdminname() {
		return adminname;
	}

	public void setAdminname(String adminname) {
		this.adminname = adminname;
	}

	public String getRoleacct() {
		return roleacct;
	}

	public void setRoleacct(String roleacct) {
		this.roleacct = roleacct;
	}

	public String getRoletypeid() {
		return roletypeid;
	}

	public void setRoletypeid(String roletypeid) {
		this.roletypeid = roletypeid;
	}

}
