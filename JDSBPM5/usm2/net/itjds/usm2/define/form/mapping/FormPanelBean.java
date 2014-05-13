package net.itjds.usm2.define.form.mapping;

import java.util.List;

import net.itjds.usm2.define.data.mapping.AjaxDataBean;
import net.itjds.usm2.define.enums.ElementAlign;
import net.itjds.usm2.define.mapping.PanelBean;



public class FormPanelBean extends PanelBean{
	private String namespace;
	
	

	private ElementAlign buttonAlign;

	private Object[] buttons;// JSON
	
	private String ftlUrl;

	private boolean footer;

	private boolean header;

	private boolean headerAsText;

	
	private ElementAlign labelAlign;

	private double labelWidth;

	private String title;

	private Object[] searchBeans;

	private  boolean fileUpload;
	private AjaxDataBean loadData;
	
	private AjaxDataBean updateData;
	

	private List<FormFieldsBean> formFieldsBeanList;

	public List<FormFieldsBean> getFormFieldsBeanList() {
		return formFieldsBeanList;
	}

	public void setFormFieldsBeanList(List<FormFieldsBean> formFieldsBeanList) {
		this.formFieldsBeanList = formFieldsBeanList;
	}

	public ElementAlign getButtonAlign() {
		return buttonAlign;
	}

	public void setButtonAlign(ElementAlign buttonAlign) {
		this.buttonAlign = buttonAlign;
	}



	public Object[] getButtons() {
		return buttons;
	}

	public void setButtons(Object[] buttons) {
		this.buttons = buttons;
	}

	public boolean isFooter() {
		return footer;
	}

	public void setFooter(boolean footer) {
		this.footer = footer;
	}

	public boolean isHeader() {
		return header;
	}

	public void setHeader(boolean header) {
		this.header = header;
	}

	public boolean isHeaderAsText() {
		return headerAsText;
	}

	public void setHeaderAsText(boolean headerAsText) {
		this.headerAsText = headerAsText;
	}



	public ElementAlign getLabelAlign() {
		return labelAlign;
	}

	public double getLabelWidth() {
		return labelWidth;
	}

	public void setLabelWidth(double labelWidth) {
		this.labelWidth = labelWidth;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public void setLabelAlign(ElementAlign labelAlign) {
		this.labelAlign = labelAlign;
	}

	public void setFtlUrl(String ftlUrl) {
		this.ftlUrl = ftlUrl;
	}

	public String getFtlUrl() {
		return ftlUrl;
	}

	public Object[] getSearchBeans() {
		return searchBeans;
	}

	public void setSearchBeans(Object[] searchBeans) {
		this.searchBeans = searchBeans;
	}

	public boolean isFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(boolean fileUpload) {
		this.fileUpload = fileUpload;
	}

	

	
	public AjaxDataBean getLoadData() {
		return loadData;
	}

	public void setLoadData(AjaxDataBean loadData) {
		this.loadData = loadData;
	}

	


	public AjaxDataBean getUpdateData() {
		return updateData;
	}

	public void setUpdateData(AjaxDataBean updateData) {
		this.updateData = updateData;
	}

	

}
