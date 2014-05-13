package com.kzxd.tjbb.serviceimpl;

import java.util.Date;
import java.util.List;

import com.kzxd.tjbb.dao.Tjbbdao;
import com.kzxd.tjbb.entity.DanWeiBean;
import com.kzxd.tjbb.entity.MingXiBean;
import com.kzxd.tjbb.entity.ShouLiYeWuBean;
import com.kzxd.tjbb.entity.TjbbBean;
import com.kzxd.tjbb.entity.YueDuBean;
import com.kzxd.tjbb.service.TjbbMsg;

public class TjjbbMsgimpl implements TjbbMsg {
	
	private Tjbbdao tjdaoimpl;

	public Tjbbdao getTjdaoimpl() {
		return tjdaoimpl;
	}

	public void setTjdaoimpl(Tjbbdao tjdaoimpl) {
		this.tjdaoimpl = tjdaoimpl;
	}

	public boolean add(TjbbBean tjbbBean) {

		return tjdaoimpl.add(tjbbBean);
	}

	public List<TjbbBean> findAllByPersonid(String personid,String bjzt) {

		return tjdaoimpl.findAllByPersonid(personid,bjzt);
	}

	public List<TjbbBean> findAllByPersonid2(String personid, String bjzt, String zxdw,
			Date sdate, Date edate) {

		return tjdaoimpl.findAllByPersonid2(personid, bjzt, zxdw, sdate, edate);
	}

	public List<TjbbBean> findAllByPersonid6(String personid,String bjzt, Date sdate, Date edate) {

		return tjdaoimpl.findAllByPersonid6(personid,bjzt, sdate,edate);
	}

	public boolean updateTjbbByUuid(TjbbBean tjbbBean) {
		return tjdaoimpl.updateTjbbByUuid(tjbbBean);
	}

	public TjbbBean getByUuid(String uuid) {
		return tjdaoimpl.getByUuid(uuid);
	}

	public List findAllByYue(Date yst, Date yet,Date nst) {
		return tjdaoimpl.findAllByYue(yst, yet, nst);
	}

	public YueDuBean findAllByNian(String personid, Date et, Date st) {
		return tjdaoimpl.findAllByNian(personid, et, st);
	}

	public List<TjbbBean> findAllByPersonid3(String personid, String bjzt, String zxdw,
			Date sdate) {

		return tjdaoimpl.findAllByPersonid3(personid, bjzt, zxdw, sdate);
	}

	public List<TjbbBean> findAllByPersonid4(String personid, String bjzt, String zxdw) {
		return tjdaoimpl.findAllByPersonid4(personid, bjzt, zxdw);
	}

	public List<TjbbBean> findAllByPersonid5(String personid, String bjzt, String zxdw,
			Date edate) {
		return tjdaoimpl.findAllByPersonid5(personid, bjzt, zxdw, edate);
	}

	public List<TjbbBean> findAllByPersonid7(String personid, String bjzt, Date edate) {
		return tjdaoimpl.findAllByPersonid7(personid, bjzt, edate);
	}

	public List<TjbbBean> findAllByPersonid8(String personid, String bjzt, Date sdate) {
		return tjdaoimpl.findAllByPersonid8(personid, bjzt, sdate);
	}

	public List<TjbbBean> findAllByPersonid12(String bjzt) {
		return tjdaoimpl.findAllByPersonid12(bjzt);
	}

	public List<TjbbBean> findAllByPersonid22(String bjzt, String personid, Date sdate,
			Date edate) {
		return tjdaoimpl.findAllByPersonid22(bjzt, personid, sdate, edate);
	}

	public List<TjbbBean> findAllByPersonid32(String bjzt, String personid, Date sdate) {
		return tjdaoimpl.findAllByPersonid32(bjzt, personid, sdate);
	}

	public List<TjbbBean> findAllByPersonid42(String bjzt, String personid) {
		return tjdaoimpl.findAllByPersonid42(bjzt, personid);
	}

	public List<TjbbBean> findAllByPersonid52(String bjzt, String personid, Date edate) {
		return tjdaoimpl.findAllByPersonid52(bjzt, personid, edate);
	}

	public List<TjbbBean> findAllByPersonid62(String bjzt, Date sdate, Date edate) {
		return tjdaoimpl.findAllByPersonid62(bjzt, sdate, edate);
	}

	public List<TjbbBean> findAllByPersonid72(String bjzt, Date edate) {
		return tjdaoimpl.findAllByPersonid72(bjzt, edate);
	}

	public List<TjbbBean> findAllByPersonid82(String bjzt, Date sdate) {
		return tjdaoimpl.findAllByPersonid82(bjzt, sdate);
	}

	public List findMingXi(Date st, Date et) {
		return tjdaoimpl.findMingXi(st, et);
	}

	public List findMingXiJdnr(Date st, Date et) {
		return tjdaoimpl.findMingXiJdnr(st, et);
	}

	public List findMingXiBjnr(Date st, Date et) {
		return tjdaoimpl.findMingXiBjnr(st, et);
	}

	public List<DanWeiBean> danWeiXiaLa() {
		return tjdaoimpl.danWeiXiaLa();
	}

	public MingXiBean zongJiMingXi(Date st, Date et) {
		return tjdaoimpl.zongJiMingXi(st, et);
	}

	public YueDuBean yueDuHeJi(Date yst, Date yet, Date nst) {
		return tjdaoimpl.yueDuHeJi(yst, yet, nst);
	}

	public YueDuBean nianDuHeJi(String personid, Date nst, Date net) {
		return tjdaoimpl.nianDuHeJi(personid, nst, net);
	}

	public YueDuBean findAllByNian2(String personid, Date et, Date st) {
		return tjdaoimpl.findAllByNian2(personid, et, st);
	}

	public ShouLiYeWuBean yueDuYeWu(Date yst, Date yet) {
		return tjdaoimpl.yueDuYeWu(yst, yet);
	}

	public ShouLiYeWuBean nianDuYeWu(Date yst, Date yet) {
		return tjdaoimpl.nianDuYeWu(yst, yet);
	}

	public List<TjbbBean> ziXunFindAllByPersonid(String personid) {
		return tjdaoimpl.ziXunFindAllByPersonid(personid);
	}

	public List<TjbbBean> ziXunHuiZongFindAllByTjdw() {
		return tjdaoimpl.ziXunHuiZongFindAllByTjdw();
	}

	public MingXiBean findByPersonid(String personid) {
		return tjdaoimpl.findByPersonid(personid);
	}	
}
