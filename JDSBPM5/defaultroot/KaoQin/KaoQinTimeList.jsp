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
					header : '开始日期',
					dataIndex : 'ksrq',
					width : 200,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('Y-m-d')
				}, {
					header : '结束日期',
					dataIndex : 'jsrq',
					width : 200,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('Y-m-d')
				}, {
					header : 'UUID',
					dataIndex : 'uuid',
					width : 100,
					sortable : true,
					hidden : true
				}, {
					header : '上午签到时间',
					dataIndex : 'swqdsj',
					width : 200,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('H:i:s')
				}, {
					header : '上午签退时间',
					dataIndex : 'swqtsj',
					width : 200,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('H:i:s')
				}, {
					header : '下午签到时间',
					dataIndex : 'xwqdsj',
					width : 200,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('H:i:s')
				}, {
					header : '下午签退时间',
					dataIndex : 'xwqtsj',
					width : 100,
					type : 'date',
					renderer : Ext.util.Format.dateRenderer('H:i:s')
				}]);
			
			var proxy = new Ext.data.HttpProxy({
				url : 'KaoQinTimeList.action'
			});

			// 链接
			var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
									name : 'ksrq',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}, {
									name:'jsrq',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}, {
									name:'uuid'
								}, {
									name : 'swqdsj',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}, {
									name : 'swqtsj',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}, {
									name : 'xwqdsj',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}, {
									name : 'xwqtsj',
									type : 'date',
									dateFormat : 'Y-m-d H:i:s.u'
								}])
			});
				
			//面板
			var grid = new Ext.grid.GridPanel({
				title : '考勤时间列表',
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
					{
						text : '修改',
						icon : '/usm/img/yingyong.gif',
						cls : "x-btn-text-icon",
						handler : updatepage
					}
				])
			});		
			
			store.load({
				params : {
					start : 0,
					limit : 10
				}
			});
			
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
        		url : 'updateKaoQinTimeByUuid.action',
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
		            fieldLabel : '开始日期',
		            name : 'ksrq',
		            xtype : 'datefield',
		            format:'Y-m-d'
		        }, {
		            fieldLabel : '结束日期',
		            name : 'jsrq',
		            xtype : 'datefield',
		            format:'Y-m-d'		            
		        }, {
		            fieldLabel : '上午签到时间',
		            name : 'swqdsj',
		            xtype : 'timefield',
		            format:'H:i:s',
					minValue:'07:30',
					maxValue:'09:30',
					increment:5
		        }, {
		            fieldLabel : '上午签退时间',
		            name : 'swqtsj',
		            xtype : 'timefield',
		            format:'H:i:s',
		            minValue:'10:30',
					maxValue:'12:30',
					increment:5
		        }, {
		            fieldLabel : '下午签到时间',
		            name : 'xwqdsj',
		            xtype : 'timefield',
		            format:'H:i:s',
		            minValue:'13:30',
					maxValue:'15:30',
					increment:5
		        }, {
		            fieldLabel : '下午签退时间',
		            name : 'xwqtsj',
		            xtype : 'timefield',
		            format:'H:i:s',
		            minValue:'16:30',
					maxValue:'18:30',
					increment:5
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
