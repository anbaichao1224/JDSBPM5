package com.kzxd.rcap.dao;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.entity.gzrdy;

/**
 * 
 * @author tanrui
 *	dao�ӿ�
 */
public interface gzrdydao {
	
	public void save(List<gzrdy> beans);
	
	public void deletebyid(String uuid) throws DAOException;
	
	public List<gzrdy> findbyid();

}
