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
import net.itjds.fdt.metting.BpmMettingInstDAO;
import net.itjds.fdt.metting.MatterInfoListBean;
import net.itjds.fdt.metting.MatterListBean;
import net.itjds.fdt.metting.MettingBean;
import net.itjds.fdt.metting.MettingBeanTree;
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

public class BpmMettingTreeShowAction implements Action {

	public BpmMettingTreeShowAction() {
		// subCondition = null;
	}
	
	public String execute() throws Exception {
		return null;
	}
	//����״̬�»����б�չʾ
	public String mtreelist(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest request=ServletActionContext.getRequest();
		 HttpSession session = request.getSession(true);
		 String personId = session.getAttribute("personId").toString();
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        BpmMettingInstDAO bminstdao = new BpmMettingInstDAO();
        try{
        	
        	conn = dbbase.getConn();
        	factory = new DAOFactory(conn, bminstdao);
			factory.setDAO(bminstdao);
			String wheresql = "";
			int lstatus = Integer.parseInt(liststatus);
			if(lstatus>=0&&lstatus<4){
				//ͨ�������Ĳ鵽���˾��еĻ���id
				int blstatus = 0;
				wheresql +=" uuid in (select  distinct METTING_ID from fdt_oa_matterbl where PERSONID='"+personId+"'";
				if(lstatus==0){
					wheresql += " and bldate is null) and blstatus!='2' and isopen='��'";
					blstatus = 1;
				}else if(lstatus==1){
					//wheresql += " and bldate is not null) and blstatus!='2'  and isopen='��'";
					wheresql += " and blstatus!='2') and blstatus!='2'  and isopen='��'";
				}else if(lstatus==2){
					wheresql += " and blstatus='2') and blstatus!='2'  and isopen='��'";
				}
				if(lstatus==3){
					wheresql += ") and blstatus='2'  and isopen='��'";
				}
			}else{
				//���ǹ�����Ա�б����ݴ�����id������״̬���Ҵ����������Ļ���
				wheresql += " creatorid='"+personId+"' and parentid is null and isopen='��'";
				if(lstatus==6||lstatus==4){
					wheresql +=" and blstatus!='2'";
				}else if(lstatus==5){
					wheresql +=" and blstatus='2'";
				}else{
					wheresql ="  creatorid='"+personId+"' and parentid is null and isopen='��'";
				}
			}
			bminstdao.setWhereClause(wheresql);
			bminstdao.addOrderBy("createdate", false);
			List list = factory.find();
			try
	        {
	            conn.close();
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
	        totalCount = list.size();
	        int count = start + limit;
	        bdList = new ArrayList();
			if(count > totalCount){
				bdList = list.subList(start, totalCount);
			}else{
				bdList = list.subList(start, count);
			}
	        mlist = new ArrayList<MettingBeanTree>();
	        for(int i=0;i<bdList.size();i++){
	        	BpmMettingInstDAO ba = (BpmMettingInstDAO)bdList.get(i);
	        	MettingBeanTree mbtree = new BpmTreeMettingAction().zh(ba,false);
	        	mlist.add(mbtree);
	        }
        }catch(Exception e){
        	e.printStackTrace();
        }
        return "success";
	}
	
	
	
	
	private String liststatus;//�б�״̬ �ڰ�or�Ѱ��״̬
	private List<MettingBeanTree> mlist;//��listתΪjson�ַ���
	//��ҳ�����ֶ�
	private int totalCount;
	List bdList;
	
	private int start;
	private int limit;

	

	public String getListstatus() {
		return liststatus;
	}

	public void setListstatus(String liststatus) {
		this.liststatus = liststatus;
	}

	public List<MettingBeanTree> getMlist() {
		return mlist;
	}

	public void setMlist(List<MettingBeanTree> mlist) {
		this.mlist = mlist;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
	
	
	

	

	
	

}