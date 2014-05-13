package com.kzxd.rcap.service;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.entity.gzrz;

public interface gzrzMsg {
    
	public void save(List<gzrz> beans);

	public void deletebyid(String uuid) throws DAOException;
	
	public List<gzrz> findbyid(String personid);
	
	
	
	
}
