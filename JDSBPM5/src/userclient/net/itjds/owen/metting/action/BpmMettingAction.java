/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.query.*;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.fdt.metting.BpmMatterDAO;
import net.itjds.fdt.metting.BpmMatterInfoDAO;
import net.itjds.fdt.metting.BpmMettingDAO;
import net.itjds.fdt.metting.MatterInfoListBean;
import net.itjds.fdt.metting.MatterListBean;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.attachment.FileListBean;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.action.BPMClientBaseBinding;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

// Referenced classes of package net.itjds.worklist.list.action:
//            BPMClientBaseBinding

public class BpmMettingAction implements Action {
	

	
	
	public BpmMettingAction() {
		// subCondition = null;
	}

	public String execute() throws Exception {
		
		return null;
	}

	public String mettingTreeSuccess(){
		
		return SUCCESS;
	}
	
	public String getOrgtreeJson(){
		
		String treestr = "";
		mtree = new MettingExtTreeDisplay();
		treestr = mtree.getChildTree(childOrgId, true, choose);
		return treestr;
	}
	
	//办结会议
	public String mettingBj(){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMettingDAO bmdao = new BpmMettingDAO();
		
		try{
			
			conn = dbbase.getConn();
			factory = new DAOFactory(conn,bmdao);
			bmdao.setUuid(childOrgId);
			BpmMettingDAO ba = (BpmMettingDAO)factory.find().get(0);
			factory = new DAOFactory(conn,ba);
			ba.setConnection(conn);
			ba.setHyzt("2");
			ba.update();
			conn.commit();
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return "bjsuccess";
	}

	public List getAllMetting(){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMettingDAO bmdao = new BpmMettingDAO();
		List minfo =null;
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bmdao);
			bmdao.setIsdelete("N");
			minfo = factory.find();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
		
        return minfo;
	}
	
	public String getById(){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMettingDAO bmdao = new BpmMettingDAO();
		List minfo =null;
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bmdao);
			bmdao.setUuid(childOrgId);
			if(blstatus!=null&&!blstatus.equals("")){
				bmdao.setUuid(blstatus);
				
			}
			minfo = factory.find();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        bmetting = (BpmMettingDAO)minfo.get(0);
        BpmMatterInfoAction bmaction = new BpmMatterInfoAction();
        matterinfolist = bmaction.getByhyjd("",childOrgId);
        return "findmettingbyid";
	}
	
	public BpmMettingDAO getById(String mid){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMettingDAO bmdao = new BpmMettingDAO();
		List minfo =null;
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bmdao);
			bmdao.setUuid(mid);
			if(blstatus!=null&&!blstatus.equals("")){
				//bmdao.setUuid(blstatus);
				bmdao.setHyzt(blstatus);
			}
			minfo = factory.find();
		}catch(Exception e){
			e.printStackTrace();
			
		}
		
		try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        if(minfo.size()==0){
        	return null;
        }else{
        	return (BpmMettingDAO)minfo.get(0);
        }
	}
	private MettingExtTreeDisplay mtree;
	private String name;
	private String childOrgId;
	private String blstatus;
	private String choose;

	private BpmMettingDAO bmetting;
	
	private List matterinfolist;
	
	
	public List getMatterinfolist() {
		return matterinfolist;
	}

	public void setMatterinfolist(List matterinfolist) {
		this.matterinfolist = matterinfolist;
	}

	public BpmMettingDAO getBmetting() {
		return bmetting;
	}

	public void setBmetting(BpmMettingDAO bmetting) {
		this.bmetting = bmetting;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChildOrgId() {
		return childOrgId;
	}

	public void setChildOrgId(String childOrgId) {
		this.childOrgId = childOrgId;
	}

	public String getChoose() {
		return choose;
	}

	public void setChoose(String choose) {
		this.choose = choose;
	}

	public String getBlstatus() {
		return blstatus;
	}

	public void setBlstatus(String blstatus) {
		this.blstatus = blstatus;
	}
	
	
	

}