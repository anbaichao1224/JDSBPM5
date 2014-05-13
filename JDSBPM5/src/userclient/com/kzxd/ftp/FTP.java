package com.kzxd.ftp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import net.itjds.common.CommonConfig;
import net.itjds.userclient.common.util.CatalogUtil;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;

public class FTP {

    /** 
     * FTP���ص����ļ����� 
     * @throws IOException 
     */ 
    public static String testDownload(String remoteFile, String fileName, String bsnum) throws IOException { 
        FTPClient ftpClient = new FTPClient(); 
        FileOutputStream fos = null; 
        Properties pro = new Properties();// �������ö���
    	pro.load(FTP.class.getResourceAsStream("/ftpdownload.properties"));// ���������ļ�
    	String ip= pro.getProperty("ip");
    	int port = Integer.parseInt(pro.getProperty("port"));
    	String userid= pro.getProperty("userid");
    	String password= pro.getProperty("password");
        String path= pro.getProperty("path");
        
        Calendar c =  Calendar.getInstance() ;
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		String rootPath = File.separator+"ftp"+File.separator+"attachment"+File.separator;
		String realPath = CommonConfig.getValue("bpm.filePath")+rootPath +File.separator;
		CatalogUtil.createFolder(realPath);
	
		realPath += year+File.separator+month+File.separator + bsnum + File.separator;
	
		String uploadProcessInstFolder = CatalogUtil.createFolder(realPath);
		
		String realName = FTP.rename(fileName);
		
		String realfilepath = uploadProcessInstFolder + realName;
		
        try { 
        	ftpClient.connect(ip, port); 
            ftpClient.login(userid, password);
            String remoteFileName = remoteFile; 
            fos = new FileOutputStream(realfilepath); 
            ftpClient.setBufferSize(1024); 
            //�����ļ����ͣ������ƣ� 
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE); 
            ftpClient.retrieveFile(remoteFileName, fos); 
        } catch (IOException e) { 
            e.printStackTrace(); 
            throw new RuntimeException("FTP�ͻ��˳���", e); 
        } finally { 
            IOUtils.closeQuietly(fos); 
            try { 
                ftpClient.disconnect(); 
            } catch (IOException e) { 
                e.printStackTrace(); 
                throw new RuntimeException("�ر�FTP���ӷ����쳣��", e); 
            } 
        } 
        return realfilepath;
    } 
    public  static String rename(String name) {  
    	  
        Long now = Long.parseLong(new SimpleDateFormat("yyyyMMddHHmmss")  
                .format(new Date()));  
        Long random = (long) (Math.random() * now);  
        String fileName = now + "" + random;  
        fileName += name;
  
        return fileName;  
    }  


}
