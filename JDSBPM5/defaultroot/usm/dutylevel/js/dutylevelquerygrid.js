/*
 * Ext JS Library 2.0.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

 
Ext.onReady(function(){

    var newFormWin;
    
    var currentRowNumber;
    
    Ext.QuickTips.init();
    
    var xg = Ext.grid;
    var store = new Ext.data.Store({
        // load using script tags for cross domain, if the data in on the same domain as
        // this page, an HttpProxy would be better
   
        proxy: new Ext.data.HttpProxy({   
            url: '/usm/getDutyLevelInfo.do'   //从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'levelid',
            fields: [
            {name: 'levelid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'adminflag', type: 'string'},
             {name: 'createtime', type: 'string'},
            {name: 'leveldesc', type: 'string'}
           
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
        ),{
           id: 'levelid', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
           header: "职级编号",
           dataIndex: 'levelid',
           align:'center',
		   //hidden :false,
           width: 60
        },{
           header: "职级名称",
           dataIndex: 'cnname',
           align:'center',
           width: 100
        },{
           header: "管理标识",
           dataIndex: 'adminflag',
           align:'center',
           hidden :true,
           width: 100
        },{
           header: "创建时间",
           dataIndex: 'createtime',
           format:'Y-m-d h:i:s',
           align:'center',
           width: 100
        },{
           header: "职级排序",
           dataIndex: 'leveldesc',
           align:'center',
           width: 100
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
                name: 'name',
                allowBlank:true 
            });   
    var dutyLevelgrid = new Ext.grid.GridPanel({
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
        tbar:['按职级名称：',name, {// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",   
                    cls : "x-btn-text-icon",   
                    handler :querydutylevelMessage
                          
                     
            },{
            text:'新增',
            tooltip:'新增',
            iconCls:'add',
            handler:function(){
            var w=new Ext.Window({
	            title:"添加职级基本信息",
	            width:400,
	            height:165,
	            maximizable:true,
			 // 	closeAction:'close',
			  	closable:true,
			  	collapsible:true,
			  //	plain: true,
			  //	buttonAlign:'center',
			  	autoScroll:true,
			  	items:new Ext.Panel({
			        title: '',        
				  	height:100,
			        width:350,
			        html: '<iframe scrolling="yes" frameborder="0" src="dutyleveladdwindow.jsp?sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
			    })
	        	});
	        	
	        		w.on("beforedestroy",function(obj){	
	        			window.location="dutylevelqueryListgrid.jsp?sysid="+sysid+"&type="+type+""; 	
	        		return false;	});     	
	         w.show();
           
            }
        },'-',{
            text:'删除',
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
        width:780,
        height:350,
        frame:true,
        title:'职级基本信息列表',
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
    dutyLevelgrid.render();

  

    dutyLevelgrid.on("rowdblclick", function(dutyLevelgrid) {
        loadFormData(dutyLevelgrid);
        //alert(form1.reader);
    });

    // 载入被选择的数据行的表单数据
    var loadFormData = function(dutyLevelgrid) {
        var _record = dutyLevelgrid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
            Ext.getCmp("levelid").value=_record.get('levelid');
           Ext.getCmp("cnname").value=_record.get('cnname');
            Ext.getCmp("leveldesc").value=_record.get('leveldesc');
            Ext.getCmp("createtime").value=_record.get('createtime');
            
           myFormWin();	
        }
    };

    var myFormWin = function() {
        // create the window on the first click and reuse on subsequent
        // clicks
        
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 400,
                height : 200,
              //  closeAction : 'hide',
                maximizable:true,                
                plain : true,
                title : '修改职务信息',
                items:fsf                
            });
        }        	
	        	newFormWin.on("beforedestroy",
        function(obj){		
        	window.location="dutylevelqueryListgrid.jsp?sysid="+sysid+"&type="+type+""; 
        return false;	});
        newFormWin.show('New1');
        store.reload();
    };

    
    
    function toggleDetails(btn, pressed){
        var view = dutyLevelgrid.getView();
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
	                    id:'levelid',
	                    xtype:'textfield',	
	                    fieldLabel: '* 职级编号',	
	                    name: 'level.levelid',
	                    readOnly:true,  
		                anchor:'80%'
	
	                },
	                {
	                    id:'cnname',
	                    xtype:'textfield',	
	                    fieldLabel: '* 职级名称',	
	                    name: 'level.cnname',
	                    allowBlank:false,//不允许为空
		           		blankText:'职级名称不能为空',//错误提示内容  
		           		checkboxToggle:true,
		           		regex: /^[\u4E00-\u9FA5]+$/, 
          		   		regexText: '只能输入汉字' ,
		                anchor:'80%'
	                }, {
	                    id:'cnname',
	                    xtype:'textfield',	
	                    fieldLabel: '* 职级名称',	
	                    name: 'level.cnname',
	                    allowBlank:false,//不允许为空
		           		blankText:'职级名称不能为空',//错误提示内容  
		           		checkboxToggle:true,
		           		
		                anchor:'80%'
	                },{
	                    id:'createtime',
	                    xtype:'textfield',	
	                    fieldLabel: '创建时间',	
	                    name: 'level.createtime',
	                    format:'Y-m-d h:i:s',
	                    readOnly:true, 
		                anchor:'80%'
	
	                },{
	                    id:'leveldesc',
	                    xtype:'textfield',	
	                    fieldLabel: '职级名称',	
	                    name: 'level.leveldesc',
	                   
	                    readOnly:true,  
		                anchor:'80%'
	
	                }
	               	  ],  

 buttons: [{
            text: '修改',
            handler: function(){
                if(fsf.form.isValid()) {
                 var _record = dutyLevelgrid.getSelectionModel().getSelected();
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        url: '/usm/dutyLevelModify.do?levelid=' + _record.get('levelid'),
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "修改成功！");
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
        }, {
            text: '返回',
            handler: function() {
              window.location='dutylevelqueryListgrid.jsp?sysid='+sysid+'&type='+type+''; 
            }
        }
        ]

    });


    
    
      
    function showDeleteMessage(){
        Ext.MessageBox.confirm('消息', '确认删除？',doDel);
    }
    function doDel(btn){
        if(btn=='yes'){
            var row=dutyLevelgrid.getSelections();
            var jsonData = "";
            for(var i=0,len=row.length;i<len;i++){
                var ss = row[i].get("levelid");
                jsonData = jsonData + ss + ",";
            }
            if(jsonData.length == 0 ){
                 Ext.MessageBox.alert('消息', '请选择要删除的数据。');
            }else{
		               Ext.Ajax.request({
		                //请求地址
		                method: 'POST',      
		                url: '/usm/dutylevelPointDel.do?levelidjsonData=' + jsonData,
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
    
    
         function querydutylevelMessage()
    {
    	var name = Ext.get('name').dom.value;
    	store.load({params:{start:0, limit:25,name:name}});
    }
});

