package com.kzxd.cpbl.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import com.kzxd.cpbl.dao.PbDao;
import com.kzxd.cpbl.module.PbBean;

public class PbDaoHibernate extends GenericDaoHibernate<PbBean, String>
		implements PbDao {

	public PbDaoHibernate() {
		super(PbBean.class);
	}

	public List<PbBean> getAll() {
		return super.getSession().createSQLQuery("from PbBean e").list();
	}

	public List<PbBean> findByZb(String hql, int start, int limit,
			List<String> list) {
		Session session = super.getSession();
		Query query = session.createQuery(hql).setFirstResult(start)
				.setMaxResults(limit);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
		return query.list();
	}

	public int getCount(String hql, List<String> list) {
		Session session = super.getSession();
		Query query = session.createQuery(hql);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
		Long count = (Long) query.uniqueResult();
		return count.intValue();
	}

	public void update(PbBean cb) {
		super.getSession().update(cb);
	}

	public PbBean select(String processInstId, String activityInstId) {
		StringBuffer hql = new StringBuffer("from PbBean bean where bean.processinstid=? and bean.activityinstid=?");
		Query query = super.getSession().createQuery(hql.toString());
		query.setParameter(0, processInstId);
		query.setParameter(1, activityInstId);
		//BaseBean bb = (BaseBean)query.uniqueResult();
		return (PbBean)query.uniqueResult();
	}

}
