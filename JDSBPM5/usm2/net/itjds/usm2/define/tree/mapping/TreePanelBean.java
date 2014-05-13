package net.itjds.usm2.define.tree.mapping;

import net.itjds.j2ee.dao.MethodChinaName;
import net.itjds.usm2.define.data.annotation.AjaxDataDefine;
import net.itjds.usm2.define.data.mapping.AjaxDataBean;
import net.itjds.usm2.define.enums.ElementCheckModel;
import net.itjds.usm2.define.mapping.PanelBean;



public class TreePanelBean  extends  PanelBean{

	private static final long serialVersionUID = 1L;


	private String region;
	
	private String xtype;

	
	private boolean animate;

	private String el;

	private String bodyStyle;

	private boolean lines;
	
	private Object[] listeners;

	private boolean border;

	private ElementCheckModel checkModel;

	private boolean onlyLeafCheckable;

	private boolean rootVisible;

	private boolean autoScroll;

	private String loader;

	private String root;

	private String namespace;
	
	private TreeLoaderBean treeLoaderBean;
	
	private TreeNodeBean treeNodeBean;
	

	
	private Object[] buttons;

	private AjaxDataBean updateData;


	private AjaxDataBean deleteData;

	public Object[] getButtons() {
		return buttons;
	}

	public void setButtons(Object[] buttons) {
		this.buttons = buttons;
	}

	public TreeNodeBean getTreeNodeBean() {
		return treeNodeBean;
	}

	public void setTreeNodeBean(TreeNodeBean treeNodeBean) {
		this.treeNodeBean = treeNodeBean;
	}

	public TreeLoaderBean getTreeLoaderBean() {
		return treeLoaderBean;
	}

	public void setTreeLoaderBean(TreeLoaderBean treeLoaderBean) {
		this.treeLoaderBean = treeLoaderBean;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public boolean isAnimate() {
		return animate;
	}

	public void setAnimate(boolean animate) {
		this.animate = animate;
	}

	public boolean isAutoScroll() {
		return autoScroll;
	}

	public void setAutoScroll(boolean autoScroll) {
		this.autoScroll = autoScroll;
	}

	public String getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}





	public ElementCheckModel getCheckModel() {
		return checkModel;
	}

	public void setCheckModel(ElementCheckModel checkModel) {
		this.checkModel = checkModel;
	}

	public String getEl() {
		return el;
	}

	public void setEl(String el) {
		this.el = el;
	}

	
	public boolean isLines() {
		return lines;
	}

	public void setLines(boolean lines) {
		this.lines = lines;
	}



	public String getRoot() {
		return root;
	}

	public void setRoot(String root) {
		this.root = root;
	}

	public boolean isOnlyLeafCheckable() {
		return onlyLeafCheckable;
	}

	public void setOnlyLeafCheckable(boolean onlyLeafCheckable) {
		this.onlyLeafCheckable = onlyLeafCheckable;
	}

	public boolean isRootVisible() {
		return rootVisible;
	}

	public void setRootVisible(boolean rootVisible) {
		this.rootVisible = rootVisible;
	}

	

	public String getXtype() {
		return xtype;
	}

	public void setXtype(String xtype) {
		this.xtype = xtype;
	}

	public String getLoader() {
		return loader;
	}

	public void setLoader(String loader) {
		this.loader = loader;
	}

	public Object[] getListeners() {
		return listeners;
	}

	public void setListeners(Object[] listeners) {
		this.listeners = listeners;
	}


	public AjaxDataBean getUpdateData() {
		return updateData;
	}

	public void setUpdateData(AjaxDataBean updateData) {
		this.updateData = updateData;
	}

	
	public AjaxDataBean getDeleteData() {
		return deleteData;
	}




	public void setDeleteData(AjaxDataBean deleteData) {
		this.deleteData = deleteData;
	}


}
