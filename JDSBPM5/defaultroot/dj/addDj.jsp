<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	 
%>
<%
	String attid = new UUID().toString();
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script>var context='<%=path%>';</script>
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

.STYLE6 {font-size: 20px; font-weight: bold; }
</style>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript"
			src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript">
			
			
		</script>
	</head>
	<body>
		<form id="addDj" name="addDj" action="/SwdjAction_addDj.action"  method="post">
		<input type="hidden" name="tid" id="tid" value='<ww:property value="sxxxid"></ww:property>' />
		<input type="hidden" name="xmlmodel" id="xmlmodel" value='<ww:property value="xmlmodel"></ww:property>' />
		<input type="hidden" name="UUID" id="UUID" value='<ww:property value="sxxxid"></ww:property>' />
			<table align="center" width="771" height="654" class="tableClass">
<tr>
					<td height="104" colspan="8" class="tdClass">
						<div align="center" class="STYLE3">
							收文信息登记表	</div></td>
				</tr>
				<tr>
					<td width="102" height="60" bgcolor="#d2dff0" class="tdClass">
						<div align="center">
							<span class="STYLE4">收文<br>编号</span></div></td>
					<td align="left" valign="middle" class="tdClass">
				  		<input name="djbh" type="text" class="STYLE4" size="20">
				  		</input></td>
				   <td width="99" bgcolor="#d2dff0" class="tdClass">
				  		<div align="center" class="STYLE4">
				  		等级	</div></td>
					<td width="186" align="left" class="tdClass">
						<select name="djjjcd" class="STYLE4" id="djjjcd">
							<option value="平急">平急</option>
							<option value="加急">加急</option>
							<option value="特急">特急</option>
							<option value="特提">特提</option>
					  </select></td>
				</tr>
                <tr>
					<td height="70" bgcolor="#d2dff0" class="tdClass">
						<div align="center">
							<span class="STYLE4">原文<br>
					  编号</span></div></td>
					<td align="left" class="tdClass"><label><input name="lwbh" type="text" class="STYLE4" id="lwbh" size="20"></label></td>
					<td bgcolor="#d2dff0" class="tdClass">
							<div align="center">
								<span class="STYLE4">登记人</span></div></td>
					<td colspan="2" align="left" class="tdClass">
						<input name="djr" type="text" class="STYLE4" id="djr" value='<ww:property value="$currPerson.name"/>'></input></td></tr>
						<tr>
					<td height="79" bgcolor="#d2dff0" class="tdClass">
					  <div align="center">
					  <span class="STYLE4">来电<br>
			             编号</span></div>	</td>
					<td align="left" class="tdClass"><label><input name="ldbh" type="text" class="STYLE4" id="ldbh" size="20"></label></td>
					<td bgcolor="#d2dff0" class="tdClass"><div align="center">
					<span class="STYLE4">来文<br>
						  日期</span></div></td>
					<td colspan="2" align="left" class="tdClass"><input name="djlwrq" type="text" class="STYLE4" id="djlwrq"></input>					</td>
				</tr>
					<tr>
					<td height="88" bgcolor="#d2dff0" class="tdClass"><div align="center">
				  <span class="STYLE4">密级<br></span></div></td>
				  <td align="center" class="tdClass"><div align="left"> <input name="mj" type="text" readOnly=true class="STYLE4" id="mj" value="明文"> </div></td>
				    <td height="77" bgcolor="#d2dff0" class="tdClass"><div align="center">
					<span class="STYLE4">发文<br>
					  单位</span></div></td>
				   <td colspan="5" align="left" class="tdClass"><input name="djlwdw"  class="STYLE4" value='<ww:property value="rbean.department"/>'/></td>
			    </tr>
				 <tr>
					<td height="70" class="tdClass"  bgcolor="#d2dff0">
						<div align="center">
							<span class="STYLE4">来文<br>标题</span></div></td>
					<td colspan="7" align="left" class="tdClass"><textarea name="djlwbt" cols="50" rows="2" class="STYLE4"><ww:property value="rbean.docbt"></ww:property></textarea></td>
				</tr>

		<tr>
					<td height="150" align="center" class="tdClass"  bgcolor="#d2dff0">
						<div align="center">
							<p class="STYLE4">
								附							</p>
							<p class="STYLE4">
								件							</p>
						</div>					</td>
					<td colspan="4" class="tdClass">
						<div id='attachment'></div>
					</td>
				</tr>

		  </table>
	</form>
	</body>
	<script type="text/javascript">
	function getActiveFormId()
	{
	  return  '<%=attid%>';
	}
 	Ext.onReady(function(){
 	 var kssj = new Ext.form.DateField({
				applyTo:'djlwrq',
				fieldLabel:'日期',
				emptyText:'请选择',
				format:'Y-m-d',
				value:'<ww:property value="$CurrTime.currDate"/>'	
			  });
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
         			//setTimeout("refFileGridById()",500);
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
</html>