package com.kzxd.cpbl.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.kzxd.cpbl.module.PbBean;

public interface PbManager extends
		GenericManager<PbBean, String> {

	public List findAll();

	public List<PbBean> findByZb(int start, int limit, String ttitle,
			String startdate, String enddate);

	public int getCount(String ttitle, String startdate, String enddate);

	public List<PbBean> findByBjcb(int start, int limit, String ttitle,
			String startdate, String enddate);

	public int getBjCount(String ttitle, String startdate, String enddate);

	public void update(PbBean cb);

	public PbBean select(String processInstId,String activityInstId);
}