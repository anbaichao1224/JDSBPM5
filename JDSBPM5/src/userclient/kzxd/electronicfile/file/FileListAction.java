package kzxd.electronicfile.file;

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
	
	public String recordid;
	
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
		    	BpmMAttachmentDAO  attdao = 	new BpmMAttachmentDAO();
			    	factory = new DAOFactory(conn,attdao);
			    	attdao.setRecordid(recordid);   //取出当前流程当前表单上的附件
			    	List dbfiles = factory.find();
			    	size = dbfiles.size();
			    	if(dbfiles != null && size >0)
			    	{
			    		for(int i=0;i<size;i++)
			    		{
			    			BpmMAttachmentDAO  ba = (BpmMAttachmentDAO)dbfiles.get(i);
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
	
	public BpmMAttachmentDAO findById(String uid){
		Connection conn = null;
	    DAOFactory factory = null; 
	    BpmMAttachmentDAO matt = null;
	    try{
		  
		    DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    	conn = dbbase.getConn();
	    	BpmMAttachmentDAO  attdao = 	new BpmMAttachmentDAO();
		    	factory = new DAOFactory(conn,attdao);
		    	attdao.setUuid(uid);  
		    	 matt = (BpmMAttachmentDAO)factory.findByPrimaryKey();
		    	
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
	    return matt;
	}
	
	public void addFileToBeanList() throws PersonNotFoundException
	{
		String realPath = ServletActionContext.getServletContext().getRealPath("");
		fileBeanList = new ArrayList();
		for(int i=0;i<fileList.size();i++)
		{
			FileListBean fb = new FileListBean();
			BpmMAttachmentDAO  ba = (BpmMAttachmentDAO)fileList.get(i);
			fb.setSxxxid(ba.getRecordid());
			fb.setFilename(ba.getFilename());
			fb.setFilepath((realPath+ba.getFilepath()+ba.getFilename()).replace("\\", File.separator+File.separator));
			fb.setFiletype(ba.getFiletype());
			fb.setCreatorid(ba.getCreatorid());
			fb.setCreatorname(ba.getCreatorname());
			fb.setCreatedate(ba.getCreatedate());
			fb.setIsToPdf(ba.getIsToPdf());
			//fb.setFileuploaduser(OrgManagerFactory.getOrgManager().getPersonByID(ba.getFileuploaduser()).getName());
			fb.setUuid(ba.getUuid());
			fileBeanList.add(fb);
		}
		
	}
	
	
	//取出此流程下的所有附件
	public List getAllByPInstId(String activityInstId){
		List files  = new ArrayList();
		 Connection conn = null;
		    DAOFactory factory = null;
		    int size = 0; 
		    try{
			  
			    DBBeanBase dbbase = new DBBeanBase("bpm",true);
		    	conn = dbbase.getConn();
		    	BpmAttachmentDAO  attdao = 	new BpmAttachmentDAO();
			    	factory = new DAOFactory(conn,attdao);
			    	attdao.setProcessinstid(activityInstId);   //取出当前流程表单上的附件
			    	//attdao.setActivityinstid(activityInstId);
			    	List dbfiles = factory.find();
			    	size = dbfiles.size();
			    	if(dbfiles != null && size >0)
			    	{
			    		for(int i=0;i<size;i++)
			    		{
			    			BpmAttachmentDAO  ba = 	(BpmAttachmentDAO)dbfiles.get(i);
			    			if(ba.getActivityinstid().equals(ba.getProcessinstid())){
			    				continue;
			    			}
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

	public List<FileListBean> getFileBeanList() {
		return fileBeanList;
	}
	public void setFileBeanList(List<FileListBean> fileBeanList) {
		this.fileBeanList = fileBeanList;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public String getRecordid() {
	
		return recordid;
	}
	
	public void setRecordid(String recordid) {
		
		this.recordid = recordid;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
