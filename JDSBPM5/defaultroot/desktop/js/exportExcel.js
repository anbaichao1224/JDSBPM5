//把页面上的grid里的内容导出到excel,用javascript操作

Ext.namespace("remote.excel");

remote.excel = (

function() {
  var getExcelObj=function() {
    var obj;
    try {
      obj = new ActiveXObject("Excel.Application");
    } catch(e) {
    }
    return obj;
  };

  var createExcel=function(name, titles, rows) {
    var obj = getExcelObj();
    if (!obj) {
      alert("您必须安装Excel电子表格软件，同时浏览器须允许使用“ActiveX 控件”");
      return;
    }

    var book = obj.workbooks.add();
    var sheet = book.worksheets(1);
    obj.DisplayAlerts = false;
    obj.visible = true;

    var colNum = titles.length;
    sheet.Range(sheet.Cells(1, 1), sheet.Cells(1, colNum)).mergecells = true;
    sheet.Range(sheet.Cells(1, 1), sheet.Cells(1, colNum)).value = name;
    sheet.Range(sheet.Cells(1, 1), sheet.Cells(1, colNum)).HorizontalAlignment = -4108;//居中
    sheet.Range(sheet.Cells(1, 1), sheet.Cells(1, colNum)).Font.Bold = true;

    for (var i = 0; i < colNum; i++) {
      sheet.Cells(2, i + 1).Value = titles[i];
      sheet.Columns(i + 1).NumberFormatLocal = "@";
    }
    sheet.Range(sheet.Cells(2, 1), sheet.Cells(2, colNum)).HorizontalAlignment = -4108;//居中
    sheet.Range(sheet.Cells(2, 1), sheet.Cells(2, colNum)).Font.Bold = true;

    for (var i = 0; i < rows.length; i++) {
      var row = rows[i];
      for (var j = 0; j <= row.length; j++) {
        sheet.Cells(3 + i,j + 1).Value = row[j];
      }
    }
    sheet.Columns.AutoFit;
    obj.UserControl = true;

    var fpath=obj.Application.GetSaveAsFilename((name||"book1")+".xls");
    if(fpath){
      book.SaveAs(fpath);
    }
    sheet=null;
    book=null;
    obj=null;
  };

  var exportExcelImpl=function(grid,name){
      var store = grid.getStore();
      var count = store.getCount();
      if (!count || count == 0) {
        alert("没有数据可导出");
        return;
      }
      name=name||grid.title||"";

      var titles=[];
      var rows=[];
      var idx=[];

      var idxNum=[];

      var cm = grid.getColumnModel();
      var colCount = cm.getColumnCount();
      for (var i = 0; i < colCount; i++) {
        var key=cm.getDataIndex(i);
        if(!cm.isHidden(i)&&key){
          idx.push(key);
          idxNum.push(i);
          titles.push(cm.getColumnHeader(i))
        }
      }
//      for(var i=0;i<count; i++) {
//        var record = store.getAt(i);
//        var row = [];
//        for (var j = 0; j < titles.length; j++) {
//          row.push(record.get(idx[j])||"");
//        }
//        rows.push(row);
//      }

      var view=grid.getView();
      for(var i=0;i<count;i++){
        var row=[];
        for(var j=0;j<titles.length;j++){
          row.push(view.getCell(i,idxNum[j]).innerText);
        }
        rows.push(row);
      }
      createExcel(name,titles,rows);
  };


  var loadFn=function(store,records,ops){
    var grid= store.exportExcelGrid;
    var name=store.exportExcelName;
    clearExportData(store);
    exportExcelImpl(grid,name);
  };

  var clearExportData=function(store){
    store=store||this;
    store.un("load",loadFn);
    store.un("loadexception",clearExportData);
    delete store.exportExcelName;
    delete store.exportExcelGrid;
  }


  var max=100;
  return {
    exportExcel:function (grid, name,isAll){
      var store = grid.getStore();
      var count = store.getCount();

      var tCount=store.getTotalCount();
      isAll = isAll === false ? false : true;
      if ((isAll && tCount > max)||count>max) {
        if (!confirm("数据量太大,如果继续执行可能会造成系统运行缓慢,是否继续?")) {
          return;
        }
      }
      if(isAll&&(tCount>count)){
        var tbar=grid.getBottomToolbar();
        var pn=tbar.paramNames||{};
        var tmp={};
        tmp[pn.start] = 0;
        tmp[pn.limit] = tCount;
        store.on("load",loadFn);
        store.on("loadexception",clearExportData,store)
        store.exportExcelGrid=grid;
        store.exportExcelName=name||"";
        store.load({params:tmp});
      }else{
        exportExcelImpl(grid,name);
      }
      return;
    }
  }
})();
