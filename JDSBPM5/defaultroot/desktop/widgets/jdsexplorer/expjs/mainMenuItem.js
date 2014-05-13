//主菜单项
/**
* 所有菜单项的格式为:Key-value形式
* value的格式为
* {id , text ,menu:[],icon,cmdName}
* 其中menu是其子菜单项的key数组
* cmdName是实对应的点击处理函数的key
* statusFnName 是其自身的状态处理函数名,以全局状态变量来设置自身的状态,在菜单显示前调用
 */

Ext.namespace("JDS.exp.mainMenu");

//主菜单大标题,以id为标识
JDS.exp.mainMenu.titles = [
  "file","edit","view","tools","help"
];


JDS.exp.mainMenu.items = {

  /*-----以下为主菜单项-------*/
  file:{id:"menu_file",text:"文件",menu:["upload","download","createnew","fileDelete","fileRename","exit"]},
  edit:{id:"menu_edit",text:"编辑",menu:["undo","redo","-","cut","copy","paste","-","copyto","moveto","-","selectAll","clearSelected"]},
  view:{id:"menu_view",text:"查看",menu:["fileAttrib","viewAS","sortBy","goto","-","refresh"]},
  tools:{id:"menu_tools",text:"工具",menu:["bookMark","modiflyProfile","config"]},
  help:{id:"menu_help",text:"帮助",menu:["about"]},

  /*-----以上为主菜单项-------*/


  /*----以下是  文件  菜单的子菜单项---*/
  upload: {
    text: "上传文件",
    id: 'menuItem_uploadFile',
    icon: '/desktop/widgets/explorer/images/up.png',
    cmdName:"upload"
  },
  download:{
    text: '下载(D)',
    id:"menuitem_download",
    icon: '/desktop/widgets/explorer/images/download.gif',
    cmdName:"download"
    ,
    statusFnName:"copy"
  },
  createnew:{
    text  :  '新建',
    id:"menuitem_new",
    icon  : '/desktop/widgets/explorer/images/new.gif',
    menu  :["createFolder","-","createUpload","createShare"]
  },

  createFolder:{
    id: 'btnFile_createFolder',
    text: '新建文件夹',
    icon: '/desktop/widgets/explorer/images/newdir.gif',
    tooltip: '<b>新建文件夹</b><br />新建文件夹',
    cmdName:"createFolder"
  },
  createUpload:    {
    text: '新建上传',
    disabled :true,
    icon: '/desktop/widgets/explorer/images/upload.gif',
    id: 'menuItem_upload',
    cmdName:"createUpload"
  },
  createShare: {
    text: '新建共享',
    disabled :true,
    icon: '/desktop/widgets/explorer/images/net.gif',
    id: 'menuItem_createShare'
  },
  
  fileDelete: {
    id: 'btnFile_delete',
    text: '删除',
    iconCls: 'page_delete',
    tooltip: '<b>删除</b><br />删除指定项目',
    statusFnName:"copy",
    cmdName:"fileDelete"
  },
  fileRename: {
    id: 'btnRename',
    text: '重命名',
    cls: 'x-btn-text-icon',
    icon: '/desktop/widgets/explorer/images/rename.gif',
    tooltip: '<b>重命名</b><br />重命名所选的项目',
    statusFnName:"copy"
  },
  exit: {
    id:"menuItem_exit",
    text: '退出(X)',
    icon: '/desktop/widgets/explorer/images/out.gif'
  },

  /*----以下是  编辑  菜单的子菜单项---*/
  undo: {
    text: "撤销",
    disabled :true,
    icon: '/desktop/widgets/explorer/images/cancel.gif'
  },
  redo: {
    text: "重做",
    disabled :true,
    icon: '/desktop/widgets/explorer/images/redo.gif'
  },

  cut: {
    id: 'btnFile_cut',
    text: '剪切',
    disabled :true,
    icon: '/desktop/widgets/explorer/images/cut.gif',
    cls: 'x-btn-text-icon',
    tooltip: '<b>剪切</b><br />将选定的项目移动到剪贴板',
    cmdName:"cut",
    statusFnName:"copy"
  },
  copy: {
    id: 'btnFile_copy',
    text: '复制',
    disabled :true,
    cls: 'x-btn-text-icon',
    icon: '/desktop/widgets/explorer/images/copy.gif',
    tooltip: '<b>复制到剪贴板</b><br />将选定的项目复制到剪贴板',
    cmdName:"copy"
    ,statusFnName:"copy"
  },
  paste: {
    id: 'btnFile_paste',
    text: '粘贴',
    disabled :true,
    cls: 'x-btn-text-icon',
    icon: '/desktop/widgets/explorer/images/paste.gif',
    tooltip: '<b>粘贴</b><br />粘贴剪贴板的内容',
    cmdName:"paste"
    ,statusFnName:"paste"
    },
  copyto: {
    id: 'btnFile_copyto',
    text: '复制到',
    disabled :true,
    cls: 'x-btn-text-icon',
    icon: '/desktop/widgets/explorer/images/copyto.gif',
    tooltip: '<b>复制到指定位置</b><br />复制到指定位置',
    cmdName:"copyto"
    ,statusFnName:"copy"  //状态 应该和copy一样
  },
  moveto: {
    text: '移动到文件夹',
    disabled :true,
    iconCls: 'page_goto',
    id: 'btnFile_moveto',
    tooltip: '<b>移动到...</b><br />将选定的项目移动到指定目标',
    cmdName:"moveto",
    statusFnName:"copy" //状态 应该和copy一样
  },

  selectAll:{
    id:"selectAll_item",
    text: '全选',
    icon: '/desktop/widgets/explorer/images/02.png',
    tooltip: '<b>全选选定</b><br />选定窗口中的所有项目',
    cmdName:'selectAll'
  },
  clearSelected: {
    id: 'btn_clearSelected',
    text: '取消选定',
    icon: '/desktop/widgets/explorer/images/09.png',
    cls: 'x-btn-icon',
    tooltip: '<b>取消</b><br />清除选区',
    cmdName:"clearSelected",
    statusFnName:"copy"
  },

  /*----以上是  编辑  菜单的子菜单项---*/

  /*----以下是  查看  菜单的子菜单项---*/
  fileAttrib: {
    id: 'btnFile_attrib',
    text: '属性',
    iconCls: 'page_attach',
    tooltip: '<b>显示属性</b><br />查看项目的详细属性',
    statusFnName:"copy"
  },
  viewAS: {
     id: 'btnViewAs',
     icon: '/desktop/widgets/explorer/images/programs.gif',
     cls: 'x-btn-text-icon',
     text: '查看...',
//     disabled :true,
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

  sortBy: {
    id:"menuItem_sortBy",
    text: '排列图标',
    icon: '/desktop/widgets/explorer/images/iconlist.gif',
    menu: ["sortByName","sortByType","sortBySize"]
  },
      sortByName: {
        id: 'menuItem_sortByName',
        text: '按名称',
        icon: '/desktop/widgets/explorer/images/12.png'
      },
      sortByType:{
        id: 'menuItem_sortByType',
        text: '按类型',
        icon: '/desktop/widgets/explorer/images/06.png'
      },
      sortBySize:{
        id: 'menuItem_sortBySize',
        text: '按大小',
        icon: '/desktop/widgets/explorer/images/08.png'
      },

  goto:{
    id:"menuItem_goto",
    text : '转到...',
    icon  : '/desktop/widgets/explorer/images/goto.gif',
    menu :  ["goParentFolder","goBack","goForward"]
  },
  goParentFolder: {
    id: 'btnParentFolder',
    text: '向上一级',
    icon: '/explorer/codebase/imgs/toolbar/FolderUp.gif',
    cls: 'x-btn-icon',
    tooltip: '<b>向上一级</b><br />转到上一级'
  },
  goForward: {
    id:"menuItem_goForward",
    text:"前进",
    icon: '/desktop/resources/images/default/ie_left.png'
  },
  goBack: {
    id:"menuItem_goBack",
    text:"后退",
    icon: '/desktop/resources/images/default/ie_right.png'
  },
  refresh:{
    id:"menuItem_refresh",

    tooltip: '<b>刷新</b><br />刷新当前的内容',
    iconCls: 'page_refresh',
    text:"刷新",
    cls: 'x-btn-text-icon',
    cmdName:"refresh"
  },
  /*----以上是  查看  菜单的子菜单项---*/


  /*----以下是  编辑  菜单的子菜单项---*/
  bookMark: {
    text: "编辑",
    id: 'menuItem_bookMark',
//    disabled :true,
    icon: '/desktop/widgets/explorer/images/edit.gif',
    statusFnName:"copy"
  },
  modiflyProfile: {
    id:"menuItem_modify",
//    disabled:true,
    text: "修改属性",
    statusFnName:"copy"
  },
  config: {
    id:"menuItem_config",
  text: "设置",
//  disabled :true,
  icon: '/desktop/widgets/explorer/images/setting.gif',
    statusFnName:"copy"
},

  /*----以上是  编辑  菜单的子菜单项---*/

  /*----以下是  帮助  菜单的子菜单项---*/
  about: {
    text: '关于(A)',
    icon: '/desktop/widgets/explorer/images/about.gif'
  },
  /*----以上是  帮助  菜单的子菜单项---*/

  open:{
    id: 'openFileOrFolder',
    text: '打开',
    iconCls: 'page_attach',
    tooltip: '打开文件夹或文件',
    cmdName:"open",
    statusFnName:"open"
  }
};
