<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextpath;
%>
{
 totalCount:<ww:property value="totalCount"/>, 
datas:[
<ww:iterator value="fileBeanList" status="rowstatus">
    {
      index:"<ww:property value="fileindex"/>",
       processInstId:"<ww:property value="processinstid"/>",
      activityInstId:"<ww:property value="activityInstId"/>",
      uuid:"<ww:property value="uuid"/>",
     // filename:"<a href=\"javascript:downLoadFile('<ww:property value="filePath"/>downloadfile.action?uuid=<ww:property value="uuid"/>','<ww:property value="filename"/>')\"> <ww:property value="filename"/></a>",
     downloadfile:"<a href='<ww:property value="filePath"/>zwdownloadfile.action?uuid=<ww:property value="uuid"/> ''>下载</a>",
     <ww:if test ="isToPdf == 0">
      filename:"<a href=\"javascript:openpdf('<ww:property value="uuid"/>','<%=basePath %>')\"> <ww:property value="filename"/></a>", 
     
     </ww:if>
     <ww:else>
     
     filename:"<a href='<ww:property value="filePath"/>zwdownloadfile.action?uuid=<ww:property value="uuid"/> ''><ww:property value="filename"/></a>",
     
      </ww:else>
      uploaduser:"<ww:property value="fileuploaduser"/>",
      uploadtime:"<ww:property value="fileuploaddate"/>",
      filetype:"<ww:property value="filetype"/>"
           
      }  
      <ww:if test ="#rowstatus.count < fileList.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 