package kzxd.electronicfile.file;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.common.org.base.PersonRole;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.document.BpmDocumentDAO;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream; 
import java.io.OutputStream;
import java.io.UnsupportedEncodingException; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kzxd.electronicfile.action.RollPepodomAction;

public class DownLoadAction implements Action {

	public String uuid;
	public String downloadDir ;
	public String downloadFile ;
	public String fileName;
	public InputStream inputStream;
	public String downloadFileName;
	public String rootpath ;
	public InputStream getInputStream() throws Exception { 

//		 ͨ�� ServletContext��Ҳ����application ����ȡ���� 
//		ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;filename=" + fileName);
		return new BufferedInputStream(new FileInputStream(downloadFile));
		} 

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		rootpath= CommonConfig.getValue("bpm.filePath");
		getFileAttribute();//�ȸ���IDȡ�ļ�����
		java.io.File file = new java.io.File(downloadFile); 
		downloadFile = file.getCanonicalPath();// ��ʵ�ļ�·��,ȥ�������..����Ϣ 
//		 ������ͼ���ز��� /download �µ��ļ�, ����ʾ������ 
		if(!file.isFile()) 
		{
			fileName="�ļ�����";
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		return SUCCESS; 
	}
	public String downloadFile() throws Exception {
		// TODO Auto-generated method stub
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		rootpath= CommonConfig.getValue("bpm.filePath");
		String fname = getFileAttribute();//�ȸ���IDȡ�ļ�����
		String personId = request.getSession().getAttribute("personId").toString();
		response.setContentType("text/html;charset=utf-8");
		RollPepodomAction rpaction = new RollPepodomAction();
		rpaction.setFiletype("3");
		rpaction.setStatus("2");
		Person person = null;
		
		
		try {
			person = (Person)OrgManagerFactory.getOrgManager().getPersonByID(personId);
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<PersonRole> rlist = person.getRoleList();
		for(PersonRole pr:rlist){
			if(pr.getName().equals("��������Ա")){
				java.io.File file = new java.io.File(downloadFile); 
				downloadFile = file.getCanonicalPath();// ��ʵ�ļ�·��,ȥ�������..����Ϣ 
				this.fileName = fname;
				if(!file.isFile()){
					fileName="�ļ�����";
					file.getParentFile().mkdirs();
					file.createNewFile();
				}
				return SUCCESS;
			}
		}
		if(rpaction.findDaoBydid(uuid)==null){
			response.getWriter().write("����Ȩ����");
			return null;
		}else{
			
			java.io.File file = new java.io.File(downloadFile); 
			downloadFile = file.getCanonicalPath();// ��ʵ�ļ�·��,ȥ�������..����Ϣ 
			this.fileName = fname;
			if(!file.isFile()){
				fileName="�ļ�����";
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
			return SUCCESS;
		}
		
	}
	
	public String downFile() throws IOException{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		String personId = request.getSession().getAttribute("personId").toString();
		response.setContentType("text/html;charset=utf-8");
		RollPepodomAction rpaction = new RollPepodomAction();
		rpaction.setFiletype("3");
		rpaction.setStatus("2");
		Person person = null;
		try {
			person = (Person)OrgManagerFactory.getOrgManager().getPersonByID(personId);
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<PersonRole> rlist = person.getRoleList();
		for(PersonRole pr:rlist){
			if(pr.getName().equals("��������Ա")){
				response.getWriter().write("y");
				return null;
			}
		}
		if(rpaction.findDaoBydid(uuid)==null){
			response.getWriter().write("����Ȩ����");
			return null;
		}else{
			
			response.getWriter().write("y");
		}
		return null;
	}
	public String downFileAgain(){
		rootpath= CommonConfig.getValue("bpm.filePath");
		String fname = getFileAttribute();//�ȸ���IDȡ�ļ�����
		java.io.File file = new java.io.File(downloadFile); 
		try{
		if(!file.isFile()) 
		{
			downloadFile = downloadFile.substring(0,downloadFile.lastIndexOf(".pdf"))+"."+fname.substring(fname.lastIndexOf(".")+1);
			java.io.File file2 = new java.io.File(downloadFile); 
			this.fileName = fname;
			if(!file2.isFile()){
				fileName="�ļ�����";
				file.getParentFile().mkdirs();
				file.createNewFile();
			}
		}else{
			downloadFile = file.getCanonicalPath();// ��ʵ�ļ�·��,ȥ�������..����Ϣ 
		}
		}catch(Exception e){
			e.printStackTrace();
		}
//		 ������ͼ���ز��� /download �µ��ļ�, ����ʾ������ 
		return SUCCESS; 
	}
	//�����ݿ���ȡ���ļ��������
	
	public String getFileAttribute()
	{
		String fname = "";
		Connection conn = null;
		DAOFactory factory = null;
		try {
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
    	conn = dbbase.getConn();
    	BpmMAttachmentDAO  attdao = 	new BpmMAttachmentDAO();
    	attdao.setUuid(uuid);
    	factory = new DAOFactory(conn,attdao);
    	List dbfiles = factory.find();
	    if(dbfiles != null && dbfiles.size() >0)
	    	{
	    		for(int i=0;i<dbfiles.size();i++)//ʵ��ֻ��һ��
	    		{
	    			BpmMAttachmentDAO  ba = 	(BpmMAttachmentDAO)dbfiles.get(i);
//	    			 �ļ�����Ŀ¼·�� 
	    			this.downloadDir = rootpath + ba.getFilepath();
//	    			 �ļ�����·�� 
	    			if(ba.getIsToPdf()==1){
	    				this.downloadFile =  rootpath+ba.getFilepath()+ba.getUuid()+".pdf";
	    				this.fileName = ba.getFilename().substring(0,ba.getFilename().lastIndexOf("."))+".pdf";
	    			}else{
	    				this.downloadFile =  rootpath+ba.getFilepath()+ba.getUuid()+"."+ba.getFiletype();
	    				this.fileName = ba.getFilename();
	    			}
	    			
	    			
	    			fname = ba.getFilename();
	    		}
	    	}
    	
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		return fname;
	}
	
	/** �ṩת�������Ĺ������õ��ļ��� */ 

	public String getDownloadFileName() { 

	String downFileName = fileName; 

	try { 

	downFileName = new String(downFileName.getBytes(), "ISO8859-1"); 

	} catch (UnsupportedEncodingException e) { 

	e.printStackTrace(); 

	} 

	return downFileName; 

	} 


	/**
	 * @return the uuid
	 */
	public String getUuid() {
		return uuid;
	}
	/**
	 * @param uuid the uuid to set
	 */
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	/**
	 * @param downloadFileName the downloadFileName to set
	 */
	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	/**
	 * @param inputStream the inputStream to set
	 */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	

}
