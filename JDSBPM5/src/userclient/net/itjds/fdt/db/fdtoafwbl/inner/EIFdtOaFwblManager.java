package net.itjds.fdt.db.fdtoafwbl.inner;

import java.util.List;

import net.itjds.usm2.*;
import net.itjds.usm2.db.*;
import net.itjds.fdt.db.fdtoafwbl.database.*;

/**
 * <p>
 * Title: USM
 * </p>
 * <p>
 * Description: ���ģ��ڲ��غ��������Ĺ�����
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
	
		public abstract class EIFdtOaFwblManager  {
	
		/**������ǰ���ʵ��*/
		private static EIFdtOaFwblManager  singleton = new DbFdtOaFwblManager() ;
	
		/**
		 * ��ȡ��ǰ���ʵ��
		 * 
		 * @return ��ǰ���ʵ��
		 */
		synchronized public static EIFdtOaFwblManager getInstance() {
			return singleton;
		}
	
		synchronized public static void setInstance(EIFdtOaFwblManager instance) {
			singleton = instance;
		}
	
		
		public abstract EIFdtOaFwbl createFdtOaFwbl();
		
		public abstract EIFdtOaFwbl createFdtOaFwbl(String uuid);
	
	
		/**
		 * ����������ȡ���������ļ�¼
		 
		 * @param activitydefId ����
		 * @return EIFdtOaFwbl����
		 */
		public abstract EIFdtOaFwbl loadByKey(String activitydefId)
				throws USMException;
	
		
	
		/**
		 * ����where������ȡ���������ļ�¼
		 
		 * @param where where����
		 * @return ����where�����ļ�¼��
		 */
		public abstract List loadByWhere(String where) throws USMException;
		
		/**
		 * ����where������ҳ��ȡ���������ļ�¼
		 
		 * @param where where����
		 * @param start ��ʼ
		 * @param limit ����
		 * @return ����where�����ļ�¼��
		 */
		public abstract List loadByWhere(String where,long start,long end) throws USMException;
	
	    /**
		 * ȡ����������
		 * @return ȡ���������ݼ�¼��
		 */
	    public abstract List loadAll() throws USMException;
	
		/**
		 * ����uuidɾ����¼
		 
		 * @param uuid uuid
		 * @return int ɾ�����
		 *            <li> >0 - ɾ���ļ�¼����
		 *			  <li> 0 - ɾ��ʧ��
		 */
		public abstract int deleteByKey(String uuid) throws USMException;
	
		/**
		 * ɾ������
		 
		 * @param pObject ��ɾ������
		 * @return int ɾ�����
		 *            <li> >0 - ɾ���ļ�¼����
		 *			  <li> 0 - ɾ��ʧ��
		 */
		public abstract int delete(EIFdtOaFwbl pObject) throws USMException;
	
		/**
		 * ����where����ɾ����¼
		 
		 * @param where where����
		 * @return int ɾ�����
		 *			  <li>  >0 - ɾ���ļ�¼����
		 *            <li>  0 - ɾ��ʧ��
		 */
		public abstract int deleteByWhere(String where) throws USMException;
	
		/**
		 * �����¼��
		 
		 * @param pObjects ��¼��
		 * @return EIFdtOaFwbl[]
		 */
		public abstract EIFdtOaFwbl[] save(EIFdtOaFwbl[] pObjects)
				throws USMException;
	
		/**
		 * ���浥����¼
		 
		 * @param pObjects EIFdtOaFwbl����
		 * @return EIFdtOaFwbl
		 */
		public abstract EIFdtOaFwbl save(EIFdtOaFwbl pObject)
				throws USMException;
	   
	}
	
