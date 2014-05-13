<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%@ page
 import="net.itjds.common.org.base.PersonNotFoundException"
 import="net.itjds.common.org.base.OrgManagerFactory"
 import="net.itjds.common.org.base.Person"

 
 %>
<%
		String personId =session.getAttribute("personId").toString();
		Person person=null;
		
		try {
			person = OrgManagerFactory.getOrgManager().getPersonByID(personId);
			
		} catch (PersonNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String bmid=person.getOrgList().get(0).getID();	
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://"
	
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
	
	</head>
	<body>
		<div id="grid" style="width:100%;height:100%"></div>
		<div id="form"></div>
	</body>
		<base href="<%=basePath%>">
		<script>var context='<%=path%>';</script>


		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript">
		 var bmid='<%=bmid%>';
		var store;
		Ext.onReady(function() {

	// 复选框
	var sm = new Ext.grid.CheckboxSelectionModel({
	              dataIndex : 'index',                       
                 renderer:function(value,cellmeta,record){                              
                if(record.get("personId")== record.get("ryid")){   
                   if(record.get("flag")=="0")  {             
                         // alert(record.get("flag"));
                      return "<div class=\"x-grid3-row-checker\">&#160;</div>";      
                      }else{
                         return " ";
                      }
                      
                 }else{                           
                     return " ";   
                     }                    
                },  
                
         // 不允许使用点击表格形式修改选择  
              handleMouseDown:Ext.emptyFn  
				
			});

	// 列名称
	var cm = new Ext.grid.ColumnModel([sm, {
	            header : '收文编号',
				dataIndex : 'SN',
				width : 100,
				sortable : true
			}, {
				header : '来文标题',
				dataIndex : 'DOCBT',
				width : 333,
				sortable : true
			}, {
			
				header : '等级',
				dataIndex : 'EMERGENCY',
				width : 100,
				sortable : true
			}, {
				header : ' 登记人',
				dataIndex : 'DJR',
				width : 100,
				sortable : true
			},{
				header : ' 原文编号',
				dataIndex : 'LWBH',
				width : 100,
				sortable : true
			},{
				header : ' 来电编号',
				dataIndex : 'LDBH',
				width : 100,
				sortable : true
			},{
				header : '单位',
				dataIndex : 'DEPARTMENT',
				width : 100,
				sortable : true
			
			}, {
				header : '来文日期',
				dataIndex : 'RDATE',
				width : 100,
				sortable : true
			}, {
				header : '操作',
				dataIndex : 'OPTION',
				width : 100
			}, {
				header : '操作',
				dataIndex : 'ZNBGW',
				width : 100
		    },{
				     header : '',
				     dataIndex : 'personId',
				     width : 150,
				     hidden:true,
				     sortable : true
	        },{
				     header : '',
				     dataIndex : 'ryid',
				     width : 150,
				     hidden:true,
				     sortable : true
		   },{
				     header : '',
				     dataIndex : 'flag',
				     width : 150,
				     hidden:true,
				     sortable : true
		     },{
				     header : '',
				     dataIndex : 'bmid',
				     width : 150,
				     hidden:true,
				     sortable : true
			},{
				header : '发送',
				dataIndex : 'JBL',
				width : 150,
				sortable : true
				
			}]);

	var proxy = new Ext.data.HttpProxy({
		url : '/SwdjAction_findAllByModel.action?xmlmodel=<ww:property value="xmlmodel"></ww:property>'
	});

	// 链接
	 store = new Ext.data.Store({
				// proxy: new Ext.data.MemoryProxy(data),
				proxy : proxy,
				reader : new Ext.data.JsonReader({
							totalProperty : 'totalCount',
							root : 'root'
						}, [{
						        name : 'SN'
								}, {
									name : 'DOCBT'
								}, {
								    name : 'EMERGENCY'
								}, {
									name : 'DJR'
								}, {
								    name : 'LWBH'
								}, {
									name : 'LDBH'
								}, {
									name : 'DEPARTMENT'
								}, {
									name : 'CLASSIFICATION'
								}, {
									
									name:'RDATE'
								}, {
									
									name : 'UUID'
								}, {
									name : 'JBL'
								}, {
									name : 'OPTION'
								}, {
									name : 'ZNBGW'
								}, {
									name : 'personId'
								}, {
									name : 'ryid'
								}, {
									name : 'flag'
								}, {
									name : 'bmid'
								
								}])
			});

	var docname = new Ext.form.TextField({
				width :100,
				name : 'docname',
				allowBlank : true
			});

	var rdata = new Ext.form.DateField({
				width : 100,
				name : 'rdata',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
			});
			
	var tdata = new Ext.form.DateField({
				width : 100,
				name:'tdata',
				format : 'Y-m-d',
				cls : "x-btn-text-icon"
	});

	var department = new Ext.form.TextField({
				width : 100,
				name : 'department',
				allowBlank : true
			});
     var swbh = new Ext.form.TextField({
				width : 100,
				name : 'swbh',
				allowBlank : true
			});
			var ywbh = new Ext.form.TextField({
				width : 100,
				name : 'ywbh',
				allowBlank : true
			});
    // 启动流程
	startlc = function() {
		var _width = 240;
		var _height = 160;
		var xmlmodel = '<ww:property value="xmlmodel"></ww:property>';
		var _record = Ext.getCmp('grid').getSelectionModel().getSelected();
		var uuid = _record.get('UUID');
		var startwin = new Ext.Window({
					title : '选择启动流程窗口',
					id:'startwin',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					html:"<iframe id='startpiframe' name='startpiframe' width='" + (_width + 2)  + "' height='" + (_height + 100) + "' src='/startP.action?xmlmodel=" + xmlmodel + "&uuid=" + uuid + "'></iframe>",
					buttons:[{
						text:"启动",
						handler:function(){
						 	var iframe = document.frames['startpiframe'];
						 	iframe.startPsubmit();
						 	startwin.close();
						}
					}]
			});
		startwin.show();
	}

	var pgsize = 24;

	// 面板
	var grid = new Ext.grid.GridPanel({
		title : '收文登记',
		id : 'grid',
		layout : 'fit',
		width : '1383',
		bodyStyle : 'width:100%',
		autoHeight : true,
		loadMask : true,
		renderTo : 'grid',
		store : store,
		cm : cm,
		sm : sm,
		bbar : new Ext.PagingToolbar({
					pageSize : pgsize,
					store : store,
					displayInfo : true,
					displayMsg : '显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg : "没有记录"
				}),
		tbar : new Ext.Toolbar(['来文标题：', docname, '来文单位：', department,'收文编号：', swbh,'原文编号：', ywbh, '开始日期：',
				rdata, '结束时间:', tdata,{// 查询按钮
					id : 'newModelButton',
					icon : "/usm/img/search.png",
					text : '查  询',
					cls : "x-btn-text-icon",
					handler : queryDj
				}, {
					icon : '/usm/img/depart.gif',
					text : '重  置',
					cls : "x-btn-text-icon",
					handler : requery
				}, {
					icon : '/usm/img/depart.gif',
					text : '刷新',
					cls : "x-btn-text-icon",
					handler : refresh
				}, {
					xtype : "tbfill"
				}, {
					text : '新增',
					icon : '/usm/img/add.gif',
					cls : "x-btn-text-icon",
					handler : function(){
						//window.top.showaddwin('<ww:property value="xmlmodel"></ww:property>',grid);
						var xmlmodel = '<ww:property value="xmlmodel"/>';
						var _width = 900;
			var _height = Ext.getBody().getHeight()-40;
			var addwin = new Ext.Window({
					title : '添加登记信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-30)  + "' height='" + (_height - 60) + "' src='/SwdjAction_addpage.action?xmlmodel=" + xmlmodel + "'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '登  记',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addDj");
							    var djlwbt = Ext.get('djiframe').dom.contentWindow.document.getElementById("djlwbt").value;
						          if(djlwbt==""){
						              Ext.Msg.alert('提示', '标题不能为空');
						              return false;
						           }	
								fn.submit();
								alert('添加成功');
								store.reload();
								addwin.close();
							}
						},{
							id : 'djreset',
							icon : "/usm/img/depart.gif",
							text : '重  置',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("addDj");
								fn.reset();
							}
						}
					]
			});
			addwin.show();
						
					}
			//	}, '-', {
			//		text : '修改',
			//		icon : '/usm/img/yingyong.gif',
			//		cls : "x-btn-text-icon",
			//		handler : updatepage
				}, '-', {
					text : '删除',
					icon : '/usm/img/delete.gif',
					cls : "x-btn-text-icon",
					handler : function() {
						var deluuid = new Array();
						var selections = grid.getSelectionModel()
								.getSelections();
						if (selections.length == 0) {
							alert('请选择需要删除的文件');
							return;
						}

						var delAllList = new Array();
						for (var i = 0; i < selections.length; i++) {
							if (selections[i].get('UUID').length > 0) {
								delAllList[delAllList.length] = selections[i]
										.get('UUID');
							} else {
								grid
										.getSelectionModel()
										.deselectRow(selections[i].get('index'));
							}
						}
						deluuid = delAllList.join();
						Ext.Ajax.request({
							url : '/SwdjAction_delDj.action?xmlmodel=<ww:property value="xmlmodel"></ww:property>',
							params : {
								deluuid : deluuid
							},
							success : function(resp, opts) {
								// Ext.Msg.alert('信息', '登记信息删除成功!');
								
								store.load({
											params : {
												start : 0,
												limit : pgsize
											}
										});
							},
							failure : function(resp, opts) {
								Ext.Msg.alert('信息', '登记信息删除失败!');
							}
						});
					}
				},'-',{
					text : '导出',
					icon : '/usm/img/duty.gif',
					cls : "x-btn-text-icon",
					handler : function(){
						
						if('<ww:property value="xmlmodel"/>'=='dj-zyl'){
							location.href='/dj/exportexceldckb.jsp?bmid='+'<%=bmid%>';
						}else if('<ww:property value="xmlmodel"/>'=='dj-zyl'){
							location.href='/dj/exportexceldcks.jsp';
						}
						
					}
				}])
	});

	store.on('beforeload', function() {
				this.baseParams = {
					docname : Ext.get('docname').dom.value,
					department : Ext.get('department').dom.value,
					rdata : Ext.get('rdata').dom.value,
					swbh : Ext.get('swbh').dom.value,
					ywbh : Ext.get('ywbh').dom.value
				};
			});

	store.load({
				params : {
					start : 0,
					limit : pgsize,
					docname : Ext.get('docname').dom.value,
					department : Ext.get('department').dom.value,
					rdata : Ext.get('rdata').dom.value,
					swbh : Ext.get('swbh').dom.value,
					ywbh : Ext.get('ywbh').dom.value
				}
			});

	// 登记查询
	function queryDj() {
		store.load({
					params : {
						start : 0,
						limit : pgsize,
						docname : docname,
						department : department,
						rdata : rdata,
						tdata : tdata,
						swbh : swbh,
						ywbh : ywbh
					}
				});
	}
	
	// 刷新
	function refresh() {
		store.load({
					params : {
						start : 0,
						limit : pgsize
			
					}
				});
	}
	

	function successload() {
		store.load({
					params : {
						start : 0,
						limit : pgsize
					}
				});
	}

	// 登记条件重置
	function requery() {
		Ext.get('docname').dom.value = '';
		Ext.get('department').dom.value = '';
		Ext.get('rdata').dom.value = '';
		Ext.get('tdata').dom.value = '';
		Ext.get('swbh').dom.value = '';
		Ext.get('ywbh').dom.value = '';
	}

	// 表单数据重置
	function reform() {
		Ext.get('formbh').dom.value = '';
		Ext.get('formdocbt').dom.value = '';
		Ext.get('formdepartment').dom.value = '';
		Ext.get('formrdate').dom.value = '';
		Ext.get('formjjcd').dom.value = '一般';
		Ext.get('formmj').dom.value = '一般';
	}
	
});
//转内部公文交换
function zswdj(uuid){
      var _width = 900;
		var _height = Ext.getBody().getHeight()-40;
		var addwin = new Ext.Window({
					title : '内部公文交换',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					y:0,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-10)  + "' height='" + (_height - 50) + "' src='SwdjAction_znbgw.action?zdjuuid="+uuid+"'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '发送',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("djnbgwjh");
								//发文人不能为空
								var inceptdept=fn.deptnames.value
         					 	 if(inceptdept==""){
             					 	alert("发送人不能为空");
           					  	 	return false;
           						}
								//附件不能为空
							//	var temp = document.getElementById('djiframe');
							//	var count = temp.contentWindow.getCount();
							//	if(count==0){
						    //    alert("附件不能为空");
						    //       return;
					        // 	}
						         
								fn.submit();
								alert('发送成功');
								store.reload();
								addwin.close();
							}
						}
					]
			});
			addwin.show();
		 
}
	//取回已经交办理的
	function quhui(uuid){
      	Ext.Ajax.request({
			url : '/JblAction_updateFlagTo0.action',
			params : {
				tid : uuid
			},
			success : function(resp, opts) {
				Ext.Msg.alert('信息', '取回成功!');
				store.load({
					params : {
						start : 0,
						limit : 10
					}
				});
	                          
			},
			failure : function(resp, opts) {
			alert(resp.responseText);
				Ext.Msg.alert('信息', '取回失败!');
			}
		});
	}
	


  //交办理的发送
    function jiaomanagne(uuid){
   Ext.Ajax.request({
		url : '/SwdjAction_findFlagBySwdjId.action',
		params : {
			uuid : uuid
		},
		success : function(o) {
			
			if(o.responseText=='0'){
				showshowwin(uuid,'<ww:property value="xmlmodel"></ww:property>');
			}else{
				Ext.Msg.alert('信息', '流程已经启动 不能交办理');
			}	
		},
		failure : function(resp, opts) {
			//alert(resp.responseText);
			Ext.Msg.alert('信息', '交办理失败!');
		}
	});
 	
       
  	}
  		function showshowwin(uuid,xmlmodel){
			var _width = 900;
			var _height = 600;
			var updatewin = new Ext.Window({
					title : '信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-30)  + "' height='" + (_height - 50) + "' src='/SwdjAction_showmanagne.action?xmlmodel=" + xmlmodel + "&uuid=" + uuid + "'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '发送',
							cls : "x-btn-text-icon",
							handler : function(){
							   var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("showmanagne");
                               var faSongRen = Ext.get('djiframe').dom.contentWindow.document.getElementById("sendrange").value;
								if(faSongRen == ''){
									Ext.Msg.alert('提示', '请选择发送人');
								}else{
									fn.submit();
									store.reload();
									Ext.MessageBox.alert('提示', '发送成功');
									updatewin.close();
								}
							}
						}
					]
			});
			updatewin.show();
}
	
	
	//显示修改数据页面的方法
	function updatepage(uuid){
	    showupdatewin(uuid,'<ww:property value="xmlmodel"></ww:property>');
	}
	
	function showupdatewin(uuid,xmlmodel){
	 		var _width = 900;
			var _height = 600;
			var updatewin = new Ext.Window({
					title : '修改登记信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-30)  + "' height='" + (_height - 50) + "' src='/SwdjAction_updatePage.action?xmlmodel=" + xmlmodel + "&uuid=" + uuid + "'></iframe>",
					tbar:[
						{
							id : 'djsave',
							icon : "/usm/img/save.gif",
							text : '修  改',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("updateDj");
								fn.submit({
									success:function(){
									store.reload();
										Ext.Msg.alert('信息','登记信息添加成功!');
									},
									failure:function(){
										Ext.Msg.alert('信息','操作失败!');
									}
								});
								updatewin.close();
							}
						}
					]
			});
			updatewin.show();
}
//不是本人显示修改页面的方法
	function djck(uuid){
		showupdatewin1(uuid,'<ww:property value="xmlmodel"></ww:property>');
	}
	//收文登记查看页面
function showupdatewin1(uuid,xmlmodel){
var _width = 900;
			var _height = 600;
			var updatewin = new Ext.Window({
					title : '查看信息',
					layout : 'fit',
					width : _width,
					minWidth : 350,
					height : _height,
					minHeight : 200,
					html:"<iframe id='djiframe' name='djiframe' width='" + (_width-30)  + "' height='" + (_height - 40) + "' src='/SwdjAction_djck.action?xmlmodel=" + xmlmodel + "&uuid=" + uuid + "'></iframe>",
					tbar:[
						{
							id : 'djsave',
						
							text : '',
							cls : "x-btn-text-icon",
							handler : function(){
								var fn = Ext.get('djiframe').dom.contentWindow.document.getElementById("updateDj");
								fn.submit({
									success:function(){
										Ext.Msg.alert('信息','登记信息添加成功!');
									},
									failure:function(){
										Ext.Msg.alert('信息','操作失败!');
									}
								});
								updatewin.close();
							}
						}
					]
			});
			updatewin.show();
}

function startlc() {
	
}
		</script>
</html>