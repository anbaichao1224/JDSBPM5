	USMContext.esbkey='${esbkey}';
var config=	{
            layout:'border',
            
         listeners:{
	          resize:function(obj,w,h){  
		      try{     
			     var ww=0;
			     var hh=0;
	               this.items.each(function(item){
  		                ww=ww+item.getInnerWidth();
  		                hh=hh+item.getInnerHeight();
		               }
	               );
                var wk= w / ww;
		        var hk= h / hh;
			       this.items.each(function(item){
			       item.setSize({width:wk * item.getInnerWidth(),height:hk * item.getInnerHeight()});
     			   
	               });
                  }catch(e){
                   // JDS.alert(e)
                  }
	           }
	           } ,
	             
		reload:function(args){
		
		 viewport.uuid=args.node.id;
		  viewPanelMap.eachKey(function(key){
		    try{
		       viewPanelMap.get(key).uuid=args.node.id;
		       if (!args.eventPath ||  args.eventPath!=key){
		            viewPanelMap.get(key).reload(args);
		       }
		       
		       
		       
		      }catch(e){
		      
            }})
		
		
		},
 		     items:[	         
					<#list items as item>
					 {
					  html:"<div id='${item.path}' ></div>",
					  region:'${item.region}',
					  <#if item_index==0>
					  	  width:${item.width},
					  </#if>
					  listeners: {render : function (panel){
									var childPanel=getChildPanel('${esbkey}','${item.path}','${esbkey}','${topNodeId}');
								},
								resize:function(obj,w,h){
									   if (childPanelMap.containsKey('${item.path}')){
									       childPanelMap.get('${item.path}').setSize({width:obj.getInnerWidth(),height:obj.getInnerHeight()});
									    }
								}
						 }
					 }
		        		<#if item_has_next>,</#if>
		        	</#list>
			]
				
		}
		
viewport= new  Ext.Panel(config);
  return viewport;
