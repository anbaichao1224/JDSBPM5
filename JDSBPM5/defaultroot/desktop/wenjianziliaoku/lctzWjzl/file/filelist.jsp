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
       sxxxid:"<ww:property value="sxxxid"/>",
      uuid:"<ww:property value="uuid"/>",
     // filename:"<a href=\"javascript:downLoadFile('<ww:property value="filePath"/>mdownloadfile.action?uuid=<ww:property value="uuid"/>','<ww:property value="filename"/>')\"> <ww:property value="filename"/></a>",
     <ww:if test="isToPdf==1">
     filename:"<a href=\"javascript:window.top.openpdfgd('<ww:property value="uuid"/>','<%=basePath %>')\"> <ww:property value="filename"/></a>",
      </ww:if>
      <ww:else>
      	 filename:"<a href='<ww:property value="filePath"/>wjzldownloadfile.action?uuid=<ww:property value="uuid"/> ''><ww:property value="filename"/></a>",
      </ww:else>
      uploaduser:"<ww:property value="creatorname"/>",
      uploadtime:"<ww:property value="createdate"/>",
      filetype:"<ww:property value="filetype"/>"
           
      }  
      <ww:if test ="#rowstatus.count < fileList.size">
      , 
      </ww:if>
   </ww:iterator>     
 ]
}

 