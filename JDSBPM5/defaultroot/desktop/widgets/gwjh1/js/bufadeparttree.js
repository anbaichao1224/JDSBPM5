
// 人员窗体
function createPersonPositionWindow(positionid, positionname) {
	var txtCheckValue;
	var txtCheckName;
	var ids = document.getElementById("id").value;
    var disabledIds=document.getElementById("yfid").value;
    alert(disabledIds);
     alert(ids);
	var _width = 300;
	var _height = Ext.getBody().getHeight()-40;
	var root = new Ext.tree.AsyncTreeNode( {
		text :'组织机构',
		leaf :false,
		cls :'folder',
		draggable :false,
		id :'toproot',
		expanded:function(){
		
			alert("expand");
		}
	});
	var win = new Ext.Window(
			{
				id :'positionWin',
				title :'人员列表',
				//width :220,
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
                html:"<iframe id='orgtreeiframe' name='orgtreeiframe' width='" + (_width-10)  + "' height='" +
                 (_height - 50) + "' src='deptAction_addtree.action?checked="+ids+"&disabled="+disabledIds+"'></iframe>",
				buttons : [
						{
							text :'确定',
							handler : function() {
							var retValue = document.getElementById('orgtreeiframe').contentWindow.getCheckedNames();
	                        document.getElementById("bufadeptnames").value=retValue.names;
							document.getElementById("id").value=retValue.ids;
							Ext.getCmp("positionWin")
									.close();
							}
						}, {
							text :'取消',
							handler : function() {
								win.close();
							}
						} ]

			});

	win.show();
}
