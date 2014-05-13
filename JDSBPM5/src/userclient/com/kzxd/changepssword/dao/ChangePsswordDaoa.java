package com.kzxd.changepssword.dao;

import java.util.List;


import com.kzxd.changepssword.entity.personaccounta;

public interface ChangePsswordDaoa {


    public List<personaccounta> getPssword(String hql);
	
	public void updataPssword(Object obj,String uuid);
	//public personaccounta getObject(personaccounta obj,String uuid);
	
}
