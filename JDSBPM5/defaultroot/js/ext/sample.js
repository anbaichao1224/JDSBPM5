/*
 * Ext JS Library 2.0
 * Copyright(c) 2006-2007, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */


// Sample desktop configuration
MyDesktop = new Ext.app.App({
	
	init :function(){
		Ext.QuickTips.init();
	},
	getModules : function(){
		return [
			new MyDesktop.GridWindow(),
            new MyDesktop.TabWindow(),
            new MyDesktop.AccordionWindow(),
            new MyDesktop.BogusMenuModule(),
            new MyDesktop.BogusModule()
		];
	},

    // config for the start menu
    getStartConfig : function(){
        return {
            title: '李文章',
            iconCls: 'user',
            toolItems: [{
                text:'设置',
                iconCls:'settings',
                scope:this
            },'-',{
                text:'退出',
                iconCls:'logout',
                scope:this
            }]
        };
    }
});

   var tools = [{
        id:'gear',
        handler: function(){
            Ext.Msg.alert('Message', 'The Settings tool was clicked.');
        }
    },{
        id:'close',
        handler: function(e, target, panel){
            panel.ownerCt.remove(panel, true);
        }
    }];


/*
 * Example windows
 */
MyDesktop.GridWindow = Ext.extend(Ext.app.Module, {
    id:'grid-win',
    init : function(){
        this.launcher = {
            text: '待办公文',
            iconCls:'icon-grid',
            handler : this.createWindow,
            scope: this
        }
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('grid-win');
		 var sm = new  Ext.grid.CheckboxSelectionModel();
        if(!win){
            win = desktop.createWindow({
                id: 'grid-win',
                title:'待办公文',
                width:500,
                height:300,
                iconCls: 'icon-grid',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items:
                    new Ext.grid.GridPanel({
                        border:false,
                        ds: new Ext.data.Store({
                            reader: new Ext.data.ArrayReader({}, [
                               {name: 'company'},
                               {name: 'price'},
                               {name: 'change'},
                               {name: 'pctChange'},
                               {name: 'lastChange', type: 'date', dateFormat: 'n/j h:ia'},
                               {name: 'industry'},
                               {name: 'desc'}
                            ]),
                             data:  [['办公室发文','办公室','文章','霍祝云','9/1 12:00am', 'Manufacturing']
				                      ,['内部办文','办公室','文章','霍祝云','9/1 12:00am', 'Manufacturing']
				                      ,['内部通知','办公室','文章','霍祝云','9/1 12:00am', 'Manufacturing']
				                      ,['内部办文','办公室','文章','霍祝云','9/1 12:00am', 'Manufacturing']
				                     ]
                        }),
                       cm: new Ext.grid.ColumnModel([
                                sm,
                               {id:'company20',header: "流程名称", width: 40, sortable: true, dataIndex: 'company'},
                               {header: "来文部门", width: 20, sortable: true},
                               {header: "发文人", width: 20, sortable: true, dataIndex: 'change'},
                              {header: "办理人", width: 20, sortable: true, dataIndex: 'pctChange'},
                              {header: "发文时间", width: 20, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'lastChange'}
                               ]),
						
                        viewConfig: {
                            forceFit:true
                        },
                        //autoExpandColumn:'company',

                        tbar:[{
                            text:'起草公文',
                            tooltip:'起草公文',
                            iconCls:'add'
                        }, '-', {
                            text:'回复公文',
                            tooltip:'回复公文',
                            iconCls:'option'
                        },'-',{
                            text:'删除公文',
                            tooltip:'删除公文',
                            iconCls:'remove'
                        }]
                    })
            });
        }
        win.show();
    }
});



MyDesktop.TabWindow = Ext.extend(Ext.app.Module, {
    id:'tab-win',
    init : function(){
        this.launcher = {
            text: '公文收发',
            iconCls:'tabs',
            handler : this.createWindow,
            scope: this
        }
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('tab-win');
        if(!win){
            win = desktop.createWindow({
                id: 'tab-win',
                title:'公文收发',
                width:620,
                height:400,
                iconCls: 'tabs',
                shim:false,
                animCollapse:false,
                border:false,
                constrainHeader:true,

                layout: 'fit',
                items:
                    new Ext.TabPanel({
                        activeTab:0,

                        items: [{
                            title: '内部来文',
                            header:false,
                            html : '<p>Something useful would be in here.</p>',
                            items:grid1,
							border:false
                        },{
                            title: '内部发文',
                            header:false,
                            html : '<p>Something useful would be in here.</p>',
                            items:grid5,
							border:false
                        },{
                            title: '外部来文',
                            header:false,
							
                            html : '<p>Something useful would be in here.</p>',
							items:grid6,
                            border:false
                        },{
                            title: '外部发文',
                            header:false,
							
                            html : '<p>Something useful would be in here.</p>',
							items:grid7,
                            border:false
                        }]
                    })
            });
        }
        win.show();
    }
});



MyDesktop.AccordionWindow = Ext.extend(Ext.app.Module, {
    id:'acc-win',
    init : function(){
        this.launcher = {
            text: '即时沟通',
            iconCls:'accordion',
            handler : this.createWindow,
            scope: this
        }
    },

    createWindow : function(){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('acc-win');
        if(!win){
            win = desktop.createWindow({
                id: 'acc-win',
                title: '即时沟通',
                width:250,
                height:400,
                iconCls: 'accordion',
                shim:false,
                animCollapse:false,
                constrainHeader:true,

                tbar:[{
                    tooltip:{title:'Rich Tooltips', text:'Let your users know what they can do!'},
                    iconCls:'connect'
                },'-',{
                    tooltip:'Add a new user',
                    iconCls:'user-add'
                },' ',{
                    tooltip:'Remove the selected user',
                    iconCls:'user-delete'
                }],

                layout:'accordion',
                border:false,
                layoutConfig: {
                    animate:false
                },

                items: [
                    new Ext.tree.TreePanel({
                        id:'im-tree',
                        title: '在线人员',
                        loader: new Ext.tree.TreeLoader(),
                        rootVisible:false,
                        lines:false,
                        autoScroll:true,
                        tools:[{
                            id:'refresh',
                            on:{
                                click: function(){
                                    var tree = Ext.getCmp('im-tree');
                                    tree.body.mask('Loading', 'x-mask-loading');
                                    tree.root.reload();
                                    tree.root.collapse(true, false);
                                    setTimeout(function(){ // mimic a server call
                                        tree.body.unmask();
                                        tree.root.expand(true, true);
                                    }, 1000);
                                }
                            }
                        }],
                        root: new Ext.tree.AsyncTreeNode({
                            text:'在线人员',
                            children:[{
                                text:'本部门人员',
                                expanded:true,
                                children:[{
                                    text:'李文章',
                                    iconCls:'user',
                                    leaf:true
                                },{
                                    text:'霍祝云',
                                    iconCls:'user',
                                    leaf:true
                                },{
                                    text:'肖天翔',
                                    iconCls:'user',
                                    leaf:true
                                },{
                                    text:'高扬',
                                    iconCls:'user',
                                    leaf:true
                                },{
                                    text:'徐宏',
                                    iconCls:'user',
                                    leaf:true
                                }]
                            },{
                                text:'外部门人员',
                                expanded:true,
                                children:[{
                                    text:'赵华',
                                    iconCls:'user-girl',
                                    leaf:true
                                },{
                                    text:'陈英',
                                    iconCls:'user-girl',
                                    leaf:true
                                },{
                                    text:'廖林',
                                    iconCls:'user-kid',
                                    leaf:true
                                },{
                                    text:'邓生',
                                    iconCls:'user-kid',
                                    leaf:true
                                }]
                            }]
                        })
                    }), {
                        title: '手机短信',
                        html:'<p>Something useful would be in here.</p>',
                        autoScroll:true
                    },{
                        title: '文件传输',
                        html : '<p>Something useful would be in here.</p>'
                    },{
                        title: 'IP电话',
                        html : '<p>Something useful would be in here.</p>'
                    }
                ]
            });
        }
        win.show();
    }
});

// for example purposes
var windowIndex = 0;

MyDesktop.BogusModule = Ext.extend(Ext.app.Module, {
    init : function(){
        this.launcher = {
            text: '信息发布',
            iconCls:'bogus',
            handler : this.createWindow,
            scope: this,
            windowId:windowIndex
        }
    },

    createWindow : function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus'+src.windowId);
        if(!win){
            win = desktop.createWindow({
				  tbar:[{
                            text:'信息采集',
                            tooltip:'信息采集',
                            iconCls:'add'
                        }, '-', {
                            text:'信息管理',
                            tooltip:'信息管理',
                            iconCls:'option'
                        },'-',{
                            text:'删除信息',
                            tooltip:'删除信息',
                            iconCls:'remove'
                        }],
                id: 'bogus'+src.windowId,
                title:'信息发布',
                width:1000,
                height:600,
                html : '<p>天诚永信信息发布系统演示</p>',
                iconCls: 'bogus',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
				layout:'border',
				  items:[{
            region:'west',
            id:'west-panel',
            title:'栏目列表',
            split:true,
            width: 200,
            minSize: 175,
            maxSize: 400,
            collapsible: true,
            margins:'35 0 5 5',
            cmargins:'35 5 5 5',
            layout:'accordion',
            layoutConfig:{
                animate:true
            },
            items: [{
               html: Ext.example.shortBogusMarkup, 
                title:'栏目列表',
                autoScroll:true,
                border:false,
                iconCls:'nav'
            },{
                title:'信息分类',
                html: Ext.example.shortBogusMarkup,
                border:false,
                autoScroll:true,
                iconCls:'settings'
            }]
        },{
            xtype:'portal',
            region:'center',
            margins:'35 5 5 0',
            items:[{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: '通知通告',
                    layout:'fit',
                    tools: tools,
					html:'局属各单位、机关各科室：按照宣武区政府转发《北京市人民政府办公厅关于印发2007年好运北京综合测试赛期间北京市环境交通保障测试方案的通知》文件要求，现就涉及我局有关事项通知如下：'
                    //items: new SampleGrid([0, 2, 3])
                },{
                    title: '内部刊物',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                }]
            },{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: '电子公告',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                },{
                    title: '科室工作动态',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                }]
            },{
                columnWidth:.33,
                style:'padding:10px',
                items:[{
                    title: '公示栏',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                },{
                    title: '公示栏',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                }]
            }]
        }]
            });
        }
        win.show();
    }
});


var windowIndex = 0;

MyDesktop.BogusModuletest = Ext.extend(Ext.app.Module, {
    init : function(){
        this.launcher = {
            text: '信息发布',
            iconCls:'bogus',
            handler : this.createWindow,
            scope: this,
            windowId:windowIndex
        }
    },

    createWindow : function(src){
        var desktop = this.app.getDesktop();
        var win = desktop.getWindow('bogus'+src.windowId);
        if(!win){
            win = desktop.createWindow({
				  tbar:[{
                            text:'信息采集',
                            tooltip:'信息采集',
                            iconCls:'add'
                        }, '-', {
                            text:'信息管理',
                            tooltip:'信息管理',
                            iconCls:'option'
                        },'-',{
                            text:'删除信息',
                            tooltip:'删除信息',
                            iconCls:'remove'
                        }],
                id: 'bogus'+src.windowId,
                title:'信息发布',
                width:1000,
                height:600,
                html : '<p>天诚永信信息发布系统演示</p>',
                iconCls: 'bogus',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
				layout:'border',
				  items:Ext.get('bgyp')
            });
        }
        win.show();
    }
});



MyDesktop.BogusMenuModule = Ext.extend(MyDesktop.BogusModuletest, {
    init : function(){
        this.launcher = {
            text: '事务审批',
            iconCls: 'bogus',
            handler: function() {
				return false;
			},
            menu: {
                items:[{
                    text: '信息审批',
                    iconCls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: '印章使用申请',
                    iconCls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: '固定资产申请',
                    iconCls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: '会议室使用申请',
                    iconCls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: '车辆使用申请',
                    iconCls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                }]
            }
        }
    }
});


// Array data for the grid
Ext.grid.dummyData = [
    ['3m Co',71.72,0.02,0.03,'9/1 12:00am'],
    ['Alcoa Inc',29.01,0.42,1.47,'9/1 12:00am'],
    ['American Express Company',52.55,0.01,0.02,'9/1 12:00am'],
    ['American International Group, Inc.',64.13,0.31,0.49,'9/1 12:00am'],
    ['AT&T Inc.',31.61,-0.48,-1.54,'9/1 12:00am'],
    ['Caterpillar Inc.',67.27,0.92,1.39,'9/1 12:00am'],
    ['Citigroup, Inc.',49.37,0.02,0.04,'9/1 12:00am'],
    ['Exxon Mobil Corp',68.1,-0.43,-0.64,'9/1 12:00am'],
    ['General Electric Company',34.14,-0.08,-0.23,'9/1 12:00am'],
    ['General Motors Corporation',30.27,1.09,3.74,'9/1 12:00am'],
    ['Hewlett-Packard Co.',36.53,-0.03,-0.08,'9/1 12:00am'],
    ['Honeywell Intl Inc',38.77,0.05,0.13,'9/1 12:00am'],
    ['Intel Corporation',19.88,0.31,1.58,'9/1 12:00am'],
    ['Johnson & Johnson',64.72,0.06,0.09,'9/1 12:00am'],
    ['Merck & Co., Inc.',40.96,0.41,1.01,'9/1 12:00am'],
    ['Microsoft Corporation',25.84,0.14,0.54,'9/1 12:00am'],
    ['The Coca-Cola Company',45.07,0.26,0.58,'9/1 12:00am'],
    ['The Procter & Gamble Company',61.91,0.01,0.02,'9/1 12:00am'],
    ['Wal-Mart Stores, Inc.',45.45,0.73,1.63,'9/1 12:00am'],
    ['Walt Disney Company (The) (Holding Company)',29.89,0.24,0.81,'9/1 12:00am']
];
