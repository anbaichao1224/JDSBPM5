package com.kzxd.Baosong.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;

import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kzxd.Baosong.dao.XxkPingfengGuizeDao;
import com.kzxd.Baosong.dao.XxktjDao;
import com.kzxd.Baosong.listBean.BmFenshuGzListBean;
public class GzAndBd {
	
	private String uuid;
	
	/**
	 * 信息上报时，生成中间表FTD_OA_XXKTJ
	 * @param uuid
	 * @param date
	 * @return 成功返回true，失败返回false
	 */
	public boolean createGzAndBd(String uuid){
		
		GzMsg gzmsg = new GzMsg();
		
		List<String> uuidList = gzmsg.getAllBiaohao();
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		
		XxktjDao tjDao = new XxktjDao();
		
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, tjDao);
			
			tjDao.setUuid_bd(uuid);
			
			for(String uuid_gz:uuidList){
				uuid = (new UUID()).toString();
				tjDao.setUuid(uuid);
				tjDao.setUuid_gz(uuid_gz);
				tjDao.setConnection(conn);
				tjDao.create();
			}
			
			conn.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return true;
	}
	
	/**
	 * 多表联合查询，得到部门，规则biaohao，上报的uuid
	 */
	public List<BmFenshuGzListBean> getBtAndTj(){
		DBBeanBase dbbase=new DBBeanBase("org",true);
		Connection conn=dbbase.getConn();
		Statement db = null;
		ResultSet rst = null;
		
		List<BmFenshuGzListBean> bList = new ArrayList<BmFenshuGzListBean>();
		try {
			db=conn.createStatement();
//			ResultSet rst=db.executeQuery("select sum(fenshu) as pingfen,dw from fdt_oa_xxkpfgz g,fdt_oa_xxkbsdwbd h,fdt_oa_xxkbstj t where h.uuid = t.uuid_bd and t.uuid_gz=g.biaohao and h.sbsj > g.kaishishijian" +
//					                      "and h.sbsj <jieshushijian group by dw");
			rst=db.executeQuery("select fenshu ,dw,biaohao from fdt_oa_xxkpfgz g,fdt_oa_xxkbsdwbd h,fdt_oa_xxkbstj t where h.uuid = t.uuid_bd and t.uuid_gz=g.biaohao and h.sbsj >= g.kaishishijian" +
            " and t.yingyong = 1  and h.sbsj <jieshushijian order by dw,biaohao");
			while(rst.next()){
				BmFenshuGzListBean bListBean = new BmFenshuGzListBean();
				int  fenshu =rst.getInt("fenshu");
				String uuid = rst.getString("biaohao");
				String dw = rst.getString("dw");
				
				bListBean.setDw(dw);
				bListBean.setGzuuid(uuid);
				bListBean.setFenshu(fenshu);
				bList.add(bListBean);
				
			}
			rst.close();
			db.close();
			conn.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try{
				rst.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			try{
				db.close();
				}catch (Exception e) {
					e.printStackTrace();
			}
				
			try{
				conn.close();
				}catch (Exception e) {
					e.printStackTrace();
			}
				
				try {
					dbbase.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		return bList;
	}
	
	/**
	 * 对查询的计算的数据二次处理
	 * @param bList
	 * @return
	 */
	public List<BmFenshuGzListBean> tj(List<BmFenshuGzListBean> bList){
		List<BmFenshuGzListBean> bList1 = new ArrayList<BmFenshuGzListBean>();
		for(int i = 0 ; i < bList.size() ; i++){
			BmFenshuGzListBean b = new BmFenshuGzListBean();
			b = bList.get(i);
			if(bList1.size() == 0){
				b.setGzs(1);
				bList1.add(b);
			}else{
				int j = 0;
				for ( j = 0 ;j < bList1.size() ; j++){
					BmFenshuGzListBean b1 = new BmFenshuGzListBean();
					b1 = bList1.get(j);
					if(b1.getGzuuid().equals(b.getGzuuid())&& b1.getDw().equals( b.getDw())){
						bList1.get(j).setGzs(b1.getGzs()+1);
						bList1.get(j).setFenshu(b1.getFenshu()+b.getFenshu());
						break;
					}
				}
				if( j== bList1.size()){
					b.setGzs(1);
					bList1.add(b);
				}
			}
			
		}
		
		return bList1;
	}
	
	/**
	 * 根据二次处理的数据得到json串
	 * @return
	 */
	public String getJsonStr(List<BmFenshuGzListBean> bList){
		String jsonStr = "";
		List<String> biaohaoList = this.getAllBiaohaoList();
		Map<String, String> dwMap = new HashMap<String, String>();
		Map<String, Integer> dwFsMap = new HashMap<String, Integer>();
		for(BmFenshuGzListBean b : bList){
			if(dwMap.containsKey(b.getDw())){
				String str = dwMap.get(b.getDw());
				str = str + "," + b.getGzuuid()+":'"+b.getGzs()+"'";
				dwMap.put(b.getDw(), str);
				Integer fenshu = dwFsMap.get(b.getDw());
				dwFsMap.put(b.getDw(), fenshu +b.getFenshu());
			}else{
				String str = "dw:'"+b.getDw()+"',"+ b.getGzuuid()+":'"+b.getGzs()+"'";
				dwMap.put(b.getDw(), str);
				dwFsMap.put(b.getDw(), b.getFenshu());
			}
			
		}
		
		for(String key:dwMap.keySet()){
			for(String bh:biaohaoList){
				if(true){
					if(dwMap.get(key).indexOf(","+bh+":")==-1){
						dwMap.put(key, dwMap.get(key)+","+bh+":'0'");
					}
				}
			}
		}
		
		int totalCount = dwMap.size();
		jsonStr = "{totalCount:"+totalCount+",root:[";
		for(String key:dwMap.keySet()){
			jsonStr = jsonStr+"{"+dwMap.get(key)+",fenshu:"+dwFsMap.get(key)+"},";
		}
		jsonStr = jsonStr.substring(0, jsonStr.length()-1);
		jsonStr = jsonStr + "]}";
		return jsonStr;
		
	}
	
	public int geAllNewGz(String biaohao,Date date){
		List gzList = new ArrayList();
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XxkPingfengGuizeDao gzdao = new XxkPingfengGuizeDao();
		List<XxkPingfengGuizeDao> xDaoT = new ArrayList<XxkPingfengGuizeDao>();
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, gzdao);
			gzdao.setWhereClause("biaohao = '"+biaohao +"' and kaishishijian <" + date +" and jieshushijian > "+ date +"and shifoukeyong = 'Y'");
//			gzList = factory.findAll();
			gzList = factory.find();
			
			if (gzList != null && gzList.size() > 0) {
				//totalCount = gzList.size();
				//getAllNewGz();
				//addFileToBeanList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		for (int j = 0; j<gzList.size();j++) {
			XxkPingfengGuizeDao daoT = (XxkPingfengGuizeDao)gzList.get(j);
			if(xDaoT.size()==0){
				xDaoT.add(daoT);
			}
			for (int i = 0; i < xDaoT.size(); i++) {
				XxkPingfengGuizeDao mubiao = xDaoT.get(i);
				if (daoT.getBiaohao().equals(mubiao.getBiaohao())) {
					if (daoT.getBanbenhao() > mubiao.getBanbenhao()) {
						xDaoT.set(i, daoT);
					}
				} else {
					xDaoT.add(daoT);
				}
			}
		}

		return 0;
	}
	
	/**
	 * 10-12新增项，用于填充评分是空的现象
	 */
	/**
 	 * 得到所有最新的规则
 	 */
	public void getAllNewGz() {
		
		for (int j = 1; j < gzList.size(); j++) {
			if (xDaoT.size() == 0 && gzList.size() > 0) {
				xDaoT.add((XxkPingfengGuizeDao) gzList.get(0));
			}
			int i = 0;
			XxkPingfengGuizeDao daoT = (XxkPingfengGuizeDao) gzList.get(j);
			for ( i = 0; i < xDaoT.size(); i++) {

				
				XxkPingfengGuizeDao mubiao = xDaoT.get(i);
				if (daoT.getBiaohao().trim().equals(mubiao.getBiaohao().trim())) {
					if (daoT.getBanbenhao() > mubiao.getBanbenhao()) {
						xDaoT.set(i, daoT);
					}
					break;
				} 
			}
			if( i == xDaoT.size() ){
				xDaoT.add(daoT);
			}
		}



	}
	List gzList;
	public List<String> getAllBiaohaoList() {

		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		XxkPingfengGuizeDao gzdao = new XxkPingfengGuizeDao();
		
		List<String> biaohaoList = new ArrayList<String>();
		
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, gzdao);
			gzList = factory.findAll();
			
			
			if (gzList != null && gzList.size() > 0) {
				
				getAllNewGz();
				for (int i = 0; i < xDaoT.size(); i++) {
					biaohaoList.add(xDaoT.get(i).getBiaohao());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{

			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return biaohaoList;
	}

 	private	List<XxkPingfengGuizeDao> xDaoT = new ArrayList<XxkPingfengGuizeDao>();

	
}
