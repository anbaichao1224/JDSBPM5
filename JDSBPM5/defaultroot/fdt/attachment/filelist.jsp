<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%   String contextpath = request.getContextPath() ; response.setContentType("text/html;charset=utf-8");

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
      filename:"<a href='<ww:property value="filePath"/>fdtdownloadfile.action?uuid=<ww:property value="uuid"/> ''> <ww:property value="filename"/></a>",
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

 