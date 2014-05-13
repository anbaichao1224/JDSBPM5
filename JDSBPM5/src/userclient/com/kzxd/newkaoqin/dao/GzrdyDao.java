package com.kzxd.newkaoqin.dao;

import java.util.Date;
import java.util.List;

import com.kzxd.newkaoqin.entity.GzrdyBean;

public interface GzrdyDao {
	
	public List<GzrdyBean> findAllByTime(Date sdate,Date edate);

}
