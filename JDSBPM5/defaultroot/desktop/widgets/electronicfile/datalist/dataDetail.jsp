<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<HTML>
<HEAD>		
<style type="text/css">
<!--
.STYLE3 {font-size: 36px}
.STYLE4 {font-size: 20px}
-->
.tableClass{
 border-collapse: collapse; 
} 
.tdClass{
    border:1px solid #000000;
}

</style>
		<script type="text/javascript">var context='/';</script>
		<script type="text/javascript" src="/js/extAll.js"></script>		
		<script type="text/javascript" src="/desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript" src="/desktop/widgets/electronicfile/rollmanager/js/formCheck.js" defer="defer"></script>
		<script type="text/javascript">
			
			Ext.onReady(function(){
			if('<ww:property value="dbean.dataId"/>'==''&&'<ww:property value="processInstId"/>'!=''){
				
				//标题
				var tt = window.top.gettitle();
				document.getElementById("title").value=tt;
				
				//文件编号，运转号	
				
				var lwbh = window.top.getFileValue('lwbh');
				var swbh= window.top.getFileValue('swbh');
				var bh=window.top.getFileValue('bh');
				if(lwbh==null){
					lwbh=swbh;
					if(lwbh==null){
						lwbh=bh;
					}
				}
				if(lwbh!=null){
					document.getElementById('yzh').value=lwbh;
				}		
				
				//密级
				var miji = window.top.getFileValue('mj');
				if(miji==null || miji==""){
					miji = window.top.getFileValue('mmdj');
				}
						
				if(miji =="明文")miji="明文";
		        
		                   
				document.getElementById('miji').value=miji;
				
			}else if('<ww:property value="dbean.dataId"/>'!=''){
				
				document.getElementById("clj").style.display="none";
				document.getElementById('miji').value='<ww:property value="dbean.miji"/>';
			}else{
				document.getElementById("clj").style.display="none";
			}
			
		})
function saveDataInfo(){
	if(checkForm('addData')){
	window.top.saveData();
	Ext.Msg.wait('处理中，请稍后...', '提示'); 
	Ext.Ajax.request({
		timeout: 6000000,
		async : false,
		url:'/data_save.action',
		method:'post',
		form:'addData',
		waitMsg:'正在保存数据,请等待...',
		success:function(o){
		    Ext.Msg.hide();
			//alert("添加成功!"+o.responseText);
			alert('信息添加成功!');
			//parent.parent.Ext.getCmp('forum-tree').reload();
			//location.href="/data_returnaddData.action";
			//parent.Ext.getCmp('positionWin').close();
		}
	})
	return true;
	}else{
		return false;
	}
}



function openWord(){
	//window.top.openUrlWin('/desktop/widgets/electronicfile/datalist/createDocument.jsp');
	var activityInstId = '<ww:property value="activityInstId"/>';
	var personId = '<ww:property value="personId"/>';
	var formId = '<ww:property value="formId"/>';
	var dataId = '<ww:property value="dataId"/>';
	var wname = window.top.getWordname();
	window.top.createWin('/data_getBookMark.action?activityInstId='+activityInstId+'&personId='+personId+'&wordname='+wname+'&dataId='+dataId,'在线编辑');
	/*
	var win = new Ext.Window({
		id:'openWord',
		tilte:'在线编辑',
		width:Ext.getBody().getWidth()-30,
		height:Ext.getBody().getHeight()-30,
		html:'<iframe frameborder="0" src="data_getBookMark.action?activityInstId='+activityInstId+'&personId='+personId+'&formId='+personId+'" width="100%" height="100%"></iframe>'
	});
	win.show();*/
}

function winclose(){
	
	
}
		</script>
		</HEAD>
		<body>
		<form id="addData" name="addData" action="/SwdjAction_addDj.action" method="post">
		<input type="hidden" name="personid" id="personid" value='<ww:property value="personId"/>'/>
		<input type="hidden" name="dataId" id="dataId" value='<ww:property value="dataId"/>'/>
<input type="hidden" name="dbean.createdate" id="createdate" value='<ww:property value="dbean.createdate"/>'/>
<input type="hidden" name="dbean.activityInstId" id="activityInstId" value='<ww:property value="activityInstId"/>'/>
<input type="hidden" name="dbean.processDefId" id="processDefId" value='<ww:property value="processDefId"/>'/>
		<input type="hidden" name="UUID" id="UUID" value='<ww:property value="sxxxid"></ww:property>' />
			<table align="center" width="720" height="450" class="tableClass">
				<tr>
					<td height="104" colspan="6" class="tdClass">
						<div align="center" class="STYLE3">
							档案信息登记表
						</div>
					</td>
				</tr>
				<tr>
					<td width="120" height="57" class="tdClass">
						<div align="center">
							<span class="STYLE4">编号</span>
						</div>
					</td>
					<td width="240" colspan="2" class="tdClass" style="padding-left:25px">
						<input id="yzh" type="text" name="dbean.yzh" value='<ww:property value="dbean.yzh"/>'></input>
					</td>
					<td width="120" class="tdClass">
						<div align="center">
							<span class="STYLE4">密级</span>
						</div>
					</td>
					<td width="240" colspan="2" align="center" class="tdClass">
						<select id="miji" name="dbean.miji" value='<ww:property value="dbean.miji"/>'>
							<option value="明文">明文</option>
						</select>
					</td>
				</tr>
				<tr>
					<td height="57" class="tdClass">
						<div align="center">
							<span class="STYLE4">标题</span>
						</div>
					</td>
					<td colspan="5" align="center" class="tdClass">
						<textarea name="dbean.title" id="title" cols="75" rows="2"><ww:property value="dbean.title"/></textarea>
					</td>
				</tr>
				<tr id="clj">
					<td height="57" class="tdClass">
						<div align="center">
							<span class="STYLE4">处理笺</span>
						</div>
					</td>
					<td colspan="5" align="center" class="tdClass">
						<input type="button" value="生成处理笺" name="adddata.addbtn" onclick="openWord()"/>
					</td>
				</tr>
				<tr>
					<td height="160" align="center" class="tdClass">
						<div align="center">
							<p class="STYLE4">
								附
							</p>
							<p class="STYLE4">
								件
							</p>
						</div>
					</td>
					<td colspan="5" style="width:50px" class="tdClass">
						<ww:action name="rfileinclude" executeResult="true"></ww:action>
					</td>
				</tr>
			</table>
		</form>
		</body>
		</html>