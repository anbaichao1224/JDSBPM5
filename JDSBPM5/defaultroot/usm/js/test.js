sm = new Ext.grid.CheckboxSelectionModel({
listeners: {
beforerowselect : function (sm, rowIndex, keep, rec) {
if (this.deselectingFlag && this.grid.enableDragDrop){
this.deselectingFlag = false;
this.deselectRow(rowIndex);
return this.deselectingFlag;
}
return keep;
}
},
onMouseDown : function(e, t){
if(e.button === 0 ){
e.stopEvent();
var row = e.getTarget('.x-grid3-row');
if(row){
var index = row.rowIndex;
if(this.isSelected(index)){
if (!this.grid.enableDragDrop)
this.deselectRow(index);
else
this.deselectingFlag = true;
}else{
this.selectRow(index, true);
}
}
}
}
});

var cm = new Ext.grid.ColumnModel([
    {header:'编号',dataIndex:'id'},
    {header:'性别',dataIndex:'sex',renderer:function(value){
            if (value == 'male') {
                return "<span style='color:red;font-weight:bold;'>红男</span>";
            } else {
                return "<span style='color:green;font-weight:bold;'>绿女</span>";
            }
        }},
    {header:'名称',dataIndex:'name'},
    {header:'描述',dataIndex:'descn'}
]);

Ext.onReady(function(){
    Ext.QuickTips.init();
    var addForm;
    var newwin;
    var eventID;
    //创建 Store
    var store = new Ext.data.Store({

        proxy: new Ext.data.HttpProxy({   
            url: '/usm/sysmoduleAllGrid.do?sysid='+sysid+''   //从此页获取数据
        }),
        
        reader: new Ext.data.JsonReader({
             root: 'data',
            totalProperty: 'totalCount',
            id: 'moduleid',
            fields: [
            {name: 'moduleid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'enname', type: 'string'},
             {name: 'memo', type: 'string'}
            ]
        })
        
        // turn on remote sorting
     //   remoteSort: true
    });
    store.setDefaultSort('moduleid', 'desc');
    
    //创建 Store
    var store2 = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: "/usm/moduleGrid.do"   //从此页获取数据
        }),
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'moduleid',
            fields: [
            {name: 'moduleid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'enname', type: 'string'},
             {name: 'memo', type: 'string'}
            ]
        })
        
        // turn on remote sorting
       // remoteSort: true
    });
    store2.setDefaultSort('moduleid', 'desc');
    
  
     var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
     
     var cm = new Ext.grid.ColumnModel([sm,{
           id: 'moduleid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'moduleid',
           align:'center',
           hidden:true,
           width: 60
        },{
           header: "中文名称",
           dataIndex: 'cnname',
           align:'center',
           width: 100
        },{
           header: "英文名称",
           dataIndex: 'enname',
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
     
   
    
     
    var sm2 = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
  var cm2 = new Ext.grid.ColumnModel([sm,{
           id: 'moduleid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'moduleid',
           align:'center',
           hidden:true,
           width: 60
        },{
           header: "中文名称",
           dataIndex: 'cnname',
           align:'center',
           width: 100
        },{
           header: "英文名称",
           dataIndex: 'enname',
           align:'center',
           width: 60
        },{
           header: "描述",
           dataIndex: 'memo',
           align:'center',
           width: 60
        }
        ]);
     cm2.defaultSortable = true;

    // by default columns are sortable
    var grid = new Ext.grid.GridPanel({
        el:'topic-grid',
	    width:699,
        height:380,
        title:'模块列表',
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
            pageSize: 25,
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
        }),
        tbar : [{
                text : '选择',
                tooltip : '选择',
                iconCls : 'add',
                handler: function (){
                           showAddPanel();
                           eventID = "add";
                         }
            }, '-', {
                text : '删除',
                tooltip : '删除',
                iconCls : 'remove',
                handler: showDelMess
            }]
    });
Ext.getCmp("")
    grid.render();

    grid.on("rowdblclick", function(grid) {
        loadFormData(grid);
    });
    var loadFormData = function(grid) {
        var _record = grid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.MessageBox.alert('提示', '请选择一笔记录');
        } else {
            
            myFormWin();
        }
    }


    // trigger the data store load
    store.load({params:{start:0, limit:15}});
   
    
    function toggleDetails(btn, pressed){
        var view = grid.getView();
        view.showPreview = pressed;
        view.refresh();
    }
    
var myFormWin = function() {
    showAddPanel();
}
function showAddPanel(){
	var txtChkValue;
 	var win;
        if(!win){
            win = new Ext.Window({
                id:'win1',
                title:'权限',
       			width: 500,
                height:250,
                x:140,
                y:50,
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
              items: new Ext.grid.GridPanel({
			id:'modulegrid',
	        width: 500,
           	height:250,
            x:140,
            y:50,
	        title:'模块',
	        store: store2,
	        cm: cm2,
	        sm: sm2,
	        trackMouseOver:false,
	        loadMask: true,
	        autoShow : true,
	        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true
       		},
       		 bbar: new Ext.PagingToolbar({
		            pageSize: 25,
		            store: store2,
		            displayInfo: true,
		            displayMsg: '当前显示{0} - {1}  条记录 /共 {2}条记录',
		            emptyMsg: "无显示数据"
		         
		        })
    		}),
            buttons: [{ 
			text: '保存', 
			handler: function() { 
			
				var row=Ext.getCmp("modulegrid").getSelections();
				
	            var txtCheckValue = "";
	            for(var i=0,len=row.length;i<len;i++){
	                var ss = row[i].get("moduleid");
	                txtCheckValue = txtCheckValue + ss + ",";
	            }
	            
					Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/sysmoduleSave.do?n="+Math.random()+"&txtCheckValue="+txtCheckValue+"&sysid="+sysid, 
					success : function(result, action)
								{ 
								Ext.MessageBox.alert('消息', '操作成功');
							  	}, 
				    failure : function(form, action) 
				    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 

				win.close(); 
				store.reload();
			} 
			},{ 
			text: '取消', 
			handler: function(){ 
			win.close(); 
			;} 
			
			}] 
            
            });
        }
        win.show();
        store2.load({params:{start:0, limit:10,sysid:sysid}});
    }
   
     
    
    
    function doSave( )
    {
         var evt = eventID;

         Ext.MessageBox.show({
            msg: '保存中...',
            progressText: '保存中...',
            width:200,
            wait:true,
            waitConfig: {interval:200},
            icon:'ext-mb-save',
            nimEl: 'btnSave'
            });
         addForm.getForm().submit({
            url:'Project.aspx?event=' + evt,
            method:'POST',
            success: function(form, action){
                Ext.MessageBox.hide();
                Ext.MessageBox.alert('信息', '保存成功！');
                newwin.hide();
                //store.load({params:{start:0, limit:25}});
                store.reload();
            },
            failure: function(form, action){
                Ext.MessageBox.hide();
                Ext.MessageBox.show({
                    title:'消息',
                    msg: '保存失败！',
                    buttons: Ext.Msg.OK,
                    icon: Ext.MessageBox.ERROR
                });
            }
            });
    }
    
    function showDelMess(){
        Ext.MessageBox.confirm('消息', '确认删除？',doDel);
    }
    function doDel(btn){
        if(btn=='yes'){
            var row=grid.getSelections();
            var jsonData = "";
            for(var i=0,len=row.length;i<len;i++){
                var ss = row[i].get("moduleid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息ジ', '请选择要删除的数据。');
            }else{
               Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/sysmoduleAllRemove.do?txtCheckValue="+jsonData+"&sysid="+sysid, 
					success : function(result, action)
								{ 
								Ext.MessageBox.alert('消息', '成功!');
								 store.reload();
							  	}, 
				    failure : function(form, action) 
				    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 
               
            }
        }
        
    }
});
var fp = new Ext.FormPanel({
        frame: true,
        title:'列表',
        labelWidth: 110,
        width: 500,
        renderTo:'form-ct',
        bodyStyle: 'padding:0 10px 0;',
        
        items: [{
            xtype:'fieldset',
            title: '模块列表',
            collapsible: true,
            autoHeight:true,
            defaults: {width: 210},
            items :str
        }],
        
        buttons: [{
            text: 'Save',
            handler: function(){
               if(fp.getForm().isValid()){
                    Ext.Msg.alert('Submitted Values', 'The following will be sent to the server: <br />'+ 
                        fp.getForm().getValues(true).replace(/&/g,'&'));
                }
            }
        },{
            text: '取消',
            handler: function(){
                fp.getForm().reset();
            }
        }]
    });
  fp.getForm().getValues(true).toString()
