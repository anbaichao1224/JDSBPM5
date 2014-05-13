var config={
       <#if title??>
          title:'${title}',
       </#if>
         <#if region??>
         region:'${region}',
          </#if>
        deferredRender:false,
        uuid:'',
     
        activeTab:${activeTab},
        iconCls: 'tabs',
        listeners:{
              tabchange:function(tabPael,panel){
                if(this.getActiveTab()){
                 if (childPanelMap.containsKey(panel.path)){
                    childPanelMap.get(panel.path).setSize({width:tabPael.getInnerWidth(),height:tabPael.getInnerHeight()});
                  }
                }
                 
              }
        },
        reload:function(args){
          
          
          try{
            this.uuid=args.node.id;
      
             this.getActiveTab().reload(args);
          }catch(e){
             JDS.alert(e);
          }
         },
        enableTabScroll:true,
       items:[
			<#list items as item>
			 {
			  title:'${item.title}',
			  html:"<div id='${item.path}' ></div>",
			  path:'${item.path}',
			  reload:function(args,tab){ 
			 
				     childPanelMap.get('${item.path}').reload(args);
			  },
			  listeners: {activate:function (panel){
			                   if (childPanelMap.containsKey(panel.path)){
				                    var tabPanel=childPanelMap.get(config.renderTo);
					                childPanelMap.get(panel.path).reload({node:{id:tabPanel.uuid}}); 
			                    }else{
			               
			                    	 getChildPanel(USMContext.esbkey,panel.path, config.renderTo,config.uuid);
			                    }
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
		],
        plugins: new Ext.ux.TabCloseMenu()
};
Ext.apply(config,arguments[0]);		

this.tabPanel= new Ext.TabPanel(config);

return this.tabPanel;