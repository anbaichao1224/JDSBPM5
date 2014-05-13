/*
 * 职务信息列表
 */
Ext.onReady(function(){

    var newFormWin;
    
    Ext.QuickTips.init();
    
    var xg = Ext.grid;
    var store = new Ext.data.Store({
        proxy: new Ext.data.HttpProxy({   
            url: '/usm/dutyQueryAll.do?sysid='+sysid+''   //从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'dutyid',
            fields: [
            {name: 'dutyid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'adminflag', type: 'string'},
            {name: 'createtime', type: 'string'},
            {name: 'dutydesc', type: 'string'} 
            ]
        })
    });
    store.load({params:{start:0, limit:10}});
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
          
           id: 'dutyid',       // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "职务编号",
           dataIndex: 'dutyid',
           align:'center',  
           hidden:true,
           width: 60
        },{
           header: "职务名称",  
           dataIndex: 'cnname',
           align:'center',
           width: 100
        },{
           header: "管理标志",
           dataIndex: 'adminflag',
           hidden :true,
           align:'center',
           width: 60
        },{
           header: "创建时间",
           dataIndex: 'createtime',
           format:'Y-m-d h:i:s',
           align:'center',
           width: 60
        },
        {
           header: "显示顺序",
           dataIndex: 'dutydesc',
           align:'center',
           width: 60
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
            
    var dutygrid = new Ext.grid.GridPanel({
        id:'button-grid',
        view:view,
        width:300,
        store:store,
	    cm:cm,
	    sm:sm,
        viewConfig: {
            forceFit:true
        },
        buttonAlign:'center',

        // inline toolbars
        tbar : ['职务名称：',name, {// 查询按钮   
                    id:'newModelButton',    
                    icon:"/usm/img/search.png", 
                    text:'查  询',
                    cls:"x-btn-text-icon",   
                    handler :querynameMessage    
            },{
            text:'新  增',
            tooltip:'新增',
            iconCls:'add',
            handler:function(){
            var w=new Ext.Window({
	            title:"添加职务基本信息",
	            width:450,
	            height:139,
	            maximizable:true,
			  	closable:true,
			  	collapsible:true,
			  	autoScroll:false,
			  	items:new Ext.Panel({
			        title: '',        
				  	height:95,
			        width:430,
			        html: '<iframe scrolling="yes" frameborder="0" src="dutyaddwindow.jsp?sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
			    })
	        	});
	        	w.on("beforedestroy",function(obj){		
	        		//alert("想关闭我，这是不可能的!");	
	        		window.location="dutyqueryListgrid.jsp?sysid="+sysid+"&type="+type+""; 
	        		//obj.show();		
	        		return false;	})   ;
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
        height:540,
        frame:true,
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
    dutygrid.render();


    dutygrid.on("rowdblclick", function(dutygrid) {
        loadFormData(dutygrid);
        //alert(form1.reader);
    });

    // 载入被选择的数据行的表单数据
    var loadFormData = function(dutygrid) {
        var _record = dutygrid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
            Ext.getCmp("dutyid").value=_record.get("dutyid");
            Ext.getCmp("cnname").value=_record.get("cnname");
            Ext.getCmp("createtime").value=_record.get("createtime");
            Ext.getCmp("dutydesc").value=_record.get("dutydesc");
            myFormWin();	
        }
    };

    var myFormWin = function() {

        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 350,
                height : 200,
               // closeAction : 'hide',
                maximizable:true,                
                plain : true,
                title : '修改职务信息',
                items:fsf
                
            });
        }
        newFormWin.on("beforedestroy",function(obj){		
	        		//alert("想关闭我，这是不可能的!");	
	        		window.location="dutyqueryListgrid.jsp?sysid="+sysid+"&type="+type+"";  
	        		//obj.show();		
	        		return false;	
	        		});
        newFormWin.show('New1');
    };

    
    
    function toggleDetails(btn, pressed){
        var view = dutygrid.getView();
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
	                {      
               		   id:'dutyid',      
		               xtype:'textfield',                
		               fieldLabel: '* 职务编号',                
		               name: 'duty.dutyid',    
		               allowBlank:false,
		               blankText:'职务编号不能为空',   
		               readOnly:true,          
		               anchor:'100%'
	                },
	                {      
               		   id:'cnname',      
		               xtype:'textfield',                
		               fieldLabel: '* 职务名称',                
		               name: 'duty.cnname',    
		               allowBlank:false,
		               blankText:'职务名称不能为空',            
		               anchor:'100%'
	                },{
	                    id:'createtime',
	                    xtype:'textfield',	
	                    fieldLabel: '创建时间',	
	                    name: 'level.createtime',
	                    format:'Y-m-d h:i:s',
	                    readOnly:true, 
		                anchor:'100%'
	                }
	                ,
	                {      
               		   id:'dutydesc',      
		               xtype:'textfield',                
		               fieldLabel: '显示顺序',                
		               name: 'duty.dutydesc',
		               readOnly:true,           
		               anchor:'100%'
	                }
                   ],       

 buttons: [{
            text: '保存',
            handler: function(){
            	var cnname=Ext.get('cnname').dom.value;
            	if(cnname=='')
            	{
            		Ext.MessageBox.alert('职务名称','职务名称不能为空');
            	}
             var _record = dutygrid.getSelectionModel().getSelected();
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        
                        url: '/usm/dutyModify.do?dutyid=' + _record.get('dutyid'),
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
            text: '重置',
            handler: function() {
                fsf.form.reset();
            }
        }, {
            text: '返回',
            handler: function() {
            window.location="dutyqueryListgrid.jsp?sysid="+sysid+"&type="+type+""; 
          
            }
        }
        
        
        
        ]

    });


    
    
      
    function showDeleteMessage(){
        Ext.MessageBox.confirm('消息', '确认删除？',doDel);
    }
    function doDel(btn){
        if(btn=='yes'){
            var row=dutygrid.getSelections();
            var jsonData = "";
            for(var i=0,len=row.length;i<len;i++){
                var ss = row[i].get("dutyid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息', '请选择要删除的数据。');
            }else{
		               Ext.Ajax.request({
		                //请求地址
		                method: 'POST',      
		                url: '/usm/dutyPointDel.do?dutyidjsonData=' + jsonData,
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
       store.load({params:{start:0,limit:15,name:name}});
    
    }
});

