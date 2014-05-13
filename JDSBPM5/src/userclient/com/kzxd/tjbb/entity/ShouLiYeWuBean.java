package com.kzxd.tjbb.entity;

public class ShouLiYeWuBean {

	private Integer sjs;
	private Integer nss;
	private Float nssbl;		//年审数比例
	private Integer bjs;
	private Integer bysbbjs;  //本月的上报办结数
	private Integer fbysbbjs; //非本月上报办结数
	private Float bjl;
	private Integer zxj;
	
	
	private int nian;
	public Integer getSjs() {
		return sjs;
	}
	public void setSjs(Integer sjs) {
		this.sjs = sjs;
	}
	public Integer getNss() {
		return nss;
	}
	public void setNss(Integer nss) {
		this.nss = nss;
	}
	public Integer getBjs() {
		return bjs;
	}
	public void setBjs(Integer bjs) {
		this.bjs = bjs;
	}
	public Integer getZxj() {
		return zxj;
	}
	public void setZxj(Integer zxj) {
		this.zxj = zxj;
	}
	public Integer getBysbbjs() {
		return bysbbjs;
	}
	public void setBysbbjs(Integer bysbbjs) {
		this.bysbbjs = bysbbjs;
	}
	public Integer getFbysbbjs() {
		return fbysbbjs;
	}
	public void setFbysbbjs(Integer fbysbbjs) {
		this.fbysbbjs = fbysbbjs;
	}
	public Float getNssbl() {
		return nssbl;
	}
	public void setNssbl(Float nssbl) {
		this.nssbl = nssbl;
	}
	public Float getBjl() {
		return bjl;
	}
	public void setBjl(Float bjl) {
		this.bjl = bjl;
	}
	public int getNian() {
		return nian;
	}
	public void setNian(int nian) {
		this.nian = nian;
	}
	
}
