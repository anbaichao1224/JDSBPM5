package net.itjds.bpm.engine.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import net.itjds.bpm.client.attribute.AttributeInterpreter;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.attribute.InterpreterManager;
import net.itjds.bpm.engine.inter.EIActivityInst;
import net.itjds.bpm.engine.inter.EIActivityInstHistory;
import net.itjds.bpm.engine.inter.EIActivityInstHistoryManager;
import net.itjds.bpm.engine.inter.EIProcessInst;
import net.itjds.bpm.engine.inter.EIProcessInstManager;
import net.itjds.common.cache.Cache;
import net.itjds.common.cache.CacheManagerFactory;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.util.UUIDGenerator;

public class DbActivityInstHistoryManager extends EIActivityInstHistoryManager {
	private static final Log log = LogFactory.getLog("bpm",
			DbActivityInstHistoryManager.class);
	public static final int ID_ACTIVITYHISTORY_ID = 0;
	public static final int TYPE_ACTIVITYHISTORY_ID = 12;
	public static final String NAME_ACTIVITYHISTORY_ID = "ACTIVITYHISTORY_ID";
	public static final int ID_PROCESSINST_ID = 1;
	public static final int TYPE_PROCESSINST_ID = 12;
	public static final String NAME_PROCESSINST_ID = "PROCESSINST_ID";
	public static final int ID_ACTIVITYDEF_ID = 2;
	public static final int TYPE_ACTIVITYDEF_ID = 12;
	public static final String NAME_ACTIVITYDEF_ID = "ACTIVITYDEF_ID";
	public static final int ID_ACTIVITYINST_ID = 3;
	public static final int TYPE_ACTIVITYINST_ID = 12;
	public static final String NAME_ACTIVITYINST_ID = "ACTIVITYINST_ID";
	public static final int ID_URGENCYTYPE = 4;
	public static final int TYPE_URGENCYTYPE = 12;
	public static final String NAME_URGENCYTYPE = "URGENCYTYPE";
	public static final int ID_ARRIVEDTIME = 5;
	public static final int TYPE_ARRIVEDTIME = 3;
	public static final String NAME_ARRIVEDTIME = "ARRIVEDTIME";
	public static final int ID_LIMITTIME = 6;
	public static final int TYPE_LIMITTIME = 3;
	public static final String NAME_LIMITTIME = "LIMITTIME";
	public static final int ID_STARTTIME = 7;
	public static final int TYPE_STARTTIME = 3;
	public static final String NAME_STARTTIME = "STARTTIME";
	public static final int ID_ENDTIME = 8;
	public static final int TYPE_ENDTIME = 3;
	public static final String NAME_ENDTIME = "ENDTIME";
	public static final int ID_RECEIVEMETHOD = 9;
	public static final int TYPE_RECEIVEMETHOD = 12;
	public static final String NAME_RECEIVEMETHOD = "RECEIVEMETHOD";
	public static final int ID_DEALMETHOD = 10;
	public static final int TYPE_DEALMETHOD = 12;
	public static final String NAME_DEALMETHOD = "DEALMETHOD";
	public static final int ID_RUNSTATUS = 11;
	public static final int TYPE_RUNSTATUS = 12;
	public static final String NAME_RUNSTATUS = "RUNSTATUS";
	@SuppressWarnings("unused")
	private static final String TABLE_NAME = "BPM_ACTIVITYHISTORY";
	private static final String[] FIELD_NAMES = {
			"BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID",
			"BPM_ACTIVITYHISTORY.PROCESSINST_ID",
			"BPM_ACTIVITYHISTORY.ACTIVITYDEF_ID",
			"BPM_ACTIVITYHISTORY.ACTIVITYINST_ID",
			"BPM_ACTIVITYHISTORY.URGENCYTYPE",
			"BPM_ACTIVITYHISTORY.ARRIVEDTIME", "BPM_ACTIVITYHISTORY.LIMITTIME",
			"BPM_ACTIVITYHISTORY.STARTTIME", "BPM_ACTIVITYHISTORY.ENDTIME",
			"BPM_ACTIVITYHISTORY.RECEIVEMETHOD",
			"BPM_ACTIVITYHISTORY.DEALMETHOD", "BPM_ACTIVITYHISTORY.RUNSTATUS" };

	@SuppressWarnings("unused")
	private static final String[] TABLEFIELD_NAMES = { "ACTIVITYHISTORY_ID",
			"PROCESSINST_ID", "ACTIVITYDEF_ID", "ACTIVITYINST_ID",
			"URGENCYTYPE", "ARRIVEDTIME", "LIMITTIME", "STARTTIME", "ENDTIME",
			"RECEIVEMETHOD", "DEALMETHOD", "RUNSTATUS" };
	@SuppressWarnings("unused")
	private static final String ALL_FIELDS = "BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID,BPM_ACTIVITYHISTORY.PROCESSINST_ID,BPM_ACTIVITYHISTORY.ACTIVITYDEF_ID,BPM_ACTIVITYHISTORY.ACTIVITYINST_ID,BPM_ACTIVITYHISTORY.URGENCYTYPE,BPM_ACTIVITYHISTORY.ARRIVEDTIME,BPM_ACTIVITYHISTORY.LIMITTIME,BPM_ACTIVITYHISTORY.STARTTIME,BPM_ACTIVITYHISTORY.ENDTIME,BPM_ACTIVITYHISTORY.RECEIVEMETHOD,BPM_ACTIVITYHISTORY.DEALMETHOD,BPM_ACTIVITYHISTORY.RUNSTATUS";
	@SuppressWarnings("unused")
	private static DbActivityInstHistoryManager singleton = new DbActivityInstHistoryManager();

	Cache cache = null;
	boolean cacheEnabled;
	@SuppressWarnings("unused")
	private static final String LOAD_EXTATTRIBUTRE = "SELECT  PROPERTY_ID,  ACTIVITYHISTORY_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE FROM BPM_ACTIVITYHISTORY_PROPERTY WHERE ACTIVITYHISTORY_ID = ? ORDER BY PROPERTY_ID , ISEXTENSION ASC ";
	@SuppressWarnings("unused")
	private static final String DELETE_ONE_EXTATTRIBUTRE = "DELETE FROM BPM_ACTIVITYHISTORY_PROPERTY WHERE PROPERTY_ID = ? ";
	@SuppressWarnings("unused")
	private static final String DELETE_ACTIVITY_EXTATTRIBUTRE = "DELETE FROM BPM_ACTIVITYHISTORY_PROPERTY WHERE ACTIVITYHISTORY_ID = ? ";
	@SuppressWarnings("unused")
	private static final String INSERT_EXTATTRIBUTRE = "INSERT INTO BPM_ACTIVITYHISTORY_PROPERTY (  PROPERTY_ID,  ACTIVITYHISTORY_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?  ) ";
	@SuppressWarnings("unused")
	private static final String UPDATE_EXTATTRIBUTRE = "UPDATE BPM_ACTIVITYHISTORY_PROPERTY SET  PROPNAME=?,  PROPVALUE=?,  PROPCLASS=?,  PROPTYPE=?,  ISEXTENSION=?,  CANINSTANTIATE=?  where PROPERTY_ID=?";

	public DbActivityInstHistoryManager() {
		this.cache = CacheManagerFactory.getCache("bpm",
				"ActivityInstHistoryCache");
		this.cacheEnabled = CacheManagerFactory.getInstance().getCacheManager(
				"bpm").isCacheEnabled();
	}

	public EIActivityInstHistory createActivityHistory() {
		return new DbActivityInstHistory();
	}

	@SuppressWarnings("unchecked")
	public EIActivityInstHistory saveActivityInstAsHistory(
			EIActivityInst activityInst) throws BPMException {
		DbActivityInstHistory history = new DbActivityInstHistory();
		if (activityInst != null) {
			history.setActivityDefId(activityInst.getActivityDefId());
			history.setActivityHistoryId(UUIDGenerator.genUUID());
			history.setActivityInstId(activityInst.getActivityInstId());
			history.setProcessInstId(activityInst.getProcessInstId());

			history.setArrivedTime(activityInst.getArrivedTime());
			history.setDealMethod(activityInst.getDealMethod());
			history.setEndTime(new Date());
			history.setLimitTime(activityInst.getLimitTime());
			history.setReceiveMethod(activityInst.getReceiveMethod());
			history.setRunStatus(activityInst.getRunStatus());
			history.setStartTime(activityInst.getStartTime());
			history.setUrgency(activityInst.getUrgency());
			save(history);

			Connection c = null;
			PreparedStatement ps = null;
			try {
				c = getConnection();
				ps = c
						.prepareStatement("INSERT INTO BPM_ACTIVITYHISTORY_PROPERTY (  PROPERTY_ID,  ACTIVITYHISTORY_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?  ) ");
				List list = activityInst.getAllAttribute();
				for (Iterator it = list.iterator(); it.hasNext();) {
					DbAttributeInst att = (DbAttributeInst) it.next();
					saveAttribute(ps, history, att);
				}
			} catch (SQLException e) {
				throw new BPMException("", e);
			} finally {
				getManager().close(ps);
				freeConnection(c);
			}
		}
		return history;
	}

	void loadAttribute(DbActivityInstHistory act) throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			ps = c
					.prepareStatement("SELECT  PROPERTY_ID,  ACTIVITYHISTORY_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE FROM BPM_ACTIVITYHISTORY_PROPERTY WHERE ACTIVITYHISTORY_ID = ? ORDER BY PROPERTY_ID , ISEXTENSION ASC ");
			ps.setString(1, act.getActivityHistoryId());
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

	void addAttributeToDb(DbActivityInstHistory activityInst,
			DbAttributeInst attr) throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();

			getManager().close(ps);
			ps = c
					.prepareStatement("INSERT INTO BPM_ACTIVITYHISTORY_PROPERTY (  PROPERTY_ID,  ACTIVITYHISTORY_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?  ) ");
			saveAttribute(ps, activityInst, attr);
		} catch (SQLException e) {
			throw new BPMException("", e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	void updateAttributeToDb(DbActivityInstHistory activityInst,
			DbAttributeInst attr) throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();

			getManager().close(ps);
			ps = c
					.prepareStatement("UPDATE BPM_ACTIVITYHISTORY_PROPERTY SET  PROPNAME=?,  PROPVALUE=?,  PROPCLASS=?,  PROPTYPE=?,  ISEXTENSION=?,  CANINSTANTIATE=?  where PROPERTY_ID=?");
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
					.prepareStatement("DELETE FROM BPM_ACTIVITYHISTORY_PROPERTY WHERE PROPERTY_ID = ? ");
			ps.setString(1, attr.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new BPMException("", e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	@SuppressWarnings("unchecked")
	private void addAttribute(DbActivityInstHistory act, DbAttributeInst extAtt) {
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

	@SuppressWarnings("unchecked")
	void saveAttribute(DbActivityInstHistory activityInstHistory)
			throws BPMException {
		if (!activityInstHistory.isAttributeModified()) {
			return;
		}
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			ps = c
					.prepareStatement("DELETE FROM BPM_ACTIVITYHISTORY_PROPERTY WHERE ACTIVITYHISTORY_ID = ? ");
			ps.setString(1, activityInstHistory.getActivityHistoryId());
			@SuppressWarnings("unused")
			int i = ps.executeUpdate();
			getManager().close(ps);
			ps = c
					.prepareStatement("INSERT INTO BPM_ACTIVITYHISTORY_PROPERTY (  PROPERTY_ID,  ACTIVITYHISTORY_ID,  PROPNAME,  PROPVALUE,  PROPCLASS,  PROPTYPE,  PARENTPROP_ID,  ISEXTENSION,  CANINSTANTIATE ) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?  ) ");
			List list = activityInstHistory.getAllAttribute();
			for (Iterator it = list.iterator(); it.hasNext();) {
				DbAttributeInst att = (DbAttributeInst) it.next();
				saveAttribute(ps, activityInstHistory, att);
			}
		} catch (SQLException e) {
			throw new BPMException("", e, 1000);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	private void saveAttribute(PreparedStatement ps,
			DbActivityInstHistory activityInstHistory, DbAttributeInst attInst)
			throws SQLException {
		String value = attInst.getValue();
		if (value == null) {
			value = "";
		}
		int block = value.length() / 500;
		for (int i = 0; i <= block; i++) {
			int begin = i * 500;
			int end = i == block ? value.length() : (i + 1) * 500;
			String v = value.substring(begin, end);

			ps.setString(1, attInst.getId());
			ps.setString(2, activityInstHistory.getActivityHistoryId());
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

	private void updateAttribute(PreparedStatement ps,
			DbActivityInstHistory activity, DbAttributeInst attInst)
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

	DbManager getManager() {
		return DbManager.getInstance();
	}

	void freeConnection(Connection c) {
		getManager().releaseConnection(c);
	}

	Connection getConnection() throws SQLException {
		return getManager().getConnection();
	}

	public EIActivityInstHistory loadByKey(String activityHistoryId)
			throws BPMException {
		EIActivityInstHistory dbActivityHistoryInst = (DbActivityInstHistory) this.cache
				.get(activityHistoryId);
		if (dbActivityHistoryInst != null) {
			return dbActivityHistoryInst;
		}

		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			log
					.debug("SELECT BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID,BPM_ACTIVITYHISTORY.PROCESSINST_ID,BPM_ACTIVITYHISTORY.ACTIVITYDEF_ID,BPM_ACTIVITYHISTORY.ACTIVITYINST_ID,BPM_ACTIVITYHISTORY.URGENCYTYPE,BPM_ACTIVITYHISTORY.ARRIVEDTIME,BPM_ACTIVITYHISTORY.LIMITTIME,BPM_ACTIVITYHISTORY.STARTTIME,BPM_ACTIVITYHISTORY.ENDTIME,BPM_ACTIVITYHISTORY.RECEIVEMETHOD,BPM_ACTIVITYHISTORY.DEALMETHOD,BPM_ACTIVITYHISTORY.RUNSTATUS FROM BPM_ACTIVITYHISTORY WHERE BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID=?");

			ps = c
					.prepareStatement(
							"SELECT BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID,BPM_ACTIVITYHISTORY.PROCESSINST_ID,BPM_ACTIVITYHISTORY.ACTIVITYDEF_ID,BPM_ACTIVITYHISTORY.ACTIVITYINST_ID,BPM_ACTIVITYHISTORY.URGENCYTYPE,BPM_ACTIVITYHISTORY.ARRIVEDTIME,BPM_ACTIVITYHISTORY.LIMITTIME,BPM_ACTIVITYHISTORY.STARTTIME,BPM_ACTIVITYHISTORY.ENDTIME,BPM_ACTIVITYHISTORY.RECEIVEMETHOD,BPM_ACTIVITYHISTORY.DEALMETHOD,BPM_ACTIVITYHISTORY.RUNSTATUS FROM BPM_ACTIVITYHISTORY WHERE BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID=?",
							1004, 1007);
			ps.setString(1, activityHistoryId);
			DbActivityInstHistory[] pReturn = loadByPreparedStatement(ps);
			if (pReturn.length < 1)
				return null;
			putToCache(activityHistoryId, pReturn[0]);
			DbActivityInstHistory localDbActivityInstHistory = pReturn[0];
			return localDbActivityInstHistory;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	public EIActivityInstHistory[] loadByProcessInstId(String value)
			throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			getManager()
					.log(
							"SELECT BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID,BPM_ACTIVITYHISTORY.PROCESSINST_ID,BPM_ACTIVITYHISTORY.ACTIVITYDEF_ID,BPM_ACTIVITYHISTORY.ACTIVITYINST_ID,BPM_ACTIVITYHISTORY.URGENCYTYPE,BPM_ACTIVITYHISTORY.ARRIVEDTIME,BPM_ACTIVITYHISTORY.LIMITTIME,BPM_ACTIVITYHISTORY.STARTTIME,BPM_ACTIVITYHISTORY.ENDTIME,BPM_ACTIVITYHISTORY.RECEIVEMETHOD,BPM_ACTIVITYHISTORY.DEALMETHOD,BPM_ACTIVITYHISTORY.RUNSTATUS FROM BPM_ACTIVITYHISTORY WHERE PROCESSINST_ID=?");

			ps = c
					.prepareStatement(
							"SELECT BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID,BPM_ACTIVITYHISTORY.PROCESSINST_ID,BPM_ACTIVITYHISTORY.ACTIVITYDEF_ID,BPM_ACTIVITYHISTORY.ACTIVITYINST_ID,BPM_ACTIVITYHISTORY.URGENCYTYPE,BPM_ACTIVITYHISTORY.ARRIVEDTIME,BPM_ACTIVITYHISTORY.LIMITTIME,BPM_ACTIVITYHISTORY.STARTTIME,BPM_ACTIVITYHISTORY.ENDTIME,BPM_ACTIVITYHISTORY.RECEIVEMETHOD,BPM_ACTIVITYHISTORY.DEALMETHOD,BPM_ACTIVITYHISTORY.RUNSTATUS FROM BPM_ACTIVITYHISTORY WHERE PROCESSINST_ID=?",
							1004, 1007);
			ps.setString(1, value);
			DbActivityInstHistory[] arrayOfDbActivityInstHistory = loadByPreparedStatement(ps);
			return arrayOfDbActivityInstHistory;
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

	public EIProcessInst getProcessInstance(DbActivityInstHistory pObject)
			throws BPMException {
		EIProcessInst other = EIProcessInstManager.getInstance()
				.createProcessInstance();
		other.setProcessInstId(pObject.getProcessInstId());
		return ((DbProcessInstManager) DbProcessInstManager.getInstance())
				.loadObject(other);
	}

	public EIActivityInstHistory setProcessInstance(
			EIActivityInstHistory pObject, EIProcessInst pObjectToBeSet) {
		pObject.setProcessInstId(pObjectToBeSet.getProcessInstId());
		return pObject;
	}

	@SuppressWarnings("unchecked")
	public List loadAll() throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			getManager()
					.log(
							"SELECT BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID,BPM_ACTIVITYHISTORY.PROCESSINST_ID,BPM_ACTIVITYHISTORY.ACTIVITYDEF_ID,BPM_ACTIVITYHISTORY.ACTIVITYINST_ID,BPM_ACTIVITYHISTORY.URGENCYTYPE,BPM_ACTIVITYHISTORY.ARRIVEDTIME,BPM_ACTIVITYHISTORY.LIMITTIME,BPM_ACTIVITYHISTORY.STARTTIME,BPM_ACTIVITYHISTORY.ENDTIME,BPM_ACTIVITYHISTORY.RECEIVEMETHOD,BPM_ACTIVITYHISTORY.DEALMETHOD,BPM_ACTIVITYHISTORY.RUNSTATUS FROM BPM_ACTIVITYHISTORY");
			ps = c
					.prepareStatement(
							"SELECT BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID,BPM_ACTIVITYHISTORY.PROCESSINST_ID,BPM_ACTIVITYHISTORY.ACTIVITYDEF_ID,BPM_ACTIVITYHISTORY.ACTIVITYINST_ID,BPM_ACTIVITYHISTORY.URGENCYTYPE,BPM_ACTIVITYHISTORY.ARRIVEDTIME,BPM_ACTIVITYHISTORY.LIMITTIME,BPM_ACTIVITYHISTORY.STARTTIME,BPM_ACTIVITYHISTORY.ENDTIME,BPM_ACTIVITYHISTORY.RECEIVEMETHOD,BPM_ACTIVITYHISTORY.DEALMETHOD,BPM_ACTIVITYHISTORY.RUNSTATUS FROM BPM_ACTIVITYHISTORY",
							1004, 1007);

			List list = new DbActivityInstHistoryList(loadByPreparedStatement(
					ps, new int[1]));
			List localList1 = Collections.unmodifiableList(list);
			return localList1;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	public DbActivityInstHistory[] loadByPreparedStatement(PreparedStatement ps)
			throws BPMException {
		return loadByPreparedStatement(ps, null);
	}

	@SuppressWarnings("unchecked")
	public DbActivityInstHistory[] loadByPreparedStatement(
			PreparedStatement ps, int[] fieldList) throws BPMException {
		ResultSet rs = null;
		ArrayList v = null;
		try {
			rs = ps.executeQuery();
			v = new ArrayList();
			while (rs.next()) {
				if (fieldList == null)
					v.add(decodeRow(rs));
				else {
					v.add(decodeRow(rs, fieldList));
				}
			}
			DbActivityInstHistory[] arrayOfDbActivityInstHistory = (DbActivityInstHistory[]) v
					.toArray(new DbActivityInstHistory[0]);
			return arrayOfDbActivityInstHistory;
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

	@SuppressWarnings("unchecked")
	public List loadByWhere(String where) throws BPMException {
		List result = new DbActivityInstHistoryList(loadByWhere(where,
				new int[1]));
		return Collections.unmodifiableList(result);
	}

	@SuppressWarnings("unchecked")
	private DbActivityInstHistory[] loadByWhere(String where, int[] fieldList)
			throws BPMException {
		String sql = null;
		if (fieldList == null) {
			sql = "select BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID,BPM_ACTIVITYHISTORY.PROCESSINST_ID,BPM_ACTIVITYHISTORY.ACTIVITYDEF_ID,BPM_ACTIVITYHISTORY.ACTIVITYINST_ID,BPM_ACTIVITYHISTORY.URGENCYTYPE,BPM_ACTIVITYHISTORY.ARRIVEDTIME,BPM_ACTIVITYHISTORY.LIMITTIME,BPM_ACTIVITYHISTORY.STARTTIME,BPM_ACTIVITYHISTORY.ENDTIME,BPM_ACTIVITYHISTORY.RECEIVEMETHOD,BPM_ACTIVITYHISTORY.DEALMETHOD,BPM_ACTIVITYHISTORY.RUNSTATUS from BPM_ACTIVITYHISTORY "
					+ where;
		} else {
			StringBuffer buff = new StringBuffer(128);
			buff.append("select ");
			for (int i = 0; i < fieldList.length; i++) {
				if (i != 0)
					buff.append(",");
				buff.append(FIELD_NAMES[fieldList[i]]);
			}
			buff.append(" from BPM_ACTIVITYHISTORY ");
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

			DbActivityInstHistory[] arrayOfDbActivityInstHistory = (DbActivityInstHistory[]) v
					.toArray(new DbActivityInstHistory[0]);
			return arrayOfDbActivityInstHistory;
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

	public int deleteByKey(String activityhistoryId) throws BPMException {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = getConnection();
			getManager()
					.log(
							"DELETE from BPM_ACTIVITYHISTORY WHERE BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID=?");
			ps = c
					.prepareStatement(
							"DELETE from BPM_ACTIVITYHISTORY WHERE BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID=?",
							1004, 1007);
			ps.setString(1, activityhistoryId);
			int i = ps.executeUpdate();
			return i;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			removeFromCache(activityhistoryId);
			getManager().close(ps);
			freeConnection(c);
		}
	}

	public int delete(EIActivityInstHistory object) throws BPMException {
		DbActivityInstHistory pObject = (DbActivityInstHistory) object;
		if (pObject.isActivityhistoryIdInitialized())
			return deleteByKey(pObject.getActivityHistoryId());
		Connection c = null;
		PreparedStatement ps = null;
		StringBuffer sql = null;
		try {
			sql = new StringBuffer("DELETE FROM BPM_ACTIVITYHISTORY WHERE ");
			int _dirtyAnd = 0;
			if (pObject.isActivityhistoryIdInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ACTIVITYHISTORY_ID").append("=?");
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
			if (pObject.isActivityinstIdInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ACTIVITYINST_ID").append("=?");
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
			if (pObject.isStarttimeInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("STARTTIME").append("=?");
				_dirtyAnd++;
			}
			if (pObject.isEndTimeInitialized()) {
				if (_dirtyAnd > 0)
					sql.append(" AND ");
				sql.append("ENDTIME").append("=?");
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
			getManager().log(sql.toString());
			c = getConnection();
			ps = c.prepareStatement(sql.toString(), 1004, 1007);
			int _dirtyCount = 0;
			if (pObject.isActivityhistoryIdInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getActivityHistoryId());
			}
			if (pObject.isProcessinstIdInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getProcessInstId());
			}
			if (pObject.isActivitydefIdInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getActivityDefId());
			}
			if (pObject.isActivityinstIdInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getActivityInstId());
			}
			if (pObject.isUrgencytypeInitialized()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getUrgency());
			}
			if (pObject.isArrivedtimeInitialized()) {
				_dirtyCount++;
				ps.setDouble(_dirtyCount, pObject.getArrivedTime().getTime());
			}
			if (pObject.isLimittimeInitialized()) {
				_dirtyCount++;
				ps.setDouble(_dirtyCount, pObject.getLimitTime().getTime());
			}
			if (pObject.isStarttimeInitialized()) {
				_dirtyCount++;
				ps.setDouble(_dirtyCount, pObject.getStartTime().getTime());
			}
			if (pObject.isEndTimeInitialized()) {
				_dirtyCount++;
				ps.setDouble(_dirtyCount, pObject.getEndTime().getTime());
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
			DbActivityInstHistory[] historys = loadByWhere(where, new int[1]);
			for (int i = 0; i < historys.length; i++) {
				removeFromCache(historys[i].getActivityHistoryId());
			}

			c = getConnection();
			String delByWhereSQL = "DELETE FROM BPM_ACTIVITYHISTORY " + where;
			getManager().log(delByWhereSQL);
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

	public EIActivityInstHistory[] save(EIActivityInstHistory[] pObjects)
			throws BPMException {
		for (int iIndex = 0; iIndex < pObjects.length; iIndex++) {
			save(pObjects[iIndex]);
		}
		return pObjects;
	}

	void beforeInsert(EIActivityInstHistory pObject) throws SQLException {
	}

	void afterInsert(EIActivityInstHistory pObject) throws SQLException {
	}

	void beforeUpdate(EIActivityInstHistory pObject) throws SQLException {
	}

	void afterUpdate(EIActivityInstHistory pObject) throws SQLException {
	}

	public EIActivityInstHistory save(EIActivityInstHistory pObject)
			throws BPMException {
		DbActivityInstHistory history = (DbActivityInstHistory) pObject;
		Connection c = null;
		PreparedStatement ps = null;
		StringBuffer _sql = null;
		try {
			c = getConnection();
			if (history.isNew()) {
				beforeInsert(history);
				int _dirtyCount = 0;
				_sql = new StringBuffer("INSERT into BPM_ACTIVITYHISTORY (");
				if (history.isActivityhistoryIdModified()) {
					_sql.append("ACTIVITYHISTORY_ID").append(",");
					_dirtyCount++;
				}
				if (history.isProcessinstIdModified()) {
					_sql.append("PROCESSINST_ID").append(",");
					_dirtyCount++;
				}
				if (history.isActivitydefIdModified()) {
					_sql.append("ACTIVITYDEF_ID").append(",");
					_dirtyCount++;
				}
				if (history.isActivityinstIdModified()) {
					_sql.append("ACTIVITYINST_ID").append(",");
					_dirtyCount++;
				}
				if (history.isUrgencytypeModified()) {
					_sql.append("URGENCYTYPE").append(",");
					_dirtyCount++;
				}
				if (history.isArrivedtimeModified()) {
					_sql.append("ARRIVEDTIME").append(",");
					_dirtyCount++;
				}
				if (history.isLimittimeModified()) {
					_sql.append("LIMITTIME").append(",");
					_dirtyCount++;
				}
				if (history.isStarttimeModified()) {
					_sql.append("STARTTIME").append(",");
					_dirtyCount++;
				}
				if (history.isEndTimeModified()) {
					_sql.append("ENDTIME").append(",");
					_dirtyCount++;
				}
				if (history.isRecievestateModified()) {
					_sql.append("RECEIVEMETHOD").append(",");
					_dirtyCount++;
				}
				if (history.isDealstateModified()) {
					_sql.append("DEALMETHOD").append(",");
					_dirtyCount++;
				}
				if (history.isRunstateModified()) {
					_sql.append("RUNSTATUS").append(",");
					_dirtyCount++;
				}
				_sql.setLength(_sql.length() - 1);
				_sql.append(") values (");
				for (int i = 0; i < _dirtyCount; i++)
					_sql.append("?,");
				_sql.setLength(_sql.length() - 1);
				_sql.append(")");

				getManager().log(_sql.toString());
				ps = c.prepareStatement(_sql.toString(), 1004, 1007);
				_dirtyCount = 0;
				if (history.isActivityhistoryIdModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, history.getActivityHistoryId());
				}
				if (history.isProcessinstIdModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, history.getProcessInstId());
				}
				if (history.isActivitydefIdModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, history.getActivityDefId());
				}
				if (history.isActivityinstIdModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, history.getActivityInstId());
				}
				if (history.isUrgencytypeModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, history.getUrgency());
				}
				if (history.isArrivedtimeModified()) {
					_dirtyCount++;
					ps.setDouble(_dirtyCount, history.getArrivedTime()
							.getTime());
				}
				if (history.isLimittimeModified()) {
					_dirtyCount++;
					ps.setDouble(_dirtyCount, history.getLimitTime().getTime());
				}
				if (history.isStarttimeModified()) {
					_dirtyCount++;
					ps.setDouble(_dirtyCount, history.getStartTime().getTime());
				}
				if (history.isEndTimeModified()) {
					_dirtyCount++;
					ps.setDouble(_dirtyCount, history.getEndTime().getTime());
				}
				if (history.isRecievestateModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, history.getReceiveMethod());
				}
				if (history.isDealstateModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, history.getDealMethod());
				}
				if (history.isRunstateModified()) {
					_dirtyCount++;
					ps.setString(_dirtyCount, history.getRunStatus());
				}
				ps.executeUpdate();

				history.setIsNew(false);
				history.resetIsModified();
				afterInsert(history);
			} else {
				beforeUpdate(history);
				_sql = new StringBuffer("UPDATE BPM_ACTIVITYHISTORY SET ");
				int _dirtyCount1 = 0;
				if (history.isActivityhistoryIdModified()) {
					_dirtyCount1++;
					_sql.append("ACTIVITYHISTORY_ID").append("=?,");
				}

				if (history.isProcessinstIdModified()) {
					_dirtyCount1++;
					_sql.append("PROCESSINST_ID").append("=?,");
				}

				if (history.isActivitydefIdModified()) {
					_dirtyCount1++;
					_sql.append("ACTIVITYDEF_ID").append("=?,");
				}

				if (history.isActivityinstIdModified()) {
					_dirtyCount1++;
					_sql.append("ACTIVITYINST_ID").append("=?,");
				}

				if (history.isUrgencytypeModified()) {
					_dirtyCount1++;
					_sql.append("URGENCYTYPE").append("=?,");
				}

				if (history.isArrivedtimeModified()) {
					_dirtyCount1++;
					_sql.append("ARRIVEDTIME").append("=?,");
				}

				if (history.isLimittimeModified()) {
					_dirtyCount1++;
					_sql.append("LIMITTIME").append("=?,");
				}

				if (history.isStarttimeModified()) {
					_dirtyCount1++;
					_sql.append("STARTTIME").append("=?,");
				}

				if (history.isEndTimeModified()) {
					_dirtyCount1++;
					_sql.append("ENDTIME").append("=?,");
				}

				if (history.isRecievestateModified()) {
					_dirtyCount1++;
					_sql.append("RECEIVEMETHOD").append("=?,");
				}

				if (history.isDealstateModified()) {
					_dirtyCount1++;
					_sql.append("DEALMETHOD").append("=?,");
				}

				if (history.isRunstateModified()) {
					_dirtyCount1++;
					_sql.append("RUNSTATUS").append("=?,");
				}

				_sql.setLength(_sql.length() - 1);
				_sql.append(" WHERE ");
				_sql.append("BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID=?");
				getManager().log(_sql.toString());
				System.out.println(_sql.toString());
				if (_dirtyCount1 > 0) {

					ps = c.prepareStatement(_sql.toString(), 1004, 1007);
				}
				int _dirtyCount = 0;
				if (history.isActivityhistoryIdModified())
					if (history.getActivityHistoryId() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, history
								.getActivityHistoryId());
					}
				if (history.isProcessinstIdModified())
					if (history.getProcessInstId() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, history.getProcessInstId());
					}
				if (history.isActivitydefIdModified())
					if (history.getActivityDefId() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, history.getActivityDefId());
					}
				if (history.isActivityinstIdModified())
					if (history.getActivityInstId() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;

						ps.setString(_dirtyCount, history.getActivityInstId());
					}
				if (history.isUrgencytypeModified())
					if (history.getUrgency() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, history.getUrgency());
					}
				if (history.isArrivedtimeModified())
					if (history.getArrivedTime() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 3);
					} else {
						_dirtyCount++;
						ps.setDouble(_dirtyCount, history.getArrivedTime()
								.getTime());
					}
				if (history.isLimittimeModified())
					if (history.getLimitTime() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 3);
					} else {
						_dirtyCount++;
						ps.setDouble(_dirtyCount, history.getLimitTime()
								.getTime());
					}
				if (history.isStarttimeModified())
					if (history.getStartTime() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 3);
					} else {
						_dirtyCount++;
						ps.setDouble(_dirtyCount, history.getStartTime()
								.getTime());
					}
				if (history.isEndTimeModified())
					if (history.getEndTime() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 3);
					} else {
						_dirtyCount++;
						ps.setDouble(_dirtyCount, history.getEndTime()
								.getTime());
					}
				if (history.isRecievestateModified())
					if (history.getReceiveMethod() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, history.getReceiveMethod());
					}
				if (history.isDealstateModified())
					if (history.getDealMethod() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, history.getDealMethod());
					}
				if (history.isRunstateModified())
					if (history.getRunStatus() == null) {
						_dirtyCount++;
						ps.setNull(_dirtyCount, 12);
					} else {
						_dirtyCount++;
						ps.setString(_dirtyCount, history.getRunStatus());
					}
				if (_dirtyCount == 0) {
					DbActivityInstHistory localDbActivityInstHistory1 = history;
					return localDbActivityInstHistory1;
				}
				_dirtyCount++;
				ps.setString(_dirtyCount, history.getActivityHistoryId());
				ps.executeUpdate();
				history.resetIsModified();

				afterUpdate(history);
			}

			saveAttribute(history);
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

	private EIActivityInstHistory decodeRow(ResultSet rs) throws SQLException {
		DbActivityInstHistory pObject = (DbActivityInstHistory) createActivityHistory();
		if (rs.getObject(1) != null)
			pObject.setActivityHistoryId(rs.getString(1));
		if (rs.getObject(2) != null)
			pObject.setProcessInstId(rs.getString(2));
		if (rs.getObject(3) != null)
			pObject.setActivityDefId(rs.getString(3));
		if (rs.getObject(4) != null)
			pObject.setActivityInstId(rs.getString(4));
		if (rs.getObject(5) != null)
			pObject.setUrgency(rs.getString(5));
		if (rs.getObject(6) != null)
			pObject.setArrivedTime(new Date(rs.getLong(6)));
		if (rs.getObject(7) != null)
			pObject.setLimitTime(new Date(rs.getLong(7)));
		if (rs.getObject(8) != null)
			pObject.setStartTime(new Date(rs.getLong(8)));
		if (rs.getObject(9) != null)
			pObject.setEndTime(new Date(rs.getLong(9)));
		if (rs.getObject(10) != null)
			pObject.setReceiveMethod(rs.getString(10));
		if (rs.getObject(11) != null)
			pObject.setDealMethod(rs.getString(11));
		if (rs.getObject(12) != null)
			pObject.setRunStatus(rs.getString(12));
		pObject.setIsNew(false);
		pObject.resetIsModified();

		return pObject;
	}

	private EIActivityInstHistory decodeRow(ResultSet rs, int[] fieldList)
			throws SQLException {
		DbActivityInstHistory pObject = (DbActivityInstHistory) createActivityHistory();
		int pos = 0;
		for (int i = 0; i < fieldList.length; i++) {
			switch (fieldList[i]) {
			case 0:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setActivityHistoryId(rs.getString(pos));

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
				pObject.setActivityInstId(rs.getString(pos));

				break;
			case 4:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setUrgency(rs.getString(pos));

				break;
			case 5:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setArrivedTime(new Date(rs.getLong(pos)));

				break;
			case 6:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setLimitTime(new Date(rs.getLong(pos)));

				break;
			case 7:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setStartTime(new Date(rs.getLong(pos)));

				break;
			case 8:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setEndTime(new Date(rs.getLong(pos)));

				break;
			case 9:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setReceiveMethod(rs.getString(pos));

				break;
			case 10:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setDealMethod(rs.getString(pos));

				break;
			case 11:
				pos++;
				if (rs.getObject(pos) == null)
					continue;
				pObject.setRunStatus(rs.getString(pos));
			}

		}

		pObject.setIsNew(false);
		pObject.resetIsModified();

		return pObject;
	}

	protected EIActivityInstHistory loadObject(EIActivityInstHistory object)
			throws BPMException {
		DbActivityInstHistory pObject = (DbActivityInstHistory) object;
		EIActivityInstHistory[] pReturn = loadObjects(pObject);
		if (pReturn.length == 0)
			return null;
		if (pReturn.length > 1)
			throw new BPMException("More than one element !!");
		return pReturn[0];
	}

	protected EIActivityInstHistory[] loadObjects(EIActivityInstHistory object)
			throws BPMException {
		DbActivityInstHistory pObject = (DbActivityInstHistory) object;
		Connection c = null;
		PreparedStatement ps = null;
		@SuppressWarnings("unused")
		StringBuffer where = new StringBuffer("");
		StringBuffer _sql = new StringBuffer(
				"SELECT BPM_ACTIVITYHISTORY.ACTIVITYHISTORY_ID,BPM_ACTIVITYHISTORY.PROCESSINST_ID,BPM_ACTIVITYHISTORY.ACTIVITYDEF_ID,BPM_ACTIVITYHISTORY.ACTIVITYINST_ID,BPM_ACTIVITYHISTORY.URGENCYTYPE,BPM_ACTIVITYHISTORY.ARRIVEDTIME,BPM_ACTIVITYHISTORY.LIMITTIME,BPM_ACTIVITYHISTORY.STARTTIME,BPM_ACTIVITYHISTORY.ENDTIME,BPM_ACTIVITYHISTORY.RECEIVEMETHOD,BPM_ACTIVITYHISTORY.DEALMETHOD,BPM_ACTIVITYHISTORY.RUNSTATUS from BPM_ACTIVITYHISTORY WHERE ");

		StringBuffer _sqlWhere = new StringBuffer("");
		try {
			int _dirtyCount = 0;
			if (pObject.isActivityhistoryIdModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("ACTIVITYHISTORY_ID= ?");
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
			if (pObject.isActivityinstIdModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("ACTIVITYINST_ID= ?");
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
			if (pObject.isStarttimeModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("STARTTIME= ?");
			}
			if (pObject.isEndTimeModified()) {
				_dirtyCount++;
				_sqlWhere.append(_sqlWhere.length() == 0 ? " " : " AND ")
						.append("ENDTIME= ?");
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
			if (_dirtyCount == 0)
				throw new SQLException(
						"The pObject to look for is invalid : not initialized !");
			_sql.append(_sqlWhere);
			getManager().log(_sql.toString());
			c = getConnection();
			ps = c.prepareStatement(_sql.toString(), 1004, 1007);
			_dirtyCount = 0;
			if (pObject.isActivityhistoryIdModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getActivityHistoryId());
			}
			if (pObject.isProcessinstIdModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getProcessInstId());
			}
			if (pObject.isActivitydefIdModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getActivityDefId());
			}
			if (pObject.isActivityinstIdModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getActivityInstId());
			}
			if (pObject.isUrgencytypeModified()) {
				_dirtyCount++;
				ps.setString(_dirtyCount, pObject.getUrgency());
			}
			if (pObject.isArrivedtimeModified()) {
				_dirtyCount++;
				ps.setDouble(_dirtyCount, pObject.getArrivedTime().getTime());
			}
			if (pObject.isLimittimeModified()) {
				_dirtyCount++;
				ps.setDouble(_dirtyCount, pObject.getLimitTime().getTime());
			}
			if (pObject.isStarttimeModified()) {
				_dirtyCount++;
				ps.setDouble(_dirtyCount, pObject.getStartTime().getTime());
			}
			if (pObject.isEndTimeModified()) {
				_dirtyCount++;
				ps.setDouble(_dirtyCount, pObject.getEndTime().getTime());
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
			ps.executeQuery();
			DbActivityInstHistory[] arrayOfDbActivityInstHistory = loadByPreparedStatement(ps);
			return arrayOfDbActivityInstHistory;
		} catch (SQLException e) {
			throw new BPMException(e);
		} finally {
			getManager().close(ps);
			freeConnection(c);
		}
	}

	@SuppressWarnings("unchecked")
	private void putToCache(String uuid, DbActivityInstHistory dbActivityInst) {
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

	@SuppressWarnings({ "unused", "unchecked" })
	private void removeFromCache(List uuids) {
		for (int i = 0; i < uuids.size(); i++) {
			Object obj = uuids.get(i);
			if ((obj instanceof String)) {
				String uuid = (String) obj;
				removeFromCache(uuid);
			}
		}
	}

	@SuppressWarnings("unchecked")
	void prepareCache(List ids) {
		StringBuffer sqlWhere = new StringBuffer(
				" WHERE ACTIVITYHISTORY_ID IN (");
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
			EIActivityInstHistory[] list = (EIActivityInstHistory[]) null;
			try {
				list = loadByWhere(sqlWhere.toString(), null);
			} catch (BPMException e) {
				log
						.error("prefetch the activity instance histoty fail! Where Sql: "
								+ sqlWhere.toString());
				log.error("SqlException is ", e);
				return;
			}
			for (int i = 0; i < list.length; i++) {
				DbActivityInstHistory pd = (DbActivityInstHistory) list[i];
				putToCache(pd.getActivityHistoryId(), pd);
			}
		}
	}
}