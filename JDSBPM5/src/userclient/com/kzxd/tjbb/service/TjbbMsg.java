package com.kzxd.tjbb.service;

import java.util.Date;
import java.util.List;

import com.kzxd.tjbb.entity.DanWeiBean;
import com.kzxd.tjbb.entity.MingXiBean;
import com.kzxd.tjbb.entity.ShouLiYeWuBean;
import com.kzxd.tjbb.entity.TjbbBean;
import com.kzxd.tjbb.entity.YueDuBean;

public interface TjbbMsg {

	public boolean add(TjbbBean tjbbBean);
	
	public List<TjbbBean> findAllByPersonid(String personid,String bjzt);
	
	public List<TjbbBean> findAllByPersonid2(String personid,String bjzt,String zxdw, Date sdate,Date edate);
	
	public List<TjbbBean> findAllByPersonid3(String personid,String bjzt,String zxdw, Date sdate);
	
	public List<TjbbBean> findAllByPersonid4(String personid,String bjzt,String zxdw);
	
	public List<TjbbBean> findAllByPersonid5(String personid,String bjzt,String zxdw,Date edate);
	
	public List<TjbbBean> findAllByPersonid6(String personid,String bjzt, Date sdate,Date edate);
	
	public List<TjbbBean> findAllByPersonid7(String personid,String bjzt,Date edate);
	
	public List<TjbbBean> findAllByPersonid8(String personid,String bjzt, Date sdate);
	
	public List<TjbbBean> findAllByPersonid12(String bjzt);
	
	public List<TjbbBean> findAllByPersonid22(String bjzt,String personid, Date sdate,Date edate);
	
	public List<TjbbBean> findAllByPersonid32(String bjzt,String personid, Date sdate);
	
	public List<TjbbBean> findAllByPersonid42(String bjzt,String personid);
	
	public List<TjbbBean> findAllByPersonid52(String bjzt,String personid,Date edate);
	
	public List<TjbbBean> findAllByPersonid62(String bjzt, Date sdate,Date edate);
	
	public List<TjbbBean> findAllByPersonid72(String bjzt,Date edate);
	
	public List<TjbbBean> findAllByPersonid82(String bjzt, Date sdate);
	
	public boolean updateTjbbByUuid(TjbbBean tjbbBean);

	public TjbbBean getByUuid(String uuid);
	
	public List findAllByYue(Date yst,Date yet,Date nst);
	
	public YueDuBean findAllByNian(String personid,Date et,Date st);
	
	public YueDuBean findAllByNian2(String personid,Date et,Date st);
	
	public List findMingXi(Date st,Date et);
	
	public List findMingXiJdnr(Date st,Date et);

	public List findMingXiBjnr(Date st,Date et);
	
	public List<DanWeiBean> danWeiXiaLa();
	
	public MingXiBean zongJiMingXi(Date st,Date et);
	
	public YueDuBean yueDuHeJi(Date yst,Date yet,Date nst);
	
	public YueDuBean nianDuHeJi(String personid,Date nst,Date net);
	
	public ShouLiYeWuBean yueDuYeWu(Date yst,Date yet);
	
	public ShouLiYeWuBean nianDuYeWu(Date yst,Date yet);
	
	public List<TjbbBean> ziXunFindAllByPersonid(String personid);
	
	public List<TjbbBean> ziXunHuiZongFindAllByTjdw();
	
	public MingXiBean findByPersonid(String personid);
}
