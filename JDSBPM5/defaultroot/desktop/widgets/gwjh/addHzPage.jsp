<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String uuid = (new UUID()).toString();
	String inceptid = request.getParameter("uuid");
	String sendid = request.getParameter("id");
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
body {font-family: "宋体", "Arial";background-color:#dfe8f6}
.STYLE3 {font-size: 24px}
.STYLE4 {font-size: 20px}
-->
</style>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript"
			src="/desktop/widgets/gwjh/js/bmtree.js" defer="defer"></script>
		<script type="text/javascript"
			src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script src="/js/JDS.js" type="text/javascript"></script>
		<script type="text/javascript">
			
		</script>	
	</head>
	<body>
		<form id="addHz" action="gwjhAction_addHz.action" method="post">
		<input type="hidden" name="parentid" id="parentid" value='<%=request.getParameter("parentid") %>'/>
		<input type="hidden" name="tid" id="tid" value='<%=uuid %>'/><input type="hidden" name="optionnum" id="optionnum" value='1'/>
		<input type="hidden" name="mettinguuid" id="mettinguuid" value='<%=request.getParameter("mettinguuid") %>'/>
		<input type="hidden" name="adddirection" id="adddirection" value='2'/>
		<input type="hidden" name="liststatus" id="liststatus" value='<%=request.getParameter("liststatus")%>'/>
		<input type="hidden" name="uuid" id="uuid" value='<%=inceptid%>' />
		<input type="hidden" name="id" id="id" value='<%=sendid%>' />
			<table align="center" width="750" height="491" border="1"
				cellpadding="0" cellspacing="0">
				<tr>
					<td height="45" colspan="6">
						<div align="center" class="STYLE3">
							 回复信息
						</div>
					</td>
				</tr>
				<tr>
					<td width="183" height="46">
						<div align="center">
							<span class="STYLE4">回复人</span>
						</div>
					</td>
					<td  width="188" align="center">
						<input name="replybean.replyPerson" type="text" readOnly="true" value='<ww:property value="$currPerson.name"/>'></input>
					</td>
					<td width="183">
						<div align="center">
							<span class="STYLE4">回复单位</span>
						</div>
					</td>
					<td width="188" align="center">
						<input name="replybean.replyDept" type="text" readOnly="true" value='<ww:property value="deptName"/>'></input>
					</td>
				</tr>
				<tr>
					<td height="57">
						<div align="center">
							<span class="STYLE4">回复<br>
							内容</span>
						</div>
					</td>
					<td colspan="4" align="center">
						<textarea name="replybean.replyContent" cols="75" rows="5"></textarea>
					</td>
				</tr>
				<tr>
					<td height="150" align="center" class="tdClass">
						<div align="center">
							<p class="STYLE4">
								附
							</p>
							<p class="STYLE4">
								件
							</p>
						</div>
					</td>
					<td colspan="5">
							<div id='attachment'></div>
					</td>
					
				</tr>
			</table>
		</form>
		
	</body>
	<script>
	
	//获得上传附件的数量
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
	if(widthF == null || widthF == '' )widthF='500';
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
</html>
