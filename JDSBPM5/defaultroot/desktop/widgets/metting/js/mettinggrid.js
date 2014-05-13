Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
    var newFormWin;
    var persongrid;
    var currentRowNumber;
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var xg = Ext.grid;
    var store = new Ext.data.Store({
       
    });
        var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    	var cm = new Ext.grid.ColumnModel([sm,
        
        ]);
    cm.defaultSortable = true;
    var sm = new xg.CheckboxSelectionModel();
    var name = new Ext.form.TextField({   
                width:175, 
                name: 'name',
                allowBlank:true 
            }); 
    var persongrid = new Ext.grid.GridPanel({
        id:'button-grid',
        width:300,
        store:store,
	    cm:cm,
	    sm:sm,
           loadMask: true,//装载动画
        viewConfig: {
            forceFit:true
        },
        
         bbar: new Ext.PagingToolbar({
            pageSize: 100,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "无记录"
        }),     
        
        width:785,
        height:540,
        frame:true,
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
    persongrid.render();//Ext.getCmp('grid').render();//渲染表格
    store.load({params:{start:0, limit:100}}); //加载数据
    persongrid.on("rowdblclick", function(persongrid) {
        loadFormData(persongrid);
    });


    
    
    
    function querypersonMessage()
    { 
    
    	var name = Ext.get('name').dom.value;
        store.load({params:{start:0, limit:10,name:name}});    
    }
    
});

