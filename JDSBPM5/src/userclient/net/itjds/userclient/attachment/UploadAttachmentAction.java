package net.itjds.userclient.attachment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
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

import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.data.FormClassBean;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.BPMSessionHandle;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonRole;
import net.itjds.common.org.conf.Constants;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.bpm.listener.SendSMSControl;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.userclient.common.UserClientException;
import net.itjds.userclient.common.util.CatalogUtil;

import com.opensymphony.xwork2.ActionContext;

public class UploadAttachmentAction extends BPMActionBase{
	
	
	//private   File file;
	private   String userfile;
	private   File[] files;
	private   File file;
	private   String[] filesContentType;
	private   String[] filesFileName;
	private   String sessionId;
	private   String fileName;
	private   String processInstId;
	private   String fileFileName;
	private   String formId;
	private   String activityInstHistoryId;
	protected static Log log = LogFactory.getLog(Constants.CONFIG_KEY,
			UploadAttachmentAction.class);
	public UploadAttachmentAction() throws  BPMException {
		//super();	
	}
	public String execute() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		
		// TODO Auto-generated method stub  
		try{

		this.processInstId = this.getProcessInst().getProcessInstId();
		Calendar c =  Calendar.getInstance() ;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		String rootPath = File.separator+"file"+File.separator+"attachment"+File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath")+rootPath;
		CatalogUtil.createFolder(realPath);
		String activeInstPath = year+File.separator+month+File.separator+processInstId+File.separator+activityInstId+File.separator+formId;
		String processInstPath = year+File.separator+month+File.separator+processInstId+File.separator+processInstId+formId;  //做为流程数据保存一份
		String uploadActiveInstFolder = CatalogUtil.createFolder(realPath+activeInstPath);
		String uploadProcessInstFolder = CatalogUtil.createFolder(realPath+processInstPath);
		String fileid = saveFileInfo(rootPath+activeInstPath+File.separator,activityInstId,fileFileName);
		writeFile(uploadActiveInstFolder+File.separator+fileid+"."+getFileExt(fileFileName),file);
		
		if(processInst.getCopyNumber()<=0)//在流程没分散前保存
		{
			fileid = saveFileInfo(rootPath+processInstPath+File.separator,processInstId,fileFileName);
			writeFile(uploadProcessInstFolder+File.separator+fileid+"."+getFileExt(fileFileName),file);
			
		}
		
		response.setContentType("text/html;charset=utf-8");
		 
		}catch(Exception e)
		{
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
 * 修改数据库
 * @param fileid
 * @param isToPdf
 */
public void updateFileInfo(String fileid,Integer isToPdf){
	BpmAttachmentDAO attdao = new BpmAttachmentDAO();
	DBBeanBase dbbase = new DBBeanBase("bpm",true);
    Connection conn = null;
    DAOFactory factory = null;
    try{
    	
    	conn=dbbase.getConn();
    	factory = new DAOFactory(conn,attdao);
    	factory.setDAO(attdao);
    	attdao.setConnection(conn);
    	attdao.setUuid(fileid);
    	attdao = (BpmAttachmentDAO) factory.findByPrimaryKey();
    	
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
 * 文件写入后,将相应的文件信息存入数据库
 * */
public String  saveFileInfo(String path,String instId,String filename)
{
	Person person=(Person) ActionContext.getContext()
	.getValueStack().findValue("$currPerson");
	BpmAttachmentDAO attdao = new BpmAttachmentDAO();
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
    	attdao.setProcessinstid(processInstId);
    	attdao.setActivityinstid(instId);
    	attdao.setFilepath(path);
    	attdao.setFilename(filename);
    	attdao.setFiletype(getFileExt(filename));
    	attdao.setFileuploaduser(person.getID());
    	attdao.setFileuploaddate(formatDate());
    	attdao.setFileindex(1);
    	
    	attdao.setIsToPdf(1);
    	
    	attdao.setFormid( getFormName());
    	if(instId.equals(processInstId))
    	{
    		attdao.setRemark(activityInstId);
    	}else
    	{
    		attdao.setRemark("");
    	}
    	
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
public String getFormName()
{
	List<FormClassBean> formList;
	String formName = "";
	try { 
		formList = this.activityInst.getActivityDef().getAllDataFormDef();

		if (formList.size() == 1) {
			formName = formList.get(0).getName();
		}else{
		for (int k = 0; formList.size() > k; k++) {
			FormClassBean formClassBean = formList.get(k);
			if (formClassBean.getId().equals(this.formId)) {
				log.error("formClassBean.getId()======"+formClassBean.getId()+"this.formId========"+this.formId);
				formName = formClassBean.getName();
			}
		}
		}
		/*FormClassBean formClassBean = this.activityInst.getActivityDef()
				.getMainFormDef();
		formName =  formClassBean.getName();*/
	} catch (BPMException e) { 
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return formName;
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
	
	public void initActivityInstId()
	{
		this.activityInstId = (String) ServletActionContext
		.getRequest().getParameter("activityInstId");

		if (this.activityInstId==null){
		this.activityInstId = (String) ServletActionContext
		.getRequest().getParameter("ActivityInstId");
		}		

		this.activityInstHistoryId= (String) ServletActionContext
		.getRequest().getParameter("activityInstHistoryId");
		
		if (this.activityInstHistoryId==null){
		this.activityInstHistoryId = (String) ServletActionContext
		.getRequest().getParameter("activityInstHistoryId");
		}	
		
		try {
			this.processInst = new BPMUserClientUtil().getClient().getActivityInst(activityInstId).getProcessInst();
			
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		this.fileFileName = fileFileName;
	}
	/**
	 * @return the formId
	 */
	public String getFormId() {
		return formId;
	}
	/**
	 * @param formId the formId to set
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}
	/**
	 * @return the activityInstHistoryId 
	 */
	public String getActivityInstHistoryId() {
		return activityInstHistoryId;
	}
	/**
	 * @param activityInstHistoryId the activityInstHistoryId to set
	 */
	public void setActivityInstHistoryId(String activityInstHistoryId) {
		this.activityInstHistoryId = activityInstHistoryId;
	}
	public String getProcessInstId() {
		return processInstId;
	}
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
	
	
}
