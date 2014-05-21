	Ext.namespace("EVAL");
JDS.bpm.Form.Action={

	//查询
	search:function(){
		JDS.bpm.Form.SearchAction.search();
	},
	
	//重发
	resender: function BPM_Resender(module){
		  module.winel.mask('正在处理...');
		  
		var evalJs = function(o){
			module.winel.unmask();
			refGridById(module.gridWinId);
			Ext.getCmp(module.winId).close();
		}
		JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context + 'demoResender.action', evalJs, $H({
			activityInstId: module.activityInstId
		}));
	
	},
	
	//重发
	reSend: function BPM_ReSend(module){
		  JDS.bpm.Form.Action.BPM_ShowSelectPersonPanelBf(module,'','重新发送','reSend') ;
	},

	
	//收回重新办理
	tackBackNone: function BPM_TackBackNONE(module){
		  module.winel.mask('正在处理...');
		  
		var evalJs = function(o){
			module.winel.unmask();
			refGridById(module.gridWinId);
			Ext.getCmp(module.winId).close();
		}
		JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context + 'demotakebackNONE.action', evalJs, $H({
			activityInstId: module.activityInstId
		}));
	
	},
	//收回重新办理
//	tackBack:function BPM_TackBack(module){
//		 module.winel.mask('正在处理...');
//		
//		var evalJs = function(o){
//			module.winel.unmask();
//			Ext.getCmp(module.winId).close();
//			refGridById(module.gridWinId);
//		}
//		JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context + 'demotakebackNONE.action', evalJs, $H({
//			activityInstId: module.activityInstId
//		}));
//	},
	
	//收回重新办理
	tackBack:function BPM_TackBack(module){
		 module.getWinEl().mask('正在处理...');
		
		var evalJs = function(o){
			module.getWinEl().unmask();
			if (portalcurrNode){
			  portalcurrNode.fireEvent("click",portalcurrNode);
				}else{
			Ext.getCmp(module.winId).close();
			refGridById(module.gridWinId);
			}
		}
		
		JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context + 'expression.jsp', evalJs, $H({
		     expression:'$currActivityInstHistory.activityInst.takeBack()',
			activityInstId: module.activityInstId,
			activityInstHistoryId:module.activityInstHistoryId
		}));
	},
	
	//收回重新办理
	tackBack1:function BPM_TackBack1(module, instId){
		 module.getWinEl().mask('正在处理...');
		
		var evalJs = function(o){
			module.getWinEl().unmask();
			if (portalcurrNode){
			  portalcurrNode.fireEvent("click",portalcurrNode);
				}else{
			Ext.getCmp(module.winId).close();
			refGridById(module.gridWinId);
			}
		}
		
		JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context + 'expression.jsp', evalJs, $H({
		     expression:'$currActivityInstHistory.activityInst.takeBack()',
			activityInstId: instId,
			activityInstHistoryId:module.activityInstHistoryId
		}));
	},
	
	
	
	//发出退回操作
	routeBack:function BPM_routeBack(module,activityInstHisId){
		 module.winel.mask('正在处理...');
	
		var evalJs = function(o){
			module.winel.unmask();
			Ext.getCmp(module.winId).close();
			refGridById(module.gridWinId);
		}
		JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context + 'demotakebackNONE.action', evalJs, $H({
			activityInstId: module.activityInstId,
			toActivityInstHistoryId: activityInstHisId
		}));
	},
	
	
	//阅必
	endRead:function BPM_EndRead(module){
		
		 module.winel.mask('正在处理...');
		var evalJs = function(o){
			module.winel.unmask();
			Ext.namespace("EVAL");
			eval(o);
			Ext.getCmp(module.winId).close();
			refGridById(module.gridWinId);
		}
		JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context + 'endRead.action', evalJs, 
		$H({
			activityInstId: module.activityInstId
			
		}));
	},
	
	
		/*BPM_showRouteLog:function (module) {

    function getSg(frameId){
      return document.frames[frameId]["sGraph"];
    }

	var url=context+'routelogView.action?activityInstId='+module.activityInstId+'&activityInstHistoryId='+module.activityInstHistoryId;
	    var p = createIframePanel({
				url:url,
	      title: ''
			});
	    var cfg={
	      title:"历程列表",
	      maximizable:true,
	      width:Ext.getBody().getWidth(),
	      height:Ext.getBody().getHeight()-30,
	      y:0,
	      items:p,
	      tbar:[
	        {
	          text:"放大",
	    cls: 'x-btn-text-icon',
	          icon:"desktop/widgets/bpm/viewroute/graph/resources/images/zoomin.gif",
	          tooltip:"放大视图",
	          handler:function(){
	            getSg(p.frameId).graph.zoomIn();
	          }
	        },
	        {
	          text:"缩小",
	    cls: 'x-btn-text-icon',
	          icon:"desktop/widgets/bpm/viewroute/graph/resources/images/zoomout.gif",
	          tooltip:"缩小视图",
	          handler:function(){
	            getSg(p.frameId).graph.zoomOut();
	          }
	        },
	        {
	          text:"正常",
	        cls: 'x-btn-text-icon',
	          icon:"desktop/widgets/bpm/viewroute/graph/resources/images/zoomactual.gif",
	          tooltip:"恢复正常大小",
	          handler:function(){
	            getSg(p.frameId).graph.zoomActual();
	          }
	        },
	        {
	          text:"查看详细列表",
		      tooltip:"查看详细列表",
	          handler:function(){
			  
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
					       {text:"办理步骤",name: 'activityInstName',sortable:true,width:100},    
					       {text:"当前状态",name: 'state',width:100},
					       {text:"开始时间",name: 'startName',width:100},
					       {text:"结束时间",name: 'endTime',width:100}
					      // {text:"查看", name: 'action'}    
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

	          }
	        }
	      ],
		  
		
		  
	      listeners: {
	        resize: function(obj,w,h) {
	          p.setSize(obj.getInnerWidth()-2,obj.getInnerHeight()-2);
	        }
	      }
	    };
	    var window =createBaseWin(cfg);
	    window.show();
			
	  },
		*/
	

	//流程结束处理
	BPM_routeToEnd:function (module) {  
		Ext.MessageBox.confirm("请确认","执行结束操作后将无法修改，请您确认：<br/>"+
									"<span >  &nbsp;&nbsp;1.正文，附件，审核意见等内容是否完整；</span><br/>"+
									"<span >  &nbsp;&nbsp;2.正文中是否已进行“接收所有修订”操作。</span>",
						function(button,text){			
							if(button=="yes"){
	
		if (module.isValid()){
			module.baseField.add('toActivityDefIds','LAST');
			module.baseField.add('readPersonIdList','');
			module.baseField.add('startActivityInstId',module.baseField.get('activityInstId'));
			module.baseField.add('bpmUserClientUtil.BPM_UpdateType','routetoend');
			module.baseField.add('performPersonIdList','');
		    module.winel.mask('正在处理数据...');
			var str=module.getValues();
			var evalJs = function(o){
				module.winel.unmask();
				Ext.getCmp(module.winId).close();
				refGridById(module.gridWinId);
			}
			alert("@@@");
			JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context+'demoupdate.action', evalJs,str);
		}else{
			JDS.Msg.warningMsg('校验失败请检查表单')
		}
		
			return true;
		}		
			});
		
		
		return true;
	},
	//调试表单
	debug:function (module) {
	   var  url=context+"fdt/designer/fdtDesigner.action?fileName="+module.getCurrForm().formpath+"&activityInstId="+module.activityInstId;
	        window.open (url, '', 'height=740, width=1000,  toolbar=no,resizable=yes, menubar=no')  ;
		
	}, 
	
	BPM_routeTo:function (module,toActivityInstId,isContinue) {
		module.baseField.add('toActivityDefIds',toActivityInstId);
		module.baseField.add('readPersonIdList','');
		module.baseField.add('startActivityInstId',module.baseField.get('activityInstId'));
		module.baseField.add('bpmUserClientUtil.BPM_UpdateType','routeto');
		module.baseField.add('performPersonIdList','');
		 var handobjs = Ext.query("[name='docid']");
		 var saveAs = true;
		 var uuid = "";
			if(Ext.get(handobjs[0])!=null){
				uuid = Ext.get(handobjs[0]).dom.value;
			}else{
				saveAs = true;
			}
		   if(uuid != null && uuid.length>1){
			   saveAs = true; 
		   }
		if (module.isValid()){
			 var actIds = module.baseField.get('activityInstId');
							    
							     if(Ext.get("ldyj"+actIds).dom.value=='n'){
							        JDS.Msg.warningMsg('请填写意见');
							     	return;
							     	
							     }
		    if(saveAs){
		      var str=module.getValues();
			  module.winel.mask('正在处理数据...');
			  evalJs = function(){
			  module.winel.unmask();
			  JDS.Msg.alertMsg("发送成功");
				if (isContinue) {
					Ext.getCmp(module.winId).close();
					refGridById(module.gridWinId);
				}
				else {
					Ext.getCmp(module.winId).close();
					refGridById(module.gridWinId);
				}
			}
			
			JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context+'demoupdate.action', evalJs,str);
		    
		    }else{
		       JDS.Msg.warningMsg('正文必须填写');
		    }
			
		}else{
			JDS.Msg.warningMsg('有必填选项没填');
		}		
		
	}, 
	
	
	

	
//		//显示选人窗口
//	BPM_ShowSelectPersonPanel:function (module,routeId,name) {
//			
//			var menuCfg={
//	        url:context+"SelectPersonJSDEF.action",
//	        params: $H({
//			 	activityInstId:module.activityInstId,
//				routeDefId:routeId
//				}),
//			module:module,
//			activityInstId:module.activityInstId,
//			name:name
//	      }
//     var SelectPerFormDat=new JDS.bpm.Form.SelectPerFormData(menuCfg).load();		
//		return true;
//	}, 
	
	EVALListeners:function(eventName,routeId,scopeCallback,scope){
	
	 var str="expression=$JSONForm($currRouteDef.Listeners.{? #this.expressionEventType=='"+eventName+"'})"
	 str=str+"&routeDefId="+routeId;
	 var url="expression.jsp"
	  var callback=function(o){
	  try{
         var listeners=	Ext.decode(o);
         var stopEvent=false;
      
         listeners.each(function(listener){
	         if (listener.expressionStr){
	            var ExpressionFunction=new Function(listener.expressionStr);
	            expression=ExpressionFunction();
	            if (!expression){
	             stopEvent=true;
	          }
	        }
	       })
        if (!stopEvent){
           scopeCallback.call(this,o);
        }
       }catch(e){
          scopeCallback.call(this,o);
       }
     }
     
     try{
	      JDS.ajax.Connection.LoadJsonFromUrl(url,callback,str,scope);
	      }catch(e){
	     scopeCallback.call(this,o);
	  }
	},
	//补发显示选人窗口
	BPM_ShowSelectPersonPanelBf:function (module,routeId,name,routeto)  {
	
		var callBack =function (){
		
	     module.routeto=routeto;
			var menuCfg={
	        url:context+"SelectPersonJSDEFBF.action",
	        params: $H({
			 	activityInstId:module.activityInstId,
			 	activityInstHistoryId: module.activityInstHistoryId,
			 	routeto:routeto,
				routeDefId:routeId,
				bf:'bf'
				}),
			module:module,
			activityInstId:module.activityInstId,
			routeto:routeto,
			activityInstHistoryId: module.activityInstHistoryId,
			name:name,
			bf:'bf'
	      }
	   
     var SelectPerFormDat=new JDS.bpm.Form.SelectPerFormDataBf(menuCfg).load();		
		}
  //  callBack();
  
    JDS.bpm.Form.Action.EVALListeners('willOpenSelectPerson',routeId,callBack,this);
		return true;
	},
	//显示选人窗口
	BPM_ShowSelectPersonPanel:function (module,routeId,name,routeto)  {
	
		var callBack =function (){
		
	     module.routeto=routeto;

			var menuCfg={
	        url:context+"SelectPersonJSDEF.action",
	        params: $H({
			 	activityInstId:module.activityInstId,
			 	activityInstHistoryId: module.activityInstHistoryId,
			 	routeto:routeto,
				routeDefId:routeId
				}),
			module:module,
			activityInstId:module.activityInstId,
			routeto:routeto,
			activityInstHistoryId: module.activityInstHistoryId,
			name:name
	      }
	   
     var SelectPerFormDat=new JDS.bpm.Form.SelectPerFormData(menuCfg).load();		
		}
  //  callBack();
  
    JDS.bpm.Form.Action.EVALListeners('willOpenSelectPerson',routeId,callBack,this);
		return true;
	}, 

	/*多人承办（潮州）
	BPM_ShowSelectPersonPanelDrcb:function (module,routeId,name) {
		var callBack =function (){
		
	     //module.routeto=routeto;

			var menuCfg={
	        url:context+"SelectPersonJSDEF.action",
	        params: $H({
			 	activityInstId:module.activityInstId,
			 	activityInstHistoryId: module.activityInstHistoryId,
			 	//routeto:routeto,
				routeDefId:routeId
				}),
			module:module,
			activityInstId:module.activityInstId,
			//routeto:routeto,
			activityInstHistoryId: module.activityInstHistoryId,
			name:name
	      }
	   
     var SelectPerFormDat=new JDS.bpm.Form.SelectPerFormDataDrcb(menuCfg).load();		
		}
  //  callBack();
  	JDS.bpm.Form.Action.EVALListeners('willOpenSelectPerson',routeId,callBack,this);
		return true;
   
	}*/
	//多人承办(内蒙)
	BPM_ShowSelectPersonPanelDrcb:function (module,routeId) {
			var menuCfg={
	        url:context+"SelectPersonJSDEF.action",
	        params: $H({
			 	activityInstId:module.activityInstId,
				routeDefId:routeId
				}),
			module:module,
			activityInstId:module.activityInstId
	      }
     var SelectPerFormDat=new JDS.bpm.Form.SelectPerFormDataDrcb(menuCfg).load();		
		return true;
	}, 
	BPM_ResendSelectPersonPanel:function (module,routeId) {
		
			var menuCfg={
	        url:context+"SelectPersonJSDEF.action",
	        params: $H({
			 	activityInstId:module.activityInstId,
				routeDefId:routeId
				}),
			module:module,
			activityInstId:module.activityInstId
	      }
	      alert(menuCfg);
     var ResendSelectPerFormDat=new JDS.bpm.Form.ResendSelectPerFormData(menuCfg).load();		
		return true;
	}, 
	
	closewin:function (btn,nextUrl){
		if (btn=="yes"){ 
		 var closewin="window.parent.location.replace('"+context+"clear.action?personId="+personid+"&event=reboot');"
		
		messageBoxprogress("","","<b>正在关闭系统...</b>",'5','',closewin);
		
		}else if (btn=="ok"){
		var closewin="window.parent.location.replace('"+context+"clear.action?personId="+personid+"&event=shutdown');";
		messageBoxprogress("","","<b>正在关闭系统...</b>",'5','',closewin);
		}else {
		
		}
	},

	
	
	//tanrui 有改动
	save:function (module) {
	  var handobjs = Ext.query("[name='docid']");
	  var saveAs = false;
	  var uuid = "";
			if(Ext.get(handobjs[0])!=null){
				uuid = Ext.get(handobjs[0]).dom.value;
			}else{
				saveAs = true;
			}
	  
	  if(uuid != null && uuid.length>1){
		 saveAs = true; 
	  }
	  if (module.isValid()){
	      //if(saveAs){
	        module.baseField.add('bpmUserClientUtil.BPM_UpdateType','saveonly');
		    module.winel.mask('正在保存数据...');
			var str=module.getValues();
			var evalJs = function(o){
				module.winel.unmask();
				Ext.namespace("EVAL");
				eval(o);
			}
			JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context+'demoupdate.action', evalJs,str);
		}else{
			JDS.Msg.warningMsg('有必填选项没填')
		}
	}
	
}

	EVAL.exportExcel=function(grid){
		exportExcel(grid);
	}
	//导出EXCLE
	function exportExcel(grid){
			remote.excel.exportExcel(grid,grid.title);
	};
	
	
	function getProcessIds(grid){
		var selections=grid.getSelections();
		  
	   if (selections.length==0){
	         JDS.Msg.warningMsg('请选择需要删除的文件');
	   return;
	   }
 
	var delAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('processInstId').length>0 ){     
	    delAllList[delAllList.length]= selections[i].get('processInstId');    
	   }else{
	    Ext.getCmp(gridId).getSelectionModel().deselectRow(selections[i].get('index'));   
	   }
	  };
	   var processInstIdList=delAllList.join();
	   var str="processInstIds="+processInstIdList;
	return str;
   }

    EVAL.deleteAll=function(grid){
		deleteAll(grid);
	}
	//批量删除
	function deleteAll(grid){
	
		  var str =getProcessIds(grid);
		
		   var evalJs = function(o){
					refGridById(grid.id);
	  			}
		  JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context+'demodelete.action', evalJs,str);
		   
		
	}
	
	EVAL.abortAll=function(grid){
		abortAll(grid);
	}
	//批量中止
	function abortAll(grid){
		  var str =getProcessIds(grid);
		  var evalJs = function(o){
					refGridById(grid.id);
	  			}
		  JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context+'demoAbort.action', evalJs,str);
	
	}

	//批量归档
	function completeAll(grid){
		  var str =getProcessIds(grid);
		  var evalJs = function(o){
					refGridById(grid.id);
	  			}
		  JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context+'democomplete.action', evalJs,str);
	
	}

