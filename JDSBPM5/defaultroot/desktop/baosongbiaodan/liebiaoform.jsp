<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<script>var context='<%=path%>';</script>


		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>


		<script type="text/javascript" src="<%=path%>js/JDS.js"></script>


		<script type="text/javascript">

		Ext.onReady(function(){
			var uuid = '<ww:property value="uuid"/>'
			var win = new Ext.form.FormPanel(
			{
				id :'positionWin',
				width :1000,
				renderTo: 'grid',
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				tbar:[
		          {
			          text:"保 存",
			          tooltip:"保存当前信息到草稿列表",
			          handler:function(){
			                 window.frames("biaodan").window.getFormQueryString("form1");
			          },
			          iconCls: 'saveButton'
		         },
		         {
		            text: '发 送',
				    tooltip: '发送当前文件',
			        handler: function(){
			            window.frames("biaodan").document.getElementById("sendorsave").value="send";
			            window.frames("biaodan").window.getFormQueryString("form1");
				        
			       },
					scope: this,
					iconCls: 'send'
		         },
		         {
		            text: '重新填写',
				    tooltip: '重新填写表单',
			        handler: function(){
				        window.frames("biaodan").location.href="/desktop/baosongbiaodan/xxkbsbd.jsp";
			       },
					scope: this,
					iconCls: 'refAction'
		         }
		        ],
				items :[{
				collapsible: true,
		        checkboxToggle:false,
		        collapsed: true,
		        xtype:'fieldset',
		        autoHeight:true,
		        collapsed:false,
		        width:982,
		        height:670,
		        checkboxName:'jiben',
		        layout:'column',
		        border:true,
		        labelSeparator:'：',
		        fileUpload:true,
		        html:'<iframe id="biaodan" name="biaodan" frameborder="0" src="getbsbdAction.do?uuid='+uuid+'" width="754" height="630"></iframe>'
				}]

				

			});

				
		});
		
	</script>
	
	
	</head>
	<body style="width: 1000">

	
		<div id="form"></div>
		<div id="grid"></div>
	</body>
</html>