
/**
 * 后台数据获取类
 * @param {Object} cfg
 */
JDS.bpm.Form.FormData = function(cfg){
	Ext.apply(this, cfg);

	//增加开始
	this.shudaoAction = function(actid){
		var shudaoAction=new Ext.Action({
			text: '疏导',
			tooltip: '流程疏导',
			handler: function(){
				openUrlWin("/bpm/admin/psd/oa/ProcessDisplay.jsp?ActivityInstId="+actid,"流程疏导");
			},
			scope: this,
			iconCls: 'printButton'
		   });
		return shudaoAction;
	}
	//增加结束
	this.printwordAction = function(){
		var printwordAction=new Ext.Action({
			text: '打印',
			tooltip: '打印表单内容到word',
			handler: function(){
				printwordjs();
			},
			scope: this,
			iconCls: 'printButton'
		   });
		return printwordAction;
	}
	//电子归档
	this.electronicAction = function(){
		var electronicAction=new Ext.Action({
			text: '电子归档',
			tooltip: '保存当前表单内容',
			handler: function(){
				var wname = getWordname();
				/*var iseles = Ext.query("[name='"+wname+"iselectronic']");
				if(Ext.get(iseles[0])!=null){
					if(Ext.get(iseles[0]).dom.value=='1'){
							alert("此流程已归档！");
							return;
					}
				}*/
				//openUrlWin('data_returnaddData.action?wordname='+wname+'&activityInstId='+this.module.activityInstId+'&personId='+personid+'&formId='+this.module.getCurrForm().formId,'档案信息');
			Ext.Ajax.request({
					url:'/data_findByActivityInstId.action',
					method:'post',
					async : false,
					params:{activityInstId:this.module.activityInstId},
					success:function(o){
						var textstr = o.responseText;
						if(textstr==0){
							//openUrlWin('/desktop/widgets/electronicfile/datalist/addDataLoad.jsp?wordname='+wname+'&activityInstId='+this.module.activityInstId+'&personId='+personid+'&formId='+this.module.getCurrForm().formId,'档案信息');
							showelecData(wname,this.module.activityInstId,personid,this.module.getCurrForm().formId);
						}else{
							alert("此流程已归档！");
						}
					}
					
				});
			},
			scope: this,
			iconCls: 'printButton'
		   });
		return electronicAction;
	}
	
	//撤回
	this.resendAction =function(name,routeDefId){
		var resendAction=new Ext.Action({
		text: name,
		tooltip: '重发',
		handler: function(){
			//JDS.bpm.Form.Action.resender(this.module);
			JDS.bpm.Form.Action.BPM_ResendSelectPersonPanel(this.module,routeDefId);
		},
		scope: this,
		iconCls: 'saveButton'
	   });
	   return resendAction;
	} 
	
	
	this.saveAction =function(){
		var saveAction=new Ext.Action({
		text: '保存表单',
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
		text: '发送',
		tooltip: '发送当前文件',
		handler: function(){
			JDS.bpm.Form.Action.BPM_routeTo(this.module);
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
			text: '结 束',
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
		text: '收回',
		tooltip: '收回重新办理',
		handler: function(){
			JDS.bpm.Form.Action.tackBack(this.module);
		},
		scope: this,
		iconCls: 'routeBackButton'
	    });
		return tackBackAction;
	}
    
    this.viewRouteLogAction =function(){
		var viewRouteLogAction=new Ext.Action({
        text: '查看流程日志',
        tooltip: '查看流程办理的过程',
        handler: function(){    
            JDS.bpm.Form.Action.BPM_showRouteLog(this.module);
        },
		scope: this,
        iconCls: 'viewRouteButton'
      });
	  return viewRouteLogAction;
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
        text: '阅闭',
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
        text: '抄送',
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
        text: '打 印',
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
	
	  this.routeTo=function(toActivityInstId,isContinue){
	  	 JDS.bpm.Form.Action.BPM_routeTo(this.module,toActivityInstId,isContinue);
	  	
	  };
	  this.showSelectPersonPanel =function(routeDefId){
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
							      	JDS.bpm.Form.Action.BPM_ShowSelectPersonPanel(this.module,routeDefId)
							       }else{
							         JDS.Msg.warningMsg('正文必须填写');
							     }
		}else{
			JDS.Msg.warningMsg('校验失败请检查表单');
		}
	  	//JDS.bpm.Form.Action.BPM_ShowSelectPersonPanel(this.module,routeDefId)
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


