
IeExplorer={};
IeExplorer.Actions = function(){
   
   
    function sendRequest(action){
    
    }
	function getHistoryMenu(currPath,action){
		var menus=[];
		var index=JDSDesk.openerModules.indexOfKey(currPath);
		var subOpeners=JDSDesk.openerModules;
	
		if (action=='forWord'){
			subOpeners=JDSDesk.openerModules.getRange(index,JDSDesk.openerModules.getCount());
		}else if(action=='goBack'){
			subOpeners=JDSDesk.openerModules.getRange(0,index);
		}else{
			subOpeners=JDSDesk.openerModules.getRange(0,JDSDesk.openerModules.getCount());
		}
			subOpeners.each(function(module){
				 menus.push(module.getMenu());
			})
		return menus;
	}
	
    function doDelete(targets){
     
    }
     return {
        goForward: {
            icon: '/desktop/resources/images/default/ie_left.png',
			disabled:true,
            handler: function(){
            }
        },
        goBack: {
           
              icon: '/desktop/resources/images/default/ie_right.png',
			  disabled:true,
            handler: function(){
            }
        },
        goBackList: {
			  id: 'btnGoback',
	        text: '<div style="display :inline;width : 20px;height:20px"><table><tr><td><img style="display :inline; width:24px;height:24px"  src="/desktop/resources/images/default/ie_left.png" /></td><td><div style="display : inline">后退</div></td></tr></table></div>',
     		menu: new Ext.menu.Menu({
				listeners:{beforeshow:function(){
				var menus=getHistoryMenu(IeExplorer.toolbar.currPath,'goBack');
				this.removeAll();
					menus.each(function(item){
							this.add(item)
					},this);
				}}}
			)
        },
        goForwardList: {
            id: 'btnGoforward',
			text: '<div style="display :inline;width : 20px;height:24px"><table><tr><td><img style="display :inline; width:24px;height:24px"  src="/desktop/resources/images/default/ie_right.png" /></td><td><div style="display : inline">前进</div></td></tr></table></div>',
           menu: new Ext.menu.Menu({
				listeners:{beforeshow:function(){
				var menus=getHistoryMenu(IeExplorer.toolbar.currPath,'forWord');
				this.removeAll();
					menus.each(function(item){
							this.add(item)
					},this);
				}}}
			)
        },
        goParentFolder: {
            id: 'btnParentFolder',
            text: '向上一级',
            icon: '/explorer/codebase/imgs/toolbar/FolderUp.gif',
            cls: 'x-btn-icon',
            tooltip: '<b>向上一级</b><br />转到上一级',
            handler: function(){
                if (parentNode.id == 'Root_Desktop') {
                    return false;
                }
                parentNode.select();
            }
        },
        viewAS: {
            id: 'btnViewAs',
            icon: '/desktop/widgets/explorer/images/programs.gif',
            cls: 'x-btn-text-icon',
            text: '查看方式...',
            menu: {
                items: [{
                    id: 'btnViewAs_thumb',
                    text: '缩略图',
                    icon: '/desktop/widgets/explorer/images/suolvetu.gif',
                    handler: function(){
                        this.setView('thumb');
                        this.refresh();
                    },
                    scope: Explorer.Manager
                }, {
                    id: 'btnViewAs_icon',
                    text: '图标',
                    icon: '/desktop/widgets/explorer/images/table.gif',
                    handler: function(){
                        this.setView('icon');
                        this.refresh();
                    },
                    scope: Explorer.Manager
                
                }, {
                    id: 'btnViewAs_list',
                    text: '列表',
                     icon: '/desktop/widgets/explorer/images/liebiao.gif',
                    handler: function(){
                        this.setView('list');
                        this.refresh();
                    },
                    scope: Explorer.Manager
                }, {
                    id: 'btnViewAs_detail',
                    text: '详细资料',
                    icon: '/desktop/widgets/explorer/images/xiangxi.gif',
                    handler: function(){
                        this.setView('detail');
                        this.refresh();
                    },
                    scope: Explorer.Manager
                }]
            }
        },
        
        
        
        fileAttrib: {
            id: 'btnFile_attrib',
            text: '属性',
			disabled:true,
            iconCls: 'page_attach',
            tooltip: '<b>显示属性</b><br />查看项目的详细属性',
            handler: function(){
                Explorer.Manager.getSelected(function(targets){
                    new Explorer.attribDialog(targets).show();
                });
            }
        },
        fileDelete: {
            id: 'btnFile_delete',
            text: '删除',
			disabled:true,
            iconCls: 'page_delete',
            tooltip: '<b>删除</b><br />删除指定项目',
            handler: function(){
                Explorer.Manager.getSelected(doDelete);
            }
        },
        fileCut: {
            id: 'btnFile_cut',
            text: '剪切',
            icon: '/desktop/widgets/explorer/images/cut.gif',
            cls: 'x-btn-text-icon',
            tooltip: '<b>剪切</b><br />将选定的项目移动到剪贴板',
            handler: function(){
            }
        },
        fileCopy: {
            id: 'btnFile_copy',
            text: '复制',
            cls: 'x-btn-text-icon',
            icon: '/desktop/widgets/explorer/images/copy.gif',
            tooltip: '<b>复制到剪贴板</b><br />将选定的项目复制到剪贴板',
            handler: function(){
            }
        },
        filePaste: {
            id: 'btnFile_paste',
            text: '粘贴',
            cls: 'x-btn-text-icon',
            icon: '/desktop/widgets/explorer/images/paste.gif',
            //icon :  '/ajaxee/resources/icon/file_paste.gif',
            tooltip: '<b>粘贴</b><br />粘贴剪贴板的内容',
            handler: function(){
            }
        },
        copyto: {
            id: 'btnFile_copyto',
            text: '复制到文件夹',
            cls: 'x-btn-text-icon',
            icon: '/desktop/widgets/explorer/images/copyto.gif',
            tooltip: '<b>复制到...</b><br />将选定的项目复制到指定目标',
            handler: function(){
                Explorer.Manager.getSelected(function(targets, fullnames){
                    Explorer.Manager.destinationChooser.show({
                        btnText: '复制到',
                        dlgTitle: '复制到..',
                        targets: targets,
                        dlgHint: (targets.length > 3 ? targets.length + '个项目' : fullnames),
                        fn: this.createDelegate({
                            targets: targets,
                            copy: true
                        })
                    });
                }, this);//scope-->sendRequest
            },
            scope: sendRequest
        },
        moveto: {
            text: '移动到文件夹',
            iconCls: 'page_goto',
            id: 'btnFile_moveto',
            tooltip: '<b>移动到...</b><br />将选定的项目移动到指定目标',
            handler: function(){
                Explorer.Manager.getSelected(function(targets){
                    Explorer.Manager.destinationChooser.show({
                        btnText: '移动到',
                        dlgTitle: '移动到..',
                        dlgHint: 'hihi',
                        fn: this.createDelegate({
                            targets: targets,
                            //copy: false
                            move: true
                        })
                    });
                }, sendRequest);//scope-->A 'escaped' sendRequest
            }
        },
        fileRename: {
            id: 'btnRename',
            text: '重命名',
            cls: 'x-btn-text-icon',
            icon: '/desktop/widgets/explorer/images/rename.gif',
            tooltip: '<b>重命名</b><br />重命名所选的项目',
            scope: Explorer.Manager
        },
        refresh: {
            iconCls: 'page_refresh',
            text: '刷新',
            cls: 'x-btn-text-icon',
            tooltip: '<b>刷新</b><br />刷新当前的内容',
            handler: Explorer.Manager.refresh,
            scope: Explorer.Manager
        },
        selectAll: {
            text: '全选选定',
             icon: '/desktop/widgets/explorer/images/allselect.gif',
            tooltip: '<b>全选选定</b><br />选定窗口中的所有项目',
            handler: Explorer.Manager.selectAll,
            scope: Explorer.Manager
        },
        disSelect: {
            id: 'btnFile_createFolder',
            text: '取消',
             icon: '/desktop/widgets/explorer/images/goback.gif',
            cls: 'x-btn-icon',
            tooltip: '<b>取消</b><br />清除选区',
            handler: Explorer.Manager.clearSelections,
            scope: Explorer.Manager
        },
        /**
         * 排列图标
         */
        sortBy: {
            text: '排列图标',
            icon: '/desktop/widgets/explorer/images/iconlist.gif',
            menu: {
                items: [{
                    id: 'menuItem_thumb',
                    text: '按名称',
                    icon: '/desktop/widgets/explorer/images/mingcheng.gif',
                    handler: function(){
                    }
                }, {
                    id: 'menuItem_icon',
                    text: '按类型',
                    icon: '/desktop/widgets/explorer/images/leixing.gif',
                    handler: function(){
                    }
                }, {
                    id: 'menuItem_list',
                    text: '按大小',
                    icon: '/desktop/widgets/explorer/images/daxiao.gif',
                    handler: function(){
                    }
                }]
            }
        },
        bookMark: {
            text: "编辑",
            id: 'menuItem_bookMark',
            icon: '/desktop/widgets/explorer/images/edit.gif',
            handler: function(){
                var node = Explorer.Manager.getViewSelected()[0];
                if (node == null) 
                    return;
                var param = {
                    fileName: node.path
                };
                JDS.ajax.explorer.Connection.getFileName(node.path,'edit');
                
            }
        },
        uploadFile: {
            text: "上传文件",
            id: 'menuItem_uploadFile',
            
            handler: function(){
            	var target=Explorer.Manager.getViewSelected()[0];
            	var destPath=target.path;
        		alert("upload file ,next into Connection.js ");
       		 	JDS.ajax.explorer.Connection.uploadFile(destPath);
    	
            }
        },
        binding: {
            text: "绑定数据源",
            id: 'menuItem_binding',
            
            handler: function(){
                var node = Explorer.Manager.getViewSelected()[0];
                if (node == null) 
                    return;
				var param = {
                    fileName: node.path
                };	
                JDS.ajax.explorer.Connection.getFileName(node.path,'binding');
            }
        },
        modiflyProfile: {
            text: "显示",
            handler: function(){
                commonFn.popupDialog.call({
                    url: '/deepcms/user/',
                    width: 892,
                    height: 530
                });
            }
        },
        config: {
            text: "设置",
            icon: '/desktop/widgets/explorer/images/setting.gif',
            handler: function(){
                this.view.clearSelections();
            }
        },
        undo: {
            text: "撤销",
            icon: '/desktop/widgets/explorer/images/cancel.gif',
            handler: function(){
                this.view.clearSelections();
            },
            scope: {}
        },
        redo: {
            text: "重做",
            icon: '/desktop/widgets/explorer/images/redo.gif',
            handler: function(){
                this.view.clearSelections();
            },
            scope: {}
        },
        createFolder: {
            id: 'btnFile_createFolder',
            text: '新建文件夹',
            icon: '/desktop/widgets/explorer/images/newdir.gif',
            tooltip: '<b>新建文件夹</b><br />新建文件夹',
            handler: function(){
            }
        },
        upload: {
            text: '新建上传',
            icon: '/desktop/widgets/explorer/images/upload.gif',
            id: 'menuItem_upload',
            handler: function(){
            }
        },
        share: {
            text: '新建共享',
            icon: '/desktop/widgets/explorer/images/net.gif',
            
            id: 'menuItem_setShare',
            handler: function(){
            }
        },
        download: {
            text: '下载(D)',
            icon: '/desktop/widgets/explorer/images/download.gif',
            handler: function(){
            }
        },
        exit: {
            text: '退出(X)',
            icon: '/desktop/widgets/explorer/images/out.gif',
            handler: IeExplorer.logout
        },
        
        about: {
            text: '关于(A)',
            icon: '/desktop/widgets/explorer/images/about.gif',
            handler: function(){
                alert(' JDS Explorer 3.0 ');
            }
        },
         bjxwnet: {
            text: '宣武网站',
            icon: '/desktop/widgets/explorer/images/setting.gif',
            handler: function(){
                openWin("http://www.bjxw.gov.cn");
            }
        },
         ygyx: {
            text: '遥感影像',
            icon: '/desktop/widgets/explorer/images/allselect.gif',
            handler: function(){
                openWin("http://172.26.15.24:8888/MapPublisher/login.do?account=xw04&pass=xw04");
            }
        },
         xfyf: {
            text: '学法用法',
            icon: '/desktop/widgets/explorer/images/about.gif',
            handler: function(){
                openWin("http://10.163.110.24:1011/law/home/index.cbs");
            }
        },
         xhsxx: {
            text: '新华社信息',
            icon: '/desktop/widgets/explorer/images/daxiao.gif',
            handler: function(){
                openWin("http://10.163.110.24:8001/");
            }
        },
         abjjxx: {
            text: '安邦经济信息',
            icon: '/desktop/widgets/explorer/images/setting.gif',
            handler: function(){
                openWin("http://10.163.110.56/riseinfo/classinfo/classinfoframe.jsp?Id=RSN=安邦经济信息,RSN=区政府信息,RSN=信息发布,RSN=服务项目,o=宣武区");
            }
        },
         dzts: {
            text: '电子图书',
            icon: '/desktop/widgets/explorer/images/copyto.gif',
            handler: function(){
                openWin("http://10.163.110.24:5004/bookhtm/index.asp");
            }
        },
         spdb: {
            text: '视频点播',
            icon: '/desktop/widgets/explorer/images/edit.gif',
            handler: function(){
                openWin("http://10.163.110.13/");
            }
        },
         wdzx: {
            text: '文档中心',
            icon: '/desktop/widgets/explorer/images/about.gif',
            handler: function(){
                openWin("http://10.163.29.90/");
            }
        },
         zhjj: {
            text: '中宏经济',
            icon: '/desktop/widgets/explorer/images/xiangxi.gif',
            handler: function(){
                openWin("http://10.163.110.24:85/");
            }
        },
         dzyj: {
            text: '电子邮件',
            icon: '/desktop/widgets/explorer/images/xiangxi.gif',
            handler: function(){
                openWin("http://61.49.16.163/");
            }
        },
         yqlj: {
            text: '友情链接',
            icon: '/desktop/widgets/explorer/images/suolvetu.gif',
            handler: function(){
                openWin("http://10.163.110.56/login/nds/more/index.jsp?stat=ggfu");
            }
        }
        ,
         saveAsDesk: {
            text: '保存到桌面快捷方式',
            icon: '/desktop/widgets/explorer/images/programs.gif',
            handler: function(){
                //URI 内容
				
				var uriValue = IeExplorer.toolbar.URIbar.getValue();
				var module=JDSDesk.getModule(IeExplorer.toolbar.currPath);
				if (!module){
			         ModuleManager.creatMdoulePanel(uriValue,uriValue);
				}else{
				  JDSDesk.getDesktop().addShortcut(module.id,true);	
				  JDSDesk.getDesktop().saveDesk();
				}
				
					
				
            }
        },
         addToFavorites: {
            text: '添加到收藏夹',
            icon: '/desktop/widgets/explorer/images/programs.gif',
            handler: function(){
				
                
            }
        }
		
    };
};
