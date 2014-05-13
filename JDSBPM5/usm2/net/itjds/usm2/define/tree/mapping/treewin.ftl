
var config={
			   title : '${title}',
			   lines : true,
			   bodyStyle:'background:url(/usm2/images/tree.jpg) repeat;', 
			   border : false,
			   collapsible: true,
			   split:true,
			   buttonAlign:'center',
               selModel: new Ext.tree.MultiSelectionModel(),
			   width:${width},
		       height: ${height},
		       baseParams:{
		         clazz:'${clazz}'
		       },
		      	 <#if region??>
			   region:'${region}',
			    </#if>
			   checkModel : '${checkModel}',       
			   onlyLeafCheckable : true,   			    
			   animCollapse:false,
			   animate: true,
			   collapseMode:'mini',           				 
			   				    
			   autoScroll : true,
			   rootVisible : ${rootVisible?string},
			   listeners:{
				beforeload:function(node){	
				 viewport.resetContext(node);
				 Ext.apply(this.baseParams,USMContext);
				 var contextparams=Ext.urlEncode(this.baseParams);
				  this.loader.dataUrl = '${treeLoaderBean.dataUrl}&'+contextparams;			       
			    },
			  <#list listeners as item>
			       ${item.eventname.type}:${item.function},
			  </#list>
			    click:function(node, e){
			   
			    if (!this.win){
			      viewport.reload({node:node,istreeevent:true});
			    }
			       
			    }
			   },
			 
			   loader : new Ext.tree.TreeLoader({
							dataUrl : '${treeLoaderBean.dataUrl}',
							baseAttrs : {uiProvider: Ext.ux.TreeCheckNodeUI}
				}),
				
			   root : new Ext.tree.AsyncTreeNode({
							id : '${treeNodeBean.id}',
							text : '${treeNodeBean.text}',
							servicekey : '${treeNodeBean.servicekey}',
							where : '${treeNodeBean.where}',
							treeclass:'${clazz}',
							parameterName : '${treeNodeBean.parameterName}',
							panelName:'${treeNodeBean.panelName}',
							leaf : false
				}),
				reload:	function(args){
				        if (!args.istreeevent){
				         this.loader.load(args.node);
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
var ${id}TreePanel= new  Ext.tree.TreePanel(config);
function EVAL.getConfig(){
  return config;
}
function EVAL.getPanel(){
 
  return ${id}TreePanel;
}
function abort(){
  
}

function getPanel(){
  return ${id}TreePanel;
}

var save = function(){
	var str="";
	var selNodes = ${id}TreePanel.getChecked();
	var servicekey="$${updateData.servicekey}.";
	var params={};
	Ext.each(selNodes, function(node){
         params={'$${updateData.servicekey}.uuid':node.attributes['uuid'],
                  '$${updateData.servicekey}.id':node.id,
                   '$${updateData.servicekey}.currNodeId':USMContext.currNodeId,
                   'clazz':'${clazz}'
               }
		}
	)
	
	JDS.alert(params);
	  Ext.apply(params,USMContext)

    var url="${updateData.url}";
    var callback=function(o){
      alert(o);
      viewport.currTreeNode.reload();
    };
    var str=Ext.urlEncode(params);
	JDS.ajax.Connection.LoadJsonFromUrl(url,callback,str)
}
