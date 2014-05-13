<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath = request.getContextPath() + "/";
String sendid = request.getParameter("uuid");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
   
    <title>公文交换-已收公文单位列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <script text="text/javascript">
    var sendid='<%=sendid%>';
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
	var cm = new Ext.grid.ColumnModel([sm,
	        {
	
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				hidden:true
			}, {
				header : '接收人',
				dataIndex : 'Inceptor',
				width : 250
				
			}, {
				header : '接收单位',
				dataIndex : 'Inceptdept',
				width : 150
				
			}, {
				header : '接收时间',
				dataIndex : 'Incepttime',
				width : 150,
				sortable : true
			},{
			    header : '反馈信息',
				dataIndex : 'reply',
				width : 100
			
			
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'gwjhAction_findsendreceive.action?uuid='+sendid
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [   {
									name : 'uuid'
								},  {
									name : 'Inceptor'
								}, {
									name:'Inceptdept'
								}, {
									name : 'Incepttime'
								}, {
									name : 'reply'
								}, {
									name : 'back'
								}])
			});
			
   var pgsize = 15;
   	store.load({
				params : {
					start : 0,
					limit : pgsize
				
				}
			});

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '已收公文',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		autoScroll: true,
		renderTo : 'grid',
		store : store,
		cm : cm,
		sm : sm,
		tbar : new Ext.Toolbar([
				{
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '返回',
					cls : "x-btn-text-icon",
					handler :function(){
					 window.location.href="/desktop/widgets/gwjh/hassentlist.jsp";
					}
				}, {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
				} ,{
					id : 'djsave',
					icon : '/usm/img/depart.gif',
					text : '查看报名',
					cls : "x-btn-text-icon",
					handler : function(){
							 		var _width = 900;
									var _height = Ext.getBody().getHeight()-40;
									var addwin = new Ext.Window({
									title : '报名人员',
									layout : 'fit',
									width : _width,
									minWidth : 350,
									height : _height,
									minHeight : 200,
									y:0,
									html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='gwjhAction_toBM.action?uuid="+sendid+"'></iframe>"
								
										});
										addwin.show();
							 
									
							}
				},{
					id : 'notbm',
					icon : '/usm/img/depart.gif',
					text : '查看未报名',
					cls : "x-btn-text-icon",
					handler : function(){
							 		var _width = 900;
									var _height = Ext.getBody().getHeight()-40;
									var addwin = new Ext.Window({
									title : '未报名人员',
									layout : 'fit',
									width : _width,
									minWidth : 350,
									height : _height,
									minHeight : 200,
									y:0,
									html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='gwjhAction_toNotBM.action?uuid="+sendid+"'></iframe>"
								
									});
									addwin.show();
							 
									
							}
				}
				
				]),
		     bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				})
				
				
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
	

});
  function showbackcontent(uuid){

        var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '拒签信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='gwjhAction_findsendbackContent.action?uuid="+uuid+"'></iframe>"
				
			});
			addwin.show();
  
  }
	
</script>
</html>
