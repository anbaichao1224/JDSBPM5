package com.kzxd.tbm.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kzxd.tbm.dao.WjzlCatalogDAO;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.OrgNotFoundException;
import net.itjds.j2ee.dao.DAOException;


//��Ŀ¼����ֻ���ɹ���Ա�������������������
public class RootCatalogManager {

	 private static final Log logger = LogFactory.getLog("bpm", RootCatalogManager.class);
	 
	
		public int findisRoot(String id){
			DBBeanBase dbbase = new DBBeanBase("bpm", true);
			Connection conn = dbbase.getConn();
			PreparedStatement pst = null;
			ResultSet rs = null;
			
			int isRoot = -1;
			String sql = "select t.is_root isRoot from tbl_bbdept t where t.uuid=?";
			
	    	try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, id);
				rs = pst.executeQuery();

				while(rs != null && rs.next()){
					isRoot=rs.getInt("isRoot");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				this.close(conn, pst, rs, dbbase);
			}
			
			return isRoot;		
		}
	 //���в��ŵĸ�Ŀ¼������
	 public int count(String isRoot){
	 	DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
	    conn = dbbase.getConn();
	    
	    int count = 0;

		String sql="select count(*) count from tbl_bbdept t where t.IS_ROOT=?";
		sql+=" order by CATALOG_ORDER desc";

	    try {
	    	pst = conn.prepareStatement(sql);
	    	pst.setString(1, isRoot);
	    
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
	 
	 //���в��ŵĸ�Ŀ¼  ���з�ҳ
	 public List<WjzlCatalogDAO> findCatalog(String isRoot, int start, int limit){
	 	DBBeanBase dbbase;
		Connection conn;
		PreparedStatement pst = null;
		ResultSet rs = null;
		dbbase = new DBBeanBase("bpm", true);
	    conn = dbbase.getConn();

		List<WjzlCatalogDAO> list=new ArrayList<WjzlCatalogDAO>();
	    WjzlCatalogDAO dao = null;
	    
		String sql="select * from (select A.*,RowNum RN from " +
	    		"(select t.* from tbl_bbdept t where t.IS_ROOT=?";
		sql+=")A ) where RN>? and RN<=?";
		sql+=" order by CATALOG_ORDER desc";

	    try {
	    	pst = conn.prepareStatement(sql);
	    	pst.setString(1, isRoot);
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
				list.add(dao);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.closeLJ(conn, pst, rs, dbbase);
			
		}
		
		return list;	
		 
	 }
	
	//�ر�����
	public void closeLJ(Connection conn, PreparedStatement st, ResultSet rs, DBBeanBase dbbase ){
		
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}
		if(st != null){
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}					
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}				
		dbbase.close();
	}

 
	 //���Ӹ�Ŀ¼
	public void addCatalog(WjzlCatalogDAO dao){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection connect  = dbbase.getConn();
		try{
			dao.setConnection(connect);
			dao.create();
			connect.commit();
		}catch(SQLException e){
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} catch (DAOException e) {
			try {
				connect.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dbbase.close();
			
		}
	}
	//����Ŀ¼uuid��ѯ���и�Ŀ¼������
	public WjzlCatalogDAO get(String uuid){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		WjzlCatalogDAO dao = null;
		try{
			WjzlCatalogDAO dao1 = new WjzlCatalogDAO(conn);
			dao1.setUuid(uuid);
			dao = (WjzlCatalogDAO) dao1.findByPrimaryKey();
			dao1 = null;
		}catch(Exception e){
			 logger.error(e);
			 return null;
		 }finally{
			 conn = null;
			 dbbase.close();
		 }
		return dao ;
	}
	
	//�޸���������
	public void changeDept(String catalogId, String newDeptId){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection connect  = dbbase.getConn();
		WjzlCatalogDAO dao = new WjzlCatalogDAO();
		try{
			Org org = OrgManagerFactory.getOrgManager().getOrgByID(newDeptId);
			if(org == null)
				return;
			dao.setUuid(catalogId);
			dao.setConnection(connect);
			dao = (WjzlCatalogDAO) dao.findByPrimaryKey();
			dao.setCatalogOrgId(org.getID());
			dao.setCatalogOrgName(org.getName());
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
		} catch (OrgNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				connect.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			if(connect!=null){
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dbbase.close();
			
		}
	}
	
	//��������������Ϣ
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
			if(connect!=null){
				try {
					connect.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dbbase.close();
			
		}
	}
	
	//�ж��Ƿ���ɾ��
	public boolean canDelete(String catalogId){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		try{
			String sql = " select count(*) from tbl_bbdept t where t.PARENT_ID=?";
			dbbase.prepareSql(sql);
			dbbase.setString(1, catalogId);
			dbbase.executePreparedQuery();
			int count = Integer.parseInt(dbbase.getSelectDBResult().getItem(0, 0).toString());
			if(count > 0)
				return false;
			sql = null;
			sql = " select count(*) from WJZL_METERIAL t where t.CATALOG_UUID=?";
			dbbase.prepareSql(sql);
			dbbase.setString(1, catalogId);
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
	
	//ɾ����Ϣ
	public boolean delete(String catalogId){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		String ids[] = catalogId.split(",");
		boolean flag = true;
		
		try{
			WjzlCatalogDAO dao = new WjzlCatalogDAO(dbbase.getConn());

			for(int i=0; i<ids.length; i++){
				if(!canDelete(ids[i])){
					flag = false;
					continue;
				}

				dao.setUuid(ids[i]);
				dao.delete();
			}
			dbbase.commit();
		}catch(Exception e){
			logger.error(e);
			dbbase.rollback();
		}finally{
			dbbase.close();
		}
		return flag;
	}
	public void close(Connection conn, PreparedStatement pst, ResultSet rs, DBBeanBase dbbase){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pst != null){
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(dbbase != null){
			dbbase.close();
		}
	}
}
