<%@page  contentType="text/xml; charset=UTF-8"%>

<%		
			
		out.println("{text:'组织机构',iconCls: 'bmenu',menu: new Ext.menu.Menu({id: 'mainMenu',items: [{id:'person',text: '人员管理',handler:onItemClick},{id:'branch',text: '部门管理',handler:onItemClick},");
        out.println("    {text: '岗位',handler:onItemClick},{text: '职级',handler:onItemClick},{text: '职务',handler:onItemClick},");
        out.println("    {text: '角色',handler:onItemClick},{text: '用户组',handler:onItemClick}]})}");
		out.println(",'-',");
		out.println("{text:'资源',iconCls: 'bmenu',menu: new Ext.menu.Menu({id: 'mainMenu3',items: [{id:'system',text: '系统注册', handler:onItemClick},{id:'apply',text: '应用目录注册管理',handler:onItemClick},");
        out.println("    {id:'resources',text: '资源管理',handler:onItemClick},{text: '系统日志',handler:onItemClick}]})}");
	
		
	

%>
