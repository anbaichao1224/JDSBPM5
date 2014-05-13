package com.kzxd.pdf;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kzxd.electronicfile.file.BpmMAttachmentDAO;

import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.attachment.BpmAttachmentDAO;

public class PdfViewGDAction {
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
		return "success"; 
	}
	
	//�����ݿ���ȡ���ļ��������
	
	public void getFileAttribute()
	{
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
	    			String pathGd = ba.getFilepath();
	    			pathGd = pathGd.replace("attachment", "electronicfile");
	    		    
	    			this.downloadDir = rootpath + ba.getFilepath();
//	    			 �ļ�����·�� 
	    			this.downloadFile =  rootpath+ba.getFilepath()+ba.getUuid()+".pdf";
	    			this.fileName = ba.getFilename()+".pdf";
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
