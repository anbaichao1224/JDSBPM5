
function replyhtml(nid,readid) {
	var form = new Ext.FormPanel({
	frame:true, 
	width:580,
	height:270,
	 url:"reply_save.action", 
	items:[
	{xtype:"textarea", 
	 fieldLabel:"反馈内容",
	 name:"content",
	 width:400,
	 height:250
	  },
	  new Ext.form.Hidden({name:"noticeid", fieldLabel:"通知id", value:nid}),
	  new Ext.form.Hidden({name:"readid", fieldLabel:"已阅通知id", value:readid})
	  ],
	   buttons:[{text:"确定", 
	   handler:login,
	    formBind:true
	    }, {
	    text:"重置",
	     handler:reset
	     }]});
	function login() {
		form.getForm().submit({success:function () {
		    Ext.MessageBox.alert('提示','反馈成功');
			Ext.getCmp('addReply').close();
		}});
	}
	function reset() {
		form.form.reset();
	}
	var win = new Ext.Window({
		id:"addReply",
		 title:"反馈内容",
		  width:580, 
		  height:300,
		  
		  items:form
	});
	win.show();
}

