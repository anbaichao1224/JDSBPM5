package com.kzxd.xzsp.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.itjds.bpm.data.FormClassBean;
import net.itjds.bpm.engine.BPMException;
import net.itjds.common.database.DBBeanBase;
import net.itjds.fdt.dao.fawen.FdtOaWsxtspDAO;
import net.itjds.owen.metting.attachment.CopyFileAction;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.UserClientException;

import org.apache.struts2.ServletActionContext;

import com.kzxd.xzsp.util.Material;
import com.kzxd.xzsp.util.Permission;
import com.opensymphony.xwork2.Action;

public class LctzAction extends BPMActionBase {
	public LctzAction()  {
		super();
	}

	public FormClassBean getCurrForm() throws  BPMException, UserClientException{
	
		List<FormClassBean> formList=this.getActivityInst().getActivityDef().getAllDataFormDef();
		if (formList.size()==1){
			return formList.get(0);
		}
		for(int k=0;formList.size()>k;k++){
			FormClassBean formClassBean=formList.get(k);
			if (formClassBean.getId().equals(this.getFormID())){
				return formClassBean;
			}
		}	
		FormClassBean formClassBean=this.getActivityInst().getActivityDef().getMainFormDef();
		return formClassBean;
	}
	
	//根据permissionid查找permission和application
	public FdtOaWsxtspDAO findAppPerByPerid(String permissionid){
		FdtOaWsxtspDAO dao = new FdtOaWsxtspDAO();
		String applicationid = "";
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			
			String sqlStr = "select * from xzspjk_permission t where t.uuid = '"+permissionid+"'";
			rs = st.executeQuery(sqlStr);
			if(rs != null && rs.next()){
				if(rs.getString("id") == null ){
					dao.setId("");
				}else{
					dao.setId(rs.getString("id"));
				}
				if(rs.getString("name") == null ){
					dao.setName("");
				}else{
					dao.setName(rs.getString("name"));
				}
				if(rs.getString("bsnum") == null ){
					dao.setBsnum("");
				}else{
					dao.setBsnum(rs.getString("bsnum"));
				}
				if(rs.getString("xmmc") == null ){
					dao.setXmmc("");
				}else{
					dao.setXmmc(rs.getString("xmmc"));
				}
				if(rs.getString("department") == null ){
					dao.setDepartment("");
				}else{
					dao.setDepartment(rs.getString("department"));
				}
				if(rs.getString("status") == null ){
					dao.setStatus("");
				}else{
					dao.setStatus(rs.getString("status"));
				}
				applicationid = rs.getString("applicationid");
			}
			
			String sqlString = "select * from xzspjk_application t where t.uuid = '"+applicationid+"'";
			rs = st.executeQuery(sqlString);
			if(rs != null && rs.next()){
				if(rs.getString("appname") == null ){
					dao.setAppname("");
				}else{
					dao.setAppname(rs.getString("appname"));
				}
				if(rs.getString("apporg") == null ){
					dao.setApporg("");
				}else{
					dao.setApporg(rs.getString("apporg"));
				}
				if(rs.getString("cardid") == null ){
					dao.setCardid("");
				}else{
					dao.setCardid(rs.getString("cardid"));
				}
				if(rs.getString("appdate") == null ){
					dao.setAppdate("");
				}else{
					dao.setAppdate(rs.getString("appdate"));
				}
				if(rs.getString("mobilephone") == null ){
					dao.setMobilephone("");
				}else{
					dao.setMobilephone(rs.getString("mobilephone"));
				}
				if(rs.getString("phone") == null ){
					dao.setPhone("");
				}else{
					dao.setPhone(rs.getString("phone"));
				}
				if(rs.getString("email") == null ){
					dao.setEmail("");
				}else{
					dao.setEmail(rs.getString("email"));
				}
				if(rs.getString("address") == null ){
					dao.setAddress("");
				}else{
					dao.setAddress(rs.getString("address"));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
		return dao;
	}
	//根据permissionid查找
	public List<Material> findMeByPerid(String permissionid){
		List<Material> materials = null;
		Material material = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			String sql ="select * from xzspjk_material t where t.permissionid='"+permissionid+"'";
			rs = st.executeQuery(sql);
			materials = new ArrayList<Material>();
			while(rs != null && rs.next()){
				material = new Material();
				material.setUuid(rs.getString("uuid"));
				material.setId(rs.getString("id"));
				material.setMlid(rs.getString("mlid"));
				material.setMlname(rs.getString("mlname"));
				material.setSelecttype(rs.getString("selecttype"));
				material.setFid(rs.getString("fid"));
				material.setFpath(rs.getString("fpath"));
				material.setFname(rs.getString("fname"));
				material.setStatus(rs.getString("status"));
				material.setOrinum(rs.getString("orinum"));
				material.setCopynum(rs.getString("copynum"));
				material.setIsneed(rs.getString("isneed"));
				material.setBaseinfo(rs.getString("baseinfo"));
				material.setAdjustment(rs.getString("adjustment"));
				materials.add(material);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if(rs != null){
					rs.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(st != null){
					st.close();					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {
				if(conn != null){
					conn.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			try {				
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}			
		return materials;
	}	
	
	//根据permissionid把流程id保存到permission里面
	public void updatePInstId(String pInstId,String perId){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = dbbase.getConn();
		Statement st = null;
		try {
			try {
				st = conn.createStatement();
				String sql ="update xzspjk_permission t set t.processinst_id = '"+pInstId+"' where t.uuid='"+perId+"'";
				st.executeUpdate(sql);
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if(st != null){
						st.close();					
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {
					if(conn != null){
						conn.close();
					}				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				try {				
					dbbase.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String execute() throws Exception {
		
		List<Material> materials = this.findMeByPerid(permissionid);
		String pInstId = "";
		if(this.getProcessInst()==null){
			pInstId = this.client.getActivityInst(activityInstId).getProcessInstId();
			
			new CopyFileAction().xzspCopyfile(materials,pInstId, this.activityInstId, this.formID);
		}else{
			pInstId = this.getProcessInst().getProcessInstId();
			new CopyFileAction().xzspCopyfile(materials, this.getProcessInst().getProcessInstId(), this.activityInstId, this.formID);
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		if(permissionid!=null){
			fdtOaWsxtspDAO = this.findAppPerByPerid(permissionid);
			
			request.setAttribute("fdtOaWsxtspDAO", fdtOaWsxtspDAO);
		}
		
		//将流程的id添加到对应的permission的表里面
		this.updatePInstId(pInstId,permissionid);
		
		return Action.SUCCESS;
	}
	
	
	private FdtOaWsxtspDAO fdtOaWsxtspDAO;

	private String permissionid;


	public String getPermissionid() {
		return permissionid;
	}

	public void setPermissionid(String permissionid) {
		this.permissionid = permissionid;
	}

	public FdtOaWsxtspDAO getFdtOaWsxtspDAO() {
		return fdtOaWsxtspDAO;
	}

	public void setFdtOaWsxtspDAO(FdtOaWsxtspDAO fdtOaWsxtspDAO) {
		this.fdtOaWsxtspDAO = fdtOaWsxtspDAO;
	}
}
