package com.kzxd.changepssword.dao.impl;

import java.util.List;


import com.kzxd.changepssword.dao.ChangePsswordDaoa;
import com.kzxd.changepssword.daohiberate.HibernateUtil;
import com.kzxd.changepssword.daohiberate.HibernaterUtilFactory;

import com.kzxd.changepssword.entity.personaccounta;

public class ChangePsswordDaoImpll implements ChangePsswordDaoa{
	
	
	   HibernateUtil util=new HibernateUtil();
	   public List<personaccounta> getPssword(String hql) {
		
		//HibernateUtil util= HibernaterUtilFactory.getHibernaterUtil();
		List<personaccounta> list=util.getList(hql);
		     
		return list;
	}

	

	public void updataPssword(Object obj,String uuid) {
		
		//HibernateUtil util= HibernaterUtilFactory.getHibernaterUtil();
		 util.updateObject(obj);
	} 



//	public personaccounta getObject(personaccounta obj, String uuid) {
	//HibernateUtil util= HibernaterUtilFactory.getHibernaterUtil();
//		personaccounta mm= (personaccounta) util.getObject(obj, uuid);
//		return mm;
	//}




}
