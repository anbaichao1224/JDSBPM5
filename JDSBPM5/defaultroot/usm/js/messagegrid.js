Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';

Ext.onReady(function(){

    var newFormWin;
    var uuid;
    var currentRowNumber;
    var urlstr;
    Ext.QuickTips.init();
    var xg = Ext.grid;
    var store = new Ext.data.Store({
     
        proxy: new Ext.data.HttpProxy({   
            url: '/usm/getMsgInfo.do'   // 从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'uuid',
            fields: [
            {name: 'uuid', type: 'string'},
            {name: 'title', type: 'string'},
            {name: 'body', type: 'string'},
            {name: 'sendtime', type: 'string'}
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
           header: "消息标题",
           dataIndex: 'title',
           align:'center',
           width: 100
        },{
            header: "消息内容",
            dataIndex: 'body',
            align:'center',
            width: 200
         },{
             header: "发送时间",
             dataIndex: 'sendtime',
             align:'center',
             width: 50
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
        loadMask: true,
        viewConfig: {
            forceFit:true
        },
        buttonAlign:'center',
        tbar:['消息内容  ：',cnname, {// 查询按钮
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png",
                    text: ' 消息查询',
                    cls : "x-btn-text-icon",   
                    handler :querypersonMessage   
                },{
    				text :'发送消息',
    				tooltip :'发送消息',
    				icon :"/usm/img/add.gif",
    				cls :"x-btn-text-icon",
    				handler : function() {
                	   urlstr ='/usm/msgSend.do';
                	   myFormWin();
    				}
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

       var myFormWin = function() {
        
        if (!newFormWin) {

            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 630,
                height : 250,
                maximizable:true,                
                plain : true,
                title : '消息管理',
                items:fsf,
                listeners:{"beforedestroy":function(obj){
                window.location="message-index.jsp"; 	
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
    	id:'messageform',
    	labelWidth: 100, // 默认标签宽度板
        baseCls: 'x-plain',//不设置该值，表单将保持原样，设置后表单与窗体完全融合
        bodyStyle:'padding:5px 5px 0',
        width: 350,
        border:false,
        defaults: {width: 260},
        defaultType: 'textfield',//默认字段类型 
        items: [
        {
            fieldLabel: '接收人',
            id:'toPerson',
            name: 'toPerson',
            readOnly : true,
            allowBlank:false,
            width:textfieldWidth,
            blankText: '接收人不能为空',
			listeners : {
				'focus' : function() {
                        //打开人员选择窗口，选择人员
        	            window.parent.parent.createMessagePersonWindow(this,Ext.getCmp("toPersonIds"));        
				}
			}
  	
        },{
        	xtype: 'hidden',
            id:'toPersonIds',
            name: 'toPersonIds'	
        },{
            fieldLabel: '消息标题',
            id:'title',
            name: 'title',
            allowBlank:false,
            width:textfieldWidth,
            blankText: '标题不能为空'
            	
        },{
            
            xtype:'textarea',
            fieldLabel: '消息内容',
            id:'body',
            name: 'body',
            allowBlank:false,
            width:textfieldWidth,
            height:180,
            blankText: '内容不能为空'
	    }],
        buttons: [{
            text: '发送消息',
            handler:function(){
	            if(fsf.form.isValid()){
	               fsf.form.doAction('submit',{url:urlstr,
	                          method:'post',
	                          waitTitle:"请稍候",
	                          waitMsg:"正在提交表单数据，请稍候......",
	                          params:'',
	               success:function(form,action){
	                  Ext.Msg.alert('操作','发送完毕！');                                                        
	               },
	               failure:function(){
	            	  Ext.Msg.alert('操作','发送失败！'); 
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
				cnname :cnname
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

