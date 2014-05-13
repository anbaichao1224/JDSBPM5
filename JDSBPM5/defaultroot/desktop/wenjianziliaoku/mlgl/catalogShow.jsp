<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	request.setCharacterEncoding("UTF-8");
	

%>
<html>
	<head>
		<title>设备列表</title>
		<base href="<%=basePath%>">
		<script>var context='<%=path%>';</script>


		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
	
	</head>
	<body>
		<div id="grid"></div>
		<div id="form"></div>
	</body>
</html>

	<script type="text/javascript">
		
			//链接（）
			var proxy = new Ext.data.HttpProxy({
				url :'wjzlRootCatalogMangerAction_showListRoot.action?isRoot=1'
			});
		
			var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
					totalProperty : 'totalProperty',
					root : 'root'
				}, [{
					name : 'catalogName'
				}, {
					name : 'catalogOrgName'
				}, {
					name : 'createDate'
				}, {
					name : 'catalogOrder'
				}, {
					name : 'uuid'
				}])
			});
		
			Ext.onReady(function() {
			
				// 复选框
				var sm = new Ext.grid.CheckboxSelectionModel({
					dataIndex : 'index'
				});
			
				// 列名称
				var cm = new Ext.grid.ColumnModel([sm,{
					header : 'uuid',
					dataIndex : 'uuid',
					width : 100,
					hidden:true
				}, {
					header : '目录名称',
					dataIndex : 'catalogName',
					width : 180,
					sortable : true
				}, {
					header : '部门名称',
					dataIndex : 'catalogOrgName',
					width : 180,
					sortable : true
				}, {
					header : '创建时间',
					dataIndex : 'createDate',
					width : 180,
					sortable : true
				}, {
					header : '排序',
					dataIndex : 'catalogOrder',
					width : 180,
					sortable : true
				}
				]);
			
				var pgsize = 20;
	
				// 面板
				var grid = new Ext.grid.GridPanel({
					title : '',
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
						pageSize : pgsize,
						store : store,
						displayInfo : true,
						displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
						emptyMsg : "没有记录"
					}),
					tbar : new Ext.Toolbar(
					[{
						text : '添加',
						icon : '/usm/img/add.gif',
						cls : "x-btn-text-icon",
						handler : function(){
							var _width = 500;
							var _height = Ext.getBody().getHeight()-30;
							var addwin = new Ext.Window({
									title : '添加文件资料信息',
									layout : 'fit',
									width : _width,
									minWidth : 130,
									height : _height,
									minHeight : 40,
									y:0,
									html:"<iframe id='addiframe' name='addiframe' width='" + (_width-10)  + "' height='" + (_height - 10) + "' src='wjzlRootCatalogMangerAction_addRoot.action?'></iframe>",
									tbar:[{
											id : 'rjsave',
											icon : "/usm/img/save.gif",
											text : '保存',
											cls : "x-btn-text-icon",
											handler : function(){
												var fn = Ext.get('addiframe').dom.contentWindow.document.getElementById("addRootCatalog");
												//判断排序序号不能为空
											var catalogOrder = Ext.get('addiframe').dom.contentWindow.document.getElementById("catalogOrder");
											if(catalogOrder.value == ""){
											//alert(biaoti.value);
											alert("排序序号不允许为空，请填写排序序号");
											return;
											}
											if(catalogOrder.value==0){
											alert("排序序号不允许为0！");
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
												var fn = Ext.get('addiframe').dom.contentWindow.document.getElementById("addRootCatalog");
												fn.reset();
											}
										}
									]
							});
							
							addwin.show();
									
						}
				
					}, '-', {
					text : '修改',
					icon : '/usm/img/yingyong.gif',
					cls : "x-btn-text-icon",
					handler : updatepage
				}, '-', {
						text : '删除',
						icon : '/usm/img/delete.gif',
						cls : "x-btn-text-icon",
						handler : function() {
						    var st=store;
							var deluuid = new Array();
							var selections = grid.getSelectionModel().getSelections();
							if (selections.length == 0) {
								alert('请选择需要删除的文件');
								return;
							}
	
							var delAllList = new Array();
							for (var i = 0; i < selections.length; i++) {
								if (selections[i].get('uuid').length > 0) {
									delAllList[delAllList.length] = selections[i].get('uuid');
								} else {
									grid.getSelectionModel().deselectRow(selections[i].get('index'));
								}
							}
							
							deluuid = delAllList.join();
							
							Ext.Ajax.request({
								url : 'wjzlRootCatalogMangerAction_deleteRoot.action',
								params : {
									catalogId : deluuid
								},
								success : function(resp, opts) {
									if(resp.responseText=='true'){
										Ext.Msg.alert('信息', '删除成功!');
									}else{
										Ext.Msg.alert('信息', '删除部分失败，因为其有目录或是有文件资料而不允许删除');
									}
	                               
									store.load({
										params : {
											start : 0,
											limit : pgsize
										}
									});
								},
								failure : function(resp, opts) {
									Ext.Msg.alert('信息', '删除失败!');
								}
							});
						}
					}])
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
				
		// 登记条件重置
		function requery() {
			Ext.get('title').dom.value = '';
			Ext.get('from').dom.value = '';
			Ext.get('to').dom.value = ''
			
		}
		
			});
			
			//显示修改数据页面的方法
			function updatepage(){
				var records = Ext.getCmp('grid').getSelectionModel().getSelections();
				if (records.length == 0) {
					alert("请选择需要修改的记录");
					return;
				} else if (records.length > 1) {
					alert("请进行单项选择");
					return;
				}
				var _record = Ext.getCmp('grid').getSelectionModel().getSelected();
				
			    showupdatewin(_record.get("uuid"));
			}
	
			function showupdatewin(uuid){
				var _width = 600;
				var _height = 510;
				
				var updatewin = new Ext.Window({
					title : '修改文件资料信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='updateiframe' name='updateiframe' width='" + (_width-30)  + "' height='" + (_height - 70) + "' src='wjzlRootCatalogMangerAction_updateRoot.action?uuid="+uuid+"'></iframe>",
					tbar:[{
						id : 'rjsave',
						icon : "/usm/img/save.gif",
						text : '修  改',
						cls : "x-btn-text-icon",
						handler : function(){
							var fn = Ext.get('updateiframe').dom.contentWindow.document.getElementById("update");
							var catalogOrder = Ext.get('updateiframe').dom.contentWindow.document.getElementById("catalogOrder");
							if(catalogOrder.value == ""){
							//alert(biaoti.value);
							alert("排序序号不允许为空，请填写排序序号");
							return;
							}
							if(catalogOrder.value==0){
							alert("排序序号不允许为0！");
							return;
							}
							fn.submit();
							store.reload();
							updatewin.close();
						}
					}]
			});
			updatewin.show();
		}
		
	</script>