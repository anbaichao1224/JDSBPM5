<%@page contentType="text/html;charset=UTF-8"%><%@ page pageEncoding="UTF-8" %>
<%@ page import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack,com.opensymphony.xwork2.ActionContext,net.itjds.fdt.define.designer.metadata.bean.DocumentBean"%>
<%@ page import="java.util.Map,com.opensymphony.xwork2.ActionContext,net.itjds.common.org.base.Person"%>
<%@include file="taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<%
String actionurl = request.getParameter("action");
String contextpath = request.getContextPath() + "/";
String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextpath;
//DocumentBean docbean = (DocumentBean) ActionContext.getContext().getValueStack().findValue("$docInject.getDocBean()");
//Person person1 = (Person)ActionContext.getContext().getValueStack().findValue("$currPerson");
%>
 
<html>
<head>
<title>文件正文</title>
<script LANGUAGE="JavaScript" type="text/javascript">
//原始的
	var context = '<%=contextpath %>';
</script>
<script type="text/javascript" src="<%=contextpath %>js/extAll.js"></script>
<script LANGUAGE="JavaScript" type="text/javascript">
//原始的
	var context = '<%=contextpath %>';
     
	var saveAs = false;
	var cuser;
	
	var newdate = new Date();
	
	var clinkObject = new ActiveXObject("Scripting.FileSystemObject");
	
	//var fname = "";
	var filePath = null;
	var processInstId = '<ww:property value="processInstId"/>';
	var personId = '<ww:property value="personId"/>';
	var fname = '<ww:property value="cljname"/>';
	var recordid = '<ww:property value="dataId"/>';
	var cljid = '<ww:property value="cljid"/>';
	
	function LoadSelf(){
			var bastpath = '<%=basePath%>';
		//var wordURL = bastpath + "/word/"+wordhand+".doc";
		var whand = window.top.getWordname();
		//try{
		
						
					   var downUrl = '<%=basePath%>'+context+"mdownLoadfileAction.action?uuid="+cljid+'&fileName='+whand; 
					   //alert(downUrl);
			 		   var uploadTempClientDir = "C:\\jdsbpm4\\";
				       var forderName = uploadTempClientDir;
						var fso = new ActiveXObject("Scripting.FileSystemObject");
						if(!fso.FolderExists(uploadTempClientDir)){
						   fso.CreateFolder (uploadTempClientDir);
						}
					    
						if(!fso.FolderExists(forderName)){
					   		fso.CreateFolder (forderName);
						}
						var fileLoad = document.all.fileLoad;
						
					    fileLoad.DownUrlPath = downUrl;
					  
					    var tempFileName = uploadTempClientDir+"electronic.doc";

					   fileLoad.SavedFileName = tempFileName;
					 
					    var result = fileLoad.HttpDownLoad();
                       
					    if(result != 0){
					       alert(fileLoad.ErrorStr);
					    }
					    var fullFileName = uploadTempClientDir +"electronic.doc";
						
						
						document.all.oframe.Open(fullFileName);    
						//document.all.oframe.setFieldValue('bt','aa','::GETMARK::');
						if(cljid==''){
						var allBookMarks = new Array();
						var marks = '<ww:property value="bookmarks"/>';
						//alert(marks);
						allBookMarks = marks.split(","); 
						fname = window.top.printwordjszq(whand,allBookMarks,document.all.oframe);
						//alert(fname);
						}
					
		/*}catch(e){
			
			alert("打开正文时出错!");
		}*/
		
	}
	function openLoad(){ 
	}
	
	function AcceptAllRevisions(){
		
		   document.all.oframe.ActiveDocument.AcceptAllRevisions();
		   alert("修改文档成功");
		   document.getElementById("AcceptAllRevisionsButton").disabled=true;  
		   
	}
	
	function showPanelRevisions(){
		if(gongwenform.showPanelButton.value == "隐藏审阅窗口"){
			gongwenform.showPanelButton.value = "显示审阅窗口";
			document.all.oframe.OpenPanelRevisions();
		}else{
			gongwenform.showPanelButton.value = "隐藏审阅窗口";
			document.all.oframe.OpenPanelRevisions();
		}
	}

	function showRevisions(){

		if(gongwenform.showButton.value == "隐藏痕迹"){
			gongwenform.showButton.value = "显示痕迹";
			document.all.oframe.ShowRevisions = false;
		}else{
			gongwenform.showButton.value = "隐藏痕迹";
			document.all.oframe.ShowRevisions = true;
		}

	}
	
	function handler(){	
  
	     document.all.oframe.save("C:\\jdsbpm4\\electronic.doc",1);//保存到本地
            //上传
	     	document.getElementById('oframe').close();
	     	var fileLoad = document.all.fileLoad;
	     	//alert("cljid:"+cljid);
	     	fileLoad.UpUrlPath = '<%=basePath%>'+context+"rattachUpload.action";
	     	//fname = '测试名称';
	    	fileLoad.AddField('fileName','lcgdclj.doc');
	    	fileLoad.AddField('processInstId',processInstId);
	    	
	    	fileLoad.AddField('personId',personId);
	    	fileLoad.AddField('recordid',recordid);
	    	if(cljid!=''){
	    		fileLoad.AddField('dcljid',cljid);
	    	}
	    	fileLoad.AddField('ctype','clj');
	     	fileLoad.AddFile('C:\\jdsbpm4\\'+'electronic.doc','file');
			
			var result = fileLoad.HttpUpLoad();
			
			if(result != 0){
			   	alert("保存失败: 错误["+fileLoad.ErrorStr+"]");
			}else{
				alert("保存成功");
				document.all.oframe.Open("C:\\jdsbpm4\\electronic.doc");
				window.top.updateBtn();
			}
			
	}

</script>



</head>

<body onload="LoadSelf()">
 <img id="myarrow" src=""  STYLE="display:none;border:0">
    
    <form name="gongwenform" method="post">
    	<input type="hidden" name="currentUserId" value="${SESSION_CURRENT_USER.userId}" />
   	 	<table width="100%" border="0" align="center" id="table1">
			<tr>
				<td align="right" class="tdv">
		        
		            <div id="layer1" style="display:inline;">
					<input type="button" name="button1" id="baocunButton" value="编辑保存" Class="button" 
						onclick="handler()" />
				    </div>
					
				</td> 
			</tr>
		</table>
	
    <table width="100%" height="100%" bordercolorlight="#000000" bordercolordark="#ffffff" class="labeltable_middle_table">
	       <tr>
	           <td class="labeltable_middle_td_zhengwen">
	               <object id="oframe" name="demo" classid="clsid:00460192-9E5E-11d5-B7C8-B8269041DD57"  width="100%" height="100%">
	                   <param name="BorderStyle" value="1">
	                   <param name="TitlebarColor" value="FFFFFF">
	                   <param name="TitlebarTextColor" value="1">
	                   <param name="Menubar" value="1">
	                   <param name="Titlebar" value="0">
	                   <param name="Toolbar" value="1">
	                   <param name="TrackRevisions" value=true>
	                   <param name="ShowRevisions" value=false>
	                   <param name="AcceptAllRevisions" value=false>
	                   
	               </object>
	           </td>
	       </tr>
	</table>
</form>
    <OBJECT ID="fileLoad" CLASSID="CLSID:54071CE9-8B69-4E41-BF1A-E8550EBB58CF" width="0" height="0" ></OBJECT>  

</body>	   