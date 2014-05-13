package com.kzxd.nbyj.action;


import java.io.IOException;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.sf.json.JSONObject;

import com.kzxd.nbyj.dao.NbyjDAO;
import com.kzxd.nbyj.service.NbyjService;
import com.opensymphony.xwork2.Action;

public class NbyjAction implements Action{
	private String activityInstId;
	private JSONObject ja;
	
	public String execute() throws Exception {
	return null	;
	}
	
	public String findnbyj1() {
		 BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
			WorkflowClientService client = bpmUserClientUtil.getClient();
		ActivityInst activityInst;
		String processInstId="";
		try {
			activityInst = client.getActivityInst(activityInstId);
			 processInstId = activityInst.getProcessInst().getProcessInstId();
		} catch (BPMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		NbyjService nbyjService = new NbyjService();
		List<NbyjDAO> nbyjList = nbyjService.findByProcessInstId(processInstId);
		Map<String ,Object> map = new HashMap<String,Object>();
		//List<Map<String ,Object>> childrens= new ArrayList<Map<String ,Object>>();
		//Map<String ,Object> childrenmap = new HashMap<String,Object>();
	  List<Map<String,Object>> comment = new ArrayList<Map<String,Object>>();
		map.put("totalProperty", nbyjList.size());
		for(int i=0;i<nbyjList.size();i++){
			String   createdate=new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(nbyjList.get(i).getCreatedate());
			String nbyj = nbyjList.get(i).getNbyj();
			String personname = nbyjList.get(i).getPersonname();
			String orgname = nbyjList.get(i).getOrg();
			String hj = nbyjList.get(i).getHj();
			Map<String ,Object> childrenmap = new HashMap<String,Object>();
			if(createdate==null){
				createdate="";
				childrenmap.put("createdate",createdate);
			}else{
				childrenmap.put("createdate",createdate);
			}
			if(nbyj==null){
				nbyj="";
				childrenmap.put("nbyj",nbyj);
			}else{
				childrenmap.put("nbyj",nbyj);
			}
			if(personname==null){
				personname="";
				childrenmap.put("personname",personname);
			}else{
				childrenmap.put("personname",personname);
			}
			if(orgname==null){
				orgname="";
				childrenmap.put("orgname",orgname);
			}else{
				childrenmap.put("orgname",orgname);
			}
			if(hj==null){
				hj="";
				childrenmap.put("hj",hj);
			}else{
				childrenmap.put("hj",hj);
			}
			comment.add(childrenmap);
		}
		//map.put("comments",comment);
	//	childrens.add(childrenmap);
		map.put("root", comment);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		try {
			ja =JSONObject.fromObject(map);
			response.getWriter().write(ja.toString());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	public String findnbyj(){
		return "nbyj";
	}
	public JSONObject getJa() {
		return ja;
	}
	public void setJa(JSONObject ja) {
		this.ja = ja;
	}
	public String getActivityInstId() {
		return activityInstId;
	}
	public void setActivityInstId(String activityInstId) {
		this.activityInstId = activityInstId;
	}
	
}
