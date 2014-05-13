package kzxd.zwxg.attachment;

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
import java.util.Date;
import java.util.List;

import net.itjds.bpm.data.FormClassBean;
import net.itjds.bpm.engine.BPMException;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.logging.Log;
import net.itjds.common.logging.LogFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.conf.Constants;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class ZwxgUploadAttachmentAction extends BPMActionBase{
	
	
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
			ZwxgUploadAttachmentAction.class);
	public ZwxgUploadAttachmentAction() throws  BPMException {
		//super();	
	}
	public String execute() throws Exception {
		
		return null;
	}
	/**
	 * 将文件写入本地文件夹
	 * */
	 public static void writeFile(String src,File file)  {
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
public String  saveFileInfo(String path,String instId,String filename,String formName,String processInstId)
{
	Person person=(Person) ActionContext.getContext()
	.getValueStack().findValue("$currPerson");
	ZwxgAttachmentDAO zattdao = new ZwxgAttachmentDAO();
	DBBeanBase dbbase = new DBBeanBase("bpm",true);
    Connection conn = null;
    String fileid = new UUID().toString();
    DAOFactory factory = null;
    try{
    	
    	conn=dbbase.getConn();
    	factory = new DAOFactory(conn,zattdao);
    	factory.setDAO(zattdao);
    	zattdao.setConnection(conn);
    	zattdao.setUuid(fileid);
    	zattdao.setProcessinstid(processInstId);
    	zattdao.setActivityinstid(instId);
    	zattdao.setFilepath(path);
    	zattdao.setFilename(filename);
    	zattdao.setFiletype(getFileExt(filename));
    	zattdao.setFileuploaduser(person.getID());
    	zattdao.setFileuploaddate(formatDate());
    	zattdao.setFileindex(1);
    	zattdao.setFormid( formName);
    	zattdao.setIsToPdf(1);
    	if(instId.equals(processInstId))
    	{
    		zattdao.setRemark(activityInstId);
    	}else
    	{
    		zattdao.setRemark("");
    	}
    	
    	zattdao.create();
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
	
}
