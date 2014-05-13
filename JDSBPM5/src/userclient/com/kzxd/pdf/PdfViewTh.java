package com.kzxd.pdf;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.opensymphony.xwork2.Action;

import kzxd.electronicfile.file.CopyFileAction;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.document.BpmDocumentDAO;

public class PdfViewTh implements Action{
	private String docid;
	private String processInstId;
	private String filepath;
	public String rootpath ;
	public String downloadDir ;
	public String downloadFile ;
	public String fileName;
	public InputStream inputStream;
	public String execute() throws Exception {
		return null;
	}
	
	public String writeResponse() throws IOException {
		rootpath= CommonConfig.getValue("bpm.filePath");
		getFileAttribute();//�ȸ���IDȡ�ļ�����
		downloadFile = this.downloadFile.substring(0,downloadFile.lastIndexOf("."))+"_ht.pdf";
		java.io.File file = new java.io.File(downloadFile);
		downloadFile = file.getCanonicalPath();// ��ʵ�ļ�·��,ȥ�������..����Ϣ 
        // ������ͼ���ز��� /download �µ��ļ�, ����ʾ������ 
		if(!file.isFile()) 
		{
			fileName="�ļ�����";
			file.getParentFile().mkdirs();
			file.createNewFile();
		}
		return "success"; 
	}
	
	
	public void getFileAttribute(){
       
        	    Connection conn = null;
	   			DAOFactory factory = null;
	   			try {
	   			DBBeanBase dbbase = new DBBeanBase("bpm",true);
	   	    	conn = dbbase.getConn();
	   	    	BpmDocumentDAO  attdao = new BpmDocumentDAO();
	   	    	attdao.setUuid(docid);
	   	    	
	   	    	factory = new DAOFactory(conn,attdao);
	   	    	List dbfiles = factory.find();
	   	    	if(dbfiles!=null)
	   	    	for(int i=0;i<dbfiles.size();i++){  //ʵ��ֻ��һ��
	    		   BpmDocumentDAO  ba = (BpmDocumentDAO)dbfiles.get(i);//�ļ�����Ŀ¼·�� 
	        	   this.downloadDir = rootpath + ba.getFilepath(); 
	        	   this.downloadFile =  rootpath+ba.getFilepath()+ba.getProcessinstid()+"."+ba.getFiletype();
	   	    	   this.fileName = ba.getFilename()+".pdf";
	   	    	}
	            }catch (Exception e) {
					
					e.printStackTrace();
				}finally{
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   }
	}
	
	public String getProcessInstId() {
		return processInstId;
	}
	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getDocid() {
		return docid;
	}
	public void setDocid(String docid) {
		this.docid = docid;
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
}
