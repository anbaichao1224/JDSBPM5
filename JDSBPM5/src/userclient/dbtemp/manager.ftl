package ${package}.db.${tempbeanid}.database;

import net.itjds.j2ee.dao.annotation.DBTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import net.itjds.common.cache.Cache;
import net.itjds.common.cache.CacheManagerFactory;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;

import net.itjds.usm2.*;
import ${package}.db.${tempbeanid}.*;
import ${package}.db.${tempbeanid}.inner.*;
import net.itjds.usm2.db.*;
import net.itjds.usm2.constants.*;



	@DBTable(tableName = "${table.tableName}", primaryKey="${table.pkName}",cname="${table.cnname}" <#if usmbean.schema??>,schema="${usmbean.schema}" </#if>)
	public class Db${table.className}Manager extends EI${table.className}Manager{
	
	private static final Log log = LogFactory.getLog(USMConstants.CONFIG_KEY,
			Db${table.className}Manager.class);
	
		<#list table.colList as col>
		
		
	public static final int ID_${col.dbUpName} = ${col.index};

	public static final int TYPE_${col.dbUpName} = ${col.dataType};

	public static final String NAME_${col.dbUpName} = "${col.dbUpName}";
					
   </#list>
   private static final String TABLE_NAME = "${table.tableName}";
   
   private static final String[] FIELD_NAMES = {
   	<#list table.colList as col>
   	   "${table.tableName}.${col.dbUpName}",
       <#if !col_has_next>"${table.tableName}.${col.dbUpName}"</#if>
   	   	 </#list>
   	   	 	};
   	   	private static final String[] TABLEFIELD_NAMES = { 
	<#list table.colList as col>
        "${col.dbUpName}",
           <#if !col_has_next>"${col.dbUpName}"</#if>
 	  </#list>  
 	 	}; 
 	 	
 	 	
 	  
 	  private static final String ALL_FIELDS = 
        <#list table.colList as col>
        <#if col.index==0>
       "${table.tableName}.${col.dbUpName}"</#if>
       <#if col.index!=0>
       +",${table.tableName}.${col.dbUpName}"</#if>
 	    </#list>;
 	    
 	    
 	 Cache ${table.fieldName}Cache = null; //
 	 	boolean cacheEnabled;
     	 	
	    public Db${table.className}Manager()  {
		${table.fieldName}Cache = CacheManagerFactory.getCache(
				USMConstants.CONFIG_KEY, "${table.fieldName}Cache");
			cacheEnabled = CacheManagerFactory.getInstance().getCacheManager(
					USMConstants.CONFIG_KEY).isCacheEnabled();
			;
		}
		
		DbManager getManager() {
		return DbManager.getInstance();
	     }
  
		void freeConnection(Connection c) {
			getManager().releaseConnection(c); // back to pool
		}
	
		Connection getConnection() throws SQLException {
			return getManager().getConnection();
		} 	
		
		
		public EI${table.className} loadByKey(String pkName) throws USMException {
			EI${table.className} ${table.fieldName} = (EI${table.className}) ${table.fieldName}Cache
					.get(pkName);
			if (${table.fieldName} != null) {
				return ${table.fieldName};
			}
		
			Connection c = null;
			PreparedStatement ps = null;
			try {
				c = getConnection();
				if (log.isDebugEnabled())
					log
							.debug("SELECT "
									+ ALL_FIELDS
									+ " FROM ${table.tableName} WHERE ${table.tableName}.${table.pkName}=?");
				ps = c
						.prepareStatement(
								"SELECT "
										+ ALL_FIELDS
										+ " FROM ${table.tableName}  WHERE ${table.tableName}.${table.pkName}=?",
								ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
				ps.setString(1, pkName);
				EI${table.className} pReturn[] = loadByPreparedStatement(ps);
				if (pReturn.length < 1)
					return null;
				else {
					${table.className}DAO inst = (${table.className}DAO)pReturn[0];
					putToCache(inst.get${table.pkClassName}(), inst);
					return pReturn[0];
				}
			} catch (SQLException e) {
				throw new USMException("", e);
			} finally {
				getManager().close(ps);
				freeConnection(c);
			}
		}	
		public List loadByWhere(String where) throws USMException {
			EI${table.className}[] insts;
			try {
				insts = loadByWhere(where, new int[] { ID_${table.pkName}});
			} catch (SQLException e) {
				throw new USMException("", e, USMException.WHERE);
	
			}
			List list = new ${table.className}List(insts);
			return Collections.unmodifiableList(list);
		}
		
		
		public List loadByWhere(String where,long start,long limit) throws USMException {
			EI${table.className}[] insts;
			try {
				insts = loadByWhere(where, new int[] { ID_${table.pkName}},start,limit);
			} catch (SQLException e) {
				throw new USMException("", e, USMException.WHERE);
	
			}
			List list = new ${table.className}List(insts);
			return Collections.unmodifiableList(list);
		}
		
		
		EI${table.className}[] loadByWhere(String where, int[] fieldList,long start, long limit)
			throws SQLException {
		String sql = null;
		sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (";
		if (fieldList == null)
			sql += "select " + ALL_FIELDS + " from ${table.tableName}  " + where;
		else {
			StringBuffer buff = new StringBuffer(128);
			buff.append("select ");
			for (int i = 0; i < fieldList.length; i++) {
				if (i != 0)
					buff.append(",");
				buff.append(FIELD_NAMES[fieldList[i]]);
			}
			buff.append(" from ${table.tableName}  ");
			buff.append(where);
			sql += buff.toString();
			buff = null;
		}
		sql += ") A  WHERE ROWNUM <= " + (start + limit) + ") WHERE RN > " + start;
		Connection c = null;
		Statement pStatement = null;
		ResultSet rs = null;
		java.util.ArrayList v = null;
		try {
			c = getConnection();
			pStatement = c.createStatement();

			if (log.isDebugEnabled())
				log.debug("Executing SQL: " + sql);
			rs = pStatement.executeQuery(sql);
			v = new java.util.ArrayList();
			while (rs.next()) {
				if (fieldList == null)
					v.add(decodeRow(rs));
				else
					v.add(decodeRow(rs, fieldList));
			}

			return (EI${table.className}[]) v.toArray(new ${table.className}DAO[0]);
		} finally {
			if (v != null) {
				v.clear();
			}
			getManager().close(pStatement, rs);
			freeConnection(c);
		}
	}
	
	// ////////////////////////////////////
	// LOAD ALL
	// ////////////////////////////////////

	
	public List loadAll() throws USMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			getManager().log(
					"SELECT " + FIELD_NAMES[ID_${table.pkName}] + " FROM ${table.tableName}");
			ps = c.prepareStatement("SELECT " + FIELD_NAMES[ID_${table.pkName}]
					+ " FROM ${table.tableName}",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			List list = new ${table.className}List(loadByPreparedStatement(
					ps, new int[] { ID_${table.pkName}}));
			return Collections.unmodifiableList(list);

		} catch (SQLException e) {
			throw new USMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}
	
	
	
	
	EI${table.className}[] loadByWhere(String where, int[] fieldList)
			throws SQLException {
		String sql = null;
		if (fieldList == null)
			sql = "select " + ALL_FIELDS + " from ${table.tableName}  " + where;
		else {
			StringBuffer buff = new StringBuffer(128);
			buff.append("select ");
			for (int i = 0; i < fieldList.length; i++) {
				if (i != 0)
					buff.append(",");
				buff.append(FIELD_NAMES[fieldList[i]]);
			}
			buff.append(" from ${table.tableName}  ");
			buff.append(where);
			sql = buff.toString();
			buff = null;
		}
		Connection c = null;
		Statement pStatement = null;
		ResultSet rs = null;
		java.util.ArrayList v = null;
		try {
			c = getConnection();
			pStatement = c.createStatement();

			if (log.isDebugEnabled())
				log.debug("Executing SQL: " + sql);
			rs = pStatement.executeQuery(sql);
			v = new java.util.ArrayList();
			while (rs.next()) {
				if (fieldList == null)
					v.add(decodeRow(rs));
				else
					v.add(decodeRow(rs, fieldList));
			}

			return (EI${table.className}[]) v.toArray(new ${table.className}DAO[0]);
		} finally {
			if (v != null) {
				v.clear();
			}
			getManager().close(pStatement, rs);
			freeConnection(c);
		}
	}
	
	
	public int deleteByKey(String pkName) throws USMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
		
			if (log.isDebugEnabled())
				log
						.debug("DELETE from ${table.tableName} WHERE ${table.tableName}.${table.pkName}=?");
			ps = c
					.prepareStatement(
							"DELETE from ${table.tableName} WHERE ${table.tableName}.${table.pkName}=?",
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			ps.setString(1, pkName);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new USMException("", e, USMException.DELETE);

		} finally {
			removeFromCache(pkName);
			getManager().close(ps);
			freeConnection(c);
		}
	}
	
	public int delete(EI${table.className} pObject) throws USMException {
		${table.className}DAO ${table.fieldName}DAO = (${table.className}DAO) pObject;

		if (${table.fieldName}DAO.is${table.pkClassName}Initialized() == true)
			return deleteByKey(${table.fieldName}DAO.get${table.pkClassName}());
		Connection c = null;
		PreparedStatement ps = null;
		StringBuffer sql = null;
		try {
			sql = new StringBuffer("DELETE FROM ${table.tableName} WHERE ");
			int _dirtyAnd = 0;
			
			<#list table.colList as col>
	   	 	if (${table.fieldName}DAO.is${col.className}Initialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("${col.dbUpName}").append("=?");
				_dirtyAnd++;
			}
	   	 </#list>
			
		
			if (log.isDebugEnabled())
				log.debug(sql.toString());
			c = getConnection();
			ps = c.prepareStatement(sql.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int _dirtyCount = 0;
			
			<#list table.colList as col>
	   	 	if (${table.fieldName}DAO.is${col.className}Initialized()) {
				ps.set${col.simpleType}(++_dirtyCount, ${table.fieldName}DAO.get${col.className}());
			}
	   	 </#list>
			
			int _rows = ps.executeUpdate();
			return _rows;
		} catch (SQLException e) {
			throw new USMException("", e, USMException.DELETE);

		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}
	
	
	EI${table.className}[] loadByPreparedStatement(PreparedStatement ps)
			throws SQLException {
		return loadByPreparedStatement(ps, null);
	}

	EI${table.className}[] loadByPreparedStatement(PreparedStatement ps,
			int[] fieldList) throws SQLException {
		ResultSet rs = null;
		java.util.ArrayList v = null;
		try {
			rs = ps.executeQuery();
			v = new java.util.ArrayList();
			while (rs.next()) {
				if (fieldList == null)
					v.add(decodeRow(rs));
				else
					v.add(decodeRow(rs, fieldList));
			}
			return (EI${table.className}[]) v.toArray(new ${table.className}DAO[0]);
		} finally {
			if (v != null) {
				v.clear();
				v = null;
			}
			getManager().close(rs);
		}
	}
	
		private void putToCache(String uuid, EI${table.className} ${table.fieldName}DAO) {
			synchronized (uuid.intern()) {
				if (!${table.fieldName}Cache.containsKey(uuid)) {
					${table.fieldName}Cache.put(uuid, ${table.fieldName}DAO);
				}
			}
		}
		
		public EI${table.className} create${table.className}(String uuid) {
			${table.className}DAO ${table.fieldName}DAO=new ${table.className}DAO();
			if (uuid==null){
				uuid=UUID.randomUUID().toString();
			}
		   ${table.fieldName}DAO.set${table.pkClassName}(uuid);
			return ${table.fieldName}DAO;
		}
		
		public EI${table.className} create${table.className}() {
			
			return create${table.className}(null);
		}
		
		
		

	    ${table.className}DAO decodeRow(ResultSet rs, int[] fieldList) throws SQLException {
			${table.className}DAO pObject = (${table.className}DAO) create${table.className}();
			int pos = 0;
			for (int i = 0; i < fieldList.length; i++) {
				switch (fieldList[i]) {
				
			<#list table.colList as col>
		   	 	case ID_${col.dbUpName}:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.set${col.className}(rs.get${col.simpleType}(pos));
					}
					break;
		   	 </#list>
	
				}
			}
			pObject.setIsNew(false);
			pObject.resetIsModified();
	
			return pObject;
		}
	
	
		${table.className}DAO decodeRow(ResultSet rs) throws SQLException {
			${table.className}DAO pObject = (${table.className}DAO) create${table.className}();
				
			<#list table.colList as col>
			   	if (rs.getObject(${col.index+1}) != null){
			   	    pObject.set${col.className}(rs.get${col.simpleType}(${col.index+1}));
			   	}
					
		   	 </#list>
			pObject.setIsNew(false);
			pObject.resetIsModified();
	
			return pObject;
		}
	
	private void removeFromCache(String uuid) {
		synchronized (uuid.intern()) {
			if (${table.fieldName}Cache.containsKey(uuid)) {
				${table.fieldName}Cache.remove(uuid);
			}
		}
	}

	private void removeFromCache(List uuids) {
		for (int i = 0; i < uuids.size(); i++) {
			Object obj = uuids.get(i);
			if (obj instanceof String) {
				String uuid = (String) obj;
				removeFromCache(uuid);
			}
		}
	}
	
	
	public EI${table.className}[] save(EI${table.className}[] pObjects) throws USMException {
		for (int iIndex = 0; iIndex < pObjects.length; iIndex++) {
			save(pObjects[iIndex]);
		}
		return pObjects;
	}
	
	
	void beforeInsert(EI${table.className} pObject) throws SQLException {
	}


	void afterInsert(EI${table.className} pObject) throws SQLException {
	}

	
	void beforeUpdate(EI${table.className} pObject) throws SQLException {
	}

	
	void afterUpdate(EI${table.className} pObject) throws SQLException {
	}
	
	
	public int deleteByWhere(String where) throws USMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			// remove from cache first!
			${table.className}DAO[] ${table.fieldName}s;
			try {
				${table.fieldName}s = (${table.className}DAO[])loadByWhere(where, new int[] { ID_${table.pkClassName?upper_case} });
				for (int i = 0; i < ${table.fieldName}s.length; i++) {
					removeFromCache(${table.fieldName}s[i].get${table.pkClassName}());
				}
			} catch (SQLException e) {
				throw new USMException("", e,
						USMException.UPDATE);
			}
			// delete it!
			c = getConnection();
	
			String delByWhereSQL = "DELETE FROM ${table.tableName} " + where;
			if (log.isDebugEnabled())
				log.debug(delByWhereSQL);
			ps = c.prepareStatement(delByWhereSQL);
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new USMException("", e, USMException.CONNECTION);

		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}
	
	
	
	public EI${table.className} save(EI${table.className} pObject) throws USMException {
		${table.className}DAO ${table.fieldName}DAO = (${table.className}DAO) pObject;
		Connection c = null;
		PreparedStatement ps = null;
		StringBuffer _sql = null;
		try {
			c = getConnection();
			if (${table.fieldName}DAO.isNew()) { // SAVE
				beforeInsert(${table.fieldName}DAO);
				int _dirtyCount = 0;
				_sql = new StringBuffer("INSERT into ${table.tableName} (");
				
				<#list table.colList as col>
				if (${table.fieldName}DAO.is${col.className}Modified()) {
			              _sql.append("${col.dbcol.name}").append(",");
			              _dirtyCount++;
			   	}		
		   	    </#list>
				
				_sql.setLength(_sql.length() - 1);
				_sql.append(") values (");
				for (int i = 0; i < _dirtyCount; i++)
					_sql.append("?,");
				_sql.setLength(_sql.length() - 1);
				_sql.append(")");

				if (log.isDebugEnabled())
					log.debug(_sql.toString());
				ps = c.prepareStatement(_sql.toString(),
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				_dirtyCount = 0;
				
				<#list table.colList as col>
				if (${table.fieldName}DAO.is${col.className}Modified()) {
				 ps.set${col.simpleType}(++_dirtyCount, ${table.fieldName}DAO.get${col.className}());    
			   	}		
		   	    </#list>
				
				
				ps.executeUpdate();
				${table.fieldName}DAO.setIsNew(false);
				afterInsert(${table.fieldName}DAO);
			} else { // UPDATE
				beforeUpdate(${table.fieldName}DAO);
				_sql = new StringBuffer("UPDATE ${table.tableName} SET ");
				
				<#list table.colList as col>
				if (${table.fieldName}DAO.is${col.className}Modified()) {
				  _sql.append("${col.dbUpName}").append("=?,");
			   	}		
		   	    </#list>
				
				_sql.setLength(_sql.length() - 1);
				_sql.append(" WHERE ");
				_sql.append("${table.tableName}.${table.pkName}=?");
				if (log.isDebugEnabled())
					log.debug(_sql.toString());
				ps = c.prepareStatement(_sql.toString(),
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				int _dirtyCount = 0;
				
				<#list table.colList as col>
				if (${table.fieldName}DAO.is${col.className}Modified()) {
				 	if (${table.fieldName}DAO.get${col.className}() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.set${col.simpleType}(++_dirtyCount,${table.fieldName}DAO.get${col.className}());
					}			
			   	}		
		   	    </#list>
				
				if (_dirtyCount != 0) {
					ps.setString(++_dirtyCount, ${table.fieldName}DAO.get${table.pkClassName}());
					ps.executeUpdate();

					afterUpdate(${table.fieldName}DAO);
				}

			}
	
			${table.fieldName}DAO.resetIsModified();
		} catch (SQLException e) {
			throw new USMException("", e, USMException.UPDATE);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
		return ${table.fieldName}DAO;
	}
	
	
	
	
	void prepareCache(List ids) {
	
		StringBuffer sqlWhere = new StringBuffer(" WHERE ${table.pkName} IN (");
		int andCount = 0;
		// check out all UUIDs not in the cache now
		for (int i = 0; i < ids.size(); i++) {
			String uuid = (String) ids.get(i);
			if (${table.fieldName}Cache.containsKey(uuid) == false) {
				if (andCount > 0) {
					sqlWhere.append(",");
				}
				sqlWhere.append(" '" + uuid + "'");
				andCount++;
			}
		}
		// batch load
		if (andCount > 0) {
			sqlWhere.append(") ");
			${table.className}DAO [] ${table.fieldName}s = null;
			try {
				${table.fieldName}s = (${table.className}DAO []) loadByWhere(sqlWhere.toString(), null);
			} catch (SQLException e) {
				log.error("prefetch the ${table.fieldName}s Definition fail! Where Sql: "
						+ sqlWhere.toString());
				log.error("SqlException is ", e);
				return;
			}
			for (int i = 0; i < ${table.fieldName}s.length; i++) {
				${table.className}DAO ${table.fieldName} = ${table.fieldName}s[i];
				putToCache(${table.fieldName}.get${table.pkClassName}(), ${table.fieldName});
			}
		}
	}
}
