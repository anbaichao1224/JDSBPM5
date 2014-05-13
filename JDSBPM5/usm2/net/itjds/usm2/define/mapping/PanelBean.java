package net.itjds.usm2.define.mapping;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.common.util.StringUtility;
import net.itjds.fdt.define.designer.utils.HtmlFileUtil;
import net.itjds.usm2.db.util.EsbUtil;
import net.itjds.usm2.define.template.FreemarkerResult;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PanelBean implements ExtBean {
    public String title;
    
    public String id;
    
    public String path;
    
    public String viewPath;
    
    public String esbkey;
    
    
	
	public String name;
	
	public Class clazz;

	public int width;

	public int height;

	public String region;
	
	public String ftlUrl;
	
	public String servicekey;
	
	public Object[] fieldsIndex;
	
	private Object data;
	
	
	public List<FieldBean> items=new ArrayList<FieldBean>();

	public String getFtlUrl() {
		return ftlUrl;
	}

	public void setFtlUrl(String ftlUrl) {
		this.ftlUrl = ftlUrl;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FieldBean> getItems() {
		return items;
	}

	public void setItems(List<FieldBean> items) {
		this.items = items;
	}
	public String getExtStr() throws IOException, TemplateException{
		
		
	     StringWriter stringWriter = new StringWriter();
		  String classpath="extviewtemp";
		  String responseStr="";
		  ActionContext.getContext().getValueStack().getRoot().add(this);
			try {
				FreemarkerResult result=new FreemarkerResult();
			stringWriter = (StringWriter) result.doExecute(classpath+File.separator+this.getFtlUrl());
			} catch (TemplateException e) {
				e.printStackTrace();
			}

		String str=stringWriter.toString();
		return str;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Class getClazz() {
		return clazz;
	}

	
	public String getServicekey() {
		return servicekey;
	}

	public void setServicekey(String servicekey) {
		this.servicekey = servicekey;
	}

	public Object[] getFieldsIndex() {
		return fieldsIndex;
	}

	public void setFieldsIndex(Object[] fieldsIndex) {
		this.fieldsIndex = fieldsIndex;
	}

	public String getEsbkey() {
	
		return esbkey;
	}

	public void setEsbkey(String esbkey) {
		this.esbkey = esbkey;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Object getData() {
		if (data==null){
			EsbUtil.parExpression(this.getPath());
		}
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getViewPath() {
		if (viewPath==null){
			viewPath=this.getPath();
		}
		return viewPath;
	}

	public void setViewPath(String viewPath) {
		this.viewPath = viewPath;
	}

	public Class setClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz=clazz;
		
	}



}
