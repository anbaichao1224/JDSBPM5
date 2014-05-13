<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>

<%
	String path = request.getContextPath();
    String activityInstId = request.getParameter("activityInstId");
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
%>
<html>
	<head>
		
	</head>
	<body>
		<div id="grid111"></div>
		<div id="form"></div>
	</body>
	<base href="<%=basePath%>">
		<script>var context='<%=path%>/';</script>


		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
       
		<script type="text/javascript" src="<%=basePath%>js/extAll.js"></script>
		 <style type="text/css">
			 .x-grid3-row td,.x-grid3-summary-row td{ 
				 line-height:10px;//控制GRID单元格高度 
				 vertical-align:middle;//单元格垂直居中 
				 border-right: 1px solid #eceff6 !important;//控制表格列线 
				 border-top: 1px solid #eceff6 !important;//控制表格行线 
			}

		</style>
		<script type="text/javascript">
		var store;
		Ext.onReady(function() {
        var activityInstId='<%=activityInstId%>';

	// 列名称
	var cm = new Ext.grid.ColumnModel([ {
	          
				header :'文种',
				dataIndex : 'wzname',
				width : 100,
				sortable : true,
				align : 'center',
				renderer : function(value){
					return "<br>" + value;
				}
			
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
			}, {
			    header:'标题',
				dataIndex : 'bt',
				width : 200,
				align : 'center',
				sortable : true
			}, {
		
				header : ' 操作',
				dataIndex : 'xz',
				width : 100,
				align : 'center',
				sortable : true,
			    renderer: function(value,cellmeta,record){
			        var wzname=record.get("wzname");
			      //  alert(wzname);
			        var str = "<br><a href=\"javascript:void(0);\" onclick=\"xzbh('<%=activityInstId%>','"+value+"','"+wzname+"');\">新增</a>";
					//alert(str);			     
			        return str;
	        	}
			}, {
		
				header : ' 查看',
				dataIndex : 'ck',
				width : 100,
				align : 'center',
				sortable : true,
				renderer: function(value){
			        var str = "<br><a href=\"javascript:void(0);\" onclick=\"window.top.chakan('"+value+"');\">查看</a>";	     
			        return str;
	        	}
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : "listwhAction.action"
	});

	// 链接
	 store = new Ext.data.Store({
				// proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalCount',
							root : 'root'
						}, [{
						           name : 'uuid'
								}, {
				
									name : 'wzname'
								}, {
								   
								   name : 'bt'
								}, {
								    
								    name : 'nh'
							    }, {
								
									name : 'bh'
								}, {
								
									name : 'xz'
								}, {
								
									name : 'ck'
								
								}])
			});


	var pgsize = 10;

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '详细列表',
		id : 'grid111',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid111',
		store : store,
		cm : cm

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
});
//查看	
 
 function chakan(uuid){
            var _width = 800;
			var _height = Ext.getBody().getHeight()-40;
			var addwin = new Ext.Window({
					title : '详细列表',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='bhckiframe1' name='bhckiframe1' width='" + (_width-30)  + "' height='" + (_height - 60) + "' src='/bhAction.action?wenzhongId="+uuid+"'></iframe>"
						
				

			});
			addwin.show();
	}
//新增编号	
	function xzbh(activityInstId,uuid,wzname){
	var myDate = new Date();
	var nh=myDate.getFullYear();
	var form = new Ext.FormPanel({
		frame:true,
     	width:300,
     	url:'/addbhAction.action',
     	items:[{
     	   xtype:'textfield',
     		name:'activityInstId',
     		id:'activityInstId',
     		hidden:true,
     		hideLabel:true,
     		value:activityInstId
     	},{
     		xtype:'textfield',
     		name:'nh',
     		id:'nh',
     		fieldLabel:'请输入年份',
     		allowBlank:false,
     		emptyText:"不能为空",
     		msgTarget:'side',
     		value:nh,
     		anchor:'90%'
     	},{
     		xtype:'textfield',
     		name:'bh',
     		id:'bh',
     		fieldLabel:'请输入编号',
     		allowBlank:false,
     		emptyText:"不能为空",
     		regex:/^[0-9]+$/,
     		msgTarget:'side',	
     		anchor:'90%'
     	},{
     	   xtype:'textfield',
     		name:'uuid',
     		id:'uuid',
     		hidden:true,
     		hideLabel:true,
     		value:uuid
     	},{
     	   xtype:'textfield',
     		name:'wzname',
     		id:'wzname',
     		hidden:true,
     		hideLabel:true,
     		value:wzname
     	}],
     	buttons:[{
     		text:'确定',
     		handler:function(){
     			form.getForm().submit({
					success:function(form, action){
						if(action.response.responseText == 'true'){
							alert('添加成功');
							Ext.getCmp('grid111').getStore().reload();
							var record = form.getValues();
							var bhstr = record.wzname + '[' + record.nh + ']' + record.bh + '号';
							//var bhstr1 = record.wzname + '[' + record.nh + ']' + record.hqbh + '号';
							window.top.writebh(bhstr);
							window.top.writehqbh(bhstr);
							Ext.getCmp('xzbh').close();
							window.top.bhwindowclose();
							
						}else{
							alert('添加失败 编号已存在');
							Ext.getCmp('xzbh').close();
						}
					}
				});
     		}
     	}]
	});
	
	var win = new Ext.Window({
			id:'xzbh',
			title:'新增编号',
			width:'310',
			height:'150',
			items:form
	});
		win.show();
}
		</script>
</html>