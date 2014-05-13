package com.kzxd.newkaoqin.dao.impl;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;

import com.kzxd.newkaoqin.dao.QianJiaTiaoDao;
import com.kzxd.newkaoqin.entity.QingJiaTiaoDAO;
import com.kzxd.newkaoqin.entity.QingJiaTiaobean;

public class QianJiaTiaoDaoImpl implements QianJiaTiaoDao{

	public QingJiaTiaobean getById(String id) {
		QingJiaTiaobean qjtbean=null;
		List<QingJiaTiaoDAO> bdList = null;
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		QingJiaTiaoDAO qjtdao = new QingJiaTiaoDAO();
		SimpleDateFormat dfg = new SimpleDateFormat("yyyy-MM-dd");
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, qjtdao);
			 qjtdao.setActivityinstId(id);
			//qjtdao.setWhereClause(" activityinstId = '" + id+"' ");
			bdList =factory.find();
			qjtbean = new QingJiaTiaobean();
			qjtbean.setActivityinstId(bdList.get(0).getActivityinstId());
			qjtbean.setXm(bdList.get(0).getXm());
			qjtbean.setCkfzryj(bdList.get(0).getCkfzryj());
			qjtbean.setZxldyj(bdList.get(0).getZxldyj());
			qjtbean.setCkmc(bdList.get(0).getCkmc());
			qjtbean.setGjt(bdList.get(0).getGjt());
			qjtbean.setProcessinstId(bdList.get(0).getProcessinstId());
			qjtbean.setXtdbkfzryj(bdList.get(0).getXtdbkfzryj());
			qjtbean.setQjsjks(bdList.get(0).getQjsjks());
			qjtbean.setQjlx(bdList.get(0).getQjlx());
			qjtbean.setQjsjjs(bdList.get(0).getQjsjjs());
			qjtbean.setSqrq(bdList.get(0).getSqrq());
			qjtbean.setSy(bdList.get(0).getSy());
			qjtbean.setUuid(bdList.get(0).getUuid());
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return qjtbean;
		
	}
	
}


