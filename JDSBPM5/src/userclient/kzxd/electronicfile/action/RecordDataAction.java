package kzxd.electronicfile.action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kzxd.electronicfile.dao.RecordCategoryDAO;
import kzxd.electronicfile.dao.RecordDataDAO;
import kzxd.electronicfile.dao.RollPepodomDAO;
import kzxd.electronicfile.entity.RecordCategoryBean;
import kzxd.electronicfile.entity.RecordDataBean;
import kzxd.electronicfile.file.BpmMAttachmentDAO;
import kzxd.electronicfile.file.CopyFileAction;
import kzxd.electronicfile.file.FileDisplayAction;
import kzxd.electronicfile.file.FileListAction;
import kzxd.lucenetest.CreateLuceneIndex;

import net.itjds.bpm.engine.BPMException;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.userclient.common.util.CatalogUtil;
import net.itjds.userclient.list.SelfDocumentAction;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class RecordDataAction extends BPMActionBase {

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void save(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		RecordDataDAO ddao = new RecordDataDAO();
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		Person person = null;
		try {
			person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, ddao);
			ddao.setConnection(conn);
			ddao.setUuid(dataId);
			ddao.setTitle(dbean.getTitle());
			ddao.setContent(dbean.getContent());
			ddao.setDkeyword(dbean.getDkeyword());
			ddao.setPersonid(personId);
			ddao.setPersonname(person.getName());
			ddao.setCreatedate(new Date());
			if(dbean.getProcessDefId()!=null&&!dbean.getProcessDefId().equals("")){
				BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
				bpmUserClientUtil.getClient().getProcessDef(dbean.getProcessDefId()).getName();
				ddao.setProcessDefId(bpmUserClientUtil.getClient().getProcessDef(dbean.getProcessDefId()).getName());
				
				//ddao.setProcessDefId(dbean.getProcessDefId());
			}
			
			ddao.setActivityInstId(dbean.getActivityInstId());
			ddao.setFenshu(dbean.getFenshu());
			ddao.setLwdw(dbean.getLwdw());
			ddao.setMiji(dbean.getMiji());
			ddao.setJjcd(dbean.getJjcd());
			ddao.setCwdate(formatDate(dbean.getCwdate()));
			ddao.setYzh(dbean.getYzh());
			ddao.setNbyj(dbean.getNbyj());
			ddao.setCljg(dbean.getCljg());
			ddao.setLdps(dbean.getLdps());
			ddao.setYbqm(dbean.getYbqm());
			ddao.setRecordindex(dbean.getRecordindex());
			ddao.create();
			conn.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		String msg = convertpdf(dataId,dbean.getTitle(),dbean.getDkeyword(),dbean.getContent(),formatDate(ddao.getCreatedate()),person.getName(),dbean.getMiji(),dbean.getJjcd(),dbean.getYzh());
		if(msg.equals("")){
			msg = "成功转为pdf";
		}
		try {
			response.getWriter().write(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//保存文档的同时将word等附件转为pdf，并添加到索引文件里
	public String convertpdf(String recordid,String title,String dkeyword,String content,String createtime,String overperson,String query1,String query2,String query3){
		Calendar c =  Calendar.getInstance() ;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		String rootPath = File.separator+"file"+File.separator+"electronicfile"+File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath")+rootPath;
		String processInstPath = year+File.separator+month+File.separator+recordid;
		String uploadProcessInstFolder = CatalogUtil.createFolder(realPath+processInstPath);
		FileListAction faction = new FileListAction();
		faction.setRecordid(recordid);
		List list = faction.getAllFile();
		String msg = "";
		for(int i=0;i<list.size();i++){
			BpmMAttachmentDAO  ba = (BpmMAttachmentDAO)list.get(i);
			
			String oldfile = uploadProcessInstFolder+File.separator+ba.getUuid()+"."+ba.getFiletype();
			
			String newfile = uploadProcessInstFolder+File.separator+ba.getUuid()+".pdf";
			if(ba.getFiletype().equals("tif")){
				new CopyFileAction().tiftopdf(oldfile, newfile);
			}else{
				//msg += new CopyFileAction().fileconvert(oldfile, newfile, ba.getFiletype());
				if(new File(newfile).exists()){
					try {
						
						
						new CreateLuceneIndex().addDoc(newfile, dataId, title, dkeyword, createtime, overperson,"2",query1,query2,query3);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return msg;
	}
	
	//组卷或拆卷
	public void updateStatus(){
		String[] ids = dataIds.split(",");
		for(int i=0;i<ids.length;i++){
			updateStatus(ids[i]);
		}
	}	
	
	public void updateStatus(String id){
		RecordDataDAO ddao = findDAOById(id);
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try {
			
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, ddao);
			if(dbean!=null){
					ddao.setRollid(dbean.getRollid());
				
				ddao.update();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	//未组卷列表
	public String list(){
		datalist = findBeanByRid(null);
		return "datalist";
	}
	
	//可查询列表 不区分是否已封卷
	public ResultSet findByPepodom(String rollid,String status,String title,String stime,String etime){
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		List list = new ArrayList();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try{
			conn = dbbase.getConn();
			String querystr = "";
			int count = 0;
			if(title!=null&&!title.equals("")){
				querystr += " and title like '%"+title+"%' ";
				//count +=1;
			}
			if(stime!=null&&!stime.equals("")){
				querystr += " and createdate>=to_date('"+stime+"','yyyy/mm/dd')";
				
			}
			if(etime!=null&&!etime.equals("")){
				querystr += " and createdate<=to_date('"+etime+"','yyyy/mm/dd')";
				
			}
			if(status!=null&&!status.equals("")){
				querystr += " and uuid in (select rollid from fdt_oa_rollpepodom where filetype='2' and ispass="+status+")";
				
			}
			String edate = formatDate(new Date());
			String ws = " mr.rollid='"+rollid+"'   ";
		String countsql = "select count(*) from FDT_OA_RECORDDATA mr where "+ws+querystr;
		st = conn.createStatement();
		rs = st.executeQuery(countsql);
		while (rs.next()) {
			totalCount = rs.getInt(1);
		}
		
		
		
		String sql = "select m.* from (select rownum r,p.ispass,mr.* from FDT_OA_RECORDDATA mr left join FDT_OA_ROLLPEPODOM p on mr.uuid=p.rollid  and rownum <=?) m where m.rollid='"+rollid+"' "+querystr+"   and  m.r >?";
		pst = conn.prepareStatement(sql);
		//int count = 0;
	
		pst.setInt(1,limit + start);
		pst.setInt(2,start);
		rs = pst.executeQuery();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	
	public List findBeanByRid(String rollid){
		List list = new ArrayList();
		ResultSet rs = null;
		PreparedStatement pst = null;
		Statement st = null;
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try{
			conn = dbbase.getConn();
			String querystr = "";
			if(!title.equals("")){
				querystr += " and title like '%"+title+"%' ";
				//count +=1;
			}
			String countsql = "select count(*) from FDT_OA_RECORDDATA where rollid is null "+querystr;
			
			st = conn.createStatement();
			rs = st.executeQuery(countsql);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
			String sql = "select m.* from (select rownum r,mr.* from FDT_OA_RECORDDATA mr where rollid is null "+querystr+" and rownum <=? order by createdate desc) m where m.r >?";
			pst = conn.prepareStatement(sql);
			//int count = 0;
		
			pst.setInt(1,limit + start);
			pst.setInt(2,start);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				RecordDataBean rdbean = new RecordDataBean();
				rdbean.setDataId(rs.getString("uuid"));
				rdbean.setWjzluuid(rs.getString("wjzluuid"));
				rdbean.setTitle(rs.getString("title"));
				rdbean.setPersonname(rs.getString("personname"));
				rdbean.setCreatedate(formatDate(rs.getDate("createdate")));
				rdbean.setProcessDefId(rs.getString("processdef_id"));
				list.add(rdbean);
			}
		}catch(Exception e){
			
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//根据案卷id查询  若id为空则查询未组卷列表
	public List findByRid(String rollid){
		RecordDataDAO ddao = new RecordDataDAO();
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, ddao);
			if(rollid!=null){
				ddao.setRollid(rollid);
			}else{
				ddao.setWhereClause(" rollid is null");
			}
			ddao.addOrderBy("createdate", false);
			list = factory.find();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	//根据活动实例查询档案  用于判断此流程是否已归档
	public void findByActivityInstId(){
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		RecordDataDAO ddao = new RecordDataDAO();
		
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		List list = new ArrayList();
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, ddao);
			if(activityInstId!=null){
				ddao.setActivityInstId(activityInstId);
			}
			list = factory.find();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {
			response.getWriter().write(list.size());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String findById(){
		HttpServletRequest request = ServletActionContext.getRequest();
		RecordDataDAO ba = findDAOById(dataId);
		if(ba!=null){
			dbean = daozh(ba);
		}
		return "addData";
			
		
		
	}
	
	//全文检索 查看档案详细信息
	public String searchById(){
		HttpServletRequest request = ServletActionContext.getRequest();
		RecordDataDAO ba = findDAOById(dataId);
		if(ba!=null){
			RollPepodomDAO rpdao = new RollPepodomAction().findDaoBydid(dataId);
			if(rpdao!=null){
				if(rpdao.getIspass()!=2){
					request.setAttribute("errorstr", "您无权查看，请先申请！");
					request.setAttribute("isapplicant","N");
					return "error";
				}
				dbean = daozh(ba);
				return "addData";
			}else{
				request.setAttribute("errorstr", "您无权查看，请先申请！");
				request.setAttribute("isapplicant","Y");
				request.setAttribute("dataId",dataId);
				return "error";
			}
			
			//}
		}else{
		request.setAttribute("errorstr", "");
		request.setAttribute("isapplicant","N");
		return "error";
		}
		
	}
	
	public RecordDataDAO findDAOById(String dataId){
		RecordDataDAO ddao = new RecordDataDAO();
		RecordDataDAO ba = new RecordDataDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try {
			conn = dbbase.getConn();
			factory = new DAOFactory(conn, ddao);
			if(dataId!=null){
				ddao.setUuid(dataId);
				ba = (RecordDataDAO)factory.findByPrimaryKey();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return ba;
	}
	
	
	//新增档案
	public String returnaddData(){
		dataId = new UUID().toString();
		if(personId==null||personId.equals("null")){
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession(true);
			personId = session.getAttribute("personId").toString();
		}
		if(getProcessInst()!=null){
			processInstId = getProcessInst().getProcessInstId();
			List list = new FileListAction().getAllByPInstId(processInstId);
			if(list!=null&&list.size()>0){
				new CopyFileAction().copyfile(list, dataId,personId,1);
			}
			List dlist = new SelfDocumentAction().getProcessInstIdDoc(processInstId, activityInstId);
			if(dlist!=null&&dlist.size()>0){
				new CopyFileAction().copyfile(dlist, dataId,personId,2);
			}
			processDefId = getProcessInst().getProcessDefId();
			
			
		}
		return "addData";
	}
	
	//获取书签
	public String getBookMark(){
		processInstId = getProcessInst().getProcessInstId();
		BpmMAttachmentDAO ba =new FileDisplayAction().findClj(dataId);
		
		if (ba != null) {
			cljid = ba.getUuid();
		} else {
			try {
				bookmarks = new ReadXmlAction().viewXML(wordname);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "createDocument";
	}
	public RecordDataBean daozh(RecordDataDAO ddao){
		RecordDataBean dbean = new RecordDataBean();
		dbean.setDataId(ddao.getUuid());
		dbean.setTitle(ddao.getTitle());
		dbean.setContent(ddao.getContent());
		dbean.setDkeyword(ddao.getDkeyword());
		dbean.setPersonId(ddao.getPersonid());
		dbean.setMiji(ddao.getMiji());
		dbean.setJjcd(ddao.getJjcd());
		dbean.setCljg(ddao.getCljg());
		dbean.setNbyj(ddao.getNbyj());
		dbean.setLdps(ddao.getLdps());
		dbean.setYbqm(ddao.getYbqm());
		dbean.setLwdw(ddao.getLwdw());
		dbean.setWenhao(ddao.getWenhao());
		dbean.setFenshu(ddao.getFenshu());
		dbean.setYzh(ddao.getYzh());
		dbean.setCwdate(formatDate(ddao.getCwdate()));
		dbean.setProcessDefId(ddao.getProcessDefId());
		dbean.setRecordindex(ddao.getRecordindex());
		dbean.setProcessdefName(ddao.getProcessDefId());
		dbean.setProcessDefId(ddao.getProcessDefId());
		/*if(ddao.getProcessDefId()!=null){
			BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
			try {
				String pname = bpmUserClientUtil.getClient().getProcessDef(ddao.getProcessDefId()).getName();
				dbean.setProcessdefName(pname);
			} catch (BPMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
		Person person = null;
		try {
			person = OrgManagerFactory.getOrgManager().getPersonByID(dbean.getPersonId());
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbean.setPersonname(person.getName());
		return dbean;
	}
	
	public String formatDate(Date date){
		if(date!=null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			return format.format(date);
		}
		return null;
		
	}
	public Date formatDate(String date){
		if(date!=null&&!date.equals("")){
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return format.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	
	private String dataId;
	private List datalist;
	
	//获取word书签
	private String bookmarks;
	private String activityInstId;
	private String personId;
	private String cljname;
	private String processInstId;
	private String cljid;
	private String wordname;
	private String processDefId; // 公文分类
	private String wjzluuid;
	
	//分页
	private int totalCount;
	private int start;
	private int limit;
	
	
	//查询字段
	private String title;
	private String dataIds;
	private int status;
	
	
	private RecordDataBean dbean;
	

	public List getDatalist() {
		return datalist;
	}

	public void setDatalist(List datalist) {
		this.datalist = datalist;
	}

	public String getBookmarks() {
		return bookmarks;
	}

	public void setBookmarks(String bookmarks) {
		this.bookmarks = bookmarks;
	}

	public String getActivityInstId() {
		return activityInstId;
	}

	public void setActivityInstId(String activityInstId) {
		this.activityInstId = activityInstId;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
	
	public String getCljname() {
		return cljname;
	}

	public void setCljname(String cljname) {
		this.cljname = cljname;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getDataId() {
		return dataId;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
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

	public String getDataIds() {
		return dataIds;
	}

	public void setDataIds(String dataIds) {
		this.dataIds = dataIds;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public RecordDataBean getDbean() {
		return dbean;
	}

	public void setDbean(RecordDataBean dbean) {
		this.dbean = dbean;
	}

	public String getCljid() {
		return cljid;
	}

	public void setCljid(String cljid) {
		this.cljid = cljid;
	}

	public String getWordname() {
		return wordname;
	}

	public void setWordname(String wordname) {
		this.wordname = wordname;
	}

	public String getProcessDefId() {
		return processDefId;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWjzluuid() {
		return wjzluuid;
	}

	public void setWjzluuid(String wjzluuid) {
		this.wjzluuid = wjzluuid;
	}	
	
	
	
}
