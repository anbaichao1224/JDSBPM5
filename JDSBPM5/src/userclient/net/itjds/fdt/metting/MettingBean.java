package net.itjds.fdt.metting;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MettingBean {
	private String id;
	private String name;
	private List<MettingBean> Children = new ArrayList<MettingBean>();
	private List<MatterInfoListBean> matterInfos = new ArrayList<MatterInfoListBean>();
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
	public List<MettingBean> getChildren() {
		return Children;
	}
	public void setChildren(List<MettingBean> children) {
		Children = children;
	}
	public List<MatterInfoListBean> getMatterInfos() {
		return matterInfos;
	}
	public void setMatterInfos(List<MatterInfoListBean> matterInfos) {
		this.matterInfos = matterInfos;
	}
	

	

}
