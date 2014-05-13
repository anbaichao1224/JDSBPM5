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


		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript">
			Ext.onReady(function() {
				/**
				*form中加入隐藏字段uuid用来超找需要注入的数据
				*/
				var uuidHidden = new Ext.form.Hidden({
					name:'uuid'
				});
				
				uuidHidden.setValue("<ww:property value="uuid"></ww:property>");
				
				var startForm = new Ext.form.FormPanel({
					id:'startform',
					name:'startform',
			        labelAlign: 'right',
			        labelWidth: 50,
			        buttonAlign: 'center',
			        frame:true,
			        height:140,
			        bodyStyle : 'width:100%',
			        url:'gotoP.action',
					items:[uuidHidden,{
						    xtype: 'fieldset',
				            autoHeight:true,
				            defaultType: 'radio',
				            hideLabels: true,
				            items:[
								<ww:property value="jsonradio"></ww:property>				            	
				            ]						
					}]
				});
				startForm.render("startP");
				
			});		
			
			function startPsubmit(){
				var pid = Ext.getCmp('startform').getForm().findField('process').getGroupValue();
				var uuid = Ext.get('uuid').dom.value;
				alert(1);
				window.top.newDjProcess(pid,uuid);
				//Ext.getCmp('startform').getForm().submit();
			}	
		</script>
	</head>
	<body>
		<div id="startP"></div>
	</body>
</html>