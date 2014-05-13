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
    
    <title>内部公文---发文列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    
    <link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
    <script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
    <script src='<%=contextpath%>js/ext/ext-all.js'></script>
    <!-- <script src="/desktop/widgets/nbgwjh/js/hassentlist.js"></script> -->
    <script text="text/javascript">
    </script>
  </head>
  
  <body>
      <div id="grid" style="width:100%;height:100%"> 
       </div>
  </body>
   <script text="text/javascript">
   Ext.onReady(function() {

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([ sm,{
				header : '标题',
				dataIndex : 'title',
				width : 300,
				sortable : true
			}, {
				header : '发送人',
				dataIndex : 'sendor',
				width : 100,
				sortable : true
			}, {
				header : '发送单位',
				dataIndex : 'senddept',
				width : 100,
				sortable : true
			}, {
				header : '发送时间',
				dataIndex : 'sendtime',
				width : 100,
				sortable : true
			}, {
				header : '总发送人数',
				dataIndex : 'copy',
				width : 100,
				sortable : true
			}, {
				header : '未收人',
				dataIndex : 'unreceive',
				width : 100
				
			}, {
				header : '已收人',
				dataIndex : 'receive',
				width : 100
				
			}, {
				header : '拒签人',
				dataIndex : 'back',
				width : 100
				
			},{
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				sortable : true,
				hidden : true
			
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'nbgwjhAction_findBydeptId.action'
	});

	// 链接
	var store = new Ext.data.Store({
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
								},{
									name : 'copy'
								}, {
									name : 'unreceive'
								}, {
									name : 'receive'
								}, {
									name : 'back'
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
		title : '发文列表',
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
		tbar : new Ext.Toolbar(['标题：', docname,'发文单位：', fwdw,'发文人：', fwr, '从 开始日期：',
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
				},'-', {
					text : '新建',
					icon : '/usm/img/add.gif',
					cls : "x-btn-text-icon",
					handler : function(){
					var _width = 900;
					var _height = Ext.getBody().getHeight()-40;
					var addwin = new Ext.Window({
					title : '新建信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='nwiframe' name='nwiframe' width='" + (_width-30)  + "' height='" + (_height - 60) + "' src='nbgwjhAction_addnewpage.action'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '发送',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('nwiframe').dom.contentWindow.document.getElementById("addSend");
								//标题不能为空
								var title=Ext.get('nwiframe').dom.contentWindow.document.getElementById("sendTitle").value;
         					 	 if(title==""){
             					 	alert("标题不能为空");
           					  	 	return false;
           						}
								
								//发文人不能为空
								var inceptdept=fn.deptnames.value
         					 	 if(inceptdept==""){
             					 	alert("发送人不能为空");
           					  	 	return false;
           						}
								
								//附件不能为空
								var temp = document.getElementById('nwiframe');
								var count = temp.contentWindow.getCount();
								if(count==0){
						         alert("附件不能为空");
						            return;
					           	}
						         
								fn.submit();
								alert('发送成功');
								store.reload();
								addwin.close();
							}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('nwiframe').dom.contentWindow.document.getElementById("addSend");
								fn.reset();
							}
						}
					]
			
				});
				addwin.show();
			}
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
                        fwdw : Ext.get('fwdw').dom.value,
                        fwr : Ext.get('fwr').dom.value
					}
				});
	}
	
	// 刷新
	function refresh() {
		store.load({
					params : {
						start : 0,
						limit : pgsize
			
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
		Ext.get('to').dom.value = '';
		Ext.get('fwdw').dom.value = '';
		Ext.get('fwr').dom.value = ''
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
			  url:'nbgwjhAction_delfw.action',
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
	function winOpen(uuid){
		//window.open("nbgwjhAction_findByUuid.action?uuid="+uuid);
      var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '已发公文信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='nbwjhframe' name='nbwjhframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='nbgwjhAction_findByUuid.action?uuid="+uuid+"'></iframe>"
			});
			addwin.show();
		 

	}
	
    </script>
</html>
