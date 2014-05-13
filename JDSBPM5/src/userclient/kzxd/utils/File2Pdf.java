package kzxd.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

import kzxd.electronicfile.file.CopyFileAction;

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
import org.apache.log4j.Logger;

public class File2Pdf {
	static final int wdDoNotSaveChanges = 0;// 不保存待定的更改。
	static final int wdFormatPDF = 17;// PDF 格式
	static final int ppSaveAsPDF = 32;// ppt 转PDF 格式
	private static Logger logger = Logger.getLogger(CopyFileAction.class);
	public static boolean fileconvert(String oldfile,String newfile,String ftype){
		String msg = "";
		if(ftype.equals("doc")||ftype.equals("docx")){
			ActiveXComponent app = null;
			try {
				app = new ActiveXComponent("Word.Application");
	//			app.setProperty("Visible", false);
				Dispatch docs = app.getProperty("Documents").toDispatch();
				Dispatch doc = Dispatch.call(docs,
						"Open", 
						oldfile,// FileName
						false,// ConfirmConversions
						true // ReadOnly
				).toDispatch();

				File tofile = new File(newfile);
				if (tofile.exists()) {
					tofile.delete();
				}
				Dispatch.call(doc,
						"SaveAs", 
						newfile, // FileName
						wdFormatPDF);

				Dispatch.call(doc, "Close", false);
			} catch (Exception e) {
			} finally {
	//			if (app != null)
	//				app.invoke("Quit", wdDoNotSaveChanges);
			}
		}else if(ftype.equals("xls")||ftype.equals("xlsx")){
	        ActiveXComponent app = new ActiveXComponent("Excel.Application"); // 启动excel(Excel.Application)  
	        try {  
//	        app.setProperty("Visible", false);  
	        Dispatch workbooks = app.getProperty("Workbooks").toDispatch();  
	        Dispatch workbook = Dispatch.invoke(workbooks, "Open", Dispatch.Method, new Object[]{oldfile, new Variant(false),new Variant(false)}, new int[3]).toDispatch();  
	        Dispatch.invoke(workbook, "SaveAs", Dispatch.Method, new Object[] {  
	        newfile, new Variant(57), new Variant(false),  
	        new Variant(57), new Variant(57), new Variant(false),  
	        new Variant(true), new Variant(57), new Variant(true),  
	        new Variant(true), new Variant(true) }, new int[1]);  
	        Variant f = new Variant(false);  
	        Dispatch.call(workbook, "Close", f);  
	        } catch (Exception e) {  
	        }finally {  
	            if (app != null){  
	                app.invoke("Quit", new Variant[] {});  
	            }  
	        }  
	   
		}else if(ftype.equals("ppt")||ftype.equals("pptx")){
	        ActiveXComponent app = null;  
	        try {  
	            app = new ActiveXComponent("Powerpoint.Application");  
	            Dispatch presentations = app.getProperty("Presentations").toDispatch();  
	            Dispatch presentation = Dispatch.call(presentations,//  
	                    "Open",   
	                    oldfile,// FileName  
	                    true,// ReadOnly  
	                    true,// Untitled 指定文件是否有标题。  
	                    false // WithWindow 指定文件是否可见。  
	                    ).toDispatch();  
	  
	            File tofile = new File(newfile);  
	            if (tofile.exists()) {  
	                tofile.delete();  
	            }  
	            Dispatch.call(presentation,//  
	                    "SaveAs", //  
	                    newfile, // FileName  
	                    ppSaveAsPDF);  
	  
	            Dispatch.call(presentation, "Close");  
	        } catch (Exception e) {  
	        } finally {  
	//            if (app != null) app.invoke("Quit");  
	        }
		}
		
		else{
			DocumentFormat stw = new DocumentFormat("OpenOffice.org 1.0 Template", DocumentFamily.TEXT, "application/vnd.sun.xml.writer", "tif"); 
			DefaultDocumentFormatRegistry formatReg = new DefaultDocumentFormatRegistry(); 
			DocumentFormat pdf = formatReg.getFormatByFileExtension("pdf"); 
			File inputFile = new File(oldfile); 
			File outputFile = new File(newfile); 
			// connect to an OpenOffice.org instance running on port 8100 
			OpenOfficeConnection connection = new SocketOpenOfficeConnection(8888); 

			try { 
				connection.connect(); 
				DocumentConverter converter = new OpenOfficeDocumentConverter(connection); 
				//converter.convert(inputFile, outputFile);
				converter.convert(inputFile, stw, outputFile, pdf);
			} catch(Exception e) { 

				msg = "不支持"+ftype+"格式的转换";
				//e.printStackTrace();
				return false;
			} finally { 
				try{ if(connection != null){connection.disconnect(); connection = null;}}catch(Exception e){} 
			} 

		}
		return true;

	}
	public static boolean tiftopdf(String oldPath,String newPath){
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
	                // 2012-6-26 ws注释：下面三行为最新注解
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
	                            // 2012-6-26 ws注释：下面三行为最新注解
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
	            return false;
	        } catch (DocumentException e) {
	            logger.error("由文件[" + oldPath + "]转为[" + newPath + "]失败！,原因："
	                    + e.getMessage());
	            e.printStackTrace();
	            return false;
	        } catch (IOException e) {
	            logger.error("由文件[" + oldPath + "]转为[" + newPath + "]失败！,原因："
	                    + e.getMessage());
	            e.printStackTrace();
	            return false;
	        } finally {
	            if (doc != null) {
	                doc.close();
	            }
	        }
	        return true;

	}
	 public static int getPercent2(float h, float w) {
	        int p = 0;
	        float p2 = 0.0f;
	        p2 = 530 / w * 100;
	        p = Math.round(p2);
	        return p;
	    }
}
