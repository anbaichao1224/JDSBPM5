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
import net.itjds.fdt.metting.BpmMettingInstDAO;
import net.itjds.fdt.metting.BpmMettingModelDAO;
import net.itjds.fdt.metting.BpmMettingTreeDAO;
import net.itjds.fdt.metting.MatterInfoListBean;
import net.itjds.fdt.metting.MatterListBean;
import net.itjds.j2ee.dao.DAOFactory;
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

public class DelMettingAction extends BPMActionBase {

	public DelMettingAction() {
		// subCondition = null;
	}

	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try{
			if(mettingids != null && !"".equals(mettingids))
			{
				String[] fid = mettingids.split(",");
				for(int i=0;i<fid.length;i++)
				{

					deldb(fid[i]);
				}
			}

			}catch(Exception e)
			{
				e.printStackTrace();
				
			}
			return SUCCESS;
	}
	
	public void deldb(String mettingid){
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		DAOFactory factory = null;
		conn = null;
		BpmMettingTreeDAO bmdao = null;
		BpmMettingTreeDAO ba = null;
		if(optionnum==1){
			bmdao = new BpmMettingInstDAO();
			ba = new BpmMettingInstDAO();
		}else{
			bmdao = new BpmMettingModelDAO();
			ba = new BpmMettingModelDAO();
		}
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bmdao);
			bmdao.setConnection(conn);
				bmdao.setMettingid(mettingid);
	    		//bmdao.setUuid(mettingid);
				
	    		List list = (List)factory.find();
	    		if(list!=null||list.size()>0){
	    			for(int i=0;i<list.size();i++){
	    				ba = (BpmMettingTreeDAO)list.get(i);
	    				new DelFileAction().delDataFromDB(ba.getUuid());
	    			}
	    			//delaction
	    			bmdao.batchDelete();
			    	conn.commit();
	    		}
	    		if(optionnum==1){
	    			bmdao = new BpmMettingInstDAO();
	    			
	    		}else{
	    			bmdao = new BpmMettingModelDAO();
	    		}
	    		bmdao.setConnection(conn);
		    	bmdao.setParentid(mettingid);
		    	bmdao.batchDelete();
		    	conn.commit();
		    	if(optionnum==1){
					bmdao = new BpmMettingInstDAO();
					
				}else{
					bmdao = new BpmMettingModelDAO();
				}
				bmdao.setConnection(conn);
		    	bmdao.setUuid(mettingid);
		    	bmdao.batchDelete();
		    	conn.commit();
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
	}
	
	private String mettingids;
	private int optionnum;
	public String getMettingids() {
		return mettingids;
	}

	public void setMettingids(String mettingids) {
		this.mettingids = mettingids;
	}

	public int getOptionnum() {
		return optionnum;
	}

	public void setOptionnum(int optionnum) {
		this.optionnum = optionnum;
	}
	
	

}