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
		<link rel="stylesheet" type="text/css" href="<%=path%>/js/ext/resources/css/ext-all.css" />  
		
		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext-base.js"></script>
		<script type="text/javascript"
			src="<%=path%>/js/ext/PageSizePlugin.js"></script>
		<script type="text/javascript" src="<%=path%>js/JDS.js"></script>
		<script type="text/javascript" src="<%=path%>/usm2/js/usmall.js"></script>
	<script type="text/javascript" src="<%=path%>/desktop/baosongbiaodan/gridToExcel.js"></script>
		<script type="text/javascript">

		Ext.onReady(function(){
			var record_start = 0;
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
				{header:'单位',dataIndex: 'dw',width:100,sortable:true},<ww:iterator value="gzListBean" status="rowstatus">
				{header:'<ww:property value="name"/>',dataIndex: '<ww:property value="biaohao"/>',sortable:true},	      
			    </ww:iterator> 
			    {header:'分数',dataIndex: 'fenshu',width:100,sortable:true}
			    
			]);
			var urlstr = '/getAllTjAction.do';
			var proxy = new Ext.data.HttpProxy({url:urlstr});
			
			
			//链接
			var store = new Ext.data.Store({
				//proxy: new Ext.data.MemoryProxy(data),
				proxy: proxy,
				reader:new Ext.data.JsonReader({
					totalProperty:'totalCount',
					root:'root'
				},[
					{name:'dw'},<ww:iterator value="gzListBean" status="rowstatus">
					{name:'<ww:property value="biaohao"/>'},	      
				    
			    </ww:iterator> 
			     {name:'fenshu'}
				])
			});

			
			
			var pgsize = 10;
			store.load({params:{start:0,limit:pgsize}});
			//面板
			var grid= new Ext.grid.GridPanel({
				title:'规则列表',
				id:'grid',
				autoHeight: true,
				bodyStyle:'width:100%;', 
				loadMask:true,
				renderTo: 'grid',
				store:store,
				viewConfig: { forceFit:true },  
				cm:cm,
				sm:sm,
//				autoExpandColumn:"dw",
				tbar: [{
						text:'导出Excel',
						tooltip:'导出Excel',              //鼠标停留在按钮上的提示 
                        //iconCls:'exportExcel', 
                        icon : "test/image/vcard.png",   
                        cls : "x-btn-text-icon",  
						handler:function() {
					         downloadViewData(grid);   }

					}]//,
	//			bbar: new Ext.PagingToolbar({
	//				pageSize:pgsize,
	//				store:store,
	//				displayInfo:true,
	//				displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
	//				emptyMsg:"没有记录"
	//			})
				
			});
			 

		});
function downloadViewData(grid){     
  
   try {   
    var xls = new ActiveXObject("Excel.Application");   
   } catch (e) {   
        alert("要打印该表，您必须安装Excel电子表格软件，同时浏览器须使用“ActiveX 控件”，您的浏览器须允许执行控件。 请点击【帮助】了解浏览器设置方法！");   
    return "";   
   }   
   var cm = grid.getColumnModel();   
   var colCount = cm.getColumnCount();   
     
   xls.visible = true; // 设置excel为可见   
   var xlBook = xls.Workbooks.Add;   
   var xlSheet = xlBook.Worksheets(1);   
     
   var temp_obj = [];   
   // 只下载没有隐藏的列(isHidden()为true表示隐藏,其他都为显示)   
   // 临时数组,存放所有当前显示列的下标   
   for (i = 2; i < colCount; i++) {   
	    if (cm.isHidden(i) == true) {   
	    } else {   
	     temp_obj.push(i);   
	    }   
   }   
   for (i = 1; i <= temp_obj.length; i++) {   
	    // 显示列的列标题   
	    //alert(cm.getColumnHeader(temp_obj[i - 1]));   
	    xlSheet.Cells(1, i).Value = cm.getColumnHeader(temp_obj[i - 1]);   
   }   
   var store = grid.getStore();   
   var recordCount = store.getCount();   
   var view = grid.getView();   
   for (i = 1; i <= recordCount; i++) {   
	    for (j = 1; j <= temp_obj.length; j++) {   
	     // EXCEL数据从第二行开始,故row = i + 1;   
	     xlSheet.Cells(i + 1, j).Value = view.getCell(i - 1, temp_obj[j   
	         - 1]).innerText;   
	    }   
   }   
   xlSheet.Columns.AutoFit;   
   xls.ActiveWindow.Zoom = 75   
   xls.UserControl = true; // 很重要,不能省略,不然会出问题 意思是excel交由用户控制   
   xls = null;   
   xlBook = null;   
   xlSheet = null;   
}   
		
	</script>
	</head>
	<body>
		<div id="form"></div>
		<div id="grid"></div>

	</body>
</html>