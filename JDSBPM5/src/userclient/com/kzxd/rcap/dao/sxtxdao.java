package com.kzxd.rcap.dao;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.entity.sxtx;

/**
 * 
 * @author tanrui
 *	dao½Ó¿Ú
 */
public interface sxtxdao {
	
	public void save(List<sxtx> beans);
	
	public void deletebyid(String uuid) throws DAOException;
	
	public List<sxtx> findbyid(String personid);

}
