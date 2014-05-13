/**
 * 后台数据获取类
 * @param {Object} cfg
 */


JDS.bpm.Form.ResendSelectPerFormData = function(cfg){
	Ext.apply(this, cfg);
	
	this.getTree=function(treeType,routeDefId,checkModel,title){
		var url=context+'SelectPerformerJAONBF.action?activityInstId='+this.module.activityInstId;
	    var Tree = Ext.tree;
	    var tree = new Tree.TreePanel({
		title:title,
		routeDefId:routeDefId,
	    animate:true,
	    enableDD:false,
	    collapsible:true,
	    checkModel: checkModel ,   //对树的级联多选   
        onlyLeafCheckable: true,//对树所有结点都可选   
        checked: undefined ,
	    loader: new Tree.TreeLoader({baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI },dataUrl:url+'&treeType='+treeType+'&routeDefId='+routeDefId}),
	    lines: true,
	    selModel: new Ext.tree.MultiSelectionModel(),
	    containerScroll: true,
	    autoHeight:true,
	    rootVisible:false
	    });
	    var root = new Tree.AsyncTreeNode({
	    text: '对象',
	    leaf : false,
	    cls : 'folder',
	    draggable:false,
	    id:'toproot'
	    });
	    tree.setRootNode(root);
	    tree.on('beforeload', function(node){
	    tree.loader.dataUrl = url+'&treeType='+treeType+'&childOrgId='+node.id+'&routeDefId='+routeDefId;
	    });
		     root.expand(true);
		 return tree;
	}	   
	this.treePanels=new Ext.util.MixedCollection(); 
	

}
			
Ext.extend(JDS.bpm.Form.ResendSelectPerFormData, Ext.util.Observable, {
	
	render:function(){
		
		if (this.isNoSelect){
			if (personIds.indexOf(personid)){
					var evalJs = function(o){
						module.win.
					    Ext.namespace("EVAL");
						JDS.Msg.alertMsg("发送成功");
						   module.win.close();
							eval(o);
			       }
		     JDS.bpm.Form.Action.submit(evalJs,module);
			}
			
		}else{
			this.readTree=this.getTree("READ",this.performTrees[0].routeDefId,"multiple","抄送");
			 var tabPanel= new Ext.TabPanel({        
                    autoTabs:true,
                    region: 'center',        
                    margins:'3 3 3 0', 
                    defaults:{autoScroll:true},        
					items:[this.readTree],        
                    activeTab:0,
                    deferredRender:false,
                    border:false
                });
			
			 var performwin = new Ext.Window({
		        title: '选择发送人',
		        collapsible:true,
		        width:330,
				shim:false,
		        height:450,
		        modal: true,
				minimizable:false,
				tabPanel:tabPanel,
		        animCollapse:false,
		        constrainHeader:true, 
		        maximizable: true,       
		        items:[{items:[tabPanel],height:355,autoScroll:true},{ html:'<div align="right"><input type="checkbox"  value ="true" name="SMSMsg" id="SMSMsg" />发送短信提醒</div>' }],   
				   
		         buttons: [{
		                    text:'确定',
							scope:this,
		                    handler: function(){
							
						   	var module=this.module;
							
							var toActivityInstIds=[];
						    var performPersonIdList=[];
							var readPersonIds=[];
							var readPersonIdList=[];
							
						  this.readTree.getChecked().each(function(node){
								if (node.isLeaf()) {
									readPersonIds.push(node.id);
								}
							});
							
							readPersonIdList.push(readPersonIds.join(","));
							
							this.treePanels.eachKey(function(toActivityDefId){
								toActivityInstIds.push(toActivityDefId);
								var performPersonIds=[];
								var treePanel=this.treePanels.get(toActivityDefId);
								treePanel.getChecked().each(function(node){
									if (node.isLeaf()){
										performPersonIds.push(node.id);
									}
									
								});
							    performPersonIdList.push(performPersonIds.join(","));
							
							},this);
							
							module.baseField.add('readPersonIdList',readPersonIdList.join(";"));
						
							if(SMSMsg.checked)
						   		{
								  module.baseField.add('bpmUserClientUtil.BPM_SMSMsg','1');
						   		}     
							module.baseField.add('activityInstId',module.baseField.get('activityInstId'));
							module.baseField.add('performPersonIds',performPersonIdList.join(";"))
							module.baseField.add('routeId',this.performTrees[0].routeDefId);
							module.baseField.add('bpmUserClientUtil.BPM_UpdateType','routeto');
							if (module.isValid()){
									var str=module.getValues();
									module.winel.mask('正在发送...');
									evalJs = function(){
										module.winel.unmask();
										JDS.Msg.alertMsg("发送成功");
										porcess=0;
									
										Ext.getCmp(module.winId).close();
											refGridById(module.gridWinId);
										
									}
									porcess=1;
										performwin.close();
										   Ext.MessageBox.wait("准备发送","正在准备数据请稍候...",{
														           width:300
														       });
										
									JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(context+'demoResender.action', evalJs,str);
							}else{
								JDS.Msg.warningMsg('校验失败请检查表单')
							}		
						
		                  
		                    }
							
		                },{
		                    text: '取消',
		                    handler: function(){
		                    performwin.close();
		                    }
		                    
		                }]      
		    });
		

		 this.performTrees.each(function(f){
		 	 var treePanel=this.getTree("PERFORM",f.routeDefId,f.perfromType=='SINGLE'?'single':'cascade',f.title);
			 this.treePanels.add(f.toActivityDefId,treePanel);
			    treePanel.setTitle(f.title);
				tabPanel.insert(0,treePanel);
		  },this);
		 
		 performwin.show();;
		}
		
		
	},
	load: function(){
		JDS.ajax.Connection.load(this);
	}
  }
)
