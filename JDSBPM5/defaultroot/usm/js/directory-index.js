
Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){

    Ext.QuickTips.init();

    var viewport = new Ext.Viewport({
        layout:'border',
        items:[
            new Ext.BoxComponent({ // raw
                region:'north',
                el: 'header',
                height:32
            }),
           
             new Ext.Panel({
                            region:'center',
                            id:'topic-grid',
                            
                     //       title:'sdfsdfsdf',
                         
                       //     trackMouseOver:false,
                      //      loadMask: {msg:'Loading Topics...'},
                            html:'<iframe name="directory-views" id="views" scrolling="yes" frameborder="0" src="/usm/info.jsp" height=100% width=100%></iframe>'
                           
                        })
         ]
    });

   
});