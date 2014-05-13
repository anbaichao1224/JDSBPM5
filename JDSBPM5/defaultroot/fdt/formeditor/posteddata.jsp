<%@ page contentType="text/html; charset=utf-8" %>


<%@ page import ="net.itjds.fdt.temp.resource.*,net.itjds.fdt.temp.resource.file.*,net.itjds.fdt.temp.*,java.io.File"  %>
<html>
  <head>
      <title></title>

  </head>
  <body>
  <%
   	String path= request.getParameter("path");
      String formContent =request.getParameter("formcontent") ;
	
     if(path!=null){
    
      	IResourceOperate resource=ResourceFactory.factory();
      	resource.createFile(path,formContent,"UTF-8");
      	
      /*	DirOperateAction doa=new DirOperateAction();
      	String rootpath=doa.getFilePath();
      	String content=resource.toXmlForDirAndFile(rootpath);
		resource.createFile(rootpath+File.separator+"indexfile.xml", content,"UTF-8");
      */
	

  %>
   <script type="text/javascript">
  
      alert('保存成功！');
   </script>  
<%
	}
%>
  </body>
</html>