package kzxd.documentmodel.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kzxd.documentmodel.entity.KZXDDocumentModel;
import kzxd.documentmodel.servers.KzxdDocumentServer;
import kzxd.documentmodel.servers.impl.KzxdDocumentServerImpl;
import net.itjds.j2ee.util.UUID;
import net.itjds.common.CommonConfig;
import net.itjds.common.org.base.Org;
import net.itjds.common.org.base.Person;
import net.itjds.userclient.common.BPMActionBase;
import net.itjds.userclient.common.util.CatalogUtil;

import org.apache.struts2.ServletActionContext;

public class DocFileUpload extends BPMActionBase {

	private File file;

	private String fileFileName;
	
	private String[] orgname;
	
	private List<KZXDDocumentModel> mlists = new ArrayList<KZXDDocumentModel>();

	public String execute() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String rootPath = File.separator + "file" + File.separator
		+ "docmodel" + File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath") + rootPath;
		CatalogUtil.createFolder(realPath);
		String fileName = this.getFileFileName().replaceAll("\"", "");
		writeFile(realPath+File.separator+fileName,file);
		save(fileName,rootPath);
		KzxdDocumentServer server = new KzxdDocumentServerImpl();
		Person person = getCurrPerson();
		List<Org> orgs = person.getOrgList();
		String[] org = new String[orgs.size()];
		for(int i = 0; i < orgs.size(); i++) {
			Org o = orgs.get(i);
			org[i] = o.getName();
		}
		mlists = server.findByDeptnull(org);
		request.setAttribute("mlists", mlists);
		request.setAttribute("orgs", orgs);
		return SUCCESS;
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
	
	private void save(String filename, String rootPath){
		String model_name = filename;
		String model_path = rootPath;
		String model_type = "doc";
		List<KZXDDocumentModel> models = new ArrayList<KZXDDocumentModel>();
		for(String on : orgname){
			String model_uploaddept = on;
			KZXDDocumentModel model = new KZXDDocumentModel();
			model.setUUID(new UUID().toString());
			model.setModelName(model_name);
			model.setModelPath(model_path);
			model.setModelType(model_type);
			model.setModelUploadDept(model_uploaddept);
			models.add(model);
		}
		KzxdDocumentServer server = new KzxdDocumentServerImpl();
		server.save(models);
	}
    
	public String delete(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String checkbox = request.getParameter("checkbox");
		String[] rgs = checkbox.split(",");
		for(int i=0;i<rgs.length;i++){
			KzxdDocumentServer server = new KzxdDocumentServerImpl();
			String uuid = rgs[i];
			server.deletebyid(uuid);
			
		}
		return "success";
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

	public String[] getOrgname() {
		return orgname;
	}

	public void setOrgname(String[] orgname) {
		this.orgname = orgname;
	}
	
	

}
