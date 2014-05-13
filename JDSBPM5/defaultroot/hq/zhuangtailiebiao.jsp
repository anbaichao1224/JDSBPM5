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
      //  var activityInstId='<%=activityInstId%>';

	// 列名称
	var cm = new Ext.grid.ColumnModel([ {
	          
				header :'局名',
				dataIndex : 'jm',
				width : 250,
				sortable : true,
				align : 'center',
				sortable : true
			
			}, {
		
				header : ' 操作',
				dataIndex : 'xz',
				width : 150,
				align : 'center',
				sortable : true,
			    renderer: function(value,cellmeta, record){
			      //  alert(wzname);
			      var str = "";
			      var flag = record.get('flag');
			      if(record.get("flag")==0){
			       str = "<a href=\"javascript:void(0);\" onclick=\"quhui('"+value+"');\">取消</a>";
			      }else if(record.get("flag")==1){
			       str = "<a href=\"javascript:void(0);\" onclick=\"quhui('"+value+"');\"></a>";
			      }else if(record.get("flag")==10){
			       str = "<a href=\"javascript:void(0);\" onclick=\"quhui('"+value+"');\">待取消</a>";
			      }else if(record.get("flag")==3){
			       str = "<a href=\"javascript:void(0);\" onclick=\"quhui('"+value+"');\">已退回</a>";
			    
			      }			     
			        return str;
	        	}
			}, {
		
				header : ' 查看',
				dataIndex : 'ck',
				width : 150,
				align : 'center',
				sortable : true,
				 renderer: function(value,cellmeta,record){
			        var hq=record.get("hq");
			      
		
			        var str = "<a href=\"javascript:void(0);\" onclick=\"window.top.ckzt('"+value+"','"+hq+"');\">查看办理状态</a>";	     
			        return str;
	        	}
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : 'listztAction.action?activityinstuuid='+ '<ww:property value="activityinstuuid"></ww:property>'
	});

	// 链接
	 store = new Ext.data.Store({
				// proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalProperty',
							root : 'root'
						}, [{
						         
								   
								   name : 'jm'
								}, {
								    
									name : 'xz'
								}, {
								
									name : 'ck'
								}, {
								
									name : 'hq'
									
								}, {
								
									name : 'flag'
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
		cm : cm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				})
	  /**    tbar:[{
					id : 'djreset',
					icon : "/usm/img/depart.gif",
					text : '补 发',
					cls : "x-btn-text-icon",
			handler : function(){
			     var me = this;
				var ids = '';
				var disabledIds='';
				var _width = 300;
				var _height = Ext.getBody().getHeight()-40;
			
				var wintree = new Ext.Window({
					id :'huiqianDanweiWin',
					title :'选择会签单位',
					closeAction :'close',
					closable :true,
					collapsible :true,
					autoScroll :false,
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
	                html:"<iframe id='orgtreeiframe' name='orgtreeiframe' width='" + (_width-10)  + "' height='" +
	                 (_height - 50) + "' src='deptAction_addtree.action?checked="+ids+"&disabled="+disabledIds+"'></iframe>",
	                 buttons : [
						{
							text :'确定',
							handler : function() {
							
								var retValue = document.getElementById('orgtreeiframe').contentWindow.getCheckedNames();
								
	                            Ext.Ajax.request({
									url : '/HqYewuAction_savebf.action',
	                                params : {
										activityinstuuid : '<ww:property value="activityinstuuid"></ww:property>',
										deptIds : retValue.ids,
										deptname : retValue.names
										
									},
									success : function(o) {
										
											Ext.Msg.alert('信息','补发会签成功!');
								
									},
									failure : function(resp, opts) {
										 
										  Ext.Msg.alert('信息','补发会签失败!'+ resp.responseText);
									}
								    });
						 wintree.close();
						}
						},{
							text :'取消',
							handler : function() {
								wintree.close();
							}
							}
						]
				});
				wintree.show();
					}
		 }]**/
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
//取回已经交办理的
	function quhui(uuid){
      	Ext.Ajax.request({
			url : '/HqYewuAction_quhui.action',
			params : {
				uuid : uuid
			},
			success : function(resp, opts) {
			//	Ext.Msg.alert('信息', '取回成功!');
				store.load({
					params : {
						start : 0,
						limit : 10
					}
				});
	                          
			},
			failure : function(resp, opts) {
			//alert(resp.responseText);
			//	Ext.Msg.alert('信息', '取回失败!');
			}
		});
	}


		</script>
</html>