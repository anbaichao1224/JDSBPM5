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
			    {header:'标号',dataIndex: 'biaohao',width:100,sortable:true},
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
			var grid1= new Ext.grid.GridPanel({
				title:'收文登记',
				id:'grid',
				bodyStyle:'width:100%',   
				autoHeight: true,
				loadMask:true,
				renderTo: 'grid',
				store:store,
				cm:cm,
				sm:sm,
				
				tbar: [{
						text:'增加',
						handler:function(){
							
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
			

		
		
		
		var addformssd = new Ext.form.FormPanel({
			labelAlign: 'left',
			labelWidth:100,
			frame:true,
			title:'form',
			url:'',
			defaultType:'textfield',
			//autoDestroy : false,
			items:[{
				fieldLabel:'规则名称',
				width:200,
				name:'name'
			},{
				fieldLabel:'分数',
				width:200,
				name:'fenshu'
			},{
				fieldLabel:'开始应用时间',
				name:'kaishiyingyongshijian',
				xtype:'datefield',
				format:'Y-m-d',
				width:100
			},{
				fieldLabel:'结束时间',
				name:'jieshushijian',
				xtype:'datefield',
				format:'Y-m-d'
			}],
			buttons:[{
				text:'确 定',
				handler:function(){
					addForm.getForm().submit({
						success:function(form,action){
							win.close();
							successload();
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
		addformssd.render("form");
//		var viewport = new Ext.Viewport({
//			layout:'border',
//			items:[addformssd]
//		});
		
		
		
	});
	</script>
	</head>
	<body>
		<div id="form"></div>
		<div id="grid"></div>

	</body>
</html>