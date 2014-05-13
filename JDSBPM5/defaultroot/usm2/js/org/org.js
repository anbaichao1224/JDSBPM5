
/**
 * 创建树
 */
com_client_view_OrgTreePanel = {};

var createTree = function(){
	this.com_client_view_OrgTreePanel = new Ext.tree.TreePanel({
	   title : '部门列表',
	   lines : true,
	   border : false,
	   checkModel : 'single',       //树勾选模式:'multiple':多选; 'single':单选; 'cascade':级联多选 
	   onlyLeafCheckable : false,   			     //是否只有树叶可勾选:false,节点可勾选;true:节点不可勾选
	   animate : false,             				 //动画效果
	   rootVisible : true,        				     //根节点是否显示
	   autoScroll : true,            				 //滚动条自动能力
	   loader : new Ext.tree.TreeLoader({
					dataUrl : 'http://localhost:8181/org/orgActionData.action',
					baseAttrs : {uiProvider: Ext.tree.TreeCheckNodeUI}
		}),
	   root : new Ext.tree.AsyncTreeNode({
					id:"0",
					text:'部门列表',
					type:'0',
					expanded:true
		})
	});
	return this.com_client_view_OrgTreePanel;
}
com_client_view_OrgTabPanel = {};

/**
 * 创建表格
 * @return {}
 */
var createTabPanel = function(){
    this.com_client_view_OrgTabPanel = new Ext.TabPanel({
        //width:900,
        height:500,
        deferredRender:false,
        activeTab:0,
        iconCls: 'tabs',
        enableTabScroll:true,
        items:[
			 	{
					title : '基本信息',
					items : [
						createFormPanel()
					]
        		}
        		,
			 	{
					title : '子部门列表',
					items : [
						createGridPanel()
					]
        		}
        		
        ],
        plugins: new Ext.ux.TabCloseMenu()
    });
    return this.com_client_view_OrgTabPanel;
}

var com_client_view_OrgInfoBean= {};
/**
  * 创建FormPanel
  */
var createFormPanel = function(){
	this.com_client_view_OrgInfoBean = new Ext.form.FormPanel(
		{
			height:400,
			bodyStyle : 'padding:5 5 5 5',
			labelAlign : 'left',
			frame : true,
			items:[
						cnName = new Ext.form.TextField({
								xType : 'TextField',
								fieldLabel : '中文名称',
								width:300,
								id:'cnName',
								name:'cnName',
								allowBlank : true,
								validateOnBlur : true
	        			})
	        		,
						enName = new Ext.form.TextField({
								xType : 'TextField',
								fieldLabel : '英文名称',
								width:300,
								id:'enName',
								name:'enName',
								allowBlank : true,
								validateOnBlur : true
	        			})
	        		,
						address = new Ext.form.TextField({
								xType : 'TextField',
								fieldLabel : '地址',
								width:300,
								id:'address',
								name:'address',
								allowBlank : true,
								validateOnBlur : true
	        			})
	        		,
						url = new Ext.form.TextField({
								xType : 'TextField',
								fieldLabel : '部门主页',
								width:300,
								id:'url',
								name:'url',
								allowBlank : true,
								validateOnBlur : true
	        			})
	        		
			],
			buttons : [
				new Ext.Button(
					{
						text : '确定'
					}
				),
				new Ext.Button(
					{
						text : '取消'
					}
				)
			]
		}
	);
	return this.com_client_view_OrgInfoBean;
}

;
com_client_view_OrgChildList = {};

//配置表格列
var createGridPanel = function(){
	this.com_client_view_OrgChildList = new Ext.grid.GridPanel({
		tbar  : new Ext.Toolbar({
				items:[		
							'ID：',
							new Ext.form.TextField({
								xtype:'TextField',
								fieldLabel : 'ID',
								width:150,
								id:'ID',
								name:'ID'
			    			})
			    		
			    		,
			    								'英文名称：',
							new Ext.form.TextField({
								xtype:'TextField',
								fieldLabel : '英文名称',
								width:150,
								id:'enName',
								name:'enName'
			    			})
			    		
			    		,
			    								'中文名称：',
							new Ext.form.TextField({
								xtype:'TextField',
								fieldLabel : '中文名称',
								width:150,
								id:'cnName',
								name:'cnName'
			    			})
			    		
			    		
			    	,
			    	new Ext.Button({
		    			id : 'queryButton',
						text : '查询',
						iconCls:'zoom',
						handler:function(){}
			    	})
				]		
			}),
		title : '子部门列表',
		frame : true,
		store : new Ext.data.Store({
					proxy : new Ext.data.HttpProxy({
						url:'http://localhost:8181/org/orgActionGridData.action'
					}),
					reader : new Ext.data.ArrayReader(
						{id:0},
						Ext.data.Record.create([
							 	{
									name : 'ID',
									mapping : 0
								}
								,
							 	{
									name : 'enName',
									mapping : 1
								}
								,
							 	{
									name : 'cnName',
									mapping : 2
								}
								
						])
						),
					autoLoad : {}/*必须项,否则不会加载数据*/
				}),
		columns : [	
				 	{
						header : 'ID',
						width: 50,
						dataIndex : 'ID',
						sortable : true
	        		}
	        		,
				 	{
						header : '英文名称',
						width: 100,
						dataIndex : 'enName',
						sortable : true
	        		}
	        		,
				 	{
						header : '中文名称',
						width: 200,
						dataIndex : 'cnName',
						sortable : true
	        		}
	        		
			],
		bbar : new Ext.Toolbar({
				items : [{
								text : '修改',
								iconCls:'edit',
								handler : function(){
									
								}
						  },
						  {
								text : '删除',
								iconCls:'remove',
								handler : function(){
									
								}
						   }]
				})
	});
	return this.com_client_view_OrgChildList;
}
/**
 * 创建布局
 */
var createViewport = function(){
	var viewport = new Ext.Viewport({
		title:'组织机构管理',
		enableTabScroll:true,
		layout:'border',
		items:[
			 	{
        		width:150,
        		height: 300,
        		region:'west',
        		items:[
					createTree()
        		]
        		}
        		,
			 	{
        		width:300,
        		height: 300,
        		region:'center',
        		items:[
					createTabPanel()
        		]
        		}
        		
		]
	});
}

var createLeftPanel = function(){
	
}

Ext.onReady(function(){
	//方法需写活
	//createTree();
	createFormPanel();
	//createGridPanel();
	//createTabPanel();
	createViewport();
	
});