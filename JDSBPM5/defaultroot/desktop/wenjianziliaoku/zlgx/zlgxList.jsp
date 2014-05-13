<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	request.setCharacterEncoding("UTF-8");

	String catalogUuid = request.getParameter("catalogUuid");
%>
<html>
	<head>
		<title>文件资料列表</title>
		<base href="<%=basePath%>">
		<script>var context='<%=path%>';</script>


		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
	
	</head>
	
	<body>
		<div id="grid"></div>
		<div id="form"></div>
	</body>
</html>

<script type="text/javascript">
		
		
		
	
		
			// 复选框
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});
		
			// 列名称
			var cm = new Ext.grid.ColumnModel([sm,{
				header : 'uuid',
				dataIndex : 'uuid',
				width : 100,
				hidden:true
			}, {
				header : '文号',
				dataIndex : 'meterialWenhao',
				width : 140,
				sortable : true
			}, {
				header : '标题',
				dataIndex : 'meterialTitle',
				width : 300,
				sortable : true
			}, {
				header : '登记人',
				dataIndex : 'creatorName',
				width : 80,
				sortable : true
			}, {
				header : '登记时间',
				dataIndex : 'createDate',
				width : 120,
				sortable : true
			}, {
				header : '最后修改时间',
				dataIndex : 'meterailzhxgsj',
				width : 120,
				sortable : true
			}, {
				header : '编号',
				dataIndex : 'meterialBianhao',
				width : 120,
				sortable : true
			}]);
		//链接（）
		var proxy = new Ext.data.HttpProxy({
			url : 'wjzlMeteriaAction_gxckList.action?catalogUuid=<%=catalogUuid%>'
		});	
		var store = new Ext.data.Store({
			proxy : proxy,
			reader : new Ext.data.JsonReader({
				totalProperty : 'totalProperty',
				root : 'root'
			}, [
			 {
				name : 'meterialWenhao'
			},{
				name : 'meterialTitle'
			}, {
				name : 'creatorName'
			}, {
				name : 'createDate'
			}, {
				name : 'meterailzhxgsj'
			}, {
				name : 'meterialBianhao'
			}, {
				name : 'uuid'
			}])
		});
		
			var pgsize = 24;
			
			var docname = new Ext.form.TextField({
				width : 100,
				name : 'title',
				allowBlank : true
			});
			var wh = new Ext.form.TextField({
				width : 50,
				name : 'wh',
				allowBlank : true
			});
			var bh = new Ext.form.TextField({
				width : 50,
				name : 'bh',
				allowBlank : true
			});
			var djr = new Ext.form.TextField({
				width : 50,
				name : 'djr',
				allowBlank : true
			});
			var zhutici = new Ext.form.TextField({
				width : 50,
				name : 'zhutici',
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
	
	
			// 面板
			var grid = new Ext.grid.GridPanel({
				title : '',
				layout : 'fit',
				id : 'grid',
				bodyStyle : 'width:100%',
				autoHeight : true,
				loadMask : true,
				renderTo : 'grid',
				store : store,
				cm : cm,
				sm : sm,
				bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				}),
				tbar : new Ext.Toolbar(
				['标题:', docname,'-','主题词：', zhutici,'-','文号：', wh,'-','编号：', bh,'-','登记人：', djr,'-', '从 开始日期：',
					rdata, '至 结束日期:', tdata,{
						id : 'newModelButton',
						icon : "/usm/img/search.png",
						text : '查  询',
						cls : "x-btn-text-icon",
						handler : queryZL
					}, '-', {
						icon : '/usm/img/depart.gif',
						text : '重  置',
						cls : "x-btn-text-icon",
						handler : requery
					}
				])
		});
	store.on('beforeload', function() {
				this.baseParams = {
					biaoti : Ext.get('title').dom.value,
					zhutici : Ext.get('zhutici').dom.value,
					djr : Ext.get('djr').dom.value,
					bh : Ext.get('bh').dom.value,
					wh : Ext.get('wh').dom.value,
					from :  Ext.get('from').dom.value,
					to : Ext.get('to').dom.value
					
				};
			});
			store.load({
				params : {
					start : 0,
					limit : pgsize,
					biaoti : Ext.get('title').dom.value,
					zhutici : Ext.get('zhutici').dom.value,
					djr : Ext.get('djr').dom.value,
					bh : Ext.get('bh').dom.value,
					wh : Ext.get('wh').dom.value,
					from : Ext.get('from').dom.value,
					to : Ext.get('to').dom.value
				}
			});
		//模糊查询
		function queryZL() {
			if ('<%=catalogUuid%>' == 'null' || '<%=catalogUuid%>'=='0') {
				alert('请选择要查询的资料所在的目录');
				return;
			}
			store.load({
				params : {
					start : 0,
					limit : pgsize,
					biaoti : Ext.get('title').dom.value,
					zhutici : Ext.get('zhutici').dom.value,
					djr : Ext.get('djr').dom.value,
					bh : Ext.get('bh').dom.value,
					wh : Ext.get('wh').dom.value,
					from : Ext.get('from').dom.value,
					to : Ext.get('to').dom.value
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
	
		// 查询条件重置
		function requery() {
			Ext.get('title').dom.value = '';
			Ext.get('from').dom.value = '';
			Ext.get('to').dom.value = '';
			Ext.get('zhutici').dom.value = '';
			Ext.get('wh').dom.value = '';
			Ext.get('bh').dom.value = '';
			Ext.get('djr').dom.value = '';
			
		}
		
	
	
	//显示修改数据页面的方法
	function chakan(){
		var records = Ext.getCmp('grid').getSelectionModel().getSelections();
		if (records.length == 0) {
			alert("请选择需要修改的资料");
			return;
		} else if (records.length > 1) {
			alert("请进行单项选择");
			return;
		}
		var _record = Ext.getCmp('grid').getSelectionModel().getSelected();
		
		
	    showchakanwin(_record.get("uuid"));
	}
	
	function showchakanwin(uuid){
		var _width = 900;
		var _height = 580;
		
		var chakanwin = new Ext.Window({
			title : '查看文件资料详细信息',
			layout : 'fit',
			width : _width,
			minWidth : 350,
			height : _height,
			minHeight : 200,
			y:0,
			html:"<iframe id='chakaniframe' name='chakaniframe' width='" + (_width-30)  + "' height='" + (_height - 70) + "' src='wjzlMeteriaAction_chakanWjzl.action?uuid="+uuid+"'></iframe>",
			tbar:[{
				id : 'rjsave',
				icon : "/usm/img/save.gif",
				text : '返  回',
				cls : "x-btn-text-icon",
				handler : function(){
					//store.reload();
					chakanwin.close();
				}
			}]
		});
		chakanwin.show();
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
	
	
</script>