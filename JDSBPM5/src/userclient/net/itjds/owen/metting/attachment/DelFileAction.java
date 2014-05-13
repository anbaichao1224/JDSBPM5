package net.itjds.owen.metting.attachment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.common.BPMActionBase;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

public class DelFileAction implements Action{
	
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
	 * 根据事项id删除所有附件
	 * 
	 */
	public void  delDataFromDB(String sxxxid)
	{
		
		BpmMAttachmentDAO att = new BpmMAttachmentDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    try{
	    	
	    	conn = dbbase.getConn();
	    	factory = new DAOFactory(conn,att);
	    		att.setSxxxid(sxxxid);
		    	factory.find();
		    	att.batchDelete();
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
	 * 从数据库删除该记录
	 * */
	public void  delDataFormDB(String fid)
	{
		
		BpmMAttachmentDAO att = new BpmMAttachmentDAO();
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
		Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
		BpmMAttachmentDAO att = new BpmMAttachmentDAO();
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
			    	BpmMAttachmentDAO getatt =  (BpmMAttachmentDAO)ls.get(0);
			    	BpmMAttachmentDAO fatt = new BpmMAttachmentDAO();
			    	fatt.setWhereClause("sxxxid='"+getatt.getSxxxid()+"' and creatorid='"+person.getID()+"' and filename='"+getatt.getFilename()+"'");
			    	DAOFactory factory2 = new DAOFactory(conn,fatt);
			    	List fls = factory2.find();
			    	if(fls.size()>0)
			    	{
			    		BpmMAttachmentDAO proceatt =  (BpmMAttachmentDAO)fls.get(0);
			    		fatt.setUuid(proceatt.getUuid());
			    		fatt.delete();
				    	conn.commit();
			    	}
			    	
	    	}
	    	//att.delete();
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
	
	//修改附件的引用id
	public void updateBysxxid(String sxxxid,String matterid){
		Person person = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
		BpmMAttachmentDAO att = new BpmMAttachmentDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm",true);
	    Connection conn = null;
	    DAOFactory factory = null;
	    String path = "";
	    try{
	    	conn = dbbase.getConn();
	    	factory = new DAOFactory(conn,att);
	    		att.setSxxxid(sxxxid);
		    	List ls = factory.find();
		    	try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	if(ls.size()>0)
		    	{
		    		BpmMAttachmentDAO attdao = new BpmMAttachmentDAO();
		    		conn = dbbase.getConn();
		    		DAOFactory factory2 = new DAOFactory(conn,attdao);
		    		attdao.setConnection(conn);
		    		for(int i=0;i<ls.size();i++){
		    			BpmMAttachmentDAO getatt =  (BpmMAttachmentDAO)ls.get(i);
		    			attdao = getatt;
		    			attdao.setSxxxid(matterid);
		    			attdao.update();
		    		}
			    	conn.commit();
			    	try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	}
	    	
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    	
	    }
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
