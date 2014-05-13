package com.kzxd.rcap.service;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.entity.sxtx;

public interface sxtxMsg {
    
	public void save(List<sxtx> beans);

	public void deletebyid(String uuid) throws DAOException;
	
	public List<sxtx> findbyid(String personid);
}
