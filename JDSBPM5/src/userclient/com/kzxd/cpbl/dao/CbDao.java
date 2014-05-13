package com.kzxd.cpbl.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.kzxd.cpbl.module.CbBean;

public interface CbDao extends GenericDao<CbBean, String> {

	public List<CbBean> findByZb(String hql, int start, int limit, List<String> list);

	public int getCount(String string, List<String> list);

	public void update(CbBean cb);

	public CbBean select(String processInstId,String activityInstId);
}

