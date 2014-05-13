<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	request.setCharacterEncoding("UTF-8");

	String wenzhongId = request.getParameter("wenzhongId");
	System.out.println("---wenzhongId--"+wenzhongId);
	String year = request.getParameter("year");
	System.out.println("---year--"+year);
	String zh = request.getParameter("zh");
	System.out.println("---zh--"+zh);
	
%>


  




<html>
	<head>
		<title>字号列表</title>
		<base href="<%=basePath%>">
		<script>var context='<%=path%>';</script>


		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
	
	</head>
	
	<body>
		<div id="grid"></div>
		<div id="topic-win"></div>
	</body>
</html>

<script type="text/javascript">
	//链接（）
	
		
		Ext.onReady(function() {
			var newFormWin;
    		var form1;
			// 复选框
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex:'messageid' 
			});
		
			// 列名称
			var cm = new Ext.grid.ColumnModel([sm,{
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				hidden:true
			}, {
				header : '年份',
				dataIndex : 'year',
				width : 160,
				sortable : true
			}, {
				header : '字号',
				dataIndex : 'zh',
				width : 160,
				sortable : true
			}, {
				header : '创建人',
				dataIndex : 'name',
				width : 160,
				sortable : true
			}, {
				header : '创建时间',
				dataIndex : 'createdate',
				width : 160,
				sortable : true
			}]);
			
			var proxy = new Ext.data.HttpProxy({
			   url : 'zhlistAction.do?wenzhongId=<%=wenzhongId%>'
		   });
		
		var store = new Ext.data.Store({
			proxy : proxy,
			reader : new Ext.data.JsonReader({
				totalProperty : 'totalProperty',
				root : 'root'
			}
			, [{
				name : 'uuid'
			}, {
				name : 'year'
			}, {
				name : 'zh'
			}, {
				name : 'name'
			}, {
				name : 'createdate'
			}])
		});
		
		var ttbar = new Ext.Toolbar(
			{
				items : [
				         {
				        	 text : '新增',
				        	 id : 'newmodelbtn',
				        	 cls : "x-btn-text-icon",
				        	 icon: '/desktop/resources/images/default/icons/grid2-2.gif',
				        	 handler : function() {
				        	 	addZihao();
				        	}
				         },'-', {
				        	 text : '删除',
				        	 id : 'delbtn',
				        	 cls : "x-btn-text-icon",
				        	 icon: '/desktop/widgets/electronicfile/images/delete_01.png',
				        	 handler : function() {
				        	 deleteZh(grid);
				        	} 
				        	
				         }, '-', {
							text : '修改',
							icon : '/usm/img/yingyong.gif',
							cls : "x-btn-text-icon",
							 handler : function() {
				        	 	updateZihao();
				        	}
				}
				         ]
			});
	     	
	     	
			var pgsize = 10;
	store.load({params:{start:0,limit:pgsize}});
	     	
			// 面板
			var grid = new Ext.grid.GridPanel({
				title : '',
				layout : 'fit',
				id : 'grid',
				bodyStyle : 'width:100%',
				autoHeight : true,
				loadMask : true,
				renderTo : 'grid',
				store : store,
				cm : cm,
				sm : sm,
				tbar:ttbar,
				bbar: new Ext.PagingToolbar({
					pageSize:pgsize,
					store:store,
					displayInfo:true,
					displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg:"没有记录"
				})
		});
	  
		store.on('beforeload', function() {
		this.baseParams = {
				//title:Ext.get('title').dom.value
				
		};
		
	});
	
		 store.load({params:{start:0,limit:pgsize}});

	//增加字号
	function addZihao(wenzhongId,year,zh){
	var wenzhongId='<%=wenzhongId%>';
	var form = new Ext.FormPanel({
		frame:true,
     	width:300,
     	url:'/zhsaveAction.action',
     	items:[{
     		xtype:'textfield',
     		name:'wenzhongId',
     		id:'wenzhongId',
     		hidden:true,
     		hideLabel:true,
     		value: wenzhongId
     	},{
     		xtype:'textfield',
     		name:'year',
     		id:'year',
     		fieldLabel:'年份',
     		allowBlank:false,
     		emptyText:"不能为空",
     		msgTarget:'side',
     		
     		anchor:'90%'
     	},{
     		xtype:'textfield',
     		name:'zh',
     		id:'zh',
     		allowBlank:false,
     		emptyText:"不能为空",
     		msgTarget:'side',
     		fieldLabel:'字号',
     		
     		anchor:'90%'
     	}],
     	buttons:[{
     		text:'确定',
     		handler:function(){
     			
     			if(Ext.getCmp('year').getValue==''){
     				alert("请填写年份");
     				return;
     			}
     			if(Ext.getCmp('zh').getValue==''){
     				alert('请填写字号');
     				return;
     			}
     			form.getForm().submit({
					success:function(){
						alert('操作成功');
						Ext.getCmp('grid').getStore().reload();
						Ext.getCmp('addZihao').close();
					}
				});
     		}
     	}]
	});
	
	var win = new Ext.Window({
			id:'addZihao',
			title:'新增字号',
			width:'310',
			height:'200',
			items:form
	});
		win.show();
}
//删除字号
function deleteZh(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请选择需要删除的信息');
	   		return;
	   }else{
	   		if(!confirm("确定删除？")){
	   			return;
	   		}
	   }
 
	var delAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('uuid').length>0 ){     
	    delAllList[delAllList.length]= selections[i].get('uuid');    
	   }else{
		   alert("error");
	   }
	  };
	   var fileList=delAllList.join();
	    var str="mtypeids="+fileList;
	  //JDS.ajax.Connection.LoadJsonFromUrl('mtype_deltype.action',null,str);
	  Ext.Ajax.request({
		  url:'/delete1Action.action',
		  params:{ids:fileList},
		  method:'post',
		  success:function(o){
			  alert(o.responseText);
			  grid.getStore().reload();
		  }
	  });
		  
}
//修改

	function updateZihao(){
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
	var wenzhongId='<%=wenzhongId%>';
	 var updateFormWin = function() {
             	if(!newFormWin) {
                	newFormWin = new Ext.Window( {
                    	el : 'topic-win',
                    	layout : 'fit',
                    	width : 310,
                    	height : 150,
                   		closeAction : 'hide',
                    	plain : true,
                    	title : '修改窗口',
                    	items : form1
                	});
            	}
            	newFormWin.show();
     };			
		
			form1 = new Ext.form.FormPanel({
				labelWidth : 80,
        		url : 'updatezhAction.action',
        		frame : true,
        		width : 230,
        		waitMsgTarget : true,
        		defaults : {
            		width : 150
        		},
		        defaultType : 'textfield',
		        items : [ {
		            fieldLabel : 'UUID',
		            name : 'uuid',
		            xtype : 'hidden'
		        }, {
     		xtype:'textfield',
     		name:'wenzhongId',
     		id:'wenzhongId',
     		hidden:true,
     		hideLabel:true,
     		value: wenzhongId
     	}, {
		            fieldLabel : '年份',
		            name : 'year'
		        }, {
		            fieldLabel : '字号',
		            name : 'zh'
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
//删除
function deleteFile(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请选择需要删除的文种');
	   		return;
	   }else{
	   		if(!confirm("确定删除？")){
	   			return;
	   		}
	   }
 
	var delAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('uuid').length>0 ){     
	    delAllList[delAllList.length]= selections[i].get('uuid');    
	   }else{
		   alert("error");
	   }
	  };
	   var fileList=delAllList.join();
	    var str="mtypeids="+fileList;
	  //JDS.ajax.Connection.LoadJsonFromUrl('mtype_deltype.action',null,str);
	  Ext.Ajax.request({
		  url:'/wzdeleteAction.action',
		  params:{ids:fileList},
		  method:'post',
		  success:function(o){
			  alert(o.responseText);
			  grid.getStore().reload();
		  }
	  });
		  
}
});
			
</script>