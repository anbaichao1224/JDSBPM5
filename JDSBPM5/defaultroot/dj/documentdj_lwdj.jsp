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

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([sm, {
				header : '发文标题',
				dataIndex : 'DOCBT',
				width : 100,
				sortable : true
			}, {
				header : '发文日期',
				dataIndex : 'RDATE',
				width : 100,
				sortable : true
			}, {
				header : '单位',
				dataIndex : 'DEPARTMENT',
				width : 100,
				sortable : true
			}, {
				header : '密级',
				dataIndex : 'CLASSIFICATION',
				width : 100,
				sortable : true
			}, {
				header : '紧急程度',
				dataIndex : 'EMERGENCY',
				width : 100,
				sortable : true
			}, {
				header : '编号',
				dataIndex : 'SN',
				width : 100,
				sortable : true
			}, {
				header : '流程模块',
				dataIndex : 'MODELTYPE',
				width : 100,
				sortable : true
			}/*, {
				header : '操作',
				dataIndex : 'OPTION',
				width : 100
			}*/]);

	var proxy = new Ext.data.HttpProxy({
		url : '/LwdjAction_findAllByModel.action?xmlmodel=<ww:property value="xmlmodel"></ww:property>'
	});

	// 链接
	var store = new Ext.data.Store({
				// proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalCount',
							root : 'root'
						}, [{
									name : 'DOCBT'
								}, {
									name:'RDATE'
								}, {
									name : 'DEPARTMENT'
								}, {
									name : 'CLASSIFICATION'
								}, {
									name : 'EMERGENCY'
								}, {
									name : 'SN'
								}, {
									name : 'MODELTYPE'
								}, {
									name : 'UUID'
								}, {
									name : 'OPTION'
								}])
			});

	var docname = new Ext.form.TextField({
				width : 100,
				name : 'docname',
				allowBlank : true
			});

	var rdata = new Ext.form.DateField({
				width : 100,
				name : 'rdata',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
			});
			
	var tdata = new Ext.form.DateField({
				width : 100,
				name:'tdata',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
	});

	var department = new Ext.form.TextField({
				width : 100,
				name : 'department',
				allowBlank : true
			});

	// 启动流程
	startlc = function() {
		var _width = 240;
		var _height = 160;
		var xmlmodel = '<ww:property value="xmlmodel"></ww:property>';
		var _record = Ext.getCmp('grid').getSelectionModel().getSelected();
		var uuid = _record.get('UUID');
		var startwin = new Ext.Window({
					title : '选择启动流程窗口',
					id:'startwin',
					//layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					html:"<iframe id='startpiframe' name='startpiframe' width='" + (_width + 2)  + "' height='" + (_height + 100) + "' src='/startP.action?xmlmodel=" + xmlmodel + "&uuid=" + uuid + "'></iframe>",
					buttons:[{
						text:"启动",
						handler:function(){
						 	var iframe = document.frames['startpiframe'];
						 	iframe.startPsubmit();
						 	startwin.close();
						}
					}]
			});
		startwin.show();
	}

	var pgsize = 10;

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '发文登记',
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
		tbar : new Ext.Toolbar(['来文标题：', docname, '来文单位：', department, '开始日期：',
				rdata, '结束时间:', tdata,{// 查询按钮
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '查  询',
					cls : "x-btn-text-icon",
					handler : queryDj
				}, {
					icon : '/usm/img/depart.gif',
					text : '重  置',
					cls : "x-btn-text-icon",
					handler : requery
				}, {
					xtype : "tbfill"
				}, {
					text : '登记',
					icon : '/usm/img/add.gif',
					cls : "x-btn-text-icon",
					handler : function(){
						window.top.showaddwin('<ww:property value="xmlmodel"></ww:property>');
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
						var deluuid = new Array();
						var selections = grid.getSelectionModel()
								.getSelections();
						if (selections.length == 0) {
							alert('请选择需要删除的文件');
							return;
						}

						var delAllList = new Array();
						for (var i = 0; i < selections.length; i++) {
							if (selections[i].get('UUID').length > 0) {
								delAllList[delAllList.length] = selections[i]
										.get('UUID');
							} else {
								grid
										.getSelectionModel()
										.deselectRow(selections[i].get('index'));
							}
						}
						deluuid = delAllList.join();
						Ext.Ajax.request({
							url : '/LwdjAction_delDj.action?xmlmodel=<ww:property value="xmlmodel"></ww:property>',
							params : {
								deluuid : deluuid
							},
							success : function(resp, opts) {
								Ext.Msg.alert('信息', '登记信息删除成功!');
								store.load({
											params : {
												start : 0,
												limit : pgsize
											}
										});
							},
							failure : function(resp, opts) {
								Ext.Msg.alert('信息', '登记信息删除失败!');
							}
						});
					}
				}])
	});

	store.on('beforeload', function() {
				this.baseParams = {
					docname : Ext.get('docname').dom.value,
					department : Ext.get('department').dom.value,
					rdata : Ext.get('rdata').dom.value
				};
			});

	store.load({
				params : {
					start : 0,
					limit : pgsize,
					docname : Ext.get('docname').dom.value,
					department : Ext.get('department').dom.value,
					rdata : Ext.get('rdata').dom.value
				}
			});

	// 登记查询
	function queryDj() {
		store.load({
					params : {
						start : 0,
						limit : pgsize,
						docname : docname,
						department : department,
						rdata : rdata,
						tdata : tdata
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
		Ext.get('docname').dom.value = '';
		Ext.get('department').dom.value = '';
		Ext.get('rdata').dom.value = '';
		Ext.get('tdata').dom.value = '';
	}

	// 表单数据重置
	function reform() {
		Ext.get('formbh').dom.value = '';
		Ext.get('formdocbt').dom.value = '';
		Ext.get('formdepartment').dom.value = '';
		Ext.get('formrdate').dom.value = '';
		Ext.get('formjjcd').dom.value = '普通';
		Ext.get('formmj').dom.value = '普通';
	}
	
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
		window.top.showupdatewin(_record.get("UUID"),'<ww:property value="xmlmodel"></ww:property>');	
	}
});

function startlc() {
	
}
		</script>
	</head>
	<body>
		<div id="grid"></div>
		<div id="form"></div>
	</body>
</html>