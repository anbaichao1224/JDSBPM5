package com.kzxd.changepssword.service.impl;

import java.util.List;

import com.kzxd.changepssword.dao.ChangePsswordDao;
import com.kzxd.changepssword.dao.impl.ChangePsswordDaoImpl;
import com.kzxd.changepssword.entity.Changepssword;
import com.kzxd.changepssword.entity.personaccount;
import com.kzxd.changepssword.service.ChangePsswordService;

public class ChangePsswordServiceImpl implements ChangePsswordService {
	
	 ChangePsswordDao changepssworddao=new ChangePsswordDaoImpl() ;

	public List<personaccount> getPssword(String username, String oldpassword) {
		
		return changepssworddao.getPssword(username, oldpassword);
		
	}

	public void updataPssword(String uuid,String newpssword,personaccount mt) {
		
		  changepssworddao.updataPssword(uuid,newpssword,mt);
	
	}

	public List<personaccount> getPassword(String username, String personid,String oldpassword) {
		
		return changepssworddao.findbyid(username, personid,oldpassword);
	}

	public void uupdataPssword(String uuid,String newpsssword,personaccount mt) {
		
		changepssworddao.update(uuid,newpsssword, mt);
	}

	
	
}
