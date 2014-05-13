<%@ page contentType="text/html; charset=GBK"%>
<%@ page import="java.io.BufferedInputStream,java.io.File,java.io.FileInputStream,java.io.IOException,java.io.InputStream,java.io.PrintWriter,java.util.Hashtable" %>
<%@ page import="net.itjds.common.CommonConfig" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

	 Hashtable MIME_TYPE = new Hashtable();
	   {
	      MIME_TYPE.clear();
	      MIME_TYPE.put("jpg","image/jpeg");
	      MIME_TYPE.put("gif","image/gif");
	      MIME_TYPE.put("bmp","image/bmp");
	      MIME_TYPE.put("tif","image/tiff");
	      MIME_TYPE.put("html","text/html;charset=gb2312");
	      MIME_TYPE.put("htm","text/html;charset=gb2312");
	      MIME_TYPE.put("txt","text/plain;charset=gb2312");
	      MIME_TYPE.put("doc","application/msword");
	      MIME_TYPE.put("pdf","application/pdf");
	      MIME_TYPE.put("xls","application/vnd.ms-excel");
	      MIME_TYPE.put("ppt","application/vnd.ms-powerpoint");
	      MIME_TYPE.put("mp3","audio/mpeg");
	      MIME_TYPE.put("wav","audio/x-wav");
	      MIME_TYPE.put("rpm","audio/x-pn-realaudio-plugin");
	      MIME_TYPE.put("rm","audio/x-pn-realaudio");
	  }

		String filePath = request.getParameter("filePath");
		String fileName = request.getParameter("filename");
		fileName = CommonConfig.getValue("bpm.filePath")+fileName;
		fileName = new String(fileName.getBytes("ISO8859_1"),"GBK");
		fileName = fileName.replace("/",File.separator);
		//File file = new File(filePath+"."+fileName);
	//	fileName = new String(fileName.getBytes("GBK"),"iso-8859-1");
		File file = new File(fileName);
		InputStream in = null ; 
		ServletOutputStream op = null;
		try
		{
		in = new BufferedInputStream(new FileInputStream(file));
		 op = response.getOutputStream();
		 String mimeType=(String)(MIME_TYPE.get(fileName.substring(fileName.lastIndexOf(".")+1)));
		 response.reset();
		 fileName = new String(fileName.substring(fileName.lastIndexOf(File.separator)+1));
		fileName = new String(fileName.getBytes("GBK"),"ISO8859_1");
		 if(mimeType != null)
		 {
			 response.setContentType(mimeType);
			 response.setHeader("Content-Disposition","attachment;filename="+ fileName);
		 }else
		 {
		 response.setContentType("application/x-msdownload");
		 response.setHeader("Content-Disposition","filename="+fileName);
		 }
		 byte [] buffer = new byte [in.available()];
         int len = 0; 
         while ((len = in.read(buffer)) > 0) {
         op.write(buffer, 0, len); 
         }
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally
		{
            if ( null != in)  {
                in.close();
            } 
              if ( null != op)  {
                op.close();
            } 
        }



%>
