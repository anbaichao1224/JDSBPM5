package com.kzxd.rcap.dao;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.entity.gzrz;

/**
 * 
 * @author tanrui
 *	dao�ӿ�
 */
public interface gzrzdao {
	
	public void save(List<gzrz> beans);
	
	public void deletebyid(String uuid) throws DAOException;
	
	public List<gzrz> findbyid(String personid);
	
	

}
