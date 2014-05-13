package net.itjds.common.org.util;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.org.base.Person;
import net.itjds.userclient.common.BPMUserClientUtil;

public class ExtNode {
	private String text;
	private String leaf;
	private String id;
	private String uid;
	private String cnName = "";
	private List<ExtNode> children = new ArrayList();
	private ExtNode parent;

	public void serializ(StringBuffer layoutJson) {
		layoutJson.append("{");
		layoutJson.append("\"text\":\"").append(filter(this.text))
				.append("\",");
		layoutJson.append("\"id\":\"").append(this.id).append("\",");

		if (this.children.size() > 0) {
			layoutJson.append("\"children\":[");
			for (int i = 0; i < this.children.size(); i++) {
				((ExtNode) this.children.get(i)).serializ(layoutJson);
			}
			layoutJson.deleteCharAt(layoutJson.length() - 1);
			layoutJson.append("],");
		}
		layoutJson.append("\"uid\":\"").append(this.uid).append("\",");

		layoutJson.append("\"cnName\":\"").append(filter(this.cnName)).append(
				"\",");

		if ((this.leaf.equals("true")) && (!this.id.equals("root"))) {
			layoutJson.append("\"checked\":").append("false,");
		}

		layoutJson.append("\"leaf\":").append(this.leaf);

		layoutJson.append("},");
	}

	public void serializChild(StringBuffer layoutJson) {
		if (this.children.size() > 0) {
			for (int i = 0; i < this.children.size(); i++) {
				((ExtNode) this.children.get(i)).serializ(layoutJson);
			}
		}
	}

	public void serializBf(StringBuffer layoutJson, String[] addr)
			throws BPMException {
		String c = "";
		for (int i = 0; i < addr.length; i++) {
			String x = addr[i];
			if (x != null && x.equals(this.text)) {
				layoutJson.append("{");
				layoutJson.append("\"text\":\"").append(filter(this.text))
						.append("\",");
				layoutJson.append("\"disabled\":").append("true,");
				layoutJson.append("\"checked\":").append("true,");
				layoutJson.append("\"id\":\"").append(this.id).append("\",");
				c = "1";
				break;
			}

		}
		if (c.equals("")) {
			layoutJson.append("{");
			layoutJson.append("\"text\":\"").append(filter(this.text)).append(
					"\",");
			layoutJson.append("\"disabled\":").append("false,");
			layoutJson.append("\"id\":\"").append(this.id).append("\",");
		}

		if (this.children.size() > 0) {
			layoutJson.append("\"children\":[");
			for (int i = 0; i < this.children.size(); i++) {
				((ExtNode) this.children.get(i)).serializBf(layoutJson, addr);
			}
			layoutJson.deleteCharAt(layoutJson.length() - 1);
			layoutJson.append("],");
		}
		layoutJson.append("\"uid\":\"").append(this.uid).append("\",");

		layoutJson.append("\"cnName\":\"").append(filter(this.cnName)).append(
				"\",");

		if ((this.leaf.equals("true")) && (!this.id.equals("root"))) {
			layoutJson.append("\"checked\":").append("false,");
		}

		layoutJson.append("\"leaf\":").append(this.leaf);

		layoutJson.append("},");
	}

	public void serializChildBf(StringBuffer layoutJson, String[] addr)
			throws BPMException {
		if (this.children.size() > 0) {
			for (int i = 0; i < this.children.size(); i++) {
				((ExtNode) this.children.get(i)).serializBf(layoutJson, addr);
			}
		}
	}

	public boolean equals(Object obj) {
		return (super.equals(obj)) || (getId().equals(((ExtNode) obj).getId()));
	}

	public int hashCode() {
		return Integer.parseInt(Integer.toString(this.id.toLowerCase()
				.charAt(0))
				+ this.id.substring(1));
	}

	public void addChild(ExtNode child) {
		this.children.add(child);
	}

	public boolean containChild(ExtNode child) {
		return this.children.contains(child);
	}

	private String filter(String str) {
		if (str == null) {
			return "";
		}
		str = str.replaceAll("(?i)<br>", "");
		return str;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getLeaf() {
		return this.leaf;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ExtNode> getChildren() {
		return this.children;
	}

	public void setChildren(List<ExtNode> children) {
		this.children = children;
	}

	public ExtNode getParent() {
		return this.parent;
	}

	public void setParent(ExtNode parent) {
		this.parent = parent;
	}

	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getCnName() {
		return this.cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
}