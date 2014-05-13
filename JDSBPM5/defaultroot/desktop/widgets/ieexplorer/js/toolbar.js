
IeExplorer.toolbar = {
	init : function(el){
		TopMenu_el = el.child('.iemenuEl');		
		this.extId=el.dom.id;	
		this.createTopMenu.call(IeExplorer.Actions, el);
		TopMenu_el.removeClass('x-menu-plain'); //hack the background-color
		this.createURIBar(el);
	},
	createTopMenu : function(el){
		var FullMenuBar = new Ext.Toolbar({
				renderTo : el.child('.iemenuEl'),
				autoShow:true,
		items:[
	/*
		{
		            text  :  '文件(F)',
					menu  :  [
						this.download,
				 		{
							text  :  '新建',
							icon  : '/desktop/widgets/explorer/images/new.gif',
							menu  :  {
								items  :  [
									this.createFolder,
									'-',
									this.upload,this.share
								]
							}
						},
						this.fileDelete,this.fileRename,'-',this.exit
					]
				},
*/{
		            text  :  '导航(E)',
					menu  :  [
						this.bjxwnet,this.ygyx,this.xfyf,this.xhsxx,this.abjjxx,
						'-',
						this.dzts,this.spdb,
						'-',
						this.wdzx,this.zhjj,this.dzyj,this.yqlj
					]
				}/*
,{
		            text  :  '查看(V)',
					menu  :  [
						this.fileAttrib,this.viewAS,this.sortBy,
				 		{
							text : '转到...',
							icon  : '/desktop/widgets/explorer/images/goto.gif',
							menu : {items :  [
								this.goParentFolder,this.goBack,this.goForward
							]}
						},'-',this.refresh
					]
				}
*/,{
		            text  :  '收藏夹(A)',
					menu  :  [
						this.addToFavorites
					]
				},
					{
		            text :  '工具(T)',
					menu : [
						this.saveAsDesk
					]},
				{
		            text  :  '帮助(H)',
					menu  :  [
						this.about
					]
				}
				
				
				]
	        });
		
		FullMenuBar.show(el.child('.iemenuEl'), "bl-bl");
	},
	
	/**
	 * @private
	 * Cobmo Box for URI Bar
	 */
	createURIBar  :  function(el){
	 var data=[];
		JDSDesk.allModules.eachKey(function(key){
		if (JDSDesk.allModules.get(key)&&JDSDesk.allModules.get(key).url){
			var item= [];
			item.push(key);
			item.push(JDSDesk.allModules.get(key).url);
			data.push(item);
			}
		});
	    var store = new Ext.data.SimpleStore({
	        fields :  ['abbr', 'state'],
	        data  : data
	    });
		var tb = new Ext.Toolbar([
			IeExplorer.Actions.goBackList,IeExplorer.Actions.goForwardList
		]);
		
		tb.render(el.child('.left_buttonsEl'));
		var _URIbarEl = el.child('.uribarEl');
		
		this.getModuleByFullName=function(fillName){
			JDSDesk.allModules.eachKey(function(key){
			var module=JDSDesk.allModules.get(key);
				if (module && module.fullTitle==fillName){
					return module;
				}
			  })
		}
	    this.URIbar = new Ext.form.ComboBox({
	        store :  store,
	        displayField : 'state',
	        mode :  'local',
	      //  triggerAction :  'all',
	        emptyText : 'http://www.xuanwu.gov.cn',
	        selectOnFocus : true,
	        width  :  500,
			typeAhead:true,
	        minListWidth : 60,
	        renderTo : _URIbarEl
			
	    });
		

		this.URIbar.on('select',function(){
			 JDSDesk.allModules.eachKey(function(key){
				var module=JDSDesk.allModules.get(key);
				if (module && module.fullTitle==this.getValue()){
					module.createWindow();
				}
				
			  },this)
			});
	    if(Ext.isIE){
		    this.URIbar.on('expand',function(){
		    	Ext.fly(document.body).child('.x-combo-list-inner').applyStyles(
		    	'top : 0px;left : 0px;');
		    });
		}

		var _rightButton = el.child('.right_buttonsEl');
	    _rightButton.createChild({
	    	tag : 'img',
	    	cls : 'left mouseOverwithHand',
	    	hspace : 2,
			tip:'刷新当前页面',
	    	src :' /desktop/widgets/explorer/images/gears.gif'
	    }).on('click',function(){
			Ext.get(this.extId+IeExplorer.toolbar.currPath).dom.src=this.URIbar.getValue();
		},this);
		/**
		 *@member {Ext.element} The button of upload
		 */
	    this.btnUpload = _rightButton.createChild({
	    	tag : 'img',
	    	id : 'btnUpload_upload',
	    	cls : 'mouseOverwithHand',
	    	hspace : 10,
	    	src : ' /desktop/widgets/explorer/images/accordian.gif'
	    });

	}
	
};