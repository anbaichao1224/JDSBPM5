package net.itjds.fdt.db.fdtoafwbl.database;

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
import net.itjds.fdt.db.fdtoafwbl.*;
import net.itjds.fdt.db.fdtoafwbl.inner.*;
import net.itjds.usm2.db.*;
import net.itjds.usm2.constants.*;



	@DBTable(tableName = "FDT_OA_FWBL", primaryKey="UUID",cname="局文，内部回函，党组文" ,schema="org" )
	public class DbFdtOaFwblManager extends EIFdtOaFwblManager{
	
	private static final Log log = LogFactory.getLog(USMConstants.CONFIG_KEY,
			DbFdtOaFwblManager.class);
	
		
		
	public static final int ID_UUID = 0;

	public static final int TYPE_UUID = 12;

	public static final String NAME_UUID = "UUID";
					
		
		
	public static final int ID_PROCESSINST_ID = 1;

	public static final int TYPE_PROCESSINST_ID = 12;

	public static final String NAME_PROCESSINST_ID = "PROCESSINST_ID";
					
		
		
	public static final int ID_ACTIVITYINST_ID = 2;

	public static final int TYPE_ACTIVITYINST_ID = 12;

	public static final String NAME_ACTIVITYINST_ID = "ACTIVITYINST_ID";
					
		
		
	public static final int ID_TYPE_TYPE = 3;

	public static final int TYPE_TYPE_TYPE = 12;

	public static final String NAME_TYPE_TYPE = "TYPE_TYPE";
					
		
		
	public static final int ID_TYPE_WORD = 4;

	public static final int TYPE_TYPE_WORD = 12;

	public static final String NAME_TYPE_WORD = "TYPE_WORD";
					
		
		
	public static final int ID_TYPE_YEAR = 5;

	public static final int TYPE_TYPE_YEAR = 3;

	public static final String NAME_TYPE_YEAR = "TYPE_YEAR";
					
		
		
	public static final int ID_TYPE_NUM = 6;

	public static final int TYPE_TYPE_NUM = 3;

	public static final String NAME_TYPE_NUM = "TYPE_NUM";
					
		
		
	public static final int ID_MIJI = 7;

	public static final int TYPE_MIJI = 12;

	public static final String NAME_MIJI = "MIJI";
					
		
		
	public static final int ID_BAOMIQIXIAN = 8;

	public static final int TYPE_BAOMIQIXIAN = 3;

	public static final String NAME_BAOMIQIXIAN = "BAOMIQIXIAN";
					
		
		
	public static final int ID_HUANJI = 9;

	public static final int TYPE_HUANJI = 12;

	public static final String NAME_HUANJI = "HUANJI";
					
		
		
	public static final int ID_DINGMIYIJU = 10;

	public static final int TYPE_DINGMIYIJU = 12;

	public static final String NAME_DINGMIYIJU = "DINGMIYIJU";
					
		
		
	public static final int ID_QIANFA = 11;

	public static final int TYPE_QIANFA = 12;

	public static final String NAME_QIANFA = "QIANFA";
					
		
		
	public static final int ID_HUIQIAN = 12;

	public static final int TYPE_HUIQIAN = 12;

	public static final String NAME_HUIQIAN = "HUIQIAN";
					
		
		
	public static final int ID_BIAOTI = 13;

	public static final int TYPE_BIAOTI = 12;

	public static final String NAME_BIAOTI = "BIAOTI";
					
		
		
	public static final int ID_ZHUSONG = 14;

	public static final int TYPE_ZHUSONG = 12;

	public static final String NAME_ZHUSONG = "ZHUSONG";
					
		
		
	public static final int ID_CHAOBAO = 15;

	public static final int TYPE_CHAOBAO = 12;

	public static final String NAME_CHAOBAO = "CHAOBAO";
					
		
		
	public static final int ID_CHAOSONG = 16;

	public static final int TYPE_CHAOSONG = 12;

	public static final String NAME_CHAOSONG = "CHAOSONG";
					
		
		
	public static final int ID_NIGAODANWEI = 17;

	public static final int TYPE_NIGAODANWEI = 12;

	public static final String NAME_NIGAODANWEI = "NIGAODANWEI";
					
		
		
	public static final int ID_NIGAO = 18;

	public static final int TYPE_NIGAO = 12;

	public static final String NAME_NIGAO = "NIGAO";
					
		
		
	public static final int ID_HEGAO = 19;

	public static final int TYPE_HEGAO = 12;

	public static final String NAME_HEGAO = "HEGAO";
					
		
		
	public static final int ID_YINSHUA = 20;

	public static final int TYPE_YINSHUA = 12;

	public static final String NAME_YINSHUA = "YINSHUA";
					
		
		
	public static final int ID_JIAODUI = 21;

	public static final int TYPE_JIAODUI = 12;

	public static final String NAME_JIAODUI = "JIAODUI";
					
		
		
	public static final int ID_FENSHU = 22;

	public static final int TYPE_FENSHU = 3;

	public static final String NAME_FENSHU = "FENSHU";
					
		
		
	public static final int ID_ZHUTICI = 23;

	public static final int TYPE_ZHUTICI = 12;

	public static final String NAME_ZHUTICI = "ZHUTICI";
					
		
		
	public static final int ID_QICAORIQI = 24;

	public static final int TYPE_QICAORIQI = 93;

	public static final String NAME_QICAORIQI = "QICAORIQI";
					
   private static final String TABLE_NAME = "FDT_OA_FWBL";
   
   private static final String[] FIELD_NAMES = {
   	   "FDT_OA_FWBL.UUID",
       
   	   "FDT_OA_FWBL.PROCESSINST_ID",
       
   	   "FDT_OA_FWBL.ACTIVITYINST_ID",
       
   	   "FDT_OA_FWBL.TYPE_TYPE",
       
   	   "FDT_OA_FWBL.TYPE_WORD",
       
   	   "FDT_OA_FWBL.TYPE_YEAR",
       
   	   "FDT_OA_FWBL.TYPE_NUM",
       
   	   "FDT_OA_FWBL.MIJI",
       
   	   "FDT_OA_FWBL.BAOMIQIXIAN",
       
   	   "FDT_OA_FWBL.HUANJI",
       
   	   "FDT_OA_FWBL.DINGMIYIJU",
       
   	   "FDT_OA_FWBL.QIANFA",
       
   	   "FDT_OA_FWBL.HUIQIAN",
       
   	   "FDT_OA_FWBL.BIAOTI",
       
   	   "FDT_OA_FWBL.ZHUSONG",
       
   	   "FDT_OA_FWBL.CHAOBAO",
       
   	   "FDT_OA_FWBL.CHAOSONG",
       
   	   "FDT_OA_FWBL.NIGAODANWEI",
       
   	   "FDT_OA_FWBL.NIGAO",
       
   	   "FDT_OA_FWBL.HEGAO",
       
   	   "FDT_OA_FWBL.YINSHUA",
       
   	   "FDT_OA_FWBL.JIAODUI",
       
   	   "FDT_OA_FWBL.FENSHU",
       
   	   "FDT_OA_FWBL.ZHUTICI",
       
   	   "FDT_OA_FWBL.QICAORIQI",
       "FDT_OA_FWBL.QICAORIQI"
   	   	 	};
   	   	private static final String[] TABLEFIELD_NAMES = { 
        "UUID",
           
        "PROCESSINST_ID",
           
        "ACTIVITYINST_ID",
           
        "TYPE_TYPE",
           
        "TYPE_WORD",
           
        "TYPE_YEAR",
           
        "TYPE_NUM",
           
        "MIJI",
           
        "BAOMIQIXIAN",
           
        "HUANJI",
           
        "DINGMIYIJU",
           
        "QIANFA",
           
        "HUIQIAN",
           
        "BIAOTI",
           
        "ZHUSONG",
           
        "CHAOBAO",
           
        "CHAOSONG",
           
        "NIGAODANWEI",
           
        "NIGAO",
           
        "HEGAO",
           
        "YINSHUA",
           
        "JIAODUI",
           
        "FENSHU",
           
        "ZHUTICI",
           
        "QICAORIQI",
           "QICAORIQI"
 	 	}; 
 	 	
 	 	
 	  
 	  private static final String ALL_FIELDS = 
       "FDT_OA_FWBL.UUID"

 	    
       +",FDT_OA_FWBL.PROCESSINST_ID"
 	    
       +",FDT_OA_FWBL.ACTIVITYINST_ID"
 	    
       +",FDT_OA_FWBL.TYPE_TYPE"
 	    
       +",FDT_OA_FWBL.TYPE_WORD"
 	    
       +",FDT_OA_FWBL.TYPE_YEAR"
 	    
       +",FDT_OA_FWBL.TYPE_NUM"
 	    
       +",FDT_OA_FWBL.MIJI"
 	    
       +",FDT_OA_FWBL.BAOMIQIXIAN"
 	    
       +",FDT_OA_FWBL.HUANJI"
 	    
       +",FDT_OA_FWBL.DINGMIYIJU"
 	    
       +",FDT_OA_FWBL.QIANFA"
 	    
       +",FDT_OA_FWBL.HUIQIAN"
 	    
       +",FDT_OA_FWBL.BIAOTI"
 	    
       +",FDT_OA_FWBL.ZHUSONG"
 	    
       +",FDT_OA_FWBL.CHAOBAO"
 	    
       +",FDT_OA_FWBL.CHAOSONG"
 	    
       +",FDT_OA_FWBL.NIGAODANWEI"
 	    
       +",FDT_OA_FWBL.NIGAO"
 	    
       +",FDT_OA_FWBL.HEGAO"
 	    
       +",FDT_OA_FWBL.YINSHUA"
 	    
       +",FDT_OA_FWBL.JIAODUI"
 	    
       +",FDT_OA_FWBL.FENSHU"
 	    
       +",FDT_OA_FWBL.ZHUTICI"
 	    
       +",FDT_OA_FWBL.QICAORIQI"
 	    ;
 	    
 	    
 	 Cache fdtOaFwblCache = null; //
 	 	boolean cacheEnabled;
     	 	
	    public DbFdtOaFwblManager()  {
		fdtOaFwblCache = CacheManagerFactory.getCache(
				USMConstants.CONFIG_KEY, "fdtOaFwblCache");
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
		
		
		public EIFdtOaFwbl loadByKey(String pkName) throws USMException {
			EIFdtOaFwbl fdtOaFwbl = (EIFdtOaFwbl) fdtOaFwblCache
					.get(pkName);
			if (fdtOaFwbl != null) {
				return fdtOaFwbl;
			}
		
			Connection c = null;
			PreparedStatement ps = null;
			try {
				c = getConnection();
				if (log.isDebugEnabled())
					log
							.debug("SELECT "
									+ ALL_FIELDS
									+ " FROM FDT_OA_FWBL WHERE FDT_OA_FWBL.UUID=?");
				ps = c
						.prepareStatement(
								"SELECT "
										+ ALL_FIELDS
										+ " FROM FDT_OA_FWBL  WHERE FDT_OA_FWBL.UUID=?",
								ResultSet.TYPE_SCROLL_INSENSITIVE,
								ResultSet.CONCUR_READ_ONLY);
				ps.setString(1, pkName);
				EIFdtOaFwbl pReturn[] = loadByPreparedStatement(ps);
				if (pReturn.length < 1)
					return null;
				else {
					FdtOaFwblDAO inst = (FdtOaFwblDAO)pReturn[0];
					putToCache(inst.getUuid(), inst);
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
			EIFdtOaFwbl[] insts;
			try {
				insts = loadByWhere(where, new int[] { ID_UUID});
			} catch (SQLException e) {
				throw new USMException("", e, USMException.WHERE);
	
			}
			List list = new FdtOaFwblList(insts);
			return Collections.unmodifiableList(list);
		}
		
		
		public List loadByWhere(String where,long start,long limit) throws USMException {
			EIFdtOaFwbl[] insts;
			try {
				insts = loadByWhere(where, new int[] { ID_UUID},start,limit);
			} catch (SQLException e) {
				throw new USMException("", e, USMException.WHERE);
	
			}
			List list = new FdtOaFwblList(insts);
			return Collections.unmodifiableList(list);
		}
		
		
		EIFdtOaFwbl[] loadByWhere(String where, int[] fieldList,long start, long limit)
			throws SQLException {
		String sql = null;
		sql = "SELECT * FROM (SELECT A.*, ROWNUM RN FROM (";
		if (fieldList == null)
			sql += "select " + ALL_FIELDS + " from FDT_OA_FWBL  " + where;
		else {
			StringBuffer buff = new StringBuffer(128);
			buff.append("select ");
			for (int i = 0; i < fieldList.length; i++) {
				if (i != 0)
					buff.append(",");
				buff.append(FIELD_NAMES[fieldList[i]]);
			}
			buff.append(" from FDT_OA_FWBL  ");
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

			return (EIFdtOaFwbl[]) v.toArray(new FdtOaFwblDAO[0]);
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
					"SELECT " + FIELD_NAMES[ID_UUID] + " FROM FDT_OA_FWBL");
			ps = c.prepareStatement("SELECT " + FIELD_NAMES[ID_UUID]
					+ " FROM FDT_OA_FWBL",
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			List list = new FdtOaFwblList(loadByPreparedStatement(
					ps, new int[] { ID_UUID}));
			return Collections.unmodifiableList(list);

		} catch (SQLException e) {
			throw new USMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}
	
	
	
	
	EIFdtOaFwbl[] loadByWhere(String where, int[] fieldList)
			throws SQLException {
		String sql = null;
		if (fieldList == null)
			sql = "select " + ALL_FIELDS + " from FDT_OA_FWBL  " + where;
		else {
			StringBuffer buff = new StringBuffer(128);
			buff.append("select ");
			for (int i = 0; i < fieldList.length; i++) {
				if (i != 0)
					buff.append(",");
				buff.append(FIELD_NAMES[fieldList[i]]);
			}
			buff.append(" from FDT_OA_FWBL  ");
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

			return (EIFdtOaFwbl[]) v.toArray(new FdtOaFwblDAO[0]);
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
						.debug("DELETE from FDT_OA_FWBL WHERE FDT_OA_FWBL.UUID=?");
			ps = c
					.prepareStatement(
							"DELETE from FDT_OA_FWBL WHERE FDT_OA_FWBL.UUID=?",
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
	
	public int delete(EIFdtOaFwbl pObject) throws USMException {
		FdtOaFwblDAO fdtOaFwblDAO = (FdtOaFwblDAO) pObject;

		if (fdtOaFwblDAO.isUuidInitialized() == true)
			return deleteByKey(fdtOaFwblDAO.getUuid());
		Connection c = null;
		PreparedStatement ps = null;
		StringBuffer sql = null;
		try {
			sql = new StringBuffer("DELETE FROM FDT_OA_FWBL WHERE ");
			int _dirtyAnd = 0;
			
	   	 	if (fdtOaFwblDAO.isUuidInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("UUID").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isProcessinstIdInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("PROCESSINST_ID").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isActivityinstIdInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ACTIVITYINST_ID").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isTypeTypeInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("TYPE_TYPE").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isTypeWordInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("TYPE_WORD").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isTypeYearInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("TYPE_YEAR").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isTypeNumInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("TYPE_NUM").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isMijiInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("MIJI").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isBaomiqixianInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("BAOMIQIXIAN").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isHuanjiInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("HUANJI").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isDingmiyijuInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("DINGMIYIJU").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isQianfaInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("QIANFA").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isHuiqianInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("HUIQIAN").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isBiaotiInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("BIAOTI").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isZhusongInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ZHUSONG").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isChaobaoInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("CHAOBAO").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isChaosongInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("CHAOSONG").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isNigaodanweiInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("NIGAODANWEI").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isNigaoInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("NIGAO").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isHegaoInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("HEGAO").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isYinshuaInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("YINSHUA").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isJiaoduiInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("JIAODUI").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isFenshuInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("FENSHU").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isZhuticiInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ZHUTICI").append("=?");
				_dirtyAnd++;
			}
	   	 	if (fdtOaFwblDAO.isQicaoriqiInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("QICAORIQI").append("=?");
				_dirtyAnd++;
			}
			
		
			if (log.isDebugEnabled())
				log.debug(sql.toString());
			c = getConnection();
			ps = c.prepareStatement(sql.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			int _dirtyCount = 0;
			
	   	 	if (fdtOaFwblDAO.isUuidInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getUuid());
			}
	   	 	if (fdtOaFwblDAO.isProcessinstIdInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getProcessinstId());
			}
	   	 	if (fdtOaFwblDAO.isActivityinstIdInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getActivityinstId());
			}
	   	 	if (fdtOaFwblDAO.isTypeTypeInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getTypeType());
			}
	   	 	if (fdtOaFwblDAO.isTypeWordInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getTypeWord());
			}
	   	 	if (fdtOaFwblDAO.isTypeYearInitialized()) {
				ps.setInt(++_dirtyCount, fdtOaFwblDAO.getTypeYear());
			}
	   	 	if (fdtOaFwblDAO.isTypeNumInitialized()) {
				ps.setInt(++_dirtyCount, fdtOaFwblDAO.getTypeNum());
			}
	   	 	if (fdtOaFwblDAO.isMijiInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getMiji());
			}
	   	 	if (fdtOaFwblDAO.isBaomiqixianInitialized()) {
				ps.setInt(++_dirtyCount, fdtOaFwblDAO.getBaomiqixian());
			}
	   	 	if (fdtOaFwblDAO.isHuanjiInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getHuanji());
			}
	   	 	if (fdtOaFwblDAO.isDingmiyijuInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getDingmiyiju());
			}
	   	 	if (fdtOaFwblDAO.isQianfaInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getQianfa());
			}
	   	 	if (fdtOaFwblDAO.isHuiqianInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getHuiqian());
			}
	   	 	if (fdtOaFwblDAO.isBiaotiInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getBiaoti());
			}
	   	 	if (fdtOaFwblDAO.isZhusongInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getZhusong());
			}
	   	 	if (fdtOaFwblDAO.isChaobaoInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getChaobao());
			}
	   	 	if (fdtOaFwblDAO.isChaosongInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getChaosong());
			}
	   	 	if (fdtOaFwblDAO.isNigaodanweiInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getNigaodanwei());
			}
	   	 	if (fdtOaFwblDAO.isNigaoInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getNigao());
			}
	   	 	if (fdtOaFwblDAO.isHegaoInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getHegao());
			}
	   	 	if (fdtOaFwblDAO.isYinshuaInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getYinshua());
			}
	   	 	if (fdtOaFwblDAO.isJiaoduiInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getJiaodui());
			}
	   	 	if (fdtOaFwblDAO.isFenshuInitialized()) {
				ps.setInt(++_dirtyCount, fdtOaFwblDAO.getFenshu());
			}
	   	 	if (fdtOaFwblDAO.isZhuticiInitialized()) {
				ps.setString(++_dirtyCount, fdtOaFwblDAO.getZhutici());
			}
	   	 	if (fdtOaFwblDAO.isQicaoriqiInitialized()) {
				ps.setTimestamp(++_dirtyCount, fdtOaFwblDAO.getQicaoriqi());
			}
			
			int _rows = ps.executeUpdate();
			return _rows;
		} catch (SQLException e) {
			throw new USMException("", e, USMException.DELETE);

		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}
	
	
	EIFdtOaFwbl[] loadByPreparedStatement(PreparedStatement ps)
			throws SQLException {
		return loadByPreparedStatement(ps, null);
	}

	EIFdtOaFwbl[] loadByPreparedStatement(PreparedStatement ps,
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
			return (EIFdtOaFwbl[]) v.toArray(new FdtOaFwblDAO[0]);
		} finally {
			if (v != null) {
				v.clear();
				v = null;
			}
			getManager().close(rs);
		}
	}
	
		private void putToCache(String uuid, EIFdtOaFwbl fdtOaFwblDAO) {
			synchronized (uuid.intern()) {
				if (!fdtOaFwblCache.containsKey(uuid)) {
					fdtOaFwblCache.put(uuid, fdtOaFwblDAO);
				}
			}
		}
		
		public EIFdtOaFwbl createFdtOaFwbl(String uuid) {
			FdtOaFwblDAO fdtOaFwblDAO=new FdtOaFwblDAO();
			if (uuid==null){
				uuid=UUID.randomUUID().toString();
			}
		   fdtOaFwblDAO.setUuid(uuid);
			return fdtOaFwblDAO;
		}
		
		public EIFdtOaFwbl createFdtOaFwbl() {
			
			return createFdtOaFwbl(null);
		}
		
		
		

	    FdtOaFwblDAO decodeRow(ResultSet rs, int[] fieldList) throws SQLException {
			FdtOaFwblDAO pObject = (FdtOaFwblDAO) createFdtOaFwbl();
			int pos = 0;
			for (int i = 0; i < fieldList.length; i++) {
				switch (fieldList[i]) {
				
		   	 	case ID_UUID:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setUuid(rs.getString(pos));
					}
					break;
		   	 	case ID_PROCESSINST_ID:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setProcessinstId(rs.getString(pos));
					}
					break;
		   	 	case ID_ACTIVITYINST_ID:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setActivityinstId(rs.getString(pos));
					}
					break;
		   	 	case ID_TYPE_TYPE:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setTypeType(rs.getString(pos));
					}
					break;
		   	 	case ID_TYPE_WORD:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setTypeWord(rs.getString(pos));
					}
					break;
		   	 	case ID_TYPE_YEAR:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setTypeYear(rs.getInt(pos));
					}
					break;
		   	 	case ID_TYPE_NUM:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setTypeNum(rs.getInt(pos));
					}
					break;
		   	 	case ID_MIJI:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setMiji(rs.getString(pos));
					}
					break;
		   	 	case ID_BAOMIQIXIAN:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setBaomiqixian(rs.getInt(pos));
					}
					break;
		   	 	case ID_HUANJI:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setHuanji(rs.getString(pos));
					}
					break;
		   	 	case ID_DINGMIYIJU:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setDingmiyiju(rs.getString(pos));
					}
					break;
		   	 	case ID_QIANFA:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setQianfa(rs.getString(pos));
					}
					break;
		   	 	case ID_HUIQIAN:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setHuiqian(rs.getString(pos));
					}
					break;
		   	 	case ID_BIAOTI:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setBiaoti(rs.getString(pos));
					}
					break;
		   	 	case ID_ZHUSONG:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setZhusong(rs.getString(pos));
					}
					break;
		   	 	case ID_CHAOBAO:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setChaobao(rs.getString(pos));
					}
					break;
		   	 	case ID_CHAOSONG:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setChaosong(rs.getString(pos));
					}
					break;
		   	 	case ID_NIGAODANWEI:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setNigaodanwei(rs.getString(pos));
					}
					break;
		   	 	case ID_NIGAO:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setNigao(rs.getString(pos));
					}
					break;
		   	 	case ID_HEGAO:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setHegao(rs.getString(pos));
					}
					break;
		   	 	case ID_YINSHUA:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setYinshua(rs.getString(pos));
					}
					break;
		   	 	case ID_JIAODUI:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setJiaodui(rs.getString(pos));
					}
					break;
		   	 	case ID_FENSHU:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setFenshu(rs.getInt(pos));
					}
					break;
		   	 	case ID_ZHUTICI:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setZhutici(rs.getString(pos));
					}
					break;
		   	 	case ID_QICAORIQI:
					++pos;
					if (rs.getObject(pos) != null) {
						pObject.setQicaoriqi(rs.getTimestamp(pos));
					}
					break;
	
				}
			}
			pObject.setIsNew(false);
			pObject.resetIsModified();
	
			return pObject;
		}
	
	
		FdtOaFwblDAO decodeRow(ResultSet rs) throws SQLException {
			FdtOaFwblDAO pObject = (FdtOaFwblDAO) createFdtOaFwbl();
				
			   	if (rs.getObject(1) != null){
			   	    pObject.setUuid(rs.getString(1));
			   	}
					
			   	if (rs.getObject(2) != null){
			   	    pObject.setProcessinstId(rs.getString(2));
			   	}
					
			   	if (rs.getObject(3) != null){
			   	    pObject.setActivityinstId(rs.getString(3));
			   	}
					
			   	if (rs.getObject(4) != null){
			   	    pObject.setTypeType(rs.getString(4));
			   	}
					
			   	if (rs.getObject(5) != null){
			   	    pObject.setTypeWord(rs.getString(5));
			   	}
					
			   	if (rs.getObject(6) != null){
			   	    pObject.setTypeYear(rs.getInt(6));
			   	}
					
			   	if (rs.getObject(7) != null){
			   	    pObject.setTypeNum(rs.getInt(7));
			   	}
					
			   	if (rs.getObject(8) != null){
			   	    pObject.setMiji(rs.getString(8));
			   	}
					
			   	if (rs.getObject(9) != null){
			   	    pObject.setBaomiqixian(rs.getInt(9));
			   	}
					
			   	if (rs.getObject(10) != null){
			   	    pObject.setHuanji(rs.getString(10));
			   	}
					
			   	if (rs.getObject(11) != null){
			   	    pObject.setDingmiyiju(rs.getString(11));
			   	}
					
			   	if (rs.getObject(12) != null){
			   	    pObject.setQianfa(rs.getString(12));
			   	}
					
			   	if (rs.getObject(13) != null){
			   	    pObject.setHuiqian(rs.getString(13));
			   	}
					
			   	if (rs.getObject(14) != null){
			   	    pObject.setBiaoti(rs.getString(14));
			   	}
					
			   	if (rs.getObject(15) != null){
			   	    pObject.setZhusong(rs.getString(15));
			   	}
					
			   	if (rs.getObject(16) != null){
			   	    pObject.setChaobao(rs.getString(16));
			   	}
					
			   	if (rs.getObject(17) != null){
			   	    pObject.setChaosong(rs.getString(17));
			   	}
					
			   	if (rs.getObject(18) != null){
			   	    pObject.setNigaodanwei(rs.getString(18));
			   	}
					
			   	if (rs.getObject(19) != null){
			   	    pObject.setNigao(rs.getString(19));
			   	}
					
			   	if (rs.getObject(20) != null){
			   	    pObject.setHegao(rs.getString(20));
			   	}
					
			   	if (rs.getObject(21) != null){
			   	    pObject.setYinshua(rs.getString(21));
			   	}
					
			   	if (rs.getObject(22) != null){
			   	    pObject.setJiaodui(rs.getString(22));
			   	}
					
			   	if (rs.getObject(23) != null){
			   	    pObject.setFenshu(rs.getInt(23));
			   	}
					
			   	if (rs.getObject(24) != null){
			   	    pObject.setZhutici(rs.getString(24));
			   	}
					
			   	if (rs.getObject(25) != null){
			   	    pObject.setQicaoriqi(rs.getTimestamp(25));
			   	}
					
			pObject.setIsNew(false);
			pObject.resetIsModified();
	
			return pObject;
		}
	
	private void removeFromCache(String uuid) {
		synchronized (uuid.intern()) {
			if (fdtOaFwblCache.containsKey(uuid)) {
				fdtOaFwblCache.remove(uuid);
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
	
	
	public EIFdtOaFwbl[] save(EIFdtOaFwbl[] pObjects) throws USMException {
		for (int iIndex = 0; iIndex < pObjects.length; iIndex++) {
			save(pObjects[iIndex]);
		}
		return pObjects;
	}
	
	
	void beforeInsert(EIFdtOaFwbl pObject) throws SQLException {
	}


	void afterInsert(EIFdtOaFwbl pObject) throws SQLException {
	}

	
	void beforeUpdate(EIFdtOaFwbl pObject) throws SQLException {
	}

	
	void afterUpdate(EIFdtOaFwbl pObject) throws SQLException {
	}
	
	
	public int deleteByWhere(String where) throws USMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			// remove from cache first!
			FdtOaFwblDAO[] fdtOaFwbls;
			try {
				fdtOaFwbls = (FdtOaFwblDAO[])loadByWhere(where, new int[] { ID_UUID });
				for (int i = 0; i < fdtOaFwbls.length; i++) {
					removeFromCache(fdtOaFwbls[i].getUuid());
				}
			} catch (SQLException e) {
				throw new USMException("", e,
						USMException.UPDATE);
			}
			// delete it!
			c = getConnection();
	
			String delByWhereSQL = "DELETE FROM FDT_OA_FWBL " + where;
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
	
	
	
	public EIFdtOaFwbl save(EIFdtOaFwbl pObject) throws USMException {
		FdtOaFwblDAO fdtOaFwblDAO = (FdtOaFwblDAO) pObject;
		Connection c = null;
		PreparedStatement ps = null;
		StringBuffer _sql = null;
		try {
			c = getConnection();
			if (fdtOaFwblDAO.isNew()) { // SAVE
				beforeInsert(fdtOaFwblDAO);
				int _dirtyCount = 0;
				_sql = new StringBuffer("INSERT into FDT_OA_FWBL (");
				
				if (fdtOaFwblDAO.isUuidModified()) {
			              _sql.append("UUID").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isProcessinstIdModified()) {
			              _sql.append("PROCESSINST_ID").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isActivityinstIdModified()) {
			              _sql.append("ACTIVITYINST_ID").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isTypeTypeModified()) {
			              _sql.append("TYPE_TYPE").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isTypeWordModified()) {
			              _sql.append("TYPE_WORD").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isTypeYearModified()) {
			              _sql.append("TYPE_YEAR").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isTypeNumModified()) {
			              _sql.append("TYPE_NUM").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isMijiModified()) {
			              _sql.append("MIJI").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isBaomiqixianModified()) {
			              _sql.append("BAOMIQIXIAN").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isHuanjiModified()) {
			              _sql.append("HUANJI").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isDingmiyijuModified()) {
			              _sql.append("DINGMIYIJU").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isQianfaModified()) {
			              _sql.append("QIANFA").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isHuiqianModified()) {
			              _sql.append("HUIQIAN").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isBiaotiModified()) {
			              _sql.append("BIAOTI").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isZhusongModified()) {
			              _sql.append("ZHUSONG").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isChaobaoModified()) {
			              _sql.append("CHAOBAO").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isChaosongModified()) {
			              _sql.append("CHAOSONG").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isNigaodanweiModified()) {
			              _sql.append("NIGAODANWEI").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isNigaoModified()) {
			              _sql.append("NIGAO").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isHegaoModified()) {
			              _sql.append("HEGAO").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isYinshuaModified()) {
			              _sql.append("YINSHUA").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isJiaoduiModified()) {
			              _sql.append("JIAODUI").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isFenshuModified()) {
			              _sql.append("FENSHU").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isZhuticiModified()) {
			              _sql.append("ZHUTICI").append(",");
			              _dirtyCount++;
			   	}		
				if (fdtOaFwblDAO.isQicaoriqiModified()) {
			              _sql.append("QICAORIQI").append(",");
			              _dirtyCount++;
			   	}		
				
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
				
				if (fdtOaFwblDAO.isUuidModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getUuid());    
			   	}		
				if (fdtOaFwblDAO.isProcessinstIdModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getProcessinstId());    
			   	}		
				if (fdtOaFwblDAO.isActivityinstIdModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getActivityinstId());    
			   	}		
				if (fdtOaFwblDAO.isTypeTypeModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getTypeType());    
			   	}		
				if (fdtOaFwblDAO.isTypeWordModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getTypeWord());    
			   	}		
				if (fdtOaFwblDAO.isTypeYearModified()) {
				 ps.setInt(++_dirtyCount, fdtOaFwblDAO.getTypeYear());    
			   	}		
				if (fdtOaFwblDAO.isTypeNumModified()) {
				 ps.setInt(++_dirtyCount, fdtOaFwblDAO.getTypeNum());    
			   	}		
				if (fdtOaFwblDAO.isMijiModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getMiji());    
			   	}		
				if (fdtOaFwblDAO.isBaomiqixianModified()) {
				 ps.setInt(++_dirtyCount, fdtOaFwblDAO.getBaomiqixian());    
			   	}		
				if (fdtOaFwblDAO.isHuanjiModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getHuanji());    
			   	}		
				if (fdtOaFwblDAO.isDingmiyijuModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getDingmiyiju());    
			   	}		
				if (fdtOaFwblDAO.isQianfaModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getQianfa());    
			   	}		
				if (fdtOaFwblDAO.isHuiqianModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getHuiqian());    
			   	}		
				if (fdtOaFwblDAO.isBiaotiModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getBiaoti());    
			   	}		
				if (fdtOaFwblDAO.isZhusongModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getZhusong());    
			   	}		
				if (fdtOaFwblDAO.isChaobaoModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getChaobao());    
			   	}		
				if (fdtOaFwblDAO.isChaosongModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getChaosong());    
			   	}		
				if (fdtOaFwblDAO.isNigaodanweiModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getNigaodanwei());    
			   	}		
				if (fdtOaFwblDAO.isNigaoModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getNigao());    
			   	}		
				if (fdtOaFwblDAO.isHegaoModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getHegao());    
			   	}		
				if (fdtOaFwblDAO.isYinshuaModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getYinshua());    
			   	}		
				if (fdtOaFwblDAO.isJiaoduiModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getJiaodui());    
			   	}		
				if (fdtOaFwblDAO.isFenshuModified()) {
				 ps.setInt(++_dirtyCount, fdtOaFwblDAO.getFenshu());    
			   	}		
				if (fdtOaFwblDAO.isZhuticiModified()) {
				 ps.setString(++_dirtyCount, fdtOaFwblDAO.getZhutici());    
			   	}		
				if (fdtOaFwblDAO.isQicaoriqiModified()) {
				 ps.setTimestamp(++_dirtyCount, fdtOaFwblDAO.getQicaoriqi());    
			   	}		
				
				
				ps.executeUpdate();
				fdtOaFwblDAO.setIsNew(false);
				afterInsert(fdtOaFwblDAO);
			} else { // UPDATE
				beforeUpdate(fdtOaFwblDAO);
				_sql = new StringBuffer("UPDATE FDT_OA_FWBL SET ");
				
				if (fdtOaFwblDAO.isUuidModified()) {
				  _sql.append("UUID").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isProcessinstIdModified()) {
				  _sql.append("PROCESSINST_ID").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isActivityinstIdModified()) {
				  _sql.append("ACTIVITYINST_ID").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isTypeTypeModified()) {
				  _sql.append("TYPE_TYPE").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isTypeWordModified()) {
				  _sql.append("TYPE_WORD").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isTypeYearModified()) {
				  _sql.append("TYPE_YEAR").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isTypeNumModified()) {
				  _sql.append("TYPE_NUM").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isMijiModified()) {
				  _sql.append("MIJI").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isBaomiqixianModified()) {
				  _sql.append("BAOMIQIXIAN").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isHuanjiModified()) {
				  _sql.append("HUANJI").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isDingmiyijuModified()) {
				  _sql.append("DINGMIYIJU").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isQianfaModified()) {
				  _sql.append("QIANFA").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isHuiqianModified()) {
				  _sql.append("HUIQIAN").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isBiaotiModified()) {
				  _sql.append("BIAOTI").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isZhusongModified()) {
				  _sql.append("ZHUSONG").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isChaobaoModified()) {
				  _sql.append("CHAOBAO").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isChaosongModified()) {
				  _sql.append("CHAOSONG").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isNigaodanweiModified()) {
				  _sql.append("NIGAODANWEI").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isNigaoModified()) {
				  _sql.append("NIGAO").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isHegaoModified()) {
				  _sql.append("HEGAO").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isYinshuaModified()) {
				  _sql.append("YINSHUA").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isJiaoduiModified()) {
				  _sql.append("JIAODUI").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isFenshuModified()) {
				  _sql.append("FENSHU").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isZhuticiModified()) {
				  _sql.append("ZHUTICI").append("=?,");
			   	}		
				if (fdtOaFwblDAO.isQicaoriqiModified()) {
				  _sql.append("QICAORIQI").append("=?,");
			   	}		
				
				_sql.setLength(_sql.length() - 1);
				_sql.append(" WHERE ");
				_sql.append("FDT_OA_FWBL.UUID=?");
				if (log.isDebugEnabled())
					log.debug(_sql.toString());
				ps = c.prepareStatement(_sql.toString(),
						ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				int _dirtyCount = 0;
				
				if (fdtOaFwblDAO.isUuidModified()) {
				 	if (fdtOaFwblDAO.getUuid() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getUuid());
					}			
			   	}		
				if (fdtOaFwblDAO.isProcessinstIdModified()) {
				 	if (fdtOaFwblDAO.getProcessinstId() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getProcessinstId());
					}			
			   	}		
				if (fdtOaFwblDAO.isActivityinstIdModified()) {
				 	if (fdtOaFwblDAO.getActivityinstId() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getActivityinstId());
					}			
			   	}		
				if (fdtOaFwblDAO.isTypeTypeModified()) {
				 	if (fdtOaFwblDAO.getTypeType() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getTypeType());
					}			
			   	}		
				if (fdtOaFwblDAO.isTypeWordModified()) {
				 	if (fdtOaFwblDAO.getTypeWord() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getTypeWord());
					}			
			   	}		
				if (fdtOaFwblDAO.isTypeYearModified()) {
				 	if (fdtOaFwblDAO.getTypeYear() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setInt(++_dirtyCount,fdtOaFwblDAO.getTypeYear());
					}			
			   	}		
				if (fdtOaFwblDAO.isTypeNumModified()) {
				 	if (fdtOaFwblDAO.getTypeNum() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setInt(++_dirtyCount,fdtOaFwblDAO.getTypeNum());
					}			
			   	}		
				if (fdtOaFwblDAO.isMijiModified()) {
				 	if (fdtOaFwblDAO.getMiji() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getMiji());
					}			
			   	}		
				if (fdtOaFwblDAO.isBaomiqixianModified()) {
				 	if (fdtOaFwblDAO.getBaomiqixian() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setInt(++_dirtyCount,fdtOaFwblDAO.getBaomiqixian());
					}			
			   	}		
				if (fdtOaFwblDAO.isHuanjiModified()) {
				 	if (fdtOaFwblDAO.getHuanji() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getHuanji());
					}			
			   	}		
				if (fdtOaFwblDAO.isDingmiyijuModified()) {
				 	if (fdtOaFwblDAO.getDingmiyiju() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getDingmiyiju());
					}			
			   	}		
				if (fdtOaFwblDAO.isQianfaModified()) {
				 	if (fdtOaFwblDAO.getQianfa() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getQianfa());
					}			
			   	}		
				if (fdtOaFwblDAO.isHuiqianModified()) {
				 	if (fdtOaFwblDAO.getHuiqian() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getHuiqian());
					}			
			   	}		
				if (fdtOaFwblDAO.isBiaotiModified()) {
				 	if (fdtOaFwblDAO.getBiaoti() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getBiaoti());
					}			
			   	}		
				if (fdtOaFwblDAO.isZhusongModified()) {
				 	if (fdtOaFwblDAO.getZhusong() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getZhusong());
					}			
			   	}		
				if (fdtOaFwblDAO.isChaobaoModified()) {
				 	if (fdtOaFwblDAO.getChaobao() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getChaobao());
					}			
			   	}		
				if (fdtOaFwblDAO.isChaosongModified()) {
				 	if (fdtOaFwblDAO.getChaosong() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getChaosong());
					}			
			   	}		
				if (fdtOaFwblDAO.isNigaodanweiModified()) {
				 	if (fdtOaFwblDAO.getNigaodanwei() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getNigaodanwei());
					}			
			   	}		
				if (fdtOaFwblDAO.isNigaoModified()) {
				 	if (fdtOaFwblDAO.getNigao() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getNigao());
					}			
			   	}		
				if (fdtOaFwblDAO.isHegaoModified()) {
				 	if (fdtOaFwblDAO.getHegao() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getHegao());
					}			
			   	}		
				if (fdtOaFwblDAO.isYinshuaModified()) {
				 	if (fdtOaFwblDAO.getYinshua() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getYinshua());
					}			
			   	}		
				if (fdtOaFwblDAO.isJiaoduiModified()) {
				 	if (fdtOaFwblDAO.getJiaodui() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getJiaodui());
					}			
			   	}		
				if (fdtOaFwblDAO.isFenshuModified()) {
				 	if (fdtOaFwblDAO.getFenshu() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setInt(++_dirtyCount,fdtOaFwblDAO.getFenshu());
					}			
			   	}		
				if (fdtOaFwblDAO.isZhuticiModified()) {
				 	if (fdtOaFwblDAO.getZhutici() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setString(++_dirtyCount,fdtOaFwblDAO.getZhutici());
					}			
			   	}		
				if (fdtOaFwblDAO.isQicaoriqiModified()) {
				 	if (fdtOaFwblDAO.getQicaoriqi() == null){
						ps.setNull(++_dirtyCount, 12);
					}else{
						ps.setTimestamp(++_dirtyCount,fdtOaFwblDAO.getQicaoriqi());
					}			
			   	}		
				
				if (_dirtyCount != 0) {
					ps.setString(++_dirtyCount, fdtOaFwblDAO.getUuid());
					ps.executeUpdate();

					afterUpdate(fdtOaFwblDAO);
				}

			}
	
			fdtOaFwblDAO.resetIsModified();
		} catch (SQLException e) {
			throw new USMException("", e, USMException.UPDATE);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
		return fdtOaFwblDAO;
	}
	
	
	
	
	void prepareCache(List ids) {
	
		StringBuffer sqlWhere = new StringBuffer(" WHERE UUID IN (");
		int andCount = 0;
		// check out all UUIDs not in the cache now
		for (int i = 0; i < ids.size(); i++) {
			String uuid = (String) ids.get(i);
			if (fdtOaFwblCache.containsKey(uuid) == false) {
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
			FdtOaFwblDAO [] fdtOaFwbls = null;
			try {
				fdtOaFwbls = (FdtOaFwblDAO []) loadByWhere(sqlWhere.toString(), null);
			} catch (SQLException e) {
				log.error("prefetch the fdtOaFwbls Definition fail! Where Sql: "
						+ sqlWhere.toString());
				log.error("SqlException is ", e);
				return;
			}
			for (int i = 0; i < fdtOaFwbls.length; i++) {
				FdtOaFwblDAO fdtOaFwbl = fdtOaFwbls[i];
				putToCache(fdtOaFwbl.getUuid(), fdtOaFwbl);
			}
		}
	}
}
