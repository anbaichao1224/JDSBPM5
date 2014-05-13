package com.kzxd.cpbl.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.kzxd.cpbl.module.BaseBean;

public interface BaseBeanManager extends
		GenericManager<BaseBean, String> {

	public List findAll();
	
	public BaseBean findByProcessInstId(String processInstId);
	
	public List<BaseBean> findListByProcessInstId(String processInstId);
	
	public void delete(BaseBean bb);

	public void deleteBy(String daid);

}