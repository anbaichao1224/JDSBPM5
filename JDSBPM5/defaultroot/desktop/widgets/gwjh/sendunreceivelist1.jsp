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
   <!--  <script src="/desktop/widgets/gwjh/js/sendunreceivelist.js"></script> -->
    <script text="text/javascript">
    var sendid='<%=sendid%>';
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
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'gwjhAction_findsendUnreceive.action?uuid='+sendid
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
									name:'unInceptdept'
							    }
							 ])
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
		title : '未收单位',
		id : 'grid1',
		layout : 'fit',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid',
		autoScroll: true,
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
					 window.location.href="/desktop/widgets/gwjh/hassentlist.jsp";
					}
				}, {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
				}/*, {
	        	 text : '删除',
	        	 id : 'delbtn',
	        	 handler : function() {
	        	 alert(Ext.getCmp('grid1').getSelections().length);
	        	// deleteFile(Ext.getCmp('grid'));
	        	 store.reload();
	         }
	         }
				
				*/
				
				
				,{
							
					
							text :'短信提醒',
							id:"tixing",
							name:"tixing",
						
							handler : function() {
								var form = new Ext.FormPanel({
								
									
								frame:true, 
								width:580,
								height:270,
								id : "form",
								// url:"reply_save.action", 
							
								items:[
									 {
									
										 xtype:"textarea", 
										 fieldLabel:"短信内容",
										 name:'name',
										 width:400,
										 height:40
										// value:str
										
									  }]
									  	})
									var win = new Ext.Window({
									id:"xinxi",
									 title:"短信提醒",
									  width:550, 
									  height:120,
									  items:form,
									  	buttons : [{
											text : '发送',
											
											handler : function() {
											var content=form.getForm().findField("name").getValue();
											sendFile(Ext.getCmp('grid1'),content);
											Ext.getCmp("xinxi").close();
											
													
																}
											}]
								});
								win.show();
								
							
							
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
	         winClose();
	         return;
	         
	       }
	   
	   })
   }
   
   
   function sendFile(grid1,content){
  Ext.MessageBox.confirm('','确定是否发送短信提醒',function(btn){
     if(btn=='yes'){
	var selections=grid1.getSelections();
	   if (selections.length==0){
	          alert('请选择需要发送信息的单位');
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
	/*  Ext.MessageBox.wait("准备发送", "正在准备数据请稍候...", {
											width : 10
										});*/
	   var fileList=delAllList.join();
	    Ext.Ajax.request({
			  url:'gwjhAction_sendMsM.action',
			  params:{
			  cfsendid:fileList,
			  cfsms:content
			  },
			  method:'post',
			  success:function(){
			 Ext.Msg.alert('提示','发送成功');
			    //window.location.href="/desktop/widgets/gwjh/sendunreceivelist1.jsp";
				/*store.load({
				    params : {
						start : 0,
						limit : 10
				
				}
				});*/
			
			  }
		  });
		   }else{
	         return;
	         
	       }
		  })
}
   
    </script>
</html>
