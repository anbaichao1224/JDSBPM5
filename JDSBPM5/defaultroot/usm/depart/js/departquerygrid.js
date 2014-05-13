var contPath = context;

Ext.onReady(function(){
    var newFormWin;
    var currentRowNumber;
    
    Ext.QuickTips.init();
    
    var xg = Ext.grid;
    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: '/usm/getDepartInfo.do'   //从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'orgid',
            fields: [
            {name: 'orgid', type: 'string'},
            {name: 'parentorgid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'enname', type: 'string'},
            {name: 'status', type: 'string'},
            {name: 'orgtype', type: 'string'},
            {name: 'intel', type: 'string'}, 
            {name: 'memo', type: 'string'},
            {name: 'orglevel', type: 'string'},
            {name: 'outtel', type: 'string'},
            {name: 'nighttel', type: 'string'}, 
            {name: 'fax', type: 'string'}, 
            {name: 'serialindex', type: 'string'}    
            ]
        })
    });
   
    
    var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm = new Ext.grid.ColumnModel([sm,
     new Ext.grid.RowNumberer(
	     {
		     header:'序号',
		     width:32,
		     align:'center'
	     }
        ),
        {
           id: 'orgid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "部门编号",
           dataIndex: 'orgid',
           align:'center',
		   hidden :true,
           width: 60
        },{
           header: "父级部门ID",
           dataIndex: 'parentorgid',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "部门名称",
           dataIndex: 'cnname',
           align:'center',
           allowBlank:false,
           blankText:'部门名称不能为空',
           width: 100
        },{
           header: "英文名称",
           dataIndex: 'enname',
           align:'center',
           allowBlank:false,
           blankText:'英文名称不能为空',
           width: 100
        },{
           header: "状态",
           dataIndex: 'status',
           align:'center',
           hidden :false,
           width: 100
        },{
           header: "部门类型",
           dataIndex: 'orgtype',
           align:'center',
           hidden :false,
           width: 100
        },{
           header: "内线电话",
           dataIndex: 'intel',
           align:'center',
           //hidden :true,
           width: 100
        },{
           header: "备注",
           dataIndex: 'memo',
           align:'center',
           //hidden :true,
           width: 100
        },{
           header: "部门级别",
           dataIndex: 'orglevel',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "外线电话",
           dataIndex: 'outtel',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "夜间值班电话",
           dataIndex: 'nighttel',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "传真",
           dataIndex: 'fax',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "排序",
           dataIndex: 'serialindex',
           align:'center',
           hidden :false,
          // hidden :true,
           width: 100
        }
        ]);
        cm.defaultSortable = true;
        
         //配置视图信息
		var view=new Ext.grid.GridView({forceFit:true,sortAscText :'正序', sortDescText :'倒序'});
		view.columnsText='列显示/隐藏';
   

       var name = new Ext.form.TextField({   
                width:175,
                id:'name',
                name: 'name',
                allowBlank:true 
            }); 
           
      var departgrid = new Ext.grid.GridPanel({
        id:'button-grid',
        width:300,
        store:store,
	    cm:cm,
	    sm:sm,
	    view:view,  
	    loadMask: true,//装载动画
	       
        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true
        },

        // inline toolbars
        tbar:['部门名称：',name, {// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",  
                    text : '查 询',
                    cls : "x-btn-text-icon",   
                    handler :querydepartMessage    
            
        }],
        
         bbar: new Ext.PagingToolbar({
            pageSize: 100,
            store: store,
            cls:'x-btn-text-icon details',
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "当前没有可以显示的数据"
            
        }), 
        width:785,
        height:540,
        frame:true,
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
    departgrid.render();
    store.setDefaultSort('serialindex', 'ASC');
    store.load({params:{start:0, limit:100}});
    departgrid.getView().refresh();
    departgrid.on("rowdblclick", function(departgrid) {
        loadFormData(departgrid);
    });

    // 载入被选择的数据行的表单数据
    var loadFormData = function(departgrid) {
         
        var _record = departgrid.getSelectionModel().getSelected();
				
           //Ext.getCmp("orgid").value=_record.get('orgid');
           Ext.getCmp("cnname").value=_record.get('cnname');
           Ext.getCmp("enname").value=_record.get('enname');
           Ext.getCmp("status").value=_record.get('status');
           Ext.getCmp("orgtype").value=_record.get('orgtype');
           Ext.getCmp("status").value=_record.get('status');
           if(_record.get('memo')=='null')
           {
           	Ext.getCmp("memo").value = '';
           }else
           {
           	Ext.getCmp("memo").value=_record.get('memo');
           }
           
           Ext.getCmp("orglevel").value=_record.get('orglevel');
           Ext.getCmp("serialindex").value=_record.get('serialindex');
           Ext.getCmp("intel").value=_record.get('intel');
           Ext.getCmp("outtel").value=_record.get('outtel');
           Ext.getCmp("fax").value=_record.get('fax');
           Ext.getCmp("nighttel").value=_record.get('nighttel');
           //Ext.getCmp("parentorgid").value=_record.get('parentorgid');
          myFormWin();   
    };
    
    

    var myFormWin = function() {
        // create the window on the first click and reuse on subsequent
        // clicks
   
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 600,
                height : 300,
                //closeAction : 'hide',
                maximizable:true,
                plain : true,
                title : '修改部门信息',
                items:fsf
                
            });
        }
        newFormWin.on("beforedestroy",
        function(obj){		
        window.location="/usm/depart/departqueryListgrid.jsp"; 			
        return false;	});
        newFormWin.show('New1');
        store.reload();
           
    };


     var fsf = new Ext.FormPanel({
        labelWidth: 80, // label settings here cascade unless overridden
        title: '',
        labelAlign: 'right',
        buttonAlign:'center',    
		bodyStyle:'padding:8px',    
		anchor:'100%',  
		frame:true, 
        items: [{
            layout:'column',
            border:false,
            labelSeparator:'：',
            items:[{
               columnWidth:.5,            
               layout: 'form',            
               border:false,            
               items:[ 
//                    {  
//                       id:'orgid',      
//		               xtype:'textfield',             
//		               fieldLabel: '部门编号',                
//		               name: 'depart.orgid',
//		               readOnly:true,
//		               anchor:'70%'
//	                },
                    {  
                       id:'cnname',      
		               xtype:'textfield',             
		               fieldLabel: '部门名称',                
		               name: 'depart.cnname',
		              allowBlank:false,//不允许为空
		               blankText:'部门名称不能为空',//错误提示内容  
		               checkboxToggle:true,
		               regex: /^[\u4E00-\u9FA5]+$/, 
          			   regexText: '只能输入汉字' ,		                              
		               anchor:'70%',
		              // validator:CheckUserName,//指定验证器  
                       invalidText:'部门名称已经被注册！'  
	                },{
	                	id:'enname',
	                    xtype:'textfield',
	                    fieldLabel: '英文名称',	
	                    name: 'depart.enname',
	                    allowBlank:false,
	                    blankText:'英文名称不能为空',
	                    
		               regex:/^[a-zA-Z]+$/, 
          			   regexText: '只能输入英文字母',
		                anchor:'70%'
	                },{
						id:'orglevel',
	                    xtype:'textfield',
	                    fieldLabel: '部门级别',	
	                    name: 'depart.orglevel',
		                anchor:'70%'
	
	                }
	                ,new Ext.form.ComboBox({
		                id:'status',
	                    fieldLabel: '状态',
	                    name: 'depart.status',
	                    store: new Ext.data.SimpleStore({
	                        fields: ['status'],
	                        data: [['正常'],['禁用'], ['删除']]
	                    }),
	                    displayField:'status',
	                    triggerAction:'all',
	                    emptyText:'选择状态……',
	                    mode: 'local',
	                    selectOnFocus:true,
	                    anchor:'70%'
                   }),
//	                ,{
//						id:'parentorgid',
//	                    xtype:'textfield',
//	                    fieldLabel: '父级部门ID',	
//	                    name: 'depart.parentorgid',
//	                    readOnly:true, 
//		                anchor:'70%'
//	                },
                   new Ext.form.ComboBox({
                        id:'orgtype',
	                    fieldLabel: '部门类型',
	                    name: 'depart.orgtype',
	                    store: new Ext.data.SimpleStore({
	                        fields: ['orgtype'],
	                        data: [['普通部门'],['实用部门']]
	                    }),
	                    displayField:'orgtype',
	                    emptyText:'选择部门类型……',
	                    triggerAction:'all',
	                    mode: 'local',
	                    selectOnFocus:true,
	                    anchor:'70%'
                  }) 
                 
                  
                  ]
              },{
                columnWidth:.4,
                layout: 'form',
                border:false, 
                items: [
		          {
                    id:'intel',
                    xtype:'textfield',
		            fieldLabel: '内线电话',
		            name: 'depart.intel',
		            anchor:'85%'
		          },
		          {
                    id:'outtel',
                    xtype :'textfield',
		            fieldLabel: '外线电话',
		            name: 'depart.outtel',
		            anchor:'85%'
		          },{
		            id:'fax',
                    xtype:'textfield',
		            fieldLabel: '传真',
		            name: 'depart.fax',
		            anchor:'85%'
		          } ,{
                    id:'nighttel',
                    xtype:'textfield',
		            fieldLabel: '夜间值班电话',
		            name: 'depart.nighttel',
		            anchor:'85%'
		          }, {
	                    id:'serialindex',
	                    xtype:'textfield',
	                    fieldLabel: '序号',
	                    name: 'depart.serialindex',
	                    readOnly:true,
	                    anchor:'85%'
	                   
		            }
		           ]
              }]

        },  {
	                    id:'memo',
	                    xtype:'textarea',
	                    fieldLabel: '备注',
	                    name: 'depart.memo',
	                    anchor:'83%'
	                  
		            }],
 buttons: [{
            text: '修 改',
            handler: function(){
             var _record = departgrid.getSelectionModel().getSelected();
             var cnname = Ext.get('cnname').dom.value;
             var enname = Ext.get('enname').dom.value;
           
             if(enname=='')
             {
             	Ext.MessageBox.alert('英文名','英文名不能为空');
             }
              if(cnname=='')
             {
             	Ext.MessageBox.alert('部门名称','部门名称不能为空');
             }
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        url: '/usm/departModify.do?orgid=' + _record.get('orgid'),
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "修改成功！");
                            store.reload();
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据修改失败！");
                            this.disabled=false;
                        }
                    });
                    
                }
            }
        }, {
            text: '取 消',
            handler: function() {
                fsf.form.reset();
            }
        }, {
            text: '关 闭',
            handler: function() {
               window.location="/usm/depart/departqueryListgrid.jsp"; 
               Ext.get('forum-tree').reload(); 
            }
        }
        
        
        
        ]

    });


    
    
      
    function showDeleteMessage(){
        Ext.MessageBox.confirm('消息', '确认删除？',doDel);
    }
    function doDel(btn){
        if(btn=='yes'){
            var row=departgrid.getSelections();
            var jsonData = "";
            for(var i=0,len=row.length;i<len;i++){
                var ss = row[i].get("orgid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息', '请选择要删除的数据。');
            }else{
		               Ext.Ajax.request({
		                //请求地址
		                method: 'POST',      
		                url: '../departPointDel.do?orgidjsonData=' + jsonData,
		                //成功时回调      
		                success: function(response, options){
		                   Ext.MessageBox.alert('消息', '删除成功');
                           store.reload();
		                },
		                //成功时回调      
		                failure: function(response, options){
		                    Ext.MessageBox.show({
                               title:'消息',
                               msg: '删除失败！',
                               buttons: Ext.Msg.OK,
                               icon: Ext.MessageBox.ERROR
                           });
		                }
		            });
            }
        }
        
    }

         function querydepartMessage()
         {
	
	         var name = Ext.get('name').dom.value;
    	     store.load({params:{start:0, limit:15,name:name}});
	        
          }
    
});

