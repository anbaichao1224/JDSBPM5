var config={
			   title : '${title}',
			   lines : true,
			   bodyStyle:'background:url(/usm2/images/tree.jpg) repeat;', 
			   border : false,
			   collapsible: true,
			   split:true,
			   buttonAlign:'center',
			   path:'${path}',
               selModel: new Ext.tree.MultiSelectionModel(),
		       baseParams:{
		         uuid:'',
		         path:'${path}'
		       },
			   checkModel : '${checkModel}',       
			   animCollapse:false,
			   animate: true,
			   collapseMode:'mini',           				 			    
			   autoScroll : true,
			   rootVisible : ${rootVisible?string},
			   listeners:{
				 contextmenu:function(node,e)
		        {
		           
		            e.preventDefault(); 
		            var treeMenu = new Ext.menu.Menu
		            ([
		                {xtype:"button",text:"全部展开",icon:"/js/ext/resources/images/default/tree/folder-open.gif",pressed:true,handler:function(){node.expand(true,false)}},
		                {xtype:"button",text:"收缩",icon:"/js/ext/resources/images/default/tree/folder.gif",pressed:true,handler:function(){node.collapse(true,true)}}
		            
		            ]);
		            //定位菜单的显示位置
		            treeMenu.showAt(e.getPoint());
		        },
			   
				beforeload:function(node){
				 if (this.win){

				 }else{
				    Ext.apply(this.baseParams,USMContext);
				 }
				 
				  this.baseParams.uuid=node.id;
				
				  var contextparams=Ext.urlEncode(this.baseParams);
			
				  this.loader.dataUrl = '${treeLoaderBean.dataUrl}&'+contextparams;			       
			    },
			  <#list listeners as item>
			       ${item.eventname.type}:${item.function},
			  </#list>
			    click:function(node, e){
			       if (!this.win){
			        
			         viewport.reload({node:node,eventPath:this.path});
			         }
			    }
			   },
			 
			   loader : new Ext.tree.TreeLoader({
							dataUrl : '${treeLoaderBean.dataUrl}'
							<#if onlyLeafCheckable>
							,baseAttrs : {uiProvider: Ext.ux.TreeCheckNodeUI}
							</#if>
				}),
				
			   root : new Ext.tree.AsyncTreeNode({
							id : '${treeNodeBean.id}',
							text : '${treeNodeBean.text}',
							panelName:'${treeNodeBean.panelName}',
							leaf : false
				}),
				reload:	function(args){		
				        if ( !args.eventPath || args.eventPath!=this.path){
				        var currNode=this.getNodeById(args.node.id);
				        currNode.uuid=args.node.id;
				         if ( !currNode.isLeaf()){
				               if(!currNode.isExpanded() ){            
				               		currNode.expand();
				               }else{
				                this.baseParams.uuid=args.node.id;
							    var contextparams=Ext.urlEncode(this.baseParams);
							    this.loader.dataUrl = '${treeLoaderBean.dataUrl}&'+contextparams;		
				                this.loader.load(currNode);
				               }				               			       
					        }			 			
				        
				        }
			   },
				
				buttons :[		
				       <#if buttons?? >				
						<#list buttons as item>
				    		new Ext.Button(
				    			{
				    				text : '${item.text.type}',
				    				iconCls : '${item.iconCls}',
				    				hidden : ${item.hidden?string},
				    				handler : function(){${item.handler}}
				    			}
				    		)
				    		<#if item_has_next>,</#if>
				    	</#list>
				    	</#if>
				]	
				
			};
			
Ext.apply(config,arguments[0]);				
this.treePanel= new  Ext.tree.TreePanel(config);


var save = function(){
 
	var str="";
	var selNodes =  this.treePanel.getChecked();
	var params={};
	var progressBar =  Ext.MessageBox.show({
                title: '提示',
                msg: '请选择要保存的数据！',
                width: 240,
                progress: true,
                closable: false,
                buttons:Ext.Msg.OK,
                fn : function(){
                  	 viewport.reload({node:{id:this.treePanel.uuid}});
                	 this.treePanel.win.close();
                },
                scope:this
                
            });
    var v=0;
      params={'uuid':this.treePanel.uuid,selectIds:''};
     
	Ext.each(selNodes, function(node){
			progressBar.updateText('正在保存'+node.text);	
			params.selectIds=params.selectIds+node.id+';';	
		}
		
	)
   
	var url="${updateData.url}";
		    var callback=function(o){
		    	progressBar.updateProgress(100);	 
		      progressBar.updateText(o);	
		      	
		    };
	var str=Ext.urlEncode(params);
	JDS.ajax.Connection.LoadJsonFromUrl(url,callback,str);
}

function abort(){
   this.treePanel.win.close();
}


return  this.treePanel;