Ext.onReady(function(){
    Ext.QuickTips.init();
    var addForm;
    var newwin;
    var eventID;
    //创建 Store
    var store = new Ext.data.Store({

        proxy: new Ext.data.HttpProxy({   
            url: '/usm/appcatalogmoduleGrid.do?appcatalogid='+appcatalogid+''   //从此页获取数据
        }),
        
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'uuid',
            fields: [
            {name: 'uuid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name:'serialindex'},
            {name: 'displayname', type: 'string'}

            ]
        })
        
        // turn on remote sorting
     //   remoteSort: true
    });
    store.setDefaultSort('serialindex', 'asc');
    
    
    var modulestore = new Ext.data.Store({

        proxy: new Ext.data.HttpProxy({   
            url: '/usm/appcatalogmoduleGrid.do?appcatalogid='+appcatalogid+''   //从此页获取数据
        }),
        
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'uuid',
            fields: [
            {name: 'uuid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name:'serialindex'},
            {name: 'displayname', type: 'string'}

            ]
        })
        
        // turn on remote sorting
     //   remoteSort: true
    });
    modulestore.setDefaultSort('serialindex', 'asc');
     //创建 Store
    var store2 = new Ext.data.Store({

      proxy: new Ext.data.HttpProxy({   
            url: '/usm/sysmoduleGrid.do?sysid='+sysid+''   //从此页获取数据
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
    store2.setDefaultSort('moduleid', 'desc');
    
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
           width: 60
        },{
           id: 'cnname', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "名称",
           dataIndex: 'cnname',
           align:'center',
           width: 60
        },{
           header: "描述",
           dataIndex: 'displayname',
           align:'center',
           width: 100
        },{
           header: "序号",
           dataIndex: 'serialindex',
           align:'center',
           hidden:true,
           width: 100
        }
        ]);
     cm.defaultSortable = true;
     
    var modulesm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var modulecm = new Ext.grid.ColumnModel([modulesm,{
           id: 'uuid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'uuid',
            hidden:true,
           align:'center',
           width: 60
        },{
           id: 'cnname', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "名称",
           dataIndex: 'cnname',
           align:'center',
           width: 60
        },{
           header: "描述",
           dataIndex: 'displayname',
           align:'center',
           width: 100
        },{
           header: "序号",
           dataIndex: 'serialindex',
           align:'center',
           hidden:true,
           width: 100
        }
        ]);
     modulecm.defaultSortable = true; 
    
     
    var sm2 = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm2 = new Ext.grid.ColumnModel([sm2,{
          id: 'moduleid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'moduleid',
           align:'center',
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
        tbar : [ {
                id : 'Sort',
                text : ' 排序 ',
                tooltip : '排序',
                iconCls : 'search',
                handler:function(){
                	moduleSort();
                }
            },{
                text : '选择',
                tooltip : '选择',
                iconCls : 'add',
                handler: function (){
                           showAddPanel();
                           
                         }
            }, '-', {
                text : '删除',
                tooltip : '删除',
                iconCls : 'remove',
                handler: showDelMess
            }]
    });

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
    store.load({params:{start:0, limit:25}});
    store2.load({params:{start:0, limit:25}});
    
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
              //  html:"<iframe style='width:100%;height:100%;' src='http://www.baidu.com'></iframe>"
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
					url:"/usm/appcatalogmoduleSave.do?appcatalogid="+appcatalogid+"&txtCheckValue="+txtCheckValue, 
					success : function(result, action)
								{ 
								Ext.MessageBox.alert('消息', '成功!')
							  	}, 
				    failure : function(form, action) 
				    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 
			//	var s="/usm/sysorgSave.do?txtCheckValue='"+txtCheckValue+"'&sysid="+sysid;

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
        store2.reload();
    }
    

//模块调序
function moduleSort(){
	var win;
        if(!win){
            win = new Ext.Window({
                id:'modulewindows',
                title:'应用列表',
                width:500,
		        height:300,
                x:110,
                y:60,
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
              //  html:"<iframe style='width:100%;height:100%;' src='http://www.baidu.com'></iframe>"
              	items: new Ext.grid.GridPanel({
		     	id:'modulegrid',
		        width:500,
		        height:250,
		        title:'',
		        store: modulestore,
		        cm: modulecm,
		        sm: modulesm,
		        trackMouseOver:true,
		        loadMask: true,
		        autoShow : true,
		        enableDragDrop: true,
		        viewConfig: {
		            forceFit:true,
		            enableRowBody:true,
		            showPreview:true
		        }
		        
		        }),
            buttons: [{ 
			text: '保存', 
			handler: function() {
				modulesm.selectAll();
				var row=Ext.getCmp("modulegrid").getSelections();
	            var txtCheckValue = "";
	            for(var i=0,len=row.length;i<len;i++){
	                var ss = row[i].get("uuid");
	                txtCheckValue = txtCheckValue + ss + ",";
	            }
	            	Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/appcatalogmoduleSaveSort.do?txtCheckValue="+txtCheckValue, 
					success : function(result, action)
								{ 
								Ext.MessageBox.alert('消息', '操作成功');
							  	}, 
				    failure : function(form, action) 
				    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 
				store.reload();
				win.close();
				
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
    //    Ext.getCmp("resgrid").render();
    
     modulestore.load();
     
     var ddrow = new Ext.dd.DropTarget(Ext.getCmp("modulegrid").container, {
        ddGroup : 'GridDD',
        copy    : false,
        notifyDrop : function(dd, e, data) {
            // 选中了多少行
            var rows = data.selections;
            // 拖动到第几行
            var index = dd.getDragData(e).rowIndex;
            if (typeof(index) == "undefined") {
                return;
            }
            // 修改ds
            for(i = 0; i < rows.length; i++) {
                var rowData = rows[i];
                if(!this.copy) modulestore.remove(rowData);
                modulestore.insert(index, rowData);
            }
        }
    }); 
        
  
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
                var ss = row[i].get("uuid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息ジ', '请选择要删除的数据。');
            }else{
               Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/appcatalogmoduleRemove.do?txtCheckValue="+jsonData, 
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


