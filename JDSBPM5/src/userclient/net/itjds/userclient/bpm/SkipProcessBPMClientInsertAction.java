/**
 * $RCSfile: SkipProcessBPMClientInsertAction.java,v $
 * $Revision: 1.1 $
 * $Date: 2011/06/09 14:41:59 $
 *
 * Copyright (C) 2003 itjds, Inc. All rights reserved.
 *
 * This software is the proprietary information of itjds, Inc.
 * Use is subject to license terms.
 */
package net.itjds.userclient.bpm;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.client.ActivityInstHistory;
import net.itjds.bpm.client.ProcessInst;
import net.itjds.bpm.data.FormClassBean;
import net.itjds.bpm.engine.BPMException;
import net.itjds.bpm.engine.OARightConstants;
import net.itjds.bpm.engine.WorkflowClientService;
import net.itjds.bpm.engine.database.right.DbActivityDefRight;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.OrgManagerFactory;
import net.itjds.common.org.base.Person;
import net.itjds.common.org.base.PersonNotFoundException;
import net.itjds.j2ee.dao.DAOException;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.userclient.common.util.CatalogUtil;
import net.itjds.userclient.document.dao.BpmDocumentDAO;

/**
 * <p>
 * Title: BPM工作流管理系统
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company: www.justdos.net
 * </p>
 * 
 * @author wenzhangli
 * @version 2.0
 */
public class SkipProcessBPMClientInsertAction extends BPMActionBase {

	private String processDefId;

	private String processName;

	private String activityInstId;

	private String parentActivityInstHistoryId;

	private String startActivityName;

	private ProcessInst praentProcess;

	private String praentActivityinstId;

	String uploadProcessInstFolder;

	String uploadActiveInstFolder;

	String saveProcessInstFolder;

	String saveActiveInstFolder;

	String realPath;

	String formid;  //实际为formName
	String praentFormId;
	
	

	/**
	 * 根据定义判断是否需要出现选人窗口
	 * @param toActivityDefId
	 * @return
	 * @throws BPMException
	 */
	public boolean isNoNeedSelect(String toActivityDefId) throws BPMException{
		String performType=(String) this.getClient().getActivityDefRightAttribute(toActivityDefId, OARightConstants.ACTIVITYDEF_RIGHT_ATT_PERFORMTYPE, null);
		if (performType.equals(OARightConstants.PERFORMTYPE_NEEDNOTSELECT)||performType.equals(OARightConstants.PERFORMTYPE_NOSELECT)){
			return true	;
		}	
		
	return false;
    }

	/**
	 * 根据定义判断如果下下一活动中代办人中有当前办理人则直接打开进行办理
	 * @param toActivityDefId
	 * @return
	 * @throws BPMException
	 * @throws PersonNotFoundException
	 */
	public boolean isContinuous(String toActivityDefId) throws BPMException, PersonNotFoundException{
		List<Person> personList=(List<Person>) this.getClient().getActivityDefRightAttribute(toActivityDefId, DbActivityDefRight.ATT_PERFORMERSELECTEDID, null);
		 Person person=OrgManagerFactory.getOrgManager().getPersonByID(this.getClient().getConnectInfo().getUserID());
		 if (personList.contains(person)){
			 return true;
		 }
		 return false;
	}
	
	
	

	public String getStartActivityName() {
		return startActivityName;
	}

	public void setStartActivityName(String startActivityName) {
		this.startActivityName = startActivityName;
	}

	public void setProcessDefId(String processDefId) {
		this.processDefId = processDefId;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getActivityInstId() {
		return this.activityInstId;
	}

	public String execute() {

		try {
			ProcessInst processInst = null;
			ActionContext context = ActionContext.getContext();
			BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
			WorkflowClientService client = null;
			client = bpmUserClientUtil.getClient();
			client.beginTransaction();

			processInst = client.newProcess(processDefId, processName, null,
					null);
			super.processInst = processInst;
			ActivityInst activityInst = (ActivityInst) processInst
					.getActivityInstList().get(0);

			formid = getFormName(activityInst);
			// System.out.println(form);
			this.activityInstId = activityInst.getActivityInstId();
			String processInstId = processInst.getProcessInstId();
			this.init();
			client.commitTransaction();
			//ActivityInstHistory actityInsthistory = new BPMUserClientUtil().getClient().getActivityInstHistory(parentActivityInstHistoryId);
			ActivityInst activityInst1 = new BPMUserClientUtil().getClient().getActivityInst(this.parentActivityInstHistoryId);
		    this.praentProcess = activityInst1.getProcessInst();
		    this.praentActivityinstId = activityInst1.getActivityInstId();
		    this.praentFormId = getParentFormName(activityInst1);
			List doc = getPraentDocument();
			List att = getPraentAtta();
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			String rootPath = File.separator + "file" + File.separator
					+ "attachment" + File.separator;
			realPath = CommonConfig.getValue("bpm.filePath") + rootPath;
			File tmpPathFile = new File(realPath);
			if (!tmpPathFile.exists() || !tmpPathFile.isDirectory()) {
				tmpPathFile.mkdirs();
			}
			String activeInstPath = year + "\\" + month + "\\" + processInstId
					+ "\\" + activityInstId;
			String processInstPath = year + "\\" + month + "\\" + processInstId
					+ "\\" + processInstId; // 做为流程数据保存一份
			uploadActiveInstFolder = CatalogUtil.createFolders(realPath,
					activeInstPath);
			uploadProcessInstFolder = CatalogUtil.createFolders(realPath,
					processInstPath);
			saveActiveInstFolder = rootPath + activeInstPath + File.separator;
			saveProcessInstFolder = rootPath + processInstPath + File.separator;

			if (doc != null && doc.size() > 0)// 原流程正文
			{
				saveDocument2Process(doc);
			}
			if (att != null && att.size() > 0)// 原流程附件
			{
				saveAtt2Process(att);
			}

		} catch (Exception e) {
			e.printStackTrace();
			return this.ERROR;
		}

		return this.SUCCESS;
	}

	// 将正文保存到新流程做为附件
	public void saveDocument2Process(List li) {
		Connection conn = null;
		DAOFactory factory = null;
		try {
			DBBeanBase dbbase = new DBBeanBase("bpm", true);
			conn = dbbase.getConn();
			for (int i = 0; i < li.size(); i++) {
				BpmDocumentDAO bdd = (BpmDocumentDAO) li.get(i);
				String activtyFid = saveActivity2DB(conn, bdd);// 先保存一份为活动数据------------------数据库
				String ProcessFid = saveProcessInst2DB(conn, bdd);// 这是流程的第一步,保存一份为流程数据---数据库
				File file = new File(CommonConfig.getValue("bpm.filePath")
						+ bdd.getFilepath() + bdd.getFilename());
				writeFile(uploadActiveInstFolder + File.separator + activtyFid
						+ "." + bdd.getFiletype(), file);
				writeFile(uploadProcessInstFolder + File.separator + ProcessFid
						+ "." + bdd.getFiletype(), file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 将附件保存到新流程
	public String saveAtt2Process(List li) {
		Connection conn = null;
		DAOFactory factory = null;
		String fileid = "";
		try {
			DBBeanBase dbbase = new DBBeanBase("bpm", true);
			conn = dbbase.getConn();
			for (int i = 0; i < li.size(); i++) {
				BpmAttachmentDAO bad = (BpmAttachmentDAO) li.get(i);
				String activtyFid = saveActivity2DB(conn, bad);// 先保存一份为活动数据------------------数据库
				String ProcessFid = saveProcessInst2DB(conn, bad);// 这是流程的第一步,保存一份为流程数据---数据库
				File file = new File(CommonConfig.getValue("bpm.filePath")
						+ bad.getFilepath() + bad.getUuid() + "."
						+ bad.getFiletype());
				writeFile(uploadActiveInstFolder + File.separator + activtyFid
						+ "." + bad.getFiletype(), file);
				writeFile(uploadProcessInstFolder + File.separator + ProcessFid
						+ "." + bad.getFiletype(), file);

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileid;
	}

	// 保存数据到新的流程实例 ---附件
	public String saveActivity2DB(Connection conn, BpmAttachmentDAO bad) {
		String fileid = new UUID().toString();
		try {
			BpmAttachmentDAO attdao = new BpmAttachmentDAO();
			DAOFactory factory = new DAOFactory(conn, attdao);
			factory.setDAO(attdao);
			attdao.setConnection(conn);
			attdao.setUuid(fileid);
			attdao.setProcessinstid(processInst.getProcessInstId());
			attdao.setActivityinstid(activityInstId);
			attdao.setFilepath(saveActiveInstFolder);
			attdao.setFormid(formid);
			attdao.setFilename(bad.getFilename());
			attdao.setFiletype(getFileExt(bad.getFilename()));
			attdao.setFileuploaduser(bad.getFileuploaduser());
			attdao.setFileuploaddate(formatDate());
			attdao.setFileindex(1);
			attdao.setRemark("");
			attdao.create();
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileid;
	}

	// 保存数据作为流程数据 ---附件

	public String saveProcessInst2DB(Connection conn, BpmAttachmentDAO bad) {
		String fileid = new UUID().toString();
		try {
			BpmAttachmentDAO attdao = new BpmAttachmentDAO(); // 作为流程数据再保存一份
			DAOFactory factory = new DAOFactory(conn, attdao);
			factory.setDAO(attdao);
			attdao.setConnection(conn);
			attdao.setUuid(fileid);
			attdao.setProcessinstid(processInst.getProcessInstId());
			attdao.setActivityinstid(processInst.getProcessInstId());
			attdao.setFilepath(saveProcessInstFolder);
			attdao.setFormid(formid);
			attdao.setFilename(bad.getFilename());
			attdao.setFiletype(getFileExt(bad.getFilename()));
			attdao.setFileuploaduser(bad.getFileuploaduser());
			attdao.setFileuploaddate(formatDate());
			attdao.setFileindex(1);
			attdao.setRemark(processInst.getProcessInstId());
			attdao.create();
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileid;
	}

	// 保存数据到新的流程实例---正文
	public String saveActivity2DB(Connection conn, BpmDocumentDAO bdd) {
		String fileid = new UUID().toString();
		try {
			BpmAttachmentDAO attdao = new BpmAttachmentDAO();
			DAOFactory factory = new DAOFactory(conn, attdao);
			factory.setDAO(attdao);
			attdao.setConnection(conn);
			attdao.setUuid(fileid);
			attdao.setProcessinstid(processInst.getProcessInstId());
			attdao.setActivityinstid(activityInstId);
			attdao.setFilepath(saveActiveInstFolder);
			attdao.setFormid(formid);
			attdao.setFilename("正文." + bdd.getFiletype());
			attdao.setFiletype(bdd.getFiletype());
			attdao.setFileuploaduser(bdd.getCreateuser());
			attdao.setFileuploaddate(formatDate());
			attdao.setFileindex(1);
			attdao.setRemark("");
			attdao.create();
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileid;
	}

	// 保存数据作为流程数据---正文

	public String saveProcessInst2DB(Connection conn, BpmDocumentDAO bdd) {
		String fileid = new UUID().toString();
		try {
			BpmAttachmentDAO attdao = new BpmAttachmentDAO(); // 作为流程数据再保存一份
			DAOFactory factory = new DAOFactory(conn, attdao);
			factory.setDAO(attdao);
			attdao.setConnection(conn);
			attdao.setUuid(fileid);
			attdao.setProcessinstid(processInst.getProcessInstId());
			attdao.setActivityinstid(processInst.getProcessInstId());
			attdao.setFilepath(saveProcessInstFolder);
			attdao.setFormid(formid);
			attdao.setFilename("正文." + bdd.getFiletype());
			attdao.setFiletype(bdd.getFiletype());
			attdao.setFileuploaduser(bdd.getCreateuser());
			attdao.setFileuploaddate(formatDate());
			attdao.setFileindex(1);
			attdao.setRemark(processInst.getProcessInstId());
			attdao.create();
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileid;
	}

	// 将原流程中的正文拿到新流程中
	public List getPraentDocument() {
		Connection conn = null;
		DAOFactory factory = null;
		int size = 0;
		List files = new ArrayList();
		List file = new ArrayList();
		try {
			DBBeanBase dbbase = new DBBeanBase("bpm", true);
			conn = dbbase.getConn();
			BpmDocumentDAO bd = new BpmDocumentDAO();
			factory = new DAOFactory(conn, bd);
			bd.setActivityinstid(praentActivityinstId);
			file = factory.find();
			size = file.size();
			if (file != null && size > 0) {
				for (int i = 0; i < size; i++) {
					BpmDocumentDAO bdd = (BpmDocumentDAO) file.get(i);
					files.add(bdd);

				}
			}
			if (praentProcess.getCopyNumber() > 1)// 有分散实例
			{
				factory = new DAOFactory(conn, bd);
				bd.setActivityinstid(praentProcess.getProcessInstId());
				file = factory.find();
				size = file.size();
				if (file != null && size > 0) {
					for (int i = 0; i < size; i++) {
						BpmDocumentDAO bdd = (BpmDocumentDAO) file.get(i);

						files.add(bdd);

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return files;
	}

	// 将原流程中的附件拿到新流程中
	public List getPraentAtta() {
		Connection conn = null;
		DAOFactory factory = null;
		int size = 0;
		List files = new ArrayList();
		List file = new ArrayList();
		try {
			DBBeanBase dbbase = new DBBeanBase("bpm", true);
			conn = dbbase.getConn();
			BpmAttachmentDAO attdao = new BpmAttachmentDAO();
			factory = new DAOFactory(conn, attdao);
			attdao.setActivityinstid(praentActivityinstId);
			attdao.setFormid(praentFormId);
			file = factory.find();
			size = file.size();
			if (file != null && size > 0) {
				for (int i = 0; i < size; i++) {
					BpmAttachmentDAO ba = (BpmAttachmentDAO) file.get(i);
					files.add(ba);

				}
			}
			if (praentProcess.getCopyNumber() > 1)// 有分散实例
			{
				factory = new DAOFactory(conn, attdao);
				attdao.setActivityinstid(praentProcess.getProcessInstId());
				attdao.setFormid(praentFormId);
				file = factory.find();
				size = file.size();
				if (file != null && size > 0) {
					for (int i = 0; i < size; i++) {
						BpmAttachmentDAO ba = (BpmAttachmentDAO) file.get(i);
						files.add(ba);

					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return files;
	}

	/**
	 * 将文件写入本地文件夹
	 */
	private static void writeFile(String src, File file) {
		try {
			InputStream in = null;
			OutputStream out = null;
			try {
				in = new BufferedInputStream(new FileInputStream(file));
				out = new BufferedOutputStream(new FileOutputStream(src));
				byte[] buffer = new byte[(int) file.length()];
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
	 * 获取文件后缀名
	 */
	public String getFileExt(String filename) {

		return filename.substring(filename.lastIndexOf(".") + 1);
	}

	/**
	 * 格式化日期
	 */
	public String formatDate() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		return sd.format(new Date(System.currentTimeMillis()));

	}

	/**
	 * 新流程的表单名
	 */
	public String getFormName(ActivityInst activityInst) {
		List<FormClassBean> formList = new ArrayList();
		String formName = "";
		try {
			formList = activityInst.getActivityDef().getAllDataFormDef();
			if (formList.size() == 1) {
				formName = formList.get(0).getName();
			} else {
				for (int k = 0; formList.size() > k; k++) {
					FormClassBean formClassBean = formList.get(k);
					if (formClassBean.getId().equals(this.getFormID())) {
						formName = formClassBean.getName();
					}
				}
			}
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formName;
	}
	/**
	 * 原流程的表单名
	 */
	public String getHisFormName(ActivityInstHistory activityHisInst) {
		List<FormClassBean> formList = new ArrayList();
		String formName = "";
		try {
			formList =activityHisInst.getActivityDef().getAllDataFormDef();
			if(formList == null || formList.size()<1)
			{
				FormClassBean formBean = activityHisInst.getActivityDef().getMainFormDef();
				return formBean.getName();
			}
			if (formList.size() == 1) {
				formName = formList.get(0).getName();
			} else {
				for (int k = 0; formList.size() > k; k++) {
					FormClassBean formClassBean = formList.get(k);
					if (formClassBean.getId().equals(this.getFormID())) {
						formName = formClassBean.getName();
					}
				}
			}
		} catch (BPMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formName;
	}
	  public String getParentFormName(ActivityInst activityHisInst)
	  {
	    List formList = new ArrayList();
	    String formName = "";
	    try {
	      formList = activityHisInst.getActivityDef().getAllDataFormDef();
	      if ((formList == null) || (formList.size() < 1))
	      {
	        FormClassBean formBean = activityHisInst.getActivityDef().getMainFormDef();
	        return formBean.getName();
	      }
	      if (formList.size() == 1) {
	        formName = ((FormClassBean)formList.get(0)).getName();
	      }
	      for (int k = 0; formList.size() > ++k; ) {
	        FormClassBean formClassBean = (FormClassBean)formList.get(k);
	        if (formClassBean.getId().equals(getFormID())) {
	          formName = formClassBean.getName();
	        }
	      }
	    }
	    catch (BPMException e)
	    {
	      e.printStackTrace();
	    }
	    label148: return formName;
	  }
	/**
	 * @return the praentProcess
	 */
	public ProcessInst getPraentProcess() {
		return praentProcess;
	}

	/**
	 * @param praentProcess
	 *            the praentProcess to set
	 */
	public void setPraentProcess(ProcessInst praentProcess) {
		this.praentProcess = praentProcess;
	}

	/**
	 * @return the parentActivityInstHistoryId
	 */
	public String getParentActivityInstHistoryId() {
		return parentActivityInstHistoryId;
	}

	/**
	 * @param parentActivityInstHistoryId
	 *            the parentActivityInstHistoryId to set
	 */
	public void setParentActivityInstHistoryId(
			String parentActivityInstHistoryId) {
		this.parentActivityInstHistoryId = parentActivityInstHistoryId;
	}

	/**
	 * @return the praentFormId 
	 */
	public String getPraentFormId() {
		return praentFormId;
	}

	/**
	 * @param praentFormId the praentFormId to set
	 */
	public void setPraentFormId(String praentFormId) {
		this.praentFormId = praentFormId;
	}

}
