
 
Ext.onReady(function(){

    var newFormWin;
    
    var currentRowNumber;
    
    Ext.QuickTips.init();

    
   // function departNameClike(orgid,cnname){
    
     //  return " <a href=javascript:window.location='/usm/person/orgPersonListgrid.jsp?orgid="+orgid+"'  style='text-decoration: none;color: #blue' >" + cnname +"</a>";
  //  }
  
    
    function departNameClike(orgid,cnname){
       return " <a href=javascript:openPersnonList('"+orgid+"')  style='text-decoration: none;color: #blue' >" + cnname +"</a>";
    }
    


    
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
    store.load({params:{start:0, limit:15}});
    
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
		   //hidden :false,
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
           allowBlank:true,
           blankText:'部门名称不能为空',
           renderer: function(value,metadata,record,rowIndex){
			   return departNameClike(record.id,value);
			  },
           width: 100
        },{
           header: "英文名称",
           dataIndex: 'enname',
           align:'center',
           allowBlank:true,
           blankText:'英文名称不能为空',
           width: 100
        },{
           header: "状态",
           dataIndex: 'status',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "部门类型",
           dataIndex: 'orgtype',
           align:'center',
           hidden :true,
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
           hidden :true,
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
           hidden :false,
           width: 100
        },{
           header: "排序",
           dataIndex: 'serialindex',
           align:'center',
           hidden :true,
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
        tbar:['按部门名称：',name, {// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",   
                    cls : "x-btn-text-icon",   
                    handler :querydepartMessage    
            
        }],
        
         bbar: new Ext.PagingToolbar({
            pageSize: 15,
            store: store,
            cls:'x-btn-text-icon details',
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "当前没有可以显示的数据"
            
        }), 
        width:780,
        height:470,
        frame:true,
        title:'',
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
    departgrid.render();
    departgrid.getView().refresh();
    //el:指定html元素用于显示grid
  

    departgrid.on("rowdblclick", function(departgrid) {
        loadFormData(departgrid);
       //alert(form1.reader);
    });

    // 载入被选择的数据行的表单数据
    var loadFormData = function(departgrid) {
         
        var _record = departgrid.getSelectionModel().getSelected();
				
           Ext.getCmp("orgid").value=_record.get('orgid');
           Ext.getCmp("cnname").value=_record.get('cnname');
           Ext.getCmp("enname").value=_record.get('enname');
           Ext.getCmp("status").value=_record.get('status');
           Ext.getCmp("orgtype").value=_record.get('orgtype');
           Ext.getCmp("status").value=_record.get('status');
           Ext.getCmp("memo").value=_record.get('memo');
           Ext.getCmp("orglevel").value=_record.get('orglevel');
           Ext.getCmp("serialindex").value=_record.get('serialindex');
           Ext.getCmp("intel").value=_record.get('intel');
           Ext.getCmp("outtel").value=_record.get('outtel');
           Ext.getCmp("fax").value=_record.get('fax');
           Ext.getCmp("nighttel").value=_record.get('nighttel');
           Ext.getCmp("parentorgid").value=_record.get('parentorgid');
         
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
                title : '部门的详细信息',
                items:fsf
                
            });
        }
        newFormWin.on("beforedestroy",
        function(obj){		
        window.location="departaddressList.jsp"; 			
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
                    {  
                       id:'orgid',      
		               xtype:'textfield',             
		               fieldLabel: '部门编号',                
		               name: 'depart.orgid',
		               readOnly:true,
		               anchor:'70%'
	                },
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
	                    regex:/^([0-9]{1,6})$/,
	                    regexText:'请输入正确的部门级别',
		                anchor:'70%'
	
	                },{
						id:'parentorgid',
	                    xtype:'textfield',
	                    fieldLabel: '父级部门ID',	
	                    name: 'depart.parentorgid',
	                    readOnly:true, 
		                anchor:'70%'
	                },
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
		                
		             new Ext.form.ComboBox({
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
	                    anchor:'85%'
                  }),{
                    id:'intel',
                    xtype:'textfield',
		            fieldLabel: '内线电话',
		            regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{3,4}$/,    
		            regexText:'请输入正确的内线电话号码',
		            name: 'depart.intel',
		            anchor:'85%'
		          },
		          {
                    id:'outtel',
                    xtype :'textfield',
		            fieldLabel: '外线电话',
		            regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		            regexText:'请输入正确的外线电话号码',
		            name: 'depart.outtel',
		            anchor:'85%'
		          },{
		            id:'fax',
                    xtype:'textfield',
		            fieldLabel: '传真',
		            name: 'depart.fax',
		            regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		            regexText:'请输入正确的传真电话号码',
		            anchor:'85%'
		          } ,{
                    id:'nighttel',
                    xtype:'textfield',
		            fieldLabel: '夜间值班电话',
		            name: 'depart.nighttel',
		            regex:/^((0[1-9]{3})?(0[12][0-9])?[-])?\d{6,8}$/,    
		            regexText:'请输入正确的夜间值班电话号码',
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
	                    anchor:'83%'
	                  
		            }],
 buttons: [{
            text: '返回',
            handler: function() {
               window.location="departaddressList.jsp";  
            }
        }
        
        
        
        ]

    });
         function querydepartMessage()
         {
	
	         var name = Ext.get('name').dom.value;
    	     store.load({params:{start:0, limit:15,name:name}});
	        
          }
    
});

