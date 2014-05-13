<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
    	<script type="text/javascript" src="<%=path%>/desktop/baosongbiaodan/biaodanform.js"></script>
		<script type="text/javascript">

		Ext.onReady(function(){
			
			var record_start = 0;
			
			
			var zt = '<ww:property value="zt"/>';
			//复选框
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex:'messageid' 
			});
	
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
			var urlstr = '/getbsbdlistAction.do?zt='+zt;
			var proxy = new Ext.data.HttpProxy({url:urlstr});
			
			
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
//				title:'报送信息列表列表',
				id:'gridbd',
				autoHeight: true,
//				autoWidth: true,
//			    width:800,
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
	<body style="width: 800">
		<div id="form"></div>
		<div id="grid"></div>

	</body>
</html>