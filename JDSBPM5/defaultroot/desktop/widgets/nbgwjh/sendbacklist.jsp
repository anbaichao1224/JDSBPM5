<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath = request.getContextPath() + "/";
String sendid = request.getParameter("uuid");
String status = request.getParameter("status");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'wynotice.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <!-- <script src="/desktop/widgets/nbgwjh/js/sendreplylist.js"></script> -->
    <script text="text/javascript">
        var sendid='<%=sendid%>';
        var status='<%=status%>';
    </script>
  </head>
  
  <body>
      <div id="grid"> 
       </div>
  </body>
  <script text="text/javascript">
      
Ext.onReady(function() {

if(status==1){

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
			},{
				header : '拒签人',
				dataIndex : 'backPerson',
				width : 100,
				sortable : true
			}, {
				header : '拒签人单位',
				dataIndex : 'backdept',
				width : 100,
				sortable : true
			}, {
				header : '拒签时间',
				dataIndex : 'backtime',
				width : 100,
				sortable : true
			}, {
				header : '查看拒签内容',
				dataIndex : 'backReason',
				width : 300
				/*renderer:function(value){
				 return String.format("<input type=\"button\" value=\"查看\" onclick=\"showbackcontent('{0}')\" />",value);
				}*/
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'nbgwjhAction_findsendBacklist.action?uuid='+sendid
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [    {
									name : 'uuid'
								},{
									name : 'backPerson'
								}, {
									name:'backdept'
								}, {
									name:'backtime'
								}, {
									name:'backReason'
								}])
			});


	
	var pgsize = 10;
	store.load({
				params : {
					start : 0,
					limit : pgsize
					}
			});

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '拒签列表',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
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
					handler : function(){
					 window.location.href="/desktop/widgets/nbgwjh/hassentlist.jsp";
					}
				}, {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
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


}else{

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
			},{
				header : '拒签人',
				dataIndex : 'backPerson',
				width : 100,
				sortable : true
			}, {
				header : '拒签单位',
				dataIndex : 'backdept',
				width : 100,
				sortable : true
			}, {
				header : '拒签时间',
				dataIndex : 'backtime',
				width : 100,
				sortable : true
			}, {
				header : '查看拒签内容',
				dataIndex : 'backReason',
				width : 100,
				renderer:function(value){
				 return String.format("<input type=\"button\" value=\"查看\" onclick=\"showbackcontent('{0}')\" />",value);
				}
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'nbgwjhAction_findyssendBacklist.action?uuid='+sendid
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [    {
									name : 'uuid'
								},{
									name : 'backPerson'
								}, {
									name:'backdept'
								}, {
									name:'backtime'
								}, {
									name:'backReason'
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
		title : '拒签列表',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
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
					handler : function(){
					 window.location.href="/desktop/widgets/nbgwjh/hassentlist.jsp";
					}
				}, {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
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
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='nbgwjhAction_findsendbackContent.action?uuid="+uuid+"'></iframe>"
				
			});
			addwin.show();
  
  }

	
    </script>
</html>
