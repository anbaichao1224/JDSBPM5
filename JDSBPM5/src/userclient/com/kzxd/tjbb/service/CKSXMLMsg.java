package com.kzxd.tjbb.service;

import java.util.List;

import com.kzxd.tjbb.entity.CKSXMLBean;

public interface CKSXMLMsg {
	
	public CKSXMLBean getByUuid(String id);
	
	public boolean delete(CKSXMLBean cksxmlBean);
	
	public boolean add(CKSXMLBean cksxmlBean);

	public List<CKSXMLBean> finAllByBuMen(String bumen);
	
	public List<CKSXMLBean> shiXiangFindByPersonid(String personid);
	
}
