package kzxd.documentmodel.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kzxd.documentmodel.dao.KzxdDocumentDao;
import kzxd.documentmodel.entity.KZXDDocumentModel;
import kzxd.documentmodel.util.KzxdJdbcUtil;

public class KzxdDocumentDaoImpl implements KzxdDocumentDao {

	// 通过部门名称去找模版的方法
	public List<KZXDDocumentModel> findByDept(String[] orgs) {
		List<KZXDDocumentModel> mlist = new ArrayList<KZXDDocumentModel>();
		Connection con = KzxdJdbcUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String mysql = "";
		String otemp = "";
		if (orgs.length > 1) {
			for (int i = 0; i < orgs.length; i++) {
				otemp = otemp + "'" + orgs[i] + "',";
				if (i == orgs.length - 1) {
					otemp = otemp + "'" + orgs[i] + "'";
				}
			}
			mysql = "SELECT * FROM KZXD_DOCUMENTMODEL where MODEL_UPLOADDEPT in ("
					+ otemp + ",'all') ORDER BY MODEL_ORDER";
		} else {
			mysql = "SELECT * FROM KZXD_DOCUMENTMODEL where MODEL_UPLOADDEPT in ('" + orgs[0]
			+ "','all') ORDER BY MODEL_ORDER";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(mysql);
			while (rs.next()) {
				KZXDDocumentModel model = new KZXDDocumentModel();
				model.setUUID(rs.getString("UUID"));
				model.setModelName(rs.getString("MODEL_NAME"));
				model.setModelPath(rs.getString("MODEL_PATH"));
				model.setModelType(rs.getString("MODEL_TYPE"));
				model.setModelUploadDept(rs.getString("MODEL_UPLOADDEPT"));
				mlist.add(model);
			}
		} catch (SQLException e) {
		} finally {
			KzxdJdbcUtil.close(con, stmt, rs);
		}
		return mlist;
	}
	
	public List<KZXDDocumentModel> findByDeptnull(String[] orgs) {
		List<KZXDDocumentModel> mlist = new ArrayList<KZXDDocumentModel>();
		Connection con = KzxdJdbcUtil.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		String mysql = "";
		String otemp = "";
		if (orgs.length > 1) {
			for (int i = 0; i < orgs.length; i++) {
				otemp = otemp + "'" + orgs[i] + "',";
				if (i == orgs.length - 1) {
					otemp = otemp + "'" + orgs[i] + "'";
				}
			}
			mysql = "SELECT * FROM KZXD_DOCUMENTMODEL where MODEL_UPLOADDEPT in ("
					+ otemp + ") ORDER BY MODEL_ORDER";
		} else {
			mysql = "SELECT * FROM KZXD_DOCUMENTMODEL where MODEL_UPLOADDEPT = '"
					+ orgs[0] + "' ORDER BY MODEL_ORDER";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(mysql);
			while (rs.next()) {
				KZXDDocumentModel model = new KZXDDocumentModel();
				model.setUUID(rs.getString("UUID"));
				model.setModelName(rs.getString("MODEL_NAME"));
				model.setModelPath(rs.getString("MODEL_PATH"));
				model.setModelType(rs.getString("MODEL_TYPE"));
				model.setModelUploadDept(rs.getString("MODEL_UPLOADDEPT"));
				mlist.add(model);
			}
		} catch (SQLException e) {
		} finally {
			KzxdJdbcUtil.close(con, stmt, rs);
		}
		return mlist;
	}

	public void save(List<KZXDDocumentModel> beans) {
		Connection con = KzxdJdbcUtil.getConnection();
		PreparedStatement ps = null;
		String savesql = "insert into KZXD_DOCUMENTMODEL values (?,?,?,?,?,?)";
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(savesql);
			for (KZXDDocumentModel bean : beans) {
				ps.setString(1, bean.getUUID());
				ps.setString(2, bean.getModelName());
				ps.setString(3, bean.getModelPath());
				ps.setString(4, bean.getModelType());
				ps.setString(5, bean.getModelUploadDept());
				ps.setInt(6, 1);
			}
			ps.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			KzxdJdbcUtil.close(con, ps);
		}
	}

	public KZXDDocumentModel findByUUID(String uuid) {
		Connection con = KzxdJdbcUtil.getConnection();
		KZXDDocumentModel model = new KZXDDocumentModel();
		Statement stmt = null;
		ResultSet rs = null;
		String mysql = "SELECT * FROM KZXD_DOCUMENTMODEL where UUID = '" + uuid
				+ "'";
		try {

			stmt = con.createStatement();
			rs = stmt.executeQuery(mysql);
			while (rs.next()) {
				model.setUUID(rs.getString("UUID"));
				model.setModelName(rs.getString("MODEL_NAME"));
				model.setModelPath(rs.getString("MODEL_PATH"));
				model.setModelType(rs.getString("MODEL_TYPE"));
				model.setModelUploadDept(rs.getString("MODEL_UPLOADDEPT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			KzxdJdbcUtil.close(con, stmt, rs);
		}
		return model;
	}

	
	public void deletebyid(String uuid) {
		
		Connection con = KzxdJdbcUtil.getConnection();
		PreparedStatement ps = null;
		String deletesql = "delete from kzxd_documentmodel where uuid='"+ uuid+"'";
		try {
			con.setAutoCommit(false);
			ps = con.prepareStatement(deletesql);
			ps.executeUpdate();		
			//ps.execute();
			con.commit();
		} catch (SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			KzxdJdbcUtil.close(con, ps);
		}
		
	}

}
