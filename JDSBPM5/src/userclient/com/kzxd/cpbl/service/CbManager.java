package com.kzxd.cpbl.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.kzxd.cpbl.module.CbBean;

public interface CbManager extends
		GenericManager<CbBean, String> {

	public List findAll();

	public List<CbBean> findByZb(int start, int limit, String ttitle,
			String startdate, String enddate);

	public int getCount(String ttitle, String startdate, String enddate);

	public List<CbBean> findByBjcb(int start, int limit, String ttitle,
			String startdate, String enddate);

	public int getBjCount(String ttitle, String startdate, String enddate);

	public void update(CbBean cb);
	
	public CbBean select(String processInstId,String activityInstId);

}