/*
 * 角色信息列表
 */
Ext.onReady(function(){

    var newFormWin;
    
    var currentRowNumber;
    
    Ext.QuickTips.init();
    
    var xg = Ext.grid;
    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: '/usm/getRoleInfo.do?sysid='+sysid+''   //从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'roleid',
            fields: [
            {name: 'roleid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'adminflag', type: 'string'},
            {name: 'createtime', type: 'string'}
            ]
        })
    });
      
  var sm = new Ext.grid.CheckboxSelectionModel(); // add checkbox column
    var cm = new Ext.grid.ColumnModel([sm,new Ext.grid.RowNumberer(
	     {
		     header:'序号',
		     width:32,
		     align:'center'
	     }
        ),{
           id: 'roleid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "角色编号",
           dataIndex: 'roleid',
           align:'center',
		   hidden :true,
           width: 60
        },{
           header: "角色名称",
           dataIndex: 'cnname',
           align:'center',
           width: 100
        },{
           header: "标识",
           dataIndex: 'adminflag',
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
            
    var rolegrid = new Ext.grid.GridPanel({
        id:'button-grid',
        view:view,
         width:930,
        height:660,
        store:store,
	    cm:cm,
	    sm:sm,
           
        viewConfig: {
            forceFit:true
        },
        buttonAlign:'center',
        tbar:['角色名称：',name, {// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",   
                    cls : "x-btn-text-icon",
                    text:'查  询',
                    handler :querynameMessage
                          
                     
            },{
            text:'新  增',
            tooltip:'新增',
            iconCls:'add',
            handler:function(){
           // window.location="departaddwindow.jsp"; 
            var win=new Ext.Window({
	            title:"添加角色基本信息",
	            width:420,
	            height:135,
	            maximizable:true,
			  	closeAction:'close',
			  	closable:true,
			  	collapsible:true,
			  	autoScroll:false,
			  	items:new Ext.Panel({
			        title: '',        
				  	height:95,
			        width:450,
			        html: '<iframe scrolling="yes" frameborder="0" src="roleaddwindow.jsp?sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
			    })
	        	});
	        	 win.on("beforedestroy",
		       function(obj){		
	             window.location="rolequeryListgrid.jsp?sysid="+sysid+"&type="+type+""; 	
	           return false;
	           
           });	
	         win.show();
           
            }
        },'-',{
            text:'删  除',
            tooltip:'删除',
            iconCls:'remove',
            handler:showDeleteMessage
     
        }],
        
         bbar: new Ext.PagingToolbar({
            pageSize: 22,
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
    
    rolegrid.render();
  store.load({params:{start:0, limit:22}});

    rolegrid.on("rowdblclick", function(rolegrid) {
        loadFormData(rolegrid);
        //alert(form1.reader);
    });

    // 载入被选择的数据行的表单数据
    var loadFormData = function(rolegrid) {
        var _record = rolegrid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
         //  Ext.getCmp("roleid").value=_record.get('roleid');
           Ext.getCmp("cnname").value=_record.get('cnname');
         //  Ext.getCmp("createtime").value=_record.get('createtime');
        //   Ext.getCmp("roledesc").value=_record.get('roledesc');
           myFormWin();	
          	
        }
    };

    var myFormWin = function() {
        
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 350,
                height : 150,
                //closeAction : 'hide',
                maximizable:true,                
                plain : true,
                title : '修改角色信息',
                items:fsf
                
            });
             newFormWin.on("beforedestroy",
		       function(obj){		
	             window.location="rolequeryListgrid.jsp?sysid="+sysid+"&type="+type+""; 		
	             return false;
           });
        }
        newFormWin.show('New1');
    };
    function toggleDetails(btn, pressed){
        var view = rolegrid.getView();
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
        items: [ 
	               /*  {
	      			    id:'roleid',
	                    xtype:'textfield',	
	                    fieldLabel: '* 角色编号',	
	                    name: 'role.roleid',
	                    allowBlank:false,
	                    blankText:'角色编号不能为空',
	                    readOnly:true,
		                anchor:'80%'
	
	                },*/{
	      			    id:'cnname',
	                    xtype:'textfield',	
	                    fieldLabel: '* 角色名称',	
	                    name: 'role.cnname',
	                    allowBlank:false,
	                    blankText:'角色名称不能为空',
		                anchor:'80%'
	
	                }/*,{
	      			    id:'createtime',
	                    xtype:'textfield',	
	                    fieldLabel: '创建时间',	
	                    name: 'role.createtime',
	                    format:'Y-m-d h:i:s',
	                    readOnly:true,
		                anchor:'80%'
	
	                }*/
                   ],       

 buttons: [{
            text: '保  存',
            handler: function(){
            	var cnname=Ext.get('cnname').dom.value;
            	if(cnname=='')
            	{
            		Ext.MessageBox.alert('角色名称','角色名称不能为空');
            	}
             var _record = rolegrid.getSelectionModel().getSelected();
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        
                        url: '/usm/roleModify.do?roleid=' + _record.get('roleid'),
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
        }/*, {
            text: '取 消',
            handler: function() {
                fsf.form.reset();
            }
        }*/, {
            text: '退 出',
            handler: function() {
               window.location="rolequeryListgrid.jsp?sysid="+sysid+"&type="+type+""; 
            }
        }
        ]

    });
    function showDeleteMessage(){
        Ext.MessageBox.confirm('消息', '确认删除？',doDel);
    }
    function doDel(btn){
        if(btn=='yes'){
            var row=rolegrid.getSelections();
            var jsonData = "";
            for(var i=0,len=row.length;i<len;i++){
                var ss = row[i].get("roleid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息', '请选择要删除的数据。');
            }else{
		               Ext.Ajax.request({
		                //请求地址
		                method: 'POST',      
		                url: '/usm/rolePointDel.do?roleidjsonData=' + jsonData,
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
    	//alert(name);
    	store.load({params:{start:0, limit:25,name:name}});    
    }
    
    
});

