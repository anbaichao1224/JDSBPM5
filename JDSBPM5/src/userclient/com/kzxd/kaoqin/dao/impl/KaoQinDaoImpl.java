package com.kzxd.kaoqin.dao.impl;

import java.util.List;

import net.itjds.usm.persistence.model.Personaccount;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.kzxd.kaoqin.dao.KaoQinDao;
import com.kzxd.kaoqin.pojo.kaoqin;
import com.kzxd.kaoqin.pojo.persona;



public class KaoQinDaoImpl extends HibernateDaoSupport implements KaoQinDao {

	public KaoQinDaoImpl(SessionFactory sessionFactory) {
		super();
	
	}
	//���ݸ���
	public void update(kaoqin obj){
		try{
		   super.getHibernateTemplate().update(obj);
		}catch(Exception e){
		}
		
	}
	//���ݱ���
	public void save(kaoqin obj) { 
		     
	 try{
		super.getHibernateTemplate().save(obj);
	 }catch(Exception e){
	 }
	
	     
	}
   //ͨ��ʱ��Ѱ�ҿ��ڼ�¼
	public List<kaoqin> findBytime(String string, int start, int limit,
			List<String> list) {
		List<kaoqin>mmt=null;
		Session session = null;
		Query query=null;
		session = super.getHibernateTemplate().getSessionFactory().openSession();
		try{
		query = session.createQuery(string).setFirstResult(start)
		.setMaxResults(limit);
	
		}catch(Exception e){
		}
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
		try{
		mmt= query.list();
		}catch(Exception e){
		}finally{
			 if(session!=null){
				  session.close();
			 }
		}
		return mmt;
		
	}  
	//
	public int getCounto(String string, List<String> list) {//��óٵ����� chaxun
		Session session = null;
		session = super.getHibernateTemplate().getSessionFactory().openSession();
		java.lang.Long mo=null;
		    Query query = session.createQuery(string);//��ʵ������ ������������һ����
	    
	
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
		try{
	mo=(java.lang.Long) query.uniqueResult();//�߲����ˣ�������
	     
		}catch(Exception e){
		e.printStackTrace();
		}finally{
			if(session!=null){
				session.close();
				
			}
			
		}
	
		int v= mo.intValue();
	
		return v;
	}
	public int getCountt(String string, List<String> list) {//������˴���
		
		Session session = null;
		Long countt=null;
		session = super.getHibernateTemplate().getSessionFactory().openSession();
		Query query = session.createQuery(string);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
		try{
		countt= (Long) query.uniqueResult();
		}catch(Exception e){
			
			e.printStackTrace();
		}
		finally{
			  if(session!=null){
				  session.close();
			  }
			
		}
		return countt.intValue();
	}
	public List<kaoqin> findByUsernname(String string,List<String> list) {//ͨ���û�����ü�¼
		
		Session session = null;
		List<kaoqin>mmt=null;
		session = super.getHibernateTemplate().getSessionFactory().openSession();
		
		
	       Query  query = session.createQuery(string);
	
				
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
    try{
		mmt= query.list();
    }catch(Exception e){
    	e.printStackTrace();
    }finally{
    	
    	if(session!=null){
    		session.close();
    	}
    }
			
	
		  return mmt;
		
		
	}
    
	public List<persona> findBypersonid(String string, List<String> list) {//ͨ��personid��ü�¼
		Session session = null;
		session = super.getHibernateTemplate().getSessionFactory().openSession();
		List<persona>mmt=null;
		Query query =null;
	      try{
			query = session.createQuery(string);	
	      }catch(Exception e){
	    	  e.printStackTrace();
	      }
	
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				query.setParameter(i, list.get(i));
			}
		}
		 try{
		mmt= query.list();
		 }catch(Exception e){
			
		 }finally{
			 
			 if(session!=null){
				 session.close();
			 }
		 }
		return mmt;
	}
	
	
	
	
	public KaoQinDaoImpl() {
		super();
	}
	public void delete(List<kaoqin> list,int m){
		Session session =null;
		session=super.getSessionFactory().openSession();
		for(int i=0;i<=list.size();i++){
			
			if(i!=m){
				session.delete(list.get(i));
				
				break;
				}
			
		}
		
		
	}
	}
	
	