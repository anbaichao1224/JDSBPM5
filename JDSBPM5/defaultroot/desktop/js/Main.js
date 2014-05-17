
var barMove=false;
var jdsPreferences=new JDSPreferences();
var qoPreferences=jdsPreferences;
var openurl;
Ext.BLANK_IMAGE_URL = '/js/ext/resources/images/default/s.gif';
Ext.onReady(function(){

// JDS.ieExplorer = new JDS.IeExplorer();
startwin();

});



var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex : 'index'
			});
/**
 * 初始化应用程序
 */	
JDSDesk = new Ext.app.App({
	init :function(){
	 Ext.form.Field.prototype.msgTarget = 'side';
	 Ext.QuickTips.init();
	  doOk();
	  updateClient();
	},
	//jyc2012-11-10修改
		getLogoutButtonConfig : function(){
		return {
		            text:'退出登录',
		            iconCls:'logout',
		            handler: function(){               
						Ext.MessageBox.show(
						 {
					       title:"退出登录",
					       msg:"请确认是否退出？",
					       width:300,
					       buttons : {
					                  
					       ok : "退出登录",
					       cancel : "取消"                                     
					       },
					       fn: closewin,                     
					       animEl:document.body,
					       icon: Ext.MessageBox.QUESTION
						 });
		                 },
		            scope:this
		      
		};
	},
	getStartConfig : function(){
		var StartConfig= {
	            title: '当前用户：'+currUserOrgName+' '+currUserName,
	            iconCls: 'user',
	            toolItems: [ {
		            text:'资源下载',
		            icon : "/usm/img/user.png",
		            handler: function(){   
			           
			          window.open ('http://19.177.51.30/zyxz.html')
			                   
		                 },
		            scope:this
		      
		},
	            
	            {
	                text:'系统设置',
	               iconCls:'settings',
	                   handler: function(){  
	                      openWinById('JDSPreferences');
	                     },
	                scope:this
	            },{
	                text:'密码修改',
	                iconCls:'bogus',
	            	handler:function(){
	            	 var fsf=new Ext.form.FormPanel({
						    labelAlign : 'left',
							labelWidth : 100,
							width:300,
							frame : true,
							defaultType:'textfield',
							url : '',
							items : [ {
							       layout:'form',
							       fieldLabel:'请输入旧密码',
							       name:'oldPassword', 
							       id:'oldPassword', 
							       inputType: 'password',
							       allowBlank: false,
							       height:30,
							       blankText: '密码不能为空',
							       regex:/^[A-Za-z0-9]|[._]{10-20}$/,
                                   regexText:'密码由9-20位的字母、数字、下划线、点“.”'
							    },
							{
							       layout:'form',
							       fieldLabel:'请输入新密码',
							       name:'newPassword', 
							       id:'newPassword', 
							       inputType: 'password',
							       allowBlank: false,
							       height:25,
							       blankText: '密码不能为空',
                                   regex:/^[A-Za-z0-9]|[._]{10-20}$/,
                                   regexText:'密码由9-20位的字母、数字、下划线、点“.”'
							       
							    },{
							       layout:'form',
							        fieldLabel:'请确认新密码',  
							          xtype:'textfield',
                                     name: 'xinmima',
                                        id:'xinmima',
                                        height:25,
                                        inputType: 'password',
                                        allowBlank: false,
                                        invalidText:'两次密码不一致！',                                          
                                        validator: function(){//这里自定义函数验证密码是否一致
                                            if (
                                            fsf.form.findField("newPassword").getValue() == fsf.form.findField("xinmima").getValue()) 
                                                return true;
                                            else 
                                                return false;
                                            
                                        }
                                       // blankText: '密码不能为空',
                                         
                                         }
						     	]				
							});
            		var winM=new Ext.Window({
								layout:'fit',
								title:"修改密码",
								width:300,
								height:200,
								closable:true,
								resizable:true,
								//html:'<iframe src="/changepssword.jsp" width="700" height="400"/>',
								items:[fsf],
							
					  buttons: [{
						            text: '保   存',
						            handler: function(){
						                var oldPassword=Ext.get('oldPassword').dom.value;
						                 
						                var newPassword=Ext.get('newPassword').dom.value;
						                var xinmima=Ext.get('xinmima').dom.value;
						                  if(oldPassword=='')
						                  {
						                      
						                      Ext.MessageBox.alert('输入旧密码','密码不能为空' );
						                  }
						                  if(newPassword=='')
						                  {
						                      Ext.MessageBox.alert('输入密码','密码不能为空' );
						                  }
						                  if(xinmima=='')
						                  {
						                      Ext.MessageBox.alert('确定密码','密码不能为空' );
						                  } 
						                if(fsf.form.isValid()) {
						                       fsf.getForm().submit({
						                       waitMsg:'正在处理，请稍候...',
						                        url: '/ChangePsswordAction_passwordModify.action',
						                        method: 'POST', 
						                        success: function() {
						                            Ext.Msg.alert("成功", "操作成功！");
						                            winM.close();
						                      
						                        },
						                        failure: function() {		
						                            Ext.Msg.alert("失败", "您输入的旧密码有误！");
						                        }
						                    });
						                  
						                }
						            }
						        },{
						            text: '重置',
						            handler: function() {
						                fsf.form.reset();
						            }
						        }
						        ]
													
							
							
							});
							winM.show();
	            	},
	                scope:this
	            },/**{
	                text:'考勤',
	                iconCls:'bogus',
	            	handler:function(){
	            	  	var winM=new Ext.Window({
									layout:'fit',
									title:"签到签退",
									width:600,
									height:500,
									closable:true,
									resizable:true,
									html:'<iframe src="/KaoQin/QianDaoQianTui.jsp" width="600" height="500"/>'
						});
						winM.show();
	            	},
	                scope:this
	            },{
	                text:'个人信息',
	                iconCls:'bogus',
	            	handler:function(){
	            		var winM=new Ext.Window({
									layout:'fit',
									title:"个人信息",
									width:600,
									height:400,
									closable:true,
									html:'<iframe src="getPersonInfo.action" width="600" height="400"/>'								
								});
								winM.show();
	            	},
	                scope:this
	            },*/{
	                text:'保存布局',
	                iconCls:'bogus',
					handler: function(){  
		               getCurDesktop().saveDesk();
	                 },
	                scope:this
	            }]
	        };
	   
		return StartConfig;
	},
	getLocalModules:function(){
		localMoudles=[];
		localMoudles.push(jdsPreferences);
		localMoudles.push(new ExtJame());
		localMoudles.push(new ExpModule());

		return localMoudles;
	}
});

/*
 公文交换
 */
		/*function gwjhstartProcess(uuid){
		 	var wincfg={
				displayAction:"formdisplay.action",	
				formdisplayAction:"TzlcAction.action?sendorid="+uuid
			}
		  	var module1=new Ext.app.FormModule(wincfg);
		  	//64A2C0C0-8DCF-11E1-80C0-9A7DEF447738
		  	var processDefId = "130230C0-7E11-11E1-B0C0-EDB5E6A1E526";
			module1.newProcess(processDefId);
	 	}
	 	*/
	 	
function gwjhstartProcess(uuid){
      var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '收文登记',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='TzlcAction_transSWDJ.action?inceptid="+uuid+"'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '确定',
							cls : "x-btn-text-icon",
							handler : function(){
						  		  var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addZDj");
									fn.submit();
									alert("登记成功");
									addwin.close();
						      
						}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addZDj");
								fn.reset();
							}
						}
					]
			});
			addwin.show();
		 
}
	 	  //会签转流程发文
 function hqstartProcess(hqId){

Ext.Ajax.request({
		url : '/HqYewuAction_findFlagByhqId.action',
		params : {
			uuid : hqId
			
		},
		success : function(o) {
			
			if(o.responseText=='0'){
				var wincfg={
					displayAction:"formdisplay.action",
				    formdisplayAction:"hqFW.action?hqId="+hqId
				}
			    var module=new Ext.app.FormModule(wincfg);
				module.newProcess("CE477AA0-C615-11E2-BBEE-8E8BCDEB726F");
			}else{
				Ext.Msg.alert('信息', '流程已经启动 不能重复启动');
			}	
		},
		failure : function(resp, opts) {
			//alert(resp.responseText);
			Ext.Msg.alert('信息', '启动流程失败!');
		}
	});

 }
  //会签转流程收文
 function swhqstartProcess(hqId){

Ext.Ajax.request({
		url : '/HqYewuAction_swfindFlagByhqId.action',
		params : {
			uuid : hqId
			
		},
		success : function(o) {
			
			if(o.responseText=='0'){
				var wincfg={
					displayAction:"formdisplay.action",
				    formdisplayAction:"hqSW.action?hqId="+hqId
				}
			    var module=new Ext.app.FormModule(wincfg);
				module.newProcess("426CED80-D632-11E2-AD17-975267965EAC");
			}else{
				Ext.Msg.alert('信息', '流程已经启动 不能重复启动');
			}	
		},
		failure : function(resp, opts) {
			//alert(resp.responseText);
			Ext.Msg.alert('信息', '启动流程失败!');
		}
	});

 }
 
   //会签交办理转流程发文
 function hqjbl(hqId){

Ext.Ajax.request({
		url : '/HqYewuAction_findFlagByhqId1.action',
		params : {
			uuid : hqId
			
		},
		success : function(o) {
			
			if(o.responseText=='0'){
				var wincfg={
					displayAction:"formdisplay.action",
				    formdisplayAction:"hqJBL.action?hqId="+hqId
				}
			    var module=new Ext.app.FormModule(wincfg);
				module.newProcess("76F4A840-B468-11E2-A840-90D06A662CB7");
			}else{
				Ext.Msg.alert('信息', '流程已经启动 不能重复启动');
			}	
		},
		failure : function(resp, opts) {
			//alert(resp.responseText);
			Ext.Msg.alert('信息', '启动流程失败!');
		}
	});

 }
  //会签交办理转流程收文
 function swhqjbl(hqId){

Ext.Ajax.request({
		url : '/HqYewuAction_swfindFlagByhqId1.action',
		params : {
			uuid : hqId
			
		},
		success : function(o) {
			
			if(o.responseText=='0'){
				var wincfg={
					displayAction:"formdisplay.action",
				    formdisplayAction:"hqswJBL.action?hqId="+hqId
				}
			    var module=new Ext.app.FormModule(wincfg);
				module.newProcess("426CED80-D632-11E2-AD17-975267965EAC");
			}else{
				Ext.Msg.alert('信息', '流程已经启动 不能重复启动');
			}	
		},
		failure : function(resp, opts) {
			//alert(resp.responseText);
			Ext.Msg.alert('信息', '启动流程失败!');
		}
	});

 }
 
 
 
//查看	
 
 function chakan(uuid){
            var _width = 800;
			var _height = Ext.getBody().getHeight()-40;
			var addwin = new Ext.Window({
					title : '详细列表',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='bhckiframe1' name='bhckiframe1' width='" + (_width-30)  + "' height='" + (_height - 60) + "' src='/bhAction.action?wenzhongId="+uuid+"'></iframe>"
						
				

			});
			addwin.show();
	}
		//查看
function ck(uuid){

      var _width = 1350;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '会签',
					 maximizable:true,//窗口最大化
                              minimizable:true,//窗口最小化
                              layout: 'fit',   
                               plain:true,
 							   listeners:{
							   minimize:function(){
        					   if(this.minimizable){
           			 			this.hide();	
       						 }
   							 }
							}, 
					width : _width,
					minWidth : '100%',
					height : _height,
					
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='HqYewuAction_hqck.action?uuid="+uuid+"'></iframe>"
					   
			});
			addwin.show();
		 
}
	//查看
function yfck(uuid){

      var _width = 1350;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '会签',
					 maximizable:true,//窗口最大化
                              minimizable:true,//窗口最小化
                              layout: 'fit',   
                               plain:true,
 							   listeners:{
							   minimize:function(){
        					   if(this.minimizable){
           			 			this.hide();	
       						 }
   							 }
							}, 
					width : _width,
					minWidth : '100%',
					height : _height,
					
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='HqYewuAction_yfhqck.action?uuid="+uuid+"'></iframe>"
					   
			});
			addwin.show();
		 
}

	//收文已发查看
function swyfck(uuid){

      var _width = 1350;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '会签',
					 maximizable:true,//窗口最大化
                              minimizable:true,//窗口最小化
                              layout: 'fit',   
                               plain:true,
 							   listeners:{
							   minimize:function(){
        					   if(this.minimizable){
           			 			this.hide();	
       						 }
   							 }
							}, 
					width : _width,
					minWidth : '100%',
					height : _height,
					
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='HqYewuAction_swyfhqck.action?uuid="+uuid+"'></iframe>"
					   
			});
			addwin.show();
		 
}
	//收文查看
function swck(uuid){

      var _width = 1350;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '会签',
					 maximizable:true,//窗口最大化
                              minimizable:true,//窗口最小化
                              layout: 'fit',   
                               plain:true,
 							   listeners:{
							   minimize:function(){
        					   if(this.minimizable){
           			 			this.hide();	
       						 }
   							 }
							}, 
					width : _width,
					minWidth : '100%',
					height : _height,
					
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='HqYewuAction_swhqck.action?uuid="+uuid+"'></iframe>"
					   
			});
			addwin.show();
		 
}
 //状态
 
 function ckzt(deptid,hq){
            
            var _width = 600;
			var _height = Ext.getBody().getHeight()-40;
			var addwin = new Ext.Window({
					title : '状态列表',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='bhckiframe2' name='bhckiframe2' width='" + (_width-30)  + "' height='" + (_height - 50) + "' src='/ckztAction.action?deptid="+deptid+" & hq="+hq+"'></iframe>"
						
				

			});
			addwin.show();
	}
 
/**
 * 兼容旧版列表打开FORM对象
 * 
 * @param {Object}
 *            url
 * @param {Object}
 *            text
 * @param {Object}
 *            params
 */
function openWin(url,text,params){
    var wincfg={
		url:url,
		id:Ext.id(),
		text:text?text:url,
		params:params
	}
	module=new Ext.app.FormModule(wincfg);
	module.createWindow();
}


/**
 * 兼容旧版列表打开FORM对象
 * 
 * @param {Object}
 *            activityInstId
 */
 function proformWork(activityInstId){
 	
	var wincfg={
		activityInstId:activityInstId,
		displayAction:"formdisplay.action",
		activityInstHistoryId:'',
	    formdisplayAction:"BPMClientFormDisplayAction.action"
	}
	module=new Ext.app.FormModule(wincfg);
	module.createWindow();
	
 }
 
 

/**
 * 取得全局桌面对象 Ext.Desktop
 */
function getCurDesktop(){
      return JDSDesk.getDesktop();
}




/*******************************************************************************
 * 利用壳对文件进行下载
 * 
 * @param {Object}
 *            url
 * @param {Object}
 *            fileName
 */
function downLoadFile(url, fileName){
	function open(btn,nextUrl){
		if (btn=="yes"){
			  messageBoxprogress("","开始下载....","<b>正在下载...</b>",'120');
			  try{
		// alert("url===="+url+"=====fileName==="+fileName);
			  	 sendNSCommand("ognl","openFile('"+url.trim()+"','"+fileName.trim()+"')");
			  }catch(e){
			  JDS.alert(e);
			  	JDS.Msg.warningMsg('下载失败！')
			  }
		   
			
		 }else if (btn=="ok"){
		 	
			try{
			  	 sendNSCommand("ognl","saveAsFile('"+url+"','"+fileName+"')");
			  }catch(e){
			  	JDS.Msg.warningMsg('下载失败！')
			  }
		   messageBoxprogress("","开始下载....","<b>正在下载...</b>",'120');
		 }else {
		   
		 }
	}
	Ext.MessageBox.show(
						 {
					       title:"下载文件",
					       msg:"下载文件",
					       width:300,
					       buttons : {
					       yes : "打开" ,            
					       ok : "另存",
					       cancel : "取消"                                     
					       },
					       fn: open,                     
					       animEl:document.body,
					       icon: Ext.MessageBox.QUESTION
	 });
	
}




/**
 * 退出
 * 
 * @param {Object}
 *            btn
 * @param {Object}
 *            nextUrl
 */
function closewin(btn,nextUrl){
   if (btn=="yes"){ 
     var evalJS=function(o){
  	sendNSCommand("ognl","reLogin('重新登录')");
  }
  var closewin = JDS.ajax.Connection.LoadJsonFromUrl( context+'clear.action?',evalJS,'personId='+personid+'&event=reboot',this);
    messageBoxprogress("","","<b>正在关闭系统...</b>",'2','',closewin);
 }else if (btn=="ok"){
 //点击退出登录关闭浏览器
    var evalJS=function(o){
    window.close();
   //document.location.href="/login.jsp";
   }
 var closewin = JDS.ajax.Connection.LoadJsonFromUrl( context+'clear.action?',evalJS,'personId='+personid+'&event=shutdown',this);
    messageBoxprogress("","正在关闭系统...","<b>正在关闭系统...</b>",'2','',closewin);
 }else { 
 }
}

//关闭浏览器将退出系统.
 
/*window.onbeforeunload =function RunOnBeforeUnload() {
			 Ext.Ajax.request({
			 	url:'clear.action',
			 	method:'post',
			 	waitMsg:'正在处理，请稍候...',
			 	params:{
			 	'personId':personid,
			 	'event':'shutdown'
			 	}
			 })
 }
 */
  
/**
 * 系统启动界面
 * 
 * @param {Object}
 *            btn
 */  
function startwin(btn){
    messageBoxprogress("","系统正在启动...","<b>系统正在启动...</b>",'5','');
}
  
  
  
/**
 * 公务消息
 * 
 * @param {Object}
 *            activityInstId
 * @param {Object}
 *            processInstId
 * @param {Object}
 *            title
 * @param {Object}
 *            body
 */
var officeMsg={size:0};
function showOfficeMsgById(activityInstId,processInstId,title,body){

		if(isNaN(officeMsg.size))
		{
			officeMsg.size = 0;
		}
		officeMsg.size++;
		var desktop = getCurDesktop();
	// alert("processInstId===="+processInstId+"
	// activityInstId==="+activityInstId);
		if(Number(officeMsg.size)==1)
		{
			if(processInstId=='rise') // 针对原OA的提醒单独处理
			{
				body = "<font color=\"#0000FF\" style=\"cursor:hand\" > "+ body + "</font>";
				var html='<a onclick="openUrlWin(\''+activityInstId+'&type=todo\',\'待办文件\',\'\');setTimeout(\'hidWin()\',2000);">'+body+'</a>';
				officeMsg.body=+"</br></br>"+html;
			}
			else
			{
				body = "<font color=\"#0000FF\" style=\"cursor:hand\" > "+ body + "</font>";
				var html='<a onclick="openActivityInstWin(\''+activityInstId+'\');setTimeout(\'hidWin()\',2000);">'+body+'</a>';
				officeMsg.activityInstId=activityInstId;
				officeMsg.processInstId=processInstId;
				officeMsg.body=(officeMsg.body?officeMsg.body:"")+"</br></br>"+html;
			}
		}else
		{
			if(processInstId=='rise') // 针对原OA的提醒单独处理
			{
				body = "<font color=\"#0000FF\" style=\"cursor:hand\" > 您有（"+ Number(officeMsg.size) + "）件新的待办文件</font>";
				var html='<a onclick="openWinById(\'riseworkflowtodolist\');setTimeout(\'hidWin()\',2000);">'+body+'</a>';		
				officeMsg.body=html;
			
			}else
			{
				body = "<font color=\"#0000FF\" style=\"cursor:hand\" > 您有（"+ Number(officeMsg.size) + "）件新的待办文件</font>";
				var html='<a onclick="openWinById(\'daibantongzhi\');setTimeout(\'hidWin()\',2000);">'+body+'</a>';		
				officeMsg.body=html;
			}
		}
		
		if (officeMsg.win ){
			officeMsg.win.hide();
		}
		 officeMsg.win=desktop.showNotification({
				autoDestroy: false,
				hideDelay: 5000,
				iconCls: 'x-icon-waiting',
				html: officeMsg.body,
				title: title
			});
}

function hidWin()
{
	// setTimeout("officeMsg.win.close()",2000);
	officeMsg.win.hide();
	officeMsg.size = 0;
}
/**
 * 系统消息
 * 
 * @param {Object}
 *            title
 * @param {Object}
 *            body
 */
function showSystemMsgById(title,body){
	var desktop = JDSDesk.getDesktop();
    	var notifyWin = desktop.showNotification({
			autoDestroy: true,
			hideDelay: 5000,
			parameters : params,
			iconCls: 'x-icon-waiting',
			html: body,
			title: title
		});
		// notifyWin.on();
}

  

/**
 * 打开表单
 * 
 * @param {Object}
 *            url
 * @param {Object}
 *            winid
 * @param {Object}
 *            title
 * @param {Object}
 *            params
 */
// tanrui 有改动
function openFormWin(url,winid,title,params,filenum,updatedoc,upyibanli){
			var fn = Ext.get('iframeup').dom.contentWindow.document.getElementById("fn").value;
			
			var yiban = Ext.get("yibanli" + upyibanli).dom.value;
		    var handobjs = Ext.query("[name='docid']");
			if(Ext.get(handobjs[0])!=null){
				var uuid = Ext.get(handobjs[0]).dom.value;
			}
			var saveAs = false;
	        if(uuid != null && uuid.length>1){
		        saveAs = true; 
	        }
			if(url.lastIndexOf('?')>0){
				url += '&yiban='+yiban;
			}else{
				url += '?yiban='+yiban;
			}
			if(saveAs == false&&fn==0){
				   	var _width = 300;
					var _height = 400;
	                
					var documentmodule = {
				        title: "选择模版",
				        width: _width,
				        height: _height,
				        shim: true,
				        animCollapse: false,
				        border: false,
				        constrainHeader: true,
				        layout: 'fit',
				         
				        html:"<iframe id='dociframe' name='dociframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='documentmodel.action?currUserOrgName=" + currUserOrgName + "'></iframe>",
				       // html:"<script type='text/javascript'>window.open ('documentmodel.action?currUserOrgName=" + currUserOrgName + "')</script>", 
				        buttons: [{ text: "确定", handler: function() { 
				       	Ext.get('iframeup').dom.contentWindow.document.getElementById("fn").value = 1;
				       	var content=Ext.get('dociframe').dom.contentWindow.document.getElementsByName("modelname");
				       	for(var i = 0; i < content.length; i++){
					       	if(content[i].checked==true){
					       		var cv = content[i].value;
						       	params = params + "&docuuid=" + cv;
					       	}
				       	}
				       	params = params + "&filenum=" + filenum;
						if(cv == null){
							alert("请选择相应的模板!");
						}else{
							Ext.Ajax.request({
							url: url,
							method:"post",
							params:params,// parameters 改为params
							success: function(o) {// 将 onComplete改为success
								o=o.responseText;
								// JDS.alert(o);
								Ext.namespace("EVAL");
								eval(o);
						   
							var defaultCfg = {
							        title: "window",
							        width: 740,
							        height: 480,
							        shim: true,
							        animCollapse: false,
							        border: false,
							        constrainHeader: true,
							        maximized:true,
							        layout: 'fit'
							       
							    };
				    			var returnCfg = EVAL.getPanel();
								returnCfg = Ext.apply(defaultCfg,returnCfg);
								var dispwin1 = new Ext.Window(returnCfg);
								dispwin1.on('beforeclose',function(){
									var issave = Ext.get('framedoc').dom.contentWindow.document.getElementById("issave").value;
										if(issave=='n'){
											if(confirm('是否保存？')){
												Ext.get('framedoc').dom.contentWindow.handler();
											}
										}
								 
							        }
								);
								 dispwin1.show();
								 dispwin.onEsc();
							  	},
							scope: this
						});
						}
				       	
	       		}},       
				    				  			    	
	       			{ text: "取消", handler: function() {
						dispwin.onEsc();	       			
	       			}}]
	    };
	
/*
 * var returnCfg1 = EVAL.getPanel(); returnCfg1 =
 * Ext.apply(documentmodule,returnCfg1);
 */
		  var dispwin = new Ext.Window(documentmodule);
		  
		      dispwin.show();
				    
			}else{
				Ext.Ajax.request({
							url: url,
							method:"post",
							params:params,// parameters 改为params
							success: function(o) {// 将 onComplete改为success
								o=o.responseText;
								// JDS.alert(o);
								Ext.namespace("EVAL");
								eval(o);
								
							var defaultCfg = {
							        title: "window",
							        width: 740,
							        height: 480,
							        shim: true,
							        animCollapse: false,
							        border: false,
							        constrainHeader: true,
							         maximized:true,
							        layout: 'fit'
							    };
				    			var returnCfg = EVAL.getPanel();
								returnCfg = Ext.apply(defaultCfg,returnCfg);
								var dispwin1 = new Ext.Window(returnCfg);
								dispwin1.on('beforeclose',function(){
									var issave = Ext.get('framedoc').dom.contentWindow.document.getElementById("issave").value;
										if(issave=='n'){
											if(confirm('是否保存？')){
												Ext.get('framedoc').dom.contentWindow.handler();
											}
										}
								});
								 dispwin1.show();
							  	},
							scope: this
						});
			
				}

 }
function window.confirm(str){   
 execScript("n = (msgbox('"+ str +"',vbYesNo,'关闭提示')=vbYes)","vbscript");    
  return(n); 
}
  //zhongqun 选择红头模版
  function selectPic(url,winid,title,params,filenum,updatedoc,upyibanli){
  		var _width = 300;
		var _height = 400;
  			var documentmodule = {
				        title: "选择模版",
				        width: _width,
				        height: _height,
				        shim: true,
				        animCollapse: false,
				        border: false,
				        constrainHeader: true,
				        layout: 'fit',
				        html:"<iframe id='dociframe' name='dociframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='ttManagerAction_selectlist.action'></iframe>",
				       // html:"<script type='text/javascript'>window.open ('documentmodel.action?currUserOrgName=" + currUserOrgName + "')</script>", 
				        buttons: [{ text: "确定", handler: function() { 
				       	Ext.get('iframeup').dom.contentWindow.document.getElementById("fn").value = 1;
				       	var content=Ext.get('dociframe').dom.contentWindow.document.getElementsByName("modelname");
				       	for(var i = 0; i < content.length; i++){
					       	if(content[i].checked==true){
					       		var cv = content[i].value;
						       	params = params + "&taoHongId=" + cv;
					       	}
				       	}
				       	params = params + "&filenum=" + filenum;
						if(cv == null){
							alert("请选择相应的模板!");
						}else{
							openFormWin(url,winid,title,params,filenum,updatedoc,upyibanli);
							dispwin.close();
						}	
				       	
	       		}},       
				    				  			    	
	       			{ text: "取消", handler: function() {
						dispwin.onEsc();	       			
	       			}}]
	    };
/*
 * var returnCfg1 = EVAL.getPanel(); returnCfg1 =
 * Ext.apply(documentmodule,returnCfg1);
 */
		  var dispwin = new Ext.Window(documentmodule);
		  
		      dispwin.show();
  }
  

/**
 * 
 * @param {Object}
 *            url
 * @param {Object}
 *            winid
 * @param {Object}
 *            title
 * @param {Object}
 *            params
 */
function openSystemWin(url,winid,title,params){
		Ext.Ajax.Request( {
			url: url,
			method:"post",
			parameters : params,
			onComplete: function(o) {
				o=o.responseText;
				Ext.namespace("EVAL");
				eval(o);
			var defaultCfg = {
			        title: title,
			        width: 740,
			        height: 480,
			        shim: true,
			        animCollapse: false,
			        border: false,					
			        constrainHeader: true,
					  maximizable: true,// 是否可以最大化
			          listeners :{
			            resize:function (){
			              if(this.resizeHandler){
			                this.resizeHandler.apply(this,arguments);
			              }else{
			                winResizeHandler.apply(this,arguments);// 2009-02加
			              }
			            }
			          },
			        layout: 'fit'
			    };
    			var returnCfg = EVAL.getPanel();
				returnCfg = Ext.apply(defaultCfg,returnCfg)	
				currWin = new Ext.Window(returnCfg);
				 currWin.show();
			  	},
			scope: this
		});
}

/**
 * 根据MODULEID打开指定模块
 * 
 * @param {Object}
 *            moduleId
 */
function openWinById(moduleId){

	if (moduleId==("qo-preferences")){
		moduleId='JDSPreferences';
	}
	if(moduleId=='rise')
	{
		openTodoUrl();
	} 
	var module=JDSDesk.getModule(moduleId)
	module.createWindow();
}

function openWinById2(moduleId){

	if (moduleId==("qo-preferences")){
		moduleId='JDSPreferences';
	}
	if(moduleId=='rise')
	{
		openTodoUrl();
	} 
	var module=JDSDesk.getModuleById(moduleId)
	module.createWindow();
}

/**
 * 兼容旧版桌面
 * 
 * @param {Object}
 *            name
 */
function messageshow(name){

   if (!barMove){
	try{
			
		openWinById('qo-preferences');
			
		setTimeout("qoPreferences.viewCard('"+name+"')",1000);
	}catch(e){
	
	}
  }
}


/**
 * 保存桌面设置
 */
function saveDesk(){
		 JDSDesk.getDesktop().saveDesk();		
}

	
function Logout(){
 
}
  
 function proformWorkEnd(activityInstHistoryId){
 	var wincfg={
		activityInstHistoryId:activityInstHistoryId,
		activityInstId:'',
		displayAction:"demohisdisplay.action",
	    formdisplayAction:"BPMClientFormHisDisplayAction.action"
	}
	module=new Ext.app.FormModule(wincfg);
	module.createWindow();
 }
  

/**
 * 新版程序打开表单方法
 * 
 * @param {Object}
 *            activityInstId
 */
function openActivityInstWin(activityInstId){
   var wincfg={
		activityInstId:activityInstId,
		displayAction:"formdisplay.action",
		activityInstHistoryId:activityInstId,
	    formdisplayAction:"BPMClientFormDisplayAction.action"
	}
	module=new Ext.app.FormModule(wincfg);
	module.createWindow();
}
/**
*	打开已办理流程 2011-11-12 开始
*/
function openselfActivityInstWin(activityInstId){
   var wincfg={
		activityInstId:activityInstId,
		displayAction:"kzxdformdisplay.action",
		activityInstHistoryId:'',
	    formdisplayAction:"kzxdBPMClientFormDisplayAction.action"
	}
	module=new Ext.app.FormModule(wincfg);
	module.createWindow();
}
/**
*	打开已办理流程 2011-11-12 结束
*/

/**
 * 打开指定URL地址
 * 
 * @param {Object}
 *            url
 * @param {Object}
 *            text
 * @param {Object}
 *            params
 */
function openUrlWin(url,text,params){
    var wincfg={
		url:url,
		id:Ext.id(),
		text:text?text:url,
		alwaysOnTop: true,
		params:params
	}
	var module=new Ext.app.UrlModule(wincfg);
	if(url.indexOf("type=todo")>-1)	{
		openurl = url;
			todoSSOUrl();		
			return;
		}
	 module.createWindow();
}

  // 新建流程
 function newProcess(processDefId){
 	 var wincfg={
		displayAction:"formdisplay.action",
	    formdisplayAction:"BPMClientFormDisplayAction.action"
	}
  var module=new Ext.app.FormModule(wincfg);
  // JDS.alert("ffffff---------"+processDefId);
	module.newProcess(processDefId);
 }
 
 //zq add
 
 //转为流程
 function startProcess(sxxxid,processDefId){
 	 var wincfg={
		displayAction:"formdisplay.action",
	    formdisplayAction:"BPMClientFormDisplaySelf.action?sxxxid="+sxxxid
	}
  var module=new Ext.app.FormModule(wincfg);
	module.newProcess(processDefId);
 }
 
 //登记转流程
 function djstartProcess(swdjId,processDefId){

Ext.Ajax.request({
		url : '/SwdjAction_findFlagBySwdjId.action',
		params : {
			uuid : swdjId
		},
		success : function(o) {
			
			if(o.responseText=='0'){
				var wincfg={
					displayAction:"formdisplay.action",
				    formdisplayAction:"djBPMClientFormDisplay.action?swdjId="+swdjId
				}
			    var module=new Ext.app.FormModule(wincfg);
				module.newProcess(processDefId);
			}else{
				Ext.Msg.alert('信息', '流程已经启动 不能重复启动');
			}	
		},
		failure : function(resp, opts) {
			//alert(resp.responseText);
			Ext.Msg.alert('信息', '启动流程失败!');
		}
	});
 	
 	

 }
 
 
 //交办理转流程
 function jblstartProcess(jblId,processDefId){
 Ext.Ajax.request({
		url : '/JblAction_findFlagByjblId.action',
		params : {
			uuid : jblId
		},
		success : function(o) {
			
			if(o.responseText=='0'){
				var wincfg={
		        displayAction:"formdisplay.action",
	            formdisplayAction:"jblBPMClientFormDisplay.action?jblId=DC8A1770-F322-11E0-9770-8D8B737FB4CA &tid="+jblId
	    
					
		
				}
			 	var module=new Ext.app.FormModule(wincfg);
				module.newProcess(processDefId);
 			}else{
				Ext.Msg.alert('信息', '流程已经启动 不能重复启动');
			}	
		},
		failure : function(resp, opts) {
			//alert(resp.responseText);
			Ext.Msg.alert('信息', '启动流程失败!');
		}
			});
 	
 	

 }
 
//查看	
 
 function chakan(uuid){
            var _width = 800;
			var _height = Ext.getBody().getHeight()-40;
			var addwin = new Ext.Window({
					title : '详细列表',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='bhckiframe1' name='bhckiframe1' width='" + (_width-30)  + "' height='" + (_height - 50) + "' src='/bhAction.action?wenzhongId="+uuid+"'></iframe>"
						
				

			});
			addwin.show();
	}
 
 //行政审批转流程
  function xzspstartProcess(bsnum){
		 	var wincfg={
				displayAction:"formdisplay.action",
				formdisplayAction:"LctzAction.action?permissionid="+bsnum
			}
		  	var module1=new Ext.app.FormModule(wincfg);
		  	//64A2C0C0-8DCF-11E1-80C0-9A7DEF447738
		  	var processDefId = "64A1FD70-8DCF-11E1-BD70-AA6548D184EE";
			module1.newProcess(processDefId);
  }
//办理情况
 function openBL(activityInstId){
	 var wincfg={
				activityInstId:activityInstId,
				displayAction:"formdisplay.action",
				activityInstHistoryId:'',
			    formdisplayAction:"BPMClientFormDisplayAction.action"
			}
			module=new Ext.app.FormModule(wincfg);
			module.createWindow();
 }
 
//弹出流程办理情况列表
 function prodetail(processInstId,activityInstId) {
 	var routeLogGridCfg = {
 		selfCfg : {
 			title : "查看流程日志",
 			width : 600,
 			id : "routeLogGridCfg"
 		},
 		metaData : {
 			dataType : "json",
 			hasRowNum : true,
 			paging : {
 				root : "datas",
 				pageSize : 10
 			},

 			cols : [ {
 				text : "index",
 				name : 'index',
 				isDisplay : 'false'
 			}, {
 				text : "processInstId",
 				name : 'processInstId',
 				isDisplay : 'false'
 			}, {
 				text : "办理人 / 所属部门",
 				name : 'performPerson',
 				width : 150
 			}, {
 				text : "办理步骤",
 				name : 'activityInstName',
 				sortable : true,
 				width : 100
 			}, {
 				text : "当前状态",
 				name : 'state',
 				width : 100
 			}, {
 				text : "开始时间",
 				name : 'startName',
 				width : 100
 			}, {
 				text : "结束时间",
 				name : 'endTime',
 				width : 100
 			}, {
 				text : "查看",
 				name : 'action'
 			} ]
 		},
 		dataUrl : 'getHistory.action?processInstId=' + processInstId+'&activityInstId='+activityInstId
 	};

 	// routeLogGridCfg.show();
 	var routelogwin = new Ext.Window({
 		title : '查看流程日志',
 		collapsible : true,
 		width : 640,
 		height : 380,
 		id : 'routelogwin',
 		shim : false,
 		animCollapse : false,
 		constrainHeader : true,
 		maximizable : true,
 		items : createGridByData(routeLogGridCfg),
 		buttons : [ {
 			text : '确定',
 			handler : function() {
 				routelogwin.close();
 			}
 	 	} ]
 	});
 	routelogwin.show();
 	// createGridByData(routeLogGridCfg);

 }

 
function newParamsProcess(params){
	 var wincfg={
				displayAction:"formdisplay.action",
			    formdisplayAction:"BPMClientFormDisplayAction.action"
	};
	var module=new Ext.app.FormModule(wincfg);
	module.newProcessByHParams(params);
}



function document.onkeydown(){
if (event.keyCode==116){
	window.location.href=window.location.href   
}
event.returnvalue = false;
}

function  openTodoUrl(){
    
	    try{
	    function evalJs(returnHtml)
		    {
			   	Ext.apply(this,Ext.decode(returnHtml));
			// openUrlWin(this.ssourl,'公文待办','');
			 var wincfg={
							url:this.ssourl,
							id:Ext.id(),
							text:'待办文件',
							params:''
						}
				var module=new Ext.app.UrlModule(wincfg);
				 module.createWindow();
			}
		}catch( e)
			{
				alert("打开待办文件出错了，请刷新桌面或退出重新登录。");;
			}	
		
		 JDS.ajax.Connection.LoadJsonFromUrl( '/gettodourl.action?personId='+personid,evalJs,'',this);	
    }
var notifyWin；
function updateClient()
{
	if(updateVersion != null && updateVersion=='true')
	{
		var body = "<font color=\"#0000FF\" style=\"cursor:hand\" >检测到您的客户端软件有新版本，请您及时更新。 </font>";
		var html='<a onclick="selectUpdate();">'+body+'</a>';
		var desktop = JDSDesk.getDesktop();
    	notifyWin = desktop.showNotification({
			autoDestroy: false,
			hideDelay: 5000,
			parameters : '',
			iconCls: 'x-icon-waiting',
			html: html,
			title: '系统消息'
		});
	}
}

function selectUpdate()
{
			Ext.MessageBox.confirm("更新提示","客户端软件更新后，系统将自动退出。您确定要更新吗？",
				function(button,text){			
				if(button=="yes")
					{		
						 sendNSCommand("ognl","extUpdate()");
					}else
					{
					notifyWin.hide();
					}		
				});
}

// 点击待办打开连接
function  todoSSOUrl(){
    
	    try{
	    function evalJs(returnHtml)
	    {
		   	Ext.apply(this,Ext.decode(returnHtml));
		// this.url=returnHtml.replace(/[\r\n]/g, "");
			if(this.ssoState !='' && this.ssoState =='1') // 需要单点登录
			{
				 sendNSCommand("ognl","SSOLogin('"+this.ssourl+"','rise')");
			
			}else
			{
					openTodoUrl();
				
			}
				return;
	    }
		
		}catch( e)
			{
				openUrlWin(openurl,'公文待办','');
			}	
		
		 JDS.ajax.Connection.LoadJsonFromUrl( '/todosso.action?personId='+personid+'&ssourl='+encodeURIComponent(openurl),evalJs,'',this);	
    }
    
function openpdf(pdfurl,url){


		if (window.screen) {//判断浏览器是否支持window.screen判断浏览器是否支持screen     
      			var myw = screen.availWidth;   //定义一个myw，接受到当前全屏的宽     
      			var myh = screen.availHeight;
			}

	/*var aa=window.open('test11.html?pdfurl='+pdfurl+'& url='+url,'positionWinl','toolbar=no,width='+myw+',height='+myh+',left=0,top=0,menubar=no,location=no,scrollbars=no,resizable=yes');
  		var win = new Ext.Window(
			{
				id :'positionWinl',
				title :'查看详细信息',
				width :Ext.getBody().getWidth(),
				height:Ext.getBody().getHeight(),
				//autoHeight: true,
//				bodyStyle:'width:100%',   
				loadMask:true,
				closeAction :'close',
				closable :true,
				collapsible :false,
			    autoScroll :false,
				maximizable:true,
				minimizable:true ,
			        html:'<div><object id="pdf" classid="clsid:3542AF22-FBBF-4799-9EE7-DB428ED59DE8" width="100%" height="100%" title="测试"></object></div>'

				

			});

	
				
  		
       win.show();
         var    sret = Ext.get("pdf").dom.OpenRemotePdf(url+"/pdfview.action?uuid="+pdfurl);
				        if (sret != 1) {
				        alert("打开文件错误");
				       }

*/

    var fea='status:0;dialogWidth:'+myw+'px;dialogHeight:'+myh+'px;dialogTop:0px;dialogLeft:0px;resizable:yes'
	window.showModelessDialog('test11.html?pdfurl='+pdfurl+'& url='+url,window,fea);

  }
  
  function djopenpdf(pdfurl,url){
		
  		var win = new Ext.Window(
			{
				id :'positionWinl',
				title :'查看详细信息',
				width :930,
				height:Ext.getBody().getHeight()-30,
				//autoHeight: true,
//				bodyStyle:'width:100%',   
				loadMask:true,
				closeAction :'close',
				closable :true,
				collapsible :false,
			    autoScroll :false,
				maximizable:true,
				minimizable:true ,
			        html:'<div><object id="pdf" classid="clsid:3542AF22-FBBF-4799-9EE7-DB428ED59DE8" width="100%" height="100%" title="测试"></object></div>'

				

			});

	
				
  		
       win.show();
         var    sret = Ext.get("pdf").dom.OpenRemotePdf(url+"/mdownloadfile.action?uuid="+pdfurl);
				        if (sret != 1) {
				        alert("打开文件错误");
				       }
  }
  function viewDocPdfH(docid,url){
  		var win = new Ext.Window(
			{
				id :'positionWind',
				title :'查看详细信息',
				width :930,
				//autoHeight: true,
//				bodyStyle:'width:100%', 
				height:Ext.getBody().getHeight()-30,  
				loadMask:true,
				closeAction :'close',
				closable :true,
				collapsible :false,
			    autoScroll :false,
				maximizable:true,
				minimizable:true ,
			    html:'<div><object id="pdf" classid="clsid:3542AF22-FBBF-4799-9EE7-DB428ED59DE8" width="100%" height="100%" title="测试"></object></div>'
			});
       win.show();
         var    sret = Ext.get("pdf").dom.OpenRemotePdf(url+"/viewpdfthh.action?docid="+docid);
				        if (sret != 1) {
				        alert("打开文件错误");
				       }
  }
 function viewDocPdftH(docid,url){
  		var pdf_tht_win = new Ext.Window(
			{
				id :'positsionWin',
				title :'查看详细信息',
				width :930,
				height:Ext.getBody().getHeight()-30,
				//autoHeight: true,
				loadMask:true,
				closeAction :'close',
				closable :true,
				collapsible :false,
			    autoScroll :false,
				maximizable:true,
				minimizable:true ,
			    html:'<div id="pdf_div"><object id="pdf" classid="clsid:3542AF22-FBBF-4799-9EE7-DB428ED59DE8" width="100%" height="100%" title="测试"></object></div>',
				buttons:[{ text: "确定", handler: function() { 
						//var picUrl = showpicdoc_pdf();	
						//alert(picUrl);
						//document.getElementById("pdf_div").style.display ="block"
						Ext.get("pdf").dom.SavePDF(url+"/saveDocPdf.action?docid="+docid);
						pdf_tht_win.close();
						
					      }
					},
					{ text: "选择红头", handler: function() { 
					    pdf_tht_win.hide();
						showpicdoc_pdf(url);	
						//Ext.get("pdf").dom.AddTitle("http://localhost:82/taotoumodel/taotou1.bmp");
						//alert(picUrl);
						//document.getElementById("pdf_div").style.display ="block"
						//Ext.get("pdf").dom.SavePDF("http://localhost:82/saveDocPdf.action");
						//win.close();
						
					      }
					}]
				

			});

	
				
  		
         pdf_tht_win.show();
         var    sret = Ext.get("pdf").dom.OpenRemotePdf(url+"/doctopdf_writeResponse.action?docid="+docid);
				        if (sret != 1) {
				        alert("打开文件错误");
				       }
				       
		function showpicdoc_pdf(url){
	
		var win = new Ext.Window({
			title:'选择红头',
			width:500,
			height:Ext.getBody().getHeight()-50,
			html:"<iframe id='myiframe' name='myiframe' src='ttManagerAction_selectlist.action'></ifrmae>",
			buttons:[{ text: "确定", handler: function() { 
						var imgeRadio = Ext.get('myiframe').dom.contentWindow.document.getElementsByName("modelname");
						if(imgeRadio.length>0){
							for(var i=0;i<imgeRadio.length;i++){
								if(imgeRadio[i].checked){
									temp = imgeRadio[i].value;
									
									Ext.get("pdf").dom.AddTitle(url+"/taotoumodel/"+temp);
									
								}
							}
						}
						
						
						
						win.close();
						pdf_tht_win.show();
						//return temp;
					}
			}]
			
		});
		win.show();
		
	}
 }
 /*
 *文件下载
 */
 function download(url){
 	Ext.Ajax.request({
 		url:'/rdownloadfile_downFile.action?uuid='+url,
 		method:'post',
 		success:function(o){
 			
 			if(o.responseText=='y'){
 					
 			var elemIF = document.createElement("iframe");   
			  elemIF.src = '/rdownloadfile_downFileAgain.action?uuid='+url;   
			  elemIF.style.display = "none";   
			  document.body.appendChild(elemIF); 
 			}else{
 				alert(o.responseText);
 			}
 			 // alert(o.responseText);
 			  //window.location.target="_ablank" ; window.localtion.href =url  
 		}
 	});
 	}
 /*
 *查看
 */
 function openpdfgd(pdfurl,url){
  		var win = new Ext.Window(
			{
				id :'positionWin',
				title :'查看详细信息',
				width :930,
				height:Ext.getBody().getHeight()-30,
				//autoHeight: true,
//				bodyStyle:'width:100%',   
				loadMask:true,
				closeAction :'close',
				closable :true,
				collapsible :false,
			    autoScroll :false,
				maximizable:true,
				minimizable:true ,

			    html:'<div> <iframe id="my_Form" name="my_Form" style="display: none;"></iframe><input type="button" value="下载" onclick=download(\"'+pdfurl+'\") /> <input type="button" value="申请下载" onclick=ajaxrequest(\"'+pdfurl+'\") /></div><div><object id="pdf" classid="clsid:3542AF22-FBBF-4799-9EE7-DB428ED59DE8" width="100%" height="100%" title="测试"></object>/rdownloadfile.action?uuid='+pdfurl+'\</div>'
			

				

			});

	
				
  		
       win.show();
         var    sret = Ext.get("pdf").dom.OpenRemotePdf(url+"/pdfgdview.action?uuid="+pdfurl);
				        if (sret != 1) {
				        alert("打开文件错误");
				       }
  }	
  
  function ajaxrequest(uid){
 	var url="/pepodom_save.action";
 	Ext.Ajax.request({
 		url:url,
 		params:{rollid:uid,filetype:'3'},
 		method:'post',
 		success:function(o){
 			var jsonobject = Ext.util.JSON.decode(o.responseText); 
 			alert(jsonobject.msg);
 		}
 	});
 }
 
 function createWin(url,text){
 	var _width = Ext.getBody().getWidth()-100;
 	var _height = Ext.getBody().getHeight()-40;
 	var addwin =  new Ext.Window({
 		title:text,
 		width:_width,
 		height: _height,
 		maximizable:true,
 		html:"<iframe id='djiframe' name='djiframe' width='100%' height='100%' src='"+url+"'></iframe>"
 	})
 	addwin.show();
 }

 
 function  fromFWtoTZGG(wname,activityInstId,personid,formId,grid){
	  	     var _width = 900;
		     var _height = Ext.getBody().getHeight()-40;
		      var addwin = new Ext.Window({
					title : '通知公告',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					draggable:true,
					constrainHeader:true,
				    autoScroll :true,  
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='TzlcAction_JumpToTZGG.action?activityInstId="+activityInstId+"&wname="+wname+"&personId="+personid+"&formId="+formId+"'></iframe>"
					
			});
			    addwin.show();
}
 function fromFWtoGwjh(wname,activityInstId,personid,formId,grid){
	  	     var _width = 900;
		     var _height = Ext.getBody().getHeight()-40;
		      var addwin = new Ext.Window({
					title : '公文交换',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					draggable:true,
					constrainHeader:true,
				    autoScroll :true,  
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='TzlcAction_JumpToGwjh.action?activityInstId="+activityInstId+"&wname="+wname+"&personId="+personid+"&formId="+formId+"'></iframe>"
					
			});
			    addwin.show();
}

 function fromSWtoGwjh(wname,activityInstId,personid,formId,grid){
	  	     var _width = 900;
		     var _height = Ext.getBody().getHeight()-40;
		      var addwin = new Ext.Window({
					title : '公文交换',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					draggable:true,
					constrainHeader:true,
				    autoScroll :true,  
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='TzlcAction_JumpSwToGwjh.action?activityInstId="+activityInstId+"&wname="+wname+"&personId="+personid+"&formId="+formId+"'></iframe>"
					
			});
			    addwin.show();
}
 function showelecData(wname,activityInstId,personid,formId,grid){
			var _width = 900;
			var _height = Ext.getBody().getHeight()-40;
			var addwin = new Ext.Window({
					title : '归档信息',
					layout : 'fit',
					width : _width,
					height : _height,
					maximizable:true,
					minimizable:true,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-20)  + "' height='" + (_height - 50) + "' src='/data_returnaddData.action?wname="+wname+"&activityInstId="+activityInstId+"&personId="+personid+"&formId="+formId+"'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '归  档',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addData");
								if(Ext.get('djiframe').dom.contentWindow.saveDataInfo()){
								/*fn.submit({
									success:function(){
										Ext.Msg.alert('信息','信息添加成功!');
									},
									failure:function(){
										Ext.Msg.alert('信息','操作失败!');
									}
								});*/
								if(grid!=null){
									grid.getStore().reload();
								}
								
								addwin.close();
								}
							}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addData");
								fn.reset();
							}
						}
					]
			});
			addwin.show();
}
//会签编号开始
	function grid(node) {
	 var obj = Ext.getCmp("grid11");
	 var store = obj.getStore();
	 var newRecord=new Ext.data.Record({name:node.name, id:node.id});
	 store.insert(store.getCount(),newRecord);

	
	}
	
	function removegrid(node) {
	 var obj = Ext.getCmp("grid11");
	 var store = obj.getStore();
	 var newRecord=new Ext.data.Record({name:node.name, id:node.id});
	  store.remove(store.getAt(0)); 
	}
	//会签编号结束
function opendocth(docid,baseurl){
		var win = new Ext.Window(
		{
			id :'positionWin',
			title :'查看详细信息',
			width :930,
			height:Ext.getBody().getHeight()-40,
			y:0,
			loadMask:true,
			closeAction :'close',
			closable :true,
			collapsible :false,
		    autoScroll :false,
			maximizable:true,
			minimizable:true ,

		    html:'<iframe id="my_Form" name="my_Form" style="width:100%;height:100%" src="/todocttviewAction.action?docid='+docid+'"></iframe>'
		
		});
       win.show();
}


function tzWjzl(wname,activityInstHistoryId,personid,formId,grid){
	var _width = 900;
	var _height = Ext.getBody().getHeight()-40;
	var addwin = new Ext.Window({
			title : '文件资料登记',
			layout : 'fit',
			width : _width,
			height : _height,
			maximizable:true,
			minimizable:true,
			html:"<iframe id='addiframe' name='addiframe' width='" + (_width-20)  + "' height='" + (_height - 50) + "' src='/lcTzWjzlkAction_tzWjzladd.action?wname="+wname+"&activityInstHistoryId=" + activityInstHistoryId +"&personId="+personid+"&formId="+formId+"'></iframe>",
			tbar:[{
				id : 'save',
				icon : "/usm/img/save.gif",
				text : '保 存',
				cls : "x-btn-text-icon",
				handler : function(){
					var fn = Ext.get('addiframe').dom.contentWindow.document.getElementById("add");
								
					//判断标题不能为空
					var biaoti = Ext.get('addiframe').dom.contentWindow.document.getElementById("wjzl.meterialTitle");
					if(biaoti.value == ""){
						//alert(biaoti.value);
						alert("标题不允许为空，请填写标题");
						return;
					}
					
					//判断目录不能为空
					var cataid = Ext.get('addiframe').dom.contentWindow.document.getElementById("cataid");
					if(cataid.value == ""){
						//alert(biaoti.value);
						alert("请选择目录，目录不允许为空");
						return;
					}
					
					//附件不能为空
					var temp = document.getElementById('addiframe');
					//var formid = temp.contentWindow.getActiveFormId();
					
					//********************************************************************
					var count = temp.contentWindow.getCount();
					//var temp1 = Ext.get('addiframe');
					//var obj = temp1.down(formid);
		          	if(count==0){
			           	alert("附件不能为空");
			            return;
		           	}
		           	
					fn.submit();
					alert('保存成功');
					
					addwin.close();
					
				}
			},{
				id : 'reset',
				icon : "/usm/img/depart.gif",
				text : '重  置',
				cls : "x-btn-text-icon",
				handler : function(){
					var fn = Ext.get('addiframe').dom.contentWindow.document.getElementById("add");
					fn.reset();
				}
			}]
	});
	addwin.show();
}


function takebackMain(instId, historyId){
	JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context + 'expression.jsp', 
					    		   evalJs, $H({
					    		   	expression:'$currActivityInstHistory.activityInst.takeBack()',
					    		   	activityInstId: value, 
					    		   	activityInstHistoryId: historyId}));
}
 