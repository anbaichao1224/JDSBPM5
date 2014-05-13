package com.kzxd.newkaoqin.service.impl;

import java.util.Date;
import java.util.List;

import com.kzxd.newkaoqin.dao.QingJiaMingXiDao;
import com.kzxd.newkaoqin.entity.ChuangKouTianShuBean;
import com.kzxd.newkaoqin.service.QingJiaMingXiMsg;

public class QingJiaMingXiMsgImpl implements QingJiaMingXiMsg{

	private QingJiaMingXiDao qjmxDaoImpl;

	public List<ChuangKouTianShuBean> findAllByDate(Date sdate, Date edate) {
		return qjmxDaoImpl.findAllByDate(sdate, edate);
	}

	public QingJiaMingXiDao getQjmxDaoImpl() {
		return qjmxDaoImpl;
	}

	public void setQjmxDaoImpl(QingJiaMingXiDao qjmxDaoImpl) {
		this.qjmxDaoImpl = qjmxDaoImpl;
	}
	
}
