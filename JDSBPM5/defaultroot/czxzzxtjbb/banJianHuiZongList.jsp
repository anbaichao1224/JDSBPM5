<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

  <head>
    
    <title>各窗口上报汇总统计列表</title>
    <script type="text/javascript">var context='/';</script>
	<script type="text/javascript" src="/js/extAll.js"></script>
	<script type="text/javascript">
		Ext.onReady(function(){
			var newFormWin;
    		var form1;
			// 复选框
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

			// 列名称
			var cm = new Ext.grid.ColumnModel([sm, {
					header : '统计窗口',
					dataIndex : 'tjdw',
					width : 150,
					sortable : true
				}, {
					header : '申报（咨询）单位',
					dataIndex : 'sbdw',
					width : 150,
					sortable : true
				}, {
					header : '申报事项',
					dataIndex : 'sbsx',
					width : 150,
					sortable : true
				}, {
					header : 'UUID',
					dataIndex : 'uuid',
					width : 100,
					sortable : true,
					hidden : true
				}, {
					header : '受理件数',
					dataIndex : 'sljs',
					width : 75,
					sortable : true
				}, {
					header : '项目办理列级',
					dataIndex : 'xmbllj',
					width : 100,
					sortable : true
				}, {
					header : '上报时间',
					dataIndex : 'sbsj',
					width : 100,
					sortable : true,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('Y-m-d')
				}, {
					header : '承诺时间(天)',
					dataIndex : 'cnsj',
					width : 100,
					sortable : true
				}, {
					header : '办结时间',
					dataIndex : 'bjsj',
					width : 100,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('Y-m-d')
			}]);
			
			var proxy = new Ext.data.HttpProxy({
				url : 'tjbbFindAllHuiZongAction.action'
			});

			// 链接
			var store = new Ext.data.Store({
//				proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
									name : 'tjdw'
								},{
									name : 'sbdw'
								}, {
									name:'sbsx'
								}, {
									name:'uuid'
								}, {
									name : 'sljs'
								}, {
									name : 'xmbllj'
								}, {
									name : 'sbsj',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}, {
									name : 'cnsj'
								}, {
									name : 'bjsj',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}])
			});
			
			var tjdw = new Ext.form.ComboBox({
				width : 150,
				name : 'tjdw',
				hiddenName:"tjdw",
				store : new Ext.data.Store({    
            			proxy: new Ext.data.HttpProxy({url: 'HuoQuChuangKouXiaLa.action'}),    
            			reader: new Ext.data.JsonReader({root:'root2'},[
            				{name: 'value'},
            				{name: 'text'}
            			])   
        		}),
				triggerAction:'all',
				mode:'remote',
				valueField: 'value',
				displayField:'text'
			});

			var sdate = new Ext.form.DateField({
				width : 100,
				name : 'sdate',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
			});
			
			var edate = new Ext.form.DateField({
				width : 100,
				name:'edate',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
			});
					
			//面板
			var grid = new Ext.grid.GridPanel({
				title : '统计列表',
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
							pageSize : 10,
							store : store,
							displayInfo : true,
							displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
							emptyMsg : "没有记录"
				}),
				tbar : new Ext.Toolbar([
					'统计单位：', tjdw, '上报日期：',sdate, '至:', edate,{// 查询按钮
						id : 'newModelButton',
						icon : "/usm/img/search.png",
						text : '查  询',
						cls : "x-btn-text-icon",
						handler : queryDj
					},'-', {
						text : '退回',
						icon : '/usm/img/delete.gif',
						cls : "x-btn-text-icon",
						handler : function(){
							var updateuuid = new Array();
							var selections = grid.getSelectionModel()
									.getSelections();
							if (selections.length == 0) {
								alert('请选择需要退回的文件');
								return;
							}
	
							var updateAllList = new Array();
							for (var i = 0; i < selections.length; i++) {
								if (selections[i].get('uuid').length > 0) {
									updateAllList[updateAllList.length] = selections[i]
											.get('uuid');
								} else {
									grid
											.getSelectionModel()
											.deselectRow(selections[i].get('index'));
								}
							}
							updateuuid = updateAllList.join();
							Ext.Msg.confirm(
								"确认消息框","是否进行退回",function(btn){
									if(btn=="yes"){
										Ext.Ajax.request({
											url : 'tuiHuiTjbb.action',
											params : {
												updateuuid : updateuuid
											},
											success : function(resp, opts) {
												 Ext.Msg.alert('信息', '退回成功!');
												
												store.load({
															params : {
																start : 0,
																limit : 15
															}
														});
											},
											failure : function(resp, opts) {
												Ext.Msg.alert('信息', '退回失败!');
											}
										});
									}
								}
							);
						}
					}
					])
			});
			
			store.on('beforeload', function() {
				this.baseParams = {
						tjdw : Ext.get('tjdw').dom.value,
						sdate : Ext.get('sdate').dom.value,
						edate : Ext.get('edate').dom.value
				};
			});
			
			store.load({
				params : {
					start : 0,
					limit : 10,
					tjdw : Ext.get('tjdw').dom.value,
					sdate : Ext.get('sdate').dom.value,
					edate : Ext.get('edate').dom.value
				}
			});
			
			function queryDj() {
				store.load({
					params : {
						start : 0,
						limit : 10,
						tjdw : tjdw,
						sdate : sdate,
						edate : edate
					}
				});
			};
		
		})
	</script>
  </head>
  
  <body>
		<div id="grid"></div>
		<div id="topic-win"></div>
  </body>
</html>
