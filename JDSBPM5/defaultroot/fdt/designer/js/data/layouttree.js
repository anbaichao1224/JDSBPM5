//基本数据
  
  Ext.namespace("TreeData");
  
  
  
  //新建时的基础框架
  TreeData.emptyTreeData=[{"text":"布局","id":"root","children":[{"text":"表头","id":"head","children":[],"uid":"head","fieldName":"head","cnName":"表头","expression":"","leaf":false},{"text":"表体","id":"body","children":[{"text":"行头","id":"rowhead","children":[],"uid":"rowhead","fieldName":"rowhead","cnName":"行头","expression":"","leaf":false},{"text":"列头","id":"colhead","children":[],"uid":"colhead","fieldName":"colhead","cnName":"列头","expression":"","leaf":false},{"text":"块","id":"group","uid":"group","fieldName":"group","cnName":"块","expression":"","leaf":true}],"uid":"body","fieldName":"body","cnName":"表体","expression":"","leaf":false},{"text":"表尾","id":"foot","children":[],"uid":"foot","fieldName":"foot","cnName":"表尾","expression":"","leaf":false}],"uid":"root","fieldName":"root","cnName":"布局","expression":"","leaf":false}];
  
  //节点各属性名及其中文显示名与是否可显示编辑等
  TreeData.attNames={
                    id:{text:"id",display:false},
                    uid:{text:"uid",display:false},
                    
                    text:{text:"节点名",display:true},
                    fieldName:{text:"字段名",display:true},
                    cnName:{text:"中文名",display:true},
                    expression:{text:"表达式",display:true}
                   };
/*
  TreeData.enToCnMap={
                      text:"节点名",
                      fieldName:"字段名",
                      cnName:"中文名",
                      expression:"表达式"
                    };
*/
  
  //不可拖动,删除,修改的节点id,这些节点下的子节点不可跨此节点拖动
  TreeData.staticNodeId=["rowhead","colhead","group","head","foot","body","root"];
  
var constructLayoutTree=(function(){
//生成布局树
function createTree(cfg){
    var Tree = Ext.tree;

    var tree = new Tree.TreePanel({
        el:"layoutTree",
        //el:"layoutTreePanel",
        //el:document.body,
        //height:500,
        autoHeight:true,
        autoWidth:true,
        //width:300,
        animate:true,
        autoScroll :true,
        //bodyBorder:false,
        border:false,
        loader: new Tree.TreeLoader(),
        lines: false,
        //selModel: new Ext.tree.MultiSelectionModel(),
        rootVisible:false,
        
        enableDD:true,
        containerScroll: false
    });
    var rootCfg={
            text: '根',
            draggable:false,
            id:'tree_root'
        };
    if(cfg.children||cfg.data){
      rootCfg.children=cfg.children||cfg.data;
    }else if(cfg.dataUrl||cfg.url){
      rootCfg.dataUrl=cfg.dataUrl||cfg.url;
    }
    var root = new Tree.AsyncTreeNode(rootCfg);
    
    tree.on("beforeload",function (node){
                if(node.attributes.dataUrl){
                  tree.loader.dataUrl=node.attributes.dataUrl;
                  tree.loader.baseParams={id:node.id,fileName:$('fileName').value};
                }
                return true;
              });
  
    tree.setRootNode(root);
    
    //排序形式,以id为基准
    var sorter=new Tree.TreeSorter(tree, {
                                            folderSort:true,
                                            sortType:function(n){
                                              return n.id;
                                            }
                                          }
                                  );
    TreeData.sorter=sorter;

    tree.on("click",treeClick);//单击节点时的处理
    tree.on("dblclick",function (node,e){openNodeAttWin(node,"edit");});//
    tree.on("nodedragover",nodedragover);//拖到目标节点上
    tree.on("enddrag",enddrag);//结束拖拽
    
    tree.on("startdrag",startdrag);//开始拖拽前
    tree.on("contextmenu",treeCtxMenu);//右键菜单
    
    tree.render();
    root.expand(true);
    
    TreeData.tree=tree;
}

//得到指定节点的最近不可动父节点
function getStaticParent(node){
  var p=node.parentNode;
  var str=TreeData.staticNodeId.join("|");
  while(p){
    var reg=new RegExp("\\b"+p.id+"\\b");
    if(reg.test(str)){
      return p;
    }
    p=p.parentNode;
  }
  return null;
}

//是否可在指定节点上加新节点
function canAdd(node){
  var id=node.id;
  return id!="body"&&id!="root";
}

//是否可编辑指定节点的属性
function canEdit(node){
  return TreeData.staticNodeId.indexOf(node.id)<0;
}

//是否可删除指定节点
function canDel(node){
  return TreeData.staticNodeId.indexOf(node.id)<0;
}

//是否可以把源节点拖到目标节点上
function canDD(sNode,dNode){
  var id=sNode.id;
  if(TreeData.staticNodeId.indexOf(id)<0){  //是否允许拖动
    if(dNode){
      try{
        var id1=getStaticParent(sNode).id;
        var id2=getStaticParent(dNode).id;
        if(id1!=id2){  //是否允许拖到目标节点
          return false;
        }
      }catch(e){
        return false;
      }
    }
    return true;
  }
  return false;
}

//开始拖拽的处理
function startdrag (tree,node){
  TreeData.startParent=node.parentNode;
  TreeData.startNode=node;
}

//拖到目标上方的处理
function nodedragover(obj){
  var node=obj.target;
  if(!TreeData.dragOverNode){
    TreeData.dragOverNode=[];
  }
  if(canDD(obj.dropNode,node)){
    if(node.isLeaf()){
      //alert(node.text);
      node.leaf=false;
      node.attributes['leaf']=false;
      TreeData.dragOverNode.push(node);
      //alert(TreeData.dragOverNode.length);
    }
    return true;
  }else{
    return false;
  }
}

//拖拽结束的处理
function enddrag(tree,node,e){
  if(TreeData.dragOverNode){
    var ns=TreeData.dragOverNode;
    
    //alert(ns.length);
    for(var i=0;i<ns.length;i++){
      //alert(ns[i].text+"=="+ns[i].findChildBy(function(){return true;}));
      if(!ns[i].findChildBy(function(){return true;})){
        ns[i].leaf=true;
        ns[i].attributes['leaf']=true;
      }else{
        ns[i].leaf=false;
        ns[i].attributes['leaf']=false;
      }
    }
    
    try{
      var oldParentCs=TreeData.startParent.findChildBy(function(){return true;});
      if(!oldParentCs){
        TreeData.startParent.leaf=true;
        TreeData.startParent.attributes['leaf']=true;
      }
      
      //重排序
      var p=node.parentNode.parentNode;
      if(p){
        TreeData.sorter.updateSort(tree,p);
      }
    }catch(e){}
    
    //清除引用 
    TreeData.dragOverNode=[];
    TreeData.startParent=null;
    TreeData.startNode=null;
  }
}

//从指定节点得到其对应的json对象
function getJsonObjByNode(node,obj){
  if(!obj){
    obj={};
  }
  var atts=node.attributes;
  for(var a in atts){
    if(typeof(atts[a])!="object"){
      obj[a]=atts[a];
    }else {
      //alert(atts[a].id)
    };
  }
  
  var cs=node.childNodes;
  
  if(cs&&cs.length>0){
    obj.children=[];
    for(var i=0;i<cs.length;i++){
      obj.children[i]={};
      getJsonObjByNode(cs[i],obj.children[i]);
    }
  }
  return obj;
}

//打开编辑或新建节点窗口
function openNodeAttWin(node,flag){
  var info="";
  if(flag=="add"){
    info="添加";
  }else if(flag=="edit"){
    info="编辑";
  }
  info+=" ["+node.text+"]";
  var panelCfg={};
  
  var pan=new Ext.FormPanel({
        frame:true,
        //autoScroll:true,
        id:Ext.id(null,"nodeAtt"),
        bodyStyle:'padding:5px 5px 0',
        items:[{}]
      });
      var atts=TreeData.attNames;
      for(var a in atts){
        var val="";
        if(flag=="add"){
          if(a=="id"){
            val=Ext.id(null,"z"+node.id+"_sub");
          }else{
            val="";
          }
        }else if(flag=="edit"){
          val=node.attributes[a];
        }
        var f=null;
        if(atts[a].display){
          f=new Ext.form.TextField({
                    fieldLabel:atts[a].text,
                    name:a,
                    width:200,
                    value:val
                  });
        }else{
          f=new Ext.form.Hidden({name:a,value:val});
        }
        pan.add(f);
      }
      
  var winParam={
      id:Ext.id(null,"nodeAttWin"),
      title: info,
      closable:true,
      autoScroll:true,
      width:400,
      height:300,
      //border:false,
      plain:true,
      items: [pan],
      buttons:[{
        text:'确 定',
        handler:function(){
          //alert(node.text+"=="+pan.getForm().getValues(true));
          var obj=pan.getForm().getValues();
          var rtn=false;
          if(flag=="add"){
            obj.leaf=true;
            rtn=appendChildren(node,obj);
          }else if(flag=="edit"){
            rtn=modNode(node,obj);
          }
          if(rtn){
            win.close();
          }
        }
      },{
        text:"取消",
        handler:function(){
                  win.close();
                }
      }
      ]
  };
  
  var win = new Ext.Window(winParam);
  win.show();
}

//生成节点的右键菜单
function treeCtxMenu(node,e){
  var menu = new Ext.menu.Menu();
  var r={
            id:'rMenu',
            text : '删除节点',
            node:node,
            handler:function (item){
              removeNode(item.node);
            }
          };
  var a={
            id:"aMenu",
            text:"添加节点",
            node:node,
            handler:function(item){
              openNodeAttWin(item.node,"add");
            }
        };
  var m={
            id:"mMenu",
            text:"编辑属性",
            node:node,
            handler:function(item){
              openNodeAttWin(item.node,"edit");
            }
        };
        
  var submit={
            id:"sMenu",
            text:"保存布局",
            node:node,
            handler:function(item){
              saveTree(item.node);
            }
          };

  var expandAll={
            id:"expMenu",
            text:"展开所有",
            node:node,
            handler:function(item){
              item.node.expand(true);
            }
          };
  var collapseAll={
            id:"collMenu",
            text:"折叠所有",
            node:node,
            handler:function(item){
              item.node.collapse(true);
            }
          };
  if(canEdit(node)){
    menu.add(m);
  }
  if(canAdd(node)){
    menu.add(a);
  }
  if(canDel(node)){
    menu.add(r);
  }
  if(!node.isLeaf()){
    if(node.childNodes&&node.childNodes.length>0){
      if(menu.items.length>0){
        menu.add("-");
      }
      menu.add(expandAll);
      menu.add(collapseAll);
    }
  }
//  if(node.id=="root"){
//    menu.add(submit);
//  }
  if(menu.items.getCount()>0){
    menu.showAt(e.getXY());
  }
  return ;
}


//节点单击处理
function treeClick(node,e){
  var obj=getJsonObjByNode(node);
  //alert(obj.fieldName);
 // alert(currform.findField(0).name);
  var field= currform.findField(obj.fieldName);
   if (field){
   	 var type=field.getXType();
 
  	if (type=='checkbox' ||type=='radio'){
		type='input';
	}
	editAllAtt(field.pid,type);
   }
  
}

//编辑或新建后检查输入的节点属性
function checkNodeAtt(obj){
  var msg="";
  if(obj.fieldName==""&&obj.expression==""){
    msg+="字段名和表达式都为空\n";
  }
  if(obj.text==""){
    msg+="节点名为空\n";
  }
  if(obj.cnName==""){
    msg+="中文名为空\n";
  }
  if(msg){
    msg+="确认继续吗?";
    return confirm(msg);
  }else{
    return true;
  }
}

//在指定节点上添加指定配置的子节点
function appendChildren(node,obj){
  //node.beginUpdate();
  try{
    var add=checkNodeAtt(obj);
    if(add){
      var n=new Ext.tree.TreeNode(obj);
      node.appendChild(n);
      if(node.isLeaf()){
        node.leaf=false;
        node.attributes.leaf=false;
      }
    
      TreeData.sorter.updateSort(TreeData.tree,node.parentNode);
      return true;
    }else{
      return false;
    }
  }catch(e){
    alert(e);
    return false;  
  }
  //node.endUpdate();
}

//修改节点属性为指定值
function modNode(node,obj){
  try{
    var mod=checkNodeAtt(obj);
    if(mod){
      for(var a in obj){
        if(a=="text"){
          node.setText(obj[a]);
        }else{
          node.attributes[a]=obj[a];
        }
      }
      return true;
    }else{
      return false;
    }
  }catch(e){
    alert(e);
    return false;  
  }
}

//删除指定节点
function removeNode(node){
  if(canDel(node)){
    if(confirm("确认要删除此节点 ["+node.text+"] 吗?")){
      var p=node.parentNode;
      node.remove();
      if(p.childNodes.length<=0){
        p.leaf=true;
        p.attributes.leaf=true;
        TreeData.sorter.updateSort(TreeData.tree,p.parentNode);
      }
    }
  }else{
    alert("此节点不可删除");
  }
}

//保存树节点结构到后台
function saveTree(node){
  var obj=getJsonObjByNode(node);
  alert(Ext.encode(obj));
  
  return ;
  /*
  var url="";
  Ext.Ajax.request({
     url: url,
     method:"POST",
     success: Ext.emptyFn,
     failure: Ext.emptyFn,
     params: { tree: Ext.encode(obj) }
  });
  */
};

//取整个树所对应的json对象,如需string格式可调用Ext.encode方法
TreeData.getJsonObj=function(){
  if(TreeData.tree){
    return getJsonObjByNode(TreeData.tree.getRootNode().childNodes[0]);
  }else{
    return {};
  }
};

function(isEmpty){
          var cfg=null;
          if(isEmpty==true){
            cfg={data:TreeData.emptyTreeData};
          }else{
            cfg={
              data:data1
              //children:data1
              //dataUrl:"treenode.txt"
            };
          }
          createTree(cfg);
        }
return function(url){
	      var cfg=null;
	      if(!url){
	      	cfg={data:TreeData.emptyTreeData};
	      }else{
            cfg={
              dataUrl:url
            };
	      }
          createTree(cfg);
        };
/*
return function(){
          var cfg={
            dataUrl:"../design/fileLayout.action?fileName=" + $F('currentOpenPage')
          };
          createTree(cfg);
        };
*/
})();

//根据id选中对应节点,暂未用
function selectNodeById(id){
  var node=TreeData.tree.getNodeById(id);
  node.select();
}

