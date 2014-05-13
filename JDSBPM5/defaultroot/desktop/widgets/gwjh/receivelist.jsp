<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String contextpath = request.getContextPath() + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>公文交换--已收列表</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link rel="stylesheet" type="text/css"
			href="<%=contextpath%>js/ext/resources/css/ext-all.css" />
		<script src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
		<script src='<%=contextpath%>js/ext/ext-all.js'></script>
		<!--  <script src="/desktop/widgets/gwjh/js/receivelist.js"></script> -->
		<script text="text/javascript">
    </script>
	</head>

	<body>
		<div id="grid" style="width:100%;height:100%"></div>
		<div id="form"></div>
	</body>
	<script text="text/javascript">
  var store;
  Ext.onReady(function() {

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([{
				header : '标题',
				dataIndex : 'title',
				width : 550,
				sortable : true
			}, {
				header : '编号',
				dataIndex : 'fdbh',
				width : 170,
				sortable : true
			}, {
				header : '文号',
				dataIndex : 'wh',
				width : 170,
				sortable : true
			}, {
				header : '等级',
				dataIndex : 'jjcd',
				width : 40,
				sortable : true
			}, {
				header : '发送单位',
				dataIndex : 'senddept',
				width : 120,
				sortable : true
			}, {
				header : '发送时间',
				dataIndex : 'sendtime',
				width : 150,
				sortable : true
			} ,{
			header :'查看报名',
			dataIndex : 'ckbm',
			width : 70
			} ,{
			header :'操作',
			dataIndex : 'cz',
			width : 80
			
		
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'gwjhAction_findReceivelist.action'
	});

	// 链接
	 store = new Ext.data.Store({
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [ {
									name : 'title'
								}, {
									name:'fdbh'
								}, {
									name : 'senddept'
								}, {
									name : 'wh'
								}, {
									name : 'sendtime'
								}, {
									name : 'jjcd'
								},{
								    name:'ckbm'
								},{
								    name:'cz'
								}])
			});

	var docname = new Ext.form.TextField({
				width : 100,
				name : 'title',
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
	
	var wh = new Ext.form.TextField({
				width : 100,
				name : 'wh',
				allowBlank : true
			});
	var fdbh = new Ext.form.TextField({
				width : 100,
				name : 'fdbh',
				allowBlank : true
			});
	var pgsize = 24;

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '已收公文列表',
		id : 'grid',
		layout : 'fit',
		bodyStyle : 'width:100%',
		width : '1383',
		autoHeight : true,
		autoScroll: true,
		loadMask : true,
		renderTo : 'grid',
		store : store,
		cm : cm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				}),
		tbar : new Ext.Toolbar(['标题：', docname,'文号：', wh, '编号：',fdbh, '从 开始日期：',
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
				}
				
				])
				
	});

	store.on('beforeload', function() {
				this.baseParams = {
					title : Ext.get('title').dom.value,
                     from : Ext.get('from').dom.value,
                      to : Ext.get('to').dom.value,
                      wh:Ext.get('wh').dom.value,
                        fdbh:Ext.get('fdbh').dom.value
					
				};
			});

	store.load({
				params : {
					start : 0,
					limit : pgsize,
					title : Ext.get('title').dom.value,
                    from : Ext.get('from').dom.value,
                    to : Ext.get('to').dom.value,
                      wh:Ext.get('wh').dom.value,
                        fdbh:Ext.get('fdbh').dom.value
				}
			});
			
  function renderUnreceive(value){
      
  
  
  }

	// 登记查询
	function queryDj() {
		store.load({
					params : {
						start : 0,
						limit : pgsize,
						title:Ext.get('title').dom.value,
                        from:Ext.get('from').dom.value,
                       to : Ext.get('to').dom.value,
                      wh:Ext.get('wh').dom.value,
                        fdbh:Ext.get('fdbh').dom.value
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
		Ext.get('wh').dom.value = '';
		Ext.get('fdbh').dom.value = ''
	}
});


function winOpen(uuid,sendid){
      var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '已收公文信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='nbwjhframe' name='nbwjhframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='gwjhAction_findInceptByUuid.action?uuid="+uuid+"&id="+sendid+"'></iframe>",
					tbar:[{
							id : 'bmback',
							icon : "/usm/img/depart.gif",
							text : '报名',
							cls : "x-btn-text-icon",
							handler : function(){
											
						      				var _width = 900;
											var _height = Ext.getBody().getHeight()-40;
											var addwin = new Ext.Window({
											title : '报名人员信息',
											layout : 'fit',
											width : _width,
											minWidth : 350,
											height : _height,
											minHeight : 200,
											y:0,
											html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 20) + "' src='gwjhAction_addbmpage.action?uuid="+ uuid+"&id="+sendid+"'></iframe>",
											tbar:[
												{
													id : 'djsave',
													icon : "/usm/img/save.gif",
													text : '确定',
													cls : "x-btn-text-icon",
													handler : function(){
												  		    var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("bmBack");
												  		    var temp = document.getElementById('djiframe');
															var result = temp.contentWindow.getBmPersons();
															Ext.get('djiframe').dom.contentWindow.document.getElementById("bmPersons").value=result;
															fn.submit();
															alert("报名成功");
															addwin.close();
												      
													}
												}
											]
									});
									addwin.show();
							}
						},
						{
							id : 'nbhz',
							icon : "/usm/img/save.gif",
							text : '回复',
							cls : "x-btn-text-icon",
							handler : function(){
							
									var _width = 900;
									var _height = Ext.getBody().getHeight()-40;
									var addwin = new Ext.Window({
									title : '添加回复信息',
									layout : 'fit',
									width : _width,
									minWidth : 350,
									height : _height,
									minHeight : 200,
									y:0,
									html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='gwjhAction_addpage.action?uuid="+ uuid+"&id="+sendid+"'></iframe>",
									tbar:[
										{
											id : 'djsave',
											icon : "/usm/img/save.gif",
											text : '回复',
											cls : "x-btn-text-icon",
											handler : function(){
											        var temp = document.getElementById('djiframe');
													var count = temp.contentWindow.getCount();
													var content = Ext.get('djiframe').dom.contentWindow.document.getElementById("replybean.replyContent").value;
												      if(count==0 && content==""){
												      		alert("回复内容和附件至少填一个");
												         	return false;
												      }
													var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addHz");
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
														var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addHz");
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
											html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='gwjhAction_addbackpage.action?uuid="+ uuid+"&id="+sendid+"'></iframe>",
											tbar:[
												{
													id : 'djsave',
													icon : "/usm/img/save.gif",
													text : '确定',
													cls : "x-btn-text-icon",
													handler : function(){
												  		  var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addBack");
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
														var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addBack");
														fn.reset();
													}
												}
											]
									});
									addwin.show();
							}
						}
					]
					
	    });
		addwin.show();
		 

	}
function gwjhstartProcess(uuid){
      var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '收文登记',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-30)  + "' height='" + (_height - 50) + "' src='TzlcAction_transSWDJ.action?inceptid="+uuid+"'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '确定',
							cls : "x-btn-text-icon",
							handler : function(){
						  		  var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addZDj");
									fn.submit();
									store.reload();
									alert("登记成功");
									addwin.close();
						      
							}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addZDj");
								fn.reset();
							}
						}
					]
			});
			addwin.show();
		 
}
	
 </script>
</html>
