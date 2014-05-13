Ext.QuickTips.init();
Ext.form.Field.prototype.msgTarget = 'side';

var sm = new Ext.grid.CheckboxSelectionModel({handleMouseDown:Ext.emptyFn});
var rowNum = new Ext.grid.RowNumberer({
				header : '序号',
				width : 32,
				align : 'center'
			});

/**
 * 创建布局
 */
var viewport;

var USMContext={
 	esbkey:'${esbkey}'

}
var childPanelMap=new Ext.util.MixedCollection(); 
var viewPanelMap=new Ext.util.MixedCollection(); 




var getChildPanel=function(esbkey,path,parentPath,uuid,callbackevent){
    var childpanel;
    var url=context+"expression.jsp";
    var params="expression=$usmMain&esbkey="+esbkey+'&path='+path;
    var uuid= uuid ? uuid: '${topNodeId}';
    var parentPanel= childPanelMap.get(parentPath);
    if (parentPanel && parentPanel.uuid){
       uuid=parentPanel.uuid;
    }
    var callback=function(o){
      try{
        var ChildPanel=new Function(o);
        var childpane= ChildPanel({uuid:uuid,renderTo:path,parentPath:parentPath});
        if (parentPath==USMContext.esbkey){
             viewPanelMap.add(path,childpane);
             childpane.setHeight(viewport.getSize().height);
         }else{
          var parentPanel=childPanelMap.get(parentPath);
          if (childpane.getXType()=='grid'){
            childpane.setSize({width:parentPanel.getSize().width,height:parentPanel.getSize().height+130});
          }else{
            childpane.setSize({width:parentPanel.getSize().width,height:parentPanel.getSize().height-20});
          }
        }
        childPanelMap.add(path,childpane);
        }catch(e){
         
          JDS.alert(e);
        }
       if (callbackevent){
		  callbackevent.call(parentPanel,childpane);
		}
    }
    JDS.ajax.Connection.LoadJsonFromUrl(url,callback,params);
    
}


var createViewport = function(){
	  viewport  = new Ext.Viewport({
		title:'${title}',
		uuid:'${topNodeId}',
		reload:function(args){
		  viewport.uuid=args.node.id;
		  
		  viewPanelMap.eachKey(function(key){
		    try{
		       viewPanelMap.get(key).uuid=args.node.id;
		       if (!args.eventPath ||  args.eventPath!=key){
		            viewPanelMap.get(key).reload(args);
		       }
		       
		       
		       
		      }catch(e){
		      
            }
		  })
		},
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
	    },
		
		enableTabScroll:true,
		layout:'${layout}',
		items:[
			<#list items as item>
			 {
			  html:"<div id='${item.path}' ></div>",
			  region:'${item.region}',
			  <#if item.region=='west'>
			  	  width:${item.width},
			  </#if>
			  listeners: {render : function (panel){
							var childPanel=getChildPanel('${esbkey}','${item.path}','${esbkey}',viewport?viewport.uuid:'${topNodeId}');
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
	});
	
}
