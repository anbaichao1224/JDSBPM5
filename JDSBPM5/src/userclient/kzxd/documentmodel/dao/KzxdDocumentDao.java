package kzxd.documentmodel.dao;

import java.util.List;

import kzxd.documentmodel.entity.KZXDDocumentModel;

/**
 * 
 * @author 李洋
 *	dao接口
 */
public interface KzxdDocumentDao {
	
	//通过部门名称去找模版的方法
	public void save(List<KZXDDocumentModel> beans);

	public List<KZXDDocumentModel> findByDept(String[] org);
	
	public List<KZXDDocumentModel> findByDeptnull(String[] org);

	public KZXDDocumentModel findByUUID(String uuid);
	
	public void deletebyid(String uuid);
}
