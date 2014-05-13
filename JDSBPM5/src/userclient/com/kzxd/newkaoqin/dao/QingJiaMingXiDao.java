package com.kzxd.newkaoqin.dao;

import java.util.Date;
import java.util.List;

import com.kzxd.newkaoqin.entity.ChuangKouTianShuBean;
import com.kzxd.newkaoqin.entity.QingJiaMingXiBean;

public interface QingJiaMingXiDao {

	public void add(QingJiaMingXiBean qjmxBean);
	
	public List<ChuangKouTianShuBean> findAllByDate(Date sdate,Date edate);
}
