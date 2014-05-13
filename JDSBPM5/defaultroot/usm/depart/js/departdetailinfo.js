
Ext.onReady(function(){
	Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
    Ext.QuickTips.init();
    var departtab = new Ext.Panel({
        width:960,
        height:710,        
        items: [{
            xtype:'tabpanel',
            activeTab: 0,
            defaults:{height:960,width: 710},  
            items:[
           {
                cls:'x-plain',
                title:'('+cnname+')详细信息',
                layout:'fit', 
                defaultType: 'textfield',          
                html: '<iframe scrolling="yes" frameborder="0" src="getDepartDetailInfo.do?orgid='+orgid+'" height=100% width=100%></iframe>'
                
            },{
                cls:'x-plain',
                title:'子部门列表',
                layout:'fit',           
                html: '<iframe scrolling="yes" frameborder="0" src="/usm/depart/childrendepartListgrid.jsp?orglevel='+orglevel+'&parentorgid='+orgid+'" height=100% width=100%></iframe>'
                
            },{
                title:'人员列表',
                layout:'form',
                defaults: {width: 230},
                defaultType: 'textfield',
                html: '<iframe scrolling="yes" frameborder="0" src="/usm/person/childrenPersonListgrid.jsp?orgid='+orgid+'&parentorgid='+parentorgid+'" height=100% width=100%></iframe>'

            }]
        }]

    });
    departtab.render(document.body);
});