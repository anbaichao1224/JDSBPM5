package com.kzxd.tjbb.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kzxd.tjbb.dao.SKSXMLDao;
import com.kzxd.tjbb.entity.CKSXMLBean;

public class CKSXMLDaoimpl extends HibernateDaoSupport implements SKSXMLDao {

	public List<CKSXMLBean> finAllByBuMen(String bumen) {
		List<CKSXMLBean> cksxmllist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from CKSXMLBean bean where bean.fwck=?");
			query.setParameter(0, bumen);
			cksxmllist = query.list();
			if (cksxmllist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return cksxmllist;
	}

	public boolean add(CKSXMLBean cksxmlBean) {
		try {
			super.getHibernateTemplate().save(cksxmlBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<CKSXMLBean> shiXiangFindByPersonid(String personid) {
		List<CKSXMLBean> cksxmllist = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery("from CKSXMLBean bean where bean.personid=?");
			query.setParameter(0, personid);
			cksxmllist = query.list();
			if (cksxmllist != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return cksxmllist;
	}

	public boolean delete(CKSXMLBean cksxmlBean) {
		 try{
				 super.getHibernateTemplate().delete(cksxmlBean);
			    return  true;
	    	 }catch(Exception e){
	    		 e.printStackTrace();
	    		return false;
	    	 }
	}

	public CKSXMLBean getByUuid(String id) {
		CKSXMLBean cksxmlBean = null;
   	    Session session = null;
   	 try{
   	 session = super.getHibernateTemplate().getSessionFactory().openSession();
   	 Query query = session.createQuery("from CKSXMLBean bean where bean.uuid = ?");
   	 query.setParameter(0, id);
   	 List<CKSXMLBean> cksxmllist = query.list();
   	 cksxmlBean=(CKSXMLBean)(cksxmllist.get(0));
   	
   	 } catch(Exception e){
   		 e.printStackTrace();
   		 
   	 }finally{
   	   	 session.close();
   	 }

   	 return cksxmlBean;
	}

}
