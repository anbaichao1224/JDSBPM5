<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String contextpath = request.getContextPath() + "/";
String tid = request.getParameter("uuid");
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
    var tid='<%=tid%>';
    </script>
  </head>
  
  <body>
      <div id="grid"> 
       </div>
  </body>
   <script text="text/javascript">
   var store;
   Ext.onReady(function() {



	// 列名称
	var cm = new Ext.grid.ColumnModel([
	        {
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				hidden:true
			}, {
				header : '未收单位',
				dataIndex : 'WSDW',
				width : 150,
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
			      }			     
			        return str;
	        	}
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : '/HqYewuAction_swwsdept.action?tid='+tid
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
								}, {
								
									name:'WSDW'
								}, {
								    name : 'xz'
							    }, {
									name : 'flag'
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
		store : store,
		cm : cm,
		tbar : new Ext.Toolbar([
			 {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
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
	


});
function successload() {
	store.load({
				params : {
					start : 0,
					limit : 15
				}
			});
	}
	
	//取回已经会签的
	function quhui(uuid){
      	Ext.Ajax.request({
			url : '/HqYewuAction_swquhui.action',
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
