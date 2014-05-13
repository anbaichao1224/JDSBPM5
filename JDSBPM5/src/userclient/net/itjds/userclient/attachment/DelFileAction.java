package net.itjds.userclient.attachment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.bpm.client.ProcessInst;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.common.BPMActionBase;

import com.opensymphony.xwork2.ActionContext;

public class DelFileAction extends BPMActionBase{
	
	private String activityInstId;
	private String fileName;
	private String fileId;
	public String execute() throws Exception {
		
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=gb2312");
		
		try{
		if(fileId != null && !"".equals(fileId))
		{
			String[] fid = fileId.split(",");
			for(int i=0;i<fid.length;i++)
			{
//				File activefile = new File(rootpath+delDataFormDB(fid[i]));
//				if(activefile.exists())
//				{
//					activefile.delete();  //不删除文件,只删除数据库记录
//				}

				delProcessDataFormDB(fid[i]);
//				delDataFormDB(fid[i]);
			}
		}

		}catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		// TODO Auto-generated method stub
		//return Action.SUCCESS;
		return SUCCESS;
	}
	/**
	 * 从数据库删除该记录
	 * */
	public void  delDataFormDB(String fid)
	{
		
		BpmAttachmentDAO att = new BpmAttachmentDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    try{
	    	
	    	conn = dbbase.getConn();
	    	factory = new DAOFactory(conn,att);
	    	//	att.setWhereClause("activityinstid='"+activityInstId+"' and filename='"+fileName+"'");
	    		att.setUuid(fid);
		    	factory.find();
		    	att.delete();
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
	 * 删除流程数据备份
	 * **/
	public String delProcessDataFormDB(String fid)
	{
		
		BpmAttachmentDAO att = new BpmAttachmentDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    String path = "";
	    try{
	    	conn = dbbase.getConn();
	    	factory = new DAOFactory(conn,att);
	    		att.setUuid(fid);
		    	List ls = factory.find();
		    	if(ls.size()>0)
		    	{
			    	BpmAttachmentDAO getatt =  (BpmAttachmentDAO)ls.get(0);
			    	BpmAttachmentDAO fatt = new BpmAttachmentDAO();
			    	fatt.setWhereClause("processinstid='"+getatt.getProcessinstid()+"' and formid='"+getatt.getFormid()+"' and filename='"+getatt.getFilename()+"' and activityinstid='"+getatt.getProcessinstid()+"'");
			    	DAOFactory factory2 = new DAOFactory(conn,fatt);
			    	List fls = factory2.find();
			    	if(fls.size()>0)
			    	{
			    		BpmAttachmentDAO proceatt =  (BpmAttachmentDAO)fls.get(0);
			    		fatt.setUuid(proceatt.getUuid());
			    		fatt.delete();
				    	conn.commit();
			    	}
			    	
	    	}
	    	att.delete();
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
		return path;
	}
	
	public String getActivityInstId() {
		return activityInstId;
	}

	public void setActivityInstId(String activityInstId) {
		this.activityInstId = activityInstId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

}
