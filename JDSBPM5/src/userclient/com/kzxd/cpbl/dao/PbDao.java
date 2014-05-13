package com.kzxd.cpbl.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.kzxd.cpbl.module.PbBean;

public interface PbDao extends GenericDao<PbBean, String> {

	public List<PbBean> findByZb(String string, int start, int limit, List<String> list);

	public int getCount(String string, List<String> list);

	public void update(PbBean cb);

	public PbBean select(String processInstId,String activityInstId);
}

