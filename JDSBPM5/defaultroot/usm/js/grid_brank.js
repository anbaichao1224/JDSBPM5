Ext.onReady(function(){
    Ext.QuickTips.init();
    var addForm;
    var newwin;
    var eventID;
    //创建 Store
    var store = new Ext.data.Store({

        proxy: new Ext.data.HttpProxy({   
            url: '/usm/personextaccountjson.do'   //从此页获取数据
        }),
        
        reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'webadminid',
            fields: [
            {name: 'webadminid', type: 'string'},
            {name: 'adminid', type: 'string'},
            {name: 'createtime', type: 'string'}
            ]
        })
        
        // turn on remote sorting
     //   remoteSort: true
    });
    store.setDefaultSort('webadminid', 'desc');
    
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
           id: 'webadminid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "编号",
           dataIndex: 'webadminid',
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

    // by default columns are sortable
    var grid = new Ext.grid.GridPanel({
        el:'topic-grid',
        width:800,
        height:500,
        title:'项目一览表',
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
                id : 'SEARCH',
                text : ' 查询  ',
                tooltip : '查询',
                iconCls : 'search'
            },{
                text : '新增',
                tooltip : '新增',
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
                        
            addForm.getForm().load( {
                url : 'Project.aspx?event=editshow&prjid='
                        + _record.get('PRJ_ID'),
                waitMsg : '正在读取中…',
                           
                failure : function() {
                    Ext.MessageBox.show({
                        title:'消息',
                        msg: '读取失败!',
                        buttons: Ext.Msg.OK,
                        icon: Ext.MessageBox.ERROR
                    });
                },
                success : function() {
                    eventID = "edit";
                    //Ext.MessageBox.alert(读取成功！');
                }
            });
        }
    }


    // trigger the data store load
    store.load({params:{start:0, limit:25}});
    
    function toggleDetails(btn, pressed){
        var view = grid.getView();
        view.showPreview = pressed;
        view.refresh();
    }
    
    //Detail Combox
    var storeDept = new Ext.data.Store({                //读取部门
          proxy: new Ext.data.HttpProxy({
                 url:"Dept.aspx"
             }),
             // create reader that reads the project records
             reader: new Ext.data.JsonReader({root: 'data'},[
                 {name:'DEP_NAME',type:'string'},
                 {name:'DEP_ID',type:'string'}
          ])
    });
    storeDept.load(); 
    
     var storeStatus = new Ext.data.Store({             //读取状态
         proxy: new Ext.data.HttpProxy({
                url:"TBL_CODE_M_Data.aspx?codekb=01"
            }),
            // create reader that reads the project records
            reader: new Ext.data.JsonReader({root: 'data'},[
                {name:'CODE_NAME',type:'string'},
                {name:'CODE_ID',type:'string'}
         ])
    });
    storeStatus.load();
    
    var storeUser = new Ext.data.Store({                  //读取用户
         proxy: new Ext.data.HttpProxy({
                url:"TBL_USER_M_Data.aspx"
            }),
            // create reader that reads the project records
            reader: new Ext.data.JsonReader({root: 'data'},[
                {name:'USER_NAME',type:'string'},
                {name:'USER_ID',type:'string'}
         ])
    });
    storeUser.load();
    
    //********************ADD Page JS**********************//
     // create reader that reads the Topic records
     var jsonFormReader = new Ext.data.JsonReader({                 
            root: 'data',
            totalProperty: 'totalCount',
            id: 'PRJ_ID',
            fields: [
                'PRJ_ID', 'PRJ_NAME', 'DEP_ID', 'PRJ_MANID','PRJ_STATE',
                {name: 'PRJ_STARTDATE',type: 'string'},
                {name: 'PRJ_ENDDATE'  ,type: 'string'}
                ,'PRJ_MEMO'
            ]
        });
        
    addForm = new Ext.FormPanel({
                    labelWidth : 75, // label settings here cascade unless overridden
                    //url : 'Project.aspx',
                    frame : true,
                    title : '编辑',
                    width : 580,
                    waitMsgTarget : true,
                //    reader : jsonFormReader,
                    defaults : {
                        width : 580
                    },
                   items: [{
                            layout:'column',
                            border:false,
                            items:[{
                                columnWidth:.5,
                                layout: 'form',
                                border:false,
                                items: [ {
                                             xtype:'textfield',
                                             fieldLabel: '&nbsp;&nbsp;&nbsp;&nbsp;项目编号',
                                             labelStyle: 'width:80px',
                                             width:150,
                                             name: 'PRJ_ID',
                                             allowBlank:false,
                                             blankText: '必须有项目编号'
                                        },new Ext.form.ComboBox({
                                            fieldLabel: '&nbsp;&nbsp;&nbsp;&nbsp;部门',
                                            labelStyle: 'width:80px',
                                            width:150,
                                            name: 'DEP_ID',
                                            store: storeDept,
                                            displayField:'DEP_NAME',
                                            valueField: 'DEP_ID',
                                            hiddenName:'DEP_ID',
                                            mode: 'client',
                                            triggerAction: 'all',
                                            emptyText:'请选择一个部门',
                                            selectOnFocus:true
                                        }),new Ext.form.DateField({
                                             fieldLabel: '&nbsp;&nbsp;&nbsp;&nbsp;预计开始日期',
                                             labelStyle: 'width:80px',
                                             width:150,
                                             format : 'Y/m/d',
                                             name: 'PRJ_STARTDATE',                                            
                                             reader: renderDateTime,
                                             beforerender :renderDateTime
                                             
                                        }),
                                        new Ext.form.ComboBox({
                                            fieldLabel: '&nbsp;&nbsp;&nbsp;&nbsp;当前状态',
                                            labelStyle: 'width:80px',
                                            width:150,
                                            name: 'PRJ_STATE',
                                            store: storeStatus,
                                            displayField:'CODE_NAME',
                                            valueField: 'CODE_ID',
                                            hiddenName:'PRJ_STATE',
                                            mode: 'client',
                                            triggerAction: 'all',
                                            emptyText:'请选择项目状态',
                                            selectOnFocus:true
                                        })
                                        ]
                            },{
                                columnWidth:.5,
                                layout: 'form',
                                border:false,
                                items: [{
                                             xtype:'textfield',
                                             fieldLabel: '&nbsp;&nbsp;&nbsp;&nbsp;项目名称',
                                             labelStyle: 'width:80px',
                                             width:150,
                                             name: 'PRJ_NAME',
                                             allowBlank:false,
                                             blankText: '请输入项目'
                                        },new Ext.form.ComboBox({
                                            fieldLabel: '&nbsp;&nbsp;&nbsp;&nbsp;项目经理',
                                            labelStyle: 'width:80px',
                                            width:150,
                                            name: 'PRJ_MANID',
                                            store: storeUser,
                                            displayField:'USER_NAME',
                                            valueField: 'USER_ID',
                                            hiddenName:'PRJ_MANID',
                                            mode: 'client',
                                            triggerAction: 'all',
                                            emptyText:'请输入项目经理',
                                            selectOnFocus:true
                                        }),new Ext.form.DateField({
                                             fieldLabel: '&nbsp;&nbsp;&nbsp;&nbsp;实际结束日期',
                                             labelStyle: 'width:80px',
                                             width:150,
                                             format : 'Y/m/d',
                                             name: 'PRJ_ENDDATE'
                                        })
                                        ]
                            }]
                        },{
                                xtype:'textarea',
                                name:'PRJ_MEMO',
                                fieldLabel:'&nbsp;&nbsp;&nbsp;&nbsp;备注',
                                labelStyle: 'width:80px',
                                height:150,
                                width:170,
                                anchor:'90%'
                            }
                ],    
                  buttons: [{
                                id:'btnSave',
                                text:'保 存',
                                handler:doSave,
                                disabled:false
                            },{
                                text: '取消',
                                handler: function(){
                                    newwin.hide();
                                }
                            }]
         });       
var myFormWin = function() {
    showAddPanel();
}
function showAddPanel(){

    if (!newwin) {
            newwin = new Ext.Window({
            
                    //el : 'topic-win',
                    layout : 'fit',
                    width : 600,
                    height : 380,
                    closeAction : 'hide',
                    closable:true,
                    plain : true,
                    modal: 'true', 
                    title : '窗口',
                    items : addForm
            });
           
         }
        
         newwin.show('New1'); 
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
                var ss = row[i].get("PRJ_ID");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息ジ', '请选择要删除的数据。');
            }else{
                Ext.lib.Ajax.request(
                            'GET',
                            'Project.aspx?event=delete&delData=' + jsonData,
                            {
                                success : function(form, action) { Ext.MessageBox.alert('消息', '删除成功');
                                                                   store.reload();},
                                failure : function(form, action) { 
                                                                     Ext.MessageBox.show({
                                                                        title:'消息',
                                                                        msg: '删除成功！',
                                                                        buttons: Ext.Msg.OK,
                                                                        icon: Ext.MessageBox.ERROR
                                                                    });
                                                                 }
                            },
                            null
                        );
               
            }
        }
        
    }
});

