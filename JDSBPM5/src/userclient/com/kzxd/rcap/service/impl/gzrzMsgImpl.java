package com.kzxd.rcap.service.impl;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.dao.gzrzdao;
import com.kzxd.rcap.dao.impl.gzrzdaoimpl;
import com.kzxd.rcap.entity.gzrz;
import com.kzxd.rcap.service.gzrzMsg;

public class gzrzMsgImpl implements gzrzMsg{
    
	gzrzdao zdao = new gzrzdaoimpl();

	public void deletebyid(String uuid) throws DAOException{
		zdao.deletebyid(uuid);
	}

	public void save(List<gzrz> beans) {
		zdao.save(beans);
	}

	public List<gzrz> findbyid(String personid) {
		List<gzrz> gzrz = zdao.findbyid(personid);
		return gzrz;
	}
	
	

	
	
	

}
