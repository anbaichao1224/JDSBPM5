<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="net.itjds.j2ee.util.UUID" %>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<% 
String attid = new UUID().toString();


 %> 
 <div id='<%=attid %>'></div>

 <script>
 	
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
	if(widthF == null || widthF == '' )widthF='650';
	widthF = parseInt(widthF);
	if(widthF>650){
		widthF = 650;
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
	var nameWith=widthF * 0.45;

	function getActiveFormId()
	{
	  return  '<%=attid%>';
	} 

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
		renderTo:getActiveFormId(),	
				
			 tbar: [
			 <ww:if test="add!='false'">
			 
			 {
			 
	                   text:'添加文件',
	                   handler: function(){
	                   	var dia=new Ext.ux.UploadDialog.Dialog({upload_autostart:true,url:contextfile+'mattachUpload.action',base_params:{'sxxxid':sxxxid,'personId':personid}});
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

try{
showAttachWin();
}catch(e){
	JDS.alert(e);
}
});	
</script>