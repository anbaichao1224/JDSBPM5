
// 人员窗体
function createWindow() {
	var txtCheckValue;
	var txtCheckName;
    var disabledIds='';
	var nid = document.getElementById("deptids").value;
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
                 (_height - 50) + "' src='tbmAction_addtree.action?checked="+nid+"&disabled="+disabledIds+"'></iframe>",
				buttons : [{
								text :'确定',
								handler : function() {
									alert(nid);
									var retValue = document.getElementById('orgtreeiframe').contentWindow.getCheckedNames();
									var names = retValue.names;
									var idss=retValue.ids;
			                        document.getElementById("deptnames").value=retValue.names;
									document.getElementById("deptids").value=retValue.ids;
									     var  personids1=idss.split(","); 
		     							 var  personnames1=names.split(","); 
									   data ="[";
									    var store1 = Ext.getCmp("bmgrid").getStore();
								       for(var i=0;i<personids1.length;i++){
								           var p = new MyRecord({
								           		 'bmry' : personnames1[i],
		      		 							'contactTel' : '',
		      		 						    'gender' : '',
		      		 						    'duty' : '',
		      									'comment' : ''
								           });
								            store1.insert(i,p);
								       }
									Ext.getCmp("positionWin")
											.close();
								}
							}, {
								text :'取消',
								handler : function(){
									win.close();
								 }
							}]
	
				});
			win.show();
		}
