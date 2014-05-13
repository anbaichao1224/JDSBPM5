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
body {
	font-family: "normal tahoma,arial,helvetica,sans-serif";
	font-size: 14px;
	background-color: #dfe8f6
}

td {
	font-family: "normal tahoma,arial,helvetica,sans-serif";
	font-size: 14px
}
-->
</style>
<script type="text/javascript">
	var context="/";
</script>

		<script type="text/javascript" src="/js/extAll.js"></script>
		<script type="text/javascript"
			src="/desktop/widgets/gwjh/js/departtree.js" defer="defer"></script>
		<script type="text/javascript"
			src="<%=contextpath%>desktop/js/CreateGrid.js" defer="defer"></script>
		<script src="/js/JDS.js" type="text/javascript" defer="defer"></script>

	</HEAD>
	<BODY>
<script type="text/javascript">
var processid = '<ww:property value="mettingbean.processdefid"/>';
var bsta = '<ww:property value="mettingbean.blstatus"/>';
var id = '<ww:property value="mettingbean.tid"/>'; 

</script>
		<center>
			<div id="addform"></div>
			<form name="addSend" id="addSend">
				<table width="800" height="350" align="center" bgcolor="#dfe8f6"
					style="border: 1px solid #99bbe8; line-height: 30px;">
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
						<td align="left" width="180">
							接收单位 <font face="verdana,arial,sans-serif" color="red">*</font>:
						</td>
						<td colspan="3">
						
							<textarea size="85" class="required" name="deptnames" type="text"
								id="deptnames" onkeydown="return false;" cols="65" rows="4"></textarea>
							<input type="hidden" name="id" id="id" />
							<input name="choice" size="70" type="button" id="choice"
								value="选择" onclick="createPersonPositionWindow('5015','')" />
					</tr>
					<tr>
						<td align="center" colspan="2">
							<input type="button" name="savematter" disable="true" onclick="save()"
								value="发送">
								

							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" name="savematter" onclick="ReSet()"
								value="重置">
							
						<td />
					</tr>
					<tr>
						<td valign="top" align="left">
							附&nbsp;&nbsp;件：
							
						</td>
						<td>
							<div id='attachment'></div>
						</td>
					</tr>
				
				</table>
			</form>
		</center>
	</BODY>
	<script type="text/javascript">
  
     
     
var uuid = '<%=uuid%>';
Ext.onReady(function(){
try{
showAttachWin();
}catch(e){

}
    	var mj = [['mw', '明文']];

		var jjcd = [['pj', '平急'], ['jj', '加急'], ['tj', '特急'], ['tt', '特提']];

		var mjstore = new Ext.data.SimpleStore({
					fields : ['value', 'text'],
					data : mj
				});

		var jjcdstore = new Ext.data.SimpleStore({
					fields : ['value', 'text'],
					data : jjcd
				});

		var combo = new Ext.form.ComboBox({
					store : mjstore,
					fieldLabel : '密 级',
					width:200,
					name : 'sendbean.sendMj',
					emptyText : '明文',
					mode : 'local',
					valueField : 'value',
					displayField : 'text',
					triggerAction : 'all'
				});

		var jjcdcombo = new Ext.form.ComboBox({
					store : jjcdstore,
					fieldLabel : '等  级',
					name : 'sendbean.sendJjcd',
					emptyText : '平急',
					width:200,
					mode : 'local',
					valueField : 'value',
					displayField : 'text',
					triggerAction : 'all'
				});
				
		var addForm = new Ext.form.FormPanel({
			labelAlign : 'left',
			labelWidth : 80,
			width:800,
			height:300,
			frame : true,
			fileUpload:true,
			id:'addform',
			url : '09_01.jsp',
			items : [ {
			    layout:'column',
			    items:[{
			       columnWidth:.5,
			       layout:'form',
			       defaultType:'textfield',
			       items:[
			       {fieldLabel:'密    级',readOnly:true ,name:'sendbean.sendMj',width:180,value:'明文'},
			       {fieldLabel:'发文单位',readOnly:true ,width:180,name:'sendbean.senddept',value:'<ww:property value="deptName"/>'},
			       {fieldLabel:'发电编号',name:'sendbean.sendType',width:180},
			       {fieldLabel:'签 发 人',width:180,name:'sendbean.sendSigner',value:'<ww:property value="senddao.sendSigner"/>'},
			       {fieldLabel:'联 系 人',width:180,name:'sendbean.sendLxr'}
			     
			       ]
			    },
			    {
			       columnWidth:.5,
			       layout:'form',
			       defaultType:'textfield',
			       items:[
			         jjcdcombo,
			       {fieldLabel:'文   号',width:180,name:'sendbean.sendWh',value:'<ww:property value="senddao.sendWh"/>'},
			       {fieldLabel:'页   数',width:180,name:'sendbean.sendPages',blankText: '只能输入数字', regex:/^[0-9]+$/,regexText: '只能输入数字' },
			       {fieldLabel:'签发时间',xtype:'datefield',value:new Date(),format: "Y-m-d H:i:s",width:200,name:'sendbean.sendSignTime'},
			       {fieldLabel:'联系电话',width:180,name:'sendbean.sendLxdh'}
			      ]}]
			},{
			  width:570,
			  height:40,
			  xtype:'textarea',
			  fieldLabel:'标   题 <font color=red>*</font>',
			  allowBlank: false,
			  name:'sendbean.sendTitle',
			  value:'<ww:property value="senddao.sendTitle"/>'
			}
			,{
			  width:570,
			  height:80,
			  xtype:'textarea',
			  fieldLabel:'内   容',
			  name:'sendbean.sendScope'
			}]
	
		});
		addForm.render('addform');
		

	});
	

     function save(){
        if(Ext.getCmp('addform').form.isValid()) {
            var inceptdept=document.addSend.deptnames.value
           if(inceptdept==""){
              alert("接受单位不能为空");
              return false;
           }
           
           var obj = Ext.getCmp(getActiveFormId());
         //等候进度条
  			Ext.MessageBox.wait("准备发送", "正在准备数据请稍候...", {
											width : 300
										});
       
			 Ext.Ajax.request({
			 	
			 	url:'gwjhAction_save.action',
			 	method:'post',
			 	waitMsg:'正在处理，请稍候...',
			 	params:{
			 	'sendbean.sendMj':Ext.get('sendbean.sendMj').dom.value,
			 	'sendbean.sendJjcd':Ext.get('sendbean.sendJjcd').dom.value,
			 	'sendbean.sendType':Ext.get('sendbean.sendType').dom.value,
			 	'sendbean.sendSignTime':Ext.get('sendbean.sendSignTime').dom.value,
			 	'sendbean.sendWh':Ext.get('sendbean.sendWh').dom.value,
			 	'sendbean.sendLxr':Ext.get('sendbean.sendLxr').dom.value,
			 	'sendbean.senddept':Ext.get('sendbean.senddept').dom.value,
			 	'sendbean.sendSigner':Ext.get('sendbean.sendSigner').dom.value,
			 	'sendbean.sendPages':Ext.get('sendbean.sendPages').dom.value,
			 	'sendbean.sendLxdh':Ext.get('sendbean.sendLxdh').dom.value,
			 	'sendbean.sendTitle':Ext.get('sendbean.sendTitle').dom.value,
			 	'sendbean.sendScope':Ext.get('sendbean.sendScope').dom.value
			 	},
			 	form:'addSend',
			 	success:function(){
			 		Ext.Msg.alert('提示','发送成功');
			 		 window.location.href="/desktop/widgets/gwjh/hassentlist.jsp";
			 	},failure:function(){
			 	   Ext.Msg.alert('提示','发送失败');
			 	}
			 })
		 }
	}
	
	function ReSet(){
			   Ext.getCmp('addform').form.reset();
	}
	
	function winClose(){
		window.close();
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
</HTML>