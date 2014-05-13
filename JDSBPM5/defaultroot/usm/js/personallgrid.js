Ext.onReady(function(){
    Ext.QuickTips.init();
    var addForm;
    var newwin;
    var eventID;
    //创建 Store
    var store = new Ext.data.Store({

        proxy: new Ext.data.HttpProxy({   
            url: '/usm/personextaccountjson.do?sysid='+sysid+''   //从此页获取数据
        }),
        
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'webadminid',
            fields: [
            {name: 'uuid', type: 'string'},
            {name: 'webadminid', type: 'string'},
            {name: 'adminid', type: 'string'},
            {name: 'createtime', type: 'string'}
            ]
        })
        
        // turn on remote sorting
     //   remoteSort: true
    });
    store.setDefaultSort('uuid', 'desc');
    
     //创建 Store
    var store2 = new Ext.data.Store({

        proxy: new Ext.data.HttpProxy({   
            url: '/usm/sysorgpersonGrid.do?sysid='+sysid+''  //从此页获取数据
        }),
        
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'personid',
            fields: [
            {name: 'personid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'sex', type: 'string'}
            ]
        })
        
        // turn on remote sorting
     //   remoteSort: true
    });
    store2.setDefaultSort('personid', 'desc');
    
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
           id: 'webadminid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'webadminid',
           hidden:true,
           align:'center',
           width: 60
        },{
           header: "名称",
           dataIndex: 'adminid',
           align:'center',
           width: 100
        },{
           header: "创建时间",
           dataIndex: 'createtime',
           align:'center',
           width: 60
        }
        ]);
     cm.defaultSortable = true;
     	 var cnname = new Ext.form.TextField({   
                width:175, 
                id:'cnname',
                name: 'cnname',
                allowBlank:true 
            }); 
    var sm2 = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm2 = new Ext.grid.ColumnModel([sm2,{
           id: 'personid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'personid',
           align:'center',
           width: 60
        },{
           header: "名称",
           dataIndex: 'cnname',
           align:'center',
           width: 100
        },{
           header: "性别",
           dataIndex: 'sex',
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
        title:'人员列表',
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
        tbar : [ '按模块查询：', 
        			cnname,
        			 {// 查询按钮   
                    id : 'queryButton',    
                    icon : "/usm/img/search.png",   
                    cls : "x-btn-text-icon",
					handler:function(){
					    
						var	cnname = Ext.getCmp('cnname').getValue();
						
						store.load({params:{start:0, limit:25,cnname:cnname}});
						
						}
         				},{
                text : '选择',
                tooltip : '选择',
                iconCls : 'add',
                handler: function (){
                           showAddPanel();
                           eventID = "add";
                         }
            }, '-', {
                text : '编辑',
                tooltip : '编辑',
                iconCls : 'edit',
                handler: function (){
                          loadFormData(grid);
                         }
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
             items: new Ext.grid.GridPanel({
			id:'admingrid',
	        width: 500,
           	height:250,
            x:140,
            y:50,
	        title:'人员列表',
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
			
				var row=Ext.getCmp("admingrid").getSelections();
	            var txtCheckValue = "";
	            for(var i=0,len=row.length;i<len;i++){
	                var ss = row[i].get("personid");
	                txtCheckValue = txtCheckValue + ss + ",";
	            }
	         
				Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/personextaccountSave.do?sysid="+sysid+"&txtCheckValue="+txtCheckValue, 
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
					url:"/usm/personextaccountRemove.do?txtCheckValue="+jsonData, 
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


