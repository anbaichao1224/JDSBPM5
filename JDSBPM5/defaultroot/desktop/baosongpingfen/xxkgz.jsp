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

		<script type="text/javascript">

		Ext.onReady(function(){
			
			//复选框
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex:'messageid' 
			});
	
			//列名称
			var cm = new Ext.grid.ColumnModel([
				sm,
				{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
			    {header:'名称',dataIndex: 'namemc',width:100,sortable:true},
			    {header:'分数',dataIndex: 'fenshu',width:100,sortable:true},
			    {header:'开始应用时间',dataIndex: 'kaishishijian',width:100,sortable:true},
			    {header:'结束时间',dataIndex: 'jieshushijian',width:100,sortable:true},
			    {header:'banbenhao',dataIndex: 'banbenhao',width:100,sortable:true,hidden:true},
			    {header:'简称',dataIndex: 'biaohao',width:100,sortable:true},
			    {header:'详细',dataIndex: 'xiangxi',width:100,sortable:true}   
			]);
			
			var proxy = new Ext.data.HttpProxy({url:'/xxkgzAction.do'});
			
			
			//链接
			var store = new Ext.data.Store({
				//proxy: new Ext.data.MemoryProxy(data),
				proxy: proxy,
				reader:new Ext.data.JsonReader({
					totalProperty:'totalCount',
					root:'root'
				},[
					{name:'uuid'},
					{name:'namemc'},
					{name:'fenshu'},
					{name:'kaishishijian'},
					{name:'jieshushijian'},
					{name:'banbenhao'},
					{name:'biaohao'},
					{name:'xiangxi'}
				])
			});

			
			
			var pgsize = 10;
			store.load({params:{start:0,limit:pgsize}});
			//面板
			var grid= new Ext.grid.GridPanel({
				title:'规则列表',
				id:'grid',
				autoHeight: true,
				 bodyStyle:'width:100%',   
				loadMask:true,
				renderTo: 'grid',
				store:store,
				cm:cm,
				sm:sm,
				
				tbar: [{
						text:'增加',
						handler:function(){
							window.top.addgz();
						}
					}],
				bbar: new Ext.PagingToolbar({
					pageSize:pgsize,
					store:store,
					displayInfo:true,
					displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg:"没有记录"
				})
				
			});
			
		});
		
		function testahref(){

			//alert("go testaherf");
			
			
			var addformssd = new Ext.form.FormPanel({
				labelAlign: 'left',
				labelWidth:100,
				height:200,
				region:'center',
				frame:true,
				title:'最新规则',
				url:'xxkgzSaveAndUpdateAction.do',
				defaultType:'textfield',
				autoDestroy : false,
				viewConfig:{
					forceFit:true
				},
				items:[
				{
					xtype:'hidden',
					name:'biaohao'
				},
				{
					xtype:'hidden',
					name:'uuid'
				},
				{
					xtype:'hidden',
					name:'banbenhao'
				},
				{
					fieldLabel:'规则名称',
					width:200,
					name:'name'
				},{
					fieldLabel:'分数',
					width:200,
					name:'fenshu'
				},{
					fieldLabel:'开始应用时间',
					name:'kaishishijian',
					xtype:'datefield',
					format:'Y-m-d',
					width:200
				},{
					fieldLabel:'结束时间',
					name:'jieshushijian',
					xtype:'datefield',
					format:'Y-m-d',
					width:200
				}],
				buttons:[{
					text:'确 定',
					handler:function(){
					addformssd.getForm().submit({
							success:function(form,action){
								//win.close();
								//successload();
								Ext.Msg.alert('信息','登记信息添加成功!');
							},
							failure:function(){
								Ext.Msg.alert('信息','操作失败!');
							}
						});
					}
				},{
					text:'重 置',
					handler:function(){
						//reform();
					}
				}]
			});
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex:'messageid' 
			});
			
			//列名称
			var cm = new Ext.grid.ColumnModel([
				sm,
				{header:'uuid',dataIndex: 'uuid',width:100,sortable:true},
			    {header:'名称',dataIndex: 'namemc',width:100,sortable:true},
			    {header:'分数',dataIndex: 'fenshu',width:100,sortable:true},
			    {header:'开始应用时间',dataIndex: 'kaishishijian',width:100,sortable:true},
			    {header:'结束时间',dataIndex: 'jieshushijian',width:100,sortable:true},
			    {header:'banbenhao',dataIndex: 'banbenhao',width:100,sortable:true},
			    {header:'biaohao',dataIndex: 'biaohao',width:100,sortable:true},
			    {header:'详细',dataIndex: 'xiangxi',width:100,sortable:true}   
			]);
			var _record = Ext.getCmp('grid').getSelectionModel().getSelected();
//			alert(_record);
			var uuid_s = _record.get('uuid');
			var biaohao_s = _record.get('biaohao');
			var proxy = new Ext.data.HttpProxy({url:'/xxkgzInfolistAction.do?uuid='+uuid_s+'&biaohao='+biaohao_s});
			
			
			//链接
			var store = new Ext.data.Store({
				//proxy: new Ext.data.MemoryProxy(data),
				proxy: proxy,
				reader:new Ext.data.JsonReader({
					totalProperty:'totalCount',
					root:'root'
				},[
					{name:'uuid'},
					{name:'namemc'},
					{name:'fenshu'},
					{name:'kaishishijian'},
					{name:'jieshushijian'},
					{name:'banbenhao'},
					{name:'biaohao'},
					{name:'xiangxi'}
				])
			});

			
			
			var pgsize = 10;
			store.load({params:{start:0,limit:pgsize}});
			//面板
			var grid1= new Ext.grid.GridPanel({
				title:'规则历史记录',
				id:'grid1',
//				autoHeight: true,
			 	height:550,
				loadMask:true,
				region:'south',
//				renderTo: 'grid',
				store:store,
				cm:cm,
				sm:sm,
				bbar: new Ext.PagingToolbar({
					pageSize:pgsize,
					store:store,
					displayInfo:true,
					displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg:"没有记录"
				})
				
			});			
			
			//弹出的窗口
			var win = new Ext.Window({
				title:'评分规则详细信息',
				layout:'border',
				width:1000,
//				minWidth:350,
				height:800,
//				minHeight:200,
				items:[addformssd,grid1]
			});
			win.show();
			setform();
		}
		function setform(){
//			alert("go setform");
			var _record = Ext.getCmp('grid').getSelectionModel().getSelected();
//			alert(_record);
			var namemc = _record.get('namemc');
			var kaishishijian = _record.get('kaishishijian');
			var jieshushijian = _record.get('jieshushijian');
			var fenshu = _record.get('fenshu');
			var biaohao = _record.get('biaohao');
			var banbenhao = _record.get('banbenhao');
			var uuid = _record.get('uuid');
			Ext.get('name').dom.value = namemc;
			Ext.get('kaishishijian').dom.value = kaishishijian;
			Ext.get('jieshushijian').dom.value = jieshushijian;
			Ext.get('fenshu').dom.value = fenshu;
			Ext.get('banbenhao').dom.value = banbenhao;
			Ext.get('biaohao').dom.value=biaohao;
			Ext.get('uuid').dom.value=uuid;
		}
		
		
	</script>
	</head>
	<body>
		<div id="form"></div>
		<div id="grid"></div>

	</body>
</html>