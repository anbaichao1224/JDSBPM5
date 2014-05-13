
// 新增按钮触发事件
function addmatter(){
	var hylx = document.getElementById("hylx").value;
	if(hylx==""){
		alert("请先选择会议类型！");
	}else{
		updateMatter('addmatter.jsp');
	}
}

// 展示常用事项
	function showmatter(){
		var mtype = document.getElementById("hylx").value;
		Ext.Ajax.request({
			url: "bpmmatter.action",
			params: {mtype:mtype},
			method: "post",
			success: function(resp,opts){
				var respText = Ext.util.JSON.decode(resp.responseText);
				var showdiv = document.getElementById("cymatter");
				showdiv.innerHTML="";
				for(var i=0;i<respText.length;i++){
					showdiv.innerHTML +="<div id='m"+respText[i].uuid+"'><a href=javascript:void(); onclick=\"matterinfodel('"+respText[i].uuid+"','matter')\" class=\"formisnull\">[删除]</a>&nbsp;&nbsp;<a href=javascript:void(0) onclick=\"updateMatter('bpmmatter.action?uuid="+respText[i].uuid+"');return false;\">"+respText[i].sxmc+"</a></div>";
				}			
				
			}
		});
	}
	
	// 事项删除
	function matterinfodel(mid,deltype){
		
		if(deltype=="matter"){
			document.getElementById("m"+mid).innerHTML="";
			document.getElementById("m"+mid).style.display="none";
		}else{
			Ext.Ajax.request({
				url:'matterinfo_matterDelete.action',
				params:{uuid:mid,delstatus:'Y'},
				method:'post',
				success:function(){
				// 删除
				document.getElementById(mid).innerHTML="";
				document.getElementById(mid).style.display="none";
				var mids = document.getElementById("matterids").value;
				var midlist = mids.split(",");
				var ids = "";
				for(var i=0;i<midlist.length;i++){
					if(midlist[i]==mid){
						continue;
					}
					ids += midlist[i]+",";
				}
				ids = ids.substring(0,ids.lastIndexOf(","));
				
				// var mids = document.getElementById("matterids").value;
				// mids.substringmids.indexOf(mid);
				},
				failure:function(){
					alert("删除失败！！");
				}
			});
		}
	}
	



// 事项办结
function banjie(){
    	var id = document.getElementById("uuid").value;
    	// location.href='matterinfo_matterBj.action?';
    	Ext.Ajax.request({
    	
    		url:"matterinfo_matterBj.action",
    		params:{uuid:id,blstatus:'2'},
    		method:"post",
    		success:function(){
    			alert("已办结,请刷新");
    			document.getElementById("bj").style.display="none";
    		},
    		failure:function(){
    			alert("暂时无法办结请刷新重试");
    		}
    	});
    }
// 重新办理
function matterrebl(){
    	var id = document.getElementById("uuid").value;
    	var personid = document.getElementById("personid").value;
    	var personname = document.getElementById("personname").value;
    	Ext.Ajax.request({
    	
    		url:"matterinfo_matterrebl.action",
    		params:{uuid:id,personid:personid,personname:personname},
    		method:"post",
    		success:function(){
    			alert("已修改为重新办理");
    		},
    		failure:function(){
    			alert("暂时无法办结请刷新重试");
    		}
    	});
    }

// 流程列表
function queryProcess(){
	var personid = document.getElementById("personid").value;
	Ext.Ajax.request({
		url:'matterinfo_getModule.action',
		params:{personid:personid},
		method:"post",
		sync:true,
		success:function(resp,opts){
			var respText = Ext.util.JSON.decode(resp.responseText);
			for(var i=0;i<respText.length;i++){
				var opt = new Option(respText[i].name,respText[i].id);
				document.getElementById("processdefid").options.add(opt);
				if(respText[i].id==processid){
					document.getElementById("processdefid").value=processid;
					document.getElementById("processdefid").text=respText[i].name;
				}
			}
		}
	});
}

// 查看办理情况
/*
 * function detail(){ //location.href='getHistory.action?processInstId=<ww:property
 * value="matterInfoListBean.activityinstid"/>'; window.top.prodetail('<ww:property
 * value="matterInfoListBean.activityinstid"/>'); }
 */
// 表单验证
function fcheck(sarray){
	var msg = "";
	for(var i=0;i<sarray.length;i++){
			var val = document.getElementById(sarray[i][0]).value;
			if(val==null||val==""){
				msg+=sarray[i][1]+"\n";
			}
	}
	if(msg!==""){
		alert(msg);
		return false;
	}
	return true;
}

function fvalidate(formname)
{   
	
	var v = "";
	 for(var   i=0;i<eval("document."+formname+".length");i++)  
	{ 
		 v = eval("document."+formname+".item(i).name"); 
		 
		 if(eval("document."+formname+".item(i).value")==""&&eval("document."+formname+".item(i).type")!="hidden"){
		 	alert("表单信息填写不完整");
		 	return false;
		 }
	}
	 	var sxkssj = document.getElementById("hykssj").value;
		var sxjssj = document.getElementById("hyjssj").value;
		if(sxjssj<sxkssj){
			alert("结束时间不能早于开始时间");
			return false;
		}
		var matterdiv = document.getElementById("matterids").value;
		if(matterdiv==""){
			alert("您还没有添加事项，或是您使用的常用事项信息没有填写完整！");
			return false;
		}
	 return true;
}

function form_validate(sarray,formname){
	if(formname!=""){
		return fvalidate(formname);
	}else{
		return fcheck(sarray);
	}
}



// 人员窗体
function createPersonPositionWindow(positionid, positionname) {
	var txtCheckValue;
	var txtCheckName;
	var sxlx = document.getElementById("sxlx").value;
	var cmodel="cascade";
	if(sxlx=="1"){
		cmodel="single";
	}
	var nid = document.getElementById("personid").value;
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
				title :'人员列表',
				width :220,
				
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				items :new Ext.tree.TreePanel( {
					id :'forum-tree',
					region :'west',
					title :'',
					split :true,
					width :200,
					height :510,
					checkModel:cmodel,
					collapsible :true,
					margins :'0 0 5 5',
					animCollapse :false,
					animate :true,
					collapseMode :'mini',
					rootVisible :false,
					loader :new Ext.tree.TreeLoader( {
						url :'/usm/orgtreeJson.do'
					}),
					rootVisible :true,
					lines :true,
					autoScroll :true,
					root :root

				}),

				buttons : [
						{
							text :'确定',
							handler : function() {
								var jsonData = "";

								jsonData = txtCheckValue;
							
								if (jsonData.length == 0) {
									Ext.MessageBox.alert('消息',
											'请在以下人员中选择您要添加到此岗位人员 。');
								} else {
									// alert(positionid+positionname);
									
									Ext.getCmp("positionWin")
									.close();
									var cname = txtCheckName;
									document.getElementById("personname").value=cname;
									document.getElementById("personid").value=txtCheckValue;
									if(sxlx=="1"){
										queryProcess();
									}
								}
							}
						}, {
							text :'取消',
							handler : function() {
								win.close();
							}
						} ]

			});

	win.show();

	var tree = Ext.getCmp('forum-tree');
	tree.setRootNode(root);
	tree.expandAll();
	tree.render();
	tree
			.on(
					'beforeload',
					function(node) {
						tree.loader.url = '/usm/orgtreeJson.do?name=person&choose=true&childOrgId=' + node.id + '';
					});

	tree.on('checkchange', function(node, checked) {
		node.expand();
		// alert("schice:"+node.id);
		node.attributes.checked = checked;
		var parentNode = node.parentNode;
		if (checked) {
			if (parentNode != null && parentNode.id != 'root') {
				// 如果是选中,把父节点保持选中状态
			parentNode.ui.toggleCheck(true);
			txtCheckValue = tree.getChecked('id');
			txtCheckName = tree.getChecked('text');
		}
	} else {
		// 如果所有子节点都没选中，取消根节点选中状态
			if (parentNode != null && parentNode.id != 'root') {
				var chk = false;
				parentNode.eachChild( function(child) {
					if (child.attributes.checked)
						chk = true;
				});
				parentNode.ui.toggleCheck(chk);
				parentNode.attributes.checked = chk;
			}
		}
		node.eachChild( function(child) {
			child.ui.toggleCheck(checked);
			child.attributes.checked = checked;
			child.fireEvent('checkchange', child, checked);
		});
		/*
		 * if(!checked){ return; }
		 */
		if(this.checkModel=='single'){    
            // var nodes =this.getNodes(this);
            var startNode = this.getRootNode();    
            var nodes = [];    
            var f = function(){    
            	nodes.push(this);    
            };    
       
            startNode.cascade(f);       
            if(nodes && nodes.length>0){    
                for(var i=0,len=nodes.length;i<len;i++){    
                    if(nodes[i].id!=node.id){    
                        if(nodes[i].getUI().checkbox){    
                            nodes[i].getUI().checkbox.checked = false;    
                                nodes[i].attributes.checked=false;    
                        }     

                 
   }    
                }    
            }    
        }    
		
		txtCheckValue = tree.getChecked('id');
		txtCheckName = tree.getChecked('text');
	}, tree);
	getNodes:function(treePanel){    
        
	}
}

// 会议窗体
function createMettingWindow() {
		
		var root = new Ext.tree.AsyncTreeNode({
		    text: '会议列表',
		    leaf : false,
		    cls : 'folder',
		    draggable:false,
		    id:'toproot'
		    });
		    
		    
		     var Tree = Ext.tree;
		    var treePanel = new Tree.TreePanel({
		  
		    animate:true,
		    enableDD:false,
		    loader: new Tree.TreeLoader({dataUrl:''}),
		    lines: true,
		    selModel: new Ext.tree.MultiSelectionModel(),
		    containerScroll: true,
		    autoHeight:true,
		    rootVisible:false
		    });
		    
		    var treePanel= new Ext.tree.TreePanel({
	                id:'forum-tree',
	                region:'west',
	                title:'会议列表',
	                split:true,
	                width: 225,
	                minSize: 175,
	                maxSize: 400,
	                collapsible: true,
	                margins:'0 0 5 5',
					      animCollapse:false,
					      animate: true,
					      collapseMode:'mini',
					       rootVisible:false,
	                loader: new Ext.tree.TreeLoader({
									url: '/metting_mettingTreeSuccess.action'
			}),
	                rootVisible:true,
	                lines:true,
	                autoScroll:true,
	                root:root
	            });
	     var viewport = new Ext.Viewport({
	        layout:'border',
	        items:[
	          
	           treePanel,
	             new Ext.Panel({
	             
	                            region:'center',
	                            id:'topic-grid',
	                            html:'<iframe id="views" frameborder="0" src="/usm/person/personqueryListgrid.jsp" height=100% width=100%></iframe>'
	                           
	             })
	         ]
	    });	 
	    var tree =treePanel;
	    tree.setRootNode(root);
		tree.render();
	    var sm = tree.getSelectionModel();
		tree.on('click', function(node){
		   if(node.leaf){
		   
	       Ext.get('views').dom.src='/bpmmatter_matterInfoById.do?uuid='+node.id;
		   }
	     });
	    tree.on('beforeload', function(node){
	    	
		  tree.loader.url = 'metting_mettingTreeSuccess.do?childOrgId='+node.id+'';
		});

	   
}



// 待办列表

function createMettingLbWindow(sxstatus) {
	
	var root = new Ext.tree.AsyncTreeNode({
	    text: '组织机构',
	    leaf : false,
	    cls : 'folder',
	    draggable:false,
	    id:'toproot'
	    });
	    
	    
	     var Tree = Ext.tree;
	    var treePanel = new Tree.TreePanel({
	  
	    animate:true,
	    enableDD:false,
	    loader: new Tree.TreeLoader({dataUrl:'/mettingLbTree.action?sxstatus='+sxstatus}),
	    lines: true,
	    selModel: new Ext.tree.MultiSelectionModel(),
	    containerScroll: true,
	    autoHeight:true,
	    rootVisible:false
	    });
	    
	    var treePanel= new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'会议列表',
                split:true,
                width: 225,
                minSize: 175,
                maxSize: 400,
                collapsible: true,
                margins:'0 0 5 5',
				      animCollapse:false,
				      animate: true,
				      collapseMode:'mini',
				       rootVisible:false,
                loader: new Ext.tree.TreeLoader({
								url: '/mettingLbTree.action?sxstatus='+sxstatus
		}),
                rootVisible:true,
                lines:true,
                autoScroll:true,
                root:root
            });
     var viewport = new Ext.Viewport({
        layout:'border',
        items:[
          
           treePanel,
             new Ext.Panel({
             
                            region:'center',
                            id:'topic-grid',
                            html:'<iframe id="views" frameborder="0" src="/desktop/widgets/metting/mettingListgrid.jsp" height=100% width=100%></iframe>'
                           
             })
         ]
    });	 
    var tree =treePanel;
    tree.setRootNode(root);
	tree.render();
    var sm = tree.getSelectionModel();
    tree.on('contextmenu',rightMenu);
	tree.on('click', function(node){
		Ext.getCmp('topic-grid').show();
	   if(node.leaf){
	   
       Ext.get('views').dom.src='/bpmmatter_matterInfoById.do?uuid='+node.id+'&sxstatus='+sxstatus;
	   }
	  // alert("nodeid:"+node.parentNode.id);
	   if(node.parentNode!=null&&node.parentNode.id=='toproot'){
		   Ext.get('views').dom.src='/metting_getById.do?childOrgId='+node.id;
	   }
     });
    tree.on('beforeload', function(node){
	  tree.loader.url = 'mettingLbTree.do?childOrgId='+node.id+'&sxstatus='+sxstatus;
	});

   
}




// 生成节点的右键菜单
function rightMenu(node,event,sxstatus){
	var menu = new Ext.menu.Menu({
		  id:'righclick',
		  items:[{
	            id:"rMenu",
	            text:"删除",
	            handler:function(item){
	            	 Ext.Ajax.request({ 
						method:'get', 
						url:"matterinfo_matterDelete.action?delstatus=Y&uuid="+node.id, 
						success : function(result, action)
									{ 
										Ext.getCmp('forum-tree').root.reload();
									Ext.MessageBox.alert('消息', '成功!')
								  	}, 
					    failure : function(form, action) 
					    { Ext.MessageBox.alert('消息', '系统初始化失败!');} 
					}); 
					// left_tree_refresh();
	            }
	  }]
	  });
	
	if(node.leaf&&(sxstatus!='3'&&sxstatus!='5')){
		event.preventDefault();
		menu.showAt(event.getXY());
	}
}
 

function newceshi(){
	
	var fsf = new Ext.FormPanel({
		 	labelWidth: 90, 
			labelAlign: 'right',  
			fileUpload: true,  
			title: '',    
			buttonAlign:'center',    
			bodyStyle:'padding:0px 0px 0',   
			anchor:'100%',  
			frame:true,    
		    items: [{
		        collapsible: true,
		        checkboxToggle:false,
		        collapsed: true,
		        xtype:'fieldset',
		        title:'基本信息',
		        autoHeight:true,
		        collapsed:false,
		        width:810,
		        height:670,
		        checkboxName:'jiben',
		        layout:'column',
		        border:true,
		        labelSeparator:'：',
		        fileUpload:true,
		        html:'<iframe frameborder="0" src="/desktop/widgets/metting/addmatter.jsp" width="754" height="630"></iframe>'
		    }]
	});
	fsf.render(document.body);
}

function updateMatter(url){
	
	var win=new Ext.Window({
		 id:'updateMatterInfo',
	     width:790,
	     height:700,
	     title:'事项信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:0 5px',
	     items:new Ext.Panel({
			        title: '',        
				  	height:620,
				  	width:755,
			        html: '<iframe frameborder="0" src="'+url+'" width="754" height="620"></iframe>'
	     })
	 });
	 win.show();
}
