package com.kzxd.changepssword.service;

import java.util.List;

import com.kzxd.changepssword.entity.personaccounta;

public interface ChangePsswordServicea {
	

    public List<personaccounta> getPssword(String username,String oldpassword);
	
	public void updataPssword(String newpsssword,String uuid);

}
