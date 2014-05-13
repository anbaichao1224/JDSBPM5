

Ext.onReady(function(){

   Ext.QuickTips.init();
  
  var imgPanel = { 
	xtype:'panel', 
	html: "<img id='img' border='0' width='30' height='30' src='/upload/no.jpg'/>",
	border:false
	}; 

    var departtab = new Ext.Panel({
        labelWidth: 75,
        border:true,
        fileUpload: true,
        frame: true,
         width:784,
        height:590,
        title:'('+cnname+')详细信息',      
        html: '<iframe scrolling="yes" frameborder="0" src="getPersonDetailInfo.do?personid='+personid+'" height=100% width=100%></iframe>'

    });

    departtab.render(document.body);
});    