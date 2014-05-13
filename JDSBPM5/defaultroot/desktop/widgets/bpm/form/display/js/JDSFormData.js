/**
 * 后台数据获取类
 * @param {Object} cfg
 */
JDS.bpm.Form.FormData = function(cfg){
	Ext.apply(this, cfg);
	
	//往文件资料库管理里面跳转
	this.tzWjzlAction = function(){
		var tzWjzlAction=new Ext.Action({
			text: '跳转文件资料库',
			tooltip: '保存当前表单内容',
			handler: function(){
				var wname = getWordname();
				Ext.Ajax.request({
					url:'/lcTzWjzlkAction_sfyxTz.action',
					method:'post',
					async : false,
					params:{activityInstHistoryId:this.module.activityInstHistoryId},
					success:function(o){
						var textstr = o.responseText;
						if(textstr=="true"){
							tzWjzl(wname,this.module.activityInstHistoryId,personid,this.module.getCurrForm().formId);
						}else{
							Ext.Msg.alert('信息','此流程已跳转到文件资料库，不可以在跳转！');
						}
					}
					
				});
			},
			scope: this,
			iconCls: 'printButton'
		   });
		return tzWjzlAction;
	
	}
	
	//增加开始
	this.shudaoAction = function(actid){
		var shudaoAction=new Ext.Action({
			text: '疏导',
			tooltip: '流程疏导',
			handler: function(){
				openUrlWin("/desktop/liucheng/shudao/shudao.jsp?ActivityInstId="+actid,"流程疏导");
			},
			scope: this,
			iconCls: 'printButton'
		   });
		return shudaoAction;
	}
	//增加结束
	this.printwordAction = function(){
		var printwordAction=new Ext.Action({
			text: '生成处理笺',
			tooltip: '打印表单内容到word',
			handler: function(){
				printwordjs();
			},
			scope: this,
			iconCls: 'printButton'
		   });
		return printwordAction;
	}
	
	/*   //从发文到公文交换跳转
	this.fromFWtoGwjhAction = function(){
		var fromFWtoGwjhAction = new Ext.Action({
			text:'公文交换',
			tooltip:'公文交换',
			handler:function(){
			var wname = getWordname();
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
				    minimizable:true,
				    maximizable:true,
				    autoScroll :true,  
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='TzlcAction_JumpToGwjh.action?activityInstId="+ this.module.activityInstId+"&wname="+wname+"&personId="+personid+"&formId="+this.module.getCurrForm().formId+"'></iframe>"
					
			});
			    addwin.show();
  
			},
			scope: this
		});
		return fromFWtoGwjhAction;
	}*/
	
	
	//从发文到公文交换跳转
	this.fromFWtoGwjhAction = function(){
		var fromFWtoGwjhAction=new Ext.Action({
			text: '公文交换',
			tooltip: '发文跳转到公文交换',
			handler: function(){
				var wname = getWordname();
				Ext.Ajax.request({
					url:'TzlcAction_findById.action',
					method:'post',
					async : false,
					params:{activityInstId:this.module.activityInstId},
					success:function(o){
						var textstr = o.responseText;
						if(textstr==0){
							fromFWtoGwjh(wname,this.module.activityInstId,personid,this.module.getCurrForm().formId);
						}else{
							Ext.Msg.alert('信息','公文已交换！');
						}
					}
					
				});
			},
			scope: this,
			iconCls: 'printButton'
		   });
		return fromFWtoGwjhAction;
	}
	
	
	//从收文到公文交换跳转
	this.fromSWtoGwjhAction = function(){
		var fromSWtoGwjhAction=new Ext.Action({
			text: '公文交换',
			tooltip: '收文跳转到公文交换',
			handler: function(){
				var wname = getWordname();
				Ext.Ajax.request({
					url:'TzlcAction_findSwById.action',
					method:'post',
					async : false,
					params:{swactivityInstId:this.module.activityInstId},
					success:function(o){
						var textstr = o.responseText;
						if(textstr==0){
							fromSWtoGwjh(wname,this.module.activityInstId,personid,this.module.getCurrForm().formId);
						}else{
							Ext.Msg.alert('信息','公文已交换！');
						}
					}
					
				});
			},
			scope: this,
			iconCls: 'printButton'
		   });
		return fromSWtoGwjhAction;
	}
	
	
	//从发文跳转到通知公告
	this.fromFWtoTZGGAction = function(){
		var fromFWtoTZGGAction=new Ext.Action({
			text: '转通知公告',
			tooltip: '发文跳转到通知公告',
			handler: function(){
				var wname = getWordname();
				Ext.Ajax.request({
					url:'TzlcAction_findById1.action',
					method:'post',
					async : false,
					params:{activityInstId:this.module.activityInstId},
					success:function(o){
						var textstr = o.responseText;
						if(textstr==0){
							fromFWtoTZGG(wname,this.module.activityInstId,personid,this.module.getCurrForm().formId);
						}else{
							Ext.Msg.alert('信息','已通知！');
						}
					}
					
				});
			},
			scope: this,
			iconCls: 'printButton'
		   });
		return fromFWtoTZGGAction;
	}
	
	//跳转发文
	this.tzfawen = function(){
		var tzfawen=new Ext.Action({
			text: '跳转发文',
			tooltip: '跳转到发文流程',
			handler: function(){
				 this.module.skipProcess('4AED1BE0-F319-11E0-AADD-D1CF6FBB2348',this.module.activityInstHistoryId);
			},
			scope: this,
			iconCls: 'readend'
		   });
		return tzfawen;
	}
	  this.ReadAction =function(){
		var sendReadAction= new Ext.Action({
        text: ' 转入收文',
        tooltip: '抄送当前传阅文件',
        handler: function(){    
        	this.module.skipProcess1('DC8A1770-F322-11E0-9770-8D8B737FB4CA',this.module.activityInstHistoryId);
        },
		scope: this,
        iconCls: 'readend'
       });
	   return sendReadAction;
		
	}
	//电子归档
	this.electronicAction = function(){
		var electronicAction=new Ext.Action({
			//text: '电子归档',
			tooltip: '保存当前表单内容',
			handler: function(){
				var wname = getWordname();

				Ext.Ajax.request({
					url:'/data_findByActivityInstId.action',
					method:'post',
					async : false,
					params:{activityInstId:this.module.activityInstId},
					success:function(o){
						var textstr = o.responseText;
						if(textstr==0){
							showelecData(wname,this.module.activityInstId,personid,this.module.getCurrForm().formId);
						}else{
							Ext.Msg.alert('信息','此流程已归档！');
						}
					}
					
				});
			},
			scope: this,
			iconCls: 'printButton'
		   });
		return electronicAction;
	}
	//终止流程
	this.abortProAction = function(){
	var me = this;
	
	
		var abortProAction = new Ext.Action({
			text:'终止流程',
			tooltip:'终止流程',
			handler:function(){
			   Ext.Ajax.request({
		    url : '/HqYewuAction_findsl.action',
	        params : {
                 activityinstuuid : me.module.activityInstId
			},
			success : function(resp, opts) {
			 var scs = resp.responseText;
		      if(scs=="0"){
				Ext.MessageBox.confirm('Confirm','流程终止，有关文件将被删除。',function(bool){

					if(bool=='yes'){
						Ext.Ajax.request({
							url:'/abortPro/abortProcess.action',
							params:{activityInstId:this.module.activityInstId},
							method:'post',
							success:function(){
								Ext.MessageBox.alert('提示','终止成功');
								Ext.getCmp(module.winId).close();
								refGridById(module.gridWinId);
							}
						});
						//Ext.getCmp(module.winId).close();
					}
				})
			}else{
			   
			   Ext.Msg.alert('信息', '正在会签，不能终止!');
			
			}
				
				},
			failure : function(resp, opts) {
			alert(resp.responseText);
				Ext.Msg.alert('信息', '终止失败!');
			}
		});
				
				
				
			},
			scope: this
		});
		return abortProAction;
	}

	//补发
	this.resendAction =function(name,routeDefId){
		
		var resendAction=new Ext.Action({
		text: name,
		tooltip: name,
		handler: function(){
			JDS.bpm.Form.Action.resender(this.module);
		},
		scope: this,
		iconCls: 'saveButton'
	   });
	   return resendAction;
	} 
	
	
	//重发1
	this.reSend =function(){
		
		var resendAction=new Ext.Action({
		text: "补发",
		tooltip: "补发",
		handler: function(){
			JDS.bpm.Form.Action.reSend(this.module);
		},
		scope: this,
		iconCls: 'send'
	   });
	   return resendAction;
	} 
	
	
	this.saveAction =function(){
		var saveAction=new Ext.Action({
		text: '保存',
		tooltip: '保存当前表单内容',
		handler: function(){
			JDS.bpm.Form.Action.save(this.module);
		},
		scope: this,
		iconCls: 'saveButton'
	   });
	   return saveAction;
	} 
	
	this.sendAction = function(){
	  var sendAction =new Ext.Action({
		text: '发 送',
		tooltip: '发送当前文件',
		handler: function(){
			JDS.bpm.Form.Action.BPM_ShowSelectPersonPanel(this.module);
		},
		scope: this,
		iconCls: 'send'
	  });
		return sendAction;
	}
	
	this.signAction = function(){
		var signAction=new Ext.Action({
		text: '签  收',
		tooltip: '此文件是我的办理内容，签收改文件',
		handler: function(){
			JDS.bpm.Form.Action.sign(this.module);
		},
		scope: this,
		iconCls: 'signButton'
	   });
	   return signAction;
	}
	
	this.endAction = function(){
		var endAction = new Ext.Action({
			text: '送办结',
			tooltip: '该工作已办理完毕，结束该流程',
			handler: function(){
				JDS.bpm.Form.Action.BPM_routeToEnd(this.module);
			},
			scope: this,
			iconCls: 'endButton'
		});
		return endAction;
	}
	
	
	this.tackBackAction = function(){
		var tackBackAction= new Ext.Action({
		text: '收 回',
		tooltip: '收回重新办理',
		handler: function(){
			JDS.bpm.Form.Action.tackBack(this.module);
		},
		scope: this,
		iconCls: 'routeBackButton'
	    });
		return tackBackAction;
	}
    //会签
    
	   this.huiqianAction = function(){
		var me = this;
		//alert(me.module.activityInstId);
		var act = new Ext.Action({
			text : '送会签',
			tooltop : '送其它部门会签',
			handler : function(){
			
			    	Ext.Ajax.request({
		    url : '/HqYewuAction_finddeptid.action',
	        params : {
                 activityinstuuid : me.module.activityInstId
			},
			success : function(resp, opts) {
			//	alert(resp.responseText);
				var ids = '';
				var disabledIds=resp.responseText;
				var _width = 600;
				var _height = Ext.getBody().getHeight()-80;
				var me1 = me;
			    var data=[];
			    var store = new Ext.data.Store({
					 
					            proxy : new Ext.data.MemoryProxy(data),
				                reader : new Ext.data.ArrayReader({}, [
				                {name : 'name'},
				                {name : 'id'}
				                ])
		
						    });
				     store.load();
				     var sms = '提示：';
				var wintree = new Ext.Window({
					layout : 'fit',
					width : _width,
					id : 'ztwindows',
					minWidth : 350,
					height : _height,
					minHeight : 250,
					 title: '送会签',
				    layout:'border',
				    items: [{
				        region:'west',
				        margins: '5 0 0 5',
				        cmargins: '5 5 0 5',
				        width: 200,
				        minSize: 80,
				        maxSize: 180,
				         html:"<iframe id='orgtreeiframe' name='orgtreeiframe' width='" + (_width-10)  + "' height='" +
	                 (_height - 80) + "' src='deptAction_hqaddtree.action?checked="+ids+"&disabled="+disabledIds+"'></iframe>"
	                
				    },{  
                     region:'center',
				     margins: '5 5 0 0',
                     width: 225,
	                 minSize: 200,
					 maxSize: 189,
						layout:'fit',
						items:[
						new Ext.grid.GridPanel({
						id : 'grid11',
						layout : 'fit',
						bodyStyle : 'width:100%',
					
						height : 80,
						loadMask : true,
					  
					   
						store :store,
						sm : new Ext.grid.CheckboxSelectionModel({
                             listeners:{
                                'rowselect':function(sm,rowIndex,record){
						         var arry =  sm.getSelections();
						         for(var i=0;i<arry.length;i++){
						       	        var d = arry[i];
						       	        if(d.id != record.id){
						       	        	var row = sm.grid.getStore().indexOf(d);
						       	        	sm.deselectRow(row);
						       	        }
								
							}
						     
						      }
						      },
								dataIndex : 'index'
							}),
						cm : new Ext.grid.ColumnModel([this.sm,
						{header:'局名',dataIndex:'name'}
						])
						})]
					
				    },{
					        region: 'south',
                    		 width: 225,
                    			 height:80,
									items:[
									{   	id:'SMSMsg',
											name:'SMSMsg',
										    xtype: 'checkbox',   
										    boxLabel: '短信提醒',   
										    width: 80 ,  
										    listeners: {  
										        'check': function (it) {  
										            if (it.checked) { 
										            } 
										        }  
										    }  
										},{
													 id:'SMScontent',
													 name:'SMScontent',
													 xtype:"textarea", 
													 fieldLabel:"短信内容",
													 width:400,
													 height:40,
													 value:sms
													 
												  }
			      						]					       
				    }],
				     buttons : [
						{
							text :'确定',
							handler : function() {
							    
								var retValue = document.getElementById('orgtreeiframe').contentWindow.getCheckedNames();
								var grid = Ext.getCmp("grid11");
						
								var row = grid.getSelectionModel().getSelections();
								var bhdeptid;
								if(row.length> 0)
								   bhdeptid = row[0].data.id;
								//   alert(bhdeptid);
								wintree.close();
								//alert(wintree.findById("SMScontent").getValue());
								//alert(wintree.findById("SMSMsg").getValue());
								if(wintree.findById("SMSMsg").getValue()){
								var smscontent = wintree.findById("SMScontent").getValue();
						
							Ext.Ajax.request({
								url : '/HqYewuAction_save.action',
								params : {
										deptIds : retValue.ids,
										deptname : retValue.names,
										smscontent:smscontent,
										flag :1
									
								},
								method : 'post',
								sunccess : function() {

								}
							});
						
						}
								Ext.getCmp(me.module.winId).close();
								    Ext.Ajax.request({
									url : '/HqYewuAction_save.action',
	                                params : {
										activityinstuuid : me.module.activityInstId,
										deptIds : retValue.ids,
										deptname : retValue.names,
										bhdeptid : bhdeptid,
										flag :0
									},
									success : function(o) {
										
											Ext.Msg.alert('信息','会签发送成功!');
								
									},
									failure : function(resp, opts) {
										 
										  Ext.Msg.alert('信息','会签发送失败!'+ resp.responseText);
									}
								    });
								failure : function(resp, opts) {
			                 
			                    Ext.Msg.alert('信息','会签失败!'); 
		}
						}
						},{
							text :'取消',
							handler : function() {
								wintree.close();
							}
							}
						]
				});
		

				wintree.show();
			},
			failure : function(resp, opts) {
			alert(resp.responseText);
				Ext.Msg.alert('信息', '会签失败!');
			}
		});
			
			
			
			},
			scope : this,
			iconCls : 'printButton'
		});
		return act;
	}
	 //收文会签
	   this.swhuiqianAction = function(){
		var me = this;
		//alert(me.module.activityInstId);
		var act = new Ext.Action({
			text : '送会签',
			tooltop : '送其它部门会签',
			handler : function(){
			    	Ext.Ajax.request({
		    url : '/HqYewuAction_finddeptid.action',
	        params : {
                 activityinstuuid : me.module.activityInstId
			},
			success : function(resp, opts) {
				var ids = '';
				var disabledIds=resp.responseText;
				var _width = 300;
				var _height = Ext.getBody().getHeight()-40;
				var me1 = me;
				var wintree = new Ext.Window({
					id :'huiqianDanweiWin',
					title :'选择会签单位',
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
	                 (_height - 50) + "' src='deptAction_hqaddtree.action?checked="+ids+"&disabled="+disabledIds+"'></iframe>",
	                 buttons : [
						{
							text :'确定',
							handler : function() {
								var retValue = document.getElementById('orgtreeiframe').contentWindow.getCheckedNames();
								wintree.close();
								Ext.getCmp(me.module.winId).close();
								    Ext.Ajax.request({
									url : '/HqYewuAction_swsave.action',
	                                params : {
										activityinstuuid : me.module.activityInstId,
										deptIds : retValue.ids,
										deptname : retValue.names
									},
									success : function(o) {
										
											Ext.Msg.alert('信息','会签发送成功!');
								
									},
									failure : function(resp, opts) {
										 
										  Ext.Msg.alert('信息','会签发送失败!'+ resp.responseText);
									}
								    });
								//}else{
							//	Ext.Msg.alert('信息','已会签,不可重复会签');
							//	}		
								//},
								failure : function(resp, opts) {
			                 
			                    Ext.Msg.alert('信息','会签失败!'); 
		}
						//		});
						}
						},{
							text :'取消',
							handler : function() {
								wintree.close();
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
	                        document.getElementById("sendrange").value=retValue.names;
							document.getElementById("personid").value=retValue.ids;
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
							
						
						} 
						]
				});
				wintree.show();
			},
			failure : function(resp, opts) {
			alert(resp.responseText);
				Ext.Msg.alert('信息', '会签失败!');
			}
		});
			
			
			
			},
			scope : this,
			iconCls : 'printButton'
		});
		return act;
	}
	
	//编号
	
	this.bianHaoAction =function(){
		var bianHaoAction=new Ext.Action({
        text: '编号',
        tooltip: '查看详细列表',
        handler: function(){    
				 var _width = 800;
			var _height = Ext.getBody().getHeight()-40;
			var addwin = new Ext.Window({
					title : '编号',
					layout : 'fit',
					width : _width,
					id : 'bhwindows',
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='bhiframe' name='bhiframe' width='" + (_width-30)  + "' height='" + (_height - 60) + "'src='/ckAction.action?activityInstId="+module.activityInstId+"'></iframe>"
			});
			addwin.show();
        },
		scope: this,
        iconCls: 'viewRouteButton'
      });
	  return bianHaoAction;
	}

  
//会签查看状态
	
	this.zhuangtaiAction =function(){
	var me = this;
		var zhuangtaiAction=new Ext.Action({
        text: '状态',
        tooltip: '查看详细列表',
        handler: function(){    
		var _width = 600;
			var _height = Ext.getBody().getHeight()-40;
			var addwin = new Ext.Window({
					title : '状态',
					layout : 'fit',
					width : _width,
					id : 'ztwindows',
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='ztiframe' name='ztiframe' width='" + (_width-30)  + "' height='" + (_height - 50) + "'src='/ztAction.action?activityinstuuid="+me.module.activityInstId+"'></iframe>"
			      
			});
			addwin.show();
			
        },
		scope: this,
        iconCls: 'viewRouteButton'
      });
	  return zhuangtaiAction;
	}
	
	
	
     /*this.viewRouteLogAction =function(){
		var viewRouteLogAction=new Ext.Action({
        text: '查看流程',
        tooltip: '查看流程办理的过程',
        handler: function(){    
            JDS.bpm.Form.Action.BPM_showRouteLog(this.module);
        },
		scope: this,
        iconCls: 'viewRouteButton'
      });
	  return viewRouteLogAction;
	}*/
	this.viewProcessAction =function(){
		
		var me = this;
		var viewProcessAction=new Ext.Action({
        text: '取回',
        tooltip: '查看取回详细列表',
        handler: function(){    
				  	var routeLogGridCfg={
					  selfCfg:{
					    title:"查看已发人员",
					    width:600,
					    id:"takebackrouteLogGridCfg"
					  },
					  metaData:{
					    dataType:"json",
					    hasRowNum:true,
					    paging:{
					      totalProperty:"totalCount",
					      root:"datas",
					      pageSize:10
					    },   
					    isSingleSelect:false,
					    hasChockbox : true,
					   cols:[
					       {text:"index",name:'index',isDisplay:'false'},
					       {text:"historyId",name:'historyId',isDisplay:'false'},
					       {text:"办理人 / 所属部门",name: 'performPerson',width:200},
					       {text:"办理步骤",name: 'name',sortable:true,width:100},  
					       {text:"开始时间",name: 'startTime',width:200},  
					       {text:"到达时间",name: 'arriveTime',width:200},
					       {text:"", name: 'instId', renderer:function(value, metadata, record){
					    		   var historyId = record.get('historyId');
					    		
					    	}
					       }
					      // {text:"查看", name: 'action'}    
					    ]
					  },
					  dataUrl:"takebackroute.action?activityInstId="+module.activityInstId+"&activityInstHistoryId="+module.activityInstHistoryId
					};
					
					//取回显示
					var routelogwin = new Ext.Window({
					        title: '取回列表',
					        collapsible:true,
					        width:640,
					        height:380,
					        id:'routelogwin',
					        shim:false,
					        animCollapse:false,
					        constrainHeader:true,
					        maximizable: true,       
					        items:createGridByData(routeLogGridCfg) ,   
					         buttons: [{
					                    text:'关闭',
					                   handler: function(){
				                      routelogwin.close();
					                    }
					                },{
					                    text:'取消发送',
					                   handler: function(){
					                		me.module.getWinEl().mask('正在处理...');
				                      		var grid = Ext.getCmp('takebackrouteLogGridCfg');
				                      		var records = grid.getSelectionModel().getSelections();
				                      		if(records.length<1)
				                      			return;
				                      		var evalJs = function(o){
				                      			//alert("取回成功");
				                      		}
				                      		for(var i=0;i<records.length;i++){
				                      			var instId = records[i].get('instId');
				                      			var historyId = records[i].get('historyId');
				                      			JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context + 'expression.jsp', evalJs, $H({
												     expression:'$currActivityInst.takeBack()',
													activityInstId: instId,
													activityInstHistoryId:historyId
												}));
				                      		}
				                      		
				                      		me.module.getWinEl().unmask();
				                      		 routelogwin.close();
				                      		 Ext.getCmp(me.module.winId).close();
					                   }
					                }]      
				    });
					routelogwin.show();
        },
		scope: this,
        iconCls: 'viewRouteButton'
      })
	  return viewProcessAction;
	}
	this.viewRouteLogAction =function(){
		var viewRouteLogAction=new Ext.Action({
        text: '查看流程',
        tooltip: '查看详细列表',
        handler: function(){    
				  	var routeLogGridCfg={
					  selfCfg:{
					    title:"查看流程日志",
					    width:600,
					    id:"routeLogGridCfg"
					  },
					  metaData:{
					    dataType:"json",
					    hasRowNum:true,
					    paging:{
					      totalProperty:"totalCount",
					      root:"datas",
					      pageSize:10
					    },   
					    
					   cols:[
					       {text:"index",name:'index',isDisplay:'false'},
					       {text:"processInstId",name: 'processInstId',isDisplay:'false'},
					       {text:"办理人 / 所属部门",name: 'performPerson',width:150},
					       {text:"办理步骤",name: 'activityInstName',sortable:true,width:80},    
					       {text:"开始时间",name: 'startName',width:160},
					       {text:"结束时间",name: 'endTime',width:160}
					    ]
					  },
					  dataUrl:"routelog.action?activityInstId="+module.activityInstId+"&activityInstHistoryId="+module.activityInstHistoryId
					};
					
					//查看流程日志
					var routelogwin = new Ext.Window({
					        title: '查看流程日志',
					        collapsible:true,
					        width:640,
					        height:380,
					        id:'routelogwin',
					        shim:false,
					        animCollapse:false,
					        constrainHeader:true,
					        maximizable: true,       
					        items:createGridByData(routeLogGridCfg) ,   
					         buttons: [{
					                    text:'确定',
					                   handler: function(){
				                      routelogwin.close();
					                    }
					                }]      
				    });
					routelogwin.show();
        },
		scope: this,
        iconCls: 'viewRouteButton'
      });
	  return viewRouteLogAction;
	}
	
	//拟办意见
	this.NbyjAction =function(){
		var NbyjAction=new Ext.Action({
        text: '拟办意见修改痕迹',
        tooltip: '查看详细列表',
        handler: function(){    
				 var _width = 800;
			var _height = Ext.getBody().getHeight()-40;
			var addwin = new Ext.Window({
					title : '拟办意见修改痕迹',
					layout : 'fit',
					width : _width,
					id : 'nbyjwindows',
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='nbyjiframe' name='nbyjiframe' width='" + (_width-30)  + "' height='" + (_height - 50) + "'src='nbyjAction_findnbyj.action?activityInstId="+module.activityInstId+"'></iframe>"
			});
			addwin.show();
        },
		scope: this,
        iconCls: 'viewRouteButton'
      });
	  return NbyjAction;
	}




	
	
	
    
    this.refAction =function(){
		
	   var refAction=	new Ext.Action({
        text: '重新填写',
        tooltip: '重新装载表单',
        handler: function(){    
            this.module.reLoad();
         
        },
		scope: this,
        iconCls: 'viewRouteButton'
       });
      return refAction
		
	}
	
    
     this.endReadAction =function (){
	 	var endReadAction=new Ext.Action({
        text: '阅 闭',
        tooltip: '阅闭当前传阅文件',
        handler: function(){    
		
            JDS.bpm.Form.Action.endRead(this.module);
           this.hide();
        },
		scope: this,
        iconCls: 'readend'
        });
		return endReadAction;
	 }
    this.sendReadAction =function(){
		
		var sendReadAction= new Ext.Action({
        text: '抄 送',
        tooltip: '抄送当前传阅文件',
        handler: function(){    
         JDS.bpm.Form.Action.BPM_routeTo(this.module,'read',true);
        
        },
		scope: this,
        iconCls: 'readend'
       });
	   return sendReadAction;
		
	}
	
	
	 this.printAction =function(){
	 var printAction= new Ext.Action({
        text: '生成处理笺',
        tooltip: '打印当前显示的表单',
        handler: function(){
			var url=context+"formprint.action";
			var text='打印'+this.module.getCurrForm().formname;
			var params={
				action:this.module.formdisplayAction.split('.')[0],
				formId:this.module.getCurrForm().formId,
				activityInstId:this.module.activityInstId,
				activityInstHistoryId:this.module.activityInstHistoryId,
				personId:personid
			};
			var str=Ext.urlEncode(params);
			window.open(url+"?"+str);
			//openUrlWin(url,text,params);

        },
		scope:this,
        iconCls: 'printButton'
    });
	return printAction;
	}
   
	  this.dubugAction =function(){
	  	var dubugAction=new Ext.Action({
	        text: '调试表单',
	        tooltip: '调试表单',
	        handler: function(){  
			 JDS.bpm.Form.Action.debug(this.module);
	     
	        },
			scope: this,
	        iconCls: 'readend'
	    })  ;
		return dubugAction;
	  }
	
	  this.routeTo=function(toActivityInstId,isContinue,name){
	  	module=this.module;
	  		if(name=="完毕"){
	  		 Ext.MessageBox.confirm("系统提示","您确定不发起承办，直接返回管理员吗？",function(button,text){
	  							if(button=="yes"){
	  							
	  						JDS.bpm.Form.Action.BPM_routeTo(module,toActivityInstId,isContinue)
	  							}
	  						});
	  		}else{
	  	 Ext.MessageBox.confirm("系统提示","确定要进行"+name+"操作吗？",function(button,text){
	  							if(button=="yes"){
	  						JDS.bpm.Form.Action.BPM_routeTo(module,toActivityInstId,isContinue)
	  							}
	  						});
	  	 
	  	}

	  	
	  };
	  this.showSelectPersonPanel =function(routeDefId,name){
							var handobjs = Ext.query("[name='docid']");
							var saveAs = false;
							var uuid="";
			                     if(Ext.get(handobjs[0])!=null){
				                    var uuid = Ext.get(handobjs[0]).dom.value;
			                     }else{
			                         saveAs = true; 
			                     }
	                             if(uuid != null && uuid.length>1){
		                                saveAs = true; 
	                             }
	                              var actIds = this.module.baseField.get('activityInstId');
	                            
	  	if (this.module.isValid()){
								// 2012 - 01 -8 意见必须填写 开始
							     var actIds = this.module.baseField.get('activityInstId');
							    
							     if(Ext.get("ldyj"+actIds).dom.value=='n'){
							        JDS.Msg.warningMsg('请填写意见');
							     	return;
							     	
							     }
							      	JDS.bpm.Form.Action.BPM_ShowSelectPersonPanel(this.module,routeDefId,name)
		}else{
			JDS.Msg.warningMsg('有必填选项没填');
		}
	  } ;
	   //承办多人办理
	     this.showSelectPersonPanelDrcb =function(routeDefId){
							var handobjs = Ext.query("[name='docid']");
							var saveAs = false;
							var uuid="";
			                     if(Ext.get(handobjs[0])!=null){
				                    var uuid = Ext.get(handobjs[0]).dom.value;
			                     }else{
			                         saveAs = true; 
			                     }
	                             if(uuid != null && uuid.length>1){
		                                saveAs = true; 
	                             }
	                              var actIds = this.module.baseField.get('activityInstId');
	                            
	  	if (this.module.isValid()){
								// 2012 - 01 -8 意见必须填写 开始
							     var actIds = this.module.baseField.get('activityInstId');
							    
							     if(Ext.get("ldyj"+actIds).dom.value=='n'){
							        JDS.Msg.warningMsg('请填写意见');
							     	return;
							     	
							     }
							      if(saveAs){
							      		alert(routeDefId);
							      	JDS.bpm.Form.Action.BPM_ShowSelectPersonPanelDrcb(this.module,routeDefId)
							       }else{
							         JDS.Msg.warningMsg('意见必须填写');
							     }
		}else{
			JDS.Msg.warningMsg('有必填选项没填');
		}
	  } ;
	   
	   this.skipProcessDoc=function(){
	   
			    var skipProcessDoc = new Ext.Action({
		        text: '转入文件资料库',
		        tooltip: '将归档文件转入文件资料库',
		        handler: function(){
		           this.module.skipProcess('F5F3E520-E37A-11DD-A520-C99DB826FEB3',this.module.activityInstHistoryId);
		        },
				scope: this,
		        iconCls: 'readend'
		    });
			
			return skipProcessDoc;
		};
	  
	  this.skipProcessInfo=function(){
	  	  var skipProcessInfo = new Ext.Action({
	          text: '转入信息发布栏目',
	          tooltip: '将归档信息转入到信息发布栏目',
	          handler: function(){
			  	this.module.skipProcess('8D853E90-E225-11DD-BE90-CB316381E253',this.module.activityInstHistoryId);
		           },
		             scope: this,
	          iconCls: 'readend'
	       });
		return skipProcessInfo;
	  }
	  
	    this.skipProcessInfoType=function(){
	  	  var skipProcessInfo = new Ext.Action({
	          text: '转入信息分类',
	          tooltip: '将归档信息进行信息分类',
	          handler: function(){
			  	this.module.skipProcess('41F94DB0-CFF8-11DE-A67B-D6C012764BCA',this.module.activityInstHistoryId);
		           },
		             scope: this,
	          iconCls: 'readend'
	       });
		return skipProcessInfo;
	  }
	
	   this.skipProcessKsdt=function(){
                    var skipProcessKsdt = new Ext.Action({ 
                  text: '转入科室动态',
                  tooltip: '将文件转入科室动态',
                  handler: function(){
                                  this.module.skipProcess('B5D1C280-FD8A-11DD-8280-A608267DA0A7',this.module.activityInstHistoryId);
                           },
                           scope: this,
                  iconCls: 'readend'
               });
                return skipProcessKsdt;
          }
         this.skipProcessYinzhang=function(){
                    var skipProcessYysp = new Ext.Action({ 
                  text: '填写用印单',
                  tooltip: '填写用印单',
                  handler: function(){
                  this.module.newProcess2('99954E10-DB02-11DE-8E10-8F4A6C0296B0',this.module.activityInstId);
                           },
                           scope: this,
                  iconCls: 'readend'
               });
                return skipProcessYysp;
          }
 
}
			
Ext.extend(JDS.bpm.Form.FormData, Ext.util.Observable, {
	load: function(){
		JDS.ajax.Connection.load(this);
	}
  }
)


