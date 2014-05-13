
var config={
         <#if region??>
         region:'${region}',
          </#if>
      
       	title:'${title}',
        border: false,
      	bodyStyle:'${bodyStyle}', 
        html:'${html}'

};
Ext.apply(config,arguments[0]);	
this.simplepanel= new Ext.Panel(config);

return this.simplepanel;
