package com.kzxd.tbm.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.itjds.j2ee.util.UUID;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.kzxd.tbm.dao.WjzlCatalogDAO;
import com.kzxd.tbm.service.PersonService;
import com.kzxd.tbm.service.RootCatalogManager;
import com.opensymphony.xwork2.ActionSupport;

public class PersonAction extends ActionSupport{
	private WjzlCatalogDAO wjzldao; 
	private String orguuid;
	private String catalogUuid;
	private JSONObject ja;
	private PersonService personservice = new PersonService();
	
	public WjzlCatalogDAO getWjzldao() {
		return wjzldao;
	}
	public void setWjzldao(WjzlCatalogDAO wjzldao) {
		this.wjzldao = wjzldao;
	}
	public String getCatalogUuid() {
		return catalogUuid;
	}
	public void setCatalogUuid(String catalogUuid) {
		this.catalogUuid = catalogUuid;
	}
	//跳转到添加人员页面
	public String toAddPerson(){
		//查找当前部门
		
		return "toAddPerson";
	}
	public String getOrguuid() {
		return orguuid;
	}
	public void setOrguuid(String orguuid) {
		this.orguuid = orguuid;
	}
	public String findperson(){
		 List<WjzlCatalogDAO> personlist = personservice.findperson(orguuid);
		 Map<String ,Object> map = new HashMap<String,Object>();
			List<Map<String ,Object>> childrens= new ArrayList<Map<String ,Object>>();
			map.put("totalProperty", personlist.size());
			for(int i=0;i<personlist.size();i++){
				Map<String ,Object> childrenmap = new HashMap<String,Object>();
				childrenmap.put("uuid", personlist.get(i).getUuid());
				childrenmap.put("name", personlist.get(i).getCatalogName());
				childrenmap.put("phone", personlist.get(i).getPhone());
				childrenmap.put("other", personlist.get(i).getCatalogOrder());
				childrenmap.put("bz", personlist.get(i).getBz());
				childrenmap.put("duty", personlist.get(i).getDuty());
				childrens.add(childrenmap);
		}
			map.put("root", childrens);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		ja =JSONObject.fromObject(map);
		try {
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	public String AddPerson(){
		UUID uuid = new UUID();
		wjzldao.setUuid(uuid.toString());
		Date dd = new Date();
		wjzldao.setCreateDate(dd);
		wjzldao.setParentId(this.catalogUuid);
		personservice.addPerson(wjzldao);
		return null;
		
	}
	public JSONObject getJa() {
		return ja;
	}
	public void setJa(JSONObject ja) {
		this.ja = ja;
	}
}
