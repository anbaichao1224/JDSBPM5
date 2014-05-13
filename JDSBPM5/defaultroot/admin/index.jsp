<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextpath = request.getContextPath() + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>共性办公应用系统监控</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
		<script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
		<script src='<%=contextpath%>js/ext/ext-all.js'></script>
		<script text="text/javascript">
    </script>
		<script language="JavaScript"> 

</script>
	</head>

	<body>
		<div id="grid">
		</div>
	</body>
	<script text="text/javascript">
   Ext.onReady(function() {

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([ 
			 {
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				hidden:true
				
			}, {
				header : 'flag',
				dataIndex : 'flag',
				width : 100,
				hidden:true
				
			},{
				header : '序号',
				dataIndex : 'dworder',
				width : 50,
				sortable : true
			},{
				header : '单位',
				dataIndex : 'dw',
				width : 150,
				sortable : true
			},{
				header : '单位标识',
				dataIndex : 'deptid',
				width : 70,
				hidden:true
			},{
				header : '库名称',
				dataIndex : 'username',
				width : 60,
				hidden:true
			}, {
				header : '库密码',
				dataIndex : 'password',
				width : 60,
				hidden:true
			},{
				header : '查看人员',
				dataIndex : 'ckry',
				width : 70,
				sortable : true
			},{
				header : '公文交换',
				dataIndex : 'ckgwjh',
				width : 80,
				sortable : true
			},{
				header : '调度表查询',
				dataIndex : 'gwjhamqp',
				width : 100,
				sortable : true
			},{
				header : '会签',
				dataIndex : 'hq',
				width : 45,
				sortable : true
			},{
				header : '调度表查询',
				dataIndex : 'hqamqp',
				width : 100,
				sortable : true
			},{
				header : '短信记录',
				dataIndex : 'msm',
				width : 70,
				sortable : true
			},{
				header : '公文统计',
				dataIndex : 'GwTj',
				width : 70,
				sortable : true
			},{
				header : '公文交换已发统计',
				dataIndex : 'GwjhYf',
				width : 140,
				sortable : true
			},{
				header : '公文交换已收统计',
				dataIndex : 'GwYS',
				width : 140,
				sortable : true
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'Chaxun_index.action'
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [    
						        {
									name : 'uuid'
								},{
									name : 'dw'
								},{
									name : 'deptid'
								},{
									name : 'username'
									
								}, {
									name:'password'
								}, {
									name:'ckry'
								}, {
									name:'dworder'
								}, {
									name:'ckgwjh'
								}, {
									name:'hq'
								}, {
									name:'gwjhamqp'
								}, {
									name:'hqamqp'
								}, {
									name:'msm'
								}, {
									name:'GwTj'
								}, {
									name:'GwjhYf'
								}, {
									name:'GwYS'
								}, {
									name:'flag'
								}])
			});

	var docname = new Ext.form.TextField({
				width : 100,
				name : 'title',
				allowBlank : true
			});

	var rdata = new Ext.form.DateField({
				width : 100,
				name : 'from',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
			});
			
	var tdata = new Ext.form.DateField({
				width : 100,
				name:'to',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
	});
	var pgsize = 1;
	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '已发公文列表',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid',
		store : store,
		cm : cm,
		sm:sm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				}),
		tbar : new Ext.Toolbar([
				 {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
				},'-', {
						text : '添加',
						icon : '/usm/img/add.gif',
						cls : "x-btn-text-icon",
						handler : function(){
							var _width = 500;
							var _height = Ext.getBody().getHeight()-320;
							var addwin = new Ext.Window({
									title : '添加监控单位信息',
									layout : 'fit',
									width : _width,
									minWidth : 130,
									height : _height,
									minHeight : 40,
									y:0,
									html:"<iframe id='addiframe' name='addiframe' width='" + (_width-10)  + "' height='" + (_height - 10) + "' src='Chaxun_AddDw.action?'></iframe>",
									tbar:[{
											id : 'rjsave',
											icon : "/usm/img/save.gif",
											text : '保存',
											cls : "x-btn-text-icon",
											handler : function(){
												var fn = Ext.get('addiframe').dom.contentWindow.document.getElementById("addDw");
												//判断排序序号不能为空
											var dw = Ext.get('addiframe').dom.contentWindow.document.getElementById("dw");
											if(dw.value == ""){
											//alert(biaoti.value);
											alert("单位名称不允许为空，请填写单位名称");
											return;
											}
											var username = Ext.get('addiframe').dom.contentWindow.document.getElementById("username");
											if(username.value == ""){
											//alert(biaoti.value);
											alert("库名称不允许为空，请填写库名称");
											return;
											}
											var password = Ext.get('addiframe').dom.contentWindow.document.getElementById("password");
											if(password.value == ""){
											alert("库密码不允许为空，请填写库密码");
											return;
											}
											var deptid = Ext.get('addiframe').dom.contentWindow.document.getElementById("deptid");
											if(deptid.value == ""){
											alert("公文交换ID不允许为空，请填写公文交换ID");
											return;
											}
											var dworder = Ext.get('addiframe').dom.contentWindow.document.getElementById("dworder");
											if(dworder.value == ""){
											alert("排序序号不允许为空，请填写排序序号");
											return;
											}
												fn.submit();
												alert('保存成功');
												store.reload();
												addwin.close();
											}
										},{
											id : 'rjreset',
											icon : "/usm/img/depart.gif",
											text : '重  置',
											cls : "x-btn-text-icon",
											handler : function(){
												var fn = Ext.get('addiframe').dom.contentWindow.document.getElementById("addDw");
												fn.reset();
											}
										}
									]
							});
							
							addwin.show();
									
						}
				
					},'-', {
						text : '系统提醒',
						icon : '/usm/img/add.gif',
						cls : "x-btn-text-icon",
						handler : function(){
						var selections=Ext.getCmp('grid').getSelections();
							var delAllList=new Array();
	 					 for(var i=0;i<selections.length;i++){
	 
	 						  if (selections[i].get('uuid').length>0 ){  
	   
	   		  		  delAllList[delAllList.length]= selections[i].get('uuid');  
	     
	 						  }
					  };
	  
						   var fileList=delAllList.join();
						
							var _width = 500;
							var _height = Ext.getBody().getHeight()-320;
							var addwin = new Ext.Window({
									title : '系统提醒',
									layout : 'fit',
									width : _width,
									minWidth : 130,
									height : _height,
									minHeight : 40,
									y:0,
									html:"<iframe id='addiframe' name='addiframe' width='" + (_width-10)  + "' height='" + (_height - 10) + "' src='Chaxun_Gxts.action'></iframe>",
									tbar:[{
											id : 'rjsave',
											icon : "/usm/img/save.gif",
											text : '发送',
											cls : "x-btn-text-icon",
											handler : function(){
												var fn = Ext.get('addiframe').dom.contentWindow.document.getElementById("gxts");
												//判断排序序号不能为空
											var msm = Ext.get('addiframe').dom.contentWindow.document.getElementById("msm");
											if(msm.value == ""){
											alert("内容不允许为空，请填写内容");
											return;
											}
												var deptnames = Ext.get('addiframe').dom.contentWindow.document.getElementById("deptnames");
											if(deptnames.value == ""){
											alert("单位不允许为空，请选择单位");
											return;
											}
												fn.submit();
												alert('保存成功');
												store.reload();
												addwin.close();
											}
										}
									]
							});
							
							addwin.show();
									
						}
				
					},'-', {
						text : '查看管理员',
						icon : '/usm/img/add.gif',
						cls : "x-btn-text-icon",
						handler : function(){
							var _width = 800;
							var _height = Ext.getBody().getHeight()-320;
							var addwin = new Ext.Window({
									title : '查看管理员',
									layout : 'fit',
									width : _width,
									minWidth : 400,
									height : _height,
									minHeight : 60,
									y:0,
									html:"<iframe id='addiframe1' name='addiframe1' width='" + (_width-10)  + "' height='" + (_height - 10) + "' src='Chaxun_Ckgly.action?'></iframe>"
									
							});
							
							addwin.show();
									
						}
				
					}
				
				])
				
	});

	

	store.load({
				params : {
					start : 0,
					limit : pgsize
				}
			});
	
	
	// 刷新
	function refresh() {
		store.load({
					params : {
						start : 0,
						limit : pgsize
			
					}
				});
	}
	

	function successload() {
		store.load({
					params : {
						start : 0,
						limit : pgsize
					}
				});
	}

	
});
	
	   function sendFile(grid){
  
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请选择需要删除的单位');
	   return;
	   }
 
	var delAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	 
	   if (selections[i].get('uuid').length>0 ){  
	   
	     		  delAllList[delAllList.length]= selections[i].get('uuid');  
	     
	   }else{
		   alert("error");
	   }
	  };
	  
	   var fileList=delAllList.join();
	    Ext.Ajax.request({
	    
			  url:'gwjhAction_delete.action',
			  params:{
			  'uuids':fileList
			  },
			  method:'post',
			  success:function(){
			   alert("删除成功");
			    grid.getStore().reload();
				
			
			  },failure:function(){
	             alert("删除失败");
	           }
		  });
		  
}
		
    </script>
</html>
