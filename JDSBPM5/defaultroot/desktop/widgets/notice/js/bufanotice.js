
// 人员窗体
function createPersonPositionWindow(positionid, positionname) {
	var txtCheckValue;
	var txtCheckName;
    var disabledIds='';
	var nid = document.getElementById("personid").value;
	var disabledIds=document.getElementById("yfid").value;
	var _width = 300;
	var _height = Ext.getBody().getHeight()-40;
   
   var tabs = new Ext.TabPanel({
    activeTab:0,                       //指定默认的活动tab
    width: 500,
    height: 300,
    plain: true,                        //True表示tab候选栏上没有背景图片（默认为false）
    enableTabScroll: true,              //选项卡过多时，允许滚动
    defaults: { autoScroll: true },
    items: [{

        id: "tab1",

        title: '部门人员',

        html: "<iframe id='orgtreeiframe' name='orgtreeiframe' scrolling='auto' width='" + (_width-20)  + "' height='290' src='NoticedeptAction_addtree.action?checked="+nid+"&disabled="+disabledIds+"'></iframe>"


    }, {

        id: "tab2",

        title: '局领导',

         html: "<iframe id='leadertreeiframe' name='leadertreeiframe' scrolling='auto' width='" + (_width-20)  + "' height='290' src='NoticedeptAction_addLeadertree.action?checked="+nid+"&disabled="+disabledIds+"'></iframe>"        //指定了当前tab正文部分从哪个html元素读取

    }, {

        id: "tab3",

        title: '科长',

         html: "<iframe id='kytreeiframe' name='kytreeiframe' scrolling='auto' width='" + (_width-20)  + "' height='290' src='NoticedeptAction_addkytree.action?checked="+nid+"&disabled="+disabledIds+"'></iframe>"  

    }]

});
	var win = new Ext.Window(
			{
				id :'positionWin',
				title :'人员列表',
				//width :220,
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				layout : 'fit',
				width : _width,
				minWidth : 350,
				height : 400,
				minHeight : 200,
				items:[tabs],
				buttons : [
						{
							text :'确定',
							handler : function() {
							var id =tabs.getActiveTab().getId();
							var frameId;
							if(id=='tab1')
								frameId='orgtreeiframe';
							else if(id=='tab2'){
								frameId='leadertreeiframe';
							}else{
							   frameId='kytreeiframe';
							}
							var retValue = document.getElementById(frameId).contentWindow.getCheckedNames();
	                        document.getElementById("extrasendrange").value=retValue.names;
							document.getElementById("personid").value=retValue.ids;
							Ext.getCmp("positionWin")
									.close();
							}
						}, {
							text :'取消',
							handler : function()
							 {
								win.close();
							 }
						},{
							
					
							text :'短信提醒',
							id:"tixing",
							name:"tixing",
						
							handler : function() {
								var form = new Ext.FormPanel({
								
									
								frame:true, 
								width:580,
								height:270,
								id : "form",
								items:[
									 {
									
										 xtype:"textarea", 
										 fieldLabel:"短信内容",
										 name:'name',
										 width:400,
										 height:40
										
									  }]
									  	})
									var win = new Ext.Window({
									id:"xinxi",
									 title:"短信提醒",
									  width:550, 
									  height:120,
									  items:form,
									  	buttons : [{
											text : '确定',
											
											handler : function() {
											var id =tabs.getActiveTab().getId();
							var frameId;
							if(id=='tab1')
								frameId='orgtreeiframe';
							else if(id=='tab2'){
								frameId='leadertreeiframe';
							}else{
							   frameId='kytreeiframe';
							}
											var retValue = document.getElementById(frameId).contentWindow.getCheckedNames();
											var content=form.getForm().findField("name").getValue();
											document.getElementById("addNotice").sms.value=content;
											document.getElementById("addNotice").flag1.value=1;
											Ext.getCmp("xinxi").close();
											Ext.Msg.alert('提示','保存成功');
													
																}
											}]
								});
								win.show();
								
							
							
							}
							
						
						}  ]

			});

	win.show();
	
}
