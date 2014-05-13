/*
 * 用户组分配角色信息
 * 
 */
Ext.onReady(function(){

    var newFormWin;
    var persongrid;
    var currentRowNumber;
    Ext.QuickTips.init();
    var xg = Ext.grid;
    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
             url: "/usm/lookOverUserGroupRoleInfo.do?roleid="+roleid   //从此页获取给用户组添加角色的数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'uuid',
            fields: [
            {name: 'uuid', type: 'string'},
            {name: 'grpid', type: 'string'},
            {name: 'grpname', type: 'string'},
            {name: 'rcnname', type: 'string'}
            ]
        })
    });
    store.setDefaultSort('uuid', 'desc');
    var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm = new Ext.grid.ColumnModel([sm, new Ext.grid.RowNumberer(
	     {
		     header:'序号',
		     width:32,
		     align:'center'
	     }
        ),{
           id:'uuid',
           header: "uuid",
           dataIndex: 'uuid',
           align:'center',
           hidden :'true',
           width: 60
        },{
           header: "用户组编号",
           dataIndex: 'grpid',
           align:'center',
           hidden:true,
           width: 60
        },{
           header: "用户组名称",
           dataIndex: 'grpname',
           align:'center',
           width: 100
        },{
           header: "角色名称",
           dataIndex: 'rcnname',
           align:'center',
           width: 100
        }
        
        ]);
       cm.defaultSortable = true;
      //配置视图信息
      var viewrole=new Ext.grid.GridView({forceFit:true,sortAscText :'正序', sortDescText :'倒序'});
      viewrole.columnsText='列显示/隐藏';
      var name = new Ext.form.TextField({   
                width:175, 
                name: 'name',
                allowBlank:true 
            });
            
     var persongrid = new Ext.grid.GridPanel({
        id:'button-grid',
        width:300,
        view:viewrole,
        store:store,
	    cm:cm,
	    sm:sm,
        viewConfig: {
            forceFit:true
        },
        buttonAlign:'center',
        tbar:['用户组：', name, {// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png", 
                    text:'查  询',
                    cls : "x-btn-text-icon",   
                    handler :querynameMessage     
            },
            '-',
            {
		            text:'选择用户组',
		            tooltip:'选择用户组',
		            iconCls:'add',
		            handler: function ()
		            {
                           showPersonPosition();
                    }
            },
            '-',
            {
                  text:'删除用户组角色',
	              tooltip:'删除',
	              iconCls:'remove',
	              handler:showDeleteMessage
	              
             }],
        
         bbar: new Ext.PagingToolbar({
            pageSize:10,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "无记录"
        }),     
        
        width:764,
        height:510,
        frame:true,
        iconCls:'icon-grid',
        renderTo: document.body
    });
    persongrid.render();	        
    store.load({params:{start:0, limit:10}});
    var fsf = new Ext.FormPanel({
    labelWidth: 75, 
    	id:'fsf',
		labelAlign: 'right',    
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:5px 5px 0',   
		anchor:'100%', 
		autoScroll:true, 
		frame:true,    
        items: [{
            checkboxToggle:true,
            closable:true,//是否可以关闭 
            xtype:'fieldset',
            title: '',
            autoHeight:true,
            defaults: {width: 400},
            layout:'column',
            border:false,
            labelSeparator:'：',
            items:[{  
               columnWidth:.5,            
               layout: 'form',            
               border:false,            
               items: [
               		  {       
               		   id:'cnname',     
		               xtype:'textfield',                
		               fieldLabel: '中文名字',                
		               name: 'personinfo.cnname',                
		               anchor:'82%'
	                   },{ 
	                   id:'enname',               
		               xtype:'textfield',                
		               fieldLabel: '英文名字',                
		               name: 'personinfo.enname',                
		               anchor:'82%'
	                   },
	                   {
	                    xtype:'textfield',	
	                    fieldLabel: '密码',	
	                    name: 'personaccount.password',
		                anchor:'82%'
	                   },          
		                new Ext.form.ComboBox({
	                    fieldLabel: '账号状态',
	                    name: 'personaccount.accountstat',
	                    //hiddenName:&apos;hyear&apos;,//提交到后台的input的name，重要
	                     //readOnly : true,//是否只读 
	                    
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['accountstat'],
	                        data: [['临时账号'], ['普通账号(连续120天不登陆，即失效（被禁止）)'],['永久']]
	                    }),	                     
	                    displayField:'accountstat',
	                    emptyText:'选择账号状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    allowBlank:false,
	                    selectOnFocus:true,
	                    anchor:'82%'
	                   }),{                
		                xtype:'textfield',                
		                fieldLabel: '账号有效期:',                
		                name: 'personaccount.accountttl',                
		                anchor:'82%'
	                  }
	                   ]
                    },{
                		columnWidth:.4,
                        layout: 'form',
                        border:false, 
                     items: [
                    {
	                    xtype:'textfield',
	                    fieldLabel: '密码问题',
	                    name: 'personaccount.passquestion',
	                    anchor:'95%'
                      },{
                        xtype:'textfield',
	                    fieldLabel: '问题答案',
	                    name: 'personaccount.passanswer',
	                    anchor:'95%'
                      },
	                  new Ext.form.ComboBox({
	                    fieldLabel: '性别',
	                    name: 'personinfo.sex',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['sex'],
	                        data: [['保密'], ['男'],['女']]
	                    }),	                     
	                    displayField:'sex',
	                    emptyText:'选择性别状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    
	                    selectOnFocus:true,
	                    anchor:'95%'
	                   }),
	                   new Ext.form.DateField({
	                    xtype:'textfield',
                        fieldLabel: '出生日期',
                        format:'Y-m-d h:i:s',
                        name: 'personinfo.birthday',
                        anchor:'95%'
                        }),
                        {                
		               xtype:'textfield',                
		               fieldLabel: '学历',                
		               name: 'personinfo.xueli',                
		               anchor:'95%'
	                   }
	                   ]

            }]

        },
        { 
            xtype:'fieldset',
            collapsible: true,
            closable:true,//是否可以关闭 
            autoHeight:true,
            title: '',
            defaults: {width: 400},
            layout:'column',
            border:false,
            labelSeparator:'：',
            items:[{  
               columnWidth:.5,            
               layout: 'form',            
               border:false,            
               items: [
	                   {                
		               xtype:'textfield',                
		               fieldLabel: '工作',                
		               name: 'personinfo.job',                
		               anchor:'82%'
	                   },{                
		               xtype:'textfield',                
		               fieldLabel: '职务',                
		               name: 'personinfo.duty',                
		               anchor:'82%'
	                   },
	                   {                
		               xtype:'textfield',                
		               fieldLabel: '相片',                
		               name: 'personinfo.photo',                
		               anchor:'82%'
	                   },{                
		               xtype:'textfield',                
		               fieldLabel: '类型',                
		               name: 'personinfo.type',                
		               anchor:'82%'
	                   },
	                   new Ext.form.ComboBox({
	                    fieldLabel: '婚否',
	                    name: 'personinfo.marry',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['marry'],
	                        data: [['保密'], ['已婚'],['未婚'],['离异']]
	                    }),	                     
	                    displayField:'marry',
	                    emptyText:'选择婚否状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    
	                    selectOnFocus:true,
	                    anchor:'82%'
	                   }),
	                    new Ext.form.ComboBox({
	                    fieldLabel: '政治面貌',
	                    name: 'personinfo.politicalstat',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['politicalstat'],
	                        data: [['群众'], ['团员'],['党员'],['其它']]
	                    }),	                     
	                    displayField:'politicalstat',
	                    emptyText:'选择政治面貌状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    
	                    selectOnFocus:true,
	                    anchor:'82%'
	                   }),{
	                    xtype:'textfield',                
		                fieldLabel: '家庭电话:',                
		                name: 'personinfo.hometel',                
		                anchor:'82%'
	                   },{
	                    xtype:'textfield',
	                    fieldLabel: '家庭传真',
	                    name: 'personinfo.homefax',
	                    anchor:'82%'
                      },{
                        id:'officetel',
                        xtype:'textfield',                
		                fieldLabel: '办公室电话:',                
		                name: 'personinfo.officetel',                
		                anchor:'82%'
	                    
	                   },{
                        xtype:'textfield',
	                    fieldLabel: '办公传真号',
	                    name: 'personinfo.officefax',
	                    anchor:'82%'
                      }]
                    },{
                		columnWidth:.4,
                        layout: 'form',
                        border:false, 
                     items: [
                      {
                        id:'mobile',
                        xtype:'textfield',
	                    fieldLabel: '手机',
	                    name: 'personinfo.mobile',
	                    anchor:'95%'
                      },
                       {
                        xtype:'textfield',
	                    fieldLabel: '邮箱',
	                    name: 'personinfo.email1',
	                    anchor:'95%'
                      },
                     {
                        xtype:'textfield',
	                    fieldLabel: '祖籍',
	                    name: 'personinfo.nativeplace',
	                    anchor:'95%'
                      },{
                        xtype:'textfield',
	                    fieldLabel: '民族',
	                    name: 'personinfo.nation',
	                    anchor:'95%'
                      },{
                        xtype:'textfield',
	                    fieldLabel: '国籍',
	                    name: 'personinfo.country',
	                    anchor:'95%'
                      },{
                        xtype:'textfield',
	                    fieldLabel: '城市',
	                    name: 'personinfo.city',
	                    anchor:'95%'
                      },
                      {
                        xtype:'textfield',
	                    fieldLabel: '邮编',
	                    name: 'personinfo.zip',
	                    anchor:'95%'
                      },
                       new Ext.form.ComboBox({
                    	fieldLabel: '最高学历',
                    	name: 'personinfo.lastedulevel',
                   		store: new Ext.data.SimpleStore({
                        fields: ['lastedulevel'],
                        data: [['无'],['小学'], ['初中'],['高中(中专、技校)'],['大专'],['本科'],['研究生'],['博士']]
                       }),
	                    displayField:'lastedulevel',
	                    emptyText:'选择最高学历……',
	                    mode: 'local',
	                   
	                    selectOnFocus:true,
	                    anchor:'95%'
                      }),
                        new Ext.form.ComboBox({
                    	fieldLabel: '最高学位',
                    	name: 'personinfo.lastdegree',
                   		store: new Ext.data.SimpleStore({
                        fields: ['lastdegree'],
                        data: [['无学位'],['学士'], ['硕士'],['博士(后)']]
                       }),
	                    displayField:'lastdegree',
	                    emptyText:'选择最高学位……',
	                    mode: 'local',
	                   
	                    selectOnFocus:true,
	                    anchor:'95%'
                      })     
                    
	                 ]

            }]
        },{
        
            xtype:'fieldset',
            collapsible: true,
            closable:true,//是否可以关闭 
            autoHeight:false,
            title: '',
            defaults: {width: 400},
            border:false,
            labelSeparator:'：',
            items:[
            		 {
                        xtype:'textfield',
	                    fieldLabel: '其他信息',
	                    name: 'personinfo.otherinfo',
	                    anchor:'90%'
                      },{  
                              
		               xtype:'textfield',                
		               fieldLabel: '家庭住址',                
		               name: 'personinfo.homeadd',                
		               anchor:'90%'
	               },{          
		               xtype:'textfield',                
		               fieldLabel: '联系地址',                
		               name: 'personinfo.connectaddr',                
		               anchor:'90%'
	               },{          
		               xtype:'textfield',                
		               fieldLabel: '办公地址',                
		               name: 'personinfo.officeadd',                
		               anchor:'90%'
	               },{          
		               xtype:'textfield',                
		               fieldLabel: '毕业院校',                
		               name: 'personinfo.school',                
		               anchor:'90%'
	               }]
        }
        
        ],

 buttons: [{
            text: '保存',
            handler: function(){
             var _record = persongrid.getSelectionModel().getSelected();
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({ 
                        url: '/usm/personInfoModify.do?personid=' + _record.get('personid'),
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！");
                            persongrid.getView().refresh(); 
                            this.disabled=false;
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据保存失败！");
                            this.disabled=false;
                        }
                    });
                  
                }
            }
        }, {
            text: '重置',
            handler: function() {
                fsf.form.reset();
            }
        }
        ]

    });
    
    var name1 = new Ext.form.TextField({   
                width:175, 
                name: 'name1',
                allowBlank:true 
            });   
    
   //查看所有岗位人员
   function showPersonPosition(){
	window.parent.parent.createUserGroupRoleWindow(roleid);
    }
      
    function showDeleteMessage(){
        Ext.MessageBox.confirm('消息', '确认解除此用户组所在的角色吗？',doDel);
    }
    
    function doDel(btn){
        if(btn=='yes'){
            var row=persongrid.getSelections();
            var jsonData = "";
            for(var i=0,len=row.length;i<len;i++){
                var ss = row[i].get("uuid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息', '请选择要解除的用户组数据。');
            }else{
		             Ext.Ajax.request({
		                //请求地址
		                method: 'POST',      
		                url: '/usm/userGroupRoleDel.do?roleidjsonData=' + jsonData,
		                //成功时回调      
		                success: function(response, options){
		                   Ext.MessageBox.alert('消息', '删除成功');	                   
                           store.reload();
                           window.location="getRoleUserGroupNameDetailInfo.do?roleid="+roleid; 
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
        store.reload();
    }
    
        
         function querynameMessage()
    {
       	         var name = Ext.get('name').dom.value;
    	store.load({params:{start:0, limit:10,name:name}});
    
    }
           function queryunameMessage()
    {
      	         var name = Ext.get('uname').dom.value;
    	store2.load({params:{start:0, limit:10,name:name}});
    
    }
    
    function addposition()
    {
          Ext.MessageBox.confirm('添加用户组', '确认将此用户组添加到 [ '+cnname+' ] 角色里吗？',doaddpostion);
    }
    function doaddpostion(btn)
    {
        if(btn=='yes'){

       
        }
    }
    
    
  
});

