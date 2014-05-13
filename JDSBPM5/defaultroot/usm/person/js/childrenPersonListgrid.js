Ext.BLANK_IMAGE_URL = '/usm/resources/images/default/s.gif';
Ext.onReady(function(){
    var newFormWin;
    Ext.QuickTips.init();
    var xg = Ext.grid;
    var p = "";
    var store = new Ext.data.Store({
      proxy: new Ext.data.HttpProxy({   
            url: '/usm/orgPersonListGrid.do?orgid='+orgid+''  //从此页获取数据
        }),   
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'personid',
            fields: [
            {name: 'personid', type: 'string'},
            {name: 'enname', type: 'string'},
            {name: 'password', type: 'string'},
            {name: 'accountstat', type: 'string'},
            {name: 'accountttl', type: 'string'},
            {name: 'passquestion', type: 'string'},
            {name: 'passanswer', type: 'string'},
            {name: 'email1', type: 'string'},
            {name: 'birthday', type: 'string'},
            {name: 'zip', type: 'string'},
            {name: 'city', type: 'string'},
            {name: 'country', type: 'string'},
            {name: 'nation', type: 'string'},
            {name: 'nativeplace', type: 'string'},
            {name: 'job', type: 'string'},
            {name: 'duty', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'officetel', type: 'string'},
            {name: 'photo', type: 'string'},
            {name: 'officefax', type: 'string'},
            {name: 'hometel', type: 'string'},
            {name: 'homefax', type: 'string'},
            {name: 'otherinfo', type: 'string'},
            {name: 'homeadd', type: 'string'},
            {name: 'officeadd', type: 'string'},
            {name: 'connectaddr', type: 'string'},
            {name: 'school', type: 'string'},
            {name: 'mobile', type: 'string'},
            {name: 'type', type: 'string'},
            {name: 'sex', type: 'string'},
            {name: 'userid', type: 'string'},//zhongqun 2011-11-22 新增rtx账号 属性
            {name: 'marry', type: 'string'},
            {name: 'politicalstat', type: 'string'},
            {name: 'lastedulevel', type: 'string'},
            {name: 'lastdegree', type: 'string'},
            {name: 'indextype', type: 'string'},
            {name: 'uuid', type: 'string'},
            {name: 'pager', type: 'string'}
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
        ),{
	        id: 'personid', 
	        header: "uuid",
	        dataIndex: 'uuid',
	        align:'center',
			hidden :true,
	        width: 60
        },{
           id: 'personid', 
           header: "人员编号",
           dataIndex: 'personid',
           align:'center',
		   hidden :true,
           width: 60
        },{
           header: "姓名",
           dataIndex: 'cnname',
           align:'center',
           width: 100
        },{
           header: "英文",
           dataIndex: 'enname',
           align:'center',
           width: 100
        },{
           header: "密码",
           dataIndex: 'password',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "账号状态",
           dataIndex: 'accountstat',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "账号有效期",
           dataIndex: 'accountttl',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "密码问题",
           dataIndex: 'passquestion',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "问题回答",
           dataIndex: 'passanswer',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "邮箱",
           dataIndex: 'email1',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "出生日期",
           dataIndex: 'birthday',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "邮编",
           dataIndex: 'zip',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "城市",
           dataIndex: 'city',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "国籍",
           dataIndex: 'country',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "民族",
           dataIndex: 'nation',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "祖籍",
           dataIndex: 'nativeplace',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "工作",
           dataIndex: 'job',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "职务",
           dataIndex: 'duty',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "办公电话",
           dataIndex: 'officetel',
           
           align:'center',
           width: 60
        },{
           header: "类型",
           dataIndex: 'type',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "办公传真",
           dataIndex: 'officefax',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "家庭电话",
           dataIndex: 'hometel',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "家庭传真",
           dataIndex: 'homefax',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "其它信息",
           dataIndex: 'otherinfo',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "家庭地址",
           dataIndex: 'homeadd',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "办公地址",
           dataIndex: 'officeadd',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "联系地址",
           dataIndex: 'connectaddr',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "毕业学校",
           dataIndex: 'school',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "手机",
           dataIndex: 'mobile',
           align:'center',
           width: 60
        },{
           header: "相片",
           dataIndex: 'photo',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "性别",
           dataIndex: 'sex',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "账号",
           dataIndex: 'userid',
           align:'center',
		   hidden :true,
           width: 60
        },{
           header: "婚否",
           dataIndex: 'marry',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "政治面貌",
           dataIndex: 'politicalstat',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "最高学位",
           dataIndex: 'lastdegree',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "话机IP",
           dataIndex: 'pager',
           align:'center',
           hidden :false,
           width: 60
        },{
           header: "排序",
           dataIndex: 'indextype',
           align:'center',
           hidden :false,
           width: 60
        }
        
        ]);
      cm.defaultSortable = true;
      //配置视图信息
      var view=new Ext.grid.GridView({forceFit:true,sortAscText :'正序', sortDescText :'倒序'});
      view.columnsText='列显示/隐藏';  
      var name = new Ext.form.TextField({   
                width:175, 
                name: 'name',
                allowBlank:true 
      }); 
      var persongrid = new Ext.grid.GridPanel({
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
            forceFit:true
        },
        buttonAlign:'center',
        tbar:['姓   名：',name, {// 查询按钮   
                    id:'newModelButton',    
                    icon:"/usm/img/search.png", 
                    text:'查  询',
                    cls:"x-btn-text-icon",   
                    handler :querydepartMessage     
            },{
            text:'新  增',
            tooltip:'新增',
            iconCls:'add',
            handler:function(){
            if(parentorgid=='10000001')
            {
                Ext.Msg.alert("提示", "此组织不能添加人员，请在您的部门下建人员");
            }
            else
            {
	             createPersonWindow(orgid);
		         //store.reload();
	             persongrid.getView().refresh();
	            }
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
				var row=persongrid.getSelections();
	            var txtCheckValue = "";
	            for(var i=0,len=row.length;i<len;i++){
	                var ss = row[i].get("personid");
	                txtCheckValue = txtCheckValue + ss + ",";
	            }
	            	Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/personSaveSort.do?txtCheckValue="+txtCheckValue, 
					success : function(result, action)
					{ 
								Ext.MessageBox.alert('消息', '操作成功');
								store.setDefaultSort('indextype', 'ASC'); 
								store.reload();
								sm.clearSelections();
								
					}, 
				    failure : function(form, action) 
				    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 
            }
        },'-',{
            text:'人员调动',
            tooltip:'人员调动',
            iconCls:'option',
            handler:function(){
               //选择部门树
        	   var row=persongrid.getSelections().length;
        	   if(row>0){
        	      getDepartTree();
        	      
        	   }else{
        		   Ext.MessageBox.alert('消息', '请选择要调动的人员！');
        	   }	   
        	   //store.reload();
        	   
            }
        }/*,'-',{
            text:'批量导入',
            tooltip:'批量导入',
            iconCls:'option',
            handler:function(){
              alert('批量导入人员信息');
        	   //更新数据库信息
            }
        }*/
        ],
        
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
    
   
    persongrid.render();
    store.setDefaultSort('indextype', 'ASC');
    store.load({params:{start:0, limit:100}});
    persongrid.on("rowdblclick", function(persongrid) {
        loadFormData(persongrid);
    });
    var ddrow = new Ext.dd.DropTarget(persongrid.getView().mainBody, {
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
    var loadFormData = function(persongrid) {
        var _record = persongrid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
		   Ext.getCmp("userid").value=_record.get('userid');
		   Ext.getCmp("rtxaccount").value=_record.get('userid');
           Ext.getCmp("cnname").value=_record.get('cnname');
           Ext.getCmp("enname").value=_record.get('enname');
           Ext.getCmp("password").value=_record.get('password');
           Ext.getCmp("accountstat").value=_record.get('accountstat');
           //Ext.getCmp("accountttl").value=_record.get('accountttl');
           Ext.getCmp("sex").value=_record.get('sex');
           Ext.getCmp("pager").value=_record.get('pager');
         //  Ext.getCmp("photo").value=_record.get('photo');
           Ext.getCmp("officetel").value=_record.get('officetel');
            if(Ext.getCmp("sex").value=='0')
           {
               Ext.getCmp("sex").value = "保密";
           }else if(Ext.getCmp("sex").value=='1')
           {
           	   Ext.getCmp("sex").value = "男";
           }else if(Ext.getCmp("sex").value=='2')
           {
           	   Ext.getCmp("sex").value = "女";
           }
           
         /*  if(Ext.getCmp("accountstat").value=='1')
           {
               Ext.getCmp("accountstat").value = "临时账号";
           }else if(Ext.getCmp("accountstat").value=='2')
           {
           	   Ext.getCmp("accountstat").value = "普通账号(连续120天不登陆，即失效（被禁止）)";
           }else */if(Ext.getCmp("accountstat").value=='3')
           {
           	   Ext.getCmp("accountstat").value = "永久";
           }else if(Ext.getCmp("accountstat").value=='4')
           {
           	   Ext.getCmp("accountstat").value = "禁用";
           } 
          
           if(_record.get('job')=='null')
           {
           	 Ext.getCmp("job").value='';
           }
           else
           {
             Ext.getCmp("job").value=_record.get('job');
           }
           
           if(_record.get('duty')=='null')
           {
           	  Ext.getCmp("duty").value='';
           }
           else
           {
              Ext.getCmp("duty").value=_record.get('duty');
           }
            if(_record.get('mobile')=='null')
           {
           	  Ext.getCmp("mobile").value='';
           }
           else
           {
              Ext.getCmp("mobile").value=_record.get('mobile');
           } 
           
          myFormWin();	
        //  document.getElementById("img").src=_record.get('photo');
        }
    };

    var myFormWin = function() {
        
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 700,
                height : 340,
                maximizable:true,
                autoScroll:true,
                plain : true,
                title : '修改人员信息',
                items:fsf,
                listeners:{"beforedestroy":function(obj){	
            	window.location="childrenPersonListgrid.jsp?orgid="+orgid; 	
            	return false;	
            	}}
            });
        }
        newFormWin.show('New1');
    };    
    
    function toggleDetails(btn, pressed){
        var view = persongrid.getView();
        view.showPreview = pressed;
        view.refresh();
    }    
    
     var fsf = new Ext.FormPanel({
        labelWidth: 95, 
		labelAlign: 'right',  
		fileUpload: true, 
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:5px 5px 0',   
		anchor:'100%',  
		frame:true,    
        items: [{
            collapsible: true,
            checkboxToggle:false,
            collapsed: true,
            xtype:'fieldset',
            title:'基本信息',
            autoHeight:true,
            collapsed:false,
            width:650,
            checkboxName:'jiben',
            layout:'column',
            border:true,
            labelSeparator:'：',
            items:[                  
                 /*  {       
		            columnWidth:.2,            
		            layout: 'form',            
		            border:false,
		            items: [    
                       {
                    	title:'',
               		    html:'<img id="img" border="0" width="100" height="120" src="/upload/no.jpg"/>'       
	                   }
	                ]},   */            
               {  
               columnWidth:.4,            
               layout: 'form',            
               border:false,            
               items: [    
	             {                
		               id:'userid',
		               xtype:'textfield',                
		               fieldLabel: '账号',                
		               name: 'personaccount.userid',                
		               anchor:'82%'
	                   },   		  
	                   {       
               		   id:'cnname',     
		               xtype:'textfield',                
		               fieldLabel: '中文名字',                
		               name: 'personinfo.cnname',
		               allowBlank:false,//不允许为空
		               blankText:'中文名不能为空',//错误提示内容  
		               checkboxToggle:true,	                              
		               anchor:'82%',
                       invalidText:'用户名已经被注册！'  
	                   },{ 
	                   id:'enname',               
		               xtype:'textfield',                
		               fieldLabel: '英文名字',                
		               name: 'personinfo.enname',
		               allowBlank:true,//不允许为空
		               blankText:'英文名不能为空',//错误提示内容               
		              anchor:'82%'
	                   },
	                           
		                new Ext.form.ComboBox({
	                    id:'accountstat',
	                    fieldLabel: '账号状态',
	                    name: 'personaccount.accountstat',	                    
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['accountstat'],
	                        data: [['永久'],['禁用']]
	                    }),	                     
	                    displayField:'accountstat',
	                    emptyText:'选择账号状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    allowBlank:false,
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    anchor:'82%'
	                   }), new Ext.form.ComboBox({
	                  	id:'sex',
	                    fieldLabel: '性别',
	                    name: 'personinfo.sex',  
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['sex'],
	                        data: [['保密'], ['男'],['女']]
	                    }),	                     
	                    displayField:'sex',
	                    triggerAction:'all',
	                    emptyText:'选择性别状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    triggerAction:'all',
	                    selectOnFocus:true,
	                    anchor:'100%'
	                   })/*, {                
		               xtype: 'fileuploadfield',
		               id: 'photo',
		               emptyText: '请选择相片',             
		               fieldLabel: '相片',                
		               name: 'file',
		               anchor:'100%',
			           buttonCfg: {
			                text: '浏览',
			                iconCls: 'upload-icon'
			            }
	                   }*/
	                    ]
                    },{
                		columnWidth:.4,
                        layout: 'form',
                        border:false, 
                       items: [
	                 {
	                    id:'password',
	                    xtype:'textfield',	
	                    fieldLabel: '密码',	
	                    name: 'personaccount.password',
	                    inputType:'password', 
		                anchor:'100%'
	                   }, {                
		               id:'job',
		               xtype:'textfield',                
		               fieldLabel: '工作',                
		               name: 'personinfo.job',                
		               anchor:'100%'
	                   },{                
		               id:'duty',
		               xtype:'textfield',                
		               fieldLabel: '职务',                
		               name: 'personinfo.duty',                
		               anchor:'100%'
	                   },{
		               id:'officetel',
		               xtype:'textfield',
			           fieldLabel: '办公电话',
			           name: 'personinfo.officetel',
			           anchor:'100%'
		               },{
	                   id:'mobile',
	                   xtype:'textfield',
		               fieldLabel: '手机',
		               name: 'personinfo.mobile',
		               anchor:'100%'
	                   },{
		               id:'pager',
		               xtype:'textfield',
			           fieldLabel: '话机IP',
			           name: 'personinfo.pager',
			           anchor:'100%'
		               },{
		               id:'rtxaccount',
		               xtype:'hidden',
			           fieldLabel: 'rtx',
			           name: 'personaccount.rtxaccount',
			           anchor:'100%'
		               }    
	                 ]
            }]
        }
          ],
 buttons: [{
            text: '修  改',
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
            text: '退  出',
            handler: function() {
        	    newFormWin.close();
            }
        }
        ]

    });
    function showDeleteMessage(){
        Ext.MessageBox.confirm('消息', '确认删除？',doDel);
    }
    function doDel(btn){
        if(btn=='yes'){
            var row=persongrid.getSelections();
            var jsonData = "";
            for(var i=0,len=row.length;i<len;i++){
                var ss = row[i].get("personid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息', '请选择要删除的数据。');
            }else{
		               Ext.Ajax.request({
		                //请求地址
		                method: 'POST',      
		                url: '/usm/personPointDel.do?orgid='+orgid+'&personidjsonData=' + jsonData,
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
	        var name = Ext.get('name').dom.value;
	    	store.load({params:{start:0, limit:100,name:name}}); 
         }

         function getDepartTree(){
         	var departid = '';
             var root = new Ext.tree.AsyncTreeNode({
         	    text: '组织机构',
         	    leaf : false,
         	    draggable:false,
         	    id:'toproot'
             });
          	var win = new Ext.Window({
                     id:'win1',
                     title:'选择调动去向部门',
                     width: 300,
                     height:450,
                     shim:true,
                     animCollapse:true,
                     constrainHeader:true,
                     layout: 'fit',
                     items: new Ext.tree.TreePanel({
                         id:'depart-tree',
                         split:true,
                         width: 225,
                         minSize: 175,
                         maxSize: 400,
                         collapsible: true,
                         margins:'0 0 5 5',
                 		 animCollapse:false,
                 		 animate: true,
                 		 collapseMode:'mini',
                 		 rootVisible:false,
                         loader: new Ext.tree.TreeLoader({
                 			url: '/usm/orgtreeJson.do'
                         }),
                         rootVisible:true,
                         lines:true,
                         autoScroll:true,
                         root:root
                               
                     }),
                     listeners:{"beforedestroy":function(obj){	
            	window.location="childrenPersonListgrid.jsp?orgid="+orgid; 	
            	return false;	
            	}},
         			buttons:[{
         					   text:'确    定',
         					   handler:function(){
         							//读取选中人员
         				            var row=persongrid.getSelections();
         				            var jsonData = "";
         				            for(var i=0,len=row.length;i<len;i++){
         				                var ss = row[i].get("personid");
         				                jsonData = jsonData + ss + ",";
         				            }
         				 
         				            if(jsonData.length == 0 ){
         				                 Ext.MessageBox.alert('消息', '请选择要调动的人员！');
         				                 return;
         				            }
         							if (departid == ""){
         								Ext.MessageBox.alert('消息','请选择去向部门！');
         							}else{
         								Ext.Ajax.request({
         									method:'POST',
         									url:'/usm/changeDepart.do',
         									params:{
         									    newOrgid:departid,
         									    personidjsonData:jsonData
         									},	
         									success:function(response,options) {
         									
         									Ext.MessageBox.alert('消息','人员调动成功！');         									
        										win.close();
         										
         								    },
         									failure:function(response,options) {
         										Ext.MessageBox.show({
         											title :'消息',
         											msg :'人员调动失败！' + response.responseText,
         											buttons :Ext.Msg.OK,
         											icon :Ext.MessageBox.ERROR
         										});
         									}
         								});
         							}
         					  }
         					},{
         						text:'取    消',
         						handler:function(){
         							win.close();
         						}
         					}]
                 });
                 win.show();
                 var tree = Ext.getCmp('depart-tree');
         	     tree.setRootNode(root);
         	     tree.render();
         	     tree.on('beforeload',function(node) {
         		    tree.loader.url = '/usm/orgtreeJson.do?name=org&childOrgId='+node.id+'';	
         	     });	
         	     tree.on('click', function(node){
         	    	departid = node.id;
         	     });
         }

});
 
function createPersonWindow(orgid){

         var win=new Ext.Window({
		     layout : 'fit',
                width : 700,
                height : 340,
maximizable:true,
                autoScroll:true,
                plain : true,
		     title:'添加人员信息',
		     closable:true,
			 collapsible:true,
		     autoScroll:true,
		     items:new Ext.Panel({
				        title: '',        
					  	height:329,
				        html: '<iframe frameborder="0" src="/usm/person/personInsertwindow.jsp?orgid='+orgid+'" height=100% width=100%></iframe>'
		     })
		 });
		 win.show();
}
