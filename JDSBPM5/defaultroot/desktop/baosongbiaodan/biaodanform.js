function showbdjs(uuid){


			//var uuid = _record.get('uuid');
			var win = new Ext.Window(
			{
				id :'positionWin',
				title :'查看详细信息',
				width :1010,
				autoHeight: true,
//				bodyStyle:'width:100%',   
				loadMask:true,
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :true,
				
				tbar:[
		          {
			          text:"保 存",
			          tooltip:"保存当前信息到草稿列表",
			          handler:function(){
			              // bsorgtree();
			                 window.frames("biaodan").window.getFormQueryString("form1");
			          },
			          iconCls: 'saveButton'
		         },
		         {
		            text: '发 送',
				    tooltip: '发送当前文件',
			        handler: function(){
			            window.frames("biaodan").document.getElementById("sendorsave").value="send";
			            window.frames("biaodan").window.getFormQueryString("form1");
				        
			       },
					scope: this,
					iconCls: 'send'
		         },
		         {
		            text: '重新填写',
				    tooltip: '重新填写表单',
			        handler: function(){
				        window.frames("biaodan").location.href="/desktop/baosongbiaodan/xxkbsbd.jsp";
			       },
					scope: this,
					iconCls: 'refAction'
		         }
		        ],
				items :[{
					collapsible: true,
			        checkboxToggle:false,
//			        collapsed: true,
			        xtype:'fieldset',
//			        title:'表单信息',
			        autoHeight:true,
			        collapsed:false,
			        width:990,
//		             bodyStyle:'width:100%',   
//			        height:670,
			        checkboxName:'jiben',
			        layout:'column',
			        border:true,
			        labelSeparator:'：',
			        fileUpload:true,
//			         bodyStyle:'width:100%', 
			        html:'<iframe id="biaodan" name="biaodan" frameborder="0" src="getbsbdAction.do?uuid='+uuid+'" width="985" height="630"></iframe>'
				}]

				

			});

	win.show();
				
		}
		

function  showbhbd(uuid){

//			alert(uuid);


            
			var win = new Ext.Window(
			{
				id :'bhpositionWin',
				title :'报送信息查看',
				width :1000,
//				renderTo: 'grid',
				closeAction :'close',
				closable :true,
				collapsible :true,
				autoScroll :false,
				shim:true,
				maximizable:true,
//				minimizable:true,
                animCollapse:true,
                constrainHeader:true,
                layout: 'fit',
                minSize: 175,
                maxSize: 400,
				/*tbar:[
		          {
			          text:"保 存",
			          tooltip:"保存当前信息到草稿列表",
			          handler:function(){
			                alert("go 保存");
			                var str="";
							window.frames("biaodan").document.forms("form1").submit();
			          },
			          iconCls: 'saveButton'
		         }
		        ],*/
				items :[{
//				collapsible: true,
//		        checkboxToggle:false,
//		        collapsed: true,
//		        xtype:'fieldset',
//		        title:'表单信息',
		        autoHeight:true,
//		        collapsed:false,
//		        width:982,
		         bodyStyle:'width:100%',   
//		        height:670,
//		        checkboxName:'jiben',
//		        layout:'column',
//		        border:true,
//		        labelSeparator:'：',
//		        fileUpload:true,
		        html:'<iframe id="biaodan" name="biaodan" frameborder="0" src="getbsbhAction.do?uuid='+uuid+'" width="982" height="700"></iframe>'
				}]

				

			});

	win.show();
				
		}
		function testahref(uuid,name,fenshu,kaishishijian,jieshushijian,banbenhao,biaohao){

			//alert("go testaherf");
			
			
			var addformssd = new Ext.form.FormPanel({
				labelAlign: 'left',
				labelWidth:100,
				height:200,
				region:'center',
				frame:true,
				title:'最新规则',
				url:'xxkgzSaveAndUpdateAction.do',
				defaultType:'textfield',
				autoDestroy : false,
				viewConfig:{
					forceFit:true
				},
				items:[
				{
					xtype:'hidden',
					name:'biaohao'
				},
				{
					xtype:'hidden',
					name:'uuid'
				},
				{
					xtype:'hidden',
					name:'banbenhao'
				},
				{
					fieldLabel:'规则名称',
					width:200,
					name:'name'
				},{
					fieldLabel:'分数',
					width:200,
					name:'fenshu'
				},{
					fieldLabel:'开始应用时间',
					name:'kaishishijian',
					xtype:'datefield',
					format:'Y-m-d',
					width:200
				},{
					fieldLabel:'结束时间',
					name:'jieshushijian',
					xtype:'datefield',
					format:'Y-m-d',
					width:200
				}],
				buttons:[{
					text:'确 定',
					handler:function(){
					addformssd.getForm().submit({
							success:function(form,action){
								//win.close();
								//successload();
								Ext.Msg.alert('信息','登记信息添加成功!');
							},
							failure:function(){
								Ext.Msg.alert('信息','操作失败!');
							}
						});
					}
				},{
					text:'重 置',
					handler:function(){
						//reform();
					}
				}]
			});
			var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex:'messageid' 
			});
			
			//列名称
			var cm = new Ext.grid.ColumnModel([
				sm,
				{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
			    {header:'名称',dataIndex: 'namemc',width:100,sortable:true},
			    {header:'分数',dataIndex: 'fenshu',width:100,sortable:true},
			    {header:'开始应用时间',dataIndex: 'kaishishijian',width:100,sortable:true},
			    {header:'结束时间',dataIndex: 'jieshushijian',width:100,sortable:true},
			    {header:'banbenhao',dataIndex: 'banbenhao',width:100,sortable:true,hidden:true},
			    {header:'简称',dataIndex: 'biaohao',width:100,sortable:true},
			    {header:'详细',dataIndex: 'xiangxi',width:100,sortable:true,hidden:true}   
			]);
//			var _record = Ext.getCmp('grid').getSelectionModel().getSelected();
//			alert(_record);
			var uuid_s = uuid;
			var biaohao_s = biaohao;
			var proxy = new Ext.data.HttpProxy({url:'/xxkgzInfolistAction.do?uuid='+uuid_s+'&biaohao='+biaohao_s});
			
			
			//链接
			var store = new Ext.data.Store({
				//proxy: new Ext.data.MemoryProxy(data),
				proxy: proxy,
				reader:new Ext.data.JsonReader({
					totalProperty:'totalCount',
					root:'root'
				},[
					{name:'uuid'},
					{name:'namemc'},
					{name:'fenshu'},
					{name:'kaishishijian'},
					{name:'jieshushijian'},
					{name:'banbenhao'},
					{name:'biaohao'},
					{name:'xiangxi'}
				])
			});

			
			
			var pgsize = 10;
			store.load({params:{start:0,limit:pgsize}});
			//面板
			var grid1= new Ext.grid.GridPanel({
				title:'规则历史记录',
				id:'grid1',
//				autoHeight: true,
			 	height:550,
				loadMask:true,
				region:'south',
//				renderTo: 'grid',
				store:store,
				cm:cm,
				sm:sm,
				bbar: new Ext.PagingToolbar({
					pageSize:pgsize,
					store:store,
					displayInfo:true,
					displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg:"没有记录"
				})
				
			});			
			
			//弹出的窗口
			var win = new Ext.Window({
				title:'评分规则详细信息',
				layout:'border',
				width:1000,
//				minWidth:350,
				height:800,
//				minHeight:200,
				items:[addformssd,grid1]
			});
			win.show();
			setform(uuid,name,fenshu,kaishishijian,jieshushijian,banbenhao,biaohao);
		}
		function setform(uuid,name,fenshu,kaishishijian,jieshushijian,banbenhao,biaohao){
//			alert("go setform");
//			var _record = Ext.getCmp('grid').getSelectionModel().getSelected();
//			alert(_record);
//			var namemc = _record.get('namemc');
//			var kaishishijian = _record.get('kaishishijian');
//			var jieshushijian = _record.get('jieshushijian');
//			var fenshu = _record.get('fenshu');
//			var biaohao = _record.get('biaohao');
//			var banbenhao = _record.get('banbenhao');
//			var uuid = _record.get('uuid');
			Ext.get('name').dom.value = name;
			Ext.get('kaishishijian').dom.value = kaishishijian;
			Ext.get('jieshushijian').dom.value = jieshushijian;
			Ext.get('fenshu').dom.value = fenshu;
			Ext.get('banbenhao').dom.value = banbenhao;
			Ext.get('biaohao').dom.value=biaohao;
			Ext.get('uuid').dom.value=uuid;
		}
function gzgrid(){
     alert("go gzgrid");
	var sm = new Ext.grid.CheckboxSelectionModel({
				dataIndex:'messageid' 
			});
	
			//列名称
			var cm = new Ext.grid.ColumnModel([
				sm,
				{header:'uuid',dataIndex: 'uuid',width:100,sortable:true,hidden:true},
			    {header:'名称',dataIndex: 'namemc',width:100,sortable:true},
			    {header:'分数',dataIndex: 'fenshu',width:100,sortable:true},
			    {header:'开始应用时间',dataIndex: 'kaishishijian',width:100,sortable:true},
			    {header:'结束时间',dataIndex: 'jieshushijian',width:100,sortable:true},
			    {header:'banbenhao',dataIndex: 'banbenhao',width:100,sortable:true,hidden:true},
			    {header:'简称',dataIndex: 'biaohao',width:100,sortable:true},
			    {header:'详细',dataIndex: 'xiangxi',width:100,sortable:true}   
			]);
			
			var proxy = new Ext.data.HttpProxy({url:'/xxkgzAction.do'});
			
			
			//链接
			var store = new Ext.data.Store({
				//proxy: new Ext.data.MemoryProxy(data),
				proxy: proxy,
				reader:new Ext.data.JsonReader({
					totalProperty:'totalCount',
					root:'root'
				},[
					{name:'uuid'},
					{name:'namemc'},
					{name:'fenshu'},
					{name:'kaishishijian'},
					{name:'jieshushijian'},
					{name:'banbenhao'},
					{name:'biaohao'},
					{name:'xiangxi'}
				])
			});

			
			
			var pgsize = 10;
			store.load({params:{start:0,limit:pgsize}});
			//面板
			var gridss= new Ext.grid.GridPanel({
				title:'规则列表',
				id:'gridss',
				autoHeight: true,
				 bodyStyle:'width:100%',   
				loadMask:true,
				region:'center',
//				renderTo: 'grid',
				store:store,
				cm:cm,
				sm:sm,
				
				tbar: [{
						text:'增加',
						handler:function(){
							//addgz();
						}
					}],
				bbar: new Ext.PagingToolbar({
					pageSize:pgsize,
					store:store,
					displayInfo:true,
					displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
					emptyMsg:"没有记录"
				})
				
			});
		var win = new Ext.Window({
				title:'评分规则详细信息',
				layout:'border',
				width:1000,
//				minWidth:350,
				height:800,
//				minHeight:200,
				items:[gridss]
			});
			win.show();
	
}

function addgz(){

			//alert("go testaherf");
			
			
			var addform = new Ext.form.FormPanel({
				labelAlign: 'left',
				labelWidth:100,
				height:200,
				region:'center',
				frame:true,
				title:'最新规则',
				url:'xxkgzSaveAndUpdateAction.do',
				defaultType:'textfield',
				autoDestroy : false,
				viewConfig:{
					forceFit:true
				},
				items:[
				{
					fieldLabel:'简称',
					width:'200',
					name:'biaohao',
					allowBlank:false,
//					fieldLabel  : "不能为空",
					blankText:'该项不能为空!',
//					regex :/^[A-Za-z]/,
//					regexText:'只能输入英文字母'
					vtype:'alpha',
					vtypeText:'只能输入英文字母'
				},
				{
					fieldLabel:'规则名称',
					width:200,
					name:'name',
					allowBlank:false
				},
				{
					fieldLabel:'规则简称',
					width:200,
					name:'jiancheng',
					allowBlank:false
				},
				{
					fieldLabel:'分数',
					width:200,
					name:'fenshu',
					allowBlank:false,
					regex :/^[0-9]/,
					regexText:'只能输入数字'
				},
				{
					fieldLabel:'开始应用时间',
					name:'kaishishijian',
					xtype:'datefield',
					format:'Y-m-d',
					width:200,
					allowBlank:false
				},
				{
					fieldLabel:'结束时间',
					name:'jieshushijian',
					xtype:'datefield',
					format:'Y-m-d',
					width:200,
					allowBlank:false
				}],
				buttons:[{
					text:'保 存',
					handler:function(){
					addform.getForm().submit({
							success:function(form,action){
								alert(action.response.statusText.ceshi);
								alert('信息','登记信息添加成功!');
							},
							failure:function(form,action){
							 Ext.Msg.alert("提示","保存失败:" + action.result.errors.info);
							
								//Ext.Msg.alert('信息','操作失败!');
							}
						});
					}
				},{
					text:'重 置',
					handler:function(){
						//reform();
					}
				}]
			});
			var win = new Ext.Window({
				title:'评分规则详细信息',
				layout:'border',
				width:350,
				height:350,
				items:[addform]
			});
			Ext.QuickTips.init(); 
			
			win.show();
			}
