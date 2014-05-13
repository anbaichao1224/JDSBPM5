Ext.onReady(function(){
 /*
 var picUrl='/jdsbpm/imgs/loading.gif';
 Ext.get('custom').dom.src=picUrl;
        var custom = new Ext.Resizable('custom', {
            wrap:true,
            pinned:true,
            minWidth:80,
            minHeight: 80,
            preserveRatio: true,
            transparent:true,
            handles: 'all',
            draggable:true,
            dynamic:true
        });
        var customEl = custom.getEl();
        // move to the body to prevent overlap on my blog
        document.body.insertBefore(customEl.dom, document.body.firstChild);
        
        customEl.on('dblclick', function(){
            customEl.hide(true);
        });
        customEl.hide();
        
        Ext.get('showMe').on('click', function(){
            customEl.center();
            customEl.show(true);
        });
        */
	
	  Ext.get('img').on('click', function(){
               /*    Ext.MessageBox.show({
		           msg: "<img src='/jdsbpm/js/desktop/resources/images/loading2.gif'/>",	        
		           width:80,
		           height:10,
		           closable:false
		       });*/
   Ext.MessageBox.show({
           title: '登入',
           msg: '登入中...',
           progressText: '加载...',
           width:300,
           progress:true,
           closable:false
       });

       // this hideous block creates the bogus progress
       var f = function(v){
            return function(){
                if(v == 12){
                 //   Ext.MessageBox.hide();
                }else{
                    var i = v/11;
                    Ext.MessageBox.updateProgress(i, Math.round(100*i)+'%');
                }
           };
       };
       for(var i = 1; i < 13; i++){
           setTimeout(f(i), i*500);
       }

        });
        
 
});