<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
String contextpath = request.getContextPath() + "/";
%> 	

{


 commentdata:{
		     comment:'<ww:property value="commenttext" escape="false"/>',
		     formname:'<ww:property value="bpmCommentsDAO.formname" escape="false"/>',
		     createdate:'<ww:property value="bpmCommentsDAO.createdate" escape="false"/>',
		     uuid:'<ww:property value="bpmCommentsDAO.uuid" escape="false"/>',
		     proxyperson:'<ww:property value="bpmCommentsDAO.proxyperson" escape="false"/>'
		     
		    },
		 
	index:"<ww:property value="$currPerson.index"/>",
	activityDefId:'<ww:property value="$currActivityInst.activityDef.activityDefId"/>'
}

