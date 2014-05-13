package com.kzxd.newkaoqin.service.impl;

import java.util.Date;
import java.util.List;

import com.kzxd.newkaoqin.dao.GzrdyDao;
import com.kzxd.newkaoqin.entity.GzrdyBean;
import com.kzxd.newkaoqin.service.GzrdyMsg;

public class GzrdyMsgImpl implements GzrdyMsg{

	private GzrdyDao gzrdyDaoImpl;

	public GzrdyDao getGzrdyDaoImpl() {
		return gzrdyDaoImpl;
	}

	public void setGzrdyDaoImpl(GzrdyDao gzrdyDaoImpl) {
		this.gzrdyDaoImpl = gzrdyDaoImpl;
	}

	public List<GzrdyBean> findAllByTime(Date sdate, Date edate) {
		return gzrdyDaoImpl.findAllByTime(sdate, edate);
	}

}
