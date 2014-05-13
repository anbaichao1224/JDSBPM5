Ext.onReady(function(){
var form = new Ext.form.FormPanel({
 labelAlign:'left',
 title:'新建通知公告',
 labelWidth:80,
 frame:true,
 border:'true',
 fileUpload:true,
 items:[
 {
   layout:'column',
    items:[
    {
    columnWidth:.5,
    layout:'form',
    defaultType:'textfield',
     items:[
         {fieldLabel:'起草人'},
         {fieldLabel:'起草单位'},
         {fieldLabel:'标题'},
         {fieldLabel:'联系人'},
         {fieldLabel:'联系电话'}
      ]
      
     },
      {
        columnWidth:.5,
        layout:'form',
        items:[
         {xtype:'textfield',fieldLabel:'签发人'},
         {xtype:'datefield',fieldLabel:'签发时间'},
         {xtype:'textfield',fieldLabel:'发送范围'},
         {xtype:'datefield',fieldLabel:'发送时间'}
        ] 
      }]
     },
     {  xtype:'textfield',
        fieldLabel:'附件',
        width:200,
  	    name:'file',
    	inputType:'file'
      },
      new Ext.form.TextArea({
  		width:400,
  		height:300,
  		grow:true,
  		preventScrollbars:true,
  		fieldLabel:'通知内容',
  		allowBlank:false
  })
        
      
      ],
   
  buttons:[{
 
     text:'发送',
     handler:function(){
         Ext.Ajax.request({
          url:'noticeAction.action',
          method:'post',
          waitMsg:'正在处理，请稍候...',
          params:{},
          success:function(){
             alert("发送成功");
          },failure:function(){
             alert("发送失败");
          }
         });
     
     }
  
  },{
  	text:'关闭',
  	handler:function(){
  	
  	   form.hide();
  	
  	}
  
  }]
});




var createwindow = new Ext.Window({
   el:'createwindow',
   layout:'fit',
   width:800,
   height:600,
   closeAction:'hide',
   items:[form],
   title:'通知公告',
   constrainHeader:true,
   constrain:true,
   maximizable:true,
   minimizable:true,
   buttons:[{
   	text:'关闭',
   	handler:function(){
   	   createwindow.hide();
   	
   	}
   
   }]


});

createwindow.show();
form.render('form');

});




