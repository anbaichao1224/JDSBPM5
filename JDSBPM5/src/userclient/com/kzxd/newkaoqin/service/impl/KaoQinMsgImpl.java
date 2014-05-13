package com.kzxd.newkaoqin.service.impl;

import java.util.Date;
import java.util.List;

import com.kzxd.newkaoqin.dao.KaoQinDao;
import com.kzxd.newkaoqin.entity.ChuangKouTianShuBean;
import com.kzxd.newkaoqin.entity.KaoQinBaoBiaoBean;
import com.kzxd.newkaoqin.entity.KaoQinBean;
import com.kzxd.newkaoqin.service.KaoQinMsg;

public class KaoQinMsgImpl implements KaoQinMsg{

	private KaoQinDao kqDaoImpl;

	public KaoQinDao getKqDaoImpl() {
		return kqDaoImpl;
	}

	public void setKqDaoImpl(KaoQinDao kqDaoImpl) {
		this.kqDaoImpl = kqDaoImpl;
	}

	public boolean add(KaoQinBean kqBean) {
		return kqDaoImpl.add(kqBean);
	}

	public List<KaoQinBean> findAllKaoQin(String sql) {
		return kqDaoImpl.findAllKaoQin(sql);
	}

	public KaoQinBean findByRq(String personid,Date rq, String czlx, String sjlx) {
		return kqDaoImpl.findByRq(personid,rq, czlx, sjlx);
	}

	public boolean updateDate(KaoQinBean kqBean) {
		return kqDaoImpl.updateDate(kqBean);
	}

	public KaoQinBean findByUuid(String uuid) {
		return kqDaoImpl.findByUuid(uuid);
	}

	public List<KaoQinBaoBiaoBean> findKaoQinBaoBiao(Date sdate, Date edate,
			List<ChuangKouTianShuBean> cktslist) {
		return kqDaoImpl.findKaoQinBaoBiao(sdate, edate, cktslist);
	}

	public KaoQinBaoBiaoBean findByPersonid(String personid) {
		return kqDaoImpl.findByPersonid(personid);
	}

	
}
