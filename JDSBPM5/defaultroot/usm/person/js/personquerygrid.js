Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
    var newFormWin;
    var persongrid;
    var currentRowNumber;
    Ext.QuickTips.init();
    Ext.form.Field.prototype.msgTarget = 'side';
    var xg = Ext.grid;
    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: '/usm/getPersonInfo.do'   //从此页获取数据
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
            {name: 'marry', type: 'string'},
            {name: 'politicalstat', type: 'string'},
            {name: 'lastedulevel', type: 'string'},
            {name: 'lastdegree', type: 'string'},
            {name: 'househao', type: 'string'},
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
           id: 'personid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "人员编号",
           dataIndex: 'personid',
           align:'center',
		   hidden:true,
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
           dataIndex:'accountstat',
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
           header: "最高学历",
           dataIndex: 'lastedulevel',
           align:'center',
           hidden :true,
           width: 60
        },{
           header: "话机IP",
           dataIndex: 'pager',
           align:'center',
           hidden :false,
           width: 60
        }
        
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
        width:600,
        store:store,
	    cm:cm,
	    sm:sm,
           loadMask: true,//装载动画
        viewConfig: {
            forceFit:true
        },
        buttonAlign:'center',
        tbar:['姓名：',name, {// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",
                    text: '查 询',
                    cls : "x-btn-text-icon",   
                    handler :querypersonMessage   
                }
/*,{
                	id:'exporttortx',
                	text:'导入rtx数据',
                	handler:function(){
                		Ext.Ajax.request({
                			url:'/usm/orgExportToRtx.action',
                			success:function(){
                				alert('导入成功');
                			}
                		});
                	}
                }*/],
        
         bbar: new Ext.PagingToolbar({
            pageSize: 100,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "无记录"
        }),     
        
        width:960,
        height:710,
        frame:true,
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
    persongrid.render();//Ext.getCmp('grid').render();//渲染表格
    store.load({params:{start:0, limit:100}}); //加载数据
    persongrid.on("rowdblclick", function(persongrid) {
        loadFormData(persongrid);
    });


    // 载入被选择的数据行的表单数据
    var loadFormData = function(persongrid) {
        var _record = persongrid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
           Ext.getCmp("cnname").value=_record.get('cnname');
           Ext.getCmp("enname").value=_record.get('enname');
           Ext.getCmp("password").value=_record.get('password');
           Ext.getCmp("accountstat").value=_record.get('accountstat');
           Ext.getCmp("sex").value=_record.get('sex');
           Ext.getCmp("birthday").value=_record.get('birthday');
           Ext.getCmp("pager").value=_record.get('pager');
           Ext.getCmp("officetel").value=_record.get('officetel');
           
           
           if(_record.get('accountttl')=='null')
           {
               Ext.getCmp("accountttl").value = '';
           }else
           {
           	   Ext.getCmp("accountttl").value=_record.get('accountttl');
           }

           
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
          /* if(Ext.getCmp("accountstat").value=='1')
           {
               Ext.getCmp("accountstat").value = "临时账号";
           }else if(Ext.getCmp("accountstat").value=='2')
           {
           	   Ext.getCmp("accountstat").value = "普通账号(连续120天不登陆，即失效（被禁止）)";
           }else*/ if(Ext.getCmp("accountstat").value=='3')
           {
           	   Ext.getCmp("accountstat").value = "永久";
           }else if(Ext.getCmp("accountstat").value=='4')
           {
           	   Ext.getCmp("accountstat").value = "禁用";
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
           document.getElementById("img").src=_record.get('photo');
          	
        }
    };
    var myFormWin = function() {    
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 730,
                height : 350,
                maximizable:true,                
                plain : true,
                title : '修改人员信息',
                items:fsf,
                listeners:{"beforedestroy":function(obj){
                window.location="personqueryListgrid.jsp"; 	
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
        labelWidth: 75, 
    	id:'fsf',
    	fileUpload: true,
		labelAlign: 'right',    
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:5px 5px 0',   
		anchor:'100%', 
		autoScroll:true, 
		frame:true,    
        items: [{  
           
            labelSeparator:'：',
            fileUpload:true,
            xtype:'fieldset',
            title: '基本信息',
            collapsible: true,
            checkboxToggle:false,
            collapsed: false,
            autoHeight:true,
            width: 670,
            layout:'column',
            border:true,
            labelSeparator:'：',
            items:[
            {
            	columnWidth:.3,            
                layout: 'form',            
                border:false,
            	items:[
            	       {   xtype:'panel', 
    					   border:false, 				   
  						   bodyStyle : 'padding:2,12,7,52;border:1px', 
               		       html:'<img id="img" border="0" width="120" height="120" />'               		   
	                   },
	                   {                
		               id:'file',
		               xtype: 'fileuploadfield',
		               emptyText: '请选择相片',             
		               fieldLabel: '相片',                
		               name: 'file',
		               decimalPrecision:2, 
		               style:'x-form-file-wrap .x-form-file-text .x-form-file-btn',
		               anchor:'90%',
			           buttonCfg: {
			                text: '浏览',
			                iconCls: 'upload-icon'
			            }
	                   }
	                   
            	]
            },{  
               columnWidth:.4,            
               layout: 'form',            
               border:false,            
               items: [
               		  {       
               		   id:'cnname',     
		               xtype:'textfield',                
		               fieldLabel: '中文名字',                
		               name: 'personinfo.cnname',
          			   allowBlank:false,//不允许为空
		               blankText:'中文名不能为空',		                 
		               anchor:'82%'
	                   },{ 
	                   id:'enname',               
		               xtype:'textfield',                
		               fieldLabel: '英文名字',                
		               name: 'personinfo.enname',                
          			   allowBlank:false,//不允许为空
		               blankText:'英文名不能为空',
		               anchor:'82%'
	                   },
	                   {
                                        fieldLabel: '密码',
                                        xtype:'textfield',
                                        id: 'password',
                                        name: 'personaccount.password',
                                        inputType: 'password',
                                        allowBlank: false,
                                        blankText: '密码不能为空',
                                        regex:/^[A-Za-z]{1}([A-Za-z0-9]|[._]){5,19}$/,
                                        regexText:'密码由6-20位的字母、数字、下划线、点“.”组成并且首字符为字母',
                                        anchor:'82%',
                                        allowBlank:false,
                                        blankText:'密码不能为空'
                                    
                                    },          
		                new Ext.form.ComboBox({
		                id:'accountstat',
	                    fieldLabel: '账号状态',
	                    name: 'personaccount.accountstat',
	                    triggerAction:'all', 
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['accountstat'],
	                       
	                        data: [['临时账号'], ['普通账号(连续120天不登陆，即失效（被禁止）)'],['永久'],['禁用']]
	                    }),	                     
	                    displayField:'accountstat',
	                    emptyText:'选择账号状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    allowBlank:false,
	                    selectOnFocus:true,
	                    anchor:'82%'
	                   }),{                
		                id:'accountttl',
		                xtype:'textfield',                
		                fieldLabel: '账号有效期',                
		                name: 'personaccount.accountttl',   
		                regex:/^[0-9]+$/, 
          			    regexText: '只能输入数字',              
		                anchor:'82%'
	                  }
	                   ]
                    },{
                		columnWidth:.3,
                        layout: 'form',
                        border:false, 
                     items: [
                       
	                  new Ext.form.ComboBox({
	                    id:'sex',
	                    fieldLabel: '性别',
	                    name: 'personinfo.sex', 
	                    triggerAction:'all', 
	                    store: new Ext.data.SimpleStore({   ////填充数据
	                        fields: ['sex'],
	                        data: [['保密'], ['男'],['女']]
	                    }),	                     
	                    displayField:'sex',
	                    emptyText:'选择性别状态……',
	                    mode: 'local',   //数据模式，local代表本地数据  
	                    selectOnFocus:true,
	                    anchor:'100%'
	                   }),
	                   new Ext.form.DateField({
	                    id:'birthday',
	                    xtype:'textfield',
	                    format:'Y-m-d',
                        fieldLabel: '出生日期',
                        name: 'birthday',
                        anchor:'100%'                   
                        })
                        ,{
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
                      }
                      ,{
                          id:'pager',
                          xtype:'textfield',
  	                    fieldLabel: '话机IP',
  	                    name: 'personinfo.pager',
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
                }else{
                	 Ext.Msg.alert("出错啦", "数据校验出错，请检查输入的数据是否合法！");
                }	
            }
        }, {
            text: '取  消',
            handler: function() {
                fsf.form.reset();
            }
        } , {
            text: '退  出',
            handler: function() {
        	    newFormWin.close();
            }
        } 
        ]

    });
    
   function personadd()
   {
   	var txtCheckValue;
     var departtree = new  Ext.Window({
      title:"请选择部门",
	            width:250,
	            height:410,
	            maximizable:true,
			  	closeAction:'close',
			  	closable:true,
			  	collapsible:true,
			  	autoScroll:true,
			  	  
			  	items: new Ext.tree.TreePanel({
				                id:'forum-tree',
				                region:'west',
				                title:'',
				                split:true,
				                width: 250,
				                height:410,
				                minSize: 175,
				                maxSize: 200,
				                animCollapse:false,
								animate: true,
								collapseMode:'mini',
				                loader: new Ext.tree.TreeLoader({
											url: '/usm/orgtreeJson.do'
						}),
				                rootVisible:true,
				                lines:true,
				                autoScroll:true,
				                root: new Ext.tree.AsyncTreeNode({
				                					id:'root',
				                					draggable:false,
				                          text: '组织机构'
				                      })
				                      
				            }),
				  
				          buttons: [{
			            text: '修改',
			            handler: function(){
			            
			            	if(txtCheckValue.length>1){
			            		alert('请选择一个组');
			            	}else{
			            		departtree.close();
			            		
			            		 var w=new Ext.Window({
				            title:"人员添加",
				            width:750,
				            height:500,
				            maximizable:true,
						  	closeAction:'close',
						  	closable:true,
						  	collapsible:true,
						  	autoScroll:true,
						  	items:new Ext.Panel({
						        title: '',        
							  	height:435,
						        width:720,
						        html: '<iframe scrolling="yes" frameborder="0" src="personInsertwindow.jsp?parentorgid='+txtCheckValue+'" height=100% width=100%></iframe>'
						    })
				        	});
				         w.show();
			            	} 
			            				            	
			            			}
			       				 }
			       					 ]

	        	});
	        
	         departtree.show();
	         
	          var tree = Ext.getCmp('forum-tree');
	

	var tree = Ext.getCmp('forum-tree');
	tree.body.mask('加载中...','x-mask-loading');
	tree.root.reload();
	tree.root.collapse(true,true);
	setTimeout(function(){
	   tree.body.unmask();
	   tree.root.expand(false,false);
	},100);

       
	   tree.on('checkchange', function(node, checked) {   
	     			node.expand();   
                    node.attributes.checked = checked;   
                    var parentNode = node.parentNode;   
                    if(checked){   
                        if(parentNode!=null&&parentNode.id!='root'){   
                            //如果是选中,把父节点保持选中状态   
                            parentNode.ui.toggleCheck(true);   
                            parentNode.attributes.checked = true; 
                         txtCheckValue = tree.getChecked('id');    
                        }   
                    }else{   
                        //如果所有子节点都没选中，取消根节点选中状态   
                        if(parentNode!=null&&parentNode.id!='root'){   
                            var chk = false;   
                            parentNode.eachChild(function(child) {   
                                if(child.attributes.checked)chk=true;   
                            });   
                            parentNode.ui.toggleCheck(chk);   
                            parentNode.attributes.checked = chk;   
                        }   
                    }   
                    node.eachChild(function(child) {   
                        child.ui.toggleCheck(checked);   
                        child.attributes.checked = checked;   
                        child.fireEvent('checkchange', child, checked);   
                    });   
                   txtCheckValue = tree.getChecked('id');   

	     }, tree); 
          
    
     
       
   }
      
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
		                url: '/usm/personPointDel.do?personidjsonData=' + jsonData,
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
    
    
    function querypersonMessage()
    { 
    
    	var name = Ext.get('name').dom.value;
        store.load({params:{start:0, limit:10,name:name}});    
    }
    
});

