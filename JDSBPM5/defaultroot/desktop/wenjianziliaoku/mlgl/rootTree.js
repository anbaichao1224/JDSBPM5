
// 人员窗体
function createPersonPositionWindow(positionid, positionname) {
	var txtCheckValue;
	var txtCheckName;
    var disabledIds='';
	var nid = document.getElementById("personid").value;
	
	var _width = 300;
	var _height = Ext.getBody().getHeight()-40;
	var root = new Ext.tree.AsyncTreeNode({
		text :'',
		leaf :false,
		cls :'folder',
		draggable :false,
		id :'toproot',
		expanded:function(){
		
			alert("expand");
		}
	});
	var tabs=new Ext.TabPanel({
		region:'center',
		margins:'3 3 3 0',
		activeTab:0,
		defaults:{autoScroll:true},
		items:[{
		   title:'部门人员',
		   html:''
		}
		
		]
	
	
	
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
                 (_height - 50) + "' src='wjzlRootCatalogMangerAction_getRootTree.action?checked="+nid+"&disabled="+disabledIds+"'></iframe>",
				buttons : [
						{
							text :'确定',
							handler : function() {
							var retValue = document.getElementById('orgtreeiframe').contentWindow.getCheckedNames();
	                        document.getElementById("sendrange").value=retValue.names;
							document.getElementById("personid").value=retValue.ids;
							Ext.getCmp("positionWin")
									.close();
							}
						}, {
							text :'取消',
							handler : function()
							 {
								win.close();
							 }
						} ]

			});

	win.show();

	
}
