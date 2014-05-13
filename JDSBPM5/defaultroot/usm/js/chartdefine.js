Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';

Ext.onReady(function(){
	
    var newFormWin;
    var uuid;
    var currentRowNumber;
    var urlstr;
    var icon;
    Ext.QuickTips.init();
    var myFormWin = function() {
        
        if (!newFormWin) {

            newFormWin = new Ext.Window( {
                layout : 'fit',
                width : 630,
                height : 160,
                maximizable: true,
                //maximizable:true,                
                plain : true,
                title : '图表信息管理',
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
        //baseCls: 'x-plain',//不设置该值，表单将保持原样，设置后表单与窗体完全融合
        //bodyStyle:'padding:5px 5px 0',
        //width: 350,
        frame:true,
        title: '图表管理',
        bodyStyle:'padding:10px 10px 10px',
        border:false,
        defaults: {width: 260},
        defaultType: 'textfield',//默认字段类型 
        items: [
        {
            fieldLabel: '图表名称',
            id:'cnname',
            name: 'cnname',
            allowBlank:false,
            width:textfieldWidth,
            blankText: '名称不能为空'
        },{
            fieldLabel: '图表说明',
            id:'memo',
            name: 'memo',
            allowBlank:true,
            width:textfieldWidth
	    },new Ext.TabPanel(
				{

					//autoHeight:true,
					autoTabs :true,
					//region :'center',
					anchor :'-20,-20',
					margins :'3 3 3 0',
					height:500,
					defaults : {
						autoScroll :true
					},
					items : [
							{
								id :'chart',
								title :'图表设计',
								html :'<iframe name="chartDef" id="chartDef" scrolling="yes" frameborder="0" src="/fdt/formeditor/chartedit.do" height=100% width=100%></iframe>'
							},
							{
								id :'bindData',
								title :'绑定数据',
								listeners: {activate: handleActivate},
								html :'<iframe name="data" id="data" scrolling="yes" frameborder="0" src="/fdt/designer/fdtDesigner.do?fileName=sssss" height=100% width=100%></iframe>'
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
        	    //取图表地址
        	    var doc;
                if (document.all){//IE 
                     doc = document.frames["chartDef" ].document;
                }else {//Firefox     
                     doc = document.getElementById("chartDef" ).contentDocument;
                }
                //urlstr = '/usm/rcModuleSave.do?moduletype=2&funcurl='+;
                //alert(doc.getElementById("path").value);
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
   
   //myFormWin();
    fsf.render(Ext.getBody());
function handleActivate(tab){
	
	if (tab.id=="bindData"){
	    var doc;
	    if (document.all){//IE 
	         doc = document.frames["chartDef"].document;
	    }else {//Firefox     
	         doc = document.getElementById("chartDef").contentDocument;
	    }
	    var fileName = doc.getElementById('path').value;
	    
	    if (fileName !=""){
	    	fileName = fileName.substr(1,fileName.length-1);
	    }
	    Ext.get('data').dom.src='/fdt/designer/chartDesigner.do?fileName='+fileName;

	}
	
}
   
});

