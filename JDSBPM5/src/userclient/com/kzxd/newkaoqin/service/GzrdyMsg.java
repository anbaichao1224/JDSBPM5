package com.kzxd.newkaoqin.service;

import java.util.Date;
import java.util.List;

import com.kzxd.newkaoqin.entity.GzrdyBean;

public interface GzrdyMsg {

	public List<GzrdyBean> findAllByTime(Date sdate,Date edate);
	
}
