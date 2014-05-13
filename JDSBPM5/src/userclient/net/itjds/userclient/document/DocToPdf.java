package net.itjds.userclient.document;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;

import kzxd.electronicfile.file.CopyFileAction;
import kzxd.utils.File2Pdf;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.usm.action.downLoadfileAction;

public class DocToPdf implements Action {
	
	private String docid;
	private String processInstId;
	private String filepath;
	public String rootpath ;
	public String downloadDir ;
	public String downloadFile ;
	public String fileName;
	public InputStream inputStream;
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	public String convert(){
		rootpath= CommonConfig.getValue("bpm.filePath");
		getFileAttribute();
		String oldfile = this.downloadFile;
		String newfile = this.downloadFile.substring(0, oldfile.lastIndexOf("."))+".pdf";
		new CopyFileAction().fileconvert(oldfile, newfile, oldfile.substring(oldfile.lastIndexOf(".")+1));
		return "showpdf";
	}
	
	public String writeResponse() throws IOException {
		
		rootpath= CommonConfig.getValue("bpm.filePath");
		
		getFileAttribute();//先根据ID取文件属性
		String oldfile = downloadFile;
		String newfile = this.downloadFile.substring(0,downloadFile.lastIndexOf("."))+".pdf";
		File2Pdf.fileconvert(oldfile, newfile, "pdf");
		downloadFile = newfile.substring(0,newfile.lastIndexOf("."))+".pdf";
		java.io.File file = new java.io.File(downloadFile);
		downloadFile = file.getCanonicalPath();// 真实文件路径,去掉里面的..等信息 
		//writeResponse();
//		 发现企图下载不在 /download 下的文件, 就显示空内容 
		if(!file.isFile()) 
		{
			fileName="文件错误";
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
	   	    	for(int i=0;i<dbfiles.size();i++){  //实际只有一条
	    		   BpmDocumentDAO  ba = (BpmDocumentDAO)dbfiles.get(i);//文件下载目录路径 
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
		//return inputStream;
	}
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
}
