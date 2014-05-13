<%@ page import="java.util.Set" %>
<%@ page import="net.itjds.common.dbutil.TableAndJava" %>
<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2009-8-25
  Time: 15:58:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="ww" %>

<%
  String contextpath = request.getContextPath() + "/";
  String urlContext = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + contextpath;
  TableAndJava tableAndJava=new TableAndJava("bpm");
%>
<html>
<head><title>显示表信息</title>

<link rel="stylesheet" type="text/css" href="<%=contextpath%>js/ext/resources/css/ext-all.css"/>
<script type="text/javascript" src="<%=contextpath%>js/ext/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=contextpath%>js/ext/ext-all.js"></script>
<script type="text/javascript" src="<%=contextpath%>js/ext/locale/ext-lang-zh_CN.js"></script>

<script type="text/javascript">
    var esbKeyList='<ww:property value="$R('esbKeyList')" escape="false"/>';
    var flowType='<ww:property value="$R('flowType')" escape="false"/>';

var context = '<%=contextpath%>';
var tableWin = null;
function initWin() {
  var winCfg = {
    id:'expressionEditorWin',
    title:'表信息',
    layout:'border',
    closeAction :'hide',
    closable:false,
    // maximizable:true,
    // plain:true,
    width:850,
    height:600,
    //minWidth:850,
    //maxWidth:900,
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
              title: '列信息',
              //height:700,
              autoWidth:true,
              html:"<div id='colsInfo' style='border:0;text-align:left'></div>"
            },
            buttons:[{text:'增加列',handler:addCol},
              {text:'确 定',handler:saveCol},
              {text:'取消',handler:function() {
                if (tableWin.canEditRow > 0) {
                  var grid = getColGrid();
                  var store = grid.getStore();
                  var row = tableWin.canEditRow;
                  var count = store.getCount();
                  if (count > row) {
                    store.remove(store.getAt(count - 1));
                  }
                }
              }}]
          },{
          region:'west',
          width:260,
          autoScroll:true,
          collapsible: true,
          title:'所有表',
          split:true,
          //layout:'accordion',
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
}

var Tree = Ext.tree;
function getTree(currel) {
  var tree = new Tree.TreePanel({
    el:currel,
    animate:true,
    enableDD:false,
    loader: new Tree.TreeLoader({dataUrl:"/design/tableTree.action"}),
    lines: true,
    selModel: new Ext.tree.MultiSelectionModel(),
    containerScroll: true,
    autoHeight:true,
    autoWidth:true,
    rootVisible:false
  });
  var root = new Tree.AsyncTreeNode({
    text: '对象',
    leaf : false,
    cls : 'folder',
    draggable:false,
    id:'treeRoot'
  });
  tree.setRootNode(root);
  tree.on('beforeload', function(node) {
    var name = node.id;
    name = name.replace(/^type_/, "");
    var url = '/design/tableTree.action?tableName=' + name + "&type=" + (node.attributes.type || "")+'&esbKeyList='+esbKeyList+'&flowType='+flowType;
//                alert(url)
    tree.loader.dataUrl = url;
  });
  tree.on('click', function(node, e) {
    var grid = getColGrid();
    grid.getStore().rejectChanges();
    var panel = Ext.getCmp("colPanel2");
    if (node.attributes.type == "col") {
      e.stopEvent();
      loadColData(node.id, "col");
      tableWin.table = node.id;
      panel.setTitle("列信息");
    } else {
      var name = node.id;
      name = name.replace(/^type_/, "");
      loadColData(name, "table");
      panel.setTitle("表信息");
    }
  });

  tree.render();
}

/**
 * 加载列表显示的信息
 * @param tableName　表名
 * @param type　为table时，显示表名和其中文名,为col时,显示其列的信息
 */
function loadColData(tableName, type) {
  var url = "/design/tableCol.action?tableName=" + tableName + "&type=" + type;
  tableWin.currType = type;
  Ext.Ajax.request(
  {
    url: url,
    //        params:param,
    success: function(rs) {
      var data = Ext.decode(rs.responseText);
      showGridData(data, "colsInfo");
    },
    failure: function() {
      alert("查询出错");
    }
  });
}

/**
 * 保存修改的表数据
 */
function saveCol() {
  var grid = getColGrid();
  var store = grid.getStore();

  var addRow = tableWin.canEditRow;
  var count = store.getCount();
  var tableName = tableWin.table;
  var url = "/design/saveCol.action"
  var param = {tableName:tableName};
  param.type = tableWin.currType;

  var rs = store.getModifiedRecords();
  
  for (var i = 0; i < rs.length; i++) {
    var r = rs[i].data;

    if (tableWin.currType == "col") {
      for (var k in r) {
        var name = r["name"];
        param["colsMap['" + name + "'].name"] = name;
        param["colsMap['" + name + "'].cnname"] = r["cnname"];
        param["colsMap['" + name + "'].type"] = r["type"];
        param["colsMap['" + name + "'].length"] = r["length"];
        param["colsMap['" + name + "'].canNull"] = r["canNull"];
      }
    } else if (tableWin.currType == "table") {
      for (var k in r) {
        var name = r["name"];
        param['tabList[' + i + "].name"] = name;
        param['tabList[' + i + "].cnname"] = r["cnname"];
      }
    }
  }

  saveData(url, param);
}

/**
 * 提交到后台,保存数据
 * @param url
 * @param param
 */
function saveData(url, param) {
  Ext.Ajax.request(
  {
    url: url,
    params:param,
    method:"post",
    success: function(rs) {
      alert(rs.responseText.trim());
    },
    failure: function() {
      alert("保存出错");
    }
  });
}

//数据库的数据类型
var types;
<%
Set<String> types=tableAndJava.getDataType();
out.print("types="+types.toString().replaceAll("(\\w[^],]+)","['$1','$1']"));
out.println(";");
%>

//返回显示grid
function getColGrid() {
  var grid = Ext.getCmp("colsInfoGridPanel");
  return grid;
}

/**
 * 生成并显示表信息
 * @param data
 * @param divId
 */
function showGridData(data, divId) {
  if ((data instanceof Array) && (data[0] instanceof Array)) {
    var grid = Ext.getCmp(divId + "GridPanel");

    var cols = data[0];
    data.shift();
    var colM = [];
    var fields = [];
    var indexs = ['name','cnname','type','length','canNull'];

    var typeStore = new Ext.data.SimpleStore({
      fields: ['val','txt'],
      data :types
    });
    var typeComb = new Ext.form.ComboBox({
      displayField:'txt',
      store: typeStore,
      valueField:'val',
      typeAhead: true,
      mode: 'local',
      triggerAction: 'all',
      emptyText:'Select a state...',
      selectOnFocus:true
    });

    var ynstore = new Ext.data.SimpleStore({
      fields: ['val','txt'],
      data : [
        ["true","是"],
        ["false","否"]
      ]
    });

    var yesNoComb = new Ext.form.ComboBox({
      store: ynstore,
      displayField:'txt',
      valueField:'val',
      typeAhead: true,
      mode: 'local',
      forceSelection: true,
      triggerAction: 'all',
      emptyText:'Select a state...',
      selectOnFocus:true
    });

    var editors = [
      new Ext.form.TextField(),
      new Ext.form.TextField(),
      //          new Ext.form.TextField(),
      typeComb,
      new Ext.form.TextField(),
      yesNoComb
    ];
    for (var i = 0; i < cols.length; i++) {
      var editor = editors[i];
      var curDate = new Date().getTime();
      colM.push({header:cols[i],dataIndex:indexs[i],width:200,sortable: true,editable:true,editor:editor});

      fields.push({name:indexs[i]});
    }
    if (!grid) {
      var reader = new Ext.data.ArrayReader({}, colM);
      var store = new Ext.data.SimpleStore({
        fields: fields
      });
      store.loadData(data);
      grid = new Ext.grid.EditorGridPanel({
        id:divId + "GridPanel",
        store: store,
        columns:colM,
        clicksToEdit:1,
        //width:500,
        //height:500,
        enableColumnHide:false,
        frame:true
      });
      grid.on("render", resizeColPanel);

      grid.on('beforeedit', beforeColEdit);
      grid.render(divId);
    } else {
      grid.getColumnModel().setConfig(colM);
      grid.getStore().loadData(data, false);
    }
    tableWin.canEditRow = null;
  }
}

//重设显示表的大小
function resizeColPanel() {
  var obj = Ext.getCmp("colPanel");
  var grid = getColGrid();
  if (grid) {
    grid.setSize(obj.getSize().width - 2, obj.getSize().height - 66);
  }
}

/**
 * 表的中文名,列的中文名,数据长度,可否为空及新增行的所有数据 可编辑
 * @param e
 */
function beforeColEdit(e) {
  var row = e.row;
  var col = e.column;
  if (tableWin.canEditRow && row >= tableWin.canEditRow) {
    return true;
  } else {
    return col != 0 && col != 2;
  }
}

/**
 * 增加一列,grid上增加一行
 */
function addCol() {
  if (!tableWin.table) {
    alert("请先选择表");
    return;
  }
  var grid = getColGrid();

  var ks = grid.getStore().getAt(0).fields.keys;
  var count = grid.getStore().getCount();
  var d = {};
  var defaultValue = ['col','列名','VARCHAR2','10','true'];
  for (var i = 0; i < ks.length; i++) {
    d[ks[i]] = defaultValue[i];
  }
  var record = new Ext.data.Record(d);
  grid.getStore().add([record]);
  if (!tableWin.canEditRow || tableWin.canEditRow > count) {
    tableWin.canEditRow = count;
  }
}

function init() {
  Ext.BLANK_IMAGE_URL = context + 'js/ext/resources/images/default/s.gif';
  Ext.QuickTips.init();

  var data = [['列名','中文名','类型','长度','是否可为空']];

  initWin();
  showGridData(data, "colsInfo");
  getTree("treeDiv");
}
Ext.onReady(init);
</script>
</head>
<body>
</body>
</html>