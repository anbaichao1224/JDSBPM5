
LeftPanel={
//-----------------------------------------

	xpPanel_slideActive : true, // Slide down/up active?
	xpPanel_slideSpeed : 20, // Speed of slide
	xpPanel_onlyOneExpandedPane : false, // Only one pane expanded at a time ?
	dhtmlgoodies_xpPane:'',
	dhtmlgoodies_paneIndex:null,
	savedActivePane : false,
	savedActiveSub : false,
	xpPanel_currentDirection : new Array(),
	cookieNames : new Array(),
	currentlyExpandedPane : false,
//------------------------------------------

	initLeftPanel:function () {
		var div = arguments[0].child("div");
		div.id=Ext.id();
		LeftPanel.initDhtmlgoodies_xpPane( div.dom,Array('文件夹与文件夹任务', '其他位置', '详细信息'),
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
	
	},
	
	
	/**
	 * 初始化 PANE
	 * @param panelTitles
	 * @param panelDisplayed
	 * @param cookieArray
	 * @return
	 */
	 initDhtmlgoodies_xpPane :function(dhtmlgoodies_xpPane,panelTitles, panelDisplayed, cookieArray) {
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
				topBar.onselectstart = LeftPanel.cancelXpWidgetEvent;
				var span = document.createElement('SPAN');
				span.innerHTML = panelTitles[dhtmlgoodies_paneIndex];
				topBar.appendChild(span);
				topBar.onclick = LeftPanel.showHidePaneContent;
				if (document.all)
					topBar.ondblclick = LeftPanel.showHidePaneContent;
				topBar.onmouseover = LeftPanel.mouseoverTopbar;
				topBar.onmouseout = LeftPanel.mouseoutTopbar;
				topBar.style.position = 'relative';
	
				var img = document.createElement('IMG');
				img.id = 'showHideButton' + dhtmlgoodies_paneIndex;
				img.src = 'desktop/widgets/explorer/images/arrow_up.gif';
				topBar.appendChild(img);
	
				if (cookieArray[dhtmlgoodies_paneIndex]) {
					cookieValue = LeftPanel.Get_Cookie(cookieArray[dhtmlgoodies_paneIndex]);
					if (cookieValue)
						panelDisplayed[dhtmlgoodies_paneIndex] = cookieValue == 1 ? true
								: false;
				}
	
				if (!panelDisplayed[dhtmlgoodies_paneIndex]) {
					outerContentDiv.style.height = '0px';
					contentDiv.style.top = 0 - contentDiv.offsetHeight + 'px';
					if (document.all)
						outerContentDiv.style.display = 'none';
					img.src = 'desktop/widgets/explorer/images/arrow_down.gif';
				}
	
				topBar.className = 'topBar';
				divs[no].appendChild(topBar);
				divs[no].appendChild(outerContentDiv);
				dhtmlgoodies_paneIndex++;
			}
		}
	},
	 cancelXpWidgetEvent:function() {
	
		return false;
	},
	
	
	/**
	 * 取COOKIE
	 * @param name
	 * @return
	 */
	 Get_Cookie:function(name) {
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
	},
	// This function has been slightly modified
	/**
	 * 设置COOKIE
	 */
	 Set_Cookie:function(name, value, expires, path, domain, secure) {
		expires = expires * 60 * 60 * 24 * 1000;
		var today = new Date();
		var expires_date = new Date(today.getTime() + (expires));
		var cookieString = name + "=" + escape(value)
				+ ((expires) ? ";expires=" + expires_date.toGMTString() : "")
				+ ((path) ? ";path=" + path : "")
				+ ((domain) ? ";domain=" + domain : "")
				+ ((secure) ? ";secure" : "");
		document.cookie = cookieString;
	},
	
	 cancelXpWidgetEvent:function() {
	
		return false;
	},
	
	 showHidePaneContent:function(e, inputObj) {
	 
		if (!inputObj)
			inputObj = this;
	
		var img = inputObj.getElementsByTagName('IMG')[0];
		var numericId = img.id.replace(/[^0-9]/g, '');
		var obj = document.getElementById('paneContent' + numericId);
		if (img.src.toLowerCase().indexOf('up') >= 0) {
			currentlyExpandedPane = false;
			img.src = img.src.replace('up', 'down');
			if (LeftPanel.xpPanel_slideActive) {
				obj.style.display = 'block';
				LeftPanel.xpPanel_currentDirection[obj.id] = (LeftPanel.xpPanel_slideSpeed * -1);
				LeftPanel.slidePane((LeftPanel.xpPanel_slideSpeed * -1), obj.id);
			} else {
				obj.style.display = 'none';
			}
			if (cookieNames[numericId])
				LeftPanel.Set_Cookie(cookieNames[numericId], '0', 100000);
		} else {
			if (this) {
				if (LeftPanel.currentlyExpandedPane && LeftPanel.xpPanel_onlyOneExpandedPane)
					showHidePaneContent(false, currentlyExpandedPane);
				currentlyExpandedPane = this;
			} else {
				currentlyExpandedPane = false;
			}
			img.src = img.src.replace('down', 'up');
			if (LeftPanel.xpPanel_slideActive) {
				if (document.all) {
					obj.style.display = 'block';
					//obj.style.height = '1px';
				}
				LeftPanel.xpPanel_currentDirection[obj.id] =LeftPanel.xpPanel_slideSpeed;
				LeftPanel.slidePane(LeftPanel.xpPanel_slideSpeed, obj.id);
			} else {
				obj.style.display = 'block';
				subDiv = obj.getElementsByTagName('DIV')[0];
				obj.style.height = subDiv.offsetHeight + 'px';
			}
			if (cookieNames[numericId])
				LeftPanel.Set_Cookie(cookieNames[numericId], '1', 100000);
		}
		return true;
	},
	
	 slidePane:function(slideValue, id) {
	 		
		if (slideValue != LeftPanel.xpPanel_currentDirection[id]) {
			return false;
		}
			
		var activePane = document.getElementById(id);
		if (activePane == LeftPanel.savedActivePane) {
			var subDiv = LeftPanel.savedActiveSub;
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
				setTimeout('LeftPanel.slidePane(' + slideValue + ',"' + id + '")', 10);
			} else {
				if (document.all)
					activePane.style.display = 'none';
			}
		} else {
			subDiv.style.top = height - subDiv.offsetHeight + 'px';
			activePane.style.height = height + 'px';
			if (height < innerHeight) {
				setTimeout('LeftPanel.slidePane(' + slideValue + ',"' + id + '")', 10);
			}
		}
	
	},
	
	 mouseoverTopbar:function() {
		var img = this.getElementsByTagName('IMG')[0];
		var src = img.src;
		img.src = img.src.replace('.gif', '_over.gif');
	
		var span = this.getElementsByTagName('SPAN')[0];
		span.style.color = '#428EFF';
	
	},
	
	 mouseoutTopbar:function() {
		var img = this.getElementsByTagName('IMG')[0];
		var src = img.src;
		img.src = img.src.replace('_over.gif', '.gif');
	
		var span = this.getElementsByTagName('SPAN')[0];
		span.style.color = '';
	
	}

}

