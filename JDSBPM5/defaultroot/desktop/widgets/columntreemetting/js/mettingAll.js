
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

// 事项办结
function banjie(){
    	var id = document.getElementById("tid").value;
    	Ext.Ajax.request({
    	
    		url:"mettingbl_matterBj.action",
    		params:{tid:id,blstatus:'2'},
    		method:"post",
    		success:function(o){
    			alert("已办结,请刷新");
    			document.getElementById("bj").style.display="none";
    		},
    		failure:function(){
    			alert("暂时无法办结请刷新重试");
    		}
    	});
    }
    //重新办理
 	function matterbl(){
  		document.getElementById("rebl").style.display="none";
  		document.getElementById("savematter").style.display="";
  		document.getElementById("savematter").value="提交";
  		document.updateForm.action="mettingbl_updateInfo.action";
  		
  	} 
//更改事项类型
function sxlxchange(){
 	if(document.getElementById("sxlx").value=="1"){
 		document.getElementById("processdefid").disabled=false;
 		document.getElementById("processtr").style.display="block";
 		document.getElementById("sxfj").style.display="none";
 	}else{
 		document.getElementById("processdefid").disabled=true;
 		document.getElementById("processtr").style.display="none";
 		document.getElementById("sxfj").style.display="block";
 	}
 	document.getElementById("personname").value="";
 	document.getElementById("personid").value="";
 	parent.Ext.get('updateMatterInfo').height='600';
 }
//办理人启动流程
function startprocess(){
		var sxid = document.getElementById("tid").value;
		var sxlx = document.getElementById("sxlx").value;
		Ext.Ajax.request({
			url:"saveBlInfo.action",
			params:{tid:sxid,sxlx:sxlx},
			method:"post",
			success:function(){
				window.top.startProcess(sxid,processid);
			}
		});
}
//保存到数据库
function savemetting(formname){
if(form_validate('',formname)){
		Ext.Ajax.request({
			url:'treemetting_savesinglmetting.action',
			method:'post',
			form:formname,
			success:function(o){
					
					parent.Ext.getCmp('form-columntree').root.reload();
					parent.Ext.getCmp('updateMatterInfo').close();
					
			}
		});
		}else{
			return false;
		}
}


function winclose(){
	var win =parent.Ext.getCmp('updateMatterInfo');
			win.close();
}
/*function updateMatter(url){
	
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
}*/
