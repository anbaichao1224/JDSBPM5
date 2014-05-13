package com.kzxd.kaoqin.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration; 
import com.kzxd.kaoqin.pojo.kaoqin;




public class HibernateUtil {
      private static Log log=LogFactory.getLog( HibernateUtil.class) ;
      private static Configuration configuration;
      private static SessionFactory sessionFactory;
      static{
    	 configuration = new Configuration();
    	 configuration.addClass(kaoqin.class);
    	 SessionFactory sessionFactory = configuration.buildSessionFactory(); 
      }
      
      /*
		* 获取Hibernate SessionFactory
		*
		*/
      public static SessionFactory getSessionFactory() {
    	  return sessionFactory;
    	 }

      public static Session getSession(){
		   return sessionFactory.openSession();
		}	
      //根据sql获取实体
      public static List<kaoqin> getList(String hql,List<String> list){
		 
		   Session s=null;
		 
		   List<kaoqin>mo=null; 
		
		    s=getSession();
		  
		try   { Query query=s.createQuery(hql);
		    if (list != null && list.size() > 0) {
		        for (int i = 0; i < list.size(); i++) {
			          query.setParameter(i, list.get(i));
		                                              }
	          }
		     
		  
		    mo=query.list();
		}finally{
			if(s!=null){
				s.close();
			}
		}
		return mo;
		 
		}
      public static void addObject(Object entity){
    	  Session s=null;
    	  Transaction tx=null;
    	  try{
    	   s=HibernateUtil.getSession();
    	   tx=s.beginTransaction();
    	   s.save(entity);
    	   tx.commit();
    	  
    	  }catch(HibernateException e){
    	   if(tx!=null)
    	    tx.rollback();
    	   throw e;
    	  }finally{
    	   if(s!=null)
    	    s.close();
    	  }
    	 }

    	 public static void update(Object entity){
    	  Session s=null;
    	  Transaction tx=null;
    	  try{
    	   s=HibernateUtil.getSession();
    	   tx=s.beginTransaction();
    	   s.update(entity);
    	   tx.commit();
    	  
    	  }catch(HibernateException e){
    	   if(tx!=null)
    	    tx.rollback();
    	   throw e;
    	  }finally{
    	   if(s!=null)
    	    s.close();
    	  }
    	 }
    	 
    	 public static void delete(Object entity){
    	  Session s=null;
    	  Transaction tx=null;
    	  try{
    	   s=HibernateUtil.getSession();
    	   tx=s.beginTransaction();
    	   s.delete(entity);
    	   tx.commit();
    	  
    	  }catch(HibernateException e){
    	   if(tx!=null)
    	    tx.rollback();
    	   throw e;
    	  }finally{
    	   if(s!=null)
    	    s.close();
    	  }
    	 }
    	 
    	 
    	 public static Object get(Class clazz ,Serializable id){
    	  Session s=null;
    	  try{
    	   s=HibernateUtil.getSession();
    	   Object obj=s.get(clazz, id);
    	   return obj;
    	  }finally{
    	   if(s!=null)
    	    s.close();
    	  }
    	 }
    	 
    	 
    	 public static List<kaoqin> findBySql(String hql, int start, int limit,
    				List<String> list){
    		       Session  s=HibernateUtil.getSession();
     	   List<kaoqin> mo=null;
    		  try{  
    			  Query query = s.createQuery(hql).setFirstResult(start)
			                                   	.setMaxResults(limit);
		             if (list != null && list.size() > 0) {
			        for (int i = 0; i < list.size(); i++) {
				          query.setParameter(i, list.get(i));
			                                              }
		          }
              
		               mo= query.list();
		               }finally{
		            	   
		            	   if(s!=null){
		            		   s.close();
		            	   }
		               }
    		 
    		  
		              return mo; 
		 }	 
    	 
    	
    	 public static int getCount(String hql, List<String> list) {
    			
    		  Session s=HibernateUtil.getSession();
    			Query query = s.createQuery(hql);
    			if (list != null && list.size() > 0) {
    				for (int i = 0; i < list.size(); i++) {
    					query.setParameter(i, list.get(i));
    				}
    			}
    			Long count = (Long) query.uniqueResult();
    			return count.intValue();
    		}

    	 
    	 
  }


      

