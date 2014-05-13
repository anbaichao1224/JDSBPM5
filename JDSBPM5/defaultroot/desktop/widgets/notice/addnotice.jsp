<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID"%>
<%
	String contextpath = request.getContextPath() + "/";
	String uuid = (new UUID()).toString();
%>
<%
	String attid = new UUID().toString();
%>
<HTML>
	<HEAD>

		<TITLE>新建通知</TITLE>
		<style type="text/css">
<!--
body {
	font-family: "宋体", "Arial";
	font-size: 9pt;
	background-color: #dfe8f6;
	
}

 .STYLE3 {font-size: 24px}
 .STYLE4 {font-size: 16px}

td {
	font-family: "宋体", "Arial";
	font-size: 9pt
}
-->
</style>
		<script type="text/javascript">
	var context="/";
</script>
		<script type="text/javascript" src="/js/extAll.js"></script>
		<script type="text/javascript"
			src="/desktop/widgets/notice/js/notice.js" defer="defer"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript">
		var processid = '<ww:property value="mettingbean.processdefid"/>';
		var bsta = '<ww:property value="mettingbean.blstatus"/>';
		var id = '<ww:property value="mettingbean.tid"/>';  
		var newid = '<%=uuid%>';

    	 function save(){
	           var inceptdept=document.addNotice.noticecontent.value;
	           var title=document.addNotice.niticetitle.value;
	           var sendrange=document.addNotice.sendrange.value;
	           var obj = Ext.getCmp(getActiveFormId());
		           if( title==""){
	           		alert("标题不能为空");
	              	return false;
	              }
	                if(sendrange==""){
	           		alert("发送范围不能为空");
	              	return false;
	              }
	              
	           if(obj.getStore().getCount()==0 && inceptdept==""){
	           		alert("通知内容和附件至少填一个");
	              	return false;
	           }
           
           	 Ext.MessageBox.wait("准备发送", "正在准备数据请稍候...", {
											width : 300
										});
			 Ext.Ajax.request({
			 	url:'noticeAction.action',
			 	method:'post',
			 	form:'addNotice',
			 	success:function(){
			 	Ext.Msg.alert('提示','发送成功');
			 		window.location.href="/desktop/widgets/notice/yfnoticelist.jsp";
			 	},failure:function(){
			 	  	Ext.Msg.alert('提示','发送失败');
			 	   window.location.href="/desktop/widgets/notice/yfnoticelist.jsp";
			 	}
			 })
			 
	}
	   
	
	function ReSet(){
		 Ext.getCmp('addNotice').form.reset();
	}
	

	

</script>

	</HEAD>
	<BODY>
		<center>
			<form name="addNotice" id="addNotice">
				<table width="750" height="720" align="center" cellpadding="0" cellspacing="0" bgcolor="#dfe8f6"
					style="border: 1px solid #99bbe8; ">
					<input type="hidden" name="parentid" id="parentid"
						value='<%=request.getParameter("parentid")%>' />
					<input type="hidden" name="tid" id="tid" value='<%=uuid%>' />
					<input type="hidden" name="optionnum" id="optionnum" value='1' />
					<input type="hidden" name="sms" id="sms" />
					<input type="hidden" name="flag1" id="flag1" value='0'/>
					<input type="hidden" name="mettinguuid" id="mettinguuid"
						value='<%=request.getParameter("mettinguuid")%>' />
					<input type="hidden" name="adddirection" id="adddirection"
						value='2' />
					<input type="hidden" name="liststatus" id="liststatus"
						value='<%=request.getParameter("liststatus")%>' />
					<tr>
						<td width="84" height="40">
							<div align="center">
								<span class="STYLE4">起草人</span>
							</div>
					   </td>
					   
						<td >
							<input  name="createname" type="text" id="createname"
								value='<ww:property value="$currPerson.name"/>' >
						    </input>
						</td>

						<td width="84" align="center">
						  <span class="STYLE4">起草单位</span>
						</td>
						<td>
							<input  name="createdept" type="text" id="createdept"
								value='<ww:property value="$currPerson.orgList[0].name"/>' />
						</td>
					</tr>

				
					<tr>
						<td width="84" align="center"height="30">
						   <span class="STYLE4">联系人</span>
						</td>
						<td>
							<input width="84" name="contactperson" type="text"
								id="contactperson" value='<ww:property value="noticedao.contactperson"/>' />
						</td>
				
						<td width="84" align="center">
							<span class="STYLE4">联系电话</span>
						</td>
						<td>
							<input  name="contacttle" type="text" id="contacttle"
							 value='' />
						</td>
					</tr>
					<tr>
						<td width="84" align="center" height="30">
						   <span class="STYLE4">签发人</span>
						</td>
						<td>
							<input  name="issuer" width="84" type="text" id="issuer"
							 value='<ww:property value="noticedao.issuer"/>' />
						</td>
						<td width="84" align="center">
							<span class="STYLE4">签发时间</span>
						</td>
						<td align="left">
							<input name="issuerdate" type="text" id="createdate2" value='' />
						</td>
					</tr>
					<tr>
						<td width="84" align="center" height="80">
						   <span class="STYLE4">标    题 <font face="verdana,arial,sans-serif" color="red">*</font></span>
						</td>
						<td colspan="3"><textarea  required type="text" name="niticetitle" id="niticetitle" cols="68" rows="4"><ww:property value="noticedao.niticetitle"/></textarea>
						</td>
					</tr>
				
					<tr>
						<td  width="84" align="center" height="80">
							<span class="STYLE4">通知内容</span>
						</td>
						<td colspan="3"><textarea name="noticecontent" cols="68" rows="10" id="noticecontent"></textarea>
						</td>
					</tr>
						
					<tr>
						<td align="center" width="84" height="60">
							<span class="STYLE4">发送范围 <font face="verdana,arial,sans-serif" color="red">*</font></span>
						</td>
						<td colspan="3"><textarea size="79" name="sendrange" height="30" type="text" id="sendrange" onkeydown="return false;" size="50" rows="3" cols="65"></textarea>
							<input type="hidden" name="sendpersonid" id="personid" />
							<input name="choice" size="70" type="button" id="choice"
								value="选择" onclick="createPersonPositionWindow('5015','')" />
					</tr>
				
					<tr>
					    <td height="150" align="center" class="tdClass">
						<div align="center">
							<p class="STYLE4">
								附
							</p>
							&nbsp;
							&nbsp;
							&nbsp;
							<p class="STYLE4">
								件
							</p>
						</div>
					</td>
					<!-- <td colspan="4" style="width:50px" >
						<ww:action name="docexchangefileinclude" executeResult="true"></ww:action>
					</td> -->
					<td colspan="4" >
						<div id='attachment'></div>
					</td>
				</tr>
					<tr>
						<td colspan="4" align="center">
							<input type="button" name="savematter" onclick="save()"
								value="发送" />
							&nbsp;&nbsp;
							<input type="button" name="savematter" onclick="ReSet()"
								value="重置">
							&nbsp;&nbsp;
						</td>
					</tr>
					
				</table>
			</form>
		</center>
	</BODY>
	
<script language="JavaScript">
    Ext.onReady(function(){

	var cadate = new Ext.form.DateField({
		applyTo:'createdate2',
		value:new Date(),
		fieldLabel:'日期',
		emptyText:'请选择',
		format:'Y-m-d H:i:s'
	});
	
});

function getActiveFormId()
	{
	  return  '<%=attid%>';
	}
 	Ext.onReady(function(){
	var personid = '<ww:property value="person.iD"/>';
	var sxxxid = '';
	var blstatus = '<ww:property value="mettingbean.blstatus"/>';
	if(blstatus!=""){
		sxxxid = '<ww:property value="mettingbean.tid"/>';
	}
	if(sxxxid==""){
		sxxxid = document.getElementById("tid").value;
	}
	var contextfile = '<ww:property value="filePath"/>';
	//全部的属性值
	//alert();	
	var openF='<ww:property value="open"/>';
	if(openF == null || openF == '')openF='true';
	var notabF='<ww:property value="notable"/>';
	if(notabF == null || notabF == '')notable='false';
	var onlyfF='<ww:property value="onlyfname"/>';
	if(onlyfF == null || onlyfF == '')onlyfF='false';
	var filecF='<ww:property value="filecount"/>';
	if(filecF == null || filecF=='')filecF='50';
	filecF = parseInt(filecF);
	var widthF='<ww:property value="width"/>';
	if(widthF == null || widthF == '' )widthF='550';
	widthF = parseInt(widthF);
	if(widthF>650){
		widthF = 500;
	}
	var heightF='<ww:property value="height"/>';
	if(heightF == null || heightF == '')heightF='200';
	heightF = parseInt(heightF);
	var bcF='<ww:property value="background_color"/>';
	if(bcF == null || bcF == '')bcF='white';
	var textAF='<ww:property value="text_align"/>';
	if(textAF == null || textAF == '')textAF='left';
	var vertAF='<ww:property value="vertical_align"/>';
	if(vertAF == null || vertAF == '')vertAF='middle';	
	var nameWith=widthF * 0.3;

 

function refFileGridById(){
  var obj=Ext.getCmp(getActiveFormId());
  if(obj){
    try{
      obj.getStore().reload();
       var bb = obj.getBottomToolbar();
    if (bb) {
      bb.setPosition(0, 0);
      bb.hide();
      bb.show();
    }
    }catch (e) {
      alert(e) ;
    }
  }
};
	function showAttachWin (){
	var accachGridCfg={
	
	  selfCfg:{
	  	
	    width:widthF,
	    height:heightF,
        id:getActiveFormId(),
     	align:textAF,
		renderTo:'attachment',	
			 tbar: [
			 <ww:if test="add!='false'">
			 
			 {
	                   text:'添加文件',
	                   handler: function(){
	                   	var dia=new Ext.ux.UploadDialog.Dialog({upload_autostart:true,url:contextfile+'mattachUpload1.action',base_params:{'sxxxid':sxxxid,'personId':personid}});
	                     dia.on('hide',function(){refFileGridById();});
	                     dia.show();
	                    }
	                }
	                
	           <ww:if test="del!='false'">
	                 ,
	           </ww:if>
	           </ww:if>
	              
	            <ww:if test="del!='false'">  
	                {
	                   text:'删除文件',
	                   handler: function(){
         				deleteFile(Ext.getCmp(getActiveFormId()));
         				alert("删除成功");
         				refFileGridById();
	                    }
	                }
	             </ww:if>
	       ] 
	  },
	  metaData:{
	  	
	  	hasChockbox:true,
	    dataType:"json",
	    hasRowNum:true,  
	   paging:{
	     totalProperty:"totalCount",
	      root:"datas",
	      pageSize:filecF
	    },   
	   
	   cols:[
	       {text:"index",name:'index',isDisplay:'false'},
	       {text:"uuid",name: 'uuid',isDisplay:'false'},
	       {text:"文件名称",name: 'filename',width:nameWith},
	       {text:"上传人",name: 'uploaduser',width:60},    
	       {text:"上传时间",name: 'uploadtime',sortable:true,width:120},
	       {text:"文件类型",name: 'filetype',width:70}
	          
	    ]
	  },
	  dataUrl:"mfileList.action?sxxxid="+sxxxid+"&personId="+personid
	};
	<ww:if test="disabled!='true'">
	createGridByData(accachGridCfg);
	refFileGridById();
	</ww:if>
  }
  
try{
showAttachWin();
}catch(e){

}

  function deleteFile(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alertMsg('请选择需要删除的文件');
	   return;
	   }
 
	var delAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('uuid').length>0 ){     
	    delAllList[delAllList.length]= selections[i].get('uuid');    
	   }else{
	    Ext.getCmp(gridId).getSelectionModel().deselectRow(selections[i].get('index'));   
	   }
	  };
	   var fileList=delAllList.join();
	    var str="fileId="+fileList;
	  JDS.ajax.Connection.LoadJsonFromUrl(contextfile+'mfiledel.action',null,str);
	  
		  
}


});	
</script>
</HTML>