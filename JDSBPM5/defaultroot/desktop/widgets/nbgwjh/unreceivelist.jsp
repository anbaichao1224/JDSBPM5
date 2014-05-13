<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath = request.getContextPath() + "/";

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
    <!-- <script src="/desktop/widgets/nbgwjh/js/unreceivelist.js"></script> -->
    <script text="text/javascript">
    </script>
  </head>
  
  <body>
      <div id="grid" style="width:100%;height:100%"> 
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
	var cm = new Ext.grid.ColumnModel([sm, {
				header : '标题',
				dataIndex : 'title',
				width : 300,
				sortable : true
			}, {
				header : '发送人',
				dataIndex : 'sendor',
				width : 150,
				sortable : true
			}, {
				header : '发送单位',
				dataIndex : 'senddept',
				width : 150,
				sortable : true
			}, {
				header : '发送时间',
				dataIndex : 'sendtime',
				width : 150,
				sortable : true
			}, {
				header : '接收情况',
				dataIndex : 'signFlag',
				width : 150,
				sortable : true
			},{
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				sortable : true,
				hidden : true
			
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'nbgwjhAction_findUnReceivelist.action'
	});

	// 链接
	store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
									name : 'title'
								}, {
									name:'sendor'
								}, {
									name : 'senddept'
								}, {
									name : 'sendtime'
								}, {
									name : 'signFlag'
								}, {
									name : 'uuid'
								}])
			});

	var docname = new Ext.form.TextField({
				width : 100,
				name : 'title',
				allowBlank : true
			});
	var fwdw = new Ext.form.TextField({
				width : 100,
				name : 'fwdw',
				allowBlank : true
			});
	var fwr = new Ext.form.TextField({
				width : 100,
				name : 'fwr',
				allowBlank : true
			});
	var rdata = new Ext.form.DateField({
				width : 100,
				name : 'from',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
			});
			
	var tdata = new Ext.form.DateField({
				width : 100,
				name:'to',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
	});

	

	

	var pgsize = 15;

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '收文列表',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		width : '1383',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid',
		store : store,
		cm : cm,
		 sm:sm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				}),
		tbar : new Ext.Toolbar(['标题：', docname, '发文单位：', fwdw, '发文人：', fwr, '从 开始日期：',
				rdata, '至 结束时间:', tdata,
				{
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '查  询',
					cls : "x-btn-text-icon",
					handler : queryDj
				}, {
					icon : '/usm/img/depart.gif',
					text : '重  置',
					cls : "x-btn-text-icon",
					handler : requery
				}, {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
				}, {
	        	 text : '删除',
	        	 id : 'delbtn',
	        	 handler : function() {
	        	 deleteFile(Ext.getCmp('grid'));
	        	 store.reload();
	         }
	      } 
				
				])
				
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
	

	store.on('beforeload', function() {
				this.baseParams = {
					title : Ext.get('title').dom.value,
                     from : Ext.get('from').dom.value,
                      to : Ext.get('to').dom.value,
                       fwdw : Ext.get('fwdw').dom.value,
                      fwr : Ext.get('fwr').dom.value
					
				};
			});

	store.load({
				params : {
					start : 0,
					limit : pgsize,
					title : Ext.get('title').dom.value,
                    from : Ext.get('from').dom.value,
                    to : Ext.get('to').dom.value,
                    fwdw : Ext.get('fwdw').dom.value,
                      fwr : Ext.get('fwr').dom.value
				}
			});
			
 

	// 登记查询
	function queryDj() {
		store.load({
					params : {
						start : 0,
						limit : pgsize,
						title:Ext.get('title').dom.value,
                        from:Ext.get('from').dom.value,
                        to:Ext.get('to').dom.value,
                         fwdw:Ext.get('fwdw').dom.value,
                        fwr:Ext.get('fwr').dom.value
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
		Ext.get('title').dom.value = '';
		Ext.get('from').dom.value = '';
		Ext.get('fwdw').dom.value = '';
		Ext.get('fwr').dom.value = ''
		Ext.get('to').dom.value = ''
		
	}
});
function deleteFile(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请选择需要删除的文件');
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
	    Ext.Ajax.request({
			  url:'nbgwjhAction_delsw.action',
			  params:{
			  dels:fileList
			  },
			  method:'post',
			  success:function(o){
			   alert(o.responseText);
			   grid.getStore().reload();
				
			
			  }
		  });
		  
}
	function winOpen(uuid,sendid){
   
      var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '接收公文信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='nbwjhframe' name='nbwjhframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='nbgwjhAction_findInceptByUuid.action?uuid="+uuid+"&id="+sendid+"'></iframe>",
					tbar:[
						{
							id : 'nbhz',
							icon : "/usm/img/save.gif",
							text : '回复',
							cls : "x-btn-text-icon",
							handler : function(){
										var _width = 900;
										var _height = Ext.getBody().getHeight()-40;
										var addwin = new Ext.Window({
										title : '添加回执信息',
										layout : 'fit',
										width : _width,
										minWidth : 350,
										height : _height,
										minHeight : 200,
										y:0,
										html:"<iframe id='djiframe2' name='djiframe2' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='nbgwjhAction_addpage.action?uuid="+uuid+"&id="+sendid+"'></iframe>",
										tbar:[
											{
												id : 'djsave',
												icon : "/usm/img/save.gif",
												text : '回复',
												cls : "x-btn-text-icon",
												handler : function(){
													var fn = Ext.get('djiframe2').dom.contentWindow.document.getElementById("addHz");
													fn.submit();
													alert("回复成功");
													addwin.close();
												}
											},{
												id : 'djreset',
												icon : "/usm/img/depart.gif",
												text : '重  置',
												cls : "x-btn-text-icon",
												handler : function(){
													var fn = Ext.get('djiframe2').dom.contentWindow.document.getElementById("addHz");
													fn.reset();
												}
											}
										]
								});
								addwin.show();
								
							}
						},{
							id : 'nbback',
							icon : "/usm/img/depart.gif",
							text : '拒签',
							cls : "x-btn-text-icon",
							handler : function(){
						      	var _width = 900;
								var _height = Ext.getBody().getHeight()-40;
								var addwin = new Ext.Window({
								title : '拒签理由',
								layout : 'fit',
								width : _width,
								minWidth : 350,
								height : _height,
								minHeight : 200,
								y:0,
								html:"<iframe id='djiframe1' name='djiframe1' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='nbgwjhAction_addbackpage.action?uuid="+ uuid+"&id="+sendid+"'></iframe>",
								tbar:[
									{
										id : 'djsave',
										icon : "/usm/img/save.gif",
										text : '确定',
										cls : "x-btn-text-icon",
										handler : function(){
									  		  var fn = Ext.get('djiframe1').dom.contentWindow.document.getElementById("addBack");
												fn.submit();
												alert("拒签成功");
												addwin.close();
									      
									}
									},{
										id : 'djreset',
										icon : "/usm/img/depart.gif",
										text : '重  置',
										cls : "x-btn-text-icon",
										handler : function(){
											var fn = Ext.get('djiframe1').dom.contentWindow.document.getElementById("addBack");
											fn.reset();
										}
									}
								]
							});
							addwin.show();
							}
						},{
						
						  	 id : 'nbqs',
							icon : "/usm/img/depart.gif",
							text : '签收',
							cls : "x-btn-text-icon",
							handler : function(){
							store.load({
								params : {
									start : 0,
									limit : 15
								}
							});
							
							  addwin.close();
							
							}
						
						
						
						}
					]
			});
			addwin.show();
		 

	}
		
    </script>
</html>
