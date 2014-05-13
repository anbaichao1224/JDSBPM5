package com.kzxd.kaoqin.ation;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import com.kzxd.kaoqin.pojo.kaoqin;
import com.kzxd.kaoqin.service.KaoQinService;
import com.kzxd.kaoqin.util.KQUtil;
import com.opensymphony.xwork2.ActionSupport;

public class KaoQinTongJi  extends ActionSupport {
	
	KaoQinService  kaoqinservice;
	/**
	 * 分页参数
	 */
	private int start;
	private int limit;
	
	 int counto;
	 int countt;
	 int count;
	
	/**
	 * 查询参数
	 */
	private String username;
	private String startdate;
	private String enddate;
	
	
	public String tongJi() {
		 startdate=startdate+" 00:00:00";
			enddate=enddate+" 23:59:59";
		getDatas();
		return SUCCESS;
	}
	public void getcounto(){
		startdate=startdate+" 00:00:00";
		enddate=enddate+" 23:59:59";
		counto = kaoqinservice.getcCdCount(username, startdate, enddate);
	    String mmo="{success:true,data:{cdcount:'"+counto+"'}}";
	    print(mmo);
		
	}
public void getcountt(){
	HttpServletResponse response=ServletActionContext.getResponse();
	 startdate=startdate+" 00:00:00";
		enddate=enddate+" 23:59:59";
		countt = kaoqinservice.getcCdCount(username, startdate, enddate);
		String mmt= "{success:true,data:{ztcount:'"+countt+"'}}";
		print(mmt);
		
	}
	public void getDatas() {
		
		List<kaoqin> kq = kaoqinservice.findByTime(start, limit, username,
				startdate, enddate);
		count=kaoqinservice.getCount(username, startdate, enddate);
		String str =  KQUtil.arrayToJson(kq,count);
		print(str);
	}

	private void print(String str) {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		 response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out =  response.getWriter();
			out.print(str);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}

	}
	
	
	
	
	
	
	
	
	
	

	public KaoQinService getKaoqinservice() {
		return kaoqinservice;
	}

	public void setKaoqinservice(KaoQinService kaoqinservice) {
		this.kaoqinservice = kaoqinservice;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


	public int getCounto() {
		return counto;
	}

	public void setCounto(int counto) {
		this.counto = counto;
	}

	public int getCountt() {
		return countt;
	}

	public void setCountt(int countt) {
		this.countt = countt;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
