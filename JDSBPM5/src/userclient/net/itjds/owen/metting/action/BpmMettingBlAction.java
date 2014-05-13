/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.query.*;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.fdt.metting.BpmMatterBLDAO;
import net.itjds.fdt.metting.BpmMatterDAO;
import net.itjds.fdt.metting.BpmMatterInfoDAO;
import net.itjds.fdt.metting.BpmMettingDAO;
import net.itjds.fdt.metting.BpmMettingInstDAO;
import net.itjds.fdt.metting.BpmMettingModelDAO;
import net.itjds.fdt.metting.BpmMettingTreeDAO;
import net.itjds.fdt.metting.MatterInfoListBean;
import net.itjds.fdt.metting.MatterListBean;
import net.itjds.fdt.metting.MettingBean;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.attachment.FileListBean;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.action.BPMClientBaseBinding;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.owen.metting.attachment.DelFileAction;

import org.apache.struts2.ServletActionContext;
import org.jdom.JDOMException;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

// Referenced classes of package net.itjds.worklist.list.action:
//            BPMClientBaseBinding

public class BpmMettingBlAction implements Action {

	public BpmMettingBlAction() {
		
	}
	
	public String execute() throws Exception {
		
		return null;
	}
	 public String formatDate()
	    {
	        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
	        return sd.format(new Date(System.currentTimeMillis()));
	    }
	 
	 
	 //修改会议信息
	 
	 public String updateInfo(){
			Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
			
			BpmMettingInstDAO bpmmmodel = new BpmMettingInstDAO();
			BpmMettingInstDAO ba = new BpmMettingInstDAO();
			DBBeanBase dbbase;
			Connection conn;
			dbbase = new DBBeanBase("bpm", true);
			conn = null;
			DAOFactory factory = null;
			conn = dbbase.getConn();
			try {
				factory = new DAOFactory(conn, ba);
				factory.setDAO(ba);
				ba.setUuid(tid);
				bpmmmodel = (BpmMettingInstDAO)factory.findByPrimaryKey();
				factory = new DAOFactory(conn, bpmmmodel);
				factory.setDAO(bpmmmodel);
				bpmmmodel.setConnection(conn);
				bpmmmodel.setUuid(tid);
				bpmmmodel.setKssj(kssj);
				bpmmmodel.setJssj(jssj);
				bpmmmodel.setSxlx(sxlx);
				bpmmmodel.setXxmc(xxname);
				bpmmmodel.setBlrid(personid);
				bpmmmodel.setBlrmc(personname);
				bpmmmodel.setSxnr(sxnr);
				bpmmmodel.setBlstatus("0");
				bpmmmodel.setProcessdefid(processdefid);
				bpmmmodel.update();
				isexit = false;
				saveblr(tid,personid,personname,bpmmmodel.getMettingid(),bpmmmodel.getParentid());
				conn.commit();
			} catch (Exception e) {
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
	        return "updateInfo";
		}
	 
	//保存事项办理人
	public String saveblr(String sxxxid,String personid,String personname,String mid,String pid){
		
		List blrlist = getByxxid(sxxxid);
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        conn = dbbase.getConn();
        Map<String,String> bldatemap = new HashMap<String,String>();
		for(int i=0;i<blrlist.size();i++){
			
			
				BpmMatterBLDAO bpmbl = (BpmMatterBLDAO)blrlist.get(i);
				if(isexit){
					bldatemap.put(bpmbl.getPersonid(), bpmbl.getBldate());
				}
	        try{
	        	
	        	
	        	factory = new DAOFactory(conn,bpmbl);
	        	factory.setDAO(bpmbl);
	        	bpmbl.setConnection(conn);
	        	bpmbl.delete();
	        	
	        }catch(Exception e){
	        	
	        	e.printStackTrace();
	        }
		}
		
		
		String[] pids = personid.split(",");
		String[] pnames = personname.split(",");
		
		for(int i = 0;i<pids.length;i++){
			BpmMatterBLDAO bpmbl = new BpmMatterBLDAO();
			String uuid = (new UUID()).toString();
			 try{
		        	
		        	//conn = dbbase.getConn();
		        	factory = new DAOFactory(conn,bpmbl);
		        	factory.setDAO(bpmbl);
		        	bpmbl.setConnection(conn);
		        	bpmbl.setUuid(uuid);
		        	bpmbl.setPersonid(pids[i]);
		        	bpmbl.setPersonname(pnames[i]);
		        	bpmbl.setSxxxid(sxxxid);
		        	bpmbl.setMettingid(mid);
		        	bpmbl.setParentid(pid);
		        	if(bldatemap.containsKey(pids[i])){
		        		bpmbl.setBldate(bldatemap.get(pids[i]));
		        	}
		        	bpmbl.create();
		        	conn.commit();
		        }catch(Exception e){
		        	
		        	e.printStackTrace();
		        }
		}
		
		
		 try
	        {
	            conn.close();
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
		
		return null;
	}

	//删除办理人
	public void deletebl(String sxxxid){
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        //uuid = (new UUID()).toString();
        conn = null;
        DAOFactory factory = null;
        BpmMatterBLDAO bpmbl = new BpmMatterBLDAO();
        try{
        	
        	conn = dbbase.getConn();
        	factory = new DAOFactory(conn,bpmbl);
        	factory.setDAO(bpmbl);
        	bpmbl.setConnection(conn);
        	bpmbl.setSxxxid(sxxxid);
        	bpmbl.batchDelete();
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
	}
	
	
	//修改办理人办理时间
	
	public void updateBl(String personid,String sxxxid){
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        //uuid = (new UUID()).toString();
        conn = null;
        DAOFactory factory = null;
        BpmMatterBLDAO bpmbl = new BpmMatterBLDAO();
        try{
        	
        	conn = dbbase.getConn();
        	factory = new DAOFactory(conn,bpmbl);
        	factory.setDAO(bpmbl);
        	bpmbl.setConnection(conn);
        	bpmbl.setPersonid(personid);
        	bpmbl.setSxxxid(sxxxid);
        	List list = factory.find();
        	BpmMatterBLDAO ba = (BpmMatterBLDAO)factory.find().get(0);
        	bpmbl = ba;
        	bpmbl.setConnection(conn);
        	bpmbl.setBldate(formatDate());
        	bpmbl.update();
        	conn.commit();
        	 try
             {
                 conn.close();
             }
             catch(SQLException e)
             {
                 e.printStackTrace();
             }
        	blstatus = "1";
        	tid = sxxxid;
        	matterBj();
        }catch(Exception e){
        	e.printStackTrace();
        }
       
	}
	
	//更改事项信息办理状态
	public String matterBj(){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMettingInstDAO bminstdao = new BpmMettingInstDAO();
		
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bminstdao);
			//bminfo.setConnection(conn);
			bminstdao.setUuid(tid);
			bminstdao = (BpmMettingInstDAO)factory.find().get(0);
			BpmMettingInstDAO ba = new BpmMettingInstDAO();
			 try
		        {
		            conn.close();
		        }
		        catch(SQLException e)
		        {
		            e.printStackTrace();
		        }
		   conn = dbbase.getConn();
		  DAOFactory factory2 = new DAOFactory(conn, ba);
		   
			ba = bminstdao;
			ba.setConnection(conn);
			ba.setBlstatus(blstatus);
			//ba.batchUpdate();
			ba.update();
			//ba.update();
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
		return null;
	}
	
	
	//更改事项信息流程实例
	public void proinst(String uid,String proinstid,String activityinstid){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		 BpmMettingTreeDAO bminfo = new BpmMettingInstDAO();
		
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bminfo);
			//bminfo.setConnection(conn);
			bminfo.setUuid(uid);
			bminfo = (BpmMettingInstDAO)factory.find().get(0);
			BpmMettingTreeDAO ba = new BpmMettingInstDAO();
			factory = new DAOFactory(conn, ba);
			ba = bminfo;
			ba.setConnection(conn);
			ba.setProcessinstid(proinstid);
			ba.setActivityinstid(activityinstid);
			ba.setBlstatus("1");
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
		
	}
	
	
	//重新办理
	
	public String matterrebl(){
		
				/*try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bminfo);
			//bminfo.setConnection(conn);
			bminfo.setUuid(uuid);
			bminfo = (BpmMatterInfoDAO)factory.find().get(0);
			BpmMatterInfoDAO ba = new BpmMatterInfoDAO();
			factory = new DAOFactory(conn, ba);
			ba = bminfo;
			ba.setConnection(conn);
			ba.setBlstatus("0");
			ba.update();
			BpmMatterBLAction bpmbl = new BpmMatterBLAction();
			bpmbl.setHystatus("2");//会议状态为2表示办结，办结后重新办理
			bpmbl.saveblr(uuid, personid, personname);
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
	        }*/
		return "reblmatter";
	}
	
	//删除
	public void matterDelete() {

		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		
		BpmMettingTreeDAO bminfo = null;
		BpmMettingTreeDAO ba = null;
		if(optionnum==1){
			bminfo = new BpmMettingInstDAO();
			ba = new BpmMettingInstDAO();
		}else{
			
			bminfo = new BpmMettingModelDAO();
			ba = new BpmMettingModelDAO();
		}

		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bminfo);
			// bminfo.setConnection(conn);
			//bminfo.setUuid(tid);
			
			//bminfo = (BpmMettingTreeDAO) factory.find().get(0);
			//factory = new DAOFactory(conn, ba);
			//ba = bminfo;
			//ba.setConnection(conn);
			if(delstatus.equals("Y")){
				bminfo.setConnection(conn);
				if(adddirection==2){
					new BpmMatterBLAction().deletebl(tid);
					new DelFileAction().delDataFromDB(tid);
					bminfo.setUuid(tid);
					bminfo.delete();
					
				}else{
					bminfo.setParentid(tid);
					List list =factory.find();
					for(int i =0;i<list.size();i++){
						BpmMettingTreeDAO bmtree = (BpmMettingTreeDAO)list.get(i);
						new BpmMatterBLAction().deletebl(bmtree.getUuid());
						new DelFileAction().delDataFromDB(bmtree.getUuid());
						//ba.delete();
					}
					bminfo.batchDelete();
					factory = new DAOFactory(conn, ba);
					ba.setConnection(conn);
					ba.setUuid(tid);
					ba.delete();
				}
			}else{
				bminfo.setConnection(conn);
				bminfo.setUuid(tid);
				
				bminfo.setIsdelete("Y");
				bminfo.update();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	//根据事项信息id查找办理人
	public List getByxxid(String sxxxid){
		
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        //uuid = (new UUID()).toString();
        conn = null;
        DAOFactory factory = null;
        BpmMatterBLDAO bpmbl = new BpmMatterBLDAO();
        List matterbl=new ArrayList();
		try{
			
			conn = dbbase.getConn();
        	factory = new DAOFactory(conn,bpmbl);
        	bpmbl.setSxxxid(sxxxid);
        	matterbl = factory.find();

		}catch(Exception e){
			e.printStackTrace();
			matterbl = null;
		}
		
		try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        
        return matterbl;
	}
	
	
	public String mettingLbTreeSuccess(){
		
		return SUCCESS;
	}
	
	
	
	private String tid;
	private String blstatus;
	private String xxname;
	private String creator;
	private String createdate;
	private String personid;
	private String personname;
	private String processdefid;
	private String sxnr;
	private String sxlx;
	private String kssj;
	private String jssj;
	private String parentid;
	private String mettingid;
	private boolean isexit = false;//判断办理人是新增还是修改
	private String delstatus;//删除标志，是否从数据库中删掉
	private int optionnum;
	private int adddirection;
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getBlstatus() {
		return blstatus;
	}

	public void setBlstatus(String blstatus) {
		this.blstatus = blstatus;
	}

	public String getXxname() {
		return xxname;
	}

	public void setXxname(String xxname) {
		this.xxname = xxname;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public String getProcessdefid() {
		return processdefid;
	}

	public void setProcessdefid(String processdefid) {
		this.processdefid = processdefid;
	}

	public String getSxnr() {
		return sxnr;
	}

	public void setSxnr(String sxnr) {
		this.sxnr = sxnr;
	}

	public String getSxlx() {
		return sxlx;
	}

	public void setSxlx(String sxlx) {
		this.sxlx = sxlx;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJssj() {
		return jssj;
	}

	public void setJssj(String jssj) {
		this.jssj = jssj;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getMettingid() {
		return mettingid;
	}

	public void setMettingid(String mettingid) {
		this.mettingid = mettingid;
	}

	public String getDelstatus() {
		return delstatus;
	}

	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}

	public int getOptionnum() {
		return optionnum;
	}

	public void setOptionnum(int optionnum) {
		this.optionnum = optionnum;
	}

	public int getAdddirection() {
		return adddirection;
	}

	public void setAdddirection(int adddirection) {
		this.adddirection = adddirection;
	}
	
	
	

	

	
	

}