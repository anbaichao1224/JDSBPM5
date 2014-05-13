package net.itjds.fdt.db.fdtoafwbl.impl;

import net.itjds.bpm.data.xmlproxy.EsbBeanAnnotation;
import net.itjds.bpm.data.xmlproxy.MapdaoBeanAnnotation;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;

import java.util.Map;
import java.util.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.usm2.*;
import net.itjds.usm2.constants.*;
import net.itjds.fdt.db.fdtoafwbl.inner.*;
import net.itjds.fdt.db.fdtoafwbl.database.*;
import net.itjds.fdt.db.fdtoafwbl.proxy.*;
import net.itjds.usm2.db.*;
import net.itjds.usm2.db.util.*;
import net.itjds.fdt.db.fdtoafwbl.*;
/**
 * <p>
 * Title: USM
 * </p>
 * <p>
 * Description: FDT_OA_FWBL
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: www.justdos.net
 * </p>
 * 
 * @author usm2
 * @version 1.0
 */
	@EsbBeanAnnotation(id="FdtOaFwblUsmService",
		name="fdtOaFwbl",
		expressionArr="FdtOaFwblUsmServiceImpl()",
		desc="局文，内部回函，党组文 by DAOTools ",
		dataType="action"
		)	
    public class FdtOaFwblUsmServiceImpl  implements UsmService{

    private static final Log log = LogFactory.getLog(USMConstants.CONFIG_KEY,
			FdtOaFwblUsmServiceImpl.class);
			
	private static Class defaultProxyClass= FdtOaFwblProxy.class;
		
    private static EIFdtOaFwblManager  singleton = new DbFdtOaFwblManager() ;
    
	
	
	/**
	 * 级联删除
	 * 删除的关联表[
]
	 *级联删除实体表[
]
	 * @return 删除的记录数
	 */
	public int delete(UsmProxy pObject){
		int result = 0;
		try {
			
			result += singleton.delete((EIFdtOaFwbl)pObject.getDAO());
			
			FdtOaFwbl fdtOaFwbl = (FdtOaFwbl)pObject;
		
		
			
		}catch(Exception e){
			e.printStackTrace();
			result = 0;
		}
		return result;
	}
	
	/**
	 * 根据主键ID级联删除
	 * 
	 * @return 删除的记录数
	 * @throws USMException 
	 */
	public int deleteByKey(String uuid) throws USMException {
			return delete(getUsmProxyByKey(uuid));
	}
	
	/**
	 * 根据where条件级联删除
	 * 
	 * @return 删除的记录数
	 * @throws USMException 
	 */
	public int deleteByWhere(String where) throws USMException {
		   USMListProxy list=(USMListProxy) this.getUsmWhere(where);
	   int f=0;
		for(int k=0;k<list.size();k++){
			 FdtOaFwblProxy proxy=(FdtOaFwblProxy) list.get(k, defaultProxyClass);
			f=f+delete(proxy);
		}
		return f;
	}
	
	/**
	 * 根据主键ID获取默认代理对象
	 *
	 * @param pkValue 主键值
	 * @return 代理对象
	 * @throws USMException 
	 */
	public FdtOaFwblProxy getUsmProxyByKey(String pkValue) throws USMException{
	  EIFdtOaFwbl  eiFdtOaFwbl= singleton.loadByKey(pkValue);
		if (eiFdtOaFwbl==null){
		  eiFdtOaFwbl=singleton.createFdtOaFwbl(pkValue);
		}
		return new FdtOaFwblProxy(eiFdtOaFwbl);
	}

	/**
	 * 根据主键ID获取默认代理对象
	 *
	 * @param pkValue 主键值
	 * @param clazz 指定的视图代理对象类型
	 * @return 指定的视图代理对象类型
	 * @throws USMException 
	 */
	public Object getUsmByKey(String pkValue,Class clazz)throws USMException {
	    FdtOaFwblProxy fdtOaFwblProxy=getUsmProxyByKey(pkValue);
		if (clazz==null){
		   clazz=EsbUtil.getCurrClass();
		}
		return ClassUtil.getUsmViewInstance(clazz, fdtOaFwblProxy);

	}
	
	/**
	 * 根据主键ID获取servicekey中默认代理对象
	 *
	 * @param pkValue 主键值
	 * @return servicekey中默认代理对象
	 * @throws USMException 
	 */
	public Object getUsmByKey(String pkValue)throws USMException {
		return getUsmByKey(pkValue, null);
	}
	
	
	/**
	 * 取得所有数据
	 * @return 取得所有数据记录集
	 */
    public  List loadAll() throws USMException{
      List usms  = singleton.loadAll();
	  return new USMListProxy(usms,this);
    }
	
	/**
	 * 根据where条件获取servicekey中默认代理对象记录集
	 *
	 * @param where where条件
	 * @return servicekey中默认代理对象记录集
	 * @throws USMException 
	 */
	public USMListProxy getUsmWhere(String where) throws USMException{
	   List usms  = singleton.loadByWhere(where);
	  return new USMListProxy(usms,this);
	}
	
	/**
	 * 根据where条件分页获取servicekey中默认代理对象记录集
	 *
	 * @param where where条件
	 * @param start start开始
	 * @param limit limit步长
	 * @return servicekey中默认代理对象记录集
	 * @throws USMException 
	 */
	public USMListProxy getUsmWhere(String where,long start, long limit) throws USMException{
	   List usms  =singleton.loadByWhere(where,start,limit);
	
		return new USMListProxy(usms,this);
	}

	/**
	 * 保存默认局文，内部回函，党组文代理对象
	 * 对1:N关系实行级联保存
	 * @param pObject 默认局文，内部回函，党组文代理对象
	 * 级联保存
	 * @throws USMException 
	 */
	public void save(UsmProxy fdtOaFwbl) throws USMException {
		EIFdtOaFwbl eiFdtOaFwbl =singleton.save((EIFdtOaFwbl)fdtOaFwbl.getDAO());
		
	}
	
	/**
	 * 保存默认局文，内部回函，党组文代理对象记录集
	 *
	 * @param pObject 默认局文，内部回函，党组文代理对象记录集
	 * @throws USMException 
	 */
	public List save(List<UsmProxy> pObjects) throws USMException {
		EIFdtOaFwbl[] usms=new EIFdtOaFwbl[pObjects.size()];
		for(int k=0;k<pObjects.size();k++){
		   FdtOaFwblProxy proxy=(FdtOaFwblProxy)pObjects.get(k);
		   usms[k]=(EIFdtOaFwbl)proxy.getDAO();
		}
	
		singleton.save(usms);
		return pObjects;
	}
	
		
    /**
	 * 创建一个默认的局文，内部回函，党组文代理对象
	 *
	 * @param 默认的局文，内部回函，党组文代理对象
	 * @throws USMException 
	 */
	public FdtOaFwblProxy create() throws USMException {
		EIFdtOaFwbl eiFdtOaFwbl=singleton.createFdtOaFwbl();
	    return 	new FdtOaFwblProxy(eiFdtOaFwbl);
	}
	public Class getDefaultProxyClass() {
		return defaultProxyClass;
	}
	
	
	
}
		
