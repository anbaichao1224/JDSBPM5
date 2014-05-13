package kzxd.documentmodel.servers.impl;

import java.util.ArrayList;
import java.util.List;

import kzxd.documentmodel.dao.KzxdDocumentDao;
import kzxd.documentmodel.dao.impl.KzxdDocumentDaoImpl;
import kzxd.documentmodel.entity.KZXDDocumentModel;
import kzxd.documentmodel.servers.KzxdDocumentServer;

public class KzxdDocumentServerImpl implements KzxdDocumentServer{
	
	KzxdDocumentDao kdao = new KzxdDocumentDaoImpl();
	
	public void save(List<KZXDDocumentModel> beans) {
		// TODO Auto-generated method stub
		kdao.save(beans);
	}

	public List<KZXDDocumentModel> findByDept(String[] org) {
		// TODO Auto-generated method stub
		List<KZXDDocumentModel> mlists = new ArrayList<KZXDDocumentModel>();;
		mlists = kdao.findByDept(org);
		return mlists;
	}
	
	public List<KZXDDocumentModel> findByDeptnull(String[] org) {
		// TODO Auto-generated method stub
		List<KZXDDocumentModel> mlists = new ArrayList<KZXDDocumentModel>();;
		mlists = kdao.findByDeptnull(org);
		return mlists;
	}
	
	public KZXDDocumentModel findByUUID(String uuid){
		return kdao.findByUUID(uuid);   
	}

	
	public void deletebyid(String uuid) {
		kdao.deletebyid(uuid);
	}

}
