/*
 * 
 * 用户组信息列表
 * 
 */
Ext.onReady(function(){

    var newFormWin; 
    var currentRowNumber;
    Ext.QuickTips.init();
    var xg = Ext.grid;
    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: "/usm/getUserGroupInfo.do?sysid="+sysid+"" //从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'grpid',
            fields: [
            {name: 'grpid', type: 'string'},
            {name: 'grpname', type: 'string'},
            {name: 'grpflag', type: 'string'},
   			{name: 'grpdesc', type: 'string'},
   			{name: 'createtime', type: 'string'}
   			//{name: 'createtime', type: 'string'}   			
            ]
        })
    });
        store.load({params:{start:0, limit:10}});
        var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
        var cm = new Ext.grid.ColumnModel([sm,new Ext.grid.RowNumberer(
	     {
		     header:'序号',
		     width:32,
		     align:'center'
	     }
        ),{
           id: 'grpid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "用户组编号",
           dataIndex: 'grpid',
           align:'center',
		   hidden :true,
           width: 60
        },{
           header: "用户组名称",
           dataIndex: 'grpname',
           align:'center',
           width: 100
        },{
           header: "标识",
           dataIndex: 'grpflag',
           hidden :true,
           align:'center',
           width: 100
        },
        {
           header: "创建时间",
           dataIndex: 'createtime',
           format:'Y-m-d h:i:s',
           align:'center',
           width: 100
        },{
           header: "排序",
           dataIndex: 'grpdesc',
           align:'center',
           width: 100
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
    var usergroupgrid = new Ext.grid.GridPanel({
        id:'button-grid',
        width:300,
        store:store,
        view:view,
	    cm:cm,
	    sm:sm,
           
        viewConfig: {
            forceFit:true
        },
        buttonAlign:'center',
        tbar:['用户组名称：',name, {// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",
                    text:'查  询',
                    cls : "x-btn-text-icon",   
                    handler :querynameMessage  
            },{
            text:'新 增',
            tooltip:'新增',
            iconCls:'add',
            handler:function(){
            var w=new Ext.Window({
	            title:"添加用户组基本信息",
	            width:500,
	            height:180,
	            maximizable:true,
			  	closable:true,
			  	collapsible:true,
			  	autoScroll:false,
			  	items:new Ext.Panel({
			        title: '',        
				  	height:180,
			        width:500,
			        html: '<iframe scrolling="yes" frameborder="0" src="usergroupadd.jsp?sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
			    })
	        	});
	        	 w.on("beforedestroy",function(obj){		
	        			
	        		window.location="usergroupqueryListgrid.jsp?sysid="+sysid+"&type="+type+""; 
	        				
	        		return false;	
	        		});
	        	
	         w.show();
           
            }
        },'-',{
            text:'删  除',
            tooltip:'删除',
            iconCls:'remove',
            handler:showDeleteMessage
     
        }],
        
         bbar: new Ext.PagingToolbar({
            pageSize: 10,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "无记录"
        }),     
        
         width:783,
        height:545,
        frame:true,
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
    usergroupgrid.render();
    usergroupgrid.on("rowdblclick", function(usergroupgrid) {
        loadFormData(usergroupgrid);
        //alert(form1.reader);
    });

    // 载入被选择的数据行的表单数据
    var loadFormData = function(usergroupgrid) {
        var _record = usergroupgrid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
           Ext.getCmp("grpid").value=_record.get('grpid');
           Ext.getCmp("grpname").value=_record.get('grpname');
           Ext.getCmp("grpdesc").value=_record.get('grpdesc');
           Ext.getCmp("createtime").value=_record.get('createtime');
           myFormWin();	
        }
    };

    var myFormWin = function() {
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 400,
                height : 200,
                maximizable:true,
                plain : true,
                title : '修改用户组信息',
                items:fsf
                
            });
        }
        newFormWin.on("beforedestroy",function(obj){		
	        		window.location="usergroupqueryListgrid.jsp?sysid="+sysid+"&type="+type+""; 
	        		//obj.show();		
	        		return false;	
	        		});
        newFormWin.show('New1');
    };

    
    
    function toggleDetails(btn, pressed){
        var view = usergroupgrid.getView();
        view.showPreview = pressed;
        view.refresh();
    } 
    
    
    
      var fsf = new Ext.FormPanel({
		labelAlign: 'right',    
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:8px',    
		anchor:'100%',  
		frame:true,    
        items: [ {
						id:'grpid',
	                    xtype:'textfield',	
	                    fieldLabel: '* 用户组编号',	
	                    name: 'usergroup.grpid',
	                    allowBlank:false,
	                    blankText:'用户组编号不能为空',
	                    readOnly:true,
		                anchor:'95%'
	
	                },
                    {
						id:'grpname',
	                    xtype:'textfield',	
	                    fieldLabel: '* 用户组名称',	
	                    name: 'usergroup.grpname',
	                    allowBlank:false,
	                    blankText:'用户组名称不能为空',
	                   
		                anchor:'95%'
	
	                }, 
	                {
						id:'grpdesc',
	                    xtype:'textfield',	
	                    fieldLabel: '排序',	
	                    name: 'usergroup.grpdesc',
	                    readOnly:true,
		                anchor:'95%'
	
	                }, 
	                {
						id:'createtime',
	                    xtype:'textfield',	
	                    fieldLabel: '创建时间',	
	                    name: 'usergroup.createtime',
	                    readOnly:true,
		                anchor:'95%'
	
	                }
            ],      

 buttons: [{
            text: '保 存',
            handler: function(){
             var grpname=Ext.get('grpname').dom.value;
             var _record = usergroupgrid.getSelectionModel().getSelected();
             if(grpname=='')
             {
             	Ext.MessageBox.alert('用户组名称','用户组名称不能为空');
             }
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        
                        url: '/usm/userGroupModify.do?grpid=' + _record.get('grpid'),
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
            text: '退 出',
            handler: function() {
               window.location="usergroupqueryListgrid.jsp?sysid="+sysid+"&type="+type+""; 
            }
        }    
        
        ]

    });

      
    function showDeleteMessage(){
        Ext.MessageBox.confirm('消息', '确认删除？',doDel);
    }
    function doDel(btn){
        if(btn=='yes'){
            var row=usergroupgrid.getSelections();
            var jsonData = "";
            for(var i=0,len=row.length;i<len;i++){
                var ss = row[i].get("grpid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息', '请选择要删除的数据。');
            }else{
		               Ext.Ajax.request({
		                //请求地址
		                method: 'POST',      
		                url: '/usm/userGroupPointDel.do?grpidjsonData=' + jsonData,
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
     function querynameMessage()
    {
	         var name = Ext.get('name').dom.value;
	         //store.load({params:{start:0, limit:10}});
         	 store.load({params:{start:0, limit:10,name:name}});
    
    }
});

