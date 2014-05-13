<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
	//		String dw=(String)request.getParameter("dw");

	//		String ddw = new String(request.getParameter("dw").getBytes("ISO-8859-1"),"utf-8");

%>
<html>
	<head>
		<base href="<%=basePath%>">
		<script>var context='<%=path%>';</script>


		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/ext/PageSizePlugin.js"></script>
		<script type="text/javascript" src="<%=path%>js/JDS.js"></script>
		<script type="text/javascript" src="<%=path%>/usm2/js/usmall.js"></script>

		<script type="text/javascript">

		Ext.onReady(function(){
			var dwuuid  = '<ww:property value="dwuuid"/>';
			//复选框
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex:'messageid' 
			});
			var record_start = 0;
			//列名称
			var cm = new Ext.grid.ColumnModel([
			    new Ext.grid.RowNumberer({
	//				  header : "序号",
	//				  width : 40,
					  renderer:function(value,metadata,record,rowIndex){
					   return record_start + 1 + rowIndex;
					  }
				 }), 
				sm,
				{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
			    {header:'标题',dataIndex: 'bt',width:100,sortable:true},
			    {header:'上报时间',dataIndex: 'sbsj',width:100,sortable:true},
			    {header:'登记人',dataIndex: 'djr',width:100,sortable:true},
			    {header:'状态',dataIndex: 'zt',width:100,sortable:true,hidden:true},
			    {header:'单位',dataIndex: 'dw',width:100,sortable:true},
			    {header:'详细',dataIndex: 'xiangxi',width:100,sortable:true}   
			]);
			//alert(dw);
			var proxy = new Ext.data.HttpProxy({url:'/getAllBydwAction.do?dwuuid='+dwuuid});
			
			
			//链接
			var store = new Ext.data.Store({
				//proxy: new Ext.data.MemoryProxy(data),
				proxy: proxy,
				reader:new Ext.data.JsonReader({
					totalProperty:'totalCount',
					root:'root'
				},[
					{name:'uuid'},
					{name:'bt'},
					{name:'sbsj'},
					{name:'djr'},
					{name:'zt'},
					{name:'dw'},
					{name:'xiangxi'}
				])
			});

			
			
			var pgsize = 10;
			store.load({params:{start:0,limit:pgsize}});
			//面板
			var grid= new Ext.grid.GridPanel({
				title:'上报信息列表',
				id:'grid',
				autoHeight: true,
				bodyStyle:'width:100%',   
				loadMask:true,
				renderTo: 'grid',
				store:store,
				cm:cm,
				sm:sm,
				bbar: new Ext.PagingToolbar({
					pageSize:pgsize,
					store:store,
					displayInfo:true,
					displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg:"没有记录",
					doLoad : function(start){
		   			record_start = start;
		            var o = {}, pn = this.paramNames;
		            o[pn.start] = start;
		            o[pn.limit] = this.pageSize;
		            this.store.load({params:o});
		     		}
					
				})
				
			});
			

		});
		
	</script>
	</head>
	<body>
		<div id="form"></div>
		<div id="grid"></div>

	</body>
</html>