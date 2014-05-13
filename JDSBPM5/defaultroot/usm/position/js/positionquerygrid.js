/*
 * 
 * 岗位信息列表
 * 
 */
Ext.onReady(function(){
    var newFormWin;
    var persongrid;
    var position;
    Ext.QuickTips.init();
    var url ="/usm/positionQery.do?sysid="+sysid;
    var xg = Ext.grid;
    var store = new Ext.data.Store({
       
        proxy: new Ext.data.HttpProxy({   
        	
            url: url   //从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'positionid',
            fields: [              
           // {name: 'positionlogid', type: 'string'},
             {name: 'positionid', type: 'string'},
             {name: 'positionname', type: 'string'},
             {name: 'positiondesc'}          
            ]
        })
    });
    store.setDefaultSort('positiondesc', 'asc');
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
           id: 'positionid',       // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "岗位编号",
           dataIndex: 'positionid',
           align:'center',  
           hidden:true,
           width: 60
        },{
           header: "岗位名称",  
           dataIndex: 'positionname',
           align:'center',
           width: 100
        },
        {
           header: "显示顺序",
           dataIndex: 'positiondesc',
           align:'center',
           width: 60
        }
        ]);
    cm.defaultSortable = true;
      //配置视图信息
    var view=new Ext.grid.GridView({forceFit:true,sortAscText :'正序', sortDescText :'倒序'});
    view.columnsText='列显示/隐藏';  
    var sm = new xg.CheckboxSelectionModel();
    ////////////////////////////////////////////////////////////////////////////////////////
    // Grid 1
    ////////////////////////////////////////////////////////////////////////////////////////
    var name = new Ext.form.TextField({   
                width:175,
                id:'name',
                name: 'name',
                allowBlank:true 
            });
            
    var positiongrid = new Ext.grid.GridPanel({
        id:'positionstore',
        width:300,
        store:store,
	    cm:cm,
	    sm:sm,	    
        view:view,   
        trackMouseOver:true,
		loadMask: true,
		autoShow : true,
		enableDragDrop: true,
		viewConfig: {
		    forceFit:true,
		    enableRowBody:true,
		    showPreview:true
		        },
        buttonAlign:'center',
        tbar:['岗位名称：',name, {// 查询按钮   
                    id: 'newModelButton',    
                    icon: "/usm/img/search.png", 
                    text:'查  询',
                    cls: "x-btn-text-icon",   
                    handler :querynameMessage 
            },{
            text:'新  增',
            tooltip:'新增',
            iconCls:'add',
            handler:function(){
            var win=new Ext.Window({
	            title:"添加岗位基本信息",
	            width:315,
	            height:135,
	            maximizable:true,
			  	closable:true,
			  	autoScroll:false,
			  	items:new Ext.Panel({
			        title: '',        
				  	height:93,
			        width:330,
			        html: '<iframe scrolling="yes" frameborder="0" src="positionaddwindow.jsp?sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
			    })
	        	});
	        	win.on("beforedestroy",
		       function(obj){		
	             window.location="positionqueryListgrid.jsp?sysid='"+sysid+"'&type='"+type+"'";	
	           return false;
           });	
	         win.show();
	         store.reload();
           
            }
        },'-',{
            text:'删 除',
            tooltip:'删除',
            iconCls:'remove',
            handler:showDeleteMessage
        },'-',{
            text:'排  序',
            tooltip:'排 序',
            icon: "/usm/img/sort.gif", 
            cls: "x-btn-text-icon",  
            handler:positionstore
        }],
        
         bbar: new Ext.PagingToolbar({
            pageSize: 10,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "无记录"
        }),     
        
         width:783,
        height:595,
        frame:true,
        title:'岗位基本信息列表',
        iconCls:'icon-grid',
        renderTo: document.body
        
    });
            store.load({params:{start:0, limit:10}});
    function positionstore()
    {
    	//var txtCheckValue;
    	var win;
     	if(!win)
    	{
        win = new Ext.Window
        ({id:'positionwindow',
                title:'岗位列表',
                width:500,
		        height:350,
                x:330,
                y:150,
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',              
                items:new Ext.grid.GridPanel({
        		id:'positionstore',
       			width:300,
        		store:store,
	    		cm:cm,
	   			sm:sm,	    
        		view:view,   
        		trackMouseOver:true,
				loadMask: true,
				autoShow : true,
				enableDragDrop: true,
				viewConfig: {
		    	forceFit:true,
		    	enableRowBody:true,
		    	showPreview:true
		        }
             
        }),
        buttons: [{ 
			text: '保  存', 
			handler: function() {
				sm.selectAll();   //
				var row=Ext.getCmp("positionstore").getSelections();
	            var txtCheckValue="";
	            for(var i=0,len=row.length;i<len;i++){
	            	//alert(positionlogid);
	                var ss = row[i].get("positionid");	                             
	                txtCheckValue=txtCheckValue + ss + ",";
	                //alert(txtCheckValue);
	            }
	            	Ext.Ajax.request({ 
					method:'get', 
					url:"/usm/positionstore.do?n="+Math.random()+"&txtCheckValue="+txtCheckValue, 
					success : function(result, action)
								{ 
								Ext.MessageBox.alert('消息', '操作成功',showpositionstrore);
							  	}, 
				    failure : function(form, action) 
				    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
				}); 
				
				win.close();
				//left_tree_refresh();				
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
     store.load();
       var ddrow = new Ext.dd.DropTarget(Ext.getCmp("positionstore").container, {
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
    }
    positiongrid.render();
  

    positiongrid.on("rowdblclick", function(positiongrid) {
    
        loadFormData(positiongrid);
        //alert(form1.reader);
    });
    
    
	// 载入被选择的数据行的表单数据
    var loadFormData = function(positiongrid) {
        var _record = positiongrid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
        	Ext.getCmp("positionid").value=_record.get("positionid");
        	Ext.getCmp("positionname").value=_record.get("positionname");
        	Ext.getCmp("positiondesc").value=_record.get("positiondesc");
           myFormWin();	
          	
        }
    };

    var myFormWin = function() {
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 400,
                height : 200,
                //closeAction : 'hide',
                maximizable:true,
                plain : true,
                title : '修改岗位信息',
               items:fsf                
            });
        }
        newFormWin.on("beforedestroy",
        function(obj){		
        window.location="positionqueryListgrid.jsp"	;	
        return false;	});
        newFormWin.show('New1');
        store.reload();
    };    
    
    var fsf = new Ext.FormPanel({
		labelAlign: 'right',    
		title: '',    
		buttonAlign:'center',    
		bodyStyle:'padding:8px',    
		anchor:'100%',  
		frame:true,    
       items: [ {
	               id:'positionid',
	               xtype:'textfield',	
	               fieldLabel: '* 岗位编号',	
	               name: 'position.positionid',
	               readOnly:true,     	                              
		           anchor:'65%'
	            },{
	               id:'positionname',
	               xtype:'textfield',	
	               fieldLabel: '* 岗位名称',	
	               name: 'position.positionname',
	               allowBlank:false,//è®¸ä¸ºç©º
		           blankText:'岗位名称不能为空', 
		           checkboxToggle:true,

		           anchor:'65%'
	            },{
	               id:'positiondesc',
	               xtype:'textfield',	
	               fieldLabel: '排序',	
	               name: 'position.positiondesc',
		           anchor:'65%'
	            }
                   ],       
 buttons: [{
            text: '保  存',
            handler: function(){
            var positionname=Ext.get('positionname').dom.value;
            
            var _record = positiongrid.getSelectionModel().getSelected();
            	if(positionname=='')
            	{
            		Ext.MessageBox.alert('岗位名称','岗位名称不能为空');
            	}
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        url: '/usm/posititionModify.do?positionid=' + _record.get('positionid'),
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "操作成功！");
                            this.disabled=false;
                        },
                        failure: function() {
                            Ext.Msg.alert("成功啦", "数据修改成功！");
                            this.disabled=false;
                        }
                    });
                  
                }
            }
        }, {
            text: '取  消',
            handler: function() {
                fsf.form.reset();
            }
        }, {
            text: '退  出',
            handler: function() {
                   window.location="positionqueryListgrid.jsp?sysid='"+sysid+"'&type='"+type+"'"; 
            }
        }
        
        
        
        ]

    });
    
        function showDeleteMessage(){
        Ext.MessageBox.confirm('消息', '确认删除？',doDel);
    }
    
    function doDel(btn){
        if(btn=='yes'){
            var row=positiongrid.getSelections();
            var jsonData = "";
            for(var i=0,len=row.length;i<len;i++){
                var ss = row[i].get("positionid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息', '请选择要删除的数据。');
            }else{
		               Ext.Ajax.request({
		                //请求地址
		                method: 'POST',      
		                url: '/usm/positionDel.do?positionJsonData=' + jsonData,
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
        
    };
    
    function toggleDetails(btn, pressed){
        var view = persongrid.getView();
        view.showPreview = pressed;
        view.refresh();
    } 
    //刷新树
    function left_tree_refresh(){
        var tree = Ext.getCmp('forum-tree');
        tree.body.mask('加载中...','x-mask-loading');
        tree.root.reload();
        tree.root.collapse(true,true);
        setTimeout(function(){
   		tree.body.unmask();
   		tree.root.expand(false,false);
		},100);
};
    function showpositionstrore()
    {       
            window.location="positionqueryListgrid.jsp";	
    }
         function querynameMessage()
    {
    	var name = Ext.get('name').dom.value;    	
    	store.load({params:{start:0, limit:10,name:name}});
    }
});

