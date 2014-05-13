/*
 * Ext JS Library 2.0.1
 * Copyright(c) 2006-2008, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */

Ext.onReady(function(){

   Ext.QuickTips.init();
  


    var usergrouptab = new Ext.FormPanel({
         labelWidth: 75,
        border:true,
        fileUpload: true,
        frame: true,
        width: 760,
        height:500,
        items: [{
           xtype:'tabpanel',
            activeTab: 0,
            defaults:{height:400,width: 300, bodyStyle:'padding:10px'},
            items:[
            {
                cls:'x-plain',
                title:'('+cnname+')详细信息',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getdutyLevelDetailInfo.do?levelid='+levelid+'&sysid='+sysid+'&type='+type+'"  height=100% width=100%></iframe>'
                
            },{
                cls:'x-plain',
                title:'给人员分配职级',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getDutyLevelNameDetailInfo.do?levelid='+levelid+'&sysid='+sysid+'&type='+type+'"   height=100% width=100%></iframe>'
                
            }]
        }]

    });

    usergrouptab.render(document.body);
});