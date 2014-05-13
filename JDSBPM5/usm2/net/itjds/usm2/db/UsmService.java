package net.itjds.usm2.db;

import java.util.List;

import net.itjds.usm2.USMException;
import net.itjds.usm2.UsmProxy;

public interface UsmService {

	public Object getUsmByKey(String pkName)throws USMException;
	public Class getDefaultProxyClass();
	
	public UsmProxy getUsmProxyByKey(String pkName)throws USMException;	
	public Object getUsmByKey(String pkName,Class clazz) throws USMException;
	public List getUsmWhere(String where)throws USMException;

	public List getUsmWhere(String where, long start, long limit)throws USMException;
	
	 public  List loadAll() throws USMException;

	public int deleteByKey(String uuid) throws USMException;
	
	public int delete(UsmProxy pObject) throws USMException;

	public int deleteByWhere(String where) throws USMException;

	public List save(List<UsmProxy> pObjects) throws USMException;

	public  void save(UsmProxy pObject) throws USMException;
	
	public  UsmProxy create() throws USMException;
	
	


}