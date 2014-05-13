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
            url: '/usm/getReportModuleInfo.do?moduletype=1'   // 从此页获取数据
        }),
         reader: new Ext.data.JsonReader({
            root: 'data',
            totalProperty: 'totalCount',
            id: 'reportid',
            fields: [
            {name: 'moduleid', type: 'string'},
            {name: 'cnname', type: 'string'},
            {name: 'memo', type: 'string'},
            {name: 'createtime', type: 'string'},
            {name: 'funcurl', type: 'string'}
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
           dataIndex: 'moduleid',
           align:'center',
		   hidden:true,
           width: 60
        },{
           header: "报表名称",
           dataIndex: 'cnname',
           align:'center',
           width: 60
        },{
           header: "报表地址",
           dataIndex: 'funcurl',
           align:'center',
           width: 120,
           renderer: _showHref
        },{
           header: "报表说明",
           dataIndex: 'memo',
           align:'center',
           width: 160
        },{
            header: "创建时间",
            dataIndex: 'createtime',
            align:'center',
            hidden :true,
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
    var grid = new Ext.grid.GridPanel({
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
    				text :'添加报表',
    				tooltip :'添加报表信息',
    				icon :"/usm/img/add.gif",
    				cls :"x-btn-text-icon",
    				handler : function() {
	             	   //urlstr ='/usm/rcModuleSave.do?moduletype=1';
                	   
	            	   openWin('/usm/report/reportdefine.jsp?isOpenTemp=false');
    				}
    			}, {
    				text :'删除报表',
    				tooltip :'删除报表信息',
    				icon :"/usm/img/delete.gif",
    				cls :"x-btn-text-icon",
    				handler :showDelMess
    			}
    			/*
    			, {
    				text :'报表定义',
    				tooltip :'删除报表信息',
    				icon :"/usm/img/grid.png",
    				cls :"x-btn-text-icon",
    				handler :doReportDefine
    			}*/
    			],
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
    
    grid.render();                         // Ext.getCmp('grid').render();//渲染表格
    store.load({params:{start:0, limit:20}}); // 加载数据
    grid.on("rowdblclick", function(reportid) {
        loadFormData(grid);
    });

    // 载入被选择的数据行的表单数据
    var loadFormData = function(grid) {
        var _record = grid.getSelectionModel().getSelected();
        if (!_record) {
            Ext.Msg.alert('修改操作', '请选择要修改的一项！');
        } else {
        	document.getElementById("cnname").value=_record.get('cnname');
        	document.getElementById("memo").value=_record.get('memo');
        	uuid=_record.get('moduleid');
        	openWin('/usm/report/reportdefine.jsp?uuid='+uuid+'&isOpenTemp=true');
        }
    };
    var myFormWin = function() {  
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 630,
                height : 160,
                maximizable:true,                
                plain : true,
                title : '报表信息管理',
                items:fsf,
                listeners:{"beforedestroy":function(obj){
                window.location="report-index.jsp"; 	
            	return false;	
            	}}
            });
        }
        newFormWin.show('New1');
    };
    function openWin(url){
    	window.open(url, 'newwindow', 
    		'height=768, width=1024,top=0, left=0, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
    }
    function toggleDetails(btn, pressed){
        var view = persongrid.getView();
        view.showPreview = pressed;
        view.refresh();
    }; 
    var textfieldWidth = 500;
    var fsf = new Ext.FormPanel({
    	labelWidth: 75, // 默认标签宽度板
        baseCls: 'x-plain',//不设置该值，表单将保持原样，设置后表单与窗体完全融合
        bodyStyle:'padding:5px 5px 0',
        width: 350,
        border:false,
        fileUpload: true,
        defaults: {width: 260},
        defaultType: 'textfield',//默认字段类型 
        items: [
        {
            fieldLabel: '报表名称',
            id:'cnname',
            name: 'cnname',
            allowBlank:false,
            width:textfieldWidth,
            blankText: '名称不能为空'
            	
        },{
            fieldLabel: '报表说明',
            id:'memo',
            name: 'memo',
            allowBlank:true,
            width:textfieldWidth
	    },new Ext.TabPanel(
				{
					autoTabs :true,
					anchor :'-20,-20',
					margins :'3 3 3 0',
					height:500,
					defaults : {
						autoScroll :true
					},
					items : [
							{
								id :'report',
								title :'报表设计',
								html :'<iframe name="reportDef" id="reportDef" scrolling="yes" frameborder="0" src="/report/newReportDef.jsp" height=100% width=100%></iframe>'
							}
							],
					activeTab :0,
					deferredRender :true,
					border :true
				})
	    ],
        buttons: [{
            text: '保   存',
            handler:function(){
	            if(fsf.form.isValid()){
	               this.disabled=true;
	               
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
				cnname :cnname
			}
		});
    }
	function showDelMess() {
		Ext.MessageBox.confirm('消息', '确认删除？', doDel);
	}
	function doDel(btn) {
		if (btn == 'yes') {
			var row = grid.getSelections();
			var jsonData = "";
			for ( var i = 0, len = row.length; i < len; i++) {
				var ss = row[i].get("moduleid");
				jsonData = jsonData + ss + ",";
			}
			if (jsonData.length == 0) {
				Ext.MessageBox.alert('消息', '请选择要删除的数据!');
			} else {
				Ext.Ajax.request( {
					method :'get',
					url :"/usm/rcModulePointDel.do?rcidjsonData="
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
	function doReportDefine(){
		  alert('定义图表！');
	}
	function _showHref(value, cellmeta, record, rowIndex, columnIndex, store){
	    var returnStr;
	    returnStr = '<a target="_blank" href="' + record.data["funcurl"] + '">'+value+'</a>';
	    return returnStr;
	}
});
