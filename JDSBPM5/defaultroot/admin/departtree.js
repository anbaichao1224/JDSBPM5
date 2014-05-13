
// 人员窗体
function createPersonPositionWindow(positionid, positionname) {
	var txtCheckValue;
	var txtCheckName;
	var ids = document.getElementById("id").value;
    var disabledIds='';
	var _width = 220;
	var _height = Ext.getBody().getHeight()-80;
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
				title :'单位列表',
				//width :220,
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				layout : 'fit',
					width : _width,
					minWidth : 100,
					height : _height,
					minHeight : 100,
                 html:"<iframe id='orgtreeiframe' name='orgtreeiframe' width='" + (_width-10)  + "' height='" +
                 (_height - 55) + "' src='deptAction_addtree.action?checked="+ids+"&disabled="+disabledIds+"'></iframe>",
				buttons : [
				
						{
							text :'确定',
							handler : function() {
							var retValue = document.getElementById('orgtreeiframe').contentWindow.getCheckedNames();
	                        document.getElementById("deptnames").value=retValue.names;
							document.getElementById("id").value=retValue.ids;
							Ext.getCmp("positionWin")
									.close();
							}
						},{
							text :'取消',
							handler : function() {
								win.close();
							}
						} ]

			});

	win.show();


}

function splitDeptNames(deptnames){
	var strs= new Array(); //定义一数组
	strs=deptnames.split(","); //字符分割      
	for (i=0;i<strs.length ;i++ )    
	    {    
	        document.write(strs[i]+"<br/>");    //分割后的字符输出
	     } 

}
