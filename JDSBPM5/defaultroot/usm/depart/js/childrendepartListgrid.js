
Ext.onReady(function(){

    var newFormWin;
    Ext.QuickTips.init();
    var xg = Ext.grid;
    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: '/usm/childDepartListGrid.do?parentorgid='+parentorgid+''   //从此页获取数据
        }),
        
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'orgid',
            fields: [
            {name: 'orgid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'enname', type: 'string'},
            {name: 'status', type: 'string'},
            {name: 'orgtype', type: 'string'},
            {name: 'intel', type: 'string'}, 
            {name: 'memo', type: 'string'},
            {name: 'orglevel', type: 'string'},
            {name: 'parentorgid', type: 'string'},
            {name: 'outtel', type: 'string'},
            {name: 'nighttel', type: 'string'}, 
            {name: 'fax', type: 'string'}, 
            {name: 'serialindex', type: 'string'}    
            ]
        })
    });
  store.setDefaultSort('serialindex', 'ASC');  
  store.load({params:{start:0, limit:100}});
  
    var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm = new Ext.grid.ColumnModel([sm,
    new Ext.grid.RowNumberer(
	     {
		     header:'序号',
		     width:32,
		     align:'center'
	     }
        )
        ,{
           id: 'orgid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "部门编号",
           dataIndex: 'orgid',
           align:'center',
		   hidden :true,
           width: 60
        }
        ,{
           header: "部门名称",
           dataIndex: 'cnname',
           align:'center',
           width: 100
        },{
           header: "英文名称",
           dataIndex: 'enname',
           align:'center',
           width: 100
        },{
           header: "状态",
           dataIndex: 'status',
           align:'center',
           width: 100
        },{
           header: "部门类型",
           dataIndex: 'orgtype',
           align:'center',
           width: 100
        },{
           header: "内线电话",
           dataIndex: 'intel',
           align:'center',
           width: 100
        },{
           header: "备注",
           dataIndex: 'memo',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "部门级别",
           dataIndex: 'orglevel',
           align:'center',
           hidden :true,
           width: 100
        }
        ,{
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
        store:store,
	    cm:cm,
	    sm:sm,
        view:view,
        trackMouseOver:true,
        enableDragDrop: true,
        ddGroup: 'GridDD',
        autoShow : true,
        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true
        },
      
        
        tbar:['部门名称：', 
               name, 
               {// 查询按钮   
                      id: 'newModelButton',    
                    icon: "/usm/img/search.png",
                    text: '查 询',
                     cls: "x-btn-text-icon",   
                 handler:querydepartMessage
               },
               {
                 text:'新  增',
	             tooltip:'新增',
	             iconCls:'add',
	             handler:function(){
	              createOrgWindow(orglevel,parentorgid);
	             }    
		        }/*,'-',{
		            text:'删  除',
		            tooltip:'删除',
		            iconCls:'remove',
		            handler:showDeleteMessage
		     
		        }*/,'-',{
		            text:'保存排序',
		            tooltip:'保存排序',
		            iconCls:'save',
		            handler:function(){
						sm.selectAll();
						var row=departgrid.getSelections();
			            var txtCheckValue = "";
			            for(var i=0,len=row.length;i<len;i++){
			                var ss = row[i].get("orgid");
			                txtCheckValue = txtCheckValue + ss + ",";
			            }
			            	Ext.Ajax.request({ 
							method:'get', 
							url:"/usm/departSaveSort.do?txtCheckValue="+txtCheckValue, 
							success : function(result, action)
							{ 
								Ext.MessageBox.alert('消息', '操作成功');
								store.setDefaultSort('serialindex', 'ASC');  
							    store.reload();
							}, 
						    failure : function(form, action) 
						    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
						}); 
		            }
		        }],
        
         bbar: new Ext.PagingToolbar({
            pageSize: 100,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "当前没有可以显示的数据"
        }),     
        
        width:930,
        height:660,
        frame:true,
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
    departgrid.render();
    
    departgrid.on("rowdblclick", function(departgrid) {
        loadFormData(departgrid);
        
        //alert(form1.reader);
    });
    var ddrow = new Ext.dd.DropTarget(departgrid.getView().mainBody, {
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
                if(!this.copy) store.remove(rowData);
                store.insert(index, rowData);
            }
        }
    }); 
    // 载入被选择的数据行的表单数据
    var loadFormData = function(departgrid) {
        var _record = departgrid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
           //Ext.getCmp("orgid").value=_record.get('orgid');
           Ext.getCmp("cnname").value=_record.get('cnname');
           Ext.getCmp("enname").value=_record.get('enname');
           
           Ext.getCmp("status").value=_record.get('status');
           Ext.getCmp("orgtype").value=_record.get('orgtype');
           Ext.getCmp("status").value=_record.get('status');
           
             if(_record.get('intel')=='null')
           {
           	    Ext.getCmp("intel").value='';
           }
           else
           {
                Ext.getCmp("intel").value=_record.get('intel');
           }
           
           
            if(_record.get('memo')=='null')
           {
           	    Ext.getCmp("memo").value='';
           }
           else
           {
                Ext.getCmp("memo").value=_record.get('memo'); 
           }
           
          if(_record.get('orglevel')=='null')
           {
           	    Ext.getCmp("orglevel").value='';
           }
           else
           {
                Ext.getCmp("orglevel").value=_record.get('orglevel'); 
           }
           
           //Ext.getCmp("parentorgid").value=_record.get('parentorgid');
            if(_record.get('outtel')=='null')
           {
           	    Ext.getCmp("outtel").value='';
           }
           else
           {
                Ext.getCmp("outtel").value=_record.get('outtel'); 
           }
           
            if(_record.get('nighttel')=='null')
           {
           	    Ext.getCmp("nighttel").value='';
           }
           else
           {
                Ext.getCmp("nighttel").value=_record.get('nighttel');
           }
           
           
            if(_record.get('fax')=='null')
           {
           	    Ext.getCmp("fax").value='';
           }
           else
           {
                Ext.getCmp("fax").value=_record.get('fax');
           }
           
        
           myFormWin();	
          	
        }
    };

    var myFormWin = function() {
        if (!newFormWin) {
           
            var newFormWin=new Ext.Window({	title:"修改部门信息",	
               layout : 'fit',
                width : 600,
                height : 350,
                maximizable:true,                
                plain : true,
                title : '修改部门信息',
                items:fsf 	
            	});		
            	                              
        }
        newFormWin.on("beforedestroy",
        function(obj){		
        window.location="childrendepartListgrid.jsp?parentorgid="+parentorgid; 			
        return false;	});
        newFormWin.show('New1');
        store.reload();
        
    };
    function toggleDetails(btn, pressed){
        var view = rolegrid.getView();
        view.showPreview = pressed;
        view.refresh();
    } 
     var fsf = new Ext.FormPanel({
        labelWidth: 80, // label settings here cascade unless overridden
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
//               		{  
//                       id:'orgid',      
//		               xtype:'textfield',             
//		               fieldLabel: '部门编号',                
//		               name: 'depart.orgid',
//		               readOnly:true, 
//		               anchor:'85%'
//	                },
	                {   
	                   id:'cnname',             
		               xtype:'textfield',             
		               fieldLabel: '* 部门名称',                
		               name: 'depart.cnname',   
		               allowBlank:false,//不允许为空
		               blankText:'部门名称不能为空',//错误提示内容  
		               checkboxToggle:true,
		               regex: /^[\u4E00-\u9FA5]+$/, 
          			   regexText: '只能输入汉字' ,		                              
		             
		              // validator:CheckUserName,//指定验证器  
                       invalidText:'部门名称已经被注册！' ,              
		               anchor:'85%'
	                },
	                {
	                    id:'enname',
	                    xtype:'textfield',
	                    fieldLabel: '* 英文名称',	
	                    name: 'depart.enname',
	                    blankText:'英文名称不能为空',
		                regex:/^[a-zA-Z]+$/, 
          			    regexText: '只能输入英文字母',
		                anchor:'85%'
	                }, 
	                {
					    id:'orglevel',
	                    xtype:'textfield',
	                    fieldLabel: '部门级别',	
	                    name: 'depart.orglevel',
		                anchor:'85%'
	
	                },
		                
		            new Ext.form.ComboBox({
		           		id:'status',
	                    fieldLabel: '状态',
	                    name: 'depart.status',
	                    store: new Ext.data.SimpleStore({
	                        fields: ['status'],
	                        data: [['正常'],['禁用'], ['删除']]
	                    }),
	                    displayField:'status',
	                    emptyText:'选择状态……',
	                    mode: 'local',
	                   // value:'status',
	                    typeAhead: true,
   						triggerAction: 'all',	
	                    allowBlank:false,
	                    selectOnFocus:true,
	                    readOnly:true,
	                    anchor:'85%'
                  })
//	                ,{
//	                    id:'parentorgid',
//	                    xtype:'textfield',
//	                    fieldLabel: '父级部门ID',	
//	                    name: 'depart.parentorgid',
//	                    readOnly:true, 
//		                anchor:'85%'
//	                }
	                ,
		           new Ext.form.ComboBox({
		           		id:'orgtype',
	                    fieldLabel: '部门类型',
	                    name: 'depart.orgtype',
	                    store: new Ext.data.SimpleStore({
	                        fields: ['orgtype'],
	                        data: [['普通部门'],['实体部门']]
	                    }),
	                    displayField:'orgtype',
	                    emptyText:'选择部门类型……',
	                    typeAhead: true,
   						triggerAction: 'all',	
	                    mode: 'local',
	                    allowBlank:false,
	                    selectOnFocus:true,
	                    anchor:'85%'
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
		            anchor:'100%'
		          } ,
                 {
                    id:'outtel',
                    xtype :'textfield',
		            fieldLabel: '外线电话',
		            name: 'depart.outtel',
		            anchor:'100%'
		          } ,
		           {
                    id:'nighttel',
                    xtype:'textfield',
		            fieldLabel: '夜间值班电话',
		            name: 'depart.nighttel',
		            anchor:'100%'
		          },
		           {
		            id:'fax',
                    xtype:'textfield',
		            fieldLabel: '传真',
		            name: 'depart.fax',
		            anchor:'100%'
		          }
		          ]
              }]

        },
	              {
	                    id:'memo',
	                    xtype:'textarea',
	                    fieldLabel: '备注',
	                    name: 'depart.memo',
	                    anchor:'89%'
	                    
		          }],

 buttons: [{
            text: '修 改',
            handler: function(){
             var _record = departgrid.getSelectionModel().getSelected();
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
                            this.disabled=false;
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
            text: '返 回',
            handler: function() {
               window.location="childrendepartListgrid.jsp?parentorgid="+parentorgid; 
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
		                url: '/usm/departPointDel.do?orgidjsonData=' + jsonData,
		                //成功时回调      
		                success: function(response, options){
		                   Ext.MessageBox.alert('消息', '删除成功');
                           store.reload();
		                },
		                //成功时回调      
		                failure: function(response, options){
		                    Ext.MessageBox.show({
                               title:'消息',
                               msg: '删除成功！',
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
	        // var name = Ext.getCmp('name').getValue();
	         var name = Ext.get('name').dom.value;
    	     store.load({params:{start:0, limit:100,name:name}});
	        
          }
});
function createOrgWindow(orglevel,parentorgid){
        var win=new Ext.Window({
	            title:"添加部门基本信息",
	            width:600,
	            height:300,
	            maximizable:true,
			  	closeAction:'close',
			  	closable:true,
			  	collapsible:true,
			  	autoScroll:false,
			  	items:new Ext.Panel({
			        title: '',        
				  	height:300,
			        width:600,
			        html: '<iframe scrolling="yes" frameborder="0" src="/usm/depart/departaddwindow.jsp?orglevel='+orglevel+'&parentorgid='+parentorgid+'" height=100% width=100%></iframe>'
			    })
			    
	        	});
	         win.show();
}