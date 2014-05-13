<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
		
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
<script type="text/javascript">var context='<%=path%>';</script>
		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		
		<script type="text/javascript"
			src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=path %>desktop/widgets/electronicfile/rollmanager/js/formCheck.js" defer="defer"></script>
		<script type="text/javascript">
			
			Ext.onReady(function(){
			var lwdate;
	if('<ww:property value="dbean.dataId"/>'==''&&'<ww:property value="processInstId"/>'!=''){
		var tt = window.top.gettitle();
		document.getElementById("title").value=tt;
		var miji = window.top.getFileValue('mj');
		var jjcd = window.top.getFileValue('jjcd');
		 if(miji =="mm")miji="秘密";
		 if(miji =="yb")miji="一般";
                        if(miji =="jm")miji="机密";
                        if(miji =="juem")miji="绝密";
                        if(miji =="juemi")miji="绝密";
                        if(jjcd =="yb")jjcd="一般";
                        if(jjcd =="pj")jjcd="平急";
                        if(jjcd =="tj")jjcd="特急";
                        if(jjcd =="jj")jjcd="加急";
                        if(jjcd =="tt")jjcd="特提";
                       
		document.getElementById('miji').value=miji;
		document.getElementById('jjcd').value=jjcd;
		lwdate = window.top.getFileValue('date');
		var lwdw = window.top.getFileValue('cwdw');
			document.getElementById("lwdw").value=lwdw;
			if(document.getElementById("lwdw").value=='undefined'){
				document.getElementById("lwdw").value='';
			}
		var nbyj = window.top.getFileValue('nbyj');
		
		document.getElementById("nbyj").value=nbyj;
		if(document.getElementById("nbyj").value=='undefined'){
				document.getElementById("nbyj").value='';
			}
		
		var ldps = window.top.getYjkValue('ldqf');
		if(ldps==null||ldps==''){
			ldps = window.top.getYjkValue('ldps');
		}
		//alert(ldps);
		document.getElementById("ldps").innerText=ldps;
		
		//alert(lwdw);
	}else if('<ww:property value="dbean.dataId"/>'!=''){
		
		document.getElementById("clj").style.display="none";
		//document.getElementById("btntr").style.display="none";
		document.getElementById('miji').value='<ww:property value="dbean.miji"/>';
		document.getElementById('jjcd').value='<ww:property value="dbean.jjcd"/>';
		lwdate = '<ww:property value="dbean.cwdate"/>';
	}else{
		document.getElementById("clj").style.display="none";
	}
	
	 var cwdate = new Ext.form.DateField({
				applyTo:'cwdate',
				fieldLabel:'日期',
				emptyText:'',
				format:'Y-m-d',
				value:lwdate	
	});
	
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
			alert("添加成功!"+o.responseText);
			//parent.parent.Ext.getCmp('forum-tree').reload();
			location.href="/data_returnaddData.action";
		}
	})
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
	window.top.openUrlWin('/data_getBookMark.action?activityInstId='+activityInstId+'&personId='+personId+'&wordname='+wname+'&dataId='+dataId,'在线编辑');
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
		<form id="addData" name="addData" action="/SwdjAction_addDj.action" method="post">
		<input type="hidden" name="dataId" id="dataId" value='<ww:property value="dataId"/>'/>
<input type="hidden" name="dbean.createdate" id="createdate" value='<ww:property value="dbean.createdate"/>'/>
<input type="hidden" name="dbean.activityInstId" id="activityInstId" value='<ww:property value="activityInstId"/>'/>
<input type="hidden" name="dbean.processDefId" id="processDefId" value='<ww:property value="processDefId"/>'/>
		<input type="hidden" name="UUID" id="UUID" value='<ww:property value="sxxxid"></ww:property>' />
			<table align="center" width="722" height="450" class="tableClass">
				<tr>
					<td height="104" colspan="6" class="tdClass">
						<div align="center" class="STYLE3">
							档案信息登记表
						</div>
					</td>
				</tr>
				<tr>
					<td width="58" height="46" class="tdClass">
						<div align="center">
							<span class="STYLE4">编号</span>
						</div>
					</td>
					<td width="200" align="center" class="tdClass">
						<input name="dbean.recordindex" type="text" value='<ww:property value="dbean.recordindex"/>'></input>
					</td>
					<td width="70" class="tdClass">
						<div align="center">
							<span class="STYLE4">密级</span>
						</div>
					</td>
					<td width="99" align="center" class="tdClass">
						<select id="miji" name="dbean.miji" value='<ww:property value="dbean.miji"/>'>
							<option value="一般">一般</option>
							<option value="秘密">秘密</option>
							<option value="机密">机密</option>
							<option value="绝密">绝密</option>
						</select>
					</td>
					<td width="100" class="tdClass">
						<div align="center">
							<span class="STYLE4">紧急程度</span>
						</div>
					</td>
					<td width="125" align="center" class="tdClass">
						<select id="jjcd" name="dbean.jjcd" value='<ww:property value="dbean.jjcd"/>'>
							<option value="一般">一般</option>
							<option value="平急">平急</option>
							<option value="加急">加急</option>
							<option value="特急">特急</option>
							<option value="特提">特提</option>
						</select>
					</td>
				</tr>
				<tr>
					<td height="57" class="tdClass">
						<div align="center">
							<span class="STYLE4">来文<br>标题</span>
						</div>
					</td>
					<td colspan="5" align="center" class="tdClass">
						<textarea name="dbean.title" id="title" cols="75" rows="2"><ww:property value="dbean.title"/></textarea>
					</td>
				</tr>
				<tr>
					<td height="53" class="tdClass">
						<div align="center">
							<span class="STYLE4">来文<br>单位</span>
						</div>
					</td>
					<td colspan="2" align="center" class="tdClass">
						<textarea cols="33" id="lwdw" rows="2" name="dbean.lwdw"><ww:property value="dbean.lwdw"/></textarea>
					</td>
					<td class="tdClass">
						<div align="center">
							<span class="STYLE4">来文日期</span>
						</div>
					</td>
					<td colspan="2" class="tdClass" style="padding-left:25px">
						<input id="cwdate" type="text" name="dbean.cwdate" value='<ww:property value="dbean.cwdate"/>'></input>
					</td>
				</tr>
				<tr>
					<td height="53" class="tdClass">
						<div align="center">
							<span class="STYLE4">运转号</span>
						</div>
					</td>
					<td colspan="2" class="tdClass" style="padding-left:25px">
						<input id="yzh" type="text" name="dbean.yzh" value='<ww:property value="dbean.yzh"/>'></input>
					</td>
					<td class="tdClass">
						<div align="center">
							<span class="STYLE4">份数</span>
						</div>
					</td>
					<td colspan="2" class="tdClass" style="padding-left:25px">
						<input id="fenshu" type="text" name="dbean.fenshu" value='<ww:property value="dbean.fenshu"/>'></input>
					</td>
				</tr>
				<tr>
					<td height="57" class="tdClass">
						<div align="center">
							<span class="STYLE4">拟办意见</span>
						</div>
					</td>
					<td colspan="5" align="center" class="tdClass">
						<textarea name="dbean.nbyj" id="nbyj" cols="75" rows="4"><ww:property value="dbean.nbyj"/></textarea>
					</td>
				</tr>
				<tr>
					<td height="57" class="tdClass">
						<div align="center">
							<span class="STYLE4">领导批示</span>
						</div>
					</td>
					<td colspan="5" align="center" class="tdClass">
						<textarea name="dbean.ldps" cols="75" id="ldps" rows="6"><ww:property value="dbean.ldps"/></textarea>
					</td>
				</tr>
				<tr>
					<td height="57" class="tdClass">
						<div align="center">
							<span class="STYLE4">阅办签名</span>
						</div>
					</td>
					<td colspan="5" align="center" class="tdClass" style="line-height:20px">
						<textarea name="dbean.ybqm" id="ybqm" cols="75" rows="6"><ww:property value="dbean.ybqm"/></textarea>
					</td>
				</tr>
				<tr>
					<td height="57" class="tdClass">
						<div align="center">
							<span class="STYLE4">处理结果</span>
						</div>
					</td>
					<td colspan="5" align="center" class="tdClass">
						<textarea name="dbean.cljg" id="cljg" cols="75" rows="4"><ww:property value="dbean.cljg"/></textarea>
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