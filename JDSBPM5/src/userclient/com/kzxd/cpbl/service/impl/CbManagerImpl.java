package com.kzxd.cpbl.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;

import com.kzxd.cpbl.dao.CbDao;
import com.kzxd.cpbl.module.CbBean;
import com.kzxd.cpbl.service.CbManager;

public class CbManagerImpl extends GenericManagerImpl<CbBean, String> implements
		CbManager {
	CbDao cbDao;

	public CbManagerImpl(CbDao cbDao) {
		super(cbDao);
		this.cbDao = cbDao;
	}

	public List<CbBean> findAll() {
		return this.cbDao.getAll();
	}

	/**
	 * 查询类表数据
	 */
	public List<CbBean> findByZb(int start, int limit, String ttitle,
			String startdate, String enddate) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		hql.append("from CbBean c where c.status = 'suspended'");
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
		return cbDao.findByZb(hql.toString(), start, limit, list);
	}

	/**
	 * 得到总数
	 */
	public int getCount(String ttitle, String startdate, String enddate) {
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		hql
				.append("select count(*) from CbBean c where c.status = 'suspended'");
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
		return cbDao.getCount(hql.toString(), list);
	}

	public List<CbBean> findByBjcb(int start, int limit, String ttitle,
			String startdate, String enddate) {
		// TODO Auto-generated method stub
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		hql.append("from CbBean c where c.status != 'suspended'");
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
		return cbDao.findByZb(hql.toString(), start, limit, list);
	}

	public int getBjCount(String ttitle, String startdate, String enddate) {
		StringBuffer hql = new StringBuffer();
		List<String> list = new ArrayList<String>();
		hql
				.append("select count(*) from CbBean c where c.status != 'suspended'");
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
		return cbDao.getCount(hql.toString(), list);
	}

	public void update(CbBean cb) {
		cbDao.update(cb);
	}

	public CbBean select(String processInstId, String activityInstId) {
		return cbDao.select(processInstId, activityInstId);
	}
}