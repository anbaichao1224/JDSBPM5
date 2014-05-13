<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String contextpath = request.getContextPath() + "/";
	String uuid = (new UUID()).toString();
%>
<%
	String attid = new UUID().toString();
%>

<HTML>
	<HEAD>
		
<style type="text/css">
<!--
body {font-family: "宋体", "Arial";background-color:#dfe8f6}
.STYLE3 {font-size: 24px}
.STYLE4 {font-size: 20px}
-->
</style>
<script type="text/javascript">
	var context="/";
</script>

		<script type="text/javascript" src="/js/extAll.js"></script>

		<script type="text/javascript"
			src="/desktop/widgets/nbgwjh/js/departtree.js" defer="defer"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js" defer="defer"></script>
		<script src="/js/JDS.js" type="text/javascript" defer="defer"></script>
<script type="text/javascript">
 //alert('<ww:property value="zdjuuid"></ww:property>');
</script>
	</HEAD>
	<BODY> 
		<center>
			<form name="djnbgwjh" id="djnbgwjh" action="nbgwjhAction_save.action" method="post">
				<table align="center" width="750" height="530" border="1"
				cellpadding="0" cellspacing="0">
					<input type="hidden" name="parentid" id="parentid"
						value='<%=request.getParameter("parentid")%>' />
					<input type="hidden" name="tid" id="tid" value='<ww:property value="rfod.uuid"/>' />
					<input type="hidden" name="zdjuuid" id="zdjuuid" value='<ww:property value="zdjuuid"></ww:property>' />
					<input type="hidden" name="optionnum" id="optionnum" value='1' />
					<input type="hidden" name="mettinguuid" id="mettinguuid"
						value='<%=request.getParameter("mettinguuid")%>' />
					<input type="hidden" name="adddirection" id="adddirection"
						value='2' />
					<input type="hidden" name="liststatus" id="liststatus"
						value='<%=request.getParameter("liststatus")%>' />
						
						
				 <tr>
					<td width="65" height="46">
						<div align="center">
							<span class="STYLE4">发文人</span>
						</div>
					</td>
					<td width="65" align="center">
						<input name="sendbean.sendor" type="text" readOnly="true" value='<ww:property value="$currPerson.name"/>'></input>
					</td>
					<td width="80">
						<div align="center">
							<span class="STYLE4">发文单位</span>
						</div>
					</td>
					<td width="99" align="center">
						<input name="sendbean.senddept" type="text" readOnly="true" value='<ww:property value="$currPerson.orgList[0].name"/>'></input>
					</td>
					
				</tr>
				<tr>
					<td height="57">
						<div align="center">
							<span class="STYLE4">标 题</span> <font color=red>*</font>
						</div>
					</td>
					<td colspan="4" align="center">
						<textarea name="sendbean.sendTitle" id="sendTitle" cols="75" rows="5" ><ww:property value="rfod.docbt"/></textarea>
					</td>
				</tr>		
					<tr>
					<td height="57">
						<div align="center">
							<span class="STYLE4">备 注 </span>
						</div>
					</td>
					<td colspan="4" align="center">
						<textarea name="sendbean.sendTitle1" cols="75" rows="5"></textarea>
					</td>
				</tr>
					<tr>
						<td align="center" width="184">
							<span class="STYLE4">接收人</span>  <font face="verdana,arial,sans-serif" color="red">*</font>:
						</td>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<td colspan="3">
							<textarea size="70" class="required" name="deptnames" type="text"
								id="deptnames" onkeydown="return false;" cols="65" rows="5"></textarea>
							<input type="hidden" name="id" id="id" />
							<input name="choice" size="70" type="button" id="choice"
								value="选择" onclick="createPersonPositionWindow('5015','')" />
					</tr>
					<tr>
						<td height="150" align="center" class="tdClass">
							<div align="center">
								<span class="STYLE4">附 件</span><font color=red>*</font>
								
							</div>
					  </td>
					<td colspan="4" >
						<div id='attachment'></div>
					</td>
					</tr>
				</table>
			</form>
		</center>
	</BODY>
<script type="text/javascript">

		//获得上传附件的数量
			function getCount(){
				var obj = Ext.getCmp(getActiveFormId());
				return obj.getStore().getCount();
			}
			
			//获得标题
			function getCount(){
				var obj = Ext.getCmp(getActiveFormId());
				return obj.getStore().getCount();
			}
			
function getActiveFormId()
	{
	  return  '<%=attid%>';
	}
 	Ext.onReady(function(){
	var personid = '<ww:property value="person.iD"/>';
	var sxxxid = '';
	var blstatus = '<ww:property value="mettingbean.blstatus"/>';//matterInfoListBean
	if(blstatus!=""){
		sxxxid = '<ww:property value="mettingbean.tid"/>';
	}
	if(sxxxid==""){
		sxxxid = document.getElementById("tid").value;
		//sxxxid = "1b157ef-132674b53a8-2f87dce8d8643a73d4d0ede3f3cf44cb";
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
					 {
	                   text:'添加文件',
	                   handler: function(){
	                   	var dia=new Ext.ux.UploadDialog.Dialog({upload_autostart:true,url:contextfile+'mattachUpload1.action',base_params:{'sxxxid':sxxxid,'personId':personid}});
	                     dia.on('hide',function(){refFileGridById();});
	                     dia.show();
	                    }
	                },{
	                   text:'删除文件',
	                   handler: function(){
         				deleteFile(Ext.getCmp(getActiveFormId()));
         				alert("删除成功");
         				refFileGridById();
	                    }
	                }
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