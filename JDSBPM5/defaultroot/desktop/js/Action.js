
/**
 * 
 * @param {Object} config
 */
DesktopAction={
	    /**
		 * 取得快捷连接菜单
		 * @param {Object} moduleNames[] 过滤关键字
		 */
		getQickMenuByName:function(moduleNames){
		 	meuns=[];
			 	JDSDesk.allModules.eachKey(function(key){
			 var module=JDSDesk.allModules.get(key);
			  moduleNames.each(function(moduleName){
			 	if (module && module.parentText && module.text.indexOf(moduleName)>-1){
			      menu=module.getMenu();
				  menu.text=module.parentText+'('+module.text+')';
				  if (menu.text){
				  	  meuns.push(menu);
				  }
				}
			  })
			})
			return meuns;
		 },
		 getQuickMeun:function(){
		 	if (!this.QuickMeun){
					  this.QuickMeun ={
					  text:'快捷菜单',
						menu:[
							{
				                text: '发文管理',
								menu: DesktopAction.getQickMenuByName(['发文'])
				            },{
							 	
				                text: '收文办理',
								menu: DesktopAction.getQickMenuByName(['收文'])
				            },{
							 	
				                text: '内部流转',
								menu: DesktopAction.getQickMenuByName(['内部'])
				            },'-',{
							 	
				                text: '考勤管理',
								menu: DesktopAction.getQickMenuByName(['请销'])
				            },{
							 	
				                text: '印章使用',
								menu: DesktopAction.getQickMenuByName(['印章'])
				            },{
							 	
				                text: '办公用品申请',
								menu: DesktopAction.getQickMenuByName(['办公用品'])
				            },{
							 	
				                text: '车辆使用',
								menu: DesktopAction.getQickMenuByName(['车辆'])
				            }
					    ]
				   }
			}
		
	
		
			return this.QuickMeun ;
		 },
		 
		  getIconSort:function(){
		  	 	if (!this.IconSort) {
					this.IconSort = {
						text: '排列图标(I)',
						menu: [{
							text: '名称',
							handler: function(e){
							}
						}, {
							text: '类型',
							handler: function(e){
								JDSDesk.getDesktop().shortcuts.handleUpdate();
							}
						}, {
						
							text: '访问时间',
							handler: function(e){
								JDSDesk.getDesktop().shortcuts.handleUpdate();
							}
							
						}, {
						
							text: '自动排列',
							handler: function(e){
								JDSDesk.getDesktop().shortcuts.handleUpdate();
							}
							
						}, {
						
							text: '对齐到网格',
							handler: function(e){
								JDSDesk.getDesktop().shortcuts.handleUpdate();
							}
							
						}]
					}
				}
			return this.IconSort ;
		 },
		 getNewDoc:function(){
		  if (!this.newDoc) {
			  	this.newDoc = {
			  		text: '新建(W)',
			  		menu: [{
			  			text: '新建文件夹',
						//disabled:true,
			  			handler: function(e){
			  			}
			  		}, {
			  			text: '快捷方式',
						//	disabled:true,
			  			handler: function(e){
			  				ModuleManager.creatMdoulePanel('','');
			  			}
			  		}, {
			  			text: '系统连接',
						disabled:true,
			  			handler: function(e){
			  			}
			  		}]
			  	}
			  }
			return this.newDoc ;
		 },
		 
	getBodyContextmenu :function (config){
		 bodyContextmenu = new Ext.menu.Menu({
		            moduleId:config.moduleId,
					items: [
					DesktopAction.getIconSort(),
					{
						
		                text: '刷新桌面(E)',
		                handler: function(e){
						//	JDSDesk.getDesktop().shortcuts.handleUpdate();
						//	JDSDesk.initDesktopConfig();
						window.location.href=window.location.href   
							
						},
	                scope: config.win
		             }, '-',
					 
					 
					 {
							
				                text: '粘帖',
								 disabled:true,
				                handler: function(e){
				                     
								}
				            } ,{
							
				                text: '粘帖快捷方式',
								disabled:true,
				                handler: function(e){
				                     
								}
				     },'-',
					DesktopAction.getNewDoc(),'-',
				//	DesktopAction.getQuickMeun(),'-',
					{
		               
		                text: '控制面板',
		                handler: function(e){
							 JDSDesk.getModule("JDSPreferences").createWindow();
						},
		                scope: config.win
		            },
					 {
		              
		                text: '保存当前设置',
		                handler: function(e){
							 JDSDesk.getDesktop().saveDesk(true);
						},
		                scope: config.win
		            }
					
					]
		        })
		return 	 bodyContextmenu;	
	},
	
	getShortcutContextMenu : function (config){
		shortcutContextMenu =new Ext.menu.Menu({
	            moduleId:config.moduleId,
				items: [{
					 iconCls: 'page',
					moduleId:config.moduleId,
	                text: '打开',
	                handler: function(e){
						openWinById(config.moduleId);
					},
	                scope: config.win
	            }, '-', {
					iconCls: 'page_delete',
					moduleId:config.moduleId,
	                text: '删除',
	                handler: function(e){
					  var selectedItems = JDSDesk.getDesktop().shortcuts.getSelectedItems();
					  var ids=[];
					  selectedItems.each(function(item){
					  	ids.push(item.moduleId);
					  });
					 JDSDesk.getDesktop().removeUserDefModule(ids);  
					},
	                scope: config.win
	            },'-', {
				    icon: '/desktop/widgets/explorer/images/copy.gif',
					moduleId:config.moduleId,
	                text: '复制',
					 disabled:true,
	                handler: function(e){
					 
					},
	                scope: config.win
	            },'-', {
				    icon: '/desktop/widgets/explorer/images/xiangxi.gif',
					moduleId:config.moduleId,
	                text: '发送到',
	                menu:[
							{
			                text: '共享文件',
							 disabled:true,
			                handler: function(e){}
			                
				            },{
							
				                text: '收藏夹',
							    disabled:true,
				                handler: function(e){
				                      ModuleManager.creatMdoulePanel();
								}
				            },{
							
				                text: '我的文档',
								 disabled:true,
				                handler: function(e){}
			              
				            }
						]
	              
	            },'-', {
				    icon: '/desktop/widgets/explorer/images/xiangxi.gif',
					moduleId:config.moduleId,
	                text: '打开方式',
	                menu:[
							{
			                text: '运行',
							disabled:true,
			                handler: function(e){}
			                
				            },{
							
				                text: '新窗口打开',
								 disabled:true,
				                handler: function(e){
				                      ModuleManager.creatMdoulePanel();
								}
				            },{
							
				                text: '打开文件位置',
								 disabled:true,
				                handler: function(e){}
			              
				            }
						]
	              
	            },'-', {
				   icon: '/desktop/widgets/explorer/images/rename.gif',
					moduleId:config.moduleId,
	                text: '重命名',
					 disabled:true,
	                handler: function(e){
					   ModuleManager.showModulePanel(this.moduleId);
					},
	                scope: config.win
	            },'-', {
					 iconCls: 'page_gear',
					moduleId:config.moduleId,
	                text: '属性',
					 disabled:true,
	                handler: function(e){
						  ModuleManager.showModulePanel(this.moduleId);
					},
	                scope: config.win
	            }
				
				]
	        });
		
		return shortcutContextMenu;
		}
		
}

