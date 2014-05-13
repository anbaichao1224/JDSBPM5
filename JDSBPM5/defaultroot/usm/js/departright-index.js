//Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){

    Ext.QuickTips.init();
function showPersonPosition(){
	var txtCheckValue;
 	var win;
        if(!win){
        	
            win = new Ext.Window({
                id:'win1',
                title:'修改密码',
                width: 300,
                height:180,
                x:140,
                y:50,
                shim:true,
                animCollapse:true,
                constrainHeader:true,
                layout: 'fit',
                items:fsf       
            });
        }
        win.on("beforedestroy",function(obj){		
	        		window.location="/usm/depart-index.jsp"; 
	        		return false;	
	    });
	    win.show();
	         
    }

var fsf = new Ext.FormPanel({
        labelWidth: 80,
        title: '',
        labelAlign: 'right',
        buttonAlign:'center',    
		bodyStyle:'padding:8px',    
		anchor:'100%',  
		frame:true, 
        items: [
                  {                
		               id:'userid',
		               xtype:'textfield',                
		               fieldLabel: '用户账号',                
		               name: 'personaccount.userid',
		               allowBlank:false,//不允许为空
		               blankText:'用户账号不能为空',//错误提示内容  
		               regex:/^[a-zA-Z]+$/, 
          			   regexText: '只能输入英文字母', 
                       invalidText:'用户账号已经被注册！' ,
                       anchor:'82%'      
	                   },
                       {
                          fieldLabel: '密码',
                          xtype:'textfield',
                          id: 'new_pass',
                          name: 'new_pass',
                          inputType: 'password',
                          allowBlank: false,
                          blankText: '密码不能为空',
                          regex:/^[A-Za-z]{1}([A-Za-z0-9]|[._]){5,19}$/,
                          regexText:'密码由6-20位的字母、数字、下划线、点“.”组成并且首字符为字母',
                          anchor:'82%'
                                    
                       }, {
                          fieldLabel: '重复密码',
                          xtype:'textfield',
                          id: 'new_pass_rp',
                          name: 'new_pass_rp',
                          inputType: 'password',
                          allowBlank: false,
                          invalidText:'两次密码不一致！',                                          
                          validator: function(){//这里自定义函数验证密码是否一致
                          if (
                            fsf.form.findField("new_pass").getValue() == fsf.form.findField("new_pass_rp").getValue()) 
                            return true;
                            else 
                            return false;
                            },
                          blankText: '密码不能为空',
                          anchor:'82%'
                          }
		            ],
 buttons: [{
            text: '修改',
            handler: function(){
             
                if(fsf.form.isValid()) {
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        url: 'usm/departModify.do?',
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "修改成功！");
                           
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据修改失败！");
                            this.disabled=false;
                        }
                    });
                    
                }
            }
        },{
            text: '返回',
            handler: function() {
               window.location="depart/departqueryListgrid.jsp";  
            }
        }
        ]

    });

    function onItemClick(item){
    	if(item.id=="person"){
       	location ="person-index.jsp";
      }else if(item.id=="depart"){
      	location ="depart-index.jsp?type=manager";
      }else if(item.id=="extsys"){
      	location ="/usmlogin.jsp";
      }else if(item.id=="modifypass"){
      	location ="role-index.jsp";
      }else if(item.id=="role"){
      	location ="role-index.jsp";
      }else if(item.id=="usergroup"){
      	location ="usergroup-index.jsp";
      }else if(item.id=="position"){
      	location ="position-index.jsp";
      }else if(item.id=="duty"){
      	location ="duty-index.jsp";
      }else if(item.id=="dutylevel"){
      	location ="dutylevel-index.jsp";
      }else if(item.id=="resources"){
      	location ="resources-index.html";
      }else if(item.id=="system"){
      	Ext.get('views').dom.src= "resources/systemInsert.jsp";
      }else if(item.id=="apply"){
      	Ext.get('views').dom.src= "resources/applyInsert.jsp";
      }else{
      	alert(item.text);	
      }
    }
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
		    loader: new Ext.tree.TreeLoader({
					url: '/usm/orgtreeJson.do'
			}),
		    lines: true,
		    selModel: new Ext.tree.MultiSelectionModel(),
		    containerScroll: true,
		    autoHeight:true,
		    rootVisible:false
	    });
	    var treePanel= new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'部门管理中心',
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
								url: '/usm/orgtreeJson.do'
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
	                            html:'<iframe id="views" frameborder="0" src="depart/departqueryListgrid.jsp" height=100% width=100%></iframe>'
	                           
	             })
	         ]
	    });
	 
    var tree =treePanel;
    tree.setRootNode(root);
	tree.render();
    tree.on('beforeload', function(node){
	   tree.loader.url = '/usm/orgtreeJson.do?name=org&childOrgId='+node.id+'';
	});
    tree.on('click', function(node){
	   if(node.id=='toproot' || node.id=='root'){
	        nodeid=node.id;
	   		tree_refresh();
	   		Ext.get('views').dom.src= 'depart/departqueryListgrid.jsp?childOrgId='+nodeid+'';
	   }else{
      	  nodeid=node.id;
		  	Ext.get('views').dom.src='/usm/rightTemplate.do?template=org&sysid='+sysid+'&nodeid='+node.id;
      }	
    });
});

//刷新树
function tree_refresh(){
var tree = Ext.getCmp('forum-tree');
tree.body.mask('加载中...','x-mask-loading');
tree.root.reload();
tree.root.collapse(true,true);
setTimeout(function(){
   tree.body.unmask();
   tree.root.expand(false,false);
},100);
};


//刷新树
function left_tree_refresh(){
var tree = Ext.getCmp('forum-tree');
tree.body.mask('加载中...','x-mask-loading');
tree.root.reload();
tree.root.collapse(true,true);
setTimeout(function(){
   tree.body.unmask();
   tree.root.expand(false,false);
},100);
};
//加载异步树   
function tree_refreshSystem(sysid) {   
    var tree = Ext.getCmp("forum-tree");   
     tree.getLoader().url="/usm/systemtreeJson.do?id="+sysid;
	tree.body.mask('加载中...','x-mask-loading');
     tree.getRootNode().loaded = false;///因数据源改变，设置loaded属性为false，从而让树的内容更新
     setTimeout(function(){
	   tree.body.unmask();
	   tree.root.expand(false,true);
	},100); 
}   


function competenceCreate(){
		 var win;
        if(!win){
            win = new Ext.Window({
                id:'win1',
                title:'权限',
                width: 250,
                height:450,
                x:760,
                y:95,
                shim:false,
                animCollapse:false,
                constrainHeader:true,
                layout: 'fit',
                items: new Ext.tree.TreePanel({
                id:'forum-tree2',
                region:'west',
                title:'所有部门人员',
                split:true,
                width: 225,
                minSize: 175,
                maxSize: 400,
                collapsible: true,
                margins:'0 0 5 5',
				animCollapse:false,
				animate: true,
				collapseMode:'mini',
                loader: new Ext.tree.TreeLoader({
								url: 'person-tree.js'
		        }),
                rootVisible:true,
                lines:true,
                autoScroll:true,
                root: new Ext.tree.AsyncTreeNode({
                					id:'root',
                					draggable:false,
                          text: '所有部门人员'
                }),
                collapseFirst:false
            })
            });
        }
        win.show();   
}
