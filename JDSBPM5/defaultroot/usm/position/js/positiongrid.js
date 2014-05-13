/*
 * Ext JS Library 2.0.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */


Ext.onReady(function(){

    Ext.QuickTips.init();
    
    var xg = Ext.grid;

    // shared reader
     var reader = new Ext.data.ArrayReader({}, [
       {name: 'CnName'},
       {name: 'PositionDesc'},
       {name: 'PositionTabIndex'},
       {name: 'CreateTime', type: 'date', dateFormat: 'n/j h:ia'},
       {name: 'DeptGuid'},
       {name: 'choose'},
       {name: 'desc'}
    ]);


   
   var store = new Ext.data.Store({
        // load using script tags for cross domain, if the data in on the same domain as
        // this page, an HttpProxy would be better
        proxy: new Ext.data.ScriptTagProxy({
            url: 'http://extjs.com/forum/topics-browse-remote.php'
        }),

        // create reader that reads the Topic records
        reader: new Ext.data.JsonReader({
            root: 'topics',
            totalProperty: 'totalCount',
            id: 'threadid',
            fields: [
                'title', 'forumtitle', 'forumid', 'author',
                {name: 'replycount', type: 'int'},
                {name: 'lastpost', mapping: 'lastpost', type: 'date', dateFormat: 'timestamp'},
                'lastposter', 'excerpt'
            ]
        }),

        // turn on remote sorting
        remoteSort: true
    });
    var sm = new xg.CheckboxSelectionModel();
    ////////////////////////////////////////////////////////////////////////////////////////
    // Grid 1
    ////////////////////////////////////////////////////////////////////////////////////////
    var sm2 = new xg.CheckboxSelectionModel();
    var grid4 = new xg.GridPanel({
        id:'button-grid',
        store: new Ext.data.Store({
            reader: reader,
            data: xg.dummyData,
            width:600
        }),
       cm: new xg.ColumnModel([
            sm,
            {id:'CnName',header: "职位名称", width: 40, sortable: true, dataIndex: 'CnName'},
            {header: "岗位说明", width: 20, sortable: true,  dataIndex: 'PositionDesc'},
            {header: "排序号", width: 20, sortable: true, dataIndex: 'PositionTabIndex'},        
            {header: "创建时间", width: 20, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'CreateTime'},
            {header: "岗位所属部门", width: 20, sortable: true, dataIndex: 'DeptGuid'},
            {header: "选择", width: 20, sortable: true, dataIndex: 'choose'}
        ]),
        sm: sm2,

        viewConfig: {
            forceFit:true
        },

        // inline buttons
       // buttons: [{text:'Save'},{text:'Cancel'}],
        buttonAlign:'center',

        // inline toolbars
        tbar:[{
            text:'新增',
            tooltip:'新增',
            iconCls:'add',
            handler:function(){
            window.location="positionadd.jsp"; 
            }
        }, '-', {
            text:'修改',
            tooltip:'修改',
            iconCls:'option',
            handler:function(){
            window.location="positionmodify.jsp"; 
            }
            
        },'-',{
            text:'删除',
            tooltip:'删除',
            iconCls:'remove',
            handler:function(){
            alert('删除成功');
            }
        }],
         bbar: new Ext.PagingToolbar({
            pageSize: 25,
            store: store,
            displayInfo: true,
            displayMsg: 'Displaying topics {0} - {1} of {2}',
            emptyMsg: "No topics to display",
            items:[
                '-', {
                pressed: true,
                enableToggle:true,
                text: 'Show Preview',
                cls: 'x-btn-text-icon details',
                toggleHandler: toggleDetails
            }]
        }),
        width:1035,
        height:300,
        frame:true,
        title:'岗位基本信息列表',
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
     function toggleDetails(btn, pressed){
        var view = grid4.getView();
        view.showPreview = pressed;
        view.refresh();
    }
});



// Array data for the grids
Ext.grid.dummyData = [
    ['研发部Java工程师','工程师','1','7/21 12:00am','研发部','详情']
];

// add in some dummy descriptions
for(var i = 0; i < Ext.grid.dummyData.length; i++){
    Ext.grid.dummyData[i].push('Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed metus nibh, sodales a, porta at, vulputate eget, dui. Pellentesque ut nisl. Maecenas tortor turpis, interdum non, sodales non, iaculis ac, lacus. Vestibulum auctor, tortor quis iaculis malesuada, libero lectus bibendum purus, sit amet tincidunt quam turpis vel lacus. In pellentesque nisl non sem. Suspendisse nunc sem, pretium eget, cursus a, fringilla vel, urna.<br/><br/>Aliquam commodo ullamcorper erat. Nullam vel justo in neque porttitor laoreet. Aenean lacus dui, consequat eu, adipiscing eget, nonummy non, nisi. Morbi nunc est, dignissim non, ornare sed, luctus eu, massa. Vivamus eget quam. Vivamus tincidunt diam nec urna. Curabitur velit.');
}