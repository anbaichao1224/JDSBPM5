﻿
Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
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
        labelWidth: 80, // label settings here cascade unless overridden
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
          			   // validator:CheckUserName,//指定验证器  
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
                    this.disabled=true;    
                    flag = false;
                    fsf.getForm().submit({
                        url: '/usm/departModify.do?',
                        method: 'POST',
                        waitTitle: '请稍等……', 
                        waitMsg: '正在上传数据……',  
                        success: function() {
                            Ext.Msg.alert("成功", "修改成功！");
                            this.disabled=false;
                           
                        },
                        failure: function() {
                            Ext.Msg.alert("出错啦", "数据修改失败！");
                            this.disabled=false;
                        }
                    });
            }
        }
        
        
        
        ]

    });


 function onItemClick(item){
    	if(item.id=="person"){
       	location ="person-index.jsp";
      }else if(item.id=="depart"){
      	location ="depart-index.jsp";
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
	    text: '单位部门',
	    leaf : false,
	    cls : 'folder',
	    draggable:false,
	    id:'toproot'
	    });
	    
	    
	    var Tree = Ext.tree;
	    var treePanel = new Tree.TreePanel({
	    animate:true,
	    enableDD:false,
	    loader: new Tree.TreeLoader({dataUrl:'/usm/orgtreeJson.do?name=person'}),
	    lines: true,
	    selModel: new Ext.tree.MultiSelectionModel(),
	    containerScroll: true,
	    autoHeight:true,
	    rootVisible:false
	    });
	    
	    var treePanel= new Ext.tree.TreePanel({
                id:'forum-tree',
                region:'west',
                title:'单位人员权限查看',
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
            new Ext.BoxComponent({ // raw
                region:'north',
                el: 'header',
                height:0
            }),
           treePanel,
             new Ext.Panel({
             
                            region:'center',
                            id:'topic-grid',
                            html:'<iframe id="views" frameborder="0" src="person/personqueryListgrid.jsp" height=100% width=100%></iframe>'
                           
             })
         ]
    });
	 
 	 
    var tree =treePanel;
    tree.setRootNode(root);
	tree.render();
    tree.on('beforeload', function(node){
	   tree.loader.url = '/usm/orgtreeJson.do?name=person&childOrgId='+node.id+'';
	});
   
   tree.on('click', function(node){
	 /*  if(node.id=='toproot' || node.id=='root'){
	        nodeid=node.id;
	   		tree_refresh();
	   		Ext.get('views').dom.src= 'person/personqueryListgrid.jsp?childOrgId='+nodeid+'';
	   }else{*/
	      if(node.attributes.uid=="person"+node.id ){
	          nodeid=node.id;
		      Ext.get('views').dom.src= 'FindModule.action?personId='+node.id;	    
	      }/*else{
	      	 nodeid=node.id;
		     var title=node.attributes['cnName'];
			   	 	var personname = title;
					var charAt ='' ;
					for(var i = 0 ; i < personname.length ; i++)
					{
						var substr = personname.substring(i,i+1);
						var k = ' ';
						charAt = charAt+k+substr.charCodeAt();	
					}
					var ppname=encodeURIComponent(charAt);
					title=ppname;
		     Ext.get('views').dom.src='/usm/departInfo.do?cnname='+title+'&orgid='+node.attributes['id']+'&orglevel='+node.attributes['orglevel']+'&parentorgid='+node.id;
	      }*/
    //  }	
      
  /*    if(node.attributes.checked==true)
      {
          nodeid=node.id;
	     // Ext.getCmp('main-view').setTitle(node.text);
	      Ext.get('views').dom.src= 'person/personDetailInfo.do?personid='+node.id;
      
      }*/
       
    });
   /* 
    
    tree.on('checkchange', function(node, checked) {  
      if(checked){
          nodeid=node.id;
          Ext.get('views').dom.src= 'person/personDetailInfo.do?personid='+node.id;
      }
       
    }); */
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
