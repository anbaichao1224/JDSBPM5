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
//			var zt = '<ww:property value="bdBean.zt"/>';
			var uuid = '<ww:property value="uuid"/>'
//			alert(uuid);
//			<a href='getbsbdAction.do?uuid=<ww:property value="uuid"/>&zt=<ww:property value="zt"/>
//		    var iframeurl;
//		    if(zt == 1){
////		    	iframeurl = "/desktop/baosongbiaodan/xxkbsbdhis.jsp"
//		    }else{
//		    	iframeurl = "/desktop/baosongbiaodan/xxkbsbd.jsp"
//		    }
			var win = new Ext.form.FormPanel(
			{
				id :'positionWin',
//				title :'报送信息',
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
			                alert("go 保存");
			                var str="";
							window.frames("biaodan").document.forms("form1").submit();
			          },
			          iconCls: 'saveButton'
		         }
		        ],
				items :[{
				collapsible: true,
		        checkboxToggle:false,
		        collapsed: true,
		        xtype:'fieldset',
//		        title:'表单信息',
		        autoHeight:true,
		        collapsed:false,
		        width:982,
		        height:670,
		        checkboxName:'jiben',
		        layout:'column',
		        border:true,
		        labelSeparator:'：',
		        fileUpload:true,
		        html:'<iframe id="biaodan" name="biaodan" frameborder="0" src="getbsbhAction.do?uuid='+uuid+'" width="982" height="630"></iframe>'
				}]

				

			});

//	win.show();
				
		});
		
	</script>
	</head>
	<body>
		<div id="form"></div>
		<div id="grid"></div>

	</body>
</html>