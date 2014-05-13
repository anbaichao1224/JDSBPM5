<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="net.itjds.j2ee.util.UUID" %>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<% 
String attid = new UUID().toString();


 %> 
 <div id='<%=attid %>'></div>

 <script>
   function getActiveFormId1()
	{
	  return  '<%=attid%>';
	}
 	Ext.onReady(function(){
	var personid = '<ww:property value="person.iD"/>';
	var yewuuuid = '';
	var blstatus = '<ww:property value="mettingbean.blstatus"/>';//matterInfoListBean
	if(blstatus!=""){
		yewuuuid = '<ww:property value="mettingbean.yewuuuid"/>';
	}
	if(yewuuuid==""){
		yewuuuid = document.getElementById("yewuuuid").value;
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

 

function refFileGridById(){
  var obj=Ext.getCmp(getActiveFormId1());
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
        id:getActiveFormId1(),
     	align:textAF,
		renderTo:getActiveFormId1()
	
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
	       {text:"文件名称",name: 'displayname',width:nameWith},
	       {text:"文件类型",name: 'filetype',width:70}
	          
	    ]
	  },
	  dataUrl:"hqzhengwen.action?yewuuuid="+yewuuuid+"&personId="+personid
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


});	
</script>