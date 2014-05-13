package net.itjds.userclient.document;

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

/**
 * ���ı���,ʵ��
 * �����ı��浽������
 * ͬʱ���ļ�·���������ݿ� 
 * @author huozhuyun
 *
 */
public class SaveDocumentAction extends BPMActionBase{
	
	
	//private   File file;
	private   String userfile;
	private   File[] files;
	private   String[] filesContentType;
	private   String[] filesFileName;
	private   PrintWriter out;
	private   String processInstId;
	private   String uuid;
	
	private String displayName;
	public SaveDocumentAction() throws  BPMException {
		super();	
	}
	public String execute() throws Exception {
	
		// TODO Auto-generated method stub  
		try
		{
		processInstId =this.getProcessInst().getProcessInstId();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=gb2312");
		out = response.getWriter();
		String fileName ;
		Calendar c =  Calendar.getInstance() ;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		String rootPath = File.separator+"file"+File.separator+"document"+File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath")+rootPath;
		CatalogUtil.createFolder(realPath);
		String activeInstPath = year+File.separator+month+File.separator+processInstId+File.separator+this.getActivityInst().getActivityInstId();
		String processInstPath = year+File.separator+month+File.separator+processInstId+File.separator+processInstId;  //��Ϊ�������ݱ���һ��
		String uploadActiveInstFolder = CatalogUtil.createFolders(realPath,activeInstPath);
		String uploadProcessInstFolder = CatalogUtil.createFolders(realPath,processInstPath);//��������\����ID\ʵ��ID����Ŀ¼
//		ProcessInst processInst=  webworkOAUtil.getClient().getProcessInst(processInstId);
		File[] srcFiles = this.getFiles(); 
//		 ����ÿ��Ҫ�ϴ����ļ�
		if(srcFiles != null)
		{
		for (int i = 0; i < srcFiles.length; i++) {
			uuid = new UUID().toString();
		fileName = this.getFilesFileName()[i].replaceAll("\"", "");
		writeFile(uploadActiveInstFolder+File.separator+fileName,srcFiles[i]);//���ļ�д������
	
		if(this.getProcessInst().getCopyNumber()<=0)//������û��ɢǰ����һ��ԭʼ�ļ�
		{
			writeFile(uploadProcessInstFolder+File.separator+fileName,srcFiles[i]);
			saveFileInfo(uuid,rootPath+processInstPath+File.separator,processInstId,fileName,displayName);
		}
		saveFileInfo(uuid,rootPath+activeInstPath+File.separator,this.getActivityInst().getActivityInstId(),fileName,displayName);//���ļ�·��д�����ݿ�
		}
		out.print(uuid);
      //  return SUCCESS;
		}else
			{
			out.print("�����ļ�ʧ�� !");
			}
		}catch(Exception e)
		{
			out.print("�����ļ�ʧ�� !");
			e.printStackTrace();
		}
		return null;

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

	 /**
	  * �ļ�д���,����Ӧ���ļ���Ϣ�������ݿ�
	  * */
	 public void saveFileInfo(String uuid,String path,String instId,String filename,String displayName)
	 {
		 Person person=(Person) ActionContext.getContext()
			.getValueStack().findValue("$currPerson");
	 	BpmDocumentDAO docdao = new BpmDocumentDAO();
	 	DBBeanBase dbbase = new DBBeanBase("bpm",true);
	     Connection conn = null;
	     DAOFactory factory = null;
	     try{
	     	
	     	conn=dbbase.getConn();
//	     	factory = new DAOFactory(conn,docdao);
//	     	docdao.setActivityinstid(instId);
//	    	List files = factory.find();
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
	     	docdao.setDisplayname(displayName);
//	    	if(files != null && files.size()>0)
//	    	{
		     	docdao.update();
//	    	}else
//	    	{
	     	   	docdao.create();
//	    	}
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
	
	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getUserfile() {
		return userfile;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

}
