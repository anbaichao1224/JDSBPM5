package com.kzxd.newkaoqin.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kzxd.newkaoqin.dao.GzrdyDao;
import com.kzxd.newkaoqin.entity.GzrdyBean;

public class GzrdyDaoImpl extends HibernateDaoSupport implements GzrdyDao{

	public List<GzrdyBean> findAllByTime(Date sdate, Date edate) {
		List<GzrdyBean> gzrdylist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from GzrdyBean bean where bean.datefrom>=? and bean.datefrom<=?");
			query.setParameter(0, sdate);
			query.setParameter(1, edate);
			gzrdylist = query.list();
			if (gzrdylist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return gzrdylist;
	}

}
