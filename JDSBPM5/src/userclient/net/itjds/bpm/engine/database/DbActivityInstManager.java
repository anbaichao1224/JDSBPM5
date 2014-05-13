package net.itjds.bpm.engine.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.itjds.bpm.client.attribute.AttributeInterpreter;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.attribute.InterpreterManager;
import net.itjds.bpm.engine.inter.EIActivityDef;
import net.itjds.bpm.engine.inter.EIActivityDefManager;
import net.itjds.bpm.engine.inter.EIActivityInst;
import net.itjds.bpm.engine.inter.EIActivityInstManager;
import net.itjds.bpm.engine.inter.EIAttributeInst;
import net.itjds.bpm.engine.inter.EIProcessInst;
import net.itjds.bpm.engine.inter.EIProcessInstManager;
import net.itjds.common.cache.Cache;
import net.itjds.common.cache.CacheManager;
import net.itjds.common.cache.CacheManagerFactory;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.util.UUIDGenerator;

public class DbActivityInstManager extends EIActivityInstManager {
	private static final Log log = LogFactory.getLog("bpm",
			DbActivityInstManager.class);
	public static final int ID_ACTIVITYINST_ID = 0;
	public static final int TYPE_ACTIVITYINST_ID = 12;
	public static final String NAME_ACTIVITYINST_ID = "ACTIVITYINST_ID";
	public static final int ID_PROCESSINST_ID = 1;
	public static final int TYPE_PROCESSINST_ID = 12;
	public static final String NAME_PROCESSINST_ID = "PROCESSINST_ID";
	public static final int ID_ACTIVITYDEF_ID = 2;
	public static final int TYPE_ACTIVITYDEF_ID = 12;
	public static final String NAME_ACTIVITYDEF_ID = "ACTIVITYDEF_ID";
	public static final int ID_PROCESSDEF_ID = 3;
	public static final int TYPE_PROCESSDEF_ID = 12;
	public static final String NAME_PROCESSDEF_ID = "PROCESSDEF_ID";
	public static final int ID_ACTIVITYINST_STATE = 4;
	public static final int TYPE_ACTIVITYINST_STATE = 12;
	public static final String NAME_ACTIVITYINST_STATE = "ACTIVITYINST_STATE";
	public static final int ID_URGENCYTYPE = 5;
	public static final int TYPE_URGENCYTYPE = 12;
	public static final String NAME_URGENCYTYPE = "URGENCYTYPE";
	public static final int ID_ARRIVEDTIME = 6;
	public static final int TYPE_ARRIVEDTIME = 3;
	public static final String NAME_ARRIVEDTIME = "ARRIVEDTIME";
	public static final int ID_LIMITTIME = 7;
	public static final int TYPE_LIMITTIME = 3;
	public static final String NAME_LIMITTIME = "LIMITTIME";
	public static final int ID_ALERTTIME = 8;
	public static final int TYPE_ALERTTIME = 3;
	public static final String NAME_ALERTTIME = "ALERTTIME";
	public static final int ID_STARTTIME = 9;
	public static final int TYPE_STARTTIME = 3;
	public static final String NAME_STARTTIME = "STARTTIME";
	public static final int ID_RECEIVEMETHOD = 10;
	public static final int TYPE_RECEIVEMETHOD = 12;
	public static final String NAME_RECEIVEMETHOD = "RECEIVEMETHOD";
	public static final int ID_DEALMETHOD = 11;
	public static final int TYPE_DEALMETHOD = 12;
	public static final String NAME_DEALMETHOD = "DEALMETHOD";
	public static final int ID_RUNSTATUS = 12;
	public static final int TYPE_RUNSTATUS = 12;
	public static final String NAME_RUNSTATUS = "RUNSTATUS";
	public static final int ID_CANTAKEBACK = 13;
	public static final int TYPE_CANTAKEBACK = 12;
	public static final String NAME_CANTAKEBACK = "CANTAKEBACK";
	private static final String TABLE_NAME = "BPM_ACTIVITYINSTANCE";
	private static final String[] FIELD_NAMES = {
			"BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID",
			"BPM_ACTIVITYINSTANCE.PROCESSINST_ID",
			"BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID",
			"BPM_ACTIVITYINSTANCE.PROCESSDEF_ID",
			"BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE",
			"BPM_ACTIVITYINSTANCE.URGENCYTYPE",
			"BPM_ACTIVITYINSTANCE.ARRIVEDTIME",
			"BPM_ACTIVITYINSTANCE.LIMITTIME", "BPM_ACTIVITYINSTANCE.ALERTTIME",
			"BPM_ACTIVITYINSTANCE.STARTTIME",
			"BPM_ACTIVITYINSTANCE.RECEIVEMETHOD",
			"BPM_ACTIVITYINSTANCE.DEALMETHOD",
			"BPM_ACTIVITYINSTANCE.RUNSTATUS",
			"BPM_ACTIVITYINSTANCE.CANTAKEBACK" };

	private static final String[] TABLEFIELD_NAMES = { "ACTIVITYINST_ID",
			"PROCESSINST_ID", "ACTIVITYDEF_ID", "PROCESSDEF_ID",
			"ACTIVITYINST_STATE", "URGENCYTYPE", "ARRIVEDTIME", "LIMITTIME",
			"ALERTTIME", "STARTTIME", "RECEIVEMETHOD", "DEALMETHOD",
			"RUNSTATUS", "CANTAKEBACK" };
	private static final String ALL_FIELDS = "BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID,BPM_ACTIVITYINSTANCE.PROCESSINST_ID,BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID,BPM_ACTIVITYINSTANCE.PROCESSDEF_ID,BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE,BPM_ACTIVITYINSTANCE.URGENCYTYPE,BPM_ACTIVITYINSTANCE.ARRIVEDTIME,BPM_ACTIVITYINSTANCE.LIMITTIME,BPM_ACTIVITYINSTANCE.ALERTTIME,BPM_ACTIVITYINSTANCE.STARTTIME,BPM_ACTIVITYINSTANCE.RECEIVEMETHOD,BPM_ACTIVITYINSTANCE.DEALMETHOD,BPM_ACTIVITYINSTANCE.RUNSTATUS,BPM_ACTIVITYINSTANCE.CANTAKEBACK";
	Cache cache = null;
	boolean cacheEnabled;
	private static final String LOAD_EXTATTRIBUTRE = "SELECT  PROPERTY_ID,  ACTIVITYINST_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE FROM BPM_ACTIVITYINST_PROPERTY WHERE ACTIVITYINST_ID = ? ORDER BY PROPERTY_ID , ISEXTENSION ASC ";
	private static final String DELETE_ACTIVITY_EXTATTRIBUTRE = "DELETE FROM BPM_ACTIVITYINST_PROPERTY WHERE ACTIVITYINST_ID = ? ";
	private static final String DELETE_ONE_EXTATTRIBUTRE = "DELETE FROM BPM_ACTIVITYINST_PROPERTY WHERE PROPERTY_ID = ? ";
	private static final String INSERT_EXTATTRIBUTRE = "INSERT INTO BPM_ACTIVITYINST_PROPERTY (  PROPERTY_ID,  ACTIVITYINST_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?  ) ";
	private static final String UPDATE_EXTATTRIBUTRE = "UPDATE BPM_ACTIVITYINST_PROPERTY SET  PROPNAME=?,  PROPVALUE=?,  PROPCLASS=?,  PROPTYPE=?,  ISEXTENSION=?,  CANINSTANTIATE=?  where PROPERTY_ID=?";

	public DbActivityInstManager() {
		this.cache = CacheManagerFactory.getCache("bpm", "ActivityInstCache");
		this.cacheEnabled = CacheManagerFactory.getInstance().getCacheManager(
				"bpm").isCacheEnabled();
	}

	public EIActivityInst createActivityInstance() {
		return new DbActivityInst();
	}

	DbManager getManager() {
		return DbManager.getInstance();
	}

	void freeConnection(Connection c) {
		getManager().releaseConnection(c);
	}

	Connection getConnection() throws SQLException {
		return getManager().getConnection();
	}

	public EIActivityInst loadByKey(String activityInstId) throws BPMException {
		EIActivityInst dbActivityInst = (DbActivityInst) this.cache
				.get(activityInstId);
		if (dbActivityInst != null) {
			return dbActivityInst;
		}

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			log
					.debug("SELECT BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID,BPM_ACTIVITYINSTANCE.PROCESSINST_ID,BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID,BPM_ACTIVITYINSTANCE.PROCESSDEF_ID,BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE,BPM_ACTIVITYINSTANCE.URGENCYTYPE,BPM_ACTIVITYINSTANCE.ARRIVEDTIME,BPM_ACTIVITYINSTANCE.LIMITTIME,BPM_ACTIVITYINSTANCE.ALERTTIME,BPM_ACTIVITYINSTANCE.STARTTIME,BPM_ACTIVITYINSTANCE.RECEIVEMETHOD,BPM_ACTIVITYINSTANCE.DEALMETHOD,BPM_ACTIVITYINSTANCE.RUNSTATUS,BPM_ACTIVITYINSTANCE.CANTAKEBACK FROM BPM_ACTIVITYINSTANCE WHERE BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID=?");

			ps = c
					.prepareStatement(
							"SELECT BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID,BPM_ACTIVITYINSTANCE.PROCESSINST_ID,BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID,BPM_ACTIVITYINSTANCE.PROCESSDEF_ID,BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE,BPM_ACTIVITYINSTANCE.URGENCYTYPE,BPM_ACTIVITYINSTANCE.ARRIVEDTIME,BPM_ACTIVITYINSTANCE.LIMITTIME,BPM_ACTIVITYINSTANCE.ALERTTIME,BPM_ACTIVITYINSTANCE.STARTTIME,BPM_ACTIVITYINSTANCE.RECEIVEMETHOD,BPM_ACTIVITYINSTANCE.DEALMETHOD,BPM_ACTIVITYINSTANCE.RUNSTATUS,BPM_ACTIVITYINSTANCE.CANTAKEBACK FROM BPM_ACTIVITYINSTANCE WHERE BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID=?",
							1004, 1007);
			ps.setString(1, activityInstId);
			EIActivityInst[] pReturn = loadByPreparedStatement(ps);
			if (pReturn.length < 1)
				return null;

			putToCache(activityInstId, (DbActivityInst) pReturn[0]);
			DbActivityInst localDbActivityInst = (DbActivityInst) pReturn[0];
			return localDbActivityInst;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	public List loadByProcessInstId(String value) throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			log
					.debug("SELECT BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID,BPM_ACTIVITYINSTANCE.PROCESSINST_ID,BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID,BPM_ACTIVITYINSTANCE.PROCESSDEF_ID,BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE,BPM_ACTIVITYINSTANCE.URGENCYTYPE,BPM_ACTIVITYINSTANCE.ARRIVEDTIME,BPM_ACTIVITYINSTANCE.LIMITTIME,BPM_ACTIVITYINSTANCE.ALERTTIME,BPM_ACTIVITYINSTANCE.STARTTIME,BPM_ACTIVITYINSTANCE.RECEIVEMETHOD,BPM_ACTIVITYINSTANCE.DEALMETHOD,BPM_ACTIVITYINSTANCE.RUNSTATUS,BPM_ACTIVITYINSTANCE.CANTAKEBACK FROM BPM_ACTIVITYINSTANCE WHERE PROCESSINST_ID=?");

			ps = c
					.prepareStatement(
							"SELECT BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID,BPM_ACTIVITYINSTANCE.PROCESSINST_ID,BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID,BPM_ACTIVITYINSTANCE.PROCESSDEF_ID,BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE,BPM_ACTIVITYINSTANCE.URGENCYTYPE,BPM_ACTIVITYINSTANCE.ARRIVEDTIME,BPM_ACTIVITYINSTANCE.LIMITTIME,BPM_ACTIVITYINSTANCE.ALERTTIME,BPM_ACTIVITYINSTANCE.STARTTIME,BPM_ACTIVITYINSTANCE.RECEIVEMETHOD,BPM_ACTIVITYINSTANCE.DEALMETHOD,BPM_ACTIVITYINSTANCE.RUNSTATUS,BPM_ACTIVITYINSTANCE.CANTAKEBACK FROM BPM_ACTIVITYINSTANCE WHERE PROCESSINST_ID=?",
							1004, 1007);
			ps.setString(1, value);
			List localList = Arrays.asList(loadByPreparedStatement(ps));
			return localList;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	public int deleteByProcessInstId(String value) throws BPMException {
		String where = "PROCESSINST_ID='" + value + "'";
		return deleteByWhere(where);
	}

	public EIProcessInst getProcessInstance(EIActivityInst pObject)
			throws BPMException {
		EIProcessInst other = EIProcessInstManager.getInstance()
				.createProcessInstance();
		other.setProcessInstId(pObject.getProcessInstId());
		return ((DbProcessInstManager) DbProcessInstManager.getInstance())
				.loadObject(other);
	}

	public EIActivityInst setProcessInstance(EIActivityInst pObject,
			EIProcessInst pObjectToBeSet) {
		pObject.setProcessInstId(pObjectToBeSet.getProcessInstId());
		return pObject;
	}

	public List loadAll() throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			log
					.debug("SELECT BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID,BPM_ACTIVITYINSTANCE.PROCESSINST_ID,BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID,BPM_ACTIVITYINSTANCE.PROCESSDEF_ID,BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE,BPM_ACTIVITYINSTANCE.URGENCYTYPE,BPM_ACTIVITYINSTANCE.ARRIVEDTIME,BPM_ACTIVITYINSTANCE.LIMITTIME,BPM_ACTIVITYINSTANCE.ALERTTIME,BPM_ACTIVITYINSTANCE.STARTTIME,BPM_ACTIVITYINSTANCE.RECEIVEMETHOD,BPM_ACTIVITYINSTANCE.DEALMETHOD,BPM_ACTIVITYINSTANCE.RUNSTATUS,BPM_ACTIVITYINSTANCE.CANTAKEBACK FROM BPM_ACTIVITYINSTANCE");
			ps = c
					.prepareStatement(
							"SELECT BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID,BPM_ACTIVITYINSTANCE.PROCESSINST_ID,BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID,BPM_ACTIVITYINSTANCE.PROCESSDEF_ID,BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE,BPM_ACTIVITYINSTANCE.URGENCYTYPE,BPM_ACTIVITYINSTANCE.ARRIVEDTIME,BPM_ACTIVITYINSTANCE.LIMITTIME,BPM_ACTIVITYINSTANCE.ALERTTIME,BPM_ACTIVITYINSTANCE.STARTTIME,BPM_ACTIVITYINSTANCE.RECEIVEMETHOD,BPM_ACTIVITYINSTANCE.DEALMETHOD,BPM_ACTIVITYINSTANCE.RUNSTATUS,BPM_ACTIVITYINSTANCE.CANTAKEBACK FROM BPM_ACTIVITYINSTANCE",
							1004, 1007);

			List list = new DbActivityInstList(loadByPreparedStatement(ps,
					new int[1]));
			List localList1 = Collections.unmodifiableList(list);
			return localList1;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	public EIActivityInst[] loadByPreparedStatement(PreparedStatement ps)
			throws BPMException {
		return loadByPreparedStatement(ps, null);
	}

	public DbActivityInst[] loadByPreparedStatement(PreparedStatement ps,
			int[] fieldList) throws BPMException {
		ResultSet rs = null;
		ArrayList v = null;
		try {
			rs = ps.executeQuery();
			v = new ArrayList();
			while (rs.next()) {
				if (fieldList == null)
					v.add(decodeRow(rs));
				else
					v.add(decodeRow(rs, fieldList));
			}
			DbActivityInst[] arrayOfDbActivityInst = (DbActivityInst[]) v
					.toArray(new DbActivityInst[0]);
			return arrayOfDbActivityInst;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			if (v != null) {
				v.clear();
				v = null;
			}
			getManager().close(rs);
		}
	}

	public List loadByWhere(String where) throws BPMException {
		List result = new DbActivityInstList(loadByWhere(where, new int[1]));
		return Collections.unmodifiableList(result);
	}

	private DbActivityInst[] loadByWhere(String where, int[] fieldList)
			throws BPMException {
		String sql = null;
		if (fieldList == null) {
			sql = "select BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID,BPM_ACTIVITYINSTANCE.PROCESSINST_ID,BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID,BPM_ACTIVITYINSTANCE.PROCESSDEF_ID,BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE,BPM_ACTIVITYINSTANCE.URGENCYTYPE,BPM_ACTIVITYINSTANCE.ARRIVEDTIME,BPM_ACTIVITYINSTANCE.LIMITTIME,BPM_ACTIVITYINSTANCE.ALERTTIME,BPM_ACTIVITYINSTANCE.STARTTIME,BPM_ACTIVITYINSTANCE.RECEIVEMETHOD,BPM_ACTIVITYINSTANCE.DEALMETHOD,BPM_ACTIVITYINSTANCE.RUNSTATUS,BPM_ACTIVITYINSTANCE.CANTAKEBACK from BPM_ACTIVITYINSTANCE "
					+ where;
		} else {
			StringBuffer buff = new StringBuffer(128);
			buff.append("select ");
			for (int i = 0; i < fieldList.length; i++) {
				if (i != 0)
					buff.append(",");
				buff.append(FIELD_NAMES[fieldList[i]]);
			}
			buff.append(" from BPM_ACTIVITYINSTANCE ");
			buff.append(where);
			sql = buff.toString();
			buff = null;
		}
		Connection c = null;
		Statement pStatement = null;
		ResultSet rs = null;
		ArrayList v = null;
		try {
			c = getConnection();
			pStatement = c.createStatement();

			if (log.isDebugEnabled())
				log.debug("Executing SQL: " + sql);
			rs = pStatement.executeQuery(sql);
			v = new ArrayList();
			while (rs.next()) {
				if (fieldList == null)
					v.add(decodeRow(rs));
				else {
					v.add(decodeRow(rs, fieldList));
				}
			}
			DbActivityInst[] arrayOfDbActivityInst = (DbActivityInst[]) v
					.toArray(new DbActivityInst[0]);
			return arrayOfDbActivityInst;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			if (v != null) {
				v.clear();
			}
			getManager().close(pStatement, rs);
			freeConnection(c);
		}
	}

	public int deleteByKey(String activityinstId) throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			log
					.debug("DELETE from BPM_ACTIVITYINSTANCE WHERE BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID=?");
			ps = c
					.prepareStatement(
							"DELETE from BPM_ACTIVITYINSTANCE WHERE BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID=?",
							1004, 1007);
			ps.setString(1, activityinstId);
			int i = ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			removeFromCache(activityinstId);
			getManager().close(ps);
			freeConnection(c);
		}
	}

	public int delete(EIActivityInst activityInst) throws BPMException {
		DbActivityInst pObject = (DbActivityInst) activityInst;
		if (pObject.isActivityinstIdInitialized())
			return deleteByKey(pObject.getActivityInstId());
		Connection c = null;
		PreparedStatement ps = null;
		StringBuffer sql = null;
		try {
			sql = new StringBuffer("DELETE FROM BPM_ACTIVITYINSTANCE WHERE ");
			int _dirtyAnd = 0;
			if (pObject.isActivityinstIdInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ACTIVITYINST_ID").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isProcessinstIdInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("PROCESSINST_ID").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isActivitydefIdInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ACTIVITYDEF_ID").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isProcessdefIdInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("PROCESSDEF_ID").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isActivityinstStateInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ACTIVITYINST_STATE").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isUrgencytypeInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("URGENCYTYPE").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isArrivedtimeInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ARRIVEDTIME").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isLimittimeInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("LIMITTIME").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isAlerttimeInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ALERTTIME").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isStarttimeInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("STARTTIME").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isRecievestateInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("RECEIVEMETHOD").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isDealstateInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("DEALMETHOD").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isRunstateInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("RUNSTATUS").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isCantakebackInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("CANTAKEBACK").append("=?");
				_dirtyAnd++;
			}
			log.debug(sql.toString());
			c = getConnection();
			ps = c.prepareStatement(sql.toString(), 1004, 1007);
			int _dirtyCount = 0;
			if (pObject.isActivityinstIdInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getActivityInstId());
			}
			if (pObject.isProcessinstIdInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getProcessInstId());
			}
			if (pObject.isActivitydefIdInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getActivityDefId());
			}
			if (pObject.isProcessdefIdInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getProcessDefId());
			}
			if (pObject.isActivityinstStateInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getState());
			}
			if (pObject.isUrgencytypeInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getUrgency());
			}
			if (pObject.isArrivedtimeInitialized()) {
				_dirtyCount++;
				ps.setLong(_dirtyCount, pObject.getArrivedTime().getTime());
			}
			if (pObject.isLimittimeInitialized()) {
				_dirtyCount++;
				ps.setLong(_dirtyCount, pObject.getLimitTime().getTime());
			}
			if (pObject.isAlerttimeInitialized()) {
				_dirtyCount++;
				ps.setLong(_dirtyCount, pObject.getAlertTime().getTime());
			}
			if (pObject.isStarttimeInitialized()) {
				_dirtyCount++;
				ps.setLong(_dirtyCount, pObject.getStartTime().getTime());
			}
			if (pObject.isRecievestateInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getReceiveMethod());
			}
			if (pObject.isDealstateInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getDealMethod());
			}
			if (pObject.isRunstateInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getRunStatus());
			}
			if (pObject.isCantakebackInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getCanTakeBack());
			}
			int _rows = ps.executeUpdate();
			int i = _rows;
			return i;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	public int deleteByWhere(String where) throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			DbActivityInst[] activityInsts = loadByWhere(where, new int[1]);
			for (int i = 0; i < activityInsts.length; i++) {
				removeFromCache(activityInsts[i].getActivityInstId());
			}

			c = getConnection();
			String delByWhereSQL = "DELETE FROM BPM_ACTIVITYINSTANCE " + where;
			log.debug(delByWhereSQL);
			ps = c.prepareStatement(delByWhereSQL);
			int i = ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	public EIActivityInst[] save(EIActivityInst[] pObjects) throws BPMException {
		for (int iIndex = 0; iIndex < pObjects.length; iIndex++) {
			save(pObjects[iIndex]);
		}
		return pObjects;
	}

	void beforeInsert(EIActivityInst pObject) throws SQLException {
	}

	void afterInsert(EIActivityInst pObject) throws SQLException {
	}

	void beforeUpdate(EIActivityInst pObject) throws SQLException {
	}

	void afterUpdate(EIActivityInst pObject) throws SQLException {
	}

	public EIActivityInst save(EIActivityInst activityInst) throws BPMException {
		DbActivityInst pObject = (DbActivityInst) activityInst;
		Connection c = null;
		PreparedStatement ps = null;
		StringBuffer _sql = null;
		try {
			c = getConnection();
			if (pObject.isNew()) {
				beforeInsert(pObject);
				int _dirtyCount = 0;
				_sql = new StringBuffer("INSERT into BPM_ACTIVITYINSTANCE (");
				if (pObject.isActivityinstIdModified()) {
					_sql.append("ACTIVITYINST_ID").append(",");
					_dirtyCount++;
				}
				if (pObject.isProcessinstIdModified()) {
					_sql.append("PROCESSINST_ID").append(",");
					_dirtyCount++;
				}
				if (pObject.isActivitydefIdModified()) {
					_sql.append("ACTIVITYDEF_ID").append(",");
					_dirtyCount++;
				}
				if (pObject.isProcessdefIdModified()) {
					_sql.append("PROCESSDEF_ID").append(",");
					_dirtyCount++;
				}
				if (pObject.isActivityinstStateModified()) {
					_sql.append("ACTIVITYINST_STATE").append(",");
					_dirtyCount++;
				}
				if (pObject.isUrgencytypeModified()) {
					_sql.append("URGENCYTYPE").append(",");
					_dirtyCount++;
				}
				if (pObject.isArrivedtimeModified()) {
					_sql.append("ARRIVEDTIME").append(",");
					_dirtyCount++;
				}
				if (pObject.isLimittimeModified()) {
					_sql.append("LIMITTIME").append(",");
					_dirtyCount++;
				}
				if (pObject.isAlerttimeModified()) {
					_sql.append("ALERTTIME").append(",");
					_dirtyCount++;
				}
				if (pObject.isStarttimeModified()) {
					_sql.append("STARTTIME").append(",");
					_dirtyCount++;
				}
				if (pObject.isRecievestateModified()) {
					_sql.append("RECEIVEMETHOD").append(",");
					_dirtyCount++;
				}
				if (pObject.isDealstateModified()) {
					_sql.append("DEALMETHOD").append(",");
					_dirtyCount++;
				}
				if (pObject.isRunstateModified()) {
					_sql.append("RUNSTATUS").append(",");
					_dirtyCount++;
				}
				if (pObject.isCantakebackModified()) {
					_sql.append("CANTAKEBACK").append(",");
					_dirtyCount++;
				}
				_sql.setLength(_sql.length() - 1);
				_sql.append(") values (");
				for (int i = 0; i < _dirtyCount; i++)
					_sql.append("?,");
				_sql.setLength(_sql.length() - 1);
				_sql.append(")");

				log.debug(_sql.toString());
				ps = c.prepareStatement(_sql.toString(), 1004, 1007);
				_dirtyCount = 0;
				if (pObject.isActivityinstIdModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getActivityInstId());
				}
				if (pObject.isProcessinstIdModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getProcessInstId());
				}
				if (pObject.isActivitydefIdModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getActivityDefId());
				}
				if (pObject.isProcessdefIdModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getProcessDefId());
				}
				if (pObject.isActivityinstStateModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getState());
				}
				if (pObject.isUrgencytypeModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getUrgency());
				}
				if (pObject.isArrivedtimeModified()) {
					_dirtyCount++;
					ps.setLong(_dirtyCount, pObject.getArrivedTime().getTime());
				}
				if (pObject.isLimittimeModified()) {
					_dirtyCount++;
					ps.setLong(_dirtyCount, pObject.getLimitTime().getTime());
				}
				if (pObject.isAlerttimeModified()) {
					_dirtyCount++;
					ps.setLong(_dirtyCount, pObject.getAlertTime().getTime());
				}
				if (pObject.isStarttimeModified()) {
					_dirtyCount++;
					ps.setLong(_dirtyCount, pObject.getStartTime().getTime());
				}
				if (pObject.isRecievestateModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getReceiveMethod());
				}
				if (pObject.isDealstateModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getDealMethod());
				}
				if (pObject.isRunstateModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getRunStatus());
				}
				if (pObject.isCantakebackModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getCanTakeBack());
				}
				ps.executeUpdate();

				pObject.setIsNew(false);
				pObject.resetIsModified();
				afterInsert(pObject);
			} else {
				int _dirtyCount1=0;
				beforeUpdate(pObject);
				_sql = new StringBuffer("UPDATE BPM_ACTIVITYINSTANCE SET ");
				if (pObject.isActivityinstIdModified()){
					_dirtyCount1++;
					_sql.append("ACTIVITYINST_ID").append("=?,");
				}
					
				if (pObject.isProcessinstIdModified()){
					_dirtyCount1++;
					_sql.append("PROCESSINST_ID").append("=?,");
				}
					
				if (pObject.isActivitydefIdModified()){
					_dirtyCount1++;
					_sql.append("ACTIVITYDEF_ID").append("=?,");
				}
					
				if (pObject.isProcessdefIdModified()){
					_dirtyCount1++;
					_sql.append("PROCESSDEF_ID").append("=?,");
					
				}
					
				if (pObject.isActivityinstStateModified()){
					_dirtyCount1++;
					_sql.append("ACTIVITYINST_STATE").append("=?,");
					
				}
					
				if (pObject.isUrgencytypeModified()){
					_dirtyCount1++;
					_sql.append("URGENCYTYPE").append("=?,");
				}
					
				if (pObject.isArrivedtimeModified()){
					_dirtyCount1++;
					_sql.append("ARRIVEDTIME").append("=?,");
				}
					
				if (pObject.isLimittimeModified()){
					_dirtyCount1++;
					_sql.append("LIMITTIME").append("=?,");
				}
					
				if (pObject.isAlerttimeModified()){
					_dirtyCount1++;
					_sql.append("ALERTTIME").append("=?,");
				}
					
				if (pObject.isStarttimeModified()){
					_dirtyCount1++;
					_sql.append("STARTTIME").append("=?,");
				}
					
				if (pObject.isRecievestateModified()){
					_dirtyCount1++;
					_sql.append("RECEIVEMETHOD").append("=?,");
				}
					
				if (pObject.isDealstateModified()){
					_dirtyCount1++;
					_sql.append("DEALMETHOD").append("=?,");
				}
					
				if (pObject.isRunstateModified()){
					_dirtyCount1++;
					_sql.append("RUNSTATUS").append("=?,");
				}
					
				if (pObject.isCantakebackModified()){
					_dirtyCount1++;
					_sql.append("CANTAKEBACK").append("=?,");
				}
					
				_sql.setLength(_sql.length() - 1);
				_sql.append(" WHERE ");
				_sql.append("BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID=?");
				log.debug(_sql.toString());
				System.out.println("sql" + _sql.toString());
				if (_dirtyCount1 > 0) {
					_dirtyCount1++;
					ps = c.prepareStatement(_sql.toString(), 1004, 1007);
				}
				
				int _dirtyCount = 0;
				if (pObject.isActivityinstIdModified())
					if (pObject.getActivityInstId() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;

						ps.setString(_dirtyCount, pObject.getActivityInstId());
					}
				if (pObject.isProcessinstIdModified())
					if (pObject.getProcessInstId() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, pObject.getProcessInstId());
					}
				if (pObject.isActivitydefIdModified())
					if (pObject.getActivityDefId() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, pObject.getActivityDefId());
					}
				if (pObject.isProcessdefIdModified())
					if (pObject.getProcessDefId() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, pObject.getProcessDefId());
					}
				if (pObject.isActivityinstStateModified())
					if (pObject.getState() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, pObject.getState());
					}
				if (pObject.isUrgencytypeModified())
					if (pObject.getUrgency() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, pObject.getUrgency());
					}
				if (pObject.isArrivedtimeModified())
					if (pObject.getArrivedTime() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 3);
					} else {
						_dirtyCount++;
						ps.setLong(_dirtyCount, pObject.getArrivedTime()
								.getTime());
					}
				if (pObject.isLimittimeModified()) {
					_dirtyCount++;
					ps.setLong(_dirtyCount, pObject.getLimitTime().getTime());
				}
				if (pObject.isAlerttimeModified()) {
					_dirtyCount++;
					ps.setLong(_dirtyCount, pObject.getAlertTime().getTime());
				}
				if (pObject.isStarttimeModified())
					if (pObject.getStartTime() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 3);
					} else {
						_dirtyCount++;
						ps.setLong(_dirtyCount, pObject.getStartTime()
								.getTime());
					}
				if (pObject.isRecievestateModified())
					if (pObject.getReceiveMethod() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, pObject.getReceiveMethod());
					}
				if (pObject.isDealstateModified())
					if (pObject.getDealMethod() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, pObject.getDealMethod());
					}
				if (pObject.isRunstateModified())
					if (pObject.getRunStatus() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, pObject.getRunStatus());
					}
				if (pObject.isCantakebackModified())
					if (pObject.getCanTakeBack() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, pObject.getCanTakeBack());
					}
				if (_dirtyCount == 0) {
					DbActivityInst localDbActivityInst1 = pObject;
					return localDbActivityInst1;
				}
				if (_dirtyCount > 0) {
					_dirtyCount++;
					ps.setString(_dirtyCount, pObject.getActivityInstId());
					ps.executeUpdate();
					pObject.resetIsModified();

					afterUpdate(pObject);
				}

			}

			saveAttribute(pObject);
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
		getManager().close(ps);
		freeConnection(c);

		return pObject;
	}

	public DbActivityInst decodeRow(ResultSet rs) throws SQLException {
		DbActivityInst pObject = (DbActivityInst) createActivityInstance();
		if (rs.getObject(1) != null)
			pObject.setActivityInstId(rs.getString(1));
		if (rs.getObject(2) != null)
			pObject.setProcessInstId(rs.getString(2));
		if (rs.getObject(3) != null)
			pObject.setActivityDefId(rs.getString(3));
		if (rs.getObject(4) != null)
			pObject.setProcessDefId(rs.getString(4));
		if (rs.getObject(5) != null)
			pObject.setState(rs.getString(5));
		if (rs.getObject(6) != null)
			pObject.setUrgency(rs.getString(6));
		if (rs.getObject(7) != null)
			pObject.setArrivedTime(new Date(rs.getLong(7)));
		if (rs.getObject(8) != null)
			pObject.setLimitTime(new Date(rs.getLong(8)));
		if (rs.getObject(9) != null)
			pObject.setAlertTime(new Date(rs.getLong(9)));
		if (rs.getObject(10) != null)
			pObject.setStartTime(new Date(rs.getLong(10)));
		if (rs.getObject(11) != null)
			pObject.setReceiveMethod(rs.getString(11));
		if (rs.getObject(12) != null)
			pObject.setDealMethod(rs.getString(12));
		if (rs.getObject(13) != null)
			pObject.setRunStatus(rs.getString(13));
		if (rs.getObject(14) != null)
			pObject.setCanTakeBack(rs.getString(14));
		pObject.setIsNew(false);
		pObject.resetIsModified();

		return pObject;
	}

	public DbActivityInst decodeRow(ResultSet rs, int[] fieldList)
			throws SQLException {
		DbActivityInst pObject = (DbActivityInst) createActivityInstance();
		int pos = 0;
		for (int i = 0; i < fieldList.length; i++) {
			switch (fieldList[i]) {
			case 0:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setActivityInstId(rs.getString(pos));

				break;
			case 1:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setProcessInstId(rs.getString(pos));

				break;
			case 2:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setActivityDefId(rs.getString(pos));

				break;
			case 3:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setProcessDefId(rs.getString(pos));

				break;
			case 4:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setState(rs.getString(pos));

				break;
			case 5:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setUrgency(rs.getString(pos));

				break;
			case 6:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setArrivedTime(new Date(rs.getLong(pos)));

				break;
			case 7:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setLimitTime(new Date(rs.getLong(pos)));

				break;
			case 8:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setAlertTime(new Date(rs.getLong(pos)));

				break;
			case 9:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setStartTime(new Date(rs.getLong(pos)));

				break;
			case 10:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setReceiveMethod(rs.getString(pos));

				break;
			case 11:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setDealMethod(rs.getString(pos));

				break;
			case 12:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setRunStatus(rs.getString(pos));

				break;
			case 13:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setCanTakeBack(rs.getString(pos));
			}

		}

		pObject.setIsNew(false);
		pObject.resetIsModified();

		return pObject;
	}

	public EIActivityInst loadObject(EIActivityInst pObject)
			throws BPMException {
		EIActivityInst[] pReturn = loadObjects(pObject);
		if (pReturn.length == 0)
			return null;
		if (pReturn.length > 1)
			throw new BPMException("More than one element !!");
		return pReturn[0];
	}

	public EIActivityInst[] loadObjects(EIActivityInst activityInst)
			throws BPMException {
		DbActivityInst pObject = (DbActivityInst) activityInst;
		Connection c = null;
		PreparedStatement ps = null;
		StringBuffer where = new StringBuffer("");
		StringBuffer _sql = new StringBuffer(
				"SELECT BPM_ACTIVITYINSTANCE.ACTIVITYINST_ID,BPM_ACTIVITYINSTANCE.PROCESSINST_ID,BPM_ACTIVITYINSTANCE.ACTIVITYDEF_ID,BPM_ACTIVITYINSTANCE.PROCESSDEF_ID,BPM_ACTIVITYINSTANCE.ACTIVITYINST_STATE,BPM_ACTIVITYINSTANCE.URGENCYTYPE,BPM_ACTIVITYINSTANCE.ARRIVEDTIME,BPM_ACTIVITYINSTANCE.LIMITTIME,BPM_ACTIVITYINSTANCE.ALERTTIME,BPM_ACTIVITYINSTANCE.STARTTIME,BPM_ACTIVITYINSTANCE.RECEIVEMETHOD,BPM_ACTIVITYINSTANCE.DEALMETHOD,BPM_ACTIVITYINSTANCE.RUNSTATUS,BPM_ACTIVITYINSTANCE.CANTAKEBACK from BPM_ACTIVITYINSTANCE WHERE ");

		StringBuffer _sqlWhere = new StringBuffer("");
		try {
			int _dirtyCount = 0;
			if (pObject.isActivityinstIdModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("ACTIVITYINST_ID= ?");
			}
			if (pObject.isProcessinstIdModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("PROCESSINST_ID= ?");
			}
			if (pObject.isActivitydefIdModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("ACTIVITYDEF_ID= ?");
			}
			if (pObject.isProcessdefIdModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("PROCESSDEF_ID= ?");
			}
			if (pObject.isActivityinstStateModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("ACTIVITYINST_STATE= ?");
			}
			if (pObject.isUrgencytypeModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("URGENCYTYPE= ?");
			}
			if (pObject.isArrivedtimeModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("ARRIVEDTIME= ?");
			}

			if (pObject.isLimittimeModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("LIMITTIME= ?");
			}
			if (pObject.isAlerttimeModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("ALERTTIME= ?");
			}
			if (pObject.isStarttimeModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("STARTTIME= ?");
			}
			if (pObject.isRecievestateModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("RECEIVEMETHOD= ?");
			}
			if (pObject.isDealstateModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("DEALMETHOD= ?");
			}
			if (pObject.isRunstateModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("RUNSTATUS= ?");
			}
			if (pObject.isCantakebackModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("CANTAKEBACK= ?");
			}
			if (_dirtyCount == 0)
				throw new SQLException(
						"The pObject to look for is invalid : not initialized !");
			_sql.append(_sqlWhere);
			log.debug(_sql.toString());
			c = getConnection();
			ps = c.prepareStatement(_sql.toString(), 1004, 1007);
			_dirtyCount = 0;
			if (pObject.isActivityinstIdModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getActivityInstId());
			}
			if (pObject.isProcessinstIdModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getProcessInstId());
			}
			if (pObject.isActivitydefIdModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getActivityDefId());
			}
			if (pObject.isProcessdefIdModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getProcessDefId());
			}
			if (pObject.isActivityinstStateModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getState());
			}
			if (pObject.isUrgencytypeModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getUrgency());
			}
			if (pObject.isArrivedtimeModified()) {
				_dirtyCount++;
				ps.setLong(_dirtyCount, pObject.getArrivedTime().getTime());
			}
			if (pObject.isLimittimeModified()) {
				_dirtyCount++;
				ps.setLong(_dirtyCount, pObject.getLimitTime().getTime());
			}
			if (pObject.isAlerttimeModified()) {
				_dirtyCount++;
				ps.setLong(_dirtyCount, pObject.getAlertTime().getTime());
			}
			if (pObject.isStarttimeModified()) {
				_dirtyCount++;
				ps.setLong(_dirtyCount, pObject.getStartTime().getTime());
			}
			if (pObject.isRecievestateModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getReceiveMethod());
			}
			if (pObject.isDealstateModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getDealMethod());
			}
			if (pObject.isRunstateModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getRunStatus());
			}
			if (pObject.isCantakebackModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getCanTakeBack());
			}
			ps.executeQuery();
			EIActivityInst[] arrayOfEIActivityInst = loadByPreparedStatement(ps);
			return arrayOfEIActivityInst;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	public EIActivityInst copyActivityInst(EIActivityInst original)
			throws BPMException {
		DbActivityInst target = new DbActivityInst();
		target.copy((DbActivityInst) original);

		target.setActivityInstId(UUIDGenerator.genUUID());
		save(target);

		List attributes = new ArrayList(((DbActivityInst) original)
				.getTopAttribute());

		if (attributes != null) {
			int i = 0;
			for (int n = attributes.size(); i < n; i++) {
				EIAttributeInst attr = (EIAttributeInst) attributes.get(i);
				copyChildAttribute(null, attr.getType(), target, attr);
			}

		}

		save(target);
		return target;
	}

	private void copyChildAttribute(EIAttributeInst parent, String name,
			EIActivityInst target, EIAttributeInst attr) {
		EIAttributeInst newAttr = new DbAttributeInst();
		String attrId = UUIDGenerator.genUUID();
		newAttr.setId(attrId);
		newAttr.setInterpretClass(attr.getInterpretClass());
		newAttr.setInterpretedValue(attr.getInterpretedValue());
		newAttr.setName(attr.getName());
		if (parent == null)
			newAttr.setParentId(null);
		else {
			newAttr.setParentId(parent.getId());
		}
		newAttr.setType(attr.getType());
		newAttr.setValue(attr.getValue());

		newAttr.setParent(attr.getParent());
		try {
			target.setAttribute(name, newAttr);
		} catch (BPMException e) {
			log.error("", e);
		}
		List childrenAttr = attr.getChildren();
		if (childrenAttr != null)
			for (Iterator it = childrenAttr.iterator(); it.hasNext();) {
				EIAttributeInst child = (EIAttributeInst) it.next();
				copyChildAttribute(newAttr, newAttr.getName(), target, child);
			}
	}

	public void instantiateExtAttribute(EIActivityInst inst)
			throws BPMException {
		EIActivityDef activityDef = EIActivityDefManager.getInstance()
				.loadByKey(inst.getActivityDefId());
		List attrDefs = activityDef.getAllAttribute();
		for (Iterator iter = attrDefs.iterator(); iter.hasNext();) {
			DbAttributeDef attrDef = (DbAttributeDef) iter.next();
			String canInstantiate = attrDef.getCanInstantiate();

			if ((canInstantiate == null) || (!canInstantiate.equals("YES")))
				continue;
			DbAttributeInst attrInst = new DbAttributeInst();
			attrInst.setId(UUIDGenerator.genUUID());
			attrInst.setCanInstantiate(attrDef.getCanInstantiate());
			attrInst.setInterpretClass(attrDef.getInterpretClass());
			attrInst.setIsExtension(attrDef.getIsExtension());
			attrInst.setName(attrDef.getName());
			attrInst.setParentId(attrDef.getParentId());
			attrInst.setType(attrDef.getType());
			attrInst.setValue(attrDef.getValue());
			attrInst.setInterpretedValue(attrDef.getInterpretedValue());

			addAttribute((DbActivityInst) inst, attrInst);
		}
	}

	void loadAttribute(DbActivityInst act) throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			ps = c
					.prepareStatement("SELECT  PROPERTY_ID,  ACTIVITYINST_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE FROM BPM_ACTIVITYINST_PROPERTY WHERE ACTIVITYINST_ID = ? ORDER BY PROPERTY_ID , ISEXTENSION ASC ");
			ps.setString(1, act.getActivityInstId());
			ResultSet rs = ps.executeQuery();
			String lastUUID = "";
			DbAttributeInst extAtt = null;
			while (rs.next()) {
				String currUUID = rs.getString("PROPERTY_ID");

				if (currUUID.equals(lastUUID)) {
					String newValue = rs.getString("PROPVALUE");
					newValue = newValue == null ? "" : newValue;
					extAtt.value += newValue;
				} else {
					lastUUID = currUUID;
					if (extAtt != null) {
						addAttribute(act, extAtt);
					}
					extAtt = (DbAttributeInst) act.attributeIdMap.get(currUUID);
					if (extAtt == null) {
						extAtt = new DbAttributeInst();
					}
					extAtt.setId(currUUID);
					extAtt.setName(rs.getString("PROPNAME"));
					extAtt.setValue(rs.getString("PROPVALUE"));
					extAtt.setInterpretClass(rs.getString("PROPCLASS"));
					extAtt.setType(rs.getString("PROPTYPE"));
					extAtt.setParentId(rs.getString("PARENTPROP_ID"));
					extAtt.setCanInstantiate(rs.getString("CANINSTANTIATE"));
				}
			}
			if (extAtt != null)
				addAttribute(act, extAtt);
		} catch (SQLException e) {
			throw new BPMException("", e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	private void addAttribute(DbActivityInst act, DbAttributeInst extAtt) {
		if (act.attributeIdMap == null) {
			act.attributeIdMap = new HashMap();
		}
		if (act.attributeTopMap == null) {
			act.attributeTopMap = new HashMap();
		}
		String parentUUID = extAtt.getParentId();
		if ((parentUUID != null) && (!parentUUID.equals(""))) {
			DbAttributeInst parent = (DbAttributeInst) act.attributeIdMap
					.get(parentUUID);
			if (parent == null) {
				parent = new DbAttributeInst();
				act.attributeIdMap.put(parentUUID, parent);
			}
			parent.addChild(extAtt);
			extAtt.setParent(parent);
		} else {
			DbAttributeInst att = (DbAttributeInst) act.attributeTopMap
					.get(extAtt.getType());
			if (att == null) {
				att = new DbAttributeInst();
				att.setName(extAtt.getType());
				att.setType(extAtt.getType());
				act.attributeTopMap.put(att.getName(), att);
			}
			att.addChild(extAtt);
		}

		act.attributeIdMap.put(extAtt.getId(), extAtt);
	}

	void addAttributeToDb(DbActivityInst activityInst, DbAttributeInst attr)
			throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			ps = c
					.prepareStatement("INSERT INTO BPM_ACTIVITYINST_PROPERTY (  PROPERTY_ID,  ACTIVITYINST_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?  ) ");
			saveAttribute(ps, activityInst, attr);
		} catch (SQLException e) {
			throw new BPMException("", e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	void updateAttributeToDb(DbActivityInst activityInst, DbAttributeInst attr)
			throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();

			getManager().close(ps);
			ps = c
					.prepareStatement("UPDATE BPM_ACTIVITYINST_PROPERTY SET  PROPNAME=?,  PROPVALUE=?,  PROPCLASS=?,  PROPTYPE=?,  ISEXTENSION=?,  CANINSTANTIATE=?  where PROPERTY_ID=?");
			updateAttribute(ps, activityInst, attr);
		} catch (SQLException e) {
			throw new BPMException("", e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	void removeAttributeFromDb(DbAttributeInst attr) throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			ps = c
					.prepareStatement("DELETE FROM BPM_ACTIVITYINST_PROPERTY WHERE PROPERTY_ID = ? ");
			ps.setString(1, attr.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new BPMException("", e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	void saveAttribute(DbActivityInst activityInst) throws BPMException {
		if (!activityInst.isAttributeModified()) {
			return;
		}
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			ps = c
					.prepareStatement("DELETE FROM BPM_ACTIVITYINST_PROPERTY WHERE ACTIVITYINST_ID = ? ");
			log
					.debug("DELETE FROM BPM_ACTIVITYINST_PROPERTY WHERE ACTIVITYINST_ID = ? ");

			ps.setString(1, activityInst.getActivityInstId());
			int k = ps.executeUpdate();
			getManager().close(ps);
			ps = c
					.prepareStatement("INSERT INTO BPM_ACTIVITYINST_PROPERTY (  PROPERTY_ID,  ACTIVITYINST_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?  ) ");
			log
					.debug("INSERT INTO BPM_ACTIVITYINST_PROPERTY (  PROPERTY_ID,  ACTIVITYINST_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?  ) ");
			List list = activityInst.getAllAttribute();
			for (Iterator it = list.iterator(); it.hasNext();) {
				DbAttributeInst att = (DbAttributeInst) it.next();
				saveAttribute(ps, activityInst, att);
			}
		} catch (SQLException e) {
			throw new BPMException("", e, 1000);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	private void saveAttribute(PreparedStatement ps,
			DbActivityInst activityInst, DbAttributeDef attr)
			throws SQLException {
		DbAttributeInst attrInst = new DbAttributeInst();
		attrInst.setCanInstantiate(attr.getCanInstantiate());
		attrInst.setInterpretClass(attr.getInterpretClass());
		attrInst.setIsExtension(attr.getIsExtension());
		attrInst.setName(attr.getName());
		attrInst.setType(attr.getType());
		attrInst.setId(attr.getId());
		attrInst.setParentId(attr.getParentId());
		attrInst.setValue(attr.getValue());

		saveAttribute(ps, activityInst, attrInst);
	}

	private void saveAttribute(PreparedStatement ps,
			DbActivityInst activityInst, DbAttributeInst attInst)
			throws SQLException {
		AttributeInterpreter interpreter = InterpreterManager.getInstance()
				.getInterpreter(attInst.getInterpretClass());

		String value = interpreter.instantiate(attInst.getInterpretedValue());

		if (value == null) {
			value = "";
		}
		attInst.setValue(value);
		int block = value.length() / 500;
		for (int i = 0; i <= block; i++) {
			int begin = i * 500;
			int end = i == block ? value.length() : (i + 1) * 500;
			String v = value.substring(begin, end);

			ps.setString(1, attInst.getId());

			ps.setString(2, activityInst.getActivityInstId());
			ps.setString(3, attInst.getName());
			ps.setString(4, v);
			ps.setString(5, attInst.getInterpretClass());
			ps.setString(6, attInst.getType());
			ps.setString(7, attInst.getParentId());
			ps.setInt(8, i);
			ps.setString(9, attInst.getCanInstantiate());
			ps.executeUpdate();
		}
	}

	private void updateAttribute(PreparedStatement ps, DbActivityInst activity,
			DbAttributeInst attInst) throws SQLException {
		AttributeInterpreter interpreter = InterpreterManager.getInstance()
				.getInterpreter(attInst.getInterpretClass());

		String value = interpreter.instantiate(attInst.getInterpretedValue());
		if (value == null) {
			value = "";
		}

		attInst.setValue(value);
		int block = value.length() / 500;
		for (int i = 0; i <= block; i++) {
			int begin = i * 500;
			int end = i == block ? value.length() : (i + 1) * 500;
			String v = value.substring(begin, end);

			ps.setString(1, attInst.getName());
			ps.setString(2, v);
			ps.setString(3, attInst.getInterpretClass());
			ps.setString(4, attInst.getType());
			ps.setInt(5, i);
			ps.setString(6, attInst.getCanInstantiate());
			ps.setString(7, attInst.getId());
			ps.executeUpdate();
		}
	}

	private void putToCache(String uuid, DbActivityInst dbActivityInst) {
		synchronized (uuid.intern()) {
			if (!this.cache.containsKey(uuid)) {
				this.cache.put(uuid, dbActivityInst);
			}
		}
	}

	private void removeFromCache(String uuid) {
		synchronized (uuid.intern()) {
			if (this.cache.containsKey(uuid))
				this.cache.remove(uuid);
		}
	}

	private void removeFromCache(List uuids) {
		for (int i = 0; i < uuids.size(); i++) {
			Object obj = uuids.get(i);
			if ((obj instanceof String)) {
				String uuid = (String) obj;
				removeFromCache(uuid);
			}
		}
	}

	void prepareCache(List ids) {
		StringBuffer sqlWhere = new StringBuffer(" WHERE ACTIVITYINST_ID IN (");
		int andCount = 0;

		for (int i = 0; i < ids.size(); i++) {
			String uuid = (String) ids.get(i);
			if (!this.cache.containsKey(uuid)) {
				if (andCount > 0) {
					sqlWhere.append(",");
				}
				sqlWhere.append(" '" + uuid + "'");
				andCount++;
			}
		}

		if (andCount > 0) {
			sqlWhere.append(") ");
			EIActivityInst[] list = (EIActivityInst[]) null;
			try {
				list = loadByWhere(sqlWhere.toString(), null);
			} catch (BPMException e) {
				log.error("prefetch the activity instance fail! Where Sql: "
						+ sqlWhere.toString());
				log.error("SqlException is ", e);
				return;
			}
			for (int i = 0; i < list.length; i++) {
				DbActivityInst pd = (DbActivityInst) list[i];
				putToCache(pd.getActivityInstId(), pd);
			}
		}
	}
}