Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';

Ext.onReady(function(){
	
    var newFormWin;
    var currentRowNumber;
    var urlstr;
    
    var icon;
    Ext.QuickTips.init();
    var myFormWin = function() {
        if (!newFormWin) {
            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 1024,
                height : 768,
                maximizable: true,
                plain : true,
                title : '报表信息管理',
                items:fsf,
                listeners:{"beforedestroy":function(obj){
                   parent.close();	
            	   return false;	
            	}}
            });
        }
        newFormWin.show('New1');
    };

    var textfieldWidth = 500;
    var fsf = new Ext.FormPanel({
    	
    	labelWidth: 75, // 默认标签宽度板
        frame:true,
        title: '报表管理',
        bodyStyle:'padding:10px 10px 10px',
        border:false,
        defaults: {width: 260},
        defaultType: 'textfield',//默认字段类型 
        items: [
        {
            fieldLabel: '报表名称',
            id:'cnname',
            name: 'cnname',
            value:cnname,
            allowBlank:false,
            width:textfieldWidth,
            blankText: '名称不能为空'
        },{
            fieldLabel: '报表说明',
            id:'memo',
            name: 'memo',
            value:memo,
            allowBlank:true,
            width:textfieldWidth
	    },new Ext.TabPanel(
				{
					autoTabs :true,
					anchor :'-20,-20',
					margins :'3 3 3 0',
					height:600,
					defaults : {
						autoScroll :true
					},
					items : [
							{
								id :'report',
								title:'报表设计器',
								html :'<iframe name="reportDef" id="reportDef" scrolling="yes" frameborder="0" '
									+' src="/report/newReportDef.jsp?tempPath='+tempPath+'&runtimePath='+runtimePath+'&isOpenTemp='+isOpenTemp+'&tempDir='+tempDir+'"'
									+' height=100% width=100%></iframe>'
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
        	    //alert(uuid);
        	    
        	    urlstr ='/usm/rcModuleSave.do?moduletype=1&uuid='+uuid+'&funcurl='+reportUrl+'&isOpenTemp='+isOpenTemp;
        	    //alert(urlstr);
        	    //取报表地址
        	    var doc;
                if (document.all){//IE 
                     doc = document.frames["reportDef" ].document;
                }else {//Firefox     
                     doc = document.getElementById("reportDef").contentDocument;
                }
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
        	parent.close(); 
          }
        }
        ]
        
    });
    fsf.render(Ext.getBody());
   
});

