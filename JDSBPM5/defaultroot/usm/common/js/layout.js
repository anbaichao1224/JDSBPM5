function initLayout()
{
    //Ext.state.Manager.setProvider(new Ext.state.CookieProvider());

    var viewport = new Ext.Viewport({
        layout:'border',
        items:[
            {
            region:'west',
            id:'west-panel',
            title:'工具',
            split:true,
            width: 200,
            minSize: 175,
            maxSize: 400,
            collapsible: true,
            margins:'0 0 0 5',
            layout:'accordion',
            layoutConfig:{
                animate:true
            },
            items: [{
                contentEl: 'leftpanel',
                title:'级次关系',
                border:false,
                autoScroll:true,
                iconCls:'nav',
                html:'<div id="usmtree"></div>'
            }]
        },
            new Ext.TabPanel({
                region:'center',
                deferredRender:false,
                activeTab:0,
                items:[{
                    contentEl:'wookbook',
                    id:'workbookpanel',
                    title: '工作区',                    
                    autoScroll:true,
                    //autoWidth:true,
                    //autoHeight:true,
                    html:'<div id="worksheet">'
                }]
            })
        ]
    });
}