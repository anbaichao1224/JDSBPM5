package com.kzxd.rcap.dao;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.entity.rcap;

/**
 * 
 * @author tanrui
 *	dao½Ó¿Ú
 */
public interface rcapdao {
	
	public void save(List<rcap> beans);
	
	public void deletebyid(String uuid) throws DAOException;
	
	public List<rcap> findbyid(String personid);
	
	

}
