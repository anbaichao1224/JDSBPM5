package com.kzxd.rcap.service;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.entity.gzrdy;

public interface gzrdyMsg {
    
	public void save(List<gzrdy> beans);

	public void deletebyid(String uuid) throws DAOException;
	
	public List<gzrdy> findbyid();
}
