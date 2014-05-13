<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="net.itjds.bpm.client.ActivityInst"%>
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

%>
 
<html>
<head>
<title>文件正文</title>
<script type="text/javascript">
var context = '<%=contextpath %>';
</script>
<script type="text/javascript" src="<%=contextpath %>js/extAll.js"></script>

<script LANGUAGE="JavaScript" type="text/javascript">
    
//原始的
	
	var saveAs = false;
	var userName = '';
	var personid = ' ';
	var filenum = '  ';
	var formid = ' ';
	var contextfile = ' ';
	var fileName = ' ';
	var processId = '<ww:property value="processInst.processInstId"/>';
	var newdate = new Date();
	var clinkObject = new ActiveXObject("Scripting.FileSystemObject");
	var uuid = '<ww:property value="docid"/>';
	var activityInstId =  '<ww:property value="activityInstId"/>';
	var activityInstHistoryId =  '<ww:property value="activityInstHistoryId"/>';
	var yiban = '  ';
	//alert('  ');
	var taoHongId = ' ';
	if(uuid != null && uuid.length>1)
	{
		saveAs = true; //有正文
	}
	saveAs = true;
	var filePath = null;
	function openLoad(){ 
		  
 	       var mark = false; //是否显示痕迹
 	       var savedoc = true; //是否保存服务器
 	       var updatedoc = false; //是否开启修订模式
		     try{
				if(saveAs)// 如果有正文
				{
				   
					var downUrl = '<%=basePath%>'+context+"openTTdoc.action?uuid="+uuid; 
					//var downUrl = '<%=basePath%>'+context+'taotoumodel/'+'<ww:property value="mubanid"/>';
                     //用户放已编辑文件地址
				       var uploadTempClientDir = "C:\\jdsbpmtt\\";
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
					    var tempFileName = uploadTempClientDir+"document.doc";

					   fileLoad.SavedFileName = tempFileName;
					 
					    var result = fileLoad.HttpDownLoad();
                       
					    if(result != 0){
					       alert(fileLoad.ErrorStr);
					    }
					    var fullFileName = uploadTempClientDir +"document.doc";
					 
						document.all.oframe.Open(fullFileName);
						if(updatedoc == false){
					    	document.all.oframe.ActiveDocument.TrackRevisions = false;
					    }else{
					    	document.all.oframe.ActiveDocument.TrackRevisions = true;
					    }
					     document.all.oframe.ShowRevisions = true;
				}else {        //如果没有,新建一个(没有用)
                    
					var downUrl = "<%=basePath%>"+context+"downLoadfileAction.action?sta=0&formID="+formid;
				 
				       var uploadTempClientDir = "C:\\jdsbpm4\\";
				       var forderName = uploadTempClientDir;
						var fso = new ActiveXObject("Scripting.FileSystemObject");
						if(!fso.FolderExists(uploadTempClientDir)){
						   fso.CreateFolder (uploadTempClientDir);
						}
						
					    // 检查模板预存文件夹
						if(!fso.FolderExists(forderName)){
					   		fso.CreateFolder (forderName);
						}
						var fileLoad = document.all.fileLoad;
						
					    fileLoad.DownUrlPath = downUrl;
					    
					    var tempFileName = uploadTempClientDir+"document.doc";

					   fileLoad.SavedFileName = tempFileName;
					 
					    var result = fileLoad.HttpDownLoad();
                        
					    if(result != 0){
					       alert(fileLoad.ErrorStr);
					    }
					    
					    var fullFileName = uploadTempClientDir +"document.doc";
					    
						document.all.oframe.Open(fullFileName);
						
					    //当前人不是拟稿人打开修订模式,通过流程控制 
					    alert(updatedoc);
					   if(updatedoc == false){
					    	document.all.oframe.ActiveDocument.TrackRevisions = false;
					   }else{
					    	document.all.oframe.ActiveDocument.TrackRevisions = true;
					   }
						//以下是按钮显示处理
						if(mark == false){
                             document.getElementById("layer3").style.display="none"; 
                          } else{
                             document.getElementById("layer3").style.display="inline"; 
                          }
                         if(savedoc == false){
                             document.getElementById("layer1").style.display="none"; 
                          } else{
                             document.getElementById("layer1").style.display="inline"; 
                          } 
					   
				} 
			
		}catch(e){
			alert("打开正文时出错!");
		}

	}
	
	
	function AcceptAllRevisions(){
		
		   document.all.oframe.ActiveDocument.AcceptAllRevisions();
		   alert("修改文档成功");
		   document.getElementById("AcceptAllRevisionsButton").disabled=true;  
		   
	}
	function showXD(){
		document.all.oframe.OpenPanelRevisions();
		//oframe.OpenPanelRevisions();
	}
	function showRevisions(){
		
		if(document.all.oframe.ShowRevisions){
			document.all.oframe.ShowRevisions = false;
			gongwenform.button3.value = "显示痕迹";
		}else{
			document.all.oframe.ShowRevisions = true;
			gongwenform.button3.value = "隐藏痕迹";
		}

	}
	
	function handler(){	
  
  
  	  
	        //document.all.oframe.save("D:\\mban\\document.doc",1);//保存到本地
	        document.all.oframe.save("C:\\jdsbpmtt\\document.doc",1);
            //上传
	        document.getElementById('oframe').close();
	     	
	     	var fileLoad = document.all.fileLoad;
	     	fileLoad.UpUrlPath = "http://localhost/savettdoc.action";
	     	alert(contextfile);
	    	fileLoad.AddField('activityInstId',activityInstId);
	    	fileLoad.AddField('personId',personid);
	    	alert(uuid);
	    	fileLoad.addField('uuid',uuid);
	    	fileLoad.AddField('filefileName',processId+'_ht.doc');
	    	fileLoad.AddField("processInstId",processId);
	    	if(uuid!=null&&uuid.length>1){
	    		
	    	fileLoad.AddField('uuid',uuid);
	    	}
	    	
	     	fileLoad.AddFile('C:\\jdsbpmtt\\'+'document.doc','files');

			var result = fileLoad.HttpUpLoad();
			if(result != 0){
			   	alert("保存失败: 错误["+fileLoad.ErrorStr+"]");
			}else{
				alert("保存成功");
				window.top.docidvalue(uuid);//必须的
				window.top.docidvalue();
				document.all.oframe.Open("C:\\jdsbpmtt\\document.doc");
			} 	
			
	}
	
	function testwordtt(){
	
	document.getElementById("testdoc").value='<%=basePath%>'+context+"downLoadfileAction.action?sta=1&uuid="+uuid;
	
	}
	
	function taotouHandler(){
		//document.all.oframe.InsertPictureInShape('E:\\AVX_ICONS DOCUMENT.bmp');
		document.getElementById("doc_div").style.display ="none";
		//document.getElementById("doc_table")
		
		showpicdoc();
		//document.all.oframe.InsertHandWritingInBookmark('E:\\taotou.bmp','ttpic');
		//var fwzh = window.top.getFileValue('fwzh');
		//document.all.oframe.SetFieldValue('fwzh', fwzh,'::GETMARK::');
	}
function showpicdoc(){
	
		var win = new Ext.Window({
			title:'选择红头',
			width:200,
			height:Ext.getBody().getHeight()-50,
			html:"<iframe id='myiframe' name='myiframe' src='ttManagerAction_selectlist.action'></ifrmae>",
			buttons:[{ text: "确定", handler: function() { 
						var imgeRadio = Ext.get('myiframe').dom.contentWindow.document.getElementsByName("modelname");
						if(imgeRadio.length>0){
							for(var i=0;i<imgeRadio.length;i++){
								if(imgeRadio[i].checked){
									temp = imgeRadio[i].value;
									document.all.oframe.InsertHandWritingInBookmark('http://localhost:82/taotoumodel/'+temp,'ttpic');
									
								}
							}
						}
						win.close();
						document.getElementById("doc_div").style.display ="block"
					}
			}]
			
		});
		win.show();
		
	}

</script>


</head>

<body onload="openLoad()">
 <img id="myarrow" src=""  STYLE="display:none;border:0">
    
    <form name="gongwenform" method="post">
  
	<div id="doc_div">
    <table width="100%" id="doc_table" height="98%" bordercolorlight="#000000" bordercolordark="#ffffff" class="labeltable_middle_table">
	       <tr>
	           <td class="labeltable_middle_td_zhengwen">
	               <object id="oframe" name="oframe" classid="clsid:00460192-9E5E-11d5-B7C8-B8269041DD57"  width="100%" height="100%">
	                   <param name="BorderStyle" value="1">
	                   <param name="TitlebarColor" value="FFFFFF">
	                   <param name="TitlebarTextColor" value="1">
	                   <param name="Menubar" value="1">
	                   <param name="Titlebar" value="0">
	                   <param name="Toolbar" value="1">
	                   <param name="TrackRevisions" value=false>
	                   <param name="ShowRevisions" value=false>
	                   <param name="AcceptAllRevisions" value=false>
	                   
	               </object>
	           </td>
	       </tr>
	</table>
	</div>
</form>
    <OBJECT ID="fileLoad" CLASSID="CLSID:54071CE9-8B69-4E41-BF1A-E8550EBB58CF" width="0" height="0" ></OBJECT>  
</body>	   