<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

  <head>
    
    <title>各窗口草稿统计列表</title>
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
					header : '申报单位',
					dataIndex : 'sbdw',
					width : 200,
					sortable : true
				}, {
					header : '申报事项',
					dataIndex : 'sbsx',
					width : 200,
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
				url : 'tjbbFindAllByTjdwAction.action?date=' + new Date()
			});

			// 链接
			var store = new Ext.data.Store({
//				proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
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

			
			var zxdw = new Ext.form.TextField({
				width : 100,
				name : 'zxdw',
				allowBlank : true
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
					'申报单位：', zxdw, '上报日期：',sdate, '至:', edate,{// 查询按钮
						id : 'newModelButton',
						icon : "/usm/img/search.png",
						text : '查  询',
						cls : "x-btn-text-icon",
						handler : queryDj
					}, '-', {
						text : '修改',
						icon : '/usm/img/yingyong.gif',
						cls : "x-btn-text-icon",
						handler : updatepage
					},'-', {
						text : '上报',
						icon : '/usm/img/add.gif',
						cls : "x-btn-text-icon",
						handler : function(){
							var updateuuid = new Array();
							var selections = grid.getSelectionModel()
									.getSelections();
							if (selections.length == 0) {
								alert('请选择需要上报的文件');
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
								"确认消息框","是否进行上报",function(btn){
									if(btn=="yes"){
										Ext.Ajax.request({
											url : 'shangBaoTjbb.action',
											params : {
												updateuuid : updateuuid
											},
											success : function(resp, opts) {
												 Ext.Msg.alert('信息', '上报成功!');
												
												store.load({
															params : {
																start : 0,
																limit : 15
															}
														});
											},
											failure : function(resp, opts) {
												Ext.Msg.alert('信息', '上报失败!');
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
						zxdw : Ext.get('zxdw').dom.value,
						sdate : Ext.get('sdate').dom.value,
						edate : Ext.get('edate').dom.value
				};
			});
						
						
			store.load({
				params : {
					start : 0,
					limit : 10,
					zxdw : Ext.get('zxdw').dom.value,
					sdate : Ext.get('sdate').dom.value,
					edate : Ext.get('edate').dom.value
				}
			});
			
			function queryDj() {
				store.load({
					params : {
						start : 0,
						limit : 10,
						zxdw : zxdw,
						sdate : sdate,
						edate : edate
					}
				});
			};

			
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
//				var uuid=_record.get('UUID');
//				alert(uuid);
				updateFormWin();
				form1.getForm().loadRecord(_record);				 
			};
			
			 var updateFormWin = function() {
             	if(!newFormWin) {
                	newFormWin = new Ext.Window( {
                    	el : 'topic-win',
                    	layout : 'fit',
                    	width : 600,
                    	height : 500,
                   		closeAction : 'hide',
                    	plain : true,
                    	title : '修改窗口',
                    	items : form1
                	});
            	}
            	newFormWin.show();
       	 	};			
		
			form1 = new Ext.form.FormPanel({
				labelWidth : 120,
        		url : 'updateTjbbByUuid.action',
        		frame : true,
        		width : 550,
        		waitMsgTarget : true,
        		defaults : {
            		width : 250
        		},
		        defaultType : 'textfield',
		        items : [ {
		            fieldLabel : 'UUID',
		            name : 'uuid',
		            xtype : 'hidden'
		        }, {
		            fieldLabel : '申报单位',
		            name : 'sbdw'
		        },{
		        	xtype : "combo",
		        	fieldLabel : '申报事项',
		        	name : 'sbsx',
		        	store : new Ext.data.Store({    
            			proxy: new Ext.data.HttpProxy({url: 'huoQuXiaLaValue.action'}),    
            			reader: new Ext.data.JsonReader({root:'root'},[
            				{name: 'value'},
            				{name: 'text'}
            			])   
        			}),
					valueField : 'value',     
					displayField : 'text',      
					mode : 'remote',      
					triggerAction : 'all'
		        }, {
		            fieldLabel : '受理件数',
		            name : 'sljs'
		        }, {
		            fieldLabel : '项目办理列级',
		            name : 'xmbllj'
		        }, {
		            fieldLabel : '上报时间',
		            name : 'sbsj',
		            xtype : 'datefield',
		            format:'Y-m-d'
		        }, {
		            fieldLabel : '承诺时间(天)',
		            name : 'cnsj'
		        }, {
		        	xtype : 'datefield',
		            fieldLabel : '办结时间',
		            name : 'bjsj',
		            format:'Y-m-d'
		        }],
 				buttons : [{
 					text : '保存',
 					handler : function(){
 						form1.getForm().submit({
 							success:function(form,action){
 								Ext.Msg.alert('提示','修改成功');
 								store.reload();
 								newFormWin.hide();
 							},
 							failure:function(){
 								Ext.Msg.alert('提示','修改失败');
 							}
 						});
 					}
 				}]	
			});
		
		})
	</script>
  </head>
  
  <body>
		<div id="grid"></div>
		<div id="topic-win"></div>
  </body>
</html>
