/*jadclipse*/// Decompiled b"y Jad" v1.5.8g. Copyright 2001 Pavel Kouznetsov.

package net.itjds.owen.metting.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import net.itjds.fdt.metting.MettingBeanTree;
import net.itjds.fdt.metting.MettingTypeBean;
import net.itjds.j2ee.dao.DAO;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.owen.metting.attachment.BpmMAttachmentDAO;
import net.itjds.owen.metting.attachment.DelFileAction;
import net.itjds.owen.metting.attachment.FileListAction;
import net.itjds.owen.metting.attachment.UploadAttachmentAction;
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

public class BpmTreeMettingAction extends BPMActionBase {
	
	public BpmTreeMettingAction() {
		
	}
	//模板列表
	public String execute() throws Exception {
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        BpmMettingModelDAO bpmmmodel = new BpmMettingModelDAO();
        String returnstr = "";
        try{
        	
        	conn = dbbase.getConn();
        	factory = new DAOFactory(conn,bpmmmodel);
        	factory.setDAO(bpmmmodel);
        	bpmmmodel.setWhereClause(" PARENTID IS NULL AND ISDELETE='N' ");
        	bpmmmodel.addOrderBy("createdate", false);
        	List list = factory.find();//所有根节点
        	modellist = new ArrayList();
        	for(int i=0;i<list.size();i++){
        		MettingBeanTree mbtree = zh((BpmMettingModelDAO)list.get(i),true);
        		modellist.add(mbtree);
        	}
        	totalCount = modellist.size();
        	
        	if(adddirection==0){
        		returnstr = "addmettinglist";
        	}else{
        		returnstr = "modellist";
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
		return returnstr;
	}
	//指定会议下所有内容
	public String mjsontree(boolean iszh){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        //uuid = (new UUID()).toString();
        conn = null;
        DAOFactory factory = null;
        
        BpmMettingTreeDAO bpmmmodel = null; //= new BpmMettingTreeDAO();
        Integer lstatus = 0;
        if(!liststatus.equals("")){
        	lstatus = Integer.parseInt(liststatus);
        	bpmmmodel = new BpmMettingInstDAO();
        }else{
        	bpmmmodel = new BpmMettingModelDAO();
        }
        try{
        	
        	conn = dbbase.getConn();
        	factory = new DAOFactory(conn,bpmmmodel);
        	factory.setDAO(bpmmmodel);
        	String mettingwhere = " PARENTID IS NULL ";
        	if(mettinguuid!=null||!mettinguuid.equals("")){//如果mettinguui有值则代表查看某一会议，若没有则是查看自己的列表
        		bpmmmodel.setUuid(mettinguuid);
        		mettingwhere += " and UUID='"+mettinguuid+"'";
        	}
        	if(lstatus==5||lstatus==3){
        		mettingwhere += " and blstatus='2'";
        	}else if(!liststatus.equals("null")){
        		mettingwhere += " and blstatus!='2'";
        	}
        	bpmmmodel.setWhereClause(mettingwhere);
        	List list = factory.find();//所有根节点
        	try
            {
                conn.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
        	
            mbtreelist = addNode(list,null,iszh);//整个tree list
            HttpServletRequest request=ServletActionContext.getRequest();
   		 HttpSession session = request.getSession(true);
   		 session.setAttribute(mettinguuid, mbtreelist);
        	JSONArray  ja = JSONArray.fromObject(mbtreelist);
			response.getWriter().write(ja.toString());
        	
        }catch(Exception e){
        	e.printStackTrace();
        }
        
        return null;
	}
	
	
	
	public List addNode(List<BpmMettingTreeDAO> list,MettingBeanTree treebean,boolean iszh){
		List clist = new ArrayList();//存放childbean
		for(int i=0;i<list.size();i++){
			MettingBeanTree mbtree = zh(list.get(i),iszh);
			boolean flagleaf = false;
			if(list.get(i).getParentid()!=null){
				flagleaf = true;
			}
			List<BpmMettingTreeDAO> childlist = getByParent(list.get(i).getUuid(),flagleaf);//获取该节点下的childbean
			if(childlist.size()>0&&childlist!=null){
				mbtree.setLeaf(false);
				addNode(childlist,mbtree,iszh);
			}else{
				mbtree.setLeaf(true);
			}
			clist.add(mbtree);
		}
		if(treebean!=null){
			treebean.setChildren(clist);
		}
			return clist;
	}
	
	//根据父id查找
	
	public List<BpmMettingTreeDAO> getByParent(String pid,boolean flagleaf){
		HttpServletRequest request=ServletActionContext.getRequest();
		 HttpSession session = request.getSession(true);
		 String personId = session.getAttribute("personId").toString();
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        //uuid = (new UUID()).toString();
        conn = null;
        DAOFactory factory = null;
        BpmMettingTreeDAO bpmmmodel = null;
        Integer lstatus = null;
        if(!liststatus.equals("")){
        	lstatus = Integer.parseInt(liststatus);
        	bpmmmodel = new BpmMettingInstDAO();
        }else{
        	bpmmmodel = new BpmMettingModelDAO();
        }
       
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bpmmmodel);
			factory.setDAO(bpmmmodel);
			bpmmmodel.setParentid(pid);
			bpmmmodel.setIsdelete("N");
			if(lstatus!=null&&lstatus>=0&&lstatus<4){
				if (lstatus == 3) {
					bpmmmodel.setWhereClause(" parentid='" + pid
							+ "'");
					if(flagleaf){
						bpmmmodel.setWhereClause(" parentid='" + pid
								+ "' and BLRID LIKE '%" + personId + "%'");
					}
				} else {
					String where = "";
					String blstatuswhere = " and blstatus!='2' and isdelete='N' ";
					if (lstatus == 0) {
						where = " bldate is null";
					} else {
						where = " bldate is not null";
						if(lstatus==2){
							blstatuswhere = " and blstatus='2' and isdelete='N' ";
						}
					}
					
					bpmmmodel.setWhereClause(" uuid in (select distinct parent_id from fdt_oa_matterbl where metting_id='"+mettinguuid+"' and personid='"+personId+"' and "+where+")");
					if(flagleaf){
						bpmmmodel.setWhereClause(" uuid in (select sxxxid from fdt_oa_matterbl where parent_id='"+pid+"' and personid='"+personId+"' and "+where+")"+blstatuswhere);
					}
				}
			}else if(lstatus!=null&&(lstatus==4||lstatus==6)){
				String where = "";
			}
			bpmmmodel.addOrderBy("createdate", false);
			List<BpmMettingTreeDAO> list = factory.find();
			try
	        {
	            conn.close();
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	//根据主键查找 
	public String getById(){
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        //uuid = (new UUID()).toString();
        conn = null;
        DAOFactory factory = null;
        BpmMettingModelDAO bpmmmodel = new BpmMettingModelDAO();
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bpmmmodel);
			factory.setDAO(bpmmmodel);
			bpmmmodel.setUuid(mettinguuid);
			BpmMettingModelDAO bpmmdao = (BpmMettingModelDAO)factory.findByPrimaryKey();
			mettingbean = zh(bpmmdao,false);
			//return bpmmdao;
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
		return "matterjsp";
	}
	
	
	public MettingBeanTree getInstById(int daoinst){
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        //uuid = (new UUID()).toString();
        conn = null;
        DAOFactory factory = null;
        //BpmMettingInstDAO bpmmmodel = new BpmMettingInstDAO();
        BpmMettingTreeDAO bpmmmodel = null;
        if(daoinst==1){
        	bpmmmodel = new BpmMettingInstDAO();
        }else{
        	bpmmmodel = new BpmMettingModelDAO();
        }
        MettingBeanTree mbean = null;
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bpmmmodel);
			factory.setDAO(bpmmmodel);
			bpmmmodel.setUuid(tid);
			BpmMettingTreeDAO bpmmdao = (BpmMettingTreeDAO)factory.findByPrimaryKey();
			if(bpmmdao!=null){
				mbean = zh(bpmmdao,false);
			}
			//return bpmmdao;
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
		return mbean;
	}
	//查询指定会议、阶段、事项实体
	public String getFromSession(){
		HttpServletRequest request=ServletActionContext.getRequest();
		 HttpSession session = request.getSession(true);
		 String personId = session.getAttribute("personId").toString();
		 if(liststatus.equals("")){
			 mettingbean = getInstById(2);
			 if(adddirection==3||adddirection==1){//修改会议和阶段所指向的jsp//(lstatus==10){原来的
				 mtbeanlist = new BpmMettingTypeAction().findAll();
				 return "createmetting";
			 }else{
				 return "matterjsp";
			 }
		 }else{
			 int lstatus = Integer.parseInt(liststatus);
			 if(lstatus>=0&&lstatus<4){
				 mettingbean = getInstById(1);
				 if(adddirection==3||adddirection==1){//(lstatus==10){
					 //mettingbean = getInstById();
					 mtbeanlist = new BpmMettingTypeAction().findAll();
					 count = getMatterByMid(mettingbean.getTid()).size();
					 return "updatemetting";
				 }else{
					 if(mettingbean.getSxlx().equals("1")){
						this.activityInstId = mettingbean.getActivityinstid();
				        getProcessInst();
					 }else{
						 blinfoList = new BpmMatterBLInfoAction().getBySxid(mettingbean.getTid(),personId);
						 
					 }
					 return "blmatter";
				 }
				 
			 }else if(lstatus>=4&&lstatus<7){
				 mettingbean = getInstById(1);
				 
				 if(adddirection==3||adddirection==1){//(lstatus==10){
					 mtbeanlist = new BpmMettingTypeAction().findAll();
					 count = getMatterByMid(mettingbean.getTid()).size();
					 return "updatemetting";
				 }else{
					 blinfoList = new BpmMatterBLInfoAction().getBySxid(mettingbean.getTid(),"");
					 return "updatematter";
				 }
				
			 }else if(lstatus==10||lstatus==7){
				 mettingbean = getInstById(1);
				 if(adddirection==3||adddirection==1){//修改会议和阶段所指向的jsp//(lstatus==10){原来的
					 mtbeanlist = new BpmMettingTypeAction().findAll();
					 return "createmetting";
				 }else{
					 return "matterjsp";
				 }
			 }else if(lstatus==101){
				 mettingbean = getInstById(1);
				 count = getMatterByMid(mettingbean.getTid()).size();
				 return "updatemetting";
			 }else{
				 return null;
			 }
		 
		 }
		
	}
	
	//通过会议id查找未办结的事项
	public List getMatterByMid(String mettingid){
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        //uuid = (new UUID()).toString();
        conn = null;
        DAOFactory factory = null;
        BpmMettingInstDAO bpmmmodel = new BpmMettingInstDAO();
        MettingBeanTree mbean = null;
        List list = new ArrayList();
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, bpmmmodel);
			factory.setDAO(bpmmmodel);
			bpmmmodel.setWhereClause(" blstatus!='2' and metting_id='"+mettingid+"'");
			list = factory.find();
			//return bpmmdao;
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
		return list;
		
	}
	
	
	
	public MettingBeanTree getById(List<MettingBeanTree> clist,String pid){
		//String pid = "";
		for(int i=0;i<clist.size();i++){
			MettingBeanTree treebean = clist.get(i);
			if(treebean.getTid().equals(tid)){
				mettingbean = treebean;
				parentid = pid;
				break;
			}
			if(treebean.getChildren().size()>0){
				//pid = treebean.getTid();
				getById(treebean.getChildren(),treebean.getTid());
			}
		}
		return mettingbean;
	}
	//数据库实体和正常实体转换
	public MettingBeanTree zh(BpmMettingTreeDAO ba,boolean iszh){
		MettingBeanTree mbtree = new MettingBeanTree();
		mbtree.setTid(ba.getUuid());
		mbtree.setName(ba.getXxmc());
		mbtree.setKssj(ba.getKssj());
		mbtree.setJssj(ba.getJssj());
		mbtree.setBlrid(ba.getBlrid());
		mbtree.setBlrmc(ba.getBlrmc());
		mbtree.setSxnr(ba.getSxnr());
		mbtree.setCreator(ba.getCreator());
		mbtree.setCreatedate(ba.getCreatedate());
		mbtree.setCreatorid(ba.getCreatorid());
		mbtree.setBlstatus(ba.getBlstatus());
		mbtree.setSxlx(ba.getSxlx());
		mbtree.setHylxid(ba.getMtypeid());
		mbtree.setIsopen(ba.getIsopen());
		if(ba.getIsopen()==null){
			mbtree.setIsopen("否");
		}
		mbtree.setProcessdefid(ba.getProcessdefid());
		mbtree.setProcessinstid(ba.getProcessinstid());
		mbtree.setActivityinstid(ba.getActivityinstid());
		mbtree.setMettingid(ba.getMettingid());
		
		String zxblqk = "";
		String dis = "";
		String titleurl = "";
		if (liststatus != null&&iszh) {
			if (ba.getParentid() == null) {
				titleurl = "<a href=javascript:void(0) onclick=\"updateMatter('treemetting_getFromSession.action?adddirection=3&liststatus='+liststatus+'&mettinguuid='+mid+'&tid="
						+ ba.getUuid() + "')\">" + mbtree.getName() + "</a>";
			} else if (ba.getMettingid() != null) {
				titleurl = "<a href=javascript:void(0) onclick=\"updateMatter('treemetting_getFromSession.action?adddirection=2&liststatus='+liststatus+'&mettinguuid='+mid+'&tid="
						+ ba.getUuid() + "')\">" + mbtree.getName() + "</a>";
			} else {
				titleurl = "<a href=javascript:void(0) onclick=\"updateMatter('treemetting_getFromSession.action?adddirection=1&liststatus='+liststatus+'&mettinguuid='+mid+'&tid="
						+ ba.getUuid() + "')\">" + mbtree.getName() + "</a>";
			}
			mbtree.setName(titleurl);
		}
		if(liststatus==null||liststatus.equals("")||liststatus.equals("10")||liststatus.equals("7")){
			dis = "disabled='disabled'";
		}
		if (ba.getSxlx()!= null) {
			if (ba.getSxlx().equals("1")) {
				zxblqk = "<input type=button value='查看办理情况' style='width:100;height:20' onclick=\"window.top.prodetail('"
						+ ba.getProcessinstid() + "','" + ba.getActivityinstid() + "')\" "+dis+"/>";
			} else {
				zxblqk = "<input type=button value='查看办理情况'  style='width:100;height:20' onclick=\"updateMatter('treemetting_getFromSession.action?adddirection=2&liststatus='+liststatus+'&mettinguuid='+mid+'&tid="+ba.getUuid()+"')\" "+dis+">";
			}
		}
		mbtree.setZxblqk(zxblqk);
		String webblstatus = "";
		if(ba.getParentid()==null){
			if(ba.getBlstatus().equals("0")){
				webblstatus = "未办结";
			}else{
				webblstatus = "办结";
			}
		}
		if(ba.getMettingid()!=null){
			if(ba.getBlstatus().equals("0")){
				webblstatus = "未办理";
			}else if(ba.getBlstatus().equals("1")){
				webblstatus = "办理中";
			}else{
				webblstatus = "办结";
			}
		}
		mbtree.setWebblstatus(webblstatus);
		return mbtree;
	}
	public String savemetting() throws DAOException{
		
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        
		 HttpServletRequest request=ServletActionContext.getRequest();
		 HttpSession session = request.getSession(true);
		 List mlist = (List)session.getAttribute(mettinguuid);//之前从session读取改为一下方法
		 conn = dbbase.getConn();
		 fjlist(conn,null,mlist);
		 
		 try
	        {
			 	//conn.commit();
	            conn.close();
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
			return "savesuccess";
	}
	
	//整个会议新增到数据库
	public void fjlist(Connection conn,String fmid,List<MettingBeanTree> clist) throws DAOException{
		
		
		for (int i = 0; i < clist.size(); i++) {
			MettingBeanTree mbtree = (MettingBeanTree) clist.get(i);
			BpmMettingTreeDAO bmmdao = null;
			if(optionnum==1){
				
				bmmdao = new BpmMettingInstDAO();
			}else{
				
				bmmdao = new BpmMettingModelDAO();
			}
				DAOFactory factory = new DAOFactory(conn, bmmdao);
				factory.setDAO(bmmdao);
				bmmdao.setConnection(conn);
				bmmdao.setUuid(new UUID().toString());
				bmmdao.setXxmc(mbtree.getName());
				if(fmid==null){
					parentid = bmmdao.getUuid();
					xxname = bmmdao.getXxmc();
					mettinguuid = bmmdao.getUuid();
					bmmdao.setIsopen(mbtree.getIsopen());
					if(mbtree.getIsopen()==null){
						bmmdao.setIsopen("否");
					}
				}
				bmmdao.setKssj(mbtree.getKssj());
				bmmdao.setJssj(mbtree.getJssj());
				bmmdao.setParentid(fmid);
				bmmdao.setBlrid(mbtree.getBlrid());
				bmmdao.setBlrmc(mbtree.getBlrmc());
				bmmdao.setCreatedate(mbtree.getCreatedate());
				bmmdao.setCreator(mbtree.getCreator());
				bmmdao.setCreatorid(mbtree.getCreatorid());
				bmmdao.setBlstatus("0");
				bmmdao.setIsdelete("N");
				bmmdao.setSxlx(mbtree.getSxlx());
				bmmdao.setSxnr(mbtree.getSxnr());
				bmmdao.setMtypeid(mbtree.getHylxid());
				
				
				bmmdao.setProcessdefid(mbtree.getProcessdefid());
				if(mbtree.isLeaf()){
					if(!fmid.equals(parentid)){
						bmmdao.setMettingid(parentid);
						bmmdao.setMettingname(xxname);
					}
				}
				if(optionnum==3){
					tid = mbtree.getTid();
					if(getInstById(2)!=null){
						bmmdao.setUuid(tid);
						bmmdao.update();
					}else{
						bmmdao.setUuid(new UUID().toString());
						bmmdao.create();
					}
				}else{
					bmmdao.create();
				}
				 try
			        {
					 	conn.commit();
			            //conn.close();
			        }
			        catch(SQLException e)
			        {
			            e.printStackTrace();
			        }
			        if(mbtree.isLeaf()){
			        	if(!fmid.equals(parentid)){
			        		new BpmMettingBlAction().saveblr(bmmdao.getUuid(), mbtree.getBlrid(),mbtree.getBlrmc(),parentid,fmid);
			        		//new DelFileAction().updateBysxxid(mbtree.getTid(), bmmdao.getUuid());
			        		//if(optionnum==1){
			        			FileListAction flistaction = new FileListAction();
			        			flistaction.setSxxxid(mbtree.getTid());
			        			List flist = flistaction.getAllFile();
			        			for(int j=0;j<flist.size();j++){
			        				BpmMAttachmentDAO ba = (BpmMAttachmentDAO)flist.get(j);
			        				new UploadAttachmentAction().saveFileInfo(ba.getFilepath(), bmmdao.getUuid(), ba.getFilename());
			        			}
			        		//}
			        	}
			        }
			if (mbtree.getChildren().size() > 0) {
				fjlist(conn, bmmdao.getUuid(), mbtree.getChildren());
			}
		}
	}
	//会议下单个事项或是阶段进行添加
	public String savesinglmetting(){
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        
		 HttpServletRequest request=ServletActionContext.getRequest();
		 HttpSession session = request.getSession(true);
		 String personId = session.getAttribute("personId").toString();
		 //List mlist = (List)session.getAttribute(mettinguuid);//之前从session读取改为一下方法
		 conn = dbbase.getConn();
		 BpmMettingTreeDAO bmmdao = null;
		 BpmMettingTreeDAO ba = null;
		 try {
			if(optionnum==1){
				
				bmmdao = new BpmMettingInstDAO();
				ba = new BpmMettingInstDAO();
			}else{
				bmmdao = new BpmMettingModelDAO();
				ba = new BpmMettingModelDAO();
			}
			factory = new DAOFactory(conn,bmmdao);
			bmmdao.setUuid(tid);
			List mlist;
			
				mlist = (List)factory.find();
				factory = new DAOFactory(conn,ba);
			
				if(mlist!=null&&mlist.size()>0){
					ba = (BpmMettingTreeDAO)mlist.get(0);
				}else{
					if(adddirection==1){
						ba.setParentid(mettinguuid);
					}else if(adddirection==2){
						ba.setParentid(parentid);
						ba.setMettingid(mettinguuid);
					}
					ba.setUuid(new UUID().toString());
				}
				if(adddirection==0){
					ba.setUuid(mettinguuid);
				}
				ba.setConnection(conn);
				ba.setXxmc(xxname);
				ba.setKssj(kssj);
				ba.setJssj(jssj);
				ba.setBlrid(personid);
				ba.setBlrmc(personname);
				ba.setCreatedate(createdate);
				ba.setCreator(creator);
				ba.setCreatorid(personId);
				ba.setBlstatus("0");
				ba.setIsdelete("N");
				ba.setSxlx(sxlx);
				ba.setSxnr(sxnr);
				ba.setMtypeid(hylxid);
				ba.setProcessdefid(processdefid);
				ba.setIsopen(isopen);
				if(adddirection==2){
					ba.setMettingid(mettinguuid);
					new BpmMettingBlAction().saveblr(ba.getUuid(), personid,personname,mettinguuid,ba.getParentid());
	        		new DelFileAction().updateBysxxid(tid, ba.getUuid());
				}
				if(mlist==null||mlist.size()==0){
					ba.create();
				}else{
					ba.update();
				}
				conn.commit();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		 
		 try
	        {
			 	//conn.commit();
	            conn.close();
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
			return null;
	}
	
	
	//查询指定会议
	public String querymodel(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest request=ServletActionContext.getRequest();
		if(liststatus==null){
			
			return "addMetting";
		}
		if(liststatus.equals("10")){
			if(mettinguuid==null){//新增
				return "addMetting";
			}
			mjsontree(true);
			if(mbtreelist==null||mbtreelist.size()==0){//选模板新增会议
				modelid = mettinguuid;
				liststatus = "";
				mjsontree(false);
				liststatus = "10";
				optionnum = 1;
				try {
					savemetting();
					
				} catch (DAOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				mjsontree(true);
			}
		}
		
		return "addMetting";
	}
	//会议列表
	public String treelist(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest request=ServletActionContext.getRequest();
		mjsontree(true);
		JSONArray  ja = JSONArray.fromObject(mbtreelist);
		jsonstrm = ja.toString();
		request.setAttribute("jsonstrm", jsonstrm);
		return "openwin";
	}
	//新增会议时，保存为模板
	public String savemodelbymetting() throws DAOException{
		
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        
		 HttpServletRequest request=ServletActionContext.getRequest();
		 HttpSession session = request.getSession(true);
		 conn = dbbase.getConn();
		 if(mbtreelist==null||mbtreelist.size()==0){
			 mjsontree(false);
		 }
		 fjlist(conn,null,mbtreelist);
		 
		 try
	        {
			 	//conn.commit();
	            conn.close();
	        }
	        catch(SQLException e)
	        {
	            e.printStackTrace();
	        }
			return null;
	}
	private String mettinguuid;//会议id
	private String parentid;//父id
	private String tid;//当前类id可能从 新增页面传过来的
	private String xxname;//项目名称
	private String kssj;
	private String jssj;
	private String hylxid;
	private String sxlx;
	private String creator;
	private String creatorid;
	private String createdate;
	private String personid;
	private String personname;
	private String sxnr;
	private String processdefid;
	private String isopen;
	private int adddirection = 0;//添加位置（添加阶段还是添加事项）
	private int optionnum;//1为保持会议2为保存模板 3为保存   操作类型
	private String liststatus = null;//列表状态
	private List modellist;//模板列表
	private List mbtreelist;//会议列表
	private int count;//未办结的事项个数
	private String modelid;//模板id,新增会议时，可能保存为模板
	private MettingBeanTree mettingbean;
	private List<MettingTypeBean> mtbeanlist;
	private List blinfoList;
	private int totalCount;//分页
	String jsonstrm;
	
	
	public String getJsonstrm() {
		return jsonstrm;
	}
	public void setJsonstrm(String jsonstrm) {
		this.jsonstrm = jsonstrm;
	}
	
	public String getMettinguuid() {
		return mettinguuid;
	}
	public void setMettinguuid(String mettinguuid) {
		this.mettinguuid = mettinguuid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getXxname() {
		return xxname;
	}
	public void setXxname(String xxname) {
		this.xxname = xxname;
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
	public String getHylxid() {
		return hylxid;
	}
	public void setHylxid(String hylxid) {
		this.hylxid = hylxid;
	}
	
	public String getSxlx() {
		return sxlx;
	}
	public void setSxlx(String sxlx) {
		this.sxlx = sxlx;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreatorid() {
		return creatorid;
	}
	public void setCreatorid(String creatorid) {
		this.creatorid = creatorid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public int getAdddirection() {
		return adddirection;
	}
	public void setAdddirection(int adddirection) {
		this.adddirection = adddirection;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
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
	public String getSxnr() {
		return sxnr;
	}
	public void setSxnr(String sxnr) {
		this.sxnr = sxnr;
	}
	
	public String getProcessdefid() {
		return processdefid;
	}
	public void setProcessdefid(String processdefid) {
		this.processdefid = processdefid;
	}
	
	public String getIsopen() {
		return isopen;
	}
	public void setIsopen(String isopen) {
		this.isopen = isopen;
	}
	public String getModelid() {
		return modelid;
	}
	public void setModelid(String modelid) {
		this.modelid = modelid;
	}
	public int getOptionnum() {
		return optionnum;
	}
	public void setOptionnum(int optionnum) {
		this.optionnum = optionnum;
	}
	
	public List getModellist() {
		return modellist;
	}
	public void setModellist(List modellist) {
		this.modellist = modellist;
	}
	public List getMbtreelist() {
		return mbtreelist;
	}
	public void setMbtreelist(List mbtreelist) {
		this.mbtreelist = mbtreelist;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public MettingBeanTree getMettingbean() {
		return mettingbean;
	}
	public void setMettingbean(MettingBeanTree mettingbean) {
		this.mettingbean = mettingbean;
	}
	public String getListstatus() {
		return liststatus;
	}
	public void setListstatus(String liststatus) {
		this.liststatus = liststatus;
	}
	public List getBlinfoList() {
		return blinfoList;
	}
	public void setBlinfoList(List blinfoList) {
		this.blinfoList = blinfoList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public List<MettingTypeBean> getMtbeanlist() {
		return mtbeanlist;
	}
	public void setMtbeanlist(List<MettingTypeBean> mtbeanlist) {
		this.mtbeanlist = mtbeanlist;
	}
	
	
	
	
	
	
	
}