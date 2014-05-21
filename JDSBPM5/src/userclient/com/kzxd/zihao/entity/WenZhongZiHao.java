package com.kzxd.zihao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import net.itjds.usm.annotation.PropertyChinaName;

@Entity
@Table(name = "kzxd_wenzhong_zihao")
public class WenZhongZiHao {

	@PropertyChinaName(cname = "UUID")
	private String uuid;
	@PropertyChinaName(cname = "文种")
	private String wenzhong;
	@PropertyChinaName(cname = "字号")
	private Integer zihao;
	@PropertyChinaName(cname = "已用")
	private String yiyong;
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

	public String getWenzhong() {
		return wenzhong;
	}

	public void setWenzhong(String wenzhong) {
		this.wenzhong = wenzhong;
	}

	

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 64)
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Integer getZihao() {
		return zihao;
	}

	public void setZihao(Integer zihao) {
		this.zihao = zihao;
	}

	public String getYiyong() {
		return yiyong;
	}

	public void setYiyong(String yiyong) {
		this.yiyong = yiyong;
	}

	public String getWenzhongid() {
		return wenzhongid;
	}

	public void setWenzhongid(String wenzhongid) {
		this.wenzhongid = wenzhongid;
	}
	
	
}
