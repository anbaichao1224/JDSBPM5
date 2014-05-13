/**
 * $RCSfile: DbManager.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/08/03 09:39:09 $
 *
 * Copyright (C) 2003 itjds, Inc. All rights reserved.
 *
 * This software is the proprietary information of itjds, Inc.
 * Use is subject to license terms.
 */
package net.itjds.usm2.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import net.itjds.common.database.ConnectionManagerFactory;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.usm2.constants.USMConstants;


/**
 * The Manager provides connections and manage transaction transparently. The
 * Manager is a singleton, you get its instance with the getInstance() method.
 * It is used by all the XxxxManager to get database connection. Before doing
 * any operation, you must pass to the manager either a datasource or a jdbc
 * driver/url/username/password You may extend it and use setInstance() method
 * to make sure your implementation is used as a singleton.
 */
public class DbManager {
	private static Log log = LogFactory.getLog(USMConstants.CONFIG_KEY,
			DbManager.class);

	private static DbManager manager_instance = new DbManager();

	private static InheritableThreadLocal trans_conn = new InheritableThreadLocal();

	/**
	 * Return the manager singleton instance.
	 */
	public static DbManager getInstance() {
		return manager_instance;
	}

	/**
	 * Get an auto commit connection. Normally you you not need this method that
	 * much ;-)
	 * 
	 * @return an auto commit connection.
	 */
	public synchronized Connection getConnection() throws SQLException {
		Connection tc = (Connection) trans_conn.get();
		if (tc != null) {
			return tc;
		}
		return ConnectionManagerFactory.getConnection(USMConstants.CONNECTION_CONFIG_KEY);
		/*
		 * if (ds!=null) { return ds.getConnection(); } else if (jdbc_driver !=
		 * null && jdbc_url != null && jdbc_username != null && jdbc_password !=
		 * null) { return DriverManager.getConnection(jdbc_url, jdbc_username,
		 * jdbc_password); } else { throw new IllegalStateException("Please set
		 * a datasource or a jdbc driver/url/username/password"); }
		 */}

	/**
	 * Release the connection. Normally you should not need this method ;-)
	 */
	public synchronized void releaseConnection(Connection c) {
		Connection tc = (Connection) trans_conn.get();
		if (tc != null) {
			return;
		}

		try {
			if (c != null) {
				c.setAutoCommit(true);
				c.close();
			}
		} catch (SQLException x) {
			log.error("Could not release the connection: ", x);
		}
	}

	/**
	 * When working within a transaction, you should invoke this method first.
	 * The connection is returned just in case you need to set the isolation
	 * level or else.
	 * 
	 * @return a non-auto commit connection with the default transaction
	 *         isolation level.
	 */
	public Connection beginTransaction() throws SQLException {
		Connection c = getConnection();
		c.setAutoCommit(false);
		trans_conn.set(c);
		return c;
	}

	/**
	 * Release connection used for the transaction and perform a commit or
	 * rollback.
	 * 
	 * @param commit
	 *            tells whether this connection should be committed: true for
	 *            commit(), false for rollback()
	 */
	public void endTransaction(boolean commit) throws SQLException {
		Connection c = (Connection) trans_conn.get();
		if (c == null) {
			return;
		}

		try {
			if (commit) {
				c.commit();
			} else {
				c.rollback();
			}
		} finally {
			trans_conn.set(null);
			releaseConnection(c);
		}
	}

	// //////////////////////////////////////////////////
	// Utils method
	// //////////////////////////////////////////////////

	/**
	 * Log a message using the underlying logwriter, if not null.
	 */
	public void log(String message) {
		log.trace(message);
	}

	/**
	 * Close the passed Statement.
	 */
	public void close(Statement s) {
		try {
			if (s != null)
				s.close();
		} catch (SQLException x) {
			log.error("Could not close statement!: ", x);
		}
		;
	}

	/**
	 * Close the passed ResultSet.
	 */
	public void close(ResultSet rs) {
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException x) {
			log.error("Could not close result set!: ", x);
		}
		;
	}

	/**
	 * Close the passed Statement and ResultSet.
	 */
	public void close(Statement s, ResultSet rs) {
		close(rs);
		close(s);
	}

}
