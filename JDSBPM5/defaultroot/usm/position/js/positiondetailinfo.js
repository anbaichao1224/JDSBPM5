/*
 * 岗位信息
 * 
 */

Ext.onReady(function(){

   Ext.QuickTips.init();
    var usergrouptab = new Ext.Panel({
            width:785,
        height:540,  
        items: [{
             xtype:'tabpanel',
            activeTab: 0,
            defaults:{height:540,width: 300}, 
            items:[
            {
                cls:'x-plain',
                title: '('+positionname+')详细信息',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getPositionDetailInfo.do?positionid='+positionid+'&sysid='+sysid+'&type='+type+'"  height=100% width=100%></iframe>'
                
            },{
                cls:'x-plain',
                title:'人员分配岗位列表',
                layout:'fit',           
                html: '<iframe id="positionviews" scrolling="yes" frameborder="0" src="getPositionNameDetailInfo.do?positionid='+positionid+'&sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
                
            }]
        }]

    });

    usergrouptab.render(document.body);
});