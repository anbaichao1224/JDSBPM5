package com.kzxd.tjbb.serviceimpl;

import java.util.List;

import com.kzxd.tjbb.dao.SKSXMLDao;
import com.kzxd.tjbb.entity.CKSXMLBean;
import com.kzxd.tjbb.service.CKSXMLMsg;

public class CKSXMLMsgimpl implements CKSXMLMsg {
	
	private SKSXMLDao CKSXMLDaoimpl;

	public List<CKSXMLBean> finAllByBuMen(String bumen) {
		return CKSXMLDaoimpl.finAllByBuMen(bumen);
	}

	public boolean add(CKSXMLBean cksxmlBean) {
		return CKSXMLDaoimpl.add(cksxmlBean);
	}

	public List<CKSXMLBean> shiXiangFindByPersonid(String personid) {
		return CKSXMLDaoimpl.shiXiangFindByPersonid(personid);
	}

	public boolean delete(CKSXMLBean cksxmlBean) {
		return CKSXMLDaoimpl.delete(cksxmlBean);
	}

	public CKSXMLBean getByUuid(String id) {
		return CKSXMLDaoimpl.getByUuid(id);
	}

	public SKSXMLDao getCKSXMLDaoimpl() {
		return CKSXMLDaoimpl;
	}

	public void setCKSXMLDaoimpl(SKSXMLDao daoimpl) {
		CKSXMLDaoimpl = daoimpl;
	}

}
