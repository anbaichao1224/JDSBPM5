/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.query.*;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.App;
import net.itjds.common.org.base.Module;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.fdt.metting.BpmMatterDAO;
import net.itjds.fdt.metting.BpmMatterInfoDAO;
import net.itjds.fdt.metting.BpmMettingDAO;
import net.itjds.fdt.metting.BpmMettingInstDAO;
import net.itjds.fdt.metting.BpmMettingTreeDAO;
import net.itjds.fdt.metting.MatterListBean;
import net.itjds.fdt.metting.ModuleBean;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.owen.metting.attachment.DelFileAction;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.attachment.FileListBean;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.action.BPMClientBaseBinding;
import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

// Referenced classes of package net.itjds.worklist.list.action:
//            BPMClientBaseBinding

public class BpmMatterInfoAction implements Action {

	private String uuid;
	private String sxname;
	
	private String creator;
	
	private String createdate;
	
	private String sxkssj;
	
	private String sxjssj;
	
	private String sxcontent;
	
	private String sxblqk;
	
	private String sxshyj;
	
	private String sxlx;
	
	private String sxhyid;
	
	private String sxjd;
	private String blstatus;
	
	private String personid;
	private String personname;
	
	private String processdefid;
	
	private String delstatus;//判断是否真删除，添加会议时的删除为真删  Y为真删
	private MatterListBean mlbean;
	
	public BpmMatterInfoAction() {
		// subCondition = null;
	}

	public String execute() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=GBK");
		BpmMatterDAO bmdao = new BpmMatterDAO();

		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;

		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bmdao);
			
		} catch (Exception e) {

			e.printStackTrace();
			// break MISSING_BLOCK_LABEL_287;
		}

		try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
		return "success";
	}
	
	//更改事项信息办理状态
	public String matterBj(){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMatterInfoDAO bminfo = new BpmMatterInfoDAO();
		
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bminfo);
			//bminfo.setConnection(conn);
			bminfo.setUuid(uuid);
			bminfo = (BpmMatterInfoDAO)factory.find().get(0);
			BpmMatterInfoDAO ba = new BpmMatterInfoDAO();
			factory = new DAOFactory(conn, ba);
			ba = bminfo;
			ba.setConnection(conn);
			ba.setBlstatus(blstatus);
			
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
		return "bjmatter";
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
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMatterInfoDAO bminfo = new BpmMatterInfoDAO();
		
		blstatus = "0";
		updateInfo();
		BpmMatterBLAction bpmbl = new BpmMatterBLAction();
		bpmbl.setHystatus("2");//会议状态为2表示办结，办结后重新办理
		bpmbl.saveblr(uuid, personid, personname);
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
		BpmMatterInfoDAO bminfo = new BpmMatterInfoDAO();

		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bminfo);
			// bminfo.setConnection(conn);
			bminfo.setUuid(uuid);
			bminfo = (BpmMatterInfoDAO) factory.find().get(0);
			BpmMatterInfoDAO ba = new BpmMatterInfoDAO();
			factory = new DAOFactory(conn, ba);
			ba = bminfo;
			ba.setConnection(conn);
			if(delstatus.equals("Y")){
				new BpmMatterBLAction().deletebl(uuid);
				new DelFileAction().delDataFromDB(uuid);
				ba.delete();
			}else{
				ba.setIsdelete("Y");
				ba.update();
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

	//修改事项信息调用此方法，查看自己添加的会议下的事项时调用此方法修改事项实体
	public String updateInfo(){
		Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
		BpmMatterInfoDAO refdao = new BpmMatterInfoDAO();
        refdao.setSxmc(sxname);
        refdao.setSxkssj(sxkssj);
        refdao.setSxjssj(sxjssj);
        refdao.setSxnr(sxcontent);
        refdao.setSxlx(sxlx);
        refdao.setSxssjd(sxjd);
        refdao.setCreatorId(person.getID());
        refdao.setCreator(creator);
        refdao.setCreatordate(createdate);
        refdao.setSxblqk(sxblqk);
        refdao.setSxshyj(sxshyj);
        refdao.setSxhyid(sxhyid);
        refdao.setBlstatus(blstatus);
        refdao.setIsdelete("N");
        refdao.setProcessdefid(processdefid);
        //为何没有对流程涉及的字段赋值，不确定是否影响修改
        //this.setPersonid(personid)
        save(refdao,uuid);
        return "updateInfo";
	}
	
	//事项信息保存
	public String save(BpmMatterInfoDAO refdao,String uid){
		
		BpmMatterInfoDAO bminfodao = new BpmMatterInfoDAO();
		
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        //uuid = (new UUID()).toString();
        conn = null;
        DAOFactory factory = null;
        BpmMettingDAO bmdao=null;
        try{
        	
        	conn = dbbase.getConn();
        	factory = new DAOFactory(conn,bminfodao);
            

            factory.setDAO(bminfodao);
            bminfodao.setConnection(conn);
            bminfodao.setUuid(refdao.getUuid());
            bminfodao.setSxmc(refdao.getSxmc());
            bminfodao.setCreatorId(refdao.getCreatorId());
            bminfodao.setCreator(refdao.getCreator());
            bminfodao.setCreatordate(refdao.getCreatordate());
            bminfodao.setSxkssj(refdao.getSxkssj());
            bminfodao.setSxjssj(refdao.getSxjssj());
            bminfodao.setSxnr(refdao.getSxnr());
            bminfodao.setSxlx(refdao.getSxlx());
            bminfodao.setSxblqk(refdao.getSxblqk());
            bminfodao.setSxshyj(refdao.getSxshyj());
            bminfodao.setSxssjd(refdao.getSxssjd());
            bminfodao.setSxhyid(refdao.getSxhyid());
            bminfodao.setBlstatus(refdao.getBlstatus());
            bminfodao.setIsdelete(refdao.getIsdelete());
            bminfodao.setProcessinstid(refdao.getProcessinstid());
            bminfodao.setActivityinstid(refdao.getActivityinstid());
            bminfodao.setProcessdefid(refdao.getProcessdefid());
           if(uid.equals("")||uid==null){
        	   
        	   //uuid = (new UUID()).toString();
        	   bminfodao.setUuid(uuid);
        	   bminfodao.create();
           }else{
        	  bminfodao.setUuid(uid);
        	   bminfodao.update();
        	   uuid = uid;
        	  // BpmMettingAction bmaction = new BpmMettingAction();
        	   //bmdao = bmaction.getById("");
           }   
           
            
            
            conn.commit();
            BpmMatterBLAction bpmblAction = new BpmMatterBLAction();
            //bpmblAction.setHystatus(bmdao.getHyzt());
            bpmblAction.saveblr(uuid, personid, personname);
        	
        }catch(Exception e){
        	e.printStackTrace();
        	try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return "";
        }
        

        try
        {
            conn.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return uuid;
	}
	
	//查找属于某个会议阶段及会议下的事项信息列表，hyjd 是阶段名称的首字母，需要转换 此功能 待做 
	public List getByhyjd(String hyjd,String mid){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMatterInfoDAO bmdao = new BpmMatterInfoDAO();
		List minfo =null;
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bmdao);
			if(!hyjd.equals("")){//之前为mid  mid应该是会议id 可能之前写错了
				
				bmdao.setSxssjd(hyjd);
			}
			bmdao.setIsdelete("N");
			bmdao.setSxhyid(mid);//此前注销了  因做已添加列表时需要 则放开了
			minfo = factory.find();
			BpmMatterAction bmaction = new BpmMatterAction();
			bmaction.setMlist(minfo);
			bmaction.addMatterToBeanList();
			minfo = bmaction.getMatterInfoList();
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
	
	//获取模块
	private List modulelist;
	public String getModule()throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=GBK");
		List modulealllist = new ArrayList();
		modulelist = new ArrayList();
		try {
			modulealllist = OrgManagerFactory.getOrgManager().getPersonByID(personid).getAllRightModule();
			if(modulealllist!=null&&modulealllist.size()>0){
				for(int i=0;i<modulealllist.size();i++){
					Module module = (Module)modulealllist.get(i);
					
					if(module.getUrl().indexOf("demoInsert.action")>=0){
						
						List<App> applist = module.getAppList();
						if(applist.size()>0){
							for(int j =0;j<applist.size();j++){
								ModuleBean mbean = new ModuleBean();
								String pid = module.getUrl().substring(module.getUrl().indexOf("=")+1);
								mbean.setId(pid);
								mbean.setName(applist.get(j).getName());
								modulelist.add(mbean);
							}
						
						}
					}
				}
			}
			
				JSONArray  ja = JSONArray.fromObject(modulelist);
				response.getWriter().write(ja.toString());
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	public List getModulelist() {
		return modulelist;
	}

	public void setModulelist(List modulelist) {
		this.modulelist = modulelist;
	}
	
	

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	public String getSxname() {
		return sxname;
	}

	public void setSxname(String sxname) {
		this.sxname = sxname;
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

	public String getSxkssj() {
		return sxkssj;
	}

	public void setSxkssj(String sxkssj) {
		this.sxkssj = sxkssj;
	}

	public String getSxjssj() {
		return sxjssj;
	}

	public void setSxjssj(String sxjssj) {
		this.sxjssj = sxjssj;
	}

	public String getSxcontent() {
		return sxcontent;
	}

	public void setSxcontent(String sxcontent) {
		this.sxcontent = sxcontent;
	}

	public String getSxblqk() {
		return sxblqk;
	}

	public void setSxblqk(String sxblqk) {
		this.sxblqk = sxblqk;
	}

	public String getSxshyj() {
		return sxshyj;
	}

	public void setSxshyj(String sxshyj) {
		this.sxshyj = sxshyj;
	}

	public String getSxlx() {
		return sxlx;
	}

	public void setSxlx(String sxlx) {
		this.sxlx = sxlx;
	}

	public String getSxhyid() {
		return sxhyid;
	}

	public void setSxhyid(String sxhyid) {
		this.sxhyid = sxhyid;
	}


	public MatterListBean getMlbean() {
		return mlbean;
	}

	public void setMlbean(MatterListBean mlbean) {
		this.mlbean = mlbean;
	}
	
	public String getSxjd() {
		return sxjd;
	}

	public void setSxjd(String sxjd) {
		this.sxjd = sxjd;
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

	public String getBlstatus() {
		return blstatus;
	}

	public void setBlstatus(String blstatus) {
		this.blstatus = blstatus;
	}

	public String getDelstatus() {
		return delstatus;
	}

	public void setDelstatus(String delstatus) {
		this.delstatus = delstatus;
	}

	public String getProcessdefid() {
		return processdefid;
	}

	public void setProcessdefid(String processdefid) {
		this.processdefid = processdefid;
	}
	
	
	


}