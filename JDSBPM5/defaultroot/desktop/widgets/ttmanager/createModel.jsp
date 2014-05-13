<%@page contentType="text/html;charset=UTF-8"%>
<%@ page import="net.itjds.bpm.client.ActivityInst"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.util.OgnlValueStack,com.opensymphony.xwork2.ActionContext,net.itjds.fdt.define.designer.metadata.bean.DocumentBean"%>
<%@ page
	import="java.util.Map,com.opensymphony.xwork2.ActionContext,net.itjds.common.org.base.Person"%>
<%@include file="../bpm/form/document/taglibs.jsp"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<%
	String actionurl = request.getParameter("action");
	String contextpath = request.getContextPath() + "/";
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ contextpath;
	String modelId = "";
	if(request.getParameter("modelId")!=null){
		modelId = request.getParameter("modelId").toString();
	}
%>

<html>
	<head>
		<title>文件正文</title>
		<SCRIPT LANGUAGE="JavaScript" type="text/javascript">
	var context='<%=contextpath%>';
	</script>
		<script type="text/javascript" src="<%=contextpath%>js/extAll.js"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>

    <script>
    /*var isupd = false;
	var keydown = function(){
		isupd = true;
	}
	window.keydown = keydown;
	//alert(isupd);
	
	window.onbeforeunload = function(event){
alert('close');
event = event || window.event;
event.returnValue = confirm_msg;
}
*/
//UnloadConfirm.clear();
//清除事件
</script> 
		<script LANGUAGE="JavaScript" type="text/javascript">

    

	var uuid = '<%=modelId%>';
	if(uuid != null && uuid.length>1)
	{
		saveAs = true; //有正文
	}
	
	var filePath = null;
	function openLoad(){ 
		  
 	      
		    // try{
				if(saveAs)// 如果有正文
				{
				    
					var downUrl = '<%=basePath%>'+context+'downTtModelAction.action?uuid='+uuid; 
					
                     //用户放已编辑文件地址
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
					    var tempFileName = uploadTempClientDir+"ttmodel.doc";

					   fileLoad.SavedFileName = tempFileName;
					 
					    var result = fileLoad.HttpDownLoad();
                       
					    if(result != 0){
					       alert(fileLoad.ErrorStr);
					    }
					    var fullFileName = uploadTempClientDir +"ttmodel.doc";
					 
						document.all.oframe.Open(fullFileName);
						//document.all.oframe.SetFieldValue('ttpic', ,'::GETMARK::');
						//alert(fullFileName);
					     //document.all.oframe.ShowRevisions = false;
					     //以下是按钮显示处理
					     
                     
                         if(yiban== 'y'){
                             //document.getElementById("layer1").style.display="none";
                             document.getElementById("bianjibaocun").style.display = "none";
                         }else{
                              if(savedoc == false){
                                  //document.getElementById("layer1").style.display="none"; 
                                  document.getElementById("bianjibaocun").style.display = "none";
                                  document.getElementById("jieshousuoyouxiuding").style.display = "none";
                              }else{
                                  //document.getElementById("layer1").style.display="inline"; 
                              }
                         }
						document.all.oframe.ActiveDocument.TrackRevisions = true;
						//document.all.oframe.ShowRevisions = true;
						document.all.oframe.UserName = userName;
						//alert('http://localhost:82/taotoumodel/'+taoHongId);
						document.all.oframe.InsertHandWritingInBookmark('http://localhost:82/taotoumodel/'+taoHongId,'ttpic');
						//document.all.oframe.OpenPanelRevisions();
						document.all.oframe.ShowRevisions = true;
						//alert(xddoc);
						if(xddoc == null){
							document.all.oframe.TrackRevisions = true;
						}
						var allPepole = document.all.oframe.GetAllReviewers();
						var allPepoleS = allPepole.split(",");
						var ihtml = "";
						for(var ap = 0 ; ap < allPepoleS.length ; ap++){
							//alert(allPepoleS[ap]);
							
							ihtml = ihtml +"<input  type='checkbox' name='checkP' onclick='showJP()' value='"+allPepoleS[ap]+"'>"+allPepoleS[ap]+"<br/><hr>"
						}
						document.getElementById("p_td").innerHTML = ihtml;
						p_p_td.style.display='none';
						document.all.oframe.ShowRevisions = false;
                          
				}else {        //如果没有,新建一个(没有用)
                    
					var downUrl = "<%=basePath%>"+context+"downTtModelAction.action?uuid="+uuid;
				 
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
					    
					    var tempFileName = uploadTempClientDir+"ttmodel.doc";

					   fileLoad.SavedFileName = tempFileName;
					 
					    var result = fileLoad.HttpDownLoad();
                        
					    if(result != 0){
					       alert(fileLoad.ErrorStr);
					    }
					    
					    var fullFileName = uploadTempClientDir +"ttmodel.doc";
					    
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
                             //document.getElementById("layer3").style.display="none"; 
                          } else{
                             //document.getElementById("layer3").style.display="inline"; 
                          }
                         if(savedoc == false){
                             //document.getElementById("layer1").style.display="none";
                             document.getElementById("bianjibaocun").style.display = "none"; 
                             document.getElementById("jieshousuoyouxiuding").style.display = "none";
                          } else{
                             //document.getElementById("layer1").style.display="inline"; 
                          } 
					   
				} 
			
		//}catch(e){
			//alert("打开正文时出错!");
		//}

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
	
	
	function handler(){	
  
  
  	  
	     //document.all.oframe.save("D:\\mban\\document.doc",1);//保存到本地
	   document.all.oframe.save("C:\\jdsbpm4\\ttmodel.doc",1);
            //上传
	    document.getElementById('oframe').close();
	     	
	     	var fileLoad = document.all.fileLoad;
	     	fileLoad.UpUrlPath = "<%=basePath%>saveTtModel.action";
	    	
	    	if(uuid!=null&&uuid.length>1){
	    		
	    	fileLoad.AddField('uuid',uuid);
	    	}
	    	
	     	fileLoad.AddFile('C:\\jdsbpm4\\'+'ttmodel.doc','files');

			var result = fileLoad.HttpUpLoad();
			if(result != 0){
			   	alert("保存失败: 错误["+fileLoad.ErrorStr+"]");
			}else{
				alert("保存成功");
				window.top.docidvalue(uuid);//必须的
				window.top.docidvalue();
				document.all.oframe.Open("C:\\jdsbpm4\\ttmodel.doc");
			} 	
			
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
	var wenzhong = window.top.getWenzhong();
	    
	    //var wenzhong = '<ww:property value="$Fdtnmfwbl.fdtOaNmfwblDAO.wz"/>';
	   // var wenzhong = 'neijifa';
		var win = new Ext.Window({
			title:'选择红头',
			width:200,
			height:Ext.getBody().getHeight()-50,
			html:"<iframe id='myiframe' name='myiframe' height='100%' src='ttManagerAction_selectlist.action?wenzhongQ="+wenzhong+"'></ifrmae>",
			buttons:[{ text: "确定", handler: function() { 
						var imgeRadio = Ext.get('myiframe').dom.contentWindow.document.getElementsByName("modelname");
						if(imgeRadio.length>0){
							for(var i=0;i<imgeRadio.length;i++){
							    
								if(imgeRadio[i].checked){
									temp = imgeRadio[i].value;
									//document.all.oframe.InsertHandWritingInBookmark('http://localhost:82/taotoumodel/'+temp,'ttpic');
									
									document.getElementById("mubanid").value=temp;
								}
							}
						}
						win.close();
						//document.getElementById("doc_div").style.display ="block";
						toDoctt();
					}
			}]
			
		});
		win.show();
		
	}
	
	
	function toDoctt(){
	   
	    handler();
		document.doctt.submit();
	}

function showAllP(){

	if(p_p_td.style.display=='none')
	{
	p_p_td.style.display='block';
	}else{
	p_p_td.style.display='none';
	}
}
function showJP(){

	var checkAll = document.getElementsByName("checkP");
	//alert(checkAll.length);
	for(var ci=0 ; ci<checkAll.length ; ci++){
		if(checkAll[ci].checked==true){
			document.all.oframe.ShowReviewer(checkAll[ci].value,1);
		}else{
			document.all.oframe.ShowReviewer(checkAll[ci].value,0);
		}
	}
    //oframe.ShowReviewer "莘富莉",0
}
function acceptAll(){
	document.all.oframe.AcceptAllRevisions();
	
	
}
function show_hidden_toobar(){
	var x = document.all.oframe.Toolbars;
    
	document.all.oframe.Toolbars = !x;
}
function show_hidden_menubar(){
	var x = document.all.oframe.Menubar;
	
	document.all.oframe.Menubar = !x
    document.all.oframe.Activate();
}

function show_all_reviewer(){

	var checkAll = document.getElementsByName("checkP");
	 document.all.oframe.ShowAllReviewers();
	
	for(var ci=0 ; ci<checkAll.length ; ci++){
		checkAll[ci].checked = document.all.oframe.GetReviewer(checkAll[ci].value);
		
	}
	
}
</script>
<LINK rel=STYLESHEET type=text/css
			href="/desktop/resources/themes/xtheme-vista/css/xtheme-vista.css">
		<LINK rel=STYLESHEET type=text/css
			href="/desktop/resources/css/desktop.css">

	</head>

	<body onload="openLoad()">
		<img id="myarrow" src="" STYLE="display: none; border: 0">

		<form name="gongwenform" method="post">
			<input type="hidden" name="currentUserId"
				value="${SESSION_CURRENT_USER.userId}" />
			
			<DIV class="x-panel-tbar x-panel-tbar-noheader">
				<DIV class="x-toolbar x-small-editor">
					<table style="WIDTH: auto" class="x-btn-wrap x-btn x-btn-text-icon"
						border=0 cellSpacing=0 cellPadding=0>
						<TBODY>
						<tr>
							<td id="bianjibaocun"><TABLE style="WIDTH: auto" class="x-btn-wrap x-btn x-btn-text-icon" border=0 cellSpacing=0 cellPadding=0>
								<TBODY>
											<TR>
												<TD class=x-btn-left>
													<I>&nbsp;</I>
												</TD>
												<TD class=x-btn-center>
												
														<EM unselectable="on">
															<BUTTON class="x-btn-text saveButton " qtip="操作提示" onclick="handler()">
															   编辑保存
															</BUTTON> 
														</EM>
											
												</TD>
												<TD class=x-btn-right>
													<I>&nbsp;</I>
												</TD>
											</TR>
								</TBODY>
								</TABLE>
							</td>
							
						</tr>
						</TBODY>
					</table>
				</DIV>
			</DIV>
			

					<div id="doc_div" class="x-panel-body" style="height:90%">
					<table width="100%" id="doc_table" height="100%"
						bordercolorlight="#000000" bordercolordark="#ffffff"
						class="labeltable_middle_table">
						<tr>
						<!-- <DIV class="x-toolbar x-small-editor"> -->
							<td width="10%" valign="top" id="p_p_td" class="x-panel-tbar x-panel-tbar-noheader x-toolbar x-small-editor" >
								
								<TABLE style="WIDTH: auto" class="x-btn-wrap x-btn x-btn-text-icon" border=0 cellSpacing=0 cellPadding=0>
									<tbody><tr>
										<td class="x-btn-center" id="p_td"></td>
									</tr></tbody>	
								</TABLE>
							</td>
							<td width="90%" class="labeltable_middle_td_zhengwen">
								<object id="oframe" name="oframe"
									classid="clsid:00460192-9E5E-11d5-B7C8-B8269041DD57"
									width="100%" height="100%">
									<param name="BorderStyle" value="1">
									<param name="TitlebarColor" value="FFFFFF">
									<param name="TitlebarTextColor" value="1">
									<param name="Menubar" value="1">
									<param name="Titlebar" value="0">
									<param name="Toolbar" value="1">
									<param name="TrackRevisions" value=false>
									<param name="ShowRevisions" value=true>
									<param name="AcceptAllRevisions" value=false>

								</object>
							</td>
						</tr>
					</table>
				</div>

			</div>
		</form>
		<OBJECT ID="fileLoad"
			CLASSID="CLSID:54071CE9-8B69-4E41-BF1A-E8550EBB58CF" width="0"
			height="0"></OBJECT>
			<div style="height: 0">
		<form name="doctt" id="doctt"  action="/toViewDocTT.action"
			method="post">
			<input type="hidden" name="activityInstId"
				value="<ww:property value="activityInstId"/>">
			<input type="hidden" name="personId"
				value="<ww:property value="person.ID"/>" />
			<input type="hidden" name="uuid" value="<ww:property value="docid"/>" />
			<input type="hidden" name="processInstId"
				value="<ww:property value="processInst.processInstId"/>">
			<input type="hidden" name="mubanid" id="mubanid" />
		</form>
		</div>
	</body>
	</html>