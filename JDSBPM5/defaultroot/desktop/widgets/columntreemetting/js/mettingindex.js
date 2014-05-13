function opentype(url,width,height){
	
	var win=new Ext.Window({
		 id:'addtype',
		 width:width,
	     height:height,
	     title:'会议类型',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:0 5px',
	     items:new Ext.Panel({
		        title: '',        
			  	height:height-50,
			  	bodyStyle:'width:100%',
		        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="100%"></iframe>'
	     })
	 });
	 win.show();
}
function updateMatter(url){
	
	var win=new Ext.Window({
		 id:'updateMatterInfo',
		 width:800,
	     height:700,
	     title:'事项信息',
	     closable:true,
		 collapsible:true,
	     autoScroll:true,
	     bodyStyle:'padding:0 5px',
	     items:new Ext.Panel({
			        title: '',        
				  	height:670,
				  	bodyStyle:'width:100%',
			        html: '<iframe frameborder="0" src="'+url+'" width="100%" height="670"></iframe>'
	     })
	 });
	 win.show();
}
function ctreload(){
	var obj = Ext.getCmp('addtype');
	Ext.getCmp('form-columntree').root.reload();
}