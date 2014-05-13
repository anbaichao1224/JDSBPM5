package net.itjds.usm2.define.tree.mapping;

import net.itjds.usm2.db.util.EsbUtil;
import net.itjds.usm2.define.mapping.ExtBean;

public class TreeNodeBean implements ExtBean {

	private static final long serialVersionUID = 1L;

	private boolean allowChildren;

	private String id;

	private String servicekey;

	private String where;

	private boolean checked;

	private String parameterName;

	private String cls;

	private boolean disabled;

	private boolean expandable;

	private boolean expanded;

	private String href;

	private String hrefTarget;

	private String icon;

	private String iconCls;

	private String qtip;
	
	private String panelName;

	private boolean singleClickExpand;

	private String text;

	public boolean isAllowChildren() {
		return allowChildren;
	}

	public void setAllowChildren(boolean allowChildren) {
		this.allowChildren = allowChildren;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getCls() {
		return cls;
	}

	public void setCls(String cls) {
		this.cls = cls;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isExpandable() {
		return expandable;
	}

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHrefTarget() {
		return hrefTarget;
	}

	public void setHrefTarget(String hrefTarget) {
		this.hrefTarget = hrefTarget;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getQtip() {
		return qtip;
	}

	public void setQtip(String qtip) {
		this.qtip = qtip;
	}

	public boolean isSingleClickExpand() {
		return singleClickExpand;
	}

	public void setSingleClickExpand(boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}

	public String getText() {
		String orgTopNodeText = (String) EsbUtil.parExpression("$OrgTopNode.text");
		if(orgTopNodeText == null || orgTopNodeText.equals("")){
			return this.text;
		}
		return orgTopNodeText;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getId() {
		String orgTopNodeId = (String) EsbUtil.parExpression("$OrgTopNode.id");
		if(orgTopNodeId == null || orgTopNodeId.equals("")){
			return this.id;
		}
		return orgTopNodeId;
	}

	public void setId(String id) {
		this.id = id;

	}

	public String getServicekey() {
		return servicekey;
	}

	public void setServicekey(String servicekey) {
		this.servicekey = servicekey;
	}

	public String getWhere() {
		return where;
	}

	public void setWhere(String where) {
		this.where = where;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setClazz(Class clazz) {
		// TODO Auto-generated method stub
		
	}
}
