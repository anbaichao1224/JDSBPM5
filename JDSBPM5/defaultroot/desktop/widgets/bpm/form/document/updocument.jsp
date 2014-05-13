<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack,com.opensymphony.xwork2.ActionContext,net.itjds.fdt.define.designer.metadata.bean.DocumentBean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
DocumentBean docbean = (DocumentBean) ActionContext.getContext().getValueStack().findValue("$docInject.getDocBean()");
String updatedoc = docbean.getDocbasic().getOpen(); 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'updocument.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  <script src="<%=path%>desktop/widgets/bpm/form/document/js/jquery-1.3.1.js" type="text/javascript"></script>
  <script src="<%=path%>desktop/widgets/bpm/form/document/js/ajaxfileupload.js" type="text/javascript"></script>
  <script language="JavaScript" type="text/javascript">

    var count= 0 ;

	$(function(){
	
	   $("#send").click(function(){
	   		  var ss  = <%=request.getParameter("processInstId")%>;
		   	  var ss2 = <%=request.getParameter("activityInstId")%>;
		   	  var ss3 = <%=request.getParameter("formId")%>;
		   	  var fileName = document.getElementById("upfile").value;
			  if(fileName == ""){
		      		alert("文件路径不能为空");
		      		document.getElementById("fn").value = 0;
		      		return false;
		      }   
			      
			  var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();	      
			      
			  if(fileType=="doc"||fileType=="docx"){
			      	window.top.docidvalue('undefined');        
			  }else{
			      	alert("请上传word文件");
			       	document.getElementById("fn").value = 0;
			       	document.fileform.reset();
			  		return false;
			  }
		   	  document.getElementById("fn").value = 1;
			  $.ajaxFileUpload({
                         url:'uploadAction.do?processInstId='+ss+'&activityInstId='+ss2+'&formId='+ss3, //上传文件的服务端
                         secureuri:false,  //是否启用安全提交
                         dataType: 'json',   //数据类型  
                         fileElementId:'upfile', //表示文件域ID
                         
                         //提交成功后处理函数      data为返回值，status为执行的状态
                         success: function(data,status)  
                         {
                         	count++;
                         	alert('上传成功');
                         	
							var displayname=data.displayname;
							var uuid=data.uuid;
							var aInstId=data.aInstId;
							var formId=data.formId;
							var filenum=data.filenum;
							if(filenum<2){
								var docimg1=window.parent.document.getElementById("docimg1");
	          					docimg1.style.display="none";
							}
	 						var $html=$("<div id=divUpload" + count +">"+"<a href=javascript:window.top.openFormWin('<%=path%>/selfdocumentAction.action','','','activityInstHistoryId=&activityInstId="+aInstId+"&docid="+uuid+"&formID="+formId+"','"+filenum+"','','"+aInstId+"','');>"+displayname+"</a>&nbsp;&nbsp;&nbsp;<a href=javascript:delUpload('" + uuid + "','divUpload" + count + "');>删除</a></div>");
	   						$("#resText").append($html);
							
                         },

                         //提交失败处理函数
                         error: function (data,status,e)
                         {
                             alert('上传失败');
                         }
             })
	   });
	})
	
	function delUpload(uuid,diva) {  
         var url="zhengwen_del.action";
	      var $sendData={
	           uuid:uuid,
	           diva:diva
	         };
	      
	       $.post(url,$sendData,处理后台servlet传递过来的数据,"json");
           
    }
    
    function 处理后台servlet传递过来的数据(servletData,ajaxStatus){
	   var result=servletData.result;
	   var diva=servletData.diva;
	   alert(result);
       document.getElementById(diva).parentNode.removeChild(document.getElementById(diva));
	   
	}
    
    
  </script>
  <body>
      <form enctype="multipart/form-data" method="post" name="fileform" target="supportiframe">
      <input type="file" id="upfile" name="file" size=50/>
      <input type="button" id="send" value="上传正文" />
      <div id="resText"></div>
      <input type="hidden" name="activityInstId" id="activityInstId"/>
      <input type="hidden" name="processInstId" id="processInstId"/></form>
      <input type="hidden" id="fn" value="0">
      <iframe id="supportiframe" name="supportiframe"  height= "0"   width= "0">
  </body>
</html>
