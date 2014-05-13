package net.kzxd.dj.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import net.kzxd.dj.util.DjUtils;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class StartPropertyAction implements Action{

	/**
	 * 接受此参数去解析xml
	 */
	private String xmlmodel;
	
	private String uuid;
	
	/**
	 *json格式的字符串 单选条目
	 */
	private String jsonradio;
	
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		jsonradio = DjUtils.parseXML(xmlmodel);
		return SUCCESS;
	}

	public String getXmlmodel() {
		return xmlmodel;
	}

	public void setXmlmodel(String xmlmodel) {
		this.xmlmodel = xmlmodel;
	}

	public String getJsonradio() {
		return jsonradio;
	}

	public void setJsonradio(String jsonradio) {
		this.jsonradio = jsonradio;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
