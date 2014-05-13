package com.kzxd.Baosong.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.worklist.list.action.BPMClientBaseBinding;

public class Testg extends BPMClientBaseBinding{

	
	public String execute() throws Exception {
		
		
		return "success";
	}
	
	private String uuid;
	
	public void togzgrid()throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.getWriter().write("<script type='text/javascript'>window.top.gzgrid();window.close();</script>");
	}
	
	public String toLibiaoForm(){
		
		
		return "success";
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	
}
