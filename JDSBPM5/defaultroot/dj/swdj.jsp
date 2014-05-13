<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="ww"%>
<%
	String path = request.getContextPath();

	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<base href="<%=basePath%>">
		<script>var context='<%=path%>';</script>


		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">

		<script type="text/javascript" src="<%=path%>js/extAll.js"></script>
		<script type="text/javascript" src="<%=path%>/js/ext/TabCloseMenu.js"></script>
		<script type="text/javascript">

		Ext.onReady(function(){
			
			//复选框
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex:'index' 
			});
	
			//列名称
			var cm = new Ext.grid.ColumnModel([
				sm,
				{header:'来文标题',dataIndex:'DOCBT',width:100,sortable:true},
				{header:'来文日期',dataIndex:'RDATE',width:100,sortable:true},
				{header:'单位',dataIndex:'DEPARTMENT',width:100,sortable:true},
				{header:'密级',dataIndex:'CLASSIFICATION',width:100,sortable:true},
				{header:'紧急程度',dataIndex:'EMERGENCY',width:100,sortable:true},
				{header:'编号',dataIndex:'SN',width:100,sortable:true},
				{header:'流程模块',dataIndex:'MODELTYPE',width:100,sortable:true},
				{header:'操作',dataIndex:'OPTION',width:100}
			]);
			
			var proxy = new Ext.data.HttpProxy({url:'/djAction_findAllByVersionId.action?versionid=<ww:property value="versionid"></ww:property>'});
			
			
			//链接
			var store = new Ext.data.Store({
				//proxy: new Ext.data.MemoryProxy(data),
				proxy: proxy,
				reader:new Ext.data.JsonReader({
					totalProperty:'totalCount',
					root:'root'
				},[
					{name:'DOCBT'},
					{name:'RDATE'},
					{name:'DEPARTMENT'},
					{name:'CLASSIFICATION'},
					{name:'EMERGENCY'},
					{name:'SN'},
					{name:'MODELTYPE'},
					{name:'UUID'},
					{name:'OPTION'}
				])
			});
			
			var docname = new Ext.form.TextField({
				width:100,
				name:'docname',
				allowBlank:true
			});
			
			var rdata = new Ext.form.DateField({
				width:100,
				name:'rdata',
				format:'Y-m-d',
				cls : "x-btn-text-icon"
			});
			
			var department = new Ext.form.TextField({
				width:100,
				name:'department',
				allowBlank:true
			});
			
			//启动流程
			startlc = function(){
				Ext.Ajax.request({
					url:'/djAction_startP.action?versionid=<ww:property value="versionid"></ww:property>',
					success:function(resp,opts){
						Ext.Msg.alert('信息','启动流程成功!');
						store.load({params:{start:0,limit:pgsize}});
								},
					failure:function(resp,opts){
						Ext.Msg.alert('信息','启动流程失败!');
					}
				});
			}
			
			var pgsize = 10;
			
			//面板
			var grid= new Ext.grid.GridPanel({
				title:'收文登记',
				id:'grid',
				layout:'fit',
				bodyStyle:'width:100%',
				autoHeight:true,
				loadMask:true,
				renderTo: 'grid',
				store:store,
				autoScroll: true,
				cm:cm,
				sm:sm,
     			bbar: new Ext.PagingToolbar({
					pageSize:pgsize,
					store:store,
					displayInfo:true,
					displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg:"没有记录"
				}),
				tbar: new Ext.Toolbar([
					'来文标题：', docname, '来文单位：', department,'来文日期：', rdata,
				{// 查询按钮   
                    id : 'newModelButton',    
                    icon : "/usm/img/search.png", 
                    text:'查  询',
                    cls : "x-btn-text-icon",   
                    handler :queryDj
            	},{
            		icon:'/usm/img/depart.gif',
            		text:'重  置',
            		cls : "x-btn-text-icon",
            		handler :requery
            	},{
				xtype:"tbfill"
				},{
					text:'登记',
					icon:'/usm/img/add.gif',
					cls : "x-btn-text-icon",
					handler:addDj
				},'-',{
					text:'修改',
					icon:'/usm/img/yingyong.gif',
					cls : "x-btn-text-icon",
					handler:updateDj
				},'-',{
					text:'删除',
					icon:'/usm/img/delete.gif',
					cls : "x-btn-text-icon",
					handler:function(){
						var deluuid = new Array();
		 			    var selections=grid.getSelectionModel().getSelections();
					    if (selections.length==0){
					        alert('请选择需要删除的文件');
					   		return;
					    }
					 
					    var delAllList=new Array();
					    for(var i=0;i<selections.length;i++){
						   if (selections[i].get('UUID').length>0 ){     
						   		delAllList[delAllList.length]= selections[i].get('UUID');    
						   }else{
						     	grid.getSelectionModel().deselectRow(selections[i].get('index'));   
						   }
					    }
					    deluuid = delAllList.join();
						Ext.Ajax.request({
							url:'/djAction_delDj.action?versionid=<ww:property value="versionid"></ww:property>',
							params:{
								deluuid:deluuid
							},
							success:function(resp,opts){
								Ext.Msg.alert('信息','登记信息删除成功!');
								store.load({params:{start:0,limit:pgsize}});
							},
							failure:function(resp,opts){
								Ext.Msg.alert('信息','登记信息删除失败!');
							}
						});
					}
				}])
			});
			
			store.on('beforeload',function(){this.baseParams ={docname:Ext.get('docname').dom.value,department:Ext.get('department').dom.value,rdata:Ext.get('rdata').dom.value};});

			store.load({params:{start:0,limit:pgsize,docname:Ext.get('docname').dom.value,department:Ext.get('department').dom.value,rdata:Ext.get('rdata').dom.value}});
			
			
			//登记查询
			function queryDj(){
				store.load({params:{start:0,limit:pgsize,docname:docname,department:department,rdata:rdata}});
			}
			
			function successload(){
				store.load({params:{start:0,limit:pgsize}});
			}
			
			//登记条件重置
			function requery(){
				Ext.get('docname').dom.value = '';
				Ext.get('department').dom.value = '';
				Ext.get('rdata').dom.value = '';
			}
			
			//表单数据重置
			function reform(){
				Ext.get('formbh').dom.value = '';
				Ext.get('formdocbt').dom.value = '';
				Ext.get('formdepartment').dom.value = '';
				Ext.get('formrdate').dom.value = '';
				Ext.get('formjjcd').dom.value = '普通';
				Ext.get('formmj').dom.value = '普通';
			}
			
	
			//登记添加
			function addDj(){
				
				var mj = [
					['yb','一般'],
					['mm','秘密'],
					['jm','机密'],
					['juem','绝密']
				];
				
				var jjcd = [
					['yb','一般'],
					['jj','平急'],
					['tj','加急'],
					['jj','特急'],
					['jj','特提']
				]
				
				var sstore = new Ext.data.SimpleStore({
					fields:['value','text'],
					data:mj
				});

				var ssstore = new Ext.data.SimpleStore({
					fields:['value','text'],
					data:jjcd
				});
				
				var combo = new Ext.form.ComboBox({
					store:sstore,
					fieldLabel:'密 级',
					name:'formmj',
					emptyText:'普通',
					mode:'local',
					valueField: 'value',
        			displayField: 'text',
        			triggerAction: 'all'
				});
				
				var jjcdcombo = new Ext.form.ComboBox({
					store:ssstore,
					fieldLabel:'紧急程度',
					name:'formjjcd',
					emptyText:'普通',
					mode:'local',
					valueField: 'value',
        			displayField: 'text',
        			triggerAction: 'all'
				});
				
				//添加的from
				var addForm = new Ext.form.FormPanel({
					labelAlign: 'left',
					labelWidth:100,
					frame:true,
					url:'/djAction_saveDj.action?versionid=<ww:property value="versionid"></ww:property>',
					defaultType:'textfield',
					//autoDestroy : false,
					items:[{
						fieldLabel:'编号',
						width:200,
						name:'formbh'
					},{
						fieldLabel:'来文标题',
						width:200,
						name:'formdocbt'
					},{
						fieldLabel:'来文单位',
						name:'formdepartment',
						width:100
					},{
						fieldLabel:'来文日期',
						name:'formrdate',
						xtype:'datefield',
						format:'Y-m-d'
					},combo,jjcdcombo],
					buttons:[{
						text:'确 定',
						handler:function(){
							addForm.getForm().submit({
								success:function(form,action){
									Ext.Msg.alert('信息','登记信息添加成功!');
									successload();
									win.close();
								},
								failure:function(){
									Ext.Msg.alert('信息','操作失败!');
								}
							});
						}
					},{
						text:'重 置',
						handler:function(){
							reform();
						}
					}]
				});
				

				
				//弹出的窗口
				var win = new Ext.Window({
					title:'添加登记信息',
					layout:'fit',
					width:350,
					minWidth:350,
					height:250,
					minHeight:200,
					items:addForm
				});
				win.show();
			}	
			
	
			
			
			
			//登记修改
			function updateDj(){
				
				var records =  Ext.getCmp('grid').getSelectionModel().getSelections(); 
				if(records.length == 0){
					alert("请选择需要修改的记录");
					return;
				}else if(records.length > 1) {
					alert("请进行单项选择");
					return;
				}
				var mj = [
					['pt','普通'],
					['jm','机密'],
					['juem','绝密']
				];
				
				var jjcd = [
					['pt','普通'],
					['jj','紧急'],
					['tj','特急']
				]
				
				var sstore = new Ext.data.SimpleStore({
					fields:['value','text'],
					data:mj
				});

				var ssstore = new Ext.data.SimpleStore({
					fields:['value','text'],
					data:jjcd
				});
				
				var combo = new Ext.form.ComboBox({
					store:sstore,
					fieldLabel:'密 级',
					name:'uformmj',
					emptyText:'普通',
					mode:'local',
					valueField: 'value',
        			displayField: 'text',
        			triggerAction: 'all'
				});
				
				var jjcdcombo = new Ext.form.ComboBox({
					store:ssstore,
					fieldLabel:'紧急程度',
					name:'uformjjcd',
					emptyText:'普通',
					mode:'local',
					valueField: 'value',
        			displayField: 'text',
        			triggerAction: 'all'
				});
				
				var uuidHidden = new Ext.form.Hidden({
					name:'uuid'
				});
				
				//修改的from
				var updateForm = new Ext.form.FormPanel({
					labelAlign: 'left',
					id:'updateForm',
					labelWidth:100,
					frame:true,
					url:'/djAction_updateDj.action?versionid=<ww:property value="versionid"></ww:property>',
					defaultType:'textfield',
					//autoDestroy : false,
					items:[{
						fieldLabel:'编号',
						width:200,
						name:'uformbh'
					},{
						fieldLabel:'来文标题',
						width:200,
						name:'uformdocbt'
					},{
						fieldLabel:'来文单位',
						name:'uformdepartment',
						width:100
					},{
						fieldLabel:'来文日期',
						name:'uformrdate',
						xtype:'datefield',
						format:'Y-m-d'
					},combo,jjcdcombo,uuidHidden],
					buttons:[{
						text:'确定',
						handler:function(){
							updateForm.getForm().submit({
								success:function(form,action){
									updatewin.close();
									successload();
									Ext.Msg.alert('信息','登记信息添加成功!');
								},
								failure:function(){
									Ext.Msg.alert('信息','操作失败!');
								}
							});
						}
					}]
				});
				
				//弹出的窗口
				var updatewin = new Ext.Window({
					title:'添加登记信息',
					layout:'fit',
					width:350,
					minWidth:350,
					height:250,
					minHeight:250,
					autoDestroy : false,
					items:updateForm
				});
				updatewin.show();

				setform();
			}
			
					
			//注入修改表单数据
			function setform(){
				var _record = Ext.getCmp('grid').getSelectionModel().getSelected();
				var uformbh = _record.get('SN');
				var uformdocbt = _record.get('DOCBT');
				var uformdepartment = _record.get('DEPARTMENT');
				var uformrdate = _record.get('RDATE');
				var uformmj = _record.get('CLASSIFICATION');
				var uformjjcd = _record.get('EMERGENCY');
				var uuid = _record.get('UUID');
				Ext.get('uuid').dom.value = uuid;
				Ext.get('uformbh').dom.value = uformbh;
				Ext.get('uformdocbt').dom.value = uformdocbt;
				Ext.get('uformdepartment').dom.value = uformdepartment;
				Ext.get('uformrdate').dom.value = uformrdate;
				Ext.get('uformjjcd').dom.value = uformjjcd;
				Ext.get('uformmj').dom.value = uformmj;	
				
			}
		});
		
		function startlc(){
		
		}
		

	</script>
	</head>
	<body>
		<div id="form"></div>
		<div id="grid"></div>
	</body>
</html>