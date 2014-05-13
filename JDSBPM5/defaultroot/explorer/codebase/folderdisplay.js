/*-----------------------------------------------------------------------
 * 
 *   类WINDOWS资源管理器界面JS代码。
 *   
 *   
 * 
 -----------------------------------------------------------------------*/
//---------------------全局变量 ------------------------------------------
var myLayout, myTree, myGrid, dhxFolders, myMenu, myToolbar;
var isFolder = true;
var leftpanel;

var fileContextMenu, folderContextMenu, treeContextMenu, bodyContextMenu;
var curFolder;//全局变量 当前的目录
var ifexpand = true; //判断左侧目录树是否隐藏
var gl_view_type = "ficon";
var gl_view_bg = "";
var xml_prefix = context + 'report/config/';

//-----------------------------------------

var xpPanel_slideActive = true; // Slide down/up active?
var xpPanel_slideSpeed = 20; // Speed of slide
var xpPanel_onlyOneExpandedPane = false; // Only one pane expanded at a time ?
var dhtmlgoodies_xpPane;
var dhtmlgoodies_paneIndex;
var savedActivePane = false;
var savedActiveSub = false;
var xpPanel_currentDirection = new Array();
var cookieNames = new Array();
var currentlyExpandedPane = false;
//------------------------------------------


/**
 * 
 * @param itemId
 * @param itemValue
 * @return
 */
function cellButtonClick(itemId, itemValue) {
	//alert(itemId);
	return true;
}
/**
 * 窗口加载
 * @return
 */
function doOnLoad() {
	init();
}

/**
 * 初始化应用界面 init();
 */
function init() {
	initWindow();
	//initToolBar();
	initContextMenu();
	initFolderTree();
	initFolders("");
	//initMenu();
	initLeftPanel();
	
	
}
/**
 * 初始化左侧面板
 * @return
 */
function initLeftPanel() {
	initDhtmlgoodies_xpPane(Array('文件夹与文件夹任务', '其他位置', '详细信息'),
	Array(true, true, false), Array('pane1', 'pane2', 'pane3'));
	ch_client = "batalf";

	ch_type = "mpu";
	ch_width = 468;
	ch_height = 60;
	ch_non_contextual = 4;
	ch_vertical = "premium";
	ch_default_category = "200001";
	ch_sid = "Chitika Premium";
	var ch_queries = new Array("javascript", "dhtml", "ipod",
			"software development", "website design", "Web design templates");
	var ch_selected = Math.floor((Math.random() * ch_queries.length));
	if (ch_selected < ch_queries.length) {
		ch_query = ch_queries[ch_selected];
		var dc_UnitID = 14;
		var dc_PublisherID = 3695;
		var dc_AdLinkColor = 'blue';
		var dc_adprod = 'ADL';
	}

}
/**
 * 初始化各部分右键菜单 
 * @return
 */
function initContextMenu() {

	var xml_prefix = 'codebase/config/';
	var gfxpath = 'codebase/imgs/toolbar/';
	treeContextMenu = new dhtmlXContextMenuObject('120', 0, gfxpath);
	treeContextMenu.menu.setGfxPath(gfxpath);
	treeContextMenu.menu.setMenuCSS("contextmenuTable");
	treeContextMenu.menu.secTableCSS = "contextsecondMenuTable";
	treeContextMenu.menu.loadXML(xml_prefix + "context_TreeMenuutf8.xml");
	treeContextMenu.setContextMenuHandler(treeContextMenuAct);

	folderContextMenu = new dhtmlXContextMenuObject('120', 0, gfxpath);
	folderContextMenu.menu.setGfxPath(gfxpath);
	folderContextMenu.menu.setMenuCSS("contextmenuTable");
	folderContextMenu.menu.secTableCSS = "contextsecondMenuTable";
	folderContextMenu.menu.loadXML(xml_prefix + "context_FolderMenuutf8.xml");
	folderContextMenu.setContextMenuHandler(folderContextMenuAct);

	fileContextMenu = new dhtmlXContextMenuObject('120', 0, gfxpath);
	fileContextMenu.menu.setGfxPath(gfxpath);
	fileContextMenu.menu.setMenuCSS("contextmenuTable");
	fileContextMenu.menu.secTableCSS = "contextsecondMenuTable";
	fileContextMenu.menu.loadXML(xml_prefix + "context_FileMenuutf8.xml");
	fileContextMenu.setContextMenuHandler(fileContextMenuAct);

	bodyContextMenu = new dhtmlXContextMenuObject('120', 0, gfxpath);
	bodyContextMenu.menu.setGfxPath(gfxpath);
	bodyContextMenu.menu.setMenuCSS("contextmenuTable");
	bodyContextMenu.menu.secTableCSS = "contextsecondMenuTable";
	bodyContextMenu.menu.loadXML(xml_prefix + "context_BodyMenuutf8.xml");
	bodyContextMenu.setContextMenuHandler(bodyContextMenuAct);

}

/**
 * 初始化WINDOW
 * 布局采用：Layout 3W 方式
 * 样式：dhx_blue
 */
function initWindow() {
	myLayout = new dhtmlXLayoutObject(document.body, "3T", "dhx_blue");
	var obj = document.getElementById("iebar");
	myLayout.cells("b").setWidth(100);

	myLayout.cells("a").hideHeader();
	myLayout.cells("b").setText("文件夹");
	myLayout.cells("c").setText("详细信息");
	myLayout.cells("c").hideHeader();
	myLayout.cells("a").setHeight(25);
	myLayout.cells("a").attachObject(obj)

}

/**
 * 初始化工具栏
 */
function initToolBar() {
	myToolbar = myLayout.attachToolbar();
	myToolbar.setOnClickHandler(toolbarclickeventhander);
	myToolbar.loadXML("codebase/config/_toolbarutf8.xml")
	myToolbar.showBar();
}

/**
 * 初始化菜单
 */
function initMenu() {
	var gfxpath = 'codebase/imgs/toolbar/';
	myMenu = myLayout.attachMenu();
	myMenu.setGfxPath(gfxpath);
	myMenu.loadXML("codebase/config/_menuutf8.xml");
	myMenu.setOnClickHandler(menuclickeventhander);
	myMenu.showBar();

}

/**
 * 初始化目录树
 */
function initFolderTree() {
	myTree = myLayout.cells("b").attachTree("0");
	
	myTree.setImagePath("codebase/imgs/menu/");
	myTree.enableDragAndDrop(true);
	myTree.setXMLAutoLoading("xmlFromDirTree.action");//加载目录
	
	myTree.loadXML("xmlFromDirTree.action?dir="+initdir,function(){
		
		  myTree.openItem(initPath);
          myTree.selectItem(initPath);
		 
	}); //加载目录
	//增加选择事件
	myTree.attachEvent("onSelect", initFolders);
	myTree.enableContextMenu(treeContextMenu);
	myTree.enableMultiselection(true);
	
 


	//onRightClick
	myTree.attachEvent("onDrag", function(sID, tID, sObj, tObj) {
		if (sID.lastIndexOf('.html')==-1){
			return alert('只能拖动表单文件');
		}
			var evalJs = function(o){
			Ext.namespace("EVAL");			
			refreshView(curFolder);
		}		
			JDS.ajax.Connection.LoadJsonFromUrl('move.action',evalJs,$H({sID:sID,tID:tID}));
			return true;
	})
}

/**
 * 初始化目录、文件 窗口
 * @param {Object} dir 选中的文件夹
 */
function initFolders(dir, findtext) {

	curFolder = dir;
	dhxFolders = myLayout.cells("c").attachFolders();
	dhxFolders.enableContextMenu(bodyContextMenu);
	dhxFolders.enableFolderContextMenu(folderContextMenu);
	
	
	dhxFolders.enableFileContextMenu(fileContextMenu);
	dhxFolders.setImagePath("codebase/imgs/");
	dhxFolders.setItemType("ficon");
	dhxFolders.setUserData("icons_src_dir", "images");
	dhxFolders.enableDragAndDrop(true);
	//添加右键菜单
	//增加拖动方法
	dhxFolders.attachEvent("onBeforeDrop", function(dropType, sID, tID) {
		if (sID.lastIndexOf('.html')==-1){
			return alert('只能拖动表单文件');
		}
		var tType = this.getItem(tID).data.dataObj.getAttribute("type");
		if (dropType == "in" && tType == "dir") {
			this.deleteItem(sID);
			var dropSrc = 'move.action';
			var evalJs = function(o){
		
			Ext.namespace("EVAL");			
			refreshView(curFolder);
		}		
			JDS.ajax.Connection.LoadJsonFromUrl('move.action',evalJs,$H({sID:sID,tID:tID}));

			//需调用文件处理程序 
			return false;
		} else {
			return true;
		}
	})
	//增加双击方法,当选中类型为文件时，调用预览方法；当选中类型为文件夹时，刷新目录。
	if (gl_view_type == "grid") {
		showDirContent(dir);
	} else {
		  var contextdir=dir;
		 
		  if (activityDefId&&activityDefId!=''){
			initdir=initdir+'/'+activityDefId
		  }
	
		if (!contextdir||contextdir==''){
			contextdir=initdir
		}
		

		dhxFolders.loadXML("XMLInDir.action?dir=" + contextdir,
				"codebase/types/ficon.xsl");
				
	}

	function doOnDblClick(id) {
		var tType = dhxFolders.getItem(id).data.dataObj.getAttribute("type");
		if (tType == "file") {
			var selectId = dhxFolders.getSelectedId();
			var a = new Act();
			a.edit(selectId);
		} else {
			showDirContent(id);
		}
	}
	dhxFolders.attachEvent("ondblclick", doOnDblClick);
	if (!gl_view_type == "grid") {
		dhxFolders.setItemType(gl_view_type, "codebase/types/" + gl_view_type
				+ ".xsl");
	}
}


/**
 * 树结构右键菜单
 */
function treeContextMenuAct(itemId, itemValue) {
	//context_TreeMenuutf8.xml
	var a = new Act();
	var selectId = myTree.getSelectedItemId();
	if (itemId == "open") {

		a.open(selectId);

	} else if (itemId == "copy") {

		a.copy(selectId);

	} else if (itemId == "paste") {

		a.paste(selectId);

	} else if (itemId == "delete") {
		var selectId = myTree.getSelectedItemId();
		if (a.del(selectId)) {
			myTree.deleteItem(selectId);
		}

	} else if (itemId == "rename") {
       //开始编辑树结点
	   
		//取消编辑
		var selectId = myTree.getSelectedItemId();
		//alert(selectId);
		myTree.enableItemEditor(true);
		myTree.setOnEditHandler(a.rename);
		
		myTree.editItem(selectId);
	}

}
/**
 * 文件夹右键菜单
 */
function folderContextMenuAct(itemId, itemValue) {

	var a = new Act();
	var selectId = dhxFolders.getSelectedId();
	if (itemId == "open") {

		a.open(selectId);

	} else if (itemId == "copy") {
		if(selectId==null){
			return;	
			alert("selectId is null.");
		}
		a.copy(selectId);

	} else if (itemId == "paste") {

		a.paste(selectId);

	} else if (itemId == "delete") {
		if (a.del(selectId)) {
			
			myTree.deleteItem(selectId);
			refreshView(myTree.getSelectedItemId());
		}

	} else if (itemId == "rename") {
		
		dhxFolders.enableEditMode(true);
		dhxFolders.editItem(selectId);
		dhxFolders.enableSelection(true);
		a.rename();

	}

}
/**
 * 文件右键菜单
 */
function fileContextMenuAct(itemId, itemValue) {

	var a = new Act();
	var selectId = dhxFolders.getSelectedId();

	if (itemId == "edit") {
		//如果选中对象是文件的话，可以编辑。
		a.edit(selectId);

	} else if (itemId == "copy") {

		a.copy(selectId);

	} else if (itemId == "paste") {

		a.paste(selectId);

	} else if (itemId == "delete") {
			if (a.del(selectId)) {
			
		
			refreshView(myTree.getSelectedItemId());
		}

	} else if (itemId == "rename") {

		a.rename();

	} else if (itemId == "expression") {

		a.expression(selectId);

	} else if(itemId == "property"){
		alert("现在是查看属性");		
	}else if(itemId == "setMainForm"){
		
		a.setMainForm(selectId);	
		refreshView(curFolder);
	}

}
/**
 * 主体本身右键菜单
 */
function bodyContextMenuAct(itemId, itemValue) {
	
	var a = new Act();
	var selectId = myTree.getSelectedItemId();
	if (itemId == "diradd") {
		
		a.newDir(curFolder);
		

	} else if (itemId == "upload") {

		a.upload(selectId);

     } else if (itemId == "openFolder") {

		a.openFolder(selectId);

	} else if (itemId == "bodypaste") {
		a.paste(curFolder);
	}
}
/**
 * 菜单和工具栏事件处理
 */
function toolbarclickeventhander(itemId, itemValue) {
	var a = new Act();
	var selectId = myTree.getSelectedItemId();

	if (itemId == "folderUp") {
		a.folderUp();

	} else if (itemId == "upload") {

		a.upload(selectId);

	}else{
		eval('a.'+itemId+'(itemId,itemValue)');
	}

}

function menuclickeventhander(itemId, itemValue) {

	var a = new Act();
	var selectId = myTree.getSelectedItemId();

	if (itemId == "folderUp") {
		//如果选中对象是文件的话，可以编辑。
		a.folderUp();

	} else if (itemId == "upload") {

		a.upload(selectId);

	}

}
/**
 * 动作对象
 * @param {Object} id
 */
function Act() {


/**
 * 目录显示
 * @return
 */
this.folder=function () {
	if (isFolder) {
		if (!leftpanel) {
			leftpanel = document.getElementById("pageContent");
		}
		myLayout.cells("b").attachObject(leftpanel)
		myLayout.cells("b").hideHeader();
		isFolder = false;
	} else {
		myLayout.cells("b").showHeader();
		initFolderTree();
		isFolder = true;
	}

}
	/**
	 * 新建目录 
	 * @return
	 */
	this.newDir = function(curD) {
		
		var evalJs = function(o){
			//alert(o);	
			Ext.namespace("EVAL");			
			refreshView(curD);		
		}		
		JDS.ajax.Connection.LoadJsonFromUrl("create.action", evalJs, "fileName="+curD+"\\new Folder");
	}
	/**
	 * 打开文件
	 */
	this.open = function(selectId) {
		refreshView(selectId);
	}
	
	this.setMainForm = function(selectId) {
		
		var evalJs = function(o){
			Ext.namespace("EVAL");			
			
		}		
		
		
		JDS.ajax.Connection.LoadJsonFromUrl("setMainForm.action?formPath="+selectId, evalJs);
		
	}
	
		
	
	/**
	 * 复制文件
	 */
	this.copy = function(selectId) {

		//window.clipboardData.setData("text", selectId);
		//alert(selectId);
		
		var evalJs = function(o){
		
			Ext.namespace("EVAL");
		
			eval(o);	
		}		
		if (selectId != null) {
			JDS.ajax.Connection.LoadJsonFromUrl('copy.action',evalJs,$H({fileName:selectId}));
			return true;
		} else {
			alert("请选择一个文件!!!");
		}	
	}
	/**
	 * 编辑文件
	 */
	this.edit = function(selectId) {
		
		var url = '../fdt/formeditor/edit.action?'+('fileName=' + selectId);
		
		winPop( url, 'formeditor');

	}
     /**
	 * 数据绑定
	 */
	this.expression = function(selectId) {
		
		var url = '../fdt/designer/fdtDesigner.action?fileName=' + selectId+'&flowType='+flowType+'&esbKeyList='+esbKeyList;
       winPop( url, 'fdtDesigner');

	}
	

	
	
	/**
	 * 粘贴文件
	 */
	this.paste = function(desId) {
	
		var evalJs = function(o){
			
			Ext.namespace("EVAL");			
			refreshView(desId);
		}		
		if (desId != null) {
			JDS.ajax.Connection.LoadJsonFromUrl('paste.action',evalJs,$H({fileName:desId}));
			return true;
		} else {
			alert("请选择一个文件!!!");
		}
	}

	/**
	 * 对文件重命名
	 */
	this.rename = function(state,id,tree,value) {
		
		
		if (state==2){
			var tID=id.substring(0,id.lastIndexOf('\\')+1)+value;
		 	tree.enableItemEditor(false);
		 	tree.changeItemId(id,tID);
			dhtmlxAjax.post('reName.action', 'sID=' + id + '&tID=' + tID, outputResponse);
		 	return true;
		}
		
		return true;
		
	}

	/**
	 * 删除文件
	 */
	this.del = function(selectId) {

		//var selectId = dhxFolders.getSelectedId();
		//alert(selectId);
		
		
		var evalJs = function(o){
			Ext.namespace("EVAL");
			refreshView(curFolder)
			eval(o);	
		}		
		if (selectId != null) {
			JDS.ajax.Connection.LoadJsonFromUrl('delete.action',evalJs,$H({fileName:selectId}));
			return true;
		} else {
			alert("请选择一个文件!!!");
			return false;
		}
	}
	/**
	 * 上传文件
	 */
	this.upload = function(selectId) {

		if (selectId == "") {
		   if (activityDefId&&activityDefId!=''){
				selectId=initdir+'/'+activityDefId
			  }
		
		 selectId=initdir
		}
		
		
		
	
		if (selectId == "") {
			alert("请选择文件上传目录");
		} else {
			
			selectId = selectId.replace(/\\/g, "/");
			var win = getVaultWin(selectId);
		//	alert(win);
			//win.centerOnScreen();
		}
	}
	
	/**
	 * 打开文件夹
	 */
	this.openFolder= function(selectId) {
	

		if (selectId == "") {
		   if (activityDefId&&activityDefId!=''){
				selectId=initdir+'/'+activityDefId
			  }
		
		 selectId=initdir
		}
		sendNSCommand("ognl","open('"+selectId+"')");
		
		
	}
	
	/**
	 * 转上级目录
	 */
	this.folderUp = function() {
		var selectId = myTree.getSelectedItemId();
		var parentId = myTree.getParentId(selectId);

		if (parentId != 0) {
			showDirContent(parentId);
		}
	}

	/**
	 * 类型转换
	 */
	this.viewType = function(itemName, itemId) {
		if (itemId == "ftable") {
			gl_view_type = "ftable";
			showDirContent(curFolder);
		} else if (itemId == "grid") {
			gl_view_type = "grid";
			showDirContent(curFolder);
		} else if (itemId == "view_icons") {
			gl_view_type = "ficon";
			showDirContent(curFolder);
		} else if (itemId == "view_tiles") {
			gl_view_type = "ftiles";
			showDirContent(curFolder);
		}
	}

}
/**
 * 显示内容 
 * @param dir
 * @return
 */
function showDirContent(dir) {
	if (gl_view_type == "grid") {
		showDList(dir);
	} else {
		refreshView(dir);
	}
}

function showDList(dir) {
	myGrid = myLayout.cells("c").attachGrid();
	myGrid.setImagePath("codebase/imgs/");
	myGrid.setIconsPath("icons/grid/");
	myGrid.setHeader("&nbsp;,名称,大小,类型,修改日期");
	myGrid.setColTypes("img,ro,ro,ro");
	myGrid.setInitWidths("40,250,50,100,*");
	myGrid.setColAlign("center,left,right,left");
	try {
		myGrid.load("XMLInTable.action?dir=" + dir);
	} catch (e) {

	}

	myGrid.init();
	gl_view_type = "grid";
}

/**
 * 显示查询窗口
 */
function showSearchWin() {
	var obj = document.getElementById("search_area");
	myLayout.cells("b").attachObject(obj)

}

function outputResponse(loader) {
	if (loader.xmlDoc.responseText != null) {
		// eval(loader.xmlDoc.responseText);
	} else {
		alert("Response contains no XML");
	}
}
/**
 * 弹出窗口 
 * @param w
 * @param h
 * @param href
 * @param name
 * @return
 */
function winPop(href, winName) {
	var winPopUp = null;
	var W = screen.width*0.9;
	var H = screen.height*0.9;
	if(W<640) W=640;
	if(H<480) H=480;

	var windowW = W;
	var windowH = H;
	var windowX = -1;
	var windowY = -1;
	
	windowX = Math.ceil( (window.screen.width  - windowW) / 2 );
	windowY = Math.ceil( (window.screen.height - windowH) / 2 );

	winPopUp = window.open(href ,winName,"toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=0,resizable=1");
	winPopUp.resizeTo( Math.ceil( W )       , Math.ceil( H ) );
	winPopUp.moveTo  ( Math.ceil( windowX ) , Math.ceil( windowY ) );
}



/**
 * 刷新窗口
 * @param id
 * @return
 */
function refreshView(id) {
	initFolders(id);
	myTree.openItem(id);
	myTree.selectItem(id);
}
/**
 * 显示文件上传窗口
 * @param selectId
 * @return
 */
function getVaultWin(selectId) {
	var dhxWins = new dhtmlXWindows();
	dhxWins.vp.style.border = "#909090 0px solid";
	dhxWins.setImagePath("codebase/imgs/");
	var win = dhxWins.createWindow("win", "0", "0", "480", "350");
	win.setText("上传文件");

	win.button("close").attachEvent("onClick", function() {
		showDirContent(selectId);
		win.close();
	});
	win.attachURL("dhtmlxVault/index.html?uploadpath=" + selectId);
	return win;
}

/**
 * 取COOKIE
 * @param name
 * @return
 */
function Get_Cookie(name) {
	var start = document.cookie.indexOf(name + "=");
	var len = start + name.length + 1;
	if ((!start) && (name != document.cookie.substring(0, name.length)))
		return null;
	if (start == -1)
		return null;
	var end = document.cookie.indexOf(";", len);
	if (end == -1)
		end = document.cookie.length;
	return unescape(document.cookie.substring(len, end));
}
// This function has been slightly modified
/**
 * 设置COOKIE
 */
function Set_Cookie(name, value, expires, path, domain, secure) {
	expires = expires * 60 * 60 * 24 * 1000;
	var today = new Date();
	var expires_date = new Date(today.getTime() + (expires));
	var cookieString = name + "=" + escape(value)
			+ ((expires) ? ";expires=" + expires_date.toGMTString() : "")
			+ ((path) ? ";path=" + path : "")
			+ ((domain) ? ";domain=" + domain : "")
			+ ((secure) ? ";secure" : "");
	document.cookie = cookieString;
}

function cancelXpWidgetEvent() {

	return false;
}

function showHidePaneContent(e, inputObj) {
	if (!inputObj)
		inputObj = this;

	var img = inputObj.getElementsByTagName('IMG')[0];
	var numericId = img.id.replace(/[^0-9]/g, '');
	var obj = document.getElementById('paneContent' + numericId);
	if (img.src.toLowerCase().indexOf('up') >= 0) {
		currentlyExpandedPane = false;
		img.src = img.src.replace('up', 'down');
		if (xpPanel_slideActive) {
			obj.style.display = 'block';
			xpPanel_currentDirection[obj.id] = (xpPanel_slideSpeed * -1);
			slidePane((xpPanel_slideSpeed * -1), obj.id);
		} else {
			obj.style.display = 'none';
		}
		if (cookieNames[numericId])
			Set_Cookie(cookieNames[numericId], '0', 100000);
	} else {
		if (this) {
			if (currentlyExpandedPane && xpPanel_onlyOneExpandedPane)
				showHidePaneContent(false, currentlyExpandedPane);
			currentlyExpandedPane = this;
		} else {
			currentlyExpandedPane = false;
		}
		img.src = img.src.replace('down', 'up');
		if (xpPanel_slideActive) {
			if (document.all) {
				obj.style.display = 'block';
				//obj.style.height = '1px';
			}
			xpPanel_currentDirection[obj.id] = xpPanel_slideSpeed;
			slidePane(xpPanel_slideSpeed, obj.id);
		} else {
			obj.style.display = 'block';
			subDiv = obj.getElementsByTagName('DIV')[0];
			obj.style.height = subDiv.offsetHeight + 'px';
		}
		if (cookieNames[numericId])
			Set_Cookie(cookieNames[numericId], '1', 100000);
	}
	return true;
}

function slidePane(slideValue, id) {
	if (slideValue != xpPanel_currentDirection[id]) {
		return false;
	}
	var activePane = document.getElementById(id);
	if (activePane == savedActivePane) {
		var subDiv = savedActiveSub;
	} else {
		var subDiv = activePane.getElementsByTagName('DIV')[0];
	}
	savedActivePane = activePane;
	savedActiveSub = subDiv;

	var height = activePane.offsetHeight;
	var innerHeight = subDiv.offsetHeight;
	height += slideValue;
	if (height < 0)
		height = 0;
	if (height > innerHeight)
		height = innerHeight;

	if (document.all) {
		activePane.style.filter = 'alpha(opacity=' + Math
				.round((height / subDiv.offsetHeight) * 100) + ')';
	} else {
		var opacity = (height / subDiv.offsetHeight);
		if (opacity == 0)
			opacity = 0.01;
		if (opacity == 1)
			opacity = 0.99;
		activePane.style.opacity = opacity;
	}

	if (slideValue < 0) {
		activePane.style.height = height + 'px';
		subDiv.style.top = height - subDiv.offsetHeight + 'px';
		if (height > 0) {
			setTimeout('slidePane(' + slideValue + ',"' + id + '")', 10);
		} else {
			if (document.all)
				activePane.style.display = 'none';
		}
	} else {
		subDiv.style.top = height - subDiv.offsetHeight + 'px';
		activePane.style.height = height + 'px';
		if (height < innerHeight) {
			setTimeout('slidePane(' + slideValue + ',"' + id + '")', 10);
		}
	}

}

function mouseoverTopbar() {
	var img = this.getElementsByTagName('IMG')[0];
	var src = img.src;
	img.src = img.src.replace('.gif', '_over.gif');

	var span = this.getElementsByTagName('SPAN')[0];
	span.style.color = '#428EFF';

}

function mouseoutTopbar() {
	var img = this.getElementsByTagName('IMG')[0];
	var src = img.src;
	img.src = img.src.replace('_over.gif', '.gif');

	var span = this.getElementsByTagName('SPAN')[0];
	span.style.color = '';

}
/**
 * 初始化 PANE
 * @param panelTitles
 * @param panelDisplayed
 * @param cookieArray
 * @return
 */
function initDhtmlgoodies_xpPane(panelTitles, panelDisplayed, cookieArray) {
	dhtmlgoodies_xpPane = document.getElementById('dhtmlgoodies_xpPane');
	var divs = dhtmlgoodies_xpPane.getElementsByTagName('DIV');
	dhtmlgoodies_paneIndex = 0;
	cookieNames = cookieArray;
	for ( var no = 0; no < divs.length; no++) {
		if (divs[no].className == 'dhtmlgoodies_panel') {

			var outerContentDiv = document.createElement('DIV');
			var contentDiv = divs[no].getElementsByTagName('DIV')[0];
			outerContentDiv.appendChild(contentDiv);

			outerContentDiv.id = 'paneContent' + dhtmlgoodies_paneIndex;
			outerContentDiv.className = 'panelContent';
			var topBar = document.createElement('DIV');
			topBar.onselectstart = cancelXpWidgetEvent;
			var span = document.createElement('SPAN');
			span.innerHTML = panelTitles[dhtmlgoodies_paneIndex];
			topBar.appendChild(span);
			topBar.onclick = showHidePaneContent;
			if (document.all)
				topBar.ondblclick = showHidePaneContent;
			topBar.onmouseover = mouseoverTopbar;
			topBar.onmouseout = mouseoutTopbar;
			topBar.style.position = 'relative';

			var img = document.createElement('IMG');
			img.id = 'showHideButton' + dhtmlgoodies_paneIndex;
			img.src = 'images/arrow_up.gif';
			topBar.appendChild(img);

			if (cookieArray[dhtmlgoodies_paneIndex]) {
				cookieValue = Get_Cookie(cookieArray[dhtmlgoodies_paneIndex]);
				if (cookieValue)
					panelDisplayed[dhtmlgoodies_paneIndex] = cookieValue == 1 ? true
							: false;
			}

			if (!panelDisplayed[dhtmlgoodies_paneIndex]) {
				outerContentDiv.style.height = '0px';
				contentDiv.style.top = 0 - contentDiv.offsetHeight + 'px';
				if (document.all)
					outerContentDiv.style.display = 'none';
				img.src = 'images/arrow_down.gif';
			}

			topBar.className = 'topBar';
			divs[no].appendChild(topBar);
			divs[no].appendChild(outerContentDiv);
			dhtmlgoodies_paneIndex++;
		}
	}
}
