package com.kzxd.newkaoqin.service;

import java.util.Date;
import java.util.List;

import com.kzxd.newkaoqin.entity.KaoQinShiJianBean;

public interface KaoQinShiJianMsg {
	
	public boolean add(KaoQinShiJianBean kqsjsdBean);
	
	public boolean update(KaoQinShiJianBean kqsjsdBean);
	
	public KaoQinShiJianBean getByRq(Date rq);

	public KaoQinShiJianBean getByUuid(String uuid);
	
	public boolean delete(KaoQinShiJianBean kqsjsdBean);
	
	public List<KaoQinShiJianBean> findAll();

}
