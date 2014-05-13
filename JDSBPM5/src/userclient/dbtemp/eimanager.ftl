package ${package}.db.${tempbeanid}.inner;

import java.util.List;

import net.itjds.usm2.*;
import net.itjds.usm2.db.*;
import ${package}.db.${tempbeanid}.database.*;

/**
 * <p>
 * Title: USM
 * </p>
 * <p>
 * Description: ${table.cnname}������
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
	
		public abstract class EI${table.className}Manager  {
	
		/**������ǰ���ʵ��*/
		private static EI${table.className}Manager  singleton = new Db${table.className}Manager() ;
	
		/**
		 * ��ȡ��ǰ���ʵ��
		 * 
		 * @return ��ǰ���ʵ��
		 */
		synchronized public static EI${table.className}Manager getInstance() {
			return singleton;
		}
	
		synchronized public static void setInstance(EI${table.className}Manager instance) {
			singleton = instance;
		}
	
		
		public abstract EI${table.className} create${table.className}();
		
		public abstract EI${table.className} create${table.className}(String uuid);
	
	
		/**
		 * ����������ȡ���������ļ�¼
		 
		 * @param activitydefId ����
		 * @return EI${table.className}����
		 */
		public abstract EI${table.className} loadByKey(String activitydefId)
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
		public abstract int delete(EI${table.className} pObject) throws USMException;
	
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
		 * @return EI${table.className}[]
		 */
		public abstract EI${table.className}[] save(EI${table.className}[] pObjects)
				throws USMException;
	
		/**
		 * ���浥����¼
		 
		 * @param pObjects EI${table.className}����
		 * @return EI${table.className}
		 */
		public abstract EI${table.className} save(EI${table.className} pObject)
				throws USMException;
	   
	}
	
