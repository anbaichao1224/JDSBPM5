package com.kzxd.changepssword.daohiberate;


import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.kzxd.changepssword.entity.personaccounta;


public class HibernateUtil {
	private static SessionFactory sessionFactory;
	private static Object mmr;

	 public HibernateUtil(){}


	
	static{
		Configuration cfg = new Configuration().addClass(personaccounta.class );
		
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		}
		/*
		* 获取Hibernate SessionFactory
		* @return
		*/
		public static SessionFactory getSessionFactory() {
		   return sessionFactory;
		}
		/*
		* 获取Hibernate Session
		* @return
		*/
		public static Session getSession(){
		   return sessionFactory.openSession();
		}
		
		public static List<personaccounta> getList(String hql){
			   List<personaccounta> list=null;
			   Session s=null;
			   Transaction ts=null;

			   try {
			    s=getSession();
			    ts=s.beginTransaction();
			    Query query=s.createQuery(hql);
			     list=query.list();
			     ts.commit();

			   } catch (Exception e) {
				   if (ts!=null) {
					     ts.rollback();
				   }
			    System.err.println("Hibernate Get List Object Error:\n"+e.getMessage());
			    list=null;
			   }finally{
			    if (s!=null) {
			     s.close();
			    }
			   }
			   return list;
			}

		public static int updateObject(Object obj){
			  int status=-1;

			   Session s=null;
			   Transaction ts=null;
			   try {
			    s=getSession();
			    ts=s.beginTransaction();
			    s.update(obj);
			    ts.commit();
			    status=1;

			   } catch (HibernateException e) {
			    if (ts!=null) {
			     ts.rollback();
			    }
			    System.err.println("Hibernate Update Object Error:\n"+e.getMessage());
			    status=-1;

			   } finally{
			    if (s!=null) {
			     s.close();
			    }
			   }
			   return status;
			}

		

		public static  Object getObject(Object obj,String uuid){
			  
			
			   Session s=null;
			   Transaction ts=null;
			   try {
			    s=getSession();
			    ts=s.beginTransaction();
			    
			    Object mmr= s.get(obj.getClass(),new String(uuid) );
			    ts.commit();
			  

			   } catch (HibernateException e) {
			    if (ts!=null) {
			     ts.rollback();
			    }
			

			   } finally{
			    if (s!=null) {
			     s.close();
			    }
			   }
			    return mmr ;
			
			}



}
