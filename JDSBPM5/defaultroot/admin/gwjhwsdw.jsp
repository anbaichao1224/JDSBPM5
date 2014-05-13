<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextpath = request.getContextPath() + "/";
	String sendid = request.getParameter("uuid");
	String deptid = request.getParameter("deptid");
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	String flag = request.getParameter("flag");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>未收单位</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
		<script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
		<script src='<%=contextpath%>js/ext/ext-all.js'></script>
		<script text="text/javascript">
    var sendid='<%=sendid%>';
    var username='<%=username%>';
    var password='<%=password%>'; 
     var flag='<%=flag%>'; 
    var deptid='<%=deptid%>'; 
    </script>
	</head>

	<body>
		<div id="grid">
		</div>
	</body>
	<script text="text/javascript">
   var store;
   Ext.onReady(function() {

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([sm, 
	        {
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				hidden:true
			}, {
				header : '未收单位',
				dataIndex : 'unInceptdept',
				width : 150,
				sortable : true
			}
			
			]);

	var proxy = new Ext.data.HttpProxy({
		url : 'Chaxun_GwYfWsDw.action?username='+username+'&password='+password+'&uuid='+sendid+'&flag='+flag
	});

	// 链接
	store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader(
				        {
							totalProperty : 'totalProperty',
							root : 'root'
						  }, [
						        {
									name : 'uuid'
								},
						        {
									name : 'unInceptor'
								}, {
									name:'unInceptdept'
							    }, {
									name:'cancel'
							    }
							 ])
			});
			
			var pgsize = 24;
			
			store.load({
				params : {
					start : 0,
					limit : pgsize
				
				}
			});

	

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '未收单位',
		id : 'grid1',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid',
		store : store,
		cm : cm,
		sm : sm,
		tbar : new Ext.Toolbar([
				{
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '返回',
					cls : "x-btn-text-icon",
					handler : function(){
					 window.location.href="/admin/gwjhyf.jsp?username="+username+"&password="+password+"&deptid="+deptid+"&flag="+flag;
					}
				},{
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '主页',
					cls : "x-btn-text-icon",
					handler : function(){
					 window.location.href="/admin/index.jsp";
					}
				}, {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
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
function successload() {
	store.load({
				params : {
					start : 0,
					limit : 15
				}
			});
	}
	
	
 function cancelSend(uuid){
   Ext.MessageBox.confirm('','确定是否取消',function(btn){
	       if(btn=='yes'){
	          Ext.Ajax.request({
	           url:'gwjhAction_updateCancelFlag.action',
	           method:'post',
	           params:{
	           'uuid':uuid
	           },
	           success:function(){
	             Ext.Msg.alert('提示','取消成功');
	              window.location.href="/desktop/widgets/gwjh/sendunreceivelist.jsp";
	             	store.load({
				    params : {
						start : 0,
						limit : 10
				
				}
			});
	             
	           },failure:function(){
	             Ext.Msg.alert('提示','取消失败');
	              window.location.href="/desktop/widgets/gwjh/sendunreceivelist.jsp";
	           }
	          });
	       }else{
	         return;
	         
	       }
	   
	   })
   }
   
   
      function sendFile(grid1){
  Ext.MessageBox.confirm('','确定是否取消',function(btn){
 		 if(btn=='yes'){
	var selections=grid1.getSelections();
	   if (selections.length==0){
	          alert('请选择需要取消的单位');
	   return;
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
	   	Ext.MessageBox.wait("准备取消", "正在准备数据请稍候...", {
											width : 300
										});
	    Ext.Ajax.request({
	    
			  url:'gwjhAction_updateCancelFlag1.action',
			  params:{
			  'fileList':fileList
			  },
			  method:'post',
			  success:function(){
			   Ext.Msg.alert('提示','取消成功');
			   window.location.href="/desktop/widgets/gwjh/sendunreceivelist.jsp";
			   store.load({
				    params : {
						start : 0,
						limit : 10
				
				}
			});
				
			
			  },failure:function(){
	              Ext.Msg.alert('提示','取消失败');
	           }
		  });
		   }else{
	         return;
	         
	       }
		  })
}
    </script>
</html>
