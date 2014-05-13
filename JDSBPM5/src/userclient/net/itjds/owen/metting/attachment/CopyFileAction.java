package net.itjds.owen.metting.attachment;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.itjds.bpm.client.ActivityInst;
import net.itjds.bpm.data.FormClassBean;
import net.itjds.bpm.engine.BPMException;
import net.itjds.common.CommonConfig;
import net.itjds.common.database.DBBeanBase;
import net.itjds.common.org.base.Person;
import net.itjds.j2ee.dao.DAOFactory;
import net.itjds.j2ee.util.UUID;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.attachment.UploadAttachmentAction;
import net.itjds.userclient.common.BPMUserClientUtil;
import net.itjds.userclient.common.util.CatalogUtil;

import org.apache.log4j.Logger;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;
import com.kzxd.xzsp.util.Material;
import com.opensymphony.xwork2.ActionContext;

public class CopyFileAction {
	private static Logger logger = Logger.getLogger(CopyFileAction.class);
	
	public void xzspCopyfile(List<Material> materials,String pInst,String aInst,String formId){
		this.activityInstId = aInst;
		String rootPath = File.separator+"file"+File.separator+"attachment"+File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath")+rootPath;
		Calendar c =  Calendar.getInstance() ;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		String activityInstPath = year+File.separator+month+File.separator+pInst+File.separator+aInst+File.separator+formId;
		String processInstPath = year+File.separator+month+File.separator+pInst+File.separator+pInst+formId;
		String uploadActiveInstFolder = CatalogUtil.createFolder(realPath+activityInstPath);
		String uploadProcessInstFolder = CatalogUtil.createFolder(realPath+processInstPath);
		for(int i=0;i<materials.size();i++)
		{
			Material mm = materials.get(i);
			if(mm.getFpath() != null && mm.getFpath().length() > 0){
				try {
					UploadAttachmentAction upload = new UploadAttachmentAction();
					
					String fileid = saveFileInfo(rootPath+activityInstPath+File.separator,aInst,pInst,formId,mm.getFname(),0);
					java.io.File file = new java.io.File(mm.getFpath());
					
					writeFile(uploadActiveInstFolder+File.separator+fileid+"."+getFileExt(mm.getFname()),file);
					
					upload.updateFileInfo(fileid, 0);
				
				} catch (BPMException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void copyfile(List flist,String pInst,String aInst,String formId){
		this.activityInstId = aInst;
		String rootPath = File.separator+"file"+File.separator+"attachment"+File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath")+rootPath;
		Calendar c =  Calendar.getInstance() ;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		String activityInstPath = year+File.separator+month+File.separator+pInst+File.separator+aInst+File.separator+formId;
		String processInstPath = year+File.separator+month+File.separator+pInst+File.separator+pInst+formId;
		//String uploadProcessInstFolder = CatalogUtil.createFolder(realPath+processInstPath);
		String uploadActiveInstFolder = CatalogUtil.createFolder(realPath+activityInstPath);
		String uploadProcessInstFolder = CatalogUtil.createFolder(realPath+processInstPath);
		for(int i=0;i<flist.size();i++)
		{
			String fname = "";
			String ftype = "";
			String uid = "";
			String htid = "";
			String path = "";
				BpmMAttachmentDAO  ba = 	(BpmMAttachmentDAO)flist.get(i);
				fname = ba.getFilename();
				ftype = ba.getFiletype();
				uid = ba.getUuid();
				path = ba.getFilepath();
				
			
			/*realPath = CommonConfig.getValue("bpm.filePath");
			String oldfile = CatalogUtil.createFolder(realPath+path).replace("\\", File.separator+File.separator);
			String src = "";
			java.io.File file = new java.io.File(oldfile,uid+".pdf"); */
			
			try {
			UploadAttachmentAction upload = new UploadAttachmentAction();
			/*upload.setActivityInstId(aInst);
			upload.setProcessInstId(pInst);
			upload.setFormId(formId);*/
			String fileid = saveFileInfo(rootPath+activityInstPath+File.separator,aInst,pInst,formId,ba.getFilename(),1);
			if(ba.getIsToPdf()==1){
				java.io.File file = new java.io.File(CommonConfig.getValue("bpm.filePath")+ba.getFilepath()+uid+".pdf");
				writeFile(uploadActiveInstFolder+File.separator+fileid+".pdf",file);
				upload.updateFileInfo(fileid, 1);
				//if(processInst.getCopyNumber()<=0)//在流程没分散前保存
				//{
					fileid = saveFileInfo(rootPath+processInstPath+File.separator,pInst,pInst,formId,ba.getFilename(),1);
					writeFile(uploadProcessInstFolder+File.separator+fileid+".pdf",file);
					//File2Pdf.fileconvert(uploadProcessInstFolder+File.separator+fileid+"."+getFileExt(ba.getFilename()), uploadProcessInstFolder+File.separator+fileid+"."+"pdf", getFileExt(ba.getFilename()));
				//}
			}else{
				java.io.File file = new java.io.File(CommonConfig.getValue("bpm.filePath")+ba.getFilepath()+uid+"."+getFileExt(fname));
				writeFile(uploadActiveInstFolder+File.separator+fileid+"."+getFileExt(fname),file);
				
				fileid = saveFileInfo(rootPath+processInstPath+File.separator,pInst,pInst,formId,ba.getFilename(),1);
				writeFile(uploadProcessInstFolder+File.separator+fileid+"."+getFileExt(ba.getFilename()),file);
				upload.updateFileInfo(fileid, 1);
			}
			} catch (BPMException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void tifconvert(String oldfile,String newfile){
		DocumentFormat stw = new DocumentFormat("OpenOffice.org 1.0 Template", DocumentFamily.TEXT, "application/vnd.sun.xml.writer", "tif"); 
        DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry(); 
        DocumentFormat pdf = formatReg.getFormatByFileExtension("pdf"); 
        File inputFile = new File(oldfile); 
        File outputFile = new File(newfile); 
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100); 
        try { 
            connection.connect(); 
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection); 
            converter.convert(inputFile, stw, outputFile, pdf); 
        } catch(Exception e) { 
            e.printStackTrace(); 
        } finally { 
            try{ if(connection != null){connection.disconnect(); connection = null;}}catch(Exception e){} 
        } 
        
		
	}
	
	public String fileconvert(String oldfile,String newfile,String ftype){
		String msg = "";
		
 		File inputFile = new File(oldfile); 
		File outputFile = new File(newfile); 
		// connect to an OpenOffice.org instance running on port 8888
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8888); 
		try { 
            connection.connect(); 
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection); 
            converter.convert(inputFile, outputFile); 
        } catch(Exception e) { 
        	msg = "不支持"+ftype+"格式的转换";
            e.printStackTrace(); 
        } finally { 
            try{ if(connection != null){connection.disconnect(); connection = null;}}catch(Exception e){} 
        } 
        return msg;

	}
	
	public void tiftopdf(String oldPath,String newPath){
		 Document doc = new Document();
	        try {
	            // 定义输出文件的位置
	            PdfWriter.getInstance(doc, new FileOutputStream(newPath));
	            // 开启文档
	            doc.open();

	            if (!oldPath.endsWith(".tif")) {
	                Image jpg = Image.getInstance(oldPath); // 原来的图片的路径
	                // 获得图片的高度
	                float heigth = jpg.getHeight();
	                float width = jpg.getWidth();
	                // 合理压缩，h>w，按w压缩，否则按w压缩
	                int percent = getPercent2(heigth, width);

	                // 设置图片居中显示
	                jpg.setAlignment(Image.MIDDLE);
	                // 按百分比显示图片的比例
	                if (width > 1024 || heigth > 786) {
	                    jpg.scalePercent(percent);
	                }
	                doc.add(jpg);
	            } else
	            // tiff多页
	            {
	                Object localObject1 = null;
	                Object localObject2 = null;
	                Image localImage1 = null;
	                URL paramURL = Utilities.toURL(oldPath);
	                try {
	                    if (paramURL.getProtocol().equals("file")) {
	                        localObject2 = paramURL.getFile();
	                        localObject2 = Utilities
	                                .unEscapeURL((String) localObject2);
	                        localObject1 = new RandomAccessFileOrArray(
	                                (String) localObject2);
	                    } else {
	                        localObject1 = new RandomAccessFileOrArray(paramURL);
	                    }

	                    int pageNums = TiffImage
	                            .getNumberOfPages((RandomAccessFileOrArray) localObject1);
	                    if (pageNums > 0) {
	                        for (int i = 1; i <= pageNums; i++) {
	                            localObject2 = TiffImage.getTiffImage(
	                                    (RandomAccessFileOrArray) localObject1, i);
	                            Image jpg = (Image) localObject2;
	                            // 获得图片的高度
	                            float heigth = jpg.getHeight();
	                            float width = jpg.getWidth();
	                            // 合理压缩，h>w，按w压缩，否则按w压缩
	                            int percent = getPercent2(heigth, width);

	                            // 设置图片居中显示
	                            jpg.setAlignment(Image.MIDDLE);
	                            // 按百分比显示图片的比例
	                            if (width > 1024 || heigth > 786) {
	                                jpg.scalePercent(percent);
	                            }
	                            doc.add(jpg);
	                        }
	                    }
	                    if (localObject1 != null)
	                        ((RandomAccessFileOrArray) localObject1).close();

	                } finally {
	                    if (localObject1 != null)
	                        ((RandomAccessFileOrArray) localObject1).close();
	                }
	            }
	        } catch (FileNotFoundException e) {
	            logger.error("由文件[" + oldPath + "]转为[" + newPath + "]失败！,原因："
	                    + e.getMessage());
	            e.printStackTrace();
	        } catch (DocumentException e) {
	            logger.error("由文件[" + oldPath + "]转为[" + newPath + "]失败！,原因："
	                    + e.getMessage());
	            e.printStackTrace();
	        } catch (IOException e) {
	            logger.error("由文件[" + oldPath + "]转为[" + newPath + "]失败！,原因："
	                    + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            if (doc != null) {
	                doc.close();
	            }
	        }


	}
	
	 public int getPercent2(float h, float w) {
	        int p = 0;
	        float p2 = 0.0f;
	        p2 = 530 / w * 100;
	        p = Math.round(p2);
	        return p;
	    }


	
 private static void writeFile(String src,File file)  {
		 
         try  {
            InputStream in = null ;
            OutputStream out = null ;
             try  {                
            	in = new BufferedInputStream(new FileInputStream(file));
                out = new BufferedOutputStream( new FileOutputStream(src));
                 byte [] buffer = new byte [(int)file.length()];
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
 
 
 
 public String  saveFileInfo(String path,String instId,String processInstId,String formId,String filename, int isToPdf)
 {
 	Person person=(Person) ActionContext.getContext()
 	.getValueStack().findValue("$currPerson");
 	BpmAttachmentDAO attdao = new BpmAttachmentDAO();
 	DBBeanBase dbbase = new DBBeanBase("bpm",true);
     Connection conn = null;
     String fileid = new UUID().toString();
     DAOFactory factory = null;
     try{
     	
     	conn=dbbase.getConn();
     	factory = new DAOFactory(conn,attdao);
     	factory.setDAO(attdao);
     	attdao.setConnection(conn);
     	attdao.setUuid(fileid);
     	attdao.setProcessinstid(processInstId);
     	attdao.setActivityinstid(instId);
     	attdao.setFilepath(path);
     	attdao.setFilename(filename);
     	attdao.setFiletype(getFileExt(filename));
     	attdao.setFileuploaduser(person.getID());
     	attdao.setFileuploaddate(formatDate());
     	attdao.setFileindex(1);
     	
     	attdao.setIsToPdf(isToPdf);
     	
     	attdao.setFormid( getFormName(instId,formId));
     	if(instId.equals(processInstId))
     	{
     		attdao.setRemark(instId);
     	}else
     	{
     		attdao.setRemark("");
     	}
     	
     	attdao.create();
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
     return fileid;
 }
 
 public String getFormName(String aInstId,String formId)
 {
 	List<FormClassBean> formList;
 	String formName = "";
 	try { 
 		BPMUserClientUtil bpmUserClientUtil = new BPMUserClientUtil();
 		ActivityInst ainst = bpmUserClientUtil.getClient().getActivityInst(this.activityInstId);
 		
 		formList = ainst.getActivityDef().getAllDataFormDef();

 		if (formList.size() == 1) {
 			formName = formList.get(0).getName();
 		}else{
 		for (int k = 0; formList.size() > k; k++) {
 			FormClassBean formClassBean = formList.get(k);
 			if (formClassBean.getId().equals(formId)) {
 				//og.error("formClassBean.getId()======"+formClassBean.getId()+"this.formId========"+formId);
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

 public String getFileExt(String filename)
	{
		
		return filename.substring(filename.lastIndexOf(".")+1);
	}
 
	/**
	 * 格式化日期
	 * */
	public String formatDate()
	{
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		return sd.format(new Date(System.currentTimeMillis()));
	
	}
	
	
	private String activityInstId;
}
