<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>

<%
  String contextpath = request.getContextPath() + "/";
  String urlContext = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextpath;
%>
<html>
<head><title>定义</title>

<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css"/>
<script type="text/javascript" src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=contextpath%>js/ext/ext-all-debug.js"></script>
<script type="text/javascript" src="<%=contextpath%>js/ext/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript" src="<%=contextpath%>desktop/js/CreateGrid.js"></script>

<script type="text/javascript">

   var esbKeyList='<ww:property value="$R('esbKeyList')" escape="false"/>';
    var flowType='<ww:property value="$R('flowType')" escape="false"/>';
var context = '<%=contextpath%>';
var tableWin = null;
function initWin() {
  var winCfg = {
    id:'expressionEditorWin',
    title:'定义',
    layout:'border',
    closeAction :'hide',
    closable:false,
    // maximizable:true,
    // plain:true,
    width:850,
    height:600,
    items:
        [
          {
            listeners:{
              "resize":resizeColPanel
            },

            id:"colPanel",
            region:'center',
            split:true,
            //maxWidth:700,
            items:{
              id:"colPanel2",
              title: '属性信息',
              //height:700,
              autoWidth:true,
              html:"<div id='colsInfo' style='border:0;text-align:left'></div>"
            }
            ,
            buttons:[
              {text:'选择查询列',id:"editQueryColBtn",handler:addQueryCol},
              {text:'增加列',handler:addCol},
              {id:"editBtn",text:'编辑列属性',handler:editAtt},
              {text:'预览',handler:preView},
              {text:'保存',handler:saveAtt}
            ]
          },{
          region:'west',
          width:260,
          autoScroll:true,
          collapsible: true,
          title:'所有表',
          split:true,
          layout:'fit',
          layoutConfig:{
            animate:true
          },
          items: {
            html:'<div id="treeDiv" style="overflow:auto; height:100%;border:1px solid #c3daf9;"></div>',
            iconCls:"nav",
            border:false,
            defaults:{autoScroll: true}
          }
        }

        ]
  };
  tableWin = new Ext.Window(winCfg);
  tableWin.show();
  tableWin.maximize();
//  tableWin.currFormId="formid1";
  tableWin.currVals={};

  tableWin.currType="table";
  //showGridData(getFormAtt("formid1"),'colsInfo');
}

//弹出的列选择树
function getFiledTreePanel() {
  var Tree = Ext.tree;
  var height = 400;
  var treeType = 'columnlist';
  var fileTree = new Tree.TreePanel({
    animate:true,
    enableDD:false,
    loader: new Tree.TreeLoader({dataUrl:"/fdt/designer/tools/tree.jsp?txtSearch=$&crrentIndex=1&currentExpression=$&treeType="+treeType + "&esbKeyList=columnlist&flowType="+flowType}),
    lines: true,
    containerScroll: true,
    height:height,
    autoScroll:true,
    autoWidth:true,
    rootVisible:false
  });
  var fileRoot = new Tree.AsyncTreeNode({
    text: '选择映射字段',
    leaf : false,
    cls : 'folder',
    id:'$',
    expression:"$",
    draggable:false
  });
  fileTree.setRootNode(fileRoot);
  fileTree.on('beforeload', function(node) {
    fileTree.loader.dataUrl = '/fdt/designer/tools/tree.jsp?txtSearch=' + node.id + "&treeType=" + treeType + "&crrentIndex="+new String(node.id.length)+"&currentExpression=" + node.attributes.expression+'&esbKeyList=columnlist&flowType='+flowType;
  });
//		        fileTree.on('click',function(node,e)
  //		        {
  //		                e.stopEvent();
  //		       });

  var win = new Ext.Window({
    id:"fileListWin",
    title: '可填加列',
    closable:true,
    autoScroll:true,
    width:300,
    autoHeight:true,
    buttons:[{text:'确 定',handler:function() {
      var node = fileTree.getSelectionModel().getSelectedNode();
      if(node){
        var value = node.attributes.expression.replace('.{**}', '');
        value=value.replace("$","");
         value=value.replace("worklist.","");
//        if(value.indexOf(".")<=0){
//          alert("只能填加字节点");
 //       }else{
          value=value.replace(/\./g,"_");
          var grid = getAttGrid();
          var ks = grid.getStore().getAt(0).fields.keys;
          var count = grid.getStore().getCount();
          var d = {};
          Ext.apply(d,_colDefaultValue);
          d.colName = value;
               d.sortable="false";
          d.tooltip="";
          d.mapping=value;
          d.header = node.attributes.text;
          var record = new Ext.data.Record(d);
          grid.getStore().add([record]);
 //       }
      }
      win.close();
    }}],
    plain:true,
    items: [fileTree]
  });

  win.show();

  fileTree.getEl().applyStyles("text-align:left");
}

//默认列
var _colDefaultCol={
  header:["列id","类型","列名","是否隐藏","宽度","对齐方式"],
  index:["colName","type","header","hidden","width","align"],
  data:[["colName","string","列","false",80,"left"]]
}
  //增加的列的默认值
var _colDefaultValue={
  colName:"colName",
  header:"列",
  width:80,
  hidden:false,
  align:"left",
  type:"string"
}

//增加一列
function addCol(){
  if(!tableWin.currFormId) {
    alert("请先选择要编辑的表");
    return;
  }
  if(tableWin.currType!="col"){
    editAtt();
  }
  getFiledTreePanel();
}

//切换编辑列或表属性
function editAtt(btn){
  if(!tableWin.currFormId){
    alert("请先选择要编辑的表");
    return;
  }
  if(!btn){
    btn=Ext.getCmp("editBtn");
  }
  if(tableWin.currType&&tableWin.currType=="col"){
    tableWin.currType="table";
    btn.setText("编辑列属性");
    tableWin.currColAtt=getTmpColData();

    getFormDataAndShow(tableWin.currFormId);
  }else{
    tableWin.currType="col";
    btn.setText("编辑表属性");
    tableWin.currFormAtt=getTmpFormData();
    getColDataAndShow(tableWin.currFormId);
  }
}

//保存值,应发到后台
function saveAtt(){
  if(!tableWin.currFormAtt&&!tableWin.currColAtt){
    alert("请先编辑列属性");
    return ;
  }
  getTmpValue();
  var rtn={};
  rtn.formAtt=tableWin.currFormAtt.mapData;
  rtn.colAtt=tableWin.currColAtt.mapData;
  rtn.self=tableWin.currSelf||{};

//  alert(Ext.encode(rtn.colAtt));
//  return

  var url="/bpmListSet.action?listId="+tableWin.currFormId;
  var param={json:Ext.encode(rtn)};

  Ext.Ajax.request(
  {
    url: url,
    params:param,
    method:"post",
    success: function(rs) {
      alert("保存成功");
    },
    failure: function() {
      alert("保存出错");
    }
  });
}

//缓存当前的各属性信息
function getTmpValue(){
  if(!tableWin.currColAtt||tableWin.currType=="col"){
    tableWin.currColAtt=getTmpColData();
  }
  if(tableWin.currType=="table"){
    tableWin.currFormAtt=getTmpFormData();
  }
}
function preView(){
  if(!tableWin.currFormAtt&&!tableWin.currColAtt){
    alert("请先编辑列属性");
    return ;
  }
  getTmpValue();
  var fd=tableWin.currFormAtt.mapData;
  var cd=tableWin.currColAtt.mapData;
  var cfg={};
  cfg.selfCfg={};
  cfg.selfCfg.id=tableWin.currFormId+"Grid";
  cfg.selfCfg.height=600;
  cfg.selfCfg.frame=true;
  cfg.selfCfg.enableColumnHide =true;
//  cfg.selfCfg.title=tableWin.currFormAtt.title.value;

  cfg.metaData={};
  cfg.metaData.hasChockbox=fd.hasCheckBox.value;
  cfg.metaData.hasRowNum=fd.hasRowNumber.value;
  cfg.metaData.dataType="json";

  var cols=[];
  var testrow={};
  var tW=0;
  var tbar=[];
  for(var k in cd){
    var c= cd[k];
    var col={};
    col.text =c.header;
    col.name =k;
    col.type =c.type;
//    col.dateFormat =
//    col.displayFormat =
//    col.renderer=

    var h=c.hidden;
    if(h===false||h==="false"){
      h="true";
    }else{
      h="false";
    }
    col.isDisplay =h;
    col.width =parseFloat(c.width);
    col.sortable=c.sortable;
    col.align=c.align;
    col.tooltip =c.tooltip ;

    tW+=col.width;
    testrow[k]=k+" 测试数据";
    cols.push(col);

    var sv=c['search'];
    if(sv){
      var ex=sv["expression"];
      if(ex){
        var tmp=null;
        var ttype=sv.fieldType;
        var label=col.text;

//	TextField("TextField"),
//	DateField("DateField"),
//	ComboBox("ComboBox");
        tbar.push({text:label});
        
        if(ttype=="DateField"){
          tmp=new Ext.form.DateField(
          {
            fieldLabel:label,
            width:sv.width
          });
        }else if(ttype=="ComboBox") {
          var store = new Ext.data.SimpleStore({
            fields: ['key','val'],
            data :[["11","选择1"],["22","选择2"]]
          });
          tmp = new Ext.form.ComboBox({
            store: store,
            editable :false,
            displayField:'val',
            valueField:'key',
            typeAhead: true,
            mode: 'local',
            forceSelection: true,
            triggerAction: 'all',
            emptyText:'Select a state...',
            selectOnFocus:true,
            fieldLabel:label,
            width:sv.width
          });
        }else{
          tmp=new Ext.form.TextField({
            fieldLabel:label,
            width:sv.width
          });
        }
        tbar.push(tmp);
      }
    }
  }
  tW+=150;
  if(tW<500){
    tW=500;
  }

  cfg.selfCfg.width=tW;

  if(tW>1300){
    tW=1300
  }
  cfg.metaData.cols=cols;

  cfg.metaData.paging={};
  cfg.metaData.paging.pageSize=fd.pageSize.value;
  cfg.metaData.paging.totalProperty="totalcount";
  cfg.metaData.paging.root='rows';

  var testData = {
    totalcount:10,
    rows: [testrow,testrow]
  };
  cfg.data = testData;

  if(tbar.length>0){
    tbar.push("-");
    tbar.push("查询");
  cfg.selfCfg.tbar=tbar;
  }


  var grid=createGridByData(cfg,true);
  var win=Ext.getCmp("winpre");
  if(win){
    win.destroy();
  }
  win=new Ext.Window({
    id:"winpre",
    title:fd.title.value,
    maximizable :true,
    width:tW,
    height:650,
    modal:true,
    layout:"fit",
    buttons:[
      {text:"关闭",
        handler:function(){
          win.close();
        }
      }
    ],
    items:[grid]
  });
  win.grid=grid;
  win.on("beforeclose",winCloseHandler);
  win.show();
}

//关闭预览窗口时,保存列宽度,是否显示等
function  winCloseHandler(win){
  var g=win.grid;
  var cm=g.getColumnModel();
  var tmp={};
  var cc=cm.getColumnCount();
  for(var k=0;k<cc;k++){
    var val={};
    val.width=cm.getColumnWidth(k);
    val.hidden=cm.isHidden(k)||false;
    tmp[cm.getDataIndex(k)]=val;
  }
  if(tableWin.currType!="col"){
    editAtt();
  }
  var grid=getAttGrid();
  var store=grid.getStore();
  var count=store.getCount();
  for(var i=0;i<count;i++){
    var r=store.getAt(i);
    var width=tmp[r.data.colName].width;
    width=parseFloat(width);
    width=Math.round(width);
    r.set("width",width);
    r.set("hidden",tmp[r.data.colName].hidden);
  }
}

//取当前编辑中的表属性数据
function getTmpFormData() {
  var grid = getAttGrid();
  var store = grid.getStore();
  var count = store.getCount();
  var cm=grid.getColumnModel();
  var rtn = {};
  rtn.header=[];
  rtn.index=store.fields.keys;
  rtn.data=[];

  rtn.mapData={};
  var colNum=cm.getColumnCount();
  for(var i=0;i<colNum;i++){
    rtn.header.push(cm.getColumnHeader(i));
  }
  for (var i = 0; i < count; i++) {
    var r = store.getAt(i);
    var tmp=[];
    for(var j=0;j<rtn.index.length;j++){
      tmp.push(r.data[rtn.index[j]]);
    }
    rtn.data.push(tmp);
    var key=r.data["name"]||r.data["colName"];
    rtn.mapData[key]=r.data;
    rtn.mapData[key].idx=i+"";
  }
  return rtn;
}

//当前编辑中的列属性数据
function getTmpColData(){
  return getTmpFormData();
}

var Tree = Ext.tree;
           function getTree(currel,treeType){
              var tree = new Tree.TreePanel({
			        el:currel,
			        animate:true,
			        enableDD:false,
			        loader: new Tree.TreeLoader({dataUrl:"/fdt/designer/tools/tree.jsp?txtSearch=$&crrentIndex=1&currentExpression=$&treeType="+treeType+"&esbKeyList="+esbKeyList+"&flowType="+flowType}),
			        lines: true,
			        selModel: new Ext.tree.MultiSelectionModel(),
			        containerScroll: true,
			        autoHeight:true,
			        rootVisible:false
			        });
			        var root = new Tree.AsyncTreeNode({
			        text: '对象',
			        leaf : false,
			        cls : 'folder',
			        draggable:false,
			        id:'$',
			        expression:"$"
			        });
			        tree.setRootNode(root);
			        tree.on('beforeload', function(node){
                if(node.id!="$"){
                  return false;
                }
                
              tree.loader.dataUrl = '/fdt/designer/tools/tree.jsp?txtSearch='+node.id + "&treeType="+treeType+"&crrentIndex=" + new String(node.id.length)+"&currentExpression=" + node.attributes.expression+'&esbKeyList='+esbKeyList+'&flowType='+flowType;
			        });
			        tree.on('click', function(node, e)
              {
                var id=node.id+"";
                id=id.replace(/^[$]|\.$/g,"");
                if(id.indexOf(".")<0){
                  getFormDataAndShow(id);
                  initTableValue(id);
                }
              });

             tree.on("contextmenu",function(node,e){

               var menu = new Ext.menu.Menu({
                 id: 'menuContext',
                 items: [
                   {   text: '以此为模版新建',
                     handler: function (){newForm(node) }
                   },
                   {
                     text: '新建空白表',
                     handler:function (){newForm() }
                   }
                 ]
               });
               menu.showAt(e.getXY());

             });

              tree.render();
             return tree;
          }
function getTreeNodeById(id){
  return tableWin.tree.getRootNode().findChild("id",id);
}
function initTableValue(id) {
  tableWin.currFormId = id;
  tableWin.currType = "table";
  var btn = Ext.getCmp("editBtn");
  btn.setText("编辑列属性");
  tableWin.currColAtt = null;
  tableWin.currFormAtt = null;
  tableWin.currSelf = null;
}

  function newOk(form,win,node){
    var name = form.findField('name').getValue();
    var cnname = form.findField('cnname').getValue();
    var n = "empty";
    if (node) {
      n = node.id.replace(/^[$]|\.$/g, "");
    }
    var cfg = {
      text: cnname,
      leaf : false,
      tempId:n,
      cls : 'folder',
      draggable:false,
      id:'$' + name + ".",
      expression:"$" + name + "."
    };
    var nNode = new Ext.tree.TreeNode(cfg);
    tableWin.tree.getRootNode().appendChild(nNode);
    nNode.select();
    win.close();

    getFormDataAndShow(name,n);
    initTableValue(name);
  }

//建新表的输入窗口
function newForm(node){
  var name="NewForm";
  var cnname="新建表";
  if(node){
    name=node.id.replace(/^[$]|\.$/g,"")+"_new";
    cnname=node.text+"_new";
  }
  var cfg={
    id:"newWin",
    title:"新建",
    maximizable :false,
    width:300,
    height:160,
    modal:true,
    layout:"fit",
		plain:true,

    items:[{
      xtype:'form',
      method:'POST',
      bodyStyle:'background:transparent;padding:10px;',
      border:false,
      items:[{
        xtype:'textfield',
        fieldLabel: '表名',
        name: 'name',
        value:name,
        anchor:'90%'
      },{
        xtype:'textfield',
        fieldLabel: '中文名',
        name: 'cnname',
        value:cnname,
        anchor:'90%'
      }],
      buttons :[{
        text:"确定",
        handler:function(f,e){
          var form=f.ownerCt.form;
          newOk(form,win,node);
        }
      }]
    }]

  };
  var win=new Ext.Window(cfg);
  win.show();
}

function showGridData(data, divId) {
  var grid = Ext.getCmp(divId + "GridPanel");
  var cols = data.header;
  var colM = [];
  var fields = [];
  var indexs = data.index;

  var curType = tableWin.currType;
  var feditor = tableWin.currVals.form || {};
  var ceditor = tableWin.currVals.col || {};
  var curEditor = {};
  if (curType == "col") {
    curEditor = ceditor;
  }

  for (var i = 0; i < cols.length; i++) {
    var ed = null;
    if (curType == "col") {
      ed = curEditor[indexs[i]];
    }
    if (!ed) {
      ed = new Ext.form.TextField();
    }
    var tmp={header:cols[i],dataIndex:indexs[i],width:150,sortable: true,editable:true,editor:ed };
    if(indexs[i]=="search"){
      tmp.hidden =true;
    }
    colM.push(tmp);

    fields.push({name:indexs[i]});
  }
  var store = new Ext.data.SimpleStore({
    fields: fields
  });
  if (grid) {
    grid.destroy();
  }

  store.loadData(data.data);
  grid = new Ext.grid.EditorGridPanel({
    id:divId + "GridPanel",
    store: store,
    columns:colM,
    clicksToEdit:1,
    width:800,
    height:500,
    enableColumnHide:false,
    frame:true
  });
  grid.on("render", resizeColPanel);

  grid.on('beforeedit', beforeColEdit);
  grid.render(divId);
}

function getAttGrid() {
  var grid = Ext.getCmp("colsInfoGridPanel");
  return grid;
}

//
//重设显示表的大小
function resizeColPanel() {
  var obj = Ext.getCmp("colPanel");
  var grid = getAttGrid();
  if (grid) {
    grid.setSize(obj.getSize().width - 2, obj.getSize().height - 66);
  }
}

/*
编辑以前设置第一列不能编辑,如果是编辑表属性时,设置相应的下拉框
 */
function beforeColEdit(e) {
  var row = e.row;
  var col = e.column;
  if(col==0)return false;
  if(e.field =="queryFieldShow"){
    return changeQueryCol(e);
  }
  if(tableWin.currType=="table"){
    var grid = getAttGrid();
    var cm=grid.getColumnModel();
    if(cm.getDataIndex(col)=="value"){
      var key=grid.getStore().getAt(row).data.name;
      var ed=tableWin.currVals.form[key];
      if(ed){
        ed=ed.cloneConfig();
      }else{
        ed=new Ext.form.TextField();
      }
      ed=new Ext.grid.GridEditor(ed);
      cm.setEditor(col,ed);
    }
  }
  return true ;
}

function changeQueryCol (e){
  if (e.field == "queryFieldShow") {
    var record = e.record;

    var sv = record.get("search");
//    alert(Ext.encode(sv))
    var cWin = Ext.getCmp("queryFieldSetWin");
    try {
      if (!cWin || cWin.error) {
        var is = [];
        for (var k in sv) {
          var txt = null;
          if (k == "fieldType") {
            var store = new Ext.data.SimpleStore({
              fields: ['key','val'],
              data :[["none","none"],["TextField","TextField"],["DateField","DateField"],["ComboBox","ComboBox"]]
            });
    //alert(Ext.encode(data[k]))
            txt = new Ext.form.ComboBox({
              store: store,
              name:k,
              editable :false,
              fieldLabel:k,
              displayField:'val',
              valueField:'key',
              typeAhead: true,
              mode: 'local',
              forceSelection: true,
              triggerAction: 'all',
              emptyText:'Select a state...',
              selectOnFocus:true
            });

          }
          if (!txt) {
            txt = new Ext.form.TextField();
            txt.name = k;
            txt.fieldLabel = k;
          }
          txt.width = 150;
          txt.setValue(sv[k]);
          is.push(txt);
        }

        var panel = new Ext.form.FormPanel(
        {
          items:is
        });
        cWin = new Ext.Window({
          id:"queryFieldSetWin",
          title:"设置查询列属性",
          width:300,
          height:200,
          modal:true,
          closable :false,
          items:[
            panel
          ],
          buttons:[
            {"text":"确定",
              handler:function() {
                var tmpR = cWin.record;
                var vs = panel.getForm().getValues();
//              alert(Ext.encode(vs))
                tmpR.data["search"] = vs;
                if (vs.expression) {
                  tmpR.set("queryFieldShow", "是");
                } else {
                  tmpR.set("queryFieldShow", "否");
                }
                cWin.hide();
              }
            }
          ]
        });
        cWin.form = panel.getForm();
      }
      cWin.record = record;
      cWin.form.setValues(sv);
      cWin.error = null;
      cWin.show();
    } catch(e) {
      //      alert(e)
      cWin.error = e;
      cWin.hide();
    }
  }
  return false;
}


//生成编辑框
function getEditor(data,type){
  var rtn={};

  for(var k in data){
    var store = new Ext.data.SimpleStore({
      fields: ['key','val'],
      data :data[k]
    });
    //alert(Ext.encode(data[k]))
    var comb=new Ext.form.ComboBox({
        store: store,
      name:k,
        editable :false,
        displayField:'val',
        valueField:'key',
        typeAhead: true,
        mode: 'local',
        forceSelection: true,
        triggerAction: 'all',
        emptyText:'Select a state...',
        selectOnFocus:true
      });
    rtn[k]=comb;
  }

  return rtn;
}

function getFormDataAndShow(id,tempId){
  if(tableWin.currFormId==id&&tableWin.currFormAtt){
    showGridData(tableWin.currFormAtt,'colsInfo');
  }else{
    loadDataAndShow(id,"form",tempId);
  }
}

function getColDataAndShow(id){
  if(tableWin.currFormId==id&&tableWin.currColAtt){
    showGridData(tableWin.currColAtt,'colsInfo');
  }else{
    var node= getTreeNodeById("$"+id+".");
    var tempId=null;
    if(node){
      tempId=node.attributes.tempId;
    }
    loadDataAndShow(id,"col",tempId);
  }
}

function loadDataAndShow(id,type,tempId) {
  var url="";
  if(type=="form"){
    url="/design/getFormAtt.action";
  }else if(type=="col"){
    url="/design/getColAtt.action";
  }
  var param={formName:id};
  if(tempId){
    param.tempId=tempId;
  }
  Ext.Ajax.request(
  {
    url: url,
    params:param,
    method:"post",
    success: function(rs) {
      var data={};
      if(rs.responseText&&rs.responseText.trim()){
        data=Ext.decode(rs.responseText.trim());
      }
//      alert(Ext.encode(data))
      if(tempId){
        if(tempId=="empty"){
          if(type=="form"){
            data={};
            data.header=["表属性名","中文名","值"];
            data.index=["name","cnname","value"];
            data.data=[["att"]];
            data.vals={};
            data.self = {};
            data.self.id=id;
            data.self.selfname=id;
            data.self.name=getTreeNodeById("$"+id+".").text;
          }else{
            data={}
            data.index=_colDefaultCol.index;
            data.header=_colDefaultCol.header;
            data.data=_colDefaultCol.data;
            data.vals={};
          }
        }else{
          if(type=="form"){
            data.self.id=id;
            data.self.selfname=id;
            data.self.name=getTreeNodeById("$"+id+".").text;
          }
        }
      }
      if (type == "form") {
        tableWin.currVals.form = getEditor(data.vals, "form");
        tableWin.currSelf=data.self;
      } else if (type == "col") {
        tableWin.currVals.col = getEditor(data.vals, "col");
      }
      showGridData(data, 'colsInfo');
    },
    failure: function() {
      alert("加载表数据出错");
    }
  });
}

//测试用,实际应从后台取
function getDefaultFormData(){
  var data={"index":["name","cnname","value"],
            "data":[["hasCheckBox","是否有选中框","false"],["buttonAlign","按钮对齐","left"],["hasRowNumber","显示序号","true"],["allowDelete","是否可删除","false"],["root","根","datas"],["pageSize","显示行数","15"],["url","url","bpmDataBind.action"],["title","标题","demo测试"],["height","高度","400"],["width","宽度","800"]],
            "header":["表属性名","中文名","值"]

             ,vals:{
                hasCheckBox:[["true","是"],["false","否"]],
                hasRowNumber:[["true","是"],["false","否"]],
                buttonAlign:[["left","左"],["center","中"],["right","右"]]
              }

  };

  return data;
}

//测试用,实际应从后台取
function getDefaultColData(){
   var data=
   {"index":["colName","hidden","sortable","type","header","width","align","tooltip","mapping"],
     "header":["列名","是否隐藏","是否排序","类型","列名","宽度","对齐方式","提示","映射字段"],
     "data":[
       ["fileTitle","false","true","string","名称","200","left","测试","fileTitle"],
       ["fileTitle2","false","true","string","名称2","200","left","测试","fileTitle"]
     ]

     ,vals:{
         hidden:[["true","是"],["false","否"]],
         sortable:[["true","是"],["false","否"]],
         type:[["string","字符串"]],
         align:[["left","左"],["center","中"],["right","右"]]
     }
   }

  return data;
}

function addQueryCol(){
  if(!tableWin.currFormId) {
    alert("请先选择要编辑的表");
    return;
  }
  if(tableWin.currType!="col"){
    editAtt();
  }
  getFiledTreePanel();
}


function init() {
  Ext.BLANK_IMAGE_URL = context + 'js/ext/resources/images/default/s.gif';
  Ext.QuickTips.init();

  initWin();
  tableWin.tree=getTree("treeDiv","worklist");

}
Ext.onReady(init);
</script>
</head>
<body>
</body>
</html>