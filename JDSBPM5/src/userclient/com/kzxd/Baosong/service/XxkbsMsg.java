package com.kzxd.Baosong.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;

import com.kzxd.Baosong.dao.Xxkbsbdbh;
import com.kzxd.Baosong.dao.Xxkbsdwbd;
import com.kzxd.Baosong.dao.XxktjDao;
import com.kzxd.Baosong.listBean.XxkPFGZListBean;

public class XxkbsMsg {
	
	String uuid;
	
	public void save(Xxkbsdwbd bdDao){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		
		Xxkbsbdbh bhDao = new Xxkbsbdbh();
		uuid = (new UUID()).toString();
//		Xxkbsdwbd bdDao = new Xxkbsdwbd();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bhDao);
			
			bhDao.setBt(bdDao.getBt());
			bhDao.setUuid(bdDao.getUuid());
			bhDao.setDjr(bdDao.getDjr());
			bhDao.setDw(bdDao.getDw());
			bhDao.setSbsj(bdDao.getSbsj());
			bhDao.setZt(bdDao.getZt());
			bhDao.setDw_uuid(bdDao.getDw_uuid());
			bhDao.setConnection(conn);
			bhDao.create();
	
			
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
		
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<XxkPFGZListBean> getGzBybduuid(Xxkbsbdbh bhdao){
		DBBeanBase dbbase=new DBBeanBase("org",true);
		Connection conn=dbbase.getConn();
		Statement db = null;
		ResultSet rst = null;
		List<XxkPFGZListBean>  xxgzList = new ArrayList<XxkPFGZListBean>();
		try {
			db=conn.createStatement();
			String sql = "select g.name as gzname,g.biaohao as gzbiaohao,t.uuid as uuid ,t.yingyong as yingyong from fdt_oa_xxkpfgz g,fdt_oa_xxkbsbdbh h,fdt_oa_xxkbstj t " +
					     " where h.uuid = t.uuid_bd and t.uuid_gz=g.biaohao and h.sbsj >= g.kaishishijian "+
						 " and h.sbsj < g.jieshushijian and h.uuid ='"+bhdao.getUuid()+"'";
			rst=db.executeQuery(sql);
			while(rst.next()){
				
				String gzname = rst.getString("gzname");
				String gzbiaohao = rst.getString("gzbiaohao");
				int yingyongt = rst.getInt("yingyong");
				String uuid = rst.getString("uuid");
				XxkPFGZListBean xxkListBean = new XxkPFGZListBean();
				xxkListBean.setBiaohao(gzbiaohao);
				xxkListBean.setName(gzname);
				xxkListBean.setYingyongt(yingyongt);
				xxkListBean.setUuid(uuid);
				xxgzList.add(xxkListBean);
			}
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
		return xxgzList;
	}
	
	/**
	 * 根据表单的uuid得到统计列表中的列。
	 * @param bduuid
	 * @return
	 */
	public List getTjByBduuid(String bduuid){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		XxktjDao tjdao = new XxktjDao();
		try {
			tjdao.setUuid(uuid);
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, tjdao);
			tjdao.setWhereClause(" uuid_bd = '"+bduuid+"'");
			list = factory.find();
			

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
	return list;
	}
	
	
	/**
	 * jdbc 根据表单uuid删除fdt_oa_xxkbstj 中间表 
	 * @param bduuid
	 */
	public void deleteTjBybdUUid(String bduuid){
		DBBeanBase dbbase=new DBBeanBase("org",true);
		Connection conn=dbbase.getConn();
		Statement db=null;
		try {
			db=conn.createStatement();
			String sql = " delete from fdt_oa_xxkbstj where uuid_bd = '"+bduuid+"'";
			
			db.execute(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				db.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
