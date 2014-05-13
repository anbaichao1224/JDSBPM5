/*!
 * Ext JS Library 3.0.0
 * Copyright(c) 2006-2009 Ext JS, LLC
 * licensing@extjs.com
 * http://www.extjs.com/license
 */
 Ext.BLANK_IMAGE_URL = '/js/ext/resources/images/default/s.gif';
Ext.onReady(function(){
	var url = "";
	//alert(rollid);
	var titstr = "案卷列表";
	if(rollid!='null'){
		url = "/roll_rollDetailTree.action?rollbean.uuid="+rollid+"&";
	}else if(cid!='null'){
		url = "/roll_rolllistByCid.action?rollbean.categoryid="+cid+"&";
	}else{
		url = "/roll_treelist.action?";
		 titstr = "未封卷案卷列表";
	}
	
	if(rollid=='applicantpass'){
		url = "/pepodom_applicantpass.action?";
		 titstr = "可查看案卷列表";
	}
	var yearchoice = new Ext.form.ComboBox({
		 mode : 'local',
		 id:'yearnum',
		 width:60,
		 name:'yearnum',
		 triggerAction:"all",
		 hiddenName:'ayearnum',
		 displayField: 'svalue', 
       valueField: 'cname', 
       store: new Ext.data.SimpleStore({ 
           fields: ['svalue','cname'] 
               , data: [ 
                    ['2011','2011'],['2012','2012'] , ['2013','2013'], ['2014','2014'],['2015','2015'],['2016','2016'] , ['2017','2017'], ['2018','2018']
               ] 
       })
	});
	var rollnum = new Ext.form.TextField({
		id:'rollnum',
		name:'rollnum',
		width:80
	});
	var title = new Ext.form.TextField({
		id:'rollname',
		name:'rollname',
		width:100
	});
	
	var root = new Ext.tree.AsyncTreeNode({
			text:'Tasks'
		});
	var loader = new Ext.tree.TreeLoader({
			dataUrl:url+'date='+new Date(),
			baseParams:{yearnum:'',rollnum:''},
			uiProviders:{
				'col': Ext.tree.ColumnNodeUI//Ext.ux.ColumnTreeCheckNodeUI//
			}
		});
	
	var ttbar = new Ext.Toolbar(['案卷名称：',title,'年度：',yearchoice, '案卷号：',rollnum,{// 查询按钮
		id : 'newModelButton',
		text : '查  询',
		cls : "x-btn-text-icon",
		icon: '/desktop/resources/images/search.jpg',
		handler : queryDj
		},
         {
        	 
        	 text:'封卷',
        	 tooltip:'封卷',
        	 id:'fjbtn',
        	 cls : "x-btn-text-icon",
        	 icon: '/desktop/widgets/electronicfile/images/fj_01.png',
        	 listeners : {   
        	 'click' : function() {   
        	 	var selectedItem = tree.getSelectionModel().getSelectedNode();
        	 	if(selectedItem.parentNode.text=='Tasks'){
        	 		createPersonPositionWindow(selectedItem.id);
        	 	}else{
        	 		alert("请选择案卷进行封卷");
        	 	}
         		}   
         	}
         },{
        	 
        	 text:'拆卷',
        	 tooltip:'拆卷',
        	 id:'cjbtn',
        	 cls : "x-btn-text-icon",
        	 icon: '/desktop/widgets/electronicfile/images/cj_01.png',
        	 listeners : {   
        	 'click' : function() {   
        	 //mid = document.getElementById("mettinguuid").value;
        	 var selectedItem = tree.getSelectionModel().getSelectedNode();
        	 if(selectedItem!=null){
        		 if(rollid=='null'&&cid=='null'){
        		 	//alert(selectedItem.parentNode.text);
        			if(selectedItem.parentNode.text!='Tasks'){
        			 Ext.Ajax.request({
        				 url:'/data_updateStatus.action',
        				 method:'post',
        				 params:{dataIds:selectedItem.id,'dbean.rollid':''},
        				 success:function(o){
        					 alert("拆卷成功！");
        					 tree.root.reload();
        				 }
        			 });
        		 	}else{
        		 		alert("请选择公文进行拆卷");
        		 	}
        		 }else{
        			 if(selectedItem.parentNode.text=='Tasks'){
        				 	Ext.Ajax.request({
        				 		url:'/roll_updateStatus.action', 
        				 		method:'post',
        				 		params:{'rollbean.uuid':selectedItem.id,'rollbean.category_uuid':'','rollbean.status':0},
        				 		success:function(){
        				 			alert("拆卷成功");
        				 			tree.root.reload();
        				 		}
        				 	});
        			 }else{
        				 alert("请选择案卷进行拆卷");
        			 }
        		 }
        	 }else{
        	 	alert("请先选择一条数据");
        	 }	
         }   
         }  
         }	 ,{
        	 text:'查看目录信息',
        	 tooltip:'查看目录信息',
        	 id:'ckbtn',
        	 cls : "x-btn-text-icon",
        	 icon: '/desktop/widgets/electronicfile/images/fj_01.png',
        	 listeners : {   
        	 'click' : function() {   
        	 		//alert(cid);
        	 		//window.top.openUrlWin("/desktop/widgets/electronicfile/recordcategory/addCategory.jsp","目录信息");
        	 		//updateMatter("category_findById.action?rbean.uuid="+cid);
        	 	if(cid=='electronicroot'){
        	 			alert("请选择子目录查看信息");
        	 	}else{
        	 		var win=new Ext.Window({
        	 			 id:'categoryInfo',
        	 			 width:450,
        	 		     height:300,
        	 		     title:'信息',
        	 		     closable:true,
        	 			 collapsible:true,
        	 		     autoScroll:true,
        	 		     bodyStyle:'padding:5px 5px',
        	 		     items:new Ext.Panel({
        	 				        title: '',        
        	 					  	bodyStyle:'width:100%;height:100%',
        	 				        html: '<iframe frameborder="0" src="/category_findById.action?rbean.uuid='+cid+'" width="100%" height="100%"></iframe>'
        	 		     })
        	 		 });
        	 		 win.show();
        	 	}
         		}   
         	} 
         },{
        	 text:'新增子目录',
        	 tooltip:'新增子目录',
        	 id:'newbtn',
        	 cls : "x-btn-text-icon",
        	 icon: '/desktop/widgets/electronicfile/images/fj_01.png',
        	 listeners : {   
        	 'click' : function() {   
        	 		//alert(cid);
        	 		//window.top.openUrlWin("/desktop/widgets/electronicfile/recordcategory/addCategory.jsp","目录信息");
        	 		//updateMatter("category_findById.action?rbean.uuid="+cid);
        	 		var win=new Ext.Window({
        	 			 id:'categoryInfo',
        	 			 width:450,
        	 		     height:200,
        	 		     title:'目录信息',
        	 		     closable:true,
        	 			 collapsible:true,
        	 		     autoScroll:true,
        	 		     //bodyStyle:'padding:0px 5px',
        	 		     items:new Ext.Panel({
        	 				        title: '',        
        	 					  	bodyStyle:'width:100%;height:100%',
        	 				        html: '<iframe frameborder="0" src="/desktop/widgets/electronicfile/recordcategory/addCategory.jsp?pid='+cid+'" width="100%" height="100%"></iframe>'
        	 		     })
        	 		 });
        	 		 win.show();
         		}   
         	} 
         }]);
    var tree = new Ext.tree.ColumnTree({
    	id:'roll-columntree',
    	//el:'rolllist',
    	bodyStyle:'width:100%',
        height: 600,
        rootVisible:false,
        autoScroll:true,
        loadMask: true,
        title: titstr,
        //checkModel:'cascade',//级联多选，如果不需要checkbox,该属性去掉
        //onlyLeafCheckable: false,//所有结点可选，如果不需要checkbox,该属性去掉
        root:root,
        loader:loader,
        columns:[{
            header:'案卷标题',
            width:300,
            dataIndex:'name'
        },{
            header:'案卷号',
            width:100,
            dataIndex:'rollnum'
        },{
            header:'年度',
            width:100,
            dataIndex:'yearnum'
        },{
            header:'保管期限',
            width:200,
            dataIndex:'timelimit'
        }  
        ]
    });
    
    new Ext.Viewport({
    	layout: 'border',
    	  items: [
    	          {
    	        	  region:"north",
    	        	  items:ttbar,
    	        	  height:25
    	          },{
    	        	  region:"center",
    	        	  items:tree
    	          }
    	]
    })
    //tree.render(); 
    //tree.expandAll(); 
    tree.on('beforeload', function() {
		this.baseParams = {
				start:0,
				limit:1,
				rollname:title,
				yearnum : yearchoice,
				rollnum:rollnum
		};
		
		
	});
    function queryDj() {
    	/*tree.on('beforeload', function() {
    		alert("cx:"+Ext.get('ayearnum').dom.value);
    		this.baseParams = {
    				yearnum : Ext.get('ayearnum').dom.value
    		};
    		
    	});*/
    	var loader = new Ext.tree.TreeLoader({ 
    		dataUrl:url+'date='+new Date(),
            baseParams:{rollname:Ext.get('rollname').dom.value,yearnum:Ext.get('ayearnum').dom.value,rollnum:Ext.get('rollnum').dom.value},
            uiProviders:{
                'col': Ext.ux.tree.ColumnNodeUI
            }});
        loader.load(tree.root);
        tree.expandAll();

	}
    
    if(rollid=='applicantpass'){
    	Ext.get('fjbtn').hide();
    	Ext.get('cjbtn').hide();
    	Ext.get('ckbtn').hide();
    	Ext.get('newbtn').hide();
    }
    if(rollid!='null'||cid!='null'){
    	Ext.getCmp('fjbtn').disable();
    }
    if(cid=='null'){
    	Ext.get('ckbtn').hide();
    	Ext.get('newbtn').hide();
    }
    
    function tdreload(pstart){
    	  Ext.Ajax.request({
    	   url : contextPath + '/servlets/ShenHeRiZhi_grid_search',
    	   params : {
    	    start : pstart,
    	    limit : 10
    	   },
    	   success : function(response) {
    	    treedata = response.responseText;
    	    realCount = Ext.decode(treedata).totalCount>0?Ext.decode(treedata).totalCount:0;
    	    MaxPagesize = Math.ceil(realCount/10)*10;
    	    treedata = treedata.substring(treedata.indexOf("data")+6,treedata.length-1);
    	    for(var i = 0;i<treedata.length;i++){
    	     if(treedata.charAt(i) == ':' && treedata.charAt(i-1) == '"'){
    	      var f=0;
    	      for(var j=i;j>0&&f<2;j--){
    	       if(treedata.charAt(j) == '"'){treedata= treedata.substring(0,j)+treedata.substring(j+1);f++;i--;}
    	      }
    	     }
    	    }
    	    treload();
    	   },
    	   failure : function() {
    	    Ext.Msg.alert("提示", "连接数据库错误或连接超时！")
    	   }
    	  });
    	 }
});

function createPersonPositionWindow(rollid) {
	var txtCheckValue;
	var txtCheckName;
	var cmodel="cascade";

	
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
				title :'目录列表',
				width :420,
				height:Ext.getBody().getHeight()-100,
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				items:new Ext.tree.TreePanel( {
					id :'forum-tree',
					region :'west',
					title :'',
					split :true,
					width :400,
					height :Ext.getBody().getHeight()-150,
					checkModel:cmodel,
					collapsible :true,
					margins :'0 0 5 5',
					animCollapse :false,
					animate :true,
					collapseMode :'mini',
					rootVisible :false,
					loader :new Ext.tree.TreeLoader( {
						url :'/category_getTree.do?bool=false'
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

								//jsonData = txtCheckValue;
								var node = tree.getSelectionModel().getSelectedNode();
								if (node==null) {
									Ext.MessageBox.alert('消息',
											'请选择要存放的目录 。');
								} else {
									// alert(positionid+positionname);
									var cid = node.id;
									Ext.Ajax.request({
				            			 url:'roll_updateStatus.action',
				            			 method:'post',
				            			 params:{'rollbean.uuid':rollid,'rollbean.categoryid':cid,'rollbean.status':'1'},
				            			 success:function(o){
				            			 alert("封卷成功！");
				            			 Ext.getCmp('roll-columntree').root.reload();
				            		 }
				            		 });
									Ext.getCmp("positionWin")
									.close();
									
									var cname = txtCheckName;
									
									
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
	//tree.setRootNode(root);
	tree.expandAll();
	tree.render();
	tree
	.on(
			'beforeload',
			function(node) {
				tree.loader.url = '/category_getTree.do?treename=person&choose=true&categoryId=' + node.id + '';
			});
}
function updateMatter(url,heightstr){
	
	var win=new Ext.Window({
		 id:'updateRollInfo',
		 width:Ext.getBody().getWidth()-350,
	     height:Ext.getBody().getHeight()-50,
	     title:'信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:5px 5px',
	     items:new Ext.Panel({
			        title: '',        
				  	height:Ext.getBody().getHeight()-100,
				  	bodyStyle:'width:100%',
			        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}
