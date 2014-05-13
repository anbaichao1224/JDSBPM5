   var tstore=new Ext.data.JsonStore({
					url:context+'${loadData.url}',
					totalProperty:'results',
					pageSize:'20',
					
					baseParams:{
					    path:'${path}'
					   },
					root:'data',
					fields:[
					     { name : 'path'},	
					     {name:'uuid'},
					     {name:'activityInstId'},
						<#list items as item>
						 	{
								name : '${item.dataIndex}'									
							}
							<#if item_has_next>,</#if>
						</#list>
						
					],
					listener:{
					  beforeload:function(obj){
						  Ext.apply(this.baseParams,USMContext)
					  }
					}
					
				});
				

   
				
				
   var   listeners={
		        render:function(obj){  
		           
		                    try{
							var drogAndDrap = new Ext.dd.DropTarget(obj.getEl(), {
						        ddGroup : '${id}GridDD',
						        copy    : false,
						        notifyDrop : function(dd, e, data) {
						        
						            var rows = data.selections;
						            var index = dd.getDragData(e).rowIndex;
						            if (typeof(index) == "undefined") {
						                return;
						            }
						            for(i = 0; i < rows.length; i++) {
						                var rowData = rows[i]; 	  
						                
						                if(!this.copy) {
											obj.store.remove(rowData);
										}
					                obj.store.insert(index, rowData);
						            }
						            
						            
						        }
						    });
						    }catch(e){
						    JDS.alert(e);
						    }
						    
					 },
					rowdblclick: function (currgrid,index,event){
				
							      var uuid=currgrid.getStore().getAt(index).get('uuid'); 
							    
							  
							     
							      if (!uuid){
							        uuid=currgrid.getStore().getAt(index).get('path'); 
							      }
							       
							        edit(uuid); 
							     }
		};
		
   var bbarConfig={
					pageSize :20,
					plugins : new Ext.ux.grid.PageSizePlugin(),
					displayInfo: true,
					store:tstore,
	           		displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
	            	emptyMsg: "无记录"
			};	
	var tbarConfig={
					items:[		
						<#list items as item>
						<#if !item.search.hidden>
						'${item.search.fieldLabel}:',
						<#if item.search.xtype == 'TextField'>
							{
								xtype:'${item.search.xtype?lower_case}',
								fieldLabel : '${item.search.fieldLabel}',
								width:150,								
								id:'${item.search.id}',								
								name:'${item.search.id}'
			    			}
						</#if>
						<#if item.search.xtype == 'NumberField'>
							{
									xtype : '${item.search.xtype?lower_case}',
									fieldLabel : '${item.search.fieldLabel}',
									width:300,
									id:'${item.search.id}',
									name:'${item.search.id}',
									allowBlank :${item.search.allowBlank?string},
									 maxLength:${item.search.maxLength},
									validateOnBlur : true
		        			}
						</#if>
						<#if item.search.xtype == 'DateField'>
							new Ext.form.DateField({
									xtype : '${item.search.xtype?lower_case}',
									vtype : '${item.search.vtype}',
									fieldLabel : '${item.search.fieldLabel}',
									width:300,
									id:'${item.search.id}',
									name:'${item.search.id}',
									allowBlank :${item.search.allowBlank?string},
									maxLength:${item.search.maxLength},
									validateOnBlur : true
		        			})
						</#if>
						,
						</#if>
			    	</#list>
			    	{xtype:'hidden'}			    	
			    	,
			    	<#list buttons as item>
			    			{
			    			<#if item.otherText != '' >
			    				text : '${item.otherText}',
			    			</#if>	
			    			<#if item.otherText == ''>
			    			   text : '${item.text.type}',
			    			</#if>
			    				iconCls : '${item.iconCls}',
			    				hidden : ${item.hidden?string},
			    			<#if item.handler != ''>
			    				handler : function(){${item.handler}}
			    			</#if>
			    			<#if item.handler == '' >	
			    			 handler :  function(){
								var params={
							      <#if item.ajax.params?? && item.ajax.params.size()>0>
												      <#list item.ajax.params as item>
													    ${item.name}:${item.value},
													</#list>
								  </#if>
								   gridId:'new',
								   expression:'${item.ajax.expression}'
								  }
								  var url=context+'${item.ajax.url}';
								  var viewId='${item.ajax.viewId}';
								  var winConfig={
								      width:${item.ajax.winConfig.width},
								      height:${item.ajax.winConfig.height},
								      title:'${item.ajax.winConfig.title}',
								      <#if item.ajax.winConfig.maximizable==true >	
								      maximizable:true
								      </#if>
								       <#if item.ajax.winConfig.maximizable==false >	
								      maximizable:false
								      </#if>
								  }
							      openWin(url,params,viewId,winConfig);
						      }
			    			</#if>
			    		
			    				
			    			}
			    	
			    		<#if item_has_next>,</#if>
			    	</#list>
					]		
		};				
			
	var config={
		tbar  : new Ext.Toolbar(tbarConfig),
		id : '${path}@Panel',
		frame : true,
		path:'${path}',
		width : 'auto',
		height:'auto',
		loadMask: {msg:'正在加载数据，请稍侯……'},
		store : tstore,
		sm :  new Ext.grid.CheckboxSelectionModel(),
		enableDragDrop: true,
	    ddGroup : '${id}GridDD',
		columns : [	
			  sm,
			  new Ext.grid.RowNumberer(),
			   <#list items as item>
				 	{
						header : '${item.header}',
						width: ${item.width},
						dataIndex : '${item.dataIndex}',
						sortable : true,
						hidden : ${item.hidden?string}
	        		}
	        		<#if item_has_next>,</#if>
	        	</#list>
			],
			reload:function(args){
				this.store.node=args.node;
				this.store.baseParams.uuid=args.node.id;
				this.uuid=args.node.id;
				
				this.store.reload({
					params:{
						start:0,
						limit:${pageSize}
						}
					});
			},
		bbar : new Ext.PagingToolbar(bbarConfig),
		listeners:listeners
	}	
	
		
					
	Ext.apply(config,arguments[0]);		
	this.gridPanel=new Ext.grid.GridPanel(config);
	this.gridPanel.reload({node:{id:config.uuid}});

   	var serialindex = function (){ 
        var rows = this.gridPanel.getSelectionModel().selectAll();
		var srow=this.gridPanel.getSelectionModel().getSelections();	
		var txtCheckValue="";
        for(var i=0,len=srow.length;i<len;i++){
            var ss = srow[i].get("uuid");
            txtCheckValue = txtCheckValue + ss + ",";
        }
        var str = "&txtCheckValue=" + encodeURIComponent(txtCheckValue);
        var url="${saveSort.url}";
    	var callback=function(o){			    				    		
	    	alert(o);	    		    				      			      
	    }   	
	    viewport.reload({node:{id:this.gridPanel.uuid}});
	    this.gridPanel.store.setDefaultSort('serialindex', 'ASC');
	  
		JDS.ajax.Connection.LoadJsonFromUrl(url,callback,str);	
	
	} 
	
	var remove = function(){
		if(this.gridPanel.getSelectionModel().getCount() == 0){
			alert('请选择要删除的记录！');
			return;
		}else{
			if(!window.confirm('确认删除记录吗？')){
				return;
			}
		}
		var rs=this.gridPanel.getSelectionModel().getSelections();		
		try{
			var progressBar =  Ext.MessageBox.show({
                title: '提示',
                msg: '正在删除数据......',
                width: 240,
                progress: true,
                closable: false,
                buttons:Ext.Msg.OK,
                fn : function(){
                
                }
            });

            var v = 0;	
            var gridIds	='';
			Ext.each(rs,function(){
				v++;
				var nodeId = this.get("uuid");
				gridIds=gridIds+encodeURIComponent(nodeId)+';';
			});		
			
			var str = "path=${path}&gridIds=" + gridIds;
				var url=context+"${deleteRowData.url}";						
			    var callback=function(o){		
			    progressBar.updateProgress(100);	    				    		
			    	progressBar.updateText(o);	
			    	viewport.reload({node:{id:this.gridPanel.uuid}});		    		    				      			      
			    };			    	

			JDS.ajax.Connection.LoadJsonFromUrl(url,callback,str);	
			
		}catch(e){}
	}
	
	
	
	var query = function(){
	  var where ='';
		<#list items as item>
						<#if !item.search.hidden>
						    if (Ext.get('${item.search.id}').getValue()!=''){
						    
						       where =where +" ${item.search.id} like '%"+Ext.get('${item.search.id}').getValue()+"%'  "
						       <#if item_has_next >where =where + " and "  </#if>
						       
						    }
						
						   		
						</#if>
				
			    	</#list>
			    	where=where +"1=1"
			    		  tstore.baseParams.where=where;
			    		 
			    	this.gridPanel.reload({node:{id:this.gridPanel.uuid}});
	
	}
	
	var rebuild = function(){
        var str = "";
        var url=context+"${rebuild.url}";
    	var callback=function(o){			    				    		
	    	alert(o);	    		    				      			      
	    }   	
	   // viewport.reload({node:{id:this.gridPanel.uuid}});	  
		JDS.ajax.Connection.LoadJsonFromUrl(url,callback,str);
	}
	
	
	var getNewWinByConfig=function(viewId,winConfig){
	  var openPanel=new Ext.Panel({
				html:"<div id='"+viewId+"'></div>"
	    });
	    var  config={
			    width:800,
			    height:600,
				maximizable:true,
				items:[openPanel]
		};
		Ext.apply(config,winConfig);	
				
	 try{		 
	   var win = new Ext.Window(config);
	   openPanel.win=win;
	   openPanel.title='';
	   win.on("resize",function(w){
	       if (childPanelMap.containsKey(viewId)){
				childPanelMap.get(viewId).setSize(w.getInnerWidth(),w.getInnerHeight());
			}
	    
	    });
	    win.on("show",function(w){
	      var wh=w.getPosition();
	      if(wh[1]<0){
		  	var width=win.getSize().width;
	        win.maximize();
	        var tw=width;
	        var th=win.getSize().height-80;
	        win.restore();
	        win.setSize(width,th);
	        win.center();
	      }});
	     win.on("close",function(w){
	         childPanelMap.removeKey(viewId);
	  
	      });
	       
	    win.show();
		}catch(e){
			JDS.alert(e);	
       }
	
		return win;
	}
	
	
	var openWin=function(url,params,viewId,winConfig){	  
	  if (childPanelMap.containsKey(viewId)){
	    childPanelMap.get(viewId).win.close();
	   
	  } 
	   var baseParams={
	    path:this.gridPanel.path,
	    viewId:viewId,
	    uuid:this.gridPanel.uuid
	   }
	 
	Ext.apply(params,baseParams);
	var str= Ext.urlEncode(params);
	
	var win=getNewWinByConfig(viewId,winConfig);
    var callback=function(o){
    
          var ChildPanel=new Function(o);
          var panelConfig={
            renderTo:viewId,
            parentPath:baseParams.path
          }
          Ext.apply(panelConfig,params);
          var childpane= ChildPanel(panelConfig);
		  childpane.gridId=params.gridId;
		  childpane.win=win;
		  childPanelMap.add(viewId,childpane);
		  childpane.setSize(win.getInnerWidth(),win.getInnerHeight());
		}
	   JDS.ajax.Connection.LoadJsonFromUrl(url,callback,str,this)
	}
	
   var edit = function(gridId){ 
 
 
	 
 
     var params={
      <#if editRowData.params?? &&editRowData.params.size()>0>
					      <#list editRowData.params as item>
						    ${item.name}:${item.value},
						</#list>
	  </#if>
	   gridId:gridId,
	   expression:'${editRowData.expression}'
	  }
	  var url=context+'${editRowData.url}';
	  var viewId='${editRowData.viewId}';
	  var winConfig={
	      width:${editRowData.winConfig.width},
	      height:${editRowData.winConfig.height},
	      title:'${editRowData.winConfig.title}',
	       <#if editRowData.winConfig.maximizable==true >	
	      maximizable:true
	      </#if>
	       <#if editRowData.winConfig.maximizable==false >	
	      maximizable:false
	      </#if>
	  };
	    var wincfg={
		activityInstId:gridId,
		displayAction:"formdisplay.action",
		activityInstHistoryId:'',
	    formdisplayAction:"BPMClientFormDisplayAction.action"
	}
	// openWin(url,params,viewId,winConfig);
	
	
	  var  serviceKey='${esbkey}'.replace('Viewport','');
	// window.open(context+"fdt/display/formPrint.jsp?action=fawen.html.jsp&uuid="+gridId+'&path=$'+serviceKey)
	// http://127.0.0.1:8181/bsiirr/fdt/display/formPrint.jsp?action=fawen.html.jsp&uuid=16be7ee-11ff9e3a1ac-c9fee00ed02066a02f428d8745b34627
      openWin(url,params,viewId,winConfig);
	};
	
	
	

	return  this.gridPanel;
	