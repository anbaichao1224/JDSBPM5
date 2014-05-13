package kzxd.electronicfile.file;

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
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
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
	private   String processInstId;
	private String personId ="";
	private String recordid;
	private String dcljid ="";
	private String ctype;//�������  ����㻹�Ǹ���
	protected static Log log = LogFactory.getLog(Constants.CONFIG_KEY,
			UploadAttachmentAction.class);
	public UploadAttachmentAction()  {
		//super();	
	}
	
	
	public String execute() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		//request.setCharacterEncoding("UTF-8");
		try{


		Calendar c =  Calendar.getInstance() ;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		String rootPath = File.separator+"file"+File.separator+"electronicfile"+File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath")+rootPath;
		CatalogUtil.createFolder(realPath);
		
		String processInstPath = year+File.separator+month+File.separator+recordid;
	
		String uploadProcessInstFolder = CatalogUtil.createFolder(realPath+processInstPath);
		
		if(fileName==null){
			fileName = fileFileName;
		}else if(fileName.equals("lcgdclj.doc")){
			fileName = "�����.doc";
		}
		
		
		response.setContentType("text/html;charset=utf-8");
			if(!dcljid.equals("")){
				writeFile(uploadProcessInstFolder+File.separator+dcljid+"."+getFileExt(fileName),file);
				if(!getFileExt(fileFileName).equals("pdf")){
					if(!File2Pdf.fileconvert(uploadProcessInstFolder+File.separator+dcljid+"."+getFileExt(fileName), uploadProcessInstFolder+File.separator+dcljid+"."+"pdf", getFileExt(fileFileName))){
						this.updateFileInfo(dcljid, 2);
					}
				}
			}else{
					String fileid = saveFileInfo(rootPath+processInstPath+File.separator,recordid,fileName);
					writeFile(uploadProcessInstFolder+File.separator+fileid+"."+getFileExt(fileName),file);
					if (!getFileExt(fileFileName).equals("pdf")) {
					if (!File2Pdf.fileconvert(uploadProcessInstFolder
							+ File.separator + fileid + "."
							+ getFileExt(fileFileName), uploadProcessInstFolder
							+ File.separator + fileid + "." + "pdf",
							getFileExt(fileFileName))) {
						this.updateFileInfo(fileid, 2);
					}
				}
			}
			
		 
		}catch(Exception e)
		{
		//	session.put("FileUpload.Progress."+sessionId, "error");
			e.printStackTrace();
			response.getWriter().print("{success:false,msg:'�ļ��ϴ�ʧ��'}");
		}
		response.getWriter().print("{success:true,msg:'�ϴ��ɹ�'}");
		fileName = "";
		personId = "";
		processInstId = "";
		
		return null;

	}
	/**
	 * ���ļ�д�뱾���ļ���
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
	  * �޸����ݿ�
	  * @param fileid
	  * @param isToPdf
	  */
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
 * �ļ�д���,����Ӧ���ļ���Ϣ�������ݿ�
 * */
public String  saveFileInfo(String path,String sxxxid,String filename)
{
	Person person = null;
	try {
		person = (Person)OrgManagerFactory.getOrgManager().getPersonByID(personId);
	} catch (PersonNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
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
    	
    	
    	attdao.setFilepath(path);
    	attdao.setFilename(filename);
    	attdao.setRecordid(sxxxid);
    	attdao.setFiletype(getFileExt(filename));
    	attdao.setCreatorid(personId);
    	if(ctype!=null){
    		attdao.setCategorytype(ctype);
    	}
    	
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


	/**
	 * ��ȡ�ļ���׺��
	 * */
	public String getFileExt(String filename)
	{
		
		return filename.substring(filename.lastIndexOf(".")+1);
	}
	/**
	 * ��ʽ������
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
	public String getProcessInstId() {
		return processInstId;
	}
	/**
	 * @param sxxxid the sxxxid to set
	 */
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
	
	
	
	public String getPersonId() {
		return personId;
	}


	public void setPersonId(String personId) {
		this.personId = personId;
	}
	

	public String getRecordid() {
		return recordid;
	}


	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}
	

	public String getCtype() {
		return ctype;
	}


	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	

	public String getDcljid() {
		return dcljid;
	}


	public void setDcljid(String dcljid) {
		this.dcljid = dcljid;
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
