package com.kzxd.newkaoqin.service;

import java.util.Date;
import java.util.List;

import com.kzxd.newkaoqin.entity.ChuangKouTianShuBean;
import com.kzxd.newkaoqin.entity.KaoQinBaoBiaoBean;
import com.kzxd.newkaoqin.entity.KaoQinBean;

public interface KaoQinMsg {

	public boolean add(KaoQinBean kqBean);
	
	public KaoQinBean findByRq(String personid,Date rq,String czlx,String sjlx);
	
	public boolean updateDate(KaoQinBean kqBean);
	
	public List<KaoQinBean> findAllKaoQin(String sql);
	
	public KaoQinBean findByUuid(String uuid);
	
	public List<KaoQinBaoBiaoBean> findKaoQinBaoBiao(Date sdate,Date edate,List<ChuangKouTianShuBean> cktslist);
	
	public KaoQinBaoBiaoBean findByPersonid(String personid);
	
}
