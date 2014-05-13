package com.kzxd.rcap.service.impl;

import java.util.List;

import net.itjds.j2ee.dao.DAOException;

import com.kzxd.rcap.dao.gzrdydao;
import com.kzxd.rcap.dao.impl.gzrdydaoimpl;
import com.kzxd.rcap.entity.gzrdy;
import com.kzxd.rcap.service.gzrdyMsg;

public class gzrdyMsgImpl implements gzrdyMsg{
    
	gzrdydao zdao = new gzrdydaoimpl();

	public void deletebyid(String uuid) throws DAOException{
		zdao.deletebyid(uuid);
	}

	public void save(List<gzrdy> beans) {
		zdao.save(beans);
	}

	public List<gzrdy> findbyid() {
		List<gzrdy> gzrdy = zdao.findbyid();
		return gzrdy;
	}

}
