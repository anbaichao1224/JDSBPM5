package com.kzxd.newkaoqin.service;

import java.util.Date;
import java.util.List;

import com.kzxd.newkaoqin.entity.ChuangKouTianShuBean;

public interface QingJiaMingXiMsg {

	public List<ChuangKouTianShuBean> findAllByDate(Date sdate,Date edate);
	
}
