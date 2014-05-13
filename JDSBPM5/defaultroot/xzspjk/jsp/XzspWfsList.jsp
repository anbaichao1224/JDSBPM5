<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

  <head>
    
    <title>未发列表</title>
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
					header : '业务流水号',
					dataIndex : 'bsnum',
					width : 200,
					sortable : true
				}]);
			
			var proxy = new Ext.data.HttpProxy({
				url : 'XZSPAction_BsnumFindAllAction.action'
			});

			// 链接
			var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
									name : 'bsnum'
								}])
			});
					
			//面板
			var grid = new Ext.grid.GridPanel({
				title : '未发列表',
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
					text : '删除',
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
							if (selections[i].get('bsnum').length > 0) {
								delAllList[delAllList.length] = selections[i]
										.get('bsnum');
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
										url : 'XZSPAction_delBsnum.action',
										params : {
											deluuid : deluuid
										},
										success : function(resp, opts) {
											 Ext.Msg.alert('信息', '删除成功!');
											
											store.load({
														params : {
															start : 0,
															limit : 15
														}
													});
										},
										failure : function(resp, opts) {
											Ext.Msg.alert('信息', '删除失败!');
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
