<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath = request.getContextPath() + "/";
String activityInstId = request.getParameter("activityInstId");

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
    <!-- <script src="/desktop/widgets/gwjh/js/backlist.js"></script> -->
    <script text="text/javascript">
    </script>
  </head>
  
  <body>
      <div id="grid"> 
       </div>
  </body>
   <script text="text/javascript">
  var store;
		Ext.onReady(function() {
        var activityInstId='<%=activityInstId%>';

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([ {
				header : '部门',
				dataIndex : 'orgname',
				width : 150,
				sortable : true
			}, {
				header : '人员',
				dataIndex : 'personname',
				width : 60,
				sortable : true
			}, {
				header : '修改时间',
				dataIndex : 'createdate',
				width : 160,
				sortable : true
			}, {
				header : '环节',
				dataIndex : 'hj',
				width : 50,
				sortable : true
			}, {
				header : '拟办意见',
				dataIndex : 'nbyj',
				fixed:true,
				width : 400,
				renderer : function(value, metadata, record)
				 {
				  metadata.attr = 'style="white-space:normal;"';
				  return value;
				   } 
				
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'nbyjAction_findnbyj1.action?activityInstId='+activityInstId
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
							
							
						}, [{
									name : 'orgname'
								}, {
									name : 'personname'
								}, {
									name : 'hj'
								}, {
									name : 'createdate'
								}, {
									name : 'nbyj'
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

	

	

	var pgsize = 15;
store.load({
			params : {
				start : 0,
				limit : pgsize
				
			}
		});
	// 面板
	var grid = new Ext.grid.GridPanel({
		//title : '拒签公文列表',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid',
		store : store,
		cm : cm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				})
				
	});

	

			
  function renderUnreceive(value){
      
  
  
  }

	// 查询
	function queryDj() {
		store.load({
					params : {
						start : 0,
						limit : pgsize,
						title:Ext.get('title').dom.value,
                        from:Ext.get('from').dom.value,
                        to:Ext.get('to').dom.value
					}
				});
	}
	
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

	// 登记条件重置
	function requery() {
		Ext.get('title').dom.value = '';
		Ext.get('from').dom.value = '';
		Ext.get('to').dom.value = ''
		
	}
});
	

		
    </script>
</html>
