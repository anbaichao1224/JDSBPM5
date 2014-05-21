package com.kzxd.zihao.service.impl;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;
import org.springframework.transaction.annotation.Transactional;

import com.kzxd.zihao.dao.ZiHaoDao;
import com.kzxd.zihao.entity.ZiHao;
import com.kzxd.zihao.service.ZiHaoService;

@Transactional
public class ZiHaoServiceImpl extends GenericManagerImpl<ZiHao, String> implements ZiHaoService {
	
	private ZiHaoDao zhDao;
	
	public ZiHaoServiceImpl(ZiHaoDao zhDao){
		super(zhDao);
		this.zhDao = zhDao;
	}

	public ZiHaoDao getZhDao() {
		return zhDao;
	}

	public void setZhDao(ZiHaoDao zhDao) {
		this.zhDao = zhDao;
	}
	public List getByWZId(String wzid) {
		return zhDao.getByWZId(wzid);
	}
	
	
	
	
}
