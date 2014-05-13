/**
 * 后台数据获取类
 * 
 * @param {Object}
 *            cfg
 */



JDS.bpm.Form.SelectPerFormData = function(cfg) {
	Ext.apply(this, cfg);
    var name = cfg.name;
    var activityInstHistoryId=cfg.activityInstHistoryId;
    this.routeto=cfg.routeto;
    if (!this.routeto){
	  this.routeto="routeto"
	  }
	this.getTree = function(treeType, routeDefId, checkModel, title) {
		var url = context + 'SelectPerformerJAON.action?activityInstId='
				+ this.module.activityInstId;
		var Tree = Ext.tree;
		var tree = new Tree.TreePanel({
					title : title,
					routeDefId : routeDefId,
					animate : true,
					enableDD : false,
					collapsible : true,
					checkModel : checkModel, // 对树的级联多选
					onlyLeafCheckable : false,// 对树所有结点都可选
					loader : new Tree.TreeLoader({
								baseAttrs : {
									uiProvider : Ext.ux.TreeCheckNodeUI
								},
								dataUrl : url + '&treeType=' + treeType
										+ '&routeDefId=' + routeDefId
							}),
					lines : true,
					selModel : new Ext.tree.MultiSelectionModel(),
					containerScroll : true,
					autoHeight : true,
					rootVisible : false
				});

		var count = 0;
		tree.on('checkchange', function(node, check) {
					var t = Ext.getCmp('datetree');
					var troot = t.root;
					var rarray = troot.childNodes;
					var marray = tree.getChecked();
					Ext.each(rarray,function(child) {
						troot = t.root;
						var rn = troot.lastChild;
						if(rn != null){
							troot.removeChild(rn);
							count--;
						}
					});
					Ext.each(marray,function(child) {
										if(child.leaf){
											troot.appendChild(new Ext.tree.TreeNode({
												id : child.id,
												text : child.text
											}));
											count++;
											}
									});
					if(!node.leaf){
						if(check == true){
							node.eachChild(function(child){
								var c = t.getNodeById(child.id);
								if(c == null){
								troot.appendChild(new Ext.tree.TreeNode({
													id : child.id,
													text : child.text
												}));
												count++;
								}
							});
						}
					}
					Ext.getCmp('blr').setText('办理人数('+ count +')');
					//Ext.getCmp('blr').setText('办理人');
				});

		var root = new Tree.AsyncTreeNode({
					text : '对象',
					leaf : false,
					cls : 'folder',
					draggable : false,
					id : 'toproot'

				});
		tree.setRootNode(root);
		tree.on('beforeload', function(node) {
					tree.loader.dataUrl = url + '&treeType=' + treeType
							+ '&childOrgId=' + node.id + '&routeDefId='
							+ routeDefId;
				});
		root.expand(true);
		return tree;
	}
	this.treePanels = new Ext.util.MixedCollection();

}

Ext.extend(JDS.bpm.Form.SelectPerFormData, Ext.util.Observable, {
 
	render : function() {

		if (this.isNoSelect) {
			if (personIds.indexOf(personid)) {
				var evalJs = function(o) {
					module.win.Ext.namespace("EVAL");
					JDS.Msg.alertMsg("发送成功");
					module.win.close();
					eval(o);
				}
				JDS.bpm.Form.Action.submit(evalJs, module);
			}

		} else {
			this.readTree = this.getTree("READ",
					this.performTrees[0].routeDefId, "multiple", "抄送");
			var tabPanel = new Ext.TabPanel({
						autoTabs : true,
						columnWidth : .5,
						height : 350,
						autoScroll : true,
						items : [this.readTree],
						activeTab : 0,
						deferredRender : false,
						border : true
					});

			var dateTree = new Ext.tree.TreePanel({
						id : 'datetree',
						rootVisible : false,
						columnWidth : .47,
						height : 350,
						autoScroll : true,
						root : new Ext.tree.AsyncTreeNode(),
						tbar : [{
									id : 'blr',
									text : '办理人数(0)',
									//text : '办理人',
									border : false
								}],
						border : false
					});
			var sms = currUserOrgName+'提示：';
			var performwin = new Ext.Window({
				title : '选择发送人',
				collapsible : false,
				width : 700,
				shim : false,
				height : 480,
				modal : true,
				minimizable : true,
				tabPanel : tabPanel,
				message:'',
				animCollapse : false,
				constrainHeader : true,
				maximizable : true,
				id:'perform',
				// items:[{items:[tabPanel],height:355,autoScroll:true},{ html:'<div align="right"><input type="checkbox"  value ="true" name="SMSMsg" id="SMSMsg" />发送短信提醒</div>' }],
				items : [{
							frame : false,
							layout : 'column',
							items : [tabPanel, dateTree],
							height : 335,
							autoScroll : true
						},
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

		          
						],
				buttons : [
				
				{
					text : '确定',
					scope : this,
					handler : function(name) {
						var module = this.module;
						
						var toActivityInstIds = [];
						var performPersonIdList = [];
						var readPersonIds = [];
						var readPersonIdList = [];

						this.readTree.getChecked().each(function(node) {
									if (node.isLeaf()) {
										readPersonIds.push(node.id);
									}
								});

						readPersonIdList.push(readPersonIds.join(","));

						this.treePanels.eachKey(function(toActivityDefId) {
									
									var performPersonIds = [];
							
									var treePanel = this.treePanels
											.get(toActivityDefId);
									treePanel.getChecked().each(function(node) {
												if (node.isLeaf()) {
													performPersonIds
															.push(node.id);
												}

											});
									if(performPersonIds.length > 0){		
										toActivityInstIds.push(toActivityDefId);
										performPersonIdList.push(performPersonIds
												.join(","));
									}

								}, this);

						
						module.baseField.add('readPersonIdList',readPersonIdList.join(";"));
						if(Ext.getCmp('SMSMsg').getValue()){
						var smscontent = Ext.getCmp('SMScontent').getValue();
							Ext.Ajax.request({
								url : '/PhoneSMS_sendMess.action',
								params : {
									performPersonIdList : performPersonIdList,
									activityInstId : module.baseField
											.get('activityInstId'),
											smscontent:smscontent
									
								},
								method : 'post',
								sunccess : function() {

								}
							});
						
						}
							
							
						
						module.baseField.add('startActivityInstId',module.baseField.get('activityInstId'));
						module.baseField.add('performPersonIdList',performPersonIdList.join(";"))
						module.baseField.add('toActivityDefIds',toActivityInstIds.join(";"));
						module.baseField.add('activityInstHistoryId',module.activityInstHistoryId);
						module.baseField.add('bpmUserClientUtil.BPM_UpdateType',this.routeto);
						//module.baseField.add('bpmUserClientUtil.BPM_UpdateType', 'routeto');
						var handobjs = Ext.query("[name='docid']");
						var saveAs = false;
						var uuid = "";
						if (Ext.get(handobjs[0]) != null) {
							var uuid = Ext.get(handobjs[0]).dom.value;
						} else {
							saveAs = true;
						}
						if (uuid != null && uuid.length > 1) {
							saveAs = true;
						}
								var str = module.getValues();
								module.winel.mask('正在发送...');
								evalJs = function() {
								
									module.winel.unmask();
									JDS.Msg.alertMsg("发送成功");
									porcess = 0;
									Ext.getCmp(module.winId).close();
									refGridById(module.gridWinId);
								}
								porcess = 1;
								performwin.close();
								Ext.MessageBox.wait("准备发送", "正在准备数据请稍候...", {
											width : 300
										});

								JDS.ajax.Connection.LoadJsonFromUrlByDeskTop(
										context + 'demoupdate.action', evalJs,
										str);

						
					}

				}, {
					text : '取消',
					handler : function() {
						performwin.close();
					}

				}]
			});

			this.performTrees.each(function(f) {
						var treePanel = this.getTree("PERFORM", f.routeDefId,
								f.perfromType == 'SINGLE'
										? 'single'
										: 'cascade', f.title);
						this.treePanels.add(f.toActivityDefId, treePanel);
						treePanel.setTitle(f.title);
						tabPanel.insert(0, treePanel);
					}, this);

			performwin.show();;
		}

	},
	load : function() {
		JDS.ajax.Connection.load(this);
	}
	
		
	
	 
	
})



 


