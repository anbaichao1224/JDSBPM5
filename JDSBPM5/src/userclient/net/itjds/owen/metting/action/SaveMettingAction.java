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
import net.itjds.fdt.metting.BpmMatterBLDAO;
import net.itjds.fdt.metting.BpmMatterDAO;
import net.itjds.fdt.metting.BpmMatterInfoDAO;
import net.itjds.fdt.metting.BpmMettingDAO;
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

public class SaveMettingAction implements Action
{
	

	

	public SaveMettingAction()
    {
        //subCondition = null;
    }

    public String execute()
        throws Exception
    {
    	HttpServletResponse response = ServletActionContext.getResponse();
    	response.setContentType("text/html;charset=GBK");
    	String result = "";
    	
    	return null;
    }
    
    public String saveMetting(){
    	BpmMettingDAO bmdao = new BpmMettingDAO();
    	mettinguuid = (new UUID()).toString();
    	DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        
        
       /*从session中获取personId
        * 
        *  HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();*/
        
        Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
        try{
        	
        	conn = dbbase.getConn();
				factory = new DAOFactory(conn, bmdao);

				factory = new DAOFactory(conn, bmdao);
				factory.setDAO(bmdao);
				bmdao.setConnection(conn);
				
				bmdao.setUuid(mettinguuid);
				bmdao.setHymc(hymc);
				bmdao.setHykssj(hykssj);
				bmdao.setHyjssj(hyjssj);
				bmdao.setHylx(hylx);
				bmdao.setCreatorId(person.getID());
				bmdao.setCreator(creator);//person.getName()
				bmdao.setCreatedate(createdate);
				bmdao.setIsdelete("N");
				bmdao.setHyzt("1");
				bmdao.create();
				conn.commit();
	            String[] mids = matterids.split(",");
	            conn = dbbase.getConn();
	            for(int i=0;i<mids.length;i++){
	            	//new BpmMatterInfoAction().updatehyid(mids[i],mettinguuid);
	            	BpmMatterInfoDAO ba = new BpmMatterInfoDAO();
	            	factory = new DAOFactory(conn, ba);
	    			ba.setUuid(mids[i]);
	    			List mlist = factory.find();
	    			BpmMatterInfoDAO fb = (BpmMatterInfoDAO)mlist.get(0);
	            	
	            	factory = new DAOFactory(conn,ba);
	            	factory = new DAOFactory(conn,ba);
	            	factory.setDAO(ba);
	            	
	            	
	            	//ba.setUuid(mids[i]);
	            	ba.setSxhyid(mettinguuid);
	            	ba = fb;
	            	ba.setConnection(conn);
	            	ba.setSxhyid(mettinguuid);
	                
	            	ba.update();
	            }
	            BpmMatterBLAction bblaction = new BpmMatterBLAction();
	            bblaction.savecjr(mettinguuid);
				conn.commit();
        	
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
        return SUCCESS;
        
    }
    
    public String formatDate()
    {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        return sd.format(new Date(System.currentTimeMillis()));
    }
    
    private String mettinguuid;
    private String hymc;
    private String hykssj;
    private String hyjssj;
    private String hylx;
    private String createdate;
    private String creator;
    private String hyzt;
    private String matterids;
	public String getMatterids() {
		return matterids;
	}

	public void setMatterids(String matterids) {
		this.matterids = matterids;
	}

	

	public String getMettinguuid() {
		return mettinguuid;
	}

	public void setMettinguuid(String mettinguuid) {
		this.mettinguuid = mettinguuid;
	}

	public String getHymc() {
		return hymc;
	}

	public void setHymc(String hymc) {
		this.hymc = hymc;
	}

	public String getHykssj() {
		return hykssj;
	}

	public void setHykssj(String hykssj) {
		this.hykssj = hykssj;
	}

	public String getHyjssj() {
		return hyjssj;
	}

	public void setHyjssj(String hyjssj) {
		this.hyjssj = hyjssj;
	}

	public String getHylx() {
		return hylx;
	}

	public void setHylx(String hylx) {
		this.hylx = hylx;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getHyzt() {
		return hyzt;
	}

	public void setHyzt(String hyzt) {
		this.hyzt = hyzt;
	}
    
    


}