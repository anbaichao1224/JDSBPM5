Ext.namespace("Jame.menu");
//


Jame.menu.ImgItem = function(config){
    Jame.menu.ImgItem.superclass.constructor.call(this, new Jame.ImgPalette(config), config);
    /** The Ext.ColorPalette object @type Ext.ColorPalette */
    this.palette = this.component;
    this.relayEvents(this.palette, ["select"]);
    if(this.selectHandler){
        this.on('select', this.selectHandler, this.scope);
    }
};
Ext.extend(Jame.menu.ImgItem, Ext.menu.Adapter);

Jame.menu.ImgMenu2x =  function(config){
    Jame.menu.ImgMenu2x.superclass.constructor.call(this, config);
    this.plain = true;
    var ci = new Jame.menu.ImgItem(config);
    this.add(ci);
    /**
     * The {@link Ext.ColorPalette} instance for this ColorMenu
     * @type ColorPalette
     */
    this.palette = ci.palette;
    /**
     * @event select
     * @param {ColorPalette} palette
     * @param {String} color
     */
    this.relayEvents(ci, ["select"]);
};
Ext.extend(Jame.menu.ImgMenu2x, Ext.menu.Menu);