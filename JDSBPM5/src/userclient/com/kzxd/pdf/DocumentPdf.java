package com.kzxd.pdf;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.document.BpmDocumentDAO;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.Action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

public class DocumentPdf implements Action {

	private String docid;
	private String processInstId;
	private String filepath;
	public String rootpath;
	public String downloadDir;
	public String downloadFile;
	public String fileName;
	public InputStream inputStream;
	private File file;

	public String execute() throws Exception {

		return "success";
	}

	public String savepdfth() {
		rootpath = CommonConfig.getValue("bpm.filePath");
		Connection conn = null;
		DAOFactory factory = null;
		try {
			DBBeanBase dbbase = new DBBeanBase("bpm", true);
			conn = dbbase.getConn();
			BpmDocumentDAO attdao = new BpmDocumentDAO();
			attdao.setUuid(docid);

			factory = new DAOFactory(conn, attdao);
			List dbfiles = factory.find();
			for (int i = 0; i < dbfiles.size(); i++) { // 实际只有一条
				BpmDocumentDAO ba = (BpmDocumentDAO) dbfiles.get(i);// 文件下载目录路径
				
				this.downloadFile = rootpath + ba.getFilepath()
						+ ba.getProcessinstid() + "." + ba.getFiletype();
				writeFile(rootpath + ba.getFilepath()+ ba.getProcessinstid() + "_ht.pdf",file );
			//this.writeFile("d:\\src\\_ht.pdf",file);
			}
		} catch (Exception e) {

			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				
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

	public String getDocid() {
		return docid;
	}

	public void setDocid(String docid) {
		this.docid = docid;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getRootpath() {
		return rootpath;
	}

	public void setRootpath(String rootpath) {
		this.rootpath = rootpath;
	}
	
	

}
