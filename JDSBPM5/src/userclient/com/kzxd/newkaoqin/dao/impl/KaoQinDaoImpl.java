package com.kzxd.newkaoqin.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.kzxd.newkaoqin.dao.KaoQinDao;
import com.kzxd.newkaoqin.entity.ChuangKouTianShuBean;
import com.kzxd.newkaoqin.entity.KaoQinBaoBiaoBean;
import com.kzxd.newkaoqin.entity.KaoQinBean;

public class KaoQinDaoImpl extends HibernateDaoSupport implements KaoQinDao{

	public boolean add(KaoQinBean kqBean) {
		try {
			super.getHibernateTemplate().save(kqBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<KaoQinBean> findAllKaoQin(String sql) {
		List<KaoQinBean> kqList = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			Query query = session
					.createQuery(sql);
			kqList = query.list();
			if (kqList != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return kqList;
	}

	public KaoQinBean findByRq(String personid,Date rq,String czlx,String sjlx) {
		KaoQinBean kqdBean = null;
   	    Session session = null;
   	    try{
   	    	session = super.getHibernateTemplate().getSessionFactory().openSession();
   	    	Query query = session.createQuery("from KaoQinBean bean where bean.rq=? and bean.czlx = ? and bean.sjlx = ? and bean.personid=?");
   	    	query.setParameter(0, rq);
   	    	query.setParameter(1, czlx);
   	    	query.setParameter(2, sjlx);
   	    	query.setParameter(3, personid);
   	    	List<KaoQinBean> kqList = query.list();
   	    	if(kqList.size()>0){
   	    		kqdBean=(KaoQinBean)(kqList.get(0));
   	    	}else{
   	    		return kqdBean;
   	    	}
   	    } catch(Exception e){
   	    	e.printStackTrace();  		 
   	    }finally{
   	   	 	session.close();
   	    }

   	    return kqdBean;
	}
	
	

	public boolean updateDate(KaoQinBean kqBean) {
		Session session = null;
		try {
			super.getHibernateTemplate().update(kqBean);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public KaoQinBean findByUuid(String uuid) {
		KaoQinBean kqdBean = null;
   	    Session session = null;
   	    try{
   	    	session = super.getHibernateTemplate().getSessionFactory().openSession();
   	    	Query query = session.createQuery("from KaoQinBean bean where bean.uuid=?");
   	    	query.setParameter(0, uuid);
   	    	List<KaoQinBean> kqList = query.list();
   	    	if(kqList.size()>0){
   	    		kqdBean=(KaoQinBean)(kqList.get(0));
   	    	}else{
   	    		return kqdBean;
   	    	}
   	    } catch(Exception e){
   	    	e.printStackTrace();  		 
   	    }finally{
   	   	 	session.close();
   	    }

   	    return kqdBean;
	}

	public List<KaoQinBaoBiaoBean> findKaoQinBaoBiao(Date sdate, Date edate,List<ChuangKouTianShuBean> cktslist) {
		List<KaoQinBaoBiaoBean> kaoqinList = null;
		List<KaoQinBaoBiaoBean> kqztList = null;
		Session session = null;
		try {
			session = super.getHibernateTemplate().getSessionFactory()
					.openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.personid personid,"
							+ "nvl((select count(*) from FDT_OA_QINGJIAMINGXI t1 where t1.personid=p.personid and t1.qingjiariqi between ? and ? and t1.qjlx='公假'),0) gj,"
							+ "nvl((select count(*) from FDT_OA_QINGJIAMINGXI t1 where t1.personid=p.personid and t1.qingjiariqi between ? and ? and t1.qjlx<>'公假'),0) bsj"
							+" from FDT_OA_CKPX p left join FDT_OA_QINGJIAMINGXI t  on p.personid=t.personid group by p.personid,p.kqpx order by p.kqpx").setResultTransformer(Transformers.aliasToBean(KaoQinBaoBiaoBean.class));
			query.addScalar("personid");
			query.addScalar("gj", Hibernate.INTEGER);
			query.addScalar("bsj", Hibernate.INTEGER);
			
			query.setDate(0, sdate);
			query.setDate(1, edate);
			query.setDate(2, sdate);
			query.setDate(3, edate);
		    
			kaoqinList = query.list();
			if (kaoqinList != null) {
			};
			
			SQLQuery query1 = (SQLQuery) session.createSQLQuery("select p.personid personid,"
					+ "nvl((select count(*) from FDT_OA_KAOQIN t1 where t1.personid=p.personid and t1.rq between ? and ? and t1.czlx='签到'),0) qq,"
					+ "nvl((select count(*) from FDT_OA_KAOQIN t1 where t1.personid=p.personid and t1.rq between ? and ? and t1.zt='迟到'),0) cd,"
					+ "nvl((select count(*) from FDT_OA_KAOQIN t1 where t1.personid=p.personid and t1.rq between ? and ? and t1.czlx='签退' and t1.zt='正常'),0) zt"
					+" from FDT_OA_CKPX p left join FDT_OA_QINGJIAMINGXI t  on p.personid=t.personid group by p.personid,p.kqpx order by p.kqpx").setResultTransformer(Transformers.aliasToBean(KaoQinBaoBiaoBean.class));
			query1.addScalar("personid");
			query1.addScalar("qq", Hibernate.INTEGER);
			query1.addScalar("cd", Hibernate.INTEGER);
			query1.addScalar("zt", Hibernate.INTEGER);
	
			query1.setDate(0, sdate);
			query1.setDate(1, edate);
			query1.setDate(2, sdate);
			query1.setDate(3, edate);
			query1.setDate(4, sdate);
			query1.setDate(5, edate);
			
			kqztList = query1.list();
			if (kqztList != null) {
			};
			
			for(int i=0;i<kqztList.size();i++){
				for(int j=0;j<cktslist.size();j++){
					if(kqztList.get(i).getPersonid().equals(cktslist.get(j).getPersonid())){
						int kqqqcs=cktslist.get(j).getTs()*2-kqztList.get(i).getQq();
						int kqztcs=kqztList.get(i).getQq()-kqztList.get(i).getZt();
						kqztList.get(i).setQq(kqqqcs);
						kqztList.get(i).setZt(kqztcs);
					}
				}
			}
			
			for(int i=0;i<kaoqinList.size();i++){
				for(int j=0;j<kqztList.size();j++){
					if(kaoqinList.get(i).getPersonid().equals(kqztList.get(j).getPersonid())){
						kaoqinList.get(i).setQq(kqztList.get(j).getQq());
						kaoqinList.get(i).setCd(kqztList.get(j).getCd());
						kaoqinList.get(i).setZt(kqztList.get(j).getZt());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if(session != null){
				session.close();
			}
		}
		return kaoqinList;
	}

	public KaoQinBaoBiaoBean findByPersonid(String personid) {
		KaoQinBaoBiaoBean kqbbBean = null;
   	    Session session = null;
   	    try{
   	    	session = super.getHibernateTemplate().getSessionFactory().openSession();
			SQLQuery query = (SQLQuery) session.createSQLQuery("select p.personid personid,p.cnname ckmc"
					+" from RO_PERSON p where p.personid = ?").setResultTransformer(Transformers.aliasToBean(KaoQinBaoBiaoBean.class));
			query.addScalar("personid");
			query.addScalar("ckmc");
	
			query.setString(0, personid);

   	    	List<KaoQinBaoBiaoBean> kqbbList = query.list();
   	    	if(kqbbList.size()>0){
   	    		kqbbBean=(KaoQinBaoBiaoBean)(kqbbList.get(0));
   	    	}else{
   	    		return kqbbBean;
   	    	}
   	    } catch(Exception e){
   	    	e.printStackTrace();  		 
   	    }finally{
   	   	 	session.close();
   	    }

   	    return kqbbBean;
	}

}
