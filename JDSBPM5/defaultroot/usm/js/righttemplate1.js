Ext.BLANK_IMAGE_URL='/usm/resources/images/default/s.gif';
Ext.onReady(function(){
    Ext.QuickTips.init();
    
    var fp = new Ext.FormPanel({
        frame: true,
        split:true,
        collapsible: true,
        labelWidth: 110,
        width:820,
        height:650,
        renderTo:'form-ct',
        bodyStyle: 'padding:0 10px 0;',
        items: str
    });

});


