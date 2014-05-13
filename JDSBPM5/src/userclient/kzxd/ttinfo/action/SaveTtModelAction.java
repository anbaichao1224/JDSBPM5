package kzxd.ttinfo.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.BPMSessionHandle;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.util.CatalogUtil;
import net.itjds.userclient.common.BPMActionBase;

import net.itjds.userclient.document.BpmDocumentDAO;

import com.opensymphony.xwork2.ActionContext;
import net.itjds.userclient.bpm.listener.SendSMSControl;
/**
 * 正文保存,实现
 * 将正文保存到服务器
 * 同时将文件路径存入数据库 
 * @author huozhuyun
 *
 */
public class SaveTtModelAction extends BPMActionBase{
	
	private   String userfile;
	private   File[] files;
	private   String[] filesContentType;
	private   String[] filesFileName;
	private   PrintWriter out;
    private String processInstId;
	




	private   String uuid;
	private  String filefileName;
	public String getFilefileName() {
		return filefileName;
	}
	public void setFilefileName(String filefileName) {
		this.filefileName = filefileName;
	}
	public SaveTtModelAction() throws  BPMException {
		super();	
	}
	public String execute() throws Exception {
		String activeInstPath="";
		try
		{
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType("text/html;charset=gb2312");
			out = response.getWriter();
			File[] srcFiles = this.getFiles(); 
			HttpServletRequest request = ServletActionContext.getRequest();
			String rootpath = request.getSession().getServletContext().getRealPath("/");
			writeFile(rootpath+"taotoumodel\\"+uuid+".doc",srcFiles[0]);//将文件写到本地
			String result="[{\"success\":\"true\",\"path\":\""+activeInstPath+"\"}]";
			out.write(result);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	
		//out.print(uuid);
		
		return null;

	}
	 private static void writeFile(String src,File file)  {
         try  {
            InputStream in = null ;
            OutputStream out = null ;
             try  {                
            	 
            	in = new BufferedInputStream(new FileInputStream(file));
                out = new BufferedOutputStream( new FileOutputStream(src));
                byte[] buffer = new byte[1024];
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

	 /**
	  * 文件写入后,将相应的文件信息存入数据库
	  * */
	 public void saveFileInfo(String path,String instId,String filename)
	 {
		 Person person=(Person) ActionContext.getContext()
			.getValueStack().findValue("$currPerson");
	 	BpmDocumentDAO docdao = new BpmDocumentDAO();
	 	DBBeanBase dbbase = new DBBeanBase("bpm",true);
	 	this.uuid = new UUID().toString();
	     Connection conn = null;
	     DAOFactory factory = null;
	     try{
	     	
	     	conn=dbbase.getConn();
	     	factory = new DAOFactory(conn,docdao);
	     	docdao.setActivityinstid(instId);
	    	List files = factory.find();
	    	factory = new DAOFactory(conn,docdao);
    		factory.setDAO(docdao);
	     	docdao.setConnection(conn);
	     	docdao.setUuid(uuid);
	     	docdao.setProcessinstid(processInstId);
	     	docdao.setActivityinstid(instId);
	     	docdao.setFilepath(path);
	     	docdao.setFilename(filename);
	     	docdao.setFiletype(getFileExt(filename));
	     	docdao.setCreateuser(person.getID());
	     	docdao.setCreatedate(formatDate());
	     	docdao.setFileindex(1);
	     	docdao.setRemark("");
	    	if(files != null && files.size()>0)
	    	{
		     	docdao.update();
	    	}else
	    	{
	     	   	docdao.create();
	    	}
	     	conn.commit();
	     }catch(Exception e)
	     {
	     	e.printStackTrace();
	     	
	     }finally
	     {
	     	try {
	 			conn.close();
	 		} catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	     }
	 }

	 	/**
	 	 * 获取文件后缀名
	 	 * */
	 	public String getFileExt(String filename)
	 	{
	 		
	 		return filename.substring(filename.lastIndexOf(".")+1);
	 	}
	 	/**
	 	 * 格式化日期
	 	 * */
	 	public String formatDate()
	 	{
	 		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
	 		return sd.format(new Date(System.currentTimeMillis()));
	 	
	 	}

	public String doDefault() throws Exception {
        return INPUT;
    }

	public void setUserfile(String userfile) {
		this.userfile = userfile;
			
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}
	


	
	public String getUserfile() {
		return userfile;
	}
	
	public String getProcessInstId() {
		return processInstId;
	}
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
