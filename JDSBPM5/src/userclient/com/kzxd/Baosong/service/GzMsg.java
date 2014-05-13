package com.kzxd.Baosong.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;

import com.kzxd.Baosong.dao.XxkPingfengGuizeDao;


public class GzMsg {
	
	
	
	List gzList;
	
	public List<String> getAllBiaohao() {

		List<String> uuidLists = new ArrayList<String>();
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XxkPingfengGuizeDao gzdao = new XxkPingfengGuizeDao();
		try {
			
			conn = dbbase.getConn();
//			gzdao.setWhereClause(" YINGYONG = 'Y' ");
			factory = new DAOFactory(conn, gzdao);
			
//			gzList = factory.find();
			
			gzList = factory.findAll();
			uuidLists = getGzBiaohao();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return uuidLists;

	}

	
	
	/**
	 * 寻找报送时间在规则内时间的规则列表
	 * @param currDate
	 * @return
	 */
	public List<XxkPingfengGuizeDao> inTime(Date currDate){
		
		List<XxkPingfengGuizeDao> intimeList = new ArrayList<XxkPingfengGuizeDao>();
		
		for(int i = 0 ; i < gzList.size() ; i++){
			XxkPingfengGuizeDao daot = (XxkPingfengGuizeDao)gzList.get(i);
			if(currDate.equals(daot.getKaishishijian())||currDate.before(daot.getJieshushijian())){
				intimeList.add(daot);
			}
		}
			
		return intimeList;
	}
	
	/**
	 * 得到当前时间内可用的规则的uuid
	 * @param xxkdaoT
	 * @return
	 */
	public List<String> getGzUuid(List<XxkPingfengGuizeDao> xxkdaoT){
		
		List<String> uuidList = new ArrayList<String>();
		List<XxkPingfengGuizeDao> xDaoT = new ArrayList<XxkPingfengGuizeDao>();
		
		for(XxkPingfengGuizeDao daoT:xxkdaoT){
			if(xDaoT.size()==0){
				xDaoT.add(daoT);
			}
			for(int i = 0; i < xDaoT.size() ; i++){
				XxkPingfengGuizeDao mubiao = xDaoT.get(i);
				if(daoT.getBiaohao().equals(mubiao.getBiaohao())){
					if(daoT.getBanbenhao()>mubiao.getBanbenhao()){
						xDaoT.set(i, daoT);
					}
				}else{
					xDaoT.add(daoT);
				}
			}
		}
		
		for(XxkPingfengGuizeDao daoT:xDaoT){
			uuidList.add(daoT.getUuid());
		}
		
		return uuidList;
	}
	
	/**
	 * 得到所有标号
	 * @return
	 */
	public List<String> getGzBiaohao(){
		List<String> biaohaoList = new ArrayList<String>();
		XxkPingfengGuizeDao daoT = new XxkPingfengGuizeDao();
		String biaohao = "";
		if(gzList!=null ){
			for(int i = 0 ; i < gzList.size() ; i ++){
				daoT = (XxkPingfengGuizeDao)gzList.get(i);
				biaohao = daoT.getBiaohao();
				if(biaohaoList.size() == 0){
					biaohaoList.add(biaohao);
				}
				int j = 0;
				for( j = 0; j<biaohaoList.size(); j++){
					if(biaohaoList.contains(biaohao)){
						break;
					}
				}
				if(j>=biaohaoList.size()){
					biaohaoList.add(biaohao);
				}
			}
		}
		
		return biaohaoList;
	}
	
	public static Date String2Date(String dateStr) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// String date = "2010-10-1 12:22:30";
		Date d = null;
		try {
			d = format.parse(dateStr);
		} catch (Exception e) {

		}

		return d;
	}
}
