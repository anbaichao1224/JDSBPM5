Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
     Ext.QuickTips.init();
     var xg = Ext.grid;
     var reader = new Ext.data.ArrayReader({}, [
       {name: 'CnName'},
       {name: 'Sex'},
       {name: 'OfficeTel'},
       {name: 'Mobile'},
       {name: 'Email1'},
       {name: 'CreateTime', type: 'date', dateFormat: 'n/j h:ia'},
       {name: 'choose'},
       {name: 'desc'}
    ]);
      
   var store = new Ext.data.Store({
        proxy: new Ext.data.ScriptTagProxy({
            url: 'http://extjs.com/forum/topics-browse-remote.php'
        }),
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
        remoteSort: true
    });
    //配置视图信息
    var view=new Ext.grid.GridView({forceFit:true,sortAscText :'正序', sortDescText :'倒序'});
    view.columnsText='列显示/隐藏';
    var sm = new xg.CheckboxSelectionModel();
    ////////////////////////////////////////////////////////////////////////////////////////
    // Grid 1
    ////////////////////////////////////////////////////////////////////////////////////////
    var persongrid = new xg.GridPanel({
        id:'button-grid',
        view:view,
        store: new Ext.data.Store({
            reader: reader,
            data: xg.dummyData,
            width:600
        }),
       cm: new xg.ColumnModel([
            sm,
            {id:'CnName',header: "中文名", width: 40, sortable: true, dataIndex: 'CnName'},
            {header: "性别", width: 20, sortable: true,  dataIndex: 'Sex'},
            {header: "办公电话", width: 20, sortable: true, dataIndex: 'OfficeTel'},
            {header: "移动电话", width: 20, sortable: true, dataIndex: 'Mobile'},
            {header: "电子邮件", width: 20, sortable: true, dataIndex: 'Email1'},
            {header: "创建时间", width: 20, sortable: true, renderer: Ext.util.Format.dateRenderer('m/d/Y'), dataIndex: 'CreateTime'},
            {header: "选择", width: 20, sortable: true, dataIndex: 'choose'}
        ]),
        viewConfig: {
            forceFit:true
        },
        buttonAlign:'center',
        tbar:[{
            text:'新增',
            tooltip:'新增',
            iconCls:'add',
            handler:function(){
            window.location="personInsert.jsp"; 
            }
        }, '-', {
            text:'修改',
            tooltip:'修改',
            iconCls:'option',
            handler:function(){
            window.location="personmodify.jsp"; 
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
            pageSize: 2,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "无记录"
        }),
        sm:sm,
        width:1035,
        height:300,
        frame:true,
        title:'人员基本信息列表',
        iconCls:'icon-grid',
        renderTo: document.body
    });
    function toggleDetails(btn, pressed){
        var view = persongrid.getView();
        view.showPreview = pressed;
        view.refresh();
    }
    
});



// Array data for the grids
Ext.grid.dummyData = [
    ['陈列','男','010-6443324','1324599595','dfd@163.com','7/21 12:00am','详情'],
    ['陈列','男','010-6443324','1324599595','dfd@163.com','7/21 12:00am','详情'],
    ['陈列','男','010-6443324','1324599595','dfd@163.com','7/21 12:00am','详情']
    //['杨尚昆','女','010-6443324','1324599595','dfd@163.com','7/21 12:00am','详情'],
   // ['李鹏','男','010-6443324','1324599595','dfd@163.com','7/21 12:00am','详情'],
   // ['林方','女','010-6443324','1324599595','dfd@163.com','7/21 12:00am','详情'],
   // ['肖像','男','010-6443324','1324599595','dfd@163.com','7/21 12:00am','详情'],
   // ['李白','女','010-6443324','1324599595','dfd@163.com','7/21 12:00am','详情'],
    //['夺夺','男','010-6443324','1324599595','dfd@163.com','7/21 12:00am','详情']
];

// add in some dummy descriptions
for(var i = 0; i < Ext.grid.dummyData.length; i++){
    Ext.grid.dummyData[i].push('Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Sed metus nibh, sodales a, porta at, vulputate eget, dui. Pellentesque ut nisl. Maecenas tortor turpis, interdum non, sodales non, iaculis ac, lacus. Vestibulum auctor, tortor quis iaculis malesuada, libero lectus bibendum purus, sit amet tincidunt quam turpis vel lacus. In pellentesque nisl non sem. Suspendisse nunc sem, pretium eget, cursus a, fringilla vel, urna.<br/><br/>Aliquam commodo ullamcorper erat. Nullam vel justo in neque porttitor laoreet. Aenean lacus dui, consequat eu, adipiscing eget, nonummy non, nisi. Morbi nunc est, dignissim non, ornare sed, luctus eu, massa. Vivamus eget quam. Vivamus tincidunt diam nec urna. Curabitur velit.');
}