package net.itjds.owen.metting.attachment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kzxd.utils.File2Pdf;

import org.apache.struts2.ServletActionContext;


import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.conf.Constants;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.bpm.listener.SendSMSControl;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.userclient.common.UserClientException;
import net.itjds.userclient.common.util.CatalogUtil;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class UploadAttachmentAction implements Action{
	
	
	//private   File file;
	private   String userfile;
	private   File[] files;
	private   File file;
	private   String[] filesContentType;
	private   String[] filesFileName;
	private   String sessionId;
	private   String fileName;

	private   String fileFileName;
	private   String sxxxid;
	protected static Log log = LogFactory.getLog(Constants.CONFIG_KEY,
			UploadAttachmentAction.class);
	public UploadAttachmentAction()  {
		//super();	
	}
	
	
	public String execute() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
	
		try{


		Calendar c =  Calendar.getInstance() ;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		String rootPath = File.separator+"file"+File.separator+"attachment"+File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath")+rootPath;
		CatalogUtil.createFolder(realPath);
	
		String processInstPath = year+File.separator+month+File.separator+sxxxid;
	
		String uploadProcessInstFolder = CatalogUtil.createFolder(realPath+processInstPath);
	
		//File[] srcFiles = this.getFiles(); 
//		 处理每个要上传的文件
		//fileName = new String( name.getBytes("ISO-8859-1"));
		String fileid = saveFileInfo(rootPath+processInstPath+File.separator,sxxxid,fileFileName);
		writeFile(uploadProcessInstFolder+File.separator+fileid+"."+getFileExt(fileFileName),file);
		if(!getFileExt(fileFileName).equals("pdf")){
			if(!File2Pdf.fileconvert(uploadProcessInstFolder+File.separator+fileid+"."+getFileExt(fileFileName), uploadProcessInstFolder+File.separator+fileid+"."+"pdf", getFileExt(fileFileName))){
				this.updateFileInfo(fileid, 2);
			}
		}
		
		response.setContentType("text/html;charset=utf-8");
		 
		}catch(Exception e)
		{
		//	session.put("FileUpload.Progress."+sessionId, "error");
			response.getWriter().print("{success:false,msg:'文件上传失败'}");
		}
		response.getWriter().print("{success:true,msg:'上传成功'}");
		return null;

	}
	/**
	 * 将文件写入本地文件夹
	 * */
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
/**
 * 文件写入后,将相应的文件信息存入数据库
 * */
public String  saveFileInfo(String path,String sxxxid,String filename)
{

	Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
	BpmMAttachmentDAO attdao = new BpmMAttachmentDAO();
	DBBeanBase dbbase = new DBBeanBase("bpm",true);
    Connection conn = null;
    String fileid = new UUID().toString();
    DAOFactory factory = null;
    try{
    	
    	conn=dbbase.getConn();
    	factory = new DAOFactory(conn,attdao);
    	factory.setDAO(attdao);
    	attdao.setConnection(conn);
    	attdao.setUuid(fileid);
    	
    	attdao.setSxxxid(sxxxid);
    	attdao.setFilepath(path);
    	attdao.setFilename(filename);
    	attdao.setFiletype(getFileExt(filename));
    	attdao.setCreatorid(person.getID());
    	attdao.setCreatorname(person.getName());
    	attdao.setCreatedate(formatDate());
    	attdao.setIsToPdf(1);
    	
    	attdao.create();
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
    return fileid;
}


public void updateFileInfo(String fileid,Integer isToPdf){
	BpmMAttachmentDAO attdao = new BpmMAttachmentDAO();
	DBBeanBase dbbase = new DBBeanBase("bpm",true);
    Connection conn = null;
    DAOFactory factory = null;
    try{
    	
    	conn=dbbase.getConn();
    	factory = new DAOFactory(conn,attdao);
    	factory.setDAO(attdao);
    	attdao.setConnection(conn);
    	attdao.setUuid(fileid);
    	attdao = (BpmMAttachmentDAO) factory.findByPrimaryKey();
    	
    	attdao.setIsToPdf(isToPdf);
    	attdao.update();
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
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}
	/**
	 * @param file the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}
	/**
	 * @return the fileFileName
	 */
	public String getFileFileName() {
		return fileFileName;
	}
	/**
	 * @param fileFileName the fileFileName to set
	 */
	public void setFileFileName(String fileFileName) {
		this.fileFileName = this.getCN(fileFileName);
	}
	/**
	 * @return the sxxxid
	 */
	public String getSxxxid() {
		return sxxxid;
	}
	/**
	 * @param sxxxid the sxxxid to set
	 */
	public void setSxxxid(String sxxxid) {
		this.sxxxid = sxxxid;
	}
	
	
	
	public  String getCN(String dir){
//		  if(dir!=null){
//				if (!dir.matches("[\u4e00-\u9fa5]+")){
//					try {
//						
//						dir=new String(dir.getBytes("utf-8"));
//
//					} catch (UnsupportedEncodingException e) {
//						e.printStackTrace();
//					}
//				}
//			}
		  return dir;
	}
}
