/*
 * 职务信息
 * 
 */

Ext.onReady(function(){

    Ext.QuickTips.init();
    var usergrouptab = new Ext.FormPanel({
       width:785,
        height:540, 
        items: [{
           xtype:'tabpanel',
            activeTab: 0,
            defaults:{height:540 ,width: 300}, 
            items:[
            {
                cls:'x-plain',
                title:'('+cnname+')职务详细信息',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getdutyDetailInfo.do?dutyid='+dutyid+'&sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
                
            },{
                cls:'x-plain',
                title:'给人员分配职务',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getDutyNameDetailInfo.do?dutyid='+dutyid+'&sysid='+sysid+'&type='+type+'"  height=100% width=100%></iframe>'
                
            }]
        }]

    });

    usergrouptab.render(document.body);
});