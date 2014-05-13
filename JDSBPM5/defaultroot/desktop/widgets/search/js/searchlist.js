
Ext.onReady(function(){
				
	
						//列名称
						var cm;
						var store;
						var urlstr = 'search_searchlist.action';
						var proxy = new Ext.data.HttpProxy({url:urlstr});
						var dkeywords = new Ext.form.TextField({
		 						id:'title1',
		 						width:150,
		 						name:'title'	
							});
					var tkeywords = new Ext.form.TextField({
		 						id:'rolltitle',
		 						width:150,
		 						name:'rolltitle'	
							});
					var twokeywords = new Ext.form.TextField({
		 						id:'filetitle',
		 						width:150,
		 						name:'filetitle'	
							});
					var yzh = new Ext.form.TextField({
		 						id:'yzh',
		 						width:150,
		 						name:'yzh'	
							});
					var rollnum = new Ext.form.TextField({
		 						id:'rollnum',
		 						width:150,
		 						name:'rollnum'	
							});			
					var yearnum = new Ext.form.TextField({
		 						id:'yearnum',
		 						width:150,
		 						name:'yearnum'	
							});	
					var mj = new Ext.form.ComboBox({
                    			name: 'miji',
                    			width:60,
                    			triggerAction:"all",
                    			store: new Ext.data.SimpleStore({
                        				fields: ['disval','valval'],
                        				data:[['明文','明文']]
                   				 }),
                   				 displayField:'disval',
                   				 valueField:'valval',
                    			mode: 'local',
                    			selectOnFocus:true
                  			});
							var cdate = new Ext.form.DateField({
	               					 name: 'createdate',
	               					 format:'Y-m-d'
            				});		
					var jjcd = new Ext.form.ComboBox({
                    			fieldLabel: '紧急程度',
                    			name: 'jjcd',
                    			width:60,
                    			triggerAction:"all",
                    			store: new Ext.data.SimpleStore({
                        				fields: ['disval','valval'],
                        				data: [['一般','一般'], ['平急','平急'],['加急','加急'],['特急','特急'],['特提','特提']]
                   				 }),
                   				 displayField:'disval',
                   				 valueField:'valval',
                    			mode: 'local',
                    			selectOnFocus:true
                  			});	
					
					var docvalue = new Ext.form.TextField({
						id:'docvalue',
						name:'docvalue',
						width:150
					});
					
						var ttbar = new Ext.Toolbar(
								['标题：',dkeywords,{  
					                id : 'newModelButton',
					                text : '综合查询',
					                cls : "x-btn-text-icon",
					                icon: '/desktop/resources/images/search.jpg',
									handler : function(){
     			         				store.load({params:{start:0,limit:pgsize,title:dkeywords}});
									}
			}
			]);
			var twotbar = new Ext.Toolbar(
							["标题：",twokeywords,"密级:",mj,"编号：",yzh,"成文日期:",cdate,"正文关键字：",docvalue,{
								text:'文件查询',
								cls : "x-btn-text-icon",
								icon: '/desktop/resources/images/search.jpg',
								handler : function(){
										store.load({params:{start:0,limit:pgsize,title:twokeywords,miji:mj,jjcd:jjcd,yzh:yzh,seartype:'2'}});
								}
							}]
						);
			var threetbar = new Ext.Toolbar([
						"标题：",tkeywords,"案卷号:",rollnum,"年度：",yearnum,{
							text:'案卷查询',
								cls : "x-btn-text-icon",
								icon: '/desktop/resources/images/search.jpg',
								handler : function(){
										store.load({params:{start:0,limit:pgsize,title:tkeywords,miji:rollnum,jjcd:yearnum,seartype:'1'}});
								}
						}
			]);
			
			var ttbar1 = new Ext.Toolbar([{
				text:'文件',
				cls:'x-btn-text-icon',
				handler:function(){
					store.on('beforeload', function() {
							this.baseParams = {
								miji:Ext.get('miji').dom.value,
								jjcd:Ext.get('jjcd').dom.value,
								yzh:Ext.get('yzh').dom.value,
								filetitle:Ext.get('filetitle').dom.value,
								docvalue:Ext.get('docvalue').dom.value,
								isadmin:isadmin
						};
		
					});
					threetbar.hide();
					twotbar.render(grid.tbar);
					twotbar.show();
				}
			},{
				text:'案卷',
				cls:'x-btn-text-icon',
				handler:function(){
				twotbar.hide();
					store.on('beforeload', function() {
							this.baseParams = {
								rolltitle:Ext.get('rolltitle').dom.value,
								rollnum:Ext.get('rollnum').dom.value,
								yearnum:Ext.get('yearnum').dom.value,
								isadmin:isadmin
						};
		
					});
					threetbar.render(grid.tbar);
					threetbar.show();
				}
			}]);
				var ctor = '开始时间';
				var crdate = '结束时间';	
				if(isadmin=='y'){
					ctor = '发送人';
					crdate = '发送时间';
				}		
						//链接
						var pgsize = 30;
						if (status == 0) {
							cm = new Ext.grid.ColumnModel( [ {
								header : 'uuid',
								dataIndex : 'uuid',
								width : 100,
								sortable : true,
								hidden : true
							}, {
								header : '标题',
								dataIndex : 'title',
								width : 400,
								sortable : true
							}, {
								header : ctor,
								dataIndex : 'creator',
								width : 400,
								sortable : true
							}, {
								header : crdate,
								dataIndex : 'createdate',
								width : 400,
								sortable : true
							}
							
							]);
							store = new Ext.data.Store( {
								// proxy: new Ext.data.MemoryProxy(data),
								proxy : proxy,
								reader : new Ext.data.JsonReader( {
									totalProperty : 'totalCount',
									root : 'root'
								}, [ {
									name : 'uuid'
								}, {
									name : 'title'
								}, {
									name : 'creator'
								}, {
									name : 'createdate'
								} ])
							});
						}
						
						//面板
						var grid= new Ext.grid.GridPanel({
							title:'可查询档案列表',
							id:'gridbd',
							autoHeight: true,
							//width:900,zzz
			                bodyStyle:'width:100%',   
							loadMask:true,
							renderTo: 'modelshow',
							tbar: ttbar1,
							store:store,
							cm:cm,
							
							bbar: new Ext.PagingToolbar({
								pageSize:pgsize,
								store:store,
								displayInfo:true,
								displayMsg:'显示第{0}条到{1}条记录,一共{2}条',
								emptyMsg:"没有记录"
							})
							
						});
						store.on('beforeload', function() {
							this.baseParams = {
								//title : Ext.get('title').dom.value,
								/*miji:Ext.get('miji').dom.value,
								jjcd:Ext.get('jjcd').dom.value,
								yzh:Ext.get('yzh').dom.value,
								filetitle:Ext.get('filetitle').dom.value,
								rolltitle:Ext.get('rolltitle').dom.value,
								rollnum:Ext.get('yearnum').dom.value,
								yearnum:Ext.get('yearnum').dom.value,*/
								isadmin:isadmin
						};
		
					});
		//store.load({params:{start:0,limit:pgsize,title:Ext.get('title').dom.value}});			

	 
});

function deleteFile(grid){
	var selections=grid.getSelections();
	   if (selections.length==0){
	          alert('请选择需要删除的文件');
	   return;
	   }
 
	var delAllList=new Array();
	  for(var i=0;i<selections.length;i++){
	   if (selections[i].get('uuid').length>0 ){     
	    delAllList[delAllList.length]= selections[i].get('uuid');    
	   }else{
	    //Ext.getCmp(gridId).getSelectionModel().deselectRow(selections[i].get('index'));   
		   alert("error");
	   }
	  };
	   var fileList=delAllList.join();
	    var str="optionnum=2&mettingids="+fileList;
	  //JDS.ajax.Connection.LoadJsonFromUrl('modeldel.action',null,str);
	    Ext.Ajax.request({
			  url:'modeldel.action',
			  params:{mettingids:fileList,optionnum:'2'},
			  method:'post',
			  success:function(){
				  alert("删除成功");
			  }
		  });
		  
}
function opentype(url,width,height){
	var win=new Ext.Window({
		 id:'addtype',
	     width:Ext.getBody().getWidth()-100,
	     height:Ext.getBody().getHeight()-100,
	     title:'查看通知信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:0 5px',
	     items:new Ext.Panel({
		        title: '',        
			  	height:Ext.getBody().getHeight()-150,
			  	width:Ext.getBody().getWidth()-150,
		        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}

function hightstquery(){
	
				
	var win = new Ext.Window({
		id:'tabss',
		title:'tabss',
		width:450,
		height:200,
		html:'<iframe frameborder="0" src="/desktop/widgets/search/searchquery.jsp" width="100%" height="100%"></iframe>'
	});
	win.show();
	
}