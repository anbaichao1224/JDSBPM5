<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

  <head>
    
    <title>各窗口事项统计列表</title>
    <script type="text/javascript">var context='/';</script>
	<script type="text/javascript" src="/js/extAll.js"></script>
	<script type="text/javascript">
		Ext.onReady(function(){
			var newFormWin;
			// 复选框
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

			// 列名称
			var cm = new Ext.grid.ColumnModel([sm, {
					header : '窗口事项',
					dataIndex : 'cksx',
					width : 500,
					sortable : true
				}, {
					header : 'UUID',
					dataIndex : 'uuid',
					width : 100,
					sortable : true,
					hidden : true
				}, {
					header : '是否年审',
					dataIndex : 'sfns',
					width : 100,
					sortable : true
				}]);
			
			var proxy = new Ext.data.HttpProxy({
				url : 'shiXiangFindByBuMenAction.action'
			});

			// 链接
			var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
									name : 'cksx'
								}, {
									name:'uuid'
								}, {
									name:'sfns'
								}])
			});
					
			//面板
			var grid = new Ext.grid.GridPanel({
				title : '事项列表',
				id : 'grid',
				layout : 'fit',
				bodyStyle : 'width:100%',
				autoHeight : true,
				loadMask : true,
				renderTo : 'grid',
				store : store,
				cm : cm,
				sm : sm,
				bbar : new Ext.PagingToolbar({
							pageSize : 15,
							store : store,
							displayInfo : true,
							displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
							emptyMsg : "没有记录"
				}),
				tbar : new Ext.Toolbar([
					{
						text : '添加事项',
						icon : '/usm/img/add.gif',
						cls : "x-btn-text-icon",
						handler : function(){
							var _width = 800;
							var _height = Ext.getBody().getHeight()-200;
							newFormWin = new Ext.Window({
								title : '添加事项',
								layout : 'fit',
								width : _width,
								minWidth : 350,
								height : _height,
								minHeight : 200,
								html:"<iframe id='tjframe' name='tjframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='shiXiangAdd.jsp"+"'></iframe>",
								tbar:[
									{
										id : 'djsave',
										icon : "/usm/img/save.gif",
										text : '添  加',
										cls : "x-btn-text-icon",
										handler : function(){
											var fn = Ext.get('tjframe').dom.contentWindow.document.getElementById("my_form");
									
											fn.submit();
											alert('添加成功');
											store.reload();
											newFormWin.close();
										}
									}
								]
							});
							newFormWin.show();
						}
					}, '-', {
					text : '删除事项',
					icon : '/usm/img/delete.gif',
					cls : "x-btn-text-icon",
					handler : function() {
						var deluuid = new Array();
						var selections = grid.getSelectionModel()
								.getSelections();
						if (selections.length == 0) {
							alert('请选择需要删除的文件');
							return;
						}

						var delAllList = new Array();
						for (var i = 0; i < selections.length; i++) {
							if (selections[i].get('uuid').length > 0) {
								delAllList[delAllList.length] = selections[i]
										.get('uuid');
							} else {
								grid
										.getSelectionModel()
										.deselectRow(selections[i].get('index'));
							}
						}
						deluuid = delAllList.join();
						Ext.Msg.confirm(
							"确认消息框","是否进行删除",function(btn){
								if(btn=="yes"){
									Ext.Ajax.request({
										url : 'delCksxml.action',
										params : {
											deluuid : deluuid
										},
										success : function(resp, opts) {
											 Ext.Msg.alert('信息', '登记信息删除成功!');
											
											store.load({
														params : {
															start : 0,
															limit : 15
														}
													});
										},
										failure : function(resp, opts) {
											Ext.Msg.alert('信息', '登记信息删除失败!');
										}
									});
								}
							}
						);
					}
					}
				])
			});		
						
			store.load({
				params : {
					start : 0,
					limit : 15
				}
			});
		
		})
	</script>
  </head>
  
  <body>
		<div id="grid"></div>
		<div id="topic-win"></div>
  </body>
</html>
