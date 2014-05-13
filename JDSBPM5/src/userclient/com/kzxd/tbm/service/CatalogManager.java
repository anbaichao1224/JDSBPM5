package com.kzxd.tbm.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kzxd.tbm.dao.WjzlCatalogDAO;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;



// 目录管理
public class CatalogManager {

	 private static final Log logger = LogFactory.getLog("bpm", CatalogManager.class);
	 private List<Org> orgs= null;
	 private Org org = null;
	 
	 
	 //查询所有的目录  
	 public List<WjzlCatalogDAO> findAllCatalog(){
	 	DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
	    conn = dbbase.getConn();
		List<WjzlCatalogDAO> list=new ArrayList<WjzlCatalogDAO>();
	    WjzlCatalogDAO dao = null;
	    
		String sql="select t.* from tbl_bbdept t where t.is_root=1 ";
		sql+=" order by CATALOG_ORDER desc";

	    try {
	    	
	    	pst = conn.prepareStatement(sql);

	    	rs = pst.executeQuery();

			while(rs.next()){
				dao = new WjzlCatalogDAO();
				dao.setUuid(rs.getString("UUID"));
				dao.setCatalogName(rs.getString("CATALOG_NAME"));
				dao.setCatalogDesc(rs.getString("CATALOG_DESC"));
				dao.setIsRoot(rs.getInt("IS_ROOT"));
				dao.setParentId(rs.getString("PARENT_ID"));
				dao.setCatalogOrgId(rs.getString("CATALOG_ORG_ID"));
				dao.setCatalogOrgName(rs.getString("CATALOG_ORG_NAME"));
				dao.setCatalogOrder(rs.getInt("CATALOG_ORDER"));
				dao.setCreateDate(rs.getDate("CREATE_DATE"));				
				list.add(dao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeLJ(conn, pst, rs, dbbase);
		}
		return list;	
	 }
	
	 //根据目录uuid查询其所有的子目录的数量
	 public int count(String catalogUuid) {
		 DBBeanBase dbbase;
		 Connection conn;
		 PreparedStatement pst = null;
		 ResultSet rs = null;
		 dbbase = new DBBeanBase("bpm", true);
	     conn = dbbase.getConn();
	     
	     int count = 0;

		 List<WjzlCatalogDAO> list=new ArrayList<WjzlCatalogDAO>();
	     WjzlCatalogDAO dao = null;
	    
		 String sql="select count(*) count from tbl_bbdept t where t.parent_id=?";
			 sql+=" order by CATALOG_ORDER desc";

	     try {
	    	
	    	 pst = conn.prepareStatement(sql);
	    	 pst.setString(1, catalogUuid);

	    	 rs = pst.executeQuery();

			 while(rs.next()){
				 count = rs.getInt("count");
			 }
		 } catch (SQLException e) {
			 e.printStackTrace();
		 }finally{
			 this.closeLJ(conn, pst, rs, dbbase);
		 }
		
		 return count;
	 }

	 //根据目录uuid查询其所有的子目录
	 public List<WjzlCatalogDAO> findAllZml(String catalogUuid, int start, int limit){
	 	DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
	    conn = dbbase.getConn();

		List<WjzlCatalogDAO> list=new ArrayList<WjzlCatalogDAO>();
	    WjzlCatalogDAO dao = null;
	    
		String sql="select * from (select A.*,RowNum RN from ";
			sql+="(select t.* from tbl_bbdept t where t.parent_id=?";
			sql+=")A ) where RN>? and RN<=?";
			sql+=" order by CATALOG_ORDER desc";

	    try {
	    	
	    	pst = conn.prepareStatement(sql);
	    	pst.setString(1, catalogUuid);
	    	pst.setInt(2, start);
	    	pst.setInt(3, start+limit);

	    	rs = pst.executeQuery();

			while(rs.next()){
				dao = new WjzlCatalogDAO();
				dao.setUuid(rs.getString("UUID"));
				dao.setCatalogName(rs.getString("CATALOG_NAME"));
				dao.setCatalogDesc(rs.getString("CATALOG_DESC"));
				dao.setIsRoot(rs.getInt("IS_ROOT"));
				dao.setParentId(rs.getString("PARENT_ID"));
				dao.setCatalogOrgId(rs.getString("CATALOG_ORG_ID"));
				dao.setCatalogOrgName(rs.getString("CATALOG_ORG_NAME"));
				dao.setCatalogOrder(rs.getInt("CATALOG_ORDER"));
				dao.setCreateDate(rs.getDate("CREATE_DATE"));
				dao.setPersonid(rs.getString("PERSONID"));
				list.add(dao);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeLJ(conn, pst, rs, dbbase);
		}
		return list;	
	 }
	 
	 
	 //查找对应目录的根节点
	 public WjzlCatalogDAO getRoot(String catalogId){
		 DBBeanBase dbbase = new DBBeanBase("bpm", true);
		 Connection conn = dbbase.getConn();
		 try{
			 WjzlCatalogDAO dao = new WjzlCatalogDAO();
			 String uuid = catalogId;
			 while(true){
				 dao.setUuid(uuid);
				 dao.setConnection(conn);
				 dao = (WjzlCatalogDAO) dao.findByPrimaryKey();
				 if(dao == null){
					logger.error("can not find node. uuid： " + uuid);
					 break;
				 }
				 if(dao.getParentId() == null || dao.getParentId().length()==0){
					 return dao;
				 }
				 uuid = dao.getParentId();
				 dao = null;
				 dao = new WjzlCatalogDAO();
			 }
			 
		 }catch(Exception e){
			 logger.error(e);
			 return null;
		 }finally{
			 conn = null;
			 dbbase.close();
		 }
		 return null;
	 }
	 
	 //判断是否能够操作，条件是orgId与catalogId所在根目录部门一致
	 public boolean canOperate(String catalogId, String orgId){
		 if(orgId == null || orgId.length()==0){
			 logger.error("param is null");
			 return false;
		 }
		 WjzlCatalogDAO dao = this.getRoot(catalogId);
		 if(dao == null)
			 return false;
		 return dao.getCatalogOrgId().equals(orgId);
	 }
	 
	 //获取单个对象
	 public WjzlCatalogDAO get(String catalogId){
		 DBBeanBase dbbase = new DBBeanBase("bpm", true);
		 Connection conn = dbbase.getConn();
		 WjzlCatalogDAO dao = null;
		 try{
			 WjzlCatalogDAO dao1 = new WjzlCatalogDAO();
			 dao1.setUuid(catalogId);
			 dao1.setConnection(conn);
			 dao = (WjzlCatalogDAO) dao1.findByPrimaryKey();
			 dao1 = null;
		 }catch(Exception e){
			 logger.error(e);
			 return null;
		 }finally{
			 conn = null;
			 dbbase.close();
		 }
		 return dao;
	 }
	 //获取子目录下的人员列表
/*	 public List<TPersonDAO> findPerson(String cataLogId){
		 DBBeanBase dbbase = new DBBeanBase("bpm", true);
		 Connection conn = dbbase.getConn();
		 List<TPersonDAO> list = null;
		 try{
			 TPersonDAO dao1 = new TPersonDAO();
			 dao1.setOrgid(cataLogId);
			 DAOFactory factory = new DAOFactory(conn, dao1);
			 
			 list =  factory.find();
			 
			 dao1 = null;
		 }catch(Exception e){
			 logger.error(e);
			 return null;
		 }finally{
			 conn = null;
			 dbbase.close();
		 }
		 return list;
	 }*/
	 //获取子目录列表
	 public List<WjzlCatalogDAO> findChildren(String cataLogId){
		 DBBeanBase dbbase = new DBBeanBase("bpm", true);
		 Connection conn = dbbase.getConn();
		 List<WjzlCatalogDAO> list = null;
		 try{
			 WjzlCatalogDAO dao1 = new WjzlCatalogDAO();
			 dao1.setParentId(cataLogId);
			 dao1.setIsRoot(2);
			 DAOFactory factory = new DAOFactory(conn, dao1);
			 list =  factory.find();
			 dao1 = null;
		 }catch(Exception e){
			 logger.error(e);
			 return null;
		 }finally{
			 conn = null;
			 dbbase.close();
		 }
		 return list;
	 }
	 //获取子目录列表
	 public List<WjzlCatalogDAO> findChildren1(String cataLogId){
		 DBBeanBase dbbase = new DBBeanBase("bpm", true);
		 Connection conn = dbbase.getConn();
		 List<WjzlCatalogDAO> list = null;
		 try{
			 WjzlCatalogDAO dao1 = new WjzlCatalogDAO();
			 dao1.setParentId(cataLogId);
			 DAOFactory factory = new DAOFactory(conn, dao1);
			 list =  factory.find();
			 dao1 = null;
		 }catch(Exception e){
			 logger.error(e);
			 return null;
		 }finally{
			 conn = null;
			 dbbase.close();
		 }
		 return list;
	 }
	 
	 //获取子部门列表
	 private Map<String, Object> processChild(Org dept){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", dept.getID());
			map.put("name", dept.getName());	
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(0);
			
			List<Person> persons = dept.getPersonList();
			for(int i=0; i<persons.size();i++){
				Person p = persons.get(i);
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("id", p.getID());
				map1.put("name", p.getName());
				list.add(map1);
			}
			List<Org> children = dept.getChildrenList();
			
			
				
				for(int i=0; i<children.size(); i++){
					list.add(processChild(children.get(i)));
				}

				map.put("children", list);
			return map;
		}
		
	 //查看单个uuid对应的对象是否存在
	 public boolean isExist(String catalogId){
		 DBBeanBase dbbase = new DBBeanBase("bpm", true);
			try{
				String sql = " select count(*) from tbl_bbdept t where t.UUID=?";
				dbbase.prepareSql(sql);
				dbbase.setString(0, catalogId);
				dbbase.executePreparedQuery();
				int count = Integer.parseInt(dbbase.getSelectDBResult().getItem(0, 0).toString());
				if(count > 0)
					return false;
			}catch(Exception e){
				logger.error(e);
				return false;
			}finally{
				dbbase.close();
			}
			return true;
	 }
	 
	 /**
	  * 
	  * @param bean
	  * @param parentId
	  * @param orgId: the org of current person 
	  * @return 1-success, 0-no operating authority, 2-other error
	  */
	 public int addCatalog(WjzlCatalogDAO bean, String parentId, String orgId){
		 WjzlCatalogDAO parent = this.get(parentId);
		 if(parent == null){
			 logger.error("no this WjzlCatalogDAO: uuid=" + parentId);
			 return 2;
		 }
		 if(!parent.getCatalogOrgId().equals(orgId)){
			 logger.error("no operating authority: orgId=" + orgId + " uuid=" + parentId);
			 return 0;
		 }
		 
		 DBBeanBase dbbase = new DBBeanBase("bpm", true);
		 Connection conn = dbbase.getConn();
		 List<WjzlCatalogDAO> list = null;
		 try{
			bean.setParentId(parentId);
			bean.setCatalogOrgId(orgId);
			bean.setCatalogOrgName(parent.getCatalogOrgName());
			bean.setConnection(conn);
			bean.create();
		 }catch(Exception e){
			 logger.error(e);
			 return 2;
		 }finally{
			 conn = null;
			 dbbase.close();
		 }
		 
		 return 1;
	 }
	 
	//判断是否能删除
	public boolean canDelete(String catalogId){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		try{
			String sql = " select count(*) from tbl_bbdept t where t.PARENT_ID=?";
			dbbase.prepareSql(sql);
			dbbase.setString(0, catalogId);
			dbbase.executePreparedQuery();
			int count = Integer.parseInt(dbbase.getSelectDBResult().getItem(0, 0).toString());
			if(count > 0)
				return false;
			sql = null;
			sql = " select count(*) from tbl_bbdept t where t.CATALOG_UUID=?";
			dbbase.prepareSql(sql);
			dbbase.setString(0, catalogId);
			dbbase.executePreparedQuery();
			count = Integer.parseInt(dbbase.getSelectDBResult().getItem(0, 0).toString());
			if(count > 0)
				return false;
			
		}catch(Exception e){
			logger.error(e);
			return false;
		}finally{
			dbbase.close();
		}
		
		return true;
	}
	
	//删除信息
	public void delete(String catalogId){
		if(!canDelete(catalogId))
			return;
		
		String prepareSql = " delete from tbl_bbdept t where t.UUID=?";
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		try{
			dbbase.prepareSql(prepareSql);
			if(-1==dbbase.executePreparedUpdate()){
				dbbase.rollback();
				return;
			}
			dbbase.commit();
		}catch(Exception e){
			logger.error(e);
			dbbase.rollback();
		}finally{
			dbbase.close();
		}
	}
	
	//更新所属部门信息
	public void update(WjzlCatalogDAO dao){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection connect  = dbbase.getConn();
	
		try{
			dao.setConnection(connect);
			dao.update();
			connect.commit();
		}catch(SQLException e){
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			connect = null;
			dbbase.close();
			
		}
	}

	//根据部门id查出所在部门的目录
	public List<WjzlCatalogDAO>  getCatalogOrg(String orgId) {
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		WjzlCatalogDAO wdao=new WjzlCatalogDAO();
		List<WjzlCatalogDAO> list = null;
		try{
			wdao.setIsRoot(1);
			DAOFactory factory = new DAOFactory(dbbase.getConn(), wdao);
			list =  factory.find();
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			dbbase.close();
		}

		return list;
	}
	
	//关闭连接
	public void closeLJ(Connection conn, PreparedStatement pst, ResultSet rs, DBBeanBase dbbase ){

		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		if(pst != null){
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
		dbbase.close();
	}



	public List<Org> getOrgs() {
		return orgs;
	}
	public void setOrgs(List<Org> orgs) {
		this.orgs = orgs;
	}
	public Org getOrg() {
		return org;
	}
	public void setOrg(Org org) {
		this.org = org;
	}


}
