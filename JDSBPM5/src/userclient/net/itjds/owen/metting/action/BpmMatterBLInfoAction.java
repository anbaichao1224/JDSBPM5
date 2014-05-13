/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

import java.io.File;
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
import net.itjds.fdt.metting.BlInfoListBean;
import net.itjds.fdt.metting.BpmMatterBLDAO;
import net.itjds.fdt.metting.BpmMatterBLInfoDAO;
import net.itjds.fdt.metting.BpmMatterDAO;
import net.itjds.fdt.metting.BpmMatterInfoDAO;
import net.itjds.fdt.metting.BpmMettingDAO;
import net.itjds.fdt.metting.MatterInfoListBean;
import net.itjds.fdt.metting.MatterListBean;
import net.itjds.fdt.metting.MettingBean;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.attachment.FileListBean;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.action.BPMClientBaseBinding;
import net.sf.json.JSONArray;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

// Referenced classes of package net.itjds.worklist.list.action:
//            BPMClientBaseBinding

public class BpmMatterBLInfoAction implements Action {

	public BpmMatterBLInfoAction() {
		// subCondition = null;
	}
	
	public String execute() throws Exception {
		return null;
	}
	
	public List getBySxid(String sxxxid,String personid){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		BpmMatterBLInfoDAO blinfodao = new BpmMatterBLInfoDAO();
		
		try{

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, blinfodao);
			if(!personid.equals("")){
				blinfodao.setPersonid(personid);
			}
			blinfodao.setSxxxid(sxxxid);
			blinfodao.addOrderBy("bldate", false);
			mlist = factory.find();
			addBlInfoToBeanList(); 
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
		return blinfolist;
	}
	
	public String saveInfo(){
		Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
		if(sxlx.equals("1")){
			new BpmMettingBlAction().updateBl(person.getID(), tid);
			return null;
		}
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		BpmMatterBLInfoDAO blinfodao = new BpmMatterBLInfoDAO();
		uuid = (new UUID()).toString();
		
		try{

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, blinfodao);
			//BpmMatterBLInfoDAO ba = new BpmMatterBLInfoDAO();
			
			blinfodao.setSxxxid(tid);
			blinfodao.setPersonid(person.getID());
			List blist = factory.find();
			if(liststatus.equals("0")){
				//new BpmMatterBLAction().updateBl(person.getID(),tid);
				new BpmMettingBlAction().updateBl(person.getID(), tid);
			}
			blinfodao.setConnection(conn);
			blinfodao.setUuid(uuid);
			blinfodao.setSxxxid(tid);
			blinfodao.setPersonid(person.getID());
			blinfodao.setPersonname(person.getName());
			blinfodao.setBldate(formatDate());
			blinfodao.setHfcontent(sxblqk);
			blinfodao.setCkstatus("0");
			blinfodao.create();
			
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
        return SUCCESS;
	}
	
	 public String formatDate()
	    {
	        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
	        return sd.format(new Date(System.currentTimeMillis()));
	    }
	public void addBlInfoToBeanList() throws PersonNotFoundException {
		
		blinfolist = new ArrayList();
		for (int i = 0; i < mlist.size(); i++) {
			BpmMatterBLInfoDAO ba = (BpmMatterBLInfoDAO)mlist.get(i);
			BlInfoListBean fb = new BlInfoListBean();
			fb.setUuid(ba.getUuid());
			fb.setSxxxid(ba.getSxxxid());
			fb.setPersonid(ba.getPersonid());
			fb.setPersonname(ba.getPersonname());
			fb.setBldate(ba.getBldate());
			fb.setCkstatus(ba.getCkstatus());
			fb.setHfcontent(ba.getHfcontent());
			blinfolist.add(fb);
		}
		
	}

	private String uuid;
	private String tid;
	private String sxblqk;
	private List blinfolist;
	private List mlist;
	private String liststatus = "";//会议列表状态
	private String sxlx;//事项类型，判断是否添加办理信息还是启动流程
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getSxblqk() {
		return sxblqk;
	}

	public void setSxblqk(String sxblqk) {
		this.sxblqk = sxblqk;
	}

	public List getBlinfolist() {
		return blinfolist;
	}

	public void setBlinfolist(List blinfolist) {
		this.blinfolist = blinfolist;
	}

	public List getMlist() {
		return mlist;
	}

	public void setMlist(List mlist) {
		this.mlist = mlist;
	}

	/*public String getSxstatus() {
		return sxstatus;
	}

	public void setSxstatus(String sxstatus) {
		this.sxstatus = sxstatus;
	}*/
	
	public String getSxlx() {
		return sxlx;
	}

	public String getListstatus() {
		return liststatus;
	}

	public void setListstatus(String liststatus) {
		this.liststatus = liststatus;
	}

	public void setSxlx(String sxlx) {
		this.sxlx = sxlx;
	}
	
	
	

	

	
	

}