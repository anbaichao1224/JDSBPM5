package net.itjds.fdt.metting;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MettingBeanTree {
	private String tid;
	private String name;
	private String kssj;
	private String jssj;
	private String blrmc;
	private String blrid;
	private String uiProvider;
	private boolean leaf;
	private String creatorid;
	private String creator;
	private String createdate;
	private String hylxid;
	private String isopen;
	private String sxlx;
	private String sxnr;
	private String blstatus;
	private String isdelete;
	private String activityinstid;
	private String processinstid;
	private String processdefid;
	private String mettingid;
	private String zxblqk;
	private String webblstatus;//前台展示办理状态
	private List<MettingBeanTree> Children = new ArrayList<MettingBeanTree>();
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	
	public String getBlrmc() {
		return blrmc;
	}
	public void setBlrmc(String blrmc) {
		this.blrmc = blrmc;
	}
	public String getBlrid() {
		return blrid;
	}
	public void setBlrid(String blrid) {
		this.blrid = blrid;
	}
	public String getUiProvider() {
		return "col";
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	public String getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getHylxid() {
		return hylxid;
	}
	public void setHylxid(String hylxid) {
		this.hylxid = hylxid;
	}
	
	public String getSxlx() {
		return sxlx;
	}
	public void setSxlx(String sxlx) {
		this.sxlx = sxlx;
	}
	public String getSxnr() {
		return sxnr;
	}
	public void setSxnr(String sxnr) {
		this.sxnr = sxnr;
	}
	public String getBlstatus() {
		return blstatus;
	}
	public void setBlstatus(String blstatus) {
		this.blstatus = blstatus;
	}
	public String getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(String isdelete) {
		this.isdelete = isdelete;
	}
	public String getActivityinstid() {
		return activityinstid;
	}
	public void setActivityinstid(String activityinstid) {
		this.activityinstid = activityinstid;
	}
	public String getProcessinstid() {
		return processinstid;
	}
	public void setProcessinstid(String processinstid) {
		this.processinstid = processinstid;
	}
	public String getProcessdefid() {
		return processdefid;
	}
	public void setProcessdefid(String processdefid) {
		this.processdefid = processdefid;
	}
	public String getMettingid() {
		return mettingid;
	}
	public void setMettingid(String mettingid) {
		this.mettingid = mettingid;
	}
	
	public String getZxblqk() {
		return zxblqk;
	}
	public void setZxblqk(String zxblqk) {
		this.zxblqk = zxblqk;
	}
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	
	public String getWebblstatus() {
		
		return webblstatus;
	}
	public void setWebblstatus(String webblstatus) {
		this.webblstatus = webblstatus;
	}
	public List<MettingBeanTree> getChildren() {
		return Children;
	}
	public void setChildren(List<MettingBeanTree> children) {
		Children = children;
	}
	
	

	

}
