<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();
    String wenzhongid = request.getParameter("wenzhongId");
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
	
	</head>
	<body>
		<div id="grid11"></div>
		<div id="form"></div>
	</body>
		<base href="<%=basePath%>">
		<script>var context='<%=path%>/';</script>


		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
       
		<script type="text/javascript" src="<%=basePath%>js/extAll.js"></script>
		<script type="text/javascript">
		var store;
		Ext.onReady(function() {
        var wenzhongid='<%=wenzhongid%>';
	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([sm, {
	            header:'<div style="text-align:center">标题</div>',
				dataIndex : 'bt',
				width : 150,
				sortable : true
			}, {
				header : '文种',
				dataIndex : 'wzname',
				width : 100,
				align : 'center',
				sortable : true
			}, {
			
				header : '年号',
				dataIndex : 'nh',
				width : 100,
				align : 'center',
				sortable : true
			}, {
				header : ' 编号',
				dataIndex : 'bh',
				width : 100,
				align : 'center',
				sortable : true
			},{
				header : ' 时间',
				dataIndex : 'sj',
				width : 150,
				align : 'center',
				sortable : true
			},{
				header : '是否已作废',
				dataIndex : 'zf',
				width : 100,
				align : 'center',
				sortable : true
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : "listbhAction.action?wenzhongId="+'<%=wenzhongid%>'
	});

	// 链接
	 store = new Ext.data.Store({
				// proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
						            name : 'uuid'
								}, {
								     name : 'bt'
								}, {
									name : 'wzname'
								}, {
								    name : 'nh'
								}, {
									name : 'bh'
								}, {
								    name : 'sj'
								}, {
								    name : 'zf'
								
								
								}])
			});


	var pgsize = 10;

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '详细列表',
		id : 'grid11',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid11',
		store : store,
		cm : cm,
		sm : sm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				}),
		tbar : new Ext.Toolbar([  
			{
					text : '作废',
					icon : '/usm/img/yingyong.gif',
					cls : "x-btn-text-icon",
						handler : function() {
						var deluuid = new Array();
						var selections = grid.getSelectionModel()
								.getSelections();
						if (selections.length == 0) {
							alert('请选择需要作废的文件');
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
						Ext.Ajax.request({
							url : '/updateflagAction.action',
							params : {
								uuid : deluuid
							},
							success : function(resp, opts) {
								 Ext.Msg.alert('信息', '编号信息作废成功!');
								
								store.load({
											params : {
												start : 0,
												limit : pgsize
											}
										});
							},
							failure : function(resp, opts) {
								Ext.Msg.alert('信息', '编号信息作废失败!');
							}
						});
					}	
				}, '-', {
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
						Ext.Ajax.request({
							url : '/delAction.action',
							params : {
								uuid : deluuid
							},
							success : function(resp, opts) {
								 Ext.Msg.alert('信息', '编号信息删除成功!');
								
								store.load({
											params : {
												start : 0,
												limit : pgsize
											}
										});
							},
							failure : function(resp, opts) {
								Ext.Msg.alert('信息', '编号信息删除失败!');
							}
						});
					}
				}])
	});

function zf(uuid){

      // var _record = Ext.getCmp('sc').getSelectionModel().getSelected();
      	Ext.Ajax.request({
			url : '/updateflagAction.action',
			params : {
				uuid : uuid
			},
			success : function(resp, opts) {
				Ext.Msg.alert('作废成功!');
				store.load({
					params : {
						start : 0,
				    	limit : 15
					}
				});
	                          
			},
			failure : function(resp, opts) {
			alert(resp.responseText);
				Ext.Msg.alert('作废失败!');
			}
		});
	}
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

		</script>
</html>