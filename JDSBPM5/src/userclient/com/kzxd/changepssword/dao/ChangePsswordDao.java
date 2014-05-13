package com.kzxd.changepssword.dao;

import java.util.List;

import com.kzxd.changepssword.entity.Changepssword;
import com.kzxd.changepssword.entity.personaccount;

public interface ChangePsswordDao {
	
	
	
    public List<personaccount> getPssword(String username,String oldpassword);
    public List<personaccount> findbyid(String username,String personid,String oldpassword) ;
	public void updataPssword(String newpsssword,String uuid,personaccount mt);
	 //public void update(String uuid,String newpssword);
	 public void update(String uuid,String newpssword,personaccount nt);
   
	
}
