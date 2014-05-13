SamplePanel = Ext.extend(Ext.DataView, {
    autoHeight: true,
    frame:true,
    cls:'demos',
    itemSelector: 'dd',
    overClass: 'over',
    
    tpl : new Ext.XTemplate(
        '<div id="sample-ct">',
            '<tpl for=".">',
            '<div><a name="{id}"></a><h2><div>{title}</div></h2>',
            '<dl>',
                '<tpl for="samples">',
                    '<dd ext:url="{url}"><img src="{icon}"/>',
                        '<div><h4>{text}</h4><p>{desc}</p></div>',
                    '</dd>',
                '</tpl>',
            '<div style="clear:left"></div></dl></div>',
            '</tpl>',
        '</div>'
    ),

    onClick : function(e){
        var group = e.getTarget('h2', 3, true);
        if(group){
            group.up('div').toggleClass('collapsed');
        }else {
            var t = e.getTarget('dd', 5, true);
            if(t && !e.getTarget('a', 2)){
                var url = t.getAttributeNS('ext', 'url');
               window.parent.document.getElementById("rolling7").src="js/ext/examples/computerInfo.jsp?nodeid="+url;
            }
        }
        return SamplePanel.superclass.onClick.apply(this, arguments);
    }
});


Ext.EventManager.on(window, 'load', function(){

    for(var i = 0, c; c = catalog[i]; i++){
        c.id = 'sample-' + i;
    }

    var store = new Ext.data.JsonStore({
        idProperty: 'id',
        fields: ['id', 'title', 'samples'],
        data: catalog
    });

    new Ext.Panel({
    	width:750,
        autoHeight: true,
        collapsible: true,
        frame: true,
        title: '服务器列表',
        items: new SamplePanel({
            store: store
        })
    }).render('all-demos');

    var tpl = new Ext.XTemplate(
        '<tpl for="."><li><a href="#{id}">{title:stripTags}</a></li></tpl>'
    );


    Ext.select('#sample-spacer').remove();

    setTimeout(function(){
        Ext.get('loading').remove();
        Ext.get('loading-mask').fadeOut({remove:true});
    }, 250);

    if(window.console && window.console.firebug){
        Ext.Msg.alert('Warning', 'Firebug is known to cause performance issues with Ext JS.');
    }
});



