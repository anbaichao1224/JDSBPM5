
JDS.desktop.Msg={
	


	showOfficeMsgById:function (activityInstId,processInstId,title,body){
			var desktop = getCurDesktop();
	    	var notifyWin = desktop.showNotification({
				autoDestroy: false,
				hideDelay: 5000,
				iconCls: 'x-icon-waiting',
				html: '<a onclick=\"openWin(\'demodisplay.action?activityInstId='+activityInstId+'\',\''+activityInstId+'\')" >'+body+'</a>',
				title: title
			});
	},
	showSystemMsgById:function (title,body){
		var desktop = JDSDesk.getDesktop();
	    	var notifyWin = desktop.showNotification({
				autoDestroy: true,
				hideDelay: 5000,
				iconCls: 'x-icon-waiting',
				html: body,
				title: title
			});
	}
	

}