package com.kzxd.index.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;

import com.kzxd.index.dao.recorddao;
import com.kzxd.index.entity.RecordData;
import com.kzxd.index.entity.recordachment;



public class recorddaoimpl implements recorddao{
    
	public List<RecordData> find(String personId,int ispass,Date endtime) {
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<RecordData> data = new ArrayList<RecordData>();
		RecordData ta = new RecordData();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, ta);
			ta.setWhereClause("rollid in(select rollid from fdt_oa_rollpepodom where applicantid='"+personId+"' and ispass=2 and endtime>to_date('"+formatDate(new Date())+"','yyyy-mm-dd'))");
			data = factory.find();
			for(int i=0;i<data.size();i++){
				RecordData rd = (RecordData)data.get(i);
				String dataID = rd.getUuid();
				List<recordachment> list = findbyrollid(dataID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  return data;
	}

	public String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(date!=null){
			return sdf.format(date);
		}else{
			return null;
		}
		
	}

	public List<recordachment> findbyrollid(String dataId) {
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<recordachment> datalist = new ArrayList<recordachment>();
		recordachment ta = new recordachment();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, ta);
			ta.setWhereClause(" recorduuid='"+dataId+"'");
			datalist = factory.find();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  return datalist;
	}
	
}	
	


	
	


