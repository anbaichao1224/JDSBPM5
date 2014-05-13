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
       yewuuuid:"<ww:property value="yewuuuid"/>",
      uuid:"<ww:property value="uuid"/>",
     // displayname:"<a href=\"javascript:downLoadFile('<ww:property value="filePath"/>mdownloadfile.action?uuid=<ww:property value="uuid"/>','<ww:property value="filename"/>')\"> <ww:property value="filename"/></a>",
     <ww:if test ="isToPdf == 0">
     displayname:"<a href=\"javascript:openpdf('<ww:property value="uuid"/>','<%=basePath %>')\"> <ww:property value="displayname"/></a>",
      </ww:if>
     <ww:else>
     displayname:"<a href='<ww:property value="filePath"/>zwhqdownloadfile.action?uuid=<ww:property value="uuid"/> ''><ww:property value="displayname"/></a>",
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

 