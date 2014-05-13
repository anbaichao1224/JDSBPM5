<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID" %>
<%
String contextpath = request.getContextPath() + "/";
String uuid = (new UUID()).toString();
 %>
<HTML>
<HEAD>
<TITLE></TITLE>
<style type="text/css">
		<!--
body {font-family: "宋体", "Arial"; font-size: 9pt;background-color:#dfe8f6}
td {font-family: "宋体", "Arial"; font-size: 9pt} 
-->
		</style>
<script type="text/javascript">
	var context="/";
</script>
 
<script type="text/javascript" src="/js/extAll.js" ></script>

<script type="text/javascript" src="/desktop/widgets/gwjh/js/departtree.js" defer="defer"></script>
<script type="text/javascript" src="<%=contextpath %>desktop/js/CreateGrid.js" defer="defer"></script>
<script  src="/js/JDS.js" type="text/javascript" defer="defer"></script>
</HEAD>
<BODY>

<center>
<div id="addform"></div>
<form name="addSend" id="addSend">
<table width="800" height="227" align="center" bgcolor="#dfe8f6" style="border:1px solid #99bbe8;line-height:30px;">
<input type="hidden" name="parentid" id="parentid" value='<%=request.getParameter("parentid") %>'/>
<input type="hidden" name="tid" id="tid" value='<%=uuid %>'/><input type="hidden" name="optionnum" id="optionnum" value='1'/>

<input type="hidden" name="mettinguuid" id="mettinguuid" value='<%=request.getParameter("mettinguuid") %>'/>
<input type="hidden" name="adddirection" id="adddirection" value='2'/>
<input type="hidden" name="liststatus" id="liststatus" value='<%=request.getParameter("liststatus")%>'/>

  <tr>
    <td align="left" width="184">接收单位:</td>&nbsp;&nbsp;&nbsp;&nbsp;
    <td colspan="3"><input size="70" class="required" name="deptnames" type="text" id="deptnames" onkeydown="return false;"  size="50"/>
    <input type="hidden" name="id" id="id"/>
    <input name="choice" size="70" type="button" id="choice" value="选择" onclick="createPersonPositionWindow('5015','')" />
    </tr>
  <tr>
    <td valign="top" align="left">附&nbsp;&nbsp;件:&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td>
     <ww:action name="docexchangefileinclude" executeResult="true"></ww:action>
    </td>
  </tr>
  <tr>
    <td  align="center" colspan="2"><input type="button" name="savematter" onclick="save()" value="发送">&nbsp;&nbsp;
    <input type="button" name="savematter" onclick="ReSet()" value="重置">&nbsp;&nbsp;
    <td/>
  </tr>
</table>
</form>
</center>
</BODY>
 <script type="text/javascript">
var processid = '<ww:property value="mettingbean.processdefid"/>';
var bsta = '<ww:property value="mettingbean.blstatus"/>';
var id = '<ww:property value="mettingbean.tid"/>';  
var uuid = '<%=uuid%>';
Ext.onReady(function(){
    	var mj = [['pt', '普通'], ['jm', '秘密'], ['juem', '机密']];

		var jjcd = [['pt', '普通'], ['jj', '紧急'], ['tj', '特急']];

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
					emptyText : '普通',
					mode : 'local',
					valueField : 'value',
					displayField : 'text',
					triggerAction : 'all'
				});

		var jjcdcombo = new Ext.form.ComboBox({
					store : jjcdstore,
					fieldLabel : '紧急程度',
					name : 'sendbean.sendJjcd',
					emptyText : '普通',
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
			frame : true,
			id:'addform',
			url : '',
			items : [ {
			    layout:'column',
			    items:[{
			       columnWidth:.5,
			       layout:'form',
			       defaultType:'textfield',
			       items:[combo,
			       {fieldLabel:'发文人',name:'sendbean.sendor',width:180,value:'<ww:property value="$currPerson.name"/>'},
			       {fieldLabel:'文件类型',name:'sendbean.sendType',width:180},
			       {fieldLabel:'签发时间',xtype:'datefield',emptyText:'请选择',
	              	format:'Y-m-d',width:200,name:'sendbean.sendSignTime'},
			       {fieldLabel:'文号',width:180,name:'sendbean.sendWh'},
			       {fieldLabel:'联系人',width:180,name:'sendbean.sendLxr'}
			
			       ]
			    },{
			       columnWidth:.5,
			       layout:'form',
			       defaultType:'textfield',
			       items:[jjcdcombo,
			         {fieldLabel:'发文单位',width:180,name:'sendbean.senddept',value:'<ww:property value="$currPerson.orgList[0].name"/>'},
			         {fieldLabel:'签发人',width:180,name:'sendbean.sendSigner'},
			         {fieldLabel:'页数',width:180,name:'sendbean.sendPages',blankText: '只能输入数字',
			             regex:/^[0-9]+$/,regexText: '只能输入数字' },
			         {fieldLabel:'总份数',width:180,name:'sendbean.sendCopy' ,blankText: '只能输入数字',
			             regex:/^[0-9]+$/, 
          			     regexText: '只能输入数字'},
			         {fieldLabel:'联系电话',width:180,name:'sendbean.sendLxdh'}
			       
			      ]}]
			},{
			  width:570,
			  height:40,
			  xtype:'textarea',
			  fieldLabel:'标题',
			  allowBlank: false,
			  name:'sendbean.sendTitle'
			},{
			  width:570,
			  height:50,
			  xtype:'textarea',
			  fieldLabel:'副标题',
			 name:'sendbean.sendTitle1'
			},{
			  width:570,
			  height:40,
			  xtype:'textarea',
			  fieldLabel:'主题词',
			  name:'sendbean.sendKeyWord'
			
			},{
			  width:570,
			  height:40,
			  xtype:'textarea',
			  fieldLabel:'发送范围',
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
           if(obj.getStore().getCount()==0){
           		alert("附件不能为空");
              	return false;
           }
			 Ext.Ajax.request({
			 	url:'gwjhAction_save.action',
			 	method:'post',
			 	params:{
			 	'sendbean.sendMj':Ext.get('sendbean.sendMj').dom.value,
			 	'sendbean.sendJjcd':Ext.get('sendbean.sendJjcd').dom.value,
			 	'sendbean.sendor':Ext.get('sendbean.sendor').dom.value,
			 	'sendbean.sendType':Ext.get('sendbean.sendType').dom.value,
			 	'sendbean.sendSignTime':Ext.get('sendbean.sendSignTime').dom.value,
			 	'sendbean.sendWh':Ext.get('sendbean.sendWh').dom.value,
			 	'sendbean.sendLxr':Ext.get('sendbean.sendLxr').dom.value,
			 	'sendbean.senddept':Ext.get('sendbean.senddept').dom.value,
			 	'sendbean.sendSigner':Ext.get('sendbean.sendSigner').dom.value,
			 	'sendbean.sendPages':Ext.get('sendbean.sendPages').dom.value,
			 	'sendbean.sendCopy':Ext.get('sendbean.sendCopy').dom.value,
			 	'sendbean.sendLxdh':Ext.get('sendbean.sendLxdh').dom.value,
			 	'sendbean.sendTitle':Ext.get('sendbean.sendTitle').dom.value,
			 	'sendbean.sendTitle1':Ext.get('sendbean.sendTitle1').dom.value,
			 	'sendbean.sendKeyWord':Ext.get('sendbean.sendKeyWord').dom.value,
			 	'sendbean.sendScope':Ext.get('sendbean.sendScope').dom.value
			 	},
			 	form:'addSend',
			 	success:function(){
			 		alert("发送成功");
			 		 window.location.href="/desktop/widgets/gwjh/hassentlist.jsp";
			 	},failure:function(){
			 	   alert("发送失败");
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
</script>
</HTML>