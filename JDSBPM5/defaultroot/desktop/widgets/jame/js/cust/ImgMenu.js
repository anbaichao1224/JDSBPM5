Ext.namespace("Jame.menu");
//
Jame.menu.ImgMenu = Ext.extend(Ext.menu.Menu, {

  enableScrolling: false,

  hideOnClick: true,
  editor:null,
  getEditor:function(){
    return this.editor;
  },
  setEditor:function(e){
    this.editor=e;
  },

  initComponent: function(){
      Ext.apply(this, {
          plain: true,
          showSeparator: false,
          items: this.palette = new Jame.ImgPalette(this.initialConfig)
      });
      this.palette.purgeListeners();
      Jame.menu.ImgMenu.superclass.initComponent.call(this);
      this.relayEvents(this.palette, ['select']);
      this.on('select', this.menuHide, this);
      if(this.handler){
          this.on('select', this.handler, this.scope || this)
      }
  },

  menuHide: function(){
      if(this.hideOnClick){
          this.hide(true);
      }
  }
});