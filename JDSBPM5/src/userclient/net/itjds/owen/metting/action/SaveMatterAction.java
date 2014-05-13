/*jadclipse*/// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

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
import net.itjds.common.org.base.Person;
import net.itjds.fdt.metting.BpmMatterDAO;
import net.itjds.fdt.metting.BpmMatterInfoDAO;
import net.itjds.fdt.metting.MatterListBean;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.worklist.list.action.BPMClientBaseBinding;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

import net.itjds.j2ee.util.UUID;

// Referenced classes of package net.itjds.worklist.list.action:
//            BPMClientBaseBinding

public class SaveMatterAction implements Action
{
	

	

	public SaveMatterAction()
    {
        //subCondition = null;
    }

    public String execute()
        throws Exception
    {
    	HttpServletResponse response = ServletActionContext.getResponse();
    	response.setContentType("text/html;charset=GBK");

    	saveMatter();
    	String result = "";
    	response.getWriter().write(infoid);
    	return null;
    }
    
    public void saveMatter(){
    	
    	
    	BpmMatterDAO bmdao = new BpmMatterDAO();
    	
    	DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        
        Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
        
        /*从sesssion获取personId
         * 
         * HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();*/
        try{
        	conn = dbbase.getConn();
			if (mstatus.equals("saveas")) {
				factory = new DAOFactory(conn, bmdao);

				factory = new DAOFactory(conn, bmdao);
				factory.setDAO(bmdao);
				bmdao.setConnection(conn);
				uuid = (new UUID()).toString();
				bmdao.setUuid(uuid);
				bmdao.setSxmc(sxmc);
				bmdao.setCreatorId(person.getID());
				bmdao.setCreator(person.getName());
				bmdao.setCreatordate(formatDate());
				bmdao.setSxsslx(sxsslx);
				bmdao.setSxssjd(sxssjd);

				bmdao.create();
				conn.commit();
				uuid = "";
			}
            
            BpmMatterInfoDAO refdao = new BpmMatterInfoDAO();
            refdao.setSxmc(sxmc);
            refdao.setSxkssj(sxkssj);
            refdao.setSxjssj(sxjssj);
            refdao.setSxnr(sxnr);
            refdao.setSxlx(sxlx);
            refdao.setSxssjd(sxssjd);
            refdao.setCreatorId(person.getID());
            refdao.setCreator(creator);//person.getName();
            refdao.setCreatordate(formatDate());
            refdao.setBlstatus("0");
            refdao.setIsdelete("N");
            refdao.setProcessdefid(processdefid);
            BpmMatterInfoAction binfoAction = new BpmMatterInfoAction();
            binfoAction.setPersonid(personid);
            binfoAction.setPersonname(personname);
            binfoAction.setUuid(sxxxid);
            infoid = binfoAction.save(refdao,uuid);  
            
            
        	
        }catch(Exception e){
        	e.printStackTrace();
        	try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
 
        
    }
    
    public String formatDate()
    {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        return sd.format(new Date(System.currentTimeMillis()));
    }
    
    private String uuid;
    private String sxxxid;//事项信息id
    private String sxlx;
    private String sxmc;
    private String creator;
    private String createDate;
    private String sxsslx;
    private String sxssjd;
    private String sxkssj;
    private String sxjssj;
    private String sxnr;
    private String mstatus;//判断是否保存，还是保存常用事项
    private String personid;
    private String personname;
    private String processdefid;//属于哪个流程
    
    
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

	public String getMstatus() {
		return mstatus;
	}

	public void setMstatus(String mstatus) {
		this.mstatus = mstatus;
	}

	private String infoid;//事项信息id 添加完成后返回uuid
    
	public String getInfoid() {
		return infoid;
	}

	public void setInfoid(String infoid) {
		this.infoid = infoid;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	

	public String getSxxxid() {
		return sxxxid;
	}

	public void setSxxxid(String sxxxid) {
		this.sxxxid = sxxxid;
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

	public String getSxmc() {
		return sxmc;
	}

	public void setSxmc(String sxmc) {
		this.sxmc = sxmc;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getSxsslx() {
		return sxsslx;
	}

	public void setSxsslx(String sxsslx) {
		this.sxsslx = sxsslx;
	}

	public String getSxssjd() {
		return sxssjd;
	}

	public void setSxssjd(String sxssjd) {
		this.sxssjd = sxssjd;
	}

	public String getProcessdefid() {
		return processdefid;
	}

	public void setProcessdefid(String processdefid) {
		this.processdefid = processdefid;
	}
 
	


}