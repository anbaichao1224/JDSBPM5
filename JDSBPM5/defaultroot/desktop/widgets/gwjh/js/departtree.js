
// 人员窗体
function createPersonPositionWindow(positionid, positionname) {
	var txtCheckValue;
	var txtCheckName;
	var ids = document.getElementById("id").value;
    var disabledIds='';
  	var jjcd = document.getElementById("sendbean.sendJjcd").value;
    var title = document.getElementById("sendbean.sendTitle").value;
    var wh = document.getElementById("sendbean.sendWh").value;
    var fdbh = document.getElementById("sendbean.sendType").value;
	var _width = 400;
	var _height = Ext.getBody().getHeight()-40;
	var root = new Ext.tree.AsyncTreeNode( {
		text :'组织机构',
		leaf :false,
		cls :'folder',
		draggable :false,
		id :'toproot',
		expanded:function(){
		
			alert("expand");
		}
	});
	
	var win = new Ext.Window(
			{
				id :'positionWin',
				title :'单位列表',
				//width :220,
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
                 html:"<iframe id='orgtreeiframe' name='orgtreeiframe' width='" + (_width-10)  + "' height='" +
                 (_height - 50) + "' src='deptAction_addtree.action?checked="+ids+"&disabled="+disabledIds+"'></iframe>",
				buttons : [
				
						{
							text :'确定',
							handler : function() {
							var retValue = document.getElementById('orgtreeiframe').contentWindow.getCheckedNames();
	                        document.getElementById("deptnames").value=retValue.names;
							document.getElementById("id").value=retValue.ids;
							Ext.getCmp("positionWin")
									.close();
							}
						},{
							text :'导入',
							handler : function() {
								
								var form = new Ext.FormPanel({
								frame:true, 
								width:580,
								height:270,
								items:[
									 {
									
										 xtype:"textarea", 
										 fieldLabel:"导入部门",
										 name:"dept",
										 id:'dept',
										 width:400,
										 height:250
									  }
								 ],
								  
								   buttons:[
								  	 {
									   text:"确定", 
									   handler:function(){
										   var deptname=form.getForm().findField("dept").getValue();
										   	//java 全角空格转换成半角空格
	
											var result=""; 
											for(var i=0;i<deptname.length;i++) 
											  { 
												code = deptname.charCodeAt(i);//获取当前字符的unicode编码 
												if (code >= 65281 && code <= 65373)//在这个unicode编码范围中的是所有的英文字母已经各种字符 
												{ 
												var d=deptname.charCodeAt(i)-65248; 
												result += String.fromCharCode(d);//把全角字符的unicode编码转换为对应半角字符的unicode码 
												} 
												else if (code == 12288)//空格 
												{ 
												var d=deptname.charCodeAt(i)-12288+32; 
												result += String.fromCharCode(d); 
												} 
												else 
												{ 
												result += deptname.charAt(i); 
												} 
											} 

										   var errdept="";
										   var strs= new Array(); //定义一数组
										  var d1= deptname.trim(); 
											strs=d1.split(/\r?\n/); //字符分割
											for(var i=0;i<strs.length;i++){  
											  if((strs[i]).length>0){
									  		   var retValue1 = document.getElementById('orgtreeiframe').contentWindow.setCheckByName(strs[i]);
									  		
									  		   if(retValue1==false){
									  		       errdept+=strs[i]
									  		   }
								  		 
									  		 
									  		  
									  		  }
							            }
							            
							                form.getForm().findField("dept").setValue(errdept);
							        
							  		 }
							  
							    } ]
							    
							    });
								
								var win = new Ext.Window({
									id:"addReply",
									 title:"导入部门",
									  width:580, 
									  height:300,
									  
									  items:form
								});
								win.show();
                        }

					
						},{
							text :'取消',
							handler : function() {
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
											var retValue = document.getElementById('orgtreeiframe').contentWindow.getCheckedNames();
										 	 document.getElementById("deptnames").value=retValue.names;
											var ids = 	document.getElementById("id").value=retValue.ids;
											var content=form.getForm().findField("name").getValue();
											document.getElementById("addSend").sms.value=content;
											document.getElementById("addSend").flag1.value=1;
											Ext.getCmp("xinxi").close();
											Ext.Msg.alert('提示','保存成功');
													
																}
											}]
								});
								win.show();
								
							
							
							}
							
						
						} ]

			});

	win.show();


}

function splitDeptNames(deptnames){
	var strs= new Array(); //定义一数组
	strs=deptnames.split(","); //字符分割      
	for (i=0;i<strs.length ;i++ )    
	    {    
	        document.write(strs[i]+"<br/>");    //分割后的字符输出
	     } 

}
