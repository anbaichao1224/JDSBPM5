package com.kzxd.kaoqin.dao;

import java.util.List;

import net.itjds.usm.persistence.model.Personaccount;

import com.kzxd.kaoqin.pojo.kaoqin;
import com.kzxd.kaoqin.pojo.persona;

public interface KaoQinDao {
	
	    
    public void save(kaoqin obj);

	public List<kaoqin> findBytime(String string, int start, int limit,List<String> list);
	
	public int getCounto(String string, List<String> list);//³Ùµ½

	public int getCountt(String string, List<String> list);//ÔçÍË

	public List<kaoqin> findByUsernname(String string, List<String> list);

	public void update(kaoqin qn);

	public List<persona> findBypersonid(String string, List<String> list);
	
	public void delete(List<kaoqin>list,int m);

	 

}
