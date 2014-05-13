



function JDSExplorer(){
	var myLayout, myTree, myGrid, dhxFolders, myMenu, myToolbar;
	var isFolder=true;
	var leftpanel;
	var fileContextMenu ,folderContextMenu,treeContextMenu,bodyContextMenu;
	var curFolder;//全局变量 当前的目录
	var ifexpand = true; //判断左侧目录树是否隐藏
	var gl_view_type = "ficon";
	var gl_view_bg = "";
	var xml_prefix=context+'report/config/'; 
	var leftPanel=new LeftPanel();
		
	function cellButtonClick(itemId,itemValue){
	  alert(itemId);
	return true;
	}
	
	/**
	 * 初始化应用界面 init();
	 */
	this.init=function init(){
	    initWindow();
	    initToolBar();
	    initContextMenu();
	    initFolderTree();
	    initFolders("");
		initMenu();
		leftPanel.initLeftPanel();
		//folder();
	}
			
	
	
	function initContextMenu(){
			
	    var xml_prefix='codebase/config/';
		var gfxpath=context+'imgs/formDef/';
			treeContextMenu=new dhtmlXContextMenuObject('120',0,gfxpath);
			treeContextMenu.menu.setGfxPath(gfxpath);
			treeContextMenu.menu.setMenuCSS("contextmenuTable") ;
			treeContextMenu.menu.secTableCSS="contextsecondMenuTable";
			treeContextMenu.menu.loadXML(xml_prefix + "context_TreeMenuutf8.xml");
		treeContextMenu.setContextMenuHandler(cellButtonClick);
		
		folderContextMenu=new dhtmlXContextMenuObject('120',0,gfxpath);
			folderContextMenu.menu.setGfxPath(gfxpath);
			folderContextMenu.menu.setMenuCSS("contextmenuTable") ;
			folderContextMenu.menu.secTableCSS="contextsecondMenuTable";
			folderContextMenu.menu.loadXML(xml_prefix + "context_FolderMenuutf8.xml");
		folderContextMenu.setContextMenuHandler(cellButtonClick);
		
		fileContextMenu=new dhtmlXContextMenuObject('120',0,gfxpath);
			fileContextMenu.menu.setGfxPath(gfxpath);
			fileContextMenu.menu.setMenuCSS("contextmenuTable") ;
			fileContextMenu.menu.secTableCSS="contextsecondMenuTable";
			fileContextMenu.menu.loadXML(xml_prefix + "context_FileMenuutf8.xml");
		fileContextMenu.setContextMenuHandler(cellButtonClick);
		
		bodyContextMenu=new dhtmlXContextMenuObject('120',0,gfxpath);
			bodyContextMenu.menu.setGfxPath(gfxpath);
			bodyContextMenu.menu.setMenuCSS("contextmenuTable") ;
			bodyContextMenu.menu.secTableCSS="contextsecondMenuTable";
			bodyContextMenu.menu.loadXML(xml_prefix + "context_BodyMenuutf8.xml");
		bodyContextMenu.setContextMenuHandler(cellButtonClick);
			
	}
	        
	/**
	 * 初始化WINDOW
	 * 布局采用：Layout 3W 方式
	 * 样式：dhx_blue
	 */
	myLayout:function initWindow(){
	    myLayout = new dhtmlXLayoutObject(document.body, "3T", "dhx_blue");
		var obj = document.getElementById("iebar");
		myLayout.cells("b").setWidth(200);
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
	function initToolBar(){
	myToolbar = myLayout.attachToolbar();
	myToolbar.setOnClickHandler(toolbarclickeventhander);
	myToolbar.loadXML("codebase/config/_toolbarutf8.xml")
	myToolbar.showBar();
	}
	
	/**
	 * 初始化菜单
	 */
	function initMenu(){
		var gfxpath=context+'imgs/formDef/';
	   myMenu = myLayout.attachMenu();
	   myMenu.setGfxPath(gfxpath);
	   myMenu.loadXML("codebase/config/_menuutf8.xml");
	   myMenu.setOnClickHandler(menuclickeventhander);
	   myMenu.showBar();
	
	}
	
	/**
	 * 初始化目录树
	 */
	function initFolderTree(){
	    myTree = myLayout.cells("b").attachTree("0");
	    myTree.setImagePath("codebase/imgs/menu/");
	    myTree.enableDragAndDrop(true);            
	    myTree.setXMLAutoLoading("/formmanager/xmlFromDirTree.action");//加载目录
	    myTree.loadXML("/formmanager/xmlFromDirTree.action"); //加载目录
	    //增加选择事件
	    myTree.attachEvent("onSelect", initFolders);
	    myTree.enableContextMenu(treeContextMenu);
	       
	    //onRightClick
	    myTree.attachEvent("onDrag", function(sID, tID,sObj,tObj){
	    var dropSrc='/formmanager/move.action?sID='+sID+'&tID='+tID;       
	    dhtmlxAjax.post(dropSrc, 'sID='+sID+'&tID='+tID,outputResponse);
				return true; 
	    })
	}
	
	/**
	 * 初始化目录、文件 窗口
	 * @param {Object} dir 选中的文件夹
	 */
	function initFolders(dir,findtext){
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
	    dhxFolders.attachEvent("onBeforeDrop", function(dropType, sID, tID){
	    var tType = this.getItem(tID).data.dataObj.getAttribute("type");
	        if (dropType == "in" && tType == "dir") {
	            this.deleteItem(sID);
	            var dropSrc='/formmanager/move.action';
	            dhtmlxAjax.post(dropSrc, 'sID='+sID+'&tID='+tID,outputResponse);
	            //需调用文件处理程序 
	            return false;
	        } else{
				return true;
			}     
	     })
	    //增加双击方法,当选中类型为文件时，调用预览方法；当选中类型为文件夹时，刷新目录。
		  if(gl_view_type=="grid"){
		  	showDirContent(dir);
		  }else{
			 dhxFolders.loadXML("/formmanager/XMLInDir.action?dir="+dir, "codebase/types/ficon.xsl");
		  }
		
		 	
		
	    function doOnDblClick(id){
	        var tType = dhxFolders.getItem(id).data.dataObj.getAttribute("type");
	        if (tType == "file") {
	            var selectId=dhxFolders.getSelectedId();
				//var url='formeditor/edit.action?fileName='+id;
				var url='/design/fdtDesigner.action?fileName='+id;
				//alert(url);
				winPop(800,600,url,'文件编辑器');
	           } else {
	            showDirContent(id);
	           }
	     }
	    dhxFolders.attachEvent("ondblclick", doOnDblClick);
		 if(!gl_view_type=="grid"){
	    dhxFolders.setItemType(gl_view_type, "codebase/types/" + gl_view_type + ".xsl");
		}
	}
	  
			  
	  
	  
	//转上级目录
	function folderUp(){
		  var selectId=myTree.getSelectedItemId();
	                    var parentId=myTree.getParentId(selectId); 
						
	                    if(parentId!=0){
	                    	showDirContent(parentId);
	                    }
	}	  
	//上传文件
	function upload(){
		 var selectId=myTree.getSelectedItemId();
	         	if(selectId==""){
	         		alert("请选择文件上传目录");
	         	}else{
	         	selectId=selectId.replace(/\\/g,"/");
	         	var win =getVaultWin(selectId);
				win.centerOnScreen();
				}
	}	
	  
	//类型转换
	function viewType(itemName,itemId){
		
		 if (itemId == "ftable"){
		  	 gl_view_type = "ftable";
			 showDirContent(curFolder);
		 } else if (itemId == "grid"){   
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
	  
	function folder(){
		if (isFolder){
			if (!leftpanel){
				leftpanel = document.getElementById("pageContent");
			}
			 myLayout.cells("b").attachObject(leftpanel)
			 myLayout.cells("b").hideHeader();
			 isFolder=false;
		}else{
			myLayout.cells("b").showHeader();
           initFolderTree();
		  
			isFolder=true;
		}
		
	} 
	
	
			  
	/**
	 * 执行工具栏的动作
	 * @param {Object} id
	 */
	function action(id){
	    if(id == "upload"){
	               
	    }else if (id.toString().indexOf("view") == 0) {
	        //转换目录视图
	      
	     } else {
	        if (id == "folders") {
				
	        }
	        else if (id == "levelup") { 
	              
				} else if(id=="delete"){
					var selectId=dhxFolders.getSelectedId();
					if(selectId !=null){
						dhxFolders.deleteItem(selectId);
						dhtmlxAjax.post('/formmanager/delete.action','fileName='+selectId ,outputResponse);
					}else{
						alert("请选择一个文件!!!");
					}
			}
	    }
	    return true;
	}
	
	
	function showDirContent(dir){
	  if(gl_view_type=="grid"){
	    showDList(dir);
	   }else{
	    refreshView(dir);
	  }
	 }
	
	function showDList(dir){
	    myGrid = myLayout.cells("c").attachGrid();
	    myGrid.setImagePath("codebase/imgs/");
		myGrid.setIconsPath("icons/grid/");
		myGrid.setHeader("&nbsp;,名称,大小,类型,修改日期"); 
		myGrid.setColTypes("img,ro,ro,ro"); 
		myGrid.setInitWidths("40,250,50,100,*");
		myGrid.setColAlign("center,left,right,left");
		try{
			myGrid.load("/formmanager/XMLInTable.action?dir="+dir);
		}catch(e){
			
		}
	   
	   	myGrid.init();
		gl_view_type = "grid";
	}
	    
	/**
	 * 显示查询窗口
	 */
	function showSearchWin(){
	    var obj = document.getElementById("search_area");
	    myLayout.cells("b").attachObject(obj)
	    
	}
	
	function outputResponse(loader){
	    	if(loader.xmlDoc.responseText!=null) {
	       	 // eval(loader.xmlDoc.responseText);
	       	  }
	    	 else{
	        alert("Response contains no XML");
	        }
	    }
		
	
	    
	function winPop(w,h,href,name){
		var winPopUp = null;
		var W = w;
		var H = h;
		var windowW = W;
		var windowH = H;
		var windowX = -1;
		var windowY = -1;
		
		windowX = Math.ceil( (window.screen.width  - windowW) / 2 );
		windowY = Math.ceil( (window.screen.height - windowH) / 2 );
		winPopUp = window.showModelessDialog(href ,name,"dialogWidth=950px;dialogHeight=700px;resizable=true");
		winPopUp.resizeTo( Math.ceil( W )       , Math.ceil( H ) );
		winPopUp.moveTo  ( Math.ceil( windowX ) , Math.ceil( windowY ) );
		return winPopUp; 
	}
	
	
	// 刷新窗口
	function refreshView(id){
		if (id){
			id=curFolder;
		}
	     initFolders(id);
	     myTree.openItem(id);
	     myTree.selectItem(id);
	}
	
	
	function getVaultWin(selectId){
	   var dhxWins = new dhtmlXWindows();
			dhxWins.vp.style.border="#909090 0px solid";
		dhxWins.setImagePath("codebase/imgs/");          				 
		var win = dhxWins.createWindow("win", "0", "0", "480", "380");
		win.setText("上传文件");
		
		win.button("close").attachEvent("onClick", function(){
			showDirContent(selectId);
			win.close();
		}); 
		win.attachURL("dhtmlxVault/index.html?uploadpath="+selectId);
		return win;
	}
	
		   
		   //菜单和工具栏事件处理
	function toolbarclickeventhander(itemId,itemValue)
	{ 
	
	    eval("(" + itemId +"(itemId,itemValue))");
	}
	function menuclickeventhander(itemId,itemValue)
	{
	    eval("(menu_" + itemId +"(itemValue))");
	} 
		


}



	
	
function LeftPanel(){
	
	var xpPanel_slideActive = true;	// Slide down/up active?
	var xpPanel_slideSpeed = 20;	// Speed of slide
	var xpPanel_onlyOneExpandedPane = false;	// Only one pane expanded at a time ?
	var dhtmlgoodies_xpPane;
	var dhtmlgoodies_paneIndex;
	var savedActivePane = false;
	var savedActiveSub = false;
	var xpPanel_currentDirection = new Array();
	var cookieNames = new Array();
	var currentlyExpandedPane = false;
	//this.initLeftPanel=initLeftPanel();
	
	this.initLeftPanel=function (){
		initDhtmlgoodies_xpPane(Array('文件夹与文件夹任务�ļ��к��ļ�������','其他位置','详细信息'),Array(true,true,false),Array('pane1','pane2','pane3'));
	    ch_client = "batalf";
		ch_type = "mpu";
		ch_width = 468;
		ch_height = 60;
		ch_non_contextual = 4;
		ch_vertical ="premium";
		ch_default_category = "200001";
		ch_sid = "Chitika Premium";
		var ch_queries = new Array( "javascript", "dhtml", "ipod", "software development", "website design", "Web design templates" );
		var ch_selected=Math.floor((Math.random()*ch_queries.length));
		if ( ch_selected < ch_queries.length ) {
			ch_query = ch_queries[ch_selected];
			var dc_UnitID = 14;
			var dc_PublisherID = 3695;
			var dc_AdLinkColor = 'blue';
			var dc_adprod='ADL';
	    }
	
	}
	
	function Get_Cookie(name) { 
	   var start = document.cookie.indexOf(name+"="); 
	   var len = start+name.length+1; 
	   if ((!start) && (name != document.cookie.substring(0,name.length))) return null; 
	   if (start == -1) return null; 
	   var end = document.cookie.indexOf(";",len); 
	   if (end == -1) end = document.cookie.length; 
	   return unescape(document.cookie.substring(len,end)); 
	} 
	// This function has been slightly modified
	function Set_Cookie(name,value,expires,path,domain,secure) { 
		expires = expires * 60*60*24*1000;
		var today = new Date();
		var expires_date = new Date( today.getTime() + (expires) );
	    var cookieString = name + "=" +escape(value) + 
	       ( (expires) ? ";expires=" + expires_date.toGMTString() : "") + 
	       ( (path) ? ";path=" + path : "") + 
	       ( (domain) ? ";domain=" + domain : "") + 
	       ( (secure) ? ";secure" : ""); 
	    document.cookie = cookieString; 
	}
	
	function cancelXpWidgetEvent()
	{
		return false;	
		
	}
	
	function showHidePaneContent(e,inputObj)
	{
		if(!inputObj)inputObj = this;
		
		var img = inputObj.getElementsByTagName('IMG')[0];
		var numericId = img.id.replace(/[^0-9]/g,'');
		var obj = document.getElementById('paneContent' + numericId);
		if(img.src.toLowerCase().indexOf('up')>=0){
			currentlyExpandedPane = false;
			img.src = img.src.replace('up','down');
			if(xpPanel_slideActive){
				obj.style.display='block';
				xpPanel_currentDirection[obj.id] = (xpPanel_slideSpeed*-1);
				slidePane((xpPanel_slideSpeed*-1), obj.id);
			}else{
				obj.style.display='none';
			}
			if(cookieNames[numericId])Set_Cookie(cookieNames[numericId],'0',100000);
		}else{
			if(this){
				if(currentlyExpandedPane && xpPanel_onlyOneExpandedPane)showHidePaneContent(false,currentlyExpandedPane);
				currentlyExpandedPane = this;	
			}else{
				currentlyExpandedPane = false;
			}
			img.src = img.src.replace('down','up');
			if(xpPanel_slideActive){
				if(document.all){
					obj.style.display='block';
					//obj.style.height = '1px';
				}
				xpPanel_currentDirection[obj.id] = xpPanel_slideSpeed;
				slidePane(xpPanel_slideSpeed,obj.id);
			}else{
				obj.style.display='block';
				subDiv = obj.getElementsByTagName('DIV')[0];
				obj.style.height = subDiv.offsetHeight + 'px';
			}
			if(cookieNames[numericId])Set_Cookie(cookieNames[numericId],'1',100000);
		}	
		return true;	
	}
	
	
	
	function slidePane(slideValue,id)
	{
		if(slideValue!=xpPanel_currentDirection[id]){
			return false;
		}
		var activePane = document.getElementById(id);
		if(activePane==savedActivePane){
			var subDiv = savedActiveSub;
		}else{
			var subDiv = activePane.getElementsByTagName('DIV')[0];
		}
		savedActivePane = activePane;
		savedActiveSub = subDiv;
		
		var height = activePane.offsetHeight;
		var innerHeight = subDiv.offsetHeight;
		height+=slideValue;
		if(height<0)height=0;
		if(height>innerHeight)height = innerHeight;
		
		if(document.all){
			activePane.style.filter = 'alpha(opacity=' + Math.round((height / subDiv.offsetHeight)*100) + ')';
		}else{
			var opacity = (height / subDiv.offsetHeight);
			if(opacity==0)opacity=0.01;
			if(opacity==1)opacity = 0.99;
			activePane.style.opacity = opacity;
		}			
		
					
		if(slideValue<0){			
			activePane.style.height = height + 'px';
			subDiv.style.top = height - subDiv.offsetHeight + 'px';
			if(height>0){
				setTimeout('slidePane(' + slideValue + ',"' + id + '")',10);
			}else{
				if(document.all)activePane.style.display='none';
			}
		}else{			
			subDiv.style.top = height - subDiv.offsetHeight + 'px';
			activePane.style.height = height + 'px';
			if(height<innerHeight){
				setTimeout('slidePane(' + slideValue + ',"' + id + '")',10);				
			}		
		}
		
		
		
		
	}
	
	function mouseoverTopbar()
	{
		var img = this.getElementsByTagName('IMG')[0];
		var src = img.src;
		img.src = img.src.replace('.gif','_over.gif');
		
		var span = this.getElementsByTagName('SPAN')[0];
		span.style.color='#428EFF';		
		
	}
	function mouseoutTopbar()
	{
		var img = this.getElementsByTagName('IMG')[0];
		var src = img.src;
		img.src = img.src.replace('_over.gif','.gif');		
		
		var span = this.getElementsByTagName('SPAN')[0];
		span.style.color='';
		
		
		
	}
	
	
	function initDhtmlgoodies_xpPane(panelTitles,panelDisplayed,cookieArray)
	{
		dhtmlgoodies_xpPane = document.getElementById('dhtmlgoodies_xpPane');
		var divs = dhtmlgoodies_xpPane.getElementsByTagName('DIV');
		dhtmlgoodies_paneIndex=0;
		cookieNames = cookieArray;
		for(var no=0;no<divs.length;no++){
			if(divs[no].className=='dhtmlgoodies_panel'){
				
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
				if(document.all)topBar.ondblclick = showHidePaneContent;
				topBar.onmouseover = mouseoverTopbar;
				topBar.onmouseout = mouseoutTopbar;
				topBar.style.position = 'relative';
	
				var img = document.createElement('IMG');
				img.id = 'showHideButton' + dhtmlgoodies_paneIndex;
				img.src = 'images/arrow_up.gif';				
				topBar.appendChild(img);
				
				if(cookieArray[dhtmlgoodies_paneIndex]){
					cookieValue = Get_Cookie(cookieArray[dhtmlgoodies_paneIndex]);
					if(cookieValue)panelDisplayed[dhtmlgoodies_paneIndex] = cookieValue==1?true:false;
					
				}
				
				if(!panelDisplayed[dhtmlgoodies_paneIndex]){
					outerContentDiv.style.height = '0px';
					contentDiv.style.top = 0 - contentDiv.offsetHeight + 'px';
					if(document.all)outerContentDiv.style.display='none';
					img.src = 'images/arrow_down.gif';
				}
								
				topBar.className='topBar';
				divs[no].appendChild(topBar);				
				divs[no].appendChild(outerContentDiv);	
				dhtmlgoodies_paneIndex++;			
			}			
		}
	}
}
	
	
	
	
	    //鼠标移动到图片上
function over(the,imgsrc){
	the.className="img_over";
	the.src='images/'+imgsrc+'_over.gif';
}
//鼠标移出图片上
function out(the,imgsrc){
	the.className="img";
	the.src='images/'+imgsrc+'.gif';
}
//鼠标在图片上按下
function down(the,imgsrc){
	the.className="img_down";
	//the.src='images/'+imgsrc+'_down.gif';
}
function over_(the){the.className="img_over";}
function out_(the){the.className="img";}
function down_(the){the.className="img_down";}
	

		            