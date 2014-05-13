//工具条 条目

Ext.namespace("JDS.exp.toolbar");

JDS.exp.toolbar.titles=["viewAS","fileAttrib","-","sortByName","sortByType","sortBySize","-","goParentFolder","fileDelete","cut","copy","paste",
  "-","copyto","moveto","-","selectAll","clearSelected","-","refresh"];
JDS.exp.toolbar.uriTitles=["goBackList","goForwardList"];


JDS.exp.toolbar.items = {
  viewAS: {
    id: 'btnViewAs',
    icon: '/desktop/widgets/explorer/images/programs.gif',
    cls: 'x-btn-text-icon',
    text: '查看...',
    disabled :true,
    menu: ["thumb","icon","list","detail"]
  },
  thumb:{
    id: 'btnViewAs_thumb',
    text: '缩略图',
    icon: '/desktop/widgets/explorer/images/suolvetu.gif',
    cmdName:"thumbView"
  },
  icon:{
    id: 'btnViewAs_icon',
    text: '图标',
    icon: '/desktop/widgets/explorer/images/table.gif',
    cmdName:"iconView"
  },
  list:{
    id: 'btnViewAs_list',
    text: '列表',
    icon: '/desktop/widgets/explorer/images/liebiao.gif',
    cmdName:"listView"
  },
  detail:{
    id:'btnViewAs_detail',
    text:'详细资料',
    icon:'/desktop/widgets/explorer/images/xiangxi.gif',
    cmdName:"detailView"
  },

  sortByName: {
    id: 'btn_sortByName',
    text: '',
    cls: 'x-btn-text-icon',
    icon: '/desktop/widgets/explorer/images/12.png',
    tooltip: '<b>按名称排序</b>'
  },
  sortByType:{
    id: 'btn_sortByType',
    text: '',
    cls: 'x-btn-text-icon',
    icon: '/desktop/widgets/explorer/images/06.png',
    tooltip: '<b>按类型排序</b>'
  },
  sortBySize:{
    id: 'btn_sortBySize',
    text: '',
    cls: 'x-btn-text-icon',
    icon: '/desktop/widgets/explorer/images/08.png',
    tooltip: '<b>按大小排序</b>'
  },

  fileAttrib: {
    id: 'btnFile_attrib',
    disabled :true,
    text: '',
    iconCls: 'page_attach',
    tooltip: '<b>显示属性</b><br />查看项目的详细属性',
    statusFnName:"copy"
  },

  fileDelete: {
    id: 'btnFile_delete',
    disabled :true,
    text: '',
    iconCls: 'page_delete',
    tooltip: '<b>删除</b><br />删除指定项目',
    statusFnName:"copy"
  },
 //编辑操作
  cut: {
    id: 'btnFile_cut',
    text: '',
    disabled :true,
    icon: '/desktop/widgets/explorer/images/cut.gif',
    cls: 'x-btn-text-icon',
    tooltip: '<b>剪切</b><br />将选定的项目移动到剪贴板',
    cmdName:"cut"
  },
  copy: {
    id: 'btnFile_copy',
    text: '',
    disabled :true,
    cls: 'x-btn-text-icon',
    icon: '/desktop/widgets/explorer/images/copy.gif',
    tooltip: '<b>复制到剪贴板</b><br />将选定的项目复制到剪贴板',
    cmdName:"copy"
  },
  paste: {
    id: 'btnFile_paste',
    text: '',
    disabled :true,
    cls: 'x-btn-text-icon',
    icon: '/desktop/widgets/explorer/images/paste.gif',
    //icon :  '/ajaxee/resources/icon/file_paste.gif',
    tooltip: '<b>粘贴</b><br />粘贴剪贴板的内容',
    cmdName:"paste"
  },
  copyto: {
    id: 'btnFile_copyto',
    text: '',
    disabled :true,
    cls: 'x-btn-text-icon',
    icon: '/desktop/widgets/explorer/images/copyto.gif',
    tooltip: '<b>复制到</b><br />复制到指定位置',
    cmdName:"copyto"
  },

  moveto: {
    text: '',
    disabled :true,
    iconCls: 'page_goto',
    id: 'btnFile_moveto',
    tooltip: '<b>移动到...</b><br />将选定的项目移动到指定目标',
    cmdName:"moveto"
  },


 selectAll:{
    id:"selectAll_item",
    text: '',
    icon: '/desktop/widgets/explorer/images/02.png',
    tooltip: '<b>全选选定</b><br />选定窗口中的所有项目',
    cmdName:'selectAll'
  },
  clearSelected: {
    id: 'btn_clearSelected',
    text: '',
    disabled:true,
    icon: '/desktop/widgets/explorer/images/09.png',
    cls: 'x-btn-icon',
    tooltip: '<b>取消选定</b><br />清除选区',
    cmdName:"clearSelected",
    statusFnName:"copy"
  }

  ,
  goParentFolder:{
    id: 'btnParentFolder',
    text: '',
    icon: '/explorer/codebase/imgs/toolbar/FolderUp.gif',
    cls: 'x-btn-icon',
    tooltip: '<b>向上一级</b><br />转到上一级',
    disabled:true,
    cmdName:"goParentFolder"
  }
  ,

   refresh:{
    id:"btn_refresh",
    tooltip: '<b>刷新</b><br />刷新当前的内容',
    iconCls: 'page_refresh',
    text:"",
    cls: 'x-btn-text-icon',
    cmdName:"refresh"
  }
}

JDS.exp.toolbar.uriItems = {
  goBackList: {
    id:"btnGoBack",
    text: '<div style="display :inline;width : 20px;height:20px"><table><tr><td><img style="display :inline; width:24px;height:24px"  src="/desktop/resources/images/default/ie_left.png" /></td><td><div style="display : inline">后退</div></td></tr></table></div>',
    disabled :true,
    cmdName:"goBack"

  },
  goForwardList: {
    id: 'btnGoforward',
    text: '<div style="display :inline;width : 20px;height:24px"><table><tr><td><img style="display :inline; width:24px;height:24px"  src="/desktop/resources/images/default/ie_right.png" /></td><td><div style="display : inline">前进</div></td></tr></table></div>',
    disabled :true,
    cmdName:"goForward"
  }
}