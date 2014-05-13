package net.itjds.usm2.action;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.UUID;


import org.apache.struts2.ServletActionContext;


import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class FileAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File file;

	private String fileFileName;

	private String path;
	
	private String newFileName;
	

	private String jsonString;


	public String getPath() {
		return path;
	}



	public void setPath(String path) {
		this.path = path;
	}
	public String execute() throws Exception {
		path="d:";
		fileupload(file,path,fileFileName);
		return Action.SUCCESS;
		
	}


	/**
	 *  �ļ��ϴ�
	 * @param file
	 * @param path �ϴ�·��
	 * @param fileName �ϴ��ļ���
	 * @return �µ��ļ���
	 * @throws Exception
	 */
	public static  String fileupload(File file, String path, String fileName)
			throws Exception {

		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}

		int index = fileName.lastIndexOf('.');
		String type = fileName.substring(index + 1, fileName.length());
		String newfileName = UUID.randomUUID() + "." + type;
		BufferedOutputStream bos = null;
		BufferedInputStream bis = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);

			FileOutputStream fos = new FileOutputStream(new File(dir,
					newfileName));
			bos = new BufferedOutputStream(fos);

			byte[] buf = new byte[8192];
			int len = -1;
			while ((len = bis.read(buf)) != -1) {
				bos.write(buf, 0, len);
			}
		} finally {
			try {
				if (null != bis)
					bis.close();
			}

			catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (null != bos) {
					bos.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return newfileName;
	}

	/**
	 * �����ļ�
	 * @param path �����ļ���·��
	 * @param fileName �����ļ�Ҫ��ʾ������
	 * @param newFileName �������������ļ�
	 * @throws Exception
	 */

	public static void downloadFile(String path, String fileName,
			String newFileName) throws Exception {
		ServletActionContext.getResponse().reset();//���Լ�Ҳ���Բ���
		ServletActionContext.getResponse().setContentType(
				"application/x-download;charset=GBK");//����Ϊ����application/x-download
		String filenamedownload = path + File.separator + newFileName;
		String filenamedisplay = fileName;
		filenamedisplay = URLEncoder.encode(filenamedisplay, "UTF-8");
		ServletActionContext.getResponse().addHeader("Content-Disposition",
				"attachment;filename=" + filenamedisplay);

		OutputStream output = null;
		FileInputStream fis = null;
		try {
			output = ServletActionContext.getResponse().getOutputStream();
			fis = new FileInputStream(filenamedownload);

			byte[] b = new byte[1024];
			int i = 0;

			while ((i = fis.read(b)) > 0) {
				output.write(b, 0, i);
			}
			output.flush();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
				fis = null;
			}
			if (output != null) {
				output.close();
				output = null;
			}
		}
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


	public String getNewFileName() {
		return newFileName;
	}



	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}



	public String getJsonString() {
		return jsonString;
	}



	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}



}
