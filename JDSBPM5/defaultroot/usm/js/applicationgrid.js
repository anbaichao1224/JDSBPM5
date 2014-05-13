Ext.onReady(function(){
    Ext.QuickTips.init();
    var addForm;
    var newwin;
    var eventID;
    //创建 Store
    var store = new Ext.data.Store({

        proxy: new Ext.data.HttpProxy({   
            url: '/usm/applicationGrid.do?appcatalogid='+appcatalogid+''   //从此页获取数据
        }),
        
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'uuid',
            fields: [
            {name: 'uuid', type: 'string'},
            {name: 'appcatalogid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'memo', type: 'string'}
            ]
        })
        
        // turn on remote sorting
     //   remoteSort: true
    });
    store.setDefaultSort('uuid', 'desc');
    
    
    
    // store.load({params:{start:0, limit:25}});
    function renderDateTime(value){
        var tmpValue = value.replace(/@/g,"");
        if(tmpValue *1 < 0) return "";
        var tmpDate = new Date(tmpValue * 1);
        return String.format('{0}', Ext.util.Format.date(tmpDate,'Y/m/d'));
    }
    
    // the column model has information about grid columns
    // dataIndex maps the column to the specific data field in
    // the data store
   // var nm = new Ext.grid.RowNumberer(); 
     var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm = new Ext.grid.ColumnModel([sm,{
           id: 'uuid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'uuid',
            hidden:true,
           align:'center',
           width: 10
        },{
           id: 'appcatalogid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'appcatalogid',
           align:'center',
            hidden:true,
           width: 40
        },{
           header: "名称",
           dataIndex: 'cnname',
           align:'center',
           width: 60
        },{
           header: "描述",
           dataIndex: 'memo',
           align:'center',
           width: 60
        }
        ]); 
     cm.defaultSortable = true;
     

    // by default columns are sortable
    var grid = new Ext.grid.GridPanel({
        el:'topic-grid',
        width:699,
        height:380,
        title:'子应用目录列表',
        store: store,
        cm: cm,
        sm: sm,
        trackMouseOver:false,
        //sm: new Ext.grid.RowSelectionModel({selectRow:Ext.emptyFn}),
        loadMask: true,
        autoShow : true,
        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true
       /*     getRowClass : function(record, rowIndex, p, store){
                if(this.showPreview){
                    p.body = '<p>　　　　　　备注：'+record.data.PRJ_MEMO+'</p>';
                    return 'x-grid3-row-expanded';
                }
                return 'x-grid3-row-collapsed';
            }
            */
        },
        bbar: new Ext.PagingToolbar({
            pageSize: 10,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示{0} - {1}  条记录 /共 {2}条记录',
            emptyMsg: "无显示数据",
            items:[
                '-', {
                pressed: true,
                enableToggle:true,
                text: '备注',
                iconCls: 'preview',
                toggleHandler: toggleDetails
            }]
        })
        
    });

    grid.render();

  

    // trigger the data store load
    store.load({params:{start:0, limit:10}});

    
    function toggleDetails(btn, pressed){
        var view = grid.getView();
        view.showPreview = pressed;
        view.refresh();
    }
    
});


