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
		
	</head>
	<body>
		<div id="grid" style="width:100%;height:100%"></div>
		<div id="form"></div>
	</body>
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
	            header : '收文编号',
				dataIndex : 'SN',
				width : 100,
				sortable : true
			}, {
				header : '来文标题',
				dataIndex : 'DOCBT',
				width : 580,
				sortable : true
			}, {
				
				header : '等级',
				dataIndex : 'EMERGENCY',
				width : 100,
				sortable : true
			}, {
				header : ' 登记人',
				dataIndex : 'DJR',
				width : 100,
				sortable : true
			},{
				header : ' 原文编号',
				dataIndex : 'LWBH',
				width : 100,
				sortable : true
			},{
				header : ' 来电编号',
				dataIndex : 'LDBH',
				width : 100,
				sortable : true
			},{
				header : '单位',
				dataIndex : 'DEPARTMENT',
				width : 100,
				sortable : true
			
			}, {
				header : '来文日期',
				dataIndex : 'RDATE',
				width : 100,
				sortable : true
			}, {
				
				header : '操作',
				dataIndex : 'OPTION',
				width : 100
			
			}
			]);

	var proxy = new Ext.data.HttpProxy({
		url : '/JblAction_findAllByModel.action?xmlmodel=<ww:property value="xmlmodel"></ww:property>'
	});

	// 链接
	var store = new Ext.data.Store({
				// proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalCount',
							root : 'root'
						}, [{
						        name : 'SN'
								}, {
									name : 'DOCBT'
								}, {
								    name : 'EMERGENCY'
								}, {
									name : 'DJR'
								}, {
								    name : 'LWBH'
								}, {
									name : 'DEPARTMENT'
								}, {
									name : 'CLASSIFICATION'
								}, {
										name:'RDATE'
								}, {
									name : 'UUID'
								}, {
									name : 'OPTION'
								}, {
									name : 'LDBH'
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
var swbh = new Ext.form.TextField({
				width : 100,
				name : 'swbh',
				allowBlank : true
			});
			var ywbh = new Ext.form.TextField({
				width : 100,
				name : 'ywbh',
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
		title : '交办理',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		width : '1383',
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
		tbar : new Ext.Toolbar(['来文标题：', docname, '来文单位：', department,'收文编号：', swbh,'原文编号：', ywbh, '开始日期：',
				rdata, '结束时间:', tdata,{// 查询按钮
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '查  询',
					cls : "x-btn-text-icon",
					handler : queryDj
				}, {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
				}
				
				
				
				])
				
		});
		
	store.on('beforeload', function() {
				this.baseParams = {
					docname : Ext.get('docname').dom.value,
					department : Ext.get('department').dom.value,
					rdata : Ext.get('rdata').dom.value,
					swbh : Ext.get('swbh').dom.value,
					ywbh : Ext.get('ywbh').dom.value
				};
			});

	store.load({
				params : {
					start : 0,
					limit : pgsize,
					docname : Ext.get('docname').dom.value,
					department : Ext.get('department').dom.value,
					rdata : Ext.get('rdata').dom.value,
					swbh : Ext.get('swbh').dom.value,
					ywbh : Ext.get('ywbh').dom.value
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
						tdata : tdata,
						swbh:swbh,
						ywbh:ywbh
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
		Ext.get('docname').dom.value = '';
		Ext.get('department').dom.value = '';
		Ext.get('rdata').dom.value = '';
		Ext.get('tdata').dom.value = '';
		Ext.get('ywbh').dom.value = '';
		Ext.get('swbh').dom.value = '';
	}

	// 表单数据重置
	function reform() {
		Ext.get('formbh').dom.value = '';
		Ext.get('formdocbt').dom.value = '';
		Ext.get('formdepartment').dom.value = '';
		Ext.get('formrdate').dom.value = '';
		Ext.get('formjjcd').dom.value = '一般';
		Ext.get('formmj').dom.value = '一般';
	}
	
});


function jiaomanagne(uuid){
        var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '交办理',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-30)  + "' height='" + (_height - 60) + "' src='/SwdjAction_showmanagne.action?uuid="+uuid+"'></iframe>"
				
			});
			addwin.show();
  
  }
	
	//显示修改数据页面的方法
	function updatepage1(){
		var records = Ext.getCmp('grid').getSelectionModel().getSelections();
		var _record = Ext.getCmp('grid').getSelectionModel().getSelected();
		showupdatewin(_record.get("UUID"),'<ww:property value="xmlmodel"></ww:property>');
	}
	//交办理标题查看页面
function showupdatewin(uuid,xmlmodel){
var _width = 900;
			var _height = 450;
			var updatewin = new Ext.Window({
					title : '查看信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-30)  + "' height='" + (_height - 40) + "' src='/JblAction_jblck.action?xmlmodel=" + xmlmodel + "&uuid=" + uuid + "'></iframe>",
					tbar:[
						{
							id : 'djsave',
						
							text : '',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("updateDj");
								fn.submit({
									success:function(){
										Ext.Msg.alert('信息','登记信息添加成功!');
									},
									failure:function(){
										Ext.Msg.alert('信息','操作失败!');
									}
								});
								updatewin.close();
							}
						}
					]
			});
			updatewin.show();
}
	
		</script>
</html>