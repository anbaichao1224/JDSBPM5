package com.kzxd.zihao.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import net.itjds.usm.annotation.PropertyChinaName;

@Entity
@Table(name = "kzxd_zihao")
public class ZiHao {
	@PropertyChinaName(cname = "UUID")
	private String uuid;
	@PropertyChinaName(cname = "字号")
	private Integer zihao;
	@PropertyChinaName(cname = "流程实例id")
	private String actid;
	@PropertyChinaName(cname = "文种")
	private String wenzhong;
	@PropertyChinaName(cname = "年号")
	private String year;
	@PropertyChinaName(cname = "文种id")
	private String wenzhongid;
	
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 64)
	public String getUuid() {
		return uuid;
	}
	
	

	public Integer getZihao() {
		return zihao;
	}


	public void setZihao(Integer zihao) {
		this.zihao = zihao;
	}


	public String getActid() {
		return actid;
	}


	public void setActid(String actid) {
		this.actid = actid;
	}


	public String getWenzhong() {
		return wenzhong;
	}


	public void setWenzhong(String wenzhong) {
		this.wenzhong = wenzhong;
	}


	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getWenzhongid() {
		return wenzhongid;
	}

	public void setWenzhongid(String wenzhongid) {
		this.wenzhongid = wenzhongid;
	}
	
	
}
