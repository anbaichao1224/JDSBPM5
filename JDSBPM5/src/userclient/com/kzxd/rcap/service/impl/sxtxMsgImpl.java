package com.kzxd.rcap.service.impl;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.dao.sxtxdao;
import com.kzxd.rcap.dao.impl.sxtxdaoimpl;
import com.kzxd.rcap.entity.sxtx;
import com.kzxd.rcap.service.sxtxMsg;

public class sxtxMsgImpl implements sxtxMsg{
    
	sxtxdao zdao = new sxtxdaoimpl();

	public void deletebyid(String uuid) throws DAOException{
		zdao.deletebyid(uuid);
	}

	public void save(List<sxtx> beans) {
		zdao.save(beans);
	}

	public List<sxtx> findbyid(String personid) {
		List<sxtx> sxtx = zdao.findbyid(personid); 
		return sxtx;
	}

}
