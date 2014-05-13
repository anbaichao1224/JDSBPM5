<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

  <head>
    
    <title></title>
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
					header : '窗口',
					dataIndex : 'ck',
					width : 150,
					sortable : true
				}, {
					header : '考勤日期',
					dataIndex : 'rq',
					width : 100,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('Y-m-d')
				}, {
					header : 'UUID',
					dataIndex : 'uuid',
					width : 100,
					sortable : true,
					hidden : true
				}, {
					header : '考勤时间类型',
					dataIndex : 'sjlx',
					width : 100
				}, {
					header : '考勤类型',
					dataIndex : 'czlx',
					width : 100
				}, {
					header : '时间',
					dataIndex : 'czsj',
					width : 200,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('Y-m-d h:i:s')
				}, {
					header : '状态',
					dataIndex : 'zt',
					width : 100
				}, {
					header : '备注',
					dataIndex : 'bz',
					width : 100
				}]);
			
			var proxy = new Ext.data.HttpProxy({
				url : 'KaoQinList.action'
			});

			// 链接
			var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
									name : 'ck'
								}, {
									name:'rq',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}, {
									name:'uuid'
								}, {
									name : 'sjlx'
								}, {
									name : 'czlx'
								}, {
									name : 'czsj',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}, {
									name : 'zt'
								}, {
									name : 'bz'
								}])
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
			
			var stime = new Ext.form.TimeField({
				width : 100,
				name:'stime',
				fieldLabel:'时间',
				minValue:'07:30',
				maxValue:'18:30',
				increment:5,
				format:'H:i:s'	
			});
			
			var etime = new Ext.form.TimeField({
				width : 100,
				name:'etime',
				fieldLabel:'时间',
				minValue:'07:30',
				maxValue:'18:30',
				increment:5,
				format:'H:i:s'	
			});
			
			var data = [
				['迟到','迟到'],
				['早退','早退'],
				['正常','正常']
			];
			
			var store1 = new Ext.data.SimpleStore({
				fields:['value','text'],
				data : data
			});
			
			var kqzt = new Ext.form.ComboBox({
				width : 100,
				store: store1,
				name:'kqzt',
				triggerAction:'all',
				mode:'local',
				valueField: 'value',
				displayField:'text'
			});
				
			//面板
			var grid = new Ext.grid.GridPanel({
				title : '考勤统计列表',
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
					'开始日期：',sdate, '结束日期:',edate, '开始时间：',stime, '结束时间:',etime, '状态:',kqzt,{// 查询按钮
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
					}
				])
			});	
			
			store.on('beforeload', function() {
				this.baseParams = {
						sdate : Ext.get('sdate').dom.value,
						edate : Ext.get('edate').dom.value,
						stime : Ext.get('stime').dom.value,
						etime : Ext.get('etime').dom.value,
						kqzt : Ext.get('kqzt').dom.value
				};
			});	
			
			store.load({
				params : {
					start : 0,
					limit : 10,
					sdate : Ext.get('sdate').dom.value,
					edate : Ext.get('edate').dom.value,
					stime : Ext.get('stime').dom.value,
					etime : Ext.get('etime').dom.value,
					kqzt : Ext.get('kqzt').dom.value
				}
			});
			
			function queryDj() {
				store.load({
					params : {
						start : 0,
						limit : 10,
						sdate : sdate,
						edate : edate,
						stime : stime,
						etime : etime,
						kqzt : kqzt
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
				labelWidth : 75,
        		url : 'updateKaoQinByUuid.action',
        		frame : true,
        		width : 550,
        		waitMsgTarget : true,
        		defaults : {
            		width : 230
        		},
		        defaultType : 'textfield',
		        items : [ {
		            fieldLabel : 'UUID',
		            name : 'uuid',
		            xtype : 'hidden'
		        }, {
		            fieldLabel : '窗口',
		            name : 'ck',
		            readOnly : true
		        }, {
		            fieldLabel : '考勤日期',
		            name : 'rq',
		            xtype : 'datefield',
		            format:'Y-m-d',
		            readOnly : true		            
		        }, {
		            fieldLabel : '考勤时间类型',
		            name : 'sjlx',
		            readOnly : true
		        }, {
		            fieldLabel : '考勤类型',
		            name : 'czlx',
		            readOnly : true
		        }, {
		            fieldLabel : '时间',
		            name : 'czsj',
		            xtype : 'datefield',
		            format:'Y-m-d h:i:s',
		            readOnly : true
		        }, {
		            fieldLabel : '状态',
		            xtype : "combo",
		            name : 'zt',
		           	store : new Ext.data.Store({    
            			proxy: new Ext.data.HttpProxy({url: 'zhuangtai.txt'}),    
            			reader: new Ext.data.ArrayReader({},[
            				{name: 'value'},
            				{name: 'text'}
            			])   
        			}),
					valueField : 'value',     
					displayField : 'text',      
					mode : 'remote',      
					triggerAction : 'all'
		        }, {
		            fieldLabel : '备注',
		            name : 'bz'
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
