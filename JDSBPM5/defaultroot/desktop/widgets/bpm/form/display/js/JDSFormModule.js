
/**
 * class 
 *
 * @param {Object} config
 */

Ext.app.FormModule = function(config){
    Ext.apply(this, config);
    Ext.app.FormModule.superclass.constructor.call(this);
	if (getCurDesktop().getManager().getActive()){
		this.gridWinId=getCurDesktop().getManager().getActive().getComponent(0).getId();
	}	
	

	this.baseField=new Ext.util.MixedCollection(); 
	if (this.activityInstId){
	this.baseField.add('activityInstId',this.activityInstId);
	}
	if (this.activityInstHistoryId){
		this.baseField.add('activityInstHistoryId',this.activityInstHistoryId);
	}
	

	this.activityInstId;
	this.activityInstHistoryId;
	this.allForm=new Ext.util.MixedCollection(); 
	
	this.winId=Ext.id();
	this.tabBarId=Ext.id();
	this.subPnael;

	this.win = getCurDesktop().createWindow({
				title: '打开流程',
				autoScroll: true,
				id:this.winId,
				width: Ext.getBody().getWidth(),
				height: Ext.getBody().getHeight()-30,
				y:0,
			    module:this,
				items: [
				
                       {
		                 region:'north',
						 height:1,
						 html:''   
						}
						,
						{
						   region:'north',
						  
	                       html:"<div id="+this.tabBarId+"></div>"
                        }	,									{
		                   region:'south',
						   height:1,
						   html:''
						}
				
				
			]
			});	

	this.win.on("resize",function(win, width, height){
			try{
			 if (win.module.subPanel.items.length>1){
			    win.module.subPanel.items.each(function(item){
						item.setHeight(height-100);
				})
			 }				
			}catch(e){}
	})
	this.winel=this.win.getEl();
	this.getWinEl=function(){
	  return this.winel;
	}
	if (window.screen.width<800){
		this.win.maximize();
	}
	this.win.show();		
	this.winel.mask("加载中，请稍候...");
	
	this.reLoad=function(){
		this.subPanel.destroy();
		this.createWindow();
	}
	
	this.markActive=function(){
		
	};
	
	
	this.isValid=function(){
		var valid=true;
		this.allForm.each(function(f){
			try{	
				if (!f.extForm.isValid()){
				    valid = false;
				}
		     }catch(e){
			 	       valid = false;
			 }
		  })
		  return valid;
	}
	
	/**
	 * 获取所有表单的提交值
	 */
	this.getValues=function(){
		var str="";
		 this.allForm.each(function(f){
			 str=f.extForm.getValues(true)+"&"+str;
			
		  })
		     this.baseField.eachKey(function(f){
			  str=str+"&"+encodeURIComponent(f)+'='+encodeURIComponent(this.baseField.get(f))
			  },this)
		  
	
		return str;  
	}

    this.display=this.createWindow;
	
	
	/**
	 * 多参数新建微流程
	 * @param params
	 * @return
	 */
    this.newProcessByHParams = function(params){
		  var menuCfg={
	        url:context+"newPublication.action",
	        params:params,
			module:this,
	        render:displayCallBack
	      } 
        var FormData=new JDS.bpm.Form.FormData(menuCfg).load();

	      return this.win;
	 }	 
   /**
    * 新建流程
    */
   this.newProcess = function(processDefId){ 
 		  var menuCfg={
	        url:context+"demoInsert.action",
	        params:$H({processDefId:processDefId}),
			module:this,
	        render:displayCallBack
	      } 
          var FormData=new JDS.bpm.Form.FormData(menuCfg).load();

	      return this.win;
	 }
	 this.newProcess2 = function(processDefId,activityInstId){ 
	 	var wincfg={
		displayAction:"formdisplay.action",
	    formdisplayAction:"BPMClientFormDisplayAction.action"
	}
  var module=new Ext.app.FormModule(wincfg);
 		  var menuCfg={
	        url:context+"newProcessInsert.action",
	        params:$H({processDefId:processDefId,oldActivityInstId:activityInstId}),
			module:module,
	        render:displayCallBack
	      } 
          var FormData=new JDS.bpm.Form.FormData(menuCfg).load();

	      return this.win;
	 } 
	 	
   /**
    * 流程跳转（收文跳转发文拟稿走这里）
    * 宣武国土项目扩展
    */
   this.skipProcess = function(processDefId,activityInstHistoryId){ 
		  var wincfg={
			    formdisplayAction:"BPMClientFormDisplayAction.action"
	     }
          var module=new Ext.app.FormModule(wincfg);
  
		   var menuCfg={
		   	 url: context+"skipProcessInsert.action",
			  formdisplayAction:"BPMClientFormDisplayAction.action",
	        params:$H({
				   processDefId:processDefId,
			       parentActivityInstHistoryId:activityInstHistoryId,
				   fileTitle:""
			}),
			module:module,
	        render:displayCallBack
	      } 
		  	Ext.getCmp(this.winId).close();
          var FormData=new JDS.bpm.Form.FormData(menuCfg).load();	
	      return win;
		  
	 }
	 
		//成文 调转 收文登记  执行 传阅<--hcl-->
	  this.skipProcess1 = function(processDefId,activityInstHistoryId){ 
		  var wincfg={
			    formdisplayAction:"BPMClientFormDisplayAction.action"
	     }
          var module=new Ext.app.FormModule(wincfg);
  
		   var menuCfg={
		   	 url: context+"skipProcessInserts.action",
			  formdisplayAction:"BPMClientFormDisplayAction.action",
	        params:$H({
				   processDefId:processDefId,
			       parentActivityInstHistoryId:activityInstHistoryId,
				   fileTitle:""
			}),
			module:module,
	        render:displayCallBack
	      } 
		  	Ext.getCmp(this.winId).close();
          var FormData=new JDS.bpm.Form.FormData(menuCfg).load();	
	      return win;
		  
	 }
   /**
    * extends module
    */
   this.createWindow = function(){
   	   
 		  var menuCfg={
	        url: context+this.displayAction,
	        params: $H({
				activityInstId:this.activityInstId,
				activityInstHistoryId:this.activityInstHistoryId
				}),
			module:this,
			activityInstHistoryId:this.activityInstHistoryId,
			activityInstId:this.activityInstId,
	        render:displayCallBack
	      }
		 
          var FormData=new JDS.bpm.Form.FormData(menuCfg).load();

	      return this.win;
	 }
	
	/**
	 * return JDS.bpm.Form
	 */
	this.getCurrForm=function(){
		var panel=this.subPanel;
		if (panel.getXType()=='tabpanel'){
			panel=panel.getActiveTab();
		}
	var	form=this.allForm.get(panel.formId);
		return form;
	};
	
	
	/**
	 * private
	 *后台获取数据后反调函数
	 */	
	function displayCallBack(){
		if (!this.module.activityInstId && this.activityInstId){
			this.module.activityInstId=this.activityInstId;
			this.module.baseField.replace('activityInstId',this.activityInstId);
			
		};
		
		if (!this.module.activityInstHistoryId && this.activityInstHistoryId){
			this.module.activityInstHistoryId=this.activityInstHistoryId;
			this.module.baseField.replace('activityInstHistoryId',this.activityInstHistoryId);
		
		}
	

	    if (this.forms.length==1){
				
		         this.module.subPanel=new Ext.Panel({
					tbar:new Ext.Toolbar({
					style:'background-color:#ff0000;',
					items:this.getTbar()
					}),
					formId:this.mainForm.formId,
					renderTo:this.module.tabBarId,
					items:[getFormPanel(Ext.apply(this.mainForm,{module:this.module,name:''}))]
					})
		}else{
		  this.module.subPanel = new Ext.TabPanel({
		        renderTo: this.module.tabBarId,
				tbar:this.getTbar(),
		        activeTab: 0,  
		        autoDestroy:true,
		        plain:true,   
		        defaults:{autoScroll: true},
				items:[getFormPanel(Ext.apply(this.mainForm,{module:this.module}))]
		      });
			
			 this.forms.each(function(f){
			  	if (f.formId!=this.mainForm.formId){
					 var formPanel=getFormPanel(Ext.apply(f,{module:this.module}));
					 this.module.subPanel.add(formPanel);
				}
			  },this);
				
		}
	
	}
	
   /**
    * private
    * @param {Object} form
    */
  /* function getFormPanel(form){
 
	    var formPanel=new Ext.Panel({
						title:form.name,
						formId:form.formId,	
						autoScroll: true,	
						listeners: {activate: function (tab){
									  	 tab.isLoad=true;
										}
									},
		                autoWidth : false,        
		                iconCls: 'tabs',
						autoScroll :true,
						style:'height:400px;',
			            autoLoad: {
								url: context+form.module.formdisplayAction, 
								scope:  form,
								params: 'formID='+form.formId+'&activityInstId='+form.module.activityInstId+"&activityInstHistoryId="+form.module.activityInstHistoryId, 
								callback:JDS.bpm.Form.FormCallFn.callFormFn
							}
	            })
			
	    return formPanel;
	}*/
	
	 function getFormPanel(form){
	    var formPanel=new Ext.Panel({
	    				//height:500,
	    				height: Ext.getBody().getHeight()-120,
	    				width: Ext.getBody().getWidth()-20,
						title:form.name,
						formId:form.formId,
						autoScroll: true,
						listeners: {activate: function (tab){
									  	 tab.isLoad=true;
										}
									},
						autoShow:true,
		                autoWidth :false,        
		                iconCls: 'tabs',
			            autoLoad: {
								url: context+form.module.formdisplayAction, 
								scope:  form,
								params: 'formID='+form.formId+'&activityInstId='+form.module.activityInstId+"&activityInstHistoryId="+form.module.activityInstHistoryId, 
								callback:JDS.bpm.Form.FormCallFn.callFormFn
							}
	            })
			
	    return formPanel;
	    
	}
    this.xtype = 'FormModule';
    this.init(); 
}
Ext.extend(Ext.app.FormModule, Ext.app.Module, {});






 