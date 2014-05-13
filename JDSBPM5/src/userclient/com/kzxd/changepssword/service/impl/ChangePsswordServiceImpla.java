package com.kzxd.changepssword.service.impl;

import java.util.List;

import com.kzxd.changepssword.dao.ChangePsswordDao;
import com.kzxd.changepssword.dao.ChangePsswordDaoa;
import com.kzxd.changepssword.dao.impl.ChangePsswordDaoImpl;
import com.kzxd.changepssword.dao.impl.ChangePsswordDaoImpll;
import com.kzxd.changepssword.entity.personaccounta;
import com.kzxd.changepssword.service.ChangePsswordServicea;

public class ChangePsswordServiceImpla implements ChangePsswordServicea {
  
	 ChangePsswordDaoa changepssworddaoa=new ChangePsswordDaoImpll() ;
	  
	public List<personaccounta> getPssword(String username, String oldpassword) {
		 StringBuffer buffer = new StringBuffer("from RO_PERSONACCOUNT C where 1=1");
		 
		  if(username!=null){
			  buffer.append("and C.userid = "+ username);
			  }

          if(oldpassword!=null)
          {buffer.append("and C.password = "+oldpassword);
          }

          String hql = buffer.toString();//µÃµ½SQL×Ö·û´®
          List<personaccounta> list = changepssworddaoa.getPssword(hql);
          
          return list;
	}

	public void updataPssword(String newpsssword, String uuid ) {
		//personaccounta personl= changepssworddaoa.getObject(person, uuid);
		personaccounta personl=new personaccounta();
		        personl.setUserid(uuid);
               personl.setPassword(newpsssword);
               changepssworddaoa.updataPssword(personl,uuid);
	}
;
}
