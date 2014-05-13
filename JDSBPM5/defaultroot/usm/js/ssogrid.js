Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';

Ext.onReady(function(){

    var newFormWin;
    var uuid;
    var currentRowNumber;
    var urlstr;
    var icon;
    Ext.QuickTips.init();
    var xg = Ext.grid;
    var store = new Ext.data.Store({
     
        proxy: new Ext.data.HttpProxy({   
            url: '/usm/getSsoInfo.do'   // 从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'ssoid',
            fields: [
            {name: 'uuid', type: 'string'},
            {name: 'sso_name', type: 'string'},
            {name: 'sso_url', type: 'string'},
            {name: 'sso_userpar', type: 'string'},
            {name: 'sso_passwordpar', type: 'string'},
            {name: 'sso_script', type: 'string'},
            {name: 'sso_errorurl', type: 'string'},
            {name: 'sso_mainmethod', type: 'string'},
            {name: 'sso_time', type: 'string'}
            ]
        })
    });
      
        var sm = new Ext.grid.CheckboxSelectionModel(); //
    	var cm = new Ext.grid.ColumnModel([sm,
    	new Ext.grid.RowNumberer(
	     {
		     header:'序号',
		     width:32,
		     align:'center'
	     }
        ),{
           id: 'uuid',
           header: "编 号",
           dataIndex: 'uuid',
           align:'center',
		   hidden:true,
           width: 60
        },{
           header: "名 称",
           dataIndex: 'sso_name',
           align:'center',
           width: 100
        },{
           header: "地 址",
           dataIndex: 'sso_url',
           align:'center',
           width: 100
        },{
           header: "错误地址转向页面",
           dataIndex: 'sso_errorurl',
           align:'center',
           //hidden :true,
           width: 100
        },{
            header: "脚本主方法名称",
            dataIndex: 'sso_mainmethod',
            align:'center',
            //hidden :true,
            width: 100
         },{
             header: "Session 失效时间",
             dataIndex: 'sso_time',
             align:'center',
             //hidden :true,
             width: 100
          }
        ]);
    cm.defaultSortable = true;
    var sm = new xg.CheckboxSelectionModel();
    var cnname = new Ext.form.TextField({   
                width:175, 
                name: 'cnname',
                id:'cnname',
                allowBlank:true 
    }); 
    var ssogrid = new Ext.grid.GridPanel({
        id:'button-grid',
        width:800,
        store:store,
	    cm:cm,
	    sm:sm,
        loadMask: true,// 装载动画
        viewConfig: {
            forceFit:true
        },
        buttonAlign:'center',
        tbar:['名  称：',cnname, {// 查询按钮
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",
                    text: '查 询',
                    cls : "x-btn-text-icon",   
                    handler :querypersonMessage   
                },{
    				text :'添加SSO',
    				tooltip :'添加SSO',
    				icon :"/usm/img/add.gif",
    				cls :"x-btn-text-icon",
    				handler : function() {
                	   urlstr ='/usm/ssoSave.do';
                	   myFormWin();
    				}
    			}, {
    				text :'删除SSO',
    				tooltip :'删除SSO',
    				icon :"/usm/img/delete.gif",
    				cls :"x-btn-text-icon",
    				handler :showDelMess
    			}],
         bbar: new Ext.PagingToolbar({
            pageSize: 20,
            store: store,
            displayInfo: true,
            displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
            emptyMsg: "无记录"
        }),     
        
        width:1000,
        height:540,
        frame:true,
        iconCls:'icon-grid',
        renderTo: document.body
    });
    
    ssogrid.render();                         // Ext.getCmp('grid').render();//渲染表格
    store.load({params:{start:0, limit:20}}); // 加载数据
    ssogrid.on("rowdblclick", function(ssoid) {
        loadFormData(ssogrid);
    });

    // 载入被选择的数据行的表单数据
    var loadFormData = function(ssogrid) {
        var _record = ssogrid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
        	Ext.getCmp('sso_name').value =_record.get('sso_name');
        	Ext.getCmp('sso_url').value =_record.get('sso_url');
        	Ext.getCmp('sso_errorurl').value =_record.get('sso_errorurl');
        	Ext.getCmp('sso_time').value =_record.get('sso_time');
        	Ext.getCmp('sso_mainmethod').value =_record.get('sso_mainmethod');
        	uuid=_record.get('uuid');
        	urlstr ='/usm/ssoModify.do?uuid='+uuid;
        	myFormWin();	

        }
    };

    var myFormWin = function() {
        
        if (!newFormWin) {

            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 630,
                height : 250,
                maximizable:true,                
                plain : true,
                title : 'SSO信息管理',
                items:fsf,
                listeners:{"beforedestroy":function(obj){
                window.location="sso-index.jsp"; 	
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
    }; 
    var textfieldWidth = 470;
    var fsf = new Ext.FormPanel({
    	labelWidth: 100, // 默认标签宽度板
        baseCls: 'x-plain',//不设置该值，表单将保持原样，设置后表单与窗体完全融合
        bodyStyle:'padding:5px 5px 0',
        width: 350,
        border:false,
        fileUpload: true,
        defaults: {width: 260},
        defaultType: 'textfield',//默认字段类型 
        items: [
        {
            fieldLabel: 'SSO名称',
            id:'sso_name',
            name: 'sso_name',
            allowBlank:false,
            width:textfieldWidth,
            blankText: '名称不能为空'
            	
        },{
            fieldLabel: '登录地址',
            id:'sso_url',
            name: 'sso_url',
            allowBlank:false,
            width:textfieldWidth,
            blankText: '地址不能为空'
	    },{                
            xtype: 'fileuploadfield',
            id: 'file',
            emptyText: '请选择登录脚本',             
            fieldLabel: '登录脚本',                
            name: 'file',
            width:textfieldWidth-50,
            anchor:'100%', 
	           buttonCfg: {
	                text: '浏览',
	                iconCls: 'upload-icon'
	            }
         },{
             fieldLabel: '错误返回地址',
             id:'sso_errorurl',
             name: 'sso_errorurl',
             width:textfieldWidth
             	
         },{
             fieldLabel: '登录脚本主方法',
             id:'sso_mainmethod',
             name: 'sso_mainmethod',
             width:textfieldWidth
 	    },{
            fieldLabel: 'Session失效时间',
            id:'sso_time',
            name: 'sso_time',
            width:textfieldWidth
	    }
	    
	    ],
        buttons: [{
            text: '保   存',
            handler:function(){
	            if(fsf.form.isValid()){
	               this.disabled=true;
	                alert(urlstr);
	               fsf.form.doAction('submit',{url:urlstr,
	                          method:'post',
	                          waitTitle:"请稍候",
	                          waitMsg:"正在提交表单数据，请稍候......",
	                          params:'',
	               success:function(form,action){
	                  Ext.Msg.alert('操作','保存成功！');                                                        
	                  this.disabled=false;
	               },
	               failure:function(){
	            	  Ext.Msg.alert('操作','保存失败！'); 
 			          this.disabled=false;
			       }
	               });
	           }
          }
        },{
            text: '退   出',
            handler:function(){
        	newFormWin.close(); 
          }
        }
        ]
        
    });
    function querypersonMessage(){
    	var cnname = Ext.getCmp('cnname').getValue();
		store.load( {
			params : {
				start :0,
				limit :25,
				sso_name :cnname
			}
		});
    }
	function showDelMess() {
		Ext.MessageBox.confirm('消息', '确认删除？', doDel);
	}
	function doDel(btn) {
		if (btn == 'yes') {
			var row = ssogrid.getSelections();
			var jsonData = "";
			for ( var i = 0, len = row.length; i < len; i++) {
				var ss = row[i].get("uuid");
				jsonData = jsonData + ss + ",";
			}
			if (jsonData.length == 0) {
				Ext.MessageBox.alert('消息', '请选择要删除的数据!');
			} else {
				Ext.Ajax.request( {
					method :'get',
					url :"/usm/ssoPointDel.do?ssoidjsonData="
							+ jsonData,
					success : function(result, action) {
						Ext.MessageBox.alert('消息', '删除成功!');
						store.reload();
					},
					failure : function(form, action) {
						Ext.MessageBox.alert('消息', '删除失败!');
					}
				});

			}
		}

	}
});

