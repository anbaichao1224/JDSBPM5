package kzxd.electronicfile.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.util.Calendar;
import java.util.List;

import net.itjds.common.CommonConfig;
import net.itjds.fdt.attachment.FileListBean;
import net.itjds.userclient.attachment.BpmAttachmentDAO;
import net.itjds.userclient.common.util.CatalogUtil;
import net.itjds.userclient.document.BpmDocumentDAO;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

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
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.RandomAccessFileOrArray;
import com.itextpdf.text.pdf.codec.TiffImage;

public class CopyFileAction {
	private static Logger logger = Logger.getLogger(CopyFileAction.class);

	public void copyfile(List flist,String recordid,String personId,int categorydao){
		
		String rootPath = File.separator+"file"+File.separator+"electronicfile"+File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath")+rootPath;
		Calendar c =  Calendar.getInstance() ;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		String processInstPath = year+File.separator+month+File.separator+recordid;
		String uploadProcessInstFolder = CatalogUtil.createFolder(realPath+processInstPath);
		for(int i=0;i<flist.size();i++)
		{
			String fname = "";
			String ftype = "";
			String uid = "";
			String htid = "";
			String path = "";
			Integer istopdf = 0;
			if(categorydao==1){
				BpmAttachmentDAO  ba = 	(BpmAttachmentDAO)flist.get(i);
				fname = ba.getFilename();
				ftype = ba.getFiletype();
				uid = ba.getUuid();
				htid = ba.getUuid();
				path = ba.getFilepath();
				istopdf = ba.getIsToPdf();
			}else{
				BpmDocumentDAO ba = (BpmDocumentDAO)flist.get(i);
				fname = "正文附件.doc";
				ftype = ba.getFiletype();
				uid = ba.getProcessinstid()+"_ht";
				htid = ba.getProcessinstid();
				path = ba.getFilepath();
			}
			UploadAttachmentAction up = new UploadAttachmentAction();
			up.setPersonId(personId);
			
			realPath = CommonConfig.getValue("bpm.filePath");
			String oldfile = CatalogUtil.createFolder(realPath+path).replace("\\", File.separator+File.separator);
			String src = "";
			java.io.File file = null;
			
					if(categorydao==1){
						String fileid = up.saveFileInfo(rootPath+processInstPath+File.separator, recordid, fname);
						up.updateFileInfo(fileid, istopdf);
						if(istopdf==2){
							file = new java.io.File(oldfile,uid+"."+ftype); 
							src = uploadProcessInstFolder+File.separator+fileid+"."+getFileExt(fname);
						}else{
							file = new java.io.File(oldfile,uid+".pdf"); 
							src = uploadProcessInstFolder+File.separator+fileid+".pdf";
						}
					
					//oldfile = CatalogUtil.createFolder(realPath+path+htid+"."+ftype).replace("\\", File.separator+File.separator);
					//file = new java.io.File(oldfile); 
					
				}else{
					String fileid = up.saveFileInfo(rootPath+processInstPath+File.separator, recordid, fname.substring(0, fname.lastIndexOf("."))+".pdf");
					src = uploadProcessInstFolder+File.separator+fileid+".pdf";
					fileconvert(oldfile+htid+".doc",src,"pdf");
					
					if(new File(oldfile+uid+".doc").exists()){
					fname = "正文套红.doc";
					String fileid1 = up.saveFileInfo(rootPath+processInstPath+File.separator, recordid, fname.substring(0, fname.lastIndexOf("."))+".pdf");
					src = uploadProcessInstFolder+File.separator+fileid1+".pdf";
					fileconvert(oldfile+uid+".doc",src,"pdf");
					}
					continue;
				}
			
			 
			
			//if(!file.exists()){
				
			/*}else{
				String fileid = up.saveFileInfo(rootPath+processInstPath+File.separator, recordid, fname.substring(0, fname.lastIndexOf("."))+".pdf");
			    
				src = uploadProcessInstFolder+File.separator+fileid+".pdf";
			}*/
			
			writeFile(src,file);
			
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
		//String commandstr1 = "cd C:\\Program Files (x86)\\OpenOffice.org 3\\program";
		//String commandstr2 = "C:\\Program Files (x86)\\OpenOffice.org 3\\program\\soffice -headless -accept='socket,host=127.0.0.1,port=8100;urp;' -nofirststartwizard";
		/* try {
			Runtime.getRuntime().exec(commandstr1);
			Runtime.getRuntime().exec(commandstr2);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		//soffice -headless -accept="socket,host=127.0.0.1,port=8100;urp;" -nofirststartwizard
 		File inputFile = new File(oldfile); 
		File outputFile = new File(newfile); 
		// connect to an OpenOffice.org instance running on port 8100 
		OpenOfficeConnection connection = new SocketOpenOfficeConnection(8888); 
		try { 
            connection.connect(); 
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection); 
            converter.convert(inputFile, outputFile); 
        } catch(Exception e) { 
        	msg = "不支持"+ftype+"格式的转换";
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
 
 public String getFileExt(String filename)
	{
		
		return filename.substring(filename.lastIndexOf(".")+1);
	}
}
