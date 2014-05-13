package com.kzxd.cpbl.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;

import com.kzxd.cpbl.dao.BaseBeanDao;
import com.kzxd.cpbl.module.BaseBean;
import com.kzxd.cpbl.service.BaseBeanManager;

public class BaseBeanManagerImpl extends
		GenericManagerImpl<BaseBean, String> implements BaseBeanManager{
	BaseBeanDao basebeanDao;

	public BaseBeanManagerImpl(BaseBeanDao basebeanDao) {
		super(basebeanDao);
		this.basebeanDao = basebeanDao;
	}

	public List<BaseBean> findAll() {
		return this.basebeanDao.getAll();
	}

	public BaseBean findByProcessInstId(String processInstId) {
		return this.basebeanDao.findByProcessInstId(processInstId);
	}

	public void delete(BaseBean bb) {
		this.basebeanDao.delete(bb);
	}

	public void deleteBy(String daid) {
		BaseBean bb = basebeanDao.findByAId(daid);
		if(bb != null){
			basebeanDao.delete(bb);
		}
	}

	public List<BaseBean> findListByProcessInstId(String processInstId) {
		return this.basebeanDao.findListByProcessInstId(processInstId);
	}
	
	
}