package com.kzxd.cpbl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;

import com.kzxd.cpbl.dao.PbDao;
import com.kzxd.cpbl.module.PbBean;
import com.kzxd.cpbl.service.PbManager;

public class PbManagerImpl extends
		GenericManagerImpl<PbBean, String> implements PbManager{
	PbDao pbDao;

	public PbManagerImpl(PbDao pbDao) {
		super(pbDao);
		this.pbDao = pbDao;
	}

	public List<PbBean> findAll() {
		return this.pbDao.getAll();
	}

	public List<PbBean> findByBjcb(int start, int limit, String ttitle,
			String startdate, String enddate) {
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		hql.append("from PbBean c where c.status != 'suspended'");
		if (ttitle != null && ttitle.length() > 0) {
			hql.append(" and c.title like ?");
			list.add("%" + ttitle + "%");
		}
		if (startdate != null && startdate.length() > 0) {
			hql
					.append(" and c.arriveTime >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(startdate);
		}
		if (enddate != null && enddate.length() > 0) {
			hql
					.append(" and c.arriveTime <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(enddate);
		}
		hql.append(" order by c.arriveTime DESC");
		return pbDao.findByZb(hql.toString(), start, limit, list);
	}

	public List<PbBean> findByZb(int start, int limit, String ttitle,
			String startdate, String enddate) {
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		hql.append("from PbBean c where c.status = 'suspended'");
		if (ttitle != null && ttitle.length() > 0) {
			hql.append(" and c.title like ?");
			list.add("%" + ttitle + "%");
		}
		if (startdate != null && startdate.length() > 0) {
			hql
					.append(" and c.arriveTime >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(startdate);
		}
		if (enddate != null && enddate.length() > 0) {
			hql
					.append(" and c.arriveTime <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(enddate);
		}
		hql.append(" order by c.arriveTime DESC");
		return pbDao.findByZb(hql.toString(), start, limit, list);
	}

	public int getBjCount(String ttitle, String startdate, String enddate) {
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		hql
				.append("select count(*) from PbBean c where c.status != 'suspended'");
		if (ttitle != null && ttitle.length() > 0) {
			hql.append(" and c.title like ?");
			list.add("%" + ttitle + "%");
		}
		if (startdate != null && startdate.length() > 0) {
			hql
					.append(" and c.arriveTime >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(startdate);
		}
		if (enddate != null && enddate.length() > 0) {
			hql
					.append(" and c.arriveTime <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(enddate);
		}
		return pbDao.getCount(hql.toString(), list);
	}

	public int getCount(String ttitle, String startdate, String enddate) {
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		hql
				.append("select count(*) from PbBean c where c.status = 'suspended'");
		if (ttitle != null && ttitle.length() > 0) {
			hql.append(" and c.title like ?");
			list.add("%" + ttitle + "%");
		}
		if (startdate != null && startdate.length() > 0) {
			hql
					.append(" and c.arriveTime >= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(startdate);
		}
		if (enddate != null && enddate.length() > 0) {
			hql
					.append(" and c.arriveTime <= to_date(?,'yyyy-mm-dd hh24:mi:ss')");
			list.add(enddate);
		}
		return pbDao.getCount(hql.toString(), list);
	}

	public void update(PbBean cb) {
		pbDao.update(cb);
	}

	public PbBean select(String processInstId, String activityInstId) {
		// TODO Auto-generated method stub
		return pbDao.select(processInstId, activityInstId);
	}
}