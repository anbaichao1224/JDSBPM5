//所有的执行动作

Ext.namespace("JDS.exp.actions");


/**
 * 各动作执行完后的调用函数列表
 * 其key和fns中的key一一对应,其参数为对应fns中的函数的返回值
 * 每一个key对应一个函数数组
 */
JDS.exp.actions.afterFns = {
  download:[
    function() {
      //alert("download after");
    }
  ]
}

/**
 * 所有动作
 * 所有事件等要执行的动作,包括菜单,工具条,右键菜单等
 * 其key和afterFns中的key为一一对应
 * 其参数与触发此调用的ext对应事件相一致
 *
 * 其this为当前选中对象
 */

JDS.exp.actions.fns = {
  /*
   *显示或编辑指定文件
   * path 相对路径
   * type 文件类型,xml,html,java等
   * codeType 文件的编码方式 gb2312,utf8等
   */
  showAndEdit:function(path,type,codeType){
//    var url="/showAndEdit.jsp?path="+path+"&type="+(type||"")+"&codeType="+(codeType||"");
    var url="/showAndEdit.jsp";
    var params={
      path:path,
      type:type||"",
      codeType:codeType||""
    };
    openUrlWin(url,"查看或编辑",params);
  },

  /*
   *打开指定window的指定路径
   * win 窗口
   * path 打开的目标路径
   * currentView 打开时的查看方式
   * historyFlag 此参数为true时,不刻录到历史记录中
   */
  changeCurrentPath:function (win, path, currentView,historyFlag) {
    if (win.exp)win = win.exp;
    win.openPath( path, currentView,historyFlag);
  },

  //默认打开方法
  open:function() {
    var obj = this[0]
    if (obj) {
      JDS.exp.actions.exec("changeCurrentPath", [obj.win,obj.path])
    } else {
      alert("请选中要打开的对象");
    }
  },


  //----------以下是 文件 主菜单的执行命令---------
  //上传文件
  upload:function() {
    var destPath = this[0].path||this[0].pPath;//JDS.exp.getCurrentWin().getCurrentPath();
    JDS.ajax.exp.Connection.uploadFile(destPath);
  },

  //下载选中的文件
  download:function() {
    if (this[0].path) {
      var ps = [];
      for (var i = 0; i < this.length; i++) {
        ps.push(this[i].path);
      }
      JDS.ajax.exp.Connection.downloadFile(ps);
    } else {
      alert("没有选中对象")
    }
  },

  //新建文件夹
  createFolder:function() {
    var path="";
    var obj=this[0];
    if(obj.eType=="tree"){
      path=obj.path;
    }else if(obj.eType=="obj"){
      path=obj.data.pid;
    }else{
      path=obj.pPath;
    }
    JDS.ajax.exp.Connection.createFolder(path);
  },

  //删除选中的文件
  fileDelete:function(){
    var path=this[0].path;
    var pPath=this[0].win.getParentPath(path);
    var ps=[path];
    for(var i=1;i<this.length;i++){
      ps.push(this[i].path);
    }

    JDS.ajax.exp.Connection.deleteFile(ps,pPath);
  },

  //重命名
  fileRename:function(){
    this[0].win.startRename();
  },

  //退出 即关闭当前窗口
  exit:function(){
    this[0].win.close();
  },
  //----------以上是 文件 主菜单的执行命令---------

  //----------以下是 编辑 主菜单的执行命令---------
  //撤消
  undo:function(){
    //todo
  },
  //重做
  redo:function(){
    //todo
  },

  //剪切
  cut:function(){
    if(this[0].path){
      JDS.setAllFlag("cut",true);
      var ps=[];
      for(var i=0;i<this.length;i++){
        ps.push(this[i].path);
      }
      JDS.exp.setClip(ps);
    }
  },
  //复制
  copy:function(){
    if(this[0].path){
      JDS.setAllFlag("cut",false);
      var ps=[];
      for(var i=0;i<this.length;i++){
        ps.push(this[i].path);
      }
      JDS.exp.setClip(ps);
    }
  },
  //粘贴
  paste:function(){
    JDS.setAllFlag("cut",false);
    var p=this[0].path||this[0].pPath;
    this[0].win.pasteTo(p);
  },
  
  //复制到
  copyto:function(){
    var path=this[0].path;
    if(path){
      var win=this[0].win;
      var ps=[];
      for(var i=0;i<this.length;i++){
        ps.push(this[i].path);
      }

      JDS.exp.getChooser().show({
        btnText: '确定',
        dlgTitle: '复制到...',
        dlgHint:  '选中项目',
        fn:function(p) {
          win.copyTo(ps,p);
        }
      });
    } else {
      alert("未选中对象");
    }
  },

  //移动到
  moveto:function(){
    var path=this[0].path;

    if(path){
      var win=this[0].win;
      var ps=[];
      for(var i=0;i<this.length;i++){
        ps.push(this[i].path);
      }

      JDS.exp.getChooser().show({
        btnText: '确定',
        dlgTitle: '移动到...',
        dlgHint:  '选中项目',
        fn:function(p) {
          win.moveTo(ps,p);
        }
      });
    } else {
      alert("未选中对象");
    }
  },

  //全选
  selectAll:function(){
    this[0].win.selectAll();
  },
  //清除选中
  clearSelected:function(){
    this[0].win.clearSelected();
  },

  //----------以上是 编辑 主菜单的执行命令---------

  //----------以下是 查看 主菜单的执行命令---------
  //显示文件属性
  fileAttrib:function() {
    //todo
    new Exp.attribDialog(this[0].path).show();
  },

  //按名称排序
  sortByName:function(){
    this[0].win.sortByName();
  },
  sortByType:function(){
    this[0].win.sortByType();
  },
  sortBySize:function(){
    this[0].win.sortBySize();
  },
  
  //向上一层
  goParentFolder:function() {
    var w = this[0].win;
    w.goParentFolder();
  },
  //后退
  goBack:function() {
    this[0].win.goBack();
  },
  //前进
  goForward:function() {
    this[0].win.goForward();
  },
  //刷新当前目录
  refresh:function() {
    JDS.exp.refreshCurrentWin();
  },
  //刷新所有窗口的当前目录  暂时未用
  refreshAll:function() {
    JDS.exp.refreshAllWin();
  },
  //----------以上是 查看 主菜单的执行命令---------

  //----------以下是 工具 主菜单的执行命令---------
  // 工具->编辑
 bookMark:function() {
   var path = this[0].path;
   if (path) {
     var param = {
       fileName: path
     };
     JDS.ajax.exp.Connection.getFileName(path, 'edit');
   }
 },

//修改属性
  modiflyProfile:function() {
    alert("modiflyProfile");
//    commonFn.popupDialog.call({
//      url: '/deepcms/user/',
//      width: 892,
//      height: 530
//    });
  },
  //配置
  config:function(){
    alert("config");
  },
  //----------以上是 工具 主菜单的执行命令---------

  //关于
  about:function(){
    alert("关于");
  }
}
