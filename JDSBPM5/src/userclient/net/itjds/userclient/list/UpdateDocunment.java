
package net.itjds.userclient.list;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.itjds.bpm.client.BPMSessionFactory;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.BPMSessionHandle;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.util.CatalogUtil;
import net.itjds.userclient.common.BPMActionBase;

import net.itjds.userclient.document.BpmDocumentDAO;
import net.itjds.userclient.document.DisplayNameBean;

import com.opensymphony.xwork2.ActionContext;
import net.itjds.userclient.bpm.listener.SendSMSControl;

public class UpdateDocunment extends BPMActionBase{

	private File[] files;
	
	public String execute() throws Exception {
	   
			BpmDocumentDAO docdao = new BpmDocumentDAO();
			DBBeanBase dbbase = new DBBeanBase("bpm", true); 
		    Connection conn = null;
		    DAOFactory factory = null;
		    List files ;
		    try{
    	
		    	conn=dbbase.getConn();
		    	factory = new DAOFactory(conn,docdao);
		    	
				
				String processInstId = this.getProcessInst().getProcessInstId();
				docdao.setActivityinstid(processInstId);
				factory = new DAOFactory(conn, docdao);
				List processInstDocs = factory.find();
				
		    	if (this.getActivityInst()!=null){
		    		docdao.setActivityinstid(this.getActivityInst().getActivityInstId());
				}else if(this.getActivityInstHistory()!=null){
					docdao.setActivityinstid(this.getActivityInstHistory().getActivityInstId());
				}    	
		    	
		    	

		    	files = factory.find();//根据活动ID查看是否有文件
		    	
		    	BpmDocumentDAO processinstdoc = (BpmDocumentDAO) processInstDocs.get(0);
		    	BpmDocumentDAO doc = (BpmDocumentDAO) files.get(0);
		    	String processInstPath = processinstdoc.getFilepath();
		    	String activeInstPath =doc.getFilepath();
			   
		         String realPath = CommonConfig.getValue("bpm.filePath");
		     	CatalogUtil.createFolder(realPath); 
		     	
		     	File[] srcFiles = this.getFiles();
				File srcFile = srcFiles[0];
				
				String uploadActiveInstFolder = CatalogUtil.createFolders(realPath,
						activeInstPath);
				String uploadProcessInstFolder = CatalogUtil.createFolders(realPath,
						processInstPath);
		    		for(int i=0;i<files.size();i++)
		    		{
		    			if (this.getActivityInst()!=null){
		    				
		    				if(this.getActivityInst().getActivityDefId().equals("9EB58C30-77CB-11E1-8C30-82576B8D5DF1_Act3")||
		    						this.getActivityInst().getActivityDefId().equals("9EB58C30-77CB-11E1-8C30-82576B8D5DF1_Act36")||
		    						this.getActivityInst().getActivityDefId().equals("9EB58C30-77CB-11E1-8C30-82576B8D5DF1_Act23")){
		    					
		    				}
		    				else{
		    					this.writeFile(uploadProcessInstFolder+ File.separator + doc.getFilename(),srcFile);
		    				}
		    			}else{
		    				this.writeFile(uploadProcessInstFolder+ File.separator + doc.getFilename(),srcFile);
		    			}
						this.writeFile(uploadActiveInstFolder+ File.separator + doc.getFilename(),srcFile);
						
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
			return null;
		}
	

	private static void writeFile(String src, File file) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {

				in = new BufferedInputStream(new FileInputStream(file));
				out = new BufferedOutputStream(new FileOutputStream(src));
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}

			} finally {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 格式化日期
	 * */
	public String formatDate() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		return sd.format(new Date(System.currentTimeMillis()));

	}

	public File[] getFiles() {
		return files;
	}
	public void setFiles(File[] files) {
		this.files = files;
	}

}
