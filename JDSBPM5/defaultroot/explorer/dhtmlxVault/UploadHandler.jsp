<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%


//request.setCharacterEncoding("UTF-8");

String uploadFolder=request.getParameter("uploadPath");

// Check that we have a file upload request
boolean isMultipart = FileUpload.isMultipartContent(request);

if (!isMultipart) {

	out.println ("Use multipart form to upload a file!");

} else {

String fileId = request.getParameter("sessionId").toString().trim();
        
// Create a new file upload handler
FileItemFactory factory = new DiskFileItemFactory();
ServletFileUpload upload = new ServletFileUpload(factory);

// Parse the request
List /* FileItem */ items = upload.parseRequest(request);

// Process the uploaded items
Iterator iter = items.iterator();
while (iter.hasNext()) {
    FileItem item = (FileItem) iter.next();

    if (item.isFormField()) {
    	String fieldName = item.getFieldName();
    	if("uploadPath".equals(fieldName)){
    		
    		uploadFolder = item.getString("UTF-8");
    		
    	}
		
        //processFormField
    } else {
        //processUploadedFile
		String fieldName = item.getFieldName();

		String fileName = item.getName();
		
		if(uploadFolder==null){
			throw new Exception("not set upload path");
		}
		
		int i2 = fileName.lastIndexOf("\\");
		if(i2>-1) fileName = fileName.substring(i2+1);
		//uploadFolder = new String(uploadFolder.getBytes("ISO-8859-1"));
		
		uploadFolder=java.net.URLDecoder.decode(uploadFolder);
		System.out.print(uploadFolder);
		//uploadFolder=new String(uploadFolder.getBytes("ISO-8859-1"));
		//System.out.print(uploadFolder);
		File dirs = new File(uploadFolder);
		//dirs.mkdirs();

		File uploadedFile = new File(dirs,fileName);
		item.write(uploadedFile);
      
		session.setAttribute("FileUpload.Progress."+fileId,"-1");
    }
 }

}  
%>