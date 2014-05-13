Ext.onReady(function() {

var url = "";
	if(rollid!='null'){
		url = "roll_rollDetailTree.action?rollbean.uuid="+rollid+"&";
	}else if(cid!='null'){
		url = "roll_rolllistByCid.action?rollbean.categoryid="+cid+"&";
	}else{
		url = "roll_treelist.action?";
		
	}

 /** ************函数定义（start）***************** */
 // 显示Tip
 function showTip() {
  qt.showAt([document.body.offsetWidth / 2 - 70,
    document.body.clientHeight / 2 - 40]);
 }

 // 隐藏Tip
 function hideTip() {
  qt.hide();
 }

 // (ajax回调函数的内容)提示用户操作结果
 function showResult(response, func) {
  hideTip();
  var data = Ext.decode(response.responseText);
  Ext.Msg.alert('信息', data.msg, func);
 }
 /** ************函数定义（end）***************** */

 // ext初始化
 Ext.QuickTips.init();

 var qt = new Ext.Tip({
  title : '提示：',
  width : 120,
  html : '正在和服务器交互...'
 });

 var tree = new Ext.tree.ColumnTree({
  width : '100%',
  enableDD : true,
  autoHeight : true,
  rootVisible : false,
  autoScroll : true,
  title : '刊物栏目与文章列表',
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
        ],

  loader : new Ext.tree.TreeLoader({
   dataUrl : url,
   uiProviders : {
    'col' : Ext.tree.ColumnNodeUI
   }
  }),

  root : new Ext.tree.AsyncTreeNode({
   text : 'Tasks'
  })
 });

//tree.render(); 
   //tree.expandAll(); 


 // 定义布局
 var viewport = new Ext.Viewport({
  layout : 'border',
  items : [{
   region : 'north',
   contentEl : Ext.getBody(),
   split : false,
   border : false,
   collapsible : false,
   height : 200
  }, {
   // title : '刊物栏目与文章列表',
   region : 'west',
   contentEl : Ext.getBody(),
   split : false,
   border : false,
   width : 700,
   collapsible : false,
   layout : 'accordion',
   layoutConfig : {
    titleCollapse : true,
    animate : true,
    activeOnTop : false
   },
   items : tree
  }, {
   region : 'center',
   contentEl :Ext.getBody(),
   split : false,
   border : false,
   collapsible : false
  }]
 });

 // 定义顶部工具栏上的返回按钮
 var backBtn = new Ext.Button({
  text : '返回',
  handler : function() {
   location.href = Ext.appRootPath
     + "/publicationListAction.do?method=listAllPublications";
  }
 });

 // 定义顶部工具栏上的修改刊物按钮
 var modifyPublicationBtn = new Ext.Button({
  text : '修改刊物',
  handler : function() {
   location.href = Ext.appRootPath
     + "/pubdefMainAction.do?method=updateJump&pubId=" + pubId;
  }
 });

 // 定义顶部工具栏上的修改栏目按钮
 var modifyColumnBtn = new Ext.Button({
  text : '修改栏目',
  handler : function() {
   location.href = Ext.appRootPath
     + "/pubcatalogEditMainAction.do?method=getColById&pubId="
     + pubId;
  }
 });

 // 定义顶部工具栏上的整合编辑按钮
 var integrationEditBtn = new Ext.Button({
  text : '整合编辑',
  handler : function() {
   showTip();
   location.href = Ext.appRootPath
     + "/pubinteditMainAction.do?method=getPubcolAndParByPubId&pubId="
     + pubId;
  }
 });

 // 定义顶部工具栏上的预览按钮
 var previewBtn = new Ext.Button({
  text : '预览',
  handler : function() {
   showTip();
   location.href = Ext.appRootPath
     + "/publicationViewAction.do?method=getAllColumnAndArticles&pubId="
     + pubId;
  }
 });

 // 定义顶部工具栏上的删除文章按钮
 var deleteArticleBtn = new Ext.Button({
  text : '删除文章',
  handler : function() {
   Ext.Msg.alert('提示', '请选择一篇文章后再进行删除操作!');
  }
 });

 // 定义顶部工具栏上的删除栏目按钮
 var deletePubcolBtn = new Ext.Button({
  text : '删除栏目',
  handler : function() {
   Ext.Msg.alert('提示', '请选择一个栏目后再进行删除操作!');
  }
 });

 // 定义定于工具栏上的提交按钮
 var submitBtn = new Ext.Button({
  text : '提交',
  handler : function() {

  }
 });

 // 定义顶部工具栏
 var topToolbar = new Ext.Toolbar({
  autoWidth : true,
  autoHeight : true,
  style : 'border:1px solid;',
  renderTo : 'north-div',
  items : ['-', backBtn, '-', modifyPublicationBtn, '-', modifyColumnBtn,
    '-', deletePubcolBtn, '-', deleteArticleBtn, '-',
    integrationEditBtn, '-', previewBtn, '-', submitBtn, '-']
 });

 // 定义查找工具栏上的开始查找按钮
 var startFindBtn = new Ext.Button({
  text : '开始查找',
  handler : function() {
   if (findForm.form.isValid()) {
    var tempParam = Ext.encode(findForm.getForm().getValues(false));
    ds.baseParams = {
     infoJsonStr : tempParam
    }
    ds.reload();
   }
  }
 });
 // 定义查找工具栏
 var findToolbar = new Ext.Toolbar({
  autoWidth : true,
  autoHeight : true,
  style : 'border:1px solid;',
  items : ['-', startFindBtn, '-']
 });

 // 定义查找form表单
 var findForm = new Ext.form.FormPanel({
  el : Ext.getBody(),
  title : '查找信息',
  width : document.body.clientWidth - 200,
  height : 150,
  labelWidth : 70,
  waitMsgTarget : true,
  border : false,
  bodyStyle : 'padding:10px;',
  frame : true,
  tbar : findToolbar,
  layout : 'column',
  items : [{
   columnWidth : 1,
   layout : 'column',
   border : false,
   items : [{
    columnWidth : .34,
    layout : 'form',
    border : false,
    items : [{
     xtype : 'datefield',
     fieldLabel : '分类时间',
     name : 'startDate',
     height : 22,
     anchor : '95%'
    }]
   }, {
    columnWidth : .33,
    layout : 'form',
    border : false,
    labelWidth : 40,
    items : [{
     xtype : 'datefield',
     fieldLabel : '&nbsp;&nbsp;至',
     name : 'endDate',
     labelSeparator : '',
     height : 22,
     anchor : '86%'
    }]
   }, {
   columnWidth : .67,
   layout : 'form',
   border : false,
   items : [{
    xtype : 'textfield',
    fieldLabel : '标题',
    name : 'artTitle',
    height : 22,
    anchor : '93%'
   }, {
    xtype : 'textfield',
    fieldLabel : '内容',
    name : 'artContent',
    height : 22,
    anchor : '93%'
   }]
  }]
  }]
 });

 // 执行查找form表单的渲染
 findForm.render();
 
 
 // 定义选择模型
 var sm = new Ext.grid.CheckboxSelectionModel({
  handleMouseDown : Ext.emptyFn
 });
 // 定义列模型
 var cm = new Ext.grid.ColumnModel([sm, {
  header : '文章ID',
  dataIndex : 'name',
  hidden : true
 }, {
  header : '文章标题',
  dataIndex : 'rollnum',
  align : 'center'
 }, {
  header : '编写日期',
  dataIndex : 'yearnum',
  align : 'center'
 }, {
  header : '分类',
  dataIndex : 'timelimit',
  align : 'center'
 }]);

 // 定义数据解析器
 var ds = new Ext.data.Store({
  baseParams : {
   size : 10
  },
  proxy : new Ext.data.HttpProxy({
   url :url
  }),
  pruneModifiedRecords : true,
  reader : new Ext.data.JsonReader({
   totalProperty : 'allRowCount',
   root : 'root'
  }, [{
   name : 'name'
  }, {
   name : 'rollnum'
  }, {
   name : 'yearnum'
  }, {
   name : 'timelimit'
  }]),
  listeners : {
   loadexception : function() {
    Ext.Msg.alert('提示', '对不起，与数据库的交互出错！');
   }
  }
 });
// 定义Grid
 var grid = new Ext.grid.GridPanel({
  el : Ext.getBody(),
  title : '可选文章列表',
  sm : sm,
  ds : ds,
  cm : cm,
  frame : true,
  tbar : canChooseToolbar,
  height : document.body.clientHeight - 177,
  width : document.body.clientWidth - 200,
  enableColumnMove : false,
  stripeRows : true,
  trackMouseOver : true,
  loadMask : {
   msg : '正在加载数据...'
  },
  viewConfig : {
   forceFit : true
  },
  bbar : new Ext.PagingToolbar({
   pageSize : 10,
   store : ds,
   displayInfo : true,
   displayMsg : '显示第 {0} 条到 {1} 条记录， 共 {2} 条',
   emptyMsg : "没有记录"
  })
 });

 // 执行可选文章列表的渲染
 grid.render();
ds.load();
});