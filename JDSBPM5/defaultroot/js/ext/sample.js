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
            title: '������',
            iconCls: 'user',
            toolItems: [{
                text:'����',
                iconCls:'settings',
                scope:this
            },'-',{
                text:'�˳�',
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
            text: '���칫��',
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
                title:'���칫��',
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
                             data:  [['�칫�ҷ���','�칫��','����','��ף��','9/1 12:00am', 'Manufacturing']
				                      ,['�ڲ�����','�칫��','����','��ף��','9/1 12:00am', 'Manufacturing']
				                      ,['�ڲ�֪ͨ','�칫��','����','��ף��','9/1 12:00am', 'Manufacturing']
				                      ,['�ڲ�����','�칫��','����','��ף��','9/1 12:00am', 'Manufacturing']
				                     ]
                        }),
                       cm: new Ext.grid.ColumnModel([
                                sm,
                               {id:'company20',header: "��������", width: 40, sortable: true, dataIndex: 'company'},
                               {header: "���Ĳ���", width: 20, sortable: true},
                               {header: "������", width: 20, sortable: true, dataIndex: 'change'},
                              {header: "������", width: 20, sortable: true, dataIndex: 'pctChange'},
                              {header: "����ʱ��", width: 20, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'lastChange'}
                               ]),
						
                        viewConfig: {
                            forceFit:true
                        },
                        //autoExpandColumn:'company',

                        tbar:[{
                            text:'��ݹ���',
                            tooltip:'��ݹ���',
                            iconCls:'add'
                        }, '-', {
                            text:'�ظ�����',
                            tooltip:'�ظ�����',
                            iconCls:'option'
                        },'-',{
                            text:'ɾ������',
                            tooltip:'ɾ������',
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
            text: '�����շ�',
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
                title:'�����շ�',
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
                            title: '�ڲ�����',
                            header:false,
                            html : '<p>Something useful would be in here.</p>',
                            items:grid1,
							border:false
                        },{
                            title: '�ڲ�����',
                            header:false,
                            html : '<p>Something useful would be in here.</p>',
                            items:grid5,
							border:false
                        },{
                            title: '�ⲿ����',
                            header:false,
							
                            html : '<p>Something useful would be in here.</p>',
							items:grid6,
                            border:false
                        },{
                            title: '�ⲿ����',
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
            text: '��ʱ��ͨ',
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
                title: '��ʱ��ͨ',
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
                        title: '������Ա',
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
                            text:'������Ա',
                            children:[{
                                text:'��������Ա',
                                expanded:true,
                                children:[{
                                    text:'������',
                                    iconCls:'user',
                                    leaf:true
                                },{
                                    text:'��ף��',
                                    iconCls:'user',
                                    leaf:true
                                },{
                                    text:'Ф����',
                                    iconCls:'user',
                                    leaf:true
                                },{
                                    text:'����',
                                    iconCls:'user',
                                    leaf:true
                                },{
                                    text:'���',
                                    iconCls:'user',
                                    leaf:true
                                }]
                            },{
                                text:'�ⲿ����Ա',
                                expanded:true,
                                children:[{
                                    text:'�Ի�',
                                    iconCls:'user-girl',
                                    leaf:true
                                },{
                                    text:'��Ӣ',
                                    iconCls:'user-girl',
                                    leaf:true
                                },{
                                    text:'����',
                                    iconCls:'user-kid',
                                    leaf:true
                                },{
                                    text:'����',
                                    iconCls:'user-kid',
                                    leaf:true
                                }]
                            }]
                        })
                    }), {
                        title: '�ֻ�����',
                        html:'<p>Something useful would be in here.</p>',
                        autoScroll:true
                    },{
                        title: '�ļ�����',
                        html : '<p>Something useful would be in here.</p>'
                    },{
                        title: 'IP�绰',
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
            text: '��Ϣ����',
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
                            text:'��Ϣ�ɼ�',
                            tooltip:'��Ϣ�ɼ�',
                            iconCls:'add'
                        }, '-', {
                            text:'��Ϣ����',
                            tooltip:'��Ϣ����',
                            iconCls:'option'
                        },'-',{
                            text:'ɾ����Ϣ',
                            tooltip:'ɾ����Ϣ',
                            iconCls:'remove'
                        }],
                id: 'bogus'+src.windowId,
                title:'��Ϣ����',
                width:1000,
                height:600,
                html : '<p>���������Ϣ����ϵͳ��ʾ</p>',
                iconCls: 'bogus',
                shim:false,
                animCollapse:false,
                constrainHeader:true,
				layout:'border',
				  items:[{
            region:'west',
            id:'west-panel',
            title:'��Ŀ�б�',
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
                title:'��Ŀ�б�',
                autoScroll:true,
                border:false,
                iconCls:'nav'
            },{
                title:'��Ϣ����',
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
                    title: '֪ͨͨ��',
                    layout:'fit',
                    tools: tools,
					html:'��������λ�����ظ����ң���������������ת�������������������칫������ӡ��2007����˱����ۺϲ������ڼ䱱���л�����ͨ���ϲ��Է�����֪ͨ���ļ�Ҫ���־��漰�Ҿ��й�����֪ͨ���£�'
                    //items: new SampleGrid([0, 2, 3])
                },{
                    title: '�ڲ�����',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                }]
            },{
                columnWidth:.33,
                style:'padding:10px 0 10px 10px',
                items:[{
                    title: '���ӹ���',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                },{
                    title: '���ҹ�����̬',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                }]
            },{
                columnWidth:.33,
                style:'padding:10px',
                items:[{
                    title: '��ʾ��',
                    tools: tools,
                    html: Ext.example.shortBogusMarkup
                },{
                    title: '��ʾ��',
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
            text: '��Ϣ����',
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
                            text:'��Ϣ�ɼ�',
                            tooltip:'��Ϣ�ɼ�',
                            iconCls:'add'
                        }, '-', {
                            text:'��Ϣ����',
                            tooltip:'��Ϣ����',
                            iconCls:'option'
                        },'-',{
                            text:'ɾ����Ϣ',
                            tooltip:'ɾ����Ϣ',
                            iconCls:'remove'
                        }],
                id: 'bogus'+src.windowId,
                title:'��Ϣ����',
                width:1000,
                height:600,
                html : '<p>���������Ϣ����ϵͳ��ʾ</p>',
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
            text: '��������',
            iconCls: 'bogus',
            handler: function() {
				return false;
			},
            menu: {
                items:[{
                    text: '��Ϣ����',
                    iconCls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: 'ӡ��ʹ������',
                    iconCls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: '�̶��ʲ�����',
                    iconCls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: '������ʹ������',
                    iconCls:'bogus',
                    handler : this.createWindow,
                    scope: this,
                    windowId: windowIndex
                    },{
                    text: '����ʹ������',
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
