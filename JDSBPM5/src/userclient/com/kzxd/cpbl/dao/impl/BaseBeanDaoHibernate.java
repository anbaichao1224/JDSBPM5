package com.kzxd.cpbl.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.kzxd.cpbl.dao.BaseBeanDao;
import com.kzxd.cpbl.module.BaseBean;

public class BaseBeanDaoHibernate extends
		GenericDaoHibernate<BaseBean, String> implements
		BaseBeanDao {

	public BaseBeanDaoHibernate() {
		super(BaseBean.class);
	}

	public List<BaseBean> getAll() {
		return super.getSession().createSQLQuery("from BaseBean e").list();
	}

	public BaseBean findByProcessInstId(String processInstId) {
		List<BaseBean> bbList=null;
		BaseBean bb= null;
		StringBuffer hql = new StringBuffer("from BaseBean bean where bean.processinstid=?");
		Query query = super.getSession().createQuery(hql.toString());
		query.setParameter(0, processInstId);
		bbList = query.list();
		System.out.println("++++++++++++++++++++++++bblist:"+bbList);
		if(bbList!=null){
			bb=bbList.get(0);
			System.out.println("=================bb:"+bb);
		}
		return bb;
	}

	public void delete(BaseBean bb) {
		super.getSession().delete(bb);
		
	}

	public BaseBean findByAId(String daid) {
		Query query = super.getSession().createQuery("from BaseBean bean where bean.activityinstid = ? and bean.status = 'running'");
		query.setParameter(0, daid);
		return (BaseBean)query.uniqueResult();
	}

	public List<BaseBean> findListByProcessInstId(String processInstId) {
		StringBuffer hql = new StringBuffer("from BaseBean bean where bean.processinstid=?");
		Query query = super.getSession().createQuery(hql.toString());
		query.setParameter(0, processInstId);
		return (List<BaseBean>) query.list();
	}
	
	

}
