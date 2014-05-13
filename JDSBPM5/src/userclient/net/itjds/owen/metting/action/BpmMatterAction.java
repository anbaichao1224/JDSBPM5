/*jadclipse*/// Decompiled b"y Jad" v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletResponse;

import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.query.*;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.fdt.metting.BpmMatterBLDAO;
import net.itjds.fdt.metting.BpmMatterDAO;
import net.itjds.fdt.metting.BpmMatterInfoDAO;
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
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

// Referenced classes of package net.itjds.worklist.list.action:
//            BPMClientBaseBinding

public class BpmMatterAction extends BPMActionBase {
	public String mtype="常委会议";

	private List mlist;

	public List matterBeanList;
	public List matterInfoList;
	public List blinfoList;
	
	public List activitylist;
	
	private String uuid;
	private MatterListBean mlbean;
	private String sxstatus="";//办理人访问还是管理人员访问  控制跳转到不同jsp
	private String blstatus = "";//事项状态  是否办结
	
	public BpmMatterAction() {
		// subCondition = null;
	}

	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		BpmMatterDAO bmdao = new BpmMatterDAO();

		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;

		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bmdao);
			if (getMtype() != null) {
				bmdao.setSxsslx(getMtype());
			}
			if(uuid!=null&&!uuid.equals("")){
				bmdao.setUuid(uuid);
			}
			mlist = factory.find();
			addFileToBeanList();
			JSONArray  ja = JSONArray.fromObject(matterBeanList);
			response.getWriter().write(ja.toString());
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
        if(uuid!=null&&!uuid.equals("")){
        	BpmMatterDAO bmmdao = (BpmMatterDAO)mlist.get(0);
        	matterInfoListBean = new MatterInfoListBean();
        	matterInfoListBean.setSxmc(bmmdao.getSxmc());
        	matterInfoListBean.setUuid(bmmdao.getUuid());
        	matterInfoListBean.setCreator(bmmdao.getCreator());
        	matterInfoListBean.setSxssjd(bmmdao.getSxssjd());
        	return "addmatter";
        }
		return null;
	}

	//读取常用事项列表要转换的实体
	public void addFileToBeanList() throws PersonNotFoundException {
		
		matterBeanList = new ArrayList();
		for (int i = 0; i < mlist.size(); i++) {
			matterlistbean = new MatterListBean();
			BpmMatterDAO ba = (BpmMatterDAO) mlist.get(i);
			matterlistbean.setUuid(ba.getUuid());
			matterlistbean.setSxmc(ba.getSxmc());
			matterlistbean.setSxssjd(ba.getSxssjd());
			matterlistbean.setCreator(ba.getCreator());
			matterBeanList.add(matterlistbean);
		}
		
	}
	
	//读取事项列表要转换的实体
	
public void addMatterToBeanList() throws PersonNotFoundException {
		
		matterInfoList = new ArrayList();
		for (int i = 0; i < mlist.size(); i++) {
			MatterInfoListBean fb = new MatterInfoListBean();
			BpmMatterInfoDAO ba = (BpmMatterInfoDAO) mlist.get(i);
			fb.setUuid(ba.getUuid());
			fb.setSxmc(ba.getSxmc());
			fb.setCreatedate(ba.getCreatordate());
			fb.setSxkssj(ba.getSxkssj());
			fb.setSxjssj(ba.getSxjssj());
			fb.setSxnr(ba.getSxnr());
			fb.setSxlx(ba.getSxlx());
			fb.setSxshyj(ba.getSxshyj());
			fb.setSxblqk(ba.getSxblqk());
			fb.setSxssjd(ba.getSxssjd());
			fb.setCreator(ba.getCreator());
			fb.setCreatorId(ba.getCreatorId());
			fb.setBlstatus(ba.getBlstatus());
			
			List blist = new BpmMatterBLAction().getByxxid(ba.getUuid());
			String personid = "";
			String personname = "";
			for(int j=0;j<blist.size();j++){
				BpmMatterBLDAO bpmbl = (BpmMatterBLDAO)blist.get(j);
				if(j==0){
					personid = bpmbl.getPersonid();
					personname = bpmbl.getPersonname(); 
				}else{
					personid += ","+bpmbl.getPersonid();
					personname += ","+bpmbl.getPersonname();
				}
			}
			
			fb.setPersonid(personid);
			fb.setPersonname(personname);
			matterInfoList.add(fb);
		}
		
	}

	//根据id查找事项信息，返回json ajax请求调用的方法，新增会议时修改已经填写的事项信息调用此方法
	
	public String getMatterById(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=GBK");
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMatterInfoDAO bmdao = new BpmMatterInfoDAO();
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bmdao);
			bmdao.setUuid(uuid);
			
			mlist = factory.find();
			addMatterToBeanList();
			JSONArray  ja = JSONArray.fromObject(matterInfoList);
			response.getWriter().write(ja.toString());
			
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
		return null;
	}
	
	//根据id 查找事项信息表（进行修改事项信息时调用此方法）
	
	public String matterInfoById(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=GBK");
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMatterInfoDAO bmdao = new BpmMatterInfoDAO();
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bmdao);
			bmdao.setUuid(uuid);
			bmdao.setIsdelete("N");
			if(!blstatus.equals("")&&blstatus!=null){
				bmdao.setBlstatus(blstatus);
			}
			mlist = factory.find();
			//addMatterToBeanList();
			if(mlist.size()==0){
				matterInfoListBean = null;
				return null;
			}
			matterInfoListBean = new MatterInfoListBean();
			BpmMatterInfoDAO ba = (BpmMatterInfoDAO)mlist.get(0);
			
			matterInfoListBean.setUuid(ba.getUuid());
			matterInfoListBean.setSxmc(ba.getSxmc());
			matterInfoListBean.setSxkssj(ba.getSxkssj());
			matterInfoListBean.setSxjssj(ba.getSxjssj());
			matterInfoListBean.setSxlx(ba.getSxlx());
			matterInfoListBean.setSxnr(ba.getSxnr());
			matterInfoListBean.setSxssjd(ba.getSxssjd());
			matterInfoListBean.setSxshyj(ba.getSxshyj());
			matterInfoListBean.setSxblqk(ba.getSxblqk());
			matterInfoListBean.setSxhyid(ba.getSxhyid());
			matterInfoListBean.setCreator(ba.getCreator());
			matterInfoListBean.setCreatedate(ba.getCreatordate());
			matterInfoListBean.setBlstatus(ba.getBlstatus());
			matterInfoListBean.setProcessinstid(ba.getProcessinstid());
			matterInfoListBean.setActivityinstid(ba.getActivityinstid());
			matterInfoListBean.setProcessdefid(ba.getProcessdefid());
			List blist = new BpmMatterBLAction().getByxxid(ba.getUuid());
			String personid = "";
			String personname = "";
			for(int i=0;i<blist.size();i++){
				BpmMatterBLDAO bpmbl = (BpmMatterBLDAO)blist.get(i);
				if(i==0){
					personid = bpmbl.getPersonid();
					personname = bpmbl.getPersonname(); 
				}else{
					personid += ","+bpmbl.getPersonid();
					personname += ","+bpmbl.getPersonname();
				}
			}
			matterInfoListBean.setPersonid(personid);
			matterInfoListBean.setPersonname(personname);
			
			
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
       
        if(sxstatus.equals("6")||sxstatus.equals("4")||sxstatus.equals("5")){
        	 
        	blinfoList = new BpmMatterBLInfoAction().getBySxid(matterInfoListBean.getUuid(),"");
        	return "update";
        }else if(sxstatus.equals("")){
        	return "addmatter";
        }
        
        else {//if(sxstatus.equals("")||sxstatus.equals("0")){
        	
        	Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
        	
        	blinfoList = new BpmMatterBLInfoAction().getBySxid(matterInfoListBean.getUuid(),person.getID());
        	this.activityInstId = matterInfoListBean.getActivityinstid();
        	getProcessInst();
        	
        	return "treesuccess";
        }
	//return null;
	}
	
	
	

	public String getSxstatus() {
		return sxstatus;
	}

	public void setSxstatus(String sxstatus) {
		this.sxstatus = sxstatus;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public List getMlist() {
		return mlist;
	}

	public void setMlist(List mlist) {
		this.mlist = mlist;
	}

	public List getMatterBeanList() {
		return matterBeanList;
	}

	public void setMatterBeanList(List matterBeanList) {
		this.matterBeanList = matterBeanList;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}
	public MatterListBean getMlbean() {
		return mlbean;
	}

	public void setMlbean(MatterListBean mlbean) {
		this.mlbean = mlbean;
	}

	
	private MatterInfoListBean matterInfoListBean;
	public MatterInfoListBean getMatterInfoListBean() {
		return matterInfoListBean;
	}

	public void setMatterInfoListBean(MatterInfoListBean matterInfoListBean) {
		this.matterInfoListBean = matterInfoListBean;
	}
	
	public List getBlinfoList() {
		return blinfoList;
	}

	public void setBlinfoList(List blinfoList) {
		this.blinfoList = blinfoList;
	}

	public List getMatterInfoList() {
		return matterInfoList;
	}

	public void setMatterInfoList(List matterInfoList) {
		this.matterInfoList = matterInfoList;
	}

	public String getBlstatus() {
		return blstatus;
	}

	public void setBlstatus(String blstatus) {
		this.blstatus = blstatus;
	}

	public List getActivitylist() {
		return activitylist;
	}

	public void setActivitylist(List activitylist) {
		this.activitylist = activitylist;
	}

	private MatterListBean matterlistbean;

	public MatterListBean getMatterlistbean() {
		return matterlistbean;
	}

	public void setMatterlistbean(MatterListBean matterlistbean) {
		this.matterlistbean = matterlistbean;
	}
	
	
	

}