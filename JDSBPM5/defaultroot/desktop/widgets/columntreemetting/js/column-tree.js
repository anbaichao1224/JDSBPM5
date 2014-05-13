/*!
 * Ext JS Library 3.0.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
Ext.onReady(function(){
	
    var tree = new Ext.ux.tree.ColumnTree({
    	id:'form-columntree',
    	el:'mettingdetail',
    	bodyStyle:'width:100%',
        height: 600,
        rootVisible:false,
        autoScroll:true,
        loadMask: true,
        title: '会议信息',
       loader: new Ext.tree.TreeLoader({
           dataUrl:'treemetting_treelist.action?liststatus='+liststatus+'&mettinguuid='+mid+'&date='+new Date(),
           uiProviders:{
               'col': Ext.ux.tree.ColumnNodeUI
           }
       }),

       root: new Ext.tree.AsyncTreeNode({
           text:'Tasks'
       }),
       tbar:[
             {
            	 text:'新增',
            	 tooltip:'新增',
            	 id:'newmettingbtn',
            	 listeners : {   
            	 'click' : function() {   
            	 //mid = document.getElementById("mettinguuid").value;
            	 updateMatter('mtype_getAll.action?liststatus='+liststatus+'&mettinguuid='+mid+'&date='+new Date()); 
            	 
             		}   
             	}  
             },{
            	 
            	 text:'保存模板',
            	 tooltip:'保存为模板',
            	 id:'savemodelbtn',
            	 listeners : {   
            	 'click' : function() {   
            	 //mid = document.getElementById("mettinguuid").value;
            	 url = 'treemetting_savemodelbymetting.action?&liststatus='+liststatus+'&optionnum=2&mettinguuid='+mid+'&date='+new Date();
            	 Ext.Ajax.request({
            		 url:url,
            		 method:'post',
            		 success:function(o){
            		 alert("保存成功！");
            	 	}
            	 });
            	 
             }   
             }  
             }],
        columns:[{
            header:'信息名称',
            width:300,
            dataIndex:'name'
        },{
            header:'开始时间',
            width:100,
            dataIndex:'kssj'
        },{
            header:'结束时间',
            width:100,
            dataIndex:'jssj'
        },{
            header:'办理人',
            width:200,
            dataIndex:'blrmc'
        },{
            header:'办理状态',
            width:100,
            dataIndex:'webblstatus'
        },{
        	header:'办理情况',
            width:150,
            dataIndex:'zxblqk'
        },{
        	header:'id',
        	//hidden:'true',
        	display:false,
        	width:0,
        	dataIndex:'tid'
        }  
        ]
    });
    tree.render(); 
    tree.expandAll(); 
    if(liststatus!=''&&liststatus!='10'){
		
		Ext.getCmp('newmettingbtn').hide();
		
		Ext.getCmp('savemodelbtn').hide();
	}else if(liststatus==''){
		Ext.getCmp('savemodelbtn').hide();
		if(lstatus!='null'){
			Ext.getCmp('newmettingbtn').hide();
		}
	}else if(liststatus=='10'){
		if(lstatus=='null'){
			Ext.getCmp('savemodelbtn').hide();
		}else{
			Ext.getCmp('newmettingbtn').hide();
		}
	}
    tree.on('contextmenu',mettingrightMenu);
});

//生成节点的右键菜单
function mettingrightMenu(node,event){
	var menu = null;
	var menu = new Ext.menu.Menu({
		  id:'righclick',
		  items:[{
	            id:"mettingrMenu",
	            text:"新增会议阶段",
	            handler:function(item){
			  		updateMatter('/desktop/widgets/columntreemetting/createMetting.jsp?adddirection=1&mettinguuid='+mid+'&liststatus='+liststatus);
					// left_tree_refresh();
	            }
		  },{
			  id:"newMatter",
	            text:"新增事项",
	            handler:function(item){
			  		updateMatter('/desktop/widgets/columntreemetting/addmatter.jsp?parentid='+node.attributes.tid+'&adddirection=2&&mettinguuid='+mid+'&liststatus='+liststatus);
					// 
	            }
		  },{
			  id:'updatemetting',
			  text:'查看',
			  handler:function(item){
			  	//alert(liststatus);
			  	var direction = 1;
			  	var heightstr = 720;
			  	if(node.parentNode.text=="Tasks"){
		  			direction = 3;
		  			heightstr = 680;
		  		
			}else{
				if(node.leaf&&node.parentNode.parentNode.text!='Tasks'){
					direction = 2;
					heightstr = 680;
				}
			}
			  	updateMatter('treemetting_getFromSession.action?adddirection='+direction+'&liststatus='+liststatus+'&mettinguuid='+mid+'&tid='+node.attributes.tid,heightstr);
		  	}
		  },{
			  id:"delMatter",
	            text:"删除",
	            handler:function(item){
			  		//alert(liststatus);
			  		var delstatus='';
			  		var optionnum = 1;
			  		if(liststatus>3&&liststatus<7){
			  			delstatus='N';
			  		}else{
			  			delstatus='Y';
			  			if(liststatus==''){
			  				optionnum = 2;
			  			}
			  		}
			  		var addir = 2;
			  		if (node.parentNode.parentNode.text == "Tasks") {
			  			addir = 1;
			  		}
			  		
			  		url='mettingbl_matterDelete.action';
			  		Ext.Ajax.request({
			  			url:url,
			  			params:{delstatus:delstatus,tid:node.attributes.tid,adddirection:addir,mettinguuid:mid,optionnum:optionnum},
			  			method:'post',
			  			success:function(){
			  				Ext.getCmp('form-columntree').root.reload();
			  				Ext.MessageBox.alert('消息', '成功!');
			  			}
			  		});
			  		
	            }
		  }]
	  });
		
	event.preventDefault();
	menu.showAt(event.getXY());
			
	if (node.parentNode.text == "Tasks") {
		Ext.getCmp('newMatter').disable();
		Ext.getCmp('delMatter').disable();
	} else {
		if (!node.leaf) {
			if (node.parentNode.text != "Tasks") {

				Ext.getCmp('mettingrMenu').disable();
				if(!(liststatus==''||liststatus>6)){
					Ext.getCmp('delMatter').disable();
					
				}
			}
		}
		if (node.leaf) {
			
			Ext.getCmp('mettingrMenu').disable();
			if (((liststatus >= 0 && liststatus < 4) || liststatus == 5)
					&& (liststatus != '' && liststatus != '10')) {
				Ext.getCmp('delMatter').disable();

			}
			if (node.parentNode.parentNode.text != "Tasks") {
				Ext.getCmp('newMatter').disable();
				if(webblstatus!="未办理"){
					Ext.getCmp('delMatter').disable();
				}
			}/*else{
				Ext.getCmp('delMatter').disable();
			}*/
		}
	}
			if(liststatus!=''&&(liststatus>=0&&liststatus<7)){
				Ext.getCmp('mettingrMenu').disable();
				Ext.getCmp('newMatter').disable();
			}
}

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
	
	
		event.preventDefault();
		menu.showAt(event.getXY());

}
function detail(processinstid,activityinstid){
		window.top.prodetail(processinstid,activityinstid);
}
function updateMatter(url,heightstr){
	
	var win=new Ext.Window({
		 id:'updateMatterInfo',
		 width:870,
	     height:750,
	     title:'事项信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:5px 5px',
	     items:new Ext.Panel({
			        title: '',        
				  	height:700,
				  	bodyStyle:'width:100%',
			        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}
