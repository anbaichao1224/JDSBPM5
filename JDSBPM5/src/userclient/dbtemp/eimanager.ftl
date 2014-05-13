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
 * Description: ${table.cnname}管理类
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
	
		/**创建当前类的实例*/
		private static EI${table.className}Manager  singleton = new Db${table.className}Manager() ;
	
		/**
		 * 获取当前类的实例
		 * 
		 * @return 当前类的实例
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
		 * 根据主键获取符合条件的记录
		 
		 * @param activitydefId 主键
		 * @return EI${table.className}对象
		 */
		public abstract EI${table.className} loadByKey(String activitydefId)
				throws USMException;
	
		
	
		/**
		 * 根据where条件获取符合条件的记录
		 
		 * @param where where条件
		 * @return 符合where条件的记录集
		 */
		public abstract List loadByWhere(String where) throws USMException;
		
		/**
		 * 根据where条件分页获取符合条件的记录
		 
		 * @param where where条件
		 * @param start 开始
		 * @param limit 步长
		 * @return 符合where条件的记录集
		 */
		public abstract List loadByWhere(String where,long start,long end) throws USMException;
	
	    /**
		 * 取得所有数据
		 * @return 取得所有数据记录集
		 */
	    public abstract List loadAll() throws USMException;
	
		/**
		 * 根据uuid删除记录
		 
		 * @param uuid uuid
		 * @return int 删除结果
		 *            <li> >0 - 删除的记录集数
		 *			  <li> 0 - 删除失败
		 */
		public abstract int deleteByKey(String uuid) throws USMException;
	
		/**
		 * 删除对象
		 
		 * @param pObject 待删除对象
		 * @return int 删除结果
		 *            <li> >0 - 删除的记录集数
		 *			  <li> 0 - 删除失败
		 */
		public abstract int delete(EI${table.className} pObject) throws USMException;
	
		/**
		 * 根据where条件删除记录
		 
		 * @param where where条件
		 * @return int 删除结果
		 *			  <li>  >0 - 删除的记录集数
		 *            <li>  0 - 删除失败
		 */
		public abstract int deleteByWhere(String where) throws USMException;
	
		/**
		 * 保存记录集
		 
		 * @param pObjects 记录集
		 * @return EI${table.className}[]
		 */
		public abstract EI${table.className}[] save(EI${table.className}[] pObjects)
				throws USMException;
	
		/**
		 * 保存单个记录
		 
		 * @param pObjects EI${table.className}对象
		 * @return EI${table.className}
		 */
		public abstract EI${table.className} save(EI${table.className} pObject)
				throws USMException;
	   
	}
	
