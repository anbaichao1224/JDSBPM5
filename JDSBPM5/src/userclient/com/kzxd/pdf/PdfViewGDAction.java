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

//		 通过 ServletContext，也就是application 来读取数据 
//		ServletActionContext.getResponse().setHeader("Content-Disposition","attachment;filename=" + fileName);
		return new BufferedInputStream(new FileInputStream(downloadFile));
		} 

	public String execute() throws Exception {
		// TODO Auto-generated method stub
		rootpath= CommonConfig.getValue("bpm.filePath");
		getFileAttribute();//先根据ID取文件属性
		java.io.File file = new java.io.File(downloadFile); 
		downloadFile = file.getCanonicalPath();// 真实文件路径,去掉里面的..等信息 
//		 发现企图下载不在 /download 下的文件, 就显示空内容 
		if(!file.isFile()) 
		{
			fileName="文件错误";
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		return "success"; 
	}
	
	//从数据库中取出文件相关属性
	
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
	    		for(int i=0;i<dbfiles.size();i++)//实际只有一条
	    		{
	    			BpmMAttachmentDAO  ba = 	(BpmMAttachmentDAO)dbfiles.get(i);
//	    			 文件下载目录路径 
	    			String pathGd = ba.getFilepath();
	    			pathGd = pathGd.replace("attachment", "electronicfile");
	    		    
	    			this.downloadDir = rootpath + ba.getFilepath();
//	    			 文件下载路径 
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
	
	/** 提供转换编码后的供下载用的文件名 */ 

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
