package kzxd.documentmodel.servers;

import java.util.List;

import kzxd.documentmodel.entity.KZXDDocumentModel;

public interface KzxdDocumentServer {
	
	public void save(List<KZXDDocumentModel> beans);

	public List<KZXDDocumentModel> findByDept(String[] org);
	
	public List<KZXDDocumentModel> findByDeptnull(String[] org);
	
	public KZXDDocumentModel findByUUID(String uuid);
	
	public void deletebyid(String uuid);
}
