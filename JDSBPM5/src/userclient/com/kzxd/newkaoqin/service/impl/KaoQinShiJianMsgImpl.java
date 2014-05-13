package com.kzxd.newkaoqin.service.impl;

import java.util.Date;
import java.util.List;

import com.kzxd.newkaoqin.dao.KaoQinShiJianDao;
import com.kzxd.newkaoqin.entity.KaoQinShiJianBean;
import com.kzxd.newkaoqin.service.KaoQinShiJianMsg;

public class KaoQinShiJianMsgImpl implements KaoQinShiJianMsg{

	private KaoQinShiJianDao kqsjDaoImpl;

	public KaoQinShiJianDao getKqsjDaoImpl() {
		return kqsjDaoImpl;
	}

	public void setKqsjDaoImpl(KaoQinShiJianDao kqsjDaoImpl) {
		this.kqsjDaoImpl = kqsjDaoImpl;
	}

	public boolean add(KaoQinShiJianBean kqsjsdBean) {
		return kqsjDaoImpl.add(kqsjsdBean);
	}

	public boolean delete(KaoQinShiJianBean kqsjsdBean) {
		return kqsjDaoImpl.delete(kqsjsdBean);
	}

	public List<KaoQinShiJianBean> findAll() {
		return kqsjDaoImpl.findAll();
	}

	public KaoQinShiJianBean getByRq(Date rq) {
		return kqsjDaoImpl.getByRq(rq);
	}

	public boolean update(KaoQinShiJianBean kqsjsdBean) {
		return kqsjDaoImpl.update(kqsjsdBean);
	}

	public KaoQinShiJianBean getByUuid(String uuid) {
		return kqsjDaoImpl.getByUuid(uuid);
	}
	
}
