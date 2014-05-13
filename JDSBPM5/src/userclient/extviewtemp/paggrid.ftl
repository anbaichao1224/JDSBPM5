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
		
  var bbarConfig = {
        pageSize: 25,
        store: tstore,
        displayInfo: true,
        displayMsg: '当前显示 {0} - {1}条记录 /共 {2}条记录',
       emptyMsg: "无记录",
        
        items:[
            '-', {
            pressed: true,
            enableToggle:true,
            text: '显示摘要',
            cls: 'x-btn-text-icon details',
            toggleHandler: function(btn, pressed){
                var view =Ext.getCmp('${path}@Panel').getView();
                view.showPreview = pressed;
                view.refresh();
            }
        }]
    };		

			
			
var tbar={	
			items:[		
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
			
			
	var tbarConfig={
	

	bbar:new Ext.Toolbar(tbar),
	   layout:'column',
       labelSeparator:'：',
					items:[		
						<#list items as item>
						<#if !item.search.hidden>
				       { 
				         columnWidth:0.25,
				         layout: 'form',            
							items:[{
								xtype:'${item.search.xtype?lower_case}',
								fieldLabel : '${item.search.fieldLabel}',						
								id:'${item.search.id}',								
								name:'${item.search.id}'
			    			}]
						
						},
						</#if>
			    	</#list>
			    	{xtype:'hidden'}			    	
			    
			    	
					]		
		};				
			
	// pluggable renders
    function renderTopic(value, p, record){
  
    
    var content=String.format(
                '<b><a href="#" >{1}</a>',
                value, record.data.kanwumingcheng, record.id, record.data.uuid);
           
                return content;
    }
    function renderLast(value, p, r){
      
        return String.format('{0}<br/> {1}', value, r.data['nikanshijianStr']);
    }		
			
	var config={
		tbar  : new Ext.Panel(tbarConfig),
		id : '${path}@Panel',
		frame : true,
		path:'${path}',
		width : 'auto',
		height:'auto',
		    // customize view config
        viewConfig: {
            forceFit:true,
            enableRowBody:true,
            showPreview:true,
            getRowClass : function(record, rowIndex, p, store){
                if(this.showPreview){
                    p.body = '<p>'+record.data.zaiyao+'</p>';
                    return 'x-grid3-row-expanded';
                }
                return 'x-grid3-row-collapsed';
            }
        },
		
		
		loadMask: {msg:'正在加载数据，请稍侯……'},
		store : tstore,
		sm :  new Ext.grid.CheckboxSelectionModel(),
		enableDragDrop: true,
	    ddGroup : '${id}GridDD',
	
        columns:[{
            id: 'topic', // id assigned so we can apply custom css (e.g. .x-grid-col-topic b { color:#333 })
            header: "摘要",
            dataIndex: 'kanwumingcheng',
            width: 420,
            renderer: renderTopic,
            sortable: true
         },{
            id: 'last',
            header: "发布时间",
            dataIndex: 'drrqStr',
            width: 150,
            renderer: renderLast,
            sortable: true
        }],
		
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
	  var  serviceKey='${esbkey}'.replace('Viewport','').toLowerCase();
	 window.open(context+"fdt/display/formshow.jsp?action="+serviceKey+".html.jsp&uuid="+gridId);
	
    //  openWin(url,params,viewId,winConfig);
	};
	
	
	

	return  this.gridPanel;
	