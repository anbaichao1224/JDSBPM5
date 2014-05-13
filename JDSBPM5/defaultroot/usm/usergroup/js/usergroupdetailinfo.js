/*
 * 
 * 用户组管理
 * 
 */

Ext.onReady(function(){

   Ext.QuickTips.init();
    var usergrouptab = new Ext.Panel({
        width:785,
        height:539, 
        items: [{
            xtype:'tabpanel',
            activeTab: 0,
            defaults:{height:539,width: 300},
            items:[
            {
                cls:'x-plain',
                title:'('+grpname+')详细信息',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getuserGroupDetailInfo.do?grpid='+grpid+'&sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
                
            },{
                cls:'x-plain',
                title:'给部门分配用户组',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getUserGroupNameDetailInfo.do?grpid='+grpid+'&sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
                
            },{
                cls:'x-plain',
                title:'给人员分配用户组',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getpersonUserGroupNameDetailInfo.do?grpid='+grpid+'&sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
                
            }]
        }]

    });

    usergrouptab.render(document.body);
});