package com.kzxd.changepssword.service;

import java.util.List;

import com.kzxd.changepssword.entity.Changepssword;
import com.kzxd.changepssword.entity.personaccount;

public interface ChangePsswordService {
	

    public List<personaccount> getPssword(String username,String oldpassword);
    
    public List<personaccount> getPassword(String username,String personid,String oldpassword);
    
	public void updataPssword(String newpsssword,String uuid,personaccount mt);
	
	public void uupdataPssword(String uuid,String newpsssword,personaccount mt);
}
