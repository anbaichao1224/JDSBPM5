<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath = request.getContextPath() + "/";
String sendid = request.getParameter("uuid");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'wynotice.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <!-- <script src="/desktop/widgets/gwjh/js/cancelCountlist.js"></script> -->
    <script text="text/javascript">
    var sendid='<%=sendid%>';
    </script>
  </head>
  
  <body>
      <div id="grid"> 
       </div>
  </body>
  <script text="text/javascript">
 
		Ext.onReady(function() {

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([
	
	       new Ext.grid.RowNumberer({
	          header : "序号",                                  
	           width : 40
	       
	       }),
	        {
	          
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				hidden:true
			},
	        {
	
				header : '单位名称',
				dataIndex : 'deptname',
				width : 100
				
			}, {
				header : '报名人数',
				dataIndex : 'bmnum',
				width : 100
				
			}, {
				header : '报名时间',
				dataIndex : 'bmtime',
				width : 160
				
			}, {
				header : '报名人员',
				dataIndex : 'bmry',
				width : 300
				
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'gwjhAction_findbbmBMlist.action?uuid='+sendid
	});

	// 链接
	var store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [   {
									name : 'uuid'
								},  {
									name : 'deptname'
								},  {
									name : 'bmnum'
								}
								,  {
									name : 'bmtime'
								},  {
									name : 'bmry'
								}])
			});
			
		
	
	var pgsize = 15;

	store.load({
			params : {
				start : 0,
				limit : pgsize
				
			}
		});
	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '报名人员统计列表',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid',
		store : store,
		cm : cm,

	    tbar : new Ext.Toolbar([
				{
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '返回',
					cls : "x-btn-text-icon",
					handler : function(btn){
					    window.location.href="/desktop/widgets/gwjh/receivelist.jsp";
					}
				}
				
				]),
		     bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				})
				
				
	});
	// 刷新
	function refresh() {
		store.load({
					params : {
						start : 0,
						limit : pgsize
			
					}
				});
	}
	
	
	

});

 function abortBM(){
   Ext.MessageBox.confirm('提示','确定是否终止报名',function(btn){
	       if(btn=='yes'){
	          Ext.Ajax.request({
	           url:'gwjhAction_updateBMFlag.action',
	           method:'post',
	           params:{
	           'uuid':sendid
	           },
	           success:function(){
	             alert("终止成功");
	             
	             
	           },failure:function(){
	             alert("操作失败");
	           }
	          });
	       }else{
	       
	         return;
	         
	       }
	   
	   })
   }
   
   
 function jxBM(){
   Ext.MessageBox.confirm('提示','确定是否继续报名',function(btn){
	       if(btn=='yes'){
	          Ext.Ajax.request({
	           url:'gwjhAction_updateJxBMFlag.action',
	           method:'post',
	           params:{
	           'uuid':sendid
	           },
	           success:function(){
	             alert("操作成功");
	             	
	             
	           },failure:function(){
	             alert("操作失败");
	           }
	          });
	       }else{
	       
	         return;
	         
	       }
	   
	   })
   }

	
    </script>
</html>
