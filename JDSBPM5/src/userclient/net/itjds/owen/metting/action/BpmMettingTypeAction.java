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
import net.itjds.fdt.metting.BpmMettingTypeDAO;
import net.itjds.fdt.metting.MatterInfoListBean;
import net.itjds.fdt.metting.MatterListBean;
import net.itjds.fdt.metting.MettingTypeBean;
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
import net.itjds.j2ee.util.UUID;
// Referenced classes of package net.itjds.worklist.list.action:
//            BPMClientBaseBinding

public class BpmMettingTypeAction extends BPMActionBase {
	
	public BpmMettingTypeAction() {
		// subCondition = null;
	}

	public String execute() throws Exception {
		
		return null;
	}
	
	public List<MettingTypeBean> findAll(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        BpmMettingTypeDAO bmtype = new BpmMettingTypeDAO();
        
        try{
        	conn = dbbase.getConn();
        	factory = new DAOFactory(conn, bmtype);
			factory.setDAO(bmtype);
			bmtype.addOrderBy("createdate", false);
			List list = factory.find();
			mtbeanlist = new ArrayList();
			for(int i=0;i<list.size();i++){
				mtbeanlist.add(zh((BpmMettingTypeDAO)list.get(i)));
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
        return mtbeanlist;
	}
	//类型维护时查询所有
	public String list(){
		List<MettingTypeBean> list = findAll();
		totalCount = list.size();
		int count = start + limit;
		 bdList = new ArrayList<MettingTypeBean>();
			if(count > totalCount){
				bdList = list.subList(start, totalCount);
			}else{
				bdList = list.subList(start, count);
			}
			 mtbeanlist = bdList;
		return "typelist";
	}
	//添加会议时查询所有类型
	public String getAll(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		mtbeanlist = findAll();
		try {
			JSONArray ja = JSONArray.fromObject(mtbeanlist);
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        return "alllist";
	}
	//保存
	public void save(){
		HttpServletRequest request=ServletActionContext.getRequest();
		 HttpSession session = request.getSession(true);
		 String personId = session.getAttribute("personId").toString();
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        BpmMettingTypeDAO bmtype = new BpmMettingTypeDAO();
        //uuid = (new UUID()).toString();
        try{
        	conn = dbbase.getConn();
        	factory = new DAOFactory(conn, bmtype);
			factory.setDAO(bmtype);
			bmtype.setConnection(conn);
			bmtype.setCreator(creator);
			bmtype.setCreatorid(personId);
			bmtype.setCreatedate(createdate);
			bmtype.setLxmc(lxmc);
			bmtype.setDescription(description);
			if(uuid.equals("")){
				uuid = (new UUID()).toString();
				bmtype.setUuid(uuid);
				bmtype.create();
			}else{
				bmtype.setUuid(uuid);
				bmtype.update();
			}
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
	//根据主键查找
	public String getById(){
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        BpmMettingTypeDAO bmtype = new BpmMettingTypeDAO();
        
        try{
        	conn = dbbase.getConn();
        	factory = new DAOFactory(conn, bmtype);
			factory.setDAO(bmtype);
			bmtype.setUuid(uuid);
			BpmMettingTypeDAO ba = (BpmMettingTypeDAO)factory.findByPrimaryKey();
			mtbean = zh(ba);
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
        return "updatetype";
	}
	//外部调用删除方法
	public String deltype(){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		try{
			if(mtypeids != null && !"".equals(mtypeids))
			{
				String[] fid = mtypeids.split(",");
				for(int i=0;i<fid.length;i++)
				{

					deldbtype(fid[i]);
				}
			}

			}catch(Exception e)
			{
				e.printStackTrace();
				
			}
			return SUCCESS;
	}
	//从数据库中删除
	public void deldbtype(String typeid){
		DBBeanBase dbbase;
        Connection conn;
        dbbase = new DBBeanBase("bpm", true);
        conn = null;
        DAOFactory factory = null;
        BpmMettingTypeDAO bmtype = new BpmMettingTypeDAO();
        
        try{
        	conn = dbbase.getConn();
        	factory = new DAOFactory(conn, bmtype);
			factory.setDAO(bmtype);
			bmtype.setConnection(conn);
			bmtype.setUuid(typeid);
			bmtype.batchDelete();
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
	//将数据库实体转换为供前台操作的实体
	public MettingTypeBean zh(BpmMettingTypeDAO ba){
		MettingTypeBean mtbean = new MettingTypeBean();
		mtbean.setCreator(ba.getCreator());
		mtbean.setCreatorid(ba.getCreatorid());
		mtbean.setCreatedate(ba.getCreatedate());
		mtbean.setUuid(ba.getUuid());
		mtbean.setLxmc(ba.getLxmc());
		mtbean.setDescription(ba.getDescription());
		return mtbean;
	}
	
	
	private String mettinguuid;
	private List<MettingTypeBean> mtbeanlist;
	private MettingTypeBean mtbean;
	private String liststatus;
	//属性
	private String uuid;
	private String creator;
	private String createdate;
	private String lxmc;
	private String description;
	private String mtypeids;
	//分页所需字段
	List<MettingTypeBean> bdList;
	private int totalCount;
	private int start;
	private int limit;
	public String getMettinguuid() {
		return mettinguuid;
	}

	public void setMettinguuid(String mettinguuid) {
		this.mettinguuid = mettinguuid;
	}

	public List<MettingTypeBean> getMtbeanlist() {
		return mtbeanlist;
	}
	
	public MettingTypeBean getMtbean() {
		return mtbean;
	}

	public void setMtbean(MettingTypeBean mtbean) {
		this.mtbean = mtbean;
	}

	public void setMtbeanlist(List<MettingTypeBean> mtbeanlist) {
		this.mtbeanlist = mtbeanlist;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getLxmc() {
		return lxmc;
	}

	public void setLxmc(String lxmc) {
		this.lxmc = lxmc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getMtypeids() {
		return mtypeids;
	}

	public void setMtypeids(String mtypeids) {
		this.mtypeids = mtypeids;
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

	public String getListstatus() {
		return liststatus;
	}

	public void setListstatus(String liststatus) {
		this.liststatus = liststatus;
	}
	
	
	
	

}