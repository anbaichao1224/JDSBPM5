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
		desc="���ģ��ڲ��غ��������� by DAOTools ",
		dataType="action"
		)	
    public class FdtOaFwblUsmServiceImpl  implements UsmService{

    private static final Log log = LogFactory.getLog(USMConstants.CONFIG_KEY,
			FdtOaFwblUsmServiceImpl.class);
			
	private static Class defaultProxyClass= FdtOaFwblProxy.class;
		
    private static EIFdtOaFwblManager  singleton = new DbFdtOaFwblManager() ;
    
	
	
	/**
	 * ����ɾ��
	 * ɾ���Ĺ�����[
]
	 *����ɾ��ʵ���[
]
	 * @return ɾ���ļ�¼��
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
	 * ��������ID����ɾ��
	 * 
	 * @return ɾ���ļ�¼��
	 * @throws USMException 
	 */
	public int deleteByKey(String uuid) throws USMException {
			return delete(getUsmProxyByKey(uuid));
	}
	
	/**
	 * ����where��������ɾ��
	 * 
	 * @return ɾ���ļ�¼��
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
	 * ��������ID��ȡĬ�ϴ������
	 *
	 * @param pkValue ����ֵ
	 * @return �������
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
	 * ��������ID��ȡĬ�ϴ������
	 *
	 * @param pkValue ����ֵ
	 * @param clazz ָ������ͼ�����������
	 * @return ָ������ͼ�����������
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
	 * ��������ID��ȡservicekey��Ĭ�ϴ������
	 *
	 * @param pkValue ����ֵ
	 * @return servicekey��Ĭ�ϴ������
	 * @throws USMException 
	 */
	public Object getUsmByKey(String pkValue)throws USMException {
		return getUsmByKey(pkValue, null);
	}
	
	
	/**
	 * ȡ����������
	 * @return ȡ���������ݼ�¼��
	 */
    public  List loadAll() throws USMException{
      List usms  = singleton.loadAll();
	  return new USMListProxy(usms,this);
    }
	
	/**
	 * ����where������ȡservicekey��Ĭ�ϴ�������¼��
	 *
	 * @param where where����
	 * @return servicekey��Ĭ�ϴ�������¼��
	 * @throws USMException 
	 */
	public USMListProxy getUsmWhere(String where) throws USMException{
	   List usms  = singleton.loadByWhere(where);
	  return new USMListProxy(usms,this);
	}
	
	/**
	 * ����where������ҳ��ȡservicekey��Ĭ�ϴ�������¼��
	 *
	 * @param where where����
	 * @param start start��ʼ
	 * @param limit limit����
	 * @return servicekey��Ĭ�ϴ�������¼��
	 * @throws USMException 
	 */
	public USMListProxy getUsmWhere(String where,long start, long limit) throws USMException{
	   List usms  =singleton.loadByWhere(where,start,limit);
	
		return new USMListProxy(usms,this);
	}

	/**
	 * ����Ĭ�Ͼ��ģ��ڲ��غ��������Ĵ������
	 * ��1:N��ϵʵ�м�������
	 * @param pObject Ĭ�Ͼ��ģ��ڲ��غ��������Ĵ������
	 * ��������
	 * @throws USMException 
	 */
	public void save(UsmProxy fdtOaFwbl) throws USMException {
		EIFdtOaFwbl eiFdtOaFwbl =singleton.save((EIFdtOaFwbl)fdtOaFwbl.getDAO());
		
	}
	
	/**
	 * ����Ĭ�Ͼ��ģ��ڲ��غ��������Ĵ�������¼��
	 *
	 * @param pObject Ĭ�Ͼ��ģ��ڲ��غ��������Ĵ�������¼��
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
	 * ����һ��Ĭ�ϵľ��ģ��ڲ��غ��������Ĵ������
	 *
	 * @param Ĭ�ϵľ��ģ��ڲ��غ��������Ĵ������
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
		
