package com.kzxd.rcap.service;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.entity.rcap;

public interface rcapMsg {
    
	public void save(List<rcap> beans);

	public void deletebyid(String uuid) throws DAOException;
	
	public List<rcap> findbyid(String personid);
	
}
