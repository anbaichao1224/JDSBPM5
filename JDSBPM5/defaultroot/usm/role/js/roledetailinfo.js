/*
 * 角色信息相关页面
 */

Ext.onReady(function(){

   Ext.QuickTips.init();
    var usergrouptab = new Ext.Panel({
        width:960,
        height:710,
        items: [{
              xtype:'tabpanel',
            activeTab: 0,
            defaults:{height:920,width: 650},  
            items:[
          /*  {
                cls:'x-plain',
                title:'('+cnname+')详细信息',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getroleDetailInfo.do?roleid='+roleid+'&sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
                
            },*/{
                cls:'x-plain',
                title:'人员分配角色信息',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getRoleNameDetailInfo.do?roleid='+roleid+'&sysid='+sysid+'&type='+type+'" height=100% width=100%></iframe>'
                
            }
/*,{
                cls:'x-plain',
                title:'部门分配角色信息',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getRoleOrgNameDetailInfo.do?roleid='+roleid+'&sysid='+sysid+'&type='+type+'"  height=100% width=100%></iframe>'
                
            },{
                cls:'x-plain',
                title:'岗位分配角色信息',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getRolePositionNameDetailInfo.do?roleid='+roleid+'&sysid='+sysid+'&type='+type+'"  height=100% width=100%></iframe>'
                
            },{
                cls:'x-plain',
                title:'用户组分配角色信息',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="getRoleUserGroupNameDetailInfo.do?roleid='+roleid+'"  height=100% width=100%></iframe>'
                
            }*/]
        }]

    });

    usergrouptab.render(document.body);
});