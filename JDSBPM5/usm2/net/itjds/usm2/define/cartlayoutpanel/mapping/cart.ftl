var config={
         <#if region??>
         region:'${region}',
          </#if>
        layout: 'card',
    
        uuid:'',
        listeners:{
	          resize:function(obj,w,h){
			    try{     
			       this.items.each(function(item){
			         item.setSize({width:obj.getInnerWidth(),height:obj.getInnerHeight()});
 	               }
	               
	               );
	                  }catch(e){}
	           }
	    },
        activeItem: ${activeItem},
        border: false,
        reload:function(args){
	        if (args.node){
	           var cartPanel;
	           
	           var path=this.layout.activeItem.path;;  
	           if (args.node.attributes && args.node.attributes['panelName'] ){
	             path= args.node.attributes['panelName'];
	             if (path.indexOf('${path}.')==-1){
	                path='${path}.'+path;
	             }
	           }  
	       
	             if(childPanelMap.containsKey(path)){
	               cartPanel=childPanelMap.get(path);
	          
	             }else{
	              	cartPanel=getChildPanel(args.node.esbkey,path,'${path}',args.node.id);
	             }
	            config.uuid=args.node.id;
	            this.layout.setActiveItem('cart'+path);
	           
	             cartPanel.reload(args);
	          
	        } 
       
        },

        items:[ 
        	<#list items as item>
			 {
			  id:'cart${item.path}',
			  path:'${item.path}',
			  html:"<div id='${item.path}'  ></div>"
			  
			  , listeners: {
			  <#if item_index==0>
			    render: function (panel){
								getChildPanel('${item.esbkey}','${item.path}', config.renderTo,config.uuid);
							},
			 </#if>
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
};
Ext.apply(config,arguments[0]);
this.cartPanel= new Ext.Panel(config)

return this.cartPanel;

