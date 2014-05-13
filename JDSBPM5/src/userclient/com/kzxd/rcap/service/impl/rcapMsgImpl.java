package com.kzxd.rcap.service.impl;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.dao.rcapdao;
import com.kzxd.rcap.dao.impl.rcapdaoimpl;
import com.kzxd.rcap.entity.rcap;
import com.kzxd.rcap.service.rcapMsg;

public class rcapMsgImpl implements rcapMsg{
    
	rcapdao zdao = new rcapdaoimpl();

	public void deletebyid(String uuid) throws DAOException{
		zdao.deletebyid(uuid);
	}

	public void save(List<rcap> beans) {
		zdao.save(beans);
	}

	public List<rcap> findbyid(String personid) {
		List<rcap> rcap = zdao.findbyid(personid);
		return rcap;
	}
	
   

}
