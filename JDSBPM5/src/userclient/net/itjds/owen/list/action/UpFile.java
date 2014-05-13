package net.itjds.owen.list.action;

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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kzxd.bpm.document.Document;
import com.kzxd.bpm.document.DocumentDao;
import com.opensymphony.xwork2.ActionContext;

import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.util.CatalogUtil;
import net.itjds.userclient.document.BpmDocumentDAO;
import net.itjds.userclient.list.SaveDocumentAcion;
import net.itjds.userclient.list.SelfDocumentAction;


public class UpFile extends BPMActionBase {

	private File file;

	private String fileFileName;

	private String uuid;
	private String processInstId;

	private PrintWriter out;
	
	private String formId;

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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String execute() throws Exception {
		// add begin
		
		String activeInstPath = "";
		try {
				String displayName=fileFileName;
				String types[]=fileFileName.split("\\.");
				int l=types.length-1;
				String type=types[l];
				uuid = new UUID().toString();
				fileFileName = uuid+"."+type;
				HttpServletResponse response = ServletActionContext.getResponse();
				response.setContentType("text/html;charset=utf-8");
				out = response.getWriter();
				String fileName;
				Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH) + 1;
				String rootPath = File.separator + "file" + File.separator
						+ "document" + File.separator;
				String realPath = CommonConfig.getValue("bpm.filePath") + rootPath;
				CatalogUtil.createFolder(realPath);
				
				String 	aInstId = this.getActivityInst().getActivityInstId();
				activeInstPath = year + File.separator + month + File.separator
						+ processInstId + File.separator
						+ aInstId;
				String processInstPath = year + File.separator + month
						+ File.separator + processInstId + File.separator
						+ processInstId; // 做为流程数据保存一份
	
				String uploadActiveInstFolder = CatalogUtil.createFolders(realPath,
						activeInstPath);
				String uploadProcessInstFolder = CatalogUtil.createFolders(
						realPath, processInstPath);
				
				fileName = this.getFileFileName().replaceAll("\"", "");
				
				fileFileName = uuid+"."+type;
				writeFile(uploadActiveInstFolder+File.separator+fileFileName,file);
				saveFileInfo(uuid,rootPath+activeInstPath+File.separator,this.getActivityInst().getActivityInstId(),fileFileName,displayName);//将文件路径写到数据库
				if(this.getProcessInst().getCopyNumber()<=0)//在流程没分散前保存一份原始文件
				{
					//uuid = new UUID().toString();
					writeFile(uploadProcessInstFolder+File.separator+fileFileName,file);
					saveFileInfo(new UUID().toString(),rootPath+processInstPath+File.separator,processInstId,fileFileName,displayName);
				}
				
				//上传后无需打开正文即可发送
				SelfDocumentAction doc = new SelfDocumentAction();
				doc.setActivityInstId(activityInstId);
				doc.getActivityDoc();

				String filenum=doc.getFilenum();
				
				String json="{displayname:'"+displayName+"',uuid:'"+uuid+
							"',aInstId:'"+aInstId+"',formId:'"+formId+
							"',filenum:'"+filenum+"'}";
				out.println(json);
				out.flush();
				out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 文件写入后,将相应的文件信息存入数据库
	 */
	public void saveFileInfo(String uuid,String path, String instId, String filename, String displayName) {
		Person person = (Person) ActionContext.getContext().getValueStack()
				.findValue("$currPerson");
		BpmDocumentDAO docdao = new BpmDocumentDAO();
		DBBeanBase dbbase = new DBBeanBase("bpm", true);
		Connection conn = null;
		DAOFactory factory = null;
		try {

			conn = dbbase.getConn();
//			factory = new DAOFactory(conn, docdao);
//			docdao.setActivityinstid(instId);
//			List files = factory.find();
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
			docdao.setDisplayname(displayName);
//			if (files != null && files.size() > 0) {
//				docdao.update();
//			} else {
				docdao.create();
//			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				dbbase.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
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
	private static void writeFile(String src,File file)  {
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
}
