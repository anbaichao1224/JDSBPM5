package com.kzxd.cpbl.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.kzxd.cpbl.module.BaseBean;

public interface BaseBeanDao extends GenericDao<BaseBean, String> {

	public BaseBean findByProcessInstId(String processInstId);
	public List<BaseBean> findListByProcessInstId(String processInstId);
	public void delete(BaseBean bb);
	public BaseBean findByAId(String daid);
}

