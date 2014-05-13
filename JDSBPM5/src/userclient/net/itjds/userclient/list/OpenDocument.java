package net.itjds.userclient.list;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kzxd.documentmodel.entity.KZXDDocumentModel;
import kzxd.documentmodel.servers.KzxdDocumentServer;
import kzxd.documentmodel.servers.impl.KzxdDocumentServerImpl;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.fdt.define.designer.metadata.bean.DocBasicBean;
import net.itjds.fdt.define.designer.metadata.bean.DocumentBean;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.util.CatalogUtil;
import net.itjds.userclient.document.BpmDocumentDAO;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

public class OpenDocument extends BPMActionBase {

	private String docid;

	private String readonly; // 只读

	private String copydoc;// 拷贝

	private String markModify;// 保留痕迹

	private String autoAcceptRevisions;// 自动接收修订
	private String filePath;
	private Person person;
	private String docuuid;
	private String processInstId;
	private String uuid;
	private String filenum;
	private String yiban;
	private String daiding;
	private String fileName;
	//套红
	private String taoHong;
	private String taoHongId;
	public String execute() throws Exception {
		
		this.filePath = CommonConfig.getValue("bpm.fileServer");
		if (this.filePath == null) {
			HttpServletRequest request = ServletActionContext.getRequest();
			this.filePath = "http://" + request.getServerName() + ":"
					+ request.getServerPort() + "/";
		}
		DocumentBean docbean = (DocumentBean) ActionContext.getContext()
				.getValueStack().findValue("$docInject.getDocBean()");

		if (docbean != null) {

			DocBasicBean docbasic = docbean.getDocbasic();
			if (docbasic != null) {
				if (docbasic.getReadonly() != null
						&& !"".equals(docbasic.getReadonly())) {
					this.readonly = docbasic.getReadonly();
				}
				if (docbasic.getCopydoc() != null
						&& !"".equals(docbasic.getCopydoc())) {
					this.copydoc = docbasic.getCopydoc();
				}
				if (docbasic.getMarkModify() != null
						&& !"".equals(docbasic.getMarkModify())) {
					this.markModify = docbasic.getMarkModify();
				}
				if (docbasic.getAutoAcceptRevisions() != null
						&& !"".equals(docbasic.getAutoAcceptRevisions())) {
					this.autoAcceptRevisions = docbasic
							.getAutoAcceptRevisions();
				}
				if ((docbasic.getTaoHong() != null)
						&& (!("".equals(docbasic.getTaoHong())))) {
					this.taoHong = docbasic.getTaoHong();
				}
			}
		}
		if (filenum != null && !"".equals(filenum)) {
			if (Integer.parseInt(filenum) == 0) {
				uploadmodel();
			}
		}
	    daiding =  this.getActivityInst().getState();
		Docinclude di = new Docinclude();
		di.setDocid(docid);
		di.getActivityDoc();
		fileName = di.getFileName();
		return SUCCESS;
	}

	private void uploadmodel() {
		KzxdDocumentServer server = new KzxdDocumentServerImpl();
		KZXDDocumentModel model = server.findByUUID(docuuid);
		String syspath = CommonConfig.getValue("bpm.filePath");
		String docpath = model.getModelPath();
		String modelfilePath = syspath + docpath + model.getModelName();
		String file = modelfilePath;
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		String rootPath = File.separator + "file" + File.separator + "document" + File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath") + rootPath;
		CatalogUtil.createFolder(realPath);
		processInstId = this.getProcessInst().getProcessInstId();
		String types[]=model.getModelName().split("\\.");
		int l=types.length-1;
		String type=types[l];
		String fileFileName = docid+"."+type;
		String activeInstPath = year + File.separator + month + File.separator
				+ processInstId + File.separator
				+ this.getActivityInst().getActivityInstId();
		String processInstPath = year + File.separator + month
				+ File.separator + processInstId + File.separator
				+ processInstId; // 做为流程数据保存一份

		String uploadActiveInstFolder = CatalogUtil.createFolders(realPath,
				activeInstPath);
		String uploadProcessInstFolder = CatalogUtil.createFolders(
				realPath, processInstPath);
		writeFile(uploadActiveInstFolder+File.separator + fileFileName, file);
		                                                   
		if(this.getProcessInst().getCopyNumber()<=0)//在流程没分散前保存一份原始文件
		{
			writeFile(uploadProcessInstFolder+File.separator + fileFileName, file);
		}
			
		saveFileInfo(docid,rootPath+activeInstPath+File.separator,this.getActivityInst().getActivityInstId(), fileFileName,type);//将文件路径写到数据库

	}

	public void saveFileInfo(String uuid,String path, String instId, String filename,String type) {
		Person person = (Person) ActionContext.getContext().getValueStack()
				.findValue("$currPerson");
		BpmDocumentDAO docdao = new BpmDocumentDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try {

			conn = dbbase.getConn();
			factory = new DAOFactory(conn, docdao);
			docdao.setActivityinstid(instId);
			List files = factory.find();
			factory = new DAOFactory(conn, docdao);
			factory.setDAO(docdao);
			docdao.setConnection(conn);
			docdao.setUuid(uuid);
			docdao.setProcessinstid(processInstId);
			docdao.setActivityinstid(instId);
			docdao.setFilepath(path);
			docdao.setFilename(filename);
			docdao.setFiletype(getFileExt(filename));
			docdao.setCreateuser(person.getID());
			docdao.setCreatedate(formatDate());
			docdao.setFileindex(1);
			docdao.setRemark("");
			docdao.setDisplayname("正文."+type);
			if (files != null && files.size() > 0) {
				docdao.update();
			} else {
				docdao.create();
			}
			conn.commit();
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
	
	
	private void writeFile(String src,String file)  {
        try  {
           InputStream in = null ;
           OutputStream out = null ;
            try  {                
           	 
           	in = new BufferedInputStream(new FileInputStream(file));
               out = new BufferedOutputStream( new FileOutputStream(src));
               byte[] buffer = new byte[1024];
                int len = 0; 
                while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len); 
                }

            } finally  {
                if ( null != in)  {
                   in.close();
               } 
                 if ( null != out)  {
                   out.close();
               } 
           } 
        } catch (Exception e)  {
           e.printStackTrace();
       } 
   } 
	
	
	public String formatDate() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		return sd.format(new Date(System.currentTimeMillis()));

	}

	public String getFileExt(String filename) {

		return filename.substring(filename.lastIndexOf(".") + 1);
	}

	/**
	 * @return the autoAcceptRevisions
	 */
	public String getAutoAcceptRevisions() {
		return autoAcceptRevisions;
	}

	/**
	 * @return the copydoc
	 */
	public String getCopydoc() {
		return copydoc;
	}

	/**
	 * @return the docid
	 */
	public String getDocid() {
		return docid;
	}

	/**
	 * @return the markModify
	 */
	public String getMarkModify() {
		return markModify;
	}

	/**
	 * @return the readonly
	 */
	public String getReadonly() {
		return readonly;
	}

	/**
	 * @param autoAcceptRevisions
	 *            the autoAcceptRevisions to set
	 */
	public void setAutoAcceptRevisions(String autoAcceptRevisions) {
		this.autoAcceptRevisions = autoAcceptRevisions;
	}

	/**
	 * @param copydoc
	 *            the copydoc to set
	 */
	public void setCopydoc(String copydoc) {
		this.copydoc = copydoc;
	}

	/**
	 * @param docid
	 *            the docid to set
	 */
	public void setDocid(String docid) {
		this.docid = docid;
	}

	/**
	 * @param markModify
	 *            the markModify to set
	 */
	public void setMarkModify(String markModify) {
		this.markModify = markModify;
	}

	/**
	 * @param readonly
	 *            the readonly to set
	 */
	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		person = (Person) ActionContext.getContext().getValueStack().findValue(
				"$currPerson");
		return person;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	/**
	 * @return the filePath
	 */
	public String getFilePath() {
		return filePath;
	}

	/**
	 * @param filePath
	 *            the filePath to set
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDocuuid() {
		return docuuid;
	}

	public void setDocuuid(String docuuid) {
		this.docuuid = docuuid;
	}

	public String getProcessInstId() {
		return processInstId;
	}

	public void setProcessInstId(String processInstId) {
		this.processInstId = processInstId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFilenum() {
		return filenum;
	}

	public void setFilenum(String filenum) {
		this.filenum = filenum;
	}

	public String getYiban() {
		return yiban;
	}

	public void setYiban(String yiban) {
		this.yiban = yiban;
	}

	public String getTaoHong() {
		return taoHong;
	}

	public void setTaoHong(String taoHong) {
		this.taoHong = taoHong;
	}

	public String getTaoHongId() {
		return taoHongId;
	}

	public void setTaoHongId(String taoHongId) {
		this.taoHongId = taoHongId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDaiding() {
		return daiding;
	}

	public void setDaiding(String daiding) {
		this.daiding = daiding;
	}
	
}
