package net.itjds.userclient.bpm.listener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kzxd.utils.File2Pdf;
import kzxd.zwxg.attachment.ZwxgUploadAttachmentAction;

import org.apache.struts2.ServletActionContext;

import com.kzxd.bpm.document.Document;
import com.kzxd.bpm.document.DocumentDao;
import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.client.event.ActivityEvent;
import net.itjds.bpm.client.event.ActivityListener;
import net.itjds.bpm.engine.BPMException;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.common.util.CatalogUtil;
import net.itjds.userclient.document.BpmDocumentDAO;
import net.itjds.userclient.document.SaveDocumentAction;
import net.itjds.userclient.list.SelfDocumentAction;

public class DrblShowListener implements ActivityListener {
	
	private Document doc;

	public void activityActived(ActivityEvent arg0) throws BPMException {

	}

	public void activityActiving(ActivityEvent arg0) throws BPMException {

	}

	public void activityCompleted(ActivityEvent event) throws BPMException {
		
		ActivityInst ainst = event.getActivityInsts()[0];
		if(!ainst.getReceiveMethod().equals(ActivityInst.RECEIVEMETHOD_RESEND)){
		if(event.getActivityInsts().length>0){
			ProcessInst pinst = event.getActivityInsts()[0].getProcessInst();
			String processInstId = pinst.getProcessInstId();
		    //String aInstId = pinst.getActivityInstHistoryListByProcessInst().get(0).getActivityInstId();
		
		
			String formId = ainst.getActivityDef().getMainFormDef().getId();
			String formName = ainst.getActivityDef().getMainFormDef().getName();		
			
			String activityInstId = ainst.getActivityInstId();
			
			ZwxgUploadAttachmentAction zupload = new ZwxgUploadAttachmentAction();
			zupload.setActivityInstId(activityInstId);
			Calendar c =  Calendar.getInstance() ;
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH)+1;
			String rootPath = File.separator+"file"+File.separator+"attachment"+File.separator;
			String realPath = CommonConfig.getValue("bpm.filePath")+rootPath;
			String docrootPath = File.separator+"file"+File.separator+"document"+File.separator;
			CatalogUtil.createFolder(realPath);
			String activeInstPath = year+File.separator+month+File.separator+processInstId+File.separator+activityInstId+File.separator+formId;
			String processInstPath = year+File.separator+month+File.separator+processInstId+File.separator+processInstId+formId;  //做为流程数据保存一份
			String uploadActiveInstFolder = CatalogUtil.createFolder(realPath+activeInstPath);
			String uploadProcessInstFolder = CatalogUtil.createFolder(realPath+processInstPath);
			
			SelfDocumentAction document = new SelfDocumentAction();
			List doclist = document.getProcessInstIdDoc(processInstId, activityInstId);
			List docfileall = document.getProcessInstIdDoc1(processInstId);
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession(true);
			String personId = session.getAttribute("personId").toString();
			Person person = null;
			try {
				person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			} catch (PersonNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String docfile=null;
			for(int i=0;i<docfileall.size();i++){
				BpmDocumentDAO docdao = ((BpmDocumentDAO)docfileall.get(i));
				docfile = CommonConfig.getValue("bpm.filePath")+docdao.getFilepath()+docdao.getFilename();
			}
		
			for(int i=0;i<doclist.size();i++){
				BpmDocumentDAO docdao = ((BpmDocumentDAO)doclist.get(i));
				String type = docdao.getFiletype();
				
				zupload.setActivityInstId(activityInstId);
				String fileid = zupload.saveFileInfo(rootPath+activeInstPath+File.separator,activityInstId,docdao.getDisplayname().substring(0, docdao.getDisplayname().lastIndexOf("."))
+"-"+person.getName()+"-修改"+"."+type,formName,processInstId);
				String oldfile = CommonConfig.getValue("bpm.filePath")+docdao.getFilepath()+docdao.getFilename();
				File file = new File(oldfile);
				if(file==null){
					continue;
				}
				File filedoc = new File(docfile);
				if(filedoc==null){
					continue;
				}
				String newfile = uploadActiveInstFolder+File.separator+fileid+"."+zupload.getFileExt(docdao.getFilename());
				zupload.writeFile(newfile,file);
				zupload.writeFile(oldfile,filedoc);
				
			
			}
		
			
		}
		
	}
	}
	public void activityCompleting(ActivityEvent arg0) throws BPMException {
		ProcessInst pinst = arg0.getActivityInsts()[0].getProcessInst();
		if(pinst.getCopyNumber()>1){
		}

	}

	public void activityDisplay(ActivityEvent event) throws BPMException {

	}

	public void activityFormSaveed(ActivityEvent arg0) throws BPMException {

	}

	public void activityFormSaveing(ActivityEvent arg0) throws BPMException {

	}

	public void activityInited(ActivityEvent event) throws BPMException {

	}

	public void activityJoined(ActivityEvent event) throws BPMException {
		

	}

	public void activityJoining(ActivityEvent arg0) throws BPMException {

	}

	public void activityOutFlowReturned(ActivityEvent arg0) throws BPMException {

	}

	public void activityOutFlowReturning(ActivityEvent arg0)
			throws BPMException {

	}

	public void activityOutFlowed(ActivityEvent arg0) throws BPMException {

	}

	public void activityOutFlowing(ActivityEvent arg0) throws BPMException {

	}

	public void activityResumed(ActivityEvent arg0) throws BPMException {

	}

	public void activityResuming(ActivityEvent arg0) throws BPMException {

	}

	public void activityRouted(ActivityEvent event) throws BPMException {
		

	}

	public void activityRouting(ActivityEvent event) throws BPMException {

	}

	public void activitySplited(ActivityEvent arg0) throws BPMException {
	}

	public void activitySpliting(ActivityEvent arg0) throws BPMException {

	}

	public void activitySuspended(ActivityEvent arg0) throws BPMException {

	}

	public void activitySuspending(ActivityEvent arg0) throws BPMException {

	}

	public void activityTakebacked(ActivityEvent arg0) throws BPMException {

	}

	public void activityTakebacking(ActivityEvent arg0) throws BPMException {

	}
	

}
