<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String deptid = request.getParameter("deptid");
	String flag = request.getParameter("flag");
%>
<html>
	<head>

	</head>
	<body>
		<div id="grid111"></div>
		<div id="form"></div>
	</body>
	<base href="<%=basePath%>">
	<script>var context='<%=path%>/';
		var username='<%=username%>';
  		  var password='<%=password%>'; 
   		  var deptid='<%=deptid%>';
   		  var flag='<%=flag%>';
   		  </script>


	<meta http-equiv="pragma" content="no-cache" />
	<meta http-equiv="cache-control" content="no-cache" />

	<script type="text/javascript" src="<%=basePath%>js/extAll.js"></script>
	<style type="text/css">
.x-grid3-row td,.x-grid3-summary-row td {
	line-height: 10px; //
	控制GRID单元格高度 vertical-align: middle; //
	单元格垂直居中 border-right: 1px solid #eceff6 !important; //
	控制表格列线 border-top: 1px solid #eceff6 !important;
	//
	控制表格行线
}
</style>
	<script type="text/javascript">
		var store;
		Ext.onReady(function() {
       
	// 列名称
	var cm = new Ext.grid.ColumnModel([ {
	            
				header : '标题',
				dataIndex : 'BT',
				width : 200,
				sortable : true
			}, {
			    header : '编号',
				dataIndex : 'BH',
				width : 100,
				sortable : true
			}, {
			
				header : '等级',
				dataIndex : 'DJ',
				width : 100,
				sortable : true
			
			},{
				header : '发送单位',
				dataIndex : 'NGDW',
				width : 100,
				sortable : true
			},{
				header : '发送时间',
				dataIndex : 'FSSJ',
				width : 200,
				sortable : true
			}, {
				header : '发送单位数',
				dataIndex : 'FSDWS',
				width : 80,
				sortable : true
			}, {
				header : '未收单位',
				dataIndex : 'WSDW',
				width : 80
				
			}, {
				header : '已收单位',
				dataIndex : 'YSDW',
				width : 80
			}, {
				header : '办结单位',
				dataIndex : 'YBJ',
				width : 80
				
			}, {
				header : '退回单位',
				dataIndex : 'TH',
				width : 80
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : '/Chaxun_HqFwYf.action?username='+username+'&password='+password+'&deptid='+deptid+'&flag='+flag
	});

	// 链接
	 store = new Ext.data.Store({
				// proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
						        name : 'BT'
								}, {
									name : 'BH'
								}, {
								    name : 'DJ'
								}, {
									name : 'NGDW'
								}, {
								    name : 'FSSJ'
								}, {
									name : 'FSDWS'
								}, {
									name : 'WSDW'
								}, {
									name : 'YSDW'
								}, {
									name : 'YBJ'
								}, {
									name:'TH'
								}])
			});

   
	var pgsize = 10;

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '已发会签列表',
		id : 'grid111',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid111',
		store : store,
	    cm : cm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				}),
		tbar : new Ext.Toolbar([
		    {
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '返回',
					cls : "x-btn-text-icon",
					handler : function(){
					 window.location.href="/admin/hq.jsp?username="+username+"&password="+password+"&deptid="+deptid+'&flag='+flag;
					}
				},{
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '主页',
					cls : "x-btn-text-icon",
					handler : function(){
					 window.location.href="/admin/index.jsp";
					}
				}, {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
				} 
          ])


	});
	store.load({
				params : {
					start : 0,
					limit : pgsize
					
				}
			});

	
	


	function successload() {
		store.load({
					params : {
						start : 0,
						limit : pgsize
					}
				});
	}
});

	
    // 刷新
	function refresh() {
		store.load();
	}

		</script>
</html>