package net.itjds.userclient.usm.action;

import java.util.ArrayList;
import java.util.List;

public class GwTjBean {
	private String id;
	private String name;
	private Integer count;
	private String gwtype;
	private Integer waitCount;
	private Integer completeCount;
	private List<GwTjBean> childList = new ArrayList<GwTjBean>();
	
	public GwTjBean(){}
	
	public GwTjBean(String id,String name,Integer count,String gwtype,Integer waitCount,Integer completeCount){
		this.id = id;
		this.name = name;
		this.count = count;
		this.gwtype = gwtype;
		this.waitCount = waitCount;
		this.completeCount = completeCount;
	}
	
	public GwTjBean(String id,String name,Integer count){
		this.id = id;
		this.name = name;
		this.count = count;
	}
	
	public void addList(GwTjBean gwtj){
		this.childList.add(gwtj);
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

	public String getGwtype() {
		return gwtype;
	}

	public void setGwtype(String gwtype) {
		this.gwtype = gwtype;
	}

	public Integer getWaitCount() {
		return waitCount;
	}

	public void setWaitCount(Integer waitCount) {
		this.waitCount = waitCount;
	}

	public Integer getCompleteCount() {
		return completeCount;
	}

	public void setCompleteCount(Integer completeCount) {
		this.completeCount = completeCount;
	}

	public List<GwTjBean> getChildList() {
		return childList;
	}

	public void setChildList(List<GwTjBean> childList) {
		this.childList = childList;
	}
	
	
	
	
}
