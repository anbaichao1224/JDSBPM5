package com.kzxd.newkaoqin.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kzxd.newkaoqin.dao.KaoQinShiJianDao;
import com.kzxd.newkaoqin.entity.KaoQinShiJianBean;

public class KaoQinShiJianDaoImpl extends HibernateDaoSupport implements KaoQinShiJianDao{

	public boolean add(KaoQinShiJianBean kqsjsdBean) {
		try {
			super.getHibernateTemplate().save(kqsjsdBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean delete(KaoQinShiJianBean kqsjsdBean) {
		 try{
			 super.getHibernateTemplate().delete(kqsjsdBean);
		    return  true;
    	 }catch(Exception e){
    		 e.printStackTrace();
    		return false;
    	 }
	}

	public List<KaoQinShiJianBean> findAll() {
		List<KaoQinShiJianBean> kqsjsdList = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from KaoQinShiJianBean bean");
			kqsjsdList = query.list();
			if (kqsjsdList != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return kqsjsdList;
	}

	public KaoQinShiJianBean getByRq(Date rq) {
		KaoQinShiJianBean kqsjsdBean = null;
   	    Session session = null;
   	    try{
   	    	session = super.getHibernateTemplate().getSessionFactory().openSession();
   	    	Query query = session.createQuery("from KaoQinShiJianBean bean where bean.ksrq <= ? and bean.jsrq >= ?");
   	    	query.setParameter(0, rq);
   	    	query.setParameter(1, rq);
   	    	List<KaoQinShiJianBean> kqsjsdList = query.list();
   	    	
   	    	if(kqsjsdList.size()>0){
   	    		kqsjsdBean=(KaoQinShiJianBean)(kqsjsdList.get(0));   	
   	    	}else{
   	    		return kqsjsdBean;
   	    	}
   	    } catch(Exception e){
   	    	e.printStackTrace();  		 
   	    }finally{
   	   	 	session.close();
   	    }

   	    return kqsjsdBean;
	}

	public boolean update(KaoQinShiJianBean kqsjsdBean) {
		Session session = null;
		try {
			super.getHibernateTemplate().update(kqsjsdBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public KaoQinShiJianBean getByUuid(String uuid) {
		KaoQinShiJianBean kqsjsdBean = null;
   	    Session session = null;
   	    try{
   	    	session = super.getHibernateTemplate().getSessionFactory().openSession();
   	    	Query query = session.createQuery("from KaoQinShiJianBean bean where bean.uuid=?");
   	    	query.setParameter(0, uuid);
   	    	List<KaoQinShiJianBean> kqsjsdList = query.list();
   	    	kqsjsdBean=(KaoQinShiJianBean)(kqsjsdList.get(0));   	
   	    } catch(Exception e){
   	    	e.printStackTrace();  		 
   	    }finally{
   	   	 	session.close();
   	    }

   	    return kqsjsdBean;
	}

}
