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
import net.itjds.fdt.metting.BpmMatterBLDAO;
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

public class BpmMatterBLAction implements Action {

	public BpmMatterBLAction() {
		
	}
	
	public String execute() throws Exception {
		
		return null;
	}
	 public String formatDate()
	    {
	        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
	        return sd.format(new Date(System.currentTimeMillis()));
	    }
	 
	public void savecjr(String hyid) {
		BpmMatterBLDAO bpmbl = new BpmMatterBLDAO();
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		uuid = (new UUID()).toString();
		conn = null;
		DAOFactory factory = null;
		conn = dbbase.getConn();
		Person person = (Person) ActionContext.getContext().getValueStack().findValue("$currPerson");
		try {
			factory = new DAOFactory(conn, bpmbl);
			factory.setDAO(bpmbl);
			bpmbl.setConnection(conn);
			bpmbl.setUuid(uuid);
			bpmbl.setHyid(hyid);
			bpmbl.setPersonid(person.getID());
			bpmbl.setPersonname(person.getName());
			bpmbl.setBldate(formatDate());
			bpmbl.create();
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
	}
	//保存事项办理人
	public String saveblr(String sxxxid,String personid,String personname){
		
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
				if(hystatus.equals("")&&hystatus!=null){
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
			uuid = (new UUID()).toString();
			 try{
		        	
		        	//conn = dbbase.getConn();
		        	factory = new DAOFactory(conn,bpmbl);
		        	factory.setDAO(bpmbl);
		        	bpmbl.setConnection(conn);
		        	bpmbl.setUuid(uuid);
		        	bpmbl.setPersonid(pids[i]);
		        	bpmbl.setPersonname(pnames[i]);
		        	bpmbl.setSxxxid(sxxxid);
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
        	BpmMatterBLDAO ba = (BpmMatterBLDAO)factory.find().get(0);
        	bpmbl = ba;
        	bpmbl.setConnection(conn);
        	bpmbl.setBldate(formatDate());
        	bpmbl.update();
        	conn.commit();
        	
        	BpmMatterInfoAction bminfoaction = new BpmMatterInfoAction();
        	bminfoaction.setUuid(sxxxid);
        	bminfoaction.setBlstatus("1");
        	bminfoaction.matterBj();
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
	
	public String getOrgtreeJson(){
		
		String treestr = "";
		MettingExtTreeDisplay mtree = new MettingExtTreeDisplay();
		 HttpServletRequest request=ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		if(sxstatus.equals("0")||sxstatus.equals("1")||sxstatus.equals("2")||sxstatus.equals("3")){
			treestr = mtree.getlbChildTree(childOrgId, true, choose,getByUserId(personId));
		}else if(sxstatus.equals("6")||sxstatus.equals("4")||sxstatus.equals("5")){//管理人员列表
			treestr = mtree.getlbChildTree(childOrgId, true, choose,getAllByUid(personId));
		}else{ //if(sxstatus.equals("2"))
			treestr = mtree.getlbChildTree(childOrgId, true, choose,getYbList(personId));
		}
		return treestr;
	}
	

	//管理人员会议列表 4：已办列表  5：历史会议  6：在办列表
	
	public List<MettingBean> getAllByUid(String personid){
		
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		List<MettingBean> mbeanlist = new ArrayList<MettingBean>();
		List mettinglist = new ArrayList();
		BpmMettingDAO bmdao = new BpmMettingDAO();
		BpmMatterInfoAction binfoaction = new BpmMatterInfoAction();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bmdao);
			
			bmdao.setCreatorId(personid);
			bmdao.setHyzt("1");
			if(sxstatus.equals("5")){//管理人员的历史会议
				bmdao.setHyzt("2");
			}
			
			mettinglist = factory.find();
			for(int i = 0;i<mettinglist.size();i++){
				BpmMettingDAO ba = (BpmMettingDAO)mettinglist.get(i);
				MettingBean mbean = new MettingBean();
				boolean b = false;
				mbean.setId(ba.getUuid());
				mbean.setName(ba.getHymc());
				List binfolist = binfoaction.getByhyjd("", ba.getUuid());//获得此会议下的所有事项 
				for(int k =0;k<binfolist.size();k++){
					MatterInfoListBean binfo = (MatterInfoListBean)binfolist.get(k);
					if(sxstatus.equals("4")){//管理人员已办列表
						if(binfo.getBlstatus().equals("2")){
							mbean = addJd(binfo,mbean);
							b = true;
						}
					}else if(sxstatus.equals("6")){
						if(!binfo.getBlstatus().equals("2")){
							mbean = addJd(binfo,mbean);
							b = true;
						}
					}else if(sxstatus.equals("5")){
						mbean = addJd(binfo,mbean);
						b = true;
					}
				}
				if(b){	
					mbeanlist.add(mbean);
				}
			}
			
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
		
		return mbeanlist;
	}

	//已办列表
	

	public List<MettingBean> getYbList(String personid) {

		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		BpmMatterBLDAO bpmbl = new BpmMatterBLDAO();
		List matterbl = new ArrayList();
		List<MettingBean> mbeanlist = new ArrayList<MettingBean>();
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bpmbl);

			bpmbl.setPersonid(personid);
			
			
			matterbl = factory.find();
			for (int i = 0; i < matterbl.size(); i++) {
				MettingBean mbean = new MettingBean();
				String bid = ((BpmMatterBLDAO) matterbl.get(i)).getSxxxid();
				BpmMatterAction matterAction = new BpmMatterAction();
				matterAction.setUuid(bid);// 根据事项id 查找事项信息表
				matterAction.setBlstatus("2");
				matterAction.matterInfoById();
				MatterInfoListBean mlistbean = matterAction
						.getMatterInfoListBean();// 事项实体
				if(mlistbean==null){//是否存在办结
					continue;
				}

				if (mbeanlist == null || mbeanlist.size() < 1) {
					BpmMettingDAO bpmmetting = new BpmMettingDAO();
					BpmMettingAction mettingaction = new BpmMettingAction();
					if(sxstatus.equals("2")){//已办列表
						mettingaction.setBlstatus("1");
					}else if(sxstatus.equals("3")){//历史会议列表
						mettingaction.setBlstatus("2");
					}
					
					bpmmetting = mettingaction.getById(mlistbean.getSxhyid());
					if(bpmmetting==null){//是否存在办结
						continue;
					}
					mbean.setId(bpmmetting.getUuid());
					mbean.setName(bpmmetting.getHymc());
					mbean = addJd(mlistbean, mbean);
					mbeanlist.add(mbean);
				} else {
					for (int j = 0; j < mbeanlist.size(); j++) {// 遍历树形会议list
						mbean = mbeanlist.get(j);
						String mid = mbeanlist.get(j).getId();

						if (mid.equals(mlistbean.getSxhyid())) {// 如果此会议已经存在
							mbean = addJd(mlistbean, mbean);
							break;
						}
						if (j == mbeanlist.size() - 1) {// 没有添加过此会议
							BpmMettingDAO bpmmetting = new BpmMettingDAO();
							BpmMettingAction mettingaction = new BpmMettingAction();
							mettingaction.setBlstatus("2");
							bpmmetting = mettingaction.getById(mlistbean.getSxhyid());
							if(bpmmetting==null){
								continue;
							}
							mbean = new MettingBean();
							mbean.setId(bpmmetting.getUuid());
							mbean.setName(bpmmetting.getHymc());

							mbean = addJd(mlistbean, mbean);
							mbeanlist.add(mbean);
							break;
						}

					}

				}
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return mbeanlist;
		} catch (Exception e) {
			e.printStackTrace();
			matterbl = null;
			return null;
		}

	}

	//办理人员列表  0：待办列表  1在办列表  2已办列表   3历史会议
	public List<MettingBean> getByUserId(String userid) {
		DBBeanBase dbbase;
		Connection conn;
		dbbase = new DBBeanBase("bpm", true);
		conn = null;
		DAOFactory factory = null;
		BpmMatterBLDAO bpmbl = new BpmMatterBLDAO();
		List matterbl = new ArrayList();
		List<MettingBean> mbeanlist = new ArrayList<MettingBean>();
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bpmbl);

			bpmbl.setPersonid(userid);
			if(sxstatus.equals("0")){
				bpmbl.markNull("bldate");
			}else if(!sxstatus.equals("3")){
				//bpmbl.addCondition("bldate", "is not null", "bldate");
			}
			
			
			matterbl = factory.find();//办理事项列表
			try
	        {
	            conn.close();
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
			for(int i=0;i<matterbl.size();i++){
				boolean isadd = true;
				MettingBean mbean = new MettingBean();
				String bid = ((BpmMatterBLDAO)matterbl.get(i)).getSxxxid();
				BpmMatterAction matterAction = new BpmMatterAction();
				matterAction.setUuid(bid);//根据事项id 查找事项信息表
				if(sxstatus.equals("1")){
					matterAction.setBlstatus("1");
				}else if(sxstatus.equals("0")){
					matterAction.setBlstatus("0");
				}else{
					matterAction.setBlstatus("2");
				} 
				
				matterAction.matterInfoById();
				MatterInfoListBean mlistbean = matterAction.getMatterInfoListBean();//事项实体
				if(mlistbean==null||mlistbean.getSxhyid()==null){
					continue;
				}	
				BpmMettingDAO bpmmetting = new BpmMettingDAO();
				BpmMettingAction mettingaction = new BpmMettingAction();
				if(sxstatus.equals("3")){//历史会议列表
					mettingaction.setBlstatus("2");
				}
				if (mbeanlist == null||mbeanlist.size()<1) {		//会议列表为空，添加第一个
					bpmmetting = mettingaction.getById(mlistbean.getSxhyid());
				} else {
					for (int j = 0; j < mbeanlist.size(); j++) {// 遍历树形会议list
						mbean = mbeanlist.get(j);
						String mid = mbeanlist.get(j).getId();
						if (mid.equals(mlistbean.getSxhyid())) {// 如果此会议已经存在
							mbean = addJd(mlistbean,mbean);
							isadd = false;  //会议存在，不必添加到mbeanlist里
							break;
						}
						if (j == mbeanlist.size() - 1) {// 没有添加过此会议
							mbean = new MettingBean();
							bpmmetting = mettingaction.getById(mlistbean.getSxhyid());
							break;
						}

					}
					
				}
				//查看历史会议时报错
				if(bpmmetting==null){
					continue;
				}
				if(isadd){
					mbean.setId(bpmmetting.getUuid());
					mbean.setName(bpmmetting.getHymc());
					mbean = addJd(mlistbean,mbean);
					mbeanlist.add(mbean);
				}
			}
			
			return mbeanlist;
		} catch (Exception e) {
			e.printStackTrace();
			matterbl = null;
			return null;
		}
		
	}
	
	public MettingBean addJd(MatterInfoListBean binfo,MettingBean mbean){
		
		
		for(int j =0;j<mbean.getChildren().size();j++){
			MettingBean childBean = mbean.getChildren().get(j);
			if(binfo.getSxssjd().equals(childBean.getName())){
				childBean.getMatterInfos().add(binfo);
				break;
			}
			if(j==mbean.getChildren().size()-1){
				childBean = new MettingBean();
				if(binfo.getSxssjd().equals("会前协调工作")){
					childBean.setId(mbean.getId()+"hqxtgz");
				}else if(binfo.getSxssjd().equals("会前准备工作")){
					childBean.setId(mbean.getId()+"hqzbgz");
				}else if(binfo.getSxssjd().equals("会议期间工作")){
					childBean.setId(mbean.getId()+"hyqjgz");
				}else if(binfo.getSxssjd().endsWith("会后工作")){
					childBean.setId(mbean.getId()+"hhgz");
				}
				childBean.setName(binfo.getSxssjd());
				childBean.getMatterInfos().add(binfo);
				mbean.getChildren().add(childBean);
				break;
			}
		}
		if(mbean.getChildren().size()==0){
			MettingBean childBean = new MettingBean();
			if(binfo.getSxssjd().equals("会前协调工作")){
				childBean.setId(mbean.getId()+"hqxtgz");
			}else if(binfo.getSxssjd().equals("会前准备工作")){
				childBean.setId(mbean.getId()+"hqzbgz");
			}else if(binfo.getSxssjd().equals("会议期间工作")){
				childBean.setId(mbean.getId()+"hyqjgz");
			}else if(binfo.getSxssjd().endsWith("会后工作")){
				childBean.setId(mbean.getId()+"hhgz");
			}
			childBean.setName(binfo.getSxssjd());
			childBean.getMatterInfos().add(binfo);
			mbean.getChildren().add(childBean);
		}
		return mbean;
	}
	
	private String uuid;
	private String sxxxid;
	
	//树属性
	private String childOrgId;

	private String choose;
	
	private String sxstatus;//列表状态
	private String hystatus = "";//会议状态
	
	

	public String getHystatus() {
		return hystatus;
	}

	public void setHystatus(String hystatus) {
		this.hystatus = hystatus;
	}

	public String getSxstatus() {
		return sxstatus;
	}

	public void setSxstatus(String sxstatus) {
		this.sxstatus = sxstatus;
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
	
	

	

	
	

}