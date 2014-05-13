package net.itjds.fdt.attachment;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;


import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.attachment.BpmAttachmentDAO;

import com.opensymphony.xwork2.Action;

public class FileListAction implements Action {
	
	private String documentRowId ;
	
	private int totalCount;
	

	
	public List fileList;
	
	public String formId;
	
	public String filePath;
	
	public List<FileListBean> fileBeanList;
	
	public String execute() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=gb2312");
		
	    
	    fileList = getAllFile();
	    if(fileList != null && fileList.size()>0)
	    {
	    	totalCount = fileList.size();
	    	addFileToBeanList();
	    }
	    
	    this.filePath = CommonConfig.getValue("bpm.fileServer")==null?"":CommonConfig.getValue("bpm.fileServer");
		return SUCCESS;
	   
	}
	public List getAllFile()
	{
		 List files  = new ArrayList();
		 Connection conn = null;
		    DAOFactory factory = null;
		    int size = 0; 
		    try{
			  
			    DBBeanBase dbbase = new DBBeanBase("bpm",true);
		    	conn = dbbase.getConn();
		    	BpmAttachmentDAO  attdao = 	new BpmAttachmentDAO();
			    	factory = new DAOFactory(conn,attdao);
			    	attdao.setFormid(formId);   //取出当前流程当前表单上的附件
			    	List dbfiles = factory.find();
			    	size = dbfiles.size();
			    	if(dbfiles != null && size >0)
			    	{
			    		for(int i=0;i<size;i++)
			    		{
			    			BpmAttachmentDAO  ba = 	(BpmAttachmentDAO)dbfiles.get(i);
			    			files.add(ba);   //先把本实例的文件取出来
			    		}
			    	}
			 
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
		    return files;
	}
	
	public void addFileToBeanList() throws PersonNotFoundException
	{
		String realPath = ServletActionContext.getServletContext().getRealPath("");
		fileBeanList = new ArrayList();
		for(int i=0;i<fileList.size();i++)
		{
			FileListBean fb = new FileListBean();
			BpmAttachmentDAO  ba = 	(BpmAttachmentDAO)fileList.get(i);
			fb.setActivityinstid(ba.getActivityinstid());
			fb.setFileindex(ba.getFileindex());
			fb.setFilename(ba.getFilename());
			fb.setFilepath((realPath+ba.getFilepath()+ba.getFilename()).replace("\\", File.separator+File.separator));
			//fb.setFilepath((ba.getFilepath()+ba.getFilename()).replace("\\", "/"));
			fb.setFiletype(ba.getFiletype());
			fb.setFileuploaddate(ba.getFileuploaddate());
			//fb.setFileuploaduser(OrgManagerFactory.getOrgManager().getPersonByID(ba.getFileuploaduser()).getName());
			fb.setUuid(ba.getUuid());
			fb.setRemark(ba.getRemark());
			fb.setProcessinstid(ba.getProcessinstid());
			fileBeanList.add(fb);
		}
		
	}
	

	
	public String getDocumentRowId() {
		return documentRowId;
	}

	public void setDocumentRowId(String documentRowId) {
		this.documentRowId = documentRowId;
	}

	public List getFileList() {
		return fileList;
	}

	public void setFileList(List fileList) {
		this.fileList = fileList;
	}

	/**
	 * @return the fileBeanList
	 */
	public List<FileListBean> getFileBeanList() {
		return fileBeanList;
	}
	/**
	 * @param fileBeanList the fileBeanList to set
	 */
	public void setFileBeanList(List<FileListBean> fileBeanList) {
		this.fileBeanList = fileBeanList;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
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
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
