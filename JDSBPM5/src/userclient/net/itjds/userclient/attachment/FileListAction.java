package net.itjds.userclient.attachment;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.data.FormClassBean;
import net.itjds.bpm.engine.BPMException;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.common.BPMActionBase;
import com.opensymphony.xwork2.ActionContext;

public class FileListAction extends BPMActionBase{
	
	private String documentRowId ;
	
	private int totalCount;
	
	private String processInstId;
	private String activeId;
	private ProcessInst proceInst;
	private ActivityInst activeInst;
	public List fileList;
	
	public String formId;
	
	public String filePath;
	
	public List<FileListBean> fileBeanList;
	
	public String execute() throws Exception {
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=gb2312");
		if (this.getActivityInst()!=null){
    		this.activeId = this.getActivityInst().getActivityInstId();
    		this.proceInst = this.getProcessInst();
    		this.activeInst = this.getActivityInst();
		}else if(this.getActivityInstHistory()!=null){
			this.activeId = ((ActivityInstHistory) this.parExprocession("$currActivityInstHistory")).getActivityInstId();
			this.proceInst = this.getActivityInstHistory().getProcessInst();
			this.activeInst = this.getActivityInstHistory().getActivityInst();
		}  
		this.processInstId = proceInst.getProcessInstId();
	    
	    fileList = getAllFile();
	    if(fileList != null && fileList.size()>0)
	    {
	    	totalCount = fileList.size();
	    	addFileToBeanList();
	    }
	    
	    this.filePath = CommonConfig.getValue("bpm.fileServer")==null?"/":CommonConfig.getValue("bpm.fileServer");
		return SUCCESS;
	   
	}
	public List getAllFile()
	{
		 List files  = new ArrayList();
		 Connection conn = null;
		    DAOFactory factory = null;
		    int size = 0; 
		    try{
			   this.formId = getFormName();
			    DBBeanBase dbbase = new DBBeanBase("bpm",true);
		    	conn = dbbase.getConn();
		    	BpmAttachmentDAO  attdao = 	new BpmAttachmentDAO();
			//	if(!activeInst.getState().equals("READ"))//抄送活动另行处理
			//	{
		    	
		    	
			    	factory = new DAOFactory(conn,attdao);
			    	//attdao.setActivityinstid(activeId);
			    	attdao.setProcessinstid(proceInst.getProcessInstId());
			    	attdao.setFormid(formId);   //取出当前流程当前表单上的附件
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
			    	/*if(proceInst.getCopyNumber()>1)//有分散实例时
			    		{
		    		
			    		factory = new DAOFactory(conn,attdao);
				    	attdao.setActivityinstid(proceInst.getProcessInstId());
				    	attdao.setFormid(formId);
				    	List copyfiles = factory.find();
				    	size = copyfiles.size();
				    	if(copyfiles != null && size>0)
				    	{
				    		for(int i=0;i<size;i++)
				    		{
				    			BpmAttachmentDAO  ba = 	(BpmAttachmentDAO)copyfiles.get(i);
				    			if(!this.getActivityInst().getActivityInstId().equals(ba.getRemark()))
				    			files.add(ba);//有分散实例时,取出流程实例文件
				    			
				    		}
			    		    		
			 //   	  }
			    	}	
				 }    	*/
				
				//抄送,应当取本活动的历史数据加上流程数据
				if(dbfiles == null || dbfiles.size()<1) {
			    		factory = new DAOFactory(conn,attdao);
				    	attdao.setActivityinstid(proceInst.getProcessInstId()); 
				    	attdao.setFormid(formId);
				    	List histryfiles = factory.find();
				    	if(histryfiles != null && histryfiles.size()>0)
				    	{
				    		for(int i=0;i<histryfiles.size();i++)
				    		{
				    			BpmAttachmentDAO  ba = 	(BpmAttachmentDAO)histryfiles.get(i);
				    			if(!activeId.equals(ba.getRemark()))
				    				files.add(ba);				    		}
				    	}
				 
				 }
		//	 }
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
			if(ba.getFileuploaduser()==null){
				fb.setFileuploaduser("");
			}else{
			fb.setFileuploaduser(OrgManagerFactory.getOrgManager().getPersonByID(ba.getFileuploaduser()).getName());
			}
			fb.setUuid(ba.getUuid());
			fb.setRemark(ba.getRemark());
			fb.setProcessinstid(ba.getProcessinstid());
			fb.setIsToPdf(ba.getIsToPdf());
			fileBeanList.add(fb);
		}
		
	}
	
	public String getFormName()
	{
		List<FormClassBean> formList = new ArrayList();
		String formName = "";
		try {
			if (this.getActivityInst()!=null){
				formList =this.getActivityInst().getActivityDef().getAllDataFormDef();
			}
		else if(this.getActivityInstHistory()!=null)
			{
				formList =this.getActivityInstHistory().getActivityDef().getAllDataFormDef();
				if(formList == null || formList.size()<1)
				{
					FormClassBean formBean = this.getActivityInstHistory().getActivityDef().getMainFormDef();
					return formBean.getName();
				}
			}
			//formList = this.activeInst.getActivityDef().getAllDataFormDef();
			
			if (formList.size() == 1) {
				formName = formList.get(0).getName();
			}else{
			for (int k = 0; formList.size() > k; k++) {
				FormClassBean formClassBean = formList.get(k);
				if (formClassBean.getId().equals(this.formId)) {
					formName = formClassBean.getName();
				}
			}
			}
			/*FormClassBean formClassBean = this.activityInst.getActivityDef()
					.getMainFormDef();
			formName =  formClassBean.getName();*/
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formName;
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

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
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
