package kzxd.ttinfo.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kzxd.documentmodel.servers.KzxdDocumentServer;
import kzxd.documentmodel.servers.impl.KzxdDocumentServerImpl;
import kzxd.electronicfile.dao.RecordCategoryDAO;
import kzxd.ttinfo.dao.TtInfoDAO;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.util.CatalogUtil;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

public class TtInfoAction implements Action {
	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		//String realPath = CommonConfig.getValue("bpm.filePath") + rootPath;
		//CatalogUtil.createFolder(realPath);
		
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String datapath = "\\taotoumodel\\";
		String fileid = save(wenzhong,ttmc,datapath,fileFileName,getFType(fileFileName),remark);
		String fileName = fileid+"."+getFType(fileFileName);
		String path = realPath + datapath+fileName;
		writeFile(path,file);
		tlist = findAll();
	
		return SUCCESS;
		
	}
	
	public String list(){
		tlist = findAll();
		//begin zhongqun 添加 动态读取文种列表
		
		//end
		return SUCCESS;
	}
	
	public String selectlist(){
		tlist = findByWz();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("tlist", tlist);
		return "selectlist";
	}
	public List findAll(){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		TtInfoDAO tinfo = new TtInfoDAO();
		List list = new ArrayList();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn,tinfo);
			tinfo.addOrderBy("createdate", false);
			list = factory.find();
		}catch(Exception e){
			
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
		
	}
	
	public List findByWz(){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		TtInfoDAO tinfo = new TtInfoDAO();
		List list = new ArrayList();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory(conn,tinfo);
			tinfo.setWenzhongid(wenzhong);
			tinfo.addOrderBy("createdate", false);
			list = factory.find();
		}catch(Exception e){
			
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
		
	}
	/**
	 * 2012-1-7修改 增加字段wenzhong
	 * @param ttmc
	 * @param path
	 * @param filename
	 * @param filetype
	 * @param remark
	 * @return
	 */
	public String save(String wenzhong,String ttmc,String path,String filename,String filetype,String remark){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		String personId = session.getAttribute("personId").toString();
		TtInfoDAO tinfo = new TtInfoDAO();
		String uuid = new UUID().toString();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try{
			conn = dbbase.getConn();
			tinfo.setConnection(conn);
			
			tinfo.setUuid(uuid);
			tinfo.setTtmc(ttmc);
			tinfo.setTtpath(path);
			tinfo.setCreatedate(new Date());
			tinfo.setCreator(personId);
			tinfo.setRemark(remark);
			tinfo.setFilename(filename);
			
			tinfo.setWenzhongid(wenzhong);//tinfo.setWenzhong(wenzhong);  
			tinfo.setFiletype(filetype);
			tinfo.create();
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return uuid;
	}
	
	public String delete(){
		String[] idarray = ids.split(",");
		for(String id:idarray){
			deleteById(id);
		}
		tlist = findAll();
		
		return SUCCESS;
	}
	public void deleteById(String uuid){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		TtInfoDAO tinfo = new TtInfoDAO();
		try{
			conn = dbbase.getConn();
			tinfo.setConnection(conn);
			tinfo.setUuid(uuid);
			tinfo.delete();
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/*public TtInfoDAO findById(){
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		TtInfoDAO tinfo = new TtInfoDAO();
		try{
			conn = dbbase.getConn();
			factory = new DAOFactory("bpm");
			tinfo.setUuid(uuid);
			tinfo.delete();
			conn.commit();
		}catch(Exception e){
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}*/
	
	public String addHong() throws IOException{
		rootpath= CommonConfig.getValue("bpm.filePath");
		//getFileAttribute();//先根据ID取文件属性
		downloadFile = "E:\\tomcat\\tomcat5empty\\webapps\\ROOT\\taotoumodel\\1969dc1-134658fbf9c-f528764d624db129b32c21fbca0cb8d6.bmp";
		//downloadFile = this.downloadFile.substring(0,downloadFile.lastIndexOf("."))+".pdf";
		java.io.File file = new java.io.File(downloadFile);
		downloadFile = file.getCanonicalPath();// 真实文件路径,去掉里面的..等信息 
		this.fileName = "aaa.bmp";
		//writeResponse();
//		 发现企图下载不在 /download 下的文件, 就显示空内容 
		if(!file.isFile()) 
		{
			fileName="文件错误";
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		return "addHong"; 
	}
	
 private static void writeFile(String src,File file)  {
		 
         try  {
            InputStream in = null ;
            OutputStream out = null ;
             try  {                
            	in = new BufferedInputStream(new FileInputStream(file));
                out = new BufferedOutputStream( new FileOutputStream(src));
                 byte [] buffer = new byte [(int)file.length()];
                 int len = 0; 
                 while ((len = in.read(buffer)) > 0) {
                 out.write(buffer, 0, len); 
                 }

             } finally  {
                 if ( null != in)  {
                    in.close();
                } 
                  if ( null != out)  {
                    out.close();
                } 
            } 
         } catch (Exception e)  {
            e.printStackTrace();
        } 
    } 
 
 public String getFType(String filename){
	 filename = filename.substring(filename.lastIndexOf(".")+1);
	 return filename;
 }
 
 	private File file;
 	private String ids;
 	private String fileFileName;
 	private String ttmc;
 	private String remark;
 	
 	public String rootpath ;
	public String downloadDir ;
	public String downloadFile ;
	public String fileName;
	public InputStream inputStream;
 	
 	private List tlist;
 	
 	private String wenzhong;
 	
 	private String wenzhongQ;
 	
 	private List wzlist;//动态获取文种list
 	private String wenzhongid;//根据文种获得所有模版
 	
	public String getWenzhongQ() {
		return wenzhongQ;
	}

	public void setWenzhongQ(String wenzhongQ) {
		this.wenzhongQ = wenzhongQ;
	}

	public String getWenzhong() {
		return wenzhong;
	}

	public void setWenzhong(String wenzhong) {
		this.wenzhong = wenzhong;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getTtmc() {
		return ttmc;
	}

	public void setTtmc(String ttmc) {
		this.ttmc = ttmc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List getTlist() {
		return tlist;
	}

	public void setTlist(List tlist) {
		this.tlist = tlist;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() throws FileNotFoundException {
		return new BufferedInputStream(new FileInputStream(downloadFile));
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public List getWzlist() {
		return wzlist;
	}

	public void setWzlist(List wzlist) {
		this.wzlist = wzlist;
	}

	public String getWenzhongid() {
		return wenzhongid;
	}

	public void setWenzhongid(String wenzhongid) {
		this.wenzhongid = wenzhongid;
	}
	
 	
 	
}
