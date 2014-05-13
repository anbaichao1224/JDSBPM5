Ext.onReady(function() {
	
	var fileA = Ext.get('fileA');
	fileA.on('click',function(){
		
		var attachForm = new Ext.FormPanel({
		id: 					'attachForm',
		labelWidth: 			55,
		baseCls: 				'x-plain',
        defaultType: 			'textfield',
        enctype:				'multipart/form-data', 

        // 添加 附件框
        items: [
        	{
				name: 			'desc',
				fieldLabel:		'文件描述',
				anchor : 		'99%', 
	            allowBlank:		true
        	},
        	{ 
				name:			'attachPath', 
				fieldLabel:		'文件路径',
				inputType:		'file',
				anchor : 		'99%',
				allowBlank:		false 
			}
        ]     
    	});
		
		var window = new Ext.Window({
			id:				'attachWin',
	        title: 			'上传附件',
	        width: 			500,
	        height:			300,
	        minWidth: 		300,
	        minHeight: 		200,
	        layout: 		'fit',
	        plain:			true,
	        bodyStyle:		'padding:5px;',
	        buttonAlign:	'center',
	        items: 			attachForm,
	
	        buttons: [
	        	{
					id:			'sendB',
	        		text: 		'上传',
	        		handler:	uploadFile
		        },{
					text:		'增加',
					handler:	addButton
		        },{
		            text: 		'取消',
		            handler: 	close
		        }]
    	});

    	window.show();
	});
	
	function uploadFile(){
		var theform = Ext.getCmp('attachForm').getForm();
		try{	
		    var sForm = theform.el.dom;	
		    var str=Form.serializeElements(Form.getElements(sForm), false); 
		    }catch(e){		
		       Ext.MessageBox.hide();		   
		       alert('表单数据序列化错误，请检查表单定义是否正确！');
		      return false;
		   }
		   
		close();
		getMyAjax('testdiv','/jdsbpm/AttachAction.action',str);
	}
	
	function addButton(){
		    	
		var attachForm = Ext.getCmp('attachForm');
		
		var attachText = new Ext.form.TextField({
			name:			'attachPath', 
			fieldLabel:		'文件路径',
			inputType:		'file', 
			anchor : 		'99%', 
			allowBlank:		false 
		});
		
		attachForm.add(attachText);
		
		Ext.getCmp('attachWin').render();
	}
	
	function close(){
		
		// 关闭文件上传窗口
		var window = Ext.getCmp('attachWin');
		window.close();
	}
    
});