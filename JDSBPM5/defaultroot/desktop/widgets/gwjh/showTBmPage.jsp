<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@page import="net.itjds.j2ee.util.UUID"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String uuid = (new UUID()).toString();
	String inceptid = request.getParameter("inceptid");
	String sendid = request.getParameter("id");
	String deptName = request.getParameter("deptName");
	String deptId = request.getParameter("deptId");
%>
<html>
	<head>
		<base href="<%=basePath%>"></base>
		<script>var context='<%=path%>';</script>
		<style type="text/css">
<!--
body {
	font-family: "宋体", "Arial";
	background-color: #dfe8f6
}

.STYLE3 {
	font-size: 24px
}

.STYLE4 {
	font-size: 20px
}
-->
</style>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		 <link rel="stylesheet" type="text/css" href="<%=path%>js/ext/resources/css/ext-all.css" />
   		 <script src="<%=path%>js/ext/adapter/ext/ext-base.js"></script>
   		 <script src='<%=path%>js/ext/ext-all.js'></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript" src="<%=path%>desktop/js/CreateGrid.js"></script>
		<script type="text/javascript" src="/desktop/widgets/gwjh/zmlgl/tbmtree.js" defer="defer"></script>
		<!-- <script type="text/javascript" src="/desktop/widgets/gwjh/js/bmtree.js" defer="defer"></script> -->
		<script src="/js/JDS.js" type="text/javascript"></script>
	</head>

	<body>
	  
		<form id="bmback" action="gwjhAction_tbmBack.action"  method="post">
			<input type="hidden" name="parentid" id="parentid"
				value='<%=request.getParameter("parentid")%>' />
			<input type="hidden" name="tid" id="tid" value='<%=uuid%>' />
			<input type="hidden" name="bmPersons" id="bmPersons"  />
			<input type="hidden" name="inceptid" id="inceptid" value='<%=inceptid%>' />
			<input type="hidden" name="inceptdeptId" id="inceptdeptId" value='<%=deptId%>' />
			<input type="hidden" name="id" id="id" value='<%=sendid%>' />
			<table align="center" width="624" height="300" border="1"
				cellpadding="0" cellspacing="0">
				
				<tr>
					<td height="45" colspan="6">
						<div align="center" class="STYLE3">
							 报名人员信息						
						</div>					
					</td>
				</tr>
				
				
				<tr>
				<td colspan="6">
					<table align="center" width="664" height="40"  border="0"
				cellpadding="0" cellspacing="0" >
						<tr>
						   <td height="144" border="1" width="83" >
								<div align="center">
									<p class="STYLE4">
										报
									</p>
									<p class="STYLE4">
										名
									</p>
									<p class="STYLE4">
										人
									</p>
									<p class="STYLE4">
										员
									</p>
								</div>
					 		 </td>
							 <td border="1">
							 	 <div id =bmrygridaa height="250"></div>
							 </td>
						    <td align="center">
						  		<input type="hidden" name="deptids" id="deptids" />
						  		<input type="hidden" name="deptnames" id="deptnames" />
								<input name="choice" size="70" type="button" id="choice"
									value="选择" onclick="createPersonPositionWindow('5015','')" />
						    </td>
						</tr>
						<tr>
						<td align="center" colspan="2">
							<input type="button" name="savematter" disable="true" onclick="save()"
								value="报名">
								

							
						<td />
					</tr>
					</table>
				
				</td>
				</tr>
				
		  </table>
		</form>
	</body>
<script type="text/javascript">
		var store;
		var data; 
	    var MyRecord = Ext.data.Record.create([
		    	{name : 'bmry',type: 'string'},
	      		{name : 'contactTel',type: 'string'},
	      		{name : 'gender' ,type: 'string'},
  		 		{name : 'duty' ,type: 'string'},
	      		{name : 'comment',type: 'string'},
	      		{name : 'px',type: 'string'}
		    
		    ]);
	    Ext.onReady(function(){
		    store = new Ext.data.Store({
		      	proxy : new Ext.data.MemoryProxy(data),
		      	reader : new Ext.data.ArrayReader({},[
		      		{name : 'bmry',type: 'string'},
		      		{name : 'contactTel',type: 'string'},
		      		{name : 'gender' ,type: 'string'},
  		 			{name : 'duty' ,type: 'string'},
		      		{name : 'comment',type: 'string'},
		      		{name : 'px',type: 'int'}
		      	])
		      
		      });
		       var fm = Ext.form;
		       // 复选框
				var sm = new Ext.grid.CheckboxSelectionModel({
					dataIndex : 'index'
				});
				
				var ComboData=[
					['男','男'],
					['女','女']
					
				];
		      	var cm = new Ext.grid.ColumnModel(
		      		 [sm,{
		             		header : "报名人员",
		             		dataIndex: "bmry",
		             		width: 100,
		            		editor: new fm.TextField({
		                 		allowBlank: false
				             })
			         	}, {	header: "联系电话",
				            	dataIndex: "contactTel",
				             	width: 130,
				           		editor: new fm.TextField({
		                 			allowBlank: true
				             	})
			        	}, {
			             	 header: "性别",
			            	dataIndex: "gender",
			            	 width: 70,
			            	editor: new Ext.grid.GridEditor(new Ext.form.ComboBox({
			            		store : new Ext.data.SimpleStore({
			            		   fields : ['value','text'],
			            		   data : ComboData
			            		}),
			            		mode : 'local',
			            		triggerAction : 'all',
			            		valueField : 'value',
			            		displayField : 'text',
			            		readOnly : true
			            	}))
			            	
		         		}, {
			             	 header: "职务",
			            	 dataIndex: "duty",
			            	 width: 100,
			            	editor: new fm.TextField({
		                 		allowBlank: true
				             })
		         		}, {
			             	 header: "备注",
			            	dataIndex: "comment",
			            	 width: 100,
			            	editor: new fm.TextField({
		                 		allowBlank: true
				             })
		         		}, {
			             	 header: "排序",
			            	dataIndex: "px",
			            	 width: 40,
			            	editor: new fm.TextField({
		                 		allowBlank: true,
		                 		regex:/^[0-9]*$/,
                                regexText:'请输入数字'
				             })
		         		}
		         	
     			]
		      );
		      	var grid = new Ext.grid.EditorGridPanel({
				    title : '报名列表',
				    cm : cm ,
				    sm : sm,
				    id : "bmgrid",
				    store : store,
				    width : 550,
					autoHeight : true,
		      		renderTo:'bmrygridaa',
		      		clicksToEdit:1,
		      		tbar : new Ext.Toolbar(['-',{
		      			text : "添加",
		      			handler : function(){
	      				    var p = new MyRecord({
		           				    'bmry' :'',
  		 							'contactTel' : '',
  		 							'gender' : '',
  		 							'duty' : '',
  									'comment' : '',
  									'px' : ''
				                   });
				              grid.stopEditing();
				              store.insert(0,p);
				              grid.startEditing(0,0);
		      			}
		      		
		      		},'-',{
		      		   text : "删除",
		      		   handler : function(){
		      		   	  Ext.Msg.confirm('信息','确定要删除',function(btn){
		      		   	  	 if(btn=='yes'){
	
		      		   	  	 	var smm = grid.getSelectionModel();
		      		   	  	 	var cell = smm.getSelected();
		      		   	  	 	 store.remove(cell);
		      		   	  	 }
		      		   	  })
		      		   }
		      		}
		      		
		      	]),
		      		listeners : {
		      		  'afteredit' : function(grid){
		      		   			/*var r = grid.record;//获取被修改的行  		
		      		   			var l = grid.field;//获取被修改的列
		      		  			var cm = r.get("contactTel");
		      		  			var cm1 = r.get("comment");
		      		  			var m='2';
		      		  			var contactTel11='';
		      		  			var comment11='';
		      		  			for(var i=0;i<m;i++){
		      		  			    var contactTel11=contactTel11+grid.store.getAt(i).data.contactTel;
		      		  			    if(i<m-1){
		      		  			    	contactTel11+=",";
		      		  			    }
		      		  			}
		      		  			grid.store.each(function(record){
		      		  					contactTel11 +=record.data.contactTel;
		      		  			});
		      		  			
		      		         if(l=='contactTel'){
		      		  			 document.getElementById("contactTel11").value=cm;
		      		  			  alert(document.getElementById("contactTel11").value);
		      		  		 }
		      		  		 if(l=='comment'){
							 	document.getElementById("comment11").value=cm1;
							 }
		      		  }*/
		      		}
		      	}
		      		
		      	});
		      
		});
		
	 function getBmPersons(){
	   var stringrecord="";
	 	for(var i=0;i<store.getCount();i++){
	 		var record = store.getAt(i);
	 		 stringrecord = stringrecord+record.get("bmry")+","+record.get("contactTel")+","+record.get("gender")+","+record.get("duty")+","+record.get("comment")+","+record.get("px");
	 		 if(i!=store.getCount()-1){
	 		 	stringrecord+="/";
	 		 }
	 	}
	 		
	   return stringrecord;
	 }
		      
		      
	// 人员窗体
	function createPersonPositionWindow(positionid, positionname) {
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
	
		var win = new Ext.Window({
					id :'positionWin',
					title :'人员列表',
					height : 500,
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
		      									'comment' : '',
		      									'px' : ''
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
		function save(){
					 Ext.Ajax.request({
			 	
			 	url:'gwjhAction_tbmBack.action',
			 	method:'post',
			 	waitMsg:'正在处理，请稍候...',
			 	params:{
			 	'bmPersons':getBmPersons()
			 	},
			 	form:'bmback',
			 	success:function(){
			 		Ext.Msg.alert('提示','报名成功');
			 	},failure:function(){
			 	   Ext.Msg.alert('提示','报名失败');
			 	}
			 })
		}
</script>
</html>